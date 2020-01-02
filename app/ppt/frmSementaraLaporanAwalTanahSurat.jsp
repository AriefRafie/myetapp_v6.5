#parse("app/ppt/SementaraPaging.jsp")

<fieldset>
<center>
<legend><strong>Maklumat Jabatan Teknikal</strong></legend>

	#parse("app/ppt/frmPPTHeader.jsp")

<br/>

	<fieldset id="bottom">
	<legend><strong>Senarai Jabatan Teknikal</strong></legend>
			
			<table width="100%" border="0">   
                	<tr>
                    	<td width="30%" align="left"><input type="button" name="cmdTambah" value="Tambah" onClick="javascript:tambahJabatan('$!id_permohonan');"></td>
    					<td width="70%" align="right">Carian Nama Pejabat :&nbsp;<input type="text" name="carianPejabat" value="$!carianPejabat" maxlength="70" size="20" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" ><a href="javascript:cariPejabat('$!id_permohonan')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanPejabat('$!id_permohonan')">&nbsp;<u>KOSONGKAN</u></a></td>
    				</tr>
    		</table>
    			
    		#if($saiz_jabatan > 5)
                <div style="width:100%;height:100;overflow:auto"> 
            #end	
    			
    		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td align="center" width="4%"><b>No</b></td>
        			<td><b>Nama Jabatan</b></td>
            		<td><b>No. Rujukan</b></td>
            		<td><b>Tarikh Surat</b></td> 
            		<td><b>Tempoh(Minggu)</b></td> 
        		</tr>
        		
        		#if($saiz_jabatan!=0)
                    #foreach($listJT in $listJabatanTeknikal)
                    #set( $i = $velocityCount )
         			#if ( ($i % 2) != 1 )
              		 	#set( $row = "row2" )
         			#else
               			#set( $row = "row1" )
         			#end
                    
               <tr>
                   <td class="$row" align="center">$!listJT.bil</td>
                   <td class="$row"><a href="javascript:viewJabatan($!listJT.id_ulasanteknikal)"><font color="blue">$!listJT.nama_jabatan</font></a></td>
                   <td class="$row">$!listJT.bil_surat</td>
                   <td class="$row">$!listJT.tarikh_surat</td>
                   <td class="$row">$!listJT.tempoh</td>
               <tr>
                    #end
               #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
               #end
		  </table>
	
			#if($saiz_jabatan > 5)
                </div>
            #end
	
	</fieldset>

	<table width="100%" border="0">
		<tr align="center">
			<td>
				<!-- #if($id_status!="22")
				<input type="button" name="cmdSeterusnya" value="Seterusnya" onClick="javascript:seterusnya('$!id_permohonan');">
				#end -->
				<input type="button" name="cmdKembali" value="Kembali" onClick="javascript:kembali();">
			</td>
		</tr>
	</table>

</center>
</fieldset>

<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_ulasanteknikal">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<script>
function seterusnya(idpermohonan){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "seterusnya";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraJabatanTeknikal";
	document.${formName}.submit();
}
function viewJabatan(id_ulasanteknikal){

	document.${formName}.ScreenLocation.value = "changeJabatan";
	
	document.${formName}.command.value = "viewJabatan";
	document.${formName}.id_ulasanteknikal.value = id_ulasanteknikal;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraJabatanTeknikal";
	document.${formName}.submit();
}
function tambahJabatan(idpermohonan){

	document.${formName}.ScreenLocation.value = "changeJabatan";
	
	document.${formName}.command.value = "tambahJabatan";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraJabatanTeknikal";
	document.${formName}.submit();
}
function kembali(){
	document.${formName}.command.value = "xx";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraJabatanTeknikal";
	document.${formName}.submit();
}
function cariPejabat(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "bottom";	
	document.${formName}.command.value = "semakSenaraiJabatan";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraJabatanTeknikal";
	document.${formName}.submit();	
}
function kosongkanPejabat(idpermohonan) {
	
	document.${formName}.carianPejabat.value = "";
	document.${formName}.ScreenLocation.value = "bottom";	
	document.${formName}.command.value = "semakSenaraiJabatan";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraJabatanTeknikal";
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
