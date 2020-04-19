package org.pb.rumination.model;

import org.pb.rumination.model.misc.TickAction;
import org.pb.rumination.sim.Simulation;

public class EffectiveBuff {

    public enum Buff {
        INNER_QUIET,
        WASTE_NOT,
        WASTE_NOT_II,
        MANIPULATION,
        GREAT_STRIDES,
        INNOVATION,
        VENERATION,
        MAKERS_MARK,
        NAME_OF_THE_ELEMENTS,
        MUSCLE_MEMORY,
        FINAL_APPRAISAL
    }

    public int duration;
    public int stacks;
    public int appliedStep;
    public Buff buff;
    public TickAction onTick;

    public void tick(Simulation state, boolean linear) {
        if (onTick == null)
            return;

        switch (onTick.type) {
            case REPAIR:
                state.repair(onTick.value);
                break;
        }
    }

    public EffectiveBuff(int duration, int stacks, int appliedStep, Buff buff, TickAction action) {
        this.duration = duration;
        this.stacks = stacks;
        this.appliedStep = appliedStep;
        this.buff = buff;
        this.onTick = action;
    }
}
