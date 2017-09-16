package ca.cmpt213.as2;

import ca.cmpt213.as2.Game_Logic.*;
import ca.cmpt213.as2.User_Interface.BattlefieldGUI;
import ca.cmpt213.as2.User_Interface.GameLog;
import ca.cmpt213.as2.User_Interface.TopBar;

import javax.swing.*;

/**
 * Created by Jonathan and Daniel on 6/2/2016.
 */

/**
 * The main class where Fortress Defense can be played.
 */
public class Main {

    private static final int NUMOFTANKS = 5;
    private static final int NUMOFCOORDINATES = 4;
    private static final int BOARDWIDTH = 10;
    private static final int BOARDHEIGHT = 10;
    private static final int FORTRESSHP = 1500;
    private static final String GAMENAME = "Tank Battlefield";

    public static void main(String args[]) {

        playGame();

    }

    private static void playGame() {

        JFrame gameFrame = new JFrame(GAMENAME);
        gameFrame.setLayout(new BoxLayout(gameFrame.getContentPane(), BoxLayout.PAGE_AXIS));

        Tank[] tanks = TankGenerator.generateTanks(NUMOFTANKS, NUMOFCOORDINATES, BOARDWIDTH, BOARDHEIGHT);
        Battlefield battlefield = new Battlefield(BOARDWIDTH, BOARDHEIGHT, tanks, FORTRESSHP);

        gameFrame.add(makeTopBar(battlefield));
        gameFrame.add(new BattlefieldGUI(BOARDWIDTH, BOARDHEIGHT, battlefield));
        gameFrame.add(makeGameLog(battlefield));

        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.pack();
        gameFrame.setVisible(true);

    }

    private static TopBar makeTopBar(Battlefield battlefield) {

        TopBar bar = new TopBar(battlefield);

        return bar;

    }

    private static GameLog makeGameLog(Battlefield battlefield) {

        GameLog log = new GameLog(battlefield);

        return log;

    }


}
