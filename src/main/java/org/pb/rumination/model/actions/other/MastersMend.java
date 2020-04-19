package org.pb.rumination.model.actions.other;

import org.pb.rumination.model.actions.CraftAction;
import org.pb.rumination.model.actions.GeneralAction;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public class MastersMend extends CraftAction {

    public JobAndLevel getLevelRequirement() {
        return new JobAndLevel(JobAndLevel.CraftingJob.ANY, 7);
    }

    public ActionType getType() {
        return ActionType.REPAIR;
    }

    public boolean _canBeUsed(Simulation state, boolean linear, boolean safeMode) {
        return true;
    }

    public int getBaseCPCost(Simulation state) {
        return 88;
    }

    public int getDurabilityCost(Simulation state) {
        return 0;
    }

    public int _getSuccessRate(Simulation state) {
        return 100;
    }

    public List<Integer> getIds() {
        return List.of(100003, 100017, 100032, 100047, 100062, 100077, 100092, 100107);
    }

    public void execute(Simulation state) {
        execute(state, false);
    }

    public void execute(Simulation state, boolean linear) {
        state.repair(30);
    }
}
