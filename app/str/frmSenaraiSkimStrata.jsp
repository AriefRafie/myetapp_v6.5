<script type="text/javascript" src="../../library/js/report.js" ></script>
<fieldset>
<legend>
<strong>Maklumat Strata (Skim Bangunan Khas)</strong></legend>
<table width="100%">
	<tr>
		<td>
			
			<table width="100%" border="0" align="center">
				<tr>
					<td align="center">
						<p style="padding: 2pt 4pt 3pt 0%;"><font color="red">Sila masukkan maklumat carian</font></p>
					</td>
				</tr>
			  <tr>
				   <td scope="row" align="right">Negeri</td>
				   <td width="1%" scope="row">:</td>
				   <td>$selectNegeri dan</td>
				</tr>
				<tr>
				   <td scope="row" align="right">Daerah</td>
				   <td width="1%" scope="row">:</td>
				   <td>$selectDaerah dan</td>
				</tr>
				<tr>
                	<td scope="row" align="right">Tahun</td>
				   <td width="1%" scope="row">:</td>
                   <td width="62%">$selectTahunDari<input type="hidden"
				name="id_tahunDari" id="id_tahunDari" value="$!id_tahunDari">&nbsp;&nbsp;&nbsp;hingga&nbsp;&nbsp;&nbsp;$selectTahunHingga<input
				type="hidden" name="id_tahunHingga" id="id_tahunHingga"
				value="$!id_tahunHingga"></td>
				</tr>
				<tr>
				 	<td width="29%" scope="row" align="right">No Lot</td>
				 	<td width="1%" scope="row">:</td>
				 	<td> <input name="paramNoLot" type="text" id="paramNoLot" value="$!paramNoLot" size="10" /> atau</td>
				 </tr>
			  	 <tr>
				 	<td width="29%" scope="row" align="right">No CF</td>
				 	<td width="1%" scope="row">:</td>
				 	<td><input name="paramNoCF" type="text" id="paramNoCF" value="$!paramNoCF" size="40" /> atau</td>
				 </tr>
				 <tr>
				 	<td width="29%" scope="row" align="right">Nama Pemaju</td>
				 	<td width="1%" scope="row">:</td>
				 	<td><input name="paramNamaPemaju" type="text" id="paramNamaPemaju" value="$!paramNamaPemaju" size="40" /> atau</td>
				 </tr>
				 <tr>
				 	<td width="29%" scope="row" align="right">Nama Skim</td>
				 	<td width="1%" scope="row">:</td>
				 	<td><input name="paramNamaSkim" type="text" id="paramNamaSkim" value="$!paramNamaSkim" size="40" /></td>
				 </tr>
<!--			  	<tr>
				  <td width="29%" scope="row" align="right">Susunan Laporan mengikut</td>
				  <td width="1%" scope="row">:</td>
				   	<td><select name="paramOrderBy">
				   		<option value="D">Daerah</option>
				   		<option value="T">Tarikh CF</option>
				   		<option value="P">Nama Pemaju</option>
				   	</select>
					</td></tr>-->
				
			  	<tr>
			  	  <td width="29%" scope="row" align="right"> </td>
				  <td width="1%" scope="row"></td>
				    <td align="left">
				      <label>
				        <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak Laporan" onclick="javascript:cetak()"/>
				        <input type="button" name="cmd" id="cmdKosongkan" value="Kosongkan" onclick="javascript:kosongkan()" />
				        <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="closeWin()" />
				        </label>    
				    </td>
				  </tr>
			</table>
		</td>
	</tr>
</table>

<input type="hidden" id="paramUserLogin" name="paramUserLogin" value='$!{session.getAttribute("_portal_login")}' >
<input type="hidden" id="rFormat" name="rFormat" value='$!{session.getAttribute("rFormat")}'>
</fieldset>



<script type="text/javascript">
function cetak(){
	//alert(document.${formName}.paramNegeri.value);
	/* var reportfile = "strSenaraiSkimStrata";
	var params = new Array();
	params[0] = "output="+(document.${formName}.rFormat.value).toLowerCase();
	params[1] = "idNegeri="+document.${formName}.paramNegeri.value;
	params[2] = "idDaerah="+document.${formName}.paramDaerah.value;
	params[3] = "idLot="+document.${formName}.paramKodLot.value;
	params[4] = "noLot="+document.${formName}.paramNoLot.value;
	params[5] = "noCf="+document.${formName}.paramNoCF.value;
	params[6] = "pemaju="+document.${formName}.paramNamaPemaju.value;
	params[7] = "skim="+document.${formName}.paramNamaSkim.value;
	params[8] = "orderBy="+document.${formName}.paramOrderBy.value;
	params[9] = "txt_userLogin="+document.${formName}.paramUserLogin.value;
	printReport(reportfile,params); */
	

	var id_tahunDari = document.${formName}.socTahunDari.value;
	var id_tahunHingga = document.${formName}.socTahunHingga.value;
	var idNegeri = document.${formName}.paramNegeri.value;
	var idDaerah = document.${formName}.paramDaerah.value;
//	var idLot = document.${formName}.paramKodLot.value;
	var noLot = document.${formName}.paramNoLot.value;
	var noCf = document.${formName}.paramNoCF.value;
	var pemaju = document.${formName}.paramNamaPemaju.value;
	var skim = document.${formName}.paramNamaSkim.value;
//	var orderBy = document.${formName}.paramOrderBy.value;
	var txt_userLogin = document.${formName}.paramUserLogin.value;
	
	//alert(idNegeri);
	
	//alert("");
	if(id_tahunDari=="")
	{
		id_tahunDari = " ";
	}
	if(id_tahunHingga=="")
	{
		id_tahunHingga = " ";
	}
	if(idNegeri=="")
	{
		idNegeri = " ";
	}
	if(idDaerah=="")
	{
		idDaerah = " ";
	}
	if(noLot=="")
	{
		noLot = " ";
	}
	if(noCf=="")
	{
		noCf = " ";
	}
	if(pemaju=="")
	{
		pemaju = " ";
	}
	if(skim=="")
	{
		skim = " ";
	}
	
	

	var url = "../../servlet/ekptg.report.strata.senaraiSkimBangunanKhas?idNegeri="+idNegeri+"&idDaerah="+idDaerah+"&noLot="+noLot+"&noCf="+noCf+"&pemaju="+pemaju+"&skim="+skim+"&txt_userLogin="+txt_userLogin+"&id_tahunDari="+id_tahunDari+"&id_tahunHingga="+id_tahunHingga+"&dummmy=";		
	
	//alert("sini===="+url);
	 var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function kosongkan() {
	document.${formName}.reset();
	document.${formName}.paramNoLot.value = "";
	document.${formName}.paramNoCF.value = "";
	document.${formName}.paramNamaPemaju.value = "";
	document.${formName}.paramNamaSkim.value = "";
	document.${formName}.submit();
}

function closeWin() {
    window.close();   // Closes the new window
}

function doChangeNegeri(){
	document.${formName}.command.value = "doFilter";
 	document.${formName}.submit();
//  	doAjaxCall${formName}("doFilter");
}

/* function cetak1() {

	var url = "../../servlet/ekptg.report.str.FrmSenaraiSkimStrata";
    var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
} */
</script>