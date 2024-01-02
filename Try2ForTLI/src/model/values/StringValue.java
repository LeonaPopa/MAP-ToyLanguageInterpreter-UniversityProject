package model.values;

import model.types.StringType;
import model.types.Type;

public class StringValue implements Value{

    private final String value;

    public StringValue(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
    @Override
    public String toString() {
        return '"'+value+'"';
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        StringValue other = (StringValue) obj;
        return value.equals(other.value);
    }
    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public Value deepCopy() {
        return new StringValue(value);
    }
}
