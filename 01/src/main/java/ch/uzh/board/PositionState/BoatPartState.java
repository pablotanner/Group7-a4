package main.java.ch.uzh.board.PositionState;

import main.java.ch.uzh.board.GridType;
import main.java.ch.uzh.board.Position;

public class BoatPartState implements PositionState {
    @Override
    public boolean attack(Position position) {
        return position.getBoat().takeHitAtPosition(position);
    }

    @Override
    public String revealContent(GridType gridType, Position position) {
        if(gridType == GridType.OCEAN_GRID || gridType == GridType.CHEAT_GRID || position.wasTarget()){
            return position.getBoat().showStatusAtPosition(position, gridType);
        }
    //If Grid is TargetGrid and Position was not Hit yet.
    return position.getStatusView();
    }
}

