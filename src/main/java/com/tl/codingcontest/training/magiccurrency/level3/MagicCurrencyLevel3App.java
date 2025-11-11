package com.tl.codingcontest.training.magiccurrency.level3;

import com.tl.codingcontest.training.magiccurrency.Coin;
import com.tl.codingcontest.training.magiccurrency.Currency;
import com.tl.utils.CodingContestFileHelper;
import com.tl.utils.ParserHelper;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MagicCurrencyLevel3App {

    public static void main(String[] args) {
        CodingContestFileHelper codingContestFileHelper = new CodingContestFileHelper(MagicCurrencyLevel3App.class);
        List<Path> inputFilesPaths = codingContestFileHelper.readInputFiles();
        for (var inputPath : inputFilesPaths) {
            List<String> inputLines = CodingContestFileHelper.readFile(inputPath);
            int currenciesNumber = ParserHelper.getNumbers(inputLines.getFirst()).getFirst().intValue();
            int coinsNumber = ParserHelper.getNumbers(inputLines.get(1)).getFirst().intValue();

            List<String> outputLines = new ArrayList<>();
            for (int i = 0; i < currenciesNumber; i++) {
                Currency currency = new Currency(
                        ParserHelper.getNumbers(inputLines.get(i + 2)).stream().map(d -> d.intValue()).map(v -> new Coin(v)).toList());
                CoinDispenser dispenser = new CoinDispenser(currency);
                for (int amount = 1; amount <= 100; amount++) {
                    String line=dispenser.dispense(amount);
                    outputLines.add(line);
                }
            }
            CodingContestFileHelper.saveOutputFile(inputPath, outputLines);
        }
        System.exit(0);
    }
}
