
<fieldset style="margin-top:5px">
<legend>Carian</legend>
$htmlCarianTukarPegawai
</fieldset>
<br />
<fieldset >	
	<table border="0" cellspacing="1" cellpadding="3" width="100%" > 
	#if($listPermohonanTukarPegawai.size()>0)
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		   #parse("app/ppk/BicaraInteraktif/recordPageList.jsp")
		   </td>		     
	</tr>
	#end 
    
     
    #if($listPermohonanTukarPegawai.size()>0)
    <tr >
		   <td  align="right" valign="top" colspan="14" >
		  <a class="blue" href="javascript:openCloseMultipleTPKfirst('view_tukarpegawaiKPP_multiple','tukarpegawaiKPP_multiple','$command');"><u><b>
          <span id="icon_tukarpegawaiKPP_multiple" >>> </span>Kelulusan Permohonan Tukar Pegawai (Pelbagai)<br /><br />
          </b></u></a>
          <input type="hidden" name="flag_tukarpegawaiKPP_multiple" id="flag_tukarpegawaiKPP_multiple" value="close" />
          <div id="view_tukarpegawaiKPP_multiple">
          
          </div>          
		   </td>		     
	</tr>
    #end
    
	<tr class="table_header" >
		   <td   align="center" valign="top" width="5%">Bil.</td>
		   <td   align="left" valign="top">No. Tukar Pegawai</td>
           <td   align="left" valign="top">No. Fail (Bil. Bicara)</td>
		   <td   align="left" valign="top">Waktu Bicara</td>
           <td   align="left" valign="top">Status Bicara</td>
		   <td   align="left" valign="top">Pegawai Asal</td>
           <td   align="left" valign="top">Pegawai Ganti</td>            
           <td   align="left" valign="top">Keputusan Tukar Pegawai</td>
           <td   align="left" valign="top">Catatan Pemohon</td>
           
           <td   align="center" valign="top" width="10%">Tindakan
            <span id= "divSelectAllTPK" style="display:none;" >
           <input type="checkbox" id="selectAllTPK" name="selectAllTPK" value="all" title="Pilih Semua Perbicaraan" onclick="checkTPMainCheckBoxK(this);" />
		   </span>	
           </td>
	</tr>
	#if($listPermohonanTukarPegawai.size()>0)	
		#foreach($pr in $listPermohonanTukarPegawai)
		<tr  >
			   <td class="$pr.rowCss"  align="center" valign="top" >$pr.BIL </td>
               <td class="$pr.rowCss"  align="left" valign="top" >$pr.NO_TUKARPEGAWAI</td>
               <td class="$pr.rowCss"  align="left" valign="top" >$pr.NO_FAIL ($pr.BIL_BICARA)</td>
               <td class="$pr.rowCss"  align="left" valign="top" >$pr.TARIKH_BICARA ($pr.MASA_BICARA)
               </td>
               <td class="$pr.rowCss"  align="left" valign="top" >$pr.STATUS_BICARA</td>	
               <td class="$pr.rowCss"  align="left" valign="top" >$pr.NAMA_PEGAWAI_ASAL</td>	
               <td class="$pr.rowCss"  align="left" valign="top" >$pr.NAMA_PEGAWAI_BARU<br />($pr.NAMA_NEGERI_PEGAWAI_GANTI)</td>	
               <td class="$pr.rowCss"  align="left" valign="top" >$pr.KETERANGAN_STATUS_TUKARPEGAWAI</td>
               <td class="$pr.rowCss"  align="left" valign="top" >$pr.CATATAN_PEMOHON</td>		   
			   <td class="$pr.rowCss"  align="center" valign="top">
			   <a href="javascript:document.getElementById('listPermohonanTukarPegawai').style.display = 'none';document.getElementById('cmdKembaliSenaraiTukarPegawai').style.display = '';doDivAjaxCall$formname('view_tukarpegawaiKPP','openSkrinTukarPegawai','div=view_tukarpegawaiKPP&ID_PERMOHONAN=$pr.ID_PERMOHONAN&ID_TUKARPEGAWAI=$pr.ID_TUKARPEGAWAI&FIELD_PK=ID_TUKARPEGAWAI&NAMA_TABLE=TBLPPKTUKARPEGAWAI&ID_FAIL=$pr.ID_FAIL&PERBICARAAN_EXP=$pr.PERBICARAAN_EXP&ID_SIMATI=$pr.ID_SIMATI&ID_PERMOHONANSIMATI=$pr.ID_PERMOHONANSIMATI&ID_PERBICARAAN=$pr.ID_PERBICARAAN&skrinName=tukarpegawaiKPP&openFrom=list')"><img title="Kemaskini" src="../img/edit.gif" border="0"></a>	   	
                
               <span id= "divselectTPK_$pr.ID_TUKARPEGAWAI" style="display:none;" >
               <input type="checkbox" id="selectTPK$pr.ID_TUKARPEGAWAI" name="selectTPK$pr.ID_TUKARPEGAWAI" value="$pr.ID_TUKARPEGAWAI" title="Pilih Permohonan" onclick="checkTPCheckBoxK('')"/> 	
               </span>	 
               <input type="hidden" id="listkID_TUKARPEGAWAI" name="listkID_TUKARPEGAWAI" value="$pr.ID_TUKARPEGAWAI" /> 	
               <input type="hidden" id="listkID_PERBICARAAN$pr.ID_TUKARPEGAWAI" name="listkID_PERBICARAAN$pr.ID_TUKARPEGAWAI" value="$pr.ID_PERBICARAAN" /> 	
               <input type="hidden" id="listkUSER_ID_PEGAWAIASAL$pr.ID_TUKARPEGAWAI" name="listkUSER_ID_PEGAWAIASAL$pr.ID_TUKARPEGAWAI" value="$pr.USER_ID_PEGAWAIASAL" />
               <input type="hidden" id="listkID_PELULUS$pr.ID_TUKARPEGAWAI" name="listkID_PELULUS$pr.ID_TUKARPEGAWAI" value="$pr.ID_PELULUS" /> 	
               <input type="hidden" id="listkID_PEGAWAIBARU$pr.ID_TUKARPEGAWAI" name="listkID_PEGAWAIBARU$pr.ID_TUKARPEGAWAI" value="$pr.ID_PEGAWAIBARU" />  
               <input type="hidden" id="listkID_NEGERIPEGAWAIBARU$pr.ID_TUKARPEGAWAI" name="listkID_NEGERIPEGAWAIBARU$pr.ID_TUKARPEGAWAI" value="$pr.ID_NEGERIPEGAWAIBARU" />  
               <input type="hidden" id="listkSTATUS_TUKARPEGAWAI$pr.ID_TUKARPEGAWAI" name="listkSTATUS_TUKARPEGAWAI$pr.ID_TUKARPEGAWAI" value="$pr.STATUS_TUKARPEGAWAI" />  
               <input type="hidden" id="listkUSER_ID_PEGAWAIBARU$pr.ID_TUKARPEGAWAI" name="listkUSER_ID_PEGAWAIBARU$pr.ID_TUKARPEGAWAI" value="$pr.USER_ID_PEGAWAIBARU" />  
               <input type="hidden" id="listkID_NEGERIPEGAWAIASAL$pr.ID_TUKARPEGAWAI" name="listkID_NEGERIPEGAWAIASAL$pr.ID_TUKARPEGAWAI" value="$pr.ID_NEGERIPEGAWAIASAL" />  
               <input type="hidden" id="listkID_PEGAWAIASAL$pr.ID_TUKARPEGAWAI" name="listkID_PEGAWAIASAL$pr.ID_TUKARPEGAWAI" value="$pr.ID_PEGAWAIASAL" />  
               <input type="hidden" id="listkID_PERMOHONAN$pr.ID_TUKARPEGAWAI" name="listkID_PERMOHONAN$pr.ID_TUKARPEGAWAI" value="$pr.ID_PERMOHONAN" />  
               <input type="hidden" id="listkID_PERMOHONANSIMATI$pr.ID_TUKARPEGAWAI" name="listkID_PERMOHONANSIMATI$pr.ID_TUKARPEGAWAI" value="$pr.ID_PERMOHONANSIMATI" />  
               <input type="hidden" id="listkID_FAIL$pr.ID_TUKARPEGAWAI" name="listkID_FAIL$pr.ID_TUKARPEGAWAI" value="$pr.ID_FAIL" />  
               <input type="hidden" id="listkCATATAN_PEMOHON$pr.ID_TUKARPEGAWAI" name="listkCATATAN_PEMOHON$pr.ID_TUKARPEGAWAI" value="$pr.CATATAN_PEMOHON" />                
               <input type="hidden" id="listkNO_FAIL$pr.ID_TUKARPEGAWAI" name="listkNO_FAIL$pr.ID_TUKARPEGAWAI" value="$pr.NO_FAIL" /> 
               <input type="hidden" id="listkTARIKH_BICARA$pr.ID_TUKARPEGAWAI" name="listkTARIKH_BICARA$pr.ID_TUKARPEGAWAI" value="$pr.TARIKH_BICARA" /> 
               <input type="hidden" id="listkMASA_BICARA$pr.ID_TUKARPEGAWAI" name="listkMASA_BICARA$pr.ID_TUKARPEGAWAI" value="$pr.MASA_BICARA" /> 
               <input type="hidden" id="listkBIL_BICARA$pr.ID_TUKARPEGAWAI" name="listkBIL_BICARA$pr.ID_TUKARPEGAWAI" value="$pr.BIL_BICARA" /> 
               <input type="hidden" id="listkPEG_PENGENDALI$pr.ID_TUKARPEGAWAI" name="listkPEG_PENGENDALI$pr.ID_TUKARPEGAWAI" value="$pr.NAMA_PEGAWAI_ASAL" /> 
               <input type="hidden" id="listkNO_TUKARPEGAWAI$pr.ID_TUKARPEGAWAI" name="listkNO_TUKARPEGAWAI$pr.ID_TUKARPEGAWAI" value="$pr.NO_TUKARPEGAWAI" />
               <input type="hidden" id="listkNAMA_PEGAWAI_BARU$pr.ID_TUKARPEGAWAI" name="listkNAMA_PEGAWAI_BARU$pr.ID_TUKARPEGAWAI" value="$pr.NAMA_PEGAWAI_BARU" />
                
                
			   </td>	   
		</tr>		
		#end
	#else
	<tr >
		   <td  align="left" valign="top" colspan="10" >Tiada Rekod</td>		     
	</tr>
	#end
	</table>
</fieldset>
<BR />



#if($flagOpenTPK == "Y")
<script>
openCloseMultipleTPK('','','','','','view_tukarpegawaiKPP_multiple','tukarpegawaiKPP_multiple');
</script>
#end

