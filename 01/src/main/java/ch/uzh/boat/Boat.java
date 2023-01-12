package main.java.ch.uzh.boat;

import main.java.ch.uzh.boat.BoatState.BoatState;
import main.java.ch.uzh.boat.BoatState.NotDestroyedState;
import main.java.ch.uzh.board.Direction;
import main.java.ch.uzh.board.GridType;
import main.java.ch.uzh.board.Position;
import main.java.ch.uzh.boat.BoatState.IsDestroyedState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Boat {

    /**
     * Maybe we only have to count the number of hits and not store position.
     * We must make sure, that a position can only be shot once!
     */
    private final BoatState isDestroyedState;
    private final BoatState notDestroyedState;
    private BoatState boatState;
    private boolean isDestroyed;
    private final String type;
    private final String representator;
    private final int size;
    private final String damage;
    private final List<Position> span = new ArrayList<>();
    private int hitCount;

    public Boat(String type, String representator, int size) {
        isDestroyedState = new IsDestroyedState(this);
        notDestroyedState = new NotDestroyedState(this);
        boatState = notDestroyedState;
        this.type = type;
        this.representator = representator;
        this.size = size;
        this.hitCount = 0;
        this.isDestroyed = false;
        this.damage = "X";
    }
    public void setBoatState(BoatState state){
        if(state == null){
            throw new IllegalArgumentException("State must not be null");
        }
        boatState = state;
    }
    public BoatState getBoatState(){
        return boatState;
    }

    public BoatState getIsDestroyedState(){
        return isDestroyedState;
    }

    public BoatState getNotDestroyedState(){
        return notDestroyedState;
    }

    //Handled by State:
    public boolean takeHitAtPosition(Position position) {
        //Check pre-conditions
        if(position == null){
            throw new IllegalArgumentException("Position must not be null");
        }
        return boatState.takeHitAtPosition(position);
    }

    public String showStatusAtPosition(Position position, GridType gridType) {
        //Check pre-conditions
        if(position == null){
            throw new IllegalArgumentException("Position must not be null");
        }
        if(gridType == null){
            throw new IllegalArgumentException("GridType must not be null");
        }
        return boatState.showStatusAtPosition(position, gridType);
    }

    public void markAsDestroyed(){
        isDestroyed = true;
    }
    public void expandSize(Position position) {
        if (!span.contains(position)) {
            span.add(position);
        }
    }

    public String getDamage(){
        return damage;
    }
    public String getRepresentator(){
        return representator;
    }

    public List<Position> getSpan(){
        //Return a copy of the span (for checking size, contains, etc.) to not break encapsulation
        return Collections.unmodifiableList(span);
    }

    public void removePositionFromSpan(Position position){
        //Check pre-conditions
        if(!span.contains(position)){
            throw new IllegalArgumentException("Position not in span");
        }
        if(position == null){
            throw new IllegalArgumentException("Position must not be null");
        }
        span.remove(position);
    }

    public boolean fitsBetween(Position start, Position end) {
        int targetSize = start.distanceTo(end).orElse(-1) + 1;
        // If coordinates are not a straight line, distanceTo returns null.
        // We count the number of cells here, therefore the +1 at the end
        return this.getSize() == targetSize;
    }

    public Position getEndPositionForDirection(Position start, Direction direction) {
        int distance = this.size - 1;
        Position end = start;
        while (distance > 0 && end != null) {
            end = end.neighbour(direction);
            distance--;
        }
        return end; // end is null, if neighbour of last iteration of while loop was no valid position
    }

    public boolean stillAlive() {
        return !this.isDestroyed;
    }

    @Override
    public String toString() {
        return this.type;
    }

    public int getSize(){
        return this.size;
    }
}
