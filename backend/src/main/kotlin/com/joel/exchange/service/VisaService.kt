package com.joel.exchange.service

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.net.URL
import java.nio.charset.StandardCharsets
import java.security.KeyStore
import java.util.*
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.KeyManagerFactory
import javax.net.ssl.SSLContext

@Service
class VisaService(
    @Value("\${app.visa.keystorePath}")
    override var keystorePath: String,

    @Value("\${app.visa.keystorePassword}")
    override var keystorePassword: String,

    @Value("\${app.visa.userId}")
    override var userId: String,

    @Value("\${app.visa.password}")
    override var password: String,

    @Value("\${app.visa.server.url}")
    override var visaUrlServer: String

): HttpInterface {

    private var logger: Logger = LogManager.getLogger(VisaService::class.java)

    private fun config(path: String): HttpsURLConnection {

        var connection: HttpsURLConnection

        logger.info("START Two-Way (Mutual) SSL")
        val url = URL(this.visaUrlServer + path)
        connection = url.openConnection() as HttpsURLConnection

        // Make a KeyStore from the PKCS-12 file
        val ks = KeyStore.getInstance("PKCS12")
        FileInputStream(keystorePath).use { fis -> ks.load(fis, keystorePassword.toCharArray()) }

        // Make a KeyManagerFactory from the KeyStore
        val kmf = KeyManagerFactory.getInstance("SunX509")
        kmf.init(ks, keystorePassword.toCharArray())

        // Now make an SSL Context with our Key Manager and the default Trust Manager
        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(kmf.keyManagers, null, null)
        connection.sslSocketFactory = sslContext.socketFactory

        return connection
    }

    fun getCurrency(sourceCurrencyCode: Int, destinationCurrencyCode: Int, sourceAmount: Float): JSONObject {
        try {
            val connection = config("/forexrates/v1/foreignexchangerates")
            val auth = "$userId:$password"
            val encodedAuth = Base64.getEncoder().encode(auth.toByteArray(StandardCharsets.UTF_8))
            val authHeaderValue = "Basic " + String(encodedAuth)

            connection.requestMethod = "POST"
            connection.setRequestProperty("Content-Type", "application/json")
            connection.setRequestProperty("Accept", "application/json")
            connection.setRequestProperty("Authorization", authHeaderValue)

            connection.doOutput = true
            val jsonHttpBody : String = "{\n" +
                    " \"sourceCurrencyCode\": \"$sourceCurrencyCode\",\n" +
                    " \"destinationCurrencyCode\": \"$destinationCurrencyCode\",\n" +
                    " \"sourceAmount\": \"$sourceAmount\"\n" +
                    "}";
            connection.outputStream.use { os ->
                val input: ByteArray = jsonHttpBody.toByteArray(charset("UTF-8"))
                os.write(input, 0, input.size)
            }

            val status = connection.responseCode
            logger.info("Http Status: $status")

            val bfReader: BufferedReader
            if (status == 200) {
                bfReader = BufferedReader(InputStreamReader(connection.inputStream))
            } else {
                bfReader = BufferedReader(InputStreamReader(connection.errorStream))
                logger.error("Two-Way (Mutual) SSL test failed")
            }
            var response: String?
            val content = StringBuffer()
            while (bfReader.readLine().also { response = it } != null) {
                content.append(response)
            }
            bfReader.close()
            connection.disconnect()

            logger.info("Response: $content")
            logger.info("END Two-Way (Mutual) SSL")

            return JSONObject(content.toString())
        } catch (e: Exception) {
            logger.info(e.message)
            return JSONObject("{\"timestamp\":\"${Date()}\",\"message\":\"malformed return\"}")
        }
    }

    fun getHelloWord(): JSONObject {
        try {
            val connection = config("/vdp/helloworld")
            val auth = "$userId:$password"
            val encodedAuth = Base64.getEncoder().encode(auth.toByteArray(StandardCharsets.UTF_8))
            val authHeaderValue = "Basic " + String(encodedAuth)

            connection.requestMethod = "GET"
            connection.setRequestProperty("Content-Type", "application/json")
            connection.setRequestProperty("Accept", "application/json")
            connection.setRequestProperty("Authorization", authHeaderValue)

            val status = connection.responseCode
            logger.info("Http Status: $status")

            val bfReader: BufferedReader
            if (status == 200) {
                bfReader = BufferedReader(InputStreamReader(connection.inputStream))
            } else {
                bfReader = BufferedReader(InputStreamReader(connection.errorStream))
                logger.error("Two-Way (Mutual) SSL test failed")
            }
            var response: String?
            val content = StringBuffer()
            while (bfReader.readLine().also { response = it } != null) {
                content.append(response)
            }
            bfReader.close()
            connection.disconnect()

            logger.info("Response: $content")
            logger.info("END Two-Way (Mutual) SSL")

            return JSONObject(content.toString())
        } catch (n: Exception) {
            return JSONObject("{\"timestamp\":\"${Date()}\",\"message\":\"malformed return\"}")
        }
    }
}