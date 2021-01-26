package prj1;

import java.util.Stack;

public class IDSSolver implements GameSolver {
    private final State initialState;
    private Stack<State> frontier = new Stack<>();

    private long createdNodes, expandedNodes;

    public IDSSolver(State initialState) {
        this.initialState = initialState;
    }

    private State DLS(int limit) {
        frontier.push(initialState);
        while (!frontier.isEmpty()) {
            State node = frontier.pop();
            expandedNodes++;

            if (node.isGoalState()) {
                return node;
            }

            if (node.getCost() == limit) {
                continue;
            }

            try {
                for (State state :
                        node.nextStates()) {
                    if (state == null)
                        break;
//                    if (frontier.contains(state)) {
//                        continue;
//                    }
                    frontier.push(state);
                    createdNodes++;
                }
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public State solve(int base_limit) {
        int limit = base_limit;
        while (true) {
            State dfsResult = DLS(limit);
            if (dfsResult != null) {
                return dfsResult;
            }
            limit++;
        }
    }


    @Override
    public State solve() {
        return solve(0);
    }

    @Override
    public long getCreatedNodes() {
        return createdNodes;
    }

    @Override
    public long getExpandedNodes() {
        return expandedNodes;
    }
}
