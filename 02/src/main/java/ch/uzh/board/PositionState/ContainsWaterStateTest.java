package main.java.ch.uzh.board.PositionState;

import main.java.ch.uzh.board.Grid;
import main.java.ch.uzh.board.GridType;
import main.java.ch.uzh.board.Position;
import main.java.ch.uzh.boat.Boat;
import main.java.ch.uzh.boat.PatrolBoat;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContainsWaterStateTest {
    Grid grid = new Grid("Test Grid");
    Position randomPosition = grid.getRandomPosition();
    PositionState containsWaterState = randomPosition.getContainsWaterState();

    @Test
    void testAttackReturnsFalse(){
        assertEquals(randomPosition.getStatusView(), randomPosition.getUnknownContentSymbol());
        assertFalse(containsWaterState.attack());
        assertEquals(randomPosition.getStatusView(), randomPosition.getOceanHitSymbol());
    }

    @Test
    void testRevealContentNoHit(){
        assertEquals(containsWaterState.revealContent(GridType.TARGET_GRID), randomPosition.getUnknownContentSymbol());
        assertEquals(containsWaterState.revealContent(GridType.OCEAN_GRID), randomPosition.getUnknownContentSymbol());
        assertEquals(containsWaterState.revealContent(GridType.CHEAT_GRID), randomPosition.getUnknownContentSymbol());
    }
    @Test
    void testRevealContentMiss(){
        containsWaterState.attack();
        assertEquals(containsWaterState.revealContent(GridType.TARGET_GRID), randomPosition.getOceanHitSymbol());
        assertEquals(containsWaterState.revealContent(GridType.OCEAN_GRID), randomPosition.getOceanHitSymbol());
        assertEquals(containsWaterState.revealContent(GridType.CHEAT_GRID), randomPosition.getOceanHitSymbol());
    }

    @Test
    void testPlaceBoat(){
        assertEquals(randomPosition.getState(), randomPosition.getContainsWaterState());
        assertEquals(randomPosition.getBoat(), null);
        Boat boat = new PatrolBoat();
        containsWaterState.placeBoat(boat);
        assertEquals(randomPosition.getBoat(), boat);
        assertEquals(randomPosition.getState(), randomPosition.getContainsBoatState());
    }
}
