package org.pb.rumination.model.actions.progress;

import org.pb.rumination.model.actions.ProgressAction;
import org.pb.rumination.model.actions.other.Observe;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public class Groundwork extends ProgressAction {

    public JobAndLevel getLevelRequirement() {
        return new JobAndLevel(JobAndLevel.CraftingJob.ANY, 72);
    }

    public boolean _canBeUsed(Simulation state, boolean linear, boolean safeMode) {
        return true;
    }

    public int getBaseDurabilityCost(Simulation state) {
        return 20;
    }

    public int getBaseSuccessRate(Simulation state) {
        return 100;
    }

    public int getBaseCPCost(Simulation state) {
        return 18;
    }

    public List<Integer> getIds() {
        return List.of(100403, 100404, 100405, 100406, 100407, 100408, 100409, 100410);
    }

    public int getPotency(Simulation state) {
        return getPotency(state, false);
    }

    public int getPotency(Simulation state, boolean safeMode) {
        if (state.durability >= this.getDurabilityCost(state))
            return 300;
        return 150;
    }
}
