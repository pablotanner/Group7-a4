package main.java.ch.uzh.boat.BoatState;

import main.java.ch.uzh.board.GridType;
import main.java.ch.uzh.board.Position;
import main.java.ch.uzh.boat.Boat;

public class NotDestroyedState implements BoatState {
    private final Boat boat;

    public NotDestroyedState(Boat boat){
        this.boat = boat;
    }

    @Override
    public String showStatusAtPosition(Position position, GridType gridType) {
        if(gridType == GridType.OCEAN_GRID){
            // If Position hasn't been hit display representator
            if(boat.getSpan().contains(position)){
                return boat.getRepresentator();
            }
            else{
                // If Position has been hit display "X"
                return boat.getDamage();
            }
        }
        // if Target Grid and Boat took damage but not destroyed
        return boat.getDamage();
    }

    @Override
    public boolean takeHitAtPosition(Position position) {
        if (boat.getSpan().contains(position)) {
            boat.removePositionFromSpan(position);
            if (boat.getSpan().isEmpty()) {
                boat.setBoatState(boat.getIsDestroyedState());
                boat.markAsDestroyed();
            }
        }
        return !(boat.stillAlive());
    }
}
