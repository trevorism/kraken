package com.trevorism.kraken.impl

import com.google.gson.Gson
import com.trevorism.http.headers.HeadersBlankHttpClient
import com.trevorism.http.headers.HeadersHttpClient
import com.trevorism.http.util.ResponseUtils
import com.trevorism.kraken.PrivateKrakenClient
import com.trevorism.kraken.error.KrakenRequestException
import com.trevorism.kraken.model.Asset
import com.trevorism.kraken.model.AssetBalance
import com.trevorism.kraken.util.AssetCache
import com.trevorism.kraken.util.KrakenSignature
import org.apache.http.client.methods.CloseableHttpResponse

class DefaultPrivateKrakenClient implements PrivateKrakenClient {

    private static final String API_PREFIX = "https://api.kraken.com"

    private static final double EPSILON = 0.0001d

    private final Gson gson = new Gson()
    private final HeadersHttpClient httpClient = new HeadersBlankHttpClient()
    private final Properties properties

    private final String apiKey
    private final String apiSecret

    DefaultPrivateKrakenClient() {
        this("secrets.properties")
    }

    DefaultPrivateKrakenClient(String propertiesFileName) {
        properties = new Properties()
        properties.load(DefaultPrivateKrakenClient.class.getClassLoader().getResourceAsStream(propertiesFileName) as InputStream)

        this.apiKey = properties.getProperty("apiKey")
        this.apiSecret = properties.getProperty("apiSecret")
    }

    DefaultPrivateKrakenClient(String apiKey, String apiSecret) {
        this.apiKey = apiKey
        this.apiSecret = apiSecret
    }

    @Override
    Set<AssetBalance> getAccountBalance() {
        String url = "https://api.kraken.com/0/private/Balance"
        def content = makeKrakenPrivateRequest(url)
        return mapResponseIntoAssetBalances(content)
    }

    private Set<AssetBalance> mapResponseIntoAssetBalances(Map content) {
        if (content.error) {
            throw new KrakenRequestException(content.error.toString())
        }

        def allAssets = AssetCache.INSTANCE.get()
        def values = content.result
        return values.findAll { k, v ->
            Double.valueOf(v) > EPSILON
        }.collect { k, v ->
            Asset asset = allAssets.find { it.krakenName == k }
            new AssetBalance(assetName: asset.assetName, balance: Double.valueOf(v))
        } as Set
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
