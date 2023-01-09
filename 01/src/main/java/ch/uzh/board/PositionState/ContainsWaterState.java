package main.java.ch.uzh.board.PositionState;

import main.java.ch.uzh.board.GridType;
import main.java.ch.uzh.board.Position;
import main.java.ch.uzh.boat.Boat;

public class ContainsWaterState implements PositionState {
    private final Position position;
    public ContainsWaterState(Position position){
        this.position = position;
    }
    @Override
    public boolean attack() {
        position.setStatusViewToOceanHit();
        return false;
    }

    @Override
    public String revealContent(GridType gridType) {
        return position.getStatusView();
    }

    @Override
    public void placeBoat(Boat boat) {
        position.setState(position.getContainsBoatState());
        position.setBoatAtPosition(boat);
    }
}

