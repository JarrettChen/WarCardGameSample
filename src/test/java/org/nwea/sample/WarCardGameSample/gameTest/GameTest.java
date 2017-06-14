package org.nwea.sample.WarCardGameSample.gameTest;

import junit.framework.TestCase;
import org.junit.Test;

import org.nwea.sample.WarCardGameSample.war.WarGame;

/**
 * Unit test for the methods in class WarGame.
 */
public class GameTest extends TestCase {

	/**
	 * Test the method divideCards(),
	 * which divides equal number of cards 
	 * and deal to each players
	 */  
	@Test
	public void testDivideCards() {

		WarGame game = new WarGame(4, 13, 3);
		game.divideCards();

		assertEquals(game.getCurrentPlayers().size(), 3);
		assertNotNull(game.getCurrentPlayers().get(0));
		assertEquals(game.getCurrentPlayers().get(0).getNumber(), 17);
		assertNotNull(game.getCurrentPlayers().get(1));
		assertEquals(game.getCurrentPlayers().get(1).getNumber(), 17);
		assertNotNull(game.getCurrentPlayers().get(2));
		assertEquals(game.getCurrentPlayers().get(2).getNumber(), 17);
	}

	/**
	 * Test 3 players play with 3 different cards. There will be only one winner.
	 */  
	@Test
	public void testStartGameWithOneWinner() {
		
		WarGame game = new WarGame(1, 3, 3);
		game.startGame();
		
		assertEquals(game.getCurrentPlayers().size(), 1);
		assertNotNull(game.getCurrentPlayers().get(0));
		assertEquals(game.getCurrentPlayers().get(0).getNumber(), 3);		
	}
	
	/**
	 * Test 3 players play with 3 same rank cards. There will be no one winner, it's a draw.
	 */  
	@Test
	public void testStartGameWithDraw() {
		
		WarGame game = new WarGame(3, 1, 3);
		game.startGame();
		
		assertEquals(game.getCurrentPlayers().size(), 0);
	}
	
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("GameTest");
	}

}
