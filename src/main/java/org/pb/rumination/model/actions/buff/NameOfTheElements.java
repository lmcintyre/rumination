package org.pb.rumination.model.actions.buff;

import org.pb.rumination.model.EffectiveBuff;
import org.pb.rumination.model.actions.BuffAction;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public class NameOfTheElements extends BuffAction {

    public JobAndLevel getLevelRequirement() {
        return new JobAndLevel(JobAndLevel.CraftingJob.ANY, 37);
    }

    public boolean _canBeUsed(Simulation state, boolean linear, boolean safeMode) {
        return super._canBeUsed(state) && !state.hasBuff(this.getBuff());
    }

    public int getBaseCPCost(Simulation state) {
        return 30;
    }

    public int getDuration(Simulation state) {
        return 3;
    }

    public List<Integer> getIds() {
        return List.of(4615, 4616, 4617, 4618, 4619, 4620, 4621, 4622);
    }

    public EffectiveBuff.Buff getBuff() {
        return EffectiveBuff.Buff.NAME_OF_THE_ELEMENTS;
    }

    public int getInitialStacks() {
        return 0;
    }

    public boolean canBeClipped() {
        return true;
    }

}
