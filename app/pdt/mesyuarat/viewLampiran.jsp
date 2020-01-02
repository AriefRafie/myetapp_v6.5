   
<tr >
    
     <td class="$rowCss"   align="center" valign="top"  >$BIL</td> 
     
		
	<td class="$rowCss"  valign="top" align="left">
	<input type="hidden" id="tempFieldLampiran$viewLampiran.ID_MESYUARATUTAMA" name="tempFieldLampiran$viewLampiran.ID_MESYUARATUTAMA" >
	
	#set($span2 = "span2listFolderLampiran_"+$viewLampiran.ID_MESYUARATDOKUMEN+"_"+$BIL)

	<span class="font_tajuk_sub" id="$span2" style="cursor:pointer" >
	<u onClick="paparDoc($viewLampiran.ID_MESYUARATDOKUMEN);">$viewLampiran.NAMA_DOC</u>
	</span>
	
	
	
	</td>
    <td  class="$rowCss"  align="center" valign="top" >
    	<a href="javascript:doDivAjaxCall$formname('rowLampiran_$viewLampiran.ID_MESYUARATDOKUMEN','editLampiran','ID_MESYUARATDOKUMEN=$viewLampiran.ID_MESYUARATDOKUMEN&ID_MESYUARATUTAMA=$viewLampiran.ID_MESYUARATUTAMA&BIL=$BIL&rowCss=$rowCss');"><img title="Kemaskini Nama Sub Direktori" src="../img/edit.gif" border="0"></a>
	<a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('divSubLampiran$viewLampiran.ID_MESYUARATUTAMA','deleteLampiran','NAMA_DOC=$viewLampiran.NAMA_DOC&ID_MESYUARATDOKUMEN=$viewLampiran.ID_MESYUARATDOKUMEN&FLAG_LAMP_OPENCLOSE=CLOSE&TAJUK=&FLAG_OPENCLOSE=&ID_MESYUARATUTAMA=$viewLampiran.ID_MESYUARATUTAMA&LAYER=&AUTOLOAD=N');}"><img title="Hapus Lampiran"  src="../img/hapus.gif" border="0"></a>
	
    </td> 
	</tr>