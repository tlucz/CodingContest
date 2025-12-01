package com.tl.advent.year2025.day1;

import com.tl.utils.CodingContestFileHelper;
import com.tl.utils.StringIteration;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class Day1MainApp {

    public static void main(String[] args) {
        CodingContestFileHelper codingContestFileHelper = new CodingContestFileHelper(Day1MainApp.class);
        List<Path> inputFilesPaths = codingContestFileHelper.readInputFiles(false);

        for (var inputPath : inputFilesPaths) {

            var stringIteration = new StringIteration(CodingContestFileHelper.readFile(inputPath));
            var dial = new Dial(50, 99);

            String instruction;
            int result = 0;
            while (!StringUtils.isBlank(instruction = stringIteration.getNext())) {
                int position = dial.move(instruction);
                if (position == 0) {
                    result++;
                }
            }
            List<String> outputLines = new ArrayList<>();
            outputLines.add(String.valueOf(result));
            outputLines.add(String.valueOf(dial.getCrossedZeroCount()));
            CodingContestFileHelper.saveOutputFile(inputPath, outputLines);
        }
        System.exit(0);
    }
}
