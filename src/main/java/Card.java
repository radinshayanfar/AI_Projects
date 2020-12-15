import java.util.Objects;

public class Card {
    private char color;
    private int number;

    public Card(char color, int number) {
        this.color = color;
        this.number = number;
    }

    public char getColor() {
        return color;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return color == card.color &&
                number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, number);
    }

    @Override
    public String toString() {
        return "Card{" +
                "color=" + color +
                ", number=" + number +
                '}';
    }
}
