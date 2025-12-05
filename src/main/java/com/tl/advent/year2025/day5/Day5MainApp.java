package com.tl.advent.year2025.day5;

import com.tl.utils.CodingContestFileHelper;
import com.tl.utils.StringIteration;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day5MainApp {

    public static void main(String[] args) {
        CodingContestFileHelper codingContestFileHelper = new CodingContestFileHelper(Day5MainApp.class);
        List<Path> inputFilesPaths = codingContestFileHelper.readInputFiles(false);

        for (var inputPath : inputFilesPaths) {

            List<String> outputLines = new ArrayList<>();
            var stringIteration = new StringIteration(CodingContestFileHelper.readFile(inputPath));
            String line;
            List<String> ranges = new ArrayList<>();
            while (!(line = stringIteration.getNext()).isEmpty()) {
                ranges.add(line);
            }
            List<String> idsToCheck = new ArrayList<>();
            while ((line = stringIteration.getNext()) != null) {
                idsToCheck.add(line);
            }

            IdValidator idValidator = new IdValidator(ranges);
            idValidator.combineRanges();
            long result = 0;
            for (var id : idsToCheck) {
                if (idValidator.isFresh(id)) {
                    System.out.println(id);
                    result++;
                }
            }
            System.out.println("Part1 " + result);
            System.out.println("Part2 " + idValidator.getFreshIdsAmount());

            CodingContestFileHelper.saveOutputFile(inputPath, outputLines);
        }
        System.exit(0);
    }
}
