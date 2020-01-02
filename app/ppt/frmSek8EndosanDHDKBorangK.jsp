#parse("app/ppt/Sek8Paging.jsp")
#set($frmtdate = "&nbsp;<i><font style='font-size:10px'>dd/mm/yyyy</font></i>")

<fieldset id="top">
<legend><strong>Catatan Memorial DHDK</strong></legend>
</fieldset>

	#parse("app/ppt/frmPPTHeader.jsp")

<br/>

	#foreach($data in $dataHeader)
		#set($txtNamaPejabatPTG=$data.nama_endosan_borangk_ptg)
		#set($txtNamaPejabatPTD=$data.nama_endosan_borangk_ptd)
		#set($lblCatatanPTG=$data.catatan_borangk_ptg)
		#set($lblCatatanPTD=$data.catatan_borangk_ptd)
	#end

	<fieldset id="bottom">
	<legend><strong>Pejabat PTG</strong></legend>
		<table width="" border="0">
			<tr>
				<td width="20%">Nama Pejabat</td>
				<td width="1%">:</td>
				<td width="79%"><input type="text" readonly class="disabled" name="txtNamaPejabatPTG" id="txtNamaPejabatPTG" maxlength="" value="$!txtNamaPejabatPTG"  size="70"  /></td>
			</tr>
		</table>
	</fieldset>
	
	<fieldset>
			#if($saiz_listHakmilikPTG != 0)
			<table width="100%" border="0">   
                	<tr>
                		<td><input type="button" name="cmdTambahPTG" value="Pilih" onClick="javascript:tambahPTG('$!id_permohonan')"></td>
                    	<!-- <td align="right">Carian No.LOT/No.PT :&nbsp;<input type="text" name="carianNoLot" value="$!carianNoLot" maxlength="20" size="20"   ><a href="javascript:cariLOT('$!id_permohonan')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanLOT('$!id_permohonan')">&nbsp;<u>KOSONGKAN</u></a></td> -->
    				</tr>
    		</table>
			#end
    			
    		#if($saiz_listHakmilikPTGOnly > 5)
                <div style="width:100%;height:100;overflow:auto"> 
            #end	
    			
    		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td align="center" width="4%"><b>No</b></td>
        			<td width="25%"><b>Bandar/Pekan/Mukim</b></td>
        			<td width="15%"><b>Jenis Hakmilik</b></td>
        			<td width="11%"><b>No.Hakmilik</b></td>
        			<td width="15%"><b>Tarikh Hantar Endorsan</b></td>
        			<td width="15%"><b>Tarikh Terima Endorsan</b></td>
        			#if($saiz_listHakmilikPTGOnly!=0)
        			<td align="center" width="15%"><b>Hantar ke HTP</b></td>
        			#end
        		</tr>
        		
        		#if($saiz_listHakmilikPTGOnly!=0)
                    #foreach($listTanah in $listHakmilikPTGOnly)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
               <tr>
               	   <td class="$row" align="center">$!listTanah.bil</td>
                   <td class="$row">$!listTanah.nama_mukim</td>
                   <td class="$row">$!listTanah.jenis_hakmilik</td>
                   <td class="$row">$!listTanah.no_hakmilik</td>
                   <td class="$row">$!listTanah.tarikh_catatan</td>
                   <td class="$row">$!listTanah.tarikh_terima
                   #if($!listTanah.checkExpired != '0' && $!listTanah.checkExpired!="")
                   <span class="blink"><i><font color='red' style='font-size:10px'>Telah dihantar selama <b>$!listTanah.dayCountExpired</b> hari</font></i></span>
                   #end
                   </td>
                   #if($saiz_listHakmilikPTGOnly!=0)
                   		<td class="$row" align="center">
                   		#if($!listTanah.flag_hantar_htp != "1")
                   		<input type="button" name="cmdHantarHTP" value="Hantar" onClick="javascript:hantarHTP('$!listTanah.id_hakmilik')">
                   		#else
                   		<b>Telah hantar pada $!listTanah.tarikh_hantar_htp</b>
                   		#end
                   		</td>
               	   #end
               <tr>
                    #end
               #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
               #end
		  </table>
	
			#if($saiz_listHakmilikPTGOnly > 5)
                </div>
            #end
            
	</fieldset>
	
	<fieldset>
		<table width="100%" border="0">
			<tr>
				<td width="1%">&nbsp;</td>
				<td width="10%" valign="top"><b>Catatan</b></td>
				<td width="1%" valign="top">:</td>
				<td width="58%">$!lblCatatanPTG</td>
				<td width="30%">&nbsp;</td>
			</tr>
		</table>
	</fieldset>
	
<br/>
<br/>
	
	<fieldset>
	<legend><strong>Pejabat PTD</strong></legend>
		<table width="" border="0">
			<tr>
				<td width="20%">Nama Pejabat</td>
				<td width="1%">:</td>
				<td width="79%"><input type="text" readonly class="disabled" name="txtNamaPejabatPTD" id="txtNamaPejabatPTD" maxlength="" value="$!txtNamaPejabatPTD"  size="70"  /></td>
			</tr>
		</table>
	</fieldset>
	
	<fieldset>
		
			#if($saiz_listHakmilikPTD != 0)
			<table width="100%" border="0">   
                	<tr>
                		<td><input type="button" name="cmdTambahPTD" value="Pilih" onClick="javascript:tambahPTD('$!id_permohonan')"></td>
                    </tr>
    		</table>
			#end
    			
    		#if($saiz_listHakmilikPTDOnly > 5)
                <div style="width:100%;height:100;overflow:auto"> 
            #end	
    			
    		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td align="center" width="4%"><b>No</b></td>
        			<td width="25%"><b>Bandar/Pekan/Mukim</b></td>
        			<td width="15%"><b>Jenis Hakmilik</b></td>
        			<td width="11%"><b>No.Hakmilik</b></td>
        			<td width="15%"><b>Tarikh Hantar Endorsan</b></td>
        			<td width="15%"><b>Tarikh Terima Endorsan</b></td>
        			#if($saiz_listHakmilikPTDOnly != 0)
        			<td align="center" width="15%"><b>Hantar ke HTP</b></td>
        			#end
        		</tr>
        		
        		#if($saiz_listHakmilikPTDOnly!=0)
                    #foreach($listPTD in $listHakmilikPTDOnly)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
               <tr>
               	   <td class="$row" align="center">$!listPTD.bil</td>
                   <td class="$row">$!listPTD.nama_mukim</td>
                   <td class="$row">$!listPTD.jenis_hakmilik</td>
                   <td class="$row">$!listPTD.no_hakmilik</td>
                   <td class="$row">$!listPTD.tarikh_catatan</td>
                   <td class="$row">$!listPTD.tarikh_terima
                   #if($!listPTD.checkExpired != '0' && $!listPTD.checkExpired!="")
                   <span class="blink"><i><font color='red' style='font-size:10px'>Telah dihantar selama <b>$!listPTD.dayCountExpired</b> hari</font></i></span>
                   #end
                   </td>
                   #if($saiz_listHakmilikPTDOnly != 0)
                   <td class="$row" align="center">
                   		#if($!listPTD.flag_hantar_htp != "1")
                   		<input type="button" name="cmdHantarHTP" value="Hantar" onClick="javascript:hantarHTP('$!listPTD.id_hakmilik')">
                   		#else
                   		<b>Telah hantar pada $!listPTD.tarikh_hantar_htp</b>
                   		#end
                   </td>
               	   #end
               <tr>
                    #end
               #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
               #end
		  </table>
	
			#if($saiz_listHakmilikPTDOnly > 5)
                </div>
            #end
            
	</fieldset>	
	
	<fieldset>
		<table width="100%" border="0">
			<tr>
				<td width="1%">&nbsp;</td>
				<td width="10%" valign="top"><b>Catatan</b></td>
				<td width="1%" valign="top">:</td>
				<td width="58%">$!lblCatatanPTD</td>
				<td width="30%">&nbsp;</td>
			</tr>
		</table>
	</fieldset>
	
	<table width="100%" border="0">
		<tr align="center">
			<td>
				#if($saiz_listHakmilikPTDOnly!=0 || $saiz_listHakmilikPTGOnly!=0)
				<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
				#end
				<input type="button" name="cmdKembali" value="Kembali" onClick="javascript:kembali();">
			</td>
		</tr>
	</table>
	
<fieldset id="tableReport1" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
	  <tr>
		 <td><a href="#" onClick="javascript:cetakEndorsanKHQ('$!id_permohonan')"><font color="blue">Endorsan Borang K HQ</font></a></td>
	  </tr>
	  #if($saiz_listHakmilikPTGOnly!=0)
	  
	  	#if($showSUKSeluruh=="yes")
	  	<tr>
		 	<td><a href="#" onClick="javascript:cetakEndorsanKSUKSeluruh('$!id_permohonan')"><font color="blue">Endorsan Borang K SUK (Keseluruhan)</font></a></td>
	  	</tr>
	  	#end
	  	#if($showSUKSebahagian=="yes")
	  	<tr>
		 	<td><a href="#" onClick="javascript:cetakEndorsanKSUK('$!id_permohonan')"><font color="blue">Endorsan Borang K SUK (Sebahagian)</font></a></td>
	  	</tr>
	  	#end
	  
	  #end
	  #if($saiz_listHakmilikPTDOnly!=0)
	  
	  	#if($showPTDSeluruh=="yes")
	  	<tr>
		 	<td><a href="#" onClick="javascript:cetakEndorsanKPTDSeluruh('$!id_permohonan')"><font color="blue">Endorsan Borang K PTD (Keseluruhan)</font></a></td>
	  	</tr>  
	  	#end
	  	#if($showPTDSebahagian=="yes")
	  	<tr>
		 	<td><a href="#" onClick="javascript:cetakEndorsanKPTD('$!id_permohonan')"><font color="blue">Endorsan Borang K PTD (Sebahagian)</font></a></td>
	  	</tr>
	  	#end
	  	
	  #end
    </table>
</fieldset>	

	
<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_hakmilik" value="$!id_status">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>	


<script>
function hantarHTP(idhakmilik){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = idhakmilik;
	document.${formName}.command.value = "hantarHTP";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8EndosanDHDKBorangK";
	document.${formName}.submit();
}
function cetakEndorsanKSUKSeluruh(idpermohonan) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=EndorsanKSUKSeluruh&selectNoFail=yes&flagShowTarikhCetak=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakEndorsanKHQ(idpermohonan) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=EndorsanKHQ&selectNoFail=yes&flagShowTarikhCetak=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakEndorsanKSUK(idpermohonan) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=EndorsanKSUK&selectNoFail=yes&flagShowTarikhCetak=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakEndorsanKPTDSeluruh(idpermohonan) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=EndorsanKPTDSeluruh&selectNoFail=yes&flagShowTarikhCetak=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakEndorsanKPTD(idpermohonan) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=EndorsanKPTD&selectNoFail=yes&flagShowTarikhCetak=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function tambahPTG(idpermohonan){

	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "tambahPTG";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8EndosanDHDKBorangK";
	document.${formName}.submit();
}
function tambahPTD(idpermohonan){

	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "tambahPTD";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8EndosanDHDKBorangK";
	document.${formName}.submit();
}
function kembali(){
	document.${formName}.command.value = "xx";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8EndosanDHDKBorangK";
	document.${formName}.submit();
}
</script>



<script>
window.onload = submitForm;
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
}
</script>