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

            int currenciesNumber = ParserHelper.getDoubles(stringIteration.getNext()).getFirst().intValue();
            int coinsNumber = ParserHelper.getDoubles(stringIteration.getNext()).getFirst().intValue();
            stringIteration.getNext();

            List<String> outputLines = new ArrayList<>();

            for (int i = 0; i < currenciesNumber; i++) {
                Currency currency = new Currency(
                        ParserHelper.getDoubles(stringIteration.getNext())
                                .stream()
                                .map(d -> d.intValue())
                                .map(v -> new Coin(v)).toList());
                List<Integer> amounts = ParserHelper.getDoubles(stringIteration.getNext())
                        .stream()
                        .map(d -> d.intValue())
                        .toList();

                Level1Dispenser dispenser = new Level1Dispenser(currency);
                for (int amount : amounts) {
                    System.out.println(inputPath.toString() + " - Currency:" + (i + 1) + "/" + currenciesNumber + " Amount:" + amount);
                    String dispenseLine = dispenser.dispense(amount).get().display();
                    System.out.println(dispenseLine);
                    outputLines.add(dispenseLine);
                }
            }
            CodingContestFileHelper.saveOutputFile(inputPath, outputLines);
        }
        System.exit(0);
    }
}
