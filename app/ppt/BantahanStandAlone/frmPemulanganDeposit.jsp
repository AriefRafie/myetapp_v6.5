	#if($mode=="view")
		
		#if($onchange=="no")
			#foreach($dataO in $dataBorangO)
				#set($sorStatusPulangDep=$dataO.FLAG_PULANG_DEPOSIT)
				
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
		  <td>&nbsp;<b>Maklumat Pemulangan Deposit</b></td>
	  </tr>
	</table>
<table width="100%">
<tr>
  			<td valign="top" width="1%">&nbsp;</td>
  			<td valign="top" width="30%">Status Pemulangan Deposit</td>
  			<td valign="top" width="1%">:</td>
  			<td valign="top" width="68%"> 
  			<input name="sorStatusPulangDep" $!disability1 $!disabilityx #if($sorStatusPulangDep=="1") checked #end id="sorStatusPulangDep" value="1" type="radio">Dikembalikan Sepenuhnya
  			<br/><input name="sorStatusPulangDep" $!disability1 $!disabilityx #if($sorStatusPulangDep=="2") checked #end id="sorStatusPulangDep" value="2" type="radio">Dikembalikan Sebahagian
  			<br/><input name="sorStatusPulangDep" $!disability1 $!disabilityx #if($sorStatusPulangDep=="3") checked #end id="sorStatusPulangDep" value="3" type="radio">Tidak Dikembalikan / Dirampas
  			<br/><input name="sorStatusPulangDep" $!disability1 $!disabilityx #if($sorStatusPulangDep=="4") checked #end id="sorStatusPulangDep" value="4" type="radio">Lain-lain
  			</td>
  		</tr>
        <tr>
        </tr>
        <tr>
          <td colspan="4" align="center">
          				#if($mode=="new")
						<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:updatePemulangan('$!id_bantahan','$!mode')">
						#end
				
					#if($mode=="view")
						#if($isEdit=="no")
						<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniPemulangan('$!id_bantahan')">
						#else
						<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:updatePemulangan('$!id_bantahan','$!mode')">
						<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:susulanPemulangan('$!id_bantahan')">
						#end
					#end
				
						<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:backToList()">
          </td>
        </tr>
</table>
</fieldset>
</fieldset>
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
function susulanPemulangan(id_bantahan) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_bantahan.value = id_bantahan;
	document.${formName}.command.value = "pemulanganDeposit";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
	document.${formName}.submit();
}
function kemaskiniPemulangan(id_bantahan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_bantahan.value = id_bantahan;
	document.${formName}.command.value = "pemulanganDeposit";
	document.${formName}.command2.value = "kemaskiniPemulanganDep";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
	document.${formName}.submit();
}
function backToList() {
	document.${formName}.command.value = "else";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
	document.${formName}.submit();
}
function updatePemulangan(id_bantahan,mode) {

	
		if ( !window.confirm("Adakah Anda Pasti?") ) return;
		document.${formName}.ScreenLocation.value = "top";
		document.${formName}.id_bantahan.value = id_bantahan;
		document.${formName}.command.value = "pemulanganDeposit";
		document.${formName}.command2.value = "updatePemulanganDep";
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