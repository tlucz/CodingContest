package com.tl.advent.year2025.day3;

import com.tl.utils.CodingContestFileHelper;
import com.tl.utils.StringIteration;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day3MainApp {

    public static void main(String[] args) {
        CodingContestFileHelper codingContestFileHelper = new CodingContestFileHelper(Day3MainApp.class);
        List<Path> inputFilesPaths = codingContestFileHelper.readInputFiles(false);

        for (var inputPath : inputFilesPaths) {

            List<String> outputLines = new ArrayList<>();
            var stringIteration = new StringIteration(CodingContestFileHelper.readFile(inputPath));
            String line;
            long result = 0;
            while ((line = stringIteration.getNext()) != null) {
                var battery = new BatteryBank(line);
                long maxVoltage = battery.calculateMaxVoltage(12);
                System.out.println(line + " " + maxVoltage);
                result += maxVoltage;
            }
            outputLines.add(String.valueOf(result));
            System.out.println("Result " + result);
            CodingContestFileHelper.saveOutputFile(inputPath, outputLines);
        }
        System.exit(0);
    }
}
