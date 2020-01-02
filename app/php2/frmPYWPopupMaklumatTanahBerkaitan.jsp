<style type="text/css">
<!--
#parse("css/eTapp_PHP.css")
-->
</style>
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT TANAH</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="50%" valign="top"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
              #foreach ($beanMaklumatTanah in $BeanMaklumatTanah)
              <tr>
                <td width="37%" align="right">PEGANGAN HAKMILIK :</td>
                <td width="63%"><font color="blue">$beanMaklumatTanah.peganganHakmilik</font>
                  <input type="hidden" name="actionPopup"/>
                  <input type="hidden" name="hitButton"/>
                  <input type="hidden" name="idHakmilikAgensi" id="idHakmilikAgensi" value="$beanMaklumatTanah.idHakmilikAgensi"/>
                  <input type="hidden" name="idHakmilikSementara" id="idHakmilikSementara" value="$beanMaklumatTanah.idHakmilikSementara"/></td>
              </tr>
              <tr>
                <td align="right">NO. LOT :</td>
                <td><font color="blue">$beanMaklumatTanah.lot</font></td>
              </tr>
              <tr>
                <td align="right">LUAS  :</td>
                <td><font color="blue">$beanMaklumatTanah.luas</font></td>
              </tr>
              <tr>
                <td align="right">NO. HAKMILIK :</td>
                <td><font color="blue">$beanMaklumatTanah.hakmilik</font></td>
              </tr>
              <tr>
                <td align="right">NO. WARTA :</td>
                <td><font color="blue">$beanMaklumatTanah.noWarta</font></td>
              </tr>
              <tr>
                <td align="right">TARIKH WARTA :</td>
                <td><font color="blue">$beanMaklumatTanah.tarikhWarta</font></td>
              </tr>
              <tr>
                <td align="right">MUKIM :</td>
                <td><font color="blue">$beanMaklumatTanah.mukim</font></td>
              </tr>
              <tr>
                <td align="right">DAERAH :</td>
                <td><font color="blue">$beanMaklumatTanah.daerah</font></td>
              </tr>
              <tr>
                <td align="right">NEGERI :</td>
                <td><font color="blue">$beanMaklumatTanah.negeri</font></td>
              </tr>
              <tr>
                <td align="right">KATEGORI TANAH :</td>
                <td><font color="blue">$beanMaklumatTanah.kategoriTanah</font></td>
              </tr>
              <tr>
                <td align="right">SUBKATEGORI TANAH :</td>
                <td><font color="blue">$beanMaklumatTanah.subKategoriTanah</font></td>
              </tr>
            </table></td>
          <td width="50%" valign="top"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
              <tr>
                <td width="37%" align="right" valign="top">SYARAT NYATA :</td>
                <td width="63%" rowspan="3" valign="top"><font color="blue">$beanMaklumatTanah.syarat</font></td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right" valign="top">SEKATAN KEPENTINGAN :</td>
                <td rowspan="3" valign="top"><font color="blue">$beanMaklumatTanah.sekatan</font></td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right" valign="top">KEGUNAAN TANAH :</td>
                <td rowspan="3" valign="top"><font color="blue">$beanMaklumatTanah.kegunaanTanah</font></td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right" valign="top">KEMENTERIAN :</td>
                <td valign="top"><font color="blue">$beanMaklumatTanah.kementerian</font></td>
              </tr>
              <tr>
                <td align="right" valign="top">AGENSI :</td>
                <td><font color="blue">$beanMaklumatTanah.agensi</font></td>
              </tr>
              #end
            </table></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td align="center"><input type="button" name="cmdPilih" id="cmdPilih" value="Pilih Tanah" onClick="pilihTanah()">
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()">
    </td>
  </tr>
</table>
<script>
function kembali() {	
	document.${formName}.actionPopup.value = "";
	document.${formName}.submit();
}
function pilihTanah() {
	document.${formName}.hitButton.value = "simpan";
	document.${formName}.submit();
}
</script>
