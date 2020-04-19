package org.pb.rumination.sim;

import java.util.List;

public class SimulationReliabilityReport {
    public List<SimulationResults> rawData;
    public int successPercent;
    public int medianHQPercent;
    public int averageHQPercent;

    public SimulationReliabilityReport(List<SimulationResults> rawData, int successPercent, int medianHQPercent, int averageHQPercent) {
        this.rawData = rawData;
        this.successPercent = successPercent;
        this.medianHQPercent = medianHQPercent;
        this.averageHQPercent = averageHQPercent;
    }
}
