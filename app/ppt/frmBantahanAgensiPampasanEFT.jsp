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

#if($clearForm == "yes")
	#set ($txtNoRujSurat = "")
    #set ($txdTkhSurat = "")
    #set ($txtNoEFT = "")
    #set ($txdTkhTerimaSurat = "")
    #set ($txdTkhBayaran = "")
    #set ($txtNoBaucer = "")
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
	#foreach ($senarai in $getMaklumatViaEFT)
		#set ($txtNoRujSurat = $senarai.no_rujukan_surateft)
        #set ($txdTkhSurat = $senarai.tarikh_surat_eft)
        #set ($txtNoEFT = $senarai.no_eft)
        #set ($txdTkhTerimaSurat = $senarai.tarikh_terima_eft)
        #set ($txdTkhBayaran = $senarai.tarikh_bayaran_eft)
        #set ($txtNoBaucer = $senarai.no_baucer)
		#set ($id_bayaran = $senarai.id_bayaran)
        <input type="hidden" name="id_bayaran" id="id_bayaran" value="$id_bayaran" />        
    #end
#end 



<fieldset>
<legend>MAKLUMAT PAMPASAN PIHAK BERKEPENTINGAN</legend>
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
                <input type="text" name="txtAmaunX" id="txtAmaunX" value="$!Util.formatDecimal($!amaun_award)" maxlength="12" size="15" style="text-align:right" class="disabled" readonly />
                #else
                <input type="text" name="txtAmaunX" id="txtAmaunX" value="$!Util.formatDecimal($!amaun_award)" maxlength="12" size="15" style="text-align:right" class="disabled" readonly />
                <input type="hidden" name="txtAmaun" id="txtAmaun" value="$!amaun_award" />
                #end
              </td>
            </tr>                                      
        </table>   
    </fieldset>
 <!--------------------------------------------- END TAB BAYARAN MELALUI EFT -------------------------------------------->   
 
<div align="center"> 

    #if ($button == "add")
       <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpan_eftAgensi()" /> 
       <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="" /> 
    #end  

    #if ($button == "edit")
  	   <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:edit_eftAgensi()" /> 
       <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="" /> 
    #end 

    #if ($button == "view")
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:skrin_editeftAgensi()" /> 
     <!-- <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="" /> -->
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />
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

function skrin_editeftAgensi(){
	document.${formName}.command.value = "skrin_editeftAgensi";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
	document.${formName}.submit();
}

function setSelectedTab(tabid) {
	document.${formName}.selectedtab.value = tabid;
}

function simpan_eftAgensi(){

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
		document.${formName}.command.value = "simpan_eftAgensi";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
		document.${formName}.submit();
	}
}

function edit_eftAgensi(){

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
		document.${formName}.command.value = "edit_eftAgensi";
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