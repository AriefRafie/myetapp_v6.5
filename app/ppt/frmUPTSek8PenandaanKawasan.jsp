#parse("app/ppt/Sek8Paging.jsp")

#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")


<fieldset id="top">
<legend><strong>Maklumat Penandaan Kawasan</strong></legend>

	<!-- parse("app/ppt/frmPPTHeaderHM.jsp") -->
	#parse("app/ppt/frmPPTHeader.jsp")

<br/>

	
	<fieldset id="bottom">
	<legend><strong>Penandaan Kawasan</strong></legend>
	
		#if($mode=="new")
		<table width="100%" border="0">
	
			<tr>
				<td>Status Tanda</td>
				<td>:</td>
				<td><select name="socStatusTanda" style="width:auto">

					#if($socStatusTanda=="1")
					<option value="1">DITANDA</option>	
      				<option value="2">TIDAK DITANDA</option>
      				<option value="">SILA PILIH</option>  
					#elseif($socStatusTanda=="2")
					<option value="2">TIDAK DITANDA</option>
      				<option value="1">DITANDA</option>	
      				<option value="">SILA PILIH</option> 	
					#else
					<option value="">SILA PILIH</option>    
      				<option value="1">DITANDA</option>	
      				<option value="2">TIDAK DITANDA</option>
					#end
    
				</select></td>
			</tr>
			
			<!-- <tr>
				<td>Status Laksana</td>
				<td>:</td>
				<td colspan="4"><input type="text" name="txtStatusLaksana" id="txtStatusLaksana" value="$!txtStatusLaksana" size="10" maxlength="30"   ></td>
			</tr> -->
			
			<tr>
				<td>Cara Laksana</td>
				<td>:</td>
				<td><select name="socStatusLaksana" style="width:auto">

					#if($socStatusLaksana=="1")
					<option value="1">DILAKUKAN SENDIRI</option>	
      				<option value="2">BANTUAN AGENSI</option>
      				<option value="">SILA PILIH</option>  
					#elseif($socStatusLaksana=="2")
					<option value="2">BANTUAN AGENSI</option> 
      				<option value="1">DILAKUKAN SENDIRI</option>	
      				<option value="">SILA PILIH</option>   
					#else
					<option value="">SILA PILIH</option>    
      				<option value="1">DILAKUKAN SENDIRI</option>	
      				<option value="2">BANTUAN AGENSI</option>
					#end
    
				</select></td>
			</tr>
			
			<tr>
				<td>No.Rujukan Agensi</td>
				<td>:</td>
				<td colspan="4"><input type="text" name="txtNoRujAgensi" id="txtNoRujAgensi" value="$!txtNoRujAgensi" size="20" maxlength="20"   ></td>
			</tr>
			
			<!-- <tr>
				<td>Nama Pegawai</td>
				<td>:</td>
				<td colspan="4"><input type="text" name="txtNamaPegawai" id="txtNamaPegawai" value="$!txtNamaPegawai" size="50" maxlength="80"   ></td>
			</tr> -->
			
			<tr>
				<td width="35%">Tarikh Tanda Dari</td>
				<td width="1%">:</td>
				<td width="27%"><input name="txdTarikhTandaDari" value="$!txdTarikhTandaDari" size="11" id="txdTarikhTandaDari" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
					<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTandaDari',false,'dmy');">&nbsp;$!frmtdate</td>
				<td width="8%">Hingga</td>
				<td width="1%">:</td>
				<td width="28%"><input name="txdTarikhTandaHingga" value="$!txdTarikhTandaHingga" size="11" id="txdTarikhTandaHingga" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
					<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTandaHingga',false,'dmy');">&nbsp;$!frmtdate</td>
			</tr>
			<tr>
				<td>Tarikh Lawatan Penandaan</td>
				<td>:</td>
				<td colspan="4"><input name="txdTarikhLawat" value="$!txdTarikhLawat" size="11" id="txdTarikhLawat" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
					<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhLawat',false,'dmy');">&nbsp;$!frmtdate</td>
			</tr>
			
			<!-- <tr>
				<td>Tarikh Lulus</td>
				<td>:</td>
				<td colspan="4"><input name="txdTarikhLulus" value="$!txdTarikhLulus" size="11" id="txdTarikhLulus" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
					<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhLulus',false,'dmy');">&nbsp;$!frmtdate</td>
			</tr> -->
			
			<tr>
				<td>Alamat Juruukur Berlesen Yang Dilantik</td>
				<td>:</td>
				<td colspan="4"><input type="text" name="txtAlamat1" id="txtAlamat1" value="$!txtAlamat1" size="50" maxlength="80"   ></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td colspan="4"><input type="text" name="txtAlamat2" id="txtAlamat2" value="$!txtAlamat2" size="50" maxlength="80"   ></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td colspan="4"><input type="text" name="txtAlamat3" id="txtAlamat3" value="$!txtAlamat3" size="50" maxlength="80"   ></td>
			</tr>
			<tr>
				<td>Poskod</td>
				<td>:</td>
				<td colspan="4"><input type="text" name="txtPoskod" id="txtPoskod" value="$!txtPoskod" size="5" maxlength="5" onkeyup="validateNumber(this,this.value)" onblur="validateNumber(this,this.value)"></td>
			</tr>
			<tr>
				<td>Negeri</td>
				<td>:</td>
				<td colspan="4">$!selectNegeri</td>
			</tr>
			<tr>
				<td>Bandar</td>
				<td>:</td>
				<td colspan="4">$!selectBandar</td>
			</tr>
			<tr><td colspan="3">&nbsp;</td></tr>
		</table>
		#end
		
		
		
		#if($mode=="view")
		
		#if($onchange=="no")
		#foreach($dt in $dataTanda)
			#set($socStatusTanda=$dt.flag_tanda)
			#set($socStatusLaksana=$dt.cara_laksana)
			#set($txtNoRujAgensi=$dt.no_rujagensi)
			#set($txtNamaPegawai=$dt.nama_pegawai)
			#set($txdTarikhTandaDari=$dt.tarikh_mula)
			#set($txdTarikhTandaHingga=$dt.tarikh_akhir)
			#set($txdTarikhLawat=$dt.tarikh_lawat)
			#set($txdTarikhLulus=$dt.tarikh_lulus)
			#set($txtAlamat1=$dt.alamat1_juruukur)
			#set($txtAlamat2=$dt.alamat2_juruukur)
			#set($txtAlamat3=$dt.alamat3_juruukur)
			#set($txtPoskod=$dt.poskod)
		#end
		#end
		
		#if($isEdit=="no")
			#set($disability = "readonly")
			#set($disabilityx = "class=disabled")
			#set($disability1 = "disabled")
			#set($M = "")
		#else
			#set($M = "*")
			#set($disability = "")
			#set($disabilityx = "")
			#set($disability1 = "")
		#end
		
		<table width="100%" border="0">
			
			<!-- <tr>
				<td>Status Tanda</td>
				<td>:</td>
				<td colspan="4"><input $disability $disabilityx type="text" name="txtStatusTanda" id="txtStatusTanda" value="$!txtStatusTanda" size="10" maxlength="30"   ></td>
			</tr> -->
			
			<tr>
				<td>Status Tanda</td>
				<td>:</td>
				<td><select name="socStatusTanda" $disability1 $disabilityx style="width:auto">

					#if($socStatusTanda=="1")
					<option value="1">DITANDA</option>	
      				<option value="2">TIDAK DITANDA</option>
      				<option value="">SILA PILIH</option>  
					#elseif($socStatusTanda=="2")
					<option value="2">TIDAK DITANDA</option>
      				<option value="1">DITANDA</option>	
      				<option value="">SILA PILIH</option> 	
					#else
					<option value="">SILA PILIH</option>    
      				<option value="1">DITANDA</option>	
      				<option value="2">TIDAK DITANDA</option>
					#end
    
				</select></td>
			</tr>
			
			<!-- <tr>
				<td>Status Laksana</td>
				<td>:</td>
				<td colspan="4"><input $disability $disabilityx type="text" name="txtStatusLaksana" id="txtStatusLaksana" value="$!txtStatusLaksana" size="10" maxlength="30"   ></td>
			</tr> -->
			
			<tr>
				<td>Cara Laksana</td>
				<td>:</td>
				<td><select name="socStatusLaksana" $disability1 $disabilityx style="width:auto">

					#if($socStatusLaksana=="1")
					<option value="1">DILAKUKAN SENDIRI</option>	
      				<option value="2">BANTUAN AGENSI</option>
      				<option value="">SILA PILIH</option>  
					#elseif($socStatusLaksana=="2")
					<option value="2">BANTUAN AGENSI</option> 
      				<option value="1">DILAKUKAN SENDIRI</option>	
      				<option value="">SILA PILIH</option>   
					#else
					<option value="">SILA PILIH</option>    
      				<option value="1">DILAKUKAN SENDIRI</option>	
      				<option value="2">BANTUAN AGENSI</option>
					#end
    
				</select></td>
			</tr>
			<tr>
				<td>No.Rujukan Agensi</td>
				<td>:</td>
				<td colspan="4"><input $disability $disabilityx type="text" name="txtNoRujAgensi" id="txtNoRujAgensi" value="$!txtNoRujAgensi" size="20" maxlength="20"   ></td>
			</tr>
			
			<!-- <tr>
				<td>Nama Pegawai</td>
				<td>:</td>
				<td colspan="4"><input $disability $disabilityx type="text" name="txtNamaPegawai" id="txtNamaPegawai" value="$!txtNamaPegawai" size="50" maxlength="80"   ></td>
			</tr> -->
			
			<tr>
				<td width="35%">Tarikh Tanda Dari</td>
				<td width="1%">:</td>
				<td width="27%"><input $disability $disabilityx name="txdTarikhTandaDari" value="$!txdTarikhTandaDari" size="11" id="txdTarikhTandaDari" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
					#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTandaDari',false,'dmy');">&nbsp;$!frmtdate#end</td>
				<td width="8%">Hingga</td>
				<td width="1%">:</td>
				<td width="28%"><input $disability $disabilityx name="txdTarikhTandaHingga" value="$!txdTarikhTandaHingga" size="11" id="txdTarikhTandaHingga" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
					#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTandaHingga',false,'dmy');">&nbsp;$!frmtdate#end</td>
			</tr>
			<tr>
				<td>Tarikh Lawatan Penandaan</td>
				<td>:</td>
				<td colspan="4"><input $disability $disabilityx name="txdTarikhLawat" value="$!txdTarikhLawat" size="11" id="txdTarikhLawat" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
					#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhLawat',false,'dmy');">&nbsp;$!frmtdate#end</td>
			</tr>
			
			<!-- <tr>
				<td>Tarikh Lulus</td>
				<td>:</td>
				<td colspan="4"><input $disability $disabilityx name="txdTarikhLulus" value="$!txdTarikhLulus" size="11" id="txdTarikhLulus" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
					#if($isEdit=="yes")<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhLulus',false,'dmy');">&nbsp;$!frmtdate#end</td>
			</tr> -->
			
			<tr>
				<td>Alamat Juruukur Berlesen Yang Dilantik</td>
				<td>:</td>
				<td colspan="4"><input $disability $disabilityx type="text" name="txtAlamat1" id="txtAlamat1" value="$!txtAlamat1" size="50" maxlength="80"   ></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td colspan="4"><input $disability $disabilityx type="text" name="txtAlamat2" id="txtAlamat2" value="$!txtAlamat2" size="50" maxlength="80"   ></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td colspan="4"><input $disability $disabilityx type="text" name="txtAlamat3" id="txtAlamat3" value="$!txtAlamat3" size="50" maxlength="80"   ></td>
			</tr>
			<tr>
				<td>Poskod</td>
				<td>:</td>
				<td colspan="4"><input $disability $disabilityx type="text" name="txtPoskod" id="txtPoskod" value="$!txtPoskod" size="5" maxlength="5" onkeyup="validateNumber(this,this.value)" onblur="validateNumber(this,this.value)"></td>
			</tr>
			<tr>
				<td>Negeri</td>
				<td>:</td>
				<td colspan="4">$!selectNegeri</td>
			</tr>
			<tr>
				<td>Bandar</td>
				<td>:</td>
				<td colspan="4">$!selectBandar</td>
			</tr>
			<tr><td colspan="3">&nbsp;</td></tr>
		</table>
		#end
		
	</fieldset>



	<table width="100%" border="0">
		<tr align="center">
			<td>
			<input type="button" name="cmdHantarEmel" value ="Hantar Emel" onClick="javascript:hantarEmel('$!id_permohonan')">
			#if($mode=="new")
			<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanPenandaan('$!id_permohonan','$!id_tandakawasan','$!mode')">
			#end
				
			#if($mode=="view")
				#if($isEdit=="no")
				<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniPenandaan('$!id_permohonan')">
				#else
				<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanPenandaan('$!id_permohonan','$!id_tandakawasan','$!mode')">
				<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batal('$!id_permohonan')">
				#end
			#end
				
				<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:kembali('$!id_permohonan')">
				</td>
			</tr>
	</table>
	
</fieldset>

<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_tandakawasan" value="$!id_tandakawasan">
<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>


#if($showAlertPaging=="yes")
<script>
alert("Sila Klik 'Paging' No.10 / No.11 untuk Proses Seksyen 8");
</script>
#end 




<script>
function onchangeNegeriUpdate(){
	document.${formName}.ScreenLocation.value = "bottom";	
	document.${formName}.command.value = "penandaanKawasan";
	document.${formName}.command2.value = "kemaskiniPenandaan";
	document.${formName}.command3.value = "onchangeNegeriUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8PenandaanKawasan";
	document.${formName}.submit();
}
function onchangeNegeri(){
	document.${formName}.ScreenLocation.value = "bottom";	
	document.${formName}.command.value = "penandaanKawasan";
	document.${formName}.command2.value = "onchangeNegeri";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8PenandaanKawasan";
	document.${formName}.submit();
}
function kemaskiniPenandaan(idpermohonan){

	document.${formName}.ScreenLocation.value = "bottom";	
	document.${formName}.id_permohonan.value = idpermohonan;
	document.${formName}.command.value = "penandaanKawasan";
	document.${formName}.command2.value = "kemaskiniPenandaan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8PenandaanKawasan";
	document.${formName}.submit();
}
function batal(id_permohonan) {

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "penandaanKawasan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8PenandaanKawasan";
	document.${formName}.submit();
}
function simpanPenandaan(idpermohonan,id_tandakawasan,mode){

	var dat1=document.${formName}.txdTarikhTandaDari
	var dat2=document.${formName}.txdTarikhTandaHingga
	var dat3=document.${formName}.txdTarikhLawat
	//var dat4=document.${formName}.txdTarikhLulus
	
	//current date
	var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate = new Date(year, month, day);

	//tarikh dari
	var TD  = document.getElementById("txdTarikhTandaDari").value;
	var dt1   = parseInt(TD.substring(0,2),10);
   	var mon1  = parseInt(TD.substring(3,5),10);
   	var yr1   = parseInt(TD.substring(6,10),10);
   	var dateMula = new Date(yr1, mon1, dt1);

  	//tarikh tamat
	var TT  = document.getElementById("txdTarikhTandaHingga").value;
	var dt2   = parseInt(TT.substring(0,2),10);
   	var mon2  = parseInt(TT.substring(3,5),10);
   	var yr2   = parseInt(TT.substring(6,10),10);
   	var dateTamat = new Date(yr2, mon2, dt2);

    //tarikh lawat tapak
	var TL  = document.getElementById("txdTarikhLawat").value;
	var dt3   = parseInt(TL.substring(0,2),10);
   	var mon3  = parseInt(TL.substring(3,5),10);
   	var yr3   = parseInt(TL.substring(6,10),10);
   	var dateLawat = new Date(yr3, mon3, dt3);

  	//tarikh 
	//var TLL  = document.getElementById("txdTarikhLulus").value;
	//var dt4   = parseInt(TLL.substring(0,2),10);
   	//var mon4  = parseInt(TLL.substring(3,5),10);
   	//var yr4   = parseInt(TLL.substring(6,10),10);
   	//var dateLulus = new Date(yr4, mon4, dt4);
	
	if (dat1.value!="" && isDate(dat1.value)==false)
	{
		dat1.focus()
		return;
	}
/*	else if(dat1.value!="" && (dateMula < currentDate)){
	   	alert("Sila pastikan \"Tarikh Mula Tanda\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhTandaDari.focus();
		return;
	}
*/	else if (dat2.value!="" && isDate(dat2.value)==false)
	{
		dat2.focus()
		return;
	}
/*	else if(dat2.value!="" && (dateTamat < currentDate)){
	   	alert("Sila pastikan \"Tarikh Tamat Tanda\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhTandaHingga.focus();
		return;
	}
*/	else if(dat2.value!="" && dat1.value!="" && (dateTamat < dateMula)){
	   	alert("Sila pastikan \"Tarikh Tamat Tanda\" tidak kurang dari \"Tarikh Mula Tanda\".");
		document.${formName}.txdTarikhTandaHingga.focus();
		return;
	}
	else if (dat3.value!="" && isDate(dat3.value)==false)
	{
		dat3.focus()
		return;
	}
/*	else if(dat3.value!="" && (dateLawat < currentDate)){
	   	alert("Sila pastikan \"Tarikh Lawat Periksa\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhLawat.focus();
		return;
	}
	else if (dat4.value!="" && isDate(dat4.value)==false)
	{
		dat4.focus()
		return;
	}
	else if(dat4.value!="" && (dateLulus < currentDate)){
	   	alert("Sila pastikan \"Tarikh Lulus\" tidak kurang dari tarikh hari ini.");
		document.${formName}.txdTarikhLulus.focus();
		return;
	}
*/	else
	if (document.${formName}.txtPoskod.value != "" && document.${formName}.txtPoskod.value.length < 5) {
		alert("Sila masukkan nombor poskod dengan selengkapnya");
		document.${formName}.txtPoskod.focus();
	}
	else{
		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "bottom";	
		document.${formName}.id_permohonan.value = idpermohonan;
	
		if(mode=="view"){
			document.${formName}.id_tandakawasan.value = id_tandakawasan;
			document.${formName}.command.value = "penandaanKawasan";
			document.${formName}.command2.value = "kemaskiniPenandaan";
			document.${formName}.command3.value = "updatePenandaan";
		}else{
			document.${formName}.command.value = "penandaanKawasan";
			document.${formName}.command2.value = "simpanPenandaan";
		}
	
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8PenandaanKawasan";
		document.${formName}.submit();
	}
}
function kembali(id_permohonan){
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewListHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8PenandaanKawasan";
	document.${formName}.submit();
}

function hantarEmel(id_permohonan) {
	//alert(id_permohonan);
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "penandaanKawasan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek8PenandaanKawasan&FlagHantarEmel=Y";
	document.${formName}.submit();
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

function validateTarikh(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric2(content);
		return;
	}
}
function RemoveNonNumeric2( strString )
{
      var strValidCharacters = "1234567890./";
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
</script>

<script>
//Declaring valid date character, minimum year and maximum year
var dtCh= "/";
var minYear=1900;
var maxYear=2100;

function isInteger(s){
	var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}

function stripCharsInBag(s, bag){
	var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function daysInFebruary (year){
	// February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
		if (i==2) {this[i] = 29}
   } 
   return this
}

function isDate(dtStr){
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strDay=dtStr.substring(0,pos1)
	var strMonth=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
		alert("Format tarikh mestilah seperti ini, dd/mm/yyyy")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Sila masukkan bulan yang sah")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Sila masukkan hari yang sah")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Sila masukkan tahun yang sah antara "+minYear+" dan "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Sila masukkan tarikh yang sah")
		return false
	}
return true
}
</script>