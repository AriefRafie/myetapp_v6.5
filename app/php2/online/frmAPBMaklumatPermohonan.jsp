<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0000FF
}
.pautan {
	color: #0000FF
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idPemohon" type="hidden" id="idPemohon" value="$idPemohon"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idPengarah" type="hidden" id="idPengarah" value="$idPengarah"/>
  <input name="idPembeliPasir" type="hidden" id="idPembeliPasir" value="$idPembeliPasir"/>
  <input name="idProjek" type="hidden" id="idProjek" value="$idProjek"/>
  <input name="idPakar" type="hidden" id="idPakar" value="$idPakar"/>
  <input name="idKoordinat" type="hidden" id="idKoordinat" value="$idKoordinat"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="actionOnline" type="hidden" id="actionOnline" value="$actionOnline"/>
  <input name="kategori" type="hidden" id="kategori" value="$!pemohon.get("kategoriPemohon")"/>
  <input name="idDokumen" type="hidden" id="idDokumen" value="$!idDokumen"/>
</p>
<body onLoad = $onload >
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/online/frmAPBHeader.jsp") </td>
  </tr>
  #end
  #if ($idFail != '')
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTab(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT PERMOHONAN</li>
          <li onClick="doChangeTab(1);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT PEMBELI PASIR</li>
          <li onClick="doChangeTab(2);" class="TabbedPanelsTab" tabindex="0">SENARAI SEMAK</li>
          <li onClick="doChangeTab(3);" class="TabbedPanelsTab" tabindex="0">LAMPIRAN</li>
          <li onClick="doChangeTab(4);" class="TabbedPanelsTab" tabindex="0">PENGESAHAN PERMOHONAN</li>
        </ul>
     	<div class="TabbedPanelsContentGroup">
        	<div class="TabbedPanelsContent"> #if ($selectedTabUpper == '0')
            #parse("app/php2/online/frmAPBMaklumatPermohonanPermohonan.jsp")
            #end </div>
          	<div class="TabbedPanelsContent"> #if ($selectedTabUpper == '1')
            #parse("app/php2/online/frmAPBMaklumatPermohonanPembeliPasir.jsp")
            #end </div>
  			<div class="TabbedPanelsContent"> #if ($selectedTabUpper == '2')
			#parse("app/php2/online/frmAPBSenaraiSemakOnline.jsp") </td>
			#end</div>
			<div class="TabbedPanelsContent"> #if ($selectedTabUpper == '3')
  			#parse("app/php2/online/frmAPBMaklumatLampiranOnline.jsp")
  			#end</div>
		<div class="TabbedPanelsContent">#if ($selectedTabUpper == '4')
					           	
<fieldset>

<legend>PENGESAHAN PERMOHONAN</legend>
<!--
<font color="#FF0000" size="2">
<i>* Sila cetak Borang A dan Pengesahan Permohonan untuk dibawa ke Unit Pembahagian Pusaka Kecil.
<br>   * Permohonan akan dibatalkan sekiranya terdapat permohonan lain yang lengkap dihantar ke Unit Pembahagian Pusaka Kecil.    
    </i></font>
<br>
<br>
<br>-->

#if ($idStatus != "150")
#if($kemaskini_pejabat != "yes")
#set($disabledDropdown = "disabled")
#else
#set($disabledDropdown = "")
#end
#end
<table width="100%" border="0">
<tr>
<td width="5%"></td>
<td width="20%" >Negeri</td>
<td width="1%">:</td>
<td width="74%"><strong> #if($disabledDropdown == 'disabled')
#set($nama_negeri="") 
 #foreach($listneg in $senaraiNegeriByPpkUnit) 
                                        #if($negerimhn == $listneg.idnegeri)
                                        
                                        #set($nama_negeri="$listneg.namanegeri")
                                        
                                       
                                        #end
                                        #end <font  style="text-transform:uppercase;">$!nama_negeri</font>
                                        
                                <input type="hidden" size="50" maxlength="46" name="namanegeri" value="$!nama_negeri" readonly class="disabled" style="text-transform:uppercase;">
                  
                                <input type="hidden" id="socNegeriPengesahan"  name="socNegeriPengesahan" value="$!negerimhn" >                   

#else


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

</select>
#end

<input type="hidden" name="saizdata" value="$!saizData">
</strong></td>
</tr>
<tr>
<td></td>
<td >Daerah</td>
<td>:</td>
<td><strong> #if($disabledDropdown == 'disabled')
#set($nama_daerah="") 
 #foreach($listDaerah in $selectedDaerah) 
                                       
                                        
                                        #if($!daerahmhn == $listDaerah.id)
                                        
                                        #set($nama_daerah="$listDaerah.nama")
                                        
                                       
                                        #end
                                        #end <font  style="text-transform:uppercase;">$!nama_daerah</font>
                                <input type="hidden" size="50" maxlength="46" name="nama_daerah" value="$!nama_daerah" readonly class="disabled" style="text-transform:uppercase;">
                                <input type="hidden" id="socDaerahPengesahan"  name="socDaerahPengesahan" value="$!daerahmhn" >    
                                     

#else

<select name="socDaerahPengesahan" style="width: 300px;" onChange="getAddress()" $disabledDropdown>
  
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

</select>

#end
</strong></td>
</tr>
#if ($selDaerah != "0" || $daerahmhn != "")
	#foreach ($data in $selectedPpkAddress)
		#set ($namapejabat = $data.namapejabat)
		#set ($alamat1 = $data.alamatOne)
		#set ($alamat2 = $data.alamatTwo)
		#set ($alamat3 = $data.alamatThree)
		#set ($poskod = $data.poskod)
		#set ($no_tel = $data.notel)
		#set ($no_fax = $data.nofax)
		#set ($no_tel_samb = $data.notel_sambungan)
	#end
#end
<tr>
<td></td>
<td >Pejabat</td>
<td>:</td>
<td><span style="font-weight: bold">
  <input type="hidden" size="50" maxlength="46" name="namapejabat" value="$!namapejabat" readonly class="disabled" style="text-transform:uppercase;">
  <font  style="text-transform:uppercase;">$!namapejabat</font>
</span></td>
</tr>
<tr>
<td></td>
<td >Alamat</td>
<td>:</td>
<td><span style="font-weight: bold">
  <input type="hidden" size="50" maxlength="46" name="alamat1" value="$!alamat1" readonly class="disabled" style="text-transform:uppercase;">
  <font  style="text-transform:uppercase;">$!alamat1</font>
</span></td>
</tr>
<tr>
<td></td>
<td></td>
<td></td>
<td><span style="font-weight: bold">
  <input type="hidden" size="50" maxlength="46" name="alamat2" value="$!alamat2" readonly class="disabled" style="text-transform:uppercase;">
  <font  style="text-transform:uppercase;">$!alamat2</font>
</span></td>
</tr>
<tr>
<td></td>
<td></td>
<td></td>
<td><span style="font-weight: bold">
  <input type="hidden" size="50" maxlength="46" name="alamat3" value="$!alamat3" readonly class="disabled" style="text-transform:uppercase;">
  <font  style="text-transform:uppercase;">$!alamat3</font>
</span></td>
</tr>
<tr>
<td></td>
<td >Poskod</td>
<td>:</td>
<td><strong>
  <input type="hidden" size="5" maxlength="5" name="poskod" value="$!poskod" readonly class="disabled" >
  <font  style="text-transform:uppercase;">$!poskod</font>
</strong></td>
</tr>
<tr>
<td></td>
<td >No. Telefon</td>
<td>:</td>
<td><strong>
  <input type="hidden" size="12" maxlength="11" value="$!no_tel" readonly class="disabled" >
  <font  style="text-transform:uppercase;">$!no_tel</font></strong></td>
</tr>
#if ($no_tel_samb != "")
<tr>
<td></td>
<td >No. Telefon (samb)</td>
<td>:</td>
<td><strong>
  <input type="hidden" size="12" maxlength="11" value="$!no_tel_samb" readonly class="disabled" >
  <font  style="text-transform:uppercase;">$!no_tel_samb</font>
</strong></td>
</tr>
#end
#if ($no_fax != "")
<tr>
<td></td>
<td >No. Fax</td>
<td>:</td>
<td><strong>
  <input type="hidden" size="12" maxlength="11" value="$!no_fax" readonly class="disabled" >
  <font  style="text-transform:uppercase;">$!no_fax</font></strong></td>
</tr>
#end

			#foreach($View in $View_pengesahan_pemohonan)
        #set ($namaPemohon = $View.namaPemohon)
        #set ($noKpBaruPemohon1 = $View.noKpBaruPemohon1)
        #set ($noKpBaruPemohon2 = $View.noKpBaruPemohon2)
        #set ($noKpBaruPemohon3 = $View.noKpBaruPemohon3)
        #set ($jenis_pemohon = $View.jenis_pemohon)
        #set ($noKpBaruPemohon = $View.noKpBaruPemohon)
    #end
    
    #if($!skrin_deraf == "yes")
	<tr><td> 
    		<td width="1%" valign="top"></td>
    		#if ($idStatus == "150")
    		<td width="3%"><input type="checkbox" name='namecb1' id='namecb1'></td>
    		#else
    		<td width="3%"><input type="checkbox" name='namecb1' id='namecb1' checked disabled>
    		</td>
    		#end
    		#if ($jenis_pemohon == "2")
      	<td width="89%">Saya $!namaPemohon MyID $!noKpBaruPemohon1 $!noKpBaruPemohon2 $!noKpBaruPemohon3 dengan ini mengakui bahawa maklumat yang diberikan dalam borang ini adalah benar, betul dan lengkap.</td>
	
				#elseif($jenis_pemohon == "1")
      	<td width="89%">Kami $!namaPemohon dengan ini mengakui bahawa maklumat yang diberikan dalam borang ini adalah benar, betul dan lengkap.</td>
	
	</td>#end
	</tr>#end

		<td></td>
		<td ></td>
		<td valign="top">
           	#if ($idStatus == '')<input type="checkbox" name="pengesahan" id="pengesahan">#end
           	#if ($idStatus != '')<input type="checkbox" name="pengesahan" id="pengesahan" $disabled checked>#end</td>
           	<td>Saya, $!pemohon.get("namaPemohon"), MyID $!pemohon.get("noPengenalan") dengan ini maklumat yang diberikan dalam borang ini adalah benar, betul dan lengkap.</td>
   <tr>
					<td></td>
					<td ></td>
					<td></td>
					<td>
						<label align="left" valign="top"> 
						<b><font color="BLUE" size="2"><span class="blink">Sekiranya permohonan telah dihantar, pemohon sudah tidak boleh mengemaskini permohonan.
						<br> Jika terdapat sebarang maklumat yang perlu ditambah, sila mengemaskini permohonan terlebih dahulu sebelum menghantar permohonan.</span></font></b>
						</label></td>
	 </tr>
	 
           	<tr>
           	<td colspan=2 align="center">
           	#if ($idStatus == '')
           		<input type="button" name="cdmCetak" id="cdmCetakBorang" value="Cetak Borang Permohonan" onClick="javascript:cetakBorangPermohonan('$idPermohonan')"/>
           		<input type="button" name="cmdHantar" id="cmdHantar" value="Hantar &amp; Emel" onClick="doHantarEmel()"/>
            	<input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onClick="doHapus()"/>
            #else
            #if ($idStatus !='')
            	<input type="button" name="cdmCetak" id="cdmCetakBorang" value="Cetak Borang Permohonan" onClick="javascript:cetakBorangPermohonan('$idPermohonan')"/>
           		<input type="button" name="cdmCetak" id="cdmCetakPengesahan" value="Cetak Pengesahan Permohonan" onClick="javascript:cetakPengesahanPermohonan('$idPermohonan')"/>
            #end
            #end
            </td>
  </tr>
  #end
  #end
	</table>
</fieldset>

<!--<div id="calculateTotalPercentPengarah_result"></div>
<fieldset id="tableReport" style="display:;"-->
<!--<legend><strong>SENARAI LAPORAN</strong></legend>  -->
<!--<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakPengesahanPermohonan('$idPermohonan')"> Pengesahan Permohonan </a></td>
  </tr>
</table>
</fieldset>-->

<script type="text/javascript">
#if ($idFail != '')
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
#end
</script>
<script>
function doChangeTab(tabId) {
	document.${formName}.actionOnline.value = "seterusnya";
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = tabId;
	doAjaxCall${formName}("");
}
function doBacklist() {
	document.${formName}.actionOnline.value = "";
	document.${formName}.submit();
}
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function validateCurrency(elmnt,content,content2) {
	content = content.replace(/,/g,'');
	content2 = content2.replace(/,/g,'');
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content != ""){
		var num = content * 1;
		elmnt.value = num.toFixed(2);
		return;
	} else {
		elmnt.value ="";
		return;
	}
}
function seterusnya(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSeterusnya";
	doAjaxCall${formName}("");
}
</script>
<!-- PEMBELI PASIR -->
<script>
function tambahPembeliPasir() {
	document.${formName}.mode.value = "newPembeliPasir";
	doAjaxCall${formName}("");
}
function batalPembeliPasir() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}

function simpanPembeliPasir(){
	
	if(document.${formName}.txtNamaPembeliPasir.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.txtNamaPembeliPasir.focus(); 
		return; 
	}
	if(document.${formName}.txtAlamat1PembeliPasir.value == ""){
		alert('Sila masukkan Alamat.');
  		document.${formName}.txtAlamat1PembeliPasir.focus(); 
		return; 
	}
	if(document.${formName}.txtPoskodPembeliPasir.value == ""){
		alert('Sila masukkan Poskod.');
  		document.${formName}.txtPoskodPembeliPasir.focus(); 
		return; 
	}
	if(document.${formName}.socNegeriPembeliPasir.value == ""){
		alert('Sila pilih Negeri.');
  		document.${formName}.socNegeriPembeliPasir.focus(); 
		return; 
	}
	if(document.${formName}.socBandarPembeliPasir.value == ""){
		alert('Sila pilih Bandar.');
  		document.${formName}.socBandarPembeliPasir.focus(); 
		return; 
	}
	if(document.${formName}.txtNoTelPembeliPasir.value == ""){
		alert('Sila masukkan No. Telefon.');
  		document.${formName}.txtNoTelPembeliPasir.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "newPembeliPasir";
		return;
	}
	
	document.${formName}.mode.value = "newPembeliPasir";
	document.${formName}.hitButton.value = "doSimpanPembeliPasir";
	document.${formName}.submit();
}
function batalPembeliPasir(){
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function kemaskiniPembeliPasir(){
	document.${formName}.mode.value = "updatePembeliPasir";
	doAjaxCall${formName}("");
}
function simpanKemaskiniPembeliPasir(){

	if(document.${formName}.txtNamaPembeliPasir.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.txtNamaPembeliPasir.focus(); 
		return; 
	}
	if(document.${formName}.txtAlamat1PembeliPasir.value == ""){
		alert('Sila masukkan Alamat.');
  		document.${formName}.txtAlamat1PembeliPasir.focus(); 
		return; 
	}
	if(document.${formName}.txtPoskodPembeliPasir.value == ""){
		alert('Sila masukkan Poskod.');
  		document.${formName}.txtPoskodPembeliPasir.focus(); 
		return; 
	}
	if(document.${formName}.socNegeriPembeliPasir.value == ""){
		alert('Sila pilih Negeri.');
  		document.${formName}.socNegeriPembeliPasir.focus(); 
		return; 
	}
	if(document.${formName}.socBandarPembeliPasir.value == ""){
		alert('Sila pilih Bandar.');
  		document.${formName}.socBandarPembeliPasir.focus(); 
		return; 
	}
	if(document.${formName}.txtNoTelPembeliPasir.value == ""){
		alert('Sila masukkan No. Telefon.');
  		document.${formName}.txtNoTelPembeliPasir.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "updatePembeliPasir";
		return;
	}
	
	document.${formName}.mode.value = "viewPembeliPasir";
	document.${formName}.hitButton.value = "doSimpanKemaskiniPembeliPasir";
	doAjaxCall${formName}("");
}
function batalKemaskiniPembeliPasir(){
	document.${formName}.mode.value = "viewPembeliPasir";
	doAjaxCall${formName}("");
}
function hapusPembeliPasir(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "viewPembeliPasir";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHapusPembeliPasir";
	doAjaxCall${formName}("");
}
function paparPembeliPasir(idPembeliPasir){
	document.${formName}.idPembeliPasir.value = idPembeliPasir;
	document.${formName}.mode.value = "viewPembeliPasir";
	doAjaxCall${formName}("");
}
</script>
<!-- PEMOHON -->
<script>
function kemaskiniPemohon() {
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function batalKemaskiniPemohon() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
</script>
<!-- PENGARAH -->
<script>
function tambahPengarah() {
	document.${formName}.mode.value = "newPengarah";
	doAjaxCall${formName}("");
}
function batalPengarah() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}

function checkPercentage(){;
	if(parseInt(document.${formName}.txtSaham.value) > 100){
		alert('Sila masukkan nilai peratusan saham dengan betul.');
		document.${formName}.txtSaham.value = ""; 
  		document.${formName}.txtSaham.focus(); 
		return; 
	}
}
function simpanPengarah(){
	
	if(document.${formName}.socWarganegara.value == ""){
		alert('Sila pilih Warganegara.');
  		document.${formName}.socWarganegara.focus(); 
		return; 
	}
	if(document.${formName}.txtNamaPengarah.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.txtNamaPengarah.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "newPengarah";
		return;
	}
	
	document.${formName}.mode.value = "newPengarah";
	document.${formName}.hitButton.value = "doSimpanPengarah";
	doAjaxCall${formName}("");
}
function batalPengarah(){
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function kemaskiniPengarah(){
	document.${formName}.mode.value = "updatePengarah";
	doAjaxCall${formName}("");
}
function simpanKemaskiniPengarah(){

	if(document.${formName}.socWarganegara.value == ""){
		alert('Sila pilih Warganegara.');
  		document.${formName}.socWarganegara.focus(); 
		return; 
	}
	if(document.${formName}.txtNamaPengarah.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.txtNamaPengarah.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "updatePengarah";
		return;
	}
	
	document.${formName}.mode.value = "newPengarah";
	document.${formName}.hitButton.value = "doSimpanKemaskiniPengarah";
	doAjaxCall${formName}("");
}
function batalKemaskiniPengarah(){
	document.${formName}.mode.value = "viewPengarah";
	doAjaxCall${formName}("");
}
function hapusPengarah(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "viewPengarah";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHapusPengarah";
	doAjaxCall${formName}("");
}
function paparPengarah(idPengarah){
	document.${formName}.idPengarah.value = idPengarah;
	document.${formName}.mode.value = "viewPengarah";
	doAjaxCall${formName}("");
}
function calculateTotalPercentPengarah() {
	url = "../servlet/ekptg.view.php2.FrmAPBServlet?command=calculateTotalPercentPengarah";
	actionName = "calculateTotalPercentPengarah";
	target = "calculateTotalPercentPengarah_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
}
function popupMsg(){
	alert('Jumlah peratusan saham yang dimasukkan telah melebihi 100%');
	document.${formName}.txtSaham.value = "";
}
</script>
<!-- PERMOHONAN -->
<script>
function kemaskiniPermohonan() {
	document.${formName}.actionOnline.value = "seterusnya";
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function batalKemaskiniPermohonan() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function simpanKemaskiniPermohonan() {
	
	if(document.${formName}.socKaitanTujuan.value == ""){
		alert('Sila pilih Kaitan Tujuan.');
  		document.${formName}.socKaitanTujuan.focus(); 
		return; 
	}
	if(document.${formName}.txtTujuanPengambilan.value == ""){
		alert('Sila masukkan Tujuan.');
  		document.${formName}.txtTujuanPengambilan.focus(); 
		return; 
	}
	if(document.${formName}.socTempoh.value == "SILA PILIH"){
		alert('Sila pilih Tempoh Lesen Dipohon.');
  		document.${formName}.socTempoh.focus(); 
		return; 
	}
	if(document.${formName}.txtRingkasanPengalaman.value == ""){
		alert('Sila masukkan Ringkasan Pengalaman Pemohon.');
  		document.${formName}.txtRingkasanPengalaman.focus(); 
		return; 
	}
	//if(document.${formName}.txtModalBenar.value == ""){
//			alert('Sila masukkan Modal Dibenarkan.');
//			document.${formName}.txtModalBenar.focus(); 
//			return; 
//		}
//	if(document.${formName}.txtModalJelas.value == ""){
//		alert('Sila masukkan Modal Jelas.');
//		document.${formName}.txtModalJelas.focus(); 
//		return; 
//	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}

	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniPermohonan";
	doAjaxCall${formName}("");
}
function cekTarikhTerima(elmnt) {
//CHECK DATE   
	var str1  = document.${formName}.tarikhTerima.value;		   
	var dt1   = parseInt(str1.substring(0,2),10);
	var mon1  = parseInt(str1.substring(3,5),10)-1;
	var yr1   = parseInt(str1.substring(6,10),10);
	var tarikhTerima = new Date(yr1, mon1, dt1);
	
	var currentDate = new Date();
	
	if (tarikhTerima > currentDate){
		alert('Tarikh Terima tidak boleh melebihi dari tarikh hari ini.');
  		elmnt.value ="";
		document.${formName}.tarikhTerima.focus(); 
		return;
	}
}
function cekTarikhSurat(elmnt) {
//CHECK DATE   
	//CHECK DATE   
	var str1  = document.${formName}.tarikhTerima.value;		   
	var dt1   = parseInt(str1.substring(0,2),10);
	var mon1  = parseInt(str1.substring(3,5),10)-1;
	var yr1   = parseInt(str1.substring(6,10),10);
	var tarikhTerima = new Date(yr1, mon1, dt1);
	
	var str2  =  document.${formName}.tarikhSurat.value;		   
	var dt2   = parseInt(str2.substring(0,2),10);
	var mon2  = parseInt(str2.substring(3,5),10)-1;
	var yr2   = parseInt(str2.substring(6,10),10);
	var tarikhSurat = new Date(yr2, mon2, dt2);
	
	var currentDate = new Date();
	
	if (tarikhSurat > currentDate){
		alert('Tarikh Surat tidak boleh melebihi dari tarikh hari ini.');
		elmnt.value ="";
  		document.${formName}.tarikhSurat.focus(); 
		return;
	}
	if (tarikhSurat > tarikhTerima){
		alert('Tarikh Surat tidak boleh melebihi dari Tarikh Terima.');
		elmnt.value ="";
  		document.${formName}.tarikhSurat.focus(); 
		return;
	}
}
</script>
<!-- PROJEK -->
<script>
function tambahProjek() {
	document.${formName}.mode.value = "newProjek";
	doAjaxCall${formName}("");
}
function batalProjek() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function simpanProjek(){
	
	if(document.${formName}.txtNamaProjek.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.txtNamaProjek.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "newProjek";
		return;
	}
	
	document.${formName}.mode.value = "newProjek";
	document.${formName}.hitButton.value = "doSimpanProjek";
	doAjaxCall${formName}("");
}
function kemaskiniProjek(){
	document.${formName}.mode.value = "updateProjek";
	doAjaxCall${formName}("");
}
function simpanKemaskiniProjek(){

	if(document.${formName}.txtNamaProjek.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.txtNamaProjek.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "updateProjek";
		return;
	}
	
	document.${formName}.mode.value = "viewProjek";
	document.${formName}.hitButton.value = "doSimpanKemaskiniProjek";
	doAjaxCall${formName}("");
}
function batalKemaskiniProjek(){
	document.${formName}.mode.value = "viewProjek";
	doAjaxCall${formName}("");
}
function hapusProjek(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "viewProjek";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHapusProjek";
	doAjaxCall${formName}("");
}
function paparProjek(idProjek){
	document.${formName}.idProjek.value = idProjek;
	document.${formName}.mode.value = "viewProjek";
	doAjaxCall${formName}("");
}
</script>
<!-- PAKAR -->
<script>
function tambahPakar() {
	document.${formName}.mode.value = "newPakar";
	doAjaxCall${formName}("");
}
function batalPakar() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function simpanPakar(){
	
	if(document.${formName}.txtNamaPakar.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.txtNamaPakar.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "newPakar";
		return;
	}
	
	document.${formName}.mode.value = "newPakar";
	document.${formName}.hitButton.value = "doSimpanPakar";
	doAjaxCall${formName}("");
}
function kemaskiniPakar(){
	document.${formName}.mode.value = "updatePakar";
	doAjaxCall${formName}("");
}
function simpanKemaskiniPakar(){

	if(document.${formName}.txtNamaPakar.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.txtNamaPakar.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "updatePakar";
		return;
	}
	
	document.${formName}.mode.value = "viewPakar";
	document.${formName}.hitButton.value = "doSimpanKemaskiniPakar";
	doAjaxCall${formName}("");
}
function batalKemaskiniPakar(){
	document.${formName}.mode.value = "viewPakar";
	doAjaxCall${formName}("");
}
function hapusPakar(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "viewPakar";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHapusPakar";
	doAjaxCall${formName}("");
}
function paparPakar(idPakar){
	document.${formName}.idPakar.value = idPakar;
	document.${formName}.mode.value = "viewPakar";
	doAjaxCall${formName}("");
}
</script>
<!-- KOORDINAT -->
<script>
function tambahKoordinat() {
	document.${formName}.mode.value = "newKoordinat";
	doAjaxCall${formName}("");
}
function batalKoordinat() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function simpanKoordinat(){
	
	if(document.${formName}.txtLabelTitik.value == ""){
		alert('Sila masukkan Label Koordinat.');
  		document.${formName}.txtLabelTitik.focus(); 
		return; 
	}
	if(document.${formName}.txtDarjahU.value == ""){
		alert('Sila masukkan Darjah U.');
  		document.${formName}.txtDarjahU.focus(); 
		return; 
	}
	if(document.${formName}.txtMinitU.value == ""){
		alert('Sila masukkan Minit U.');
  		document.${formName}.txtMinitU.focus(); 
		return; 
	}
	if(document.${formName}.txtSaatU.value == ""){
		alert('Sila masukkan Saat U.');
  		document.${formName}.txtSaatU.focus(); 
		return; 
	}
	if(document.${formName}.txtDarjahT.value == ""){
		alert('Sila masukkan Darjah T.');
  		document.${formName}.txtDarjahT.focus(); 
		return; 
	}
	if(document.${formName}.txtMinitT.value == ""){
		alert('Sila masukkan Minit T.');
  		document.${formName}.txtMinitT.focus(); 
		return; 
	}
	if(document.${formName}.txtSaatT.value == ""){
		alert('Sila masukkan Saat T.');
  		document.${formName}.txtSaatT.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "newKoordinat";
		return;
	}
	
	document.${formName}.mode.value = "newKoordinat";
	document.${formName}.hitButton.value = "doSimpanKoordinat";
	doAjaxCall${formName}("");
}
function kemaskiniKoordinat(){
	document.${formName}.mode.value = "updateKoordinat";
	doAjaxCall${formName}("");
}
function simpanKemaskiniKoordinat(){

	if(document.${formName}.txtLabelTitik.value == ""){
		alert('Sila masukkan Label Koordinat.');
  		document.${formName}.txtLabelTitik.focus(); 
		return; 
	}
	if(document.${formName}.txtDarjahU.value == ""){
		alert('Sila masukkan Darjah U.');
  		document.${formName}.txtDarjahU.focus(); 
		return; 
	}
	if(document.${formName}.txtMinitU.value == ""){
		alert('Sila masukkan Minit U.');
  		document.${formName}.txtMinitU.focus(); 
		return; 
	}
	if(document.${formName}.txtSaatU.value == ""){
		alert('Sila masukkan Saat U.');
  		document.${formName}.txtSaatU.focus(); 
		return; 
	}
	if(document.${formName}.txtDarjahT.value == ""){
		alert('Sila masukkan Darjah T.');
  		document.${formName}.txtDarjahT.focus(); 
		return; 
	}
	if(document.${formName}.txtMinitT.value == ""){
		alert('Sila masukkan Minit T.');
  		document.${formName}.txtMinitT.focus(); 
		return; 
	}
	if(document.${formName}.txtSaatT.value == ""){
		alert('Sila masukkan Saat T.');
  		document.${formName}.txtSaatT.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "updateKoordinat";
		return;
	}
	
	document.${formName}.mode.value = "viewKoordinat";
	document.${formName}.hitButton.value = "doSimpanKemaskiniKoordinat";
	doAjaxCall${formName}("");
}
function batalKemaskiniKoordinat(){
	document.${formName}.mode.value = "viewKoordinat";
	doAjaxCall${formName}("");
}
function hapusKoordinat(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "viewKoordinat";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHapusKoordinat";
	doAjaxCall${formName}("");
}
function paparKoordinat(idKoordinat){
	document.${formName}.idKoordinat.value = idKoordinat;
	document.${formName}.mode.value = "viewKoordinat";
	doAjaxCall${formName}("");
}
function textCounter(field, countfield, maxlimit) {
   if (field.value.length > maxlimit) // if too long...trim it!
		 field.value = field.value.substring(0, maxlimit);
    // otherwise, update 'Baki Aksara' counter
	else
	 countfield.value = maxlimit - field.value.length;
}
function doHantarEmel(){
	if(pengesahan.checked != true){
		alert('Sila tanda pada checkbox untuk teruskan permohonan. ');
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHantarEmel";
	document.${formName}.submit();
}
function doHapus(){
		
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHapus";
	document.${formName}.actionOnline.value = "";
	document.${formName}.submit();
}
</script>
<script>
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
//Copy dari Sewa
function cetakBorangPermohonan(idPermohonan) {
	var url = "../servlet/ekptg.report.php2.online.PYWBorangPermohonan?ID_PERMOHONAN="+idPermohonan;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
//YG ASAL
function cetakPengesahanPermohonan(idPermohonan) {
	var url = "../servlet/ekptg.report.php2.online.APBPengesahanPermohonanOnline?ID_PERMOHONAN="+idPermohonan;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>

<script>
<!-- MAKLUMAT LAMPIRAN -->
function daftarLampiran() {
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmAPBOnlineSenaraiFailView";
	document.${formName}.method="POST";
	document.${formName}.actionOnline = "seterusnya";
	document.${formName}.mode.value = "view";	
	document.${formName}.flagPopup.value = "openPopupLampiran";
	document.${formName}.mode.value = "new";
	document.${formName}.submit();
}

<!-- SENARAI SEMAK -->
function doSimpanKemaskiniSenaraiSemak() {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniSenaraiSemak";
	document.${formName}.submit();
}
function doBatalKemaskini() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}

//LAMPIRAN
</script>
$javascriptLampiran
