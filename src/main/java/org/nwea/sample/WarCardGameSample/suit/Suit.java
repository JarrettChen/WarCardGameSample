package org.nwea.sample.WarCardGameSample.suit;

import org.nwea.sample.WarCardGameSample.lookup.Lookup;

/**
 * @author Jarrett
 *
 */
public class Suit {

	private int suitValue;

	/**
	 * @param suit
	 * 
	 * Constructor of Suit class.
	 */
	public Suit(int suit) {
		this.suitValue = suit;
	}

	/**
	 * @return the suit value
	 */
	public int getValue() {
		return suitValue;
	}

	public boolean equals(Object other) {
		if (!(other instanceof Suit)) {
			return false;
		}
		Suit otherRank = (Suit) other;
		return suitValue == otherRank.getValue();
	}

	/**
	 * 
	 *      Print out regular 4-suit name if number of ranks less or equals to
	 *      4, otherwise print out the suit value instead.
	 */
	public String toString() {
		if (suitValue < Lookup.suitName.length) {
			return Lookup.suitName[suitValue];
		}
		return "" + suitValue;
	}
}
