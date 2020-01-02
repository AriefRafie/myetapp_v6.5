#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")


	#if($mode=="view")
		
		#if($onchange=="no")
			#foreach($dataO in $dataBorangO)
				
				#set($txdTarikhLanjutanMahkamah=$dataO.TARIKH_LANJUTAN_MAHKAMAH)
				
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
<legend><strong>LANJUTAN TEMPOH</strong></legend>

#if($sizeDataBantahan!=0)
#parse("app/ppt/BantahanStandAlone/frmPaging.jsp")
#end

<fieldset>
	
  	<table width="100%" border="0">
		<tr class="table_header">
		  <td>&nbsp;<b>Maklumat Lanjutan Tempoh</b></td>
	  </tr>
	</table>
	<fieldset>
        	<legend>Orang Berkepentingan</legend>
	<table width="100%" border="0">
       
		
  		<tr>
  			<td width="1%">&nbsp;</td>
  			<td width="30%">Tarikh Lanjutan Mahkamah</td>
  			<td width="1%">:</td>
  			<td width="68%"><input $!disability $!disabilityx name="txdTarikhLanjutanMahkamah" id="txdTarikhLanjutanMahkamah" size="11" type="text" value="$!txdTarikhLanjutanMahkamah" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            #if($mode=="new" || ($mode=="view" && $isEdit=="yes"))<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhLanjutanMahkamah',false,'dmy');">&nbsp;$!frmtdate #end</td>
  		</tr>
  
	</table>
	</fieldset>
    <fieldset>
        	<legend>Pentadbir Tanah</legend>
	<table width="100%" border="0">
       
		
  		<tr>
  			<td width="1%">&nbsp;</td>
  			<td width="30%">Tarikh Lanjutan Mahkamah</td>
  			<td width="1%">:</td>
  			<td width="68%"><input $!disability $!disabilityx name="txdTarikhLanjutanMahkamah" id="txdTarikhLanjutanMahkamah" size="11" type="text" value="$!txdTarikhLanjutanMahkamah" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            #if($mode=="new" || ($mode=="view" && $isEdit=="yes"))<img src="../img/calendar.gif" onclick="displayDatePicker('txdTarikhLanjutanMahkamah',false,'dmy');">&nbsp;$!frmtdate #end</td>
  		</tr>
  
	</table>
	</fieldset>
	<br/>
	
</fieldset>	


</fieldset>

				<table width="100%" border="0">
					<tr align="center">
						<td>
						#if($mode=="new")
						<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:updateLanjutan('$!id_bantahan','$!mode')">
						#end
				
					#if($mode=="view")
						#if($isEdit=="no")
						<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniLanjutan('$!id_bantahan')">
						#else
						<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:updateLanjutan('$!id_bantahan','$!mode')">
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
function lanjutanTempoh(id_bantahan) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_bantahan.value = id_bantahan;
	document.${formName}.command.value = "lanjutanTempoh";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
	document.${formName}.submit();
}
function kemaskiniLanjutan(id_bantahan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_bantahan.value = id_bantahan;
	document.${formName}.command.value = "lanjutanTempoh";
	document.${formName}.command2.value = "kemaskiniLanjutan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
	document.${formName}.submit();
}
function backToList() {
	document.${formName}.command.value = "else";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
	document.${formName}.submit();
}
function updateLanjutan(id_bantahan,mode) {

			
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "top";
		document.${formName}.id_bantahan.value = id_bantahan;
		document.${formName}.command.value = "lanjutanTempoh";
		document.${formName}.command2.value = "updateLanjutan";
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

</script>