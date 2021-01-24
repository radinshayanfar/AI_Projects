package prj2;

import java.util.Arrays;
import java.util.HashSet;

public class State {
    public static int N, M;

    private Variable[][] numVars;
    private Variable[][] colorVars;

    public State(NumberVariable[][] numVars, ColorVariable[][] colorVars) {
        this.numVars = numVars;
        this.colorVars = colorVars;
    }

    public State(State old, Variable assignment, int x, int y) {

        // 0- copy vars
        numVars = new NumberVariable[N][N];
        colorVars = new ColorVariable[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                numVars[i][j] = old.numVars[i][j];
                colorVars[i][j] = old.colorVars[i][j];
            }
        }

        if (assignment instanceof NumberVariable) {
            // 1- assignment
            numVars[x][y] = assignment;

            // 2- forward checking

        } else {
            // 1- assignment
            colorVars[x][y] = assignment;

            // 2- forward checking
        }



    }

    // initializes clean state
    public State(HashSet<Integer> numbersDomain, HashSet<Character> colorsDomain) {
        numVars = new NumberVariable[N][N];
        colorVars = new ColorVariable[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                numVars[i][j] = new NumberVariable(null, numbersDomain);
                colorVars[i][j] = new ColorVariable(null, colorsDomain);
            }
        }
    }

    @Override
    public String toString() {
        return "State{" +
                "numVars=" + Arrays.deepToString(numVars) +
                ", colorVars=" + Arrays.deepToString(colorVars) +
                '}';
    }
}
