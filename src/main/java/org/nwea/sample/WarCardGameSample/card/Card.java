package org.nwea.sample.WarCardGameSample.card;

import org.nwea.sample.WarCardGameSample.rank.Rank;
import org.nwea.sample.WarCardGameSample.suit.Suit;

/**
 * @author Jarrett
 *
 */
public class Card {

	private Suit suit;
	private Rank rank;
	
	/**
	 * @param suit
	 * @param rank
	 * 
	 * Constructor of Card class
	 */
	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	/**
	 * @return the rank value of the card
	 */
	public int getRank() {
		return rank.getValue();
	}
	
    /** 
     * if the rank and suit are equal, then the card are equal
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object other) {
        if (!(other instanceof Card)) {
            return false;
        }
        Card otherCard = (Card) other;
        return this.rank.equals(otherCard.rank) && this.suit.equals(otherCard.suit);
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "Card<" + suit.toString() + ", " + rank.toString() + ">";
    }
    
    /**
     * @param otherCard
     * @return compare value of this.card and otherCard
     * 
     * Compare the rank value of this.card and otherCard
     */
    public int compareTo(Card otherCard) {
        return this.rank.compareTo(otherCard.rank);
    }
	
}
