package org.pb.rumination.solving;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;
import org.pb.rumination.model.ActionResult;
import org.pb.rumination.model.actions.CraftAction;
import org.pb.rumination.sim.Simulation;
import org.pb.rumination.sim.SimulationResults;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RotationScoreCalculator implements EasyScoreCalculator<Rotation> {

    final int HQ_FACTOR = 10;
    final int EMPTY_MAX = 500;

    @Override
    public HardSoftScore calculateScore(Rotation rotation) {

        HardSoftScore score = HardSoftScore.of(0, 0);

        List<CraftAction> actions = rotation.getActions();

        Simulation simulation = new Simulation(rotation.getRecipe(),
                                                actions,
                                                rotation.getStats(),
                                                new ArrayList<>(),
                                                new HashMap<>(),
                                                0);

        SimulationResults res = simulation.run(true, 9999, true);
        score = score.add(HardSoftScore.ofSoft(res.hqPercent * HQ_FACTOR - HQ_FACTOR));

        boolean emptyCheck = true;
        for (int i = 0; i < actions.size() - 2 && emptyCheck; i++) {
            if (!actions.get(i).isEmpty() &&
                actions.get(i + 1).isEmpty())
                emptyCheck = false;
        }

        //reduce hard for using an empty mid-craft
        if (!emptyCheck)
            score = score.subtract(HardSoftScore.ONE_HARD);

        //500 possible for fewest steps used
        int steps = actions.size();
        int emptySteps = (int) actions.stream().filter(CraftAction::isEmpty).count();
        double percentEmpty = emptySteps / (double) steps;
        if (steps != emptySteps)
            score = score.add(HardSoftScore.ofSoft((int) (percentEmpty * EMPTY_MAX)));
        else
            score = score.subtract(HardSoftScore.ONE_HARD);

        //reduce hard for failed
        if (!res.success)
            score = score.subtract(HardSoftScore.ONE_HARD);

        //reduce hard for skipped actions
        int skippedActions = (int) res.steps.stream().filter(step -> !step.skipped).count();
        if (skippedActions > 0)
            score = score.subtract(HardSoftScore.ONE_HARD);

        rotation.setSimulation(simulation);
        rotation.setResults(res);
        return score;
    }
}
