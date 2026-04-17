public class Card {
    private String color;
    private String type; // "number" or "action"
    private String value; // "0-9", "skip", "reverse", "draw2"
    private boolean matched;

    public Card(String color, String type, String value) {
        this.color = color;
        this.type = type;
        this.value = value;
        this.matched = false;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }
    
    public String getValue() {
        return value;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    public boolean matches(Card other) {
        return this.color.equalsIgnoreCase(other.color) ||
        this.value.equalsIgnoreCase(other.value);
    }

    @Override
    public String toString() {
        return color + " " + value;
    }
}
