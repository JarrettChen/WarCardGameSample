package org.nwea.sample.WarCardGameSample.deck;

import org.nwea.sample.WarCardGameSample.card.Card;

/**
 * @author Jarrett
 *
 */
public interface Deck {

	/**
	 * @param numberOfSuits
	 * @param numberOfRanks
	 * 
	 * Create the deck of cards
	 */
	public void create(int numberOfSuits, int numberOfRanks);
	
	/**
	 * Shuffle the deck
	 */
	public void shuffle();
	
	/**
	 * @return deal a card from the deck
	 */
	public Card deal();
	
	/**
	 * @return number of cards in the deck
	 */
	public int size();
	
}
