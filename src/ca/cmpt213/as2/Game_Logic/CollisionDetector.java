package ca.cmpt213.as2.Game_Logic; /**
 * Created by Jonathan and Daniel on 6/3/2016.
 */

/**
 * Class for collision detection methods. It provides out of bound check for the board, and collision check between two coordinate
 */
public class CollisionDetector {

    public static boolean isOutOfBounds(Coordinate coordinate, int boardWidth, int boardHeight) {

        if (coordinate.getX() >= boardWidth || coordinate.getX() < 0) {

            return true;

        } else if (coordinate.getY() >= boardHeight || coordinate.getY() < 0) {

            return true;

        } else {

            return false;

        }

    }

    public static boolean isColliding(Coordinate coord1, Coordinate coord2) {

        return coord1.isEqual(coord2);

    }

}
