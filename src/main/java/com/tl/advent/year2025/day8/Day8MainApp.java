package com.tl.advent.year2025.day8;

import com.tl.advent.year2025.day7.BeamSimulator;
import com.tl.utils.CodingContestFileHelper;
import com.tl.utils.StringIteration;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day8MainApp {

    public static void main(String[] args) {
        CodingContestFileHelper codingContestFileHelper = new CodingContestFileHelper(Day8MainApp.class);
        List<Path> inputFilesPaths = codingContestFileHelper.readInputFiles(false);

        for (var inputPath : inputFilesPaths) {

            List<String> outputLines = new ArrayList<>();
            var stringIteration = new StringIteration(CodingContestFileHelper.readFile(inputPath));
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = stringIteration.getNext()) != null) {
                lines.add(line);
            }

            JunctionConnector connector = new JunctionConnector(lines);
//            System.out.println("Part1 " + connector.calculatePart1(1000));

            connector = new JunctionConnector(lines);
            System.out.println("Part2 " + connector.calculatePart2());

            CodingContestFileHelper.saveOutputFile(inputPath, outputLines);
        }
        System.exit(0);
    }
}
