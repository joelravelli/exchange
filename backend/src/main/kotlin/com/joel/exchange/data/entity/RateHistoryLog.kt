package com.joel.exchange.data.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import org.springframework.lang.NonNull
import java.time.Instant
import java.util.*

@Entity
data class RateHistoryLog (
    @Id
    @GeneratedValue
    var id: Long? = null,

    @NotNull
    var paymentCompany: String,

    @NotNull
    var currentConversionRate: Float,

    @OneToOne
    var destinationCurrencyCode: CurrencyCode,

    @OneToOne
    var sourceCurrencyCode: CurrencyCode,

    @Column(updatable = false)
    var createAt: Date? = null,

    var updateAt: Date? = null
)
