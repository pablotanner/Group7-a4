package main.java.ch.uzh.boat.BoatState;
import main.java.ch.uzh.board.Grid;
import main.java.ch.uzh.board.GridType;
import main.java.ch.uzh.board.Position;
import main.java.ch.uzh.boat.Boat;
import main.java.ch.uzh.boat.PatrolBoat;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IsDestroyedStateTest {
    Grid grid = new Grid("Test Grid");
    Position randomPosition1 = grid.getRandomPosition();
    Boat boat = new PatrolBoat();
    BoatState isDestroyedState = boat.getIsDestroyedState();

    @Test
    void testShowStatusAtPositionOceanGrid() {
        assertEquals(boat.getNotDestroyedState(), boat.getBoatState());
        grid.putBoatAtPosition(boat, randomPosition1);
        grid.attackAtPosition(randomPosition1);
        assertEquals(isDestroyedState, boat.getBoatState());
        assertEquals(boat.getDamage(), isDestroyedState.showStatusAtPosition(randomPosition1, GridType.OCEAN_GRID));
    }

    @Test
    void testShowStatusAtPositionTargetGrid() {
        assertEquals(boat.getNotDestroyedState(), boat.getBoatState());
        grid.putBoatAtPosition(boat, randomPosition1);
        grid.attackAtPosition(randomPosition1);
        assertEquals(isDestroyedState, boat.getBoatState());
        assertEquals(boat.getRepresentator(), isDestroyedState.showStatusAtPosition(randomPosition1, GridType.TARGET_GRID));
    }

}
