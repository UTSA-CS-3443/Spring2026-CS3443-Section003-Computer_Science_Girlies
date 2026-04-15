import java.util.ArrayList;
import java.util.Collections;

public class GameModel {
    private ArrayList<Card> deck;
    private Card[][] board;

    public GameModel() {
        deck = new ArrayList<>();
        board = new Card[4][4];

        createDeck();
        shuffleDeck();
        fillBoard();
    }

    private void createDeck() {
        String[] colors = {"Red", "Blue", "Green", "Yellow"};

        for (int i = 0; i <= colors.length; i++) {
            String color = colors[i];

            // add pairs for matching
            deck.add(new Card(i, color));
            deck.add(new Card(i, color));
        }
    }

    private void shuffleDeck() {
        Collections.shuffle(deck);
    }

    private void fillBoard() {
        int index = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = deck.get(index++);
            }
        }
    }

    public Card[][] getBoard() {
        return board;
    }
}