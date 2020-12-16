import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        State.BATCHES_K = 3;
        Batch.BATCH_N = 2;
        Batch.COLOR_M = 2;

        Batch batch1 = new Batch(), batch2 = new Batch();
        batch1.addTop(new Card('r', 1));
        batch1.addTop(new Card('b', 2));
        batch2.addTop(new Card('r', 2));
        batch2.addTop(new Card('b', 1));

        State initialState = new State(new Batch[]{batch1, batch2, new Batch()});

        GameSolver gameSolver = new BFSSolver(initialState);
        State solved = gameSolver.solve();
        System.out.println(solved);
    }
}
