package org.pb.rumination.model.actions.quality;

import org.pb.rumination.model.actions.QualityAction;
import org.pb.rumination.model.actions.other.Observe;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public class FocusedTouch extends QualityAction {

    public JobAndLevel getLevelRequirement() {
        return new JobAndLevel(JobAndLevel.CraftingJob.ANY, 68);
    }

    public boolean _canBeUsed(Simulation state, boolean linear, boolean safeMode) {
        return true;
    }

    public int getBaseDurabilityCost(Simulation state) {
        return 10;
    }

    public int getBaseSuccessRate(Simulation state) {
        if (state.lastStep() != null) {
            if (new Observe().getIds().contains(state.lastStep().action.getIds().get(0)))
                return 100;
        }
        return 50;
    }

    public int getBaseCPCost(Simulation state) {
        return 18;
    }

    public List<Integer> getIds() {
        return List.of(100243, 100244, 100245, 100246, 100247, 100248, 100249, 100250);
    }

    public int getPotency(Simulation state) {
        return getPotency(state, false);
    }

    public int getPotency(Simulation state, boolean safeMode) {
        return 150;
    }
}
