package model.types;

import model.values.RefValue;
import model.values.Value;

public class RefType implements Type{
    private final Type inner;

    public RefType(Type type){
        this.inner = type;
    }

    @Override
    public Value defaultValue() {
        return new RefValue(0, inner);
    }

    @Override
    public Type deepCopy() {
        return new RefType(inner.deepCopy());
    }
    @Override
    public boolean equals(Object obj) {
        return obj instanceof RefType && inner.equals(((RefType) obj).inner);
    }
    @Override
    public String toString() {
        return "Ref(" + inner + ")";
    }
    public Type getInner(){
        return inner;
    }
}
