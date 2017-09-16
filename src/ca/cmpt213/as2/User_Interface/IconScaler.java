package ca.cmpt213.as2.User_Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Jonathan on 7/13/2016.
 */

/**
 * Code provided in the assignment to resize ImageIcons
 */
public class IconScaler {

    public static ImageIcon getScaleImageIcon(ImageIcon icon, int width, int height) {
        return new ImageIcon(getScaledImage(icon.getImage(), width, height));
    }

    private static Image getScaledImage(Image srcImg, int width, int height) {
        BufferedImage resizedImg =
                new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, width, height, null);
        g2.dispose();
        return resizedImg;

    }

}
