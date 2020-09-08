<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0000FF
}
.style3 {
	color: #FF0000; font-style: italic; 
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input type="hidden" name="idFail" id="idFail" value="$idFail"/>
  <input type="hidden" name="idPermohonan" id="idPermohonan" value="$idPermohonan"/>
  <input type="hidden" name="idStatus" id="idStatus" value="$idStatus"/>
  <input type="hidden" name="idMesyuarat" id="idMesyuarat" value="$idMesyuarat"/>
  <input type="hidden" name="idKehadiran" id="idKehadiran" value="$idKehadiran"/>
  <input type="hidden" name="actionMesyuarat" id="actionMesyuarat" value="$actionMesyuarat"/>
  <input type="hidden" name="mode" id="mode" value="$mode"/>
  <input type="hidden" name="flagPopup" id="flagPopup" value="$flagPopup"/>
  <input type="hidden" name="modePopup" id="modePopup" value="$modePopup"/>
  <input type="hidden" name="selectedTabUpper" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input type="hidden" name="hitButton" id="hitButton"/>
  <input type="hidden" name="step" id="step" value="$step">
  
 <input type="hidden" name="idMesyuaratPermohonan" id="idMesyuaratPermohonan" value="$idMesyuaratPermohonan">
 <input type="hidden" name="flagResult" id="flagResult" value="$flagResult"/>
 <input type="hidden" name="catatan" id="catatan" value="$catatan"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><div id="TabbedPanels" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTab(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT MESYUARAT</li>
          <li onClick="doChangeTab(1);" class="TabbedPanelsTab" tabindex="0">KEHADIRAN</li>
          <li onClick="doChangeTab(2);" class="TabbedPanelsTab" tabindex="0">SENARAI PERMOHONAN</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <!-- START MAKLUMAT MESYUARAT -->
          <div class="TabbedPanelsContent">
          	<table width="100%" border="0" cellspacing="2" cellpadding="2">
  			#foreach ($beanMaklumatMesyuarat in $BeanMaklumatMesyuarat)
  				<tr>
			    	<td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
			    	<td width="28%">Tarikh Mesyuarat</td>
			    	<td width="1%">:</td>
			    	<td width="70%">
			    		<input type="text" name="txtTarikhMesyuarat" id="txtTarikhMesyuarat" size="9" onBlur="check_date(this)" value="$beanMaklumatMesyuarat.tarikhMesyuarat" $readonly class="$inputTextClass">
			      		#if ($mode != 'view') <a href="javascript:displayDatePicker('txtTarikhMesyuarat',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end 
			      	</td>
			  	</tr>
			  	<tr>
			    	<td>&nbsp;</td>
			    	<td>Masa Mesyuarat</td>
			    	<td>:</td>
			    	<td>$selectJamDari$selectMinitDari &nbsp;Hingga&nbsp; $selectJamHingga$selectMinitHingga </td>
			  	</tr>
			  	#if ($mode != 'view')
			  	<tr>
			    	<td></td>
			    	<td>&nbsp;</td>
			    	<td>&nbsp;</td>
			    	<td><span class="style3">Sila pastikan masa adalah dalam format 24 jam (HHMM).</span></td>
			  	</tr>
			  	#end			  	
			  	<tr>
			    	<td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
			    	<td width="28%">Bil. Mesyuarat</td>
			    	<td width="1%">:</td>
			    	<td width="70%"><input name="txtBilMesyuarat" type="text" class="$inputTextClass" id="txtBilMesyuarat" value="$beanMaklumatMesyuarat.bilMesyuarat" 
			    				size="9" maxlength="12" $readonly onblur="this.value=this.value.toUpperCase();"/></td>
			  	</tr>
				<tr>
				    <td width="1%">&nbsp;</td>
				    <td width="28%">Tempat Mesyuarat</td>
				    <td width="1%">:</td>
				    <td width="70%">$selectLokasi</td>
				</tr>
				<tr>
				    <td valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
				    <td valign="top">Tajuk Mesyuarat</td>
				    <td valign="top">:</td>
				    <td valign="top"><textarea name="txtTajukMesyuarat" id="txtTajukMesyuarat" rows="5" cols="50" $readonly class="$inputTextClass" 
				    			onBlur="this.value=this.value.toUpperCase();">$beanMaklumatMesyuarat.tajukMesyuarat</textarea></td>
				</tr>
				<tr>
				    <td valign="top">&nbsp;</td>
				    <td valign="top">Catatan</td>
				    <td valign="top">:</td>
				    <td><textarea name="txtCatatanMesyuarat" id="txtCatatanMesyuarat" rows="5" cols="50" $readonly class="$inputTextClass" 
				    			onBlur="this.value=this.value.toUpperCase();">$beanMaklumatMesyuarat.catatanMesyuarat</textarea></td>
				</tr>
				#if ($mode != 'view')
			  	<tr>
			    	<td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
			  	</tr>
			  	#end
				<tr>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td>&nbsp;</td>
				    <td> 
				      #if ($mode == 'view')
				      <input name="cmdkmskiniMesyuarat" type="button" value="Kemaskini" onClick="kemaskiniMesyuarat()" >
				      <input name="cmdBatalMesyuarat" type="button" value="Kembali" onClick="batalMesyuarat()" />
				      #end  
				      #if ($mode == 'update')
				      <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onclick="simpanKemaskiniMesyuarat()"/>
				      <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onclick="batalKemaskiniMesyuarat()"/>
				      #end </td>
				</tr>
  			#end
  			</table>
          </div>
          <!-- END MAKLUMAT MESYUARAT -->
          <!-- START KEHADIRAN -->
          <div class="TabbedPanelsContent">
          	<table width="100%" border="0" cellspacing="2" cellpadding="2">
          		#if ($flagPopup == 'openKehadiran')
			  	#if ($modePopup == 'new')
			  	<tr>
			    	<td> #parse("app/php2/frmCRBKehadiranMesyuaratDaftar.jsp") </td>
			  	</tr>
			  	#end
			  	#if ($modePopup != 'new')
			  	<tr>
			    	<td> #parse("app/php2/frmCRBKehadiranMesyuaratPapar.jsp") </td>
			  	</tr>
			  	#end
			  	<tr>
			    	<td>&nbsp;</td>
			  	</tr>
			  	#end
			  	<tr>
			  		<td><fieldset>
      					<legend><strong>SENARAI KEHADIRAN</strong></legend>
      					<table align="center" width="100%">
      						<tr>
					          <td colspan="5" scope="row"><input name="cmdDaftarKehadiran" type="button" value="Tambah" onclick="javascript:daftarKehadiran()"/></td>
					        </tr>
					        <tr class="table_header">
					          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
					          <td width="30%" align="left" ><strong>Nama Pegawai</strong></td>
					          <td width="30%" align="left"><strong>KJP/Agensi/Syarikat</strong></td>
					          <td width="20%"><strong>Jawatan</strong></td>
					          <td width="10%"><strong>No Telefon</strong></td>
					          <td width="10%"><strong>Email</strong></td>
					          <td width="5%" align="center">&nbsp;</td>
					        </tr>
					        #set ($senaraiKehadiran = "")
					        #if ($SenaraiKehadiran.size() > 0)
					        #foreach ($senaraiKehadiran in $SenaraiKehadiran)
					        #if ($senaraiKehadiran.bil == '')
					        #set( $row = "row1" )
					        #elseif (($senaraiKehadiran.bil % 2) != 0)
					        #set( $row = "row1" )
					        #else 
					        #set( $row = "row2" )
					        #end
					        <tr>
					          <td class="$row" align="center">$senaraiKehadiran.bil</td>
					          <td class="$row"><a href="javascript:paparKehadiran('$senaraiKehadiran.idKehadiran')" class="style2">$senaraiKehadiran.namaPegawai</a>&nbsp;&nbsp; #if ($senaraiKehadiran.flagPengerusi == 'Y')(PENGERUSI)#end</td>
					          <td class="$row">$senaraiKehadiran.namaAgensi</td>
					          <td class="$row">$senaraiKehadiran.namaJawatan</td>
					          <td class="$row">$senaraiKehadiran.noTelefon</td>
					          <td class="$row">$senaraiKehadiran.email</td>
					          <td class="$row" align="center"><input name="cmdHapus" type="button" value="Hapus" onclick="hapusKehadiran('$senaraiKehadiran.idKehadiran')"/></td>
					        </tr>
					        #end
					        #else
					        <tr>
					          <td class="row1" align="center">&nbsp;</td>
					          <td class="row1">Tiada Rekod</td>
					          <td class="row1">&nbsp;</td>
					          <td class="row1">&nbsp;</td>
					          <td class="row1">&nbsp;</td>
					          <td class="row1">&nbsp;</td>
					          <td class="row1">&nbsp;</td>
					        </tr>
					        #end
      					</table>
      				</fieldset></td>
			  	</tr>
          	</table> 
          </div>
          <!-- END KEHADIRAN -->
          <!-- START SENARAI PERMOHONAN -->
          <div class="TabbedPanelsContent">
         
          	<table width="100%" border="0" cellspacing="2" cellpadding="2">
          		<tr>
			  		<td><fieldset>
      					<legend><strong>SENARAI PERMOHONAN BARU</strong></legend>
      					#parse("app/utils/record_paging.jsp")
      					<table align="center" width="100%">
      						<tr>
					          <td colspan="5" scope="row">
					          <input name="cmdPilihPermohonanBaru" type="button" value="Pilih Senarai Permohonan Baru" onclick="javascript:pilihSenaraiPermohonanBaru()"/></td>
					        </tr>
					        <tr class="table_header" align="center">
					          <td scope="row" width="5%"><strong>Bil</strong></td>
					          <td width="20%"><strong>No. Fail</strong></td>
					          <td width="15%"><strong>Jenis Permohonan</strong></td>
					          <td width="20%"><strong>Nama Pemohon</strong></td>
					          <td width="10%"><strong>Kertas Pertimbangan</strong></td>
					          <td width="10%"><strong>Keputusan</strong></td>
					          <td width="15%"><strong>Catatan</strong></td>
					          <td width="10%"><strong>Hapus</strong></td>
					        </tr>
					        #set ($senaraiFailMohonBaru = "")
					        #if ($SenaraiFailMohonBaru.size() > 0)
					        #foreach ($senaraiFailMohonBaru in $SenaraiFailMohonBaru)
					        #if ($senaraiFailMohonBaru.bil == '')
					        #set( $row = "row1" )
					        #elseif (($senaraiFailMohonBaru.bil % 2) != 0)
					        #set( $row = "row1" )
					        #else 
					        #set( $row = "row2" )
					        #end
					        <tr>
					          <td class="$row" align="center">$senaraiFailMohonBaru.bil</td>
					          <td class="$row">$senaraiFailMohonBaru.noFailPermohonan</td>
					          <td class="$row">$senaraiFailMohonBaru.jenisPermohonan</td>
					          <td class="$row">$senaraiFailMohonBaru.namaPemohon</td>
					          <td class="$row" align="center"><a href="#" class="style2" onClick="javascript:doHapus('$senaraiFailMohonBaru.idPermohonan')">
                      		  <img border="0" src="../img/print.gif"/></a></td>
					          <td class="$row">
					            #if ($!senaraiFailMohonBaru.jenisPermohonan == "PERMOHONAN BARU")
								    <select id="idKeputusan$senaraiFailMohonBaru.id" name="idKeputusan$senaraiFailMohonBaru.id" style="width:100%" onChange="doSaveKeputusanBaru('idKeputusan$senaraiFailMohonBaru.id',$senaraiFailMohonBaru.id)">
									    <option value="">SILA PILIH</option>
									    <option #if ( "L" == $senaraiFailMohonBaru.flagKeputusan ) selected #end value="L">LULUS</option>
									    ##<option #if ( "LB" == $senaraiFailMohonBaru.flagKeputusan ) selected #end value="LB">LULUS BERSYARAT</option>
									    ##<option #if ( "TG" == $senaraiFailMohonBaru.flagKeputusan ) selected #end value="TG">TANGGUH</option>
									    <option #if ( "T" == $senaraiFailMohonBaru.flagKeputusan ) selected #end value="T">TOLAK</option>
									</select>
								#else
									<b>$senaraiFailMohonBaru.flagKeputusan()</b>
								#end
					          </td>
					          <td class="$row">
					            ##if ($!r.status == "B")
								    <textarea id="catatanKeputusan$senaraiFailMohonBaru.id" name="catatanKeputusan$senaraiFailMohonBaru.id" onBlur="doSaveCatatanKeputusanBaru('catatanKeputusan$senaraiFailMohonBaru.id',$senaraiFailMohonBaru.id)" >$senaraiFailMohonBaru.catatanKeputusan</textarea>
								##else
									<b>$!senaraiFailMohonBaru.catatan</b>
								##end
					          </td>
                      		  <td class="$row" align="center"><a href="#" class="style2" onClick="javascript:doHapus('$senaraiFailMohonBaru.idPermohonan')">
                      		  					<img border="0" src="../img/hapus.gif"/></a></td>
					        </tr>
					        #end
					        #else
					        <tr>
					          <td class="row1" align="center">&nbsp;</td>
					          <td class="row1">Tiada Rekod</td>
					          <td class="row1">&nbsp;</td>
					          <td class="row1">&nbsp;</td>
					          <td class="row1">&nbsp;</td>
					          <td class="row1">&nbsp;</td>
					          <td class="row1">&nbsp;</td>
					        </tr>
					        #end
					    </table>
					</fieldset></td>
				</tr>
          	</table>
          </div>
          <!-- END SENARAI PERMOHONAN -->
        </div>
      </div></td>
  </tr>
</table>

<table width="100%">
	<tr>
		<td align="right">
			##if ($!r.status == "B")
			<input id="btnSelesai" type="button" value="Selesai Mesyuarat" onClick="javascript:doSelesaiMesyuarat();">
			##end
	    	<input id="btnBack" type="button" value="Kembali" onClick="$('div_main_entry').style.display='none';$('div_main').style.display='block';doDivAjaxCall$formname('div_list','list_page','page_num=$!current_page_num')">
	    </td>
	</tr>
</table>
<script type="text/javascript">
	var TabbedPanels = new Spry.Widget.TabbedPanels("TabbedPanels",{defaultTab:$selectedTabUpper});
</script>
<script>
function doChangeTab(tabId) {
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmPYWSenaraiMesyuaratView";
	document.${formName}.method="POST";
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.mode.value = "view";
	document.${formName}.actionMesyuarat.value = "papar";
	document.${formName}.submit();
}
function batalMesyuarat(){
	document.${formName}.actionMesyuarat.value = "";
	document.${formName}.mode.value = "";
	document.${formName}.submit();
}
function kemaskiniMesyuarat(){
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function batalKemaskiniMesyuarat(){
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function simpanKemaskiniMesyuarat(){
	if(document.${formName}.txtTarikhMesyuarat.value == ""){
		alert('Sila masukkan Tarikh Mesyuarat.');
  		document.${formName}.txtTarikhMesyuarat.focus(); 
		return; 
	}
	if(document.${formName}.txtBilMesyuarat.value == ""){
		alert('Sila masukkan Bilangan Mesyuarat.');
  		document.${formName}.txtBilMesyuarat.focus(); 
		return; 
	}
	if(document.${formName}.txtTajukMesyuarat.value == ""){
		alert('Sila masukkan Tajuk Mesyuarat.');
  		document.${formName}.txtTajukMesyuarat.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniMesyuarat";
	doAjaxCall${formName}("");
}
function trimUlasan(s) {
	var maxlength = 1500; // Change number to your max length.
	
	if (s.value.length > maxlength) {
		s.value = s.value.substring(0,maxlength);
		alert('Melebihi aksara yang dibenarkan.'); 
	}
}
function trimTindakan(s) {
	var maxlength = 4000; // Change number to your max length.
	
	if (s.value.length > maxlength) {
		s.value = s.value.substring(0,maxlength);
		alert('Melebihi aksara yang dibenarkan.'); 
	}
}
function kemaskiniKeputusan(){
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function batalKemaskiniKeputusan(){
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function simpanKemaskiniKeputusan(){	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniKeputusan";
	doAjaxCall${formName}("");
}
function daftarKehadiran(){
	document.${formName}.flagPopup.value = "openKehadiran";
	document.${formName}.modePopup.value = "new";
	doAjaxCall${formName}("");
}
function paparKehadiran(idKehadiran){
	document.${formName}.flagPopup.value = "openKehadiran";
	document.${formName}.modePopup.value = "view";
	document.${formName}.idKehadiran.value = idKehadiran;
	doAjaxCall${formName}("");
}
function hapusKehadiran(idKehadiran){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.idKehadiran.value = idKehadiran;
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "hapusKehadiran";
	doAjaxCall${formName}("");
}
function doUpdateCheck(bil){
	var b = parseInt(bil)-1;
	if (document.${formName}.flagPengerusi.length > 1){
		for (i = 0; i < document.${formName}.flagPengerusi.length; i++){
			document.${formName}.flagPengerusi[i].checked = false;
		}
		document.${formName}.flagPengerusi[b].checked = true;
	}			         
}

function batalKehadiran(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function simpanKehadiran(){	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "simpanKehadiran";
	doAjaxCall${formName}("");
}
function kemaskiniKehadiran(){
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function batalKemaskiniKehadiran(){
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function simpanKemaskiniKehadiran(){	
	if(document.${formName}.txtNama.value == ""){
		alert('Sila masukkan Nama Pegawai.');
  		document.${formName}.txtNama.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniKehadiran";
	doAjaxCall${formName}("");
}
function pilihSenaraiPermohonanBaru() {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupSenaraiPermohonanView";
    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function doSaveKeputusanBaru(id,idMesyuaratPermohonan){	
	if(document.${formName}.id.value !== "SILA PILIH"){
		document.${formName}.hitButton.value = "simpanKeputusanBaru";
		document.${formName}.idMesyuaratPermohonan.value= idMesyuaratPermohonan;
		document.${formName}.flagResult.value= document.getElementById(id).value;
		document.${formName}.submit();
	}
}
function doSaveCatatanKeputusanBaru(id,idMesyuaratPermohonan){	
	document.${formName}.hitButton.value = "simpanCatatanBaru";
	document.${formName}.idMesyuaratPermohonan.value= idMesyuaratPermohonan;
	document.${formName}.catatan.value= document.getElementById(id).value;
	document.${formName}.submit();
	
}
</script>