import java.util.Objects;
import java.util.Stack;

public class Batch implements Cloneable{
    public static int BATCH_N;
    public static int COLOR_M;

    private int sortCount = 0;
    private Stack<Card> cards = new Stack<>();

    public boolean isGoalBatch() {
        return cards.size() == BATCH_N && sortCount == BATCH_N;
    }

    public void addTop(Card card) {
        int prevSize = cards.size();
        cards.push(card);

        if (prevSize != sortCount || prevSize >= BATCH_N) {
            return;
        }
        if (prevSize == 0 && card.getNumber() == BATCH_N) {
            sortCount++;
            return;
        }
        if (prevSize != 0 && cards.elementAt(prevSize - 1).getColor() == cards.peek().getColor() &&
                cards.elementAt(prevSize - 1).getNumber() - 1 == cards.peek().getNumber()) {
            sortCount++;
        }

    }

    public Card removeTop() {
        if (cards.size() == sortCount) {
            sortCount--;
        }
        return cards.pop();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int getSortCount() {
        return sortCount;
    }

    public int getTopNumber() {
        return cards.peek().getNumber();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Batch cloned = new Batch();
        cloned.cards = (Stack<Card>) cards.clone();
        cloned.sortCount = sortCount;

        return cloned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Batch batch = (Batch) o;
        return sortCount == batch.sortCount &&
                cards.equals(batch.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sortCount, cards);
    }

    @Override
    public String toString() {
        return "Batch{" +
                "sortPos=" + sortCount +
                ", cards=" + cards +
                '}';
    }
}
