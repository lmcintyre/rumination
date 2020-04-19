package org.pb.rumination.solving;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.pb.rumination.model.ActionResult;
import org.pb.rumination.model.Craft;
import org.pb.rumination.model.CrafterStats;
import org.pb.rumination.model.actions.CraftAction;
import org.pb.rumination.model.actions.buff.*;
import org.pb.rumination.model.actions.other.*;
import org.pb.rumination.model.actions.progress.*;
import org.pb.rumination.model.actions.quality.*;
import org.pb.rumination.sim.Simulation;
import org.pb.rumination.sim.SimulationReliabilityReport;
import org.pb.rumination.sim.SimulationResults;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@PlanningSolution
public class Rotation {

//    private List<ActionListElement> actions;
//
//    public List<CraftAction> asList() {
//        return actions.stream().map(ActionListElement::getAction).collect(Collectors.toList());
//    }

    @PlanningEntityCollectionProperty
    private List<RotationAction> actions;

    @PlanningScore
    private HardSoftScore score;

    private Simulation simulation;
    private SimulationResults results;
    private SimulationReliabilityReport report;
    private Craft recipe;
    private CrafterStats stats;

    public Rotation() {}

    public Rotation(Craft recipe, CrafterStats stats) {
        this.recipe = recipe;
        this.stats = stats;
        this.actions = new ArrayList<>();
    }

    public List<RotationAction> getRotationActions() {
        return actions;
    }

    public void setRotationActions(List<RotationAction> rotActions) {
        this.actions = rotActions;
    }

    public void setActions(List<CraftAction> actions) {
        this.actions = actions.stream().map(RotationAction::new).collect(Collectors.toList());
    }

    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

    public SimulationResults getResults() {
        return results;
    }

    public void setResults(SimulationResults results) {
        this.results = results;
    }

    public SimulationReliabilityReport getReport() {
        return report;
    }

    public void setReport(SimulationReliabilityReport report) {
        this.report = report;
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    public Craft getRecipe() {
        return recipe;
    }

    public void setRecipe(Craft recipe) {
        this.recipe = recipe;
    }

    public CrafterStats getStats() {
        return stats;
    }

    public void setStats(CrafterStats stats) {
        this.stats = stats;
    }

    public List<CraftAction> getActions() {
        return actions.stream().map(RotationAction::getAction).collect(Collectors.toList());
    }
}
