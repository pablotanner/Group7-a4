package main.java.ch.uzh2.player;

import main.java.ch.uzh2.player.AttackStrategy.ComputerPlayerAttack;
import main.java.ch.uzh2.player.PlaceBoatsStrategy.ComputerPlayerPlacement;
import main.java.ch.uzh2.boat.Fleet;

// this shall be the default strategy for the computer player (as described by the rules of the assignment).
public class ComputerPlayer extends AbstractPlayer {

    public ComputerPlayer() {
        super(new ComputerPlayerPlacement(), new ComputerPlayerAttack(), new Fleet(), "Computer");
    }
}
