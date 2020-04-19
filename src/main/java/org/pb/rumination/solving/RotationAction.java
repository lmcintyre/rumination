package org.pb.rumination.solving;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.pb.rumination.model.actions.CraftAction;
import org.pb.rumination.model.actions.buff.*;
import org.pb.rumination.model.actions.other.*;
import org.pb.rumination.model.actions.progress.*;
import org.pb.rumination.model.actions.quality.*;
import org.pb.rumination.sim.SimulationReliabilityReport;
import org.pb.rumination.sim.SimulationResults;

import java.util.ArrayList;
import java.util.List;

@PlanningEntity
public class RotationAction {

    @PlanningVariable(valueRangeProviderRefs = {"actionRange"})
    private CraftAction action;

    public RotationAction() {}

    public RotationAction(CraftAction action) {
        this.action = action;
    }

    public CraftAction getAction() {
        return action;
    }

    public void setAction(CraftAction action) {
        this.action = action;
    }

    @ValueRangeProvider(id = "actionRange")
    public List<CraftAction> getPossibleActions() {
        return new ArrayList<>() {{
            add(new FinalAppraisal());
            add(new GreatStrides());
            add(new InnerQuiet());
            add(new Innovation());
            add(new Manipulation());
            add(new NameOfTheElements());
            add(new Veneration());
            add(new WasteNot());
            add(new WasteNotII());
            add(new DelicateSynthesis());
            add(new MastersMend());
            add(new Observe());
            add(new RemoveFinalAppraisal());
            add(new TricksOfTheTrade());
            add(new BasicSynthesis());
            add(new BrandOfTheElements());
            add(new CarefulSynthesis());
            add(new FocusedSynthesis());
            add(new Groundwork());
            add(new IntensiveSynthesis());
            add(new MuscleMemory());
            add(new RapidSynthesis());
            add(new BasicTouch());
            add(new ByregotsBlessing());
            add(new FocusedTouch());
            add(new HastyTouch());
            add(new PatientTouch());
            add(new PreciseTouch());
            add(new PreparatoryTouch());
            add(new PrudentTouch());
            add(new Reflect());
            add(new StandardTouch());
            add(new TrainedEye());
        }};
    }
}