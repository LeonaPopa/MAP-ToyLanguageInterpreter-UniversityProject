package model.values;

import model.types.BoolType;
import model.types.Type;

public class BoolValue implements Value{
    private boolean value;
    public BoolValue(boolean value) {
        this.value = value;
    }
    public boolean getValue() {
        return value;
    }
    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public Value deepCopy() {
        return new BoolValue(value);
    }
    @Override
    public String toString() {
        if (value) return "true";
        return "false";
    }
    @Override
    public boolean equals(Object another) {
        if (this == another) return true;
        if (another == null || getClass() != another.getClass()) return false;
        BoolValue boolValue = (BoolValue) another;
        return value == boolValue.value;
    }
}
