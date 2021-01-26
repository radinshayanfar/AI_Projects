package prj2;

import java.util.Stack;

public class CSPSolver {
    private final State initialState;
    private final Stack<State> frontier = new Stack<>();

    public CSPSolver(State initialState) {
        this.initialState = initialState;
    }

    public State solve() {
        frontier.push(initialState);
        while (!frontier.isEmpty()) {
            State node = frontier.pop();

            if (node.isGoalState()) {
                return node;
            }

            for (State state :
                    node.nextStates()) {
                frontier.push(state);
            }
        }
        return null;
    }
}
