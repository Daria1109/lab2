package ru.bstu.iitus.vt41.perova;


import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class Main {
    private static final String FILE_PATH = "src/main/resources/lab_2_v2.txt";

    public static void main(String[] args) throws IOException, ParseException {

        ArrayList<BallType> balls = new FileParser(FILE_PATH).parseBall();

        balls.stream().map(Object::toString).forEach(System.out::println);
    }
}
