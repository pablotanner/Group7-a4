package main.java.ch.uzh.board.PositionState;
import main.java.ch.uzh.board.GridType;
import main.java.ch.uzh.boat.Boat;

public interface PositionState {
    boolean attack();
    String revealContent(GridType gridType);
    void placeBoat(Boat boat);
}
