package org.pb.rumination.model.actions.quality;

import org.pb.rumination.model.EffectiveBuff;
import org.pb.rumination.model.actions.QualityAction;
import org.pb.rumination.model.actions.buff.FinalAppraisal;
import org.pb.rumination.model.actions.buff.InnerQuiet;
import org.pb.rumination.model.actions.other.RemoveFinalAppraisal;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public class Reflect extends QualityAction {

    public JobAndLevel getLevelRequirement() {
        return new JobAndLevel(JobAndLevel.CraftingJob.ANY, 69);
    }

    public boolean _canBeUsed(Simulation state, boolean linear, boolean safeMode) {
        return state.steps.stream().filter(step -> !step.action.equals(new FinalAppraisal()) && !step.action.equals(new RemoveFinalAppraisal())).toArray().length == 0;
    }

    public boolean canBeMoved(int currentIndex) {
        return currentIndex > 0;
    }

    public int getBaseDurabilityCost(Simulation state) {
        return 10;
    }

    public int getBaseSuccessRate(Simulation state) {
        return 100;
    }

    public int getBaseCPCost(Simulation state) {
        return 24;
    }

    public List<Integer> getIds() {
        return List.of(100387, 100388, 100389, 100390, 100391, 100392, 100393, 100394);
    }

    public int getPotency(Simulation state) {
        return getPotency(state, false);
    }

    public int getPotency(Simulation state, boolean safeMode) {
        return 100;
    }

    public void execute(Simulation state) {
        super.execute(state);
        InnerQuiet iq = new InnerQuiet();
        state.buffs.add(new EffectiveBuff(iq.getDuration(state), 3, state.steps.size(), iq.getBuff(), iq.getTickAction()));
    }
}
