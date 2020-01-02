<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"> #foreach ($beanMaklumatSiasatan in $BeanMaklumatSiasatan)
      <fieldset>
      <legend><b>MAKLUMAT LAPORAN SIASATAN APMM</b></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Tarikh Terima Surat</td>
          <td width="1%">:</td>
          <td width="70%"><input type="text" name="txtTarikhTerimaSuratAPMM" id="txtTarikhTerimaSuratAPMM" size="9" onBlur="check_date(this)" value="$beanMaklumatSiasatan.tarikhTerimaSuratAPMM" $readonly class="$inputTextClass">
            #if ($mode != 'view') <a href="javascript:displayDatePicker('txtTarikhTerimaSuratAPMM',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
        </tr>
        <tr>
          <td width="1%"></td>
          <td width="28%">Tarikh Surat</td>
          <td width="1%">:</td>
          <td width="70%"><input type="text" name="txtTarikhSuratAPMM" id="txtTarikhSuratAPMM" size="9" onBlur="check_date(this)" value="$beanMaklumatSiasatan.tarikhSuratAPMM" $readonly class="$inputTextClass">
            #if ($mode != 'view') <a href="javascript:displayDatePicker('txtTarikhSuratAPMM',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">No Rujukan Surat</td>
          <td width="1%">:</td>
          <td width="70%"><input type="text" name="txtNoRujSuratAPMM" id="txtNoRujSuratAPMM" size="9" value="$beanMaklumatSiasatan.noRujSuratAPMM" $readonly class="$inputTextClass">          </td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Maklumat Laporan Siasatan</td>
          <td width="1%">:</td>
          <td width="70%" rowspan="4" valign="top"><textarea name="txtSiasatanAPMM" id="txtSiasatanAPMM" rows="5" cols="50" $readonly class="$inputTextClass" >$beanMaklumatSiasatan.siasatanAPMM</textarea></td>
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
        #if ($mode == 'update')
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2"><fieldset>
      <legend><b>MAKLUMAT ULASAN JKPTG</b></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%"></td>
          <td width="28%">Maklumat Laporan Siasatan</td>
          <td width="1%">:</td>
          <td width="70%" rowspan="4" valign="top"><textarea name="txtSiasatanJKPTG" id="txtSiasatanJKPTG" rows="5" cols="50" $readonly class="$inputTextClass" >$beanMaklumatSiasatan.siasatanJKPTG</textarea></td>
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
        #if ($mode == 'update')
        <tr>
          <td valign="bottom">&nbsp;</td>
          <td valign="bottom">&nbsp;</td>
          <td valign="bottom">&nbsp;</td>
          <td valign="bottom">&nbsp;</td>
        </tr>
        #end
        <tr>
          <td colspan="4" valign="bottom"></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  
  <tr>
    <td colspan="2"><fieldset>
      <legend><b>MAKLUM BALAS PELESEN</b></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%"></td>
          <td width="28%">Catatan</td>
          <td width="1%">:</td>
          <td width="70%" rowspan="4" valign="top"><textarea name="txtCatatanMaklumbalas" id="txtCatatanMaklumbalas" rows="5" cols="50" $readonly class="$inputTextClass" >$beanMaklumatSiasatan.txtCatatanMaklumbalas</textarea></td>
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
        #if ($mode == 'update')
        <tr>
          <td valign="bottom">&nbsp;</td>
          <td valign="bottom">&nbsp;</td>
          <td valign="bottom">&nbsp;</td>
          <td valign="bottom">&nbsp;</td>
        </tr>
        #end
        <tr>
          <td colspan="4" valign="bottom"></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>  
  
  
  #if ($mode != 'view')
  <tr>
    <td colspan="2"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i> </td>
  </tr>
  #end
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%"> #if ($mode == 'view')
      <input type="button" name="cmdKemaskinL" id="cmdKemaskiniL" value="Kemaskini" onClick="kemaskiniMaklumatSiasatan()"/>
      <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport2')"/>
      #end
      #if ($mode == 'update')
      <input type="button" name="cmdSimpanKemaskiniL" id="cmdSimpanKemaskiniL" value="Simpan" onClick="simpankemaskiniMaklumatSiasatan()"/>
      <input type="button" name="cmdBatalKemaskiniL" id="cmdBatalKemaskiniL" value="Batal" onClick="batalMaklumatSiasatan()"/>
      #end </td>
  </tr>
  #end
</table>
<fieldset id="tableReport2" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakKertasRingkasanAduan('$idFail','$idPengadu')"> Kertas Ringkasan Mesyuarat </a>
    </td>
  </tr>
  
  <tr>
	<td ><a href="#" class="style2" onClick="javascript:cetakSuratTunjukSebabAduan('$idFail')"> Surat Tunjuk Sebab </a></td>
  </tr>
    
</table>
</fieldset>
<script>
function kemaskiniMaklumatSiasatan() {
	document.${formName}.actionAduan.value = "maklumatAduan";
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function simpankemaskiniMaklumatSiasatan() {
	if(document.${formName}.txtTarikhTerimaSuratAPMM.value == ""){
		alert('Sila masukkan Tarikh Terima Surat.');
  		document.${formName}.txtTarikhTerimaSuratAPMM.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	document.${formName}.actionAduan.value = "maklumatAduan";
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniMaklumatSiasatan";
	document.${formName}.submit();
}
function batalMaklumatSiasatan() {
	document.${formName}.actionAduan.value = "maklumatAduan";
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
</script>
