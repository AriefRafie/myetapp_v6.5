#parse("app/ppt/frmLabelSet.jsp")
#parse("app/ppt/Sek4Paging.jsp")
#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")
#set($portal_role = "${session.getAttribute('myrole')}")

<br/>

<fieldset>
<legend><strong>Daftar Permohonan</strong></legend>


<fieldset>
<legend><strong>&nbsp;Maklumat Asas Permohonan</strong></legend>

		<table width="100%" cellpadding="0" border="0">
			<tr align="right">
				<td>PERMOHONAN KAUNTER</td>
			</tr>
		</table>
		
		<table width="100%" cellpadding="0" border="0">
			<tr>
				<td width="1%">&nbsp;</td>
				<td width="24%">Bil. Permohonan</td>
				<td width="1%">:</td>
				<td width="74%"><input type="text" name="txtBilPermohonan" value="$!txtBilPermohonan" size="15" class="disabled" readonly></td>
			</tr>
		
			<tr>
				<td>&nbsp;</td>
				<td>No. Fail Permohonan</td>
				<td>:</td>
				<td><input type="text" name="txtNoFail" value="$!txtNoFail" size="37" class="disabled" readonly></td>
			</tr>
		
			<tr>
				<td>&nbsp;</td>
				<td>No. Rujukan PTG</td>
				<td>:</td>
				<td><input type="text" name="txtNoRujukanPTG" style="text-transform:uppercase;" 
					onBlur="this.value=this.value.toUpperCase()" value="$!txtNoRujukanPTG" maxlength="30" size="37">
				</td>
			</tr>
		
			<tr>
				<td>&nbsp;</td>
				<td>No. Rujukan PTD</td>
				<td>:</td>
				<td><input type="text" name="txtNoRujukanPTD" style="text-transform: uppercase;"
					onBlur="this.value=this.value.toUpperCase()" value="$!txtNoRujukanPTD" maxlength="30" size="37">
				</td>
			</tr>
		
			<tr>
				<td>&nbsp;</td>
				<td>No. Rujukan UPT</td>
				<td>:</td>
				<td><input type="text" name="txtNoRujukanUPT" style="text-transform: uppercase;"
					onBlur="this.value=this.value.toUpperCase()" value="$!txtNoRujukanUPT" maxlength="30" size="37">
				</td>
			</tr>
		
			<tr>
				<td>&nbsp;</td>
				<td>Urusan&nbsp;</td>
				<td>:</td>
				<td>$!txtUrusanPengambilan</td>
			</tr>
			<tr>
				<td><font color="red">*</font></td>
				<td>Tarikh Permohonan Di Terima</td>
				<td>:</td>
				<td><input name="txdTarikhPermohonan" value="$!txdTarikhPermohonan" size="11" id="txdTarikhPermohonan" type="text"
					onblur="check_date(this);getJenisKodDaerah('$!userIdNeg','$!semak','$!edit')" /><img src="../img/calendar.gif"
					onclick="displayDatePicker('txdTarikhPermohonan',false,'dmy');">$!frmtdate</td>
			</tr>
		
			#if($userIdNeg=="10")
			
			#if($sorJenisKodDaerah=="1") 
				#set($checkJK1="checked")
				#set($checkJK2="") 
			#elseif($sorJenisKodDaerah=="2") 
				#set($checkJK1="")
				#set($checkJK2="checked") #else #set($checkJK1="") #set($checkJK2="")
			#end 
			
			<tr>
				<td>&nbsp;</td>
				<td>Jenis Kod Daerah</td>
				<td>:</td>
				<td>
					<input name="sorJenisKodDaerah" $!checkJK1 type="radio" value="1" onchange="onchangeJenisKodDaerah('$!semak','$!edit')" />PTG (Sebelum 01/10/2010) 
					<input name="sorJenisKodDaerah" $!checkJK2 type="radio" value="2" onchange="onchangeJenisKodDaerah('$!semak','$!edit')" />SPTB (Selepas 01/10/2010)
				</td>
			</tr>
			#end
		
		
		</table>
</fieldset>

<br/>
      
<fieldset id="changeKementerian">
<legend><strong>&nbsp;Maklumat Agensi / KJP</strong></legend>

		<table width="100%"  cellpadding="0" border="0">
        	
        	#set($checkPermohonanK="")
        	#set($checkPermohonanA="")
        	
        	#if($txtPermohonanOleh=="1")
        		#set($checkPermohonanK="checked")
        		#set($checkPermohonanA="")
        	#elseif($txtPermohonanOleh=="2")	
        		#set($checkPermohonanK="")
        		#set($checkPermohonanA="checked")
        	#end
        	
        	<tr>
        		<td><font color="red">*</font></td>
        		<td>Pemohon</td>
        		<td>:</td>
        		<td>
        			<input name="txtPermohonanOleh" type="radio" value="1" $!checkPermohonanK onchange="onchangePemohon()" />Kementerian
                	<input name="txtPermohonanOleh" type="radio" $!checkPermohonanA value="2" onchange="onchangePemohon()" />Agensi
                </td> 
        	</tr>
        	
        	<tr>
        		<td width="1%"><font color="red">*</font></td>
				<td width="24%">Kementerian</td>
				<td width="1%">:</td>
				<td width="74%">$!SelectKementerian</td>
			</tr>
           
           	<tr>
           		<td>&nbsp;</td>
				<td>Nama Agensi</td>
				<td>:</td>
				<td>$!SelectAgensi</td>
			</tr>
           
           	<tr>      
           		<td>&nbsp;</td>
                <td>Alamat</td>
                <td>:</td>
                <td><input type="text" readonly class="disabled" name="txtAlamat1" value="$!txtAlamat1" id="txtAlamat1" maxlength="80" size="60" /></td>
            </tr>
            
            <tr>
            	<td colspan="2">&nbsp;</td>
            	<td>&nbsp;</td>
                <td><input type="text" readonly class="disabled" name="txtAlamat2" value="$!txtAlamat2" id="txtAlamat2" maxlength="80" size="60" /></td>
            </tr>
            
            <tr>
            	<td colspan="2">&nbsp;</td>
            	<td>&nbsp;</td>
                <td><input type="text" readonly class="disabled" name="txtAlamat3" value="$!txtAlamat3" maxlength="80" id="txtAlamat3" size="60" /></td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
                <td>Poskod</td>
                <td>:</td>
                <td><input type="text" name="txtPoskod" size="4" readonly class="disabled" value="$!txtPoskod" maxlength="5" id="txtPoskod" /></td>
            </tr>
            
            <tr>
            	<td>&nbsp;</td>
                <td>Negeri</td>
                <td>:</td>
                <td>$!SelectNegeriKementerian</td>
            </tr>
            	
       </table>  
</fieldset>

<br/>
   
   <fieldset>
   <legend><strong>Maklumat Lengkap Projek</strong></legend>
       
       <table width="100%"  cellpadding="0" border="0">    
         	
         	<tr>
         		<td width="1%"><font color="red">*</font></td>
                <td width="24%">Peruntukan Projek</td>
                <td width="1%">:</td>
                <td width="74%">
                	<input name="sorFlagPeruntukan" type="radio" value="1" />Ada
                	<input name="sorFlagPeruntukan" type="radio" value="2" />Tiada
                </td> 
            </tr>
            
         	<tr>
         		<td><font color="red">*</font></td>
                <td>Negeri</td>
                <td>:</td>
                <td>$!SelectProjekNegeri</td>
             </tr>
          
        	<tr>
             	<td><font color="red">*</font></td>
             	#if($showJajahan=="yes")
             	<td>Jajahan</td>
             	#else
             	<td>Daerah</td>
             	#end
             	<td>:</td>
                <td>$SelectDaerah</td>
          	</tr>
             
         	<tr>
         		<td valign='top'><font color="red">*</font></td>
                <td valign='top'>Nama Projek</td>
                <td valign='top'>:</td>
           	   	<td><textarea name="txtTujuan" id="txtTujuan" cols="60%" rows="5">$!txtTujuan</textarea></td>
              </tr>
         	
        	<tr>
         	 	<td>&nbsp;</td>
                <td>No. Ruj Surat KJP</td>
                <td>:</td>
           	    <td><input type="text" name="txtNoSuratKJP" id="txtNoSuratKJP" maxlength="50" value="$!txtNoSuratKJP" size="35" /></td>
            </tr>
         	
         	<tr>
         		<td>&nbsp;</td>
                <td>Tarikh Surat KJP</td>
                <td>:</td>
                <td>
                	<input name="txdTarikhSuratKJP" value="$!txdTarikhSuratKJP" size="11" id="txdTarikhSuratKJP" type="text" onblur="check_date(this)" />
       		 		<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhSuratKJP',false,'dmy');">$!frmtdate
       		 	</td>
            </tr>
         	
            <tr>
            	<td>&nbsp;</td>
                <td>Tarikh Dikehendaki</td>
                <td>:</td>
            	<td><input name="txdTarikhKehendaki" value="$!txdTarikhKehendaki" size="11" id="txdTarikhKehendaki" type="text" onblur="check_date(this)" />
       		 		<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhKehendaki',false,'dmy');">$!frmtdate</td>
            </tr>
         	
        </table>	
        
        <table width="100%" border="0">
        	<tr><td>&nbsp;</td></tr>
        	<tr>
        		<td>$!perhatian3</td>
        	</tr>
        </table>
        
    </fieldset>



#if($dataPermohonan.size()!="0")    

<br/>
<input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>

    <div id="TabbedPanels1" class="TabbedPanels">
      <ul class="TabbedPanelsTabGroup">
        <li class="TabbedPanelsTab" onClick="javascript:setSelected(0);" tabindex="1">Maklumat Tanah Terlibat</li>
        <li class="TabbedPanelsTab" onClick="javascript:setSelected(1);" tabindex="1">Senarai Dokumen</li>
        #if($currentSuburusan!="53")
        <li class="TabbedPanelsTab" onClick="javascript:setSelected(2);" tabindex="1">Senarai Semakan</li>
        #end
      </ul>
      
      <div class="TabbedPanelsContentGroup">
        <div class="TabbedPanelsContent">
        	<fieldset>
        	<legend><strong>Maklumat Tanah Terlibat</strong></legend>

				
                <table width="100%"  cellpadding="0" cellspacing="2" border="0">   
                	<tr>
                    	
                    	<td width="30%" align="left"><input type="button" name="cmdTambah" value ="Tambah" onClick="javascript:tambah();"></td>
    					<!-- #if($currentStatus=="11" || $currentStatus=="113")
    					#else
    					<td width="30%">&nbsp;</td>
    					#end -->
    						
    					
    					<td width="70%" align="right">Carian No.LOT/No.PT :&nbsp;<input type="text" name="carianNoLot" value="$!carianNoLot" maxlength="20" size="20"   ><a href="javascript:cariLOT('$!id_permohonan')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanLOT('$!id_permohonan')">&nbsp;<u>KOSONGKAN</u></a></td>
    					
    				</tr>
    			</table>
                
                #if($saiz_listTanah > 5)
	<div style="width:100%;height:100;overflow:auto"> 
	#end
                
 	<table width="100%"  cellpadding="0" cellspacing="2" border="0">     
                    
		<tr class="table_header">
        	<td width="4%" align="center"><b>No</b></td>
          	<td width="46%"><b>&nbsp;Bandar/Pekan/Mukim</b></td>
           	<td width="50%"><b>No.LOT/No.PT</b></td> 
       	</tr>
                    
   					
        #if($saiz_listTanah!=0)
	        #foreach($listTanah in $listMaklumatTanah)
	                    
	        #set( $i = $velocityCount )
	         	#if ( ($i % 2) != 1 )
	             	#set( $row = "row2" )
	         	#else
	               	#set( $row = "row1" )
	         	#end
	                   
	       	<tr>
	            <td class="$row" align="center">$!listTanah.bil</td>
	            <td class="$row"><a href="javascript:viewMaklumat('$listTanah.id_hakmilik')"><font color="blue">$!listTanah.nama_mukim</font></a></td>
	            <td class="$row">$!listTanah.no_lotpt</td>
	           	<tr>
	        #end
        #else
            <tr>
               	<td colspan="2">Tiada rekod</td>
            </tr>
        #end
        
   	</table>   
                
    #if($saiz_listTanah > 5)
    </div>
    #end
                     	
          </fieldset>	
          
          	<table width="100%" border="0">
				<tr>
					<td>$!perhatian7</td>
				</tr>
			</table>
			
        </div>
        
        <div class="TabbedPanelsContent">
        	<fieldset>
        		<legend><strong>&nbsp;Senarai Dokumen yang Disertakan</strong></legend>
			
			<!-- #if($currentStatus=="11" || $currentStatus=="113")#end  -->
			
				<table width="100%"  cellpadding="0" border="0">
                	<tr>
                    	<td colspan="4"><input type="button" name="cmdTambah2" value ="Tambah" onClick="javascript:tambahDokumen('$id_permohonan');"></td>
                    </tr>
                </table>
                
                <table width="100%"  cellpadding="0" cellspacing="2" border="0">   
                    <tr class="table_header">
                    	<td width="3" align="center"><b>No</b></td>
                    	<td width="27%"><b>Nama Dokumen</b></td>
                        <td width="34%"><b>Keterangan</b></td>
                        <td width="30%"><b>Dokumen Sokongan</b></td>
                        <!-- #if($currentStatus=="11" || $currentStatus=="113")#end --> 
                        #if($listD_size!=0)
                        <td width="6" align="center"><b>&nbsp;</b></td>
                        #end
                        
                    </tr>
              
         #if($listD_size!=0)
          
             #foreach($listD in $listDokumen)  
              #set($idDokumen=$listD.id_Dokumen)    
                   
                    #set( $i = $velocityCount )       	
         		#if ( ($i % 2) != 1 )
              		#set( $row = "row2" )
         		#else
               		#set( $row = "row1" )
         		#end
         		      
                    <tr>
                    	<td class="$row" align="center">$listD.bil</td>
                    	<td class="$row">$listD.tajuk</td>
                        <td class="$row">$listD.keterangan</td>
                       	<td class="$row"><a href="javascript:papar_Lampiran('$idDokumen')"><font color="blue">$listD.nama_Fail</font></a></td>
                    	<!-- #if($currentStatus=="11" || $currentStatus=="113")#end -->
                    	#if($listD_size!=0)
                    	<td class="$row" align="center"><input name="cmdHapus" type="button" value="Hapus" onClick="hapusDokumen('$!idDokumen')"></td>
                    	#end
                    	<!-- <img src="../img/hapus.gif" onclick="hapusDokumen('$idDokumen')"> -->
                    	
                    <tr>
             #end  
              		 
         #else
                	<tr>
                    	<td colspan="4">Tiada rekod</td>
                    </tr>
         #end
                    
                </table>        	
            </fieldset>	
            
            <table width="100%" border="0">
				<tr>
					<td>$!perhatian7</td>
				</tr>
			</table>
			
        </div>
      	
  #if($currentSuburusan!="53")	
        <div class="TabbedPanelsContent">
    	


#if($currentStatus=="11" || $currentStatus=="113" || $currentStatus=="138")
	#set($disCheck="")
	#set($namecb1="cbsemaks10")
	#set($namecb2="cbsemaks20")
#else
	#set($disCheck="disabled")
	#set($namecb1="cbsemaks10x")
	#set($namecb2="cbsemaks20x")
	<input type="hidden" name="cbsemaks10" id="cbsemaks10" value="1">
	<input type="hidden" name="cbsemaks20" id="cbsemaks20" value="1">
#end


<fieldset>
<legend><strong>Senarai Semakan</strong></legend>
<br/>
    <table width="100%" cellpadding="0" cellspacing="0">
   		<tr>
    		<td width="4%">&nbsp;</td>
    		<td width="1%" valign="top">#if($edit=="yes")<font color="red">*</font>#end</td>
    		<td width="3%"><input type="checkbox" value="1" $disCheck $chkmode name='$!namecb1' id='$!namecb1' $checked10 ></td>
      		<td width="3%">1.</td>
      		<td width="89%">Pelan Pengambilan Tanah yang lengkap.</td>
      			
  		</tr>
        
  		<tr>
    		<td>&nbsp;</td>
    		<td valign="top">#if($edit=="yes")<font color="red">*</font>#end</td>
    		<td><input type="checkbox" name='$!namecb2' $disCheck $chkmode value="1" id='$!namecb2' $checked20 ></td>
    		<td>2.</td>
    		<td>Pengesahan peruntukan yang mencukupi untuk membiayai kos pampasan kerosakan akibat kerja-kerja ukur dan kajian tanah.</td>
		</tr>
        
  </table>
</fieldset>
    
    		<table width="100%" border="0">
				<tr>
					<td>$!perhatian7</td>
				</tr>
			</table>
			
        </div>
  #end
      	
  </div> 
  </div>    
 
#end 


    <table width="100%"  cellpadding="0" border="0">
    	<tr align="center">
        	<td>
        	#if($dataPermohonan.size()!="0")
	
        		#if($edit=="yes")
        		<input name="cmdUpdate" type="button" value="Simpan" onClick="update_item('$id_permohonan')">
        		<input name="cmdBatal" type="button" value="Batal" onClick="batal_update('$id_permohonan')">
        		#end
       		
        		#if($edit=="no")	
        		
        			#if(($flagStatusOnline=="" || $flagStatusOnline=="0") && ($currentStatus=="138" && ($portal_role == "(PPT)KetuaPenolongPengarahUnit" || $portal_role == "(PPT)PengarahUnit" || $portal_role == "adminppt")) )
					<input type="button" name="cmdTolakPermohonan" value="Kembalikan Permohonan" onClick="javascript:tolakPermohonanOnline('$id_permohonan','yes')">
					#end
					
        			#if($currentStatus=="11" || $currentStatus=="113" || $currentStatus=="138")
        			#if(($checked10 == "checked" && $checked20 == "checked") && ($saiz_listTanah!=0))
          				#if($portal_role == "(PPT)KetuaPenolongPengarahUnit" || $portal_role == "(PPT)PengarahUnit" || $portal_role == "adminppt")
          					#if($flagStatusOnline=="" || $flagStatusOnline=="0")
          					<input name="cmdSah" type="button" value="Sahkan" onClick="sah('$id_permohonan')">
          					#end
          				#else
          					#if($flagSahkan!="1")
          					<img src="../img/emel.gif" title="Minta pengesahan juga akan dihantar melalui emel" >
          					<input name="cmdSah" type="button" value="Hantar Untuk Pengesahan" onClick="updateFlagSah('$id_permohonan')">
          					#end
          				#end
          			#end
          			#end
          			
          			#if(($flagSahkan!="1" && $flagSahkan!="2") 
					||($portal_role == "(PPT)KetuaPenolongPengarahUnit" || $portal_role == "(PPT)PengarahUnit" || $portal_role == "adminppt"))
					<input name="cmdKemaskini" type="button" value="Kemaskini" onClick="kemaskini()">
					#end
          			
          			#if($currentStatus!="11" && $currentStatus!="113" && $currentStatus!="138")
          			<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
          			#end
          			
          		<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()">
       			<!-- <input name="cmdCetak" type="button" value="Cetak"> -->	
          		#end	
          	#end
          		
          	#if($dataPermohonan.size()=="0")
                <input name="cmdSimpan" type="button" value="Simpan" onClick="add_item()">      						
                <input name="cmdKembali" type="button" value="Kembali" onClick="kembaliListFail()">
            #end
               
            </td>
        </tr>
    </table>   

<fieldset id="tableReport1" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        	<td ><a href="#" class="style2" onClick="javascript:cetakSuratKPTG('$!id_permohonan')"><font color="blue">Surat Iringan Kepada PTG</font></a></td>
      </tr>  
      <tr>
       	 	<td ><a href="#" class="style2" onClick="javascript:cetakLampiranA('$!id_permohonan','$!nama2Mukim')"><font color="blue">Lampiran A</font></a></td>
      </tr>   
    </table>
</fieldset>

<!-- <input type="hidden" name="command"> -->
<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="idPermohonan" value="$!idPermohonan">

<input type="hidden" name="id_semak" value="$!idSemak">
<input type="hidden" name="id_status" value="$!idstatus">
<input type="hidden" name="suburusan" value="$!idSuburusan">
<input type="hidden" name="id_senaraiSemak" value="$!idSenaraiSemak">
<input type="hidden" name="id_fail" value="$!id_fail">
<input type="hidden" name="idKementerianA" value="$!idKementerianA">
<input type="hidden" name="idNegeriA" value="$!idNegeriA">
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<input type="hidden" name="ResultAdd" value="$!ResultAdd">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

</fieldset>


<script>
window.onload = submitForm;

function kembaliListFail(){
	document.${formName}.command.value = "elsefunction";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.seksyen4dan8.PermohonanSeksyen4dan8Module";
	document.${formName}.submit();
}
function doChangeKementerian(){
	document.${formName}.command.value = "daftarBaru";
	document.${formName}.command2.value = "doChangeKementerian";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.seksyen4dan8.PermohonanSeksyen4dan8Module";
	document.${formName}.submit();
}






function onchangePemohon() {
	document.${formName}.command.value = "onchangePemohon";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}
function onchangeJenisKodDaerah(mode,isEdit) {

	if(mode=="no" || (mode=="yes" && isEdit=="yes")){

		if(mode=="no"){
			document.${formName}.command.value = "onchangeJenisKodDaerah";
		}else{
			document.${formName}.command.value = "onchangeJenisKodDaerahUpdate";
		}
	
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
		document.${formName}.submit();
	}

}
function getJenisKodDaerah(userIdNeg,mode,isEdit) {

	if(mode=="no"){
		var dat1=document.${formName}.txdTarikhPermohonan;
	}else{
		var dat1=document.${formName}.edit_tarikh_permohonan;
	}

	if((dat1.value!="" && userIdNeg=="10") && (mode=="no" || (mode=="yes" && isEdit=="yes"))){
		if(isDate(dat1.value)==false){
			dat1.focus()
			return;
		}else{
			if(mode=="no"){
				document.${formName}.command.value = "getJenisKodDaerah";
			}else{
				document.${formName}.command.value = "getJenisKodDaerahUpdate";
			}
			document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
			document.${formName}.submit();
		}
	}
}
function kembaliScreenUtama(id_permohonan) {
	document.${formName}.tabId.value = "0";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semak";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}
function tolakPermohonanOnline(id_permohonan,formnew) {

	var w = "400";
	var h = "200";
	var left = (screen.width/2)-(w/2);
	var top = (screen.height/2)-(h/2);
	var url = "../x/${securityToken}/ekptg.view.ppt.FrmPopupTolakPermohonan?id_permohonan="+id_permohonan+"&formnew="+formnew;
	
	var hWnd = window.open(url, "Permohonan Online Dikembalikan", 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	
}
function viewMaklumat(id_hakmilik) {	
	document.${formName}.ScreenLocation.value = "changeTanah";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "edit_maklumat";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}
function updateFlagSah(id_permohonan) {	
	document.${formName}.ScreenLocation.value = "TabbedPanels1";	
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "updateFlagSah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
	document.${formName}.submit();	
}
function edit_maklumat(id_hakmilik) {
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "edit_maklumat";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}

function tambahDokumen(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "tambahDokumen";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
	document.${formName}.submit();
}
function tambah() {
	
	document.${formName}.ScreenLocation.value = "changeTanah";
	//document.${formName}.command.value = "tambah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4&command=tambah"; 
	document.${formName}.submit();
}
function kemaskini() {

	document.${formName}.ScreenLocation.value = "TabbedPanels1";

	document.${formName}.command.value = "kemaskini";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
	document.${formName}.submit();
}
function add_item()
{

	var dat1=document.${formName}.tarikh_surat
	var dat2=document.${formName}.tarikh_kehendaki
	var dat3=document.${formName}.txdTarikhPermohonan
	
	var radioSelected = false;
	for (i = 0;  i < ${formName}.new_flag_peruntukan.length;  i++){
	if (${formName}.new_flag_peruntukan[i].checked)
	radioSelected = true;
	}

	//current date
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = new Date(year, month, day);

	//tarikh surat kjp
	var KJP  = document.getElementById("tarikh_surat").value;
	var dt1   = parseInt(KJP.substring(0,2),10);
   	var mon1  = parseInt(KJP.substring(3,5),10);
   	var yr1   = parseInt(KJP.substring(6,10),10);
   	var dateKJP = new Date(yr1, mon1, dt1);

   	//tarikh kehendaki
   	var Khndk  = document.getElementById("tarikh_kehendaki").value;
	var dt2   = parseInt(Khndk.substring(0,2),10);
   	var mon2  = parseInt(Khndk.substring(3,5),10);
   	var yr2   = parseInt(Khndk.substring(6,10),10);
   	var dateKehendaki = new Date(yr2, mon2, dt2);

   	if(document.${formName}.txdTarikhPermohonan.value == ""){
		alert("Sila masukkan \"Tarikh Permohonan Di Terima\" terlebih dahulu.");
  		document.${formName}.txdTarikhPermohonan.focus(); 
		return;
	}
   	else if (dat3.value!="" && isDate(dat3.value)==false)
	{
		dat3.focus()
		return;
	}
   	else if(document.${formName}.kementerian.value == ""){
		alert("Sila pilih \"Kementerian\" terlebih dahulu.");
  		document.${formName}.kementerian.focus(); 
		return;
	}
	else if (!radioSelected){
		alert("Sila pilih \"Amaun peruntukan\" terlebih dahulu.");
		return;
	}
	else if(document.${formName}.projek_negeri.value == ""){
		alert("Sila pilih \"Negeri maklumat projek\" terlebih dahulu.");
  		document.${formName}.projek_negeri.focus(); 
		return;
	}
	else if(document.${formName}.daerah.value == ""){
		alert("Sila pilih \"Daerah / Jajahan\" terlebih dahulu.");
  		document.${formName}.daerah.focus(); 
		return;
	}
	else if(document.${formName}.tujuan.value == ""){
		alert("Sila masukkan \"Nama Projek\" terlebih dahulu.");
  		document.${formName}.tujuan.focus(); 
		return;
	}
/*	else if(document.${formName}.alamat1.value == ""){
		alert("Sila masukkan \"alamat\" terlebih dahulu.");
  		document.${formName}.alamat1.focus(); 
		return;
	}
	else if(document.${formName}.negeri.value == ""){
		alert("Sila pilih \" Negeri \" terlebih dahulu.");
  		document.${formName}.negeri.focus(); 
		return;
	}
	else if(document.${formName}.poskod.value == ""){
		alert("Sila masukkan \"poskod\" terlebih dahulu.");
  		document.${formName}.poskod.focus(); 
		return;
	}
	else if (document.${formName}.poskod.value != "" && document.${formName}.poskod.value.length < 5) {
		alert("Sila masukkan nombor \"Poskod\" alamat perayu dengan selengkapnya");
		document.${formName}.poskod.focus();
	}

	//validate tarikh
	else if(dateKJP > currentDate){
   		alert("Sila pastikan \"Tarikh Surat KJP\" tidak lebih dari tarikh hari ini.");
	 	document.${formName}.tarikh_surat.focus();
	 	return;
	} 
*/  else if(dateKehendaki < dateKJP){
   		alert("Sila pastikan \"Tarikh Dikehendaki\" tidak kurang dari tarikh surat KJP.");
	 	document.${formName}.tarikh_kehendaki.focus();
	 	return;
	}
/*   	else if(dateKehendaki < currentDate){
   		alert("Sila pastikan \"Tarikh Dikehendaki\" tidak kurang dari tarikh hari ini.");
	 	document.${formName}.tarikh_kehendaki.focus();
	 	return;
	}
*/   	else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
	else if (dat2.value!="" && isDate(dat2.value)==false)
	{
		dat2.focus()
		return;
	}
	else
	{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.method = "POST";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4&command=simpan";
		document.${formName}.submit();
	}	
}
function batal_update(id_permohonan)
{
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semak";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}
function batal() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.reset();
	document.${formName}.command.value = "batal";
	document.${formName}.submit();
}
function kembali() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.command.value = "batal";
	document.${formName}.submit();
}
function update_item(id_permohonan) {

	document.${formName}.ScreenLocation.value = "TabbedPanels1";

	var dat1=document.${formName}.edit_tarikh_surat
	var dat2=document.${formName}.edit_tarikh_kehendaki
	var dat3=document.${formName}.edit_tarikh_permohonan
	
	//current date
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = new Date(year, month, day);

	//tarikh surat kjp
	var KJP  = document.getElementById("edit_tarikh_surat").value;
	var dt1   = parseInt(KJP.substring(0,2),10);
   	var mon1  = parseInt(KJP.substring(3,5),10);
   	var yr1   = parseInt(KJP.substring(6,10),10);
   	var dateKJP = new Date(yr1, mon1, dt1);

   	//tarikh kehendaki
   	var Khndk  = document.getElementById("edit_tarikh_kehendaki").value;
	var dt2   = parseInt(Khndk.substring(0,2),10);
   	var mon2  = parseInt(Khndk.substring(3,5),10);
   	var yr2   = parseInt(Khndk.substring(6,10),10);
   	var dateKehendaki = new Date(yr2, mon2, dt2);

   	
    if(document.${formName}.edit_tarikh_permohonan.value == ""){
		alert("Sila masukkan \"Tarikh Permohonan Di Terima\" terlebih dahulu.");
  		document.${formName}.edit_tarikh_permohonan.focus(); 
		return;
	}
    else if (dat3.value!="" && isDate(dat3.value)==false)
	{
		dat3.focus()
		return;
	}
    else if(document.${formName}.editKementerian.value == ""){
		alert("Sila pilih \"Kementerian\" terlebih dahulu.");
  		document.${formName}.editKementerian.focus(); 
		return;
	}
   	else if(document.${formName}.flag_peruntukan.value == ""){
		alert("Sila pilih \"Amaun peruntukan\" terlebih dahulu.");
  		document.${formName}.flag_peruntukan.focus(); 
		return;
	}
   	else if(document.${formName}.editProjekNegeri.value == ""){
		alert("Sila pilih \"Negeri maklumat projek\" terlebih dahulu.");
  		document.${formName}.editProjekNegeri.focus(); 
		return;
	} 
   	else if(document.${formName}.editDaerah.value == ""){
		alert("Sila pilih \"Daerah / Jajahan\" terlebih dahulu.");
  		document.${formName}.editDaerah.focus(); 
		return;
	}
   	else if(document.${formName}.edit_tujuan.value == ""){
		alert("Sila masukkan \"Nama Projek\" terlebih dahulu.");
  		document.${formName}.edit_tujuan.focus(); 
		return;
	}

	//validate tarikh
/*	else if(dateKJP > currentDate){
   		alert("Sila pastikan \"Tarikh Surat KJP\" tidak lebih dari tarikh hari ini.");
	 	document.${formName}.edit_tarikh_surat.focus();
	 	return;
	} 
*/  else if(dateKehendaki < dateKJP){
   		alert("Sila pastikan \"Tarikh Dikehendaki\" tidak kurang dari tarikh surat KJP.");
	 	document.${formName}.edit_tarikh_kehendaki.focus();
	 	return;
	}
/*   	else if(dateKehendaki < currentDate){
   		alert("Sila pastikan \"Tarikh Dikehendaki\" tidak kurang dari tarikh hari ini.");
	 	document.${formName}.edit_tarikh_kehendaki.focus();
	 	return;
	}
*/  else if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
	else if (dat2.value!="" && isDate(dat2.value)==false)
	{
		dat2.focus()
		return;
	}
	else
	{  
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		document.${formName}.id_permohonan.value = id_permohonan;
		document.${formName}.command.value = "update";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
		document.${formName}.submit();	
 	}
}
function sah(id_permohonan) {
	
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "sah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
	document.${formName}.submit();	
}
function lulus(id_permohonan) {

	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "lulus";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
	document.${formName}.submit();	
}
function validatePoskod(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function RemoveNonNumeric( strString )
{
      var strValidCharacters = "1234567890";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}
function doChangeidNegeri() {

	document.${formName}.ScreenLocation.value = "changeKementerian";
	document.${formName}.CursorPoint.value = "daerah";
	
	//doAjaxCall${formName}("doChangeidNegeri");
	document.${formName}.command.value = "doChangeidNegeri";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
	document.${formName}.submit();
	
	}
/*function doChangeidNegeri2() {
	document.${formName}.command.value = "doChangeidNegeri2";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
	document.${formName}.submit();
		
	}	
*/	
function doChangeidKementerian() {
	
	document.${formName}.ScreenLocation.value = "changeKementerian";
	document.${formName}.CursorPoint.value = "agensi";
	
	//doAjaxCall${formName}("doChangeidKementerian");
	document.${formName}.command.value = "doChangeidKementerian";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
	document.${formName}.submit();	
	}
function doChangeidKementerianUPDATE() {

	document.${formName}.ScreenLocation.value = "changeKementerian";
	document.${formName}.CursorPoint.value = "editAgensi";
	
	//doAjaxCall${formName}("doChangeidKementerianUPDATE");
	document.${formName}.command.value = "doChangeidKementerianUPDATE";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
	document.${formName}.submit();
	}
/*
function doChangeidNegeriUPDATE() {
	document.${formName}.command.value = "doChangeidNegeriUPDATE";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
	document.${formName}.submit();
	
	}
*/	
function doChangeidProjekNegeriUPDATE() {

	document.${formName}.ScreenLocation.value = "changeKementerian";
	document.${formName}.CursorPoint.value = "editDaerah";
	//doAjaxCall${formName}("doChangeidProjekNegeriUPDATE");
	document.${formName}.command.value = "doChangeidProjekNegeriUPDATE";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
	document.${formName}.submit();	
	}
function doChangeAlamatAgensi() {
	document.${formName}.command.value = "doChangeAlamatAgensi";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
	document.${formName}.submit();
	}
/*
function doChangeidMukim() {

	document.${formName}.ScreenLocation.value = "changeKementerian";
	document.${formName}.CursorPoint.value = "tujuan";
	document.${formName}.command.value = "doChangeidMukim";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
	document.${formName}.submit();
	}
	
function doChangeidMukimUPDATE() {
	
	document.${formName}.ScreenLocation.value = "changeKementerian";
	document.${formName}.CursorPoint.value = "edit_tujuan";
	document.${formName}.command.value = "doChangeidMukimUPDATE";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4"; 
	document.${formName}.submit();
	
	}
*/
function papar_Lampiran(id_dokumen) {
    var url = "../servlet/ekptg.view.ppt.DisplayBlob?id="+id_dokumen;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function setSelected(tabId) {
	document.${formName}.tabId.value = tabId;	
}
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});

function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakLampiranA(idpermohonan,namaMukim) {

	//var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+NO_FAIL+"&idpermohonansimati="+idpermohonansimati+"&report=BorangDD&flagReport=B";
    /*var url = "../servlet/ekptg.report.ppt.LampiranASek4?idfail="+idfail+"&nama_mukim="+namaMukim;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
    */

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=LampiranASek4&flagReport=S&flagShowTarikhCetak=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
    
}
function cetakSuratKPTG(idpermohonan) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=SuratKpdPTGDariJKPTGIbuPejabat&selectNoFail=yes&flagShowTarikhCetak=yes";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cariLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "TabbedPanels1";

	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "semak";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}
function kosongkanLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "TabbedPanels1";

	document.${formName}.carianNoLot.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "semak";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4";
	document.${formName}.submit();
}
function hapusDokumen(iddokumen) {
	
	document.${formName}.ScreenLocation.value = "TabbedPanels1";

	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanUPTSek4&command=hapusDokumen&iddokumen="+iddokumen;
	document.${formName}.submit();
}
</script>

<script>
//Declaring valid date character, minimum year and maximum year
var dtCh= "/";
var minYear=1900;
var maxYear=2100;

function isInteger(s){
	var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}

function stripCharsInBag(s, bag){
	var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function daysInFebruary (year){
	// February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
		if (i==2) {this[i] = 29}
   } 
   return this
}

function isDate(dtStr){
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strDay=dtStr.substring(0,pos1)
	var strMonth=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
		alert("Format tarikh mestilah seperti ini, dd/mm/yyyy")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Sila masukkan bulan yang sah")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yang sah")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan tarikh yang sah")
		return false
	}
return true
}
</script>

<script type="text/javascript">
//var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
</script>
