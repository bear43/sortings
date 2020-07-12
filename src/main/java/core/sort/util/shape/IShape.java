package core.sort.util.shape;

import core.sort.util.vector.IVectorNf;

import java.util.List;

public interface IShape <VectorType extends IVectorNf<VectorType>> {
    List<IVectorNf<VectorType>> getVertices();
    List<Primitive<VectorType>> getPrimitives();
}
