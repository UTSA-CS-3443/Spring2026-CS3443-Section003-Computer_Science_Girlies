import java.util.ArrayList;

public class Player {
  String name;
  ArrayList<Card> hand;
  boolean isBot;

  public Card playCard(ArrayList<Card> hand, ){
    /*I need to read the hand of the player, 
    pick a car that card that gets played would then get removed from the List, 
    return the card as the card played;*/
  }
  public void drawCard(){
    // This card needs to read the deck
    //add a card to ArrayList<Card> hand
    //return either the drawn card or nothing since deck would manage externally*/

  }
  public boolean hasValidMove(){
    /*Purpose:
    Checks whether the player can make a legal move given the current game situation.
    Look through the player’s hand. Determine if at least one card is playable

    Needs to check: Top card on discard pile, Current suit/color, Any active rules affecting play */
  }
}
