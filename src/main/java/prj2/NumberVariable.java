package prj2;

import java.util.HashSet;

public class NumberVariable implements Variable {
    private final Integer assignment;
    private final HashSet<Integer> domain;

    public NumberVariable(Integer assignment, HashSet<Integer> domain) {
        this.assignment = assignment;
        this.domain = domain;
    }

    public NumberVariable(NumberVariable nv) {
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
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "NumberVariable{" +
                "assignment=" + assignment +
                ", domain=" + domain +
                '}';
    }
}
