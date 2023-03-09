package com.joel.exchange

import com.joel.exchange.service.VisaService
import org.json.JSONObject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyFloat
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class VisaServiceTests {

    @Autowired
    private val visaService: VisaService? = null

    // Unit Test
    @Test
    fun `Unit test that should return a JSON object`() {
        val mockVisaService = Mockito.mock(VisaService::class.java)

        Mockito.`when`(mockVisaService.getCurrency(
            sourceCurrencyCode = anyInt(),
            destinationCurrencyCode = anyInt(),
            sourceAmount = anyFloat()
        )).thenReturn(JSONObject("{\"conversionRate\": \"0.2651200\", \"destinationAmount\": \"132.56\"}"))

        assertEquals(JSONObject("{\"conversionRate\": \"0.2651200\", \"destinationAmount\": \"132.56\"}").toString(),
            mockVisaService.getCurrency(840, 978, 500f).toString()
        )
    }

    // Integration Test
    @Test
    fun `Integration Test should return a JSON object and correct values`() {
        val response = visaService?.getCurrency(986, 978, 500f);
        assertNotNull(response?.get("conversionRate"))
        assertNotNull(response?.get("destinationAmount"))
    }
}