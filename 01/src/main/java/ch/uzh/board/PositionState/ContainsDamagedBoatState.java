package main.java.ch.uzh.board.PositionState;

import main.java.ch.uzh.board.GridType;
import main.java.ch.uzh.board.Position;
import main.java.ch.uzh.boat.Boat;

public class ContainsDamagedBoatState implements PositionState {
    private final Position position;

    public ContainsDamagedBoatState(Position position){
        this.position = position;
    }

    @Override
    public boolean attack() {
        //Does nothing, returns false but this is never reached.
        return false;
    }

    @Override
    public String revealContent(GridType gridType) {
        return position.getBoat().showStatusAtPosition(position, gridType);
    }
    @Override
    public void placeBoat(Boat boat) {
        //Does nothing
    }
}
