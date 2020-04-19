package org.pb.rumination.model.actions.progress;

import org.pb.rumination.model.EffectiveBuff;
import org.pb.rumination.model.actions.ProgressAction;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public class CarefulSynthesis extends ProgressAction {

    public JobAndLevel getLevelRequirement() {
        return new JobAndLevel(JobAndLevel.CraftingJob.ANY, 62);
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
        return 7;
    }

    public List<Integer> getIds() {
        return List.of(100203, 100204, 100205, 100206, 100207, 100208, 100209, 100210);
    }

    public int getPotency(Simulation state) {
        return getPotency(state, false);
    }

    public int getPotency(Simulation state, boolean safeMode) {
        return 150;
    }
}
