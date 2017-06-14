package org.nwea.sample.WarCardGameSample.rank;

import org.nwea.sample.WarCardGameSample.suit.Suit;
import org.nwea.sample.WarCardGameSample.lookup.Lookup;

/**
 * @author Jarrett
 *
 */
public class Rank {

	private int rankValue;

	/**
	 * @param rank
	 * 
	 * Constructor of Rank class
	 */
	public Rank(int rank) {
		this.rankValue = rank;
	}

	public int getValue() {
		return rankValue;
	}

	
	public boolean equals(Object other) {
		if (!(other instanceof Rank)) {
			return false;
		}
		Suit otherRank = (Suit) other;
		return rankValue == otherRank.getValue();
	}

	/**
	 * 
	 * 
	 *      Print out regular 13-rank name if number of ranks less or equals to
	 *      13, otherwise print out the rank value instead.
	 */
	public String toString() { 
		if (rankValue < Lookup.rankName.length) {
			return Lookup.rankName[rankValue];
		}
		return "" + rankValue;
	}

	/**
	 * @param other
	 * @return compare value of two ranks
	 */
	public int compareTo(Rank other) {
		if (rankValue == other.getValue()) {
			return 0;
		} else if (rankValue > other.getValue()) {
			return -1;
		} else
			return 1;
	}
}
