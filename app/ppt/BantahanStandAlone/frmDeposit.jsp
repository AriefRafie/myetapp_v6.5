#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

	#if($mode=="view")
		
		#if($onchange=="no")
			#foreach($data in $dataBantahan)
				#set($txtAmaunTuntutan=$data.AMAUN_TUNTUTAN)
				#set($txtAmaunPampasan=$data.AMAUN_PAMPASAN)
				#set($txtNoCek=$data.NO_RUJUKAN_BAYARAN)
				#set($txtNoAkaun=$data.NO_AKAUN)
				#set($socCaraBayaran=$data.CARA_BAYAR)
				#set($txtNamaBank=$data.NAMA_BANK)
				#set($txdTarikhTerimaResit=$data.TARIKH_TERIMA_RESIT)
				#set($txdTarikhResit=$data.TARIKH_RESIT)
				#set($txtNoResit=$data.no_resit)
				#set($txtDeposit=$data.AMAUN_DEPOSIT)
				#set($socCaraBayaranDeposit=$data.CARA_BAYAR_DEPOSIT)
				#set($txtNoCekDeposit=$data.NO_RUJUKAN_BAYARAN_DEPOSIT)
				#set($txtNoAkaunDeposit=$data.NO_AKAUN_DEPOSIT)
				#set($txtNamaBankDeposit=$data.NAMA_BANK_DEPOSIT)
				#set($txdTarikhPulang=$data.TARIKH_SURAT_BAYARDEPOSIT)
				#set($socStatusPulang=$data.FLAG_TERIMADEPOSIT)
				#set($txtCatatanPulang=$data.CATATAN_PEMULANGAN_DEPOSIT)
				#set($txdTarikhHantarDeposit=$data.TARIKH_AKHIR_BAYARDEPOSIT)
				#set($txtNamaPenghantar=$data.NAMA_PENGHANTAR)
				
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
<legend><strong>MAKLUMAT DEPOSIT BANTAHAN</strong></legend>

#if($sizeDataBantahan!=0)
#parse("app/ppt/BantahanStandAlone/frmPaging.jsp")
#end

<fieldset>
	
	<table width="100%" border="0">
		<tr class="table_header"><td>&nbsp;<b>Maklumat Pampasan</b></td></tr>
	</table>
	
	<table width="100%" border="0">
		<tr>
  			<td width="1%">$M</td>
  			<td width="30%">Amaun Pampasan (RM)</td>
  			<td width="1%">:</td>
  			<td width="68%">
  			<input type="text" $!disability $!disabilityx name="txtAmaunPampasan" id="txtAmaunPampasan" value="$!txtAmaunPampasan" size="12" maxlength="8" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtAmaunPampasan')" >
  			</td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>No. Cek</td>
  			<td>:</td>
  			<td><input type="text" $!disability $!disabilityx name="txtNoCek" id="txtNoCek" value="$!txtNoCek" size="23" maxlength="40" ></td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>No. Akaun</td>
  			<td>:</td>
  			<td><input type="text" $!disability $!disabilityx name="txtNoAkaun" id="txtNoAkaun" value="$!txtNoAkaun" size="23" maxlength="40" ></td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>Cara Bayaran</td>
  			<td>:</td>
  			<td><select $!disability1 $!disabilityx name="socCaraBayaran" style="width:180px">
	      			<option value="" #if($socCaraBayaran=="") selected=selected #end >SILA PILIH</option>
	      			<option value="1" #if($socCaraBayaran=="1") selected=selected #end >T - TUNAI</option>
					<option value="2" #if($socCaraBayaran=="2") selected=selected #end >CB - CEK BANK NEGARA</option>
					<option value="5" #if($socCaraBayaran=="5") selected=selected #end >CT - CEK TEMPATAN</option>
					<option value="6" #if($socCaraBayaran=="6") selected=selected #end >CL - CEK LUAR</option>
					<option value="9" #if($socCaraBayaran=="9") selected=selected #end >BD - BANK DRAF</option>
      			</select>
      		</td>	
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>Nama Bank</td>
  			<td>:</td>
  			<td><input type="text" $!disability $!disabilityx name="txtNamaBank" id="txtNamaBank" value="$!txtNamaBank" size="40" maxlength="40" ></td>
  		</tr>
  		
  	</table>
  	
  	<br/>
  	
  	<table width="100%" border="0">
		<tr class="table_header"><td>&nbsp;<b>Maklumat Deposit Bantahan</b></td></tr>
	</table>
	
	<table width="100%" border="0">
		<tr>
  			<td width="1%">$M</td>
  			<td width="30%">Tarikh Terima Resit</td>
  			<td width="1%">:</td>
  			<td width="68%"><input $!disability $!disabilityx name="txdTarikhTerimaResit" id="txdTarikhTerimaResit" size="11" type="text" value="$!txdTarikhTerimaResit" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            #if($mode=="new" || ($mode=="view" && $isEdit=="yes"))<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerimaResit',false,'dmy');">&nbsp;$!frmtdate #end</td>
  		</tr>
  		<tr>
  			<td>$M</td>
  			<td>Tarikh Resit</td>
  			<td>:</td>
  			<td><input $!disability $!disabilityx name="txdTarikhResit" id="txdTarikhResit" size="11" type="text" value="$!txdTarikhResit" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            #if($mode=="new" || ($mode=="view" && $isEdit=="yes"))<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhResit',false,'dmy');">&nbsp;$!frmtdate #end</td>
  		</tr>
  		<tr>
  			<td>$M</td>
  			<td>No. Resit</td>
  			<td>:</td>
  			<td><input type="text" $!disability $!disabilityx name="txtNoResit" id="txtNoResit" value="$!txtNoResit" size="23" maxlength="40" ></td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>Amaun Tuntutan (RM)</td>
  			<td>:</td>
  			<td><input type="text" $!disability $!disabilityx name="txtAmaunTuntutan" id="txtAmaunTuntutan" value="$!txtAmaunTuntutan" size="12" maxlength="8" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtAmaunTuntutan')" ></td>
  		</tr> 
  		<tr>
  			<td>&nbsp;</td>
  			<td>Deposit (RM)</td>
  			<td>:</td>
  			<td>
  			<input type="text" $!disability $!disabilityx name="txtDeposit" id="txtDeposit" value="$!txtDeposit" size="12" maxlength="8" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtDeposit')" >
  			<i><font color='red' style='font-size:9px'>SEKIRANYA 10% DARIPADA AMAUN TUNTUTAN LEBIH ATAU SAMA DENGAN RM3000, NILAI DEPOSIT IALAH RM3000</font></i>
  			</td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>Cara Bayaran</td>
  			<td>:</td>
  			<td><select $!disability1 $!disabilityx name="socCaraBayaranDeposit" style="width:180px">
	      			<option value="" #if($socCaraBayaranDeposit=="") selected=selected #end >SILA PILIH</option>
	      			<option value="1" #if($socCaraBayaranDeposit=="1") selected=selected #end >T - TUNAI</option>
					<option value="2" #if($socCaraBayaranDeposit=="2") selected=selected #end >CB - CEK BANK NEGARA</option>
					<option value="5" #if($socCaraBayaranDeposit=="5") selected=selected #end >CT - CEK TEMPATAN</option>
					<option value="6" #if($socCaraBayaranDeposit=="6") selected=selected #end >CL - CEK LUAR</option>
					<option value="9" #if($socCaraBayaranDeposit=="9") selected=selected #end >BD - BANK DRAF</option>
      			</select>
      		</td>	
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>No. Cek</td>
  			<td>:</td>
  			<td><input type="text" $!disability $!disabilityx name="txtNoCekDeposit" id="txtNoCekDeposit" value="$!txtNoCekDeposit" size="23" maxlength="40" ></td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>No. Akaun</td>
  			<td>:</td>
  			<td><input type="text" $!disability $!disabilityx name="txtNoAkaunDeposit" id="txtNoAkaunDeposit" value="$!txtNoAkaunDeposit" size="23" maxlength="40" ></td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>Nama Bank</td>
  			<td>:</td>
  			<td><input type="text" $!disability $!disabilityx name="txtNamaBankDeposit" id="txtNamaBankDeposit" value="$!txtNamaBankDeposit" size="40" maxlength="40" ></td>
  		</tr>
	</table>
	
	<br/>
	
	<table width="100%" border="0">
		<tr class="table_header"><td>&nbsp;<b>Butiran Pemulangan Deposit</b></td></tr>
	</table>
	
	<table width="100%" border="0">
		<tr>
  			<td width="1%">&nbsp;</td>
  			<td width="30%">Tarikh Pemulangan</td>
  			<td width="1%">:</td>
  			<td width="68%"><input $!disability $!disabilityx name="txdTarikhPulang" id="txdTarikhPulang" size="11" type="text" value="$!txdTarikhPulang" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            #if($mode=="new" || ($mode=="view" && $isEdit=="yes"))<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhPulang',false,'dmy');">&nbsp;$!frmtdate #end</td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>Status Pemulangan</td>
  			<td>:</td>
  			<td><select $!disability1 $!disabilityx name="socStatusPulang" style="width:180px">
	      			<option value="" #if($socStatusPulang=="") selected=selected #end >SILA PILIH</option>
	      			<option value="1" #if($socStatusPulang=="1") selected=selected #end >YA</option>
					<option value="2" #if($socStatusPulang=="2") selected=selected #end >TIDAK</option>
      			</select>
      		</td>	
  		</tr>
  		<tr>
  			<td valign="top">&nbsp;</td>
  			<td valign="top">Catatan</td>
  			<td valign="top">:</td>
  			<td valign="top"><textarea name="txtCatatanPulang" id="txtCatatanPulang" cols="40" rows="4" $!disability $!disabilityx >$!txtCatatanPulang</textarea></td>
  		</tr> 
	</table>
	
	<br/>
	
	<table width="100%" border="0">
		<tr class="table_header"><td>&nbsp;<b>Butiran Penghantaran Deposit</b></td></tr>
	</table>
	
	<table width="100%" border="0">
		<tr>
  			<td width="1%">&nbsp;</td>
  			<td width="30%">Tarikh Hantar</td>
  			<td width="1%">:</td>
  			<td width="68%"><input $!disability $!disabilityx name="txdTarikhHantarDeposit" id="txdTarikhHantarDeposit" size="11" type="text" value="$!txdTarikhHantarDeposit" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            #if($mode=="new" || ($mode=="view" && $isEdit=="yes"))<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhHantarDeposit',false,'dmy');">&nbsp;$!frmtdate #end</td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>Nama Penghantar</td>
  			<td>:</td>
  			<td><input type="text" $!disability $!disabilityx name="txtNamaPenghantar" id="txtNamaPenghantar" value="$!txtNamaPenghantar" size="40" maxlength="40" ></td>
  		</tr>
	</table>
	
	<br/>
	
</fieldset>	


</fieldset>

				<table width="100%" border="0">
					<tr align="center">
						<td>
						#if($mode=="new")
						<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanDataDeposit('$!id_bantahan','$!mode')">
						#end
				
					#if($mode=="view")
						#if($isEdit=="no")
						<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniDataDeposit('$!id_bantahan')">
						#else
						<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanDataDeposit('$!id_bantahan','$!mode')">
						<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:viewDeposit('$!id_bantahan')">
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
<input type="hidden" name="paging" value="2">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>


<script>
	
</script>

<script> 
function viewDeposit(id_bantahan) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_bantahan.value = id_bantahan;
	document.${formName}.command.value = "viewDeposit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
	document.${formName}.submit();
}
function kemaskiniDataDeposit(id_bantahan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_bantahan.value = id_bantahan;
	document.${formName}.command.value = "viewDeposit";
	document.${formName}.command2.value = "kemaskiniDataDeposit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
	document.${formName}.submit();
}
function backToList() {
	document.${formName}.command.value = "else";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
	document.${formName}.submit();
}
function simpanDataDeposit(id_bantahan,mode) {
	
	if(document.${formName}.txtAmaunPampasan.value == ""){
		alert("Sila masukkan \"Amaun Pampasan\" terlebih dahulu.");
  		document.${formName}.txtAmaunPampasan.focus(); 
		return;
	}else if(document.${formName}.txdTarikhTerimaResit.value == ""){
		alert("Sila masukkan \"Tarikh Terima Resit\" terlebih dahulu.");
  		document.${formName}.txdTarikhTerimaResit.focus(); 
		return;
	}else if(document.${formName}.txdTarikhResit.value == ""){
		alert("Sila masukkan \"Tarikh Resit\" terlebih dahulu.");
  		document.${formName}.txdTarikhResit.focus(); 
		return;		
	}else if(document.${formName}.txtNoResit.value == ""){
		alert("Sila masukkan \"No. Resit\" terlebih dahulu.");
  		document.${formName}.txtNoResit.focus(); 
		return;		
	}else{		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "top";
		document.${formName}.id_bantahan.value = id_bantahan;
		document.${formName}.command.value = "viewDeposit";
		document.${formName}.command2.value = "simpanDataDeposit";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
		document.${formName}.submit();
	}
	
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