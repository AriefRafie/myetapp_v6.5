
<script type="text/javascript" src="../../../library/js/report.js" ></script>
<link rel="stylesheet" type="text/css" href="../../css/Integrasi.css" />
<fieldset>
<legend>
<strong>Carian</strong>
</legend><table width="100%">
	<tr>
		<td>
			
			<table width="100%" border="0" align="center">
				<tr>
			    <tr>
                    <td width="15%"><div align="right">Tempoh</div></td>
                    <td width="1%">:</td>
                    <td>$selectTahunDari
                      <input type="hidden"
				name="id_tahunDari" id="id_tahunDari" value="$!id_tahunDari" />
&nbsp;&nbsp;&nbsp;hingga&nbsp;&nbsp;&nbsp;$selectTahunHingga
<input
				type="hidden" name="id_tahunHingga" id="id_tahunHingga"
				value="$!id_tahunHingga" /></td>
  				</tr>
			  	<tr>
    <td width="29%"><div align="right"></div></td>
    <td width="1%">&nbsp;</td>
    <td><label>
     <input type="button" name="cmdCetak" id="cmdCetak" value="Jana Laporan" onclick="javascript:cetak($!id)"/>
	 <input type="button" name="cmd" id="cmdKosongkan" value="Kosongkan" onclick="javascript:kosongkan()" />
	 <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="closeWin()" />

    </label></td>
  </tr>
			</table>
		</td>
	</tr>
</table>

<input type="hidden" id="paramUserLogin" name="paramUserLogin" value='$!{session.getAttribute("_portal_login")}' >
<input type="hidden" id="rFormat" name="rFormat" value='$!{session.getAttribute("rFormat")}'>
<input type="hidden" name="id" value="$!id"/>
</fieldset>



<script type="text/javascript">
function cetak(id){
	
	var id_tahunDari = document.${formName}.socTahunDari.value;
	var id_tahunHingga = document.${formName}.socTahunHingga.value;
	var txt_userLogin = document.${formName}.paramUserLogin.value;

	
	var url = "../../servlet/ekptg.report.macgdi.RekodRingkasanRizabNegeri?txt_userLogin="+txt_userLogin+"&id="+id+"&id_tahunDari="+id_tahunDari+"&id_tahunHingga="+id_tahunHingga+"&dummmy=";		

	 var hWnd = window.open(url,'printuser','width=700,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function kosongkan() {
	document.${formName}.reset();
	document.${formName}.socTahunHingga.value = "";
	document.${formName}.socTahunDari.value = "";
	document.${formName}.submit();
}

function closeWin() {
    window.close();   // Closes the new window
}

</script>