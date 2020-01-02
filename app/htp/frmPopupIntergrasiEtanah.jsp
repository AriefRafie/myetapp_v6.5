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
    <td><fieldset>
      <legend><strong>MAKLUMAT HAKMILIK</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
      #foreach ($beanMaklumatHakmilik in $BeanMaklumatHakmilik)
      <input type="hidden" name="peganganHakmilik" value="$beanMaklumatHakmilik.peganganHakmilik"/>
      <input type="hidden" name="kodKementerian" value="$beanMaklumatHakmilik.kodKementerian"/>
      <input type="hidden" name="namaKementerian" value="$beanMaklumatHakmilik.namaKementerian"/>
      <input type="hidden" name="kodAgensi" value="$beanMaklumatHakmilik.kodAgensi"/>
      <input type="hidden" name="namaAgensi" value="$beanMaklumatHakmilik.namaAgensi"/>
      <input type="hidden" name="kegunaanTanah" value="$beanMaklumatHakmilik.kegunaanTanah"/>
        <tr>
          <td width="100%" valign="top"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
              <tr>
                <td width="37%" align="right" valign="top">ID HAKMILIK :</td>
                <td width="63%" valign="top"><span class="style1">$beanMaklumatHakmilik.peganganHakmilik</span></td>
              </tr>
              <tr>
                <td align="right" valign="top">KEMENTERIAN:</td>
                <td><span class="style1">$beanMaklumatHakmilik.namaKementerian</span></td>
              </tr>
              <tr>
                <td align="right" valign="top">AGENSI:</td>
                <td><span class="style1">$beanMaklumatHakmilik.namaAgensi</span></td>
              </tr>
              <tr>
                <td width="37%" align="right" valign="top">KEGUNAAN TANAH :</td>
                <td width="63%" rowspan="2" valign="top"><span class="style1">$beanMaklumatHakmilik.kegunaanTanah</span></td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
            </table></td>
        </tr>
        #end
      </table>      
      </fieldset></td>
  </tr>
  <tr>
    <td align="center">
    <input type="button" name="cmdTutup" id="cmdTutup" value="Hantar" onClick="hantar()">
    <input type="button" name="cmdTutup" id="cmdTutup" value="Tutup" onClick="tutup()">
    </td>
  </tr>
</table>
<script>
function hantar() {	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.hitButt.value = "hantar";
	document.${formName}.submit();
}
function tutup() {	
	window.close;
}
</script>
