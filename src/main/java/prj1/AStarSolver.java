package prj1;

import java.util.HashSet;
import java.util.PriorityQueue;

public class AStarSolver implements GameSolver {
    private final State initialState;
    private PriorityQueue<State> frontier = new PriorityQueue<>();
    private HashSet<State> visited = new HashSet<>();

    private long createdNodes, expandedNodes;

    public AStarSolver(State initialState) {
        this.initialState = initialState;
    }


    @Override
    public State solve() {
        frontier.offer(initialState);
        while (!frontier.isEmpty()) {
            State node = frontier.poll();
            if (visited.contains(node)) {
                continue;
            }
            expandedNodes++;

            if (node.isGoalState()) {
                return node;
            }

            visited.add(node);

            try {
                for (State state :
                        node.nextStates()) {
                    if (state == null)
                        break;
                    if (visited.contains(state)) {
                        continue;
                    }
//                    if (frontier.contains(state)) {
//                        continue;
//                    }
                    frontier.offer(state);
                    createdNodes++;
                }
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return null;
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
