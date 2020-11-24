<style type="text/css">
<!--
.style3 {
	color: #FF0000;
	font-style: italic;
}
.style4 {color: #FF0000}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idMesyuarat" type="hidden" id="idMesyuarat" value="$idMesyuarat"/>
  <input name="idKehadiran" type="hidden" id="idKehadiran" value="$idKehadiran"/>  
  <input name="actionMesyuarat" type="hidden" id="actionMesyuarat" value="$actionMesyuarat"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>  
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="hitButton" type="hidden" id="hitButton"/>  
  <input name="idUrusan" type="hidden" id="idUrusan" value="$!idUrusan"/>
</p>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend>MAKLUMAT MESYUARAT</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%"><span class="style4">*</span></td>
          <td width="28%">Tarikh Mesyuarat</td>
          <td width="1%">:</td>
          <td width="70%"><input type="text" name="txtTarikhMesyuarat" id="txtTarikhMesyuarat" size="9" onBlur="check_date(this)" class="$inputTextClass">
          <a href="javascript:displayDatePicker('txtTarikhMesyuarat',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Masa Mesyuarat</td>
          <td>:</td>
          <td>$selectJamDari$selectMinitDari
            &nbsp;Hingga&nbsp;
            $selectJamHingga$selectMinitHingga </td>
        </tr>
        <tr>
          <td></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td><span class="style3">Sila pastikan masa adalah dalam format 24 jam (HHMM).</span></td>
        </tr>
        <tr>
          <td width="1%"><span class="style4">*</span></td>
          <td>Bil. Mesyuarat</td>
          <td>:</td>
          <td><input name="txtBilMesyuarat" type="text" class="$inputTextClass" id="txtBilMesyuarat" size="9" maxlength="12" onBlur="this.value=this.value.toUpperCase();"/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Tempat Mesyuarat</td>
          <td>:</td>
          <td>$selectLokasi</td>
        </tr>
        #if($!idUrusan == '9')
        <tr>
          <td valign="top"><span class="style4">*</span></td>
          <td valign="top">Tajuk Mesyuarat</td>
          <td valign="top">:</td>
          <td valign="top">
          	<textarea name="txtTujuanMesyuarat" id="txtTujuanMesyuarat" rows="5" cols="50" 
          		class="$inputTextClass">Mesyuarat Jawatankuasa One Stop Centre (OSC) Permohonan Lesen Bagi Aktiviti Di kawasan Pelantar Benua Di bawah Akta Pelantar Benua 1966 Bil.</textarea>
          </td>          
        </tr>
        #else
        <tr>
          <td valign="top"><span class="style4">*</span></td>
          <td valign="top">Tajuk Mesyuarat</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtTujuanMesyuarat" id="txtTujuanMesyuarat" rows="5" cols="50" class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();"></textarea>
          </td>
        </tr>
        #end
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">Catatan</td>
          <td valign="top">:</td>
          <td><textarea name="txtCatatanMesyuarat" id="txtCatatanMesyuarat" rows="5" cols="50" class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();"></textarea></td>
        </tr>
        <tr>
          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td><input name="cmdSimpanMesyuarat" type="button" value="Simpan" onClick="simpanMesyuarat()" />
            <input name="cmdBatalMesyuarat" type="button" value="Batal" onClick="batalMesyuarat()" />
          </td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>

<script>
function simpanMesyuarat(){
	if(document.${formName}.txtTarikhMesyuarat.value == ""){
		alert('Sila masukkan Tarikh Mesyuarat.');
  		document.${formName}.txtTarikhMesyuarat.focus(); 
		return; 
	}
	if(document.${formName}.txtBilMesyuarat.value == ""){
		alert('Sila masukkan Bilangan Mesyuarat.');
  		document.${formName}.txtBilMesyuarat.focus(); 
		return; 
	}
	if(document.${formName}.txtTujuanMesyuarat.value == ""){
		alert('Sila masukkan Tajuk Mesyuarat.');
  		document.${formName}.txtTujuanMesyuarat.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.actionMesyuarat.value = "papar";
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "simpanMesyuarat";
	document.${formName}.submit();
}
function batalMesyuarat(){
	document.${formName}.actionMesyuarat.value = "";
	document.${formName}.mode.value = "";
	document.${formName}.submit();
}
</script>