package org.pb.rumination.model.actions.quality;

import org.pb.rumination.model.EffectiveBuff;
import org.pb.rumination.model.actions.QualityAction;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public class PatientTouch extends QualityAction {

    public JobAndLevel getLevelRequirement() {
        return new JobAndLevel(JobAndLevel.CraftingJob.ANY, 64);
    }

    public boolean _canBeUsed(Simulation state, boolean linear, boolean safeMode) {
        return true;
    }

    public int getBaseDurabilityCost(Simulation state) {
        return 10;
    }

    public int getBaseSuccessRate(Simulation state) {
        return 50;
    }

    public int getBaseCPCost(Simulation state) {
        return 6;
    }

    public List<Integer> getIds() {
        return List.of(100219, 100220, 100221, 100222, 100223, 100224, 100225, 100226);
    }

    public int getPotency(Simulation state) {
        return getPotency(state, false);
    }

    public int getPotency(Simulation state, boolean safeMode) {
        return 100;
    }

    public void execute(Simulation state, boolean safeMode) {
        super.execute(state, safeMode);

        if (state.hasBuff(EffectiveBuff.Buff.INNER_QUIET) && state.getBuff(EffectiveBuff.Buff.INNER_QUIET).stacks < 11) {
//            EffectiveBuff iq = state.getBuff(EffectiveBuff.Buff.INNER_QUIET);
//            state.removeBuff(EffectiveBuff.Buff.INNER_QUIET);
//            iq.stacks = Math.min((iq.stacks - 1) * 2, 11);
//            state.buffs.add(iq);

            state.getBuff(EffectiveBuff.Buff.INNER_QUIET).stacks =
                    Math.min((state.getBuff(EffectiveBuff.Buff.INNER_QUIET).stacks - 1) * 2, 11);
        }
    }

    public void onFail(Simulation state) {
        if (state.hasBuff(EffectiveBuff.Buff.INNER_QUIET)) {
            state.getBuff(EffectiveBuff.Buff.INNER_QUIET).stacks = (int) Math.ceil(state.getBuff(EffectiveBuff.Buff.INNER_QUIET).stacks / 2f);
        }
    }
}
