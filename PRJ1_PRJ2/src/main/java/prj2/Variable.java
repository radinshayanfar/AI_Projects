package prj2;

public abstract class Variable implements Comparable {
    public final int x, y;
    private int degree;

    public Variable(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Variable(int x, int y, int degree) {
        this.x = x;
        this.y = y;
        this.degree = degree;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public void decreaseDegree() {
        degree--;
    }

    public abstract Object getAssignment();
}
