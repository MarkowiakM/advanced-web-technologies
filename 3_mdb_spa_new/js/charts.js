Chart.plugins.register({
    id: 'paddingBelowLegends',
    beforeInit: function (chart, options) {
        chart.legend.afterFit = function () {
            this.height = this.height + 50;
        };
    }
});

//doughnut
var ctxD = document.getElementById("doughnutChart").getContext('2d');
var myLineChart = new Chart(ctxD, {
    type: 'doughnut',
    data: {
        labels: ["Zakopane", "Krynica-Zdrój", "Ustroń", "Karpacz", "Wisła"],
        datasets: [{
            data: [1438198, 1042829, 979590, 820087, 539920],
            backgroundColor: ["#619B8A", "#A1C181", "#FCCA46", "#FE7F2D", "#233D4D"],
            hoverBackgroundColor: ["#395c52", "#5b6e48", "#a1812f", "#8f491b", "#1a2d38"]
        }]
    },

    options: {
        responsive: true,
        legend: {
            // position: 'right',
            // labels: {
            //     padding: 20,
            //     boxWidth: 10
            // }
        },
    }
});