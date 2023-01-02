package main.java.ch.uzh2.player.PlaceBoatsStrategy;

import main.java.ch.uzh2.board.Grid;
import main.java.ch.uzh2.boat.Fleet;

public interface IPlacementStrategy{

    void placeBoats(Fleet fleet, Grid grid);
}
