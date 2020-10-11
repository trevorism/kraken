package com.trevorism.kraken.model;

import java.util.Date;

public class Order {
    private String pair;
    private String type;
    private String orderType;
    private String status;
    private String reason;

    private Double amount;
    private Double amountExecuted;
    private Double cost;
    private Double fee;
    private Double price;
    private Double stopPrice;
    private Double limitPrice;
    private String leverage;

    private Date openDate;
    private Date closedDate;
    private Date startDate;
    private Date expireDate;

    private String misc;
    private String oflags;

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLeverage() {
        return leverage;
    }

    public void setLeverage(String leverage) {
        this.leverage = leverage;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmountExecuted() {
        return amountExecuted;
    }

    public void setAmountExecuted(Double amountExecuted) {
        this.amountExecuted = amountExecuted;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getStopPrice() {
        return stopPrice;
    }

    public void setStopPrice(Double stopPrice) {
        this.stopPrice = stopPrice;
    }

    public Double getLimitPrice() {
        return limitPrice;
    }

    public void setLimitPrice(Double limitPrice) {
        this.limitPrice = limitPrice;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
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

    public String getMisc() {
        return misc;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }

    public String getOflags() {
        return oflags;
    }

    public void setOflags(String oflags) {
        this.oflags = oflags;
    }

    @Override
    public String toString() {
        return "Order{" +
                "pair='" + pair + '\'' +
                ", type='" + type + '\'' +
                ", orderType='" + orderType + '\'' +
                ", status='" + status + '\'' +
                ", reason='" + reason + '\'' +
                ", amount=" + amount +
                ", amountExecuted=" + amountExecuted +
                ", cost=" + cost +
                ", fee=" + fee +
                ", price=" + price +
                ", stopPrice=" + stopPrice +
                ", limitPrice=" + limitPrice +
                ", leverage='" + leverage + '\'' +
                ", openDate=" + openDate +
                ", closedDate=" + closedDate +
                ", startDate=" + startDate +
                ", expireDate=" + expireDate +
                ", misc='" + misc + '\'' +
                ", oflags='" + oflags + '\'' +
                '}';
    }
}