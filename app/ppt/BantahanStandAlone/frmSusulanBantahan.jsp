#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")


	#if($mode=="view")
		
		#if($onchange=="no")
			#foreach($dataO in $dataBorangO)
				#set($sorStatusPulangDep=$dataO.FLAG_PULANG_DEPOSIT)
				#set($sorKeputusanMahkamah=$dataO.KEPUTUSAN_MAHKAMAH)
				#set($txtNoProsiding=$dataO.NO_RUJUKAN_TANAH)
				#set($txtNoRujPemohon=$dataO.NO_RUJUKAN_MAHKAMAH)
				#set($txdTarikhLanjutanMahkamah=$dataO.TARIKH_LANJUTAN_MAHKAMAH)
				#set($txtKeteranganPampasan=$dataO.KETERANGAN_PAMPASAN)
				#set($txtKosPengapit=$dataO.KOS_PENGAPIT_HAKIM)
				#set($txdTarikhPerintah=$dataO.TARIKH_PERINTAH)
				#set($txdTarikhTerimaPerintah=$dataO.TARIKH_TERIMA_PERINTAH)
                #set($txtKosJPPH=$dataO.KOS_JPPH)
				#set($txtNamaJPPH=$dataO.NAMA_JPPH)
				#set($txtKosSwasta=$dataO.KOS_SWASTA)
				#set($txtNamaSwasta=$dataO.NAMA_SWASTA)
				#set($txtNamaSyarikat=$dataO.SYARIKAT_SWASTA)
			#end	
			#foreach($data in $dataBantahan)
				#set($txtAmaunTambahan=$data.AMAUN_AWARD)
				#set($txtTempohBayaran=$data.TEMPOH_BAYAR)
				#set($socUnitTempoh=$data.UNIT_TEMPOH)
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
<legend><strong>PERINTAH</strong></legend>

#if($sizeDataBantahan!=0)
#parse("app/ppt/BantahanStandAlone/frmPaging.jsp")
#end

<fieldset>
	
  	<table width="100%" border="0">
		<tr class="table_header">
		  <td>&nbsp;<b>Maklumat Perintah</b></td>
	  </tr>
	</table>
	
	<table width="100%" border="0">
        <tr>
  			<td width="1%">$M</td>
  			<td width="30%">No. Ruj. Prosiding Bantahan (No Ruj Tanah)</td>
  			<td width="1%">:</td>
  			<td width="68%"><input type="text" $!disability $!disabilityx name="txtNoProsiding" id="txtNoProsiding" value="$!txtNoProsiding" size="30" maxlength="50" ></td>
  		</tr>
		<tr>
  			<td width="1%">&nbsp;</td>
  			<td width="30%">No. Ruj. Mahkamah</td>
  			<td width="1%">:</td>
  			<td width="68%"><input type="text" $!disability $!disabilityx name="txtNoRujPemohon" id="txtNoRujPemohon" value="$!txtNoRujPemohon" size="30" maxlength="50" ></td>
        </tr>
  		
  		
  		<tr>
  			<td>&nbsp;</td>
  			<td valign="top">Keputusan Mahkamah</td>
  			<td valign="top">:</td>
  			<td valign="top"> 
  			<input name="sorKeputusanMahkamah" $!disability1 $!disabilityx #if($sorKeputusanMahkamah=="1") checked #end id="sorKeputusanMahkamah" value="1" type="radio">Penambahan Pampasan
  			<br/><input name="sorKeputusanMahkamah" $!disability1 $!disabilityx #if($sorKeputusanMahkamah=="2") checked #end id="sorKeputusanMahkamah" value="2" type="radio">Pengurangan Pampasan
  			<br/><input name="sorKeputusanMahkamah" $!disability1 $!disabilityx #if($sorKeputusanMahkamah=="3") checked #end id="sorKeputusanMahkamah" value="3" type="radio">Kekal
  			</td>
  		</tr>
  		<tr>
  			<td>$M</td>
  			<td>Tarikh Perintah Mahkamah</td>
  			<td>:</td>
  			<td><input $!disability $!disabilityx name="txdTarikhPerintah" id="txdTarikhPerintah" size="11" type="text" value="$!txdTarikhPerintah" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            #if($mode=="new" || ($mode=="view" && $isEdit=="yes"))<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhPerintah',false,'dmy');">&nbsp;$!frmtdate #end</td>
  		</tr>
  		<tr>
  			<td>$M</td>
  			<td>Tarikh Terima Perintah</td>
  			<td>:</td>
  			<td><input $!disability $!disabilityx name="txdTarikhTerimaPerintah" id="txdTarikhTerimaPerintah" size="11" type="text" value="$!txdTarikhTerimaPerintah" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            #if($mode=="new" || ($mode=="view" && $isEdit=="yes"))<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhTerimaPerintah',false,'dmy');">&nbsp;$!frmtdate #end</td>
  		</tr>
  		<tr>
  			<td>&nbsp;</td>
  			<td>Amaun Pampasan Tambahan (RM)</td>
  			<td>:</td>
  			<td><input type="text" $!disability $!disabilityx name="txtAmaunTambahan" id="txtAmaunTambahan" value="$!txtAmaunTambahan" size="11" maxlength="8" style="text-align:right" onkeyup="validateNilai(this,this.value)" onblur="validateModal(this,this.value,'$!txtAmaunTambahan')" ></td>
  		</tr> 
  		<tr>
  			<td>&nbsp;</td>
  			<td valign="top">Kos Pengapit Hakim (RM)</td>
  			<td valign="top">:</td>
  			<td>
            <fieldset><legend>JPPH</legend>
            <table width="100%">
            	<tr>
                	<td width="7%">Kos                    </td>
                  <td width="1%">:                  </td>
                  <td width="92%"><input type="text" size="11" name="txtKosJPPH" id="txtKosJPPH" value="$!txtKosJPPH" maxlength="12" $!disability $!disabilityx onblur="validateNumber(this,this.value);validateModal(this,this.value,'$txtKosJPPH')"/>
                  </td>
              </tr>
                <tr>
                	<td width="7%">Nama                    </td>
                  <td width="1%">:                  </td>
                  <td width="92%"><input type="text" size="40" name="txtNamaJPPH" id="txtNamaJPPH" value="$!txtNamaJPPH" maxlength="12" $!disability $!disabilityx />
                  </td>
              </tr>
             </table>
            </fieldset>
            <fieldset><legend>Swasta</legend>
            <table width="100%">
            	<tr>
                	<td width="6%">Kos
                    </td>
                    <td width="2%">:
                    </td>
                    <td width="92%"><input type="text" size="11" name="txtKosSwasta" id="txtKosSwasta" value="$!txtKosSwasta" maxlength="12" $!disability $!disabilityx onblur="validateNumber(this,this.value);validateModal(this,this.value,'$txtKosSwasta')"/>
                    </td>
                </tr>
               <tr>
                	<td width="6%">Nama
                    </td>
                    <td width="2%">:
                    </td>
                    <td width="92%"><input type="text" size="40" name="txtNamaSwasta" id="txtNamaSwasta" value="$!txtNamaSwasta" maxlength="12" $!disability $!disabilityx />
                    </td>
                </tr>
                <tr>
                	<td width="6%">Syarikat
                    </td>
                    <td width="2%">:
                    </td>
                    <td width="92%"><input type="text" size="40" name="txtNamaSyarikat" id="txtNamaSyarikat" value="$!txtNamaSyarikat" maxlength="12" $!disability $!disabilityx />
                    </td>
                </tr>
            </table>
            </fieldset>
             <table width="100%">
            <tr>
            	<td width="7%">
            Jumlah</td>
             <td width="2%">:             </td>
             <td width="91%"><input type="text" size="11" name="txtKosPengapit" id="txtKosPengapit" value="$!txtKosPengapit" maxlength="12" onblur="validateNumber(this,this.value);validateModal(this,this.value,'$txtKosPengapit')" onkeyup="validateNumber(this,this.value);" $!disability $!disabilityx /></td>
            </tr></table>
          </td>
  		</tr> 
  		<tr>
  			<td>&nbsp;</td>
  			<td>Tempoh Bayaran</td>
  			<td>:</td>
  			<td>
  			<input type="text" $!disability $!disabilityx name="txtTempohBayaran" id="txtTempohBayaran" value="$!txtTempohBayaran" size="2" onkeyup="validateNumber(this,this.value);" onblur="validateNumber(this,this.value);" />
  				<select $!disability1 $!disabilityx name="socUnitTempoh" style="width:100px">
	      			<option value="" #if($socUnitTempoh=="") selected=selected #end >SILA PILIH</option>
	      			<option value="1" #if($socUnitTempoh=="1") selected=selected #end >HARI</option>
	      			<option value="2" #if($socUnitTempoh=="2") selected=selected #end >MINGGU</option>
	      			<option value="3" #if($socUnitTempoh=="3") selected=selected #end >BULAN</option>
      			</select>
  			</td>
  		</tr> 
  		
  		<tr>
  			<td valign="top">&nbsp;</td>
  			<td valign="top">Keterangan Pampasan</td>
  			<td valign="top">:</td>
  			<td valign="top"><textarea name="txtKeteranganPampasan" id="txtKeteranganPampasan" cols="50" rows="5" $!disability $!disabilityx >$!txtKeteranganPampasan</textarea></td>
  		</tr> 
  		
	</table>
	
	<br/>
	
</fieldset>	


</fieldset>

				<table width="100%" border="0">
					<tr align="center">
						<td>
						#if($mode=="new")
						<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:updateSusulan('$!id_bantahan','$!mode')">
						#end
				
					#if($mode=="view")
						#if($isEdit=="no")
						<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniSusulan('$!id_bantahan')">
						#else
						<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:updateSusulan('$!id_bantahan','$!mode')">
						<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:susulanBantahan('$!id_bantahan')">
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
function susulanBantahan(id_bantahan) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_bantahan.value = id_bantahan;
	document.${formName}.command.value = "susulanBantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
	document.${formName}.submit();
}
function kemaskiniSusulan(id_bantahan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_bantahan.value = id_bantahan;
	document.${formName}.command.value = "susulanBantahan";
	document.${formName}.command2.value = "kemaskiniSusulan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
	document.${formName}.submit();
}
function backToList() {
	document.${formName}.command.value = "else";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
	document.${formName}.submit();
}
function updateSusulan(id_bantahan,mode) {

	if(document.${formName}.txtNoProsiding.value == ""){
		alert("Sila masukkan \"No. Ruj. Prosiding Bantahan\" terlebih dahulu.");
  		document.${formName}.txtNoProsiding.focus(); 
		return;
	}else if(document.${formName}.txdTarikhPerintah.value == ""){
		alert("Sila masukkan \"Tarikh Perintah Mahkamah\" terlebih dahulu.");
  		document.${formName}.txdTarikhPerintah.focus(); 
		return;
	}else if(document.${formName}.txdTarikhTerimaPerintah.value == ""){
		alert("Sila masukkan \"Tarikh Terima Perintah\" terlebih dahulu.");
  		document.${formName}.txdTarikhTerimaPerintah.focus(); 
		return;
	}else{		
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "top";
		document.${formName}.id_bantahan.value = id_bantahan;
		document.${formName}.command.value = "susulanBantahan";
		document.${formName}.command2.value = "updateSusulan";
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