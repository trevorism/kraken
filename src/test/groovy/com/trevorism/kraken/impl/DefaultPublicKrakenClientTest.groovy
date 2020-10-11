package com.trevorism.kraken.impl

import com.trevorism.kraken.PublicKrakenClient
import com.trevorism.kraken.model.Price
import com.trevorism.kraken.model.ValidCandleDurations
import org.junit.Test

class DefaultPublicKrakenClientTest {

    private static long TEN_SECONDS_IN_MILLIS = 10 * 1000

    @Test
    void "server time returns the time"() {
        PublicKrakenClient defaultKrakenClient = new DefaultPublicKrakenClient()
        long value = defaultKrakenClient.serverTime()
        assertServerTimeIsWithinTenSecondsOfNow(value)
    }

    private void assertServerTimeIsWithinTenSecondsOfNow(long value) {
        assert Math.abs(value - new Date().time) < TEN_SECONDS_IN_MILLIS
    }

    @Test
    void "get assets"() {
        PublicKrakenClient defaultKrakenClient = new DefaultPublicKrakenClient()
        def assets = defaultKrakenClient.assets

        assert assets
        assert assets[0].assetName
    }

    @Test
    void "get asset pairs"() {
        PublicKrakenClient defaultKrakenClient = new DefaultPublicKrakenClient()
        def assetPairs = defaultKrakenClient.assetPairs
        println assetPairs
        assert assetPairs
        assert assetPairs[0].pairName
        assert assetPairs[-1].pairName
    }

    @Test
    void "get price"() {
        PublicKrakenClient defaultKrakenClient = new DefaultPublicKrakenClient()
        Price price = defaultKrakenClient.getCurrentPrice("XBTUSD")

        assert price
        assert price.last
        assert price.bid
        assert price.ask
    }

    @Test
    void "get daily candles"() {
        PublicKrakenClient defaultKrakenClient = new DefaultPublicKrakenClient()
        def candles = defaultKrakenClient.getCandles("XBTUSD")

        assert candles
        assert candles[0]
        assert candles[-1]
    }

    @Test
    void "get five minute candles"() {
        PublicKrakenClient defaultKrakenClient = new DefaultPublicKrakenClient()
        def candles = defaultKrakenClient.getCandles("XBTUSD", ValidCandleDurations.FIVE_MINUTES)

        assert candles
        assert candles[0]
        assert candles[-1]
    }

}
