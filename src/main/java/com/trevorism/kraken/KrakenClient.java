package com.trevorism.kraken;

import com.trevorism.kraken.model.*;

import java.time.Duration;
import java.util.List;

public interface KrakenClient {

    long serverTime();
    List<Asset> getAssets();
    List<AssetPair> getAssetPairs();
    Price getCurrentPrice(String assetPair);
    List<Candle> getCandles(String assetPair, Duration duration);

}
