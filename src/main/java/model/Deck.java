import java.util.*;
import java.io.*;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
    }

    public void loadFromFile(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;

            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                String color = values[0];
                String type = values[1];
                String value = values[2];

                Card card = new Card(color, type, value);
                cards.add(card);
            }

            br.close();
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }
    
    public Card drawCard(){
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }
    
    public boolean isEmpty(){
        return cards.isEmpty();
    }

    public int size() {
        return cards.size();
    }

    public void printDeck() {
        for (Card card : cards) {
            System.out.println(card);
        }
    }
}
