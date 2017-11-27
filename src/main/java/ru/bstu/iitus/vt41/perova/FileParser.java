package ru.bstu.iitus.vt41.perova;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileParser {
    private final Pattern typePattern = Pattern.compile(".*\"value\":\"([^<]*)\".*");
    private final Pattern materialPattern = Pattern.compile(".*\"material\":\"([^<]*)\".*");
    private final Pattern weightPattern = Pattern.compile(".*\"weight\":\"(\\S+)\".*");
    private BufferedReader reader;

    public FileParser(String filePath) throws IOException {
        reader = Files.newBufferedReader(Paths.get(filePath));
    }

    public ArrayList<BallType> parseBall() throws IOException, ParseException {
        String type = "";
        String material = "";
        float weight = 0;
        ArrayList<BallType> balls = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {

            Matcher typeBallMatcher = typePattern.matcher(line);
            Matcher materialMatcher = materialPattern.matcher(line);
            Matcher weightMatcher = weightPattern.matcher(line);
            if (typeBallMatcher.matches()) {
                type = typeBallMatcher.group(1);
            }
            if (materialMatcher.matches()) {
                material = materialMatcher.group(1);
            }
            if (weightMatcher.matches()) {
                weight = Float.parseFloat(weightMatcher.group(1).replace(',', '.'));
            }
            if (!type.isEmpty() && !material.isEmpty() && weight != 0) {
                balls.add(new BallType(type, material, weight));
                type = "";
                material = "";
                weight = 0;
            }
        }
        reader.close();
        return balls;
    }


}
