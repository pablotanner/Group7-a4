package main.java.ch.uzh.boat.BoatState;

import main.java.ch.uzh.board.GridType;
import main.java.ch.uzh.board.Position;

public interface BoatState {
    String showStatusAtPosition(Position position, GridType gridType);
    boolean takeHitAtPosition(Position position);
}
