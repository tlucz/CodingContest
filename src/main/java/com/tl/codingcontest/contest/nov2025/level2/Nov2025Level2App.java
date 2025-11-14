package com.tl.codingcontest.contest.nov2025.level2;

import com.tl.utils.CodingContestFileHelper;
import com.tl.utils.ParserHelper;
import com.tl.utils.StringIteration;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Nov2025Level2App {

    public static void main(String[] args) {
        CodingContestFileHelper codingContestFileHelper = new CodingContestFileHelper(Nov2025Level2App.class);
        List<Path> inputFilesPaths = codingContestFileHelper.readInputFiles(false);

        for (var inputPath : inputFilesPaths) {

            var stringIteration = new StringIteration(CodingContestFileHelper.readFile(inputPath));

            int sequencesNumber = ParserHelper.getDoubles(stringIteration.getNext()).getFirst().intValue();

            List<String> outputLines = new ArrayList<>();

            for (int i = 0; i < sequencesNumber; i++) {
                List<Integer> paces = ParserHelper.getInts(stringIteration.getNext());

                int distance = paces.stream()
                        .mapToInt(x -> x)
                        .map(operand -> Math.min(operand, 1))
                        .map(operand -> Math.max(operand, -1))
                        .sum();
                int time = paces.stream()
                        .mapToInt(x -> x)
                        .map(operand -> Math.abs(operand))
                        .map(operand -> Math.max(operand, 1))
                        .sum();


                outputLines.add(distance + " " + time);
            }
            CodingContestFileHelper.saveOutputFile(inputPath, outputLines);
        }
        System.exit(0);
    }
}
