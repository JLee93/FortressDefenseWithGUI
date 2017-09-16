package ca.cmpt213.as2.Game_Logic; /**
 * Created by Jonathan and Daniel on 6/2/2016.
 */

/**
 * This class holds information about a coordinate on the board.
 */
public class Coordinate {

    private int x;
    private int y;

    public Coordinate(int x, int y) {

        this.x = x;
        this.y = y;

    }

    public int getX() {

        return this.x;

    }

    public int getY() {

        return this.y;

    }


    public boolean isEqual(Coordinate coordinate) {

        if (coordinate.x == this.x && coordinate.y == this.y) {

            return true;

        }

        return false;

    }

    @Override
    public String toString() {

        return "[x: " + this.x + ", y: " + this.y + "]";

    }

}
