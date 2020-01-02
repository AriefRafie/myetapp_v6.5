#parse("app/ppt/Sek8Paging.jsp")

<fieldset id="top">
<legend><strong>Maklumat Warta - Seksyen 8</strong></legend>

	#parse("app/ppt/frmPPTHeader.jsp")

<br/>

	<fieldset id="bottom">
	<legend><strong>Jadual Tanah - Tanah Yang Terlibat Dengan Pengambilan</strong></legend>
	
    <table width="100%" border="0"> 
    <tr >
    <td align="left">
    <a href="javascript:popupCarianHakmilik('$id_permohonan','warta')"><font color="blue">>> SKRIN CAPAIAN HAKMILIK</font></a>
    </td>
    </tr>
    </table>
    
    	
	<!--
            #parse("app/ppt/frmCarianListHMSek8.jsp")
            #parse("app/ppt/frmSeksyen8ListHM.jsp")
      -->      
	</fieldset>
    
    <br />
    
    <fieldset id="bottom">
	<legend><strong>&nbsp;MUATNAIK DOKUMEN BERKAITAN</strong></legend>
                
               
    <table width="100%" border="0"> 
    <tr >
    <td align="left">
     <a href="javascript:popupCarianDokumen('$id_permohonan','warta')"><font color="blue">>> SKRIN SENARAI DOKUMEN</font></a>
    </td>
    </tr>
    </table>
    
    	
	<!--
            #parse("app/ppt/frmCarianListHMSek8.jsp")
            #parse("app/ppt/frmSeksyen8ListHM.jsp")
      -->      
	</fieldset>

<br/>
	
	<fieldset>
	<legend><strong>Senarai Warta</strong></legend>	
	
		<table width="100%" border="0">   
            <tr>
                <td><input type="button" name="cmdTambah" value="Tambah" onClick="javascript:tambahWarta('$!id_permohonan')"></td>
    		</tr>
    	</table>
    	
    	#if($saiz_listWarta > 5)
        <div style="width:100%;height:100;overflow:auto"> 
        #end	
    			
    	<table width="100%" border="0"> 
  
        	<tr class="table_header">
        		<td align="center" width="4%"><b>No</b></td>
        		<td width="20%"><b>Proses Warta</b></td>
        		<td width="46%"><b>No.Warta</b></td>
            	<td width="30%"><b>Tarikh Warta</b></td>
        	</tr>
        		
        #if($saiz_listWarta!=0)
            #foreach($list in $listWarta)
               #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		#set( $row = "row2" )
         		#else
               		#set( $row = "row1" )
         		#end
                    
            <tr>
                <td class="$row" align="center">$!list.bil</td>
               	<td class="$row"><a href="javascript:viewWarta('$!list.id_warta')"><font color="blue">$!list.proses_warta</font></a></td>
                <td class="$row">$!list.no_warta</td>
                <td class="$row">$!list.tarikh_warta</td>
            <tr>
            #end
        #else
        
            <tr>
                <td colspan="2">Tiada rekod</td>
            </tr>
        #end
        
		  </table>
	
		#if($saiz_listWarta > 5)
        </div>
        #end
            
	</fieldset>

	<table width="100%" border="0">
		<tr align="center">
			<td>
				#if($saiz_listWarta!=0)
					<!-- #if($id_status!="31")
					<input type="button" name="cmdHantar" value="Hantar" onClick="javascript:hantar('$!id_permohonan');">
					#end -->
					<input type="button" name="button" id="button" value="Cetak" onClick="javascript:setTable('tableReport1')" />
				#end
				<input type="button" name="cmdKembali" value="Kembali" onClick="javascript:kembali();">
			</td>
		</tr>
	</table>
	
</fieldset>

<fieldset id="tableReport1" style="display:none;">
<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
     	<tr>
    		<td><a href="#" onClick="javascript:cetakPerkara1JPBD('$!id_permohonan')"><font color="blue">JPBD (Maklumat Perancang)</font></a></td>
    	</tr>
    	<tr>
    		<td><a href="#" onClick="javascript:cetakPerkara2PTD('$!id_permohonan')"><font color="blue">PTD (Charting)</font></a></td>
    	</tr>
    	<tr>
    		<td><a href="#" onClick="javascript:cetakPerkara3KSU('$!id_permohonan')"><font color="blue">Penandaan Kawasan</font></a></td>
    	</tr>
    	<tr>
    		<td><a href="#" onClick="javascript:cetakPerkara4JPPH('$!id_permohonan')"><font color="blue">JPPH</font></a></td>
    	</tr>
    	<tr>
    		<td><a href="#" onClick="javascript:cetakPerkara4PEKELILING('$!id_permohonan')"><font color="blue">Lampiran A (JPPH)</font></a></td>
    		</tr>  
    </table>
</fieldset>	

<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_warta">
<input type="hidden" name="id_hakmilik">

<input type="hidden" name="command2">
<input type="hidden" name="command3">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>



<script>

function popupCarianHakmilik(id_permohonan,flag_skrin)
{
	var url = "../x/${securityToken}/ekptg.view.ppt.SkrinPopupCarianHakmilik?&id_permohonan="+id_permohonan+"&flag_skrin="+flag_skrin;
	var hWnd = window.open(url,'printuser','width=1200,height=800, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();		
	
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

function hantar(idpermohonan){

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "hantar";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Warta";
	document.${formName}.submit();
}
function viewHM(idHakmilik){

	document.${formName}.ScreenLocation.value = "changeHM";
	document.${formName}.id_hakmilik.value = idHakmilik;
	document.${formName}.command.value = "viewHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Warta";
	document.${formName}.submit();
}
function viewWarta(idwarta){

	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_warta.value = idwarta;
	document.${formName}.command.value = "viewWarta";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Warta";
	document.${formName}.submit();
}
function tambahWarta(idpermohonan){

	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "tambahWarta";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Warta";
	document.${formName}.submit();
}
function kembali(){
	document.${formName}.command.value = "cleardata";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Warta";
	document.${formName}.submit();
}
function cariLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "bottom";

	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewSenaraiWarta";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Warta";
	document.${formName}.submit();
}
function kosongkanLOT(idpermohonan) {
	
	document.${formName}.ScreenLocation.value = "bottom";

	document.${formName}.carianNoLot.value = "";
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "viewSenaraiWarta";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8Warta";
	document.${formName}.submit();
}
function cetakPerkara4PEKELILING(idpermohonan) {

    //var url = "../servlet/ekptg.report.ppt.Perkara4PEKELILING?idFail="+idfail;
    var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=Perkara4PEKELILING&selectNoFail=yes&flagShowTarikhCetak=yes";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakPerkara4JPPH(idpermohonan) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=Perkara4JPPH&selectNoFail=yes&flagShowTarikhCetak=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
  
}
function cetakPerkara1JPBD(idpermohonan) {

    //var url = "../servlet/ekptg.report.ppt.Perkara1JPBD?idFail="+idfail+"&namaPengarah="+namaPengarah;
    var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=Perkara1JPBD&selectNoFail=yes&flagShowTarikhCetak=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakPerkara2PTD(idpermohonan) {

    //var url = "../servlet/ekptg.report.ppt.Perkara2PTD?idFail="+idfail+"&namaPengarah="+namaPengarah;
    var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=Perkara2PTD&selectNoFail=yes&flagShowTarikhCetak=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakPerkara3KSU(idpermohonan) {

    //var url = "../servlet/ekptg.report.ppt.Perkara3KSU?idFail="+idfail+"&namaPengarah="+namaPengarah;
    var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?id_permohonan="+idpermohonan+"&report=Perkara3KSU&selectNoFail=yes&flagShowTarikhCetak=yes";
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
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
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
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
function validateTarikh(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric2(content);
		return;
	}
}
function RemoveNonNumeric2( strString )
{
      var strValidCharacters = "1234567890/.";
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
function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'characters left' counter
	else 
		countfield.value = maxlimit - field.value.length;
}
</script>