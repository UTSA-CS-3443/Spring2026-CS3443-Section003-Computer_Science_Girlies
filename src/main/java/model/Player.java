import java.util.ArrayList;

public class Player {
  String name;
  ArrayList<Card> hand;
  boolean isBot;

  public Card playCard(Card TopCard){
    for (int i = 0; i < hand.size; i++){
      Card current = hand.get(i); //I need to read the hand of the player, 

      if (current.matches(topCard)){
        hand.remove(i); //pick a car that card that gets played would then get removed from the List, 
        return current; //return the card as the card played;
      }
    }
    
    
    
    return null;
  }
  public void drawCard(Deck deck){
    // This card needs to read the deck
    Card drawnCard = deck.drawCard(deck);
    //add a card to ArrayList<Card> hand
    //return either the drawn card or nothing since if deck is empty
    if (drawnCard != null){
      hand.add(drawnCard);
    }
    else {
      System.out.println("The deck is empty. We cannot draw a Card.");
    }

  }
  public boolean hasValidMove(Card topCard){
    for (Card card : hand){//Look through the player’s hand. 
      if (card.matches(topCard)){ //Needs to check: Top card on discard pile, Current suit/color, Any active rules affecting
        return true;
      }
    }
    return false;
  }
}
