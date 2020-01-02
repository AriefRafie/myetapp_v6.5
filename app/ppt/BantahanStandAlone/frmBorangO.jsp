#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")


	#if($mode=="view")
		
		#if($onchange=="no")
			#foreach($data in $dataBorangO)
				#set($txdTarikhBorangO=$data.TARIKH_BORANGO)
				#set($txdTarikhHantarBorangO=$data.TARIKH_HANTAR_BORANGO)
				#set($txtNamaPenghantar=$data.NAMA_PENGHANTAR)
				#set($txtNamaPenerima=$data.NAMA_PENERIMA_BORANGO)
				
				#set($txtAlamat1=$data.ALAMAT1)
				#set($txtAlamat2=$data.ALAMAT2)
				#set($txtAlamat3=$data.ALAMAT3)
				#set($txtPoskod=$data.POSKOD)
	
			#end
		#end
		
		#if($isEdit=="no")
			#set($disability = "readonly")
			#set($disabilityx = "class=disabled")
			#set($disability1 = "disabled")
			#set($M = "")
		#else
			#set($M = "<font color='red'>*</font>")
			#set($disability = "")
			#set($disabilityx = "")
			#set($disability1 = "")
		#end
	
	#else
		#set($M = "<font color='red'>*</font>")
		#set($disability = "")
		#set($disabilityx = "")
		#set($disability1 = "")
	#end
	

<fieldset id="top">
<legend><strong>BORANG O</strong></legend>

#if($sizeDataBantahan!=0)
#parse("app/ppt/BantahanStandAlone/frmPaging.jsp")
#end

<fieldset>
	
	<table width="100%" border="0">
		<tr class="table_header"><td>&nbsp;<b>Maklumat Borang O</b></td></tr>
	</table>
	
	<table width="100%" border="0">
		<tr>
  			<td width="1%">$M</td>
  			<td width="30%">Tarikh Borang O</td>
  			<td width="1%">:</td>
  			<td width="68%"><input $!disability $!disabilityx name="txdTarikhBorangO" id="txdTarikhBorangO" size="11" type="text" value="$!txdTarikhBorangO" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            #if($mode=="new" || ($mode=="view" && $isEdit=="yes"))<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangO',false,'dmy');">&nbsp;$!frmtdate #end</td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>Mahkamah Tinggi</td>
  			<td>:</td>
  			<td>$!selectMahkamahTinggi</td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>Alamat</td>
  			<td>:</td>
  			<td><input type="text" name="txtAlamat1" id="txtAlamat1" value="$!txtAlamat1" size="50" class="disabled" readonly /></td>
  		</tr>
  		<tr>
  			<td colspan="3">&nbsp;</td>
  			<td><input type="text" name="txtAlamat2" id="txtAlamat2" value="$!txtAlamat2" size="50" class="disabled" readonly /></td>
  		</tr>
  		<tr>
  			<td colspan="3">&nbsp;</td>
  			<td><input type="text" name="txtAlamat3" id="txtAlamat3" value="$!txtAlamat3" size="50" class="disabled" readonly /></td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>Poskod</td>
  			<td>:</td>
  			<td><input type="text" name="txtPoskod" id="txtPoskod" value="$!txtPoskod" size="6" maxlength="5" class="disabled" readonly /></td>
  		</tr> 
  		<tr>
  			<td>&nbsp;</td>
  			<td>Negeri</td>
  			<td>:</td>
  			<td>$!selectNegeri</td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>Bandar</td>
  			<td>:</td>
  			<td>$!selectBandar</td>
  		</tr>
  		
  	</table>
  	
  	<br/>
  	
  	<table width="100%" border="0">
		<tr class="table_header"><td>&nbsp;<b>Maklumat Penghantar Borang O</b></td></tr>
	</table>
	
	<table width="100%" border="0">
		<tr>
  			<td width="1%">&nbsp;</td>
  			<td width="30%">Tarikh Hantar</td>
  			<td width="1%">:</td>
  			<td width="68%"><input $!disability $!disabilityx name="txdTarikhHantarBorangO" id="txdTarikhHantarBorangO" size="11" type="text" value="$!txdTarikhHantarBorangO" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            #if($mode=="new" || ($mode=="view" && $isEdit=="yes"))<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhHantarBorangO',false,'dmy');">&nbsp;$!frmtdate #end</td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>Nama Penghantar</td>
  			<td>:</td>
  			<td><input type="text" $!disability $!disabilityx name="txtNamaPenghantar" id="txtNamaPenghantar" value="$!txtNamaPenghantar" size="40" maxlength="100" ></td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>Nama Penerima</td>
  			<td>:</td>
  			<td><input type="text" $!disability $!disabilityx name="txtNamaPenerima" id="txtNamaPenerima" value="$!txtNamaPenerima" size="40" maxlength="100" ></td>
  		</tr>
	</table>
	
	<br/>
	
</fieldset>	


</fieldset>

				<table width="100%" border="0">
					<tr align="center">
						<td>
						#if($mode=="new")
						<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanBorangO('$!id_bantahan','$!mode','')">
						#end
				
					#if($mode=="view")
						#if($isEdit=="no")
						<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniBorangO('$!id_bantahan')">
						#else
						<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanBorangO('$!id_bantahan','$!mode','$!id_borango')">
						<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:viewBorangO('$!id_bantahan')">
						#end
					#end
				
						<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:backToList()">
						</td>
					</tr>
				</table>
				
				
<input type="hidden" name="mode" value="$!mode">
<input type="hidden" name="id_bantahan" value="$id_bantahan">
<input type="hidden" name="id_borango" value="$id_borango">

<input type="hidden" name="command2">
<input type="hidden" name="command3">
<input type="hidden" name="paging" value="3">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>


<script>
	
</script>

<script> 
function viewBorangO(id_bantahan) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_bantahan.value = id_bantahan;
	document.${formName}.command.value = "viewBorangO";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
	document.${formName}.submit();
}
function kemaskiniBorangO(id_bantahan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_bantahan.value = id_bantahan;
	document.${formName}.command.value = "viewBorangO";
	document.${formName}.command2.value = "kemaskiniBorangO";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
	document.${formName}.submit();
}
function doChangeAlamatMahkamah() {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "viewBorangO";
	document.${formName}.command2.value = "doChangeAlamatMahkamah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
	document.${formName}.submit();
}
function backToList() {
	document.${formName}.command.value = "else";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
	document.${formName}.submit();
}
function simpanBorangO(id_bantahan,mode,id_borango) {

	if(document.${formName}.txdTarikhBorangO.value == ""){
		alert("Sila masukkan \"Tarikh Borang O\" terlebih dahulu.");
  		document.${formName}.txdTarikhBorangO.focus(); 
		return;
	}else{		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "top";
		document.${formName}.id_bantahan.value = id_bantahan;
		if(mode=="new"){			
			document.${formName}.command.value = "viewBorangO";
			document.${formName}.command2.value = "simpanBorangO";
		}else{
			document.${formName}.id_borango.value = id_borango;
			document.${formName}.command.value = "viewBorangO";
			document.${formName}.command2.value = "kemaskiniBorangO";
			document.${formName}.command3.value = "updateBorangO";
		}
		
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
		document.${formName}.submit();
	}
}





function kemaskiniDataDeposit(id_bantahan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_bantahan.value = id_bantahan;
	document.${formName}.command.value = "viewDeposit";
	document.${formName}.command2.value = "kemaskiniDataDeposit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
	document.${formName}.submit();
}



window.onload = submitForm;

function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function validateModal(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content!=""){
		var num = content * 1;
		elmnt.value = num.toFixed(2);
		return;
	}else{
		elmnt.value = "";
	}
}
function validateNilai(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric3(content);
		return;
	}
}
function RemoveNonNumeric3( strString )
{
      var strValidCharacters = "1234567890.";
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
      var strValidCharacters = "1234567890/.";
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