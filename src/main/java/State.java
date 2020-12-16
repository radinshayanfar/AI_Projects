import java.util.Arrays;

public class State {
    public static int BATCHES_K;

    private Batch[] batches;
    private int cost;

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

    public boolean isGoalState() {
        int goalBatches = 0;
        for (Batch batch :
                batches) {
            if (batch.isGoalBatch()) {
                goalBatches++;
            }
        }

        return goalBatches == Batch.BATCH_N;
    }

    public int heuristic() {
        int h = Batch.BATCH_N * Batch.COLOR_M;
        for (Batch batch :
                batches) {
            h -= batch.getSortCount();
        }

        return h;
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
}
