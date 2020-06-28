package com.trevorism.kraken.impl

import org.junit.Test

class DefaultKrakenClientTest {

    @Test
    void testConstructor(){
        def client = new DefaultKrakenClient()
        assert client.publicKrakenClient
        assert client.privateKrakenClient
    }

    @Test
    void testConstructorNull(){
        def client = new DefaultKrakenClient(new DefaultPrivateKrakenClient("key","secret"))
        assert client.publicKrakenClient
        assert client.privateKrakenClient
    }
}
