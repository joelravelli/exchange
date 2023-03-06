import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { ExchangeHistoryModel } from 'src/app/models/exchange/history.model';
import { VisaService } from 'src/app/services/visa/visa.service';
import {
  ApexAxisChartSeries,
  ApexChart,
  ChartComponent,
  ApexDataLabels,
  ApexPlotOptions,
  ApexYAxis,
  ApexTitleSubtitle,
  ApexXAxis,
  ApexFill,
  ApexTheme
} from "ng-apexcharts";
import { GraphTemplate } from './graph.template';
import * as ApexCharts from 'apexcharts';
import * as moment from 'moment';

@Component({
  selector: 'app-graph',
  templateUrl: './graph.component.html',
  styleUrls: ['./graph.component.css']
})
export class GraphComponent implements OnInit, AfterViewInit {

  exchangeHistoryLogs?: ExchangeHistoryModel[];
  @ViewChild("chart") chart!: ChartComponent;
  public chartOptions: Partial<ChartOptions> | undefined;
  graphReady = false;

  constructor(private visaService: VisaService) {
  }

  ngAfterViewInit(): void {
    // this.chartOptions!.xAxis!.categories! = ['Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab', 'Dom'];
    // this.chartOptions!.series![0].data = [2.3, 3.1, 4.0, 10.1, 4.0, 3.6, 3.2];
    // this.chart.updateOptions({
    //   title: {
    //     text: 'new title'
    //   }
    // })
    //this.chart.updateOptions(this.chartOptions, false, true, true);  
  }

  ngOnInit(): void {
    this.getExchangeHistoryLogs();
    this.chartOptions = GraphTemplate.baseConfig;
  }

  getExchangeHistoryLogs() {
    var latestDate = moment().format('YYYY-MM-DD');
    var oldestAte = moment().add(-7, 'day').format('YYYY-MM-DD');
    this.visaService
      .getExchangeHistoryLogs(986, 840, oldestAte, latestDate)
      .subscribe({
        next: (res) => {
          this.exchangeHistoryLogs = res;
          var categories: string[] = [];
          var data: number[] = [];
          res.map(item => { 
            var df = moment(item.createAt).format("YYYY-MM-DD");
            categories.push(df);
            data.push(item.currentConversionRate!);
          });
          this.chartOptions!.xAxis!.categories! = categories;
          this.chartOptions!.series![0].data = data;
          this.chartOptions!.title!.text = `Exchange rate from Real to Dollar for the last ${data.length} days`;
          this.graphReady = true;
        },
        error: (e) => console.error(e)
      });
  }
}

export type ChartOptions = {
  series: ApexAxisChartSeries;
  chart: ApexChart;
  dataLabels: ApexDataLabels;
  plotOptions: ApexPlotOptions;
  yAxis: ApexYAxis;
  xAxis: ApexXAxis;
  fill: ApexFill;
  title: ApexTitleSubtitle;
  theme: ApexTheme;
};