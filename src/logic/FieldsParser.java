package logic;

import entity.Field;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldsParser {
    public List<Field> parse(String fieldsString) throws IOException {
        List<Field> fields = new ArrayList<>();

        Pattern pattern = Pattern.compile("\\s*[0-9]+\\s*[0-9]+\\s*[0-9]+\\s*[0-9]+");
        Matcher matcher = pattern.matcher(fieldsString);

        while (matcher.find()) {
            String result = matcher.group().trim();
            Scanner scanner = new Scanner(result);
            int[] inputValue = new int[4];
            int i = 0;
            while (scanner.hasNextInt()) {
                inputValue[i++] = scanner.nextInt();
            }
            System.out.println(i);
            if (i == 4) {
                //transforming x, y, width, height to left, right, top, bot
                fields.add(new Field(inputValue[0], inputValue[0] + inputValue[2], inputValue[1], inputValue[1] + inputValue[3]));
            } else {
                throw new IOException("Wrong number of values.");
            }
        }
        for (Field field : fields) {
            System.out.println(field);
        }

        return fields;
    }
}
