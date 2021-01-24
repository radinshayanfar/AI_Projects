package prj2;

import java.util.HashSet;
import java.util.Scanner;

abstract public class IOParser {

    private static final Scanner sc = new Scanner(System.in);

    private IOParser() {
    }

    public static State parseInput() {
        State.M = sc.nextInt();
        State.N = sc.nextInt();
        sc.nextLine();

        String[] colors = sc.nextLine().trim().split(" ");
        HashSet<Character> colorsDomain = new HashSet<>();
        for (int i = 0; i < State.M; i++) {
            ColorVariable.PRIORITIES_MAP.put(colors[i].charAt(0), i);
            ColorVariable.PRIORITIES_ARRAY[i] = colors[i].charAt(0);
            colorsDomain.add(colors[i].charAt(0));
        }

        HashSet<Integer> numbersDomain = new HashSet<>();
        for (int i = 0; i < State.N; i++) {
            numbersDomain.add(i + 1);
        }

        State state = new State(numbersDomain, colorsDomain);
        for (int i = 0; i < State.N; i++) {
            String[] cells = sc.nextLine().trim().split(" ");

            for (int j = 0; j < State.N; j++) {
                if (cells[j].charAt(0) != '*') {
                    Integer val = Integer.parseInt(cells[j].substring(0, cells[j].length() - 1));
                    state = new State(state, new NumberVariable(i, j, val, null));
                }
                if (cells[j].charAt(cells[j].length() - 1) != '#') {
                    state = new State(state, new ColorVariable(i, j, cells[j].charAt(cells[j].length() - 1), null));
                }
            }
        }

        return state;
    }

}
