package ca.cmpt213.as2.User_Interface;

import ca.cmpt213.as2.Game_Logic.Battlefield;
import ca.cmpt213.as2.Game_Logic.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jonathan on 7/13/2016.
 */

/**
 * This JPanel is placed in the center handles the display of the game
 */

public class BattlefieldGUI extends JPanel {

    private static final String FIELDPATH = "./Resources/field.jpg";
    private static final String FOGPATH = "./Resources/fog.png";
    private static final String HITPATH = "./Resources/hit.png";
    private static final String MISSPATH = "./Resources/miss.png";
    private static final String TANKPATH = "./Resources/tank.png";

    private static final int GAMEWIDTH = 500;
    private static final int GAMEHEIGHT = 500;

    private static final String GAMEOVER = "I'm sorry; you lost.";
    private static final String WIN = "Congratulations! You won!";

    private static final ImageIcon field = IconScaler.getScaleImageIcon(new ImageIcon(FIELDPATH), 90, 90);
    private static final ImageIcon fog = IconScaler.getScaleImageIcon(new ImageIcon(FOGPATH), 90, 90);
    private static final ImageIcon hit = IconScaler.getScaleImageIcon(new ImageIcon(HITPATH), 90, 90);
    private static final ImageIcon miss = IconScaler.getScaleImageIcon(new ImageIcon(MISSPATH), 90, 90);
    private static final ImageIcon tank = IconScaler.getScaleImageIcon(new ImageIcon(TANKPATH), 90, 90);

    private Battlefield battlefield;
    private JButton[][] cells;
    private boolean gameDone;

    //Sets up the battlefield GUI
    public BattlefieldGUI(int numRow, int numCol, Battlefield battlefield) {

        this.battlefield = battlefield;
        cells = new JButton[numRow][numCol];
        gameDone = false;

        setLayout(new GridLayout(numRow, numCol));
        setPreferredSize(new Dimension(GAMEWIDTH, GAMEHEIGHT));

        for (int row = 0; row < numRow; row++) {
            for (int col = 0; col < numCol; col++) {
                JButton button = new JButton();
                button.setIcon(fog);
                button.addActionListener(makeButtonListener(row, col));
                add(button);
                cells[row][col] = button;
            }
        }

    }

    private ActionListener makeButtonListener(int row, int col) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiLogic(gameDone, row, col);

            }
        };

    }

    //The primary class for calling the rest of the GUI functions as needed.
    private void guiLogic(boolean gameDone, int row, int col) {

        if (gameDone) {

            return;

        }

        this.battlefield.shootAtBoard(row, col);
        setHitOrMissIcon(row, col);
        checkGameStatus();

    }

    private void checkGameStatus() {

        if (this.battlefield.getFortress().getFortressHP() <= 0) {
            gameDone = true;
            revealBoard();
            JOptionPane.showMessageDialog(null, GAMEOVER);

        } else if (this.battlefield.getTotalTanksDamage() <= 0) {
            gameDone = true;
            revealBoard();
            JOptionPane.showMessageDialog(null, WIN);

        }

    }

    private void revealBoard() {

        for (int i = 0; i < cells.length; i++) {

            for (int j = 0; j < cells[i].length; j++) {

                Square.State cellState = this.battlefield.getCellState(i, j);

                if (cellState == Square.State.TANKEXISTS) {

                    cells[i][j].setIcon(tank);

                } else if (cellState == Square.State.FOG) {

                    cells[i][j].setIcon(field);

                }
            }

        }

    }

    private void setHitOrMissIcon(int row, int col) {

        Square.State stateOfSquare = this.battlefield.getCellState(row, col);

        if (stateOfSquare == Square.State.EMPTY) {

            cells[row][col].setIcon(miss);

        } else if (stateOfSquare == Square.State.HIT) {

            cells[row][col].setIcon(hit);

        }

    }

}
