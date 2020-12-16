import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

public class StateTest {
    @BeforeClass
    public static void setN_K() {
        State.BATCHES_K = 4;
        Batch.BATCH_N = 3;
        Batch.COLOR_M = 3;
    }

    @Test
    public void goalStateTest() {
        char[] colors = {'r', 'g', 'b'};
        Batch[] batches = new Batch[State.BATCHES_K];

        for (int i = 0; i < Batch.COLOR_M; i++) {
            Batch batch = new Batch();
            for (int j = 3; j >= 1; j--) {
                batch.addTop(new Card(colors[i], j));
                Assert.assertTrue(batch.isGoalBatch());
                Assert.assertEquals(batch.getSortCount(), 4 - j);
            }
            batches[i] = batch;
        }
        batches[3] = new Batch();
        State state = new State(batches);
        Assert.assertTrue(state.isGoalState());
//        Assert.assertEquals(state.heuristic(), 0);

        batches[2].addTop(batches[1].removeTop());
        Assert.assertFalse(state.isGoalState());
//        Assert.assertEquals(state.heuristic(), 1);
    }

    @Test
    public void nextStatesTest() throws CloneNotSupportedException {
        char[] colors = {'r', 'g', 'b'};
        Batch[] batches = new Batch[State.BATCHES_K];

        for (int i = 0; i < Batch.COLOR_M; i++) {
            Batch batch = new Batch();
            for (int j = 3; j >= 1; j--) {
                batch.addTop(new Card(colors[i], j));
                Assert.assertTrue(batch.isGoalBatch());
                Assert.assertEquals(batch.getSortCount(), 4 - j);
            }
            batches[i] = batch;
        }
        batches[3] = new Batch();
        State state = new State(batches);

        System.out.println("Current state: " + state);

        State[] nextStates = state.nextStates();
        System.out.println("Next states: " + Arrays.toString(nextStates));

//        Assert.assertEquals(state, nextStates[0].nextStates()[3]);

    }
}
