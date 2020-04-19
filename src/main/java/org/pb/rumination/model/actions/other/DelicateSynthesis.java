package org.pb.rumination.model.actions.other;

import org.pb.rumination.model.EffectiveBuff;
import org.pb.rumination.model.actions.GeneralAction;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public class DelicateSynthesis extends GeneralAction {

    public JobAndLevel getLevelRequirement() {
        return new JobAndLevel(JobAndLevel.CraftingJob.ANY, 76);
    }

    public ActionType getType() {
        return ActionType.OTHER;
    }

    public boolean _canBeUsed(Simulation state, boolean linear, boolean safeMode) {
        return true;
    }

    public int getBaseCPCost(Simulation state) {
        return 32;
    }

    public int getBaseDurabilityCost(Simulation state) {
        return 10;
    }

    public int getBaseSuccessRate(Simulation state) {
        return 100;
    }

    public List<Integer> getIds() {
        return List.of(100323, 100324, 100325, 100326, 100327, 100328, 100329, 100330);
    }

    public int getPotency(Simulation state) {
        return 100;
    }

    public void execute(Simulation state) {
        execute(state, false);
    }

    public void execute(Simulation state, boolean linear) {

        int progressPotency = this.getPotency(state);
        int progressBonus = 1;

        if (state.hasBuff(EffectiveBuff.Buff.MUSCLE_MEMORY)) {
            progressBonus += 1;
            state.removeBuff(EffectiveBuff.Buff.MUSCLE_MEMORY);
        }

        if (state.hasBuff(EffectiveBuff.Buff.VENERATION))
            progressBonus += 0.5;
        state.progression += Math.floor((Math.floor(this.getBaseProgression(state)) * progressPotency * progressBonus) / 100 );
        if (state.hasBuff(EffectiveBuff.Buff.FINAL_APPRAISAL) && state.progression >= state.recipe.progress) {
            state.progression = Math.min(state.progression, state.recipe.progress - 1);
            state.removeBuff(EffectiveBuff.Buff.FINAL_APPRAISAL);
        }

        int qualityPotency = this.getPotency(state);
        int qualityBonus = 1;
        if (state.hasBuff(EffectiveBuff.Buff.GREAT_STRIDES)) {
            qualityBonus += 1;
            state.removeBuff(EffectiveBuff.Buff.GREAT_STRIDES);
        }

        if (state.hasBuff(EffectiveBuff.Buff.INNOVATION))
            qualityBonus += 0.5;

        int qualityIncrease = this.getBaseQuality(state);
        switch (state.state) {
            case EXCELLENT:
                qualityIncrease *= 4;
                break;
            case POOR:
                qualityIncrease *= 0.5;
                break;
            case GOOD:
                qualityIncrease *= 1.5;
                break;
            default:
                break;
        }

        state.quality += Math.floor((Math.floor(qualityIncrease) * qualityPotency * qualityBonus) / 100);
        if (state.hasBuff(EffectiveBuff.Buff.INNER_QUIET) && state.getBuff(EffectiveBuff.Buff.INNER_QUIET).stacks < 11)
            state.getBuff(EffectiveBuff.Buff.INNER_QUIET).stacks++;
    }
}
