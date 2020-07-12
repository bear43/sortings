package core.sort.util.shape;

import core.sort.util.vector.ComponentCount;
import core.sort.util.vector.IVectorNf;
import core.sort.util.vector.VectorComponent;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Primitive <VectorType extends IVectorNf<VectorType>> {
    public final VectorType[] vertices;
    public final ComponentCount componentCount;

    public Primitive() {
        vertices = null;
        componentCount = null;
    }

    @SuppressWarnings("unchecked")
    public Primitive(VectorType... vertices) {
        VectorType[] array = (VectorType[]) Array.newInstance(vertices[0].getClass(), vertices.length);
        System.arraycopy(vertices, 0, array, 0, vertices.length);
        this.vertices = array;
        this.componentCount = ComponentCount.resolve(vertices.length);
    }

    @SuppressWarnings("unchecked")
    public Primitive(ComponentCount pointsPerVertex, float... points) {
        final int totalVertices = points.length / pointsPerVertex.verticesCount();
        VectorType[] array = (VectorType[]) Array.newInstance(pointsPerVertex.getVectorClass(), totalVertices);
        int pointsStartIndex = 0;
        for(int index = 0; index < totalVertices; index++) {
            array[index] = pointsPerVertex.buildVectorNf(points, pointsStartIndex);
            pointsStartIndex += pointsPerVertex.verticesCount();
        }
        this.vertices = array;
        this.componentCount = pointsPerVertex;
    }
}
