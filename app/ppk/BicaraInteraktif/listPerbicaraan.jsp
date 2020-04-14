

#if($list_id_pegawai == "0" && $id_jawatan_login == "9")
	<div style="margin:5px" ><i><font color='red' class="blink" size="+1">Perhatian</font> <font size="+1">: Login anda tidak direkodkan sebagai pegawai perbicaraan. Sila hubungi BPICT atau etappsupport.</font></i></div>
#end

<fieldset style="margin-top:5px">
<legend>Carian</legend>
$htmlCarianBicara
<!--
<table width="100%" align="center" border="0" cellspacing="1" cellpadding="3">	
<tr>
<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"></td>
</tr>
<tr>
<td valign="top" >				
</td>			
<td valign="top" >
No. Fail	
</td>
<td valign="top" >
:
</td>
<td valign="top" ><input size="50" type="text" id="CT_NO_FAIL" name="CT_NO_FAIL" style="text-transform:uppercase;" value="$CT_NO_FAIL"></td>
</tr>
<tr>
<td valign="top" >				
</td>			
<td valign="top" >
Nama Simati	
</td>
<td valign="top" >
:
</td>
<td valign="top" ><input size="50" type="text" id="CT_NAMA_SIMATI" name="CT_NAMA_SIMATI" style="text-transform:uppercase;"  value="$CT_NAMA_SIMATI"></td>
</tr>
<tr>
<td valign="top" >				
</td>			
<td valign="top" >
</td>
<td valign="top" >
</td>
<td valign="top" >
<input type="button" id="cmdCariListCari" name="cmdCariListCari" value="Cari" onClick="doDivAjaxCall$formname('listPerbicaraan','cariListPerbicaraan','')" >
<input type="button" id="cmdCariListReset" name="cmdCariListReset" value="Reset" onClick="resetListPerbicaraan();" >
</td>
</tr>
</table>
-->
</fieldset>
<br />
<div style="margin:5px"><i><font color='blue'>Info</font> : Paparan senarai perbicaraan secara 'default' adalah mengikut tarikh perbicaraan (hari ini) dan pegawai bicara yang ditetapkan pada peringkat notis.</i></div>
<div style="margin:5px"><i><font color='blue'>Info</font> : Carian boleh dibuat untuk paparan perbicaraan yang lain merujuk kepada pegawai sedang log masuk.</i></div>
<div style="margin:5px"><i><font color='blue'>Info</font> : Bagi KPP Negeri, carian perbicaraan meliputi kesemua pegawai di seluruh negeri.</i></div>
<div style="margin:5px"><i><font color='blue'>Info</font> : Bagi KPP dan Pengarah HQ, carian perbicaraan meliputi kesemua pegawai di seluruh negara.</i></div>
<fieldset >	

	

	<table border="0" cellspacing="1" cellpadding="3" width="100%" > 
	#if($listPerbicaraan.size()>0)
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		   #parse("app/ppk/BicaraInteraktif/recordPageList.jsp")
		   </td>		     
	</tr>
	#end 
    
    
    #if($SuccessMesej!="")
    <tr >
	<td  align="right" valign="top" colspan="14" >
	<div class="info" id="success_listPerbicaraan">
		#if($SuccessMesej=="PermohonTukarPegawai")	
		
		Permohonan Tukar Pegawai Berjaya Didaftarkan
		
		#end
	</div>
	<script >
	$jquery("#success_listPerbicaraan").show().delay(3000).fadeOut();
	</script>
    </td>
    </tr>
	#end
    
    
    #if($listPerbicaraan.size()>0)
    <tr >
		   <td  align="right" valign="top" colspan="14" >
		  <a class="blue" href="javascript:openCloseMultipleTPfirst('','','','','','view_tukarpegawai_multiple','tukarpegawai_multiple','$command');"><u><b>
          <span id="icon_tukarpegawai_multiple" >>> </span>Skrin Tukar Pegawai (Pelbagai)<br /><br />
          </b></u></a>
          <input type="hidden" name="flag_tukarpegawai_multiple" id="flag_tukarpegawai_multiple" value="close" />
          <div id="view_tukarpegawai_multiple">
          
          </div>
          
		   </td>		     
	</tr>
    #end
    
    
	<tr class="table_header" >
		   <td   align="center" valign="top" width="5%">Bil.</td>
		   <td   align="left" valign="top" width="15%">No. Fail</td>
           <td   align="left" valign="top">Status Permohonan</td>
           <td   align="center" valign="top">Bil. Bicara</td>
		   <td   align="left" valign="top">Nama Simati</td>
		   <td   align="left" valign="top">Masa Bicara</td>
		   <td   align="center" valign="top">Tarikh Bicara</td>
		   <td   align="left" valign="top">Pegawai Pengendali</td>
           <td   align="left" valign="top">Keputusan Perbicaraan</td>
           <td   align="center" valign="top" width="10%">Tindakan
           <span id= "divSelectAllTP" style="display:none;" >
           <input type="checkbox" id="selectAllTP" name="selectAllTP" value="all" title="Pilih Semua Perbicaraan" onclick="checkTPMainCheckBox(this);" />
		   </span>	
           
           </td>
	</tr>
	#if($listPerbicaraan.size()>0)	
		#foreach($pr in $listPerbicaraan)
		<tr  >
			   <td class="$pr.rowCss"  align="center" valign="top" >$pr.BIL </td>
               <td class="$pr.rowCss"  align="left" valign="top" >$pr.NO_FAIL
               
                <div id="bulletAmaran$pr.ID_PERBICARAAN">   
                #set($adaMasalah = "")
                #if($pr.TOTAL_PERINTAH > 1)
                #set($adaMasalah = "Y")
                
                           <div style="border-top: 1px dotted red; font-size:95%" ><div><font color="red" ><span class="blink">PERHATIAN!</span> DIDAPATI DATA PERINTAH PADA PERBICARAAN INI BERTINDIH.</font></div></div>
                #end
                
                #if(($pr.BIL_BICARA < $pr.MAX_BIL_BICARA)  
                && $pr.FLAG_JENIS_KEPUTUSAN != "0" && $pr.FLAG_JENIS_KEPUTUSAN != "1" && $pr.FLAG_JENIS_KEPUTUSAN != "2"
                )
                #set($adaMasalah = "Y")
                
                           <div style="border-top: 1px dotted red; font-size:95%" ><div ><font color="red"  ><span class="blink">PERHATIAN!</span> DIDAPATI TIADA KEPUTUSAN 'TANGGUH' PADA PERBICARAAN BILANGAN $pr.BIL_BICARA INI, SEDANGKAN PERBICARAAN DISETERUSNYA TELAH DIDAFTARKAN.</font></div></div>
                #end
                
               
                
                
                #if($adaMasalah == "Y")    
                   	<script>document.getElementById('bulletAmaran$pr.ID_PERBICARAAN').style.display = "";</script>
                #else    
                    <script>document.getElementById('bulletAmaran$pr.ID_PERBICARAAN').style.display = "none";</script>
                #end    
                </div>  
                
                 #if($pr.REKOD_TUKARPEGAWAI > 0)
                	<div style="border-top: 1px dotted blue; font-size:95%" ><div ><font color="blue"  ><span class="blink">INFO!</span> TERDAPAT PERMOHONAN TUKAR PEGAWAI PERBICARAAN YANG SEDANG DIPROSES.</font></div></div>
                #end
                  
               
               #if($pr.JAGAAN_MATCH == 0)
                <br />
                <div style="border-top: 1px dotted red; font-size:95%" ><font color="red" ><span class="blink">PERHATIAN!</span> DIDAPATI DAERAH MOHON UNTUK PERMOHONAN INI BUKAN DIBAWAH DAERAH JAGAAN UNIT ANDA, SILA MEMDAFTAR PERMOHONAN MEMBANTU UPP <a class="red" href="javascript:goToBU()"><u> DISINI</u></a></font></div>
                #end

               
               </td>
               <td class="$pr.rowCss"  align="left" valign="top" >$pr.STATUS_PERMOHONAN</td>
               <td class="$pr.rowCss"  align="center" valign="top" >$pr.BIL_BICARA</td>
               <td class="$pr.rowCss"  align="left" valign="top" >$pr.NAMA_SIMATI</td>
               <td class="$pr.rowCss"  align="left" valign="top" >$pr.MASA_BICARA</td>
               <td class="$pr.rowCss"  align="center" valign="top" >$pr.TARIKH_BICARA</td>	
               <td class="$pr.rowCss"  align="left" valign="top" >
               
               #if($pr.ID_PEGAWAIBARU != "")
               $pr.NAMA_PEGAWAI_BARU 
                   #if($pr.NAMA_PEGAWAI_BARU != $pr.PEG_PENGENDALI) 
                    <br />(Asal : $pr.PEG_PENGENDALI)
                   #end
               #else
               $pr.PEG_PENGENDALI
               #end
               
               
               
               
               </td>		
               <td class="$pr.rowCss"  align="left" valign="top" >$pr.KETERANGAN_KEPUTUSAN</td>		   
			   <td class="$pr.rowCss"  align="center" valign="top">
			   <a href="javascript:doDivAjaxCall$formname('div_viewPerbicaraan','viewPerbicaraan','ID_PEMOHON=$pr.ID_PEMOHON&ID_SIMATI=$pr.ID_SIMATI&ID_PERBICARAAN=$pr.ID_PERBICARAAN&ID_PERMOHONAN=$pr.ID_PERMOHONAN&ID_PERMOHONANSIMATI=$pr.ID_PERMOHONANSIMATI')"><img title="Kemaskini" src="../img/edit.gif" border="0"></a>	  
               <span id= "divselectTP_$pr.ID_PERBICARAAN" style="display:none;" >
               <input type="checkbox" id="selectTP$pr.ID_PERBICARAAN" name="selectTP$pr.ID_PERBICARAAN" value="$pr.ID_PERBICARAAN" title="Pilih Perbicaraan" onclick="checkTPCheckBox('')"/> 	
               </span>	 
               <input type="hidden" id="listID_PERBICARAAN" name="listID_PERBICARAAN" value="$pr.ID_PERBICARAAN" /> 	
               <input type="hidden" id="listID_UNITPSK$pr.ID_PERBICARAAN" name="listID_UNITPSK$pr.ID_PERBICARAAN" value="$pr.ID_UNITPSK" /> 	
               <input type="hidden" id="listPEG_PENGENDALI$pr.ID_PERBICARAAN" name="listPEG_PENGENDALI$pr.ID_PERBICARAAN" value="$pr.PEG_PENGENDALI" /> 	
               <input type="hidden" id="listTARIKH_BICARA$pr.ID_PERBICARAAN" name="listTARIKH_BICARA$pr.ID_PERBICARAAN" value="$pr.TARIKH_BICARA" /> 	
               <input type="hidden" id="listMASA_BICARA$pr.ID_PERBICARAAN" name="listMASA_BICARA$pr.ID_PERBICARAAN" value="$pr.MASA_BICARA" /> 	
               <input type="hidden" id="listBIL_BICARA$pr.ID_PERBICARAAN" name="listBIL_BICARA$pr.ID_PERBICARAAN" value="$pr.BIL_BICARA" /> 	
               <input type="hidden" id="listNO_FAIL$pr.ID_PERBICARAAN" name="listNO_FAIL$pr.ID_PERBICARAAN" value="$pr.NO_FAIL" /> 
               <input type="hidden" id="listID_FAIL$pr.ID_PERBICARAAN" name="listID_FAIL$pr.ID_PERBICARAAN" value="$pr.ID_FAIL" /> 
               <input type="hidden" id="listID_PERMOHONANSIMATI$pr.ID_PERBICARAAN" name="listID_PERMOHONANSIMATI$pr.ID_PERBICARAAN" value="$pr.ID_PERMOHONANSIMATI" /> 
               <input type="hidden" id="listID_SIMATI$pr.ID_PERBICARAAN" name="listID_SIMATI$pr.ID_PERBICARAAN" value="$pr.ID_SIMATI" /> 
               <input type="hidden" id="listID_PERMOHONAN$pr.ID_PERBICARAAN" name="listID_PERMOHONAN$pr.ID_PERBICARAAN" value="$pr.ID_PERMOHONAN" /> 	
               <input type="hidden" id="listID_STATUS$pr.ID_PERBICARAAN" name="listID_STATUS$pr.ID_PERBICARAAN" value="$pr.ID_STATUS" /> 	
               <input type="hidden" id="listFLAG_JENIS_KEPUTUSAN$pr.ID_PERBICARAAN" name="listFLAG_JENIS_KEPUTUSAN$pr.ID_PERBICARAAN" value="$pr.FLAG_JENIS_KEPUTUSAN" /> 
               <input type="hidden" id="listTOTAL_PERINTAH$pr.ID_PERBICARAAN" name="listTOTAL_PERINTAH$pr.ID_PERBICARAAN" value="$pr.TOTAL_PERINTAH" /> 
               <input type="hidden" id="listMAX_BIL_BICARA$pr.ID_PERBICARAAN" name="listMAX_BIL_BICARA$pr.ID_PERBICARAAN" value="$pr.MAX_BIL_BICARA" /> 
               <input type="hidden" id="listID_NEGERI$pr.ID_PERBICARAAN" name="listID_NEGERI$pr.ID_PERBICARAAN" value="$pr.ID_NEGERI" /> 	
               <input type="hidden" id="listREKOD_TUKARPEGAWAI$pr.ID_PERBICARAAN" name="listREKOD_TUKARPEGAWAI$pr.ID_PERBICARAAN" value="$pr.REKOD_TUKARPEGAWAI" />
               <!--
               #set($divMesejLP = "")
               #if($pr.REKOD_TUKARPEGAWAI > 0)
                	#set($divMesejLP = "Terdapat")
               #end                	
               <div id="divMesejLP$pr.ID_PERBICARAAN"></div>
               -->
              
			   </td>	   
		</tr>
        <!--
        <tr>
        <td class="$pr.rowCss"></td>
        <td colspan="10" class="$pr.rowCss">
        xxxxxxxxxxxx
        </td>
        </tr>
        -->		
		#end
	#else
	<tr >
		   <td  align="left" valign="top" colspan="10" >Tiada Rekod</td>		     
	</tr>
	#end
	</table>
</fieldset>




#if($flagOpenTP == "Y")
<script>
openCloseMultipleTP('','','','','','view_tukarpegawai_multiple','tukarpegawai_multiple');
</script>
#end
