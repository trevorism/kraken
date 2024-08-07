package com.trevorism.kraken.impl

import com.google.gson.Gson
import com.trevorism.http.HeadersHttpResponse
import com.trevorism.http.HttpClient
import com.trevorism.kraken.PrivateKrakenClient
import com.trevorism.kraken.error.KrakenRequestException
import com.trevorism.kraken.model.DateRange
import com.trevorism.kraken.model.trade.LimitTrade
import com.trevorism.kraken.model.trade.MarketTrade
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertThrows

class DefaultPrivateKrakenClientTest {

    private Gson gson = new Gson()
    private PrivateKrakenClient privateKrakenClient

    @BeforeEach
    void setup() {
        privateKrakenClient = new DefaultPrivateKrakenClient("key", "secret")
    }

    @Test
    void testGetAccountBalance() {
        mockHttpCallForAccountBalance()
        def balances = privateKrakenClient.getAccountBalances()

        assert balances
        assert balances.size() == 2
        assert balances[0].assetName
        assert balances[0].balance > DefaultPrivateKrakenClient.EPSILON
    }

    @Test
    void testGetClosedOrders() {
        mockHttpCallForOrders("closed")
        def result = privateKrakenClient.getClosedOrders(new DateRange(endDate: new Date()))
        assert result
        assert result[0]
        assert result[0].orderId == "transactionId1"
        assert result[0].pair == "USDXBT"
        assert result[0].status == "closed"
        assert result[0].amount == 10
        assert result[0].price == 100
    }

    @Test
    void testGetOpenOrders() {
        mockHttpCallForOrders("open")
        def result = privateKrakenClient.getOpenOrders(new DateRange(startDate: new Date()))
        assert result
        assert result[0]
        assert result[0].orderId == "transactionId1"
        assert result[0].pair == "USDXBT"
        assert result[0].status == "open"
        assert result[0].amount == 10
        assert result[0].price == 100
    }

    @Test
    void testCreateOrder() {
        mockHttpCallForTrade()
        def result = privateKrakenClient.createOrder(new LimitTrade(pair:  "XBTUSD", buyOrSell: "buy", amount: 1, price: 400d))
        assert result
        assert result.orderDescription
        assert result.transactionIds
    }

    @Test
    void testCreateMarketOrder() {
        mockHttpCallForTrade()
        def result = privateKrakenClient.createOrder(new MarketTrade(pair:  "XBTUSD", buyOrSell: "buy", amount: 1))
        assert result
        assert result.orderDescription
        assert result.transactionIds
    }


    @Test
    void testDeleteOrder() {
        mockHttpCallForDeletion()
        def result = privateKrakenClient.deleteOrder("transactionId1")
        assert result
        assert result.count
    }

    @Test
    void validateOrder() {
        def marketTrade = new MarketTrade(pair: "XBTUSD", buyOrSell: "buy", amount: 0)
        assertThrows(KrakenRequestException, () -> privateKrakenClient.validateTrade(marketTrade))
    }

    private void mockHttpCallForOrders(String closedOrOpen) {
        String responseJson = gson.toJson([result: ["${closedOrOpen}": ["transactionId1": [status   : closedOrOpen, descr: [pair: "USDXBT"],
                                                                                           vol      : 10, vol_exec: 10, price: 100, cost: 1, fee: 1,
                                                                                           stopprice: 0, limitprice: 0,
                                                                                           opentm   : new Date().getTime() / 1000]]]])

        String urlSuffix = closedOrOpen == "closed" ? "ClosedOrders" : "OpenOrders"

        privateKrakenClient.httpClient = [post: { url, formData, headersMap ->
            assert url == "https://api.kraken.com/0/private/$urlSuffix"
            assert formData
            return new HeadersHttpResponse(responseJson, [:])
        }] as HttpClient
    }

    private void mockHttpCallForAccountBalance() {
        String responseJson = gson.toJson([result: ["XXBT": 100, "ADA": 1000]])
        privateKrakenClient.httpClient = [post: { url, formData, headersMap ->
            assert url == "https://api.kraken.com/0/private/Balance"
            assert formData
            return new HeadersHttpResponse(responseJson, [:])
        }] as HttpClient
    }

    private void mockHttpCallForTrade() {
        String responseJson = gson.toJson(result: [txid: ["transactionId1"], descr: [order: "success"]])

        privateKrakenClient.httpClient = [post: { url, formData, headersMap ->
            assert url == "https://api.kraken.com/0/private/AddOrder"
            assert formData.startsWith("pair=XBTUSD&type=buy")
            return new HeadersHttpResponse(responseJson, [:])
        }] as HttpClient
    }

    private void mockHttpCallForDeletion() {
        String responseJson = gson.toJson(result: [count: 1.0])

        privateKrakenClient.httpClient = [post: { url, formData, headersMap ->
            assert url == "https://api.kraken.com/0/private/CancelOrder"
            assert formData
            return new HeadersHttpResponse(responseJson, [:])
        }] as HttpClient
    }
}
