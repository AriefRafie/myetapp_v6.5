#set($spansubaddfield = "editSubDirField_"+$ID_DOCTRMUTAMA+"_"+$ID_REFER+"_")
#set($spanvalidatesubdir = "validate_subdir_"+$ID_DOCTRMUTAMA+"_"+$ID_REFER+"_")
#set($fieldvalidateSubDir = "fieldvalidateSubDir_"+$ID_DOCTRMUTAMA+"_"+$ID_REFER+"_")

<table width="100%" align="right" border="0"  cellspacing="0" cellpadding="0">
<tr>
<td>
<fieldset>
<input type="hidden" id="addSubDirID_REFER" name="addSubDirID_REFER" value="$ID_REFER" >
<input type="hidden" id="addSubDirLAYER" name="addSubDirLAYER" value="$LAYER" >
<input type="hidden" id="addSubDirID_DOCTRMUTAMA" name="addSubDirID_DOCTRMUTAMA" value="$ID_DOCTRMUTAMA" >

<input type="text" size="50" id="$spansubaddfield" name="$spansubaddfield" style="text-transform:uppercase;" 
onKeyUp="doDivAjaxCall3$formname('$spanvalidatesubdir','validateSubDir','TAJUK='+this.value+'&ID_REFER=$ID_REFER&ID_DOCTRM=&ID_DOCTRMUTAMA=$ID_DOCTRMUTAMA&BIL=$BIL');"
value="" >

#set($divLoadList = "")
#set($divFolderTambah = "")
#if($ID_REFER!="")
	#set($divLoadList = "divSubFolder"+$ID_REFER)
	#set($divFolderTambah = "divSubFolderTambah"+$ID_REFER)
#else
	#set($divLoadList = "div_viewFolderUtama"+$ID_DOCTRMUTAMA)	
	#set($divFolderTambah = "divSubFolderTambahFirst"+$ID_DOCTRMUTAMA)
#end
<input type="button" id="cmdSimpanSub" name="cmdSimpanSub" value="Simpan" onClick="if(validateCheckSubDir('$ID_DOCTRMUTAMA','','$ID_REFER')==true){this.value='Sila Tunggu!'; this.disabled = true; doDivAjaxCall$formname('$divLoadList','SimpanAddSubDir','FLAG_SUB_OPENCLOSE=CLOSE&TAJUK=&FLAG_OPENCLOSE=&ID_DOCTRMUTAMA=$ID_DOCTRMUTAMA&ID_REFER=$ID_REFER&ID_DOCTRM=$ID_REFER&LAYER=$LAYER&AUTOLOAD=N&carianTerperinci='+$jquery('#carianTerperinci').val()+'&carianTerperinciLampiran='+$jquery('#carianTerperinciLampiran').val());   }" >
<input type="button" id="cmdBatalSimpanSub" name="cmdBatalSimpanSub" value="Batal" onClick="document.getElementById('$divFolderTambah').innerHTML=''" >
<br>
<span id="$spanvalidatesubdir"><input type="hidden" id="$fieldvalidateSubDir" name="$fieldvalidateSubDir" value="N" ></span>
</fieldset>
</td>
</tr>
</table>