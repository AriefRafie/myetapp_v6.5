<style type="text/css">
<!--
.pautanms {color: #0000FF}
-->
</style>
<br><br>
<fieldset> <legend><strong>CARIAN</strong></legend><table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
        <td height="25" align="right">No. Fail : <strong>&nbsp;&nbsp;</strong></td>
        <td><input name="NoFail" type="text" size="55" maxlength="400" value="$carianNoFail" onkeyup="this.value=this.value.toUpperCase();" class="mediumselect"></td>
      </tr>
      <tr>
        <td height="25" align="right">Nama Fail <strong>: &nbsp;&nbsp;</strong></td>
        <td><input name="NamaFail" type="text" size="55" maxlength="300" value="$carian" onkeyup="this.value=this.value.toUpperCase();"></td>
      </tr>
      <tr>
        <td height="25"  align="right">Negeri : <strong>&nbsp;&nbsp;</strong></td>
        <td>$selectNegeri</td>
      </tr>
      <tr style="display:none">
        <td  align="right">Status :<strong> &nbsp;&nbsp;</strong></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td></td>
        <td><input name="cari" value="Cari" type="button" onclick="javascript:fSFGA_search_data()">
        <input name="Reset" type="reset" value="Kosongkan"></td>
      </tr>
    </tbody>
  </table>
</fieldset>
<fieldset>
<legend><strong>SENARAI FAIL</strong></legend>

	<input name="add" value="Tambah" type="button" onClick="fSFGA_Tambah()">


  <table border="0" cellpadding="2" cellspacing="1" width="100%">
    <tbody>
      <tr class="table_header">
        <td width="5%"><b>No</b></td>
        <td width="20%"><b>No Fail</b></td>
        <td width="17%"><b>Negeri</b></td>
        <td width="35%"><b>Nama Fail</b></td>
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
        <td class="$row">$fail.bil</td>
        <td class="$row"><a href="javascript:fSFGA_seterusnya('$fail.id', '$fail.no')" class="pautanms">$fail.no</a></td>
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
  <!--
  <input type="hidden" name="idFail">
  <input type="hidden" name="noFail">
-->
 
  
</fieldset>
<script>

/**********************************************
script template : frmSenaraiFailGadaianAjax.jsp
**********************************************/

function fSFGA_cancel() {
/*
document.${formName}.reset();
document.${formName}.NamaFail.value = "";
document.${formName}.NoFail.value = "";
document.${formName}.socNegeri.value = "";
document.${formName}.NamaFail.focus();
*/
	//doAjaxCall${formName}('',"mode=cancel");

}
function fSFGA_Tambah() {
	doAjaxCall${formName}('FailBaru');
}
function fSFGA_search_data(){
	doAjaxCall${formName}('');
	
}
function fSFGA_seterusnya(id, no) {
	doAjaxCall${formName}('SenaraiPermohonan','idFail='+id+'&noFail='+no);
}

//end of script

/********************************
script template : frmGadaianSemakan2Ajax.jsp
********************************/

function fGS2A_Kembali() {
	document.${formName}.action = "";
	document.${formName}.command.value = "";
	document.${formName}.submit();

}

function fGS2A_Seterusnya() {
	doAjaxCall${formName}('Semakan','mode=baru');
}

function fGS2A_Kemaskini() {
	doAjaxCall${formName}("FailBaru","mode=kemaskini");
}

function fGS2A_Batal() {
//fir	document.f2.action = "";
	if(document.${formName}.idFail.value == ""){
		//fir document.f2.mode.value = "";
//		doAjaxCall${formName}("mode=kemaskini");
	}
	else{
	doAjaxCall${formName}("FailBaru","mode=view");
	}
}
function fGS2A_Simpan() {
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih " Negeri " terlebih dahulu.');
  		document.${formName}.socNegeri.focus(); 
		return; 
	}
	
	if(document.${formName}.socSuburusan.value == ""){
		alert('Sila pilih " Urusan " terlebih dahulu.');
  		document.${formName}.socSuburusan.focus(); 
		return; 
	}
	if(document.${formName}.socTajuk.value == ""){
		alert('Sila pilih " Tajuk " terlebih dahulu.');
  		document.${formName}.txtTajuk.focus(); 
		return; 
	}
	
	if(document.${formName}.txtNoFailSek.value == ""){
		alert('Sila masukkan No Fail Seksyen terlebih dahulu.');
  		document.${formName}.txtNoFailSek.focus(); 
		return; 
	}
	
	
	if ( !window.confirm("Anda Pasti?") ) return;
		doAjaxCall${formName}('FailBaru','mode=simpan');
}

function doChangeKementerian() {
	doAjaxCall${formName}('doChangeKementerian');
}

function doChangeUrusan(){
	doAjaxCall${formName}('FailBaru','mode=doChangeUrusan');
}

//document.forms[0].cmdKemaskini.style.display = document.${formName}.style1.value;
//document.forms[0].cmdSimpan.style.display = document.${formName}.style2.value;
//document.forms[0].cmdBatal.style.display = document.${formName}.style2.value;
//document.forms[0].cmdKembali.style.display = document.${formName}.style1.value;
//document.forms[0].cmdSeterusnya.style.display = document.${formName}.style1.value;

//end of script


/**********************************
script template : app/htp/frmGadaianSemakanAjax.jsp
************************************/

function fGSA_Kembali() {
	doAjaxCall${formName}("SenaraiPermohonan");
}

function fGSA_Seterusnya() {
	doAjaxCall${formName}("PenHakmilik");
}

function fGSA_Kemaskini() {
	doAjaxCall${formName}("Semakan","mode=kemaskini");
}

function fGSA_Batal() {
	if(document.${formName}.idPermohonan.value == "")
			doAjaxCall${formName}("Semakan","mode='baru'");
	else{
		doAjaxCall${formName}("Semakan","mode=''");
	}
}
function fGSA_Simpan() {
	if(document.${formName}.txdTarikhSurKJP.value == ""){
		alert('Sila masukkan " Tarikh Surat KJP " terlebih dahulu.');
  		document.${formName}.txdTarikhSurKJP.focus(); 
		return; 
	}
	if(document.${formName}.txtNoFailLain.value == ""){
		alert('Sila masukkan " No. Fail Lain " terlebih dahulu.');
  		document.${formName}.txtNoFailLain.focus(); 
		return; 
	}
	if(document.${formName}.txdTarikhBukaFail.value == ""){
		alert('Sila masukkan " Tarikh Buka Fail " terlebih dahulu.');
  		document.${formName}.txdTarikhBukaFail.focus(); 
		return; 
	}
	if ( !window.confirm("Anda Pasti?") ) return;
		doAjaxCall${formName}("Semakan","mode=simpan");
}

/*
document.forms[0].cmdKemaskini.style.display = document.${formName}.style1.value;
document.forms[0].cmdSimpan.style.display = document.${formName}.style2.value;
document.forms[0].cmdBatal.style.display = document.${formName}.style2.value;
//document.forms[0].cmdKembali.style.display = document.${formName}.style1.value;
document.forms[0].cmdSeterusnya.style.display = document.${formName}.style1.value;
*/

/*********************************************
script template : frmGadaianPenHamilikAjax.jsp
**********************************************/

function validateCurrency(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}

function RemoveNonNumeric( strString )
{
      var strValidCharacters = "1234567890.,";
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

function fGPHA_Kembali() {
	doAjaxCall${formName}("SenaraiPermohonan");
}

function fGPHA_seterusnya() {
	doAjaxCall${formName}("Hakmilik","mode=hakmilikview");
}

function fGPHA_Kemaskini() {
	doAjaxCall${formName}("PenHakmilik","mode=kemaskini");
}

function fGPHA_Batal() {
	/*
	document.${formName}.action = "";
	document.${formName}.mode.value = "";
	document.${formName}.command.value = "PenHakmilik"
	document.${formName}.submit();
	*/
	doAjaxCall${formName}("PenHakmilik","mode=''");
}
function fGPHA_Simpan() {
	if(document.${formName}.socDaerah.value == ""){
		alert('Sila pilih " Daerah " terlebih dahulu.');
  		document.${formName}.socDaerah.focus(); 
		return; 
	}
	if(document.${formName}.socMukim.value == ""){
		alert('Sila pilih " Bandar/Pekan/Mukim " terlebih dahulu.');
  		document.${formName}.socMukim.focus(); 
		return; 
	}
	if(document.${formName}.socJenisHakmilik.value == ""){
		alert('Sila pilih " Jenis Hakmilik " terlebih dahulu.');
  		document.${formName}.socJenisHakmilik.focus(); 
		return; 
	}
	if(document.${formName}.txtNoHakmilik.value == ""){
		alert('Sila masukkan " No. Hakmilik " terlebih dahulu.');
  		document.${formName}.txtNoHakmilik.focus(); 
		return; 
	}
	if(document.${formName}.txtKodLot.value == ""){
		alert('Sila masukkan " Kod Lot/No. " terlebih dahulu.');
  		document.${formName}.txtKodLot.focus(); 
		return; 
	}
	if(document.${formName}.txtNoLot.value == ""){
		alert('Sila masukkan " No. Lot " terlebih dahulu.');
  		document.${formName}.txtNoLot.focus(); 
		return; 
	}
	if(document.${formName}.socLot.value == ""){
		alert('Sila pilih " Kod Lot/No. " terlebih dahulu.');
  		document.${formName}.socLot.focus(); 
		return; 
	}
	if(document.${formName}.txdTarikhTerima.value == ""){
		alert('Sila masukkan " Tarikh Terima Hakmilik " terlebih dahulu.');
  		document.${formName}.txdTarikhTerima.focus(); 
		return; 
	}
	/*
	if(document.${formName}.socLuas.value == ""){
		alert('Sila pilih " Unit Luas " terlebih dahulu.');
  		document.${formName}.socLuas.focus(); 
		return; 
	}
	if(document.${formName}.socRizab.value == ""){
		alert('Sila pilih " Rizab " terlebih dahulu.');
  		document.${formName}.socRizab.focus(); 
		return; 
	}
	if(document.${formName}.socKategori.value == ""){
		alert('Sila pilih " Kategori " terlebih dahulu.');
  		document.${formName}.socKategori.focus(); 
		return; 
	}	
	if(document.${formName}.txdTarikhLuput.value == ""){
		alert('Sila masukkan " Tarikh Luput " terlebih dahulu.');
  		document.${formName}.txdTarikhLuput.focus(); 
		return; 
	}
	if(document.${formName}.txtLuas.value == ""){
		alert('Sila masukkan " Luas " terlebih dahulu.');
  		document.${formName}.txtLuas.focus(); 
		return; 
	}
	if(document.${formName}.txdTarikhWarta.value == ""){
		alert('Sila masukkan " Tarikh Warta " terlebih dahulu.');
  		document.${formName}.txdTarikhWarta.focus(); 
		return; 
	}
	if(document.${formName}.txtNoWarta.value == ""){
		alert('Sila masukkan " No. Warta " terlebih dahulu.');
  		document.${formName}.txtNoWarta.focus(); 
		return; 
	}
	*/
	if ( !window.confirm("Anda Pasti?") ) return;
	doAjaxCall${formName}("PenHakmilik","mode=simpan");
}

function doChangeDaerah(){
	doAjaxCall${formName}("PenHakmilik","mode=doChangeDaerah");
}


/*
document.forms[0].cmdKemaskini.style.display = document.${formName}.style1.value;
document.forms[0].cmdSimpan.style.display = document.${formName}.style2.value;
document.forms[0].cmdBatal.style.display = document.${formName}.style2.value;
document.forms[0].cmdKembali.style.display = document.${formName}.style1.value;
document.forms[0].cmdSeterusnya.style.display = document.${formName}.style1.value;
*/

/*******************************************
script template : frmGadaianHakmilikAjax.jsp
********************************************/

function validatePoskod(elmnt,content) {
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

function fGHA_KemaskiniHakmilik() {
	doAjaxCall${formName}("Hakmilik","mode=kemaskiniHakmilik");
}

function fGHA_BatalHakmilik() {
	doAjaxCall${formName}("Hakmilik","mode=hakmilikview");
}

function fGHA_SimpanHakmilik() {
	if(document.${formName}.txtNama.value == ""){
		alert('Sila masukkan " Nama " terlebih dahulu.');
  		document.${formName}.txtNama.focus(); 
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
	if(document.${formName}.txtNoPerserahan.value == ""){
		alert('Sila masukkan " Gadaian Pendua No. Perserahan " terlebih dahulu.');
  		document.${formName}.txtNoPerserahan.focus(); 
		return; 
	}
	if ( !window.confirm("Anda Pasti?") ) return;
		doAjaxCall${formName}("Hakmilik","mode=simpanHakmilik");
}

function fGHA_KembaliHakmilik() {
	doAjaxCall${formName}("SenaraiPermohonan");
}

function fGHA_HakmilikView() {
	doAjaxCall${formName}("Hakmilik","mode=hakmilikview");
}

function fGHA_KemaskiniPeguam() {
	doAjaxCall${formName}("Hakmilik","mode=kemaskiniPeguam");
}

function fGHA_BatalPeguam() {
	doAjaxCall${formName}("Hakmilik","mode=peguamview");
}

function fGHA_SimpanPeguam() {
	if(document.${formName}.txtNamaPeguam.value == ""){
		alert('Sila masukkan Nama Peguam terlebih dahulu.');
  		document.${formName}.txtNamaPeguam.focus(); 
		return; 
	}
	if(document.${formName}.txtAlamat1Peguam.value == ""){
		alert('Sila masukkan  Alamat Peguam terlebih dahulu.');
  		document.${formName}.txtAlamat1Peguam.focus(); 
		return; 
	}
	if(document.${formName}.txtPoskodPeguam.value == ""){
		alert('Sila masukkan Poskod Peguam terlebih dahulu.');
  		document.${formName}.txtPoskodPeguam.focus(); 
		return; 
	}
	if(document.${formName}.socBDaerah.value == ""){
		alert('Sila pilih Daerah terlebih dahulu.');
  		document.${formName}.socBDaerah.focus(); 
		return; 
	}
	if(document.${formName}.socBNegeri.value == ""){
		alert('Sila pilih Negeri terlebih dahulu.');
  		document.${formName}.socBNegeri.focus(); 
		return; 
	}
	if ( !window.confirm("Anda Pasti?") ) return;
		doAjaxCall${formName}("Hakmilik","mode=simpanPeguam");
}
function fGHA_KembaliPeguam() {
	doAjaxCall${formName}("SenaraiPermohonan");
}

function fGHA_PeguamView() {
	doAjaxCall${formName}("Hakmilik","mode=peguamview");
}

function setSelected(tabId,mode) {
		doAjaxCall${formName}("Hakmilik","mode="+mode+"&tabId="+tabId);
}

/*
document.${formName}.cmdKemaskini.style.display = document.${formName}.style1.value;
document.${formName}.cmdSimpan.style.display = document.${formName}.style2.value;
document.${formName}.cmdBatal.style.display = document.${formName}.style2.value;
document.${formName}.cmdKembali.style.display = document.${formName}.style1.value;

document.${formName}.cmdKemaskini.style.display = document.${formName}.style1.value;
document.${formName}.cmdSimpan.style.display = document.${formName}.style2.value;
document.${formName}.cmdBatal.style.display = document.${formName}.style2.value;
document.${formName}.cmdKembali.style.display = document.${formName}.style1.value;
*/

/*******************************************
script template : app/htp/frmGadaianSenaraiPermohonanAjax.jsp
********************************************/


function fGSPA_cancel() {
document.${formName}.reset();
document.${formName}.NamaPemohon.value = "";
document.forms[0].NamaPemohon.focus();
}
function fGSPA_Tambah() {
	doAjaxCall${formName}("Semakan","mode=baru");
}

function fGSPA_search_data(){
	doAjaxCall${formName}("SenaraiPermohonan");
}

function fGSPA_seterusnya(id) {
	doAjaxCall${formName}("Semakan","idPermohonan="+id);
}

function fGSPA_Kembali() {
	doAjaxCall${formName}("");
}

// document.forms[0].NamaPemohon.focus(); 

/* end of script*/

/* start script frmGadaianMaklumatPeminjamAjax.jsp  */

function doChangeNegeriPeminjam(){
	doAjaxCall${formName}("Hakmilik", "mode=hakmilikview&doChange=doChangeNegeriPeminjam&tabId=0");
}

function doChangeNegeriPeminjamKemaskini(){
	doAjaxCall${formName}("Hakmilik", "mode=kemaskiniHakmilik&doChange=doChangeNegeriPeminjam&tabId=0");
}

/* end of script*/

/* start script frmGadaianMaklumatPeguamAjax.jsp  */

function doChangeNegeriPeguam(){
	doAjaxCall${formName}("Hakmilik", "mode=peguamview&doChange=doChangeNegeriPeguam&tabId=1");
}

function doChangeNegeriPeguamKemaskini(){
	doAjaxCall${formName}("Hakmilik", "mode=kemaskiniPeguam&doChange=doChangeNegeriPeguam&tabId=1");
}

/* end of script*/

/* start script for frmGadainTabGeran.jsp  */

function fGTG_SimpanGeran(){
	doAjaxCall${formName}("Hakmilik", "mode=simpangeran");
}

function fGTG_BatalGeran(){
	doAjaxCall${formName}("Hakmilik", "mode=batalgeran");
}

function fGTG_KemaskiniGeran(){
	doAjaxCall${formName}("Hakmilik", "mode=kemaskinigeran");
}

function fGTG_KembaliGeran(){
	doAjaxCall${formName}("Hakmilik", "mode=kembaligeran");
}

function fGTG_SimpanUpdateGeran(){
	doAjaxCall${formName}("Hakmilik", "mode=simpanupdategeran");
}




/* end of script */



</script>

<script>

//Senarai surat di Skrin Peguam
function senaraiSuratPeguam(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}                   

function senaraiSuratPeminjam(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

// 2010/05/31 -Pilih Pegawai untuk tandatangan surat
function openSuratPegawai(urli,param,laporan,tem){
	var url = "../x/${securityToken}/ekptg.view.htp.FrmSenaraiSuratHTP?command=pegawai&urli="+urli+"&"+param+tem+"&flagReport=S";
    var hWnd = window.open(url,'Cetak','status=1,width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
//Gadaian Pelepasan

function simpanPelepasan() {
	if(document.${formName}.socDaerah.value == ""){
		alert('Sila pilih " Daerah " terlebih dahulu.');
  		document.${formName}.socDaerah.focus(); 
		return; 
	}
	if(document.${formName}.socMukim.value == ""){
		alert('Sila pilih " Bandar/Pekan/Mukim " terlebih dahulu.');
  		document.${formName}.socMukim.focus(); 
		return; 
	}
	if(document.${formName}.socJenisHakmilik.value == ""){
		alert('Sila pilih " Jenis Hakmilik " terlebih dahulu.');
  		document.${formName}.socJenisHakmilik.focus(); 
		return; 
	}
	if(document.${formName}.txtNoHakmilik.value == ""){
		alert('Sila masukkan " No. Hakmilik " terlebih dahulu.');
  		document.${formName}.txtNoHakmilik.focus(); 
		return; 
	}
	if(document.${formName}.txtKodLot.value == ""){
		alert('Sila masukkan " Kod Lot/No. " terlebih dahulu.');
  		document.${formName}.txtKodLot.focus(); 
		return; 
	}
	if(document.${formName}.txtNoLot.value == ""){
		alert('Sila masukkan " No. Lot " terlebih dahulu.');
  		document.${formName}.txtNoLot.focus(); 
		return; 
	}
	if(document.${formName}.socLot.value == ""){
		alert('Sila pilih " Kod Lot/No. " terlebih dahulu.');
  		document.${formName}.socLot.focus(); 
		return; 
	}
	/*if(document.${formName}.txdTarikhTerima.value == ""){
		alert('Sila masukkan " Tarikh Terima Hakmilik " terlebih dahulu.');
  		document.${formName}.txdTarikhTerima.focus(); 
		return; 
	}*/

	if ( !window.confirm("Anda Pasti?") ) return;
	doAjaxCall${formName}("PenHakmilik","mode=simpan");
}

function kemaskiniPelepasan() {
	doAjaxCall${formName}("PenHakmilik","mode=kemaskini");
}

</script> 

