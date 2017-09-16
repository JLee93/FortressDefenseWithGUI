package ca.cmpt213.as2.User_Interface;

import ca.cmpt213.as2.Game_Logic.Battlefield;

import javax.swing.*;


/**
 * Created by Jonathan on 7/13/2016.
 */

/**
 * This class is responsible for keeping track of the damage your fortress has taken
 */
public class GameLog extends JPanel {

    private static final int LOGHEIGHT = 10;
    private static final int LOGWIDTH = 25;

    private Battlefield battlefield;

    private JTextArea gameFeedBack;

    public GameLog(Battlefield battlefield) {

        this.battlefield = battlefield;
        this.gameFeedBack = new JTextArea(LOGHEIGHT, LOGWIDTH);
        JScrollPane scroll = new JScrollPane(this.gameFeedBack,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        add(scroll);
        registerAsObserver();

    }

    public void printDamage() {

        int[] tankShots = battlefield.getDamagePerTankShot();

        if(battlefield.getLastHit()){

            gameFeedBack.setText(gameFeedBack.getText() + "Hit!\n");

        }

        else{

            gameFeedBack.setText(gameFeedBack.getText() + "Miss!\n");

        }

        for (int shot : tankShots) {

            if (shot > 0) {

                gameFeedBack.setText(gameFeedBack.getText() + "You were shot for " + shot + "!\n");

            }

        }

        gameFeedBack.setText(gameFeedBack.getText() + "\n");

    }

    private void registerAsObserver() {
        battlefield.addObserver(new BattlefieldObserver() {
            @Override
            public void statusChanged() {

                printDamage();

            }
        });

    }

}
