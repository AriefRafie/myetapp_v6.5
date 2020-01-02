<style type="text/css">
<!--
#parse("css/eTapp_HTP.css") .style1 {
 color: #0000FF;
 font-weight: bold;
}
-->
</style>
<input type="hidden" name="hitButt"/>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT CUKAI / BAYARAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="100%" valign="top"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
              <tr>
                <td width="37%" align="right" valign="top">ID HAKMILIK :</td>
                <td width="63%" valign="top"><input name="txtIDHakmilik" id="txtIDHakmilik" type="text" value="$txtIDHakmilik" size="20" maxlength="20" style="text-transform:uppercase;" /></td>
              </tr>
              <tr>
                <td width="37%" align="right" valign="top">TAHUN :</td>
                <td width="63%" valign="top"><input name="txtTahun" id="txtTahun" type="text" value="$txtTahun" size="4" maxlength="4" style="text-transform:uppercase;" /></td>
              </tr>
          </table></td>
        </tr>
      </table>      
      </fieldset></td>
  </tr>
  <tr>
    <td width="37%" align="left">&nbsp;</td>
    <td width="63%" align="left"><input type="button" name="cmdCukai" id="cmdCukai" value="Maklumat Cukai" onclick="getMaklumatCukai()" />
      <input type="button" name="cmdBayaran" id="cmdBayaran" value="Maklumat Bayaran" onclick="getMaklumatBayaran()" /></td>
  </tr>
</table>
<script>
function getMaklumatCukai() {	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.hitButt.value = "getMaklumatCukai";
	document.${formName}.submit();
}
function getMaklumatBayaran() {	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.hitButt.value = "getMaklumatBayaran";
	document.${formName}.submit();
}
</script>
