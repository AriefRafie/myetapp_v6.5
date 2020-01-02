
#set($spanmainviewfield = "editMainDirField_"+$viewMainFoler.ID_DOCTRMUTAMA)
#set($spanvalidateMaindir = "validate_maindir_"+$viewMainFoler.ID_DOCTRMUTAMA)
#set($fieldvalidateMainDir = "fieldvalidateMainDir_"+$viewMainFoler.ID_DOCTRMUTAMA)
#set($spanMain = "span1listFolderUtama"+$BIL)
<input type="text" size="50" id="$spanmainviewfield" name="$spanmainviewfield" style="text-transform:uppercase;" 
value="$viewMainFoler.TAJUK" 
onKeyUp="doDivAjaxCall3$formname('$spanvalidateMaindir','validateMainDir','TAJUK='+this.value+'&ID_DOCTRMUTAMA=$viewMainFoler.ID_DOCTRMUTAMA');"

>
<input type="button" id="cmdSimpanMain" name="cmdSimpanMain" value="Simpan" onClick="if(validateCheckMainDir('$viewMainFoler.ID_DOCTRMUTAMA')==true){doDivAjaxCall$formname('$spanMain','SimpanMainDir','ID_DOCTRMUTAMA=$viewMainFoler.ID_DOCTRMUTAMA&BIL=$BIL');}" >
<input type="button" id="cmdBatalSimpanMain" name="cmdBatalSimpanMain" value="Batal" onClick="doDivAjaxCall$formname('$spanMain','editMainDir','ID_DOCTRMUTAMA=$viewMainFoler.ID_DOCTRMUTAMA&BIL=$BIL');" >
<br>
<span id="$spanvalidateMaindir"><input type="hidden" id="$fieldvalidateMainDir" name="$fieldvalidateMainDir" value="N" ></span>