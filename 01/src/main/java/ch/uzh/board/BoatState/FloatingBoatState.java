package main.java.ch.uzh.board.BoatState;

import main.java.ch.uzh.board.GridType;
import main.java.ch.uzh.board.Position;

public class FloatingBoatState implements BoatState {
    @Override
    public String showStatusAtPosition(Position position, GridType gridType) {
        if(gridType == GridType.OCEAN_GRID){
            // Show X for damaged positions, representator for all other positions
            if(position.getBoat().getSpan().contains(position)){
                return position.getBoat().getRepresentator();
            }
            else{
                return position.getBoat().getDamage();
            }
        }
        // Only show damaged positions
        return position.getBoat().getDamage();
    }
}
