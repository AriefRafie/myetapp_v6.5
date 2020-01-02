
<fieldset id="top">
<legend><strong>Permintaan Ukur</strong></legend>
<center>

	#parse("app/ppt/frmPPTHeader.jsp")

<br/>

	<fieldset id="bottom">
	<legend><strong>Senarai Surat</strong></legend>
			
			<table width="100%" border="0">   
                	<tr>
                    	<td><a href="#" onClick="javascript:cetakSuratKPTG('$!id_fail')"><font color="blue">Surat Iringan Kepada PTG</font></a></td>
    					<!-- <td><a href="#" onClick="javascript:cetakLampiranA('$!id_fail','$!nama2Mukim')"><font color="blue">Lampiran A</font></a></td> -->
    				</tr>
    				<tr>
    					<td><a href="#" onClick="javascript:cetakSuratPenandaankawasan('$!id_fail')"><font color="blue">Surat Kepada AP Supaya Melakukan Penandaan Kawasan</font></a></td>
    				</tr>
    				<tr>
    					<td><a href="#" onClick="javascript:cetakSuratJPPH('$!id_fail')"><font color="blue">Surat Kepada JPPH Supaya Menghadiri Rundingan</font></a></td>
    				</tr>
    				<tr>
    					<td><a href="#" onClick="javascript:cetakSiasatanAP('$!id_fail')"><font color="blue">Surat Kepada AP Supaya Hadir Untuk Siasatan</font></a></td>
    				</tr>
    				<tr>
    					<td><a href="#" onClick="javascript:cetakSiasatanAPPBUlang('$!id_fail')"><font color="blue">Surat Kepada AP/PB Supaya Hadir Untuk Ulang Siasatan</font></a></td>
    				</tr>
    				<tr>
    					<td><a href="#" onClick="javascript:cetakMaklumanBayaran('$!id_fail')"><font color="blue">Surat Kepada AP - Maklumat Cek Pampasan Telah Diserahkan</font></a></td>
    				</tr>
    				<tr>
    					<td><a href="#" onClick="javascript:cetakPerkara1JPBD('$!id_fail')"><font color="blue">JPBD (Maklumat Perancang)</font></a></td>
    				</tr>
    				<tr>
    					<td><a href="#" onClick="javascript:cetakPerkara2PTD('$!id_fail')"><font color="blue">PTD (Catatan dan Penandaan)</font></a></td>
    				</tr>
    				<tr>
    					<td><a href="#" onClick="javascript:cetakPerkara3KSU('$!id_fail')"><font color="blue">KSU (Kementerian Terbabit)</font></a></td>
    				</tr>
    				<tr>
    					<td><a href="#" onClick="javascript:cetakPerkara4JPPH('$!id_fail')"><font color="blue">JPPH</font></a></td>
    				</tr>
    				<tr>
    					<td><a href="#" onClick="javascript:cetakPerkara4PEKELILING('$!id_fail')"><font color="blue">Pekeliling KPTG</font></a></td>
    				</tr>
    		</table>
    		
	</fieldset>
	
	<br/>
	
	<fieldset id="bottom">
	<legend><strong>Senarai Borang</strong></legend>
		<table width="100%" border="0"> 
			<tr>
				<td><a href="#" onClick="javascript:cetakBorangC('$!id_fail')"><font color="blue">Borang C</font></a></td>
			</tr>
			<tr>
				<td><a href="#" onClick="javascript:cetakBorangD('$!id_fail')"><font color="blue">Borang D</font></a></td>
			</tr>
			<tr>
				<td><a href="#" onClick="javascript:cetakBorangE('$!id_fail')"><font color="blue">Borang E</font></a></td>
			</tr>
			<tr>
				<td><a href="#" onClick="javascript:cetakBorangF('$!id_fail')"><font color="blue">Borang F</font></a></td>
			</tr>
			<tr>
				<td><a href="#" onClick="javascript:cetakBorangG('$!id_fail')"><font color="blue">Borang G</font></a></td>
			</tr>
			<tr>
				<td><a href="#" onClick="javascript:cetakBorangH('$!id_fail')"><font color="blue">Borang H</font></a></td>
			</tr>
			<tr>
				<td><a href="#" onClick="javascript:cetakBorangI('$!id_fail')"><font color="blue">Borang I</font></a></td>
			</tr>
			<tr>
				<td><a href="#" onClick="javascript:cetakBorangJ('$!id_fail')"><font color="blue">Borang J</font></a></td>
			</tr>
			<tr>
				<td><a href="#" onClick="javascript:cetakBorangK('$!id_fail')"><font color="blue">Borang K</font></a></td>
			</tr>
			<tr>
				<td><a href="#" onClick="javascript:cetakBorangL('$!id_fail')"><font color="blue">Borang L</font></a></td>
			</tr>
		</table>
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

<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_fail" value="$!id_fail">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>


<script>
function cetakPerkara4PEKELILING(idfail) {

    var url = "../servlet/ekptg.report.ppt.Perkara4PEKELILING?idFail="+idfail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakPerkara4JPPH(idfail) {

    var url = "../servlet/ekptg.report.ppt.Perkara4JPPH?idFail="+idfail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakPerkara1JPBD(idfail) {

    var url = "../servlet/ekptg.report.ppt.Perkara1JPBD?idFail="+idfail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakPerkara2PTD(idfail) {

    var url = "../servlet/ekptg.report.ppt.Perkara2PTD?idFail="+idfail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakPerkara3KSU(idfail) {

    var url = "../servlet/ekptg.report.ppt.Perkara3KSU?idFail="+idfail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakLampiranA(idfail,namaMukim) {

	//var url = "../x/${securityToken}/ekptg.report.ppk.FrmPopupPilihPegawaiReportView?noFail="+NO_FAIL+"&idpermohonansimati="+idpermohonansimati+"&report=BorangDD&flagReport=B";
    var url = "../servlet/ekptg.report.ppt.LampiranASek4?id_fail="+idfail+"&nama_mukim="+namaMukim;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakSuratKPTG(idfail) {

    var url = "../servlet/ekptg.report.ppt.SuratKpdPTGDariJKPTGIbuPejabat?idfail="+idfail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakSuratPenandaankawasan(idfail) {

    var url = "../servlet/ekptg.report.ppt.SuratKepadaAPSupayaMelakukanPenandaanKawasan?idFail="+idfail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakSuratJPPH(idfail) {

    var url = "../servlet/ekptg.report.ppt.SuratKepadaJPPHSupayaMenghadiriPerundingan?idFail="+idfail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakSiasatanAP(idfail) {

    var url = "../servlet/ekptg.report.ppt.SuratSiasatanKpdAP?idFail="+idfail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakSiasatanAPPBUlang(idfail) {

    var url = "../servlet/ekptg.report.ppt.SuratKpdAPPBUlangSiasatan?idFail="+idfail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakMaklumanBayaran(idfail) {

    var url = "../servlet/ekptg.report.ppt.suratMaklumanSerahBayaranPampasanKpdAP?idFail="+idfail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBorangC(idfail) {

    var url = "../servlet/ekptg.report.ppt.BorangC?id_fail="+idfail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBorangD(idfail) {

    var url = "../servlet/ekptg.report.ppt.BorangD?id_fail="+idfail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBorangE(idfail) {

    var url = "../servlet/ekptg.report.ppt.BorangE?id_fail="+idfail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBorangF(idfail) {

    var url = "../servlet/ekptg.report.ppt.BorangF?id_fail="+idfail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBorangG(idfail) {

    var url = "../servlet/ekptg.report.ppt.BorangG?id_Fail="+idfail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBorangH(idfail) {

    var url = "../servlet/ekptg.report.ppt.BorangH?id_Fail="+idfail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBorangI(idfail) {

    var url = "../servlet/ekptg.report.ppt.BorangI?id_Fail="+idfail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBorangJ(idfail) {

    var url = "../servlet/ekptg.report.ppt.BorangJ?id_Fail="+idfail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBorangK(idfail) {

    var url = "../servlet/ekptg.report.ppt.BorangK?id_Fail="+idfail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakBorangL(idfail) {

    var url = "../servlet/ekptg.report.ppt.BorangL?id_Fail="+idfail;
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
function kembali(){
	document.${formName}.command.value = "cleardata";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek8ListSurat";
	document.${formName}.submit();
}

</script>