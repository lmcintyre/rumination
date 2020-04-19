package org.pb.rumination.model.actions;

import org.pb.rumination.model.EffectiveBuff;
import org.pb.rumination.model.misc.TickAction;
import org.pb.rumination.sim.Simulation;

import java.util.List;

public abstract class BuffAction extends CraftAction {

    public abstract int getDuration(Simulation state);
    public abstract EffectiveBuff.Buff getBuff();
    public abstract int getInitialStacks();

    public TickAction getTickAction() { return null; }

    public ActionType getType() {
        return ActionType.BUFF;
    }

    public boolean canBeClipped() {
        return false;
    }

    public boolean _canBeUsed(Simulation state) {
        if (this.canBeClipped())
            return true;
        return !state.hasBuff(this.getBuff());
    }

    public int getDurabilityCost(Simulation state) {
        return 0;
    }

    public int _getSuccessRate(Simulation state) {
        return 100;
    }

    public List<EffectiveBuff.Buff> getOverrides() {
        return List.of(this.getBuff());
    }

    public void execute(Simulation state) {
        execute(state, false);
    }

    public void execute(Simulation state, boolean safeMode) {
        for (EffectiveBuff.Buff toOverride : this.getOverrides()) {
            state.removeBuff(toOverride);
        }
        state.buffs.add(this.getAppliedBuff(state));
    }

    private EffectiveBuff getAppliedBuff(Simulation state) {
        return
                new EffectiveBuff(this.getDuration(state),
                                 this.getInitialStacks(),
                                 state.steps.size(),
                                 this.getBuff(),
                                 this.getTickAction());
    }
}
