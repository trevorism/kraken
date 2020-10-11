package com.trevorism.kraken.impl

import com.trevorism.kraken.PrivateKrakenClient
import com.trevorism.kraken.error.KrakenRequestException
import org.junit.Test

class DefaultPrivateKrakenClientTest {

    //@Test
    void testGetAccountBalanceNoArgConstructor() {
        PrivateKrakenClient privateKrakenClient = new DefaultPrivateKrakenClient()
        def balances = privateKrakenClient.getAccountBalances()

        println balances

        assert balances
        def first = balances[0]
        assert first
        assert first.assetName
        assert first.balance > DefaultPrivateKrakenClient.EPSILON
    }

    //@Test
    void testGetAccountBalanceWithExpiredKeys() {
        PrivateKrakenClient privateKrakenClient = new DefaultPrivateKrakenClient("secrets.properties.expired")

        try {
            def balances = privateKrakenClient.getAccountBalances()
            assert false
        }
        catch (KrakenRequestException kre){
            assert kre.message == "[EAPI:Invalid key]"
        }
    }

    //@Test
    void testNewThing(){
        def client = new DefaultPrivateKrakenClient()
        println client.getOpenOrders().size()

    }
}
