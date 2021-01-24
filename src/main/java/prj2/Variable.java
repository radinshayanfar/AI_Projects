package prj2;

public abstract class Variable {
    public final int x, y;

    public Variable(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract Object getAssignment();
}
