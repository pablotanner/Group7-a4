package main.java.ch.uzh.board.BoatState;

import main.java.ch.uzh.board.GridType;
import main.java.ch.uzh.board.Position;

public class FloatingBoatState implements BoatState {
    @Override
    public String showStatusAtPosition(Position position, GridType gridType) {
        if(gridType == GridType.OCEAN_GRID){
            // If Position hasn't been hit display representator
            if(position.getBoat().getSpan().contains(position)){
                return position.getBoat().getRepresentator();
            }
            else{
                // If Position has been hit display "X"
                return position.getBoat().getDamage();
            }
        }
        // if Target Grid and Boat took damage but not destroyed
        return position.getBoat().getDamage();
    }
}
