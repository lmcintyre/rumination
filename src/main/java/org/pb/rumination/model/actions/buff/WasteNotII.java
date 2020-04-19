package org.pb.rumination.model.actions.buff;

import org.pb.rumination.model.EffectiveBuff;
import org.pb.rumination.model.actions.BuffAction;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.sim.Simulation;

import java.util.ArrayList;
import java.util.List;

public class WasteNotII extends BuffAction {

    public JobAndLevel getLevelRequirement() {
        return new JobAndLevel(JobAndLevel.CraftingJob.ANY, 47);
    }

    public boolean _canBeUsed(Simulation state, boolean linear, boolean safeMode) {
        return super._canBeUsed(state);
    }

    public int getBaseCPCost(Simulation state) {
        return 98;
    }

    public int getDuration(Simulation state) {
        return 8;
    }

    public List<Integer> getIds() {
        return List.of(4639, 4640, 4641, 4642, 4643, 4644, 19002, 19003);
    }

    public EffectiveBuff.Buff getBuff() {
        return EffectiveBuff.Buff.WASTE_NOT_II;
    }

    public List<EffectiveBuff.Buff> getOverrides() {
        List<EffectiveBuff.Buff> base = new ArrayList<>(super.getOverrides());
        base.add(EffectiveBuff.Buff.WASTE_NOT);
        return base;
    }

    public int getInitialStacks() {
        return 0;
    }

    public boolean canBeClipped() {
        return true;
    }

}
