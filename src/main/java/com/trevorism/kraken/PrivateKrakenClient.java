package com.trevorism.kraken;

import com.trevorism.kraken.model.AssetBalance;
import com.trevorism.kraken.model.DateRange;
import com.trevorism.kraken.model.Order;
import com.trevorism.kraken.model.trade.CancelOrderResult;
import com.trevorism.kraken.model.trade.Trade;
import com.trevorism.kraken.model.trade.TradeResult;

import java.util.List;
import java.util.Set;

public interface PrivateKrakenClient {

    Set<AssetBalance> getAccountBalances();

    //returns up to the last 50 orders sorted from latest order before the end date
    List<Order> getClosedOrders(DateRange dateRange);

    //returns up to the last 50 orders sorted from latest order before the end date
    List<Order> getOpenOrders(DateRange dateRange);

    TradeResult createOrder(Trade tradeInfo);

    CancelOrderResult deleteOrder(String transactionId);
}
