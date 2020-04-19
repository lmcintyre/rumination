package org.pb.rumination.model.misc;

// ugh
public class TickAction {
    public enum Type {
        REPAIR
    }

    public Type type;
    public int value;

    public TickAction(Type type, int value) {
        this.type = type;
        this.value = value;
    }
}
