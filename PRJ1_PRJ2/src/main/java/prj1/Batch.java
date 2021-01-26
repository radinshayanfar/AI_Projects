package prj1;

import java.util.Objects;
import java.util.Stack;

public class Batch implements Cloneable{
    public static int BATCH_N;
    public static int COLOR_M;

    private int sortCount = 0;
    private Stack<Card> cards = new Stack<>();

    public boolean isGoalBatch() {
        return cards.size() == sortCount;
    }

    public void addTop(Card card) {
        int prevSize = cards.size();
        cards.push(card);

        if (prevSize != sortCount || prevSize >= BATCH_N) {
            return;
        }
        if (prevSize == 0) {
            sortCount++;
            return;
        }
        if (cards.elementAt(prevSize - 1).getColor() == cards.peek().getColor() &&
                cards.elementAt(prevSize - 1).getNumber() > cards.peek().getNumber()) {
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
        return "prj1.Batch{" +
                "sortPos=" + sortCount +
                ", cards=" + cards +
                '}';
    }

    public String outputString() {
        if (cards.size() == 0) {
            return "#";
        }

        StringBuilder out = new StringBuilder();
        for (Card card :
                cards) {
            out.append(card.getNumber()).append(card.getColor()).append(" ");
        }

        return out.toString();
    }
}
