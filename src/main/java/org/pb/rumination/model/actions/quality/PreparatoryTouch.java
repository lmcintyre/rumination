package org.pb.rumination.model.actions.quality;

import org.pb.rumination.model.EffectiveBuff;
import org.pb.rumination.model.actions.QualityAction;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public class PreparatoryTouch extends QualityAction {

    public JobAndLevel getLevelRequirement() {
        return new JobAndLevel(JobAndLevel.CraftingJob.ANY, 71);
    }

    public boolean _canBeUsed(Simulation state, boolean linear, boolean safeMode) {
        return true;
    }

    public int getBaseDurabilityCost(Simulation state) {
        return 20;
    }

    public int getBaseSuccessRate(Simulation state) {
        return 100;
    }

    public int getBaseCPCost(Simulation state) {
        return 40;
    }

    public List<Integer> getIds() {
        return List.of(100299, 100300, 100301, 100302, 100303, 100304, 100305, 100306);
    }

    public int getPotency(Simulation state) {
        return getPotency(state, false);
    }

    public int getPotency(Simulation state, boolean safeMode) {
        return 200;
    }

    public void execute(Simulation state) {
        super.execute(state);

        if (state.hasBuff(EffectiveBuff.Buff.INNER_QUIET) && state.getBuff(EffectiveBuff.Buff.INNER_QUIET).stacks < 11) {
            state.getBuff(EffectiveBuff.Buff.INNER_QUIET).stacks++;
        }
    }
}
