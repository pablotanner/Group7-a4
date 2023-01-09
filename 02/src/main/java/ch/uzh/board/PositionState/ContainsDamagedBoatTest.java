package main.java.ch.uzh.board.PositionState;

import main.java.ch.uzh.board.Grid;
import main.java.ch.uzh.board.GridType;
import main.java.ch.uzh.board.Position;
import main.java.ch.uzh.boat.Boat;
import main.java.ch.uzh.boat.PatrolBoat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContainsDamagedBoatTest {
    Grid grid = new Grid("Test Grid");
    Position randomPosition1 = grid.getRandomPosition();
    Position randomPosition2 = grid.getRandomPositionExcept(randomPosition1);
    PositionState containsDamagedBoatState = randomPosition1.getContainsDamagedBoatState();
    Boat boat = new PatrolBoat();

    @Test
    void testPlaceRaisesException(){
        assertThrows(IllegalStateException.class, () -> containsDamagedBoatState.placeBoat(boat));
    }

    @Test
    void testAttackRaisesException(){
        assertThrows(IllegalStateException.class, () -> containsDamagedBoatState.attack());
    }

    @Test
    void testRevealContentBoatHit(){
        grid.putBoatAtPosition(boat, randomPosition1);
        grid.putBoatAtPosition(boat, randomPosition2);
        randomPosition1.attack();
        assertEquals(boat.getDamage(), containsDamagedBoatState.revealContent(GridType.TARGET_GRID));
        assertEquals(boat.getDamage(), containsDamagedBoatState.revealContent(GridType.OCEAN_GRID));
    }

    @Test
    void testRevealContentBoatDestroyedOceanGrid(){
        grid.putBoatAtPosition(boat, randomPosition1);
        randomPosition1.attack();
        assertEquals(boat.getDamage(), containsDamagedBoatState.revealContent(GridType.OCEAN_GRID));
    }

    @Test
    void testRevealContentBoatDestroyedTargetGrid(){
        grid.putBoatAtPosition(boat, randomPosition1);
        randomPosition1.attack();
        assertEquals(boat.getRepresentator(), containsDamagedBoatState.revealContent(GridType.TARGET_GRID));
    }
}
