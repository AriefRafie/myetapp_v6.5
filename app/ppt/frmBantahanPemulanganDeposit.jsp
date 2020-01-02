<div id="checking_progress"></div>
#if ($completed)
<script>
parent.document.getElementById("checking_progress").innerHTML="<div class=\"status\">TIADA MAKLUMAT SUSULAN BANTAHAN DIDAFTARKAN.</div>";
</script>
#end

<!-- #if ($statusPerintah == "true")
    <div class="warning">
    Sila proses permohonan. Tempoh Bayaran Mahkamah Hampir Luput.
    </div>
#end -->


#if ($clearForm == "yes")
	
    #set ($sorStatusPulangDep = "")
   
#end

#foreach ( $senarai in $Header )
	#set ($nama_kementerian=$senarai.nama_kementerian)
    #set ($no_fail=$senarai.no_fail)
    #set ($no_permohonan=$senarai.no_permohonan)
    #set ($tarikh_terima=$senarai.tarikh_terima)
    #set ($projek_negeri=$senarai.projek_negeri)
    #set ($nama_daerah=$senarai.nama_daerah)
    #set ($tujuan=$senarai.tujuan)
    #set ($tarikh_kehendaki=$senarai.tarikh_kehendaki)
    #set ($no_rujukan_surat=$senarai.no_rujukan_surat)
    #set ($no_rujukan_ptd=$senarai.no_rujukan_ptd)
    #set ($no_rujukan_ptg=$senarai.no_rujukan_ptg)
    #set ($no_rujukan_upt=$senarai.no_rujukan_upt)
    #set ($keterangan=$senarai.keterangan)
    #set ($nama_agensi=$senarai.nama_agensi)
    #set ($tarikh_permohonan=$senarai.tarikh_permohonan)    
#end 

#foreach ( $senarai in $getMaklumatBantahan )
    #set ($desc_status_bantahan=$senarai.desc_status_bantahan)
#end


#set ( $id_award = $id_award.get("id_award") )

#set ( $txtKeteranganPampasan = $getKeteranganPampasan.get("keterangan_pampasan") )

<fieldset>
	<legend>PERMOHONAN BANTAHAN KE MAHKAMAH</legend>

	<!-- Header -->
	#parse("app/ppt/frmPPTHeader.jsp")
	
     </br>
<!------------------------------------------- END HEADER --------------------------------------------->  

<!------------------------------------------ TAB SUSULAN BANTAHAN -------------------------------------------->

<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
   <li class="TabbedPanelsTab" tabindex="0" onclick="setSelectedTab(0);bantahan()">Bantahan</li>
    <li class="TabbedPanelsTab" tabindex="1" onclick="setSelectedTab(1);deposit()">Deposit</li>
    #if($!idBorangO_check != "")
    <li class="TabbedPanelsTab" tabindex="2" onclick="setSelectedTab(2);borangO()">Borang O</li>
    <li class="TabbedPanelsTab" tabindex="3" onclick="setSelectedTab(3);lanjutanTempoh()">Lanjutan Tempoh</li>
    <li class="TabbedPanelsTab" tabindex="4" onclick="setSelectedTab(4);susulanBantahan()">Perintah</li>
    <li class="TabbedPanelsTab" tabindex="5" onclick="setSelectedTab(5);pemulanganDeposit()">Pemulangan Deposit
    </li>
    <li class="TabbedPanelsTab" tabindex="6" onclick="setSelectedTab(6);tarikBalikBantahan()">Tarik Balik Bantahan</li>
    <li class="TabbedPanelsTab" tabindex="7" onclick="setSelectedTab(7);batalBantahan()">Pembatalan Bantahan oleh MT</li>
    #end
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>    
    <div class="TabbedPanelsContent">
    
    	#if ($mode=="disabled")
		#set($disability = "readonly")
		#set($disabilityx = "class=disabled")
		#else
		#set($disability = "")
		#set($disabilityx = "")
		#end
    
 
<!---------------------------------------- PEMULANGAN DEPOSIT --------------------------------------------->

<br />
<fieldset>
<legend>Maklumat Pemulangan Deposit</legend>
	<table width="100%" border="0"> 
    	<tr>
           <td width="1%">#if ($mode=="") <font color="red">*</font> #end</td>
          <td width="21%">
           
          
           Status Pemulangan 
           
           </td>
       	  <td width="1%">:</td>
       	  <td width="28%">
            #if ($mode=="disabled")
            <input type="radio" name="sorStatusPulangDep" id="sorStatusPulangDep" value="1" disabled $TEMPcheckedDep1 />Dikembalikan Sepenuhnya
            #else
            <input type="radio" name="sorStatusPulangDep" id="sorStatusPulangDep" value="1" $TEMPcheckedDep1 />Dikembalikan Sepenuhnya
            #end          </td>
       	  <td width="22%"></td>
          <td width="1%"></td>
   	      <td width="27%">&nbsp;</td>
    	</tr> 
    	<tr>
          <td width="1%"></td>
          <td width="21%" valign="top">Deposit</td>
       	  <td width="1%">&nbsp;</td>
       	  <td width="28%">
            #if ($mode=="disabled")
            <input type="radio" name="sorStatusPulangDep" id="sorStatusPulangDep" value="2" disabled $TEMPcheckedDep2 />Dikembalikan Sebahagian
            #else
            <input type="radio" name="sorStatusPulangDep" id="sorStatusPulangDep" value="2" $TEMPcheckedDep2 />Dikembalikan Sebahagian
          #end          </td>
          <td width="22%">&nbsp;</td>
       	  <td width="1%"></td>
   	      <td width="27%">&nbsp;</td>
    	</tr> 
    	<tr>
          <td width="1%"></td>
    	  <td>&nbsp;</td>
    	  <td>&nbsp;</td>
    	  <td>
          #if ($mode=="disabled")
          <input type="radio" name="sorStatusPulangDep" id="sorStatusPulangDep" value="3" disabled $TEMPcheckedDep3 />Tidak Dikembalikan / Dirampas
          #else
          <input type="radio" name="sorStatusPulangDep" id="sorStatusPulangDep" value="3" $TEMPcheckedDep3 />Tidak Dikembalikan / Dirampas
          #end          </td>

    	  <td>&nbsp;</td>
    	  <td></td>
    	  <td>&nbsp;</td>
  	  </tr>
    	<tr>
          <td width="1%"></td>
          <td width="21%">&nbsp;</td>
       	  <td width="1%">&nbsp;</td>
       	  <td width="28%">
            #if ($mode=="disabled")
            <input type="radio" name="sorStatusPulangDep" id="sorStatusPulangDep" value="4" disabled $TEMPcheckedDep4 />Lain-lain
            #else
            <input type="radio" name="sorStatusPulangDep" id="sorStatusPulangDep" value="4" $TEMPcheckedDep4 />Lain-lain
          #end          </td>
   
          <td width="22%">&nbsp;</td>
       	  <td width="1%"></td>
   	      <td width="27%">&nbsp;</td>
    	</tr>  
    	                                                         
    </table>
</fieldset>

<!--------------------------------------- END PEMULANGAN DEPOSIT ------------------------------------------>
        <div align="center">
          #if ($button=="view")
          <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="javascript:kemaskiniPemulanganDeposit()" />
          #end
          
          #if ($button=="edit")
          <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="javascript:simpanPemulanganDeposit()" /> 
          <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="" /> 
          #end
          
          <input type="button" name="cmdkembali" id="cmdkembali" value="Kembali" onClick="javascript:kembaliList()" />
              
        </div>     
    </div>
    
    
    
  
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
  </div>
</div>
<input type=hidden name=selectedtab />
<!--------------------------------------- END TAB BANTAHAN ------------------------------------------->

</fieldset>


<!------------------------------------------ OUTPUT LAPORAN/SURAT ----------------------------------------------->

<!------------------------------------------ END OUTPUT LAPORAN/SURAT ------------------------------------------>

<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name="id_permohonan" id="id_permohonan" value="$id_permohonan" />
<input type="hidden" name="id_fail" id="id_fail" value="$id_fail" />
<input type="hidden" name="id_hakmilikpb" id="id_hakmilikpb" value="$id_hakmilikpb" />
<input type="hidden" name="id_hakmilik" id="id_hakmilik" value="$id_hakmilik" />
<input type="hidden" name="id_pihakberkepentingan" id="id_pihakberkepentingan" value="$id_pihakberkepentingan" />
<input type="hidden" name="status_bantahan" id="status_bantahan" value="$status_bantahan" />
<input type="hidden" name="id_bantahan" id="id_bantahan" value="$id_bantahan" />
<input type="hidden" name="id_award" id="id_award" value="$id_award" />

<script type="text/javascript">


function open_header(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
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

function cetaksuratKepadaAPSupayaMembayarPampasanTambahan(id_fail,id_bantahan,id_permohonan) {
	//var url = "../servlet/ekptg.report.ppt.suratKepadaAPSupayaMembayarPampasanTambahan?idFail="+id_fail+"&id_bantahan="+id_bantahan;
	
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_fail="+id_fail+"&id_bantahan="+id_bantahan+"&id_permohonan="+id_permohonan+"&report=suratKepadaAPSupayaMembayarPampasanTambahan&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
	
	
}

function kembaliList(){
	document.${formName}.command.value = "kembaliList";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}
function setSelectedTab(tabid) {
	document.${formName}.selectedtab.value = tabid;
}
function bantahan(){
	document.${formName}.command.value = "bantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}
function deposit(){
	document.${formName}.command.value = "deposit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}
function borangO(){
	document.${formName}.command.value = "borangO";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}
function lanjutanTempoh(){
	document.${formName}.command.value = "lanjutanTempoh";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}
function susulanBantahan(){
	document.${formName}.command.value = "susulanBantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}
function pemulanganDeposit(){
	document.${formName}.command.value = "pemulanganDeposit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}
function tarikBalikBantahan(){
	document.${formName}.command.value = "tarikBalikBantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}
function batalBantahan(){
	document.${formName}.command.value = "batalBantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}
function simpanPemulanganDeposit(){
	
	
	var radioSelected1 = false;	
	for (i = 0;  i < ${formName}.sorStatusPulangDep.length;  i++){
		if (${formName}.sorStatusPulangDep[i].checked)
		radioSelected1 = true;
	}	
	if (!radioSelected1){
		alert("Sila pilih \"Status Pemulangan Deposit\" terlebih dahulu.");
		return (false);
	}							
	
/*	if(document.${formName}.txtKosPengapitHakim.value == ""){
		alert("Sila masukkan \"Kos Pengapit Hakim (RM)\" terlebih dahulu.");
  		document.${formName}.txtKosPengapitHakim.focus(); 
		return;		
	}		
	if(document.${formName}.txtTempohBayaran.value == ""){
		alert("Sila masukkan \"Tempoh Bayaran\" terlebih dahulu.");
  		document.${formName}.txtTempohBayaran.focus(); 
		return;		
	}	
	if(document.${formName}.unitTempohBayaran.value == ""){
		alert("Sila masukkan \"Unit Tempoh Bayaran\" terlebih dahulu.");
  		document.${formName}.unitTempohBayaran.focus(); 
		return;		
	}				*/	
	else{
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.command.value = "simpanPemulanganDeposit";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
		document.${formName}.submit();
	}
}

function kemaskiniPemulanganDeposit(){
	document.${formName}.command.value = "kemaskiniPemulanganDeposit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}

<!--UTK DEFAULTKAN TAB KEPADA TAB BANTAHAN
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedtab});
//-->
</script>
