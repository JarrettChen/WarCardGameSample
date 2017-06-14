package org.nwea.sample.WarCardGameSample.war;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.nwea.sample.WarCardGameSample.card.Card;
import org.nwea.sample.WarCardGameSample.deck.Deck;
import org.nwea.sample.WarCardGameSample.deck.DeckImpl;
import org.nwea.sample.WarCardGameSample.player.Player;

/**
 * @author Jarrett
 * 
 * WarGame class is used to simulate the war card game process. 
 * 
 * The rules has been followed: 
 * 
 * 1. Each player deals a card on table, if only one wins, takes all the card on table.
 *  
 * 2. If multiple players deal same largest rank card, start a war for these player.
 * 
 * 3. In a war, each participant deals three cards face-down on table, then compare 
 *    the fourth cards been dealt (If some participants don't have 4 cards in hand, 
 *    then go with the number of cards from whom has least number of hand cards).
 *    if only one wins, takes all the card on table. If still multiple participant win, then 
 *    go next war for them.
 *    
 * 4. After one round, who doesn't have card is auto lost.
 * 
 * 5. Play until one wins all the cards, or if everyone has put their cards on table, 
 *    but no one can win them, it will be a draw game.   
 * 
 * 
 * The game steps are as below:
 * 
 * Step 1: use divideCard() method, 
 * 		   divide cards and deal cards to players, 
 *         make sure each one has same number of cards.
 * 
 * Step 2: use startGame() method, start game
 * 
 * Step 3: oneRoundPlay() method, simulate one round in the game.
 * 		   There are 3 cases:
 * 		   Case 1: one wins the game or a draw game, then go to Step 5.
 * 		   Case 2: one wins this round, but no war is started, then go back to Step 3.
 * 		   Case 3: multiple players deal same largest rank card, a war starts. Go to Step 4.
 * 
 * Step 4: startWar() method, simulate a war happened in one round.
 *  	   There are 3 cases:
 * 		   Case 1: one wins the game or a draw game, then go to Step 5.
 * 		   Case 2: one wins this war, but no next war is started, then go back to Step 3.
 * 		   Case 3: multiple players deal same largest rank card, a next war starts. Go back to Step 4.
 * 
 * Step 5: when one wins the game, or a draw game, or the maximum time is reached, one game is 
 * 		   ended.
 *  
 */
public class WarGame {

	private Deck deck;
	private int totalPlayers;
	private List<Player> currentPlayers; // Track each player's hand. If some players are auto lost, remove them form the list.
	private boolean ableToEnd = false;   // Sign to end the game.
	private int maxNumber;         // Maximum time can be reached, in millisecond.
	
	/**
	 * @param numberOfSuits
	 * @param numberOfRanks
	 * @param numberOfPlayers
	 * 
	 * Constructor of WarGame class.
	 */
	public WarGame(int numberOfSuits, int numberOfRanks, int numberOfPlayers) {
		
		/* When number of suits or ranks is invalid, throw an exception. */
		if(numberOfSuits > 4 || numberOfSuits <= 0 || numberOfRanks > 13 || numberOfRanks <= 0) {
			throw new IllegalArgumentException("Invalid number of suits or ranks");
		}
		
		int totalCards = numberOfSuits * numberOfRanks;
		
		/* When number of players is invalid, throw an exception. */
		if(totalCards < numberOfPlayers || numberOfPlayers < 2) {
			throw new IllegalArgumentException("Invalid number of players");
		}
		
		this.deck = new DeckImpl();
		deck.create(numberOfSuits, numberOfRanks);
		
		this.totalPlayers = numberOfPlayers;
		
		this.maxNumber = totalCards * 10 * 1000;
		
		currentPlayers = new ArrayList<Player>();
		for(int i = 0; i < numberOfPlayers; i++) {
			currentPlayers.add(new Player(i));
		}
	}
	
	/**
	 * @return the currentPlayers list
	 */
	public List<Player> getCurrentPlayers() {
		return currentPlayers;
	}
	
	/**
	 * Divide cards from a deck and deal cards to players, 
	 * make sure each one has same number of cards.
	 */
	public void divideCards() {
		
		int totalCards = deck.size();
		
		/* Shuffle the deck. */
		deck.shuffle();
		
		/* Give players cards. */
		for(int i = 0; i < totalPlayers; i++) {
			List<Card> curPlayerCards = new LinkedList<Card>();
			for(int j = 0; j < totalCards / totalPlayers; j++) {
				curPlayerCards.add(deck.deal());
			}
			currentPlayers.get(i).addMultiple(curPlayerCards);
		}
		
	}
	
	/**
	 *  Start the game.
	 */
	public void startGame() {
		
		divideCards();
		
		/* Simulate each round play. */
		long startTime = System.currentTimeMillis();
		while((System.currentTimeMillis() - startTime) < maxNumber) {
			
			oneRoundPlay();
			
			/* When game end condition reached, break the loop. */
			if(ableToEnd) {
				break;
			}
		}
		
		if(ableToEnd) {
			System.out.println("Successfully finished one game!"); // One wins the game or draw game.
		}
		else {
			System.out.println("Reached the maximum time."); // Reach the maximum time.
		}
	}
	
	/**
	 * Simulate one round play.
	 */
	public void oneRoundPlay() {
		
		int curPlayersNum = currentPlayers.size();
		int[] oneRoundRanks = new int[curPlayersNum]; // Record all the rank of cards put on table, then compare who is winner.
		
		List<Card> oneRoundCards = new ArrayList<Card>(); // Record all the cards put on table.
		
		for(int i = 0; i < curPlayersNum; i++) {
			Card curCard = currentPlayers.get(i).deal();
			oneRoundRanks[i] = curCard.getRank();
			
			oneRoundCards.add(curCard);
			System.out.println("Player " + currentPlayers.get(i).getId() + " plays " + curCard);
		}
		
		List<Integer> hasMaxPlayer = findMaxPlayer(oneRoundRanks); // The indices of the players in List<Integer> currentPlayer, who played the maximum rank card
		
		/* see this method comments. */
		oneRoundProcess(hasMaxPlayer, oneRoundCards);
		
	}
	

	/**
	 * @param hasMaxPlayer
	 * @param oneRoundCards
	 * 
	 * 	
	 * There are 3 cases:
	 * Case 1: one wins the game or a draw game, then go to Step 5.
	 * Case 2: one wins this round, but no war is started, then go back to Step 3.
	 * Case 3: multiple players deal same largest rank card, a war starts. Go to Step 4.
	 * 
	 * Determine now is on which case.
	 */
	private void oneRoundProcess(List<Integer> hasMaxPlayer, List<Card> oneRoundCards) {
		/* Case 1: one wins game, or Case 2: one wins this round */
		if(hasMaxPlayer.size() == 1) {
			int oneRoundWinner = hasMaxPlayer.get(0);
			oneWinThisRound(oneRoundCards, oneRoundWinner);
		}
		/* Case 3 */
		else if(hasMaxPlayer.size() > 1) {
			startWar(hasMaxPlayer, oneRoundCards);
		}
		/* Case 1: draw game. */
		else {
			System.out.println("Draw game. Start next game.");
			ableToEnd = true;
		}
	}
	
	/**
	 * @param hasMaxPlayer
	 * @param oneRoundCards
	 * 
	 * Start a war. 
	 */
	private void startWar(List<Integer> hasMaxPlayer, List<Card> oneRoundCards) {
		/* remove players who don't have cards in hand from List<Integer> currentPlayers. */ 
		Iterator<Integer> itr = hasMaxPlayer.iterator();
		int index = 0;
		int sign = 0;
		while (index < hasMaxPlayer.size() ) {
			int x = hasMaxPlayer.get(index) + sign;
			hasMaxPlayer.set(index, x);
				if (!currentPlayers.get(x).hasCard()) {
					currentPlayers.remove(x);
					hasMaxPlayer.set(index, -1);
					sign--;
				}
				index++;
		}
		while(itr.hasNext()) {
			if(itr.next() == -1) itr.remove();
		}
		
		/* Case 1: draw game. */
		if(hasMaxPlayer.size() == 0) {			
			System.out.println("Draw game. Start next game.");
			ableToEnd = true;
		}
		if(ableToEnd) return;
		
		/* Start a new war. */
		List<Card> warCards = new ArrayList<Card>();
		int min = findMinHand(hasMaxPlayer); // Find the minimal number of cards from the participants' hand.
		int fourOrLess = min >= 4 ? 4 : min; // Use either 4 cards or the minimal number of cards to do the war.
		for(int j = 0; j < fourOrLess; j++) {
			for(Integer i : hasMaxPlayer) {
				Card cur = currentPlayers.get(i).deal();
				oneRoundCards.add(cur);
				if(j == fourOrLess - 1) {
					/* Compare the last cards. */
					warCards.add(cur);
					System.out.println("Player " + currentPlayers.get(i).getId() + " plays " + cur);
				}
				else {
					/* Face-down cards represented as XX. */
					System.out.println("Player " + currentPlayers.get(i).getId() + " plays XX");
				}
			}
		}
		
		/* Compare the last cards. */
		int[] warCardsRanks = new int[warCards.size()];
		for(int i = 0; i < warCards.size(); i++) {
			warCardsRanks[i] = warCards.get(i).getRank();
		}
		
		hasMaxPlayer = findMaxPlayer(warCardsRanks); // Update the indices of the players in List<Integer> currentPlayer, who played the maximum rank card
		

		oneRoundProcess(hasMaxPlayer, oneRoundCards);
		
	}
	
	/**
	 * @param hasMaxPlayer
	 * @return the minimal number of cards from the participants' hand
	 */
	private int findMinHand(List<Integer> hasMaxPlayer) {
		int min = Integer.MAX_VALUE;
		for(Integer i : hasMaxPlayer) {
			int cur = currentPlayers.get(i).getNumber();
			if(cur < min) min = cur;
		}
		return min;
	}
	
	/**
	 * @param cards
	 * @param index
	 * 
	 * Give all the cards on table to the only winner of this round, 
	 * then determine now on which case.
	 * 
	 * There are 2 cases:
	 * Case 1: one wins the game
	 * Case 2: one wins this round, but no war is started, then go back to Step 3.
	 */
	private void oneWinThisRound(List<Card> cards, int index) {
		
		/* Give all the cards on table to the only winner of this round. */
		currentPlayers.get(index).addMultiple(cards);
		System.out.println("Player " + currentPlayers.get(index).getId() + " wins this round.");
		
		/* Remove the players who don't have cards in hand. */
		Iterator<Player> itr = currentPlayers.iterator();
		while (itr.hasNext()) {
			Player x = itr.next();
			if (!x.hasCard()) {
				itr.remove();
			}
		}
		
		if(currentPlayers.size() == 1) {
			/* Case 1: one wins the game. */
			int id = currentPlayers.get(0).getId();
			System.out.println("Player " + id + " wins this game.");
			ableToEnd = true;
		}
		else if(currentPlayers.size() > 1) {
			/* Case 2: start next turn. */
			System.out.println("Next round");
		}
		
	}
	
	/**
	 * @param ranks
	 * @return a list of indices of the max number in array ranks.
	 */
	private List<Integer> findMaxPlayer(int[] ranks) {
		
		int max = Arrays.stream(ranks).max().getAsInt();
		List<Integer> res = new ArrayList<Integer>();
		for(int i = 0; i < ranks.length; i++) {
			if(ranks[i] == max) res.add(i);
		}
		return res;
	}
	
}
