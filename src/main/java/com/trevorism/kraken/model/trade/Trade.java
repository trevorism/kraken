package com.trevorism.kraken.model.trade;

import java.util.Date;

public abstract class Trade {

    protected String orderType;

    protected String pair;
    protected String buyOrSell;
    protected double amount;

    protected Date startDate;
    protected Date expireDate;
    protected boolean validateOnly;

    public String getOrderType() {
        return orderType;
    }

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    public String getBuyOrSell() {
        return buyOrSell;
    }

    public void setBuyOrSell(String buyOrSell) {
        this.buyOrSell = buyOrSell;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(buyOrSell);
        stringBuilder.append(" ");
        stringBuilder.append(amount);
        stringBuilder.append(" ");
        stringBuilder.append(pair);
        stringBuilder.append(" ");
        stringBuilder.append(orderType);
        return stringBuilder.toString();
    }
}
