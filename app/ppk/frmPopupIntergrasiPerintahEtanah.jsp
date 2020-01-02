<style type="text/css">
<!--
#parse("css/eTapp_PPK.css") .style1 {
 color: #0000FF;
 font-weight: bold;
}
-->
</style>
<input type="hidden" name="actionPopup"/>
<input type="hidden" name="hitButton"/>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($flagMsg == 'Y')
  <tr>
    <td colspan="3">&nbsp;
      <div class="success">$outputMsg</div></td>
  </tr>
  #end
  #if ($flagMsg == 'N')
  <tr>
    <td colspan="3">&nbsp;
      <div class="error">MAKLUMAT PERINTAH TIDAK BERJAYA DIHANTAR : $outputMsg</div></td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT PERINTAH</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="50%" valign="top"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
              #foreach ($beanMaklumatPerintah in $BeanMaklumatPerintah)
              <input type="hidden" name="idPPKPerintah" value="$beanMaklumatPerintah.idPPKHTA"/>
              <tr>
                <td width="37%" align="right">NO FAIL :</td>
                <td width="63%"><span class="style1">$beanMaklumatPerintah.noFail</span></td>
              </tr>
              <tr>
                <td align="right">NAMA SIMATI :</td>
                <td><span class="style1">$beanMaklumatPerintah.namaSimati</span></td>
              </tr>
              <tr>
                <td align="right">NO. KP. SIMATI :</td>
                <td><span class="style1">$beanMaklumatPerintah.noKPSimati</span></td>
              </tr>
              <tr>
                <td align="right">TARIKH MATI :</td>
                <td><span class="style1">$beanMaklumatPerintah.tarikhMati</span></td>
              </tr>
              <tr>
                <td align="right">NO. SIJIL MATI :</td>
                <td><span class="style1">$beanMaklumatPerintah.noSijilMati</span></td>
              </tr>              
            </table></td>
          <td width="50%" valign="top"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
              <tr>
                <td width="37%" align="right" valign="top">NAMA PEMOHON :</td>
                <td width="63%" valign="top"><span class="style1">$beanMaklumatPerintah.namaPemohon</span></td>
              </tr>
              <tr>
                <td align="right" valign="top">NO. KP. PEMOHON :</td>
                <td valign="top"><span class="style1">$beanMaklumatPerintah.noKPPemohon</span></td>
              </tr>
              <tr>
                <td align="right" valign="top">PEGAWAI BICARA :</td>
                <td valign="top"><span class="style1">$beanMaklumatPerintah.pegawaiBicara</span></td>
              </tr>
              <tr>
                <td align="right" valign="top">TEMPAT BICARA :</td>
                <td valign="top"><span class="style1">$beanMaklumatPerintah.tempatBicara</span></td>
              </tr>
              <tr>
                <td align="right" valign="top">TARIKH PERINTAH :</td>
                <td valign="top"><span class="style1">$beanMaklumatPerintah.tarikhPerintah</span></td>
              </tr>
              #end
            </table></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td align="center">&nbsp;</td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI HAKMILIK</strong></legend>
      <table align="center" width="100%">
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="10%"><strong>ID Hakmilik</strong></td>
          <td width="15%"><strong>Bahagian Simati</strong></td>
          <td width="15%"><strong>Jenis Pembahagian</strong></td>
          <td width="10%" align="center"><strong>Tarikh Hantar</strong></td>
        </tr>
        #set ($senaraiHakmilik = "")
        #if ($SenaraiHakmilik.size() > 0)
        #foreach ($senaraiHakmilik in $SenaraiHakmilik)
        #if ($senaraiHakmilik.bil == '')
        #set( $row = "row1" )
        #elseif (($senaraiHakmilik.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$senaraiHakmilik.bil</td>
          <td class="$row">$senaraiHakmilik.idHakmilik</td>
          <td class="$row">$senaraiHakmilik.ba / $senaraiHakmilik.bb</td>
          <td class="$row">$senaraiHakmilik.jenisPembahagian</td>
          <td class="$row" align="center"><strong>$senaraiHakmilik.tarikhHantar</strong></td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  #if ($flagHantar == 'T')
  <tr>
    <td align="center"><input type="button" name="cmdHantar" id="cmdHantar" value="Hantar" onClick="hantar()">
    </td>
  </tr>
  #end
</table>
<script>
function hantar() {	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.actionPopup.value = "";
	document.${formName}.hitButton.value = "hantar";
	document.${formName}.submit();
}
</script>
