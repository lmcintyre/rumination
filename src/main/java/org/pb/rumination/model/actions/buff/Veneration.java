package org.pb.rumination.model.actions.buff;

import org.pb.rumination.model.EffectiveBuff;
import org.pb.rumination.model.actions.BuffAction;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public class Veneration extends BuffAction {

    public JobAndLevel getLevelRequirement() {
        return new JobAndLevel(JobAndLevel.CraftingJob.ANY, 15);
    }

    public boolean _canBeUsed(Simulation state, boolean linear, boolean safeMode) {
        return super._canBeUsed(state);
    }

    public int getBaseCPCost(Simulation state) {
        return 18;
    }

    public int getDuration(Simulation state) {
        return 4;
    }

    public List<Integer> getIds() {
        return List.of(19297, 19298, 19299, 19300, 19301, 19302, 19303, 19304);
    }

    public EffectiveBuff.Buff getBuff() {
        return EffectiveBuff.Buff.VENERATION;
    }

    public int getInitialStacks() {
        return 0;
    }

    public boolean canBeClipped() {
        return true;
    }

}
