package main.java.ch.uzh.board.PositionState;

import main.java.ch.uzh.board.GridType;
import main.java.ch.uzh.board.Position;
import main.java.ch.uzh.boat.Boat;

public class ContainsBoatState implements PositionState {
    private final Position position;

    public ContainsBoatState(Position position){
        this.position = position;
    }

    @Override
    public boolean attack() {
        position.setState(position.getContainsDamagedBoatState());
        return position.getBoat().takeHitAtPosition(position);
    }

    @Override
    public String revealContent(GridType gridType) {
        if(gridType == GridType.OCEAN_GRID || gridType == GridType.CHEAT_GRID){
            return position.getBoat().showStatusAtPosition(position, gridType);
        }
        //If Grid is TargetGrid and Position was not Hit yet.
        return position.getStatusView();
    }

    @Override
    public void placeBoat(Boat boat) {
        throw new IllegalStateException("Position already contains a Boat.");
    }

}
