package ca.cmpt213.as2.Game_Logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jonathan on 6/2/2016.
 */

/**
 * Class to contain information about a ca.cmpt213.as2.Game_Logic.Tank object.
 * It also allows retrieval of position and methods for modifying it's hp and damage
 */
public class Tank {

    private static final int[] DAMAGEVALUES = {20, 5, 2, 1, 0};
    private static final int HPPERPAT = 1;
    private static final int NUMOFCOORDINATES = 4;

    private int hp;
    private int[] hpPerPart;    //A tank consists of multiple parts and each part has HP.
    private int damage;
    private List<Coordinate> position; //A list of coordinates map out where exactly the tank will be on the board.

    private Tank(int hp, int damage, int numberOfCoordinates, List<Coordinate> position) {

        this.hp = hp;
        this.hpPerPart = new int[numberOfCoordinates];
        for (int i = 0; i < this.hpPerPart.length; i++) {

            hpPerPart[i] = HPPERPAT;

        }

        this.damage = damage;
        this.position = position;


    }

    public int getDamage() {

        return this.damage;

    }

    public List<Coordinate> getPosition() {

        return this.position;

    }

    public static Tank createTank(List<Coordinate> position, int numberOfCoordinates) {

        return new Tank(numberOfCoordinates, DAMAGEVALUES[0], numberOfCoordinates, position);

    }

    public void damageTank(Coordinate tankPart) {

        //check which tank part is receiving the damage
        for (int i = 0; i < this.position.size(); i++) {

            if (tankPart.isEqual(this.position.get(i)) && this.hpPerPart[i] >= 1) {

                this.hp--;
                this.hpPerPart[i]--;
                this.damage = computeDamage(damage);

            }

        }

        return;

    }

    private int computeDamage(int damage) {

        for (int i = 0; i < DAMAGEVALUES.length; i++) {

            if (damage == DAMAGEVALUES[i] && damage != 0) {

                return DAMAGEVALUES[i + 1];

            }

        }

        return DAMAGEVALUES[0];

    }

    @Override
    public String toString() {

        return "[HP: " + this.hp + ", hpPerPart: " + Arrays.toString(this.hpPerPart) + ", damage: " + this.damage + " position: " + this.position.toString() + "]";

    }

    public static void main(String args[]) {


        Coordinate tankpart1 = new Coordinate(1, 1);
        Coordinate tankpart2 = new Coordinate(1, 2);
        Coordinate tankpart3 = new Coordinate(1, 3);
        Coordinate tankpart4 = new Coordinate(1, 4);

        List<Coordinate> tankPosition = new ArrayList<>();
        tankPosition.add(tankpart1);
        tankPosition.add(tankpart2);
        tankPosition.add(tankpart3);
        tankPosition.add(tankpart4);

        Tank tank = Tank.createTank(tankPosition, NUMOFCOORDINATES);

        System.out.println(tank.toString());

        tank.damageTank(tankpart1);
        tank.damageTank(tankpart1);

        System.out.println(tank.toString());


    }

}
