package prj1;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class BatchTest {
    @BeforeClass
    public static void setBatchN() {
        Batch.BATCH_N = 3;
    }

    @Test
    public void addRemoveTopTest() {
        Batch batch = new Batch();

        Card[] input = {new Card('g', 2), new Card('r', 1), new Card('b', 3), new Card('r', 2)};

        for (Card card :
                input) {
            batch.addTop(card);
        }

        Card[] input2 = {new Card('g', 2), new Card('r', 1), new Card('b', 3), new Card('r', 2)};

        int count = input2.length - 1;
        for (int i = count; i >= 0; i--) {
            Assert.assertEquals(batch.removeTop(), input2[i]);
        }

        Assert.assertTrue(batch.isEmpty());
    }

    @Test
    public void addingIsNotSorted1Test() {
        Batch batch = new Batch();

        batch.addTop(new Card('r', 2));
        Assert.assertTrue(batch.isGoalBatch());
        Assert.assertEquals(batch.getSortCount(), 1);

        batch.addTop(new Card('r', 1));
        Assert.assertTrue(batch.isGoalBatch());
        Assert.assertEquals(batch.getSortCount(), 2);

        batch.addTop(new Card('r', 3));
        Assert.assertFalse(batch.isGoalBatch());
        Assert.assertEquals(batch.getSortCount(), 2);

    }

    @Test
    public void addingIsNotSorted2Test() {
        Batch batch = new Batch();

        batch.addTop(new Card('r', 3));
        Assert.assertTrue(batch.isGoalBatch());
        Assert.assertEquals(batch.getSortCount(), 1);

        batch.addTop(new Card('r', 1));
        Assert.assertTrue(batch.isGoalBatch());
        Assert.assertEquals(batch.getSortCount(), 2);

        batch.addTop(new Card('r', 2));
        Assert.assertFalse(batch.isGoalBatch());
        Assert.assertEquals(batch.getSortCount(), 2);
    }

    @Test
    public void addingIsSortedTest() {
        Batch batch = new Batch();

        batch.addTop(new Card('r', 4));
        Assert.assertTrue(batch.isGoalBatch());
        Assert.assertEquals(batch.getSortCount(), 1);

        batch.addTop(new Card('r', 2));
        Assert.assertTrue(batch.isGoalBatch());
        Assert.assertEquals(batch.getSortCount(), 2);

        batch.addTop(new Card('r', 1));
        Assert.assertTrue(batch.isGoalBatch());
        Assert.assertEquals(batch.getSortCount(), 3);

        batch.addTop(new Card('g', 1));
        Assert.assertFalse(batch.isGoalBatch());
        Assert.assertEquals(batch.getSortCount(), 3);
    }

    @Test
    public void removingIsSortedTest() {
        Batch batch = new Batch();

        batch.addTop(new Card('r', 3));
        Assert.assertTrue(batch.isGoalBatch());
        Assert.assertEquals(batch.getSortCount(), 1);

        batch.addTop(new Card('r', 2));
        Assert.assertTrue(batch.isGoalBatch());
        Assert.assertEquals(batch.getSortCount(), 2);

        batch.addTop(new Card('r', 1));
        Assert.assertTrue(batch.isGoalBatch());
        Assert.assertEquals(batch.getSortCount(), 3);

        batch.addTop(new Card('g', 1));
        Assert.assertFalse(batch.isGoalBatch());
        Assert.assertEquals(batch.getSortCount(), 3);

        batch.removeTop();
        Assert.assertTrue(batch.isGoalBatch());
        Assert.assertEquals(batch.getSortCount(), 3);

        batch.removeTop();
        Assert.assertTrue(batch.isGoalBatch());
        Assert.assertEquals(batch.getSortCount(), 2);

        batch.removeTop();
        Assert.assertTrue(batch.isGoalBatch());
        Assert.assertEquals(batch.getSortCount(), 1);

        batch.removeTop();
        Assert.assertTrue(batch.isGoalBatch());
        Assert.assertEquals(batch.getSortCount(), 0);

        Assert.assertTrue(batch.isEmpty());
    }

    @Test
    public void removingIsNotSortedTest() {
        Batch batch = new Batch();

        batch.addTop(new Card('r', 2));
        Assert.assertTrue(batch.isGoalBatch());
        Assert.assertEquals(batch.getSortCount(), 1);

        batch.addTop(new Card('g', 2));
        Assert.assertFalse(batch.isGoalBatch());
        Assert.assertEquals(batch.getSortCount(), 1);

        batch.addTop(new Card('r', 1));
        Assert.assertFalse(batch.isGoalBatch());
        Assert.assertEquals(batch.getSortCount(), 1);

        batch.addTop(new Card('g', 1));
        Assert.assertFalse(batch.isGoalBatch());
        Assert.assertEquals(batch.getSortCount(), 1);

        batch.removeTop();
        Assert.assertFalse(batch.isGoalBatch());
        Assert.assertEquals(batch.getSortCount(), 1);

        batch.removeTop();
        Assert.assertFalse(batch.isGoalBatch());
        Assert.assertEquals(batch.getSortCount(), 1);

        batch.removeTop();
        Assert.assertTrue(batch.isGoalBatch());
        Assert.assertEquals(batch.getSortCount(), 1);

        batch.removeTop();
        Assert.assertTrue(batch.isGoalBatch());
        Assert.assertEquals(batch.getSortCount(), 0);

        Assert.assertTrue(batch.isEmpty());
    }

    @Test
    public void cloneTest() throws CloneNotSupportedException {
        Batch batch1 = new Batch(), batch2 = new Batch();
        batch1.addTop(new Card('r', 3));
        batch1.addTop(new Card('r', 2));
        batch1.addTop(new Card('r', 1));
        batch2.addTop(new Card('g', 3));

        Batch batch3 = (Batch) batch1.clone(), batch4 = (Batch) batch2.clone();
        batch2.addTop(batch1.removeTop());

        Assert.assertNotEquals(batch1, batch3);
        Assert.assertNotEquals(batch2, batch4);

        System.out.println(batch1);
        System.out.println(batch2);
        System.out.println(batch3);
        System.out.println(batch4);

    }

}
