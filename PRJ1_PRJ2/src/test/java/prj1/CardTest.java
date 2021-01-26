package prj1;

import org.junit.Assert;
import org.junit.Test;

public class CardTest {

    @Test
    public void gettersTest() {
        Card card = new Card('r', 2);

        Assert.assertEquals(card.getColor(), 'r');
        Assert.assertEquals(card.getNumber(), 2);
    }
}
