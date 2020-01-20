<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<script type="text/javascript" src="../js/poll/google.js"></script>
<script type="text/javascript" src="../js/poll/jquery.throttledresize.js"></script>
<script type="text/javascript">
google.load("visualization", "1", {packages:["corechart"]});
google.setOnLoadCallback(drawChart);

function drawChart() {
  var data = google.visualization.arrayToDataTable([
    ['Answer', 'Votes'],
    ['Yes',     ${selectProgram.yes}],
    ['No',      ${selectProgram.no}],
    ["Don't know",  ${selectProgram.none}]/* ,
    ['Watch TV', 2],
    ['Sleep',    7] */
  ]);

  var options = {
    title: '${selectProgram.programName}'
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

<center>
	<h1>Poll Details</h1>
	</center>
<br/><br/>
<a href="viewPolls" style="color:black">View Polls</a> -> Poll Details
<br/><br/>
<center>
		<table style="width: 100%;font-size: 14px;" align="center" class="table">
		
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Poll Title / Question
			</th>
			 <td>
				${selectProgram.programName}
			</td> 
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Program Created Date
			</th>
			 <td>
				<fmt:formatDate value="${selectProgram.createdDate}" pattern="dd-MMM-yyyy hh:mm:ss" />
			</td>  
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Program Location
			</th>
			 <td>
				${selectProgram.location}
			</td>  
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Posted  By
			</th>
			 <td>
				${selectProgram.posted_by}
			</td>  
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Total View
			</th>
			 <td>
				${selectProgram.frequency}
			</td>  
		</tr>
		<tr id="name" class="odd" style="width:200px;height: 35px;">
			<th>
				Preview of Results
			</th>
			 <td>
				<div id="chart_wrap"><div id="chart_div"></div></div>
			</td>  
		</tr>
		</table>
</center>
		