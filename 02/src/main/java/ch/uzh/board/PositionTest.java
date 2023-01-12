package main.java.ch.uzh.board;
import main.java.ch.uzh.boat.Boat;
import main.java.ch.uzh.boat.PatrolBoat;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

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
                assertEquals(p.getUnknownContentSymbol(), p.revealContent(GridType.CHEAT_GRID));
                assertEquals(p.getUnknownContentSymbol(), p.revealContent(GridType.TARGET_GRID));
                assertEquals(p.getUnknownContentSymbol(), p.revealContent(GridType.OCEAN_GRID));
                //Attack on water must return false (indicate Miss)
                assertFalse(p.attack());
                //After attack on Water, StatusView should be OceanHit (For every GridType)
                assertEquals(p.getOceanHitSymbol(), p.revealContent(GridType.CHEAT_GRID));
                assertEquals(p.getOceanHitSymbol(), p.revealContent(GridType.OCEAN_GRID));
                assertEquals(p.getOceanHitSymbol(), p.revealContent(GridType.TARGET_GRID));

            }
        }
    }
    @Test
    void testRevealContentTargetGridHitBoat(){
        //In TargetGrid both positions should display the UnknownContent symbol if they haven't been attacked yet
        assertEquals(randomPosition1.getUnknownContentSymbol(), randomPosition1.revealContent(GridType.TARGET_GRID));
        assertEquals(randomPosition2.getUnknownContentSymbol(), randomPosition2.revealContent(GridType.TARGET_GRID));
        grid.putBoatAtPosition(boat, randomPosition1);
        grid.putBoatAtPosition(boat, randomPosition2);
        randomPosition1.attack();
        //After Boat is damaged at a position, the position should be displayed as "X" in TargetGrid
        assertEquals(randomPosition1.getBoat().getDamage(), randomPosition1.revealContent(GridType.TARGET_GRID));
        //Since other position isn't hit yet, it should be displayed as unknown content " ".
        assertEquals(randomPosition2.getUnknownContentSymbol(), randomPosition2.revealContent(GridType.TARGET_GRID));
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
    @Test
    void testDistanceTo(){
        Position p1 = new Position(Column.A,Row._0);
        Position p2 = new Position(Column.A,Row._5);
        Position p3 = new Position(Column.F,Row._0);
        assertEquals(Optional.of(5), p1.distanceTo(p2));
        assertEquals(Optional.of(5), p1.distanceTo(p3));
    }
    @Test
    void testDistanceToSamePosition(){
        Position p1 = new Position(Column.A,Row._0);
        Position p2 = new Position(Column.A,Row._0);
        assertEquals(Optional.of(0), p1.distanceTo(p2));
    }

    @Test
    void testDistanceToDifferentAxis(){
        Position p1 = new Position(Column.A, Row._0);
        Position p2 = new Position(Column.B, Row._1);
        assertEquals(Optional.empty(), p1.distanceTo(p2));
    }

    @Test
    void testNeighbour(){
        Position p1 = new Position(Column.F, Row._5);
        assertEquals(new Position(Column.F, Row._4), p1.neighbour(Direction.NORTH));
        assertEquals(new Position(Column.F, Row._6), p1.neighbour(Direction.SOUTH));
        assertEquals(new Position(Column.G, Row._5), p1.neighbour(Direction.EAST));
        assertEquals(new Position(Column.E, Row._5), p1.neighbour(Direction.WEST));
    }
    @Test
    void testNeighbourNull(){
        Position p1 = new Position(Column.A, Row._9);
        assertEquals(null, p1.neighbour(Direction.WEST));
        assertEquals(null, p1.neighbour(Direction.SOUTH));
    }

    @Test
    void testPathToInvalid(){
        //Trying to put a boat between diagonal positions returns empty list
        Position p1 = new Position(Column.A, Row._0);
        Position p2 = new Position(Column.B, Row._3);
        assertEquals(new ArrayList<Position>(), p1.pathTo(p2));
    }

    @Test
    void testPathTo(){
        Position p1 = new Position(Column.A, Row._0);
        Position p2 = new Position(Column.C, Row._0);
        ArrayList<Position> path = p1.pathTo(p2);
        assertTrue(path.contains(p1));
        assertTrue(path.contains(p2));
        assertTrue(path.contains(new Position(Column.B, Row._0)));
        assertTrue(path.size() == 3);
    }

    @Test
    void testParseValid(){
        assertEquals(new Position(Column.A, Row._5), Position.parse("A5"));
        assertEquals(new Position(Column.F, Row._0), Position.parse("F0"));
    }

    @Test
    void testParseInvalid(){
        //String longer than 2 characters / Invalid Row
        assertEquals(null, Position.parse("F11"));
        //Invalid Column
        assertEquals(null, Position.parse("X5"));
    }
}
