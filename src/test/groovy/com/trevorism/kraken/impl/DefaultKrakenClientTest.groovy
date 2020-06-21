package com.trevorism.kraken.impl
import com.trevorism.kraken.KrakenClient
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

        assets.each {
            println it
        }
    }
}
