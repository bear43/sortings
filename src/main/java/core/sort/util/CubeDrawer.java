package core.sort.util;

import core.sort.interfaces.ISortState;
import core.sort.util.shape.Cube2D;
import core.sort.util.shape.Primitive;
import core.sort.util.vector.Vector2f;
import core.sort.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class CubeDrawer {

    private static void setColor(Cube2D cube) {
        cube.color.set();
    }

    private static void drawVertex(Vector2f vertex) {
        glVertex2f(vertex.x, vertex.y);
    }

    private static void drawPrimitive(Primitive<Vector2f> primitive) {
        for(Vector2f vertex : primitive.vertices) {
            drawVertex(vertex);
        }
    }

    private static void drawSingleCube(Cube2D cube) {
        for(Primitive<Vector2f> primitive : cube.getPrimitives()) {
            drawPrimitive(primitive);
        }
    }

    public static void draw(List<Cube2D> cubes) {
        glBegin(GL_QUADS);
        for(Cube2D cube : cubes) {
            setColor(cube);
            drawSingleCube(cube);
        }
        glEnd();
    }

    public static void drawCubes(int index, int length) {
        final float halfLength = length / 2f;
        final float transformedIndex = index - halfLength;
        final float indexRange = 1f/length + 0.1f;
        List<Cube2D> cubes = new ArrayList<>();
        for(int currentCube = 0; currentCube < length; currentCube++) {
            Cube2D cube = new Cube2D(transformedIndex/halfLength + (currentCube) * indexRange, 0.05f);
            cubes.add(cube);
        }
        draw(cubes);
    }

    public static <ObjectType extends Number & Comparable<ObjectType>> void drawCubes(ISortState<ObjectType> state) {
        final ObjectType[] array = state.getSortingArray();
        final float halfLength = array.length / 2f;
        float transformedIndex;
        final int maxValue = array.length-1;
        float heightMultiplier;
        final float indexRange = 1f/array.length;
        Color activeColor;
        List<Cube2D> cubes = new ArrayList<>();
        for(int currentCube = 0; currentCube < array.length; currentCube++) {
            transformedIndex = currentCube - halfLength;
            heightMultiplier = array[currentCube].floatValue() / maxValue * 10f;
            if(currentCube == state.getLeftIndex()) {
                activeColor = Color.RED;
            } else if(currentCube == state.getRightIndex()) {
                activeColor = Color.GREEN;
            } else {
                activeColor = Color.WHITE;
            }
            Cube2D cube = new Cube2D(transformedIndex/halfLength + indexRange, 0.05f, heightMultiplier, activeColor);
            cubes.add(cube);
        }
        draw(cubes);
    }
}
