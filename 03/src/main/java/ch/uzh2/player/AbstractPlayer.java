package main.java.ch.uzh2.player;

import main.java.ch.uzh2.player.AttackStrategy.IAttackStrategy;
import main.java.ch.uzh2.player.PlaceBoatsStrategy.IPlacementStrategy;
import main.java.ch.uzh2.board.Grid;
import main.java.ch.uzh2.board.GridType;
import main.java.ch.uzh2.board.Position;
import main.java.ch.uzh2.boat.Fleet;


public abstract class AbstractPlayer implements IPlayer {
    private final IPlacementStrategy placeStrategy;
    private final IAttackStrategy attackStrategy;
    private Grid grid;
    private Grid opponentgrid;
    private final Fleet fleet;


    AbstractPlayer(IPlacementStrategy placeStrategy, IAttackStrategy attackStrategy, Fleet fleet) {
        this.placeStrategy = placeStrategy;
        this.attackStrategy = attackStrategy;
        this.fleet = fleet;
    }

    @Override
    public void assignGrid(Grid grid) {
        this.grid = grid;
    }
    public void assignOpponentGrid(Grid grid) {
        this.opponentgrid = grid;
    }
    @Override
    public void placeFleet() {
        if (this.grid == null) {
            throw new IllegalStateException("You have to assign a Grid to the Player before calling this method!");
        }
        placeStrategy.placeBoats(this.fleet, this.grid,this);
    }


    @Override
    public void shootAt(IPlayer opponent) {
        attackStrategy.shootAt(this,opponent);
    }

    @Override
    public void takeShotAt(Position position) {
        this.grid.attackAtPosition(position);
    }

    @Override
    public Boolean fleetIsAlive() {
        return fleet.stillStanding();
    }


    @Override
    public Position getRandomGridPosition() {
        if (this.grid == null) {
            throw new IllegalStateException("You have to assign a Grid to the Player before calling this method!");
        }
        return this.grid.getRandomPosition();
    }

    @Override
    public boolean wasAttackedAtPosition(Position position) {
        if (this.grid == null) {
            throw new IllegalStateException("You have to assign a Grid to the Player before calling this method!");
        }
        return this.grid.wasAttackedAt(position);
    }

    @Override
    public String showGridContent(GridType gridType) {
        if (this.grid == null) {
            throw new IllegalStateException("You have to assign a Grid to the Player before calling this method!");
        }
        return this.grid.showGridContent(gridType,this);
    }
}
