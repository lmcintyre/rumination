package org.pb.rumination.model.actions.buff;

import org.pb.rumination.model.EffectiveBuff;
import org.pb.rumination.model.actions.BuffAction;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public class GreatStrides extends BuffAction {

    public JobAndLevel getLevelRequirement() {
        return new JobAndLevel(JobAndLevel.CraftingJob.ANY, 21);
    }

    public boolean _canBeUsed(Simulation state, boolean linear, boolean safeMode) {
        return super._canBeUsed(state);
    }

    public int getBaseCPCost(Simulation state) {
        return 32;
    }

    public int getDuration(Simulation state) {
        return 3;
    }

    public List<Integer> getIds() {
        return List.of(260, 261, 262, 263, 264, 265, 266, 267);
    }

    public EffectiveBuff.Buff getBuff() {
        return EffectiveBuff.Buff.GREAT_STRIDES;
    }

    public int getInitialStacks() {
        return 0;
    }

    public boolean canBeClipped() {
        return true;
    }
}
