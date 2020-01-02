<body>
	<fieldset>
<legend>Maklumat untuk Borang A</legend>
<table width="97%" border="0">
    <tr>
        <td>
 <input type="hidden" name="action">
 <input type="hidden" name="hitButt">
 <input type="hidden" name="mode">
 <input type="hidden" name="idpermohonansimati" value="$idpermohonansimati">
 <input type="hidden" name="simpanStatus" value="$SimpanStatus">
 <input type="hidden" name="nopermohonanonline" value="$nopermohonanonline">
 <input name="tabIdatas" type="hidden" id="tabIdatas" value="$selectedTabatas"/>
 <input name="tabIdtengah" type="hidden" id="tabIdtengah" value="$selectedTabtengah"/>
 <input name="tabIdbawah" type="hidden" id="tabIdbawah" value="$selectedTabbawah"/>
 <input name="tabIdtepi" type="hidden" id="tabIdtepi" value="$selectedTabtepi"/>

 <input type="hidden" name="eventStatus">
 <input type="hidden" name="idTemp" value="$id">
 <input type="hidden" name="id" value="$id">
 <input type="hidden" name="id1" value="$id1">
 <input type="hidden" name="id2" value="$id2">
 <input type="hidden" name="id3" value="$id3">
 <input type="hidden" name="idha" value="$idha">
 <input type="hidden" name="bil" value="$jumList">
 
#foreach($list in $View)
#set ($id = $list.idPermohonan)
#set ($idPemohon = $list.idPemohon)
#set ($idSimati = $list.idSimati)
#set ($negerimhn = $list.idnegerimhn)
#set ($daerahmhn = $list.iddaerahmhn)    
    <input name="idPermohonanp" type="hidden"  value="$list.idPermohonan"/>
    <input name="idPermohonan" type="hidden"  value="$id"/>
     <input name="idPemohon" type="hidden"  value="$idPemohon"/>
      <input name="idSimati" type="hidden"  value="$idSimati"/>
       <input name="idtemp" type="hidden"  value="$id"/>
#end        </td>
    </tr>
<tr>
<td></td>
</tr>
<tr>
<td>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
  <li class="TabbedPanelsTab" tabindex="0" onClick="SimatiView('0','0','0','0')">PERMOHONAN</li>
   <li class="TabbedPanelsTab" tabindex="0" onClick="HtaamView('1','0','0','0')">HARTA TAK ALIH</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="HAview('2','0','0','0')">HARTA ALIH</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="PengesahanView('3','0','0','0')">PENGESAHAN PERMOHONAN</li>
  </ul>
   <div class="TabbedPanelsContentGroup">
	<div class="TabbedPanelsContent"></div>
	<div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent">
	<br>
        <table width="97%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>
<fieldset><legend>PENGESAHAN PERMOHONAN</legend>
<font color="#FF0000" size="2">
<i>* Sila cetak Borang A dan Pengesahan Permohonan untuk dibawa ke Unit Pembahagian Pusaka Kecil.
<br>   * Permohonan akan dibatalkan sekiranya terdapat permohonan lain yang lengkap dihantar ke Unit Pembahagian Pusaka Kecil.    
    </i></font>
<br>
<br>
<br>
<table width="100%" border="0" cellpadding="1" cellspacing="0">
<tr>
<td width="30%" align="right">Negeri : </td>
<td width="70%">



<select name="socNegeriPengesahan" style="width: 300px;" onChange="getDaerah()" $disabledDropdown>
#set ($selIdNegeri = "")
	#if ($selNegeri != "0")
			#set ($selected = "")
			#foreach ($listNegeri in $senaraiNegeriByPpkUnit)
				#if ($listNegeri.idnegeri == $selNegeri)
					#set ($selected = "selected")	
				<option value="$listNegeri.idnegeri" $selected>$listNegeri.namanegeri</option>
				#end
			#end
			
			<option value="0">Sila Pilih</option>
			#foreach ($listNegeri in $senaraiNegeriByPpkUnit)
				#set ($selIdNegeri = $selNegeri)	
				<option value="$listNegeri.idnegeri">$listNegeri.namanegeri</option>
			#end
	#else
			#if ($negerimhn != "")
				#foreach ($listNegeri in $senaraiNegeriByPpkUnit)
						#if ($listNegeri.idnegeri == $negerimhn)
							#set ($selected = "selected")	
                           
						<option value="$listNegeri.idnegeri" $selected>$listNegeri.namanegeri</option>
						#end
				#end	
				<option value="0">Sila Pilih</option>
				#foreach ($listNegeri in $senaraiNegeriByPpkUnit)
					#set ($selIdNegeri = $selNegeri)	
					<option value="$listNegeri.idnegeri">$listNegeri.namanegeri</option>
				#end
			#else
		
				<option value="0">Sila Pilih</option>
				#foreach ($listNegeri in $senaraiNegeriByPpkUnit)
					#set ($selIdNegeri = $selNegeri)	
					<option value="$listNegeri.idnegeri">$listNegeri.namanegeri</option>
				#end
			#end
	#end
</select><input type="hidden" name="saizdata" value="$!saizData"></td>
</tr>
<tr>
<td align="right">Daerah :</td>
<td><select name="socDaerahPengesahan" style="width: 300px;" onChange="getAddress()" $disabledDropdown>
	#if ($selDaerah != "0")
			#foreach ($listDaerah in $selectedDaerah)
				#if ($listDaerah.id == $selDaerah)
					#set ($selected3 = "selected")
				<option value="$listDaerah.id" $selected3>$listDaerah.nama</option>
				#end
			#end
			<option value="0">Sila Pilih</option>
			#foreach ($listDaerah in $selectedDaerah)
				<option value="$listDaerah.id">$listDaerah.nama</option>
			#end
	#else
			#if ($daerahmhn != "")
					#foreach ($listDaerah in $selectedDaerah)
						#if ($listDaerah.id == $daerahmhn)
                       
						<option value="$listDaerah.id" selected>$listDaerah.nama</option>
						#end
					#end
					<option value="0">Sila Pilih</option>
					#foreach ($listDaerah in $selectedDaerah)
						<option value="$listDaerah.id">$listDaerah.nama</option>
					#end
			#else
					<option value="0">Sila Pilih</option>
					#foreach ($listDaerah in $selectedDaerah)
						<option value="$listDaerah.id">$listDaerah.nama</option>
					#end
			#end
	#end
</select></td>
</tr>
#if ($selDaerah != "0" || $daerahmhn != "")
	#foreach ($data in $selectedPpkAddress)
		#set ($namapejabat = $data.namapejabat)
		#set ($alamat1 = $data.alamatOne)
		#set ($alamat2 = $data.alamatTwo)
		#set ($alamat3 = $data.alamatThree)
		#set ($poskod = $data.poskod)
		#set ($no_tel = $data.notel)
		#set ($no_tel_samb = $data.notel_sambungan)
	#end
#end
<tr>
<td align="right">Pejabat : </td>
<td><input type="text" size="50" maxlength="46" name="namapejabat" value="$!namapejabat" readonly class="disabled" style="text-transform:uppercase;"></td>
</tr>
<tr>
<td align="right">Alamat : </td>
<td><input type="text" size="50" maxlength="46" name="alamat1" value="$!alamat1" readonly class="disabled" style="text-transform:uppercase;"></td>
</tr>
<tr>
<td></td>
<td><input type="text" size="50" maxlength="46" name="alamat2" value="$!alamat2" readonly class="disabled" style="text-transform:uppercase;"></td>
</tr>
<tr>
<td></td>
<td><input type="text" size="50" maxlength="46" name="alamat3" value="$!alamat3" readonly class="disabled" style="text-transform:uppercase;"></td>
</tr>
<tr>
<td align="right">Poskod :</td>
<td><input type="text" size="5" maxlength="5" name="poskod" value="$!poskod" readonly class="disabled" ></td>
</tr>
<tr>
<td align="right">No. Telefon : </td>
<td><input type="text" size="12" maxlength="11" value="$!no_tel" readonly class="disabled" ></td>
</tr>
#if ($no_tel_samb != "")
<tr>
<td align="right">No. Telefon (samb) : </td>
<td><input type="text" size="12" maxlength="11" value="$!no_tel_samb" readonly class="disabled" ></td>
</tr>
#end
</table>
</fieldset>

<input name="idStatus" type="hidden" value="$idStatus">
<p align="center">
#if ($idStatus == "150")
<input type="button" name="cmdHantar" value="Hantar" onClick="javascript:getUnitPPK('$id','$nopermohonanonline')"><!--<input type="button" name="cmdKosongkan" value="Kosongkan" onClick="PengesahanView('3','0','0','0')">-->
#else
<input type="button" name="cmdBorangA" value="Cetak Borang A" onClick="javascript:cetakBorangA('$id','$nopermohonanonline')">
<input type="button" name="cmdPengesahanA" value="Cetak Pengesahan Permohonan" onClick="javascript:cetakPengesahan()">
#end</p>    </td>
  </tr>
</table>
      </div>
    </div>
  </div></td>
  </tr>
</table>
</fieldset>
	</div>
  </div>
</div>
</td>
</tr>
</table>
<script>
function getUnitPPK(idpermohonan,nopermohonanonline) {
    if (document.${formName}.socNegeriPengesahan.value=="0"){
		alert("Sila pilih Negeri");
		socNegeriPengesahan.focus();
	}else if (document.${formName}.socDaerahPengesahan.value=="0"){
		alert("Sila pilih Daerah");
		socDaerahPengesahan.focus();
	}
	else{
		input_box=confirm("Adakah anda pasti?");
		if (input_box == true) {
		document.${formName}.method="post";
		//document.${formName}.idPermohonan.value=idpermohonan;
		document.${formName}.tabIdatas.value="4";
		document.${formName}.tabIdtengah.value="0";
		document.${formName}.tabIdbawah.value="0";
		document.${formName}.tabIdtepi.value="0";
		document.${formName}.action.value="";
		doAjaxCall${formName}("cetak_surat");
		document.${formName}.submit();

		//PengesahanView(4,0,0,0);
		
		}
	}
}

function cetakPengesahan() {
    var url="../servlet/ekptg.report.ppk.PengesahanPermohonanOnline?idpermohonan="+$IdPermohonan;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	
	refreshPengesahanView();
}

function refreshPengesahanView() {
	document.${formName}.method="post";
	doAjaxCall${formName}("pengesahan_permohonan");
	document.${formName}.tabIdatas.value="3";
    document.${formName}.tabIdtengah.value="0";
    document.${formName}.tabIdbawah.value="0";	
	document.${formName}.tabIdtepi.value="0";
	document.${formName}.action.value="";

	document.${formName}.submit();
}

function cetakBorangA() {
    var url="../servlet/ekptg.report.ppk.BorangAOnline?NoPermohonan="+document.${formName}.nopermohonanonline.value;
    var hWnd=window.open(url,'Cetak2','width=800,height=500, resizable=yes,scrollbars=yes');
}

function PengesahanView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
    //alert("Sila pastikan maklumat harta dan waris lengkap");
	document.${formName}.method="post";
	doAjaxCall${formName}("pengesahan_permohonan");
	document.${formName}.tabIdatas.value=tabIdatas;
    document.${formName}.tabIdtengah.value=tabIdtengah;
    document.${formName}.tabIdbawah.value=tabIdbawah;	
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function WarisView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Warisview";
	doAjaxCall${formName}("Waris");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}
function PemohonView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Pemohonview";
	doAjaxCall${formName}("Pemohon");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}
function SimatiView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Simatiview";
	doAjaxCall${formName}("Simati");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}
function HtaamView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.mode.value="Htaamview";
	doAjaxCall${formName}("Htaam");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.action.value="";
	document.${formName}.submit();
}
function HAview(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.action.value="";
	document.${formName}.mode.value="view_harta_alih";
	doAjaxCall${formName}("harta_alih");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.submit();
}
function NAview(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.action.value="";
	document.${formName}.mode.value="view_nilai_harta";
	document.${formName}.hitButt.value="nilai_harta";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.submit();
}

function PenghutangView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.action.value="";
	document.${formName}.mode.value="Penghutangview";
	doAjaxCall${formName}("Penghutang");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.submit();
}
function PemiutangView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.action.value="";
	document.${formName}.mode.value="Pemiutangview";
	doAjaxCall${formName}("Pemiutang");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.submit();
}

function PentingView(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	document.${formName}.method="post";
	document.${formName}.action.value="";
	document.${formName}.mode.value="Pentingview";
	doAjaxCall${formName}("Penting");
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.submit();
}



function kembali_simati(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi){
	document.${formName}.method="post";
	document.${formName}.mode.value="Simati";
	document.${formName}.hitButt.value="kembali_simati";
	document.${formName}.action.value="";
	document.${formName}.tabIdatas.value=tabIdatas;
	document.${formName}.tabIdtengah.value=tabIdtengah;
	document.${formName}.tabIdbawah.value=tabIdbawah;
	document.${formName}.tabIdtepi.value=tabIdtepi;
	document.${formName}.submit();
}

function setSelected(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi)
 {
    document.${formName}.tabIdatas.value=tabIdatas;
    document.${formName}.tabIdtengah.value=tabIdtengah;
    document.${formName}.tabIdbawah.value=tabIdbawah;	
	document.${formName}.tabIdtepi.value=tabIdtepi;	
}
function cancelwaris() {
input_box=confirm("Adakah anda pasti?");
	if (input_box == true) {
document.${formName}.reset();
document.${formName}.txtNoKPBaru1Waris.focus();
}
}
<!-- NILAI HARTA -->
function getNilaiHartaKemaskini(){
	document.${formName}.method="post";
	document.${formName}.hitButt.value="nilai_harta";
	doAjaxCall${formName}("nilai_harta");
	document.${formName}.mode.value="kemaskini_nilai_harta";
	document.${formName}.eventStatus.value="4";
	document.${formName}.action.value="";
	document.${formName}.submit();
}
function getNilaiHartaSimpan(){
	document.${formName}.method="post";
	doAjaxCall${formName}("nilai_harta");
	document.${formName}.mode.value="simpan_nilai_harta";
	document.${formName}.eventStatus.value="1";
	document.${formName}.action.value="";
	document.${formName}.submit();
}
function getNilaiHartaBatal(){
	document.${formName}.method="post";
	doAjaxCall${formName}("nilai_harta");
	document.${formName}.mode.value="batal_nilai_harta";
	document.${formName}.eventStatus.value="1";
	document.${formName}.action.value="";
	document.${formName}.submit();
}

<!-- HARTA ALIH -->
function getFormHa(){
	document.${formName}.method="post";
	doAjaxCall${formName}("nilai_harta");
	document.${formName}.mode.value="tambah_harta";
	document.${formName}.eventStatus.value="0";
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function getJenisHa(){
	document.${formName}.method="post";
	doAjaxCall${formName}("nilai_harta");
	document.${formName}.mode.value="tambah_harta";
	document.${formName}.eventStatus.value="0";
	document.${formName}.action.value="";
	document.${formName}.submit();
}

function getSimpan(){
	
	input_box=confirm("Adakah anda pasti?");
	if (input_box == true) {
		document.${formName}.method="post";
		doAjaxCall${formName}("nilai_harta");
		document.${formName}.mode.value="simpan_harta";
		document.${formName}.eventStatus.value="1";
		document.${formName}.action.value="";
		document.${formName}.submit();
	
	}
}
function edit_hartaalih(id3){
	document.${formName}.method="post";
	doAjaxCall${formName}("nilai_harta");
	document.${formName}.mode.value="edit_harta";
	document.${formName}.eventStatus.value="2";
	document.${formName}.idha.value=id3;
	document.${formName}.action.value="";
	document.${formName}.submit();
}
function getKemaskini(){
	document.${formName}.method="post";
	document.${formName}.hitButt.value="harta_alih";
	doAjaxCall${formName}("nilai_harta");
	document.${formName}.eventStatus.value="3";
	document.${formName}.action.value="";
	document.${formName}.submit();
}
function getHapus(){
	input_box=confirm("Adakah anda pasti?");
	if (input_box == true) {
		document.${formName}.method="post";
		doAjaxCall${formName}("nilai_harta");
		document.${formName}.mode.value="hapus_harta";
		document.${formName}.eventStatus.value="1";
		document.${formName}.action="";
		document.${formName}.submit();
	}
}
function getUpdate(){
	input_box=confirm("Adakah anda pasti?");
	if (input_box == true) {
		document.${formName}.method="post";
		doAjaxCall${formName}("nilai_harta");
		document.${formName}.mode.value="update_harta";
		document.${formName}.eventStatus.value="2";
		document.${formName}.action="";
		document.${formName}.submit();
	
	}
}
function getBatal(){
		document.${formName}.method="post";
		doAjaxCall${formName}("nilai_harta");
		document.${formName}.mode.value="batal_harta";
		document.${formName}.eventStatus.value="1";
		document.${formName}.action.value="";
		document.${formName}.submit();
}

function getDaerah(){
	document.${formName}.method="post";
	document.${formName}.mode.value="selection_daerah";
	document.${formName}.tabIdatas.value="4";
	document.${formName}.tabIdtengah.value="0";
	document.${formName}.tabIdbawah.value="0";
	document.${formName}.tabIdtepi.value="0";
	document.${formName}.action.value="";
	doAjaxCall${formName}("pengesahan_permohonan");
	document.${formName}.submit();	
}
function getAddress(){
	document.${formName}.method="post";
	document.${formName}.mode.value="ppkAddressView";
	document.${formName}.tabIdatas.value="4";
	document.${formName}.tabIdtengah.value="0";
	document.${formName}.tabIdbawah.value="0";
	document.${formName}.tabIdtepi.value="0";
	document.${formName}.action.value="";
	doAjaxCall${formName}("pengesahan_permohonan");
	document.${formName}.submit();	
}
</script>
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:3});
var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:0});
//-->
</script>
</body>
