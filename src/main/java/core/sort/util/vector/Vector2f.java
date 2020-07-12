package core.sort.util.vector;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Vector2f implements IVectorNf<Vector2f> {
    public float x;
    public float y;

    public Vector2f() {

    }

    @Override
    public float getComponent(VectorComponent component) {
        switch (component) {
            case X:
                return x;
            case Y:
                return y;
            default:
                throw new UnsupportedOperationException("Unknown component");
        }
    }

    @Override
    public float setComponent(VectorComponent component, float value) {
        switch (component) {
            case X:
                x = value;
            case Y:
                y = value;
            default:
                throw new UnsupportedOperationException("Unknown component");
        }
    }
}
