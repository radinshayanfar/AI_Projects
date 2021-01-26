package prj2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class ColorVariable extends Variable {
    public static final HashMap<Character, Integer> PRIORITIES_MAP = new HashMap<>();
    public static final Character[] PRIORITIES_ARRAY = new Character[State.M];

    private final Character assignment;
    private final HashSet<Character> domain;

    public ColorVariable(int x, int y, int degree, Character assignment, HashSet<Character> domain) {
        super(x, y, degree);
        this.assignment = assignment;
        this.domain = domain;
    }

    public ColorVariable(ColorVariable cv) {
        super(cv.x, cv.y, cv.getDegree());
        this.assignment = cv.assignment;
        this.domain = new HashSet<>(cv.getDomain());
    }

    @Override
    public Object getAssignment() {
        return assignment;
    }

    public HashSet<Character> getDomain() {
        return domain;
    }

    @Override
    public int compareTo(Object o) {
        Variable other = (Variable) o;
        int otherDomainSize = (o instanceof NumberVariable) ? ((NumberVariable) other).getDomain().size() : ((ColorVariable) other).getDomain().size();

        int thisHeuristic = -1 * State.MAX_DEGREE * this.getDomain().size() + this.getDegree();
        int otherHeuristic = -1 * State.MAX_DEGREE * otherDomainSize+ other.getDegree();

        return thisHeuristic - otherHeuristic;
    }

    @Override
    public String toString() {
        return "ColorVariable{" +
                "assignment=" + assignment +
                ", domain=" + domain +
                '}';
    }
}
