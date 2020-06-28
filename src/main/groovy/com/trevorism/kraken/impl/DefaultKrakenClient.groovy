package com.trevorism.kraken.impl

import com.trevorism.kraken.KrakenClient
import com.trevorism.kraken.PrivateKrakenClient
import com.trevorism.kraken.PublicKrakenClient
import com.trevorism.kraken.model.Asset
import com.trevorism.kraken.model.AssetPair
import com.trevorism.kraken.model.Candle
import com.trevorism.kraken.model.Price
import com.trevorism.kraken.model.ValidCandleDurations

import java.time.Duration

class DefaultKrakenClient implements KrakenClient{

    private static final PrivateKrakenClient privateKrakenClient = new DefaultPrivateKrakenClient()
    private static final PublicKrakenClient publicKrakenClient = new DefaultPublicKrakenClient()

    @Override
    long serverTime() {
        return publicKrakenClient.serverTime()
    }

    @Override
    List<Asset> getAssets() {
        return publicKrakenClient.getAssets()
    }

    @Override
    List<AssetPair> getAssetPairs() {
        return publicKrakenClient.getAssetPairs()
    }

    @Override
    Price getCurrentPrice(String assetPair) {
        return publicKrakenClient.getCurrentPrice()
    }

    @Override
    List<Candle> getCandles(String assetPair, Duration duration = ValidCandleDurations.DAY) {
        return publicKrakenClient.getCandles(assetPair, duration)
    }

    @Override
    void getAccountBalance() {
        privateKrakenClient.getAccountBalance()
    }

}
