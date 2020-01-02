#parse("app/ppt/Sek8Paging.jsp")

<fieldset id="top">
<legend><strong>Maklumat Endorsan/Memorial DHDK</strong></legend>

	#parse("app/ppt/frmPPTHeader.jsp")

<br/>

	#foreach($data in $dataHeader)
		#set($txtNamaPejabatPTG=$data.nama_pejabat_ptg)
		#set($txtNamaPejabatPTD=$data.nama_pejabat_ptd)
		#set($lblCatatanPTG=$data.catatan_endosan_ptg)
		#set($lblCatatanPTD=$data.catatan_endosan_ptd)
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
        			<td><b>Bandar/Pekan/Mukim</b></td>
        			<td><b>Jenis Hakmilik</b></td>
        			<td><b>No.Hakmilik</b></td>
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
                    	<!-- <td align="right">Carian No.LOT/No.PT :&nbsp;<input type="text" name="carianNoLot" value="$!carianNoLot" maxlength="20" size="20"   ><a href="javascript:cariLOT('$!id_permohonan')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanLOT('$!id_permohonan')">&nbsp;<u>KOSONGKAN</u></a></td> -->
    				</tr>
    		</table>
			#end
    			
    		#if($saiz_listHakmilikPTDOnly > 5)
                <div style="width:100%;height:100;overflow:auto"> 
            #end	
    			
    		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td align="center" width="4%"><b>No</b></td>
        			<td><b>Bandar/Pekan/Mukim</b></td>
        			<td><b>Jenis Hakmilik</b></td>
        			<td><b>No.Hakmilik</b></td>
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
				<!-- #if($saiz_listWarta!=0)#end -->
				
					<!-- #if($id_status!="35")
						<input type="button" name="cmdHantar" value="Hantar" onClick="javascript:hantar('$!id_permohonan');">
					#end -->
					 
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
	  #if($saiz_listHakmilikPTGOnly!=0)	
      <tr>
		 <td><a href="#" onClick="javascript:cetakEndosanSUK('$!id_permohonan')"><font color="blue">Endorsan SUK</font></a></td>
	  </tr>
     
       <tr style="display:none">
		 <td><a href="#" onClick="javascript:cetakBATAL_ENDOS_PTG('$!id_permohonan')"><font color="blue">Surat Batal Endorsan PTG</font></a></td>
	  </tr>
    
	  #end
	  #if($saiz_listHakmilikPTDOnly!=0)
	  <tr>
		 <td><a href="#" onClick="javascript:cetakEndosanPTD('$!id_permohonan')"><font color="blue">Endorsan PTD</font></a></td>
         </tr>
         
         
          <tr style="display:none">
		 <td><a href="#" onClick="javascript:cetakBATAL_ENDOS_PTD('$!id_permohonan')"><font color="blue">Surat Batal Endorsan PTD</font></a></td>
	  
	  </tr>
	  #end

	  
	
    </table>
</fieldset>		

</fieldset>
	
<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>	


<script>


function cetakBATAL_ENDOS_PTG(idpermohonan) {

    var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=BatalEndosDPTG&selectNoFail=yes&flagShowTarikhCetak=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakBATAL_ENDOS_PTD(idpermohonan) {

    var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=BatalEndosDPTD&selectNoFail=yes&flagShowTarikhCetak=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

function cetakEndosanSUK(idpermohonan) {

    var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=EndosanDSUK&selectNoFail=yes&flagShowTarikhCetak=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakEndosanPTD(idpermohonan) {

    var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=EndosanDPTD&selectNoFail=yes&flagShowTarikhCetak=yes";
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
function hantar(idpermohonan){

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "hantar";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8EndosanMemorialDHDK";
	document.${formName}.submit();
}
function tambahPTG(idpermohonan){

	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "tambahPTG";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8EndosanMemorialDHDK";
	document.${formName}.submit();
}
function tambahPTD(idpermohonan){

	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "tambahPTD";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8EndosanMemorialDHDK";
	document.${formName}.submit();
}
function kembali(){
	document.${formName}.command.value = "xx";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8EndosanMemorialDHDK";
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