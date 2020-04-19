package org.pb.rumination.model;

import org.pb.rumination.sim.Simulation;

public class CrafterStats {

    public int jobId;
    public int craftsmanship;
    public int _control;
    public int cp;
    public boolean specialist;
    public int level;
    public int[] crafterLevels = new int[8];

    public CrafterStats() {}

    public CrafterStats(int jobId, int craftsmanship, int _control, int cp, boolean specialist, int level, int[] crafterLevels) {
        this.jobId = jobId;
        this.craftsmanship = craftsmanship;
        this._control = _control;
        this.cp = cp;
        this.specialist = specialist;
        this.level = level;
        this.crafterLevels = crafterLevels;
    }

    public int getControl(Simulation state) {
        int control = this._control;

        if (state.hasBuff(EffectiveBuff.Buff.INNER_QUIET)) {
            int stacks = state.getBuff(EffectiveBuff.Buff.INNER_QUIET).stacks;
            control += 0.2 * (stacks - 1) * this._control;
        }

        return control;
    }

}
