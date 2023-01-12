package main.java.ch.uzh.boat.BoatState;

import main.java.ch.uzh.board.GridType;
import main.java.ch.uzh.board.Position;
import main.java.ch.uzh.boat.Boat;

public class IsDestroyedState implements BoatState {
    private final Boat boat;

    public IsDestroyedState(Boat boat){
        this.boat = boat;
    }
    @Override
    public String showStatusAtPosition(Position position, GridType gridType) {
        if(gridType == GridType.OCEAN_GRID){
            return boat.getDamage();
        }
        return boat.getRepresentator();
    }

    @Override
    public boolean takeHitAtPosition(Position position) {
        //Does nothing, returns true (since destroyed) but this is never reached.
        return true;
    }
}

