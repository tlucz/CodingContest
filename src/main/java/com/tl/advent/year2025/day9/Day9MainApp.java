package com.tl.advent.year2025.day9;

import com.tl.advent.year2025.day8.JunctionConnector;
import com.tl.utils.CodingContestFileHelper;
import com.tl.utils.StringIteration;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day9MainApp {

    public static void main(String[] args) {
        CodingContestFileHelper codingContestFileHelper = new CodingContestFileHelper(Day9MainApp.class);
        List<Path> inputFilesPaths = codingContestFileHelper.readInputFiles(false);

        for (var inputPath : inputFilesPaths) {

            List<String> outputLines = new ArrayList<>();
            var stringIteration = new StringIteration(CodingContestFileHelper.readFile(inputPath));
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = stringIteration.getNext()) != null) {
                lines.add(line);
            }

            RectangleFinder rectangleFinder = new RectangleFinder(lines);
            System.out.println("Part1 " + rectangleFinder.calculatePart1());

            rectangleFinder = new RectangleFinder(lines);
            System.out.println("Part2 " + rectangleFinder.calculatePart2());

            CodingContestFileHelper.saveOutputFile(inputPath, outputLines);
        }
        System.exit(0);
    }
}
