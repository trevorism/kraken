package com.trevorism.kraken.model.trade;

public class MarketTrade extends Trade {

    public MarketTrade(String pair, String buyOrSell, double amount) {
        super(pair, buyOrSell, amount);
        orderType = "market";
    }
}
