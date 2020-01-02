
<fieldset id="top">
<legend><strong>Urusan Pampasan Kepada Agensi</strong></legend>
<center>

	#parse("app/ppt/frmPPTHeader.jsp")

<br/>

	<fieldset id="bottom">
	<legend><strong>Senarai Maklumat Hakmilik</strong></legend>
			
			<table width="100%" border="0">   
                	<tr>
                    	<td align="right">Carian No.LOT/No.PT :&nbsp;<input type="text" name="carianNoLot" value="$!carianNoLot" maxlength="20" size="20" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" ><a href="javascript:cariLOT('$!id_permohonan')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanLOT('$!id_permohonan')">&nbsp;<u>KOSONGKAN</u></a></td>
    				</tr>
    		</table>
    			
    		#if($saiz_listTanah > 5)
                <div style="width:100%;height:100;overflow:auto"> 
            #end	
    			
    		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td align="center"><b>No</b></td>
        			<td><b>No.Hakmilik</b></td>
                  	<td><b>FT</b></td>  
                  	<td><b>QT</b></td>               
                  	<td><b>Mukim/Pekan/Bandar</b></td>
            		<td><b>Status Bayaran</b></td>
            		<td><b>Status Bantahan</b></td> 
        		</tr>
        		
        		#if($saiz_listTanah!=0)
                    #foreach($listTanah in $listMaklumatTanah)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
               <tr>
                   <td class="$row" align="center">$!listTanah.bil</td>
                   <td class="$row"><a href="javascript:maklumatSuratAgensi('$!listTanah.id_hakmilik','$!listTanah.id_siasatan')"><font color="blue">$!listTanah.no_hakmilik</font></a></td>
                   <td class="$row">$!listTanah.no_lot</td>          
                   <td class="$row">$!listTanah.kod_lot$!listTanah.no_pt</td>     
                   <td class="$row">$!listTanah.nama_mukim</td>
                   <td class="$row">
                   
                    #if ($!listTanah.flag_bayar_bantahan == "1")
                      <div align="center"><img src="../img/validyes.png" alt="Sudah Terima Bayaran" width="18" height="18" border="0" /></a></div>
                    #else
                      <div align="center"><img src="../img/validno.png" alt="Belum Terima Bayaran" width="18" height="18" border="0" /></a></div>
                    #end                   
                   
                   </td>							
                   <td class="$row">$!listTanah.keterangan</td>
               <tr>
                    #end
               #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
               #end
		  </table>
	
			#if($saiz_listTanah > 5)
                </div>
            #end
	
	</fieldset>

	<table width="100%" border="0">
		<tr align="center">
			<td>
				<input type="button" name="cmdKembali" value="Kembali" onClick="javascript:kembali();">
			</td>
		</tr>
	</table>

</center>
</fieldset>

<input type="hidden" name="id_siasatan" value="$!id_siasatan">
<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_hakmilik">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<script>
window.onload = submitForm;
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
}
function kembali(){
	document.${formName}.command.value = "cleardata";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasanAgensi";
	document.${formName}.submit();
}
/*function maklumatSuratAgensi(idHakmilik){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.command.value = "maklumatSuratAgensi";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasanAgensi";
	document.${formName}.submit();
}*/
function maklumatSuratAgensi(idHakmilik,id_siasatan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.id_siasatan.value = id_siasatan;
	<!--document.${formName}.pagingPampasan.value = "3";-->
	document.${formName}.command.value = "maklumatSuratAgensi";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasanAgensi";
	document.${formName}.submit();
}
function cariLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "bottom";

	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewlistHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasanAgensi";
	document.${formName}.submit();
}
function kosongkanLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "bottom";

	document.${formName}.carianNoLot.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewlistHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanPampasanAgensi";
	document.${formName}.submit();
}
</script>