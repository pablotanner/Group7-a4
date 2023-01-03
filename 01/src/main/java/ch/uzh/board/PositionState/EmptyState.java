package main.java.ch.uzh.board.PositionState;

import main.java.ch.uzh.board.GridType;
import main.java.ch.uzh.board.Position;

public class EmptyState implements PositionState {
    @Override
    public boolean attack(Position position) {
        position.setStatusViewToOceanHit();
        return false;
    }
    @Override
    public String revealContent(GridType gridType, Position position) {
        if(position.wasTarget()){
            return position.getStatusView();
        }
        return position.getStatusView();
    }
}

