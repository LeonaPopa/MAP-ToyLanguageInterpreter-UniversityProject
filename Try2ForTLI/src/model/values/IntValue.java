package model.values;

import model.types.IntType;
import model.types.Type;

public class IntValue implements Value{
    private final int value;
    public IntValue(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    @Override
    public boolean equals(Object another) {
        if (this == another) return true;
        if (another == null || getClass() != another.getClass()) return false;
        IntValue intValue = (IntValue) another;
        return value == intValue.value;
    }
    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public Value deepCopy() {
        return new IntValue(value);
    }
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
