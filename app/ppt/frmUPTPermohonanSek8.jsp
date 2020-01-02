
#foreach ($senarai in $PermohonanListView )
	#set($id_tugas=$senarai.id_tugas)
    #set($no_fail=$senarai.no_fail)
    #set($no_permohonan=$senarai.no_permohonan)
    #set($keterangan=$senarai.keterangan) 
    #set($nama_suburusan=$senarai.nama_suburusan)
    #set($tarikh_permohonan=$senarai.tarikh_permohonan) 
    #set($nama_kementerian=$senarai.nama_kementerian) 
    #set($nama_negeri=$senarai.projek_negeri) 
    #set($nama_daerah=$senarai.nama_daerah) 
    #set($tujuan=$senarai.tujuan) 
    #set($no_rujukan_surat=$senarai.no_rujukan_surat)
    #set($tarikh_kehendaki=$senarai.tarikh_kehendaki)
    #set($no_rujukan_ptd=$senarai.no_rujukan_ptd)
    #set($no_rujukan_ptg=$senarai.no_rujukan_ptg)
    #set($no_rujukan_upt=$senarai.no_rujukan_upt)
    #set($alamat1=$senarai.alamat1)
    #set($alamat2=$senarai.alamat2)
    #set($alamat3=$senarai.alamat3)
    #set($poskod=$senarai.poskod)
    #set($nama_bandar=$senarai.nama_bandar)
    #set($flag_peruntukan=$senarai.flag_peruntukan)
    #set($flag_segera=$senarai.flag_segera)
    #set($id_senaraisemak=$senarai.id_senaraisemak)   
    #set($semak1=$senarai.semak1)
    #set($semak2=$senarai.semak2)
    #set($semak3=$senarai.semak3)
    #set($semak4=$senarai.semak4)
    #set($semak5=$senarai.semak5)
    #set($semak6=$senarai.semak6)
    #set($semak7=$senarai.semak7)
#end

<!---------------------------------------------------- view mode -------------------------------------------------->
#if($semak=="yes")

<fieldset>
	<legend><strong>Daftar Permohonan Agensi</strong></legend>
<br/>
  
<fieldset>
    <legend><strong>Kategori Permohonan</strong></legend>   
	
     <table width="100%" border="0">
        <tr>
          	<td width="25%">No.Fail</td>
          	<td width="1%">:</td>
      		<td width="74%">$no_fail</td>
      	</tr>     	
        <tr>
          	<td>No.Permohonan</td>
          	<td>:</td>
        	<td>$no_permohonan</td>
        </tr>        
        <tr>
        	<td valign="top">Di Bawah Seksyen</td>
        	<td valign="top">:</td>
      		<td>$nama_suburusan</td>
      	</tr>      	
      	<tr>
      		<td>Tarikh Permohonan</td>
      		<td>:</td>
      		<td>$tarikh_permohonan</td>
      	</tr>      	
      	<tr>
      		<td>Status</td>
      		<td>:</td>
      		<td>$keterangan</td>
      	</tr>     	
    </table>
        
</fieldset>
 
<br/>
 
<fieldset>
  <legend><strong>Maklumat Agensi Memohon</strong></legend>
	
	<table width="100%" border="0" cellpadding="2" cellspacing="1">
       	<tr>
          	<td width="25%" valign="top">Kementerian</td>
          	<td width="1%" valign="top">:</td>
            <td width="74%" valign="top">$nama_kementerian</td>
        </tr>       
        <tr>
            <td>Nama Agensi</td>
            <td>:</td>
            <td>$idAgensi</td>
        </tr>        
        <tr>
            <td valign="top">Amaun Peruntukan</td>
            <td valign="top">:</td>
            <td valign="top">              
               #if ($flag_peruntukan == "1")
                 	YA
               #else
                	TIDAK
               #end                 
            </td>
        </tr>               
        <tr>
            <td valign="top">Pengambilan Segera?</td>
            <td valign="top">:</td>
        	<td valign="top">
                 #if ($flag_segera == "1")
                 	YA
                 #else
                 	TIDAK
                 #end                 
            </td> 
        </tr>        
        <tr>
            <td valign="top">Projek Negeri</td>
            <td valign="top">:</td>
        	<td valign="top">$!nama_negeri</td>
        </tr>        
        <tr>
            <td valign="top">Daerah / Jajahan</td>
            <td valign="top">:</td>
        	<td valign="top">$!nama_daerah</td>
        </tr>        
        <tr>
            <td height="30" valign="top">Tujuan</td>
            <td valign="top">:</td>
        	<td valign="top">$!tujuan</td>
        </tr>
        
        <tr>
            <td valign="top">No. Rujukan Kementerian</td>
            <td valign="top">:</td>
    		<td valign="top">$!no_rujukan_surat</td>
    	</tr>
             
       <tr>
            <td>Tarikh Surat KJP</td>
            <td>:</td>
            <td>$!tarikh_permohonan</td>
       </tr>
       <tr>
            <td>Tarikh Dikehendaki</td>
            <td>:</td>
            <td>$!tarikh_kehendaki</td>
       </tr>
       
               #if($currentStatus!=149)
               <tr>
               		<td>No. Rujukan PTD</td>
               		<td>:</td>
               		<td><input type="text" name="txtNoRujPTDx" id="txtNoRujPTD1x" style="text-transform:uppercase" value="$!no_rujukan_ptd" size="30" class=disabled readonly /></td>
               </tr>
               #else
               <tr>
               		<td>No. Rujukan PTD</td>
               		<td>:</td>
               		<td>$!no_rujukan_ptd</td>
               </tr>
               #end
               #if($currentStatus!=149)
               <tr>
               		<td>No. Rujukan PTG</td>
               		<td>:</td>
               		<td><input type="text" name="txtNoRujPTGx" id="txtNoRujPTGx" value="$!no_rujukan_ptg" size="30" class=disabled readonly style="text-transform:uppercase" /></td>
               </tr>
               #else
               <tr>
               		<td>No. Rujukan PTG</td>
               		<td>:</td>
               		<td>$!no_rujukan_ptg</td>
               </tr>
               #end
               #if($currentStatus!=149)
               <tr>
               		<td>No. Rujukan JKPTG Negeri</td>
               		<td>:</td>
               		<td><input type="text" name="txtNoRujJPTGNegerix" id="txtNoRujJPTGNegerix" value="$!no_rujukan_upt" size="30" class=disabled readonly style="text-transform:uppercase" /></td>
               </tr>
               #else
               <tr>
               		<td>No. Rujukan JKPTG Negeri</td>
               		<td>:</td>
               		<td>$!no_rujukan_upt</td>
               </tr>
               #end
 	  </table>
</fieldset>
 
<br/>
              
<fieldset>
  <legend><strong>Alamat Agensi Memohon</strong></legend>              
               
      <table width="100%" border="0" cellpadding="2" cellspacing="1">
               <tr>
               		<td valign="top">Alamat</td>
               		<td width="1%" valign="top">:</td>
               		<td width="74%" valign="top">$!alamat1<p>$!alamat2</p><p>$!alamat3</p></td>
               </tr>               
               <tr><td>&nbsp;</td></tr>               
               <tr>
               		<td>Negeri</td>
               		<td>:</td>
               		<td>$!nama_negeri</td>
               </tr>
               <tr>
               		<td>Poskod</td>
               		<td>:</td>
               		<td>$!poskod</td>
               </tr>
               <tr>
               		<td>Bandar</td>
               		<td>:</td>
               		<td>$!nama_bandar</td>
               </tr>
   </table>
</fieldset>  
		  
<br/>

<fieldset>
   <legend><strong>Senarai Semakan</strong></legend> 
   <input name="id_senaraisemak" type="hidden" value="$id_senaraisemak" />  
    
    
   #if($idSuburusan==52)              
     <table width="100%" border="0">
                      <tr>
                        <td width="2%">&nbsp;</td>
                        <td width="98%">
                        #if($semak1 == '1')
                        <input type="checkbox" name="semak1" value="1" checked="checked" readonly="readonly" disabled="disabled">
                        #else
                        <input type="checkbox" name="semak1"  value="1" readonly="readonly" disabled="disabled">
                        #end
                          1. Pelan Pengambilan Tanah yang lengkap.</td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>
                        #if($semak2 == '1')
                          <input type="checkbox" name="semak2" value="1" checked="checked" readonly="readonly" disabled="disabled">
                        #else
                        <input type="checkbox" name="semak2"  value="1" readonly="readonly" disabled="disabled">
                        #end
                          2. Sijil Carian Rasmi/ Persendirian yang terkini.</td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>
                        #if($semak3 == '1')
                        <input type="checkbox" name="semak3" value="1" checked="checked" readonly="readonly" disabled="disabled">
                        #else
                        <input type="checkbox" name="semak3"  value="1" readonly="readonly" disabled="disabled">
                        #end
                          3. Ulasan dari Jabatan-Jabatan Teknikal. </td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>
                        #if($semak4 == '1')
                        <input type="checkbox" name="semak4" value="1" checked="checked" readonly="readonly" disabled="disabled">
                        #else
                        <input type="checkbox" name="semak5"  value="1" readonly="readonly" disabled="disabled">
                        #end
                          4. Ulasan dari Jabatan Alam Sekitar.</td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>
                        #if($semak5 == '1')
                        <input type="checkbox" name="semak5" value="1" checked="checked" readonly="readonly" disabled="disabled">
                        #else
                        <input type="checkbox" name="semak5"  value="1" readonly="readonly" disabled="disabled">
                        #end
                          5. Persetujuan Jawatankuasa Pembangunan Daerah atau Jawatankuasa seumpamanya. </td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>
                        #if($semak6 == '1')
                        <input type="checkbox" name="semak6" value="1" checked="checked" readonly="readonly" disabled="disabled">
                        #else
                        <input type="checkbox" name="semak6"  value="1" readonly="readonly" disabled="disabled">
                        #end
                          6. Pengesahan peruntukan yang mencukupi termasuk kos penandaan dan lain-lain kos.</td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>
                        #if($semak7 == '1')
                        <input type="checkbox" name="semak7" value="1" checked="checked" readonly="readonly" disabled="disabled">
                        #else
                        <input type="checkbox" name="semak7"  value="1" readonly="readonly" disabled="disabled">
                        #end
                          7. Surat Perakuan segera (Borang I)</td>
                      </tr>
   </table>
   
  #end
  
  #if($idSuburusan==51)
  	<table width="100%" cellpadding="0" cellspacing="0" border="0">
   		<tr>
    		<td width="3%">&nbsp;</td>
    		<td align="justify" width="4%">
    		#if($semak1 == '1')
    		<input type="checkbox" value="1" name="semak10" checked disabled="disabled">
      		#else
      		<input type="checkbox" value="1" name="semak10" disabled="disabled">
      		#end
      		</td>
      		<td width="3%">1.</td>
      		<td width="80%">Pelan Pengambilan Tanah yang lengkap.</td>
      		<td width="10%">&nbsp;</td>
  		</tr>
        
  		<tr>
    		<td>&nbsp;</td>
    		<td>
    		#if($semak2 == '1')
    		<input type="checkbox" name="semak20" checked value="1" disabled="disabled" >
      		#else
      		<input type="checkbox" name="semak20" value="1" disabled="disabled" >
      		#end
      		</td>
      		<td>2.</td>
      		<td>Pengesahan peruntukan yang mencukupi untuk membiayai kos pampasan 
                   kerosakan akibat kerja-kerja ukur dan kajian tanah.</td>
 			<td>&nbsp;</td>
 		</tr>
        
  	</table>
  #end  
</fieldset>

              
#end <!-- close for semak -->

<!---------------------------------------------------- edit mode -------------------------------------------------->

#if($edit=="yes")

<fieldset>
<legend><strong>Daftar Permohonan Agensi</strong></legend>

    <fieldset>
        <legend><strong>Kategori Permohonan</strong></legend>   
	
     <table width="100%" border="0">
        <tr>
          	<td width="25%">No.Fail</td>
          	<td width="1%">:</td>
      		<td width="74%">$!no_fail</td>
      	</tr>     	
        <tr>
          	<td>No.Permohonan</td>
          	<td>:</td>
        	<td>$!no_permohonan</td>
        </tr>        
        <tr>
        	<td valign="top">Di Bawah Seksyen</td>
        	<td valign="top">:</td>
      		<td>$!nama_suburusan</td>
      	</tr>     	
      	<tr>
      		<td>Tarikh Permohonan</td>
      		<td>:</td>
      		<td>$!tarikh_permohonan</td>
      	</tr>      	
      	<tr>
      		<td>Status</td>
      		<td>:</td>
      		<td>$!keterangan</td>
      	</tr>     	
    </table>      
</fieldset>
 
<br/>
 
<fieldset>
  <legend><strong>Maklumat Agensi Memohon</strong></legend>
	
	<table width="100%" border="0" cellpadding="2" cellspacing="1">
       	<tr>
          	<td width="25%" valign="top">Kementerian</td>
          	<td width="1%" valign="top">:</td>
            <td width="74%" valign="top">$!nama_kementerian</td>
        </tr>
        
        <tr>
            <td>Nama Agensi</td>
            <td>:</td>
            <td>$idAgensi</td>
        </tr>
        
        <tr>
            <td valign="top">Amaun Peruntukan</td>
            <td valign="top">:</td>
            <td valign="top">              
               #if ($flag_peruntukan == "1")
                 	YA
               #else
                	TIDAK
               #end                 
            </td>
        </tr>
               
        <tr>
            <td valign="top">Pengambilan Segera?</td>
            <td valign="top">:</td>
        	<td valign="top">
                 #if ($flag_segera == "1")
                 	YA
                 #else
                 	TIDAK
                 #end                 
            </td> 
        </tr>
        
        <tr>
            <td valign="top">Projek Negeri</td>
            <td valign="top">:</td>
        	<td valign="top">$!nama_negeri</td>
        </tr>
        
        <tr>
            <td valign="top">Daerah / Jajahan</td>
            <td valign="top">:</td>
        	<td valign="top">$!nama_daerah</td>
        </tr>
        
        <tr>
            <td height="30" valign="top">Tujuan</td>
            <td valign="top">:</td>
        	<td valign="top">$!tujuan</td>
        </tr>
        
        <tr>
            <td valign="top">No. Rujukan Kementerian</td>
            <td valign="top">:</td>
    		<td valign="top">$!no_rujukan_surat</td>
    	</tr>
             
               <tr>
               		<td>Tarikh Surat KJP</td>
               		<td>:</td>
               		<td>$!tarikh_permohonan</td>
               </tr>
               <tr>
               		<td>Tarikh Dikehendaki</td>
               		<td>:</td>
               		<td>$!tarikh_kehendaki</td>
               </tr>
               <tr>
               		<td><font color="red">*</font>No. Rujukan PTD</td>
               		<td>:</td>
               		<td><input type="text" name="txtNoRujPTD" id="txtNoRujPTD1x" style="text-transform:uppercase" onBlur="this.value=this.value.toUpperCase();" value="$!no_rujukan_ptd" size="30"  /></td>
               </tr>
               <tr>
               		<td><font color="red">*</font>No. Rujukan PTG</td>
               		<td>:</td>
               		<td><input type="text" name="txtNoRujPTG" id="txtNoRujPTGx" value="$!no_rujukan_ptg" size="30" style="text-transform:uppercase" onBlur="this.value=this.value.toUpperCase();" /></td>
               </tr>
               <tr>
               		<td><font color="red">*</font>No. Rujukan JKPTG Negeri</td>
               		<td>:</td>
               		<td><input type="text" name="txtNoRujJPTGNegeri" id="txtNoRujJPTGNegerix" value="$!no_rujukan_upt" size="30" style="text-transform:uppercase" onBlur="this.value=this.value.toUpperCase();" /></td>
               </tr>
 	  </table>
</fieldset>
 
<br/>
   
<fieldset>
  <legend><strong>Alamat Agensi Memohon</strong></legend>              
               
      <table width="100%" border="0" cellpadding="2" cellspacing="1">
               <tr>
               		<td valign="top">Alamat</td>
               		<td width="1%" valign="top">:</td>
               		<td width="74%" valign="top">$!alamat1<p>$!alamat2</p><p>$!alamat3</p></td>
               </tr>
               
               <tr><td>&nbsp;</td></tr>
               
               <tr>
               		<td>Negeri</td>
               		<td>:</td>
               		<td>$!nama_negeri</td>
               </tr>
               <tr>
               		<td>Poskod</td>
               		<td>:</td>
               		<td>$!poskod</td>
               </tr>
               <tr>
               		<td>Bandar</td>
               		<td>:</td>
               		<td>$!nama_bandar</td>
               </tr>
   </table>
</fieldset> 

<br/>
    
  
		  <fieldset>
          <legend><strong>Senarai Semakan</strong></legend>   
             <p>         
                    <input name="id_senaraisemak" type="hidden" value="$id_senaraisemak" />
               
         #if($idSuburusan==52)      
               <table width="100%" border="0">
                  <tr>
                    <td width="2%">&nbsp;</td>
                    <td width="98%">
                    #if($semak1 == '1')
                    <input type="checkbox" name="semak1" value="1" checked="checked">
                    #else
                    <input type="checkbox" name="semak1"  value="1">
                    #end
                      1. Pelan Pengambilan Tanah yang lengkap.</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>
                    #if($semak2 == '1')
                      <input type="checkbox" name="semak2" value="1" checked="checked">
                    #else
                    <input type="checkbox" name="semak2"  value="1">
                    #end
                      2. Sijil Carian Rasmi/ Persendirian yang terkini.</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>
                    #if($semak3 == '1')
                    <input type="checkbox" name="semak3" value="1" checked="checked">
                    #else
                    <input type="checkbox" name="semak3"  value="1">
                    #end
                      3. Ulasan dari Jabatan-Jabatan Teknikal. </td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>
                    #if($semak4 == '1')
                    <input type="checkbox" name="semak4" value="1" checked="checked">
                    #else
                    <input type="checkbox" name="semak4"  value="1">
                    #end
                      4. Ulasan dari Jabatan Alam Sekitar.</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>
                    #if($semak5 == '1')
                    <input type="checkbox" name="semak5" value="1" checked="checked">
                    #else
                    <input type="checkbox" name="semak5" value="1">
                    #end
                      5. Persetujuan Jawatankuasa Pembangunan Daerah atau Jawatankuasa seumpamanya. </td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>
                    #if($semak6 == '1')
                    <input type="checkbox" name="semak6" value="1" checked="checked">
                    #else
                    <input type="checkbox" name="semak6"  value="1">
                    #end
                      6. Pengesahan peruntukan yang mencukupi termasuk kos penandaan dan lain-lain kos.</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>
                    #if($semak7 == '1')
                    <input type="checkbox" name="semak7" value="1" checked="checked">
                    #else
                    <input type="checkbox" name="semak7"  value="1">
                    #end
                      7. Surat Perakuan segera (Borang I)</td>
                  </tr>
                </table>
            #end
           
 #if($idSuburusan==51)   
          		<table width="100%" cellpadding="0" cellspacing="0" border="0">
   		<tr>
    		<td width="3%">&nbsp;</td>
    		<td align="justify" width="4%">
    		#if($semak1 == '1')
    		<input type="checkbox" value="1" name="semak1" checked>
      		#else
      		<input type="checkbox" value="1" name="semak1">
      		#end
      		</td>
      		<td width="3%">1.</td>
      		<td width="80%">Pelan Pengambilan Tanah yang lengkap.</td>
      		<td width="10%">&nbsp;</td>
  		</tr>
        
  		<tr>
    		<td>&nbsp;</td>
    		<td>
    		#if($semak2 == '1')
    		<input type="checkbox" value="1" name="semak2" checked>
    		#else
    		<input type="checkbox" value="1" name="semak2">
    		#end
    		</td>
    		<td>2.</td>
      		<td>Pengesahan peruntukan yang mencukupi untuk membiayai kos pampasan 
                   kerosakan akibat kerja-kerja ukur dan kajian tanah.</td>
 			<td>&nbsp;</td>
 		</tr>
        
  	</table>
  #end 
            
		</fieldset>
#end 

		<div align="center">          	
        #if($semak=="yes")
           <p>
           #if($currentStatus!=149)
            <input name="cmdKemaskini " type="button" id="cmdKemaskini " value="Kemaskini" onclick="javascript:Kemaskini('$id_fail','$id_permohonan');">
           #end	
          	<input name="cmdKembali" type="button" value="Kembali" onClick="javascript: Kembali_skrin1('$id_fail','$id_permohonan');" >
          	<input name="cmdCetak" type="submit" id="cmdCetak" value="Cetak">
           #if($currentStatus!=149)	
           #if($no_rujukan_upt!="")
          	<input name="cmdHantar" type="button" id="cmdHantar" value="Hantar" onclick="javascript:hantar('$id_fail','$id_permohonan');">
           #end
           #end
        #end
            
        #if($edit=="yes")
           <p>
          	<input name="cmdSimpan" type="button" value="Simpan" onClick="javascript: add_item('$id_fail','$id_permohonan');">  
          	<input name="cmdBatal" type="button" value="Batal" onClick="javascript: batal('$id_fail','$id_permohonan');" >            
        #end    
        </div>   
      		<input type="hidden" name="id_fail" value="$id_fail">
      		<input type="hidden" name="no_fail">          
      		<input type="hidden" name="id_permohonan" value="$id_permohonan">
      		<input type="hidden" name="no_permohonan">        
</fieldset>

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<script>
function batal(id_fail,id_permohonan){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semak";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTPermohonanSenaraiSek8";
	document.${formName}.submit();
}

function Kemaskini(id_fail,id_permohonan){
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "Kemaskini";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTPermohonanSenaraiSek8";
	document.${formName}.submit();
}
function hantar(id_fail,id_permohonan){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "hantar";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTPermohonanSenaraiSek8";
	document.${formName}.submit();
}
function add_item(id_fail,id_permohonan){

	 	if(document.${formName}.txtNoRujPTD.value == ""){
		alert("Sila masukkan \" No. Rujukan PTD \" terlebih dahulu.");
  		document.${formName}.txtNoRujPTD.focus(); 
		return;
	}
 		if(document.${formName}.txtNoRujPTG.value == ""){
		alert("Sila masukkan \" No. Rujukan PTG \" terlebih dahulu.");
  		document.${formName}.txtNoRujPTG.focus(); 
		return;
	}
		if(document.${formName}.txtNoRujJPTGNegeri.value == ""){
		alert("Sila masukkan \" No. Rujukan JKPTG Negeri \" terlebih dahulu.");
  		document.${formName}.txtNoRujJPTGNegeri.focus(); 
		return;
	} 
	else{ 
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "simpan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTPermohonanSenaraiSek8";
	document.${formName}.submit();
	
	}
}

function Kembali_skrin1(id_fail,id_permohonan){
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "Kembali_skrin1";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTPermohonanSenaraiSek8";
	document.${formName}.submit();
}
</script>