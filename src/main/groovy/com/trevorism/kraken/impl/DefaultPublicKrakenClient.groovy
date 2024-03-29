package com.trevorism.kraken.impl

import com.google.gson.Gson
import com.trevorism.http.BlankHttpClient
import com.trevorism.http.HttpClient
import com.trevorism.kraken.PublicKrakenClient
import com.trevorism.kraken.error.KrakenRequestException
import com.trevorism.kraken.model.*
import java.time.Duration

class DefaultPublicKrakenClient implements PublicKrakenClient {

    private final Gson gson = new Gson()
    private final HttpClient httpClient = new BlankHttpClient()
    private final def headersMap = ["User-Agent": "Mozilla/5.0"]

    @Override
    long serverTime() {
        String url = "https://api.kraken.com/0/public/Time"
        def content = makeKrakenPublicGetRequest(url)
        return content.result.unixtime * 1000
    }

    @Override
    List<Asset> getAssets() {
        String url = "https://api.kraken.com/0/public/Assets"
        def content = makeKrakenPublicGetRequest(url)
        return mapResponseToAssets(content)
    }

    @Override
    List<AssetPair> getAssetPairs() {
        String url = "https://api.kraken.com/0/public/AssetPairs"
        def content = makeKrakenPublicGetRequest(url)
        return mapResponseToAssetPairs(content)
    }

    @Override
    Price getCurrentPrice(String assetPair) {
        String url = "https://api.kraken.com/0/public/Ticker?pair=${assetPair}"
        def content = makeKrakenPublicGetRequest(url)
        return mapResponseIntoPrice(content)
    }

    @Override
    List<Candle> getCandles(String assetPair, Duration duration = ValidCandleDurations.DAY) {
        if (!ValidCandleDurations.validate(duration)) {
            duration = ValidCandleDurations.DAY
        }
        long minutes = duration.toMinutes()
        String url = "https://api.kraken.com/0/public/OHLC?pair=${assetPair}&interval=${minutes}"
        def content = makeKrakenPublicGetRequest(url)
        return mapResponseIntoCandles(content, duration)
    }

    private def makeKrakenPublicGetRequest(String url) {
        String json = httpClient.get(url, headersMap).value
        return gson.fromJson(json, Map)
    }

    private static List<Candle> mapResponseIntoCandles(Map content, Duration duration) {
        if (content.error) {
            throw new KrakenRequestException(content.error.toString())
        }

        List<Candle> candles = []

        content.result.findAll { k, v ->
            k != "last"
        }.each { k, v ->
            candles = v.collect { arr ->
                new Candle(duration: duration, time: new Date((arr[0] as Long) * 1000), open: arr[1] as Double,
                        high: arr[2] as Double, low: arr[3] as Double, close: arr[4] as Double,
                        volumeWeightedAveragePrice: arr[5] as Double, volume: arr[6] as Double,
                        count: arr[7] as Long)
            }
        }
        return candles
    }

    private static  List<Asset> mapResponseToAssets(Map content) {
        if (content.error) {
            throw new KrakenRequestException(content.error.toString())
        }

        def values = content.result
        return values.collect { k, v ->
            new Asset(assetName: v.altname, decimals: v.decimals, displayDecimals: v.display_decimals, krakenName: k)
        }
    }

    private static  List<AssetPair> mapResponseToAssetPairs(Map content) {
        if (content.error) {
            throw new KrakenRequestException(content.error.toString())
        }

        def values = content.result
        return values.findAll { k, v ->
            v.wsname
        }.collect { k, v ->
            String wsName = v.wsname
            def baseName = wsName[0..(wsName.indexOf("/") - 1)]
            def quoteName = v.wsname[(wsName.indexOf("/") + 1)..-1]
            def orderMin = v.ordermin as Double

            new AssetPair(pairName: v.altname, baseName: baseName, quoteName: quoteName,
                    leverageBuy: v.leverage_buy, leverageSell: v.leverage_sell, orderMin: orderMin)
        }
    }

    private static Price mapResponseIntoPrice(Map content) {
        if (content.error) {
            throw new KrakenRequestException(content.error.toString())
        }

        def values = content.result

        Price price = new Price()

        values.each { k, v ->
            price.ask = v.a[0] as Double
            price.bid = v.b[0] as Double
            price.last = v.c[0] as Double
        }

        return price
    }
}
