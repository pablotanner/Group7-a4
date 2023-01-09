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
        throw new IllegalStateException("Position is already hit.");
    }

    @Override
    public String revealContent(GridType gridType) {
        return position.getBoat().showStatusAtPosition(position, gridType);
    }
    @Override
    public void placeBoat(Boat boat) {
        throw new IllegalStateException("Position already contains a Boat.");
    }

}
