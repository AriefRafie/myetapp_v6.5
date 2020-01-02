<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="actionTamat" type="hidden" id="actionTamat" value="$actionTamat"/> 
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idMohonTamat" type="hidden" id="idMohonTamat" value="$idMohonTamat"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>  
</p>
#set($saizSebabTamat="500")
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/frmAPBHeader.jsp") </td>
  </tr>
  #elseif ($idFail == '' )
  <tr>
    <td>&nbsp;
      <div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend>PENDAFTARAN PENAMATAN LESEN</legend>
      #foreach($beanMaklumatMohonTamat in $BeanMaklumatMohonTamat)
      <table align="center" width="100%">
        <tr>
          <td>#if ($modePopup == 'new')<span class="style1">*</span>#end</td>
          <td valign="top">Permohonan Dari</td>
          <td>:</td>
          <td><select name="socFlagDari" id="socFlagDari" style="width:100px;" $readonlyPopup class="$inputTextClassPopup" $inputTextClassPopup >        
        	     #if($beanMaklumatMohonTamat.socFlagDari=='J')
                   <option value="">SILA PILIH</option>
                   <option value="J" selected="selected">J - JKPTG</option>
                   <option value="P">P - PELESEN</option>
                #elseif ($beanMaklumatMohonTamat.socFlagDari=='P')
                   <option value="">SILA PILIH</option>
                   <option value="J">J - JKPTG</option>
                   <option value="P" selected="selected">P - PELESEN</option>              
                #elseif ($beanMaklumatMohonTamat.socFlagDari=='')
                   <option value="" selected="selected">SILA PILIH</option>
                   <option value="J">J - JKPTG</option>
                   <option value="P">P - PELESEN</option>
                #end                    
             </select> </td>
        </tr>
        <tr>
          <td width="1%">#if ($modePopup == 'new')<span class="style1">*</span>#end</td>
          <td width="30%" valign="top">Tarikh Surat</td>
          <td width="1%">:</td>
          <td width="68%"><input name="tarikhSurat" type="text" class="$inputTextClassPopup"  $inputTextClassPopup id="tarikhSurat" value="$beanMaklumatMohonTamat.tarikhSurat" size="9" maxlength="10" $readonlyPopup onBlur="check_date(this);validateTarikh(this)" />
            <a href="javascript:displayDatePicker('tarikhSurat',false,'dmy');">#if ($modePopup != 'view')<img border="0" src="../img/calendar.gif"/>#end
            <div id="tarikhTerima_check" style="color:red" ></div></td>
        </tr>
        <tr>
          <td width="1%">#if ($modePopup == 'new')<span class="style1">*</span>#end</td>
          <td valign="top">Tarikh Terima</td>
          <td>:</td>
          <td><input name="tarikhTerima" type="text" class="$inputTextClassPopup"  $inputTextClassPopup id="tarikhTerima" value="$beanMaklumatMohonTamat.tarikhTerima" size="9" maxlength="10" $readonlyPopup onBlur="check_date(this);validateTarikh(this)"/>
            <a href="javascript:displayDatePicker('tarikhTerima',false,'dmy');">#if ($modePopup != 'view')<img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        <tr>
          <td valign="top">#if ($modePopup == 'new')#end</td>
          <td valign="top">No Rujukan</td>
          <td valign="top">:</td>
          <td valign="top">
            <input name="rujukan" type="text" id="rujukan" value="$beanMaklumatMohonTamat.rujukan" size="40" maxlength="80" $readonlyPopup class="$inputTextClassPopup"  $inputTextClassPopup>          </td>
        </tr>
        <tr>
          <td valign="top">#if ($modePopup == 'new')<span class="style1">*</span>#end</td>
          <td valign="top">Sebab Penamatan</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="sebabTamat" cols="40" rows="5" id="sebabTamat" $readonlyPopup class="$inputTextClassPopup"  $inputTextClassPopup onKeyUp="textCounter(this.form.sebabTamat,this.form.remLen2,$!saizSebabTamat);" onKeyDown="textCounter(this.form.sebabTamat,this.form.remLen2,$!saizSebabTamat);">$beanMaklumatMohonTamat.sebabTamat</textarea></td>
        </tr>
       #if ($modePopup == 'new')
        <tr>
          <td valign="bottom">&nbsp;</td>
          <td valign="bottom">&nbsp;</td>
          <td valign="bottom">&nbsp;</td>
          <td valign="bottom">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLen2" size="3" maxlength="3" value="$!saizSebabTamat" /></td>
        </tr>
        #end
        #if ($modePopup == 'new')
        <tr>
          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
        #end
        <tr>
          <td></td>
          <td></td>
          <td></td>
          <td> 
          #if ($modePopup == 'new')
            <input type="button" name="cmdDaftarBaru" id="cmdDaftarBaru" value="Daftar" onClick="doDaftarTamatLesen()"/>
            <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="doBackToList()"/>
          #end </td>
        </tr>
        <tr>
          <td colspan="4">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function doBackToList(){
	document.${formName}.actionTamat.value = "";
	document.${formName}.mode.value = "";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";	
	document.${formName}.submit();
}
function doDaftarTamatLesen() {
	if(document.${formName}.tarikhSurat.value == ""){
		alert('Sila masukkan Tarikh Surat.');
  		document.${formName}.tarikhSurat.focus(); 
		return; 
	}
	if(document.${formName}.tarikhTerima.value == ""){
		alert('Sila masukkan Tarikh Terima.');
  		document.${formName}.tarikhTerima.focus(); 
		return; 
	}
	if(document.${formName}.sebabTamat.value == ""){
		alert('Sila masukkan Sebab Penamatan.');
  		document.${formName}.sebabTamat.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionTamat.value = "add";
		document.${formName}.mode.value = "view";
		document.${formName}.flagPopup.value = "openPopupTamatLesen";
		document.${formName}.modePopup.value = "new";
		return;
	}
	document.${formName}.actionTamat.value = "papar";
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = 0;	
	document.${formName}.hitButton.value = "simpanMaklumatMohonTamat";
	document.${formName}.submit();
}
function textCounter(field, countfield, maxlimit) {
   if (field.value.length > maxlimit) // if too long...trim it!
		 field.value = field.value.substring(0, maxlimit);
    // otherwise, update 'Baki Aksara' counter
	else
	 countfield.value = maxlimit - field.value.length;
}
</script>
