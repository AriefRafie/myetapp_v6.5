<style type="text/css">
<!--
body {
	background-color: #FFFFFF;
}
.style42 {color: #0000FF}
-->
</style>
<body>
<fieldset>
<legend>PEMBAHAGIAN PUSAKA KECIL</legend>

<table width="100%" border="0">
    <tr>
        <td>
        <input type="hidden" name="hitButt">
        <input type="hidden" name="mode">
        <input name="tabIdatas" type="hidden" id="tabIdatas" value="$selectedTabatas"/>
        <input name="tabIdtengah" type="hidden" id="tabIdtengah" value="$selectedTabtengah"/>
        <input name="tabIdbawah" type="hidden" id="tabIdbawah" value="$selectedTabbawah"/>
        <input name="tabIdtepi" type="hidden" id="tabIdtepi" value="$selectedTabtepi"/>
        <input type="hidden" name="idpermohonansimati" value="$idpermohonansimati">
		<input type="hidden" name="nopermohonanonline">
		<input type="hidden" name="idfail">
        <input type="hidden" name="idfaillama" value="$idfaillama">
        <input type="hidden" name="idpermohonanbaru">
        #foreach($list in $View)
            #set ($id = $list.idPermohonan)
            #set ($idPemohon = $list.idPemohon)
            #set ($idSimatix = $list.idSimati)
        <input name="idPermohonanp" type="hidden"  value="$list.idPermohonan"/>
        <input name="idPermohonan" type="hidden"  value="$id"/>
        <input name="idPemohon" type="hidden"  value="$idPemohon"/>
        <input name="idtemp" type="hidden"  value="$id"/>
        #end
        <input type="hidden" name="idSimati" value="$idSimati">
        </td>
    </tr>
<tr>
<td>
#foreach ($fail in $DetailPemohon)
	#set ($nofail1 = $fail.nofaillama)
    #set ($idfail2 = $fail.idfail)
    #set ($nofail2 = $fail.nofail)
	#set ($keterangan = $fail.keterangan)
    #set ($seksyen = $fail.seksyen)
	#set ($tarikhMohon = $fail.tarikhmohon)
	#set ($pemohon = $fail.namaPemohon)
    #set ($namasimati = $fail.namasimati)
    #set ($idfaillama = $fail.idfaillama)
    #set ($idpermohonanbaru = $fail.idPermohonan)
#end
    <fieldset>
<legend>MAKLUMAT UNTUK BORANG P</legend>
<table width="100%" border="0" align="center">
  <tr>
    <td valign="top"><div align="center">
      <table width="100%" border="0">
      #if ($idStatus == "0" || $idStatus == "160" || $idStatus == "171")  
       <tr>
          <td width="35%" style="text-transform:uppercase; "><div align="right">No Rujukan Lama :</div></td>
          <td width="65%" style="text-transform:uppercase;"><div align="left" class="style42">$!nofail1</div></td>
        </tr>
      #else
        <tr>
          <td width="35%" style="text-transform:uppercase; "><div align="right">No Fail :</div></td>
          <td width="65%" style="text-transform:uppercase;"><div align="left" class="style42">$!nofail2</div></td>
        </tr>
      #end   
      <tr>
          <td style="text-transform:uppercase;" valign="top"><div align="right">Status Fail :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style42">$!keterangan</span><input type="hidden" name="idfail2" value="$idfail2" /></div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Seksyen :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style42">$!seksyen</span>
                    <input type="hidden" name="txtSeksyen" value="$list.seksyen" readonly="true"/>
          </div></td>
        </tr>
      </table>
    </div></td>
    <td width="50%" valign="top"><div align="center">
      <table width="100%" border="0">
        <tr>
          <td width="35%" style="text-transform:uppercase;"><div align="right">Tarikh Mohon :</div></td>
          <td width="65%" style="text-transform:uppercase;"><div align="left"><span class="style42">$!tarikhMohon</span>
                    <input type="hidden" name="txdTarikhMohon" value="$View.tarikhMohon" readonly="true"/>
          </div></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Nama Simati :</div></td>
          <td style="text-transform:uppercase;"><span class="style42">$!namasimati</span>
            <input type="hidden" name="idSimati" value="$list.idSimati" readonly="true"/></td>
        </tr>
        <tr>
          <td style="text-transform:uppercase;"><div align="right">Nama Pemohon :</div></td>
          <td style="text-transform:uppercase;"><div align="left"><span class="style42">$!pemohon</span>
                    <input type="hidden" name="txtNamaPemohon" value="$View.namaPemohon" readonly="true"/>
          </div></td>
        </tr>
		
      </table>
    </div></td>
  </tr>
</table>
</fieldset>  
</td>
</tr>
<tr>
<td>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
   <li class="TabbedPanelsTab" tabindex="0" onClick="HtaamView()">HARTA TAK ALIH</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="HAview()">HARTA ALIH</li>
    <li class="TabbedPanelsTab" tabindex="0" onClick="PengesahanView()">PENGESAHAN PERMOHONAN</li>
  </ul>
   <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"><br>
    <table width="98%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>
<fieldset><legend>PENGESAHAN PERMOHONAN BORANG P</legend>
<font color="#FF0000" size="2">
<i>* Sila cetak Borang P dan Pengesahan Permohonan untuk dibawa ke Unit Pembahagian Pusaka Kecil.
<br>
   * Permohonan akan dibatalkan sekiranya terdapat permohonan lain yang lengkap dihantar ke Unit Pembahagian Pusaka Kecil.    
    </i></font><br>
<!--<i>* Sila cetak Borang P untuk dibawa ke Pejabat Pusaka.<i>-->
<br>
<br>
<br>
#set ($disabledP = "")
#if ($idStatus == "171")
	#set ($disabledP = "disabled")
#end
<table width="100%" border="0">
<tr>
<td width="30%" align="right">Negeri :&nbsp;</td>
<td width="70%"><select name="socNegeriPengesahan" style="width: 300px;" onChange="getDaerah()" $setmode $disabledP>
	#if ($selNegeri != "0")
			#set ($selected = "")
			#foreach ($listNegeri in $senaraiNegeriByPpkUnit)
				#if ($listNegeri.idnegeri == $selNegeri)
					#set ($selected = "selected")	
				<option value="$listNegeri.idnegeri" $selected>$!listNegeri.namanegeri</option>
				#end
			#end
			
			<option value="0">Sila Pilih</option>
			#foreach ($listNegeri in $senaraiNegeriByPpkUnit)
				#set ($selIdNegeri = $selNegeri)	
				<option value="$listNegeri.idnegeri">$!listNegeri.namanegeri</option>
			#end
	#else
			#if ($negerimhn != "")
				#foreach ($listNegeri in $senaraiNegeriByPpkUnit)
						#if ($listNegeri.idnegeri == $negerimhn)
							#set ($selected = "selected")	
						<option value="$listNegeri.idnegeri" $selected>$!listNegeri.namanegeri</option>
						#end
				#end	
				<option value="0">Sila Pilih</option>
				#foreach ($listNegeri in $senaraiNegeriByPpkUnit)
					#set ($selIdNegeri = $selNegeri)	
					<option value="$listNegeri.idnegeri">$!listNegeri.namanegeri</option>
				#end
			#else
		
				<option value="0">Sila Pilih</option>
				#foreach ($listNegeri in $senaraiNegeriByPpkUnit)
					#set ($selIdNegeri = $selNegeri)	
					<option value="$listNegeri.idnegeri">$!listNegeri.namanegeri</option>
				#end
			#end
	#end
</select><input type="hidden" name="saizdata" value="$!saizData"></td>
</tr>
<tr>
<td align="right">Daerah :&nbsp;</td>
<td><select name="socDaerahPengesahan" style="width: 300px;" onChange="getAddress()" $setmode $disabledP>
	#if ($selDaerah != "0")
			#foreach ($listDaerah in $selectedDaerah)
				#if ($listDaerah.id == $selDaerah)
					#set ($selected3 = "selected")
				<option value="$listDaerah.id" $selected3>$!listDaerah.nama</option>
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
						<option value="$listDaerah.id" selected>$!listDaerah.nama</option>
						#end
					#end
					<option value="0">Sila Pilih</option>
					#foreach ($listDaerah in $selectedDaerah)
						<option value="$listDaerah.id">$!listDaerah.nama</option>
					#end
			#else
					<option value="0">Sila Pilih</option>
					#foreach ($listDaerah in $selectedDaerah)
						<option value="$listDaerah.id">$!listDaerah.nama</option>
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
<td align="right">Pejabat :&nbsp;</td>
<td><input type="text" size="55" maxlength="46" name="namapejabat" value="$!namapejabat" readonly class="disabled" style="text-transform:uppercase;"></td>
</tr>
<tr>
<td align="right">Alamat :&nbsp;</td>
<td><input type="text" size="55" maxlength="46" name="alamat1" value="$!alamat1" readonly class="disabled" style="text-transform:uppercase;"></td>
</tr>
<tr>
<td></td>
<td><input type="text" size="55" maxlength="46" name="alamat2" value="$!alamat2" readonly class="disabled" style="text-transform:uppercase;"></td>
</tr>
<tr>
<td></td>
<td><input type="text" size="55" maxlength="46" name="alamat3" value="$!alamat3" readonly class="disabled" style="text-transform:uppercase;"></td>
</tr>
<tr>
<td align="right">Poskod : </td>
<td><input type="text" size="5" maxlength="5" name="poskod" value="$!poskod" readonly class="disabled"></td>
</tr>
<tr>
<td align="right">No. Telefon : </td>
<td><input type="text" size="15" maxlength="11" value="$!no_tel" readonly class="disabled"></td>
</tr>
#if ($no_tel_samb != "")
<tr>
<td align="right">No. Telefon (samb) : </td>
<td><input type="text" size="15" maxlength="11" value="$!no_tel_samb" readonly class="disabled"></td>
</tr>
#end
</table>
</fieldset>


<p align="center">
#if ($idStatus == "160" || $idStatus == "21" )
<input type="button" name="cmdHantar" value="Hantar" onClick="javascript:getUnitPPK('$id','$nopermohonanonline')"><!--<input type="button" name="cmdKosongkan" value="Kosongkan" onClick="PengesahanView('4','0','0','0')">-->
#end 
#if ($idStatus == "171" )
<input type="button" name="cmdBorangP" value="Cetak Borang P" onClick="javascript:cetakBorangP('$idpermohonanbaru','$idfail','$idfaillama')">
#end
</p>
    </td>
  </tr>
</table>
</div>
  </div>
</div>
</td>
</tr>
</table>

</fieldset>
<script type="text/javascript">
function HtaamView() {
	//document.${formName}.method="post";
	document.${formName}.mode.value="Htaamview";
	doAjaxCall${formName}('Htaam');
	//document.${formName}.submit();
}

function HAview(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi) {
	//document.${formName}.method="post";
	document.${formName}.mode.value="view_harta_alih";
	doAjaxCall${formName}('harta_alih');
	//document.${formName}.submit();
}

function HtaamViewX() {
	//document.${formName}.method="post";
	document.${formName}.mode.value="HtaamviewX";
	doAjaxCall${formName}('HtaamX');
	//document.${formName}.submit();
}

function PengesahanView() {
	//document.${formName}.method="post";
	doAjaxCall${formName}("pengesahan_permohonan");
	//document.${formName}.submit();
}

function setSelected(tabIdatas,tabIdtengah,tabIdbawah,tabIdtepi)
 {
    document.${formName}.tabIdatas.value = tabIdatas;
    document.${formName}.tabIdtengah.value = tabIdtengah;
    document.${formName}.tabIdbawah.value = tabIdbawah;	
	document.${formName}.tabIdtepi.value = tabIdtepi;	
}

function getUnitPPK(idpermohonan,nopermohonanonline) {
	if (document.${formName}.saizdata.value=="0"){
		input_box=confirm("Sila masukkan ' Harta Tak Alih ' sebelum membuat pengesahan permohonan");
		if (input_box == true) {
			HtaamView();
		}
	}else if (document.${formName}.socNegeriPengesahan.value=="0"){
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
		doAjaxCall${formName}("cetak_surat");
		document.${formName}.idPermohonan.value=idpermohonan;
		document.${formName}.submit();

		PengesahanView();

		}
	}
}

function cetakPengesahan() {
    var url="../servlet/ekptg.report.ppk.PengesahanPermohonanOnline?NoPermohonan="+document.${formName}.nopermohonanonline.value;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	
	PengesahanView();
}

function cetakBorangP(idpermohonanbaru,idfail,idfaillama) {
    var url="../servlet/ekptg.report.ppk.BorangP?idfaillama="+idfaillama+"&idfailbaru="+idfail+"&idpermohonanbaru="+idpermohonanbaru;
    var hWnd=window.open(url,'Cetak2','width=800,height=500, resizable=yes,scrollbars=yes');
}

<!-- SIMATI -->

<!--  -->
function getDaerah(){
	document.${formName}.method="post";
	doAjaxCall${formName}("pengesahan_permohonan");
	document.${formName}.mode.value="selection_daerah";
	document.${formName}.submit();	
}
function getAddress(){
	document.${formName}.method="post";
	doAjaxCall${formName}("pengesahan_permohonan");
	document.${formName}.mode.value="ppkAddressView";
	document.${formName}.submit();	
}
</script>
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:2});
var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:0});
//-->
</script>
</body>

