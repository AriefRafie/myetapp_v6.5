#set($spansubaddfield = "editSubDirField_"+$ID_MESYUARATUTAMA+"_"+$ID_REFER+"_")
#set($spanvalidatesubdir = "validate_subdir_"+$ID_MESYUARATUTAMA+"_"+$ID_REFER+"_")
#set($fieldvalidateSubDir = "fieldvalidateSubDir_"+$ID_MESYUARATUTAMA+"_"+$ID_REFER+"_")
#set($spansubaddfield_COUNT = "editSubDirFieldCount_"+$ID_MESYUARATUTAMA+"_"+$ID_REFER+"_")

<table width="100%" align="right" border="0"  cellspacing="0" cellpadding="0">
<tr>
<td>
<fieldset>
<input type="hidden" id="addSubDirID_REFER" name="addSubDirID_REFER" value="$ID_REFER" >
<input type="hidden" id="addSubDirLAYER" name="addSubDirLAYER" value="$LAYER" >
<input type="hidden" id="addSubDirID_MESYUARATUTAMA" name="addSubDirID_MESYUARATUTAMA" value="$ID_MESYUARATUTAMA" >

<table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
			</tr>
            <tr>
				<td valign="top" >
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Item Mesyuarat
		 	
         	
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                <textarea style="text-transform:uppercase;" name="$spansubaddfield" 
				 id="$spansubaddfield" cols="80" rows="5" 
		 onKeyup="check_length(this,4000,'$spansubaddfield_COUNT');doDivAjaxCall3$formname('$spanvalidatesubdir','validateSubDir','TAJUK='+this.value+'&ID_REFER=$ID_REFER&ID_MESYUARATCONTENT=&ID_MESYUARATUTAMA=$ID_MESYUARATUTAMA&BIL=$BIL');" ></textarea>
         <span id="$spansubaddfield_COUNT"  ></span>
				</td>
				
			</tr>




#set($divLoadList = "")
#set($divFolderTambah = "")
#if($ID_REFER!="")
	#set($divLoadList = "divSubFolder"+$ID_REFER)
	#set($divFolderTambah = "divSubFolderTambah"+$ID_REFER)
#else
	#set($divLoadList = "div_viewFolderUtama"+$ID_MESYUARATUTAMA)	
	#set($divFolderTambah = "divSubFolderTambahFirst"+$ID_MESYUARATUTAMA)
#end
	<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >				
				</td>
				<td valign="top" >				
				</td>
				<td valign="top" >
<input type="button" id="cmdSimpanSub" name="cmdSimpanSub" value="Simpan" onClick="if(validateCheckSubDir('$ID_MESYUARATUTAMA','','$ID_REFER')==true){this.value='Sila Tunggu!'; this.disabled = true; doDivAjaxCall$formname('$divLoadList','SimpanAddSubDir','FLAG_SUB_OPENCLOSE=CLOSE&TAJUK=&FLAG_OPENCLOSE=&ID_MESYUARATUTAMA=$ID_MESYUARATUTAMA&ID_REFER=$ID_REFER&ID_MESYUARATCONTENT=$ID_REFER&LAYER=$LAYER&AUTOLOAD=Y&carianTerperinci='+$jquery('#carianTerperinci').val()+'&carianBahagian='+$jquery('#carianBahagian').val());   }" >
<input type="button" id="cmdBatalSimpanSub" name="cmdBatalSimpanSub" value="Batal" onClick="document.getElementById('$divFolderTambah').innerHTML=''" >
<br>
<span id="$spanvalidatesubdir"><input type="hidden" id="$fieldvalidateSubDir" name="$fieldvalidateSubDir" value="N" ></span>
</td>
</tr>
</table>
</fieldset>

</td>
</tr>
</table>
