package main.java.ch.uzh.board.PositionState;

import main.java.ch.uzh.board.Grid;
import main.java.ch.uzh.board.GridType;
import main.java.ch.uzh.board.Position;
import main.java.ch.uzh.boat.Boat;
import main.java.ch.uzh.boat.PatrolBoat;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class ContainsBoatStateTest {
    Grid grid = new Grid("Test Grid");
    Position randomPosition1 = grid.getRandomPosition();
    Position randomPosition2 = grid.getRandomPositionExcept(randomPosition1);
    PositionState containsBoatState = randomPosition1.getContainsBoatState();
    Boat boat = new PatrolBoat();

    @Test
    void testPlaceBoatInvalid(){
        assertThrows(IllegalStateException.class, () -> containsBoatState.placeBoat(boat));
    }

    @Test
    void testAttackHitBoatReturnsFalse(){
        randomPosition1.placeBoat(boat);
        randomPosition2.placeBoat(boat);
        assertFalse(containsBoatState.attack());
        assertEquals(randomPosition1.getState(), randomPosition1.getContainsDamagedBoatState());
    }
    @Test
    void testAttackDestroyBoatReturnsTrue(){
        grid.putBoatAtPosition(boat, randomPosition1);
        assertTrue(containsBoatState.attack());
        assertEquals(randomPosition1.getState(), randomPosition1.getContainsDamagedBoatState());
    }

    @Test
    void testRevealContentBoatHidden(){
        grid.putBoatAtPosition(boat, randomPosition1);
        assertEquals(randomPosition1.getUnknownContent(), containsBoatState.revealContent(GridType.TARGET_GRID));
    }

    @Test
    void testRevealContentBoatVisible(){
        grid.putBoatAtPosition(boat, randomPosition1);
        assertEquals(boat.getRepresentator(), containsBoatState.revealContent(GridType.OCEAN_GRID));
    }

}
