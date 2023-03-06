import { ChartOptions } from "./graph.component";

export class GraphTemplate {

    static baseConfig : ChartOptions = {
        series: [
          {
            name: "Exchange Rate",
            data: [1, 1, 1, 1, 1, 1, 1]
          }
        ],
        chart: {
          height: 350,
          type: "bar"
        },
        plotOptions: {
          bar: {
            borderRadius: 6,
            dataLabels: {
              position: "top" // top, center, bottom
            }
          },
  
        },
        theme: {
          mode: 'dark'
        },
        dataLabels: {
          enabled: true,
          formatter: function(val) {
            return val + "";
          },
          offsetY: -20,
          style: {
            fontSize: "12px",
            colors: ["#FFFFFF"]
          }
        },
        xAxis: {
          categories: [
            "Jan",
            "Feb",
            "Mar",
            "Apr",
            "May",
            "Jun",
            "Jul"
          ],
          position: "top",
          labels: {
            offsetY: -8
          },
          axisBorder: {
            show: false
          },
          axisTicks: {
            show: false
          },
          crosshairs: {
            fill: {
              type: "gradient",
              gradient: {
                colorFrom: "#D8E3F0",
                colorTo: "#BED1E6",
                stops: [0, 100],
                opacityFrom: 0.4,
                opacityTo: 0.5
              }
            }
          },
          tooltip: {
            enabled: true,
            offsetY: -35
          }
        },
        fill: {
          colors: ['#7b1fa2'],
          type: "solid",
          gradient: {
            shade: "dark",
            type: "horizontal",
            shadeIntensity: 0.25,
            gradientToColors: undefined,
            inverseColors: true,
            opacityFrom: 1,
            opacityTo: 1,
            stops: [50, 0, 100, 100]
          }
        },
        yAxis: {
          axisBorder: {
            show: false
          },
          axisTicks: {
            show: false
          },
          labels: {
            show: false,
            formatter: function(val) {
              return val + "";
            }
          }
        },
        title: {
          text: "Exchange rate from Real to Dollar for the last 7 days",
          floating: false,
          offsetY: 323,
          align: "center",
          style: {
            color: "#FFFFFF"
          }
        }
    };
}