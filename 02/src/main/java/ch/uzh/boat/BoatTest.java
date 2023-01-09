package main.java.ch.uzh.boat;

import main.java.ch.uzh.board.Grid;
import main.java.ch.uzh.board.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoatTest {
    Grid grid = new Grid("Test Grid");
    Position randomPosition1 = grid.getRandomPosition();
    Position randomPosition2 = grid.getRandomPositionExcept(randomPosition1);
    Boat boat = new PatrolBoat();
    @Test
    void testToString(){
        Boat battleShip = new Battleship();
        assertEquals("Battleship", battleShip.toString());
        Boat patrolBoat = new PatrolBoat();
        assertEquals("Patrol boat", patrolBoat.toString());
        Boat carrier = new Carrier();
        assertEquals("Carrier", carrier.toString());
        Boat submarine = new Submarine();
        assertEquals("Submarine", submarine.toString());
    }

    @Test
    void testStillAliveTrue(){
        Boat battleShip = new Battleship();
        assertTrue(battleShip.stillAlive());
    }

    @Test
    void testStillAliveFalse(){
        grid.putBoatAtPosition(boat, randomPosition1);
        boat.takeHitAtPosition(randomPosition1);
        assertFalse(boat.stillAlive());
    }

    @Test
    void testExpand(){
        assertTrue(boat.getSpan().isEmpty());
        boat.expandSize(randomPosition1);
        assertEquals(1,boat.getSpan().size());
        assertTrue(boat.getSpan().contains(randomPosition1));
        boat.expandSize(randomPosition2);
        assertEquals(2,boat.getSpan().size());
        assertTrue(boat.getSpan().contains(randomPosition2));
    }
}
