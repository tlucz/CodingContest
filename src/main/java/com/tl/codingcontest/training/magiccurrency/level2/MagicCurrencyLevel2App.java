package com.tl.codingcontest.training.magiccurrency.level2;

import com.tl.codingcontest.training.magiccurrency.Coin;
import com.tl.codingcontest.training.magiccurrency.Currency;
import com.tl.utils.CodingContestFileHelper;
import com.tl.utils.ParserHelper;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MagicCurrencyLevel2App {

    public static void main(String[] args) {
        CodingContestFileHelper codingContestFileHelper = new CodingContestFileHelper(MagicCurrencyLevel2App.class);
        List<Path> inputFilesPaths = codingContestFileHelper.readInputFiles();
        for (var inputPath : inputFilesPaths) {
            List<String> inputLines = CodingContestFileHelper.readFile(inputPath);
            int currenciesNumber = ParserHelper.getNumbers(inputLines.getFirst()).getFirst().intValue();
            int coinsNumber = ParserHelper.getNumbers(inputLines.get(1)).getFirst().intValue();
            int amountsNumber = ParserHelper.getNumbers(inputLines.get(2)).getFirst().intValue();

            List<String> outputLines = new ArrayList<>();
            for (int i = 0; i < currenciesNumber; i++) {
                Currency currency = new Currency(
                        ParserHelper.getNumbers(inputLines.get(i * 2 + 3)).stream().map(d -> d.intValue()).map(v -> new Coin(v)).toList());
                List<Integer> amounts = ParserHelper.getNumbers(inputLines.get(i * 2 + 4)).stream().map(d -> d.intValue()).toList();

                for (int amount : amounts) {
                    outputLines.add(find(amount, currency));
                }
            }
            CodingContestFileHelper.saveOutputFile(inputPath, outputLines);
        }
        System.exit(0);
    }

    private static String find(int amount, Currency currency) {
        for (Coin c1 : currency.coins()) {
            for (Coin c2 : currency.coins()) {
                if (c1.value() + c2.value() == amount) {
                    return c1.value() + " " + c2.value();
                }
            }
        }
        throw new RuntimeException();
    }
}
