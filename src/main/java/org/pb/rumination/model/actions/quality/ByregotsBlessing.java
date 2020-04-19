package org.pb.rumination.model.actions.quality;

import org.pb.rumination.model.EffectiveBuff;
import org.pb.rumination.model.actions.QualityAction;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public class ByregotsBlessing extends QualityAction {

    public JobAndLevel getLevelRequirement() {
        return new JobAndLevel(JobAndLevel.CraftingJob.ANY, 50);
    }

    public boolean _canBeUsed(Simulation state, boolean linear, boolean safeMode) {
        return state.hasBuff(EffectiveBuff.Buff.INNER_QUIET) && state.getBuff(EffectiveBuff.Buff.INNER_QUIET).stacks > 1;
    }

    public Simulation.SimulationFailCause getFailCause(Simulation state, boolean linear, boolean safeMode) {
        if (!state.hasBuff(EffectiveBuff.Buff.INNER_QUIET))
            return Simulation.SimulationFailCause.NO_INNER_QUIET;
        return super.getFailCause(state, linear, safeMode);
    }

    public void execute(Simulation state) {
        super.execute(state);
        state.removeBuff(EffectiveBuff.Buff.INNER_QUIET);
    }

    public int getBaseDurabilityCost(Simulation state) {
        return 10;
    }

    public int getBaseSuccessRate(Simulation state) {
        return 100;
    }

    public int getBaseCPCost(Simulation state) {
        return 24;
    }

    public List<Integer> getIds() {
        return List.of(100339, 100340, 100341, 100342, 100343, 100344, 100345, 100346);
    }

    public int getPotency(Simulation state) {
        return getPotency(state, false);
    }

    public int getPotency(Simulation state, boolean safeMode) {
        return 100 + (state.getBuff(EffectiveBuff.Buff.INNER_QUIET).stacks - 1) * 20;
    }
}
