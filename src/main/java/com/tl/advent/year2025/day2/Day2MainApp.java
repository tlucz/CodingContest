package com.tl.advent.year2025.day2;

import com.tl.utils.CodingContestFileHelper;
import com.tl.utils.StringIteration;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day2MainApp {

    public static void main(String[] args) {
        CodingContestFileHelper codingContestFileHelper = new CodingContestFileHelper(Day2MainApp.class);
        List<Path> inputFilesPaths = codingContestFileHelper.readInputFiles(false);

        for (var inputPath : inputFilesPaths) {

            List<String> outputLines = new ArrayList<>();
            var stringIteration = new StringIteration(CodingContestFileHelper.readFile(inputPath));
            var line = stringIteration.getNext();
            String[] splitByComma = line.split(",");
            long sum = 0;
            for (int i = 0; i < splitByComma.length; i++) {
                var splitByDash = splitByComma[i].split("-");
                var validator = new IdValidator(Long.parseLong(splitByDash[0]), Long.parseLong(splitByDash[1]));
                validator.validate();
                List<Long> wrongIds = validator.getWrongIds();
                long sum1 = wrongIds.stream().mapToLong(Long::longValue).sum();
                sum += sum1;
            }
            outputLines.add(String.valueOf(sum));
            CodingContestFileHelper.saveOutputFile(inputPath, outputLines);
        }
        System.exit(0);
    }
}
