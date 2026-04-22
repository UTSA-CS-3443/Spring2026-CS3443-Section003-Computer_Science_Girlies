public class Game {
    private ArrayList<Player> players;
    private Deck deck;
    private Stack<Card> discardPile;
    private int currentPlayerIndex;
    private int direction;

    public Game(){
        players = new ArrayList<Player>();
        deck = new Deck();
        discardPile = new Stack();
        currentPlayerIndex = 0;
        direction = 1;

    }

    public void addPlayer(Player player){
        players.addPlayer(player);
    }
    public void startGame(){
        /* load cards.csv, then shuffle the deck, play a card */
        deck.loadFromFile("cards.csv");
        deck.shuffle();
        for (Player player : players){
            for (int i = 0; i < player.length(); i++){
                player.drawCard(deck);

            }
        }
        discardPile.push(deck.drawCard());
    }
    public Player getCurrentPlayer(){
        return players.get(currentPlayerIndex);
    }
    public void nextTurn(){
        currentPlayerIndex = (currentPlayerIndex + direction + players.size()) % players.size();

    }
    public void playTurn(){
        /* */
        Player player = getCurrentPlayer(); 
        Card topCard = discardPile.peek();
        System.out.println(player.name() + "'s turn. Top card: " + topCard);

        if(player.hasValidMove(topCard)){
            Card played = player.playCard();
            discardPile.push(played);
            System.out.println(player.name() + " played: " + played + ".");
            applyAction(played);
        }
        else {
            System.out.println(player.name() + " has no valid move.");
            player.drawCard(deck);
        }
        nextTurn();
    }
    public Player checkWinner(){
        /* Check player's hand is empty */
        for (Player player : players){
            if(player.hand.isEmpty()){
                return player;
            }
        }

    }
    public boolean isValidMove(){
        /* Looks at card the player played and then discard pile and checks to make sure they match either number or color */
        Card played = player.playCard();//Looks at card the player played
        Card topCard = discardPile.peek();//then discard pile
        for (Card card : player.hand.length()){/*Look through the player’s hand. */
            player.hand(i);
            if (played.matches(topCard)){//checks to make sure they match either number or color
                return true;
        }
       }
    }
}
