package main.java.ch.uzh.boat.BoatState;

import main.java.ch.uzh.board.GridType;
import main.java.ch.uzh.board.Position;

public class DestroyedBoatState implements BoatState {
    @Override
    public String showStatusAtPosition(Position position, GridType gridType) {
        if(gridType == GridType.OCEAN_GRID){
            return position.getBoat().getDamage();
        }
        return position.getBoat().getRepresentator();
    }
}

