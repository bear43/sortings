package core.sort.util.vector;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Vector3f implements IVectorNf<Vector3f> {
    public float x;
    public float y;
    public float z;

    public Vector3f() {

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
            default:
                throw new UnsupportedOperationException("Unknown component");
        }
    }


}
