/**
 * 
 */
package org.nwea.sample.WarCardGameSample.deck;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.List;

import org.nwea.sample.WarCardGameSample.card.Card;
import org.nwea.sample.WarCardGameSample.rank.Rank;
import org.nwea.sample.WarCardGameSample.suit.Suit;

/**
 * @author Jarrett
 *
 */
public class DeckImpl implements Deck {

	private List<Card> deck = null;
	
	
	public void create(int numberOfSuits, int numberOfRanks) {
		
		deck = new LinkedList<Card>();
		
		for(int i = 0; i < numberOfSuits; i++) {
			Suit suit = new Suit(i); 
			for(int j = 0; j < numberOfRanks; j++) {
				Rank rank = new Rank(j);
				Card currentCard = new Card(suit, rank);
				deck.add(currentCard);
			}
		}
	}
	

	public void shuffle() {
		/* Must create a new deck before to shuffle the deck. */
		if (deck == null) {
			throw new RuntimeException("Deck needs to be initialized.");
		}
		
		Collections.shuffle(deck);
	}
	
	
	/** 
	 * @return deal the first card from the deck if there is at least on card
	 * in the deck.
	 */
	public Card deal() {
		/* Must create a new deck before to deal a card. */
		if (deck == null) {
			throw new RuntimeException("Deck needs to be initialized.");
		}
		else if (deck.size() == 0) {
			return null;
		}
		
		return deck.remove(0);
	}
	
	
	/** 
	 * @return the size of the deck
	 */
	public int size() {
		
		if (deck == null) {
			throw new RuntimeException("Deck needs to be initialized.");
		}
		
		return deck.size();
	}
}
