package com.tl.codingcontest.training.magiccurrency.level3;

import com.tl.codingcontest.training.magiccurrency.Currency;
import com.tl.codingcontest.training.magiccurrency.Wallet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class BruteForceCoinDispenser {

    private final Currency currency;
    private final List<Integer> sortedCoins;
    private Map<Integer, Wallet> bestWallets = new HashMap<>();

    public BruteForceCoinDispenser(Currency currency) {
        this.currency = currency;
        sortedCoins = currency.coins().stream().map(c -> c.value()).sorted().toList().reversed();
    }

    public String dispense(int amount) {
        PriorityQueue<Wallet> possibleWallets = new PriorityQueue<>();
        for (var coin : sortedCoins) {
            possibleWallets.add(new Wallet(Map.of(coin, 1)));
        }
        while (true) {
            Wallet wallet = possibleWallets.poll();
            if (wallet.sum() == amount) {
                bestWallets.put(amount, wallet);
                return wallet.display();
            }
            if (wallet.sum() < amount && wallet.coinsNumber()<=getBest(wallet.sum())) {
                for (Integer sortedCoin : sortedCoins) {
                    Wallet newWallet = wallet.add(sortedCoin);
                    if (newWallet.sum() <= amount) {
                        possibleWallets.add(newWallet);
                    }
                }
            }
        }
    }

    private int getBest(int sum) {
        if(bestWallets.get(sum)==null) {
            return 1000000;
        } else {
            return bestWallets.get(sum).coinsNumber();
        }
    }
}