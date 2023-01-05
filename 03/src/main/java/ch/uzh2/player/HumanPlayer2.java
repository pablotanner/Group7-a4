package main.java.ch.uzh2.player;

import main.java.ch.uzh2.player.AttackStrategy.HumanPlayerAttack;
import main.java.ch.uzh2.player.PlaceBoatsStrategy.HumanPlayerPlacement;
import main.java.ch.uzh2.boat.Fleet;


public class HumanPlayer2 extends AbstractPlayer {

    public HumanPlayer2() {
        super(new HumanPlayerPlacement(), new HumanPlayerAttack(), new Fleet());
    }
}
