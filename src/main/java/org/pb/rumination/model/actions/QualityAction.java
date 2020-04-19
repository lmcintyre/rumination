package org.pb.rumination.model.actions;

import org.pb.rumination.model.EffectiveBuff;
import org.pb.rumination.sim.Simulation;

public abstract class QualityAction extends GeneralAction {
    public ActionType getType() {
        return ActionType.QUALITY;
    }

    public void execute(Simulation state) {
        execute(state, false);
    }

    public void execute(Simulation state, boolean safeMode) {
        int bonus = 1;
        int potency = this.getPotency(state);
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

        if (state.hasBuff(EffectiveBuff.Buff.GREAT_STRIDES)) {
            bonus += 1;
            state.removeBuff(EffectiveBuff.Buff.GREAT_STRIDES);
        }
        if (state.hasBuff(EffectiveBuff.Buff.INNOVATION))
            bonus += 0.5;

        state.quality += Math.floor((Math.floor(qualityIncrease) * potency * bonus) / 100f);

        if (state.hasBuff(EffectiveBuff.Buff.INNER_QUIET) && state.getBuff(EffectiveBuff.Buff.INNER_QUIET).stacks < 11) {
            state.getBuff(EffectiveBuff.Buff.INNER_QUIET).stacks++;
        }
    }
}
