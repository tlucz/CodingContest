package com.tl.advent.year2025.day6;

import com.tl.advent.year2025.day5.IdValidator;
import com.tl.utils.CodingContestFileHelper;
import com.tl.utils.StringIteration;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day6MainApp {

    public static void main(String[] args) {
        CodingContestFileHelper codingContestFileHelper = new CodingContestFileHelper(Day6MainApp.class);
        List<Path> inputFilesPaths = codingContestFileHelper.readInputFiles(false);

        for (var inputPath : inputFilesPaths) {

            List<String> outputLines = new ArrayList<>();
            var stringIteration = new StringIteration(CodingContestFileHelper.readFile(inputPath));
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = stringIteration.getNext()) != null) {
                lines.add(line);
            }

            Calculator calculator = new Calculator(lines);

            System.out.println("Part1 " + calculator.calculatePart1());
            System.out.println("Part2 " + calculator.calculatePart2());

            CodingContestFileHelper.saveOutputFile(inputPath, outputLines);
        }
        System.exit(0);
    }
}
