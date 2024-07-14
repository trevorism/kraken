package com.trevorism.kraken.model.trade

import org.junit.jupiter.api.Test

class TradeTest {

    @Test
    void testLimitToString(){
        LimitTrade limitTrade = new LimitTrade(pair:  "XBTUSD", buyOrSell: "buy", amount: 1, price: 400d)
        assert "buy 1.0 XBTUSD limit @ 400.0" == limitTrade.toString()
    }

    @Test
    void testMarketToString(){
        MarketTrade marketTrade = new MarketTrade(pair:  "XBTUSD", buyOrSell: "buy", amount: 1)
        assert "buy 1.0 XBTUSD market" == marketTrade.toString()
    }
    @Test
    void testStopLossToString(){
        StopLossTrade stopLossTrade = new StopLossTrade(pair:  "XBTUSD", buyOrSell: "buy", amount: 1, price: 400d)
        assert "buy 1.0 XBTUSD stop-loss @ 400.0" == stopLossTrade.toString()
    }
}
