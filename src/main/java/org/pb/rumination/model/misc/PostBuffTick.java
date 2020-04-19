package org.pb.rumination.model.misc;

public class PostBuffTick {
    public int addedProgression;
    public int addedQuality;
    public int cpDifference;
    public int solidityDifference;

    public PostBuffTick(int addedProgression, int addedQuality, int cpDifference, int solidityDifference) {
        this.addedProgression = addedProgression;
        this.addedQuality = addedQuality;
        this.cpDifference = cpDifference;
        this.solidityDifference = solidityDifference;
    }
}
