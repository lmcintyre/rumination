package org.pb.rumination.sim;

import org.pb.rumination.model.*;
import org.pb.rumination.model.actions.CraftAction;
import org.pb.rumination.model.actions.buff.*;
import org.pb.rumination.model.actions.other.MastersMend;
import org.pb.rumination.model.actions.other.RemoveFinalAppraisal;
import org.pb.rumination.model.actions.progress.Groundwork;
import org.pb.rumination.model.actions.progress.MuscleMemory;
import org.pb.rumination.model.actions.progress.RapidSynthesis;
import org.pb.rumination.model.actions.quality.*;
import org.pb.rumination.model.misc.IngredientElement;
import org.pb.rumination.model.misc.PostBuffTick;
import org.pb.rumination.model.misc.StateRate;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Ported from Teamcraft's crafting simulator! Thanks
 */

public class Simulation {

    public enum StepState {
        // All
        NORMAL,
        GOOD,
        // Non expert
        EXCELLENT,
        POOR,
        // Expert only
        CENTERED, // success rate + 25
        STURDY, // durability loss / 2
        PLIANT, // CP cost / 2
        FAILED // ?
    }

    public enum SimulationFailCause {
        // Only used for safe mode, this is for when safe mode is enabled and action success rate is <100 at this moment.
        UNSAFE_ACTION,
        DURABILITY_REACHED_ZERO,
        NOT_ENOUGH_CP,
        MISSING_LEVEL_REQUIREMENT,
        MISSING_STATS_REQUIREMENT,
        NOT_SPECIALIST,
        NO_INNER_QUIET,
        // Added for Java
        UNDEFINED
    }

    public int availableCP;
    public int maxCP;
    public int progression = 0;
    public int quality = 0;
    public int startingQuality = 0;
    public int durability;

    public StepState state = StepState.NORMAL;
    public List<EffectiveBuff> buffs;
    public List<ActionResult> steps;

    public boolean safe = false;

    // 0 = in progress, 1 = success, -1 = fail
    public int success = 0;

    // equals the index of the last step where you have CP/durability for Reclaim,
    // or -1 if Reclaim is uncastable (i.e. not enough CP)
    public int lastPossibleReclaimStep = -1;

    public Craft recipe;
    public List<CraftAction> actions;
    public CrafterStats _crafterStats;
    public List<IngredientElement> hqIngredients;
    public Map<Integer, StepState> stepStates;

    public Simulation(Craft recipe, List<CraftAction> actions, CrafterStats _crafterStats,
                      List<IngredientElement> hqIngredients, Map<Integer, StepState> stepStates,
                      int startingQuality) {
        this.recipe = recipe;
        this.actions = actions;
        this._crafterStats = _crafterStats;
        this.hqIngredients = hqIngredients;
        this.stepStates = stepStates;
        this.startingQuality = startingQuality;

        this.buffs = new ArrayList<>();
        this.steps = new ArrayList<>();

        this.durability = recipe.durability;
        this.availableCP = this._crafterStats.cp;
        this.maxCP = this.availableCP;

        for (IngredientElement element : hqIngredients) {
            //TODO how slow is this omegalul
            var details =
                    Arrays.stream(this.recipe.ingredients)
                            .filter(ingredient -> Integer.parseInt(ingredient.id) == element.id)
                            .findFirst();
            Ingredient ingredientDetails;
            if (details.isPresent()) {
                ingredientDetails = details.get();
                this.quality += ingredientDetails.quality * ingredientDetails.amount;
            }
        }
        if (this.hqIngredients.size() == 0)
            this.quality = startingQuality;
        this.quality = (int) Math.floor(this.quality);
        this.startingQuality = this.quality;
    }

    public SimulationResults run(boolean linear, int maxTurns, boolean safeMode) {
        this.lastPossibleReclaimStep = -1;
        for (int i = 0; i < actions.size(); i++) {
            CraftAction action = actions.get(i);

            StepState baseStep = this.stepStates.getOrDefault(i, StepState.NORMAL);

            // what to do?
            if (this.stepStates.get(i) != StepState.FAILED) {

            }

            ActionResult result;
            SimulationFailCause failCause = null;
            boolean canUseAction = action.canBeUsed(this, linear);
            if (!canUseAction)
                failCause = action.getFailCause(this, linear, safeMode);

            boolean hasEnoughCP = action.getBaseCPCost(this) <= this.availableCP;
            if (!hasEnoughCP)
                failCause = SimulationFailCause.NOT_ENOUGH_CP;

            if (this.success == 0 &&
                hasEnoughCP &&
                this.steps.size() < maxTurns &&
                canUseAction) {
                result = this.runAction(action, linear, safeMode, i);
            } else {
                result = new ActionResult(action, false, failCause, 0, 0, 0, 0, true, this.state, null);
            }

            if (this.steps.size() <= maxTurns) {
                int qualityBefore = this.quality;
                int progressionBefore = this.progression;
                int durabilityBefore = this.durability;
                int cpBefore = this.availableCP;

                if (this.success == 0 &&
                    !action.equals(new FinalAppraisal()) &&
                    !action.equals(new RemoveFinalAppraisal())) {
                    this.tickBuffs(linear);
                }

                result.postBuffTick = new PostBuffTick(this.progression - progressionBefore,
                                                        this.quality - qualityBefore,
                                                        this.availableCP - cpBefore,
                                                        this.durability - durabilityBefore);
            }

            if (!linear && !action.equals(new FinalAppraisal()) & !action.equals(new RemoveFinalAppraisal()))
                this.tickState();

            this.steps.add(result);
        }

        Optional<ActionResult> failedAction = this.steps.stream().filter(step -> step.failCause != null && step.failCause != SimulationFailCause.UNDEFINED).findFirst();
        SimulationResults res = new SimulationResults(new ArrayList<>(this.steps), this.getHQPercent(), this.progression >= this.recipe.progress, this);

        if (failedAction.isPresent() &&
                failedAction.get().failCause != null &&
                failedAction.get().failCause != SimulationFailCause.UNDEFINED) {
            res.failCause = failedAction.get().failCause;
        }
        return res;
    }

    public ActionResult runAction(CraftAction action, boolean linear, boolean safeMode, int index) {
        int probabilityRoll = linear ? 0 : (int) (Math.random() * 100);
        if (this.stepStates.get(index) == StepState.FAILED)
            probabilityRoll = 999;

        int qualityBefore = this.quality;
        int progressionBefore = this.progression;
        int durabilityBefore = this.durability;
        int cpBefore = this.availableCP;

        SimulationFailCause failCause = null;
        boolean success = false;

        if (safeMode && (action._getSuccessRate(this) < 100 || action.requiresGood())) {
            failCause = SimulationFailCause.UNSAFE_ACTION;
            action.onFail(this);
            this.safe = false;
        } else {
            if (action._getSuccessRate(this) >= probabilityRoll) {
                action.execute(this, safeMode);
                success = true;
            } else {
                action.onFail(this);
            }
        }

        this.durability -= action.getDurabilityCost(this);
        this.availableCP -= action.getCPCost(this, linear);
        if (this.progression >= this.recipe.progress) {
            this.success = 1;
        } else if (this.durability <= 0) {
            failCause = SimulationFailCause.DURABILITY_REACHED_ZERO;
            this.success = -1;
        }

        return new ActionResult(action,
                success,
                failCause,
                this.progression - progressionBefore,
                this.quality - qualityBefore,
                this.availableCP - cpBefore,
                this.durability - durabilityBefore,
                false,
                this.state,
                null);
    }

    public SimulationReliabilityReport getReliabilityReport() {
        return getReliabilityReport(200);
    }

    public SimulationReliabilityReport getReliabilityReport(int runs) {
        this.reset();
        List<SimulationResults> results = new ArrayList<>();
        for (int i = 0; i < runs; i++) {
            results.add(this.run(false, 9999, false));
            this.reset();
        }

        int successPercent = (int) ((results.stream().filter(res -> res.success).toArray().length / (double) results.size()) * 100);
        int hqPercent = results.stream().reduce(0, (p, c) -> p + c.hqPercent, Integer::sum) / results.size();
        int hqMedian = 0;

        List<Integer> newResults = results.stream().map(res -> res.hqPercent).sorted(Comparator.comparingInt(a -> a)).collect(Collectors.toList());

        if (newResults.size() % 2 != 0) {
            hqMedian = newResults.get(newResults.size() / 2);
        } else {
            hqMedian = (newResults.get((int) Math.floor(newResults.size() / 2)) +
                        newResults.get((int) Math.ceil(newResults.size() / 2))) / 2;
        }

        return new SimulationReliabilityReport(results, Math.round(successPercent), hqMedian, Math.round(hqPercent));
    }

    public ActionResult lastStep() {
        if (this.steps.size() == 0)
            return null;
        return this.steps.get(this.steps.size() - 1);
    }

    public CrafterStats getCrafterStats() {
        return this._crafterStats;
    }

    public boolean hasBuff(EffectiveBuff.Buff buff) {
        return this.buffs.stream().anyMatch(effectives -> effectives.buff == buff);
    }

    public EffectiveBuff getBuff(EffectiveBuff.Buff buff) {
        return this.buffs.stream().filter(effectives -> effectives.buff == buff).findFirst().orElse(null);
    }

    public void removeBuff(EffectiveBuff.Buff buff) {
        this.buffs = this.buffs.stream().filter(effectives -> effectives.buff != buff).collect(Collectors.toList());
    }

    public void repair(int amount) {
        this.durability += amount;
        if (this.durability > this.recipe.durability)
            this.durability = this.recipe.durability;
    }

    protected Simulation clone() {
        return new Simulation(this.recipe, this.actions, this._crafterStats, this.hqIngredients, this.stepStates, this.startingQuality);
    }

    public void reset() {
        this.success = 0;
        this.progression = 0;
        this.durability = this.recipe.durability;
        this.quality = this.startingQuality;
        this.buffs.clear();
        this.steps = new ArrayList<>();
        this.maxCP = this._crafterStats.cp;
        this.availableCP = this.maxCP;
        this.safe = false;
    }

    private int getHQPercent() {
        double qualityPercent = Math.min(this.quality / (double) this.recipe.quality, 1) * 100;
        if (qualityPercent == 0.0)
            return 1;
        else if (qualityPercent >= 100)
            return 100;
        else
            return Tables.HQ_TABLE[(int) Math.floor(qualityPercent)];
    }

    private void tickBuffs() {
        tickBuffs(false);
    }

    private void tickBuffs(boolean linear) {
        for (EffectiveBuff effectiveBuff : this.buffs) {
            if (effectiveBuff.appliedStep < this.steps.size()) {
                effectiveBuff.tick(this, linear);
            }
            effectiveBuff.duration--;
        }
        this.buffs = this.buffs.stream().filter(effectives -> effectives.duration > 0).collect(Collectors.toList());
    }

    public void tickState() {
        if (this.state == StepState.EXCELLENT) {
            this.state = StepState.POOR;
            return;
        }

        int recipeLevel = this.recipe.rlvl;
        boolean qualityAssurance = this._crafterStats.level >= 63;

        double goodChances = 0;
        if (recipeLevel >= 300)
            goodChances = qualityAssurance ? 0.11 : 0.1;
        else if (recipeLevel >= 276)
            goodChances = qualityAssurance ? 0.17 : 0.15;
        else if (recipeLevel >= 255)
            goodChances = qualityAssurance ? 0.22 : 0.2;
        else if (recipeLevel >= 150)
            goodChances = qualityAssurance ? 0.11 : 0.1;
        else if (recipeLevel >= 136)
            goodChances = qualityAssurance ? 0.17 : 0.15;
        else
            goodChances = qualityAssurance ? 0.27 : 0.25;

        double excellentChances = 0;
        if (recipeLevel >= 300)
            excellentChances = 0.01;
        else if (recipeLevel >= 255)
            excellentChances = 0.02;
        else if (recipeLevel >= 150)
            excellentChances = 0.01;
        else
            excellentChances = 0.02;

        double finalGoodChances = goodChances;
        double finalExcellentChances = excellentChances;
        ArrayList<StateRate> states = new ArrayList<>() {{
            add(new StateRate(StepState.GOOD, finalGoodChances));
        }};

        if (this.recipe.expert) {
            states.add(new StateRate(StepState.CENTERED, 0.25));
            states.add(new StateRate(StepState.STURDY, 0.25));
            states.add(new StateRate(StepState.PLIANT, 0.25));
        } else {
            states.add(new StateRate(StepState.EXCELLENT, finalExcellentChances));
        }

        states.add(new StateRate(StepState.NORMAL, 1));

        for (StateRate state : states) {
            double roll = Math.random();
            if (roll <= state.rate) {
                this.state = state.state;
                break;
            }
        }
    }
}
