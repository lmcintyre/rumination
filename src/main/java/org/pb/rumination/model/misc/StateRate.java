package org.pb.rumination.model.misc;

import org.pb.rumination.sim.Simulation;

public class StateRate {
    public Simulation.StepState state;
    public double rate;

    public StateRate(Simulation.StepState state, double rate) {
        this.state = state;
        this.rate = rate;
    }
}
