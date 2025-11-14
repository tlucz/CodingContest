package com.tl.codingcontest.contest.nov2025.level1;

import com.tl.codingcontest.training.magiccurrency.Coin;
import com.tl.codingcontest.training.magiccurrency.Currency;
import com.tl.utils.CodingContestFileHelper;
import com.tl.utils.ParserHelper;
import com.tl.utils.StringIteration;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Nov2025Level1App {

    public static void main(String[] args) {
        CodingContestFileHelper codingContestFileHelper = new CodingContestFileHelper(Nov2025Level1App.class);
        List<Path> inputFilesPaths = codingContestFileHelper.readInputFiles(false);

        for (var inputPath : inputFilesPaths) {

            var stringIteration = new StringIteration(CodingContestFileHelper.readFile(inputPath));

            int sequencesNumber = ParserHelper.getDoubles(stringIteration.getNext()).getFirst().intValue();

            List<String> outputLines = new ArrayList<>();

            for (int i = 0; i < sequencesNumber; i++) {
                List<Integer> paces = ParserHelper.getInts(stringIteration.getNext());
                int sum = paces.stream().mapToInt(x -> x).sum();
                outputLines.add(String.valueOf(sum));

            }
            CodingContestFileHelper.saveOutputFile(inputPath, outputLines);
        }
        System.exit(0);
    }
}
