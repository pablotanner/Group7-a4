package main.java.ch.uzh.board;
import main.java.ch.uzh.boat.Boat;
import main.java.ch.uzh.boat.PatrolBoat;
import main.java.ch.uzh.boat.Submarine;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {
    Grid grid = new Grid("Test Grid");
    Position randomPosition1 = grid.getRandomPosition();
    Position randomPosition2 = grid.getRandomPosition();
    Boat boat = new PatrolBoat();

    @Test
    void testGetRandomPosition(){
        randomPosition1 = grid.getRandomPosition();
        assertNotNull(randomPosition1);
        assertTrue(randomPosition1.getRow() instanceof Row);
        assertTrue(randomPosition1.getColumn() instanceof Column);
    }

    @Test
    void testPutBoatAtPosition(){
        assertTrue(grid.putBoatAtPosition(boat, randomPosition1));
    }

    @Test
    void testPutBoatAtPositionNull(){
        assertFalse(grid.putBoatAtPosition(boat, null));
    }

    @Test
    void testCanBeBoatPutBetweenTrue(){
        assertTrue(grid.canBeBoatPutBetween(randomPosition1, randomPosition2));
    }

    @Test
    void testCanBeBoatPutBetweenFalse(){
        Position left = new Position(Column.A, Row._0);
        Position middle = new Position(Column.B, Row._0);
        Position right = new Position(Column.C, Row._0);
        Boat submarine = new Submarine();
        grid.putBoatAtPosition(submarine, middle);
        assertFalse(grid.canBeBoatPutBetween(left, right));
    }

    @Test
    void testWasAttackedAtTrue(){
        grid.attackAtPosition(randomPosition1);
        assertTrue(grid.wasAttackedAt(randomPosition1));
    }

    @Test
    void testWasAttackedAtFalse(){
        assertFalse(grid.wasAttackedAt(randomPosition1));
    }

    @Test
    void testPutBoatBetweenPositionsInvalid(){
        //Trying to put a boat between diagonal positions doesn't actually place anything.
        Position p1 = new Position(Column.A, Row._0);
        Position p2 = new Position(Column.B, Row._1);
        grid.putBoatBetweenPositions(boat, p1, p2);
        assertEquals(null, p1.getBoat());
        assertEquals(null, p2.getBoat());
    }

    @Test
    void testPutBoatBetweenPositions(){
        Boat submarine = new Submarine();
        Position p1 = grid.getRandomPosition();
        Position p2 = grid.getRandomPositionExcept(p1);
        //Find two positions where a boat could be placed between
        //and get Position instances that are within the board matrix varaible.
        while(p1.pathTo(p2).size() != 3){
            p1 = grid.getRandomPosition();
            p2 = grid.getRandomPositionExcept(p1);
        }
        grid.putBoatBetweenPositions(submarine, p1, p2);
        Position p3 = grid.getRandomPosition();
        while(!submarine.getSpan().contains(p3) && p1 != p3 && p2 != p3){
            p3 = grid.getRandomPosition();
        }
        assertEquals(submarine, p1.getBoat());
        assertEquals(submarine, p2.getBoat());
        assertEquals(submarine, p3.getBoat());
    }
}
