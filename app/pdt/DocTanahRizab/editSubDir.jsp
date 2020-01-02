#set($spansubview = "")
#set($spansubviewfield = "editSubDirField_"+$viewSubFoler.ID_DOCTRMUTAMA+"_"+$viewSubFoler.ID_REFER+"_"+$viewSubFoler.ID_DOCTRM)
#set($spanvalidatesubdir = "validate_subdir_"+$viewSubFoler.ID_DOCTRMUTAMA+"_"+$viewSubFoler.ID_REFER+"_"+$viewSubFoler.ID_DOCTRM)
#set($fieldvalidateSubDir = "fieldvalidateSubDir_"+$viewSubFoler.ID_DOCTRMUTAMA+"_"+$viewSubFoler.ID_REFER+"_"+$viewSubFoler.ID_DOCTRM)
#if($viewSubFoler.LAYER=="1")
			#set($spansubview = "span1listFolderSub_"+$viewSubFoler.LAYER+"_"+$viewSubFoler.ID_DOCTRMUTAMA+"_"+$BIL)
#else
			#set($spansubview = "span1listFolderSub_"+$viewSubFoler.ID_DOCTRM+"_"+$BIL)
#end
<input type="text" size="50" id="$spansubviewfield" name="$spansubviewfield" style="text-transform:uppercase;" 
value="$viewSubFoler.TAJUK" 
onKeyUp="doDivAjaxCall3$formname('$spanvalidatesubdir','validateSubDir','TAJUK='+this.value+'&ID_REFER=$viewSubFoler.ID_REFER&ID_DOCTRM=$viewSubFoler.ID_DOCTRM&ID_DOCTRMUTAMA=$viewSubFoler.ID_DOCTRMUTAMA&BIL=$BIL');"

>

<input type="button" id="cmdSimpanSub" name="cmdSimpanSub" value="Simpan" onClick="if(validateCheckSubDir('$viewSubFoler.ID_DOCTRMUTAMA','$viewSubFoler.ID_DOCTRM','$viewSubFoler.ID_REFER')==true){doDivAjaxCall$formname('$spansubview','SimpanSubDir','ID_REFER=$viewSubFoler.ID_REFER&ID_DOCTRMUTAMA=$viewSubFoler.ID_DOCTRMUTAMA&ID_DOCTRM=$viewSubFoler.ID_DOCTRM&LAYER=$viewSubFoler.LAYER&BIL=$BIL');}" >
<input type="button" id="cmdBatalSimpanSub" name="cmdBatalSimpanSub" value="Batal" onClick="doDivAjaxCall$formname('$spansubview','editSubDir','ID_DOCTRM=$viewSubFoler.ID_DOCTRM&LAYER=$viewSubFoler.LAYER&BIL=$BIL');" >
<br>
<span id="$spanvalidatesubdir"><input type="hidden" id="$fieldvalidateSubDir" name="$fieldvalidateSubDir" value="N" ></span>