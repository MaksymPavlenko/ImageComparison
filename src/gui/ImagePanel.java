package gui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends Panel {
    BufferedImage image;

    public ImagePanel(BufferedImage image) {
        this.image = image;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image, 0, 0, this);
    }
}
