<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0000FF
}
-->
</style>
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input name="idFail" type="hidden" id="idFail" value="$idFail"/>
<input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
<input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
<input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
<input name="mode" type="hidden" id="mode" value="$mode"/>
<input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
<input name="hitButton" type="hidden" id="hitButton"/>
<input name="idDokumen" type="hidden" id="idDokumen" value="$idDokumen"/>
<input name="step" type="hidden" id="step" value="$step"/>
<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
<!-- saiz text -->
#set($saizTxtUlasanEIA="1500")
#set($saizTxtUlasanHidro="1500")
#set($saizTxtMaklumatTambahan="900")
#set($saizTxtSyaratKelulusan="900")

<body onLoad = $onload >
<table width="100%" border="0" cellpadding="2" cellspacing="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/frmAPBHeader.jsp") </td>
  </tr>
  #else
  <tr>
    <td>&nbsp;
      <div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end
  
  #if ($idFail != '' && $flagOpenDetail)
  <tr>
    <td>
    <div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTabUpper(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT PENYEDIAAN LESEN</li>         
          <li onClick="doChangeTabUpper(1);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT PELAN</li>       
        </ul>
      <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent">
		  <fieldset>
      		<legend><strong>MAKLUMAT PENYEDIAAN LESEN</strong></legend>
    		<table width="100%" border="0" cellspacing="2" cellpadding="2">
        	#foreach ($beanMaklumatSuratKelulusanLesenKepadaPemohon in $BeanMaklumatSuratKelulusanLesenKepadaPemohon)
        		<tr>
		          <td width="1%" valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
		          <td width="28%" valign="top">No Lesen</td>
		          <td width="1%" valign="top">:</td>
		          <td width="70%" valign="top"><input name="txtNoLesen" type="text" class="$inputTextClass" id="txtNoLesen" value="$beanMaklumatSuratKelulusanLesenKepadaPemohon.txtNoLesen" size="30" maxlength="100" $readonly/></td>
		        </tr>
		        <tr>
		          <td valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
		          <td valign="top">Tarikh Mula</td>
		          <td valign="top">:</td>
		          <td valign="top"><input name="txtTarikhMula" type="text" class="$inputTextClass" id="txtTarikhMula" onBlur="check_date(this);calcDate();" value="$beanMaklumatSuratKelulusanLesenKepadaPemohon.txtTarikhMula" size="9" maxlength="10" $readonly />
		            #if ($mode != 'view') <a href="javascript:displayDatePicker('txtTarikhMula',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
		        </tr>
		        <tr>
		          <td valign="top">&nbsp;</td>
		          <td valign="top">Tempoh</td>
		          <td valign="top">:</td>
		          <td>$beanMaklumatSuratKelulusanLesenKepadaPemohon.txtTempohDiluluskan #if($beanMaklumatSuratKelulusanLesenKepadaPemohon.idTempoh=='1') Bulan #else Tahun #end
	              <input name="idTempoh" type="hidden" id="idTempoh" value="$!beanMaklumatSuratKelulusanLesenKepadaPemohon.idTempoh"/>
                  <input name="tempoh" type="hidden" id="tempoh" value="$!beanMaklumatSuratKelulusanLesenKepadaPemohon.txtTempohDiluluskan"/>
                  </td>
	          </tr>
		        <tr>
		          <td valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
		          <td valign="top">Tarikh Tamat</td>
		          <td valign="top">:</td>
		          <td valign="top"><input name="txtTarikhTamat" type="text" class="$inputTextClass" id="txtTarikhTamat" onBlur="check_date(this)" value="$beanMaklumatSuratKelulusanLesenKepadaPemohon.txtTarikhTamat" size="9" maxlength="10" $readonly/>
		            #if ($mode != 'view') <a href="javascript:displayDatePicker('txtTarikhTamat',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end 
		          </td>
		        </tr>
		        <tr>
		          <td>&nbsp;</td>
		          <td>Fee Lesen</td>
		          <td>:</td>
		          <td>RM $beanMaklumatSuratKelulusanLesenKepadaPemohon.kadarFeeLesen          bagi setiap
		            $beanMaklumatSuratKelulusanLesenKepadaPemohon.kmPersegi
		            km persegi</td>
		        </tr>
		        <tr>
		          <td>&nbsp;</td>
		          <td>Jumlah Fee Lesen</td>
		          <td>:</td>
		          <td>RM
		            $beanMaklumatSuratKelulusanLesenKepadaPemohon.jumlahFeeLesen </td>
		        </tr>
		        <tr>
		          <td>&nbsp;</td>
		          <td>Kadar Royalti Pasir</td>
		          <td>:</td>
		          <td>RM
		            $beanMaklumatSuratKelulusanLesenKepadaPemohon.kadarRoyaltiPasir / meter padu</td>
		        </tr>
		        <tr>
		          <td>&nbsp;</td>
		          <td>Jumlah Royalti Keseluruhan</td>
		          <td>:</td>
		          <td>RM $beanMaklumatSuratKelulusanLesenKepadaPemohon.jumlahRoyaltiSeluruh</td>
		        </tr>
		        <tr>
		          <td>&nbsp;</td>
		          <td>Jumlah Pendahuluan Royalti</td>
		          <td>:</td>
		          <td>RM          $beanMaklumatSuratKelulusanLesenKepadaPemohon.jumDahuluRoyalti</td>
		        </tr>
		        <tr>
		          <td>&nbsp;</td>
		          <td>Luar Perairan Negeri</td>
		          <td>: </td>
		          <td>#if($beanMaklumatSuratKelulusanLesenKepadaPemohon.flagLuar=='1') Ya #else Tidak #end</td>
		        </tr>
		        <tr>
		          <td>&nbsp;</td>
		          <td>Negeri</td>
		          <td>: </td>
		          <td>$beanMaklumatSuratKelulusanLesenKepadaPemohon.namaNegeri</td>
		        </tr>
		        <tr>
		          <td>&nbsp;</td>
		          <td>Lokasi</td>
		          <td>: </td>
		          <td>$beanMaklumatSuratKelulusanLesenKepadaPemohon.lokasi</td>
		        </tr>
		        <tr>
		          <td>&nbsp;</td>
		          <td>Luas Kawasan</td>
		          <td>: </td>
		          <td>$beanMaklumatSuratKelulusanLesenKepadaPemohon.luas $beanMaklumatSuratKelulusanLesenKepadaPemohon.jenisLuas</td>
		        </tr>
		        #if ($mode != 'view')
		        <tr>
		          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
		        </tr>
		        #end
		        <tr>
		          <td>&nbsp;</td>
		          <td>&nbsp;</td>
		          <td>&nbsp;</td>
		          <td>&nbsp;</td>
		        </tr>
		        <tr>
		          <td>&nbsp;</td>
		          <td>&nbsp;</td>
		          <td>&nbsp;</td>
		          <td>#if ($mode == 'view')
		            <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="doKemaskini()"/>
		            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
		            #if($idStatus =='1610239')
		            <input type="button" name="cmdSeterusnya" id="cmdHantar" value="Seterusnya" onClick="doSeterusnya()"/>
		            <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
		            #end       
		            #end
		            #if ($mode == 'update')
		            <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="doSimpanKemaskiniSuratKelulusanLesenKepadaPemohon()"/>
		            <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="doBatalKemaskini()"/>
		            #end </td>
		        </tr>
		     #end
    		</table>
      	  </fieldset>
          <br>
      	  <fieldset id="tableReport" style="display:none;">
		    <legend><strong>SENARAI LAPORAN</strong></legend>
			<table width="100%" border="0" cellspacing="2" cellpadding="2">
				<tr>
				  <td ><a href="#" class="style2" onClick="javascript:cetakSuratHantarLesen('$idFail')"> Surat Hantar Lesen </a></td>
				</tr>
				<tr>
				  <td ><a href="#" class="style2" onClick="javascript:cetakSyaratKelulusan('$idFail','$idPermohonan')"> Syarat-syarat Kelulusan </a></td>
				</tr>
			</table>
		  </fieldset>
          </div>

		  <div class="TabbedPanelsContent">
      		#parse("app/php2/frmAPBPelan.jsp")
          </div>
      </div>
    </div>
    </td>
  </tr>
  #elseif ($idFail != '')
  <tr>
    <td> 
      <div class="warning"><strong>STATUS FAIL MASIH DI PERINGKAT $!status</strong></div></td>
  </tr>
  #end
</table>

<script type="text/javascript">
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
	/* var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0}); */
</script>
<script>
function doChangeTabUpper(tabId) {
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBCetakSuratKelulusanLesenKepadaPemohon";
	document.${formName}.method="POST";
	document.${formName}.submit();
}

function doKemaskini() {
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("doKemaskini");
}
function doBatalKemaskini() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doSimpanKemaskiniSuratKelulusanLesenKepadaPemohon() {
	
	if(document.${formName}.txtNoLesen.value == ""){
		alert('Sila masukkan No Lesen.');
  		document.${formName}.txtNoLesen.focus(); 
		return; 
	}
	if(document.${formName}.txtTarikhMula.value == ""){
		alert('Sila masukkan Tarikh Mula.');
  		document.${formName}.txtTarikhMula.focus(); 
		return; 
	}
	if(document.${formName}.txtTarikhTamat.value == ""){
		alert('Sila masukkan Tarikh Tamat.');
  		document.${formName}.txtTarikhTamat.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniSuratKelulusanLesenKepadaPemohon";
	document.${formName}.submit();
}

function calcDate(){
	if (document.${formName}.txtTarikhMula.value != "" && document.${formName}.idTempoh.value != "" && document.${formName}.tempoh.value != ""){

		var tarikhMula  = document.${formName}.txtTarikhMula.value;
		var pekali = 1;
		if (document.${formName}.idTempoh.value != "1") {
			pekali = 12;
		}
		var month  = parseInt(document.${formName}.tempoh.value*pekali);
		
		var dt1   = parseInt(tarikhMula.substring(0,2),10);
		var mon1  = parseInt(tarikhMula.substring(3,5),10)-1 + month;
		var yr1   = parseInt(tarikhMula.substring(6,10),10);
	 
		var myDate = new Date(yr1, mon1, dt1);
		myDate.setDate(myDate.getDate()-1);
		
		var day = myDate.getDate();
		var month = myDate.getMonth()+1;
		var year = myDate.getFullYear();
		
		var tarikhTamat = "";
		if(month>=10){
			if(day>=10){
				tarikhTamat = day + "/" + month + "/" + year;	
			} else {
				tarikhTamat = "0"+ day + "/" + month + "/" + year;	
			}				
		} else {
			if(day>=10){
				tarikhTamat = day + "/0" + month + "/" + year;	
			} else {
				tarikhTamat = "0"+ day + "/0" + month + "/" + year;	
			}
		}
		document.${formName}.txtTarikhTamat.value = tarikhTamat;
	
	} else {
		document.${formName}.txtTarikhTamat.value = "";
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
function doSeterusnya(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}

	document.${formName}.hitButton.value = "doSeterusnya";
	document.${formName}.submit();
}
function cetakSuratHantarLesen(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmAPBPopupCetakLaporanView?idFail="+idFail+"&report=cetakSuratHantarLesen";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSyaratKelulusan(idFail,idPermohonan) {
	var url = "../servlet/ekptg.report.php2.APBSyaratLulus?ID_FAIL="+idFail+"&ID_PERMOHONAN="+idPermohonan;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
<input name="step" type="hidden" id="step"/>
<script>
function gotoSelesaiPermohonan(){	
	document.${formName}.step.value = "selesaiPermohonan";
	document.${formName}.submit();
}
function gotoBatalPermohonan(){	
	document.${formName}.step.value = "batalPermohonan";
	document.${formName}.submit();
}
</script>
