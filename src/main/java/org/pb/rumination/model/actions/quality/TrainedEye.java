package org.pb.rumination.model.actions.quality;

import org.pb.rumination.model.actions.CraftAction;
import org.pb.rumination.model.actions.QualityAction;
import org.pb.rumination.model.actions.buff.FinalAppraisal;
import org.pb.rumination.model.actions.other.RemoveFinalAppraisal;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public class TrainedEye extends CraftAction {

    public JobAndLevel getLevelRequirement() {
        return new JobAndLevel(JobAndLevel.CraftingJob.ANY, 80);
    }

    public ActionType getType() {
        return ActionType.QUALITY;
    }

    public boolean _canBeUsed(Simulation state, boolean linear, boolean safeMode) {
        return state.steps.stream().filter(step -> !step.action.equals(new FinalAppraisal()) && !step.action.equals(new RemoveFinalAppraisal())).toArray().length == 0
                && state._crafterStats.level - state.recipe.lvl >= 10;
    }

    public int getDurabilityCost(Simulation state) {
        return 10;
    }

    public int _getSuccessRate(Simulation state) {
        return 100;
    }

    public int getBaseCPCost(Simulation state) {
        return 250;
    }

    public List<Integer> getIds() {
        return List.of(100283, 100284, 100285, 100286, 100287, 100288, 100289, 100290);
    }

    public void execute(Simulation state, boolean safeMode) {
        state.quality = state.recipe.quality;
    }
}
