package org.pb.rumination.model.actions;

import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public class EmptyAction extends CraftAction {

    @Override
    public List<Integer> getIds() {
        return List.of(0);
    }

    @Override
    public ActionType getType() {
        return ActionType.OTHER;
    }

    @Override
    public JobAndLevel getLevelRequirement() {
        return new JobAndLevel(JobAndLevel.CraftingJob.ANY, 0);
    }

    @Override
    public int _getSuccessRate(Simulation state) {
        return 100;
    }

    @Override
    public int getBaseCPCost(Simulation state) {
        return 0;
    }

    @Override
    public int getDurabilityCost(Simulation state) {
        return 0;
    }

    @Override
    public void execute(Simulation state, boolean safeMode) {

    }

    @Override
    public boolean _canBeUsed(Simulation state, boolean linear, boolean safeMode) {
        return true;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
