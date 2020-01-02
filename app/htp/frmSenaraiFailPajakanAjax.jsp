<br/>
<fieldset> 
<legend>Carian</legend>
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
        <td height="25" align="right"><strong>No. Fail : &nbsp;&nbsp;</strong></td>
        <td><input name="NoFail" type="text" size="44" maxlength="400" value="$carianNoFail" onkeyup="this.value=this.value.toUpperCase();" /></td>
      </tr>
      <tr>
        <td height="25" align="right"><strong>Nama Fail : &nbsp;&nbsp;</strong></td>
        <td><input name="NamaFail" type="text" size="44" maxlength="300" value="$carian" onkeyup="this.value=this.value.toUpperCase();"></td>
      </tr>
      <tr>
        <td height="25"  align="right"><strong>Negeri : &nbsp;&nbsp;</strong></td>
        <td>$selectNegeri</td>
      </tr>
      <tr style="display:none">
        <td  align="right"><strong>Status : &nbsp;&nbsp;</strong></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td></td>
        <td><input class="stylobutton" name="cari" value="Cari " type="button" onclick="javascript:Carian()">
        <input value="Kosongkan" onclick="javascript:Cancel()" type="button" class="stylobutton"></td>
      </tr>
    </tbody>
  </table>
</fieldset>
<fieldset>
<legend>Senarai Fail</legend>
	<input class="stylobutton" name="add" value="Tambah" type="button" onclick="javascript:TambahFail()">
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr class="table_header">
        <td width="5%"><b>No</b></td>
        <td width="23%"><b>No Fail</b></td>
        <td width="17%"><b>Negeri</b></td>
        <td width="32%"><b>Nama Fail</b></td>
        <td width="23%"><b>Status</b></td>
      </tr>	
      #set ($count = 0)
      #foreach ( $fail in $Senaraifail )
      	#set ($count = $count+1)
          #set( $i = $velocityCount )
          #if ( ($i % 2) != 1 )
               #set( $row = "row2" )
          #else
               #set( $row = "row1" )
          #end
      <tr>
        <td class="$row" scope="row">$fail.bil</td>
        <td class="$row"><a href="javascript:Details('view','$fail.idPermohonan', '$fail.idFail', '$fail.noFail')" class="style1">$fail.noFail</a></td>
        <td class="$row">$fail.negeri</td>
        <td class="$row">$fail.tajuk</td>
        <td class="$row">$fail.keterangan</td>
      </tr>
      #end
      #if ($count == 0)
      <tr> 
        <td colspan="5" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
      </tr>
      #end
    </tbody>
  </table>
  
<input type="hidden" name="idPermohonan">
<input type="hidden" name="idFail">
<input type="hidden" name="noFail">
  
</fieldset>




<script language="javascript" type="text/javascript">

<!-- start script frmSenaraiFailPajakanAjax.jsp  -->

function Cancel() {
	document.${formName}.reset();
	document.${formName}.NamaFail.value = "";
	document.${formName}.NoFail.value = "";
	document.${formName}.socNegeri.value = "";
	document.${formName}.NoFail.focus();
}

function TambahFail() {
	doAjaxCall${formName}('FailBaru');
}

function Carian(){
	doAjaxCall${formName}('Carian');
}

function Details(mode,idPermohonan, idFail, noFail) {
	doAjaxCall${formName}('FailBaru','mode='+mode+'&idPermohonan='+idPermohonan+'&idFail='+idFail+'&noFail='+noFail);
}

<!-- end of script -->

<!-- start script frmPajakanSemakanAjax.jsp -->


function fPSA_Kembali() {
	doAjaxCall${formName}('');
}

function fPSA_Seterusnya() {
	doAjaxCall${formName}('Maklumat','mode=tanahview');
}

function fPSA_Kemaskini() {
	doAjaxCall${formName}('FailBaru','mode=kemaskini');
}

function fPSA_Batal() {
	var mode;
	
	if(document.${formName}.idPermohonan.value == ""){
		mode = "baru";
	}
	else{
		mode = "view";
	}
	doAjaxCall${formName}('FailBaru','mode='+mode);
}

function fPSA_Simpan() {
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih " Negeri " terlebih dahulu.');
  		document.${formName}.socNegeri.focus(); 
		return; 
	}
	if(document.${formName}.socKementerian.value == ""){
		alert('Sila pilih " Kementerian " terlebih dahulu.');
  		document.${formName}.socKementerian.focus(); 
		return; 
	}
	if(document.${formName}.socAgensi.value == ""){
		alert('Sila pilih " Agensi " terlebih dahulu.');
  		document.${formName}.socAgensi.focus(); 
		return; 
	}
	if(document.${formName}.socJenisTanah.value == ""){
		alert('Sila pilih " Jenis Tanah " terlebih dahulu.');
  		document.${formName}.socJenisTanah.focus(); 
		return; 
	}
	if(document.${formName}.txtTajuk.value == ""){
		alert('Sila masukkan " Tajuk " terlebih dahulu.');
  		document.${formName}.txtTajuk.focus(); 
		return; 
	}
	if(document.${formName}.txtNoFailKJP.value == ""){
		alert('Sila masukkan " No. Fail KJP " terlebih dahulu.');
  		document.${formName}.txtNoFailKJP.focus(); 
		return; 
	}
	if(document.${formName}.txtNoFailLain.value == ""){
		alert('Sila masukkan " No. Fail Lain " terlebih dahulu.');
  		document.${formName}.txtNoFailLain.focus(); 
		return; 
	}
	if(document.${formName}.txdTarikhSurKJP.value == ""){
		alert('Sila masukkan " Tarikh Surat KJP " terlebih dahulu.');
  		document.${formName}.txdTarikhSurKJP.focus(); 
		return; 
	}
	if(document.${formName}.txdTarikhPermohonan.value == ""){
		alert('Sila masukkan " Tarikh Permohonan " terlebih dahulu.');
  		document.${formName}.txdTarikhPermohonan.focus(); 
		return; 
	}
	if ( !window.confirm("Anda Pasti?") ) return;
	doAjaxCall${formName}('FailBaru','mode=simpan');
}


function doChangeKementerian() {
 	doAjaxCall${formName}("doChangeKementerian");
}
 
function fPSA_seterusnyaPemohon(idHakmilikurusan){
	doAjaxCall${formName}('Pemohon','mode=pemohonview&idHakmilikurusan='+idHakmilikurusan);
}

function fPSA_seterusnyaPemohonBaru(idHakmilik){
	doAjaxCall${formName}('Pemohon','mode=pemohonview&idHakmilik='+idHakmilik);
}

function fPSA_cancel() {
	document.${formName}.reset();
	document.${formName}.socJenishakmilik.value = "";
	document.${formName}.socMukim.value = "";
	document.${formName}.socDaerah.value = "";
	document.${formName}.txtNoHakmilik.value = "";
	document.${formName}.socDaerah.focus();
}

function fPSA_Hakmilik(){	
	if(document.${formName}.idPermohonan.value == ""){
		document.${formName}.tabId.value = '0';
	}
	
	doAjaxCall${formName}('Hakmilik','mode=');	
}

function fPSA_HakmilikSearch(){
	if(document.${formName}.idPermohonan.value == ""){
		document.${formName}.tabId.value = '0';
	}
	doAjaxCall${formName}('Hakmilik','mode=');
}

function doChangeDaerah() {
 	doAjaxCall${formName}("doChangeDaerah");
}

<!-- end of script -->


<!-- script frmPajakanMaklumatAjax.jsp -->

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


function Kembali() {
	doAjaxCall${formName}("");
}

/*pemohon javascript controller*/
function KemaskiniPemohon() {
	doAjaxCall${formName}("Pemohon",'mode=pemohonkemaskini');
}

function BatalPemohon() {
	doAjaxCall${formName}("Pemohon",'pemohonview');
}

function SimpanPemohon() {
	if(document.${formName}.txtNamaPemohon.value == ""){
		alert('Sila masukkan " Nama / Syarikat " terlebih dahulu.');
  		document.${formName}.txtNamaPemohon.focus(); 
		return; 
	}
	if(document.${formName}.txtNoPemohon.value == ""){
		alert('Sila masukkan " No. KP / Syarikat " terlebih dahulu.');
  		document.${formName}.v.focus(); 
		return; 
	}
	if(document.${formName}.txtAlamat1.value == ""){
		alert('Sila masukkan " Alamat " terlebih dahulu.');
  		document.${formName}.txtAlamat1.focus(); 
		return; 
	}
	if(document.${formName}.txtPoskod.value == ""){
		alert('Sila masukkan " Poskod " terlebih dahulu.');
  		document.${formName}.txtPoskod.focus(); 
		return; 
	}
	if(document.${formName}.socADaerah.value == ""){
		alert('Sila pilih " Daerah " terlebih dahulu.');
  		document.${formName}.socADaerah.focus(); 
		return; 
	}
	if(document.${formName}.socANegeri.value == ""){
		alert('Sila pilih " Negeri " terlebih dahulu.');
  		document.${formName}.socANegeri.focus(); 
		return; 
	}
	if ( !window.confirm("Anda Pasti?") ) return;

	doAjaxCall${formName}("Pemohon",'mode=pemohonsimpan');
}

function PemohonView() {
	doAjaxCall${formName}("Pemohon",'mode=pemohonview');
}

function doChangeNegeri() {
 	doAjaxCall${formName}("doChangeNegeri");
}

/*pemohon javascript controller end*/

/*ulasan javascript controller start*/
function KemaskiniUlasanKJP() {
	doAjaxCall${formName}("Ulasan",'mode=ulasankjpkemaskini');	
}

function BatalUlasanKJP() {
	doAjaxCall${formName}("Ulasan",'mode=ulasankjpview');	
	
}

function SimpanUlasanKJP() {
	if(document.${formName}.txdTarikhHantarKJP.value == ""){
		alert('Sila masukkan " Tarikh Hantar " terlebih dahulu.');
  		document.${formName}.txdTarikhHantarKJP.focus(); 
		return; 
	}
	if(document.${formName}.txdTarikhTerimaKJP.value == ""){
		alert('Sila masukkan " Tarikh Terima " terlebih dahulu.');
  		document.${formName}.txdTarikhTerimaKJP.focus(); 
		return; 
	}
	if ( !window.confirm("Anda Pasti?") ) return;
	
	doAjaxCall${formName}("Ulasan",'mode=ulasankjpsimpan');	
}

function UlasanView() {
	document.${formName}.action = "";
	document.${formName}.mode.value = "ulasankjpview";
	document.${formName}.commander.value = "Ulasan";
	document.${formName}.submit();
	doAjaxCall${formName}("Ulasan",'mode=ulasankjpview');	
}

function UlasanJPPHView() {
	doAjaxCall${formName}("Ulasan",'mode=ulasanjpphview');	
}

function BatalUlasanJPPH() {
	if(document.${formName}.idUlasannilai.value == ""){	
		document.${formName}.txtNoRujJPPH.value = "";
		document.${formName}.txdTarikhHantarJPPH.value = "";
		document.${formName}.txdTarikhTerimaJPPH.value = "";
		document.${formName}.socPegawai.value = "";
	}
	document.${formName}.socTahunNilaian.value = "";
	document.${formName}.txtNilaiTanah.value = "";
	document.${formName}.txtSyorBayaran.value = "";
	document.${formName}.txtKeteranganJPPH.value = "";
	document.${formName}.idUlasannilai.value = "";
	document.${formName}.cmdBatal.value = "Kosongkan";
	alert('testing Batal=');
}
function KemaskiniUlasanJPPH(idUlasannilai, idTN, NT, Syor, Keterangan) {
	
	document.${formName}.idUlasannilai.value = idUlasannilai;
	document.${formName}.socTahunNilaian.value = idTN;
	document.${formName}.txtNilaiTanah.value = NT;
	document.${formName}.txtSyorBayaran.value = Syor;
	document.${formName}.txtKeteranganJPPH.value = Keterangan;
	document.${formName}.cmdBatal.value = "Batal";
	alert('testing idUlasannilai='+document.${formName}.idUlasannilai.value);
	
	doAjaxCall${formName}("Ulasan",'mode=ulasankjpkemaskini');	
}
function SimpanUlasanJPPH() {
	
	if(document.${formName}.idUlasanteknikal.value == ""){
		if(document.${formName}.txtNoRujJPPH.value == ""){
			alert('Sila masukkan " No. Ruj. JPPH " terlebih dahulu.');
			document.${formName}.txtNoRujJPPH.focus(); 
			return; 
		}
		if(document.${formName}.txdTarikhHantarJPPH.value == ""){
			alert('Sila masukkan " Tarikh Hantar " terlebih dahulu.');
			document.${formName}.txdTarikhHantarJPPH.focus(); 
			return; 
		}
		if(document.${formName}.txdTarikhTerimaJPPH.value == ""){
			alert('Sila masukkan " Tarikh Terima " terlebih dahulu.');
			document.${formName}.txdTarikhTerimaJPPH.focus(); 
			return; 
		}
		if(document.${formName}.socPegawai.value == ""){
			alert('Sila pilih " Pegawai " terlebih dahulu.');
			document.${formName}.socPegawai.focus(); 
			return; 
		}
	}
	if(document.${formName}.socTahunNilaian.value == ""){
		alert('Sila pilih " Tahun Nilaian " terlebih dahulu.');
  		document.${formName}.socTahunNilaian.focus(); 
		return; 
	}
	if(document.${formName}.txtNilaiTanah.value == ""){
		alert('Sila masukkan " Nilaian Tanah " terlebih dahulu.');
  		document.${formName}.txtNilaiTanah.focus(); 
		return; 
	}
	if ( !window.confirm("Anda Pasti?") ) return;
	
	doAjaxCall${formName}("Ulasan",'mode=ulasanjpphsimpan');	
}
/*ulasan javascript controller end*/

/*draf javascript controller start*/
function DrafView() {
	doAjaxCall${formName}("Draf",'mode=drafview');	
}

function KemaskiniDraf(idDokumenPerjanjian,tHantar, tTerima, ulasan) {
	document.${formName}.idDokumenPerjanjian.value = idDokumenPerjanjian;
	document.${formName}.txdTarikhHantarDraf.value = tHantar;
	document.${formName}.txdTarikhTerimaDraf.value = tTerima;
	document.${formName}.txtUlasanDraf.value = ulasan;
	document.${formName}.cmdBatalDraf.value = "Batal";
}

function BatalDraf() {
	document.${formName}.idDokumenPerjanjian.value = "";
	document.${formName}.txdTarikhHantarDraf.value = "";
	document.${formName}.txdTarikhTerimaDraf.value = "";
	document.${formName}.txtUlasanDraf.value = "";
	document.${formName}.cmdBatalDraf.value = "Kosongkan";
	document.${formName}.idDokumenPerjanjian.focus();
}

function SimpanDraf() {
	if(document.${formName}.txdTarikhHantarDraf.value == ""){
		alert('Sila masukkan " Tarikh Hantar Draf " terlebih dahulu.');
  		document.${formName}.txdTarikhHantarDraf.focus(); 
		return; 
	}
	if(document.${formName}.txdTarikhTerimaDraf.value == ""){
		alert('Sila masukkan " Tarikh Terima Draf " terlebih dahulu.');
  		document.${formName}.txdTarikhTerimaDraf.focus(); 
		return; 
	}
	if ( !window.confirm("Anda Pasti?") ) return;
	
	doAjaxCall${formName}("Draf",'mode=drafsimpan');	
}
/*draf javascript controller end*/

/*MJM javascript controller start*/
function SimpanMJM() {
	/*if(document.${formName}.txdTHPTP.value == ""){
		alert('Sila masukkan " Tarikh Hantar PTP " terlebih dahulu.');
  		document.${formName}.txdTHPTP.focus(); 
		return; 
	}
	if(document.${formName}.txdTTPTP.value == ""){
		alert('Sila masukkan " Tarikh Terima PTP " terlebih dahulu.');
  		document.${formName}.txdTTPTP.focus(); 
		return; 
	}
	if(document.${formName}.txdTHTUP.value == ""){
		alert('Sila masukkan " Tarikh Hantar ke Bahagian TUP " terlebih dahulu.');
  		document.${formName}.txdTHTUP.focus(); 
		return; 
	}
	if(document.${formName}.txdTMJM.value == ""){
		alert('Sila masukkan " Tarikh Mesyuarat Jemaah Menteri " terlebih dahulu.');
  		document.${formName}.txdTMJM.focus(); 
		return; 
	}
	if(document.${formName}.txdTTK.value == ""){
		alert('Sila masukkan " Tarikh Terima Keputusan " terlebih dahulu.');
  		document.${formName}.txdTTK.focus(); 
		return; 
	}
	if(document.${formName}.txtNoMemorandum.value == ""){
		alert('Sila masukkan " No. Memorandum " terlebih dahulu.');
  		document.${formName}.txtNoMemorandum.focus(); 
		return; 
	}
	if(document.${formName}.txtKeputusan.value == ""){
		alert('Sila masukkan " Keputusan " terlebih dahulu.');
  		document.${formName}.txtKeputusan.focus(); 
		return; 
	}
	if(document.${formName}.txtKeterangan.value == ""){
		alert('Sila masukkan " Keterangan Kelulusan " terlebih dahulu.');
  		document.${formName}.txtKeterangan.focus(); 
		return; 
	}*/
	if ( !window.confirm("Anda Pasti?") ) return;
	
	doAjaxCall${formName}("MJM",'mode=mjmsimpan');	
}

function KemaskiniMJM() {
	doAjaxCall${formName}("MJM",'mode=mjmkemaskini');	
}

function BatalMJM() {
	doAjaxCall${formName}("MJM",'mode=mjmview');	
}

function MJMView() {
	doAjaxCall${formName}("MJM",'mode=mjmview');	
}

/*MJM javascript controller end*/

function setSelected(tabId,command,mode,tabmode) {
	doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode);
}

<!-- end of script -->
</script>