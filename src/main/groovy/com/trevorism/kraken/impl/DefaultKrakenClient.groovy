package com.trevorism.kraken.impl

import com.google.gson.Gson;
import com.trevorism.http.headers.HeadersBlankHttpClient;
import com.trevorism.http.headers.HeadersHttpClient
import com.trevorism.http.util.ResponseUtils
import com.trevorism.kraken.KrakenClient
import com.trevorism.kraken.model.Asset;
import org.apache.http.client.methods.CloseableHttpResponse;


class DefaultKrakenClient implements KrakenClient {

    private Gson gson = new Gson()

    @Override
    long serverTime() {
        String url = "https://api.kraken.com/0/public/Time"
        def content = makeKrakenPublicGetRequest(url)
        return content.result.unixtime * 1000

    }

    @Override
    List<Asset> getAssets() {
        String url = "https://api.kraken.com/0/public/Assets"
        def content = makeKrakenPublicGetRequest(url)
        def values = content.result
        return values.collect { k,v ->
            new Asset(assetName: v.altname, decimals: v.decimals, displayDecimals: v.display_decimals)
        }
    }

    private def makeKrakenPublicGetRequest(String url) {
        HeadersHttpClient httpClient = new HeadersBlankHttpClient()
        def headersMap = ["User-Agent": "Mozilla/5.0"]
        CloseableHttpResponse response = httpClient.get(url, headersMap)
        String json = ResponseUtils.getEntity(response)
        return gson.fromJson(json, Map)
    }
}
