		
<style>
	.sizeFontTanah{
		font-size:14px
	}
</style>		

	<fieldset style="width:95%;margin:auto">	
		<table style="width:100%; border-style: solid;">
			<tr>
				<td style="width:50%" valign="top">
					<table style="width:100%">
						<tr class="table_header">
							<td style="font-size:20px;" colspan="3">
								<b>Luas Keseluruhan :</b> <label id="divLuasKeseluruhan">$!luasKeseluruhan</label> <label id="divUnit1">ha</label> 
							</td>
						</tr>
						<tr><td colspan="3">&nbsp;</td></tr>
						<tr><td colspan="3">&nbsp;</td></tr>
						<tr>
							<td class="sizeFontTanah">Tanah Milik</td>
							<td class="sizeFontTanah">:</td>
							<td class="sizeFontTanah"><label id="divLuasMilik">$!luasTanahMilik</label> <label id="divUnit2">ha</label> ($!percentTanahMilik%)</td>
						</tr>
						<tr>
							<td class="sizeFontTanah">Tanah Rizab</td>
							<td class="sizeFontTanah">:</td>
							<td class="sizeFontTanah"><label id="divLuasRizab">$!luasTanahRizab</label> <label id="divUnit3">ha</label> ($!percentTanahRizab%)</td>
						</tr>
						<tr><td colspan="3">&nbsp;</td></tr>
						<tr>
							<td colspan="3">
								$!selectLuas
								<input type="button" name="cmdConvert" id="cmdConvert" value="Convert" onclick="doConvert()" />
							</td>
						</tr>
					</table>
				</td>
				
				<td style="width:50%;text-align:center">
			        <div id="chartContainer" style="height: 300px; width: 100%;"></div>
				</td>
			</tr>
			
		</table>
	</fieldset>	
		
		<table style="width:100%">
			<tr>
				<td style="text-align:center">
					<input type="button" name="cmdLaporanNegeri" id="cmdLaporanNegeri" value="Laporan Mengikut Negeri" onclick="doViewReportNegeri()" />
					<input type="button" name="cmdLaporanKementerian" id="cmdLaporanKementerian" value="Laporan Mengikut Kementerian" onclick="doViewReportKementerian()" />
					<input type="button" name="cmdCarian" id="cmdCarian" value="Carian" onclick="doCarian()" />
				</td>
			</tr>
		</table>
		

  <script type="text/javascript">
  
  window.onload = function () {
	  
	    var luasMilik = '$!percentTanahMilik';
		luasMilik = parseFloat(luasMilik.toString().replace(/[^\d\.eE-]/g,''));
		var luasRizab = '$!percentTanahRizab';
		luasRizab = parseFloat(luasRizab.toString().replace(/[^\d\.eE-]/g,''));
	    
		var chart = new CanvasJS.Chart("chartContainer",
		{
			title:{
				text: "Tanah Pesuruhjaya Tanah Persekutuan",
				fontFamily: "arial black"
			},
			legend: {
				verticalAlign: "bottom",
				horizontalAlign: "center"
			},
			backgroundColor: "#53E2F8",
			theme: "theme1",
			data: [
			{       
				type: "pie",
				indexLabelFontFamily: "verdana",       
				indexLabelFontSize: 18,
				indexLabelFontWeight: "bold",
				startAngle:0,
				indexLabelFontColor: "MistyRose",       
				indexLabelLineColor: "darkgrey", 
				indexLabelPlacement: "inside", 
				toolTipContent: "{name}: {y}%",
				showInLegend: true,
				indexLabel: "#percent%", 
				dataPoints: [
					{  y: luasMilik, name: "Tanah Milik", legendMarkerType: "triangle"},
					{  y: luasRizab, name: "Tanah Rizab", legendMarkerType: "circle"}
				]
			}
			]
		});
		chart.render();
	}
  
  </script>
  <script type="text/javascript" src="../assets/script/canvasjs.min.js"></script>			
			