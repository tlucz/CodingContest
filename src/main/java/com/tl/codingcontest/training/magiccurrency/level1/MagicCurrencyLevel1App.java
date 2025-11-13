package com.tl.codingcontest.training.magiccurrency.level1;

import com.tl.codingcontest.training.magiccurrency.Coin;
import com.tl.codingcontest.training.magiccurrency.Currency;
import com.tl.utils.CodingContestFileHelper;
import com.tl.utils.ParserHelper;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MagicCurrencyLevel1App {

    public static void main(String[] args) {
        CodingContestFileHelper codingContestFileHelper = new CodingContestFileHelper(MagicCurrencyLevel1App.class);
        List<Path> inputFilesPaths = codingContestFileHelper.readInputFiles(false);
        for (var inputPath : inputFilesPaths) {
            List<String> inputLines = CodingContestFileHelper.readFile(inputPath);
            int currenciesNumber = ParserHelper.getDoubles(inputLines.getFirst()).getFirst().intValue();
            int coinsNumber = ParserHelper.getDoubles(inputLines.get(1)).getFirst().intValue();

            List<String> outputLines = new ArrayList<>();
            for (int i = 0; i < currenciesNumber; i++) {
                Currency currency = new Currency(ParserHelper.getDoubles(inputLines.get(i + 2)).stream().map(d -> d.intValue()).map(v->new Coin(v)).toList());
                Coin min = new Coin(1);
                while (true) {
                    if (!currency.coins().contains(min)) {
                        break;
                    }
                    min = new Coin(min.value()+1);
                }
                outputLines.add(String.valueOf(min.value()));
            }
            CodingContestFileHelper.saveOutputFile(inputPath, outputLines);
        }
        System.exit(0);
    }
}
