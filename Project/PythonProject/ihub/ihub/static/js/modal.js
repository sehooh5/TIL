
var showDetails = document.querySelectorAll('#detail');

for (var i = 0; i < showDetails.length; i++) {
showDetails[i].addEventListener('click', function(event){
        var targetId = event.target.dataset.id
        console.log(targetId) 
        axios.get(`/apis/${targetId}/detail/`)
        .then(res => {
            console.log(res.data)

            var download = document.querySelector('#download')
            download.setAttribute('href', `/apis/${targetId}/download/`);
            document.querySelector('.modal-body-div').innerHTML =''
            document.querySelector('#chartdiv').innerHTML =''
            document.querySelector('.modal-title').innerHTML = '<h2>' + res.data.api_name + '</h2>'
            document.querySelector('.modal-body-div').innerHTML = 
            '<div>최종수정일 : ' + res.data.latest_modified_date + '</div>' +
            '<div>저작권자명 : ' + res.data.copyright + '</div>' +
            '<div>' + res.data.copyright_range + '</div>' +
            '<div>다운로드수 : ' + res.data.download_count + '</div>'

            axios.get(`/apis/${targetId}/graph/`)
                .then(res => {
                //var data = JSON.parse(res.data.result);
                var data = res.data.result;
                console.log(res.data.result)
                
                am4core.ready(function() {
                    am4core.useTheme(am4themes_animated);
                    var chart = am4core.create("chartdiv", am4charts.XYChart);
                    chart.paddingRight = 20;
                    chart.data = data;
                    chart.dateFormatter.inputDateFormat = "yyyy-MM-dd HH:mm:ss";

                    var dateAxis = chart.xAxes.push(new am4charts.DateAxis());
                    dateAxis.renderer.minGridDistance = 50;
                    dateAxis.periodChangeDateFormats.setKey("second", "[bold]date");

                    var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
                    valueAxis.tooltip.disabled = true;
                    valueAxis.renderer.disabled = true;

                    var series = chart.series.push(new am4charts.StepLineSeries());
                    series.dataFields.dateX = "date";
                    series.dataFields.valueY = "value";
                    series.tooltipText = "{date} - {status}";
                    series.strokeWidth = 3;
                    
                    chart.cursor = new am4charts.XYCursor();
                    chart.cursor.xAxis = dateAxis;
                    chart.cursor.fullWidthLineX = true;
                    chart.cursor.lineX.strokeWidth = 0;
                    chart.cursor.lineX.fill = chart.colors.getIndex(2);
                    chart.cursor.lineX.fillOpacity = 0.1;
                    
                    chart.scrollbarX = new am4core.Scrollbar();
                    
                }); // end am4core.ready()
                })
        })
    })
}