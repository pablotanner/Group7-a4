package main.java.ch.uzh2.player.PlaceBoatsStrategy;

import main.java.ch.uzh2.board.Grid;
import main.java.ch.uzh2.boat.Fleet;
import main.java.ch.uzh2.player.IPlayer;

public interface IPlacementStrategy{

    void placeBoats(Fleet fleet, Grid grid, IPlayer player);
}
