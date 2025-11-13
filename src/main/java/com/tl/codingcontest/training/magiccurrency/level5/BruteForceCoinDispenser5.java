package com.tl.codingcontest.training.magiccurrency.level5;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.tl.codingcontest.training.magiccurrency.DispenseData;
import com.tl.codingcontest.training.magiccurrency.Wallet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BruteForceCoinDispenser5 {

    private final Map<Integer, Integer> possibleCoins;
    private final List<Integer> sortedCoins;

    Cache<DispenseData, Optional<Wallet>> cache = Caffeine.newBuilder()
            .build();

    public BruteForceCoinDispenser5(Map<Integer, Integer> possibleCoins) {
        this.possibleCoins = possibleCoins;
        sortedCoins = possibleCoins.keySet().stream().sorted().toList().reversed();
    }

    private static boolean isInteger(double coinsNumber) {
        return coinsNumber == (int) coinsNumber;
    }

    public Optional<Wallet> dispense(int amount) {
        Optional<Wallet> wallet = cachedDispense(new DispenseData(amount, sortedCoins));
        return wallet;
    }

    private Optional<Wallet> cachedDispense(DispenseData dispenseData) {
        if (cache.getIfPresent(dispenseData) != null) {
            return cache.getIfPresent(dispenseData);
        }
        Optional<Wallet> calc = dispense(dispenseData);
        cache.put(dispenseData, calc);
        return calc;
    }

    private Optional<Wallet> dispense(DispenseData dispenseData) {
        if (dispenseData.availableCoins().size() == 1) {
            Integer coinValue = dispenseData.availableCoins().get(0);
            double coinsNumber = (double) dispenseData.amount() / coinValue;
            if (isInteger(coinsNumber) && coinsNumber <= possibleCoins.get(coinValue)) {
                return Optional.of(new Wallet(Map.of(coinValue, (int) coinsNumber)));
            }
            return Optional.empty();
        } else {
            var biggestCoin = dispenseData.availableCoins().get(0);
            var numberOfBiggestCoin = Math.min(dispenseData.amount() / biggestCoin, possibleCoins.get(biggestCoin));
            var rest = dispenseData.amount() - numberOfBiggestCoin * biggestCoin;
            if (rest == 0) {
                return Optional.of(new Wallet(Map.of(biggestCoin, numberOfBiggestCoin)));
            } else {
                Wallet bestWallet = null;
                for (int nobc = numberOfBiggestCoin; nobc >= 0; nobc--) {
                    rest = dispenseData.amount() - nobc * biggestCoin;
                    if (bestWallet != null && !canBeBetter(bestWallet, nobc, rest, dispenseData.availableCoins())) {
                        break;
                    }

                    Optional<Wallet> restDispensed = cachedDispense(new DispenseData(rest, dispenseData.availableCoins().stream().skip(1).toList()));
                    if (restDispensed.isPresent()) {
                        Wallet wallet = new Wallet(new HashMap<>(Map.of(biggestCoin, nobc)));
                        wallet.add(restDispensed.get());
                        if (bestWallet == null || wallet.coinsNumber() < bestWallet.coinsNumber()) {
                            bestWallet = wallet;
                        }
                    }
                }
                return Optional.ofNullable(bestWallet);
            }
        }
    }

    private boolean canBeBetter(Wallet bestWallet, int nobc, int rest, List<Integer> availableCoins) {
        int best = bestWallet.coinsNumber();

        double bestPossible = nobc;
        for (int idx = 1; idx < availableCoins.size(); idx++) {
            Integer nextCoinValue = availableCoins.get(idx);
            double restInNextCoins = Math.ceil((double) rest / nextCoinValue);
            double usedNextCoins = Math.min(possibleCoins.get(nextCoinValue), restInNextCoins);
            bestPossible += usedNextCoins;
            rest-=usedNextCoins*nextCoinValue;
            if(rest<=0) {
                break;
            }
        }

        return bestPossible < best && rest<=0;
    }

    private int maxSum(List<Integer> availableCoins) {
        return availableCoins.stream().mapToInt(c -> c * possibleCoins.get(c)).sum();
    }
}