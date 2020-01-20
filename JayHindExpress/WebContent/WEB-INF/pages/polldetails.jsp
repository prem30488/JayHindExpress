<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Poll Details</title>
<style>
#chart_wrap {
    position: relative;
    padding-bottom: 100%;
    height: 0;
    overflow:hidden;
}

#chart_div {
    position: absolute;
    top: 0;
    left: 0;
    width:100%;
    height:100%;
}
</style>
<script type="text/javascript" src="js/poll/google.js"></script>
<script type="text/javascript" src="js/poll/jquery.throttledresize.js"></script>
<script type="text/javascript">
google.load("visualization", "1", {packages:["corechart"]});
google.setOnLoadCallback(drawChart);

function drawChart() {
  var data = google.visualization.arrayToDataTable([
    ['Answer', 'Votes'],
    ['Yes',     ${poll.yes}],
    ['No',      ${poll.no}],
    ["Don't know",  ${poll.none}]/* ,
    ['Watch TV', 2],
    ['Sleep',    7] */
  ]);

  var options = {
    title: '${poll.programName}'
  };

  var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
  chart.draw(data, options);
}

$(window).on("throttledresize", function (event) {
    var options = {
        width: '100%',
        height: '100%'
    };

    var data = google.visualization.arrayToDataTable([]);
    drawChart(data, options);
});
</script>
</head>
<body>
<table>
<tbody>
<tr><th style="width:400px;height:400px;">
<div id="chart_wrap"><div id="chart_div"></div></div>
</th></tr>
</tbody>
</table>
</body>
</html>