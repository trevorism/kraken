package com.trevorism.kraken.impl

import com.trevorism.kraken.PrivateKrakenClient
import org.junit.Test

class DefaultPrivateKrakenClientTest {

    @Test
    void testGetAccountBalance() {
        PrivateKrakenClient privateKrakenClient = new DefaultPrivateKrakenClient("key", "secret")
        privateKrakenClient.getAccountBalance()
    }

    @Test
    void testGetAccountBalanceNoArgConstructor() {
        PrivateKrakenClient privateKrakenClient = new DefaultPrivateKrakenClient()
        privateKrakenClient.getAccountBalance()
    }
}
