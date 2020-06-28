package com.trevorism.kraken.model;

public class AssetBalance {

    private String assetName;
    private Double balance;

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "AssetBalance{" +
                "assetName='" + assetName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
