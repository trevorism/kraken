package com.trevorism.kraken.model.trade;

public class CancelOrderResult {
    private int count;
    private String pending;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPending() {
        return pending;
    }

    public void setPending(String pending) {
        this.pending = pending;
    }
}
