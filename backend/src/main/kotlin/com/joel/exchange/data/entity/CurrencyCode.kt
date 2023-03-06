package com.joel.exchange.data.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.jetbrains.annotations.NotNull
import java.util.*

@Entity
data class CurrencyCode(
    @Id
    var iso4217Code: Int,

    @NotNull
    @Column(length = 5)
    var symbol: String,
)
