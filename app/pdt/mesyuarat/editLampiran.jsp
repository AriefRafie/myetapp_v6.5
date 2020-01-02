


<tr >
    
     <td class="$rowCss"   align="center" valign="top"  >$BIL</td> 
     
		
	<td class="$rowCss"  valign="top" align="left">
	<input type="hidden" id="tempFieldLampiran$viewLampiran.ID_MESYUARATUTAMA" name="tempFieldLampiran$viewLampiran.ID_MESYUARATUTAMA" >
	
	#set($spanlampiran = "span2listFolderLampiran_"+$viewLampiran.ID_MESYUARATDOKUMEN)
	#set($spanvalidate = "validate_lampiran_"+$viewLampiran.ID_MESYUARATDOKUMEN)	
	#set($fieldvalidatelampiran = "fieldvalidatelampiran_"+$viewLampiran.ID_MESYUARATDOKUMEN)
	#set($namaFieldlampiran = "editLampiranField_"+$viewLampiran.ID_MESYUARATDOKUMEN)


<input type="text" size="50" id="$namaFieldlampiran" name="$namaFieldlampiran" style="text-transform:uppercase;" 
value="$viewLampiran.NAMA_DOC" 
onKeyUp="doDivAjaxCall3$formname('$spanvalidate','validateLampiran','NAMA_DOC='+this.value+'&ID_MESYUARATDOKUMEN=$viewLampiran.ID_MESYUARATDOKUMEN&ID_MESYUARATUTAMA=$viewLampiran.ID_MESYUARATUTAMA&BIL=$BIL&rowCss=$rowCss');"
>
<span id="$spanvalidate"><input type="hidden" id="$fieldvalidatelampiran" name="$fieldvalidatelampiran" value="N" ></span>
	
	
	</td>
    <td  class="$rowCss"  align="center" valign="top" >
    <input type="button" id="cmdSimpanLampiran" name="cmdSimpanLampiran" value="Simpan" onClick="if(validateCheckLampiran('$viewLampiran.ID_MESYUARATUTAMA','$viewLampiran.ID_MESYUARATDOKUMEN')==true){doDivAjaxCall$formname('rowLampiran_$viewLampiran.ID_MESYUARATDOKUMEN','SimpanLampiran','ID_MESYUARATDOKUMEN=$viewLampiran.ID_MESYUARATDOKUMEN&ID_MESYUARATUTAMA=$viewLampiran.ID_MESYUARATUTAMA&BIL=$BIL&rowCss=$rowCss');}" >
<input type="button" id="cmdBatalSimpanLampiran" name="cmdBatalSimpanLampiran" value="Batal" onClick="doDivAjaxCall$formname('rowLampiran_$viewLampiran.ID_MESYUARATDOKUMEN','editLampiran','ID_MESYUARATDOKUMEN=$viewLampiran.ID_MESYUARATDOKUMEN&ID_MESYUARATUTAMA=$viewLampiran.ID_MESYUARATUTAMA&BIL=$BIL&rowCss=$rowCss');" >
   </td> 
	</tr>




