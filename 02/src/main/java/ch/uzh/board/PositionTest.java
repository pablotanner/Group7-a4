package main.java.ch.uzh.board;

import main.java.ch.uzh.boat.Battleship;
import main.java.ch.uzh.boat.Boat;
import main.java.ch.uzh.boat.Carrier;
import main.java.ch.uzh.boat.PatrolBoat;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
public class PositionTest {
    @Test
    void attackWater(){
        for(Row r : Row.values()){
            for(Column c : Column.values()){
                Position p = new Position(c,r);
                //For Water, StatusView before Attack should be UnknownContent
                assertEquals(p.getUnknownContent(), p.revealContent(GridType.CHEAT_GRID));
                assertEquals(p.getUnknownContent(), p.revealContent(GridType.TARGET_GRID));
                assertEquals(p.getUnknownContent(), p.revealContent(GridType.OCEAN_GRID));
                //Attack on water must return false (indicate Miss)
                assertFalse(p.attack());
                //After attack on Water, StatusView should be OceanHit
                assertEquals(p.getOceanHit(), p.revealContent(GridType.CHEAT_GRID));
                assertEquals(p.getOceanHit(), p.revealContent(GridType.OCEAN_GRID));
                assertEquals(p.getOceanHit(), p.revealContent(GridType.TARGET_GRID));

            }
        }
    }
    @Test
    void attackBoatHit(){
        Grid grid = new Grid("Test Grid");
        Position RandomPosition1 = grid.getRandomPosition();
        Position RandomPosition2 = grid.getRandomPosition();
        Boat PBoat = new PatrolBoat();
        grid.putBoatAtPosition(PBoat, RandomPosition1);
        grid.putBoatAtPosition(PBoat, RandomPosition2);
        assertFalse(grid.attackAtPosition(RandomPosition1));
    }

    @Test
    void attackBoatDestroyed(){
        Grid grid = new Grid("Test Grid");
        Position RandomPosition1 = grid.getRandomPosition();
        Position RandomPosition2 = grid.getRandomPosition();
        Boat PBoat = new PatrolBoat();
        grid.putBoatAtPosition(PBoat, RandomPosition1);
        grid.putBoatAtPosition(PBoat, RandomPosition2);
        assertFalse(grid.attackAtPosition(RandomPosition1));
        assertTrue(grid.attackAtPosition(RandomPosition2));
    }

    @Test
    void revealContentTargetGridHit(){
        Position p1 = new Position(Column.A,Row._0);
        Position p2 = new Position(Column.B,Row._1);
        //In TargetGrid both positions should display the UnknownContent symbol if they haven't been attacked yet
        assertEquals(p1.getUnknownContent(), p1.revealContent(GridType.TARGET_GRID));
        assertEquals(p2.getUnknownContent(), p2.revealContent(GridType.TARGET_GRID));
        Boat Patrol = new PatrolBoat();
        p1.placeBoat(Patrol);
        p2.placeBoat(Patrol);
        //Attack on Boat must return false, since boat is not sunk yet
        assertFalse(p1.attack());
        //After Boat is Hit, it should be displayed as "X" in TargetGrid
        assertEquals(p1.getBoat().getDamage(), p1.revealContent(GridType.TARGET_GRID));
    }

    @Test
    void revealContentTargetGridDestroy(){
        Grid TargetGrid = new Grid("Target Grid");
        Position p1 = new Position(Column.A,Row._0);
        Position p2 = new Position(Column.B,Row._1);
        Boat Patrol = new PatrolBoat();
        TargetGrid.putBoatAtPosition(Patrol, p1);
        p1.placeBoat(Patrol);
        TargetGrid.putBoatAtPosition(Patrol, p2);
        p2.placeBoat(Patrol);
        assertFalse(p1.attack());
        //After first hit, hit Position should display "X"
        assertEquals(Patrol.getDamage(), p1.revealContent(GridType.TARGET_GRID));
        assertTrue(p2.attack());
        //After second hit boat is destroyed and both positions should display PatrolBoat Representator
        assertEquals(Patrol.getRepresentator(), p1.revealContent(GridType.TARGET_GRID));
        assertEquals(Patrol.getRepresentator(), p2.revealContent(GridType.TARGET_GRID));
    }

    @Test
    void revealContentOceanGrid(){
        Grid TargetGrid = new Grid("Ocean/Cheat Grid");
        Position RandomPosition = TargetGrid.getRandomPosition();
        Boat Patrol = new PatrolBoat();
        TargetGrid.putBoatAtPosition(Patrol, RandomPosition);
        assertEquals(Patrol.getRepresentator(), RandomPosition.revealContent(GridType.OCEAN_GRID));
    }

    @Test
    void revealContentOceanGridHit(){
        Grid TargetGrid = new Grid("Ocean/Cheat Grid");
        Position RandomPosition1 = TargetGrid.getRandomPosition();
        Position RandomPosition2 = TargetGrid.getRandomPosition();
        Boat Patrol = new PatrolBoat();
        TargetGrid.putBoatAtPosition(Patrol, RandomPosition1);
        TargetGrid.putBoatAtPosition(Patrol, RandomPosition2);
        TargetGrid.attackAtPosition(RandomPosition1);
        assertEquals(Patrol.getDamage(), RandomPosition1.revealContent(GridType.OCEAN_GRID));
        assertEquals(Patrol.getRepresentator(), RandomPosition2.revealContent(GridType.OCEAN_GRID));
    }

    @Test
    void revealContentOceanGridDestroy(){
        Grid TargetGrid = new Grid("Ocean/Cheat Grid");
        Position RandomPosition1 = TargetGrid.getRandomPosition();
        Position RandomPosition2 = TargetGrid.getRandomPosition();
        Boat Patrol = new PatrolBoat();
        TargetGrid.putBoatAtPosition(Patrol, RandomPosition1);
        TargetGrid.putBoatAtPosition(Patrol, RandomPosition2);
        TargetGrid.attackAtPosition(RandomPosition1);
        TargetGrid.attackAtPosition(RandomPosition2);
        assertEquals(Patrol.getDamage(), RandomPosition1.revealContent(GridType.OCEAN_GRID));
        assertEquals(Patrol.getDamage(), RandomPosition2.revealContent(GridType.OCEAN_GRID));
    }

}
