<style type="text/css">
<!--
.pautanms {color: #0000FF}
-->
</style>
<!--<br><br>-->
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>&nbsp;</td>
    </tr>
	<tr>
		<td>			
			<fieldset><legend><strong>CARIAN</strong></legend>
				<table border="0" width="100%">
					<tr>
					    <td width="29%"><div align="right">NO. RUJUKAN <i>ONLINE</i></div></td>
					    <td width="1%">:</td>
					    <td width="70%"><input name="NoFail" type="text" id="NoFail" size="43" maxlength="40" value="$!carianNoFail" onkeyup="this.value=this.value.toUpperCase();"></td>
			  		</tr>
			  		<tr>
					    <td><div align="right">NO. FAIL UPT</div></td>
					    <td>:</td>
					    <td><input name="NoFailUpt" type="text" id="NoFailUpt" size="43" maxlength="40" value="$!carianNoFailUpt" onkeyup="this.value=this.value.toUpperCase();"></td>
			  		</tr>
			  		<tr>
					    <td><div align="right">NO. FAIL PTG</div></td>
					    <td>:</td>
					    <td><input name="NoFailPtg" type="text" id="NoFailPtg" size="43" maxlength="40" value="$!carianNoFailPtg" onkeyup="this.value=this.value.toUpperCase();"></td>
			  		</tr>
			  		<tr>
					    <td><div align="right">NO. FAIL PTD</div></td>
					    <td>:</td>
					    <td><input name="NoFailPtd" type="text" id="NoFailPtd" size="43" maxlength="40" value="$!carianNoFailPtd" onkeyup="this.value=this.value.toUpperCase();"></td>
			  		</tr>
  					<tr>
					    <td><div align="right">TAJUK FAIL</div></td>
					    <td>:</td>
					    <td><input name="NamaFail" type="text" id="NamaFail" size="43" maxlength="300" value="$!carian" onkeyup="this.value=this.value.toUpperCase();"></td>
			  		</tr>
  					<tr>
					    <td><div align="right">NEGERI</div></td>
					    <td>:</td>
					    <td>$selectNegeri</td>
			  		</tr>
					<tr>
					    <td><div align="right">NAMA PEMILIK</div></td>
					    <td>:</td>
					    <td><input name="NamaPemilik" type="text" id="NamaPemilik" size="43" maxlength="80" value="$!carianPemilik" onkeyup="this.value=this.value.toUpperCase();"></td>
			  		</tr>
			  		<tr>
					    <td><div align="right">NO. KP / SYARIKAT / RUJUKAN</div></td>
					    <td>:</td>
					    <td><input name="NoRujukan" type="text" id="NoRujukan" size="43" maxlength="20" value="$!carianNoRujukan" onkeyup="this.value=this.value.toUpperCase();"></td>
			  		</tr>
			  		
      <tr style="display:none">
        <td  align="right">Status :<strong> &nbsp;&nbsp;</strong></td>
        <td>&nbsp;</td>
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
		</td>
	</tr>

	<tr>
		<td>
			<fieldset><legend><strong>SENARAI FAIL</strong></legend>
				<input type="button" class="stylobutton" name="add" value="Tambah"  onClick="fSFGA_Tambah()" >
				#parse("app/utils/record_paging.jsp")

  <table border="0" cellpadding="2" cellspacing="1" width="100%">
      <tr class="table_header">
        <td width="3%"><b>Bil.</b></td>
        <td width="20%"><b>No. Rujukan <i>Online</i></b></td>
        <td width="37%"><b>Tajuk Fail</b></td>
        <td width="17%"><b>Negeri</b></td>
        <td width="23%"><b>Status</b></td>
      </tr>	
      #set ($count = 0)
      #foreach ( $fail in $SenaraiFail )
      ##foreach ( $fail in $lists )
      	
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
        <td class="$row">$fail.keterangan</td>
      </tr>
      #end
      #if ($count == 0)
      <tr>
        <td colspan="5" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
      </tr>
      #end
  </table>
  <!--
  <input type="hidden" name="idFail">
  <input type="hidden" name="noFail">
  <input type="hidden" name="keterangan">
-->
 
  
</fieldset>

		</td>
	</tr>
</table>
  
<script>

/**********************************************
script template : frmKJPSenaraiFailGadaianAjax.jsp
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
script template : frmKJPGadaianSemakan2Ajax.jsp
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
script template : app/htp/frmKJPGadaianSemakanAjax.jsp
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
function fGPHA_seterusnyaHantar() {
	doAjaxCall${formName}("pengesahan","mode=hantarselesai");
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
	if(document.${formName}.socLot.value == ""){
		alert('Sila pilih " Kod Lot/No. " terlebih dahulu.');
  		document.${formName}.socLot.focus(); 
		return; 
	}	
	/*if(document.${formName}.txtKodLot.value == ""){
		alert('Sila masukkan " Kod Lot/No. " terlebih dahulu.');
  		document.${formName}.txtKodLot.focus(); 
		return; 
	}*/
	if(document.${formName}.txtNoLot.value == ""){
		alert('Sila masukkan " No. Lot " terlebih dahulu.');
  		document.${formName}.txtNoLot.focus(); 
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

	 if(document.${formName}.socLuas.value == "4" || document.${formName}.socLuas.value == "7" ||document.${formName}.socLuas.value == "8"){
		if(document.${formName}.socLuas.value == "4"){
			document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas2.value +"E,"+document.${formName}.txtLuas3.value+"R,"+document.${formName}.txtLuas4.value+"P";
		}else if(document.${formName}.socLuas.value == "7"){
			document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas5.value +"E,"+document.${formName}.txtLuas6.value+"D";
		}else if(document.${formName}.socLuas.value == "8"){
			document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas2.value +"R,"+document.${formName}.txtLuas3.value+"J,"+document.${formName}.txtLuas4.value+"K";
		}
	 }else{
		document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas1.value;
	 }
	
	if ( !window.confirm("Anda Pasti?") ) return;
	doAjaxCall${formName}("PenHakmilik","mode=simpan");
}

function doChangeDaerah(){
	doAjaxCall${formName}("PenHakmilik","mode=doChangeDaerah");
}

function doChangeDaerahKemaskini(){
	doAjaxCall${formName}("PenHakmilik","mode=doChangeDaerahKemaskini");
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
		if(document.${formName}.txtNoTelefon.value == ""){
			alert('Sila masukkan " No.Telefon " terlebih dahulu.');
	  		document.${formName}.txtNoTelefon.focus(); 
			return; 
		}
		if(document.${formName}.txtNoPerserahan.value == ""){
			alert('Sila masukkan " Gadaian Pendua No. Perserahan " terlebih dahulu.');
	  		document.${formName}.txtNoPerserahan.focus(); 
			return; 
		}
		if(document.${formName}.txtjilid.value == ""){
			alert('Sila masukkan " Gadaian Pendua No. Jilid " terlebih dahulu.');
	  		document.${formName}.txtjilid.focus(); 
			return; 
		}
		if(document.${formName}.txtfolio.value == ""){
			alert('Sila masukkan " Gadaian Pendua No. Folio " terlebih dahulu.');
	  		document.${formName}.txtfolio.focus(); 
			return; 
		}	
	
		if ( !window.confirm("Anda Pasti?") ) return;
			doAjaxCall${formName}("Hakmilik","mode=simpanHakmilik");
	
	}

function fGHA_KembaliHakmilik() {
	doAjaxCall${formName}("SenaraiPermohonan");
}
function fGHA_KembaliHantar() {
	doAjaxCall${formName}("SenaraiPemilikPeguam");
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
	if(document.${formName}.txtNoTelefonPeguam.value == ""){
		alert('Sila masukkan No. Telefon Peguam terlebih dahulu.');
  		document.${formName}.txtNoTelefonPeguam.focus(); 
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
	document.${formName}.namaskrin.value = "";
	doAjaxCall${formName}("");
	
}

// document.forms[0].NamaPemohon.focus(); 

/* end of script*/

/* start script frmGadaianMaklumatPeminjamAjax.jsp  */

function doChangeNegeriPeminjam(){
	doAjaxCall${formName}("Hakmilik", "mode=hakmilikview&doChange=doChangeNegeriPeminjam&tabId=0");
}

function doChangeNegeriPeminjamKemaskini(){
	doAjaxCall${formName}("Hakmilik", "mode=kemaskiniHakmilikChangeNegeri&doChange=doChangeNegeriPeminjam&tabId=0");
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

function fGTG_SimpanUpdate16a(){
	doAjaxCall${formName}("Hakmilik", "mode=simpanupdate16a");
}
function fGTG_KemaskiniGeran(){
	doAjaxCall${formName}("Hakmilik", "mode=kemaskinigeran");
}
function fGTG_Simpan16a(){
	
	doAjaxCall${formName}("Hakmilik", "mode=simpan16a");
}

function fGTG_Kemaskini16a(){
	doAjaxCall${formName}("Hakmilik", "mode=kemaskini16a");
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
	if(document.${formName}.socLot.value == ""){
		alert('Sila pilih " Kod Lot/No. " terlebih dahulu.');
  		document.${formName}.socLot.focus(); 
		return; 
	}	
	if(document.${formName}.txtKodLot.value == ""){
		alert('Sila masukkan " Kod Lot/No. " terlebih dahulu.');
  		document.${formName}.txtKodLot.focus(); 
		return; 
	}
	
	/*if(document.${formName}.txtNoLot.value == ""){
		alert('Sila masukkan " No. Lot " terlebih dahulu.');
  		document.${formName}.txtNoLot.focus(); 
		return; 
	}	*/

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

function kiraLuas(idLuas){
  var jenisLuas = idLuas;
  // KILOMETER PERSEGI
  if(jenisLuas == "1"){
  	var luasK = (document.${formName}.txtLuas1.value);
	var luasH = luasK*100;
  	document.${formName}.txtLuas.value = luasH;
   }
   else
   //HEKTER
    if(jenisLuas == "2"){
  	var luasH = (document.${formName}.txtLuas1.value);
  	document.${formName}.txtLuas.value = luasH;
   }
   else
   // METER PERSEGI
   if(jenisLuas == "3"){
  	  var luasM = document.${formName}.txtLuas1.value;
  	  var luasH = (luasM*0.0001);
	  document.${formName}.txtLuas.value = luasH.toFixed(5);
   }
   else
   //EKAR, ROOD, POLE
   if(jenisLuas == "4"){
  	  var luasE = document.${formName}.txtLuas2.value;
	  var luasR = document.${formName}.txtLuas3.value;
	  var luasP = document.${formName}.txtLuas4.value;
	  var luasH = (luasE*0.4046864)+(luasR*0.1011716)+(luasP*0.00252929);
  	  document.${formName}.txtLuas.value = luasH.toFixed(5);
   }
   else
   //KAKI PERSEGI
   if(jenisLuas == "5"){
  	  var luasAsal = document.${formName}.txtLuas1.value;
	  var luasK = luasAsal*0.0000092;
  	  document.${formName}.txtLuas.value = luasK.toFixed(5);
   }
   else
   //EKAR,DEPA
   if(jenisLuas == "7"){
  	  var luasE = document.${formName}.txtLuas5.value;
	  var luasD = document.${formName}.txtLuas6.value;
	  
	  var luasH = (luasE*0.4046864)+(luasD*0.00040469);
  	  document.${formName}.txtLuas.value = luasH.toFixed(5);
   }
    else
   //RELONG,JEMBA,KAKI PERSEGI
   if(jenisLuas == "8"){
  	  var luasR = document.${formName}.txtLuas2.value;
	  var luasJ = document.${formName}.txtLuas3.value;
	  var luasK = document.${formName}.txtLuas4.value;
	  
	  var luasH = (luasR*0.2877764)+(luasJ*0.0005945)+(luasK*0.0000092);
  	  document.${formName}.txtLuas.value = luasH.toFixed(5);
   }
}

function doChangeKodLuas(t) {
	var command = 'PenHakmilik';
	//var mode = 'MakAsasTanah';
	var mode = '';
	alert(mode);
	var tabId = 0;
	var tabmode = t;
	var dochange = "doChangeKodLuas";
	doAjaxCall${formName}(command,'mode=$pagemode'+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&doChange='+dochange);

}

function doChangeKodLuasUpdate(t) {
	var command = 'PenHakmilik';
	var mode = 'kemaskini';
	var tabId = 0;
	var tabmode = t;
	var dochange = "doChangeKodLuas";
	doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&doChange='+dochange);

}

//TAB PENGHANTARAN/ SELESAI

function simpanSelesai(){
	if(document.${formName}.txdTarikhTerima.value == ""){
		alert('Sila masukkan " Tarikh " terlebih dahulu.');
  		document.${formName}.txdTarikhTerima.focus(); 
		return; 
	}
	doAjaxCall${formName}("Hakmilik", "mode=selesaisimpan");
	
}

function kemaskiniSimpanSelesai(){
	doAjaxCall${formName}("Hakmilik", "mode=selesaikemaskinisimpan");
	
}

function kemaskiniSelesai(){
	doAjaxCall${formName}("Hakmilik", "mode=selesaikemaskini");
	
}

//semakan Tarikh semasa
function checkDate(inputfield) {
	var today = new Date();
	
	dari_bulan = inputfield.value.substring(3,5);
	dari_hari = inputfield.value.substring(0,2);
	dari_tahun = inputfield.value.substring(6,10);
	var daritemp = dari_bulan+"/"+dari_hari+"/"+dari_tahun;
	
	var myDate = Date.parse(daritemp);
	

	if (myDate>today) {
  		alert("Tarikh yang dimasukkan tidak boleh melebihi Tarikh semasa");
  		inputfield.value="";
  		inputfield.focus();
 		return;
 	}

}
function cetakPengesahan(idpermohonan) {
	
    //var url = "../servlet/ekptg.report.htp.NoFailTajukFail?template=PengesahanPembelianOnline&idpermohonan="+idpermohonan;
    var url = "../servlet/ekptg.report.htp.NoFailTajukFail?template=PengesahanPermohonanOnline&idpermohonan="+idpermohonan;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();


}

</script> 

