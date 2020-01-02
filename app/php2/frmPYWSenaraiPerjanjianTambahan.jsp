<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0000FF
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idUrusan" type="hidden" id="idUrusan" value="$idUrusan"/>
  <input name="idSuburusan" type="hidden" id="idSuburusan" value="$idSuburusan"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="actionPenyewaan" type="hidden" id="actionPenyewaan" value="$actionPenyewaan"/>
  <input name="flagAktif" type="hidden" id="flagAktif" value="$flagAktif"/>
  
  <input name="idPermohonanPerjanjianTambahan" type="hidden" id="idPermohonanPerjanjianTambahan" value="$idPermohonanPerjanjianTambahan"/>
  <input name="idStatusPerjanjianTambahan" type="hidden" id="idStatusPerjanjianTambahan" value="$idStatusPerjanjianTambahan"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/frmPYWHeader.jsp") </td>
  </tr>
  #else
  <tr>
    <td>&nbsp;
      <div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
  </tr>
  #if ($idFail != '' && ($idStatus == '1610195' || $idStatus == '1610221' || $idStatus == '1610222'))
  <tr>
    <td><fieldset>
      <legend><b>SENARAI PERMOHONAN PERJANJIAN TAMBAHAN</b></legend>
      <table align="center" width="100%">
        #if ($idStatus == '1610195')
        <tr>
          <td colspan="5" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onclick="javascript:daftarBaru()"/></td>
        </tr>
        #end
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="50%"><strong>Jenis Perjanjian</strong></td>
          <td width="15%" align="center"><strong>Tarikh Terima</strong></td>
          <td width="15%" align="center"><strong>Tarikh Surat</strong></td>
          <td width="15%"><strong>Status</strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiPerjanjianTambahan.size() > 0)
        #foreach ($list in $SenaraiPerjanjianTambahan)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row"><a href="javascript:papar('$list.idPermohonanPerjanjianTambahan','$list.idStatusPerjanjianTambahan')" class="style2">$list.jenisPerjanjianTambahan</a></td>
          <td class="$row" align="center">$list.tarikhTerima</td>
          <td class="$row" align="center">$list.tarikhSurat</td>
          <td class="$row">$list.status</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  #end
</table>

<script>
function daftarBaru(){
	document.${formName}.actionPenyewaan.value = "daftarBaru";
	document.${formName}.mode.value = "new";
	document.${formName}.submit();
}
function papar(idPermohonan, idStatus){
	document.${formName}.actionPenyewaan.value = "papar";
	document.${formName}.mode.value = "view";
	document.${formName}.idPermohonanPerjanjianTambahan.value = idPermohonan;
	document.${formName}.idStatusPerjanjianTambahan.value = idStatus;
	document.${formName}.submit();
}
</script>
