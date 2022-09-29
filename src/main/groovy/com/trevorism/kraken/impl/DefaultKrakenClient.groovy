package com.trevorism.kraken.impl

import com.trevorism.kraken.KrakenClient
import com.trevorism.kraken.PrivateKrakenClient
import com.trevorism.kraken.PublicKrakenClient
import com.trevorism.kraken.model.*
import com.trevorism.kraken.model.trade.CancelOrderResult
import com.trevorism.kraken.model.trade.Trade
import com.trevorism.kraken.model.trade.TradeResult

import java.time.Duration

class DefaultKrakenClient implements KrakenClient {

    private PublicKrakenClient publicKrakenClient = new DefaultPublicKrakenClient()
    private PrivateKrakenClient privateKrakenClient

    DefaultKrakenClient() {
        this(new DefaultPrivateKrakenClient())
    }

    DefaultKrakenClient(PrivateKrakenClient privateKrakenClient) {
        this.privateKrakenClient = privateKrakenClient
    }

    @Override
    long serverTime() {
        return publicKrakenClient.serverTime()
    }

    @Override
    List<Asset> getAssets() {
        return publicKrakenClient.getAssets()
    }

    @Override
    List<AssetPair> getAssetPairs() {
        return publicKrakenClient.getAssetPairs()
    }

    @Override
    Price getCurrentPrice(String assetPair) {
        return publicKrakenClient.getCurrentPrice(assetPair)
    }

    @Override
    List<Candle> getCandles(String assetPair, Duration duration = ValidCandleDurations.DAY) {
        return publicKrakenClient.getCandles(assetPair, duration)
    }

    @Override
    Set<AssetBalance> getAccountBalances() {
        privateKrakenClient.getAccountBalances()
    }

    @Override
    List<Order> getClosedOrders(DateRange dateRange) {
        privateKrakenClient.getClosedOrders(dateRange)
    }

    @Override
    List<Order> getOpenOrders(DateRange dateRange) {
        privateKrakenClient.getOpenOrders(dateRange)
    }

    @Override
    TradeResult createOrder(Trade tradeInfo) {
        privateKrakenClient.createOrder(tradeInfo)
    }

    @Override
    CancelOrderResult deleteOrder(String transactionId) {
        privateKrakenClient.deleteOrder(transactionId)
    }
}
