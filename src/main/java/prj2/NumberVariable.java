package prj2;

import java.util.HashSet;

public class NumberVariable extends Variable {
    private final Integer assignment;
    private final HashSet<Integer> domain;

    public NumberVariable(int x, int y, int degree, Integer assignment, HashSet<Integer> domain) {
        super(x, y, degree);
        this.assignment = assignment;
        this.domain = domain;
    }

    public NumberVariable(NumberVariable nv) {
        super(nv.x, nv.y, nv.getDegree());
        this.assignment = nv.assignment;
        this.domain = new HashSet<>(nv.getDomain());
    }

    @Override
    public Object getAssignment() {
        return assignment;
    }

    public HashSet<Integer> getDomain() {
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
        return "NumberVariable{" +
                "assignment=" + assignment +
                ", domain=" + domain +
                '}';
    }
}
