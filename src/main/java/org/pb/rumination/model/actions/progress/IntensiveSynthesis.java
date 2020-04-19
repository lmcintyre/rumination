package org.pb.rumination.model.actions.progress;

import org.pb.rumination.model.actions.ProgressAction;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public class IntensiveSynthesis extends ProgressAction {

    public JobAndLevel getLevelRequirement() {
        return new JobAndLevel(JobAndLevel.CraftingJob.ANY, 78);
    }

    public boolean _canBeUsed(Simulation state, boolean linear, boolean safeMode) {
        if (linear)
            return true;
        if (state.safe)
            return false;
        return (state.state == Simulation.StepState.GOOD || state.state == Simulation.StepState.EXCELLENT);
    }

    public int getBaseDurabilityCost(Simulation state) {
        return 10;
    }

    public int getBaseSuccessRate(Simulation state) {
        return 100;
    }

    public int getBaseCPCost(Simulation state) {
        return 6;
    }

    public List<Integer> getIds() {
        return List.of(100315, 100316, 100317, 100318, 100319, 100320, 100321, 100322);
    }

    public int getPotency(Simulation state) {
        return getPotency(state, false);
    }

    public int getPotency(Simulation state, boolean safeMode) {
        return 300;
    }
}
