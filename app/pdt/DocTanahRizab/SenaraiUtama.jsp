<fieldset id="div_rowPenggunaOnline">
<legend>Senarai Jenis Dokumen &nbsp;&nbsp; 

#if($editable=="Y")
<input type="button" id="cmdTambahTajukUtama" name="cmdTambahTajukUtama" value="Tambah" 
onClick="doDivAjaxCall$formname('div_rowFolderUtama','addTajukUtama','FlagCari=$FlagCari&ID_DOCTRMUTAMA=');" >
#end
</legend>


<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
<tr id="div_rowFolderUtama">
</tr>
</table>


<table width="100%">
<tr>
<td>

	<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($listFolderUtama.size()>0)
	<tr>
		   <td  align="left" valign="top" colspan="14" >
		   #parse("app/pdt/DocTanahRizab/record_paging_pu.jsp")
		   </td>	     
	</tr>
	#end 
	<tr class="table_header" >
		   <td width = "100%"   align="left" valign="top" width="100%">Jenis Dokumen</td> 
           <!-- <td width = "40%"   align="left" valign="top" width="100%">Bahagian/Urusetia</td>--> 
	</tr>
	#if($listFolderUtama.size()>0)
	
		#foreach($LFU in $listFolderUtama)
		<tr id="div_rowFolderUtama$LFU.ID_DOCTRMUTAMA">
			   <td class="$LFU.rowCss"  align="left" valign="top">
			   
			   &nbsp;
			   
			   #set($span1 = "span1listFolderUtama"+$LFU.BIL)
			   #if($editable=="Y")
			   <a href="javascript:doDivAjaxCall$formname('$span1','editMainDir','ID_DOCTRMUTAMA=$LFU.ID_DOCTRMUTAMA&BIL=$LFU.BIL');"><img title="Kemaskini Nama Sub Direktori" src="../img/edit.gif" border="0"></a>
	    	   <a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_senaraiUtama','deleteFolderUtama','ID_DOCTRMUTAMA=$LFU.ID_DOCTRMUTAMA&FlagCari=N&carianTerperinci='+$jquery('#carianTerperinci').val());}"><img title="Hapus Direktori"  src="../img/hapus.gif" border="0"></a>
	    		#end
			   
			   
			   
			   <span id="$span1" > 
			   <span onClick="doDivAjaxCall$formname('div_viewFolderUtama$LFU.ID_DOCTRMUTAMA','showAllFolder','NAMA_FOLDER=$LFU.TAJUK&FLAG_OPENCLOSE='+$jquery('#HID_OPENCLOSE_$LFU.ID_DOCTRMUTAMA').val()+'&ID_DOCTRMUTAMA=$LFU.ID_DOCTRMUTAMA&LAYER=1&AUTOLOAD=N&FlagCari=$FlagCari&MAX_LAYER=$LFU.MAX_LAYER');" >
			   <span class="font_tajuk_utama" ><u>$LFU.TAJUK</u></span>
			   </span>
			   <!--  
			   #if($LFU.JUMLAH_SUB!="0" && $LFU.JUMLAH_SUB!="")
			   <span onClick="doDivAjaxCall$formname('div_viewFolderUtama$LFU.ID_PANDANGANUNDANGUTAMA','showAllFolder','NAMA_FOLDER=$LFU.TAJUK&FLAG_OPENCLOSE='+$jquery('#HID_OPENCLOSE_$LFU.ID_PANDANGANUNDANGUTAMA').val()+'&ID_PANDANGANUNDANGUTAMA=$LFU.ID_PANDANGANUNDANGUTAMA&LAYER=1&AUTOLOAD=N&FlagCari=$FlagCari&MAX_LAYER=$LFU.MAX_LAYER');" >
			   <span class="font_tajuk_utama"><u>$LFU.TAJUK</u></span>
			   </span>
			   #else
			   <span class="font_tajuk_utama_wc">$LFU.TAJUK</span>
			   #end
			   -->
			   </span>
			   
			   <input type="hidden" id="countFolder_$LFU.ID_DOCTRMUTAMA" name="countFolder_$LFU.ID_DOCTRMUTAMA" value = "0"  >
			   <input type="hidden" id="countActFolder_$LFU.ID_DOCTRMUTAMA" name="countActFolder_$LFU.ID_DOCTRMUTAMA" value = "$LFU.TOTAL_FOLDER"  >
			   <input type="hidden" id="countLampiran_$LFU.ID_DOCTRMUTAMA" name="countLampiran_$LFU.ID_DOCTRMUTAMA" value = "0"  >
			   <input type="hidden" id="countActLampiran_$LFU.ID_ID_DOCTRMUTAMA" name="countActLampiran_$LFU.ID_DOCTRMUTAMA" value = "$LFU.TOTAL_LAMPIRAN"  >
			   <input type="hidden" id="HID_OPENCLOSE_$LFU.ID_DOCTRMUTAMA" name="HID_OPENCLOSE_$LFU.ID_DOCTRMUTAMA" value = "CLOSE"  >
			   <input type="hidden" id="HID_MAXLAYER_$LFU.ID_DOCTRMUTAMA" name="HID_MAXLAYER_$LFU.ID_DOCTRMUTAMA" value = "$LFU.MAX_LAYER"  >
			  
			  
			   </td>   
<!--		<td align="left" valign="top" class="$LFU.rowCss">$LFU.NAMA_SEKSYEN</td>-->	
		</tr>
        
        
        
		<tr  >
		<td align="left" valign="top" colspan="3" >		
		<div id="div_viewFolderUtama$LFU.ID_DOCTRMUTAMA">
		
		
		</div>			
		</td>	
		
		</tr>
		#end
	#else
	<tr >
		   <td  align="left" valign="top" colspan="3" >Tiada Rekod</td>
		     
	</tr>
	#end
	</table>

 
</td>
</tr>
</table>
</fieldset>


