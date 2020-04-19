package org.pb.rumination.model.actions.progress;

import org.pb.rumination.model.EffectiveBuff;
import org.pb.rumination.model.actions.ProgressAction;
import org.pb.rumination.model.actions.buff.FinalAppraisal;
import org.pb.rumination.model.actions.other.RemoveFinalAppraisal;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public class MuscleMemory extends ProgressAction {

    public JobAndLevel getLevelRequirement() {
        return new JobAndLevel(JobAndLevel.CraftingJob.ANY, 54);
    }

    public boolean canBeMoved(int currentIndex) {
        return currentIndex > 0;
    }

    public ActionType getType() {
        return ActionType.PROGRESSION;
    }

    public boolean _canBeUsed(Simulation state, boolean linear, boolean safeMode) {
        return state.steps.stream().filter(step -> !step.action.equals(new FinalAppraisal()) && !step.action.equals(new RemoveFinalAppraisal())).toArray().length == 0;
    }

    public int getBaseDurabilityCost(Simulation state) {
        return 10;
    }

    public int getBaseSuccessRate(Simulation state) {
        return 100;
    }

    public int getBaseCPCost(Simulation state) {
        return 6;
    }

    public List<Integer> getIds() {
        return List.of(100379, 100380, 100381, 100382, 100383, 100384, 100385, 100386);
    }

    public int getPotency(Simulation state) {
        return getPotency(state, false);
    }

    public int getPotency(Simulation state, boolean safeMode) {
        return 300;
    }

    public void execute(Simulation state) {
        execute(state, false);
    }

    public void execute(Simulation state, boolean safeMode) {
        super.execute(state, safeMode);
        state.buffs.add(new EffectiveBuff(5, 0, state.steps.size(), EffectiveBuff.Buff.MUSCLE_MEMORY, null));
    }
}
