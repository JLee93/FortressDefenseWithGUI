package ca.cmpt213.as2.User_Interface;

import ca.cmpt213.as2.Game_Logic.Battlefield;

import javax.swing.*;

/**
 * Created by Jonathan on 7/13/2016.
 */

/**
 * This class is a JPanel responsible for display the fortress hp at the top
 */
public class TopBar extends JPanel {

    private final static String BARTEXT = "Fortress HP: ";
    private JLabel hpTracker;
    private Battlefield battlefield;

    public TopBar(Battlefield battlefield) {

        this.battlefield = battlefield;
        this.hpTracker = makeLabel(battlefield.getFortress().getFortressHP());

        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        add(this.hpTracker);
        add(Box.createHorizontalGlue());
        registerAsObserver();

    }

    public void setHealth() {

        hpTracker.setText(BARTEXT + battlefield.getFortress().getFortressHP());

    }

    private JLabel makeLabel(int health) {

        JLabel label = new JLabel(BARTEXT + health);
        return label;

    }

    public void registerAsObserver() {

        battlefield.addObserver(
                () -> setHealth()
        );

    }

}
