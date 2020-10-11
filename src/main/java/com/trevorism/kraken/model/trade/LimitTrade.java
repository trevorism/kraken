package com.trevorism.kraken.model.trade;

public class LimitTrade extends Trade {

    private final double price;

    public LimitTrade(String pair, String buyOrSell, double amount, double price) {
        super(pair, buyOrSell, amount);
        orderType = "limit";
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

}
