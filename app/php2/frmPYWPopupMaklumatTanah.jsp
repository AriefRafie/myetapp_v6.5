<style type="text/css">
<!--
#parse("css/eTapp_PHP.css")
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT TANAH</strong></legend>
      #foreach($beanMaklumatTanah in $BeanMaklumatTanah)
      #set($idHakmilikAgensi = $beanMaklumatTanah.idHakmilikAgensi)
      #set($idHakmilikSementara = $beanMaklumatTanah.idHakmilikSementara)
      #set($idHakmilik = $beanMaklumatTanah.idHakmilik)
      #set($peganganHakmilik = $beanMaklumatTanah.peganganHakmilik)
      #set($noLot = $beanMaklumatTanah.noLot)
      #set($luasLot = $beanMaklumatTanah.luasLot)
      #set($noHakmilik = $beanMaklumatTanah.noHakmilik)
      #set($noWarta = $beanMaklumatTanah.noWarta)
      #set($tarikhWarta = $beanMaklumatTanah.tarikhWarta)             
      #set($mukim = $beanMaklumatTanah.mukim)
      #set($daerah = $beanMaklumatTanah.daerah)
      #set($negeri = $beanMaklumatTanah.negeri)            
      #set($kategoriTanah = $beanMaklumatTanah.kategoriTanah)
      #set($subKategoriTanah = $beanMaklumatTanah.subKategoriTanah)
      #set($syarat = $beanMaklumatTanah.syarat)
      #set($sekatan = $beanMaklumatTanah.sekatan)
      #set($kementerian = $beanMaklumatTanah.kementerian)
      #set($agensi = $beanMaklumatTanah.agensi) 
      #set($kegunaanTanah = $beanMaklumatTanah.kegunaanTanah)         
      #end
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="50%" valign="top"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
              #foreach ($beanMaklumatTanah in $BeanMaklumatTanah)
              <tr>
                <td width="37%" align="right">PEGANGAN HAKMILIK :</td>
                <td width="63%"><font color="blue">$peganganHakmilik</font>
                  <input type="hidden" name="actionPopup"/></td>
              </tr>
              <tr>
                <td align="right">NO. LOT :</td>
                <td><font color="blue">$noLot</font></td>
              </tr>
              <tr>
                <td align="right">LUAS  :</td>
                <td><font color="#0000FF">$luasLot</font></td>
              </tr>
              <tr>
                <td align="right">NO. HAKMILIK :</td>
                <td><font color="blue">$noHakmilik</font></td>
              </tr>
              <tr>
                <td align="right">NO. WARTA :</td>
                <td><font color="blue">$noWarta</font></td>
              </tr>
              <tr>
                <td align="right">TARIKH WARTA :</td>
                <td><font color="blue">$tarikhWarta</font></td>
              </tr>
              <tr>
                <td align="right">MUKIM :</td>
                <td><font color="blue">$mukim</font></td>
              </tr>
              <tr>
                <td align="right">DAERAH :</td>
                <td><font color="blue">$daerah</font></td>
              </tr>
              <tr>
                <td align="right">NEGERI :</td>
                <td><font color="blue">$negeri</font></td>
              </tr>
              <tr>
                <td align="right">KATEGORI TANAH :</td>
                <td><font color="blue">$kategoriTanah</font></td>
              </tr>
              <tr>
                <td align="right">SUBKATEGORI TANAH :</td>
                <td><font color="blue">$subKategoriTanah</font></td>
              </tr>
            </table></td>
          <td width="50%" valign="top"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
              <tr>
                <td width="37%" align="right" valign="top">STATUS TANAH :</td>
                <td width="63%" rowspan="3" valign="top"><font color="blue">$syarat</font></td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td width="37%" align="right" valign="top">SYARAT NYATA :</td>
                <td width="63%" rowspan="3" valign="top"><font color="blue">$syarat</font></td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right" valign="top">SEKATAN KEPENTINGAN :</td>
                <td rowspan="3" valign="top"><font color="blue">$sekatan</font></td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right" valign="top">KEGUNAAN TANAH :</td>
                <td rowspan="3" valign="top"><font color="blue">$kegunaanTanah</font></td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right">&nbsp;</td>
              </tr>
              <tr>
                <td align="right" valign="top">KEMENTERIAN :</td>
                <td valign="top"><font color="blue">$kementerian</font></td>
              </tr>
              <tr>
                <td align="right" valign="top">AGENSI :</td>
                <td><font color="blue">$agensi</font></td>
              </tr>
              #end
            </table></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td align="center"><input type="button" name="cmdPilih" id="cmdPilih" value="Pilih Tanah" onClick="pilihTanah('$idHakmilikAgensi','$idHakmilikSementara')">
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()">
    </td>
  </tr>
</table>
<script>
function kembali() {	
	document.${formName}.actionPopup.value = "";
	document.${formName}.submit();
}
function pilihTanah(idHakmilikAgensi,idHakmilikSementara) {
	window.opener.refreshFromPilihTanah(idHakmilikAgensi,idHakmilikSementara);
	window.close();
}
</script>
