import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFSSolver implements GameSolver {

    private final State initialState;
    private Queue<State> frontier = new LinkedList<>();
    private HashSet<State> visited = new HashSet<>();

    public BFSSolver(State initialState) {
        this.initialState = initialState;
    }

    @Override
    public State solve() {
        frontier.offer(initialState);
        while (!frontier.isEmpty()) {
            State node = frontier.poll();
            if (node.isGoalState()) {
                return node;
            }
            visited.add(node);
            try {
                for (State state :
                        node.nextStates()) {
                    if (state == null)
                        break;
                    frontier.offer(state);
                }
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
