import java.util.Arrays;

public class State {
    public static int BATCHES_K;

    private Batch[] batches = new Batch[BATCHES_K];
    private State parent;
    private Transition transition;
    private int cost;
    private int f = -1;

    public State(Batch[] batches) {
        if (batches.length != BATCHES_K)
            throw new IllegalArgumentException();
        this.batches = batches;
    }

    public State(Batch[] batches, int cost) {
        this(batches);
        this.cost = cost;
    }

    public State() {
        this.cost = 0;
    }

    public State(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public State getParent() {
        return parent;
    }

    public Transition getTransition() {
        return transition;
    }

    public boolean isGoalState() {
        for (Batch batch :
                batches) {
            if (! batch.isGoalBatch()) {
                return false;
            }
        }

        return true;
    }

    public int heuristic() {
        int h = Batch.BATCH_N * Batch.COLOR_M;
        for (Batch batch :
                batches) {
            h -= batch.getSortCount();
        }

        return h;
    }

    public int f_n() {
        if (f != -1) {
            return f;
        }
        f = heuristic() + cost;
        return f;
    }

    public State[] nextStates() throws CloneNotSupportedException {
        int notEmpty = 0;
        for (Batch batch :
                batches) {
            if (!batch.isEmpty()) {
                notEmpty++;
            }
        }


        State[] next = new State[(BATCHES_K - 1) * notEmpty];
        int pos = 0;
        for (int i = 0; i < BATCHES_K; i++) {
            if (batches[i].isEmpty())
                continue;

            for (int j = 0; j < BATCHES_K; j++) {
                if (i == j || (!batches[j].isEmpty() && batches[i].getTopNumber() >= batches[j].getTopNumber()))
                    continue;


                State newState = new State();
                newState.parent = this;
                newState.transition = new Transition(i, j);
                newState.cost = cost + 1;
                for (int k = 0; k < batches.length; k++) {
                    newState.batches[k] = batches[k];
                }
                newState.batches[i] = (Batch) batches[i].clone();
                newState.batches[j] = (Batch) batches[j].clone();
                newState.batches[j].addTop(newState.batches[i].removeTop());
                next[pos++] = newState;
            }
        }

        return next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Arrays.equals(batches, state.batches);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(batches);
    }

    @Override
    public String toString() {
        return "State{" +
                "batches=" + Arrays.toString(batches) +
                ", cost=" + cost +
                '}';
    }

    public StringBuilder outputString() {
        StringBuilder out = new StringBuilder();
        for (Batch batch :
                batches) {
            out.append(batch.outputString()).append("\n");
        }

        return out;
    }
}
