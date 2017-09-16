package ca.cmpt213.as2.Game_Logic;

/**
 * Created by Jonathan on 7/13/2016.
 */

/**
 * This class is responsible for calculating the damage dealt to the fortress and how much damage the tanks do
 */
public class DamageCalculator {

    public static void damageFortress(Fortress fortress, Tank[] tanks) {

        int damageTaken = 0;
        int fortressHP = fortress.getFortressHP();


        for (int i = 0; i < tanks.length; i++) {

            if (tanks[i].getDamage() > 0) {

                damageTaken = damageTaken + tanks[i].getDamage();

            }

        }

        fortressHP = fortressHP - damageTaken;

        fortress.setFortressHP(fortressHP);

    }

    public static int[] getDamagePerTank(Tank[] tanks){

        int[] tankShots = new int[tanks.length];

        for(int i = 0; i < tankShots.length; i++){

            tankShots[i] = tanks[i].getDamage();

        }

        return tankShots;

    }

    public static int getTotalTanksDamage(Tank[] tanks){

        int totalDamage = 0;

        for(int i = 0; i < tanks.length; i++){

            totalDamage = totalDamage + tanks[i].getDamage();

        }

        return totalDamage;

    }

}
