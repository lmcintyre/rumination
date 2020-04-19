package org.pb.rumination.model.actions.quality;

import org.pb.rumination.model.EffectiveBuff;
import org.pb.rumination.model.actions.QualityAction;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public class PreciseTouch extends QualityAction {

    public JobAndLevel getLevelRequirement() {
        return new JobAndLevel(JobAndLevel.CraftingJob.ANY, 53);
    }

    public boolean _canBeUsed(Simulation state, boolean linear, boolean safeMode) {
        if (linear)
            return true;
        if (state.safe)
            return false;

        return state.state == Simulation.StepState.GOOD || state.state == Simulation.StepState.EXCELLENT;
    }

    public int getBaseDurabilityCost(Simulation state) {
        return 10;
    }

    public int getBaseSuccessRate(Simulation state) {
        return 60;
    }

    public int getBaseCPCost(Simulation state) {
        return 18;
    }

    public List<Integer> getIds() {
        return List.of(100128, 100129, 100130, 100131, 100132, 100133, 100134, 100135);
    }

    public int getPotency(Simulation state) {
        return getPotency(state, false);
    }

    public int getPotency(Simulation state, boolean safeMode) {
        return 150;
    }

    public boolean requiresGood() {
        return true;
    }

    public void execute(Simulation state, boolean safeMode) {
        super.execute(state, safeMode);

        if (state.hasBuff(EffectiveBuff.Buff.INNER_QUIET) && state.getBuff(EffectiveBuff.Buff.INNER_QUIET).stacks < 11) {
            state.getBuff(EffectiveBuff.Buff.INNER_QUIET).stacks++;
        }
    }
}
