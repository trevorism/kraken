package com.trevorism.kraken.util

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import org.apache.commons.codec.binary.Base64;

class KrakenSignature {

    static final String SHA256 = "SHA-256"
    static final String HMAC_SHA512 = "HmacSHA512"

    static String create(String nonce, String postData, String apiSecret, String path) {
        byte[] sha256Data = sha256("${nonce}${postData}")
        byte[] hmacKey = Base64.decodeBase64(apiSecret.toString())
        byte[] hmacMessage = concatByteArrays(stringToBytes(path), sha256Data)
        byte[] sha512 = hmacSha512(hmacKey, hmacMessage)
        return Base64.encodeBase64String(sha512)
    }

    static byte[] hmacSha512(byte[] key, byte[] message) {
        Mac mac = Mac.getInstance(HMAC_SHA512)
        mac.init(new SecretKeySpec(key, HMAC_SHA512))
        return mac.doFinal(message)
    }

    static byte[] sha256(String message) {
        MessageDigest md = MessageDigest.getInstance(SHA256)
        return md.digest(stringToBytes(message))
    }

    static byte[] stringToBytes(String input) {
        return input.getBytes(StandardCharsets.UTF_8)
    }

    static byte[] concatByteArrays(byte[] a, byte[] b) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
        outputStream.write(a)
        outputStream.write(b)
        return outputStream.toByteArray()
    }

}
