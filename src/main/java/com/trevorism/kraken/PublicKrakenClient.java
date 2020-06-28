package com.trevorism.kraken;

import com.trevorism.kraken.model.Asset;
import com.trevorism.kraken.model.AssetPair;
import com.trevorism.kraken.model.Candle;
import com.trevorism.kraken.model.Price;

import java.time.Duration;
import java.util.List;

public interface PublicKrakenClient {

    long serverTime();
    List<Asset> getAssets();
    List<AssetPair> getAssetPairs();

    Price getCurrentPrice(String assetPair);
    List<Candle> getCandles(String assetPair, Duration duration);
}