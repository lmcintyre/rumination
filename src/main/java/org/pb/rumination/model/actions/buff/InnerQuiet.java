package org.pb.rumination.model.actions.buff;

import org.pb.rumination.model.EffectiveBuff;
import org.pb.rumination.model.actions.BuffAction;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public class InnerQuiet extends BuffAction {

    public JobAndLevel getLevelRequirement() {
        return new JobAndLevel(JobAndLevel.CraftingJob.ANY, 11);
    }

    public boolean _canBeUsed(Simulation state, boolean linear, boolean safeMode) {
        return super._canBeUsed(state) && !state.hasBuff(this.getBuff());
    }

    public int getBaseCPCost(Simulation state) {
        return 18;
    }

    public int getDuration(Simulation state) {
        return 9999;
    }

    public List<Integer> getIds() {
        return List.of(252, 253, 254, 255, 256, 257, 258, 259);
    }

    public EffectiveBuff.Buff getBuff() {
        return EffectiveBuff.Buff.INNER_QUIET;
    }

    public int getInitialStacks() {
        return 1;
    }
}
