package ca.cmpt213.as2.Game_Logic; /**
 * Created by Jonathan and Daniel on 6/3/2016.
 */

/**
 * This class creates an object that represents one square on the board.
 * state: determines what is visible to the player
 * tankId: holds the id of the tank that is on the square
 */
public class Square {

    public enum State {
        FOG, EMPTY, HIT, TANKEXISTS
    }

    private static final int NOTANK = -1;

    private State state;
    private int tankId;

    public Square(State state) {

        this.state = state;
        this.tankId = NOTANK;

    }

    public void setSquare(State state, int tankId) {

        this.state = state;
        this.tankId = tankId;

    }

    public State getState() {

        return this.state;

    }

    public void setState(State state) {

        this.state = state;

    }

    public int getTankId() {

        return this.tankId;

    }

    @Override
    public String toString() {

        return "[State: " + this.state + ", TankID: " + this.tankId + "]";

    }


}