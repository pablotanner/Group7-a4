package main.java.ch.uzh.board.PositionState;

import main.java.ch.uzh.board.GridType;
import main.java.ch.uzh.board.Position;

public interface PositionState {
    boolean attack(Position position);
    String revealContent(GridType gridType, Position position);
}
