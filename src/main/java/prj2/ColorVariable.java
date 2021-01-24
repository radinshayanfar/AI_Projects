package prj2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class ColorVariable implements Variable {
    public static final HashMap<Character, Integer> PRIORITIES = new HashMap<>();

    private final Character assignment;
    private final HashSet<Character> domain;

    public ColorVariable(Character assignment, HashSet<Character> domain) {
        this.assignment = assignment;
        this.domain = domain;
    }

    @Override
    public Object getAssignment() {
        return assignment;
    }

    public HashSet<Character> getDomain() {
        return domain;
    }

    @Override
    public String toString() {
        return "ColorVariable{" +
                "assignment=" + assignment +
                ", domain=" + domain +
                '}';
    }
}
