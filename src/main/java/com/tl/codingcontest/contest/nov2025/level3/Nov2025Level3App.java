package com.tl.codingcontest.contest.nov2025.level3;

import com.tl.utils.CodingContestFileHelper;
import com.tl.utils.ParserHelper;
import com.tl.utils.StringIteration;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Nov2025Level3App {

    public static void main(String[] args) {
        CodingContestFileHelper codingContestFileHelper = new CodingContestFileHelper(Nov2025Level3App.class);
        List<Path> inputFilesPaths = codingContestFileHelper.readInputFiles(false);

        for (var inputPath : inputFilesPaths) {

            var stringIteration = new StringIteration(CodingContestFileHelper.readFile(inputPath));

            int sequencesNumber = ParserHelper.getDoubles(stringIteration.getNext()).getFirst().intValue();

            List<String> outputLines = new ArrayList<>();

            for (int i = 0; i < sequencesNumber; i++) {
                List<Integer> numbers = ParserHelper.getInts(stringIteration.getNext());
                int travelTo = numbers.get(0);
                int maxAvailable = numbers.get(1);
                List<Integer> paces = new Spaceship().travelTo(travelTo);

                //TODO:
                outputLines.add(paces.stream()
                        .map(integer -> Integer.toString(integer))
                        .collect(Collectors.joining(" ")));

            }
            CodingContestFileHelper.saveOutputFile(inputPath, outputLines);
        }
        System.exit(0);
    }
}
