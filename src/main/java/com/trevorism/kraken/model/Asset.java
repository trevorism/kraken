package com.trevorism.kraken.model;

public class Asset {

    private String assetName;
    private int decimals;
    private int displayDecimals;
    private String krakenName;

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public int getDecimals() {
        return decimals;
    }

    public void setDecimals(int decimals) {
        this.decimals = decimals;
    }

    public int getDisplayDecimals() {
        return displayDecimals;
    }

    public void setDisplayDecimals(int displayDecimals) {
        this.displayDecimals = displayDecimals;
    }

    public String getKrakenName() {
        return krakenName;
    }

    public void setKrakenName(String krakenName) {
        this.krakenName = krakenName;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "assetName='" + assetName + '\'' +
                ", decimals=" + decimals +
                ", displayDecimals=" + displayDecimals +
                ", krakenName='" + krakenName + '\'' +
                '}';
    }
}
