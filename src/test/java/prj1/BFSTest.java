package prj1;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class BFSTest {
    @Test
    public void n1_trivial_Test() {
        State.BATCHES_K = 3;
        Batch.COLOR_M = 2;
        Batch.BATCH_N = 2;

        Batch batch1 = new Batch(), batch2 = new Batch();
        batch1.addTop(new Card('r', 2));
        batch1.addTop(new Card('r', 1));
        batch2.addTop(new Card('b', 2));
        batch2.addTop(new Card('b', 1));

        State initialState = new State(new Batch[]{batch1, batch2, new Batch()});

        GameSolver gameSolver = new BFSSolver(initialState);
        State solved = gameSolver.solve();
        System.out.println(solved);

        Assert.assertEquals(solved, initialState);
    }

    @Test
    public void n2_simple_Test() {
        State.BATCHES_K = 3;
        Batch.COLOR_M = 2;
        Batch.BATCH_N = 2;

        Batch batch1 = new Batch(), batch2 = new Batch();
        batch1.addTop(new Card('r', 2));
        batch1.addTop(new Card('r', 1));
        batch2.addTop(new Card('b', 2));
        batch1.addTop(new Card('b', 1));

        State initialState = new State(new Batch[]{batch1, batch2, new Batch()});

        GameSolver gameSolver = new BFSSolver(initialState);
        State solved = gameSolver.solve();
        System.out.println(solved);


    }

    @Test
    @Ignore("Time limit")
    public void n3_given_Test() {
        State.BATCHES_K = 5;
        Batch.COLOR_M = 3;
        Batch.BATCH_N = 5;

        Batch[] batches = new Batch[State.BATCHES_K];
        for (int i = 0; i < State.BATCHES_K; i++) {
            batches[i] = new Batch();
        }
        batches[0].addTop(new Card('g', 5));
        batches[0].addTop(new Card('r', 5));
        batches[0].addTop(new Card('y', 4));
        batches[1].addTop(new Card('g', 2));
        batches[1].addTop(new Card('r', 4));
        batches[1].addTop(new Card('y', 3));
        batches[1].addTop(new Card('g', 3));
        batches[1].addTop(new Card('y', 3));
        batches[2].addTop(new Card('y', 1));
        batches[2].addTop(new Card('g', 4));
        batches[2].addTop(new Card('r', 1));
        batches[3].addTop(new Card('g', 1));
        batches[3].addTop(new Card('r', 2));
        batches[3].addTop(new Card('y', 5));
        batches[3].addTop(new Card('r', 3));

        State initialState = new State(batches);

        GameSolver gameSolver = new BFSSolver(initialState);
        State solved = gameSolver.solve();
        System.out.println(solved);

    }
}
