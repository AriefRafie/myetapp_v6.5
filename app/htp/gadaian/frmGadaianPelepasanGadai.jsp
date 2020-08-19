<!-- frmGadaianPelepasanGadai -->
<style type="text/css">
	<!--
	.pautanms {color: #0000FF}
	-->
</style> 

<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>
			
			<fieldset><legend style="font-variant:small-caps">Pelepasan Gadaian</legend>
<!--<br/>-->
			<fieldset> <legend style="font-variant:small-caps">Carian</legend>
  				<table border="0" cellpadding="0" cellspacing="0" width="100%">
  					<tr>
					    <td width="29%"><div align="right">No Fail</div></td>
					    <td width="1%">:</td>
					    <td width="70%"><input name="NoFail" type="text" id="NoFail" size="40" maxlength="40" value="$!carianNoFail" onkeyup="this.value=this.value.toUpperCase();"></td>
			  		</tr>
  					<tr>
					    <td width="29%"><div align="right">Tajuk Fail</div></td>
					    <td width="1%">:</td>
					    <td width="70%"><input name="NamaFail" type="text" id="NamaFail" size="40" maxlength="300" value="$!carian" onkeyup="this.value=this.value.toUpperCase();"></td>
			  		</tr>
  					<tr>
					    <td width="29%"><div align="right">Negeri</div></td>
					    <td width="1%">:</td>
					    <td width="70%">$selectNegeri</td>
			  		</tr>
  					<tr style="display:none">
					    <td width="29%"><div align="right">Status</div></td>
					    <td width="1%">:</td>
					    <td width="70%"></td>
			  		</tr>	
      				<tr>
				    	<td colspan="3">&nbsp;
				        </td>
      				</tr>			  				  		
      				<tr>
				    	<td align="center" colspan="3" >
				        	<input name="cari" value="Cari" type="button" class="stylobutton" onclick="javascript:fSFGA_search_data()">
				        	<input name="Reset" value="Kosongkan" type="reset" class="stylobutton">
				        </td>
      				</tr>
  				</table>
			</fieldset>

			<fieldset><legend style="font-variant:small-caps">Senarai Fail</legend>
				<input name="add" value="Tambah" type="button" class="stylobutton" onClick="TambahPelepasan()">
				#parse("app/utils/record_paging.jsp")
  
  				<table border="0" cellpadding="2" cellspacing="1" width="100%">
    <tbody>
      <tr class="table_header">
        <td width="3%"><b>Bil.</b></td>
        <td width="20%"><b>No Fail</b></td>
        <td width="37%"><b>Tajuk Fail</b></td>
        <td width="17%"><b>Negeri</b></td>
        <td width="23%" style="display:none"><b>Status</b></td>
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
        <td class="$row">$fail.bil.</td>
        <td class="$row"><a href="javascript:fSFGA_seterusnya('$fail.id', '$fail.no')" class="pautanms">$fail.no</a></td>
        <td class="$row">$fail.tajuk</td>
        <td class="$row">$fail.negeri</td>
        <td class="$row" style="display:none">$fail.keterangan</td>
      </tr>
      #end
      #if ($count == 0)
      <tr>
        <td colspan="5" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
      </tr>
      #end
    </tbody>
  </table>

 
  

			</fieldset>

    	</td>
	</tr>
</table>


<script>

/**********************************************
script template : frmSenaraiFailGadaianAjax.jsp
**********************************************/

function fSFGA_cancel() {

	//doAjaxCall${formName}('',"mode=cancel");

}

function TambahPelepasan() {
	doAjaxCall${formName}('FailBaru1');
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
	doAjaxCall${formName}('SenaraiFailGadaian');
}

function fGS2A_Kemaskini() {
	doAjaxCall${formName}("FailBaru","mode=kemaskini");
}

function fGS2A_Batal() {

	if(document.${formName}.idFail.value == ""){

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
		alert('Sila masukkan No. Fail terlebih dahulu.');
  		document.${formName}.txtNoFailSek.focus(); 
		return; 
	}
		
	if ( !window.confirm("Anda Pasti?") ) return;
		doAjaxCall${formName}('FailBaru1','mode=simpan');
		
}

function doChangeKementerian() {
	doAjaxCall${formName}('doChangeKementerian');
}

function doChangeUrusan(){
	doAjaxCall${formName}('FailBaru1','mode=doChangeUrusan');

}

//end of script


/**********************************
script template : app/htp/frmGadaianSemakanAjax.jsp
************************************/

function fGSFG_LepasGadai(){
	doAjaxCall${formName}('DaftarPelepasan');
}

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

	if ( !window.confirm("Anda Pasti?") ) return;
	doAjaxCall${formName}("PenHakmilik","mode=simpan");
}

function doChangeDaerah(){
	doAjaxCall${formName}("PenHakmilik","mode=doChangeDaerah");
}




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


/*******************************************
script template : app/htp/frmGadaianSenaraiPermohonanPelepasanGadaian.jsp
********************************************/


function fGSPPG_cancel() {
document.${formName}.reset();
document.${formName}.NamaPemohon.value = "";
document.forms[0].NamaPemohon.focus();
}
function fGSPPG_Tambah() {
	doAjaxCall${formName}("Semakan","mode=baru");
}

function fGSPPG_search_data(){
	doAjaxCall${formName}("SenaraiPermohonan");
}

function fGSPPG_seterusnya(id) {
	doAjaxCall${formName}("Semakan","idPermohonan="+id);
}

function fGSPPG_Kembali() {
	doAjaxCall${formName}("");
}



/* end of script*/

/* start script frmGadaianMaklumatPeminjamAjax.jsp  */

function doChangeNegeriPeminjam(){
	doAjaxCall${formName}("Hakmilik", "mode=hakmilikview&doChange=doChangeNegeriPeminjam&tabId=0");
}

/* end of script*/

/* start script frmGadaianMaklumatPeguamAjax.jsp  */

function doChangeNegeriPeguam(){
	doAjaxCall${formName}("Hakmilik", "mode=peguamview&doChange=doChangeNegeriPeguam&tabId=1");
}

/* end of script*/

function TambahPermohonanLepasGadai(){
	doAjaxCall${formName}("TambahPermohonanPelepasanGadai");
	
}

function viewDetails(idFail, idPermohonan){
	doAjaxCall${formName}("PelepasanGadaiDetailsBaru","idFail="+idFail+"&idPermohonan="+idPermohonan);
	
}

function fGPGP_SimpanPelepasan(){
	if(document.${formName}.txTarikhLepasGadai.value == ""){ 
		alert('Sila masukkan " Tarikh Pelepasan " terlebih dahulu.'); 
		document.${formName}.txTarikhLepasGadai.focus();
		return; 
	}
	doAjaxCall${formName}("SimpanPelepasan");
}

function viewDetailPemohonPelepasan(idFail,idPermohonan,id){
	doAjaxCall${formName}("PelepasanGadaiDetails", "idFail="+idFail+"&idPermohonan="+idPermohonan+"&idfaillama="+id);
}

function fGPGP_KemaskiniPelepasan(){
	doAjaxCall${formName}("kemaskinipelepasan");

}


function semakTarikhHariIni(txdTarikhSuratKJP) {
	var today = new Date();
	dari_bulan	= txdTarikhSuratKJP.value.substring(3,5);
	dari_hari 	= txdTarikhSuratKJP.value.substring(0,2);
	dari_tahun 	= txdTarikhSuratKJP.value.substring(6,10);
	var daritemp = dari_bulan+"/"+dari_hari+"/"+dari_tahun;
	
	var myDate = Date.parse(daritemp);
	

	if (myDate>today) {
  		alert("Tarikh yang dimasukkan tidak boleh melebihi Tarikh semasa");
  		txdTarikhSuratKJP.value="";	 
  		txdTarikhSuratKJP.focus();	 
  		
 		return;
 	}

}


</script>

