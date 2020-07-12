package core.sort.util.shape;

import core.sort.util.vector.IVectorNf;
import core.sort.util.vector.Vector1f;
import core.sort.util.vector.Vector2f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PrimitiveExtractor<VectorType extends IVectorNf<VectorType>> {

    private List<IVectorNf<VectorType>> transformVertexArrayIntoList(Primitive<VectorType> primitive) {
        List<IVectorNf<VectorType>> vertices = new ArrayList<>();
        vertices.addAll(Arrays.asList(primitive.vertices));
        return vertices;
    }

    public List<IVectorNf<VectorType>> extractVertices(List<Primitive<VectorType>> primitives) {
        return primitives.stream()
                .filter(primitive -> primitive != null && primitive.vertices != null)
                .map(this::transformVertexArrayIntoList)
                .reduce((a, b) -> {
                    a.addAll(b);
                    return a;
                }).orElse(new ArrayList<>());
    }
}
