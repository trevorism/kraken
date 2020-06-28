package com.trevorism.kraken.impl

import com.trevorism.kraken.PrivateKrakenClient
import org.junit.Test

class DefaultPrivateKrakenClientTest {

    @Test
    void testGetAccountBalanceNoArgConstructor() {
        PrivateKrakenClient privateKrakenClient = new DefaultPrivateKrakenClient()
        def balances = privateKrakenClient.getAccountBalance()

        balances.each{
            println it
        }
    }
}
