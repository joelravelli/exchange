package com.joel.exchange.data

import com.joel.exchange.data.entity.CurrencyCode
import com.joel.exchange.data.entity.RateHistoryLog
import com.joel.exchange.data.repository.CurrencyCodeRepository
import com.joel.exchange.data.repository.RateHistoryLogRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*


@Configuration
class ExchangeDataConfiguration {

    @Bean
    fun databaseInitializer(
        rateHistoryLogRepository: RateHistoryLogRepository,
        currencyCodeRepository: CurrencyCodeRepository
    ) = ApplicationRunner {

        val countryCodeList = arrayListOf<CurrencyCode>(
            CurrencyCode(
                // Brazil
                iso4217Code = 986,
                symbol = "R$",
            ),
            CurrencyCode(
                // Europe
                iso4217Code = 978,
                symbol = "€",
            ),
            CurrencyCode(
                // England
                iso4217Code = 826,
                symbol = "£",
            ),
            CurrencyCode(
                // USA
                iso4217Code = 840,
                symbol = "$",
            )
        )
        currencyCodeRepository.saveAll(countryCodeList)

        val getMockDate: (Int) -> Date =
            { daysAgo: Int ->
                val calendar = Calendar.getInstance()
                calendar.add(Calendar.DATE, -daysAgo)
                calendar.time
            }

        rateHistoryLogRepository.saveAll(
            arrayListOf<RateHistoryLog>(
                RateHistoryLog(
                    paymentCompany = "VISA",
                    currentConversionRate = 3.254f,
                    destinationCurrencyCode = countryCodeList[0],
                    sourceCurrencyCode = countryCodeList[2],
                    createAt = getMockDate(1)
                ),
                RateHistoryLog(
                    paymentCompany = "VISA",
                    currentConversionRate = 0.383f,
                    destinationCurrencyCode = countryCodeList[3],
                    sourceCurrencyCode = countryCodeList[0],
                    createAt = getMockDate(0)
                ),
                RateHistoryLog(
                    paymentCompany = "VISA",
                    currentConversionRate = 0.303f,
                    destinationCurrencyCode = countryCodeList[3],
                    sourceCurrencyCode = countryCodeList[0],
                    createAt = getMockDate(1)
                ),
                RateHistoryLog(
                    paymentCompany = "VISA",
                    currentConversionRate = 0.492f,
                    destinationCurrencyCode = countryCodeList[3],
                    sourceCurrencyCode = countryCodeList[0],
                    createAt = getMockDate(2)
                ),
                RateHistoryLog(
                    paymentCompany = "VISA",
                    currentConversionRate = 0.409f,
                    destinationCurrencyCode = countryCodeList[3],
                    sourceCurrencyCode = countryCodeList[0],
                    createAt = getMockDate(3)
                ),
                RateHistoryLog(
                    paymentCompany = "VISA",
                    currentConversionRate = 0.453f,
                    destinationCurrencyCode = countryCodeList[3],
                    sourceCurrencyCode = countryCodeList[0],
                    createAt = getMockDate(4)
                ),
                RateHistoryLog(
                    paymentCompany = "VISA",
                    currentConversionRate = 0.352f,
                    destinationCurrencyCode = countryCodeList[3],
                    sourceCurrencyCode = countryCodeList[0],
                    createAt = getMockDate(5)
                ),
                RateHistoryLog(
                    paymentCompany = "VISA",
                    currentConversionRate = 0.296f,
                    destinationCurrencyCode = countryCodeList[3],
                    sourceCurrencyCode = countryCodeList[0],
                    createAt = getMockDate(6)
                ),
                RateHistoryLog(
                    paymentCompany = "VISA",
                    currentConversionRate = 0.340f,
                    destinationCurrencyCode = countryCodeList[3],
                    sourceCurrencyCode = countryCodeList[0],
                    createAt = getMockDate(7)
                ),
                RateHistoryLog(
                    paymentCompany = "VISA",
                    currentConversionRate = 0.409f,
                    destinationCurrencyCode = countryCodeList[3],
                    sourceCurrencyCode = countryCodeList[0],
                    createAt = getMockDate(8)
                )
            )
        )
    }
}