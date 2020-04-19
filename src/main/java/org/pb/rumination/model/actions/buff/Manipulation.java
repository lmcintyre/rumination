package org.pb.rumination.model.actions.buff;

import org.pb.rumination.model.EffectiveBuff;
import org.pb.rumination.model.actions.BuffAction;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.model.misc.TickAction;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public class Manipulation extends BuffAction {

    public ActionType getType() {
        return ActionType.REPAIR;
    }

    public JobAndLevel getLevelRequirement() {
        return new JobAndLevel(JobAndLevel.CraftingJob.ANY, 65);
    }

    public boolean _canBeUsed(Simulation state, boolean linear, boolean safeMode) {
        return super._canBeUsed(state);
    }

    public int getBaseCPCost(Simulation state) {
        return 96;
    }

    public int getDuration(Simulation state) {
        return 8;
    }

    public List<Integer> getIds() {
        return List.of(4574, 4575, 4576, 4577, 4578, 4579, 4580, 4581);
    }

    public EffectiveBuff.Buff getBuff() {
        return EffectiveBuff.Buff.MANIPULATION;
    }

    public int getInitialStacks() {
        return 0;
    }

    public boolean canBeClipped() {
        return true;
    }

    public TickAction getTickAction() {
        return new TickAction(TickAction.Type.REPAIR, 5);
    }
}
