package main.java.ch.uzh2;

import main.java.ch.uzh2.player.HumanPlayer1;
import main.java.ch.uzh2.player.HumanPlayer2;
import main.java.ch.uzh2.game.Game;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //create new game and play the game
        Game game = new Game(new HumanPlayer1(), new HumanPlayer2());
        game.setup();
        game.play();
    }
}
