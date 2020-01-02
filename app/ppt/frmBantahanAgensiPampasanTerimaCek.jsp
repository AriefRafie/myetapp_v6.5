<style type="text/css">
<!--
.kaler_biru {color: #0000FF}
-->
</style>

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

#foreach ($senarai in $getMaklumatAP)
	#set ($id_bantahan=$senarai.id_bantahan)
    #set ($no_hakmilik=$senarai.no_hakmilik)   
    #set ($id_agensi=$senarai.id_agensi)   
    #set ($nama_agensi=$senarai.nama_agensi)
    #set ($id_mukim=$senarai.id_mukim)
    #set ($nama_mukim=$senarai.nama_mukim)
    #set ($jumlah_award=$senarai.jumlah_award)
    #set ($luas_lot=$senarai.luas_lot)
    #set ($unit_luaslot=$senarai.keterangan)
    #set ($amaun_award=$senarai.amaun_award)
    #set ($status_bantahan_ap=$senarai.status_bantahan_ap)
    #set ($flag_bayar_bantahan=$senarai.flag_bayar_bantahan)
    #set ($keteranganstatusbantahanap=$senarai.keteranganstatusbantahanap)
    <input type="hidden" name="id_agensi" id="id_agensi" value="$id_agensi" />
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
<legend>Maklumat Pampasan Agensi Pemohon</legend>
<!------------------------------------------ HEADER ----------------------------------------------------->
	<table width="100%" border="0">		
        <tr>
            <td style="text-transform:uppercase">Nama Agensi</td>
            <td>:</td>
            <td><span class="kaler_biru">$!nama_agensi.toUpperCase()</span></td>
        </tr>
        <tr>
            <td style="text-transform:uppercase" valign="top">No Hakmilik</td>
            <td valign="top">:</td>
            <td><span class="kaler_biru">$!no_hakmilik.toUpperCase()</span></td>
        </tr>
        <tr>
            <td width="36%" valign="top" style="text-transform:uppercase">Mukim</td>
            <td width="1%" valign="top">:</td>
          <td width="63%"><span class="kaler_biru">$!nama_mukim.toUpperCase()</span></td>
      </tr>
        <tr>
            <td style="text-transform:uppercase">Luas Lot</td>
            <td>:</td>
            <td><span class="kaler_biru">$!luas_lot&nbsp;$!unit_luaslot</span></td>
        </tr>     
        <tr>
            <td valign="top" style="text-transform:uppercase">Jumlah Pampasan Ditawarkan</td>
            <td valign="top">:</td>
            <td><span class="kaler_biru">RM &nbsp;$!Util.formatDecimal($!jumlah_award)</span></td>
        </tr>
        <tr>
            <td valign="top" style="text-transform:uppercase">Jumlah Award Bantahan Dipulangkan</td>
            <td valign="top">:</td>
            <td><span class="kaler_biru">RM &nbsp;$!Util.formatDecimal($!amaun_award)</span></td>
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
    <legend>Penerimaan Cek</legend>
    	<table width="100%" border="0">
        	<tr>
            	<td width="14%"><font color="red">*</font>&nbsp;Tarikh Terima</td>
           	  	<td width="1%">:</td>
           	  	<td width="45%">
                
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
                <input type="text" name="txtPenamaCek" id="txtPenamaCek" value="$!nama_agensi" size="50" class="disabled" readonly />
                #else
                    <input type="text" name="txtPenamaCek" id="txtPenamaCek" value="$!nama_agensi" size="50" tabindex="2" />
            	#end  
                              
                </td>
                <td><font color="red">*</font>&nbsp;Amaun Cek&nbsp;(RM)</td>
                <td width="1%">:</td>
           	  	<td>
                
              	#if ($mode=="disabled")
              	<input type="text" name="txtAmaunCekx" id="txtAmaunCekx" value="$!Util.formatDecimal($!amaun_award)" maxlength="12" size="15" style="text-align:right" class="disabled" readonly />
              	#else
              	<input type="text" name="txtAmaunCekx" id="txtAmaunCekx" value="$!Util.formatDecimal($!amaun_award)" maxlength="12" size="15" style="text-align:right" class="disabled" readonly tabindex="5" />              
         	  	<input type="hidden" name="txtAmaunCek" id="txtAmaunCek" value="$!amaun_award" />  
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
  <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskiniTerimaCekAgensi()" /> 
  <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" /> 
  <!--<input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="" /> -->
  #end

  #if ($button=="edit")
  <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:update_terimaCekAgensi()" /> 
  <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batal_updateTerimaCek()" /> 
  #end
  
  #if ($button=="add")
  <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:add_terimaCekAgensi()" /> 
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
        <td><a href="#" class="style2" onClick="javascript:cetaksuratUtkPanggilanTerimaPampasanKpdPB('$id_bayaran','$id_fail')"><font color="blue"> Surat Kepada Pihak Berkepentingan - Makluman Supaya Hadir Untuk Penyerahan Cek Pampasan </font></a></td>
      </tr>           
    </table>
</fieldset>
<!------------------------------------------ END OUTPUT LAPORAN/SURAT ------------------------------------------>

<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name="id_permohonan" id="id_permohonan" value="$id_permohonan" />
<input type="hidden" name="id_fail" id="id_fail" value="$id_fail" />
<input type="hidden" name="id_status" id="id_status" value="$id_status" /> 
<input type="hidden" name="id_hakmilik" id="id_hakmilik" value="$id_hakmilik" />
<input type="hidden" name="status_bantahan_ap" id="status_bantahan_ap" value="$status_bantahan_ap" />
<input type="hidden" name="id_bantahan" id="id_bantahan" value="$id_bantahan" />

<script type="text/javascript">

function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

function cetaksuratUtkPanggilanTerimaPampasanKpdPB(id_bayaran,id_fail) {
	var url = "../servlet/ekptg.report.ppt.suratUtkPanggilanTerimaPampasanKpdPB?idBayaran="+id_bayaran+"&idFail="+id_fail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}

function TerimaCek(){
	document.${formName}.command.value = "TerimaCekAgensi";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
	document.${formName}.submit();
}

function SerahCek(){
	document.${formName}.command.value = "SerahCekAgensi";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
	document.${formName}.submit();
}

function BayaranEft(){
	document.${formName}.command.value = "BayaranEftAgensi";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
	document.${formName}.submit();
}

function setSelectedTab(tabid) {
	document.${formName}.selectedtab.value = tabid;
}

function add_terimaCekAgensi(){

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
		document.${formName}.command.value = "add_terimaCekAgensi";
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

function kemaskiniTerimaCekAgensi(){
		document.${formName}.command.value = "kemaskiniTerimaCekAgensi";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
		document.${formName}.submit();
}

function update_terimaCekAgensi(){

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
		document.${formName}.command.value = "update_terimaCekAgensi";
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




<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedtab});
//-->
</script>