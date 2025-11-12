package com.tl.codingcontest.training.magiccurrency.level4;

import com.tl.codingcontest.training.magiccurrency.Coin;
import com.tl.codingcontest.training.magiccurrency.Currency;
import com.tl.utils.CodingContestFileHelper;
import com.tl.utils.ParserHelper;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MagicCurrencyLevel4App {

    public static void main(String[] args) {
        CodingContestFileHelper codingContestFileHelper = new CodingContestFileHelper(MagicCurrencyLevel4App.class);
        List<Path> inputFilesPaths = codingContestFileHelper.readInputFiles(false);
        for (var inputPath : inputFilesPaths) {
            List<String> inputLines = CodingContestFileHelper.readFile(inputPath);
            int currenciesNumber = ParserHelper.getNumbers(inputLines.getFirst()).getFirst().intValue();
            int coinsNumber = ParserHelper.getNumbers(inputLines.get(1)).getFirst().intValue();

            List<String> outputLines = new ArrayList<>();
            for (int i = 0; i < currenciesNumber; i++) {
                Currency currency = new Currency(
                        ParserHelper.getNumbers(inputLines.get(i * 2 + 3)).stream().map(d -> d.intValue()).map(v -> new Coin(v)).toList());
                List<Integer> amounts = ParserHelper.getNumbers(inputLines.get(i * 2 + 4)).stream().map(d -> d.intValue()).toList();

                BruteForceCoinDispenser4 dispenser = new BruteForceCoinDispenser4(currency);
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
