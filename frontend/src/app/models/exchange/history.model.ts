export class ExchangeHistoryModel {
    id?: number;
    paymentCompany?: string;
    currentConversionRate?: number;
    destinationCurrencyCode?: CurrencyCode;
    sourceCurrencyCode?: CurrencyCode;
    createAt?: number;
    updateAt?: number
}

export class CurrencyCode {
    iso4217Code?: number;
    symbol?: string;
}