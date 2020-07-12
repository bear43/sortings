package core.sort.util.vector;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Vector4f implements IVectorNf<Vector4f> {
    public float x;
    public float y;
    public float z;
    public float w;

    public Vector4f() {

    }

    @Override
    public float getComponent(VectorComponent component) {
        switch (component) {
            case X:
                return x;
            case Y:
                return y;
            case Z:
                return z;
            case W:
                return w;
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
            case Z:
                z = value;
            case W:
                w = value;
            default:
                throw new UnsupportedOperationException("Unknown component");
        }
    }
}
