<script type="text/javascript" src="../../library/js/report.js" ></script>
<fieldset>
<legend>
<strong>Susunan Laporan</strong></legend>
<table width="100%">
	<tr>
		<td>
			
			<table width="100%" border="0" align="center">
				<tr>
					<td align="center">
						<p style="padding: 2pt 4pt 3pt 0%;"><font color="red">Sila pilih susunan laporan</font></p>
					</td>
				</tr>
				<tr>
				   <td align="center">
				   	<select name="paramOrderBy">
				   		<option value="D">Daerah</option>
				   		<option value="T">Tarikh CF</option>
				   		<option value="P">Nama Pemaju</option>
				   	</select>
					</td>
				</tr>
			  	<tr>
				    <td align="center">
				      <label>
				        <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak Laporan" onclick="javascript:cetak()"/>
				        <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="closeWin()" />
				        </label>
				    </td>
				  </tr>
			</table>
		</td>
	</tr>
</table>
<input type="hidden" id="paramNegeri" name="paramNegeri" value="$paramNegeri">
<input type="hidden" id="paramKodLot" name="paramKodLot" value="$paramKodLot">
<input type="hidden" id="paramNoLot" name="paramNoLot" value="$paramNoLot">
<input type="hidden" id="paramNoCF" name="paramNoCF" value="$paramNoCF">
<input type="hidden" id="paramNamaPemaju" name="paramNamaPemaju" value="$paramNamaPemaju">
<input type="hidden" id="paramNamaSkim" name="paramNamaSkim" value="$paramNamaSkim">

<input type="hidden" id="rFormat" name="rFormat" value='$!{session.getAttribute("rFormat")}'>
<input type="hidden" id="paramUserLogin" name="paramUserLogin" value='$!{session.getAttribute("_portal_login")}' >

</fieldset>
<script type="text/javascript">

function cetak(){
	//alert(document.${formName}.paramSort.value);
	
	var reportfile = "strSenaraiSkimStrata";
	var params = new Array();
	params[0] = "output="+(document.${formName}.rFormat.value).toLowerCase();
	params[1] = "idNegeri="+document.${formName}.paramNegeri.value;
	params[2] = "idLot="+document.${formName}.paramKodLot.value;
	params[3] = "noLot="+document.${formName}.paramNoLot.value;
	params[4] = "noCf="+document.${formName}.paramNoCF.value;
	params[5] = "pemaju="+document.${formName}.paramNamaPemaju.value;
	params[6] = "skim="+document.${formName}.paramNamaSkim.value;
	params[7] = "orderBy="+document.${formName}.paramOrderBy.value;
	params[8] = "txt_userLogin="+document.${formName}.paramUserLogin.value;
	
	printReport(reportfile,params);
}

function closeWin() {
    window.close();   // Closes the new window
}
</script>