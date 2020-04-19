package org.pb.rumination.model.actions.other;

import org.pb.rumination.model.actions.CraftAction;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public class Observe extends CraftAction {

    public JobAndLevel getLevelRequirement() {
        return new JobAndLevel(JobAndLevel.CraftingJob.ANY, 13);
    }

    public ActionType getType() {
        return ActionType.OTHER;
    }

    public boolean _canBeUsed(Simulation state, boolean linear, boolean safeMode) {
        return true;
    }

    public int getBaseCPCost(Simulation state) {
        return 7;
    }

    public int getDurabilityCost(Simulation state) {
        return 0;
    }

    public int _getSuccessRate(Simulation state) {
        return 100;
    }

    public List<Integer> getIds() {
        return List.of(100010, 100023, 100040, 100053, 100070, 100082, 100099, 100113);
    }

    public void execute(Simulation state) {
        execute(state, false);
    }

    public void execute(Simulation state, boolean linear) {}
}
