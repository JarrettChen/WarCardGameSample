package org.nwea.sample.WarCardGameSample.deckTest;

import junit.framework.TestCase;
import org.junit.Test;

import org.nwea.sample.WarCardGameSample.card.Card;
import org.nwea.sample.WarCardGameSample.deck.Deck;
import org.nwea.sample.WarCardGameSample.deck.DeckImpl;

/**
 * Unit test for the implementation of Deck Interface.
 */
public class DeckTest extends TestCase {

	/**
	 * Test the situation when deal card from a deck 
	 * before creating the deck
	 */ 
	@Test
	public void testBeforeCreating() {
		Deck deck = new DeckImpl();
		try {
			deck.deal();
			assertTrue("There should be a exception", false);
		} 
		catch (Exception e) {
		}
	}

	/**
	 * Test the deck with small number
	 */  
	@Test
	public void testCreateSmallDeck() {
		Deck deck = new DeckImpl();
		deck.create(1, 1);
	}

	/**
	 * Test the deck with small number and two different suits
	 */ 
	@Test
	public void testCreateSmallDeckAndDealTwoDifferentSuits() {
		Deck deck = new DeckImpl();
		deck.create(4, 1);
		deck.shuffle();
		Card cardOne = deck.deal();
		Card cardTwo = deck.deal();
		assertNotNull(cardOne);
		assertNotNull(cardTwo);
		assertNotSame("cardOne and cardTwo are not the same", cardOne, cardTwo);
	}

	/**
	 * Test the deck with small number and two different ranks
	 */ 
	@Test
	public void testCreateSmallDeckAndDealTwoDifferentRanks() {
		Deck deck = new DeckImpl();
		deck.create(1, 5);
		Card cardOne = deck.deal();
		Card cardTwo = deck.deal();
		deck.shuffle();
		assertNotNull(cardOne);
		assertNotNull(cardTwo);
		assertNotSame(cardOne, cardTwo);
	}
	
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("GameTest");
	}

}
