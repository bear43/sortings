package core.sort.util.vector;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Vector1f implements IVectorNf<Vector1f> {

    public float x;

    public Vector1f() {

    }

    @Override
    public float getComponent(VectorComponent component) {
        if (component == VectorComponent.X) {
            return x;
        }
        throw new UnsupportedOperationException("Unknown component");
    }

    @Override
    public float setComponent(VectorComponent component, float value) {
        if (component == VectorComponent.X) {
            x = value;
        }
        throw new UnsupportedOperationException("Unknown component");
    }
}
