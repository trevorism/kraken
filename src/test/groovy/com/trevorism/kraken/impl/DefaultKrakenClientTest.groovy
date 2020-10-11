package com.trevorism.kraken.impl

import com.trevorism.kraken.PrivateKrakenClient
import com.trevorism.kraken.PublicKrakenClient
import com.trevorism.kraken.model.Asset
import com.trevorism.kraken.model.AssetBalance
import com.trevorism.kraken.model.AssetPair
import com.trevorism.kraken.model.Candle
import com.trevorism.kraken.model.DateRange
import com.trevorism.kraken.model.Order
import com.trevorism.kraken.model.Price
import org.junit.Test

import java.time.Duration

class DefaultKrakenClientTest {

    @Test
    void testConstructor(){
        def client = new DefaultKrakenClient()
        assert client.publicKrakenClient
        assert client.privateKrakenClient
    }

    @Test
    void testConstructorNull(){
        def client = new DefaultKrakenClient(new DefaultPrivateKrakenClient("key","secret"))
        assert client.publicKrakenClient
        assert client.privateKrakenClient
    }

    @Test
    void testFunctions() {
        def client = new DefaultKrakenClient()
        client.publicKrakenClient = new TestPublicKrakenClient()
        client.privateKrakenClient = new TestPrivateKrakenClient()

        assert client.serverTime() == 1
        assert client.getAssets()
        assert client.getAssetPairs()
        assert client.getCurrentPrice("TESTUSD")
        assert client.getCandles("TESTUSD", Duration.ofDays(1))
        assert client.getAccountBalances()
    }

    class TestPublicKrakenClient implements PublicKrakenClient{
        @Override
        long serverTime() {
            1
        }

        @Override
        List<Asset> getAssets() {
            [new Asset(assetName: "test")]
        }

        @Override
        List<AssetPair> getAssetPairs() {
            [new AssetPair(pairName:"TESTUSD", baseName: "TEST", quoteName: "USD")]
        }

        @Override
        Price getCurrentPrice(String assetPair) {
            assert assetPair
            new Price(last: 5.0, bid: 5.1, ask: 4.9)
        }

        @Override
        List<Candle> getCandles(String assetPair, Duration duration) {
            assert assetPair
            assert duration
            [new Candle(duration: Duration.ofDays(1), open: 5.0, close: 4.0, high: 5.2, low: 3.5, count: 532, volumeWeightedAveragePrice: 5.0, volume: 123.23, time: new Date())]
        }
    }

    class TestPrivateKrakenClient implements PrivateKrakenClient{

        @Override
        Set<AssetBalance> getAccountBalances() {
            [new AssetBalance(assetName: "test", balance: 5.0)]
        }

        @Override
        List<Order> getClosedOrders(DateRange dateRange) {
            return null
        }

        @Override
        List<Order> getOpenOrders(DateRange dateRange) {
            return null
        }
    }


}
