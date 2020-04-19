package org.pb.rumination.model.actions.progress;

import org.pb.rumination.model.actions.ProgressAction;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public class BasicSynthesis extends ProgressAction {

    public JobAndLevel getLevelRequirement() {
        return new JobAndLevel(JobAndLevel.CraftingJob.ANY, 1);
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
        return 0;
    }

    public List<Integer> getIds() {
        return List.of(100001, 100015, 100030, 100075, 100045, 100060, 100090, 100105);
    }

    public int getPotency(Simulation state) {
        return getPotency(state, false);
    }

    public int getPotency(Simulation state, boolean safeMode) {
        if (state._crafterStats.level >= 31) {
            return 120;
        }
        return 100;
    }
}
