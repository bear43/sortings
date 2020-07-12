package core.sort.util.vector;

public enum ComponentCount {
    ONE,
    TWO,
    THREE,
    FOUR;

    public static ComponentCount resolve(int pointsPerVertexCount) {
        switch (pointsPerVertexCount) {
            case 1:
                return ONE;
            case 2:
                return TWO;
            case 3:
                return THREE;
            case 4:
                return FOUR;
            default:
                throw new OutOfComponentBoundException();
        }
    }

    private void checkBounds(float[] points, int startIndex) {
        switch (this) {
            case ONE:
                if (startIndex >= points.length) {
                    throw new IndexOutOfBoundsException();
                }
                break;
            case TWO:
                if (
                        startIndex >= points.length
                                && startIndex + 1 >= points.length
                ) {
                    throw new IndexOutOfBoundsException();
                }
                break;
            case THREE:
                if (
                        startIndex >= points.length
                                && startIndex + 1 >= points.length
                                && startIndex + 2 >= points.length
                ) {
                    throw new IndexOutOfBoundsException();
                }
                break;
            case FOUR:
                if (
                        startIndex >= points.length
                                && startIndex + 1 >= points.length
                                && startIndex + 2 >= points.length
                                && startIndex + 3 >= points.length
                ) {
                    throw new IndexOutOfBoundsException();
                }
                break;
            default:
                throw new OutOfComponentBoundException();
        }
    }

    public int verticesCount() {
        switch (this) {
            case ONE:
                return 1;
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            default:
                throw new OutOfComponentBoundException();
        }
    }

    @SuppressWarnings("unchecked")
    public <VectorType extends IVectorNf<VectorType>> Class<VectorType> getVectorClass() {
        switch (this) {
            case ONE:
                return (Class<VectorType>) Vector1f.class;
            case TWO:
                return (Class<VectorType>) Vector2f.class;
            case THREE:
                return (Class<VectorType>) Vector3f.class;
            case FOUR:
                return (Class<VectorType>) Vector4f.class;
            default:
                throw new OutOfComponentBoundException();
        }
    }

    public <VectorType extends IVectorNf<VectorType>> VectorType buildVectorNf(float[] points, int pointsStartIndex) {
        checkBounds(points, pointsStartIndex);
        switch (this) {
            case ONE:
                return (VectorType) new Vector1f(points[pointsStartIndex]);
            case TWO:
                return (VectorType) new Vector2f(points[pointsStartIndex], points[pointsStartIndex + 1]);
            case THREE:
                return (VectorType) new Vector3f(points[pointsStartIndex], points[pointsStartIndex + 1], points[pointsStartIndex + 2]);
            case FOUR:
                return (VectorType) new Vector4f(points[pointsStartIndex], points[pointsStartIndex + 1], points[pointsStartIndex + 2], points[pointsStartIndex + 3]);
            default:
                throw new OutOfComponentBoundException();
        }
    }
}
