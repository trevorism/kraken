package com.trevorism.kraken.impl

import com.trevorism.kraken.PrivateKrakenClient
import com.trevorism.kraken.PublicKrakenClient
import com.trevorism.kraken.model.*
import com.trevorism.kraken.model.trade.CancelOrderResult
import com.trevorism.kraken.model.trade.MarketTrade
import com.trevorism.kraken.model.trade.Trade
import com.trevorism.kraken.model.trade.TradeResult
import org.junit.jupiter.api.Test

import java.time.Duration

class DefaultKrakenClientTest {

    @Test
    void testConstructor() {
        def client = new DefaultKrakenClient(new DefaultPrivateKrakenClient("key", "secret"))
        assert client.publicKrakenClient
        assert client.privateKrakenClient
    }

    @Test
    void testConstructorNull() {
        def client = new DefaultKrakenClient(new DefaultPrivateKrakenClient(null, null))
        assert client.publicKrakenClient
        assert client.privateKrakenClient
    }

    @Test
    void testFunctions() {
        def client = new DefaultKrakenClient(new DefaultPrivateKrakenClient("key", "secret"))
        client.publicKrakenClient = new TestPublicKrakenClient()
        client.privateKrakenClient = new TestPrivateKrakenClient()

        assert client.serverTime() == 1
        assert client.getAssets()
        assert client.getAssetPairs()
        assert client.getCurrentPrice("TESTUSD")
        assert client.getCandles("TESTUSD", Duration.ofDays(1))
        assert client.getAccountBalances()
        assert client.getOpenOrders(null)
        assert client.getClosedOrders(null)
        assert client.createOrder(new MarketTrade(pair: "USDUSDT", buyOrSell: "buy", amount: 10))
        assert client.deleteOrder("654321-open")
    }

    class TestPublicKrakenClient implements PublicKrakenClient {
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
            [new AssetPair(pairName: "TESTUSD", baseName: "TEST", quoteName: "USD")]
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

    class TestPrivateKrakenClient implements PrivateKrakenClient {

        @Override
        Set<AssetBalance> getAccountBalances() {
            [new AssetBalance(assetName: "test", balance: 5.0)]
        }

        @Override
        List<Order> getClosedOrders(DateRange dateRange) {
            return [new Order(orderId: "123456-closed")]
        }

        @Override
        List<Order> getOpenOrders(DateRange dateRange) {
            return [new Order(orderId: "654321-open")]
        }

        @Override
        TradeResult createOrder(Trade tradeInfo) {
            return new TradeResult(orderDescription: "", transactionIds: ["654321-open"])
        }

        @Override
        CancelOrderResult deleteOrder(String transactionId) {
            return new CancelOrderResult(count: 1)
        }
    }


}
