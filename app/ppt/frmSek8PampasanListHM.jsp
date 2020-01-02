#parse("app/ppt/Sek8Paging.jsp")

<fieldset id="top">
<legend><strong>Pampasan</strong></legend>
<center>

	#parse("app/ppt/frmPPTHeader.jsp")

<br/>

	<fieldset id="bottom">
	<legend><strong>Senarai Maklumat Hakmilik</strong></legend>
    
    
    <table width="100%" border="0"> 
    <tr >
    <td align="left">
    <a href="javascript:popupCarianHakmilik('$id_permohonan','hakmilik_pampasan')"><font color="blue">>> SKRIN CAPAIAN HAKMILIK</font></a>
    </td>
    </tr>
    </table>
		<!--	
		    #parse("app/ppt/frmCarianListHMSek8.jsp")
    	   #parse("app/ppt/frmSeksyen8ListHM.jsp")
	-->
	</fieldset>

	<table width="100%" border="0">
		<tr align="center">
			<td>
<!-- 				<input type="button" name="cmdEmail" value="Hantar Email kepada KJP" onClick="javascript:sendEmail('$id_permohonan');"> -->
				<input type="button" name="cmdKembali" value="Kembali" onClick="javascript:kembali();">
				#if($listBorangG.size()!=0)
				<input type="button" name="cmdBorangG" value="Papar Senarai Borang G" onClick="javascript:setTable('tableReport1')">
				#end
			</td>
		</tr>
	</table>

</center>
</fieldset>

<br/>

<fieldset id="tableReport1" style="display:none;">
<legend><strong>SENARAI BORANG G MENGIKUT TARIKH</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
	
					<tr class="table_header">
	
                  		<td align="center"><b>No</b></td>
                  		<td><b>Tarikh Borang G</b></td>
                  		<td><b>Jumlah Lot</b></td>  
                  		<td><b>&nbsp;</b></td>        
           		 	</tr>
                    
   					#if($listBorangG.size()!=0)
                    	#foreach($list in $listBorangG)
                    	#set( $i = $velocityCount )
         				#if ( ($i % 2) != 1 )
              		 		#set( $row = "row2" )
         				#else
               				#set( $row = "row1" )
         				#end
                    	
                    	
                    <tr>
                		<td class="$row" align="center">$!list.bil</td>
                		<td class="$row">$!list.TARIKH_BORANGG</td>
                		<td class="$row">$!list.BIL_LOT</td>     
                		<td class="$row"><input type="button" name="cmdCetakBorangG" value="Cetak" onClick="javascript:cetakBorangG('$!id_permohonan','','$!list.TARIKH_BORANGG');"></td>
 
            		</tr>
                    	#end
                    	
                    #else
                    <tr>
                    	<td colspan="2">Tiada rekod</td>
                    </tr>
                    #end
	
	</table>
</fieldset>	

<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_hakmilik">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<script>
/* function sendEmail(id_permohonan) {
// 	if ( !window.confirm("Adakah Anda Pasti?") ) return;
// 	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "sendEmail";
	document.${formName}.submit();
} */

function sendEmail(idpermohonan,idhakmilik) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&id_hakmilik="+idhakmilik+"&report=SuratMintaBayaran&selectNoFail=yes&flagShowTarikhCetak=yes&todo=sendEmail";
    var hWnd = window.open(url,'Cetak','width=400,height=100, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
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


function cetakBorangG(idpermohonan,idhakmilik,tarikhBorangG) {
	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?bydate="+tarikhBorangG+"&id_permohonan="+idpermohonan+"&id_hakmilik=&report=BorangG&selectNoFail=yes";
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
window.onload = submitForm;
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
}
function kembali(){
	document.${formName}.command.value = "cleardata";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function viewHM(idHakmilik){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.command.value = "viewJumlahAward";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function cariLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "bottom";

	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewlistHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}
function kosongkanLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "bottom";

	document.${formName}.carianNoLot.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewlistHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8PenyediaanPampasan";
	document.${formName}.submit();
}

</script>