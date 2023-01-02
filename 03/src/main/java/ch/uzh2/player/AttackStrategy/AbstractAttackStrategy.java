package main.java.ch.uzh2.player.AttackStrategy;

import main.java.ch.uzh2.player.AttackStrategy.IAttackStrategy;
import main.java.ch.uzh2.board.Position;

import java.util.HashSet;
import java.util.Set;

abstract class AbstractAttackStrategy implements IAttackStrategy {
    Set<Position> shotsTaken;

    AbstractAttackStrategy() {
        shotsTaken = new HashSet<>();
    }
}
