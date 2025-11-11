package com.tl.utils;

import java.util.Arrays;
import java.util.List;

public class ParserHelper {

    public static List<Double> getNumbers(String line) {
        return Arrays.stream(line.split(" "))
                .map(Double::parseDouble)
                .toList();
    }
}
