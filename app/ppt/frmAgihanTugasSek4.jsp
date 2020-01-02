#parse("app/ppt/frmLabelSet.jsp")
#parse("app/ppt/Sek4Paging.jsp")

#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

<fieldset id="top">
<center>
<legend><strong>Agihan Tugas</strong></legend>

	#parse("app/ppt/frmPPTHeader.jsp")

<br/>

	<fieldset id="bottom">
	<legend><strong>Maklumat Pengagihan / Penyerahan Tugas</strong></legend>
	
		#if($mode=="new")
		<table width="100%" border="0">
			<tr>
				<td width="1%">&nbsp;</td>
				<td width="24%">Pegawai Pengagih</td>
				<td width="1%">:</td>
				<td width="74%"><input type="text" size="50" name="txtNamaPegawai" id="txtNamaPegawai" value="${session.getAttribute('_portal_username')}" style="text-transform:uppercase" onblur="this.value=this.value.toUpperCase()" readonly class="disabled"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Tarikh Serah Tugas</td>
				<td>:</td>
				<td>$!currentDATE&nbsp;$!frmtdate</td>
			</tr>
			<tr>
				<td><font color="red">*</font></td>
				<td>Pegawai Penerima</td>
				<td>:</td>
				<td>$!selectPegawai</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Jawatan Pegawai Penerima</td>
				<td>:</td>
				<td><input type="text" name="lblJawatanPegawai" id="lblJawatanPegawai" value="$!lblJawatanPegawai" readonly class="disabled" size="40" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td valign="top">Catatan</td>
				<td valign="top">:</td>
				<td valign="top"><textarea name="txtCatatan" id="txtCatatan" cols="47%" rows="3" onKeyUp="textCounter(this.form.txtCatatan,this.form.remLen2,100);" onKeyDown="textCounter(this.form.txtCatatan,this.form.remLen2,100);" >$!txtCatatan</textarea></td>
			</tr>
		</table>
		#end
		
		
		
		#if($mode=="view")
		
		#if($onchange=="no")
		#foreach($data in $dataAgihan)
			#set($txtNamaPegawai = $data.pegawai_agih)
			#set($txdTarikhSerah = $data.tarikh_agih)
			#set($lblJawatanPegawai = $data.jawatan)
			#set($txtCatatan = $data.perihal_agih)
		#end
		#end
		
		#if($isEdit=="no")
			#set($disability = "readonly")
			#set($disabilityx = "class=disabled")
			#set($disability1 = "disabled")
		#else
			#set($disability = "")
			#set($disabilityx = "")
			#set($disability1 = "")
		#end
		
		<table width="100%" border="0">
			<tr>
				<td width="1%">&nbsp;</td>
				<td width="24%">Pegawai Pengagih</td>
				<td width="1%">:</td>
				#if($isEdit=="no")
				<td width="74%"><input type="text" size="50" name="txtNamaPegawai" id="txtNamaPegawai" value="$!txtNamaPegawai" style="text-transform:uppercase" onblur="this.value=this.value.toUpperCase()" readonly class="disabled"></td>
				#else
				<td width="74%"><input type="text" size="50" name="txtNamaPegawai" id="txtNamaPegawai" value="${session.getAttribute('_portal_username')}" style="text-transform:uppercase" onblur="this.value=this.value.toUpperCase()" readonly class="disabled"></td>
				#end
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Tarikh Serah Tugas</td>
				<td>:</td>
				#if($isEdit=="no")
				<td>$!txdTarikhSerah&nbsp;$!frmtdate</td>
				#else
				<td>$!currentDATE&nbsp;$!frmtdate</td>
				#end	
			</tr>
			<tr>
				<td><font color="red">#if($isEdit=="yes")*#end</font></td>
				<td>Pegawai Penerima</td>
				<td>:</td>
				<td>$!selectPegawai</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>Jawatan Pegawai Penerima</td>
				<td>:</td>
				<td><input type="text" name="lblJawatanPegawai" id="lblJawatanPegawai" value="$!lblJawatanPegawai" readonly class="disabled" size="40" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td valign="top">Catatan</td>
				<td valign="top">:</td>
				<td valign="top"><textarea $disability $disabilityx name="txtCatatan" id="txtCatatan" cols="47%" rows="3" onKeyUp="textCounter(this.form.txtCatatan,this.form.remLen2,100);" onKeyDown="textCounter(this.form.txtCatatan,this.form.remLen2,100);" >$!txtCatatan</textarea></td>
			</tr>
		</table>
		#end
		
		#if($mode=="new" || ($mode=="view" && $isEdit=="yes"))
		<table width="100%" border="0">
        	<tr><td>&nbsp;</td></tr>
        	<tr>
        		<td>$!perhatian4</td>
        	</tr>
        </table>
        #end
		
	</fieldset>


			<table width="100%" border="0">
				<tr align="center">
					<td>
					#if($mode=="new")
					<input type="button" name="cmdSimpan" value ="Agihkan" onClick="javascript:simpanAgihan('$!id_permohonan','$!id_tugas','$!mode')">
					#end
				
					#if($mode=="view")
						#if($isEdit=="no")
						<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniAgihan('$!id_permohonan')">
						#else
						<input type="button" name="cmdUpdate" value ="Agihkan" onClick="javascript:simpanAgihan('$!id_permohonan','$!id_tugas','$!mode')">
						<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batal('$!id_permohonan')">
						#end
					#end	
						<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:kembali()">
					</td>
				</tr>
			</table>
			
</center>
</fieldset>

			

<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_status" value="$!id_status">
<input type="hidden" name="id_fail" value="$!id_fail">
<input type="hidden" name="id_tugas" value="$!id_tugas">

<input type="hidden" name="command2">
<input type="hidden" name="command3">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>


<script>
function batal(id_permohonan) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewAgihan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAgihanTugasSek4";
	document.${formName}.submit();
}
function doChangePegawaiUpdate() {
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.command.value = "viewAgihan";
	document.${formName}.command2.value = "kemaskiniAgihan";
	document.${formName}.command3.value = "doChangePegawaiUpdate";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAgihanTugasSek4";
	document.${formName}.submit();
}
function kemaskiniAgihan() {
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.command.value = "viewAgihan";
	document.${formName}.command2.value = "kemaskiniAgihan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAgihanTugasSek4";
	document.${formName}.submit();
}
function simpanAgihan(idpermohonan,idtugas,mode) {

	if(document.${formName}.socPegawai.value == ""){
		alert("Sila pilih \"Pegawai Penerima\" terlebih dahulu.");
  		document.${formName}.socPegawai.focus(); 
		return;
	}
	else{
		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "bottom";
		document.${formName}.id_permohonan.value = idpermohonan;

		if(mode=="new"){
			document.${formName}.command.value = "tambahAgihan";
			document.${formName}.command2.value = "simpanAgihan";
		}else{
			document.${formName}.id_tugas.value = idtugas;
			document.${formName}.command.value = "viewAgihan";
			document.${formName}.command2.value = "kemaskiniAgihan";
			document.${formName}.command3.value = "updateAgihan";
		}
		
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAgihanTugasSek4";
		document.${formName}.submit();
	}
}
function doChangePegawai() {
	document.${formName}.ScreenLocation.value = "bottom";
	document.${formName}.command.value = "tambahAgihan";
	document.${formName}.command2.value = "doChangePegawai";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAgihanTugasSek4";
	document.${formName}.submit();
}
function kembali() {
	document.${formName}.command.value = "xx";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAgihanTugasSek4";
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
function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'characters left' counter
	else 
		countfield.value = maxlimit - field.value.length;
}
</script>