package com.trevorism.kraken.model.trade;

public class StopLossTrade extends Trade {

    private final double price;

    public StopLossTrade(String pair, String buyOrSell, double amount, double price) {
        super(pair, buyOrSell, amount);
        orderType = "stop-loss";
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

}
