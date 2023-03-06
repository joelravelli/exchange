package com.joel.exchange.data.repository

import com.joel.exchange.data.entity.CurrencyCode
import com.joel.exchange.data.entity.RateHistoryLog
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface RateHistoryLogRepository : CrudRepository<RateHistoryLog, Long> {
    fun findBySourceCurrencyCode(sourceCurrencyCode: CurrencyCode) : Iterable<RateHistoryLog>

    @Query(
        value = "SELECT * FROM RATE_HISTORY_LOG " +
                "WHERE SOURCE_CURRENCY_CODE_ISO4217CODE = ?1 " +
                "AND DESTINATION_CURRENCY_CODE_ISO4217CODE = ?2 " +
                "AND CREATE_AT >= ?3 AND CREATE_AT <= ?4 ORDER BY CREATE_AT ASC",
        nativeQuery = true
    )
    fun findHistoriesLogWithFilters(
        sourceCurrencyCode: Int,                // ?1
        destinationCurrencyCode: Int,           // ?2
        oldestDay: String,                      // ?3
        newestDay: String                       // ?4
    ): Iterable<RateHistoryLog>?
}