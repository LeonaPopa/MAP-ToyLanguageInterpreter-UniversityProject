package model.values;

import model.types.RefType;
import model.types.Type;

public class RefValue implements Value{
    private final int address;
    private final Type locationType;

    public RefValue(int address, Type locationType) {
        this.address = address;
        this.locationType = locationType;
    }
    public int getAddress() {
        return address;
    }
    @Override
    public String toString() {
        return "(" + address + "; " + locationType + ")";
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        RefValue other = (RefValue) obj;
        return address == other.address && locationType.equals(other.locationType);
    }
    @Override
    public Type getType() {
        return new RefType(locationType);
    }

    @Override
    public Value deepCopy() {
        return new RefValue(address, locationType.deepCopy());
    }
}
