package com.tl.codingcontest.training.magiccurrency.level5;

import com.tl.codingcontest.training.magiccurrency.Coin;
import com.tl.codingcontest.training.magiccurrency.Currency;
import com.tl.utils.CodingContestFileHelper;
import com.tl.utils.ParserHelper;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MagicCurrencyLevel5App {

    public static void main(String[] args) {
        CodingContestFileHelper codingContestFileHelper = new CodingContestFileHelper(MagicCurrencyLevel5App.class);
        List<Path> inputFilesPaths = codingContestFileHelper.readInputFiles(false);
        for (var inputPath : inputFilesPaths) {
            List<String> inputLines = CodingContestFileHelper.readFile(inputPath);
            int currenciesNumber = ParserHelper.getDoubles(inputLines.getFirst()).getFirst().intValue();
            int coinsNumber = ParserHelper.getDoubles(inputLines.get(1)).getFirst().intValue();

            List<String> outputLines = new ArrayList<>();
            for (int i = 0; i < currenciesNumber; i++) {

                Map<Integer, Integer> possibleCoins = ParserHelper.getStrings(inputLines.get(i * 2 + 3)).stream().map(s -> ParserHelper.getInts(s, "x")).collect(
                        Collectors.toMap(list->list.get(1),list->list.get(0)));
                List<Integer> amounts = ParserHelper.getDoubles(inputLines.get(i * 2 + 4)).stream().map(d -> d.intValue()).toList();

                BruteForceCoinDispenser5 dispenser = new BruteForceCoinDispenser5(possibleCoins);

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
