package com.trevorism.kraken.util

import org.junit.Test
import org.apache.commons.codec.binary.Base64

class KrakenSignatureTest extends GroovyTestCase {

    @Test
    void testCreate() {
        String signature = KrakenSignature.create("1", "nonce=1", "secret", "/0/private/Balance")
        assert signature == "YL8erVLiKHVoHZUOyKhSZ3Qg5esH6M6hjBOEr/8yvh8O/2aJJ1Qxg1itY62OUqySq5a2Z6toPKkdT2fhyiF+uA=="
    }

    @Test
    void testHmacSha512() {
        byte[] sha256Data = KrakenSignature.stringToBytes("message")
        byte[] hmacKey = Base64.decodeBase64("secret")

        def bytes = KrakenSignature.hmacSha512(hmacKey, sha256Data)
        def resultAsString = Base64.encodeBase64String(bytes)
        assert resultAsString == "mqMMkExSHxcEeVR9eJ0djOc35fd1hLlckCHCWmwdxAKsvuTYJwu2zRvL2CrntSjeExdz0q348Hw4TC8TXIGUkQ=="
    }

    @Test
    void testSha256() {
        def bytes = KrakenSignature.sha256("nonce=1")
        def resultAsString = Base64.encodeBase64String(bytes)

        assert resultAsString == "iVdcOzbRzdiPHejLqIJ0jXKKdL8Q+mrIhX0sR3Mdko4="

    }

    @Test
    void testConcatByteArrays() {
        byte [] byteArr1 = [12,15].toArray()
        byte [] byteArr2 = [76,45].toArray()

        def result = KrakenSignature.concatByteArrays(byteArr1, byteArr2)
        assert result == [12,15,76,45].toArray()

        result = KrakenSignature.concatByteArrays(byteArr2, byteArr1)
        assert result == [76,45,12,15].toArray()

    }
}
