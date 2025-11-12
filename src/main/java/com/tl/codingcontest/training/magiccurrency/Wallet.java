package com.tl.codingcontest.training.magiccurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public record Wallet(Map<Integer, Integer> coins) implements Comparable<Wallet> {

    @Override
    public int compareTo(Wallet o) {
        int compareCoinsNumber = Integer.compare(coinsNumber(), o.coinsNumber());
        if (compareCoinsNumber == 0) {
            return Integer.compare(o.sum(), sum());
        }
        return compareCoinsNumber;
    }

    public int sum() {
        return coins.entrySet().stream().mapToInt(entry -> entry.getKey() * entry.getValue()).sum();
    }

    public int coinsNumber() {
        return coins.values().stream().mapToInt(v -> v).sum();
    }

    public String display() {
        return coins.entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .map(entry -> entry.getValue() + "x" + entry.getKey()).collect(Collectors.joining(" "));
    }

    public Wallet add(Integer coin) {
        Map<Integer, Integer> newCoins = new HashMap<>(coins());
        if (newCoins.get(coin) == null) {
            newCoins.put(coin, 1);
        } else {
            newCoins.put(coin, newCoins.get(coin) + 1);
        }
        return new Wallet(newCoins);
    }

    public void add(Wallet otherWallet) {
        otherWallet.coins().forEach(
                (k, v) -> coins.merge(k, v, Integer::sum)
        );
    }
}
