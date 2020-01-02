<script type="text/javascript" src="../../library/js/jquery-1.3.2.min.js" ></script>
<script type="text/javascript" src="../../fusioncharts/Charts/FusionCharts.js"></script>
<script>var $jquery = jQuery.noConflict();</script>
<div id="chartLocation_msg" align="center" ><font color="blue">Sila Tunggu..</font></div>
<div id="chartLocation" align="center"></div>

<script  type="text/javascript">
$jquery(document).ready(function(){  
	//alert('dalam popup');
	//opener.loadChart();
	var type_chart = window.opener.document.getElementById('type_chart').value;
	var chart_id = window.opener.document.getElementById('chart_id').value;
	var xml = window.opener.document.getElementById('xml').value;
	var chart_h = window.opener.document.getElementById('chart_h').value;
	var chart_w = window.opener.document.getElementById('chart_w').value;
	loadChart(type_chart,chart_id,xml,chart_w,chart_h)
});


function loadChart(type_chart,chart_id,xml,chart_w,chart_h)
{
	 //alert('call loadChart');
	 FusionCharts.printManager.enabled(true);
	 //Doughnut2D.swf
	 var myChart = new FusionCharts("../../fusioncharts/Charts/"+type_chart, chart_id, chart_w, chart_h);
	 var xm = xml;
	 xm = xm.replace(/'/g, '"');
	 myChart.setDataXML(xm);
	 myChart.render('chartLocation');
	 FusionCharts.addEventListener ( 
		  FusionChartsEvents.PrintReadyStateChange, 
		  function (identifier, parameter) {
			if(parameter.ready){ 
			   //alert("Chart is now ready for printing.");
			   //document.getElementById('printButton').disabled = false;
			   document.getElementById('chartLocation_msg').style.display = 'none';			   
			   FusionCharts.printManager.managedPrint();
			   window.close();
			}
	});
}


</script>
