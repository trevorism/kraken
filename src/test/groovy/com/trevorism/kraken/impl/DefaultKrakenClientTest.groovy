package com.trevorism.kraken.impl
import com.trevorism.kraken.KrakenClient
import com.trevorism.kraken.model.Price
import com.trevorism.kraken.model.ValidCandleDurations
import org.junit.Test

class DefaultKrakenClientTest {

    @Test
    void "server time returns the time"() {
        KrakenClient defaultKrakenClient = new DefaultKrakenClient()
        long value = defaultKrakenClient.serverTime();

        println new Date(value)
    }


    @Test
    void "get assets"() {
        KrakenClient defaultKrakenClient = new DefaultKrakenClient()
        def assets = defaultKrakenClient.assets

        assert assets
        assert assets[0].assetName
    }

    @Test
    void "get asset pairs"(){
        KrakenClient defaultKrakenClient = new DefaultKrakenClient()
        def assetPairs = defaultKrakenClient.assetPairs

        assetPairs.each {
            println it
        }
    }

    @Test
    void "get price"(){
        KrakenClient defaultKrakenClient = new DefaultKrakenClient()
        Price price = defaultKrakenClient.getCurrentPrice("XBTUSD")

        println price
    }

    @Test
    void "get daily candles"(){
        KrakenClient defaultKrakenClient = new DefaultKrakenClient()
        def candles = defaultKrakenClient.getCandles("XBTUSD")

        candles.each {
            println it
        }
    }

    @Test
    void "get five minute candles"(){
        KrakenClient defaultKrakenClient = new DefaultKrakenClient()
        def candles = defaultKrakenClient.getCandles("XBTUSD", ValidCandleDurations.FIVE_MINUTES)

        candles.each {
            println it
        }
    }
}
