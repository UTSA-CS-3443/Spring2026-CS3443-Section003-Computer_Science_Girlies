public class Card {
    private String color;
    private boolean matched; // useful for your game

    public Card(String color, int number) {
        this.number = number;
        this.color = color;
        this.matched = false;
    }

    public int getNumber() {
        return number;
    }

    public String getColor() {
        return color;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }
}