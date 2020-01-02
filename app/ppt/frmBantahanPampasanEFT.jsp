
<div id="checking_progress"></div>
#if ($completed)
<script>
parent.document.getElementById("checking_progress").innerHTML="<div class=\"status\">URUSAN PAMPASAN TIDAK DAPAT DITERUSKAN.SILA CUBA LAGI.</div>";
</script>
#end

#if($clearForm == "yes")
	#set ($txtNoRujSurat = "")
    #set ($txdTkhSurat = "")
    #set ($txtNoEFT = "")
    #set ($txdTkhTerimaSurat = "")
    #set ($txdTkhBayaran = "")
    #set ($txtNoBaucer = "")
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

#if ($flag=="semak")
	#foreach ($senarai in $getMaklumatViaEFT)
		#set ($txtNoRujSurat = $senarai.no_rujukan_surateft)
        #set ($txdTkhSurat = $senarai.tarikh_surat_eft)
        #set ($txtNoEFT = $senarai.no_eft)
        #set ($txdTkhTerimaSurat = $senarai.tarikh_terima_eft)
        #set ($txdTkhBayaran = $senarai.tarikh_bayaran_eft)
        #set ($txtNoBaucer = $senarai.no_baucer)
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

<!---------------------------------------- TAB BAYARAN MELALUI EFT ----------------------------------------------->

<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onclick="setSelectedTab(0);TerimaCek()">Penerimaan Cek</li>
    <li class="TabbedPanelsTab" tabindex="1" onclick="setSelectedTab(1);SerahCek()">Penyerahan Cek</li>
    <li class="TabbedPanelsTab" tabindex="2" onclick="setSelectedTab(2);BayaranEft()">Bayaran Melalui EFT</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    
    
    
    <div class="TabbedPanelsContent">
    
  <fieldset>
  
    <legend>Maklumat Bayaran</legend>
    	<table width="100%" border="0">
        	<tr>
              <td width="17%"><font color="red">*</font>&nbsp;No. Ruj. Surat</td>
              <td width="1%">:</td>
           	  <td width="32%">
              #if ($mode=="disabled")
              <input type="text" name="txtNoRujSurat" id="txtNoRujSurat" value="$!txtNoRujSurat" class="disabled" readonly />
              #else
              <input type="text" name="txtNoRujSurat" id="txtNoRujSurat" value="$!txtNoRujSurat" tabindex="1" />
              #end
              </td>
           	  <td width="18%"><font color="red">*</font>&nbsp;Tarikh Terima Surat</td>
              <td width="1%">:</td>
              <td width="31%">
              #if ($mode=="disabled")
              <input type="text" name="txdTkhTerimaSurat" id="txdTkhTerimaSurat" value="$!txdTkhTerimaSurat" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" class="disabled" readonly />
              #else
              <input type="text" name="txdTkhTerimaSurat" id="txdTkhTerimaSurat" value="$!txdTkhTerimaSurat" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" tabindex="4" />
            <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhTerimaSurat',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>
              #end
              </td>
          </tr>
        	<tr>
            	<td><font color="red">*</font>&nbsp;Tarikh Surat</td>
                <td>:</td>
                <td>
                #if ($mode=="disabled")
                <input type="text" name="txdTkhSurat" id="txdTkhSurat" value="$!txdTkhSurat" size="10" class="disabled" readonly />
                #else
                <input type="text" name="txdTkhSurat" id="txdTkhSurat" value="$!txdTkhSurat" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" tabindex="2" />
            <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhSurat',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>
            	#end
              </td>
                <td><font color="red">*</font>&nbsp;Tarikh Bayaran</td>
                <td width="1%">:</td>
           	  <td>
                #if ($mode=="disabled")
                <input type="text" name="txdTkhBayaran" id="txdTkhBayaran" value="$!txdTkhBayaran" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" class="disabled" readonly  />
                #else
                <input type="text" name="txdTkhBayaran" id="txdTkhBayaran" value="$!txdTkhBayaran" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" tabindex="5" />
            <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhBayaran',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>
            	#end
           	  </td>
            </tr>   
        	<tr>
            	<td><font color="red">*</font>&nbsp;No. EFT</td>
                <td>:</td>
                <td>
                #if ($mode=="disabled")
                <input type="text" name="txtNoEFT" id="txtNoEFT" value="$!txtNoEFT" class="disabled" readonly />
                #else
                <input type="text" name="txtNoEFT" id="txtNoEFT" value="$!txtNoEFT" tabindex="3" />
                #end
              </td>
                <td><font color="red">*</font>&nbsp;No. Baucar</td>
                <td width="1%">:</td>
           	  <td>
                #if ($mode=="disabled")
                 <input type="text" name="txtNoBaucer" id="txtNoBaucer" value="$!txtNoBaucer" class="disabled" readonly />
                #else
                <input type="text" name="txtNoBaucer" id="txtNoBaucer" value="$!txtNoBaucer" tabindex="6" />
                #end
              </td>
            </tr>   
        	<tr>
            	<td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;&nbsp;&nbsp;Amaun (RM)</td>
                <td width="1%">:</td>
           	  <td>
                #if ($mode=="disabled")                
                <input type="text" name="txtAmaunX" id="txtAmaunX" value="$!Util.formatDecimal($!jumlah_award_bantahan)" maxlength="12" class="disabled" readonly />
                #else
                <input type="text" size="11" name="txtAmaunX" id="txtAmaunX" value="$!Util.formatDecimal($!jumlah_award_bantahan)" maxlength="12" class="disabled" readonly />
                <input type="hidden" name="txtAmaun" id="txtAmaun" value="$!jumlah_award_bantahan" />
                #end
              </td>
            </tr>                                      
        </table>   
    </fieldset>
    
   
 <!--------------------------------------------- END TAB BAYARAN MELALUI EFT -------------------------------------------->   

<div align="center"> 

    #if ($button == "add")
       <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpan_eft()" /> 
       <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:BayaranEft()" /> 
    #end  

    #if ($button == "edit")
  	   <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:edit_eft()" /> 
       <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:BayaranEft()" /> 
    #end 

    #if ($button == "view")
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:skrin_editeft()" /> 
     <!-- <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="" /> -->
    <!--  <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />-->
    #end   

  	<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="javascript:kembali_listPB()" />

</div>       
    
     
    
    
    </div>
    
    
    
    
    
    
    
  </div>
</div>
<input type=hidden name=selectedtab />
<!------------------------------------------ END TAB PENYERAHAN CEK ------------------------------------------->

    
<!--</fieldset>-->

<!------------------------------------------ OUTPUT LAPORAN/SURAT ----------------------------------------------->
<br/>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" class="style2" onClick=""><font color="blue"> Tiada Output </font></a></td>
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
<input type="hidden" name="id_bayaran" id="id_bayaran" value="$id_bayaran" />

<script type="text/javascript">

function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function TerimaCek(){
	//document.${formName}.command.value = "TerimaCekAgensi";
	document.${formName}.command.value = "TerimaCek";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
	document.${formName}.submit();
}

function SerahCek(){
	//document.${formName}.command.value = "SerahCekAgensi";
	document.${formName}.command.value = "SerahCek";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
	document.${formName}.submit();
}

function BayaranEft(){
	//document.${formName}.command.value = "BayaranEftAgensi";
	document.${formName}.command.value = "BayaranEft";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
	document.${formName}.submit();
}

function skrin_editeft(){
	document.${formName}.command.value = "skrin_editeft";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
	document.${formName}.submit();
}

function setSelectedTab(tabid) {
	document.${formName}.selectedtab.value = tabid;
}

function simpan_eft(){

	if(document.${formName}.txtNoRujSurat.value == ""){
		alert("Sila masukkan \"No Ruj. Surat\" terlebih dahulu.");
  		document.${formName}.txtNoRujSurat.focus(); 
		return;		
	}	
	if(document.${formName}.txdTkhSurat.value == ""){
		alert("Sila masukkan \"Tarikh Surat\" terlebih dahulu.");
  		document.${formName}.txdTkhSurat.focus(); 
		return;		
	}
	if(document.${formName}.txtNoEFT.value == ""){
		alert("Sila masukkan \"No. EFT\" terlebih dahulu.");
  		document.${formName}.txtNoEFT.focus(); 
		return;		
	}	
	if(document.${formName}.txdTkhTerimaSurat.value == ""){
		alert("Sila masukkan \"Tarikh Terima Surat\" terlebih dahulu.");
  		document.${formName}.txdTkhTerimaSurat.focus(); 
		return;		
	}	
	if(document.${formName}.txdTkhBayaran.value == ""){
		alert("Sila masukkan \"Tarikh Bayaran\" terlebih dahulu.");
  		document.${formName}.txdTkhBayaran.focus(); 
		return;		
	}
	if(document.${formName}.txtNoBaucer.value == ""){
		alert("Sila masukkan \"No. Baucar\" terlebih dahulu.");
  		document.${formName}.txtNoBaucer.focus(); 
		return;		
	}						
	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.command.value = "simpan_eft";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
		document.${formName}.submit();
	}
}

function edit_eft(){

	if(document.${formName}.txtNoRujSurat.value == ""){
		alert("Sila masukkan \"No Ruj. Surat\" terlebih dahulu.");
  		document.${formName}.txtNoRujSurat.focus(); 
		return;		
	}	
	if(document.${formName}.txdTkhSurat.value == ""){
		alert("Sila masukkan \"Tarikh Surat\" terlebih dahulu.");
  		document.${formName}.txdTkhSurat.focus(); 
		return;		
	}
	if(document.${formName}.txtNoEFT.value == ""){
		alert("Sila masukkan \"No. EFT\" terlebih dahulu.");
  		document.${formName}.txtNoEFT.focus(); 
		return;		
	}	
	if(document.${formName}.txdTkhTerimaSurat.value == ""){
		alert("Sila masukkan \"Tarikh Terima Surat\" terlebih dahulu.");
  		document.${formName}.txdTkhTerimaSurat.focus(); 
		return;		
	}	
	if(document.${formName}.txdTkhBayaran.value == ""){
		alert("Sila masukkan \"Tarikh Bayaran\" terlebih dahulu.");
  		document.${formName}.txdTkhBayaran.focus(); 
		return;		
	}
	if(document.${formName}.txtNoBaucer.value == ""){
		alert("Sila masukkan \"No. Baucar\" terlebih dahulu.");
  		document.${formName}.txtNoBaucer.focus(); 
		return;		
	}						
	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.command.value = "edit_eft";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
		document.${formName}.submit();
	}
}


function kembali_listPB(){
	document.${formName}.command.value = "kembali_listPB";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
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

<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedtab});
//-->
</script>