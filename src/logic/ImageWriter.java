package logic;

import entity.Field;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class ImageWriter {
    /**
     * @param image  Image on which the fields will be drawn.
     * @param fields Fields that will be drawn on image.
     */
    public void write(BufferedImage image, List<Field> fields) {
        final int width = 3;

        Graphics2D g2 = image.createGraphics();
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(2));

        for (Field field : fields) {
            g2.drawRect(field.getLeft() - width / 2, field.getTop() - width / 2,
                    field.getRight() - field.getLeft() + width / 2, field.getBot() - field.getTop() + width / 2);
        }
    }
}
