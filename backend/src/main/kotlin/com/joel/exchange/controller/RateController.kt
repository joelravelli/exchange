package com.joel.exchange.controller

import com.joel.exchange.data.entity.RateHistoryLog
import com.joel.exchange.data.repository.RateHistoryLogRepository
import com.joel.exchange.service.VisaService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*


@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("exchange")
class RateController(
    private val rateHistoryLogRepository: RateHistoryLogRepository,
    private val visaService: VisaService
) {
    val formatDate2String: (Date) -> String =
        { date: Date ->
            val ts = Timestamp(date.time)
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            formatter.format(ts)
        }

    @GetMapping("/rates")
    fun getAllRates(
        @RequestParam sourceCurrencyCode: Int,
        @RequestParam destinationCurrencyCode: Int,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") oldestDay: Date,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") newestDay: Date,
    ): Iterable<RateHistoryLog>? = rateHistoryLogRepository.findHistoriesLogWithFilters(
        sourceCurrencyCode,
        destinationCurrencyCode,
        formatDate2String(oldestDay),
        formatDate2String(newestDay)
    )

    @GetMapping("/hello")
    fun getCurrencyHistory() : ResponseEntity<MutableMap<String, Any>> =
        ResponseEntity(visaService.getHelloWord().toMap(), HttpStatus.OK)

    @GetMapping("/rate")
    fun getExchangeRate(
        @RequestParam sourceCurrencyCode: Int,
        @RequestParam destinationCurrencyCode: Int,
        @RequestParam sourceAmount: Float
    ): ResponseEntity<MutableMap<String, Any>> =
        ResponseEntity(
            visaService.getCurrency(sourceCurrencyCode, destinationCurrencyCode, sourceAmount).toMap(),
            HttpStatus.OK
        )
}