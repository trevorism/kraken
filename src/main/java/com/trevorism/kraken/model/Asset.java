package com.trevorism.kraken.model;

import groovy.transform.ToString;

public class Asset {

    private String assetName;
    private int decimals;
    private int displayDecimals;

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

    @Override
    public String toString() {
        return "Asset{" +
                "assetName='" + assetName + '\'' +
                ", decimals=" + decimals +
                ", displayDecimals=" + displayDecimals +
                '}';
    }
}
