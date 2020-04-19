package org.pb.rumination.model.actions.progress;

import org.pb.rumination.model.EffectiveBuff;
import org.pb.rumination.model.actions.ProgressAction;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public class BrandOfTheElements extends ProgressAction {

    public JobAndLevel getLevelRequirement() {
        return new JobAndLevel(JobAndLevel.CraftingJob.ANY, 37);
    }

    public boolean _canBeUsed(Simulation state, boolean linear, boolean safeMode) {
        return true;
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
        return List.of(100331, 100332, 100333, 100334, 100335, 100336, 100337, 100338);
    }

    public int getPotency(Simulation state) {
        return getPotency(state, false);
    }

    public int getPotency(Simulation state, boolean safeMode) {
        return 100;
    }

    public int getBaseBonus(Simulation state) {
        if (state.hasBuff(EffectiveBuff.Buff.NAME_OF_THE_ELEMENTS)) {
            return (1 + (2 * (int) Math.ceil((1 - state.progression / (double) state.recipe.progress) * 100)) / 100);
        }
        return 1;
    }
}
