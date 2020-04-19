package org.pb.rumination.model.actions.buff;

import org.pb.rumination.model.EffectiveBuff;
import org.pb.rumination.model.actions.BuffAction;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.model.misc.LevelDifference;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public class FinalAppraisal extends BuffAction {

    public JobAndLevel getLevelRequirement() {
        return new JobAndLevel(JobAndLevel.CraftingJob.ANY, 42);
    }

    public boolean _canBeUsed(Simulation state, boolean linear, boolean safeMode) {
        return super._canBeUsed(state);
    }

    public int getBaseCPCost(Simulation state) {
        return 1;
    }

    public int getDuration(Simulation state) {
        return 5;
    }

    public List<Integer> getIds() {
        return List.of(19012, 19013, 19014, 19015, 19016, 19017, 19018, 19019);
    }

    public EffectiveBuff.Buff getBuff() {
        return EffectiveBuff.Buff.FINAL_APPRAISAL;
    }

    public int getInitialStacks() {
        return 0;
    }

    public boolean canBeClipped() {
        return true;
    }
}
