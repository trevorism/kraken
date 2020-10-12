package com.trevorism.kraken.model.trade;

public final class StopLossTrade extends Trade {

    private double price;

    public StopLossTrade() {
        orderType = "stop-loss";
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        String prefix = super.toString();
        return prefix + " @ " + price;
    }
}
