package prj2;

import java.util.Arrays;

public class NumberVariable implements Variable {
    private final Integer assignment;
    private final Integer[] domain;

    public NumberVariable(Integer assignment, Integer[] domain) {
        this.assignment = assignment;
        this.domain = domain;
    }

    @Override
    public Object getAssignment() {
        return assignment;
    }

    @Override
    public Object[] getDomain() {
        return domain;
    }

    @Override
    public String toString() {
        return "NumberVariable{" +
                "assignment=" + assignment +
                ", domain=" + Arrays.toString(domain) +
                '}';
    }
}
