<style type="text/css">
<!--
.style1 {color: #FF0000}
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }
-->
</style>
#parse("app/ppt/frmHakmilikSementaraMaklumatPermohonan.jsp")
<p>
<fieldset><legend><strong>Maklumat Penandaan Kawasan</strong></legend>
<table width="100%">
  <tr>
    <td align="left" width="1%"><span class="style1">*</span></td>
    <td align="left" width="28%">Status Tanda</td>
    <td width="1%">:</td>
    <td width="70%">
      <select name="socStatusTanda" id="socStatusTanda" class="$disabled" $disabled $readonly>
      #if($StatusTanda == '0')
      <option value="0" selected="selected">SILA PILIH</option>
      <option value="1">DIBUAT</option>
      <option value="2">TIDAK DIBUAT</option>
      #end
      #if($StatusTanda == '1')
      <option value="0">SILA PILIH</option>
      <option value="1" selected="selected">DIBUAT</option>
      <option value="2">TIDAK DIBUAT</option>
      #end
      #if($StatusTanda == '2')
      <option value="0">SILA PILIH</option>
      <option value="1" >DIBUAT</option>
      <option value="2" selected="selected">TIDAK DIBUAT</option>
      #end
                        </select></td>
  </tr>
  <tr>
    <td width="1%" align="left"><span class="style1">*</span></td>
    <td width="28%" align="left">Status Laksana</td>
    <td>:</td>
    <td width="70%">
      <select name="socStatusLaksana" id="socStatusLaksana" class="$disabled" $disabled $readonly>
      #if($StatusLaksana == '0')
      <option value="0" selected="selected">SILA PILIH</option>
      <option value="1">DILAKUKAN SENDIRI</option>
      <option value="2">BANTUAN AGENSI</option>
      #end
      #if($StatusLaksana == '1')
      <option value="0">SILA PILIH</option>
      <option value="1" selected="selected">DILAKUKAN SENDIRI</option>
      <option value="2">BANTUAN AGENSI</option>
      #end
      #if($StatusLaksana == '2')
      <option value="0">SILA PILIH</option>
      <option value="1">DILAKUKAN SENDIRI</option>
      <option value="2" selected="selected">BANTUAN AGENSI</option>
      #end
        </select></td>
  </tr>
  <tr>
    <td width="1%" align="left"><span class="style1">*</span></td>
    <td width="28%" align="left">Tarikh Tanda Dari</td>
    <td>:</td>
    <td width="70%"><label>
      <input name="txdTkhTandaMula" type="text" id="txdTkhTandaMula" value="$TarikhTandaDari" size="10" class="$disabled" $disabled $readonly onblur="checking_validation(this,'tarikh_tanda_dari_check','yes','tanda dari','tarikh');">
    #if( $readonly != 'readonly')<a href="javascript:displayDatePicker('txdTkhTandaMula',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> #end <span class="style52">dd/mm/yyyy</span>    </label></td>
  </tr>
  <tr>
    <td width="1%" align="left"><span class="style1">*</span></td>
    <td width="28%" align="left">Tarikh Tanda Hingga</td>
    <td>:</td>
    <td width="70%"><label>
      <input name="txdTkhTandaHingga" type="text" id="txdTkhTandaHingga" value="$TarikhTandaHingga" size="10" class="$disabled" $disabled $readonly onblur="checking_validation(this,'tarikh_hingga_check','yes','tanda hingga','tarikh');">
    #if( $readonly != 'readonly')  <a href="javascript:displayDatePicker('txdTkhTandaHingga',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> #end<span class="style52"> dd/mm/yyyy</span>    </label></td>
  </tr>
  <tr>
    <td width="1%" align="left"><span class="style1">*</span></td>
    <td width="28%" align="left">Tarikh Lawat Periksa</td>
    <td>:</td>
    <td width="70%"><label>
      <input name="txdTkhLawatPeriksa" type="text" id="txdTkhLawatPeriksa" value="$TarikhLawatPeriksa" size="10" class="$disabled" $disabled $readonly onblur="checking_validation(this,'tarikh_lawat_periksa_check','yes','lawat periksa','tarikh');">
    #if( $readonly != 'readonly') <a href="javascript:displayDatePicker('txdTkhLawatPeriksa',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> #end<span class="style52"> dd/mm/yyyy</span>    </label></td>
  </tr>
  <tr>
    <td align="left"><span class="style1">*</span></td>
    <td align="left">Tarikh Diluluskan</td>
    <td>:</td>
    <td><input name="txdTarikhLulus" type="text" class="$disabled" id="txdTarikhLulus" onblur="checking_validation(this,'tarikh_lulus_check','yes','diluluskan','tarikh');" value="$TarikhLulus" size="10" $disabled $readonly/>
    #if( $readonly != 'readonly') <a href="javascript:displayDatePicker('txdTarikhLulus',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> #end<span class="style52"> dd/mm/yyyy</span>    </label>
    </td>
  </tr>
  <tr>
    <td width="1%" align="left"><span class="style1">*</span></td>
    <td width="28%" align="left">No. Ruj. Agensi</td>
    <td>:</td>
    <td width="70%"><input name="txtNoRujAgensi" type="text" id="txtNoRujAgensi" value="$rujAgensi" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" class="$disabled" $disabled $readonly /></td>
  </tr>
  <tr>
    <td width="1%" align="left"><span class="style1">*</span></td>
    <td width="28%" align="left">Nama Pegawai</td>
    <td>:</td>
    <td width="70%"><input name="txtNamaPegawai" type="text" id="txtNamaPegawai" value="$namaPegawai" size="45" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" class="$disabled" $disabled $readonly /></td>
  </tr>
  <tr>
    <td width="1%" align="left"><span class="style1">*</span></td>
    <td width="28%" align="left">Alamat Juruukur Berlesen yang dilantik</td>
    <td>:</td>
    <td width="70%"><input name="txtAlamat1Jurukur" type="text" id="txtAlamat1Jurukur" value="$Alamat1" size="45" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" class="$disabled" $disabled $readonly /></td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td width="28%" align="left">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="70%"><input name="txtAlamat2Jurukur" type="text" id="txtAlamat2Jurukur" value="$Alamat2" size="45" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" class="$disabled" $disabled $readonly /></td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td width="28%" align="left">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="70%"><input name="txtAlamat3Jurukur" type="text" id="txtAlamat3Jurukur" value="$Alamat3" size="45" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" class="$disabled" $disabled $readonly /></td>
  </tr>
  <tr>
    <td width="1%" align="left"><span class="style1">*</span></td>
    <td width="28%" align="left">Poskod</td>
    <td>&nbsp;</td>
    <td width="70%"><input name="txtPoskod" type="text" id="txtPoskod" value="$Poskod" size="6" class="$disabled" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $disabled $readonly /></td>
  </tr>
  <tr>
    <td width="1%" align="left"><span class="style1">*</span></td>
    <td width="28%" align="left">Negeri</td>
    <td>&nbsp;</td>
    <td width="70%">$SelectNegeri</td>
  </tr>
  <tr>
    <td width="1%" align="left"><span class="style1">*</span></td>
    <td width="28%" align="left">Bandar</td>
    <td>&nbsp;</td>
    <td width="70%">$SelectBandar</td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td width="28%" align="left">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="70%">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="7" align="center">
        #if($mode == 'newTanda')
       <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick = "simpan('$idPermohonan')"/>
       <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick = "batal()"/>
    	#end
    	#if($mode == 'viewTanda')
     	<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskini()"/>
      	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="setTable('tableReport1')"/>
     	<input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="hapus()"/>
   		#end
    	#if($mode == 'kemaskiniTanda') 
      	<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="simpan_update()"/>
      	<input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal_update()"/>
      	#end
      	<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>    </td>
  </tr>
</table>



</fieldset>
<p>
  <input type="hidden" name="id_fail" value="$idFail" />
  <input type="hidden" name="id_permohonan" value="$idPermohonan" />
  <input type="hidden" name="id_tandakawasan" value="$idTandaKawasan" />
</p>
<!--
<fieldset>
<legend><strong>Senarai Penandaan Kawasan</strong></legend>
#if($mode != 'newTanda')
      <input type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onclick = "tambah()" />
      #end 
<table width="100%">
  <tr class = "table_header">
    <td><strong>Bil.</strong></td>
    <td><strong>No.Ruj.Agensi</strong></td>
    <td><strong>Nama Pegawai</strong></td>
    <td><strong>Status Tanda</strong></td>
    <td><strong>Status Laksana</strong></td>
    <td><strong>Tarikh Tanda Dari</strong></td>
    <td><strong>Tarikh Lawat Periksa</strong></td>
  </tr>
  #foreach ($fail in $TandaList)
  
  #if ($fail.bil == '') 
  #set ($row = 'row1')
  #elseif ($fail.bil % 2 != 0)
  #set ($row = 'row1')
  #else 
  #set ($row = 'row2')
  #end
  <tr>
    <td class="$row">$fail.bil</td>
     #if ($fail.bil != '') 
    <td class="$row"><a href="javascript:viewTanda('$fail.id_tandakawasan')"><font color="blue">$fail.norujagensi</font></a></td>
    #else
     <td class="$row">$fail.norujagensi</td>
    #end
    <td class="$row">$fail.namapegawai</td>
    <td class="$row">$fail.statustanda</td>
    <td class="$row">$fail.statuslaksana</td>
    <td class="$row">$fail.tarikhmula</td>
    <td class="$row">$fail.tarikhlawat</td>
  </tr>
  #end
</table>
</fieldset>
-->
<p>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#"onClick="javascript:cetakSuratTanda('$idFail')"><font color="blue">Surat Kepada Agensi Pemohon - Makluman Penandaan Kawasan </font></a></td>
      </tr>           
    </table>
</fieldset>
<script>
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakSuratTanda(id_fail) {
	<!--var url = "../servlet/ekptg.report.ppt.SuratRujukan?idfail="+id_fail;-->
	var url = "../servlet/ekptg.report.ppt.SuratKepadaAPSupayaMelakukanPenandaanKawasan?idFail="+id_fail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
function simpan(id_permohonan){
	
	if(document.${formName}.socStatusTanda.value == "0"){
		alert("Sila pilih \"Status Tanda\" terlebih dahulu.");
  		document.${formName}.socStatusTanda.focus(); 
		return;
	}
	if(document.${formName}.socStatusLaksana.value == "0"){
		alert("Sila pilih \"Status Laksana\" terlebih dahulu.");
  		document.${formName}.socStatusLaksana.focus(); 
		return;
	}
	if(document.${formName}.txdTkhTandaMula.value == ""){
		alert("Sila masukkan \"Tarikh Tanda Dari\" terlebih dahulu.");
  		document.${formName}.txdTkhTandaMula.focus(); 
		return;
	}
	if(document.${formName}.txdTkhTandaHingga.value == ""){
		alert("Sila masukkan \"Tarikh Tanda Hingga\" terlebih dahulu.");
  		document.${formName}.txdTkhTandaHingga.focus(); 
		return;
	}
	if(document.${formName}.txdTkhLawatPeriksa.value == ""){
		alert("Sila masukkan \"Tarikh Lawat Periksa\" terlebih dahulu.");
  		document.${formName}.txdTkhLawatPeriksa.focus(); 
		return;
	}
	if(document.${formName}.txdTarikhLulus.value == ""){
		alert("Sila masukkan \"Tarikh Diluluskan\" terlebih dahulu.");
  		document.${formName}.txdTarikhLulus.focus(); 
		return;
	}
	if(document.${formName}.txtNoRujAgensi.value == ""){
		alert("Sila masukkan \"No. Ruj. Agensi\" terlebih dahulu.");
  		document.${formName}.txtNoRujAgensi.focus(); 
		return;
	}
	if(document.${formName}.txtNamaPegawai.value == ""){
		alert("Sila masukkan \"Nama Pegawai\" terlebih dahulu.");
  		document.${formName}.txtNamaPegawai.focus(); 
		return;
	}
	if(document.${formName}.txtAlamat1Jurukur.value == ""){
		alert("Sila masukkan \"Alamat Jurukur Berlesen Yang Dilantik\" terlebih dahulu.");
  		document.${formName}.txtAlamat1Jurukur.focus(); 
		return;
	}
	if(document.${formName}.txtPoskod.value == ""){
		alert("Sila masukkan \"Poskod\" terlebih dahulu.");
  		document.${formName}.txtPoskod.focus(); 
		return;
	}
	if(document.${formName}.txtPoskod.value == ""){
		alert("Sila pilih \"Negeri\" terlebih dahulu.");
  		document.${formName}.txtPoskod.focus(); 
		return;
	}
	if(document.${formName}.socBandar.value == ""){
		alert("Sila pilih \"Bandar\" terlebih dahulu.");
  		document.${formName}.socBandar.focus(); 
		return;
	}
	
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraTandaKwsn&action=addTanda";
	document.${formName}.submit();
}

function batal(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraTandaKwsn&action=newTanda";
	document.${formName}.submit();
}

function kemaskini(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraTandaKwsn&action=kemaskiniTanda";
	document.${formName}.submit();
}
function viewTanda(id_tandakawasan){
	
	document.${formName}.id_tandakawasan.value = id_tandakawasan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraTandaKwsn&action=viewTandaKwsn";
	document.${formName}.submit();
}
function kembali(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraTandaKwsn";
	document.${formName}.submit();
}

function simpan_update(){
	
	if(document.${formName}.socStatusTanda.value == "0"){
		alert("Sila pilih \"Status Tanda\" terlebih dahulu.");
  		document.${formName}.socStatusTanda.focus(); 
		return;
	}
	if(document.${formName}.socStatusLaksana.value == "0"){
		alert("Sila pilih \"Status Laksana\" terlebih dahulu.");
  		document.${formName}.socStatusLaksana.focus(); 
		return;
	}
	if(document.${formName}.txdTkhTandaMula.value == ""){
		alert("Sila masukkan \"Tarikh Tanda Dari\" terlebih dahulu.");
  		document.${formName}.txdTkhTandaMula.focus(); 
		return;
	}
	if(document.${formName}.txdTkhTandaHingga.value == ""){
		alert("Sila masukkan \"Tarikh Tanda Hingga\" terlebih dahulu.");
  		document.${formName}.txdTkhTandaHingga.focus(); 
		return;
	}
	if(document.${formName}.txdTkhLawatPeriksa.value == ""){
		alert("Sila masukkan \"Tarikh Lawat Periksa\" terlebih dahulu.");
  		document.${formName}.txdTkhLawatPeriksa.focus(); 
		return;
	}
	if(document.${formName}.txdTarikhLulus.value == ""){
		alert("Sila masukkan \"Tarikh Diluluskan\" terlebih dahulu.");
  		document.${formName}.txdTarikhLulus.focus(); 
		return;
	}
	if(document.${formName}.txtNoRujAgensi.value == ""){
		alert("Sila masukkan \"No. Ruj. Agensi\" terlebih dahulu.");
  		document.${formName}.txtNoRujAgensi.focus(); 
		return;
	}
	if(document.${formName}.txtNamaPegawai.value == ""){
		alert("Sila masukkan \"Nama Pegawai\" terlebih dahulu.");
  		document.${formName}.txtNamaPegawai.focus(); 
		return;
	}
	if(document.${formName}.txtAlamat1Jurukur.value == ""){
		alert("Sila masukkan \"Alamat Jurukur Berlesen Yang Dilantik\" terlebih dahulu.");
  		document.${formName}.txtAlamat1Jurukur.focus(); 
		return;
	}
	if(document.${formName}.txtPoskod.value == ""){
		alert("Sila masukkan \"Poskod\" terlebih dahulu.");
  		document.${formName}.txtPoskod.focus(); 
		return;
	}
	if(document.${formName}.txtPoskod.value == ""){
		alert("Sila pilih \"Negeri\" terlebih dahulu.");
  		document.${formName}.txtPoskod.focus(); 
		return;
	}
	if(document.${formName}.socBandar.value == ""){
		alert("Sila pilih \"Bandar\" terlebih dahulu.");
  		document.${formName}.socBandar.focus(); 
		return;
	}
	
		
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraTandaKwsn&action=simpanUpdateTanda";
	document.${formName}.submit();
}

function batal_update(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraTandaKwsn&action=viewTandaKwsn";
	document.${formName}.submit();
}

function hapus(){

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraTandaKwsn&action=hapusTanda";
	document.${formName}.submit();
}

function tambah(){

	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraTandaKwsn&action=newTambah";
	document.${formName}.submit();
}
function doChangeidNegeri() {
    doAjaxCall${formName}("doChangeidNegeri");
}
function doChangeidNegeriUpdate() {
    doAjaxCall${formName}("doChangeidNegeriUpdate");
}
function checking_validation(field,point,mandatory,value_field,jenis_field){	
    	
	var lepas_or_xlepas = 2;	
	
	
	if(jenis_field == "tarikh")
	{
	var checkstr = "0123456789";
	var DateField = field;
	var Datevalue = "";
	var DateTemp = "";
	var seperator = "/";
	var day;
	var month;
	var year;
	var leap = 0;
	var err = 0;
	var i;
    err = 0;
	
	
	   DateValue = DateField.value;
	   
	   if(DateValue == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }
	   else
	   {
	   
	   for (i = 0; i < DateValue.length; i++) {
		  if (checkstr.indexOf(DateValue.substr(i,1)) >= 0) {
		     DateTemp = DateTemp + DateValue.substr(i,1);
		  }
	   }
	   DateValue = DateTemp;
	   /* Always change date to 8 digits - string*/
	   /* if year is entered as 2-digit / always assume 20xx */
	   if (DateValue.length == 6) {
	      DateValue = DateValue.substr(0,4) + '20' + DateValue.substr(4,2); }
	   if (DateValue.length != 8) {
	      err = 19;}
	   /* year is wrong if year = 0000 */
	   year = DateValue.substr(4,4);
	   if (year == 0) {
	      err = 20;
	   }
	   /* Validation of month*/
	   month = DateValue.substr(2,2);
	   if ((month < 1) || (month > 12)) {
	      err = 21;
	   }
	   /* Validation of day*/
	   day = DateValue.substr(0,2);
	   if (day < 1) {
	     err = 22;
	   }
	   /* Validation leap-year february / day */
	   if ((year % 4 == 0) || (year % 100 == 0) || (year % 400 == 0)) {
	      leap = 1;
	   }
	   if ((month == 2) && (leap == 1) && (day > 29)) {
	      err = 23;
	   }
	   if ((month == 2) && (leap != 1) && (day > 28)) {
	      err = 24;
	   }
	   /* Validation of other months */
	   if ((day > 31) && ((month == "01") || (month == "03") || (month == "05") || (month == "07") || (month == "08") || (month == "10") || (month == "12"))) {
	      err = 25;
	   }
	   if ((day > 30) && ((month == "04") || (month == "06") || (month == "09") || (month == "11"))) {
	      err = 26;
	   }
	   /* if 00 ist entered, no error, deleting the entry */
	   if ((day == 0) && (month == 0) && (year == 00)) {
	      err = 0; day = ""; month = ""; year = ""; seperator = "";
	   }
	   /* if no error, write the completed date to Input-Field (e.g. 13.12.2001) */
	   if (err == 0) {
	      DateField.value = day + seperator + month + seperator + year;
		   
	   }
	  
	   else { 
	   
		  
	      DateField.select();
		 // DateField.focus();
		  lepas_or_xlepas = "3";
		 
	  
	   
	   
	   }
	  } 
	   //alert(lepas_or_xlepas);
	   if(lepas_or_xlepas == "1")
	   {
	       var tarikh_valid = "valid";
	       var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
			
		   var str1  = DateField.value;
		   
		   var dt1   = parseInt(str1.substring(0,2),10);
		   var mon1  = parseInt(str1.substring(3,5),10)-1;
		   var yr1   = parseInt(str1.substring(6,10),10);
		   
		   var date = new Date(yr1, mon1, dt1);
		 
		  if (date > currentTime)
		  {			  
		  tarikh_valid = "xvalid";			
		  }
	   
	   
	   
	   if(tarikh_valid == "valid")
	   {	   
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   }
	   else
	   {
	  
	   
	   document.${formName}.alert_message.value  = "Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh hari ini";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	 //  DateField.focus();
	   
	   }
	   
	   
	   }
	   
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	 //  DateField.select();
	//   DateField.focus();
	   }
	   
	   if(lepas_or_xlepas == "3")
	   {
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+" dengan betul";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	//   DateField.select();
	//   DateField.focus();
	   
	   }
	   
	   }
	   
	   
	   if(jenis_field == "normal")
	   {
	   
	   if(field.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }   
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	   document.${formName}.alert_message.value  = "Sila masukkan "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	
	   }
	   else
	   {
	    document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	   }
	   
	   	   
	   }
	   
	   
	   
	
	}
</script>
