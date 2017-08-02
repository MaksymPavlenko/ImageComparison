package logic;

import entity.Field;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class FieldsFinder {
    private static final int DISTANCE_TO_CONSIDER_ONE_FIELD = 10;
    private static final double DIFFERENCE_PERCENTAGE = 0.10;
    private static final int MAX_COLOR_VALUE = 255;

    /**
     * @param image1 First image to compare.
     * @param image2 Second image to compare.
     * @return The list of separated by some distance rectangles which highlight the fields which differs in given images.
     * @throws Exception which could be caused by difference in images resolution.
     */
    public List<Field> getFields(BufferedImage image1, BufferedImage image2, List<Field> toExclude) throws Exception {
        if ((image1.getHeight() != image2.getHeight()) || (image1.getWidth() != image2.getWidth())) {
            throw new Exception("Images have different sizes!");
        }

        List<Field> fields = new ArrayList<>();

        for (int j = 0; j < image1.getHeight(); j++) {
            inner:
            for (int i = 0; i < image1.getWidth(); i++) {
                //checking if this dot hits any of given fields to exclude and skipping the iteration if it does.
                for (Field field : toExclude) {
                    if (field.distance(i, j) <= 0.1) {
                        continue inner;
                    }
                }

                Color firstPixel = new Color(image1.getRGB(i, j));
                Color secondPixel = new Color(image2.getRGB(i, j));
                double difference = (abs(firstPixel.getRed() - secondPixel.getRed())
                        + abs(firstPixel.getGreen() - secondPixel.getGreen())
                        + abs(firstPixel.getBlue() - secondPixel.getBlue())) / MAX_COLOR_VALUE;

                if (difference > DIFFERENCE_PERCENTAGE) {
                    processDot(i, j, fields);
                }
            }
        }

        Field toRemove = null;
        boolean foundFieldsToExtend;
        do {
            foundFieldsToExtend = false;
            for (int i = 0; i < fields.size() - 1; i++) {
                Field field1 = fields.get(i);
                for (int j = i + 1; j < fields.size(); j++) {
                    Field field2 = fields.get(j);
                    if (field1.distance(field2) < DISTANCE_TO_CONSIDER_ONE_FIELD) {
                        foundFieldsToExtend = true;
                        field1.extend(field2);
                        toRemove = field2;
                        break;
                    }
                }
            }
            fields.remove(toRemove);
        } while (foundFieldsToExtend);

        return fields;
    }

    private void processDot(int x, int y, List<Field> fields) {
        boolean foundCloseField = false;
        for (Field field : fields) {
            if (field.distance(x, y) < DISTANCE_TO_CONSIDER_ONE_FIELD) {
                foundCloseField = true;
                field.extend(x, y);
                break;
            }
        }

        if (!foundCloseField) {
            fields.add(new Field(x, x, y, y));
        }
    }
}
