package ca.cmpt213.as2.Game_Logic; /**
 * Created by Jonathan and Daniel on 6/2/2016.
 */


import ca.cmpt213.as2.User_Interface.BattlefieldObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to create the board object where the game will be played and handling the logic
 */
public class Battlefield {

    private Square[][] board;
    private Tank[] tanks;
    private Fortress fortress;
    private List<BattlefieldObserver> battlefieldObservers = new ArrayList<>();
    private boolean lastHit = false;

    public Battlefield(int boardWidth, int boardHeight, Tank[] tanks, int fortressHP) {

        this.tanks = tanks;
        this.fortress = new Fortress(fortressHP);

        this.board = new Square[boardWidth][boardHeight];

        for (int i = 0; i < boardWidth; i++) {

            for (int j = 0; j < boardHeight; j++) {

                this.board[i][j] = new Square(Square.State.FOG);

            }

        }

        for (int i = 0; i < tanks.length; i++) {

            for (int j = 0; j < tanks[i].getPosition().size(); j++) {

                int tankXPosition = this.tanks[i].getPosition().get(j).getX();
                int tankYPosition = this.tanks[i].getPosition().get(j).getY();

                board[tankXPosition][tankYPosition].setSquare(Square.State.TANKEXISTS, i);

            }

        }

    }

    public Fortress getFortress(){

        return this.fortress;

    }

    public boolean getLastHit(){

        return this.lastHit;

    }

    public Square.State getCellState(int row, int col){

        return board[row][col].getState();

    }

    public void shootAtBoard(int row, int column) {

        this.lastHit = hasTank(row, column);

        if (board[row][column].getState() == Square.State.TANKEXISTS) {


            board[row][column].setState(Square.State.HIT);

            int tankId = board[row][column].getTankId();
            Coordinate tankPart = new Coordinate(row, column);
            this.tanks[tankId].damageTank(tankPart);

        } else if(board[row][column].getState() == Square.State.FOG) {

            board[row][column].setState(Square.State.EMPTY);

        }

        DamageCalculator.damageFortress(fortress, tanks);

        notifyObservers();

    }

    public boolean hasTank(int row, int column){

        if (board[row][column].getState() == Square.State.TANKEXISTS) {

            return true;

        } else{

            return false;

        }

    }

    public int[] getDamagePerTankShot(){

        return DamageCalculator.getDamagePerTank(tanks);

    }

    public int getTotalTanksDamage(){

        return DamageCalculator.getTotalTanksDamage(tanks);

    }

    public void addObserver(BattlefieldObserver observer) {
        battlefieldObservers.add(observer);
    }

    private void notifyObservers() {
        for (BattlefieldObserver observer : battlefieldObservers) {
            observer.statusChanged();
        }
    }

    public static void main(String[] args) {

    }

}
