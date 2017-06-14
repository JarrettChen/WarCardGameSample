package org.nwea.sample.WarCardGameSample;


import org.nwea.sample.WarCardGameSample.war.WarGame;


/**
 * @author Jarrett
 * 
 * Run the app.
 *
 */
public class App {
    public static void main(String[] args) {
        /* Can change the input of WarGame class. */
        WarGame war = new WarGame(3, 5, 3);
        war.startGame();
    }
}

