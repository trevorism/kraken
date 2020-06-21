package com.trevorism.kraken.model;

import java.util.ArrayList;
import java.util.List;

public class AssetPair {

    private String pairName;
    private String baseName;
    private String quoteName;
    private List<Integer> leverageBuy = new ArrayList<>();
    private List<Integer> leverageSell = new ArrayList<>();
    private Double orderMin;

    public String getPairName() {
        return pairName;
    }

    public void setPairName(String pairName) {
        this.pairName = pairName;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public String getQuoteName() {
        return quoteName;
    }

    public void setQuoteName(String quoteName) {
        this.quoteName = quoteName;
    }

    public List<Integer> getLeverageBuy() {
        return leverageBuy;
    }

    public void setLeverageBuy(List<Integer> leverageBuy) {
        this.leverageBuy = leverageBuy;
    }

    public List<Integer> getLeverageSell() {
        return leverageSell;
    }

    public void setLeverageSell(List<Integer> leverageSell) {
        this.leverageSell = leverageSell;
    }

    public Double getOrderMin() {
        return orderMin;
    }

    public void setOrderMin(Double orderMin) {
        this.orderMin = orderMin;
    }

    @Override
    public String toString() {
        return "AssetPair{" +
                "pairName='" + pairName + '\'' +
                ", baseName='" + baseName + '\'' +
                ", quoteName='" + quoteName + '\'' +
                ", leverageBuy=" + leverageBuy +
                ", leverageSell=" + leverageSell +
                ", orderMin=" + orderMin +
                '}';
    }
}
