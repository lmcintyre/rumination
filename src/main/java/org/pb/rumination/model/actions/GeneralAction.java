package org.pb.rumination.model.actions;

import org.pb.rumination.model.EffectiveBuff;
import org.pb.rumination.sim.Simulation;

public abstract class GeneralAction extends CraftAction {

    public abstract int getPotency(Simulation state);
    public abstract int getBaseDurabilityCost(Simulation state);
    public abstract int getBaseSuccessRate(Simulation state);

    public int getDurabilityCost(Simulation state) {
        double divider = 1;
        if (state.hasBuff(EffectiveBuff.Buff.WASTE_NOT) ||
            state.hasBuff(EffectiveBuff.Buff.WASTE_NOT_II))
            divider *= 2;
        if (state.state == Simulation.StepState.STURDY)
            divider *= 2;

        return (int) Math.ceil(this.getBaseDurabilityCost(state) / divider);
    }

    public int _getSuccessRate(Simulation state) {
        return this.getBaseSuccessRate(state);
    }

    public int getBaseBonus(Simulation state) {
        return 1;
    }
}
