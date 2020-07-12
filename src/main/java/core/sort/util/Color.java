package core.sort.util;

import core.sort.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.glColor3f;

public enum Color {
    WHITE(new Vector3f(1f, 1f, 1f)),
    BLACK(new Vector3f(0f, 0f, 0f)),
    RED(new Vector3f(1f, 0f, 0f)),
    GREEN(new Vector3f(0f, 1f, 0f)),
    BLUE(new Vector3f(0f, 0f, 1f));
    private final Vector3f color;

    Color(Vector3f color) {
        this.color = color;
    }

    public Vector3f getColor() {
        return color;
    }

    public void set() {
        glColor3f(color.x, color.y, color.z);
    }
}
