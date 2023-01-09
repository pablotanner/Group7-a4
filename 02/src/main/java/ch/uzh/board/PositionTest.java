package main.java.ch.uzh.board;
import main.java.ch.uzh.boat.Boat;
import main.java.ch.uzh.boat.PatrolBoat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class PositionTest {
    Grid grid = new Grid("Test Grid");
    Position randomPosition1 = grid.getRandomPosition();
    Position randomPosition2 = grid.getRandomPositionExcept(randomPosition1);
    Boat boat = new PatrolBoat();

    @Test
    void testAttackWater(){
        for(Row r : Row.values()){
            for(Column c : Column.values()){
                Position p = new Position(c,r);
                //Attack on water must return false (indicate Miss)
                assertFalse(p.attack());
            }
        }
    }
    @Test
    void testAttackBoatHit(){
        grid.putBoatAtPosition(boat, randomPosition1);
        grid.putBoatAtPosition(boat, randomPosition2);
        //First attack on Boat must return false, since boat is not sunk yet
        assertFalse(randomPosition1.attack());
    }

    @Test
    void testAttackBoatDestroyed(){
        grid.putBoatAtPosition(boat, randomPosition1);
        grid.putBoatAtPosition(boat, randomPosition2);
        //First attack on Boat must return false, since boat is not sunk yet
        assertFalse(randomPosition1.attack());
        //Second attack on Boat must return true, since PatrolBoat is sunk.
        assertTrue(randomPosition2.attack());
    }

    @Test
    void testRevealContentWater(){
        for(Row r : Row.values()){
            for(Column c : Column.values()){
                Position p = new Position(c,r);
                //For Water, StatusView before Attack should be UnknownContent (For every GridType)
                assertEquals(p.getUnknownContent(), p.revealContent(GridType.CHEAT_GRID));
                assertEquals(p.getUnknownContent(), p.revealContent(GridType.TARGET_GRID));
                assertEquals(p.getUnknownContent(), p.revealContent(GridType.OCEAN_GRID));
                //Attack on water must return false (indicate Miss)
                assertFalse(p.attack());
                //After attack on Water, StatusView should be OceanHit (For every GridType)
                assertEquals(p.getOceanHit(), p.revealContent(GridType.CHEAT_GRID));
                assertEquals(p.getOceanHit(), p.revealContent(GridType.OCEAN_GRID));
                assertEquals(p.getOceanHit(), p.revealContent(GridType.TARGET_GRID));

            }
        }
    }
    @Test
    void testRevealContentTargetGridHitBoat(){
        //In TargetGrid both positions should display the UnknownContent symbol if they haven't been attacked yet
        assertEquals(randomPosition1.getUnknownContent(), randomPosition1.revealContent(GridType.TARGET_GRID));
        assertEquals(randomPosition2.getUnknownContent(), randomPosition2.revealContent(GridType.TARGET_GRID));
        grid.putBoatAtPosition(boat, randomPosition1);
        grid.putBoatAtPosition(boat, randomPosition2);
        randomPosition1.attack();
        //After Boat is damaged at a position, the position should be displayed as "X" in TargetGrid
        assertEquals(randomPosition1.getBoat().getDamage(), randomPosition1.revealContent(GridType.TARGET_GRID));
        //Since other position isn't hit yet, it should be displayed as unknown content " ".
        assertEquals(randomPosition2.getUnknownContent(), randomPosition2.revealContent(GridType.TARGET_GRID));
    }

    @Test
    void testRevealContentTargetGridDestroyBoat(){
        Grid TargetGrid = new Grid("Target Grid");
        Position p1 = new Position(Column.A,Row._0);
        Position p2 = new Position(Column.B,Row._1);
        Boat Patrol = new PatrolBoat();
        TargetGrid.putBoatAtPosition(Patrol, p1);
        p1.placeBoat(Patrol);
        TargetGrid.putBoatAtPosition(Patrol, p2);
        p2.placeBoat(Patrol);
        p1.attack();
        //After Boat is damaged at a position, the position should be displayed as "X" in TargetGrid
        assertEquals(Patrol.getDamage(), p1.revealContent(GridType.TARGET_GRID));
        p2.attack();
        //After second hit boat is destroyed and both positions should display PatrolBoat Representator "P"
        assertEquals(Patrol.getRepresentator(), p1.revealContent(GridType.TARGET_GRID));
        assertEquals(Patrol.getRepresentator(), p2.revealContent(GridType.TARGET_GRID));
    }

    @Test
    void testRevealContentOceanGridBoat(){
        Grid TargetGrid = new Grid("Ocean/Cheat Grid");
        Position RandomPosition = TargetGrid.getRandomPosition();
        Boat Patrol = new PatrolBoat();
        TargetGrid.putBoatAtPosition(Patrol, RandomPosition);
        //On OceanGrid the position should display the Representator of the Boat if it hasn't been hit yet.
        assertEquals(Patrol.getRepresentator(), RandomPosition.revealContent(GridType.OCEAN_GRID));
    }

    @Test
    void testRevealContentOceanGridHitBoat(){
        grid.putBoatAtPosition(boat, randomPosition1);
        grid.putBoatAtPosition(boat, randomPosition2);
        grid.attackAtPosition(randomPosition1);
        //On Ocean Grid, once a Position is hit, it should display the Damage symbol "X"
        assertEquals(boat.getDamage(), randomPosition1.revealContent(GridType.OCEAN_GRID));
        //And other parts of the boat that aren't hit yet should still be displayed as the Representator, "P" for PatrolBoat.
        assertEquals(boat.getRepresentator(), randomPosition2.revealContent(GridType.OCEAN_GRID));
    }

    @Test
    void testRevealContentOceanGridDestroyedBoat(){
        grid.putBoatAtPosition(boat, randomPosition1);
        grid.putBoatAtPosition(boat, randomPosition2);
        grid.attackAtPosition(randomPosition1);
        grid.attackAtPosition(randomPosition2);
        assertEquals(boat.getDamage(), randomPosition1.revealContent(GridType.OCEAN_GRID));
        assertEquals(boat.getDamage(), randomPosition2.revealContent(GridType.OCEAN_GRID));
    }

}
