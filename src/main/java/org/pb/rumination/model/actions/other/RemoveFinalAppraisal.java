package org.pb.rumination.model.actions.other;

import org.pb.rumination.model.EffectiveBuff;
import org.pb.rumination.model.actions.CraftAction;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public class RemoveFinalAppraisal extends CraftAction {

    public JobAndLevel getLevelRequirement() {
        return new JobAndLevel(JobAndLevel.CraftingJob.ANY, 42);
    }

    public ActionType getType() {
        return ActionType.OTHER;
    }

    public boolean _canBeUsed(Simulation state, boolean linear, boolean safeMode) {
        return state.hasBuff(EffectiveBuff.Buff.FINAL_APPRAISAL);
    }

    public int getBaseCPCost(Simulation state) {
        return 0;
    }

    public int getDurabilityCost(Simulation state) {
        return 0;
    }

    public int _getSuccessRate(Simulation state) {
        return 100;
    }

    public List<Integer> getIds() {
        return List.of(-1);
    }

    public void execute(Simulation state) {
        execute(state, false);
    }

    public void execute(Simulation state, boolean linear) { state.removeBuff(EffectiveBuff.Buff.FINAL_APPRAISAL); }
}
