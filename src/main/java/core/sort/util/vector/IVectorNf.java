package core.sort.util.vector;

public interface IVectorNf <RealType extends IVectorNf<RealType>> {
    float getComponent(VectorComponent component);
    float setComponent(VectorComponent component, float value);
    default RealType getRealVector() {
        return (RealType) this;
    }
}
