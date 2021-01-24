package prj2;

import java.util.Arrays;
import java.util.HashMap;

public class ColorVariable implements Variable {
    public static final HashMap<Character, Integer> PRIORITIES = new HashMap<>();

    private final Character assignment;
    private final Character[] domain;

    public ColorVariable(Character assignment, Character[] domain) {
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
        return "ColorVariable{" +
                "assignment=" + assignment +
                ", domain=" + Arrays.toString(domain) +
                '}';
    }
}
