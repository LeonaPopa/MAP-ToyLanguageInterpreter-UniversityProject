package model.types;

import model.values.StringValue;
import model.values.Value;

public class StringType implements Type{
    @Override
    public Value defaultValue() {
        return new StringValue("");
    }

    @Override
    public Type deepCopy() {
        return new StringType();
    }
    @Override
    public boolean equals(Object obj) {
        return obj instanceof StringType;
    }
    @Override
    public String toString() {
        return "string";
    }
}
