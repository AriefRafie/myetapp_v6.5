
<div id="checking_progress"></div>
#if ($completed)
<script>
parent.document.getElementById("checking_progress").innerHTML="<div class=\"status\">URUSAN PAMPASAN TIDAK DAPAT DITERUSKAN.SILA CUBA LAGI.</div>";
</script>
#end

#if ($clearForm == "yes")
    #set ($txdTkhSerah = "")
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
    #set ($txtPenerima=$senarai.nama_pb)
    #set ($txtNoKP=$senarai.no_pb)
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
              <input type="text" name="txtPenerima" id="txtPenerima" value="$!txtPenerima" size="50"class="disabled" readonly  />
              #else
              <input type="text" name="txtPenerimaX" id="txtPenerimaX" value="$!txtPenerima" size="50" />
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
  <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskini_serahCek()" /> 
  <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />
  <!--<input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="" /> -->
  #end
          
  #if ($button == "edit")
  <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:update_serahCek()" />
  <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batal_DataSerahCek()" />    
  #end
  
  #if ($button == "add")
  <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:update_serahCek()" />
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
        <td><a href="#" class="style2" onClick="javascript:cetaksuratMaklumanSerahBayaranPampasanKpdAP_bantahan('$id_bayaran','$id_fail','$id_permohonan','$id_hakmilikpb')"><font color="blue"> Surat Makluman Kepada Agensi Pemohon - Makluman Cek Pampasan Telah Diserahkan Kepada PB</font></a></td>
      </tr> 
      
       <tr>
        <td><a href="#" class="style2" onClick="javascript:cetakAkuanPenerimaanCek_bantahan('$id_bayaran','$id_fail','$id_permohonan','$id_hakmilikpb','$id_bantahan')"><font color="blue"> Akuan Penerimaan Cek - Makluman kepada AP cek telah diserahkan kepada tuan tanah </font></a></td>
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

function cetaksuratMaklumanSerahBayaranPampasanKpdAP_bantahan(id_bayaran,id_fail,id_permohonan,id_hakmilikpb) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_bayaran="+id_bayaran+"&id_fail="+id_fail+"&id_permohonan="+id_permohonan+"&id_hakmilikpb="+id_hakmilikpb+"&report=suratMaklumanSerahBayaranPampasanKpdAP_bantahan&flagReport=S";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

/*	var url = "../servlet/ekptg.report.ppt.suratMaklumanSerahBayaranPampasanKpdAP?idBayaran="+id_bayaran+"&idFail="+id_fail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	*/
}

function cetakAkuanPenerimaanCek_bantahan(id_bayaran,id_fail,id_permohonan,id_hakmilikpb,id_bantahan) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_bayaran="+id_bayaran+"&id_fail="+id_fail+"&id_permohonan="+id_permohonan+"&id_hakmilikpb="+id_hakmilikpb+"&id_bantahan="+id_bantahan+"&report=AkuanPenerimaanCek_bantahan&flagReport=S";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

/*	var url = "../servlet/ekptg.report.ppt.suratMaklumanSerahBayaranPampasanKpdAP?idBayaran="+id_bayaran+"&idFail="+id_fail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	*/
}

function TerimaCek(){
	document.${formName}.command.value = "TerimaCek";
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

function update_serahCek(){

	var currentTime = new Date()

	var str1  = document.getElementById("txdTkhSerah").value;   
    var dt1   = parseInt(str1.substring(0,2),10);
	var mon1  = parseInt(str1.substring(3,5),10)-1;
    var yr1   = parseInt(str1.substring(6,10),10);   
    var date1 = new Date(yr1, mon1, dt1);

	
	if(date1 > currentTime){
   		alert("Sila pastikan Tarikh Serah Cek tidak melebihi dari tarikh hari ini");
	 	document.${formName}.txdTkhSerah.focus();
	 	return;	
	}
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
		document.${formName}.command.value = "update_serahCek";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasan";
		document.${formName}.submit();
	}
}

function kemaskini_serahCek(){
	document.${formName}.command.value = "kemaskini_serahCek";
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