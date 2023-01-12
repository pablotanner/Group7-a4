package main.java.ch.uzh.player;
import main.java.ch.uzh.board.Grid;
import main.java.ch.uzh.board.GridType;
import main.java.ch.uzh.board.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class HumanPlayerTest {
    IPlayer humanPlayer = new HumanPlayer("Test Player");
    Grid grid = new Grid("Test Grid");
    Position randomPosition = grid.getRandomPosition();

    @Test
    void testGetName(){
        assertEquals("Test Player", humanPlayer.getName());
    }

    @Test
    void testPlaceFleetMissingGrid() {
        assertThrows(IllegalStateException.class, () -> {
            humanPlayer.placeFleet();
        });
    }
    @Test
    void testGetRandomGridPositionMissingGrid() {
        assertThrows(IllegalStateException.class, () -> {
            humanPlayer.getRandomGridPosition();
        });
    }
    @Test
    void testWasAttackedAtPositionMissingGrid() {
        assertThrows(IllegalStateException.class, () -> {
            humanPlayer.wasAttackedAtPosition(randomPosition);
        });
    }
    @Test
    void testShowGridContentMissingGrid() {
        assertThrows(IllegalStateException.class, () -> {
            humanPlayer.showGridContent(GridType.TARGET_GRID);
        });
        assertThrows(IllegalStateException.class, () -> {
            humanPlayer.showGridContent(GridType.OCEAN_GRID);
        });
        assertThrows(IllegalStateException.class, () -> {
            humanPlayer.showGridContent(GridType.CHEAT_GRID);
        });
    }

}
