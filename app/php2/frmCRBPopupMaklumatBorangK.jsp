<style type="text/css">
<!--
#parse("css/eTapp_PHP.css")
-->
</style>
<input type="hidden" name="actionPopup"/>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT LOT</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="50%" valign="top"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
              #foreach ($beanMaklumatBorangK in $BeanMaklumatBorangK)
              <tr>
                <td width="37%" align="right">PEGANGAN HAKMILIK :</td>
                <td width="63%"><font color="blue">$beanMaklumatBorangK.peganganHakmilik</font>
                  #set($idPHPBorangK = $beanMaklumatBorangK.idPHPBorangK)                  </td>
              </tr>
              <tr>
                <td align="right">NO. LOT :</td>
                <td><font color="blue">$beanMaklumatBorangK.noLot</font></td>
              </tr>
              <tr>
                <td align="right">LUAS  :</td>
                <td><font color="#0000FF">$beanMaklumatBorangK.luasLot</font></td>
              </tr>
              <tr>
                <td align="right">NO. HAKMILIK :</td>
                <td><font color="blue">$beanMaklumatBorangK.noHakmilik</font></td>
              </tr>
              <tr>
                <td align="right">NO. WARTA :</td>
                <td><font color="blue">$beanMaklumatBorangK.noWarta</font></td>
              </tr>
              <tr>
                <td align="right">TARIKH WARTA :</td>
                <td><font color="blue">$beanMaklumatBorangK.tarikhWarta</font></td>
              </tr>
              <tr>
                <td align="right">MUKIM :</td>
                <td><font color="blue">$beanMaklumatBorangK.mukim</font></td>
              </tr>
              <tr>
                <td align="right">DAERAH :</td>
                <td><font color="blue">$beanMaklumatBorangK.daerah</font></td>
              </tr>
              <tr>
                <td align="right">NEGERI :</td>
                <td><font color="blue">$beanMaklumatBorangK.negeri</font></td>
              </tr>
              <tr>
                <td align="right">KATEGORI TANAH :</td>
                <td><font color="blue">$beanMaklumatBorangK.kategoriTanah</font></td>
              </tr>
              <tr>
                <td align="right">SUBKATEGORI TANAH :</td>
                <td><font color="blue">$beanMaklumatBorangK.subKategoriTanah</font></td>
              </tr>
            </table></td>
          <td width="50%" valign="top"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
              <tr>
                <td width="37%" align="right" valign="top">SYARAT NYATA :</td>
                <td width="63%" rowspan="3" valign="top"><font color="blue">$beanMaklumatBorangK.syarat</font></td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right" valign="top">SEKATAN KEPENTINGAN :</td>
                <td rowspan="3" valign="top"><font color="blue">$beanMaklumatBorangK.sekatan</font></td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right" valign="top">KEGUNAAN TANAH :</td>
                <td rowspan="3" valign="top"><font color="blue">$beanMaklumatBorangK.kegunaanTanah</font></td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right" valign="top">KEMENTERIAN :</td>
                <td valign="top"><font color="blue">$beanMaklumatBorangK.kementerian</font></td>
              </tr>
              <tr>
                <td align="right" valign="top">AGENSI :</td>
                <td><font color="blue">$beanMaklumatBorangK.agensi</font></td>
              </tr>
              #end
            </table></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT BORANG K</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="50%" valign="top"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
              #foreach ($beanMaklumatBorangK in $BeanMaklumatBorangK)
              <tr>
                <td width="37%" align="right">TARIKH BORANG K :</td>
                <td width="63%"><font color="blue">$beanMaklumatBorangK.tarikhBorangK</font></td>
              </tr>
              <tr>
                <td align="right">CATATAN :</td>
                <td rowspan="2" valign="top"><font color="blue">$beanMaklumatBorangK.catatan</font></td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
            </table></td>
          <td width="50%" valign="top"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
              <tr>
                <td width="37%" align="right" valign="top">&nbsp;</td>
                <td width="63%" rowspan="3" valign="top">&nbsp;</td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              #end
            </table></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT REKOD ENDOSAN BORANG K</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="50%" valign="top"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
              #foreach ($beanMaklumatBorangK in $BeanMaklumatBorangK)
              <tr>
                <td width="37%" align="right">NO PERSERAHAN :</td>
                <td width="63%"><font color="blue">$beanMaklumatBorangK.noPerserahan</font></td>
              </tr>
              <tr>
                <td align="right">TARIKH CATATAN DIBUAT :</td>
                <td><font color="blue">$beanMaklumatBorangK.tarikhCatatan</font></td>
              </tr>
              <tr>
                <td align="right">TARIKH TERIMA  :</td>
                <td><font color="#0000FF">$beanMaklumatBorangK.tarikhTerima</font></td>
              </tr>
            </table></td>
          <td width="50%" valign="top"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
              <tr>
                <td width="37%" align="right" valign="top">&nbsp;</td>
                <td width="63%" rowspan="3" valign="top">&nbsp;</td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              #end
            </table></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td align="center"><input type="button" name="cmdPilih" id="cmdPilih" value="Pilih Tanah" onClick="pilihBorangK('$idPHPBorangK')">
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()">
    </td>
  </tr>
</table>
<script>
function kembali() {	
	document.${formName}.actionPopup.value = "";
	document.${formName}.submit();
}
function pilihBorangK(idPHPBorangK) {
	window.opener.refreshFromPilihBorangK(idPHPBorangK);
	window.close();
}
</script>
