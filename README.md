# WarCardGameSample

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
