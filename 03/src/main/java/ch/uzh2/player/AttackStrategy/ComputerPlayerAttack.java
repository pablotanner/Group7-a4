package main.java.ch.uzh2.player.AttackStrategy;
import main.java.ch.uzh2.player.IPlayer;
import main.java.ch.uzh2.board.Position;

public class ComputerPlayerAttack extends AbstractAttackStrategy{

    public ComputerPlayerAttack() {}

    @Override
    public void shootAt(IPlayer opponent) {
        while(true) {
            Position position = opponent.getRandomGridPosition();
            if (!opponent.wasAttackedAtPosition(position)) {
                opponent.takeShotAt(position);
                break;
            }
        }
    }
}
