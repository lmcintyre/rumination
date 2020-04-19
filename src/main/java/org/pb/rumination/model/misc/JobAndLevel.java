package org.pb.rumination.model.misc;

public class JobAndLevel {

    public JobAndLevel(CraftingJob job, int level) {
        this.job = job;
        this.level = level;
    }

    public enum CraftingJob {
        ANY(-1),
        CRP(0),
        BSM(1),
        ARM(2),
        GSM(3),
        LTW(4),
        WVR(5),
        ALC(6),
        CUL(7);

        private int value;

        CraftingJob(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public CraftingJob job;
    public int level;

}
