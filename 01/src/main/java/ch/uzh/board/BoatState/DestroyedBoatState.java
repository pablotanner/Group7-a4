package main.java.ch.uzh.board.BoatState;

import main.java.ch.uzh.board.GridType;
import main.java.ch.uzh.board.Position;

public class DestroyedBoatState implements BoatState {
    @Override
    public String showStatusAtPosition(Position position, GridType gridType) {
        return position.getBoat().getRepresentator();
    }
}

