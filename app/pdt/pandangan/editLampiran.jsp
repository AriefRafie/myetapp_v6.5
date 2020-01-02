
#set($spanlampiran = "span2listFolderLampiran_"+$viewLampiran.ID_PANDANGANLAMPIRAN+"_"+$BIL)
#set($spanvalidate = "validate_lampiran_"+$viewLampiran.ID_PANDANGANUNDANG+"_"+$viewLampiran.ID_PANDANGANLAMPIRAN)	
#set($fieldvalidatelampiran = "fieldvalidatelampiran_"+$viewLampiran.ID_PANDANGANUNDANG+"_"+$viewLampiran.ID_PANDANGANLAMPIRAN)
#set($namaFieldlampiran = "editLampiranField_"+$viewLampiran.ID_PANDANGANUNDANG+"_"+$viewLampiran.ID_PANDANGANLAMPIRAN)
<span>
<input type="text" size="50" id="$namaFieldlampiran" name="$namaFieldlampiran" style="text-transform:uppercase;" 
value="$viewLampiran.NAMA_DOC" 
onKeyUp="doDivAjaxCall3$formname('$spanvalidate','validateLampiran','NAMA_DOC='+this.value+'&ID_PANDANGANLAMPIRAN=$viewLampiran.ID_PANDANGANLAMPIRAN&ID_PANDANGANUNDANG=$viewLampiran.ID_PANDANGANUNDANG&ID_PANDANGANUNDANGUTAMA=$viewLampiran.ID_PANDANGANUNDANGUTAMA&BIL=$BIL');"
>
<input type="button" id="cmdSimpanLampiran" name="cmdSimpanLampiran" value="Simpan" onClick="if(validateCheckLampiran('$viewLampiran.ID_PANDANGANUNDANGUTAMA','$viewLampiran.ID_PANDANGANUNDANG','$viewLampiran.ID_PANDANGANLAMPIRAN')==true){doDivAjaxCall$formname('$spanlampiran','SimpanLampiran','ID_PANDANGANLAMPIRAN=$viewLampiran.ID_PANDANGANLAMPIRAN&ID_PANDANGANUNDANG=$viewLampiran.ID_PANDANGANUNDANG&ID_PANDANGANUNDANGUTAMA=$viewLampiran.ID_PANDANGANUNDANGUTAMA&BIL=$BIL');}" >
<input type="button" id="cmdBatalSimpanLampiran" name="cmdBatalSimpanLampiran" value="Batal" onClick="doDivAjaxCall$formname('$spanlampiran','editLampiran','ID_PANDANGANLAMPIRAN=$viewLampiran.ID_PANDANGANLAMPIRAN&ID_PANDANGANUNDANG=$viewLampiran.ID_PANDANGANUNDANG&ID_PANDANGANUNDANGUTAMA=$viewLampiran.ID_PANDANGANUNDANGUTAMA&BIL=$BIL');" >
</span>
<br>
<span id="$spanvalidate"><input type="hidden" id="$fieldvalidatelampiran" name="$fieldvalidatelampiran" value="N" ></span>