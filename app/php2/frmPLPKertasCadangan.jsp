<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanKertasCadangan in $BeanKertasCadangan)
  <tr>
    <td colspan="2"><fieldset>
      <legend>TAJUK KERTAS</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="20%">&nbsp;</td>
          <td width="80%"><textarea name="txtTajukKertas" id="txtTajukKertas" cols="80" rows="5" $readonly class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();">$beanKertasCadangan.txtTajukKertas</textarea><input name="idKertasKerja" type="hidden" id="idKertasKerja" value="$beanKertasCadangan.idKertasKerja"/></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2"><fieldset>
      <legend>TUJUAN</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="20%">&nbsp;</td>
          <td width="80%"><textarea name="txtTujuan" id="txtTujuan" cols="80" rows="5" $readonly class="$inputTextClass">$beanKertasCadangan.txtTujuan</textarea></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2"><fieldset>
      <legend>LATAR BELAKANG TANAH</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="20%">&nbsp;</td>
          <td width="80%"><textarea name="txtLatarBelakangTanah" id="txtLatarBelakangTanah" cols="80" rows="5" $readonly class="$inputTextClass">$beanKertasCadangan.txtLatarBelakangTanah</textarea></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2"><fieldset>
      <legend>LAPORAN NILAIAN</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="20%">&nbsp;</td>
          <td width="80%"><textarea name="txtLaporanNilaian" id="txtLaporanNilaian" cols="80" rows="5" $readonly class="$inputTextClass">$beanKertasCadangan.txtLaporanNilaian</textarea></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2"><fieldset>
      <legend>CADANGAN PEMBANGUNAN DAN PROJEK</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="20%">&nbsp;</td>
          <td width="80%"><textarea name="txtCadanganPembangunan" id="txtCadanganPembangunan" cols="80" rows="5" $readonly class="$inputTextClass">$beanKertasCadangan.txtCadanganPembangunan</textarea></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2"><fieldset>
      <legend>PIHAK YANG MEMOHON PENYERAHAN BALIK</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="20%">&nbsp;</td>
          <td width="80%"><textarea name="txtPPemohon" id="txtPPemohon" cols="80" rows="5" $readonly class="$inputTextClass">$beanKertasCadangan.txtPemohon</textarea></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2"><fieldset>
      <legend>PERAKUAN PESURUHJAYA TANAH PERSEKUTUAN</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="20%">&nbsp;</td>
          <td width="80%"><textarea name="txtPerakuanPTP" id="txtPerakuanPTP" cols="80" rows="5" $readonly class="$inputTextClass">$beanKertasCadangan.txtPerakuanPTP</textarea></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  #end
  <tr>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td width="20%">&nbsp;</td>
    <td width="80%"> #if ($mode == 'view')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskiniCadangan()"/>
      <input type="button" name="cdmCetakCadangan" id="cdmCetakCadangan" value="Cetak" onClick="javascript:setTable('tableReportKertasCadangan')"/>
      #if ($idStatus == '1610199')
      <input type="button" name="cmdHantar" id="cmdHantar" value="Seterusnya" onClick="doSeterusnya()"/>
      <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
      #end
      #end
      #if ($mode == 'update')
      <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onclick="simpanKemaskiniCadangan()"/>
      <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="batalCadangan()"/>
      #end </td>
  </tr>
</table>

<fieldset id="tableReportKertasCadangan" style="display:none;"-->
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakKertasCadangan('$idFail','$idPermohonan')"> Kertas Cadangan </a></td>
  </tr>
</table>
</fieldset>

<script>
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function kemaskiniCadangan() {
	document.${formName}.mode.value = "update";
	//doAjaxCall${formName}("");
	document.${formName}.submit();
}
function simpanKemaskiniCadangan(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniMaklumatCadangan";
	document.${formName}.submit();
}
function batalCadangan(){
	document.${formName}.mode.value = "view";
	//doAjaxCall${formName}("");
	document.${formName}.submit();
}
</script>
