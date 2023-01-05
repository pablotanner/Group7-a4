package main.java.ch.uzh2.player.PlaceBoatsStrategy;

import main.java.ch.uzh2.board.Grid;
import main.java.ch.uzh2.board.Position;
import main.java.ch.uzh2.boat.*;
import main.java.ch.uzh2.player.IPlayer;

import java.util.InputMismatchException;

public class AbstractPlacementStrategy implements IPlacementStrategy{

    public void placeBoats(Fleet fleet, Grid grid, IPlayer player){}

    protected void placeOneBoat(Boat boat, Grid grid, Position start, Position end){

        if (!boat.fitsBetween(start, end))
            throw new InputMismatchException("The ship can't be inserted, provided coordinates don't match the size of the ship");

        if(grid.canBeBoatPutBetween(start, end)){
            grid.putBoatBetweenPositions(boat, start, end);
        }
        else{
            throw new InputMismatchException("You cannot put a boat on occupied places.");
        }

    }

}
