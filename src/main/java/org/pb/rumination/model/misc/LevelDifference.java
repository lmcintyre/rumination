package org.pb.rumination.model.misc;

public class LevelDifference {
    public int difference;
    public int progressFactor;
    public int qualityFactor;

    public LevelDifference(int difference, int progressFactor, int qualityFactor) {
        this.difference = difference;
        this.progressFactor = progressFactor;
        this.qualityFactor = qualityFactor;
    }
}