import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, of, tap } from 'rxjs';
import { ExchangeHistoryModel } from 'src/app/models/exchange/history.model';
import { ExchangeRateModel } from 'src/app/models/exchange/rate.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class VisaService {
  constructor(private http: HttpClient) {}

  public getExchangeHistoryLogs(
    sourceCurrencyCode: number,
    destinationCurrencyCode: number,
    oldestDay: string,
    newestDay: string
  ): Observable<ExchangeHistoryModel[]> {
    let params = new HttpParams().appendAll({
      sourceCurrencyCode: sourceCurrencyCode,
      destinationCurrencyCode: destinationCurrencyCode,
      oldestDay: oldestDay,
      newestDay: newestDay,
    });

    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    const httpOptions = {
      headers: headers,
      params: params,
    };

    return this.http.get<ExchangeHistoryModel[]>(
      `${environment.baseApiVisaUrl}/exchange/rates`,
      httpOptions
    );
  }

  public calculateExchange(
    sourceCurrencyCode: number,
    destinationCurrencyCode: number,
    sourceAmount: number
  ): Observable<ExchangeRateModel> {
    let params = new HttpParams().appendAll({
      sourceCurrencyCode: sourceCurrencyCode,
      destinationCurrencyCode: destinationCurrencyCode,
      sourceAmount: sourceAmount,
    });

    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    const httpOptions = {
      headers: headers,
      params: params,
    };

    return this.http.get<ExchangeRateModel>(
      `${environment.baseApiVisaUrl}/exchange/rate`,
      httpOptions
    );
  }

}
