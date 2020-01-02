#parse("app/ppt/Sek8Paging.jsp")

<fieldset id="top">
<center>

	#parse("app/ppt/frmPPTHeader.jsp")

<br/>
	
	<!-- <fieldset id="bottom">
	<legend><strong>Senarai Maklumat Hakmilik</strong></legend>
			
            #parse("app/ppt/frmCarianListHMSek8.jsp")
    			
            #parse("app/ppt/frmSeksyen8ListHM.jsp")
	
	</fieldset>
	
	<br/> -->
	
	
	<fieldset>
	<legend><strong>Senarai Rekod Maklumat Borang E</strong> <input type="button" name="cmdMainscreen" value="Kemasukan Maklumat Borang E" onClick="javascript:daftarMaklumatBorangEInBulk();"></legend>
    
    
    <table width="100%" border="0"> 
    <tr >
    <td align="left">
    <a href="javascript:popupCarianHakmilik('$id_permohonan','senarai_borangE')"><font color="blue">>> SKRIN CAPAIAN MAKLUMAT BORANG E</font></a>
    </td>
    </tr>
    </table>
    
    <!--
	<table width="100%" border="0">
		<tr align="left">
			<td colspan="3">
				
			</td>
            
            <td colspan = "2"  align="right">
				Carian No.LOT<b>/</b>No.PT<b>/</b>Nama Pihak Berkepentingan :
			</td>
            
            <td colspan="4" align="left">
				<input type="text" style="width:60%" name="carianLotHakmilik" id="carianLotHakmilik" value="$carianLotHakmilik" >
                <a href="javascript:cariLOT('$!id_permohonan')">&nbsp;<u>CARI</u></a>
                &nbsp;
                <a href="javascript:kosongkanLOT('$!id_permohonan')">&nbsp;<u>KOSONGKAN</u></a>
			</td>
		</tr>
		
		<tr class="table_header">
       		<td align="center"><b>No</b></td>
            <td><b>Senarai Rekod/Tarikh Daftar</b></td>
           	<td><b>Tarikh Borang E</b></td>              
            <td><b>Tarikh Siasatan</b></td>
            <td align="center"><b>Senarai Lot</b></td>
            <td><b>&nbsp;</b></td>
        </tr>
                    
   		#if($saiz_listBorangEInBulk!=0)
      		#foreach($listN in $listBorangEInBulk)
            #set( $i = $velocityCount )
         	#if ( ($i % 2) != 1 )
           		#set( $row = "row2" )
	        #else
	            #set( $row = "row1" )
	       	#end
         	
       	<tr>
           	<td class="$row" align="center">$!listN.bil</td>
            <td class="$row">
            <a href="javascript:viewBorangEInBulk('$!listN.ID_BORANGE')"><font color="blue">Rekod $!listN.bil - $!listN.TARIKH_MASUK</font></a></td>
			<td class="$row">$!listN.TARIKH_BORANGE</td>  
			<td class="$row">$!listN.TARIKH_SIASATAN</td> 
			<td align="center" class="$row"><a href="javascript:viewPopupLot('$!listN.ID_BORANGE','$!id_permohonan')"><font color="blue"><b>$!listN.TOTALHM</b></font></a></td>   
        	<td align="center" class="$row"><input type="button" name="cmdBorangF" value="Kemasukan Maklumat Borang F" onClick="javascript:maklumatBorangF('$!listN.ID_BORANGE');">
            <input type="button" name="cmdHaspusBorangE" value="Hapus Borang E" onClick="javascript:hapusBorangE('$!listN.ID_BORANGE');">
            </td>
        </tr>
        	#end
        #else
        <tr>
           	<td colspan="2">Tiada rekod</td>
       	</tr>
        #end 
		
	</table>	
    -->
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

<input type="hidden" name="id_borange" value="">
<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="isEdit">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<script>

function popupCarianHakmilik(id_permohonan,flag_skrin)
{
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupCarianHakmilik_BorangE_F?&id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
}

function cariLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "top";

	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewListHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function kosongkanLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.carianLotHakmilik.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewListHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}


function maklumatBorangF(id_borange){	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_borange.value = id_borange;
	document.${formName}.command.value = "maklumatBorangF";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function hapusBorangE(id_borange){	
	{if ( !window.confirm("Adakah Anda Pasti?") ) return;}
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_borange.value = id_borange;
	document.${formName}.command.value = "deleteBorangEInBulk";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function viewBorangEInBulk(id_borange){	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_borange.value = id_borange;
	document.${formName}.command.value = "viewMaklumatBorangEInBulk";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function viewPopupLot(id_borange,id_permohonan){
	var url = "../x/${securityToken}/ekptg.view.ppt.FrmMyInfoPopupPegawaiBertugas?id_borange="+id_borange+"&type=borange&id_permohonan="+id_permohonan;
	var hWnd = window.open(url,'Senarai Lot','width=800,height=400, resizable=yes,scrollbars=yes');	
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function daftarMaklumatBorangEInBulk(){	
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.isEdit.value = "yes";
	document.${formName}.command.value = "daftarMaklumatBorangEInBulk";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function kembali(){
	document.${formName}.command.value = "cleardata";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}
function viewHM(idHakmilik) {
	document.${formName}.ScreenLocation.value = "TabbedPanels1";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.command.value = "viewListBorangF";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8BorangF";
	document.${formName}.submit();
}

window.onload = submitForm;
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
}
</script>