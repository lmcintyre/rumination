package org.pb.rumination.model;

import org.pb.rumination.model.actions.CraftAction;
import org.pb.rumination.model.misc.PostBuffTick;
import org.pb.rumination.sim.Simulation;

public class ActionResult {
    public ActionResult(CraftAction action, boolean success,
                        Simulation.SimulationFailCause failCause,
                        int addedProgression,
                        int addedQuality,
                        int cpDifference,
                        int solidityDifference,
                        boolean skipped,
                        Simulation.StepState state,
                        PostBuffTick postBuffTick) {
        this.action = action;
        this.success = success;
        this.failCause = failCause;
        this.addedProgression = addedProgression;
        this.addedQuality = addedQuality;
        this.cpDifference = cpDifference;
        this.solidityDifference = solidityDifference;
        this.skipped = skipped;
        this.state = state;
        this.postBuffTick = postBuffTick;
    }

    public CraftAction action;
    public boolean success;
    public Simulation.SimulationFailCause failCause;
    public int addedProgression;
    public int addedQuality;
    public int cpDifference;
    public int solidityDifference;
    public boolean skipped;
    public Simulation.StepState state;

    public PostBuffTick postBuffTick;
}
