package org.pb.rumination.model.actions.other;

import org.pb.rumination.model.EffectiveBuff;
import org.pb.rumination.model.actions.CraftAction;
import org.pb.rumination.model.misc.JobAndLevel;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public class TricksOfTheTrade extends CraftAction {

    public JobAndLevel getLevelRequirement() {
        return new JobAndLevel(JobAndLevel.CraftingJob.ANY, 42);
    }

    public CraftAction.ActionType getType() {
        return ActionType.OTHER;
    }

    public boolean _canBeUsed(Simulation state, boolean linear, boolean safeMode) {
        if (linear)
            return true;
        if (state.safe)
            return false;

        return (state.state == Simulation.StepState.GOOD || state.state == Simulation.StepState.EXCELLENT);
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
        return List.of(100371, 100372, 100373, 100374, 100375, 100376, 100377, 100378);
    }

    public void execute(Simulation state) {
        execute(state, false);
    }

    public void execute(Simulation state, boolean safe) {
        if (!safe) {
            state.availableCP += 20;
            if (state.availableCP > state.maxCP) {
                state.availableCP = state.maxCP;
            }
        }
    }
}
