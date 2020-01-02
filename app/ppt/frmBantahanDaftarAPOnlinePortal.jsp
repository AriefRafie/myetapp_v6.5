#parse("app/ppt/HadAksesOnlinePPT.jsp")

#if ($showPopupHantar=="yes")
<div class="success">
Permohonan Bantahan telah berjaya dihantar.&nbsp;&nbsp;<u><a href="javascript:cetakBorangN('$id_fail','$id_bantahan')">Cetak Borang N</a></u>
</div>
#end

#if ($clearForm == "yes")    	
    #set ($txdBrgN = "")
    #set ($txtKptgnAtasTnh = "")
    #set ($txtAlasanBantahan = "")
    #set ($amaun_pampasan = "") 
    #set ($terima_pampasan = "") 
    #set ($umpuk_pampasan = "") 
    #set ($TEMPchecked1 = "") 
    #set ($TEMPchecked2 = "") 
    #set ($TEMPchecked3 = "") 
    #set ($TEMPchecked4 = "")   
#end

#set ( $id_status = $statusFail.get("id_status") )


#if ($flag == "semak")
    ##foreach ( $info in $getHakmilik )
        ##set ($no_lot=$info.no_lot) 
        ##set ($txtUnitLot=$info.unitlot) 
        ##set ($txtNoPt=$info.no_pt)
        ##set ($txtUnitPt=$info.unitpt)
        ##set ($txtPengambilanNo=$info.no_siasatan)
        ##set ($txtNoLot=$info.no_lotpt)       
    ##end       
    
    #foreach ( $senarai in $getMaklumatBantahan )
        #set ($txdMohon=$senarai.tarikh_mohon)
        #set ($txdBrgN=$senarai.tarikh_borangn)
        #set ($status_bantahan=$senarai.status_bantahan)
        #set ($txtAlasanBantahan=$senarai.alasan)
        #set ($txtKptgnAtasTnh=$senarai.kepentingankeatas)
        #set ($flag_syarat=$senarai.flag_syarat)
        #set ($ukuran_luas=$senarai.flag_penerima_pampasan)
        #set ($amaun_pampasan=$senarai.flag_bahagian_pampasan)
        #set ($terima_pampasan=$senarai.flag_ukur_luas)
        #set ($umpuk_pampasan=$senarai.flag_pampasan)
        #set ($txtAmaunTuntutan=$senarai.amaun_tuntutan)
        #set ($flag_online=$senarai.flag_online)
    #end    
    
#end

<fieldset>
	<legend>PERMOHONAN BANTAHAN KE MAHKAMAH</legend>
    
    #parse("app/ppt/frmPPTHeader.jsp")


<!-------------------------------------- MAKLUMAT BANTAHAN -------------------------------------------->
<br />

<fieldset>
<legend>Maklumat Bantahan</legend>
    <table width="100%" border="0">
        <tr>
            <td width="25%">#if($mode!="disabled")<font color="red">*</font>#end&nbsp;Tarikh Permohonan</td>
            <td width="1%">:</td>
            <td width="25%">
            
            #if ($mode=="disabled")  
            <input type="text" name="txdMohon" id="txdMohon" value="$!txdMohon" size="11" onblur="check_date(this);" class="disabled" readonly />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>
            #else
            <input type="text" name="txdMohon" id="txdMohon" value="$!txdMohon" size="11" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" tabindex="4" />&nbsp;<img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdMohon',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>
            #end             </td>
          <td width="1%">&nbsp;</td>
          <td width="21%">&nbsp;&nbsp;&nbsp;Pihak membantah</td>
          <td width="1%">:</td>
            <td width="26%"><select name="jenis_pembantah" tabindex="6" id="jenis_pembantah" style="text-transform:uppercase; width:auto" onblur="uppercase()" class="disabled" disabled="disabled" >
              
              
                    
			#if($jenis_pembantah=="1")                                        
            
              <option value="2">Agensi Pemohon</option>
              <option value="1">Pihak Berkepentingan</option>
              
              
              
            
            #elseif($jenis_pembantah=="2")                                     
            
              
              <option value="2">Agensi Pemohon</option>
              <option value="1">Pihak Berkepentingan</option>
              
              
            
	        #else                                         
            

              <option value="2">Agensi Pemohon</option>
              <option value="1">Pihak Berkepentingan</option>
              
            #end 
                                 
          
            
            </select>            
            </td>
        </tr>
         
        <tr>
          <td>#if($mode!="disabled")<font color="red">*</font>#end&nbsp;Tarikh Borang N</td>
          <td>:</td>
          <td>
          
          #if ($mode=="disabled")   
          <input type="text" name="txdBrgN" id="txdBrgN" value="$!txdBrgN" size="11" onblur="check_date(this);" class="disabled" readonly />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>

          #else
          <input type="text" name="txdBrgN" id="txdBrgN" value="$!txdBrgN" size="11" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" tabindex="4" />&nbsp;<img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdBrgN',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>

          #end          
          
          </td>
               <td>&nbsp;</td>
          <td colspan="3">
           <i><font color="#FF0000" style="font-size:10px">
          <b>Syarat :</b><br/> 
          <span id="alert_bantahan1">1. Jika hadir/diwakili semasa award pampasan ialah 6 minggu dari tarikh award pampasan bertulis (Borang G)</span>
          <br/>
          <span id="alert_bantahan2">2. Jika tidak hadir 6 minggu dari tarikh tawaran pampasan (Borang H) diterima atau 6 bulan dari tarikh award dibuat (Borang G), mengikut mana yang dahulu tamat</span>
          </font></i>  
          </td>
        </tr>
        <tr>
          <td>&nbsp;No. Hakmilik</td>
          <td>:</td>
          <td>
          
          #if ($mode=="disabled") 
           <input type="text" name="txtNoHakmilik" id="txtNoHakmilik" value="$!txtNoHakmilik" class="disabled" readonly />
          #else
           <input type="text" name="txtNoHakmilik" id="txtNoHakmilik" value="$!txtNoHakmilik" class="disabled" readonly />
          #end          </td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;No Lot/PT</td>
            <td>:</td>
            <td>
            
            #if ($mode=="disabled") 
            <input type="text" name="txtNoLot" id="txtNoLot" value="$!txtNoLot" size="30" class="disabled" readonly />
            #else
            <input type="text" name="txtNoLot" id="txtNoLot" value="$!txtNoLot" size="30" class="disabled" readonly />
            #end            
            
            </td>
            <td>&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
           	<td>&nbsp;</td>
            <td>&nbsp;</td>
        </tr>                          
    </table>   
</fieldset>

<br />
<!------------------------------------ END MAKLUMAT BANTAHAN ------------------------------------------> 

<!------------------------------------ MAKLUMAT PEMBANTAH --------------------------------------------->  

<fieldset>
	<legend>Maklumat Pembantah</legend>
    <table width="100%" border="0">
        <tr>
            <td width="25%">&nbsp;Nama Kementerian</td>
            <td width="1%">:</td>
            <td colspan="5">
            
            #if ($mode=="disabled") 
            <input type="text" name="txtNamaPembantah" id="txtNamaPembantah" value="$!txtNamaPembantah" size="60"  class="disabled" readonly />
            #else
            <input type="text" name="txtNamaPembantah" id="txtNamaPembantah" value="$!txtNamaPembantah" size="60" tabindex="12" class="disabled" readonly />
            #end            
            
            </td>
      </tr>
        <tr>
          <td>&nbsp;Nama Agensi</td>
          <td>&nbsp;</td>
          <td colspan="5">
          
            #if ($mode=="disabled") 
            <input type="text" name="txtNamaAgensi" id="txtNamaAgensi" value="$!txtNamaAgensi" size="60"  class="disabled" readonly />
            #else
            <input type="text" name="txtNamaAgensi" id="txtNamaAgensi" value="$!txtNamaAgensi" size="60" tabindex="12" class="disabled" readonly />
            #end           
          
          </td>
        </tr>
        <tr>
            <td>&nbsp;Alamat</td>
            <td>:</td>
            <td colspan="5">
            
            #if ($mode=="disabled") 
            <input type="text" name="txtAlamat1" id="txtAlamat1" value="$!txtAlamat1" size="60" class="disabled" readonly />
            #else
            <input type="text" name="txtAlamat1" id="txtAlamat1" value="$!txtAlamat1" size="60" class="disabled" readonly />
            #end            </td>
      </tr>  
        <tr>
            <td>&nbsp;</td>
            <td></td>
            <td colspan="5">
            
            #if ($mode=="disabled") 
            <input type="text" name="txtAlamat2" id="txtAlamat2" value="$!txtAlamat2" size="60" class="disabled" readonly />
            #else
            <input type="text" name="txtAlamat2" id="txtAlamat2" value="$!txtAlamat2" size="60" tabindex="13" class="disabled" readonly />
            #end            </td>
      </tr> 
        <tr>
            <td>&nbsp;</td>
            <td></td>
            <td colspan="5">
            
            #if ($mode=="disabled") 
            <input type="text" name="txtAlamat3" id="txtAlamat3" value="$!txtAlamat3" size="60" class="disabled" readonly />
            #else
            <input type="text" name="txtAlamat3" id="txtAlamat3" value="$!txtAlamat3" size="60" tabindex="14" class="disabled" readonly />
            #end            </td>
      </tr> 
        <tr>
          <td>&nbsp;Poskod</td>
          <td>:</td>
          <td colspan="5">
          
          #if ($mode=="disabled") 
          <input type="text" name="txtPoskod" id="txtPoskod" value="$!txtPoskod" maxlength="5" size="5" class="disabled" readonly />
          #else
          <input type="text" name="txtPoskod" id="txtPoskod" value="$!txtPoskod" maxlength="5" size="5" onkeyup="validateNumber(this,this.value);" tabindex="15" class="disabled" readonly />
          #end          
          
          </td>
        </tr>
        <tr>
          <td>&nbsp;Negeri</td>
          <td>:</td>
          <td colspan="5">$selectNegeri<input type="hidden" name="id_negeri" id="id_negeri" value="$!id_negeri" size="5" /></td>
        </tr>
<!--        <tr>
          <td><font color="red">*</font>&nbsp;Bandar</td>
          <td>:</td>
          <td colspan="5">$selectBandar<input type="hidden" name="id_bandar" id="id_bandar" value="$!id_bandar" size="5" /></td>
        </tr>-->
        <tr>
          <td valign="top">#if($mode!="disabled")<font color="red">*</font>#end&nbsp;Kepentingan</td>
          <td valign="top">:</td>
          <td colspan="5">
          
          #if ($mode=="disabled") 
          <textarea name="txtKptgnAtasTnh" id="txtKptgnAtasTnh" cols="50" rows="6" class="disabled" readonly >$!txtKptgnAtasTnh</textarea>
          #else
          <textarea name="txtKptgnAtasTnh" id="txtKptgnAtasTnh" cols="50" rows="6" tabindex="16">$!txtKptgnAtasTnh</textarea>
          #end          
          
          </td>
        </tr>
        
       #if ($syarat=="bawah") 
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td colspan="5">   
                         	
          	<i><font color="#FF0000" style="font-size:10px"> PAMPASAN PIHAK BERKEPENTINGAN INI KURANG ATAU SAMA DENGAN RM3000.00 </font></i>            </td>
        </tr>
       #end
        
        <tr>
            <td>#if($mode!="disabled")<font color="red">*</font>#end&nbsp;Bantahan Terhadap</td>
            <td>:</td>
            <td width="4%">
            
           #if($ukuran_luas=="Y")
           #set($TEMPchecked1 = "checked")
           #else
           #set($TEMPchecked1 = "")
           #end
            
            
          #if ($mode=="disabled" )
          <input type="checkbox" name="ukuran_luas" id="ukuran_luas" value="Y" disabled $TEMPchecked1  />
          #elseif ($mode!="disabled"  &&  $alasan1 == "1")
          <input type="checkbox" name="ukuran_luas" id="ukuran_luas" value="Y" disabled $TEMPchecked1  />
          #else
          <input type="checkbox" name="ukuran_luas" id="ukuran_luas" value="Y" tabindex="17" $TEMPchecked1  />
          #end            </td>            
            <td width="73%" colspan="4">Ukuran Luas Tanah Tersebut;</td>
        </tr>

       
        <tr>
            <td valign="top">&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td>
            
           #if($amaun_pampasan=="Y")
           #set($TEMPchecked2 = "checked")
           #else
           #set($TEMPchecked2 = "")
           #end
           
         
          
          #if ($mode=="disabled")
          <input type="checkbox" name="amaun_pampasan" id="amaun_pampasan" value="Y" disabled $TEMPchecked2  />
          #elseif ($mode!="disabled"  &&  $alasan2 == "2")
          <input type="checkbox" name="amaun_pampasan" id="amaun_pampasan" value="Y" disabled $TEMPchecked2  />
          #else
          <input type="checkbox" name="amaun_pampasan" id="amaun_pampasan" value="Y" tabindex="18" $TEMPchecked2 />
          #end            </td>
            <td colspan="4">Jumlah Pampasan;</td>
        </tr>  
          
        <tr>
            <td valign="top">&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td>
            
            
           #if($terima_pampasan=="Y")
           #set($TEMPchecked3 = "checked")
           #else
           #set($TEMPchecked3 = "")
           #end
          
        
          #if ($mode=="disabled")
          <input type="checkbox" name="terima_pampasan" id="terima_pampasan" value="Y" disabled $TEMPchecked3 />
          #elseif ($mode!="disabled"  &&  $alasan3 == "3")
          <input type="checkbox" name="terima_pampasan" id="terima_pampasan" value="Y" disabled $TEMPchecked3 />
          #else
          <input type="checkbox" name="terima_pampasan" id="terima_pampasan" value="Y" tabindex="19"  $TEMPchecked3 />
          #end            </td>
            <td colspan="4">Orang-orang yang  menerima pampasan;</td>
      </tr>
      
        <tr>
            <td valign="top">&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td>
            
           #if($umpuk_pampasan=="Y")
           #set($TEMPchecked4 = "checked")
           #else
           #set($TEMPchecked4 = "")
           #end
          
        
          #if ($mode=="disabled")
          <input type="checkbox" name="umpuk_pampasan" id="umpuk_pampasan" value="Y" tabindex="20" disabled $TEMPchecked4 />
          #elseif ($mode!="disabled"  &&  $alasan4 == "4")
          <input type="checkbox" name="umpuk_pampasan" id="umpuk_pampasan" value="Y" tabindex="20" disabled $TEMPchecked4 />
          #else
          <input type="checkbox" name="umpuk_pampasan" id="umpuk_pampasan" value="Y" tabindex="20" $TEMPchecked4 />
          #end            </td>
            <td colspan="4">Bahagian Pemberian Pampasan</td>
      </tr> 
                  
                                   
        <tr>
          <td>#if($mode!="disabled")<font color="red">*</font>#end&nbsp;Amaun Tuntutan (RM)</td>
          <td>:</td>
          <td colspan="5">
          
          #if ($mode=="disabled")
          <input type="text" size="15" name="txtAmaunTuntutan" id="txtAmaunTuntutan" value="$!txtAmaunTuntutan" maxlength="12" class="disabled" readonly />
          #else
          <input type="text" size="15" name="txtAmaunTuntutan" id="txtAmaunTuntutan" value="$!txtAmaunTuntutan" maxlength="12" onblur="validateNumber(this,this.value);validateModal(this,this.value,'$txtAmaunTuntutan')" onkeyup="validateNumber(this,this.value);" />
          #end          
          
          
          </td>
        </tr>       
        <tr>
          <td valign="top">#if($mode!="disabled")<font color="red">*</font>#end&nbsp;Alasan Bantahan</td>
          <td valign="top">:</td>
          <td colspan="5">
          
          #if ($mode=="disabled")
          <textarea name="txtAlasanBantahan" id="txtAlasanBantahan" cols="50" rows="6" class="disabled" readonly >$!txtAlasanBantahan</textarea>
          #else
          <textarea name="txtAlasanBantahan" id="txtAlasanBantahan" cols="50" rows="6" tabindex="21" onKeyDown="textCounter(this.form.txtAlasanBantahan,this.form.remLentxtAlasanBantahan,400);" onKeyUp="textCounter(this.form.txtAlasanBantahan,this.form.remLentxtAlasanBantahan,400);">$!txtAlasanBantahan</textarea>
          #end          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td colspan="5"><input type="text" readonly class="disabled" name="remLentxtAlasanBantahan" size="3" maxlength="3" value="400"> Baki Aksara </td>
        </tr>                                   
    </table> 
    
</fieldset>

<!------------------------------------------------------ END MAKLUMAT PEMBANTAH ---------------------------------------->

<div align="center">
#if($roleAgensi=="no")
 		   #if ($button=="add") 
          <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:add_bantahanOnline()" />
          <!-- <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batal()" />      -->        
           #end

          #if ($button=="view" && $flagOnline=="1")    
          	#if(($id_jawatan_user == $layer1 && $flag_semakan_online=="") || 
						($id_jawatan_user == $layer2 && ($flag_semakan_online=="" || $flag_semakan_online=="1" )) ||
						($id_jawatan_user == $layer3 && ($flag_semakan_online=="" || $flag_semakan_online=="1" || $flag_semakan_online=="2")))
          	<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskiniBantahan()" /> 
          	#end
		  #elseif($id_status_bantahan=="1610250" && $button!="edit")
		  <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskiniBantahan()" /> 
          #end
	
          #if ($button=="edit")
          <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanEditBantahan()" /> 
          <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batal_bantahan('$!id_fail','$!id_permohonan','$!id_hakmilik')" /> 
          #end  
          
          
          #if ($button=="view")
          #if ($flagOnline=="1" || $flagOnline=="")
          
          	<!-- Pembantu Tadbir Hantar Untuk Semakan -->
          	#if(($id_jawatan_user == $layer1) && ($flag_semakan_online==""))
          	<input type="button" name="cmdHantarSemakan" value ="Hantar Untuk Semakan" onClick="javascript:hantarSemakan()">
          	#end
          				
          	<!-- Penolong Pengarah Membuat Semakan -->
          	#if($id_jawatan_user == $layer2 && $flag_semakan_online=="1")
          	<input type="button" name="cmdSahSemakan" value ="Sahkan Semakan" onClick="javascript:sahSemakan()" >
          	#end
          				
			<!-- Permohonan meluluskan permohonan -->
          	#if($id_jawatan_user == $layer3 && $flag_semakan_online=="2")
	        <input type="button" name="cmdLulusPermohonan" value ="Luluskan Permohonan" onClick="javascript:lulusPermohonan()" >	
	        #end	
          
          	<!-- Telah diluluskan dan Pengarah yang hantar -->
	         #if($id_jawatan_user == $layer3 && $flag_semakan_online=="3")
				<input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="javascript:seterusnya()" />   
	         #end	  
          	<!-- <input type="button" name="cmdSah" id="cmdSah" value="Hantar Permohonan" onclick="javascript:sahkan_permohonan()" />      -->   
                
  		  	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak Borang N" onclick="javascript:cetakBorangN('$id_fail','$id_bantahan')" /> 
          #end
          #end
#end          
 		 <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="javascript:kembaliList($!id_bantahan)" />
</div> 

</fieldset>

<!------------------------------------------ OUTPUT LAPORAN/SURAT ----------------------------------------------->
<br/>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" class="style2" onClick="javascript:cetakBorangN('$id_fail','$id_bantahan')"><font color="blue"> Borang N - Borang Permohonan Bantahan Untuk Di Bawa Ke Kaunter UPT </font></a></td>
      </tr>           
    </table>
</fieldset>
<!------------------------------------------ END OUTPUT LAPORAN/SURAT ------------------------------------------>

<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name="id_permohonan" id="id_permohonan" value="$id_permohonan" />
<input type="hidden" name="id_fail" id="id_fail" value="$id_fail" />
<input type="hidden" name="id_hakmilik" id="id_hakmilik" value="$id_hakmilik" />
<input type="hidden" name="id_agensi" id="id_agensi" value="$id_agensi" />
<input type="hidden" name="id_kementerian" id="id_kementerian" value="$id_kementerian" />
<input type="hidden" name="id_bantahan" id="id_bantahan" value="$id_bantahan" />
<input type="hidden" name="command2">

<script>
function hantarSemakan(){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "updateFlagSemakan";	
	document.${formName}.command2.value = "hantarSemakan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanDaftarAPOnline";
	document.${formName}.submit();
}
function sahSemakan(){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "updateFlagSemakan";	
	document.${formName}.command2.value = "sahSemakan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanDaftarAPOnline";
	document.${formName}.submit();
}
function lulusPermohonan(){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "updateFlagSemakan";	
	document.${formName}.command2.value = "lulusPermohonan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanDaftarAPOnline";
	document.${formName}.submit();
}
function seterusnya(){
	document.${formName}.command.value = "seterusnya";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanDaftarAPOnline";
	document.${formName}.submit();
}

function sahkan_permohonan(){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	alert("Sila Pastikan Borang N dan Deposit Bantahan Dibawa Ke Kaunter JKPTG");
	document.${formName}.command.value = "sahkan_permohonan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanDaftarAPOnline";
	document.${formName}.submit();
}

function batal_bantahan(id_fail,id_permohonan,id_hakmilik) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	//document.${formName}.id_status.value = id_status;
	document.${formName}.id_hakmilik.value = id_hakmilik;	
	document.${formName}.command.value = "screenBorangN";	
	document.${formName}.method="POST";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanDaftarAPOnline";
	document.${formName}.submit();	
}
function open_header(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function cetakBorangN(id_fail,id_bantahan) {
	var url = "../servlet/ekptg.report.ppt.BorangN_AP?idFail="+id_fail+"&id_bantahan="+id_bantahan;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
function kemaskiniBantahan(){
	document.${formName}.command.value = "kemaskiniBantahan";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanDaftarAPOnline";
	document.${formName}.submit();
}

function kembaliList(id_bantahan){
	document.${formName}.id_bantahan.value = id_bantahan;	
	document.${formName}.command.value = "papar_ListHakmilik";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanDaftarAPOnline";
	document.${formName}.submit();
}

function batal(){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "addBorangN";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanDaftarAPOnline";
	document.${formName}.submit();
}
function doChangeNegeri() {
	document.${formName}.command.value = "doChangeNegeri";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanDaftarAPOnline";
	document.${formName}.submit();		
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function add_bantahanOnline(){
	if(document.${formName}.txdMohon.value == ""){
		alert("Sila masukkan \"Tarikh Permohonan\" terlebih dahulu.");
  		document.${formName}.txdMohon.focus(); 
		return;		
	}
	if(document.${formName}.txdBrgN.value == ""){
		alert("Sila masukkan \"Tarikh Borang N\" terlebih dahulu.");
  		document.${formName}.txdBrgN.focus(); 
		return;		
	}			
	if(document.${formName}.txtNamaPembantah.value == ""){
		alert("Sila masukkan \"Nama Pembantah\" terlebih dahulu.");
  		document.${formName}.txtNamaPembantah.focus(); 
		return;		
	}	
//	if(document.${formName}.txtAlamat1.value == ""){
//		alert("Sila masukkan \"Alamat\" terlebih dahulu.");
//  		document.${formName}.txtAlamat1.focus(); 
//		return;		
//	}	
//	if(document.${formName}.txtPoskod.value == ""){
//		alert("Sila masukkan \"Poskod\" terlebih dahulu.");
//  		document.${formName}.txtPoskod.focus(); 
//		return;		
//	}	
/*	if(document.${formName}.socNegeri.value == ""){
		alert("Sila pilih \"Negeri\" terlebih dahulu.");
  		document.${formName}.socNegeri.focus(); 
		return;		
	}			
*/	if(document.${formName}.txtKptgnAtasTnh.value == ""){
		alert("Sila masukkan \"Kepentingan\" terlebih dahulu.");
  		document.${formName}.txtKptgnAtasTnh.focus(); 
		return;		
	}	
	if( document.${formName}.ukuran_luas.checked == false && document.${formName}.amaun_pampasan.checked == false && document.${formName}.terima_pampasan.checked == false && document.${formName}.umpuk_pampasan.checked == false ) {
		alert("Sila pilih \"Bantahan Terhadap\" terlebih dahulu.");
  		document.${formName}.txtKptgnAtasTnh.focus(); 
		return;	
	}	
	if(document.${formName}.txtAmaunTuntutan.value == ""){
		alert("Sila masukkan \"Amaun Tuntutan\" terlebih dahulu.");
  		document.${formName}.txtAmaunTuntutan.focus(); 
		return;		
	}	
	if(document.${formName}.txtAlasanBantahan.value == ""){
		alert("Sila masukkan \"Alasan Bantahan\" terlebih dahulu.");
  		document.${formName}.txtAlasanBantahan.focus(); 
		return;		
	}				
	else{
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "add_bantahanOnline";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanDaftarAPOnline";
	document.${formName}.submit();
	}
}

function simpanEditBantahan(){

	if(document.${formName}.txdMohon.value == ""){
		alert("Sila masukkan \"Tarikh Permohonan\" terlebih dahulu.");
  		document.${formName}.txdMohon.focus(); 
		return;		
	}
	if(document.${formName}.txdBrgN.value == ""){
		alert("Sila masukkan \"Tarikh Borang N\" terlebih dahulu.");
  		document.${formName}.txdBrgN.focus(); 
		return;		
	}			
	if(document.${formName}.txtNamaPembantah.value == ""){
		alert("Sila masukkan \"Nama Pembantah\" terlebih dahulu.");
  		document.${formName}.txtNamaPembantah.focus(); 
		return;		
	}	
/*	if(document.${formName}.txtAlamat1.value == ""){
		alert("Sila masukkan \"Alamat\" terlebih dahulu.");
  		document.${formName}.txtAlamat1.focus(); 
		return;		
	}	
	if(document.${formName}.txtPoskod.value == ""){
		alert("Sila masukkan \"Poskod\" terlebih dahulu.");
  		document.${formName}.txtPoskod.focus(); 
		return;		
	}	
	if(document.${formName}.socNegeri.value == ""){
		alert("Sila pilih \"Negeri\" terlebih dahulu.");
  		document.${formName}.socNegeri.focus(); 
		return;		
	}			
*/	if(document.${formName}.txtKptgnAtasTnh.value == ""){
		alert("Sila masukkan \"Kepentingan\" terlebih dahulu.");
  		document.${formName}.txtKptgnAtasTnh.focus(); 
		return;		
	}	
	if( document.${formName}.ukuran_luas.checked == false && document.${formName}.amaun_pampasan.checked == false && document.${formName}.terima_pampasan.checked == false && document.${formName}.umpuk_pampasan.checked == false ) {
		alert("Sila pilih \"Bantahan Terhadap\" terlebih dahulu.");
  		document.${formName}.txtKptgnAtasTnh.focus(); 
		return;	
	}	
	if(document.${formName}.txtAmaunTuntutan.value == ""){
		alert("Sila masukkan \"Amaun Tuntutan\" terlebih dahulu.");
  		document.${formName}.txtAmaunTuntutan.focus(); 
		return;		
	}	
	if(document.${formName}.txtAlasanBantahan.value == ""){
		alert("Sila masukkan \"Alasan Bantahan\" terlebih dahulu.");
  		document.${formName}.txtAlasanBantahan.focus(); 
		return;		
	}				
	else{
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "simpanEditBantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanDaftarAPOnline";
	document.${formName}.submit();
	}
}

function UploadDokumen(){
	document.${formName}.command.value = "UploadDokumen";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanDaftarAPOnline";
	document.${formName}.submit();
}
function validateModal(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	var num = content * 1;
	elmnt.value = num.toFixed(2);
	return;
}
function validateNumber(elmnt,content) {
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

function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'Baki Aksara' counter
	else 
		countfield.value = maxlimit - field.value.length;
}

</script>

