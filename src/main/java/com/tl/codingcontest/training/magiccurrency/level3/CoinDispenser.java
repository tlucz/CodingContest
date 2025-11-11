package com.tl.codingcontest.training.magiccurrency.level3;

import com.tl.codingcontest.training.magiccurrency.Currency;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CoinDispenser {

    private final Currency currency;
    private final List<Integer> sortedCoins;

    public CoinDispenser(Currency currency) {
        this.currency = currency;
        sortedCoins = currency.coins().stream().map(c -> c.value()).sorted().toList().reversed();

    }

    public String dispense(int amount) {
        Map<Integer, Integer> usedCoins = sortedCoins.stream().collect(Collectors.toMap(i -> i, i -> 0));
        int sum = 0;
        while (sum < amount) {
            int value = findNextCoin(amount - sum);
            sum += value;
            usedCoins.compute(value, (k, before) -> before + 1);
        }
        if (sum > amount) {
            throw new IllegalStateException("sum>amount");
        }
        return usedCoins.entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .map(entry -> entry.getValue() + "x" + entry.getKey())
                .collect(Collectors.joining(" "));
    }

    private int findNextCoin(int value) {
        for (Integer sortedCoin : sortedCoins) {
            if (sortedCoin <= value) {
                return sortedCoin;
            }
        }
        throw new RuntimeException("Illegal state");
    }
}
