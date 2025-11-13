package com.tl.utils;

import java.util.Arrays;
import java.util.List;

public class ParserHelper {

    public static List<Double> getDoubles(String line) {
        return getDoubles(line," ");
    }

    public static List<Double> getDoubles(String line, String regex) {
        return Arrays.stream(line.split(regex))
                .map(Double::parseDouble)
                .toList();
    }

    public static List<Integer> getInts(String line) {
        return getInts(line," ");
    }

    public static List<Integer> getInts(String line, String regex) {
        return Arrays.stream(line.split(regex))
                .map(Integer::parseInt)
                .toList();
    }

    public static List<String> getStrings(String line) {
        return getStrings(line, " ");
    }

    public static List<String> getStrings(String line, String regex) {
        return Arrays.stream(line.split(regex))
                .toList();
    }
}
