/**
 * 
 */
package org.nwea.sample.WarCardGameSample.player;

import java.util.LinkedList;
import java.util.List;

import org.nwea.sample.WarCardGameSample.card.Card;

/**
 * @author Jarrett
 *
 */
public class Player {

	private List<Card> hand;
	private int id;

	/**
	 * @param id
	 * 
	 *            Constructor of Player class, each player has an id.
	 */
	public Player(int id) {
		this.hand = new LinkedList<Card>();
		this.id = id;
	}

	/**
	 * @return the id of player
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param card
	 * 
	 *            Add a card to player's hand.
	 */
	public void addCard(Card card) {
		hand.add(card);
	}

	/**
	 * @param list
	 * 
	 *            Add a list of cards to player's hand.
	 */
	public void addMultiple(List<Card> list) {
		hand.addAll(list);
	}

	/**
	 * @return deal the first card from player's hand
	 */
	public Card deal() {
		return hand.remove(0);
	}

	/**
	 * @return player has card or not in hand
	 */
	public boolean hasCard() {
		return !hand.isEmpty();
	}

	/**
	 * @return the number of cards in hand
	 */
	public int getNumber() {
		return hand.size();
	}
}
