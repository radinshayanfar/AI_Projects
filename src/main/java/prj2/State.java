package prj2;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class State {
    public static int N, M;
    public static int MAX_DEGREE;

    private final NumberVariable[][] numVars;

    private final ColorVariable[][] colorVars;
    private final Set<Coordinate> unassigned;
    // initializes clean state

    public State(HashSet<Integer> numbersDomain, HashSet<Character> colorsDomain) {
        numVars = new NumberVariable[N][N];
        colorVars = new ColorVariable[N][N];
        unassigned = new HashSet<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Coordinate[] adjs = getAdjacents(i, j);
                int adjCount = 4;
                for (int k = 3; k >= 0; k--) {
                    if (adjs[k] == null)
                        adjCount--;
                    else
                        break;
                }

                numVars[i][j] = new NumberVariable(i, j, 2 * N - 2 + adjCount, null, numbersDomain);
                colorVars[i][j] = new ColorVariable(i, j, 2 * adjCount, null, colorsDomain);
                unassigned.add(new Coordinate(i, j, VariableType.Number));
                unassigned.add(new Coordinate(i, j, VariableType.Color));
            }
        }
    }
    public State(State old, Variable assignment) {
        int x = assignment.x;
        int y = assignment.y;

        // 0- copy vars
        numVars = new NumberVariable[N][N];
        colorVars = new ColorVariable[N][N];
        unassigned = new HashSet<>(old.unassigned);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                numVars[i][j] = old.numVars[i][j];
                colorVars[i][j] = old.colorVars[i][j];
            }
        }

        if (assignment instanceof NumberVariable) {
            // 1- assignment
            int degree = numVars[x][y].getDegree();
            numVars[x][y] = (NumberVariable) assignment;
            numVars[x][y].setDegree(degree);
            unassigned.remove(new Coordinate(x, y, VariableType.Number));

            // 2- forward checking
            FCNumberAssignment(x, y, (Integer) assignment.getAssignment());
        } else {
            // 1- assignment
            int degree = colorVars[x][y].getDegree();
            colorVars[x][y] = (ColorVariable) assignment;
            colorVars[x][y].setDegree(degree);
            unassigned.remove(new Coordinate(x, y, VariableType.Color));

            // 2- forward checking
            FCColorAssignment(x, y, (Character) assignment.getAssignment());
        }

    }

    private Variable selectVariable() {
        int unassignedCount = unassigned.size();
        if (unassignedCount == 0)
            return null;

        Variable[] unassignedArray = new Variable[unassignedCount];
        int index = 0;
        for (Coordinate c :
                unassigned) {
            unassignedArray[index++] = (c.type == VariableType.Number) ? numVars[c.x][c.y] : colorVars[c.x][c.y];
        }

        return Collections.max(Arrays.asList(unassignedArray));
    }

    public State[] nextStates() {
        State[] out;
        Variable variable = selectVariable();
        if (variable == null)
            return null;

        int index = 0;
        if (variable instanceof NumberVariable) {
            HashSet<Integer> domain = ((NumberVariable) variable).getDomain();
            out = new State[domain.size()];
            for (Integer a :
                    domain) {
                out[index++] = new State(this, new NumberVariable(variable.x, variable.y, variable.getDegree(), a, null));
            }
        } else {
            HashSet<Character> domain = ((ColorVariable) variable).getDomain();
            out = new State[domain.size()];
            for (Character a :
                    domain) {
                out[index++] = new State(this, new ColorVariable(variable.x, variable.y, variable.getDegree(), a, null));
            }
        }

        return out;
    }

    private void FCNumberAssignment(int x, int y, Integer assignment) {
        for (int i = 0; i < State.N; i++) {
            // checking row
            if (i != y && numVars[x][i].getDomain() != null) {
                numVars[x][i] = new NumberVariable(numVars[x][i]);
                numVars[x][i].getDomain().remove(assignment);
                numVars[x][i].decreaseDegree();
            }

            // checking column
            if (i != x && numVars[i][y].getDomain() != null) {
                numVars[i][y] = new NumberVariable(numVars[i][y]);
                numVars[i][y].getDomain().remove(assignment);
                numVars[i][y].decreaseDegree();
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
                    && colorVars[x_p][y_p].getDomain().contains(assignment)) {
                colorVars[x_p][y_p] = new ColorVariable(colorVars[x_p][y_p]);
                colorVars[x_p][y_p].getDomain().remove(assignment);
                colorVars[x_p][y_p].decreaseDegree();
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
                dir = -1 * (int) Math.signum((Integer) numVars[x][y].getAssignment() - (Integer) numVars[x_p][y_p].getAssignment());

                for (int j = base + dir; j < State.M && j >= 0; j += dir) {
                    colorVars[x_p][y_p] = new ColorVariable(colorVars[x_p][y_p]);
                    colorVars[x_p][y_p].getDomain().remove(ColorVariable.PRIORITIES_ARRAY[j]);
                    colorVars[x_p][y_p].decreaseDegree();
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
                    numVars[x_p][y_p].decreaseDegree();
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

    public boolean isGoalState() {
        return unassigned.size() == 0;
    }

    public String outputString() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                out.append(numVars[i][j].getAssignment()).append(colorVars[i][j].getAssignment()).append(' ');
            }
            out.append('\n');
        }

        return out.toString();
    }

    @Override
    public String toString() {
        return "State{" +
                "numVars=" + Arrays.deepToString(numVars) +
                ", colorVars=" + Arrays.deepToString(colorVars) +
//                ", unassigned=" + unassigned +
                '}';
    }

    public static void calcMacDegree() {
        MAX_DEGREE = 2 * N + 3;
    }
}
