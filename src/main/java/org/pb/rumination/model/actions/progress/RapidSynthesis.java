package org.pb.rumination.model.actions.progress;

import org.pb.rumination.model.EffectiveBuff;
import org.pb.rumination.model.actions.ProgressAction;
import org.pb.rumination.model.actions.buff.FinalAppraisal;
import org.pb.rumination.model.actions.other.RemoveFinalAppraisal;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public class RapidSynthesis extends ProgressAction {

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
        return 50;
    }

    public int getBaseCPCost(Simulation state) {
        return 0;
    }

    public List<Integer> getIds() {
        return List.of(100363, 100364, 100365, 100366, 100367, 100368, 100369, 100370);
    }

    public int getPotency(Simulation state) {
        return getPotency(state, false);
    }

    public int getPotency(Simulation state, boolean safeMode) {
        if (state._crafterStats.level >= 63)
            return 500;
        return 250;
    }
}
