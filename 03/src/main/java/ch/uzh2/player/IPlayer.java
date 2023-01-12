package main.java.ch.uzh2.player;


import main.java.ch.uzh2.board.Grid;
import main.java.ch.uzh2.board.GridType;
import main.java.ch.uzh2.board.Position;

// This interface is used to implement the Strategy Design Pattern.
public interface IPlayer {
    void assignGrid(Grid grid);
    void placeFleet();


    void shootAt(IPlayer opponent);
    void takeShotAt(Position position);
    Boolean fleetIsAlive();
    Position getRandomGridPosition();
    boolean wasAttackedAtPosition(Position position);
    String showGridContent(GridType gridType);

}
