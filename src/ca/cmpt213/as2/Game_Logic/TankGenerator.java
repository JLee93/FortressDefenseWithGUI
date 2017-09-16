package ca.cmpt213.as2.Game_Logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Jonathan and Daniel on 6/2/2016.
 */

/**
 * Class that has a static factory method for creating the list of tanks that will be used in the game.
 */
public class TankGenerator {

    public static Tank[] generateTanks(int numberOfTanks, int numberOfCoordinates, int boardWidth, int boardHeight) {

        return generateTanksHelper(numberOfTanks, numberOfCoordinates, boardWidth, boardHeight);

    }

    private static Tank[] generateTanksHelper(int numberOfTanks, int numberOfCoordinates, int boardWidth, int boardHeight) {

        List<Tank> tanks = new ArrayList<>();

        //as long as the tanks arraylist is not filled yet
        while (tanks.size() < numberOfTanks) {

            boolean collisionDetected = false;

            Tank tank = Tank.createTank(generateCoordinates(numberOfCoordinates, boardWidth, boardHeight), numberOfCoordinates);

            //Check if newly created tank collides with any previously created tanks
            for (int i = 0; i < tanks.size(); i++) {

                if (doTanksCollide(tanks.get(i), tank)) {

                    collisionDetected = true;

                }

            }

            if (!collisionDetected) {

                tanks.add(tank);

            }

        }

        //Cast the tank arraylist into a normal array.
        //Turns out letting Java discard a 0 size array and building a proper one by itself is better than initializing a proper length one
        return tanks.toArray(new Tank[0]);

    }

    private static boolean doTanksCollide(Tank tank1, Tank tank2) {

        //The maximum distance two tank parts can be away from each other while still colliding is if both parts are I shaped,
        //parallel to each other on the same line, and share exactly one square on the board.
        int maxDistance = tank1.getPosition().size() * 2 - 1;

        for (int i = 0; i < tank1.getPosition().size(); i++) {

            for (int j = 0; j < tank2.getPosition().size(); j++) {

                //If the difference in distance between any 2 tank parts is greater than the max distance, then we know
                //that the newly created tank cannot possibly collide with a previously created tank.
                //No need to check the other tank parts.
                if (tank1.getPosition().get(i).getX() - tank2.getPosition().get(j).getX() >= maxDistance ||
                        tank1.getPosition().get(i).getY() - tank2.getPosition().get(j).getY() >= maxDistance) {

                    return false;

                }

                if (CollisionDetector.isColliding(tank1.getPosition().get(i), tank2.getPosition().get(j))) {

                    return true;

                }

            }

        }

        return false;

    }

    //generate the list of coordinates for a tank
    private static List<Coordinate> generateCoordinates(int numberOfCoordinates, int boardWidth, int boardHeight) {

        Random random = new Random();

        List<Coordinate> Coordinates = new ArrayList<>();

        //create a random x and y value between 0 and board dimensions.
        int x = random.nextInt(boardWidth);
        int y = random.nextInt(boardHeight);

        Coordinates.add(new Coordinate(x, y));

        while (Coordinates.size() < numberOfCoordinates) {

            Boolean isOk = true;

            //pick a random direction left, right, up, or down to create next coordinate.
            int rand = random.nextInt(3);

            //Pick a random coordinate from the list of coordinates we have
            int rand2 = random.nextInt(Coordinates.size());

            Coordinate testCoordinate;

            if (rand == 0) {

                testCoordinate = new Coordinate(Coordinates.get(rand2).getX() + 1, Coordinates.get(rand2).getY());

            } else if (rand == 1) {

                testCoordinate = new Coordinate(Coordinates.get(rand2).getX() - 1, Coordinates.get(rand2).getY());

            } else if (rand == 2) {

                testCoordinate = new Coordinate(Coordinates.get(rand2).getX(), Coordinates.get(rand2).getY() + 1);

            } else {

                testCoordinate = new Coordinate(Coordinates.get(rand2).getX(), Coordinates.get(rand2).getY() - 1);

            }

            //test if the new coordinate collides with any of the previous created coordinates.
            for (int i = 0; i < Coordinates.size(); i++) {

                if (CollisionDetector.isColliding(Coordinates.get(i), testCoordinate) || CollisionDetector.isOutOfBounds(testCoordinate, boardWidth, boardHeight)) {

                    isOk = false;
                    testCoordinate = null;
                    break;

                }

            }

            if (isOk) {

                Coordinates.add(testCoordinate);

            }

        }

        return Coordinates;

    }

    public static void main(String args[]) {

        Tank[] tanks = generateTanks(5, 4, 10, 10);

        for (Tank tank : tanks) {

            System.out.println(tank.toString());

        }

    }

}
