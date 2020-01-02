<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanMaklumatKeputusanAduan in $BeanMaklumatKeputusanAduan)
  <tr>
    <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td width="28%">Tarikh Keputusan</td>
    <td width="1%">:</td>
    <td width="70%"><input type="text" name="txtTarikhKeputusan" id="txtTarikhKeputusan" size="9" onBlur="check_date(this)" value="$beanMaklumatKeputusanAduan.tarikhKeputusan" $readonly class="$inputTextClass">
      #if ($mode != 'view') <a href="javascript:displayDatePicker('txtTarikhKeputusan',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
  </tr>
  <tr>
    <td width="1%">#if ($mode!= 'view')<span class="style1">*</span>#end</td>
    <td width="28%">Ulasan Keputusan</td>
    <td width="1%">:</td>
    <td width="70%" rowspan="4" valign="top"><textarea name="txtUlasanKeputusan" id="txtUlasanKeputusan" rows="5" cols="50" $readonly class="$inputTextClass"  >$beanMaklumatKeputusanAduan.ulasanKeputusan</textarea></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4" valign="bottom"></td>
  </tr>
  #if ($mode != 'view')
  <tr>
    <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i> </td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td> #if ($mode == 'view')
      <input type="button" name="cmdKemaskiniK" id="cmdKemaskiniK" value="Kemaskini" onClick="kemaskiniMaklumatKeputusan()"/>
       <input type="button" name="cdmCetak3" id="cdmCetak3" value="Cetak" onClick="javascript:setTable('tableReport3')"/>
      #end
      #if ($mode == 'update')
      <input type="button" name="cmdSimpanKemaskiniK" id="cmdSimpanKemaskiniK" value="Simpan" onClick="simpankemaskiniMaklumatKeputusan()"/>
      <input type="button" name="cmdBatalKemaskiniK" id="cmdBatalKemaskiniK" value="Batal" onClick="batalMaklumatKeputusan()"/>
      #end </td>
  </tr>
  #end
</table>
<fieldset id="tableReport3" style="display:none;"-->
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakSuratAmaranAduan('$idFail')"> Surat Amaran </a></td>
  </tr>
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakSuratTamatLesenAduan('$idFail')"> Surat Penamatan Lesen </a></td>
  </tr>
<!--  <tr>
	<td ><a href="#" class="style2" onClick="javascript:cetakSuratTunjukSebabAduan('$idFail')"> Surat Tunjuk Sebab </a></td>
  </tr>-->

</table>
</fieldset>
<script>
function kemaskiniMaklumatKeputusan() {
	document.${formName}.actionAduan.value = "maklumatAduan";
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function simpankemaskiniMaklumatKeputusan() {

	if(document.${formName}.txtTarikhKeputusan.value == ""){
		alert('Sila masukkan Tarikh Keputusan.');
  		document.${formName}.txtTarikhKeputusan.focus(); 
		return; 
	}
	if(document.${formName}.txtUlasanKeputusan.value == ""){
		alert('Sila masukkan Ulasan Keputusan.');
  		document.${formName}.txtUlasanKeputusan.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	document.${formName}.actionAduan.value = "maklumatAduan";
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniKeputusanAduan";
	document.${formName}.submit();
}
function batalMaklumatKeputusan() {
	document.${formName}.actionAduan.value = "maklumatAduan";
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
</script>
