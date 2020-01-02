<style type="text/css">
<!--
.style1 {
	color: #0000FF
}
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
              <input type="hidden" name="idPPTBorangK" value="$beanMaklumatBorangK.idPPTBorangK"/>
              <input type="hidden" name="idHakmilikUrusan" value="$beanMaklumatBorangK.idHakmilikUrusan"/>
              <input type="hidden" name="idPHPBorangK" value="$beanMaklumatBorangK.idPHPBorangK"/>
              <tr>
                <td width="37%" align="right">PEGANGAN HAKMILIK :</td>
                <td width="63%">$beanMaklumatBorangK.peganganHakmilik</td>
              </tr>
              <tr>
                <td align="right">NO. LOT :</td>
                <td>$beanMaklumatBorangK.lot</td>
              </tr>
              <tr>
                <td align="right">LUAS  :</td>
                <td>$beanMaklumatBorangK.luas</td>
              </tr>
              <tr>
                <td align="right">NO. HAKMILIK :</td>
                <td>$beanMaklumatBorangK.hakmilik</td>
              </tr>
              <tr>
                <td align="right">NO. WARTA :</td>
                <td>$beanMaklumatBorangK.noWarta</td>
              </tr>
              <tr>
                <td align="right">TARIKH WARTA :</td>
                <td>$beanMaklumatBorangK.tarikhWarta</td>
              </tr>
              <tr>
                <td align="right">MUKIM :</td>
                <td>$beanMaklumatBorangK.mukim</td>
              </tr>
              <tr>
                <td align="right">DAERAH :</td>
                <td>$beanMaklumatBorangK.daerah</td>
              </tr>
              <tr>
                <td align="right">NEGERI :</td>
                <td>$beanMaklumatBorangK.negeri</td>
              </tr>
              <tr>
                <td align="right">KATEGORI TANAH :</td>
                <td>$beanMaklumatBorangK.kategoriTanah</td>
              </tr>
              <tr>
                <td align="right">SUBKATEGORI TANAH :</td>
                <td>$beanMaklumatBorangK.subKategoriTanah</td>
              </tr>
            </table></td>
          <td width="50%" valign="top"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
              <tr>
                <td width="37%" align="right" valign="top">SYARAT NYATA :</td>
                <td width="63%" rowspan="3" valign="top">$beanMaklumatBorangK.syarat</td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right" valign="top">SEKATAN KEPENTINGAN :</td>
                <td rowspan="3" valign="top">$beanMaklumatBorangK.sekatan</td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right" valign="top">KEGUNAAN TANAH :</td>
                <td rowspan="3" valign="top">$beanMaklumatBorangK.kegunaanTanah</td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right" valign="top">KEMENTERIAN :</td>
                <td valign="top">$beanMaklumatBorangK.kementerian</td>
              </tr>
              <tr>
                <td align="right" valign="top">AGENSI :</td>
                <td>$beanMaklumatBorangK.agensi</td>
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
                <td width="63%">$beanMaklumatBorangK.tarikhBorangK</td>
              </tr>
              <tr>
                <td align="right">CATATAN :</td>
                <td rowspan="2" valign="top">$beanMaklumatBorangK.catatan</td>
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
                <td width="63%">$beanMaklumatBorangK.noPerserahan</td>
              </tr>
              <tr>
                <td align="right">TARIKH CATATAN DIBUAT :</td>
                <td>$beanMaklumatBorangK.tarikhCatatan</td>
              </tr>
              <tr>
                <td align="right">TARIKH TERIMA  :</td>
                <td>$beanMaklumatBorangK.tarikhTerima</td>
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
    <td align="center"><input type="button" name="cmdPilih" id="cmdPilih" value="Pilih Tanah" onClick="pilihBorangK()">
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()">
    </td>
  </tr>
</table>
<script>
function kembali() {	
	document.${formName}.actionPopup.value = "";
	document.${formName}.submit();
}
function pilihBorangK() {
	
	var idPPTBorangK = document.${formName}.idPPTBorangK.value;
	var idHakmilikUrusan = document.${formName}.idHakmilikUrusan.value;
	var idPHPBorangK = document.${formName}.idPHPBorangK.value;
	
	window.opener.refreshFromPilihBorangK(idPPTBorangK,idHakmilikUrusan,idPHPBorangK);
	window.close();
}
</script>
