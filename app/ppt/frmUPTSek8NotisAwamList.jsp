#parse("app/ppt/Sek8Paging.jsp")

<fieldset id="top">
<center>

	#parse("app/ppt/frmPPTHeader.jsp")

<br/>

#if($!id_permohonan != "") 
    <fieldset id="bottom">
	<legend><strong>&nbsp;MUATNAIK BORANG SABPN  <input type="button" name="button" id="button" value="Cetak SABPN" onClick="javascript:cetakSABPN('$id_permohonan','sabpn_notis_awam_sek8')" /></strong></legend>
                
               
    <table width="100%" border="0"> 
    <tr >
    <td align="left">
     <a href="javascript:popupCarianDokumen('$id_permohonan','notis_awam_sek8')"><font color="blue">>> SKRIN SENARAI DOKUMEN</font></a>
    </td>
    </tr>
    </table>
    
   
	</fieldset>	
    #end
<br/>

	
	<fieldset>
	<legend><strong>Senarai Rekod Maklumat Notis</strong></legend>
	<table width="100%" border="0">
		<tr align="left">
			<td colspan="5">
				<input type="button" name="cmdMainscreen" value="Kemasukan Maklumat Notis" onClick="javascript:daftarMaklumatNotis();">
				<input type="button" name="cmdCetak" value="Cetak Notis Umum" onClick="javascript:cetakNotis('$!id_permohonan')">
			</td>
			
		</tr>
		
		<tr class="table_header">
       		<td align="center"><b>No</b></td>
            <td><b>Keterangan Tempat</b></td>
           	<td><b>Tempat Tampal</b></td>              
            <td><b>Tarikh Tampal</b></td>
            <td align="center"><b>Senarai Lot</b></td>
        </tr>
                    
   		#if($saiz_listNotisInBulk!=0)
      		#foreach($listN in $listNotisInBulk)
            #set( $i = $velocityCount )
         	#if ( ($i % 2) != 1 )
           		#set( $row = "row2" )
	        #else
	            #set( $row = "row1" )
	       	#end
         	
       	<tr>
           	<td class="$row" align="center">$!listN.bil</td>
            <td class="$row">
            <a href="javascript:viewNotisInBulk('$!listN.ID_NOTISAWAM')"><font color="blue">$!listN.TEMPAT</font></a></td>
			<td class="$row">$!listN.JENIS_NAMA_TEMPAT_TAMPAL</td>  
			<td class="$row">$!listN.TARIKH_TAMPAL</td> 
			<td align="center" class="$row"><a href="javascript:viewPopupLot('$!listN.ID_NOTISAWAM','$!id_permohonan')"><font color="blue"><b>$!listN.TOTALHM</b></font></a></td>   
        </tr>
        	#end
        #else
        <tr>
           	<td colspan="2">Tiada rekod</td>
       	</tr>
        #end 
		
	</table>	
	</fieldset>
	
	<!-- <br/>
	
	<fieldset>
	<legend><strong>Kemasukan Maklumat Notis Mengikut Hakmilik</strong></legend>
			
            #parse("app/ppt/frmCarianListHMSek8.jsp")
    			
            #parse("app/ppt/frmSeksyen8ListHM.jsp")
	
	</fieldset> -->
	

	<table width="100%" border="0">
		<tr align="center">
			<td>
				<input type="button" name="cmdKembali" value="Kembali" onClick="javascript:kembali();">
			</td>
		</tr>
	</table>

</center>
</fieldset>

<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_notisawam">
<input type="hidden" name="id_hakmilik">

<input type="hidden" name="flag1">
<input type="hidden" name="flag2">
<input type="hidden" name="flag3">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<script>
function cetakSABPN(id_permohonan,jenis_report) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+id_permohonan+"&report="+jenis_report+"&selectNoFail=yes";	
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function popupCarianDokumen(id_permohonan,flag_skrin)
{
	
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupUploadDokumen?&id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}
function cetakNotis(idpermohonan) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=cetakNotis&selectNoFail=yes&flagShowTarikhCetak=yes";	
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function viewPopupLot(id_notisawam,id_permohonan){
	var url = "../x/${securityToken}/ekptg.view.ppt.FrmMyInfoPopupPegawaiBertugas?id_notisawam="+id_notisawam+"&type=notis&id_permohonan="+id_permohonan;
	var hWnd = window.open(url,'Senarai Lot','width=800,height=400, resizable=yes,scrollbars=yes');	
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function viewNotisInBulk(id_notisawam){	
	document.${formName}.id_notisawam.value = id_notisawam;
	document.${formName}.command.value = "viewNotisInBulk";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8NotisAwam";
	document.${formName}.submit();		
}
function daftarMaklumatNotis(){	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "tambahNotisInBulk";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8NotisAwam";
	document.${formName}.submit();
}
function viewNotis(idNotisAwam){	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_notisawam.value = idNotisAwam;
	document.${formName}.command.value = "viewNotis";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8NotisAwam";
	document.${formName}.submit();
}
function viewHM(idHakmilik){	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.command.value = "tambahNotis";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8NotisAwam";
	document.${formName}.submit();
}
function kembali(){
	document.${formName}.command.value = "cleardata";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8NotisAwam";
	document.${formName}.submit();
}
function viewLampiran(id_dokumen) {
    var url = "../servlet/ekptg.view.ppt.DisplayBlob?id="+id_dokumen;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
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
function cariLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "bottom";

	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewListNotis";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8NotisAwam";
	document.${formName}.submit();
}
function kosongkanLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "bottom";

	document.${formName}.carianNoLot.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewListNotis";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8NotisAwam";
	document.${formName}.submit();
}
</script>