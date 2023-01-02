package main.java.ch.uzh2;

import main.java.ch.uzh2.player.ComputerPlayer;
import main.java.ch.uzh2.player.HumanPlayer;
import main.java.ch.uzh2.game.Game;

public class Main {

    public static void main(String[] args) {

        //create new game and play the game
        Game game = new Game(new HumanPlayer("Student"), new ComputerPlayer());
        game.setup();
        game.play();
    }
}
