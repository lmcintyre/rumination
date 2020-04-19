package org.pb.rumination.model.actions;

import org.pb.rumination.model.CrafterStats;
import org.pb.rumination.model.formulas.CraftLevelDifference;
import org.pb.rumination.model.Tables;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.model.misc.LevelDifference;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public abstract class CraftAction {

    public enum ActionType {
        PROGRESSION,
        QUALITY,
        CP_RECOVERY,
        BUFF,
        SPECIALTY,
        REPAIR,
        OTHER
    }

    public abstract List<Integer> getIds();
    public abstract ActionType getType();
    public abstract JobAndLevel getLevelRequirement();
    public abstract int _getSuccessRate(Simulation state);
    public abstract int getBaseCPCost(Simulation state);
    public abstract int getDurabilityCost(Simulation state);

    public abstract void execute(Simulation state, boolean safeMode);

    public boolean _canBeUsed(Simulation state) {
        return this._canBeUsed(state, false);
    }

    public boolean _canBeUsed(Simulation state, boolean linear) {
        return this._canBeUsed(state, linear, false);
    }

    public abstract boolean _canBeUsed(Simulation state, boolean linear, boolean safeMode);

    private int getSuccessRate(Simulation state) {
        int base = this._getSuccessRate(state);
        if (state.state == Simulation.StepState.CENTERED)
            return base + 25;
        return base;
    }

    public int getCPCost(Simulation state) {
        return getCPCost(state, false);
    }

    public int getCPCost(Simulation state, boolean linear) {
        int base = this.getBaseCPCost(state);
        if (state.state == Simulation.StepState.PLIANT) {
            return (int) Math.floor(base / 2f);
        }
        return base;
    }

    public int getId(int jobId) {
        List<Integer> ids = this.getIds();
        if (ids.size() < 8)
            return ids.get(0);
        return ids.get(jobId - 8);
    }

    public boolean isEmpty() { return false; }

    public int getWaitDuration() {
        return this.getType() == ActionType.BUFF ? 2 : 3;
    }

    public boolean requiresGood() {
        return false;
    }

    public boolean canBeUsed(Simulation state) {
        return canBeUsed(state, false, false);
    }

    public boolean canBeUsed(Simulation state, boolean linear) {
        return canBeUsed(state, linear, false);
    }

    public void onFail(Simulation state) {
        // Base onFail does nothing, override to implement it, as it wont be used in most cases.
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CraftAction))
            return false;
        // TODO test this?
        return this.getClass().equals(obj.getClass());
    }

    protected LevelDifference getLevelDifference(Simulation state) {
        int recipeLevel = state.recipe.rlvl;
        CrafterStats stats = state._crafterStats;
        int crafterLevel = Tables.LEVEL_TABLE.getOrDefault(stats.level, stats.level);
        int levelDifference = crafterLevel - recipeLevel;
        levelDifference = Math.min(49, Math.max(-30, levelDifference));

        for (LevelDifference diff : CraftLevelDifference.CraftLevelDifference)
            if (diff.difference == levelDifference)
                return diff;

        // i have failed
        return null;
    }

    public int getBaseProgression(Simulation state) {
        CrafterStats stats = state._crafterStats;

        return (
                (((stats.craftsmanship + 10000) / (state.recipe.suggestedCraftsmanship + 10000)) *
                        ((stats.craftsmanship * 21) / 100 + 2) *
                        this.getLevelDifference(state).progressFactor) /
                        100
        );
    }

    public int getBaseQuality(Simulation state) {
        CrafterStats stats = state._crafterStats;

        return (int) (
                (((stats.getControl(state) + 10000) / (double) (state.recipe.suggestedControl + 10000)) *
                        ((stats.getControl(state) * 35) / 100f + 35) *
                        this.getLevelDifference(state).qualityFactor) / 100f
        );
    }

    public boolean canBeUsed(Simulation state, boolean linear, boolean safeMode) {
        JobAndLevel levelReq = this.getLevelRequirement();
        int craftsmanshipReq = state.recipe.craftsmanshipReq;
        int controlReq = state.recipe.controlReq;

//        boolean meetsCraftReq = state.crafterStats.craftsmanship >= craftsmanshipReq;
//        boolean meetsControlReq = state.crafterStats._control >= controlReq;
//        boolean meetsLevelReq = state.crafterStats.

        if (safeMode && this.getSuccessRate(state) < 100)
            return false;

        if (levelReq.job != JobAndLevel.CraftingJob.ANY
            && state._crafterStats.crafterLevels[levelReq.job.getValue()] != 0) {
            return
                   state._crafterStats.crafterLevels[levelReq.job.getValue()] >= levelReq.level &&
                   this._canBeUsed(state, linear);
        }

        if (craftsmanshipReq > 0 && controlReq > 0) {
            return
                    state._crafterStats.craftsmanship >= craftsmanshipReq &&
                    state._crafterStats._control >= controlReq &&
                    state._crafterStats.level >= levelReq.level &&
                    this._canBeUsed(state, linear);
        }

        if (craftsmanshipReq > 0) {
            return
                    state._crafterStats.craftsmanship >= craftsmanshipReq &&
                    state._crafterStats.level >= levelReq.level &&
                    this._canBeUsed(state, linear);
        }

        if (controlReq > 0) {
            return
                    state._crafterStats._control >= controlReq &&
                    state._crafterStats.level >= levelReq.level &&
                    this._canBeUsed(state, linear);
        }

        return
                state._crafterStats.level >= levelReq.level &&
                this._canBeUsed(state, linear);
    }

    public Simulation.SimulationFailCause getFailCause(Simulation state, boolean linear, boolean safeMode) {
        JobAndLevel levelReq = this.getLevelRequirement();
        int craftsmanshipReq = state.recipe.craftsmanshipReq;
        int controlReq = state.recipe.controlReq;

        if (safeMode && this.getSuccessRate(state) < 100)
            return Simulation.SimulationFailCause.UNSAFE_ACTION;

        if (levelReq.job != JobAndLevel.CraftingJob.ANY
            && state._crafterStats.crafterLevels[levelReq.job.getValue()] != 0) {
            if (state._crafterStats.crafterLevels[levelReq.job.getValue()] < levelReq.level)
                return Simulation.SimulationFailCause.MISSING_LEVEL_REQUIREMENT;
        }

        if (state._crafterStats.level < levelReq.level)
            return Simulation.SimulationFailCause.MISSING_LEVEL_REQUIREMENT;
        if (craftsmanshipReq > 0 && state._crafterStats.craftsmanship < craftsmanshipReq)
            return Simulation.SimulationFailCause.MISSING_STATS_REQUIREMENT;
        if (controlReq > 0 && state._crafterStats._control < controlReq)
            return Simulation.SimulationFailCause.MISSING_STATS_REQUIREMENT;

        return Simulation.SimulationFailCause.UNDEFINED;
    }
}