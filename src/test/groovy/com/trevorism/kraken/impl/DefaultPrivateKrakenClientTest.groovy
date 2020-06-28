package com.trevorism.kraken.impl

import com.trevorism.kraken.PrivateKrakenClient
import org.junit.Test

class DefaultPrivateKrakenClientTest {

    @Test
    void testGetAccountBalanceNoArgConstructor() {
        PrivateKrakenClient privateKrakenClient = new DefaultPrivateKrakenClient()
        def balances = privateKrakenClient.getAccountBalance()

        assert balances
        def first = balances[0]
        assert first
        assert first.assetName
        assert first.balance > DefaultPrivateKrakenClient.EPSILON

    }
}
