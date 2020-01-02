<script type="text/javascript" src="../../library/js/report.js" ></script>
<fieldset>
<legend>
<strong>Maklumat Strata (Skim Bangunan Khas)</strong></legend>
<table width="89%">
	<tr>
		<td>
			
			<table width="100%" height="85" border="0" align="center">
				<tr>
					<td align="center">
						<p style="padding: 2pt 4pt 3pt 0%;"><font color="red">Sila masukkan maklumat carian</font></p>
					</td>
				</tr>
				<tr>
				<tr>
				   <td scope="row" align="right">Negeri</td>
				   <td width="1%" scope="row">:</td>
				   <td>$selectNegeriD</td>
				</tr>
				<tr>
			  	  <td width="29%" scope="row" align="right"> </td>
				  <td width="1%" scope="row"></td>
				    <td align="left">
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
<input type="hidden" id="paramUserLogin" name="paramUserLogin" value='$!{session.getAttribute("_portal_login")}' >
<input type="hidden" id="rFormat" name="rFormat" value='$!{session.getAttribute("rFormat")}'>

</fieldset>
<script type="text/javascript">

function cetak(){
/*	var reportfile = "strStatistikMaklumatStrataIkutDaerahStatus";
	var params = new Array();                                                                                                                                                                        
	params[0] = "output="+(document.${formName}.rFormat.value).toLowerCase();
	params[1] = "idnegeri="+document.${formName}.paramNegeri.value;
	params[2] = "txt_userLogin="+document.${formName}.paramUserLogin.value;*/
	
	
	
	var idNegeri = document.${formName}.paramNegeri.value;
	var txt_userLogin = document.${formName}.paramUserLogin.value;
	
		var url = "../../servlet/ekptg.report.strata.statistikStatusDaerahBangunanKhas?idNegeri="+idNegeri+"&txt_userLogin="+txt_userLogin;		
	
	//alert("sini===="+url);
	 var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function closeWin() {
    window.close();   // Closes the new window
}
</script>