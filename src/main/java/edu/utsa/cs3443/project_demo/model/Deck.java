package edu.utsa.cs3443.project_demo.model;

import java.util.*;
import java.io.*;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
    }

    public void loadFromFile(String resourcePath) {
        cards.clear();

        try {
            InputStream stream = getClass().getResourceAsStream(resourcePath);

            if (stream == null) {
                System.out.println("Missing file: " + resourcePath);
                return;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(stream));

            String line;

            // Skip header row
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length < 3) {
                    System.out.println("Skipping bad card line: " + line);
                    continue;
                }

                String color = values[0].trim();
                String type = values[1].trim();
                String value = values[2].trim();

                Card card = new Card(color, type, value);
                cards.add(card);
            }

            br.close();

            System.out.println("Loaded " + cards.size() + " cards.");

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