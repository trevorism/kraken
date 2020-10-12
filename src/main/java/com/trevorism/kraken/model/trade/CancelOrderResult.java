package com.trevorism.kraken.model.trade;

import groovy.transform.ToString;

public class CancelOrderResult {
    private String transactionId;
    private int count;
    private String pending;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

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

    @Override
    public String toString() {
        return transactionId + " : " + count;
    }
}
