package org.pb.rumination.model.actions;

import org.pb.rumination.model.EffectiveBuff;
import org.pb.rumination.sim.Simulation;

public abstract class ProgressAction extends GeneralAction {
    public ActionType getType() {
        return ActionType.PROGRESSION;
    }

    public void execute(Simulation state) {
        execute(state, false);
    }

    public void execute(Simulation state, boolean safeMode) {
        int potency = this.getPotency(state);
        int bonus = this.getBaseBonus(state);

        if (state.hasBuff(EffectiveBuff.Buff.MUSCLE_MEMORY)) {
            bonus += 1;
            state.removeBuff(EffectiveBuff.Buff.MUSCLE_MEMORY);
        }
        if (state.hasBuff(EffectiveBuff.Buff.VENERATION))
            bonus += 0.5;

        double addition = (Math.floor(this.getBaseProgression(state)) * potency * bonus) / 100;
        state.progression += (int) Math.floor(addition);

        if (state.hasBuff(EffectiveBuff.Buff.FINAL_APPRAISAL) && state.progression >= state.recipe.progress) {
            state.progression = Math.min(state.progression, state.recipe.progress - 1);
            state.removeBuff(EffectiveBuff.Buff.FINAL_APPRAISAL);
        }
    }
}
