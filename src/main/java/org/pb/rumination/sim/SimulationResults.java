package org.pb.rumination.sim;

import org.pb.rumination.model.ActionResult;

import java.util.List;

public class SimulationResults {
    public List<ActionResult> steps;
    public int hqPercent;
    public boolean success;
    public Simulation simulation;
    public Simulation.SimulationFailCause failCause;

    public SimulationResults(List<ActionResult> steps, int hqPercent, boolean success, Simulation simulation) {
        this.steps = steps;
        this.hqPercent = hqPercent;
        this.success = success;
        this.simulation = simulation;
    }
}
