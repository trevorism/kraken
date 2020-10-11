package com.trevorism.kraken;

import com.trevorism.kraken.model.AssetBalance;
import com.trevorism.kraken.model.DateRange;
import com.trevorism.kraken.model.Order;

import java.util.List;
import java.util.Set;

public interface PrivateKrakenClient {

    Set<AssetBalance> getAccountBalances();

    //returns up to the last 50 orders sorted from latest order before the end date
    List<Order> getClosedOrders(DateRange dateRange);

    //returns up to the last 50 orders sorted from latest order before the end date
    List<Order> getOpenOrders(DateRange dateRange);
}
