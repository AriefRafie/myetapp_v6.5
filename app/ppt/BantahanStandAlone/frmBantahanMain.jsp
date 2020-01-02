
#parse("app/ppt/frmLabelSet.jsp")
#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")
	
	#if($mode=="view")
		
		#if($onchange=="no")
			#foreach($data in $dataBantahan)
               	#set($txtNoFail=$data.NO_FAIL)
				#set($txtNoBantahan=$data.NO_BANTAHAN)
				#set($txdTarikhTerimaBorangN=$data.TARIKH_TERIMA)
				#set($txdTarikhBorangN=$data.TARIKH_BORANGN)
				#set($txtNoHakmilik=$data.NO_HAKMILIK)
				#set($txtNoLot=$data.NO_LOT)
				#set($socStatusBantahan=$data.STATUS_BANTAHAN)
				#set($txdTarikhTerimaBorangH=$data.TARIKH_TERIMA_BORANGH)
				#set($txdTarikhAward=$data.TARIKH_TERIMA_AWARD)
				#set($socStatusHadir=$data.STATUS_HADIR)
				#set($txtNamaPembantah=$data.NAMA_PEMBANTAH)
				#set($txtAlamat1=$data.ALAMAT1)
				#set($txtAlamat2=$data.ALAMAT2)
				#set($txtAlamat3=$data.ALAMAT3)
				#set($txtPoskod=$data.POSKOD)
				#set($txtKepentingan=$data.KEPENTINGANKEATAS)
				
				#set($cbUkuranLuas=$data.FLAG_PENERIMA_PAMPASAN)
				#set($cbAmaunPampasan=$data.FLAG_BAHAGIAN_PAMPASAN)
				#set($cbTerimaPampasan=$data.FLAG_UKUR_LUAS)
				#set($cbBahagianPampasan=$data.FLAG_PAMPASAN)
				
				#set($txtAmaunTuntutan=$data.AMAUN_TUNTUTAN)
				#set($txtAlasanBantahan=$data.ALASAN)
				#set($txtMaklumatTamatTempoh=$data.MAKLUMAT_BANTAHAN_TAMAT_TEMPOH)
                
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
<legend><strong>MAKLUMAT BANTAHAN</strong></legend>

#if($sizeDataBantahan!=0)
#parse("app/ppt/BantahanStandAlone/frmPaging.jsp")
#end


	<fieldset>
	
	<table width="100%" border="0">
		<tr class="table_header"><td>&nbsp;<b>Maklumat Bantahan</b></td></tr>
	</table>
	
	<table width="100%" border="0">
		<tr>
  			<td width="1%">$M</td>
  			<td width="30%">No. Rujukan Bantahan</td>
  			<td width="1%">:</td>
  			<td width="68%">
  			<input type="text" $!disability $!disabilityx name="txtNoBantahan" id="txtNoBantahan" value="$!txtNoBantahan" size="20" maxlength="50" >
  			</td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>No. Fail JKPTG/PTG/PTD/UPT</td>
  			<td>:</td>
  			<td><input type="text" $!disability $!disabilityx name="txtNoFail" id="txtNoFail" value="$!txtNoFail" size="30" maxlength="90" ></td>
  		</tr>
  		<tr>
  			<td>$M</td>
  			<td>Tarikh Terima Borang N</td>
  			<td>:</td>
  			<td><input $!disability $!disabilityx name="txdTarikhTerimaBorangN" id="txdTarikhTerimaBorangN" size="11" type="text" value="$!txdTarikhTerimaBorangN" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            #if($mode=="new" || ($mode=="view" && $isEdit=="yes"))<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerimaBorangN',false,'dmy');">&nbsp;$!frmtdate #end</td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>Tarikh Borang N</td>
  			<td>:</td>
  			<td><input $!disability $!disabilityx name="txdTarikhBorangN" id="txdTarikhBorangN" size="11" type="text" value="$!txdTarikhBorangN" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            #if($mode=="new" || ($mode=="view" && $isEdit=="yes"))<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhBorangN',false,'dmy');">&nbsp;$!frmtdate #end</td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>No. Hakmilik</td>
  			<td>:</td>
  			<td><input type="text" $!disability $!disabilityx name="txtNoHakmilik" id="txtNoHakmilik" value="$!txtNoHakmilik" size="20" maxlength="40" ></td>
  		</tr>
  		<tr>
  			<td>$M</td>
  			<td>No.Lot / No.PT</td>
  			<td>:</td>
  			<td><input type="text" $!disability $!disabilityx name="txtNoLot" id="txtNoLot" value="$!txtNoLot" size="20" maxlength="40" ></td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>Status Bantahan</td>
  			<td>:</td>
  			<td><select $!disability1 $!disabilityx name="socStatusBantahan" style="width:200px">
	      			<option value="" #if($socStatusBantahan=="") selected=selected #end >SILA PILIH</option>
	      			<option value="1" #if($socStatusBantahan=="1") selected=selected #end >SYARAT DIPENUHI</option>
	      			<option value="2" #if($socStatusBantahan=="2") selected=selected #end >SYARAT TIDAK DIPENUHI</option>
      			</select>
      		</td>	
  		</tr>
  		<tr>
  			<td>$M</td>
  			<td>Tarikh Terima Borang H</td>
  			<td>:</td>
  			<td><input $!disability $!disabilityx name="txdTarikhTerimaBorangH" id="txdTarikhTerimaBorangH" size="11" type="text" value="$!txdTarikhTerimaBorangH" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            #if($mode=="new" || ($mode=="view" && $isEdit=="yes"))<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerimaBorangH',false,'dmy');">&nbsp;$!frmtdate #end</td>
  		</tr>
  		<tr>
  			<td>$M</td>
  			<td>Tarikh Award</td>
  			<td>:</td>
  			<td><input $!disability $!disabilityx name="txdTarikhAward" id="txdTarikhAward" size="11" type="text" value="$!txdTarikhAward" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            #if($mode=="new" || ($mode=="view" && $isEdit=="yes"))<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhAward',false,'dmy');">&nbsp;$!frmtdate #end</td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>Status Hadir Bicara</td>
  			<td>:</td>
  			<td><select $!disability1 $!disabilityx name="socStatusHadir" style="width:200px">
	      			<option value="" #if($socStatusHadir=="") selected=selected #end >SILA PILIH</option>
	      			<option value="1" #if($socStatusHadir=="1") selected=selected #end >HADIR</option>
	      			<option value="2" #if($socStatusHadir=="2") selected=selected #end >TIDAK HADIR</option>
      			</select>
      		</td>		
  		</tr>
  	</table>
  	
  	<br/>
  	
	<table width="100%" border="0">
		<tr class="table_header"><td>&nbsp;<b>Maklumat Pembantah</b></td></tr>
	</table>
	
	<table width="100%" border="0">
		<tr>
  			<td width="1%">$M</td>
  			<td width="30%">Nama Pembantah</td>
  			<td width="1%">:</td>
  			<td width="68%"><input $!disability $!disabilityx type="text" name="txtNamaPembantah" id="txtNamaPembantah" value="$!txtNamaPembantah" size="50" maxlength="100" ></td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>Alamat</td>
  			<td>:</td>
  			<td><input $!disability $!disabilityx type="text" name="txtAlamat1" id="txtAlamat1" value="$!txtAlamat1" size="33" maxlength="40" ></td>
  		</tr>  
  		<tr>
  			<td colspan="3">&nbsp;</td>
  			<td><input $!disability $!disabilityx type="text" name="txtAlamat2" id="txtAlamat2" value="$!txtAlamat2" size="33" maxlength="40" ></td>
  		</tr>  
  		<tr>
  			<td colspan="3">&nbsp;</td>
  			<td><input $!disability $!disabilityx type="text" name="txtAlamat3" id="txtAlamat3" value="$!txtAlamat3" size="33" maxlength="40" ></td>
  		</tr> 
  		<tr>
  			<td>&nbsp;</td>
  			<td>Poskod</td>
  			<td>:</td>
  			<td><input $!disability $!disabilityx type="text" name="txtPoskod" id="txtPoskod" value="$!txtPoskod" size="6" maxlength="5" ></td>
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
  		<tr>
  			<td>&nbsp;</td>
  			<td>Jenis Pihak Pembantah</td>
  			<td>:</td>
  			<td>$!selectJenisPB</td>
  		</tr>  
  		<tr>
  			<td valign="top">$M</td>
  			<td valign="top">Kepentingan</td>
  			<td valign="top">:</td>
  			<td valign="top"><textarea name="txtKepentingan" id="txtKepentingan" cols="50" rows="5" $!disability $!disabilityx >$!txtKepentingan</textarea></td>
  		</tr> 
  		
  		#if($!cbUkuranLuas=="Y")
  			#set($selectedUkuranLuas="checked")
  		#else
  			#set($selectedUkuranLuas="")
  		#end
  		
  		<tr>
  			<td>$M</td>
  			<td>Bantahan Terhadap</td>
  			<td>:</td>
  			<td>
  			<input name="cbUkuranLuas" id="cbUkuranLuas" $!selectedUkuranLuas value="Y" $!disability1 type="checkbox">
  			Ukuran Luas Tanah Tersebut
  			</td>
  		</tr> 
  		
  		#if($!cbAmaunPampasan=="Y")
  			#set($selectedAmaunPampasan="checked")
  		#else
  			#set($selectedAmaunPampasan="")
  		#end
  		
  		<tr>
  			<td colspan="3">&nbsp;</td>
  			<td>
  			<input name="cbAmaunPampasan" $!selectedAmaunPampasan id="cbAmaunPampasan" value="Y" $!disability1 type="checkbox">
  			Jumlah Pampasan
  			</td>
  		</tr>
  		
  		#if($!cbTerimaPampasan=="Y")
  			#set($selectedTerimaPampasan="checked")
  		#else
  			#set($selectedTerimaPampasan="")
  		#end
  		
  		<tr>
  			<td colspan="3">&nbsp;</td>
  			<td>
  			<input name="cbTerimaPampasan" $!selectedTerimaPampasan id="cbTerimaPampasan" value="Y" $!disability1 type="checkbox">
  			Orang-orang yang  menerima pampasan
  			</td>
  		</tr>
  		
  		#if($!cbBahagianPampasan=="Y")
  			#set($selectedBahagianPampasan="checked")
  		#else
  			#set($selectedBahagianPampasan="")
  		#end
  		
  		<tr>
  			<td colspan="3">&nbsp;</td>
  			<td>
  			<input name="cbBahagianPampasan" $!selectedBahagianPampasan id="cbBahagianPampasan" value="Y" $!disability1 type="checkbox">
  			Bahagian Pemberian Pampasan
  			</td>
  		</tr>
  		<tr>
  			<td>$M</td>
  			<td>Amaun Tuntutan (RM)</td>
  			<td>:</td>
  			<td><input type="text" $!disability $!disabilityx name="txtAmaunTuntutan" id="txtAmaunTuntutan" value="$!txtAmaunTuntutan" size="12" maxlength="8" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtAmaunTuntutan')" ></td>
  		</tr> 
  		<tr>
  			<td valign="top">$M</td>
  			<td valign="top">Alasan Bantahan</td>
  			<td valign="top">:</td>
  			<td valign="top"><textarea name="txtAlasanBantahan" id="txtAlasanBantahan" cols="50" rows="5" $!disability $!disabilityx >$!txtAlasanBantahan</textarea></td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td valign="top">Maklumat Bantahan Tamat Tempoh</td>
  			<td valign="top">:</td>
  			<td valign="top"><textarea name="txtMaklumatTamatTempoh" id="txtMaklumatTamatTempoh" cols="50" rows="5" $!disability $!disabilityx >$!txtMaklumatTamatTempoh</textarea></td>
  		</tr> 
  			
  	</table>
	
	<br/>
	
  					
	</fieldset>
	
	</fieldset>	
	
				<table width="100%" border="0">
					<tr align="center">
						<td>
						#if($mode=="new")
						<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanDataBantahan('','$!mode')">
						#end
				
					#if($mode=="view")
						#if($isEdit=="no")
						<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniDataBantahan('$!id_bantahan')">
						<input type="button" name="cmdHapus" value ="Hapus" onClick="javascript:hapusDataBantahan('$!id_bantahan')">
						#else
						<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanDataBantahan('$!id_bantahan','$!mode')">
						<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:viewBantahan('$!id_bantahan')">
						#end
					#end
				
						<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:backToList()">
						</td>
					</tr>
				</table>
			

<input type="hidden" name="mode" value="$!mode">
<input type="hidden" name="id_bantahan" id="id_bantahan" value="$!id_bantahan">
<input type="hidden" name="id_borango" id="id_borango">

<input type="hidden" name="command2">
<input type="hidden" name="command3">


<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>


<script> 
function doChangeNegeri() {
	var mode = document.${formName}.mode.value;

	if(mode=="new"){
		document.${formName}.command.value = "registerBantahan";
	}else{
		document.${formName}.command.value = "viewBantahan";
	}
	document.${formName}.command2.value = "doChangeNegeri";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
	document.${formName}.submit();
}
function viewBantahan(id_bantahan) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_bantahan.value = id_bantahan;
	document.${formName}.command.value = "viewBantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
	document.${formName}.submit();
}
function kemaskiniDataBantahan(id_bantahan) {
   
	document.${formName}.command.value = "viewBantahan";
	document.${formName}.command2.value = "kemaskiniDataBantahan";
	document.${formName}.id_bantahan.value = id_bantahan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
	document.${formName}.submit();
}
function hapusDataBantahan(id_bantahan) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "viewBantahan";
	document.${formName}.command2.value = "hapusDataBantahan";
	document.${formName}.id_bantahan.value = id_bantahan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
	document.${formName}.submit();
}
function backToList() {
	document.${formName}.command.value = "else";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
	document.${formName}.submit();
}
function simpanDataBantahan(id_bantahan,mode) {
	
	if(document.${formName}.txtNoBantahan.value == ""){
		alert("Sila masukkan \"No. Rujukan Bantahan\" terlebih dahulu.");
  		document.${formName}.txtNoBantahan.focus(); 
		return;
	}else if(document.${formName}.txdTarikhTerimaBorangN.value == ""){
		alert("Sila masukkan \"Tarikh Terima Borang N\" terlebih dahulu.");
  		document.${formName}.txdTarikhTerimaBorangN.focus(); 
		return;
	}else if(document.${formName}.txtNoLot.value == ""){
		alert("Sila masukkan \"No.LOT/PT\" terlebih dahulu.");
  		document.${formName}.txtNoLot.focus(); 
		return;		
	}else if(document.${formName}.txdTarikhTerimaBorangH.value == ""){
		alert("Sila masukkan \"Tarikh Terima Borang H\" terlebih dahulu.");
  		document.${formName}.txdTarikhTerimaBorangH.focus(); 
		return;		
	}else if(document.${formName}.txdTarikhAward.value == ""){
		alert("Sila masukkan \"Tarikh Award\" terlebih dahulu.");
  		document.${formName}.txdTarikhAward.focus(); 
		return;		
	}else if(document.${formName}.txtNamaPembantah.value == ""){
		alert("Sila masukkan \"Nama Pembantah\" terlebih dahulu.");
  		document.${formName}.txtNamaPembantah.focus(); 
		return;		
	}else if(document.${formName}.txtKepentingan.value == ""){
		alert("Sila masukkan \"Kepentingan Atas Tanah\" terlebih dahulu.");
  		document.${formName}.txtKepentingan.focus(); 
		return;		
	}else if(document.${formName}.cbUkuranLuas.checked == false && document.${formName}.cbAmaunPampasan.checked == false
			 && document.${formName}.cbTerimaPampasan.checked == false && document.${formName}.cbBahagianPampasan.checked == false){
		alert("Sila pilih \"Bantahan Terhadap\" terlebih dahulu.");
  		document.${formName}.cbUkuranLuas.focus(); 
		return;		
	}else if(document.${formName}.txtAmaunTuntutan.value == "" || document.${formName}.txtAmaunTuntutan.value == "0.00" ){
		alert("Sila masukkan \"Amaun Tuntutan\" terlebih dahulu.");
  		document.${formName}.txtAmaunTuntutan.focus(); 
		return;		
	}else if(document.${formName}.txtAlasanBantahan.value == ""){
		alert("Sila masukkan \"Alasan Bantahan\" terlebih dahulu.");
  		document.${formName}.txtAlasanBantahan.focus(); 
		return;		
	}else{		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "top";
		if(mode=="new"){
			document.${formName}.command.value = "registerBantahan";
			document.${formName}.command2.value = "simpanDataBantahan";
		}else{
			document.${formName}.id_bantahan.value = id_bantahan;
			document.${formName}.command.value = "viewBantahan";
			document.${formName}.command2.value = "kemaskiniDataBantahan";
			document.${formName}.command3.value = "updateDataBantahan";
		}
		
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
		document.${formName}.submit();
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
</script>

<script>
window.onload = submitForm;

function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
}
function validateLuas(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content!=""){
		var num = content * 1;
		elmnt.value = num.toFixed(4);
		return;
	}else{
		elmnt.value = "";
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