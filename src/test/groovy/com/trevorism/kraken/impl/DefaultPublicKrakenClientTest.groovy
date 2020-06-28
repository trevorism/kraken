package com.trevorism.kraken.impl

import com.trevorism.kraken.PublicKrakenClient
import com.trevorism.kraken.model.Price
import com.trevorism.kraken.model.ValidCandleDurations
import org.junit.Test

class DefaultPublicKrakenClientTest {

    @Test
    void "server time returns the time"() {
        PublicKrakenClient defaultKrakenClient = new DefaultPublicKrakenClient()
        long value = defaultKrakenClient.serverTime();

        println new Date(value)
    }


    @Test
    void "get assets"() {
        PublicKrakenClient defaultKrakenClient = new DefaultPublicKrakenClient()
        def assets = defaultKrakenClient.assets

        assert assets
        assert assets[0].assetName
    }

    @Test
    void "get asset pairs"(){
        PublicKrakenClient defaultKrakenClient = new DefaultPublicKrakenClient()
        def assetPairs = defaultKrakenClient.assetPairs

        assetPairs.each {
            println it
        }
    }

    @Test
    void "get price"(){
        PublicKrakenClient defaultKrakenClient = new DefaultPublicKrakenClient()
        Price price = defaultKrakenClient.getCurrentPrice("XBTUSD")

        println price
    }

    @Test
    void "get daily candles"(){
        PublicKrakenClient defaultKrakenClient = new DefaultPublicKrakenClient()
        def candles = defaultKrakenClient.getCandles("XBTUSD")

        candles.each {
            println it
        }
    }

    @Test
    void "get five minute candles"(){
        PublicKrakenClient defaultKrakenClient = new DefaultPublicKrakenClient()
        def candles = defaultKrakenClient.getCandles("XBTUSD", ValidCandleDurations.FIVE_MINUTES)

        candles.each {
            println it
        }
    }

}
