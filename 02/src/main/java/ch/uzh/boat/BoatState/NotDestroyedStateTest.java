package main.java.ch.uzh.boat.BoatState;
import main.java.ch.uzh.board.Grid;
import main.java.ch.uzh.board.GridType;
import main.java.ch.uzh.board.Position;
import main.java.ch.uzh.boat.Boat;
import main.java.ch.uzh.boat.PatrolBoat;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NotDestroyedStateTest {
    Grid grid = new Grid("Test Grid");
    Position randomPosition1 = grid.getRandomPosition();
    Position randomPosition2 = grid.getRandomPositionExcept(randomPosition1);
    Boat boat = new PatrolBoat();
    BoatState notDestroyedState = boat.getNotDestroyedState();

    @Test
    void testTakeHitAtPositionBoatSurvives() {
        grid.putBoatAtPosition(boat, randomPosition1);
        grid.putBoatAtPosition(boat, randomPosition2);
        assertFalse(notDestroyedState.takeHitAtPosition(randomPosition1));
    }
    @Test
    void testTakeHitAtPositionBoatDestroyed() {
        grid.putBoatAtPosition(boat, randomPosition1);
        grid.putBoatAtPosition(boat, randomPosition2);
        assertFalse(notDestroyedState.takeHitAtPosition(randomPosition1));
        assertTrue(notDestroyedState.takeHitAtPosition(randomPosition2));
    }

    @Test
    void testShowStatusAtPositionOceanGridNotHit(){
        grid.putBoatAtPosition(boat, randomPosition1);
        assertEquals(boat.getRepresentator(), notDestroyedState.showStatusAtPosition(randomPosition1, GridType.OCEAN_GRID));
    }

    @Test
    void testShowStatusAtPositionOceanGridHit(){
        grid.putBoatAtPosition(boat, randomPosition1);
        notDestroyedState.takeHitAtPosition(randomPosition1);
        assertEquals(boat.getDamage(), notDestroyedState.showStatusAtPosition(randomPosition1, GridType.OCEAN_GRID));
    }

    @Test
    void testShowStatusAtPositionTargetGrid(){
        grid.putBoatAtPosition(boat, randomPosition1);
        assertEquals(boat.getDamage(), notDestroyedState.showStatusAtPosition(randomPosition1, GridType.TARGET_GRID));
    }
}
