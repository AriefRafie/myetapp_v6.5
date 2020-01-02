<p>
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
	<input type="hidden" name="hitButton">
	<input type="hidden" name="idWarta">
</p>
<fieldset>
	<legend>
		<strong>Maklumat Tanah Rizab Melayu</strong>
	</legend>
	#set ($Senaraifail = $session.getAttribute("_portal_moduleVectorInternalFail")) #set ($startno =
	$startnoInt.intValue()) #set ($i = $startno) #set ($total = $totalInt.intValue())
	<table width="100%">
		<td width="100%">#parse("app/utils/record_paging.jsp")</td>
	</table>
	<table width="100%">
	<tr>
		<td width="10" valign="top">
			<div align="right"><h4>Negeri</h4></div>
		</td>
		<td width="1%" scope="row">:</td>
		<td id="namaNegeri">$namaNegeri</td>
		
		<td>
			<div align="right"><h4>Luas Negeri</h4></div>
		</td>
		<td width="1%" scope="row">:</td>
		<td id="namaNegeri">$luasNegeri</td>
		
	</tr>
	</table>
	<table width="100%">
		<tr class="table_header">
			<td width="3%" align="center" scope="row">
				<strong>Bil.</strong>
			</td>
			<td width="13%" align="center">
				Daerah/Jajahan</strong>
			</td>
			<td width="10%" align="center">
				No.Warta</strong>
			</td>
			<td width="8%" align="center">
				Jenis Warta</strong>
			</td>
			<td width="8%" align="center">
				Status</strong>
			</td>
			<td width="10%" align="center">
				No.Warta Asal</strong>
			</td>
			<td width="8%" align="center">
				No.Warta Ganti</strong>
			</td>
			<td width="10%" align="center">
				Tarikh</strong>
			</td>
			<td width="8%" align="center">
				No.Pelan</strong>
			</td>
			<td width="13%" align="center">
				Bandar/Pekan/Mukim</strong>
			</td>
			<td width="8%" align="center">
				Luas</strong>
			</td>
		</tr>
		#if($ListExcel.size() > 0) #foreach ($result in $ListExcel ) #set($counter = $velocityCount ) #if ( ($counter % 2) ==
		0 ) #set( $row ="row2" ) #else #set( $row = "row1" ) #end
		<tr>
			<td height="20" align="center" class="$row">$result.Bil</td>
			<td class="$row" align="center">$result.namaDaerah</td>
			<td class="$row" align="center">$!result.noWarta</td>
			<td class="$row" align="center">$result.flagJenisWarta</td>
			<td class="$row" align="center">$result.flagStatusWarta</td>
			<td class="$row" align="center">$!result.idTblPdtWartaAsal</td>
			<td class="$row" align="center">$!result.idTblPdtWartaGanti</td>
			<td class="$row" align="center">$result.tarikhWarta</td>
			<td class="$row" align="center">$result.noPelan</td>
			<td class="$row" align="center">$result.namaMukim</td>
			<td class="$row" align="center">$result.luas</td>
		</tr>
		#end #else
		<tr>
			<td colspan="6">Rekod Tidak Dijumpai</td>
		</tr>
		#end
	</table>
	</tr>
	</table>
	<div align="right">
		<input type="button" name="cmdKembaliExcel" id="cmdKembaliExcel" value="Kembali" onclick="javascript:kembaliexcel()" />
	</div>
</fieldset>
<div style="visibility: hidden;">
	<input type="hidden" name="mode" />
	<input type="hidden" name="action" />
</div>

<input type="hidden" id="rFormat" name="rFormat" value='$!{session.getAttribute("rFormat")}'>

<script type="text/javascript">
function kembaliexcel(){
	document.${formName}.hitButton.value = "kembaliexcel";
	/* doAjaxCall${formName}("doCarian"); */
	 document.${formName}.submit();
}
</script>

<!-- /*
#foreach ($result in $ListExcel ) 
		#if ( $result.Luas != '' ) 
			#set( $totalLuas =  $result.Luas ) 
		#end
#end -->