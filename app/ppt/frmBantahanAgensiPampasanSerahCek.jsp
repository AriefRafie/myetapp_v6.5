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
    #set ($txdTkhSerah = "")
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
#end

#if ($flag=="semak")
	#foreach ($senarai in $getMaklumatSerahCek)
    	#set ($txdTkhSerah = $senarai.tarikh_serah_cek)
        #set ($txtPenerima = $senarai.nama_wakil)
        #set ($txtNoKP = $senarai.no_wakil)
        #set ($socStatusSerah = $senarai.flag_serah_cek)
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
    <div class="TabbedPanelsContent"></div>



    <div class="TabbedPanelsContent"> 
      
    <fieldset>
    <legend>Penyerahan Cek</legend>
    	<table width="100%" border="0">
        	<tr>
            	<td width="18%"><font color="red">*</font>&nbsp;Penerima Cek/Wakil</td>
           	  <td width="1%">:</td>
           	  <td width="43%">
              
              #if ($mode=="disabled")
              <input type="text" name="txtPenerima" id="txtPenerima" value="$!txtPenerima" size="50" class="disabled" readonly  />
              #else
              <input type="text" name="txtPenerimaX" id="txtPenerimaX" value="$!txtPenerima" size="50"  />
              <input type="hidden" name="txtPenerima" id="txtPenerima" value="$!txtPenerima" />
              #end
              
              </td>
           	  <td width="15%"><font color="red">*</font>&nbsp;Tarikh Serah Cek</td>
           	  <td width="1%">:</td>
           	  <td width="22%">
              
              #if ($mode=="disabled")
              <input type="text" name="txdTkhSerah" id="txdTkhSerah" value="$!txdTkhSerah" size="10" class="disabled" readonly />
              #else
              <input type="text" name="txdTkhSerah" id="txdTkhSerah" value="$!txdTkhSerah" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" tabindex="3" />
            <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhSerah',false,'dmy');" />&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>
              #end
              
            </td>
          </tr>
        	<tr>
            	<td><font color="red">*</font>&nbsp;No. KP</td>
                <td>:</td>
                <td>
                
                #if ($mode=="disabled")
                <input type="text" name="txtNoKP" id="txtNoKP" value="$!txtNoKP" class="disabled" readonly />
                #else
                 <input type="text" name="txtNoKPx" id="txtNoKPx" value="$!txtNoKP" />
                <input type="hidden" name="txtNoKP" id="txtNoKP" value="$!txtNoKP" />
                #end  
                            
              </td>
                <td>Status Penyerahan</td>
                <td width="1%">:</td>
           	  	<td>
                <select name="socStatusSerah" id="socStatusSerah" style="text-transform:uppercase;" onblur="uppercase()" class="autoselect" disabled >
           	  	                     
			#if($socStatusSerah=="1")                                                              
           	  	  <option value="1">01-Diserahkan</option>
                  <option value="2">02-Tidak Diserahkan</option>           	  	                        
            #elseif($socStatusSerah=="2")                                                            
           	  	  <option value="2">02-Tidak Diserahkan</option>
                  <option value="1">01-Diserahkan</option>           	  	             
	        #else                                                                 
           	  	  <option value="1">01-Diserahkan</option>
                  <option value="2">02-Tidak Diserahkan</option>          	  	              
            #end         
          
         	  	  </select>
           	  	</td>
            </tr>                    
        </table>   
    </fieldset>
 <!--------------------------------------------- END TAB PENYERAHAN CEK ---------------------------------------------------->   
 
<div align="center"> 
  #if ($button == "view")
  <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskini_serahCekAgensi()" /> 
  <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />
  <!--<input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="" /> -->
  #end
          
  #if ($button == "edit")
  <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:update_serahCekAgensi()" />
  <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batal_DataSerahCek()" />    
  #end
  
  #if ($button == "add")
  <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:update_serahCekAgensi()" />
  <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batal_noDataSerahCek()" />  
  #end
  
  <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="javascript:kembali_listPB()" />
      
</div>       
    
    
    </div>
    
    
    
    
    
    
    
    
    <div class="TabbedPanelsContent"></div>
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
        <td><a href="#" class="style2" onClick="javascript:cetaksuratMaklumanSerahBayaranPampasanKpdAP('$id_bayaran','$id_fail')"><font color="blue"> Surat Makluman Kepada Agensi Pemohon - Makluman Cek Pampasan Telah Diserahkan Kepada PB</font></a></td>
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

function cetaksuratMaklumanSerahBayaranPampasanKpdAP(id_bayaran,id_fail) {
	var url = "../servlet/ekptg.report.ppt.suratMaklumanSerahBayaranPampasanKpdAP?idBayaran="+id_bayaran+"&idFail="+id_fail;
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

function batal_noDataSerahCek(){
	document.${formName}.command.value = "batal_noDataSerahCek";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
	document.${formName}.submit();
}

function batal_DataSerahCek(){
	document.${formName}.command.value = "batal_DataSerahCek";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
	document.${formName}.submit();
}

function kembali_listPB(){
	document.${formName}.command.value = "kembali_listPB";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
	document.${formName}.submit();
}

function update_serahCekAgensi(){

	if(document.${formName}.txtPenerimaX.value == ""){
		alert("Sila masukkan \"Penerima Cek/Wakil\" terlebih dahulu.");
  		document.${formName}.txtPenerimaX.focus(); 
		return;		
	}	
	if(document.${formName}.txtNoKPx.value == ""){
		alert("Sila masukkan \"No KP\" terlebih dahulu.");
  		document.${formName}.txtNoKPx.focus(); 
		return;		
	}
	if(document.${formName}.txdTkhSerah.value == ""){
		alert("Sila masukkan \"Tarikh Serah Cek\" terlebih dahulu.");
  		document.${formName}.txdTkhSerah.focus(); 
		return;		
	}			
	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.command.value = "update_serahCekAgensi";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
		document.${formName}.submit();
	}
}

function kemaskini_serahCekAgensi(){
	document.${formName}.command.value = "kemaskini_serahCekAgensi";
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