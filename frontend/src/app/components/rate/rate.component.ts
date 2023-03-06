import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { VisaService } from 'src/app/services/visa/visa.service';
import { ExchangeRateDto } from './dto/exchangeRate.dto';

@Component({
  selector: 'app-rate',
  templateUrl: './rate.component.html',
  styleUrls: ['./rate.component.css'],
})
export class RateComponent implements OnInit {

  exchangeRateForm!: FormGroup;
  showResults: boolean = false;
  resultData?: any;

  constructor(private visaService: VisaService,
    private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.createForm();
  }

  onChange(UpdatedValue: any): void {
    //console.log(UpdatedValue); // Live debug
  }

  createForm() {
    this.exchangeRateForm = this.formBuilder.group({
      sourceCurrencyCode: [Validators.required],
      destinationCurrencyCode: [Validators.required],
      sourceAmount: [Validators.required, Validators.min(1)],
    })

    this.exchangeRateForm.get('sourceCurrencyCode')?.setValue(null);
    this.exchangeRateForm.get('destinationCurrencyCode')?.setValue(null);
    this.exchangeRateForm.get('sourceAmount')?.setValue(null);
  }

  get getSourceCurrencyCode() { return this.exchangeRateForm.get('sourceCurrencyCode'); }
  get getDestinationCurrencyCode() { return this.exchangeRateForm.get('destinationCurrencyCode'); }
  get getSourceAmount() {return this.exchangeRateForm.get('sourceAmount'); }

  onClickSubmit(formData: ExchangeRateDto) {
    console.log(formData);
    this.calculateExchange(formData);
  }

  calculateExchange(formData: ExchangeRateDto) {
    this.visaService
      .calculateExchange(
        Number(formData.sourceCurrencyCode),
        Number(formData.destinationCurrencyCode),
        Number(formData.sourceAmount)
      )
      .subscribe({
        next: (res: any ) => {
          console.log(res);
          this.showResults = true;
          this.resultData = res;
        },
        error: (e) => console.error(e),
      });
  }
}
