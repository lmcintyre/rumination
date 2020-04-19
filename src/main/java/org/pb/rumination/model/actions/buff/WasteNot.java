package org.pb.rumination.model.actions.buff;

import org.pb.rumination.model.EffectiveBuff;
import org.pb.rumination.model.actions.BuffAction;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.sim.Simulation;

import java.util.ArrayList;
import java.util.List;

public class WasteNot extends BuffAction {

    public JobAndLevel getLevelRequirement() {
        return new JobAndLevel(JobAndLevel.CraftingJob.ANY, 15);
    }

    public boolean _canBeUsed(Simulation state, boolean linear, boolean safeMode) {
        return super._canBeUsed(state);
    }

    public int getBaseCPCost(Simulation state) {
        return 56;
    }

    public int getDuration(Simulation state) {
        return 4;
    }

    public List<Integer> getIds() {
        return List.of(4631, 4632, 4633, 4634, 4635, 4636, 4637, 4638);
    }

    public EffectiveBuff.Buff getBuff() {
        return EffectiveBuff.Buff.WASTE_NOT;
    }

    public List<EffectiveBuff.Buff> getOverrides() {
        List<EffectiveBuff.Buff> base = new ArrayList<>(super.getOverrides());
        base.add(EffectiveBuff.Buff.WASTE_NOT_II);
        return base;
    }

    public int getInitialStacks() {
        return 0;
    }

    public boolean canBeClipped() {
        return true;
    }
}
