package prj2;

import java.util.Objects;

public class Coordinate {
    public final int x, y;
    public final VariableType type;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
        type = null;
    }

    public Coordinate(int x, int y, VariableType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, type);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                ", type=" + type +
                '}';
    }
}
