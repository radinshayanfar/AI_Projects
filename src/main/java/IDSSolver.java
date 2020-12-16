import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class IDSSolver implements GameSolver {
    private final State initialState;
    private Queue<State> frontier = new LinkedList<>();

    private long createdNodes, expandedNodes;

    public IDSSolver(State initialState) {
        this.initialState = initialState;
    }

    private State DFS(int limit) {
        frontier.offer(initialState);
        while (!frontier.isEmpty()) {
            State node = frontier.poll();
            expandedNodes++;

            if (node.isGoalState()) {
                return node;
            }

            if (node.getCost() > limit) {
                break;
            }

            try {
                for (State state :
                        node.nextStates()) {
                    if (state == null)
                        break;
                    frontier.offer(state);
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
            State dfsResult = DFS(limit);
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
