package gui;

import entity.Field;
import logic.FieldsFinder;
import logic.FieldsParser;
import logic.ImageWriter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public class MainFrame extends CentredFrame {
    private File image1File;
    private File image2File;
    private List<Field> toExclude;

    public MainFrame() {
        setLocationRelativeTo(null);
        setTitle("Image Comparison");
        setSize(640, 240);
        GridLayout gl = new GridLayout(4, 3);
        gl.setHgap(48);
        gl.setVgap(10);
        setLayout(gl);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        Label image1Label = new Label("Image 1");
        Label image2Label = new Label("Image 2");
        image1Label.setAlignment(Label.CENTER);
        image2Label.setAlignment(Label.CENTER);

        Button image1button = new Button("Open");
        Button image2button = new Button("Open");

        FileDialog image1FileDialog = new FileDialog(this, "Image 1");
        FileDialog image2FileDialog = new FileDialog(this, "Image 2");

        image1button.addActionListener(e -> {
            image1FileDialog.setVisible(true);
            setImage1File(image1FileDialog.getDirectory() + image1FileDialog.getFile());
            image1Label.setText("Image 1: " + image1FileDialog.getFile());
        });

        image2button.addActionListener(e -> {
            image2FileDialog.setVisible(true);
            setImage2File(image2FileDialog.getDirectory() + image2FileDialog.getFile());
            image2Label.setText("Image 2: " + image2FileDialog.getFile());
        });

        TextArea excludeFields = new TextArea();

        Button compareButton = new Button("Compare");
        compareButton.addActionListener(e -> {
            try {
                BufferedImage image1 = ImageIO.read(image1File);
                BufferedImage image2 = ImageIO.read(image2File);
                FieldsParser fieldsParser = new FieldsParser();
                List<Field> fieldsToExclude = fieldsParser.parse(excludeFields.getText());
                ImageWriter imageWriter = new ImageWriter();
                imageWriter.write(image2, new FieldsFinder().getFields(image1, image2, fieldsToExclude));
                ImageFrame imageFrame = new ImageFrame(image2);
                imageFrame.setVisible(true);
                imageFrame.centreWindow();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        add(image1Label);
        add(image2Label);
        add(image1button);
        add(image2button);
        add(new Label("List of fields to exclude \"x y width height\":"));
        add(excludeFields);
        add(compareButton);
        setVisible(true);
        centreWindow();
    }

    public static void main(String[] args) {
        new MainFrame();
    }

    private void setImage1File(String image1Path) {
        this.image1File = new File(image1Path);
    }

    private void setImage2File(String image2Path) {
        this.image2File = new File(image2Path);
    }
}