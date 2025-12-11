package com.tl.advent.year2025.day11;

import com.tl.utils.CodingContestFileHelper;
import com.tl.utils.StringIteration;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day11MainApp {

    public static void main(String[] args) {
        CodingContestFileHelper codingContestFileHelper = new CodingContestFileHelper(Day11MainApp.class);
        List<Path> inputFilesPaths = codingContestFileHelper.readInputFiles(false);

        for (var inputPath : inputFilesPaths) {

            List<String> outputLines = new ArrayList<>();
            var stringIteration = new StringIteration(CodingContestFileHelper.readFile(inputPath));
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = stringIteration.getNext()) != null) {
                outputLines.add(line);
                lines.add(line);
            }
            DevicesGraph devicesGraph = new DevicesGraph(lines);
            System.out.println("Part1 " + devicesGraph.connectionsAmount("you","out"));
//            System.out.println("Part2 " + devicesGraph.connectionsAmount("svr","out"));

            CodingContestFileHelper.saveOutputFile(inputPath, outputLines);
        }
        System.exit(0);
    }
}
