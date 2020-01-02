#set ($noKpBaru1 = "")
#set ($noKpBaru2 = "")
#set ($noKpBaru3 = "")
#set ($noKpLain = "")
#set ($NamaSimati = "")
#set ($SocWaris = "")
#set ($SocOb = "")
#set ($noKpLama = "")
#set ($JenisKpLain = "")
#set ($NamaPemohon = "")
#set ($SocWaris = "")
#set ($SocOb = "")

#set ($setmode = "")
#set ($checked1 = "")
#set ($checked2 = "")
#set ($checked3 = "")
#set ($checked4 = "")
#set ($setbutton = "")

#if ($EventStatus == 1)
	#set ($setmode = "disabled")
#else
	#set ($setmode = "")
#end

#if ($sorAdaHTA == 2)
	#set ($checked2 = "checked")
	#set ($setbutton = "disabled")
#end

#if ($submission == 1)
	#set ($setbutton = "disabled")
#end

#if ($sorAdaHTA == 1 && $TempDb == 1)
	#set ($noKpBaru1 = $noKPBaru1)
	#set ($noKpBaru2 = $noKPBaru2)
	#set ($noKpBaru3 = $noKPBaru3)
	#set ($noKpLama = $noKPLama)
	#set ($JenisKpLain = $jenisKPLain)
	#set ($noKpLain = $noKPLain)
	#set ($NamaPemohon = $namaPemohon)
	#set ($SocWaris = $socWaris)
	#set ($SocOb = $socOb)
	#set ($checked1 = "checked")
#end
#if ($sorWaris == 1)
	#set ($checked3 = "checked")
#elseif ($sorWaris == 2)
	#set ($checked4 = "checked")
#else
	#set ($checked3 = "")
	#set ($checked4 = "")
#end

#if ($IdAlert == 1)
<script type="text/javascript">
	alert("Minta maaf. No KP telah wujud.")
</script>
#end
<body onload="window.print();" >
<p></p>
<p></p>
#if ($command == "cetak_form")
#if ($submission == 1 && $IdAlert == 0)
#set ($namePemohon = "")
#set ($icPemohon = "")
#set ($noPemohon = "")
#set ($IdPemohonan = "")
#foreach ($detailPemohon in $DetailPemohon)
	#set ($namePemohon = $detailPemohon.namaPemohon)
	#set ($icPemohon = $detailPemohon.noKpBaruPemohon)
	#set ($noPemohon = $detailPemohon.noPermohonan)
	#set ($IdPemohonan = $detailPemohon.idPermohonan)
#end
<fieldset>
<table width="100%">
<tr>
<td colspan="2" align="center"><font color="red"><b>PERHATIAN</b></font></td>
</tr>
<tr>
<td height="20px" colspan="2">Sila gunakan nombor Kad Pengenalan baru dan No. Permohonan untuk mengemaskini permohonan anda.</td>
</tr>
<tr>
<td align="right" width="30%">Nama : </td>
<td width="70%"><b>$namePemohon.toUpperCase()</b></td>
</tr>
<tr>
<td align="right">No. KP : </td>
<td><b>$icPemohon</b></td>
</tr>
<tr>
<td align="right">No Permohonan : </td>
<td><b>$noPemohon.toUpperCase()</b></td>
</tr>
</table>
</fieldset>
#end
<p align="right">CL-01-01</p>
#elseif ($command == "cetak_surat")	
<fieldset><legend>STATUS PENGESAHAN PERMOHONAN</legend>
<table width="100%">
<tr>
<td colspan="2">Permohonan Anda Telah Dihantar Ke :</td>
</tr>
#foreach ($data in $selectedPpkAddress)
		#set ($namapejabat = $data.namapejabat)
		#set ($alamat1 = $data.alamatOne)
		#set ($alamat2 = $data.alamatTwo)
		#set ($alamat3 = $data.alamatThree)
		#set ($poskod = $data.poskod)
		#set ($namanegeri = $data.negerinama)
		#set ($no_tel = $data.notel)
		#set ($no_tel_samb = $data.notel_sambungan)
#end
<tr>
<td colspan="2"><b>$namapejabat<br>$alamat1<br>$alamat2<br>$alamat3<br>$poskod&nbsp;&nbsp;$namanegeri</td>
</tr>
<tr>
<td colspan="2" height="20px">&nbsp;</td>
</tr>
<tr>
<td colspan="2">PERHATIAN</td>
</tr>
<tr>
<td>1.</td>
<td>Anda boleh mengemaskini permohonan anda dalam tempoh 14 hari dari tarikh permohonan.</td>
</tr>
<tr>
<td>2.</td>
<td>Sila kemukakan permohonan anda ke : $namapejabat</td>
</tr>
<tr>
<td>3</td>
<td>Sila bawa bersama dokumen berikut :</td>
</tr>
<tr>
<td>&nbsp;</td>
<td>1. Borang A</td>
</tr>
<tr>
<td>&nbsp;</td>
<td>2. Senarai Semak</td>
</tr>
</table>
</fieldset>
#end
<input type="hidden" name="hitButt" />
<input type="hidden" name="idPermohonan" value="$IdPemohonan"/>
</body>
<script>
function cancel() {
document.${formName}.reset();
document.${formName}.txtNoFail.value = "";
document.${formName}.txtNoFail.focus();
}
function Tambah() {
	document.${formName}.command.value = "pemohon_tambah";
	document.${formName}.action = "";
	document.${formName}.submit();
}
function search_data(){
	document.${formName}.command.value = "";
	//document.${formName}.nama_fail.value = key;
	document.${formName}.action = "";
	document.${formName}.submit();
}
function edit_item(id,id2){
	document.${formName}.command.value = "pemohon_papar";
	document.${formName}.action = "";
	document.${formName}.idPermohonan.value = id;
	document.${formName}.idSimati.value = id2;
	document.${formName}.submit();
}
function getTidak(){
	alert("Maaf. Permohonan tidak dapat diteruskan.");
	document.${formName}.method="post";
	document.${formName}.command.value = "pemohon_tidak";
	document.${formName}.action="";
	document.${formName}.submit();
}
function getYa(){
	document.${formName}.method="post";
	document.${formName}.command.value="pemohon_ya";
	document.${formName}.action="";
	document.${formName}.submit();
}
function getSimpan(){
	if (isNaN(document.${formName}.txtNoKPBaru1.value)){
		alert("Sila masukkan nombor sahaja");
		txtNoKPBaru1.focus();
	}
	else if (isNaN(document.${formName}.txtNoKPBaru2.value)){
		alert("Sila masukkan nombor sahaja");
		txtNoKPBaru2.focus();
	}
	else if (isNaN(document.${formName}.txtNoKPBaru3.value)){
		alert("Sila masukkan nombor sahaja");
		txtNoKPBaru3.focus();
	}
	else if (document.${formName}.txtNoKPBaru1.value == "" && document.${formName}.txtNoKPBaru2.value == "" && document.${formName}.txtNoKPBaru3.value == ""){
		alert("Sila masukkan No KP Baru");
		txtNoKPBaru1.focus();
	}
	else if (document.${formName}.txtNoKPBaru1.value != "" && document.${formName}.txtNoKPBaru1.value.length < 6){
		alert("Sila masukkan No KP Baru sepenuhnya");
		txtNoKPBaru1.focus();
	}
	else if (document.${formName}.txtNoKPBaru2.value != "" && document.${formName}.txtNoKPBaru2.value.length < 2){
		alert("Sila masukkan No KP Baru sepenuhnya");
		txtNoKPBaru2.focus();
	}
	else if (document.${formName}.txtNoKPBaru3.value != "" && document.${formName}.txtNoKPBaru3.value.length < 4){
		alert("Sila masukkan No KP Baru sepenuhnya");
		txtNoKPBaru3.focus();
	}
	else if (document.${formName}.txtNoKPBaru1.value != "" && document.${formName}.txtNoKPBaru2.value == "" && document.${formName}.txtNoKPBaru3.value == ""){
		alert("Sila masukkan No KP Baru sepenuhnya");
		txtNoKPBaru2.focus();
	}
	else if (document.${formName}.txtNoKPBaru1.value != "" && document.${formName}.txtNoKPBaru2.value != "" && document.${formName}.txtNoKPBaru3.value == ""){
		alert("Sila masukkan No KP Baru sepenuhnya");
		txtNoKPBaru3.focus();
	}
	else if (document.${formName}.txtNoKPLama.value != "" && document.${formName}.txtNoKPLama.value.length < 7){
		alert("Sila masukkan NO KP Lama Sepenuhnya");
		txtNoKPLama.focus();
	}
	else if (document.${formName}.txtNoKPLain.value != "" && document.${formName}.socJenisKPLain.value == "0"){
		alert("Sila pilih jenis No KP");
		socJenisKPLain.focus();
	}
	else if (document.${formName}.socJenisKPLain.value != "0" && document.${formName}.txtNoKPLain.value == ""){
		alert("Sila masukkan No KP Lain");
		txtNoKPLain.focus();
	}
	else if (document.${formName}.sorWaris[0].checked == false && document.${formName}.sorWaris[1].checked == false){
		alert("Sila tanda salah satu hubungan pemohon dengan simati");
	}
	else if (document.${formName}.sorWaris[0].checked == true && document.${formName}.socWaris.value == "0"){
		alert("Sila pilih hubungan waris");
		document.${formName}.socOB.selectedindex=0;
		document.${formName}.socOB.options[0].selected = true;
	}
	else if (document.${formName}.sorWaris[1].checked == true && document.${formName}.socOB.value == "0"){
		alert("Sila pilih hubungan orang berkepentingan");
		document.${formName}.socWaris.selectedindex=0;
		document.${formName}.socWaris.options[0].selected = true;
	}
	else if (document.${formName}.sorAdaHTA[0].checked == false && document.${formName}.sorAdaHTA[1].checked == false){
		alert("Sila tanda salah satu 'Adakah SiMati Memiliki Harta Tak Alih'");
	}
	else{
	document.${formName}.method="post";
	document.${formName}.command.value = "pemohon_simpan";
	document.${formName}.action="";
	document.${formName}.submit();
	}
}
function getCetak(){
	var url = "../x/${securityToken}/ekptg.view.ppk.FrmPrmhnnBorangACetak?idPermohonan="+idPermohonan+"&command="+command;
	var hWnd = window.open(url,'printuser','width=600,height=600, resizable=yes,scrollbars=yes');
}
function getSeterusnya(){
	document.${formName}.method="post";
	document.${formName}.command.value="Seterusnya_pemohon";
	document.${formName}.action="";
	document.${formName}.submit();
}
</script>

