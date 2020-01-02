#parse("app/ppt/SementaraPaging.jsp")

<fieldset>
<legend><strong>Hakmilik Penggunaan/Pendudukan Sementara Dan PB</strong></legend>
#parse("app/ppt/frmPPTHeader.jsp")
<br/>
<center>
	<fieldset id="bottom">
	<legend><strong>Senarai Maklumat Hakmilik</strong> <input type="button" name="cmdTambah" value="Tambah" onClick="javascript:tambahHM('$!flag_subjaket');"></legend>
		<table width="100%" border="0">   
                	<tr>
                    <td>
                       <a href="javascript:popupCarianHakmilik('$id_permohonan','skrin_hakmilik_sementara')"><font color="blue">>> SKRIN CAPAIAN HAKMILIK</font></a>
                    </td>
                    </tr>
                    </table>
               <!--     	
			<table width="100%" border="0">   
                	<tr>
                    	<td width="30%" align="left"><input type="button" name="cmdTambah" value="Tambah" onClick="javascript:tambahHM('$!flag_subjaket');"></td>
    					<td width="70%" align="right">Carian No.LOT/No.PT :&nbsp;<input type="text" name="carianNoLot" value="$!carianNoLot" maxlength="20" size="20" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" ><a href="javascript:cariLOT('$!id_permohonan')">&nbsp;<u>CARI</u></a>&nbsp;<a href="javascript:kosongkanLOT('$!id_permohonan')">&nbsp;<u>KOSONGKAN</u></a></td>
    				</tr>
    		</table>
    			
    		#if($saiz_listTanah > 5)
                <div style="width:100%;height:100;overflow:auto"> 
            #end	
    			
    		<table width="100%" border="0"> 
  
        		<tr class="table_header">
        			<td align="center" width="4%"><b>No</b></td>
                  	<td><b>No.Hakmilik</b></td>
                  	<td><b>No.LOT/No.PT</b></td>                  
                  	<td><b>Mukim/Pekan/Bandar</b></td>
            		<td><b>Keluasan</b></td> 
            		#if($flag_subjaket=="1")<td><b>No.Subjaket</b></td> #end
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
                	<td class="$row"><a href="javascript:viewHM('$!listTanah.id_hakmilik')"><font color="blue">$!listTanah.no_hakmilik</font></a></td>
                	<td class="$row">$!listTanah.no_lotpt</td>   
                	<td class="$row">$!listTanah.nama_mukim</td>
                	<td class="$row">$!listTanah.luas_ambil&nbsp;$!listTanah.unitByKategori</td>
                   	#if($flag_subjaket=="1")
                		#if($id_negeriProjek=="10")
                		<td class="$row">$!listTanah.no_rujukan_ptg Sj.$!listTanah.no_subjaket</td>
                		#else
                		<td class="$row">$!listTanah.no_fail Sj.$!listTanah.no_subjaket</td>
                		#end
                	#end
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
	-->
	</fieldset>

	<table width="100%" border="0">
		<tr align="center">
			<td>
				#if($saiz_listTanah > 1 && $flag_subjaket!="1")	
				<input type="button" name="cmdJana" value="Jana Subjaket" onClick="javascript:janaSubjaket($!id_permohonan);">
				#end
				
				#if(($id_status=="127" && ($flag_subjaket=="1" && $saiz_listTanah != 0 )) || ($id_status=="127" && $saiz_listTanah == 1))					
				<input type="button" name="cmdHantar" value="Hantar Untuk Diagihkan" onClick="javascript:hantar();">
				#end
				<input type="button" name="cmdKembali" value="Kembali" onClick="javascript:kembali();">
			</td>
		</tr>
	</table>

</center>
</fieldset>

<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_hakmilik">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

#foreach($listId in $listSeqSubjaket)
<input type="hidden" name="ListIdHM" value="$!listId.id_hakmilik">
#end

<script>
window.onload = submitForm;
function janaSubjaket(idPermohonan){

	if ( !window.confirm("Adakah Anda Pasti? Sebarang penambahan hakmilik selepas ini memerlukan subjaket dikesemua hakmilik dijana semula") ) return;
	document.${formName}.id_permohonan.value = idPermohonan;
	document.${formName}.command.value = "janaSubjaket";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraHakmilik";
	document.${formName}.submit();
}
function tambahPB(idHakmilik){

	document.${formName}.ScreenLocation.value = "changePB";
	
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.command.value = "tambahPB";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraHakmilik";
	document.${formName}.submit();
}
function popupCarianHakmilik(id_permohonan,flag_skrin)
{
	
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupCarianHakmilik?&id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}
function hantar(idHakmilik){

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "hantar";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraHakmilik";
	document.${formName}.submit();
}
function viewHM(idHakmilik){

	document.${formName}.ScreenLocation.value = "changeHM";
	
	document.${formName}.command.value = "viewHM";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraHakmilik";
	document.${formName}.submit();
}
function tambahHM(flagSubjaket){

	if(flagSubjaket=="1"){
		alert("No Subjaket perlu dijana semula sekiranya ada penambahan hakmilik");
	}
	
	document.${formName}.command.value = "tambahHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraHakmilik";
	document.${formName}.submit();
}
function kembali(){
	document.${formName}.command.value = "cleardata";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraHakmilik";
	document.${formName}.submit();
}
function cariLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "bottom";

	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "semakHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraHakmilik";
	document.${formName}.submit();
}
function kosongkanLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "bottom";

	document.${formName}.carianNoLot.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "semakHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSementaraHakmilik";
	document.${formName}.submit();
}
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
}
</script>