package prj2;

import java.util.Arrays;
import java.util.HashSet;

public class State {
    public static int N, M;

    private NumberVariable[][] numVars;
    private ColorVariable[][] colorVars;

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
            numVars[x][y] = (NumberVariable) assignment;

            // 2- forward checking
            FCNumberAssignment(x, y, (Integer) assignment.getAssignment());
        } else {
            // 1- assignment
            colorVars[x][y] = (ColorVariable) assignment;

            // 2- forward checking
            FCColorAssignment(x, y, (Character) assignment.getAssignment());
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

    private void FCNumberAssignment(int x, int y, Integer assignment) {
        for (int i = 0; i < State.N; i++) {
            // checking row
            if (i != y && numVars[x][i].getDomain() != null) {
                numVars[x][i] = new NumberVariable(numVars[x][i]);
                numVars[x][i].getDomain().remove(assignment);
            }

            // checking column
            if (i != x && numVars[i][y].getDomain() != null) {
                numVars[i][y] = new NumberVariable(numVars[i][y]);
                numVars[i][y].getDomain().remove(assignment);
            }
        }
        // if color was not assigned before -> return
        if (colorVars[x][y].getDomain() != null)
            return;

        Coordinate[] adjs = getAdjacents(x, y);
        FCBoth(x, y, adjs);
    }

    private void FCColorAssignment(int x, int y, Character assignment) {
        Coordinate[] adjs = getAdjacents(x, y);
        for (int i = 0; i < 4; i++) {
            if (adjs[i] == null)
                break;

            int x_p = adjs[i].x, y_p = adjs[i].y;
            if (colorVars[x_p][y_p].getDomain() != null
                    && colorVars[x_p][y_p].getDomain().contains(assignment))  {
                colorVars[x_p][y_p] = new ColorVariable(colorVars[x_p][y_p]);
                colorVars[x_p][y_p].getDomain().remove(assignment);
            }
        }
        // if number was not assigned before -> return
        if (numVars[x][y].getDomain() != null)
            return;

        FCBoth(x, y, adjs);
    }

    private void FCBoth(int x, int y, Coordinate[] adjs) {
        int base, dir;
        for (int i = 0; i < 4; i++) {
            if (adjs[i] == null)
                break;

            int x_p = adjs[i].x, y_p = adjs[i].y;
            if (numVars[x_p][y_p].getDomain() == null && colorVars[x_p][y_p].getDomain() == null)
                continue;

            // editing adjacent color domain
            if (numVars[x_p][y_p].getDomain() == null) {
                base = ColorVariable.PRIORITIES_MAP.get((Character) colorVars[x][y].getAssignment());
                System.out.println(numVars[x][y].getAssignment() );
                dir = (int) Math.signum((Integer) numVars[x][y].getAssignment() - (Integer) numVars[x_p][y_p].getAssignment());

                for (int j = base + dir; j < State.M && j >= 0; j += dir) {
                    colorVars[x_p][y_p] = new ColorVariable(colorVars[x_p][y_p]);
                    colorVars[x_p][y_p].getDomain().remove(ColorVariable.PRIORITIES_ARRAY[j]);
                }
            }

            // editing adjacent number domain
            if (colorVars[x_p][y_p].getDomain() == null) {
                base = (Integer) numVars[x][y].getAssignment();
                dir = -1 * (int) Math.signum(ColorVariable.PRIORITIES_MAP.get((Character) colorVars[x][y].getAssignment())
                        - ColorVariable.PRIORITIES_MAP.get((Character) colorVars[x_p][y_p].getAssignment()));

                for (int j = base + dir; j <= State.N && j >= 1; j += dir) {
                    numVars[x_p][y_p] = new NumberVariable(numVars[x_p][y_p]);
                    numVars[x_p][y_p].getDomain().remove(j);
                }
            }

        }
    }

    private Coordinate[] getAdjacents(int x, int y) {
        Coordinate[] out = new Coordinate[4];
        int index = 0;

        // top
        if (x > 0)
            out[index++] = new Coordinate(x - 1, y);
        // left
        if (y > 0)
            out[index++] = new Coordinate(x, y - 1);
        // bottom
        if (x < State.N - 1)
            out[index++] = new Coordinate(x + 1, y);
        // right
        if (y < State.N - 1)
            out[index] = new Coordinate(x, y + 1);

        return out;
    }
}
