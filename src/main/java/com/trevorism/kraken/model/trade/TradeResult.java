package com.trevorism.kraken.model.trade;

import java.util.ArrayList;
import java.util.List;

public class TradeResult {

    private String orderDescription;
    private String closeDescription;
    private List<String> transactionIds = new ArrayList<>();

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public String getCloseDescription() {
        return closeDescription;
    }

    public void setCloseDescription(String closeDescription) {
        this.closeDescription = closeDescription;
    }

    public List<String> getTransactionIds() {
        return transactionIds;
    }

    public void setTransactionIds(List<String> transactionIds) {
        this.transactionIds = transactionIds;
    }

    @Override
    public String toString() {
        String close = (closeDescription == null) ? "" : (" " + closeDescription);
        return transactionIds.toString() + " " + orderDescription + close;
    }
}
