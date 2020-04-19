package org.pb.rumination;

import com.google.gson.internal.bind.util.ISO8601Utils;
import javassist.runtime.Inner;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.pb.rumination.model.Craft;
import org.pb.rumination.model.CrafterStats;
import org.pb.rumination.model.actions.*;
import org.pb.rumination.model.actions.buff.InnerQuiet;
import org.pb.rumination.model.actions.other.DelicateSynthesis;
import org.pb.rumination.model.actions.other.MastersMend;
import org.pb.rumination.model.actions.progress.BasicSynthesis;
import org.pb.rumination.model.actions.progress.Groundwork;
import org.pb.rumination.model.actions.progress.IntensiveSynthesis;
import org.pb.rumination.model.actions.progress.MuscleMemory;
import org.pb.rumination.model.actions.quality.*;
import org.pb.rumination.model.misc.IngredientElement;
import org.pb.rumination.sim.Simulation;
import org.pb.rumination.sim.SimulationReliabilityReport;
import org.pb.rumination.solving.Rotation;
import org.pb.rumination.solving.RotationAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Craft recipe = new Craft() {{
            id = "test";
            job = 0;
            rlvl = 480;
            durability = 70;
            quality = 36208;
            progress = 6178;
            lvl = 80;
            stars = 3;
            hq = true;
            quickSynth = false;
            suggestedCraftsmanship = 2480;
            suggestedControl = 2195;
            craftsmanshipReq = 2480;
            controlReq = 2195;
            unlockId = 0;
            itemYield = 1;
            expert = false;
        }};

        CrafterStats stats = new CrafterStats() {{
            jobId = 0;
            craftsmanship = 2486;
            _control = 2318;
            cp = 613;
            specialist = true;
            level = 80;
            this.crafterLevels = new int[] {80, 80, 80, 80, 80, 80, 80, 80};
        }};

        Rotation rotation = new Rotation(recipe, stats);
        rotation.setRotationActions(generateActionList(20));

        SolverFactory<Rotation> solverFactory = SolverFactory.createFromXmlResource("solver/solverConfiguration.xml");
        Solver<Rotation> solver = solverFactory.buildSolver();

        Rotation newrot = solver.solve(rotation);
        newrot.getActions().forEach(System.out::println);
//        System.out.println(newrot.getActions());
        System.out.println(newrot.getScore());
        System.out.println("hi");
    }

    public static void main1(String[] args) {
        Craft recipe = new Craft() {{
            id = "test";
            job = 0;
            rlvl = 480;
            durability = 70;
            quality = 36208;
            progress = 6178;
            lvl = 80;
            stars = 3;
            hq = true;
            quickSynth = false;
            suggestedCraftsmanship = 2480;
            suggestedControl = 2195;
            craftsmanshipReq = 2480;
            controlReq = 2195;
            unlockId = 0;
            itemYield = 1;
            expert = false;
        }};

        CrafterStats stats = new CrafterStats() {{
            jobId = 0;
            craftsmanship = 2486;
            _control = 2318;
            cp = 613;
            specialist = true;
            level = 80;
            this.crafterLevels = new int[]{80, 80, 80, 80, 80, 80, 80, 80};
        }};

        List<CraftAction> actions = new ArrayList<>() {{
            add(new InnerQuiet());
            add(new Groundwork());
            add(new DelicateSynthesis());
            add(new DelicateSynthesis());
            add(new DelicateSynthesis());
            add(new PrudentTouch());
            add(new PrudentTouch());
            add(new DelicateSynthesis());
            add(new DelicateSynthesis());
            add(new MastersMend());
            add(new Groundwork());
            add(new ByregotsBlessing());
            add(new IntensiveSynthesis());
            add(new ByregotsBlessing());
            add(new PreparatoryTouch());
            add(new BasicSynthesis());
            add(new ByregotsBlessing());
            add(new ByregotsBlessing());
        }};

        Simulation test = new Simulation(recipe,
                                            actions,
                                            stats,
                                            new ArrayList<IngredientElement>(),
                                            new HashMap<Integer, Simulation.StepState>(),
                                            0);

        var results = test.run(true, 9999, true);
        System.out.println(results.failCause);

        SimulationReliabilityReport report = test.getReliabilityReport(100);
        System.out.println("Success %: " + report.successPercent);
        System.out.println("Average HQ %: " + report.averageHQPercent);
        System.out.println(report.rawData.get(0).failCause);
    }

    private static List<RotationAction> generateActionList(int size) {
        ArrayList<RotationAction> ret = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ret.add(new RotationAction(new EmptyAction()));
        }
        return ret;
    }
}
