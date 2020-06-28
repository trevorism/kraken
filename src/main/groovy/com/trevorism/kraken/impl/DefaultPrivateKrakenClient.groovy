package com.trevorism.kraken.impl

import com.google.gson.Gson
import com.trevorism.http.headers.HeadersBlankHttpClient
import com.trevorism.http.headers.HeadersHttpClient
import com.trevorism.http.util.ResponseUtils
import com.trevorism.kraken.PrivateKrakenClient
import com.trevorism.kraken.util.KrakenSignature
import org.apache.http.client.methods.CloseableHttpResponse

class DefaultPrivateKrakenClient implements PrivateKrakenClient {

    private static final String API_PREFIX = "https://api.kraken.com"

    private final Gson gson = new Gson()
    private final HeadersHttpClient httpClient = new HeadersBlankHttpClient()
    private final Properties properties

    private String apiKey
    private String apiSecret

    DefaultPrivateKrakenClient() {
        properties = new Properties()
        properties.load(DefaultPrivateKrakenClient.class.getClassLoader().getResourceAsStream("secrets.properties") as InputStream)

        apiKey = properties.getProperty("apiKey")
        apiSecret = properties.getProperty("apiSecret")
    }

    @Override
    void getAccountBalance() {
        HeadersHttpClient httpClient = new HeadersBlankHttpClient()
        String url = "https://api.kraken.com/0/private/Balance"
        println makeKrakenPrivateRequest(url)
    }

    private def makeKrakenPrivateRequest(String url, Map requestData = [:]) {
        String path = url.substring(API_PREFIX.length())
        String nonce = String.valueOf(System.currentTimeMillis() * 1000)
        String formData = buildFormDataString(requestData, nonce)
        def signature = KrakenSignature.create(nonce, formData, apiSecret, path)
        def headersMap = ["User-Agent": "Mozilla/5.0",
                          "Content-Type": "application/x-www-form-urlencoded",
                          "API-Key": apiKey,
                          "API-Sign": signature]

        CloseableHttpResponse response = httpClient.post(url, formData, headersMap)
        String json = ResponseUtils.getEntity(response)
        return gson.fromJson(json, Map)
    }

    private String buildFormDataString(Map requestData, String nonce) {
        StringBuilder builder = new StringBuilder()
        requestData.each { k, v ->
            builder << "${k}=${v}&"
        }
        builder << "nonce=${nonce}"
        return builder.toString()
    }

}
