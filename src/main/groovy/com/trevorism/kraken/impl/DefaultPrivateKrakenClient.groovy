package com.trevorism.kraken.impl

import com.google.gson.Gson
import com.trevorism.http.headers.HeadersBlankHttpClient
import com.trevorism.http.headers.HeadersHttpClient
import com.trevorism.http.util.ResponseUtils
import com.trevorism.kraken.PrivateKrakenClient
import com.trevorism.kraken.error.KrakenRequestException
import com.trevorism.kraken.model.Asset
import com.trevorism.kraken.model.AssetBalance
import com.trevorism.kraken.model.DateRange
import com.trevorism.kraken.model.Order
import com.trevorism.kraken.util.AssetCache
import com.trevorism.kraken.util.KrakenSignature
import com.trevorism.secure.PropertiesProvider
import org.apache.http.client.methods.CloseableHttpResponse
import org.jasypt.util.text.StrongTextEncryptor

import java.time.Instant
import java.time.LocalDateTime
import java.time.temporal.TemporalAmount

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

    //The code using this must inject the appropriate files..
    DefaultPrivateKrakenClient(String propertiesFileName) {
        properties = new Properties()
        properties.load(DefaultPrivateKrakenClient.class.getClassLoader().getResourceAsStream(propertiesFileName) as InputStream)
        StrongTextEncryptor encryptor = createEncryptor()

        this.apiKey = properties.getProperty("apiKey")
        this.apiSecret = encryptor.decrypt(properties.getProperty("apiSecret"))
    }

    DefaultPrivateKrakenClient(String apiKey, String apiSecret) {
        this.apiKey = apiKey
        this.apiSecret = apiSecret
    }

    @Override
    Set<AssetBalance> getAccountBalances() {
        String url = "https://api.kraken.com/0/private/Balance"
        def content = makeKrakenPrivateRequest(url)
        return mapResponseIntoAssetBalances(content)
    }

    @Override
    List<Order> getClosedOrders(DateRange dateRange) {
        String url = "https://api.kraken.com/0/private/ClosedOrders"
        LinkedHashMap<String, Long> inputMap = createInputMapFromDateRange(dateRange)


        def content = makeKrakenPrivateRequest(url, inputMap)
        return mapResponseIntoOrders(content)
    }

    @Override
    List<Order> getOpenOrders(DateRange dateRange) {
        String url = "https://api.kraken.com/0/private/OpenOrders"
        LinkedHashMap<String, Long> inputMap = createInputMapFromDateRange(dateRange)

        def content = makeKrakenPrivateRequest(url, inputMap)
        return mapResponseIntoOrders(content)
    }

    private LinkedHashMap<String, Long> createInputMapFromDateRange(DateRange dateRange) {
        Map<String, Long> inputMap = [:]
        if (dateRange?.startDate != null)
            inputMap.put("start", dateRange.startDate.getTime() / 1000)
        if (dateRange?.endDate != null)
            inputMap.put("end", dateRange.endDate.getTime() / 1000)
        return inputMap
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

    private static StrongTextEncryptor createEncryptor() {
        String encryptionKey = new PropertiesProvider("encryption.properties").getProperty("encryptionKey")
        StrongTextEncryptor encryptor = new StrongTextEncryptor()
        encryptor.setPassword(encryptionKey)
        return encryptor
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

    private List<Order> mapResponseIntoOrders(Map content) {
        if (content.error) {
            throw new KrakenRequestException(content.error.toString())
        }

        def values = content.result.closed
        return values.collect { k, v ->
            def thing = convertDataIntoOrder(v)
            println thing
            return thing
        }

    }

    Order convertDataIntoOrder(Map data) {
        Date openTime = data.opentm == 0 ? null : new Date((long)(data.opentm * 1000))
        Date closedTime = data.closetm == 0 ? null : new Date((long)(data.closetm * 1000))
        Date startTime = data.starttm == 0 ? null : new Date((long)(data.starttm * 1000))
        Date expireTime = data.expiretm == 0 ? null : new Date((long)(data.expiretm * 1000))
        Double price = Double.valueOf(data.price) == 0 ? Double.valueOf(data.descr.price) : Double.valueOf(data.price)

       return new Order(pair: data.descr.pair, type:data.descr.type, orderType: data.descr.ordertype,
               status: data.status, reason: data.reason, amount: Double.valueOf(data.vol), amountExecuted: Double.valueOf(data.vol_exec),
       cost: Double.valueOf(data.cost), fee: Double.valueOf(data.fee), price: price, stopPrice: Double.valueOf(data.stopprice), limitPrice: Double.valueOf(data.limitprice),
       leverage: data.descr.leverage, openDate: openTime, closedDate: closedTime, startDate: startTime, expireDate: expireTime,
       misc: data.misc, oflags: data.oflags)
    }
}
