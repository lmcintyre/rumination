package org.pb.rumination.model.actions.quality;

import org.pb.rumination.model.EffectiveBuff;
import org.pb.rumination.model.actions.QualityAction;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public class PrudentTouch extends QualityAction {

    public JobAndLevel getLevelRequirement() {
        return new JobAndLevel(JobAndLevel.CraftingJob.ANY, 66);
    }

    public boolean _canBeUsed(Simulation state, boolean linear, boolean safeMode) {
        return !(state.hasBuff(EffectiveBuff.Buff.WASTE_NOT) || state.hasBuff(EffectiveBuff.Buff.WASTE_NOT_II));
    }

    public int getBaseDurabilityCost(Simulation state) {
        return 5;
    }

    public int getBaseSuccessRate(Simulation state) {
        return 100;
    }

    public int getBaseCPCost(Simulation state) {
        return 25;
    }

    public List<Integer> getIds() {
        return List.of(100227, 100228, 100229, 100230, 100231, 100232, 100233, 100234);
    }

    public int getPotency(Simulation state) {
        return getPotency(state, false);
    }

    public int getPotency(Simulation state, boolean safeMode) {
        return 100;
    }
}
