package gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class ImageFrame extends CentredFrame {
    public ImageFrame(BufferedImage image) {
        setTitle("Comparison");
        setSize(image.getWidth(), image.getHeight());

        ImagePanel imagePanel = new ImagePanel(image);
        add(imagePanel);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }
}
