package com.tl.codingcontest.contest.nov2025.level1;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.tl.codingcontest.training.magiccurrency.Currency;
import com.tl.codingcontest.training.magiccurrency.DispenseData;
import com.tl.codingcontest.training.magiccurrency.Wallet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Level1Dispenser {

    private final Currency currency;
    private final List<Integer> sortedCoins;

    Cache<DispenseData, Optional<Wallet>> cache = Caffeine.newBuilder()
            .build();

    public Level1Dispenser(Currency currency) {
        this.currency = currency;
        sortedCoins = currency.coins().stream().map(c -> c.value()).sorted().toList().reversed();
    }

    private static boolean isInteger(double coinsNumber) {
        return coinsNumber == (int) coinsNumber;
    }

    public Optional<Wallet> dispense(int amount) {
        return cachedDispense(new DispenseData(amount, sortedCoins));
    }

    private Optional<Wallet> cachedDispense(DispenseData dispenseData) {
        if(cache.getIfPresent(dispenseData)!=null) {
            return cache.getIfPresent(dispenseData);
        }
        Optional<Wallet> calc = dispense(dispenseData);
        cache.put(dispenseData,calc);
        return calc;
    }

    private Optional<Wallet> dispense(DispenseData dispenseData) {
        if (dispenseData.availableCoins().size() == 1) {
            Integer coinValue = dispenseData.availableCoins().get(0);
            double coinsNumber = (double) dispenseData.amount() / coinValue;
            if (isInteger(coinsNumber)) {
                return Optional.of(new Wallet(Map.of(coinValue, (int) coinsNumber)));
            }
            return Optional.empty();
        } else {
            var biggestCoin = dispenseData.availableCoins().get(0);
            var numberOfBiggestCoin = dispenseData.amount() / biggestCoin;
            var rest = dispenseData.amount() - numberOfBiggestCoin * biggestCoin;
            if (rest == 0) {
                return Optional.of(new Wallet(Map.of(biggestCoin, numberOfBiggestCoin)));
            } else {
                Wallet bestWallet = null;
                for (int nobc = numberOfBiggestCoin; nobc >= 0; nobc--) {
                    rest = dispenseData.amount() - nobc * biggestCoin;
                    if (bestWallet!=null && cannotBeBetter(bestWallet, nobc, rest, dispenseData.availableCoins())) {
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
                return Optional.of(bestWallet);
            }
        }
    }

    private boolean cannotBeBetter(Wallet bestWallet, int nobc, int rest, List<Integer> availableCoins) {
        int best= bestWallet.coinsNumber();

        double restInSecondCoin = Math.ceil((double) rest / availableCoins.get(1));
        var bestPossible = nobc + restInSecondCoin;
        return bestPossible>=best;
    }
}