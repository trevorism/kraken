package com.trevorism.kraken.model.trade;

public final class LimitTrade extends Trade {

    private double price;

    public LimitTrade() {
        this.orderType = "limit";
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
