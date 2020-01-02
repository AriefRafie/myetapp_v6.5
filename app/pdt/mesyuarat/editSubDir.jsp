#set($spansubview = "")
#set($spansubviewfield = "editSubDirField_"+$viewSubFoler.ID_MESYUARATUTAMA+"_"+$viewSubFoler.ID_REFER+"_"+$viewSubFoler.ID_MESYUARATCONTENT)
#set($spansubviewfield_COUNT = "eeditSubDirFieldCount_"+$viewSubFoler.ID_MESYUARATUTAMA+"_"+$viewSubFoler.ID_REFER+"_"+$viewSubFoler.ID_MESYUARATCONTENT)
#set($spanvalidatesubdir = "validate_subdir_"+$viewSubFoler.ID_MESYUARATUTAMA+"_"+$viewSubFoler.ID_REFER+"_"+$viewSubFoler.ID_MESYUARATCONTENT)
#set($fieldvalidateSubDir = "fieldvalidateSubDir_"+$viewSubFoler.ID_MESYUARATUTAMA+"_"+$viewSubFoler.ID_REFER+"_"+$viewSubFoler.ID_MESYUARATCONTENT)
#if($viewSubFoler.LAYER=="1")
			#set($spansubview = "span1listFolderSub_"+$viewSubFoler.LAYER+"_"+$viewSubFoler.ID_MESYUARATUTAMA+"_"+$BIL)
#else
			#set($spansubview = "span1listFolderSub_"+$viewSubFoler.ID_MESYUARATCONTENT+"_"+$BIL)
#end

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
                <textarea style="text-transform:uppercase;" name="$spansubviewfield" 
				 id="$spansubviewfield" cols="80" rows="5" 
		 onKeyup="check_length(this,4000,'$spansubviewfield_COUNT');doDivAjaxCall3$formname('$spanvalidatesubdir','validateSubDir','TAJUK='+this.value+'&ID_REFER=$viewSubFoler.ID_REFER&ID_MESYUARATCONTENT=$viewSubFoler.ID_MESYUARATCONTENT&ID_MESYUARATUTAMA=$viewSubFoler.ID_MESYUARATUTAMA&BIL=$BIL');" >$viewSubFoler.KETERANGAN</textarea>
		 	
         <span id="$spansubviewfield_COUNT"  ></span>	
         
         
         

</td>
</tr>
<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >				
				</td>
				<td valign="top" >				
				</td>
				<td valign="top" >

<input type="button" id="cmdSimpanSub" name="cmdSimpanSub" value="Simpan" onClick="if(validateCheckSubDir('$viewSubFoler.ID_MESYUARATUTAMA','$viewSubFoler.ID_MESYUARATCONTENT','$viewSubFoler.ID_REFER')==true){doDivAjaxCall$formname('$spansubview','SimpanSubDir','ID_REFER=$viewSubFoler.ID_REFER&ID_MESYUARATUTAMA=$viewSubFoler.ID_MESYUARATUTAMA&ID_MESYUARATCONTENT=$viewSubFoler.ID_MESYUARATCONTENT&LAYER=$viewSubFoler.LAYER&BIL=$BIL');}" >
<input type="button" id="cmdBatalSimpanSub" name="cmdBatalSimpanSub" value="Batal" onClick="doDivAjaxCall$formname('$spansubview','editSubDir','ID_MESYUARATCONTENT=$viewSubFoler.ID_MESYUARATCONTENT&LAYER=$viewSubFoler.LAYER&BIL=$BIL');" >
<br>
<span id="$spanvalidatesubdir"><input type="hidden" id="$fieldvalidateSubDir" name="$fieldvalidateSubDir" value="N" ></span>
</td>
</tr>
</table>