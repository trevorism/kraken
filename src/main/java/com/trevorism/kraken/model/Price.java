package com.trevorism.kraken.model;

public class Price {
    private Double last;
    private Double bid; //Buy at this price
    private Double ask; //Sell at this price

    public Double getLast() {
        return last;
    }

    public void setLast(Double last) {
        this.last = last;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    @Override
    public String toString() {
        return "Price{" +
                "last=" + last +
                ", bid=" + bid +
                ", ask=" + ask +
                '}';
    }
}
