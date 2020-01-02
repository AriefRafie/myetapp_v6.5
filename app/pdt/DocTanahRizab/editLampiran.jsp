
#set($spanlampiran = "span2listFolderLampiran_"+$viewLampiran.ID_DOCTRMLAMPIRAN+"_"+$BIL)
#set($spanvalidate = "validate_lampiran_"+$viewLampiran.ID_DOCTRM+"_"+$viewLampiran.ID_DOCTRMLAMPIRAN)	
#set($fieldvalidatelampiran = "fieldvalidatelampiran_"+$viewLampiran.ID_DOCTRM+"_"+$viewLampiran.ID_DOCTRMLAMPIRAN)
#set($namaFieldlampiran = "editLampiranField_"+$viewLampiran.ID_DOCTRM+"_"+$viewLampiran.ID_DOCTRMLAMPIRAN)

#set($tarikhDaftar = "tarikhDaftar_"+$viewLampiran.ID_DOCTRM+"_"+$viewLampiran.ID_DOCTRMLAMPIRAN)

<span>

<p>Tarikh Dokumen : <label>
      <input name="$tarikhDaftar" type="text" id="$tarikhDaftar" size="10" onblur="check_date(this);" value="$viewLampiran.TARIKH_DAFTAR" onkeyup="validateNumber(this,this.value);" >
     <a href="javascript:displayDatePicker('$tarikhDaftar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></label></p>
     
Tajuk Dokumen :
<input type="text" size="50" id="$namaFieldlampiran" name="$namaFieldlampiran" style="text-transform:uppercase;" 
value="$viewLampiran.NAMA_DOC" 
onKeyUp="doDivAjaxCall3$formname('$spanvalidate','validateLampiran','NAMA_DOC='+this.value+'&ID_DOCTRMLAMPIRAN=$viewLampiran.ID_DOCTRMLAMPIRAN&ID_DOCTRM=$viewLampiran.ID_DOCTRM&ID_DOCTRMUTAMA=$viewLampiran.ID_DOCTRMUTAMA&BIL=$BIL');"
>
<input type="button" id="cmdSimpanLampiran" name="cmdSimpanLampiran" value="Simpan" onClick="if(validateCheckLampiran('$viewLampiran.ID_DOCTRMUTAMA','$viewLampiran.ID_DOCTRM','$viewLampiran.ID_DOCTRMLAMPIRAN')==true){doDivAjaxCall$formname('$spanlampiran','SimpanLampiran','ID_DOCTRMLAMPIRAN=$viewLampiran.ID_DOCTRMLAMPIRAN&ID_DOCTRM=$viewLampiran.ID_DOCTRM&ID_DOCTRMUTAMA=$viewLampiran.ID_DOCTRMUTAMA&BIL=$BIL');}" >
<input type="button" id="cmdBatalSimpanLampiran" name="cmdBatalSimpanLampiran" value="Batal" onClick="doDivAjaxCall$formname('$spanlampiran','editLampiran','ID_DOCTRMLAMPIRAN=$viewLampiran.ID_DOCTRMLAMPIRAN&ID_DOCTRM=$viewLampiran.ID_DOCTRM&ID_DOCTRMUTAMA=$viewLampiran.ID_DOCTRMUTAMA&BIL=$BIL');" >
</span>
<br>
<span id="$spanvalidate"><input type="hidden" id="$fieldvalidatelampiran" name="$fieldvalidatelampiran" value="N" ></span>