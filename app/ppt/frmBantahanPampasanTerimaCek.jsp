
<div id="checking_progress"></div>
#if ($completed)
<script>
parent.document.getElementById("checking_progress").innerHTML="<div class=\"status\">URUSAN PAMPASAN TIDAK DAPAT DITERUSKAN.SILA CUBA LAGI.</div>";
</script>
#end

#if ($clearForm == "yes")  
	#set ($txdTkhTerima = "") 
    #set ($txtPenamaCek = "") 
    #set ($txtNoCek = "") 
    #set ($txdTkhCek = "") 
    #set ($txtAmaunCek = "") 
    #set ($txdTkhAkhirCek = "") 
#end

#foreach ($senarai in $getMaklumatPB)
	#set ($id_hakmilik=$senarai.id_hakmilik)
	#set ($id_pihakberkepentingan=$senarai.id_pihakberkepentingan)
    #set ($nama_pb=$senarai.nama_pb)
    #set ($no_pb=$senarai.no_pb)
    #set ($no_lot=$senarai.no_lot)
    #set ($no_pt=$senarai.no_pt)
    #set ($id_mukim=$senarai.id_mukim)
    #set ($syer_atas=$senarai.syer_atas)
    #set ($syer_bawah=$senarai.syer_bawah)
    #set ($jumlah_award_pu=$senarai.jumlah_award_pu)
    #set ($nama_mukim=$senarai.nama_mukim)
    #set ($luas_ambil=$senarai.luas_ambil)
    #set ($unit_luasambil=$senarai.keterangan)
    #set ($jumlah_award_bantahan=$senarai.jumlah_award_bantahan)
#end

#foreach ($senarai in $getMaklumatTkhH)
#set ($tarikhBorangH=$senarai.tarikh_borangh)
#end


#if ($flag=="semak")
	#foreach ($senarai in $getMaklumatTerimaCek)
    	#set ($txdTkhTerima = $senarai.tarikh_terima)
        #set ($txtNoCek = $senarai.no_bayaran)
        #set ($txdTkhCek = $senarai.tarikh_cek)
        #set ($txtAmaunCek = $senarai.amaun_bayaran)
        #set ($txdTkhAkhirCek = $senarai.tarikh_akhir_cek)
        #set ($id_bayaran = $senarai.id_bayaran)
        <input type="hidden" name="id_bayaran" id="id_bayaran" value="$id_bayaran" />
    #end
#end

<fieldset>
<legend>Maklumat Pampasan Pihak Berkepentingan</legend>
<!------------------------------------------ HEADER ----------------------------------------------------->
	<table width="100%" border="0">
		<table width="100%"> 
			<tr>
				<td width="51%">
	  			<table width="101%"  cellpadding="1" cellspacing="1" border="0">
						<tr>
							<td width="26%" style="text-transform:uppercase">No pb</td>
		                  	<td width="2%">:</td>
           				  	<td width="72%">$!no_pb
                            <input type="hidden" name="id_pihakberkepentingan" id="id_pihakberkepentingan" value="$!id_pihakberkepentingan" />
                            <input type="hidden" name="no_pb" id="no_pb" value="$!no_pb" />
                            <input type="hidden" name="id_hakmilik" id="id_hakmilik" value="$!id_hakmilik" /></td>
			    		</tr>
						<tr>
							<td style="text-transform:uppercase">Nama PB</td>
							<td>:</td>
							<td>$!nama_pb.toUpperCase()</td>
						</tr>
						<tr>
							<td style="text-transform:uppercase" valign="top">No Lot</td>
							<td valign="top">:</td>
							<td>$!no_lot.toUpperCase()</td>
						</tr>
						<tr>
							<td valign="top" style="text-transform:uppercase">No Pt</td>
							<td valign="top">:</td>
							<td>$!no_pt.toUpperCase()</td>
						</tr>
					</table>
			  </td>
				
		  <td width="49%">
  			<table width="100%"  cellpadding="1" cellspacing="1" border="0">
						<tr>
							<td width="44%" valign="top" style="text-transform:uppercase">Mukim</td>
						    <td width="1%" valign="top">:</td>
					      	<td width="55%">$!nama_mukim.toUpperCase()</td>
			  			</tr>
						<tr>
						  	<td style="text-transform:uppercase">Bahagian</td>
							<td>:</td>
							<td>$!syer_atas&nbsp;/&nbsp;$!syer_bawah</td>
						</tr>
						<tr>
						  	<td style="text-transform:uppercase">Luas Yang Diambil</td>
							<td>:</td>
							<td>$!luas_ambil&nbsp;$!unit_luasambil</td>
						</tr>
						<tr>
						  	<td valign="top" style="text-transform:uppercase">Jumlah Pampasan</td>
							<td valign="top">:</td>
							<td>RM &nbsp;$!Util.formatDecimal($!jumlah_award_pu)</td>
						</tr>
						<tr>
						  	<td valign="top" style="text-transform:uppercase">Jumlah Award Bantahan</td>
							<td valign="top">:</td>
							<td>RM &nbsp;$!Util.formatDecimal($!jumlah_award_bantahan)</td>
						</tr>                        
			</table>
			  </td>				
		  </tr>
		</table>                                    
  <br />
  
<!------------------------------------------ END HEADER ----------------------------------------------------->    

<!---------------------------------------- TAB PENERIMAAN CEK ----------------------------------------------->

<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
 
    <li class="TabbedPanelsTab" tabindex="0" onclick="setSelectedTab(0);TerimaCek()">Penerimaan Cek</li>
    <li class="TabbedPanelsTab" tabindex="1" onclick="setSelectedTab(1);SerahCek()">Penyerahan Cek</li>
    <li class="TabbedPanelsTab" tabindex="2" onclick="setSelectedTab(2);BayaranEft()">Bayaran Melalui EFT</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent">
    
    <fieldset>
    
    
    <legend>Caj Bayaran Lewat</legend>
    	<table width="100%" border="0">
        	<tr>
            	<td width="18%">&nbsp;Tarikh Kuatkuasa Caj Lewat</td>
           	  	<td width="1%">:</td>
           	  	<td width="41%">
                
                #if ($mode=="disabled")
                <input type="text" name="txdTkhCajLewatBantahan" id="txdTkhCajLewatBantahan" value="$!txdTkhCajLewatBantahan" size="10" class="disabled" readonly />          
            	#else
                <input type="text" name="txdTkhCajLewatBantahan" id="txdTkhCajLewatBantahan" value="$!txdTkhCajLewatBantahan" size="10"  tabindex="1" />
            <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhCajLewatBantahan',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>
                #end            
              </td>
                
           	  	<td width="16%">&nbsp;Bil Hari Lewat</td>
           	  	<td width="1%">:</td>
           	  	<td width="23%">
                
              	#if ($mode=="disabled")
             <input type="text" name="txtBilLewatBantahan" id="txtBilLewatBantahan" value="$!txtBilLewatBantahan" size="10" class="disabled" readonly />
                #else
                    <input type="text" name="txtBilLewatBantahan" id="txtBilLewatBantahan" value="$!txtBilLewatBantahan" size="10" class="disabled" readonly tabindex="2" />
                #end 
                           
              </td>          
          	</tr>
        	<tr>
            	  <td>&nbsp;Peratus Caj Lewat</td>
                <td width="1%">:</td>
           	  	<td>
                
              	#if ($mode=="disabled")
              	<input type="text" name="txtPeratusCaj" id="txtPeratusCaj" value="$!txtPeratusCaj" maxlength="3" class="disabled" readonly />
              	#else
              	<input type="text" name="txtPeratusCaj" id="txtPeratusCaj" value="$!txtPeratusCaj" maxlength="3" />              
         	  	<input type="hidden" name="txtPeratusCaj" id="txtPeratusCaj" value="$!txtPeratusCaj" />  
         	  	#end 
                           
       		  </td>
            	
            	
                <td>&nbsp;Caj Bayaran Lewat&nbsp;(RM)</td>
                <td width="1%">:</td>
           	  	<td>
                
              	#if ($mode=="disabled")
              	<input type="text" name="txtAmaunLewatBantahan" id="txtAmaunLewatBantahan" value="$!txtAmaunLewatBantahan" maxlength="12" class="disabled" readonly />
              	#else
              	<input type="text" name="txtAmaunLewatBantahan" id="txtAmaunLewatBantahan" value="$!txtAmaunLewatBantahan" maxlength="12"  tabindex="5" />              
         	  	<input type="hidden" name="txtAmaunLewatBantahan" id="txtAmaunLewatBantahan" value="$!txtAmaunLewatBantahan" />  
         	  	#end 
                           
       		  </td>
            </tr>
            <tr>
             <td width="18%">&nbsp;Tarikh</td>
           	  	<td width="1%">:</td>
           	  	<td width="41%">
                
                #if ($mode=="disabled")
                <input type="text" name="txdTkhBayarBantah" id="txdTkhBayarBantah" value="$!txdTkhBayarBantah" size="10" class="disabled" readonly />          
            	#else
                <input type="text" name="txdTkhBayarBantah" id="txdTkhBayarBantah" value="$!txdTkhBayarBantah" size="10" onblur="dateBantah(this);" tabindex="1" />
            	<img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhBayarBantah',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>
                #end            
              	</td>
              
            </tr>
           
        </table>   
    </fieldset>
    
    <fieldset>
    
    
    <legend>Penerimaan Cek</legend>
    	<table width="100%" border="0">
        	<tr>
            	<td width="18%"><font color="red">*</font>&nbsp;Tarikh Terima</td>
           	  	<td width="1%">:</td>
           	  	<td width="41%">
                
                #if ($mode=="disabled")
                <input type="text" name="txdTkhTerima" id="txdTkhTerima" value="$!txdTkhTerima" size="10" class="disabled" readonly  />          
            	#else
                <input type="text" name="txdTkhTerima" id="txdTkhTerima" value="$!txdTkhTerima" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" tabindex="1" />
            <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhTerima',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>
                #end            
              </td>
                
           	  	<td width="16%"><font color="red">*</font>&nbsp;Tarikh Cek</td>
           	  	<td width="1%">:</td>
           	  	<td width="23%">
                
              	#if ($mode=="disabled")
              	<input type="text" name="txdTkhCek" id="txdTkhCek" value="$!txdTkhCek" size="10" class="disabled" readonly />        
              	#else
              	<input type="text" name="txdTkhCek" id="txdTkhCek" value="$!txdTkhCek" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" tabindex="4" />
            	<img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhCek',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>
              	#end 
                           
              </td>          
          	</tr>
        	<tr>
            	<td><font color="red">*</font>&nbsp;Penama Cek</td>
                <td>:</td>
                <td>
                
                #if ($mode=="disabled")
                <input type="text" name="txtPenamaCek" id="txtPenamaCek" value="$!nama_pb" size="50" class="disabled" readonly />
                #else
                    <input type="text" name="txtPenamaCek" id="txtPenamaCek" value="$!nama_pb" size="50" class="disabled" readonly tabindex="2" />
            	#end  
                              
                </td>
                <td><font color="red">*</font>&nbsp;Amaun Cek&nbsp;(RM)</td>
                <td width="1%">:</td>
           	  	<td>
                
              	#if ($mode=="disabled")
              	<input type="text" name="txtAmaunCekx" id="txtAmaunCekx" value="$!Util.formatDecimal($!jumlah_award_bantahan)" maxlength="12" class="disabled" readonly />
              	#else
              	<input type="text" name="txtAmaunCekx" id="txtAmaunCekx" value="$!Util.formatDecimal($!jumlah_award_bantahan)" maxlength="12" class="disabled" readonly tabindex="5" />              
         	  	<input type="hidden" name="txtAmaunCek" id="txtAmaunCek" value="$!jumlah_award_bantahan" />  
         	  	#end 
                           
       		  </td>
            </tr>
        	<tr>
            	<td><font color="red">*</font>&nbsp;No. Cek</td>
                <td>:</td>
                <td>
                
                #if ($mode=="disabled")
                <input type="text" name="txtNoCek" id="txtNoCek" value="$!txtNoCek" class="disabled" readonly />
                #else
                <input type="text" name="txtNoCek" id="txtNoCek" value="$!txtNoCek" tabindex="3" />
                #end  
                              
              </td>
                <td>&nbsp;&nbsp;&nbsp;Tarikh Akhir Cek</td>
                <td width="1%">:</td>
       	  	  	<td>
                
                #if ($mode=="disabled")
                <input type="text" name="txdTkhAkhirCek" id="txdTkhAkhirCek" value="$!txdTkhAkhirCek" size="10" class="disabled" readonly/>       
            	#else
                <input type="text" name="txdTkhAkhirCek" id="txdTkhAkhirCek" value="$!txdTkhAkhirCek" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" tabindex="6" />
            <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhAkhirCek',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>
                #end  
                          
              </td>
            </tr>                    
        </table>   
    </fieldset>
 <!--------------------------------------------- END TAB PENERIMAAN CEK ---------------------------------------------------->   
 </br>
<div align="center">  

  #if ($button=="view")         
  <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskiniTerimaCek()" /> 
  <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" /> 
  <!--<input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="" /> -->
  #end

  #if ($button=="edit")
  <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:update_terimaCek()" /> 
  <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batal_updateTerimaCek()" /> 
  #end
  
  #if ($button=="add")
  <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:add_terimaCek()" /> 
  <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalTerimaCek()" /> 
  #end
  
  <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="javascript:kembali_listPB()" />
      
</div>     
    </div>
    
    
    
    
    
    
    
    
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
  </div>
</div>
<input type=hidden name=selectedtab />
<!------------------------------------------ END TAB PENERIMAAN CEK ------------------------------------------->

    
<!--</fieldset>-->

<!------------------------------------------ OUTPUT LAPORAN/SURAT ----------------------------------------------->
<br/>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" class="style2" onClick="javascript:cetaksuratUtkPanggilanTerimaPampasanKpdPB_bantahan('$id_bayaran','$id_fail','$id_permohonan')"><font color="blue"> Surat Kepada Pihak Berkepentingan - Makluman Supaya Hadir Untuk Penyerahan Cek Pampasan </font></a></td>
      </tr>           
    </table>
</fieldset>
<!------------------------------------------ END OUTPUT LAPORAN/SURAT ------------------------------------------>

<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name="id_permohonan" id="id_permohonan" value="$id_permohonan" />
<input type="hidden" name="id_fail" id="id_fail" value="$id_fail" />
<input type="hidden" name="id_status" id="id_status" value="$id_status" /> 
<input type="hidden" name="id_pihakberkepentingan" id="id_pihakberkepentingan" value="$id_pihakberkepentingan" />
<input type="hidden" name="id_hakmilik" id="id_hakmilik" value="$id_hakmilik" />
<input type="hidden" name="id_hakmilikpb" id="id_hakmilikpb" value="$id_hakmilikpb" />
<input type="hidden" name="status_bantahan" id="status_bantahan" value="$status_bantahan" />
<input type="hidden" name="id_bantahan" id="id_bantahan" value="$id_bantahan" />
<input type="text" name="tarikhBorangH" id="tarikhBorangH" value="$tarikhBorangH" />
<input type="text" name="Date5" id="Date5" value="$Date5" />
<input type="text" name="Date8" id="Date8" value="$Date8" />
<input type="text" name="Date3bln" id="Date3bln" value="$Date3bln" />
<input type="text" name="jumlah_award_pu" id="jumlah_award_pu" value="$jumlah_award_pu" />

<script type="text/javascript">

function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function cetaksuratUtkPanggilanTerimaPampasanKpdPB_bantahan(id_bayaran,id_fail,id_permohonan) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_bayaran="+id_bayaran+"&id_fail="+id_fail+"&id_permohonan="+id_permohonan+"&report=suratUtkPanggilanTerimaPampasanKpdPB_bantahan&flagReport=S";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

/*	var url = "../servlet/ekptg.report.ppt.suratUtkPanggilanTerimaPampasanKpdPB_bantahan?idBayaran="+id_bayaran+"&idFail="+id_fail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();*/	
}

function TerimaCek(){
	document.${formName}.command.value = "TerimaCek";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
	document.${formName}.submit();
}

function BayarTambah(){
	document.${formName}.command.value = "BayarTambah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
	document.${formName}.submit();
}

function SerahCek(){
	document.${formName}.command.value = "SerahCek";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
	document.${formName}.submit();
}

function BayaranEft(){
	document.${formName}.command.value = "BayaranEft";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
	document.${formName}.submit();
}

function setSelectedTab(tabid) {
	document.${formName}.selectedtab.value = tabid;
}

function add_terimaCek(){

	if(document.${formName}.txdTkhTerima.value == ""){
		alert("Sila masukkan \"Tarikh Terima\" terlebih dahulu.");
  		document.${formName}.txdTkhTerima.focus(); 
		return;		
	}
	if(document.${formName}.txtPenamaCek.value == ""){
		alert("Sila masukkan \"Penama Cek\" terlebih dahulu.");
  		document.${formName}.txtPenamaCek.focus(); 
		return;		
	}
	if(document.${formName}.txtNoCek.value == ""){
		alert("Sila masukkan \"No Cek\" terlebih dahulu.");
  		document.${formName}.txtNoCek.focus(); 
		return;		
	}	
	if(document.${formName}.txdTkhCek.value == ""){
		alert("Sila masukkan \"Tarikh Cek\" terlebih dahulu.");
  		document.${formName}.txdTkhCek.focus(); 
		return;		
	}		
	if(document.${formName}.txtAmaunCek.value == ""){
		alert("Sila masukkan \"Amaun Cek\" terlebih dahulu.");
  		document.${formName}.txtAmaunCek.focus(); 
		return;		
	}		
	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.command.value = "add_terimaCek";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
		document.${formName}.submit();
	}
}

function batalTerimaCek(){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "batalTerimaCek";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
	document.${formName}.submit();
}

function kembali_listPB(){
	document.${formName}.command.value = "kembali_listPB";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
	document.${formName}.submit();
}

function kemaskiniTerimaCek(){
		document.${formName}.command.value = "kemaskiniTerimaCek";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
		document.${formName}.submit();
}

function update_terimaCek(){

	if(document.${formName}.txdTkhTerima.value == ""){
		alert("Sila masukkan \"Tarikh Terima\" terlebih dahulu.");
  		document.${formName}.txdTkhTerima.focus(); 
		return;		
	}
	if(document.${formName}.txtPenamaCek.value == ""){
		alert("Sila masukkan \"Penama Cek\" terlebih dahulu.");
  		document.${formName}.txtPenamaCek.focus(); 
		return;		
	}
	if(document.${formName}.txtNoCek.value == ""){
		alert("Sila masukkan \"No Cek\" terlebih dahulu.");
  		document.${formName}.txtNoCek.focus(); 
		return;		
	}	
	if(document.${formName}.txdTkhCek.value == ""){
		alert("Sila masukkan \"Tarikh Cek\" terlebih dahulu.");
  		document.${formName}.txdTkhCek.focus(); 
		return;		
	}		
	if(document.${formName}.txtAmaunCek.value == ""){
		alert("Sila masukkan \"Amaun Cek\" terlebih dahulu.");
  		document.${formName}.txtAmaunCek.focus(); 
		return;		
	}		
	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.command.value = "update_terimaCek";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
		document.${formName}.submit();
	}
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

function dateBantah(){
	
}
/*
function check_date() {
	
	var Mula  = document.getElementById("txdTkhCajLewatBantahan").value;
	var dt1   = parseInt(Mula.substring(0,2),10);
	var mon1  = parseInt(Mula.substring(3,5),10)-1;
	var yr1   = parseInt(Mula.substring(6,10),10);
	var dateLewat = new Date(yr1, mon1, dt1);
	//alert("dateLewat "+dateLewat);
	//alert("year "+yr1);
	var leapyr1 = yr1%4;
	//alert(varleapyr1);
	
	var TarikhH  = document.getElementById("tarikhBorangH").value;
	var dtH   = parseInt(TarikhH.substring(0,2),10);
	var monH  = parseInt(TarikhH.substring(3,5),10)-1;
	var yrH   = parseInt(TarikhH.substring(6,10),10);
	var dateH = new Date(yrH, monH, dtH);
	var leapyrH = yrH%4;
	//alert("date borang h"+dateH);

	
	var Date3bln = new Date(dateH);
	//alert(Date3bln);
	Date3bln.setDate(Date3bln.getDate() + 90);
	document.${formName}.Date3bln.value = Date3bln;
	var Date3blnyear = Date3bln.getFullYear();
	var leapyr3bln = Date3blnyear%4;
	//alert(leapyr3bln);
	//document.getElementById("demo").innerHTML = d;
	
	//var Tarikh3bln  = document.getElementById("Date3bln").value;
	//var dt3bln   = parseInt(Tarikh3bln.substring(0,2),10);
	//var mon3bln  = parseInt(Tarikh3bln.substring(3,5),10)-1;
	//var yr3bln   = parseInt(Tarikh3bln.substring(6,10),10);
	//var date3bln = new Date(yr3bln, mon3bln, dt3bln);
	//alert("date date3bln"+date3bln);
	
	var Tarikh5  = document.getElementById("Date5").value;
	var dt5   = parseInt(Tarikh5.substring(0,2),10);
	var mon5  = parseInt(Tarikh5.substring(3,5),10)-1;
	var yr5   = parseInt(Tarikh5.substring(6,10),10);
	var date5 = new Date(yr5, mon5, dt5);
	//alert("date 5"+date5);
	
	var Tarikh8  = document.getElementById("Date8").value;
	var dt8   = parseInt(Tarikh8.substring(0,2),10);
	var mon8  = parseInt(Tarikh8.substring(3,5),10)-1;
	var yr8   = parseInt(Tarikh8.substring(6,10),10);
	var date8 = new Date(yr8, mon8, dt8);
	//alert(""+date8);

	// get total seconds between two dates
	var res = Math.abs(dateLewat - dateH) / 1000;
	var days = Math.floor(res / 86400)  ;
	//alert(days);

	if(yr1 == yrH)
	{
		var dta   = 31;
		var mona  = 12-1;
		var yra   = yrH;
		var datea = new Date(yra, mona, dta);
		//alert("date a"+datea);
		//alert("date after 3 bln "+Date3bln);
		var resa = Math.abs(datea - dateH) / 1000;
		var daysa = Math.floor(resa / 86400) 
		//alert("gap day in 2008 "+daysa);
		var leap3blnyr = yra%4;
	
		
		if(leapyrH == "0"){
			var dayYr = 366;
		}
		else{
			var dayYr = 365;
		}
		
		var award = document.getElementById("jumlah_award_pu").value;
		
		if(dateLewat>date5){
			
			var result = (award * 0.05 * daysa) / dayYr;
		}
		if(dateLewat<date8){
			var result = (award * 0.08 * daysa) / dayYr;
		}
			//alert(result);
			var finalrs = result.toFixed(2);
			//alert("final sini" +finalrs);
	}

	if(yr1 != yrH){
		//alert("masuk x sama");
		var newyr1 = yr1;
		var newyrH = yrH;
		var diffyear = newyr1 - newyrH;
		alert(diffyear);
		if(diffyear == "1")
			{
			//alert("masuk here");
			//var a = 31/12/Date3blnyear;
			
			var dta   = 31;
			var mona  = 12-1;
			var yra   = newyrH;
			var datea = new Date(yra, mona, dta);
			
			var dtb   = 01;
			var monb  = 01-1;
			var yrb   = yr1;
			var dateb = new Date(yrb, monb, dtb);
			
			alert("date a"+datea);
			alert("date b"+dateb);
			//alert("date after 3 bln "+Date3bln);
			
			var resa = Math.abs(datea - dateH) / 1000;
			var daysa = Math.floor(resa / 86400) 
			alert("gap day in 1 "+daysa);
			
			alert("date b val :"+dateb);
			alert("dateLewat val :"+dateLewat);
			
			var resb = Math.abs(dateb + dateLewat) / 1000;
			var daysb = Math.floor(resb / 86400) 
			alert("gap day in 2 "+daysb);
			
			var leap3blnyr = yra%4;
			var leapyr1 = yrb%4;
			
			if(leapyr1 == "0"){
				var dayYr = 366;
			}
			else{
				var dayYr = 365;
			}
			
			if(leapyr1 == "0"){
				var dayYr1 = 366;
			}
			else{
				var dayYr1 = 365;
			}
			//alert(dayYr1);
			var award = document.getElementById("jumlah_award_pu").value;
			
			if(dateLewat>date5){
				
				var result = (award * 0.05 * daysa) / dayYr;
				var result1 = (award * 0.05 * daysb) / dayYr1;
			}
			if(dateLewat<date8){
				var result = (award * 0.08 * daysa) / dayYr;
				var result1 = (award * 0.08 * daysb) / dayYr1;
				
			}
				alert("result : " +result);
				alert("result1 : " +result1);
				//var resultyr = result.toFixed(2) ;
				//var resultyr1 =  result1.toFixed(2) ;
				//alert(resultyr1);
				var finalyr = Math.abs(result + result1) ;
				var finalrs =  finalyr.toFixed(2) ;
				alert("final : "+finalrs);
			}
	
			
	}
			
	
	//var award = document.getElementById("jumlah_award_pu").value;
	//alert(award);
	
	if(dateLewat>date5){
		//alert(5);
		var c = Math.abs(5*award)/100;		
		//alert(c);
		
	}
	if(dateLewat<date8){
		//alert(8);
		var c = Math.abs(8*award)/100;		
		//alert(c);
		
	}
//alert(c);
	
	//document.${formName}.txtAmaunLewatBantahan.value = c;
	document.${formName}.txtBilLewatBantahan.value = days;
	document.${formName}.txtAmaunLewatBantahan.value = finalrs;
	
}

*/

<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedtab});
//-->
</script>