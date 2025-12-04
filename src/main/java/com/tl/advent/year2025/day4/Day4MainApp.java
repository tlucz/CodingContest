package com.tl.advent.year2025.day4;

import com.tl.advent.year2025.day3.BatteryBank;
import com.tl.utils.CodingContestFileHelper;
import com.tl.utils.StringIteration;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day4MainApp {

    public static void main(String[] args) {
        CodingContestFileHelper codingContestFileHelper = new CodingContestFileHelper(Day4MainApp.class);
        List<Path> inputFilesPaths = codingContestFileHelper.readInputFiles(false);

        for (var inputPath : inputFilesPaths) {

            List<String> outputLines = new ArrayList<>();
            var stringIteration = new StringIteration(CodingContestFileHelper.readFile(inputPath));
            String line;
            long result = 0;
            List<String> lines = new ArrayList<>();
            while ((line = stringIteration.getNext()) != null) {
                lines.add(line);
            }
            GridManager gridManager = new GridManager(lines);
            int part1Value = gridManager.calculatePart1();
            outputLines.add(String.valueOf(part1Value));
            System.out.println("Part1 " + part1Value);
            int part2Value = gridManager.calculatePart2();
            System.out.println("Part2 " + part2Value);

            CodingContestFileHelper.saveOutputFile(inputPath, outputLines);
        }
        System.exit(0);
    }
}
