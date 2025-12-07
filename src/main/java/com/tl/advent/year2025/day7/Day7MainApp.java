package com.tl.advent.year2025.day7;

import com.tl.advent.year2025.day6.Calculator;
import com.tl.utils.CodingContestFileHelper;
import com.tl.utils.StringIteration;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day7MainApp {

    public static void main(String[] args) {
        CodingContestFileHelper codingContestFileHelper = new CodingContestFileHelper(Day7MainApp.class);
        List<Path> inputFilesPaths = codingContestFileHelper.readInputFiles(false);

        for (var inputPath : inputFilesPaths) {

            List<String> outputLines = new ArrayList<>();
            var stringIteration = new StringIteration(CodingContestFileHelper.readFile(inputPath));
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = stringIteration.getNext()) != null) {
                lines.add(line);
            }

            BeamSimulator simulator = new BeamSimulator(lines);

            System.out.println("Part1 " + simulator.calculatePart1());
            System.out.println("Part2 " + simulator.calculatePart2());

            CodingContestFileHelper.saveOutputFile(inputPath, outputLines);
        }
        System.exit(0);
    }
}
