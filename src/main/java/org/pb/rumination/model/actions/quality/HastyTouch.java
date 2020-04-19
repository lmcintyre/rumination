package org.pb.rumination.model.actions.quality;

import org.pb.rumination.model.actions.QualityAction;
import org.pb.rumination.model.actions.other.Observe;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public class HastyTouch extends QualityAction {

    public JobAndLevel getLevelRequirement() {
        return new JobAndLevel(JobAndLevel.CraftingJob.ANY, 9);
    }

    public boolean _canBeUsed(Simulation state, boolean linear, boolean safeMode) {
        return true;
    }

    public int getBaseDurabilityCost(Simulation state) {
        return 10;
    }

    public int getBaseSuccessRate(Simulation state) {
        return 60;
    }

    public int getBaseCPCost(Simulation state) {
        return 0;
    }

    public List<Integer> getIds() {
        return List.of(100355, 100356, 100357, 100358, 100359, 100360, 100361, 100362);
    }

    public int getPotency(Simulation state) {
        return getPotency(state, false);
    }

    public int getPotency(Simulation state, boolean safeMode) {
        return 100;
    }
}
