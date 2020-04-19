package org.pb.rumination.model.actions.quality;

import org.pb.rumination.model.actions.QualityAction;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public class BasicTouch extends QualityAction {

    public JobAndLevel getLevelRequirement() {
        return new JobAndLevel(JobAndLevel.CraftingJob.ANY, 5);
    }

    public boolean _canBeUsed(Simulation state, boolean linear, boolean safeMode) {
        return true;
    }

    public int getBaseDurabilityCost(Simulation state) {
        return 10;
    }

    public int getBaseSuccessRate(Simulation state) {
        return 100;
    }

    public int getBaseCPCost(Simulation state) {
        return 18;
    }

    public List<Integer> getIds() {
        return List.of(100002, 100016, 100031, 100076, 100046, 100061, 100091, 100106);
    }

    public int getPotency(Simulation state) {
        return getPotency(state, false);
    }

    public int getPotency(Simulation state, boolean safeMode) {
        return 100;
    }
}
