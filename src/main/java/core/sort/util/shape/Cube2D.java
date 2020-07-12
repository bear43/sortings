package core.sort.util.shape;

import core.sort.util.Color;
import core.sort.util.vector.ComponentCount;
import core.sort.util.vector.IVectorNf;
import core.sort.util.vector.Vector2f;
import core.sort.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class Cube2D implements IShape<Vector2f> {

    private static final Color DEFAULT_COLOR = Color.WHITE;

    private List<Primitive<Vector2f>> primitives = new ArrayList<>();
    private final PrimitiveExtractor<Vector2f> extractor = new PrimitiveExtractor<>();
    public Color color;

    public Cube2D() {
    }

    public Cube2D(float leftBorder, float sideLength, float heightMultiplier, Color color) {
        final float halfOfSideLength = sideLength / 2;
        final float height = (halfOfSideLength * heightMultiplier) - 1f + 0.05f;
        this.color = color;
        primitives.add(new Primitive<>(
                ComponentCount.TWO,
                leftBorder, height,
                leftBorder, -1f,
                leftBorder + sideLength, -1f,
                leftBorder + sideLength, height
        ));
    }

    public Cube2D(float leftBorder, float sideLength, float heightMultiplier) {
        this(leftBorder, sideLength, heightMultiplier, DEFAULT_COLOR);
    }

    public Cube2D(float leftBorder, float sideLength) {
        this(leftBorder, sideLength, 1f);
    }

    @Override
    public List<IVectorNf<Vector2f>> getVertices() {
        return extractor.extractVertices(primitives);
    }

    @Override
    public List<Primitive<Vector2f>> getPrimitives() {
        return primitives;
    }
}
