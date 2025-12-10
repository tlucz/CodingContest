package com.tl.advent.year2025.day10;

import com.tl.utils.CodingContestFileHelper;
import com.tl.utils.StringIteration;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day10MainApp {

    public static void main(String[] args) {
        CodingContestFileHelper codingContestFileHelper = new CodingContestFileHelper(Day10MainApp.class);
        List<Path> inputFilesPaths = codingContestFileHelper.readInputFiles(false);

        for (var inputPath : inputFilesPaths) {

            List<String> outputLines = new ArrayList<>();
            var stringIteration = new StringIteration(CodingContestFileHelper.readFile(inputPath));
            String line;
            List<String> lines = new ArrayList<>();
            long sum = 0;
            long sum2 = 0;
            while ((line = stringIteration.getNext()) != null) {
                outputLines.add(line);
                lines.add(line);
                Machine machine = new Machine(line);
                int steps = 0;
//                int steps = machine.configure();
                System.out.print(line);
                System.out.flush();
                int steps2 = machine.configurePart2SecondAttempt();
                System.out.println(":" + steps2);
                System.out.flush();
                sum += steps;
                sum2 += steps2;
            }

            System.out.println("Part1 " + sum);
            System.out.println("Part2 " + sum2);

            CodingContestFileHelper.saveOutputFile(inputPath, outputLines);
        }
        System.exit(0);
    }
}
