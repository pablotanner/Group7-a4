package main.java.ch.uzh.player;
import main.java.ch.uzh.board.Column;
import main.java.ch.uzh.board.Grid;
import main.java.ch.uzh.board.Position;
import main.java.ch.uzh.board.Row;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ComputerPlayerTest {
    IPlayer computerPlayer = new ComputerPlayer();
    Grid grid = new Grid("Test Grid");
    Position randomPosition = grid.getRandomPosition();

    void setup(){
        computerPlayer.assignGrid(grid);
        computerPlayer.placeFleet();
    }

    @Test
    void testPlaceFleet(){
        computerPlayer.assignGrid(grid);
        computerPlayer.placeFleet();
        assertDoesNotThrow(() -> computerPlayer.placeFleet());

    }

    @Test
    void testWasAttackedAtPositionFalse(){
        setup();
        assertFalse(computerPlayer.wasAttackedAtPosition(randomPosition));
    }

    @Test
    void testWasAttackedAtPositionTrue(){
        setup();
        grid.attackAtPosition(randomPosition);
        assertTrue(computerPlayer.wasAttackedAtPosition(randomPosition));
    }

    @Test
    void testTakeShotAt(){
        setup();
        assertFalse(computerPlayer.wasAttackedAtPosition(randomPosition));
        //EnemyPlayer shoots at Grid of ComputerPlayer
        computerPlayer.takeShotAt(randomPosition);
        assertTrue(computerPlayer.wasAttackedAtPosition(randomPosition));
    }

    @Test
    void testGetRandomGridPosition(){
        setup();
        Position randomPosition = computerPlayer.getRandomGridPosition();
        assertNotNull(randomPosition);
        assertTrue(randomPosition.getRow() instanceof Row);
        assertTrue(randomPosition.getColumn() instanceof Column);
    }

}
