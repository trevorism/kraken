package com.trevorism.kraken.model.trade;

import java.util.Date;

public abstract class Trade {

    protected String orderType;

    private final String pair;
    private final String buyOrSell;
    private final double amount;

    private Date startDate;
    private Date expireDate;
    private boolean validateOnly;

    public Trade(String pair, String buyOrSell, double amount) {
        this.pair = pair;
        this.buyOrSell = buyOrSell;
        this.amount = amount;
    }

    public String getPair() {
        return pair;
    }

    public String getBuyOrSell() {
        return buyOrSell;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public boolean isValidateOnly() {
        return validateOnly;
    }

    public void setValidateOnly(boolean validateOnly) {
        this.validateOnly = validateOnly;
    }

    public double getAmount() {
        return amount;
    }
}
