package com.joel.exchange.data.repository

import com.joel.exchange.data.entity.CurrencyCode
import org.springframework.data.repository.CrudRepository

interface CurrencyCodeRepository : CrudRepository<CurrencyCode, Long> {
}