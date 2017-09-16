package ca.cmpt213.as2.Game_Logic;

/**
 * Created by Jonathan on 7/13/2016.
 */

/**
 * Class to represent the fortress in game.
 */
public class Fortress {

    private int HP;

    public Fortress(int HP){

        this.HP = HP;

    }

    public int getFortressHP(){

        return this.HP;

    }

    protected void setFortressHP(int HP){

        this.HP = HP;

    }

}
