<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($flagPopup == 'openPopupPenceroboh')
  <tr>
    <td> #parse("app/php2/frmMaklumatPencerobohanDetail.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI PENCEROBOH</strong></legend>
      <table align="center" width="100%">
        <tr>
          <td colspan="3" scope="row"><input name="cmdDaftarPenceroboh" type="button" value="Tambah" onClick="javascript:doDaftarBaruPenceroboh()"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="35%"><strong>Nama</strong></td>
          <td width="30%"><strong>Alamat</strong></td>
          <td width="30%"><strong>Jenis Pencerobohan</strong></td>
        </tr>
        #set ($senaraiPenceroboh = "")
        #if ($SenaraiPenceroboh.size() > 0)
        #foreach ($senaraiPenceroboh in $SenaraiPenceroboh)
        #if ($senaraiPenceroboh.bil == '')
        #set( $row = "row1" )
        #elseif (($senaraiPenceroboh.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$senaraiPenceroboh.bil</td>
          <td class="$row"><a href="javascript:doPaparPenceroboh('$senaraiPenceroboh.idPenceroboh')" style="color:#0000FF">$senaraiPenceroboh.namaPenceroboh</a></td>
          <td class="$row">$senaraiPenceroboh.alamatPenceroboh1&nbsp;$senaraiPenceroboh.alamatPenceroboh2&nbsp;$senaraiPenceroboh.alamatPenceroboh3 &nbsp; </td>
          <td class="$row">$senaraiPenceroboh.jenisPencerobohan </td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
        <tr>
          <td colspan="7" align="center">&nbsp;
          </td>
        </tr>
        <tr>
          <td colspan="7" align="center">
           #if ($flagPopup == '')
          <input name="cmdBatalLT1" id="cmdBatalLT1" type="button" value="Kembali" onClick="batalLawatanTapak()" />
          #end</td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>

<script>
function doChangeNegeriPenceroboh() {
	doAjaxCall${formName}("doChangeNegeriPenceroboh");
}
function doDaftarBaruPenceroboh() {
	document.${formName}.step.value = "kemaskini";
	document.${formName}.flagPopup.value = "openPopupPenceroboh";
	document.${formName}.modePopup.value = "new";
	doAjaxCall${formName}("");
}
function doPaparPenceroboh(idPenceroboh){
	document.${formName}.idPenceroboh.value = idPenceroboh;
	document.${formName}.flagPopup.value = "openPopupPenceroboh";
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function simpanPenceroboh() {
	if(document.${formName}.txtNamaPenceroboh.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.txtNamaPenceroboh.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.step.value = "kemaskini";
	document.${formName}.flagPopup.value = "openPopupPenceroboh";
	document.${formName}.modePopup.value = "new";
	document.${formName}.hitButton.value = "doSimpanPenceroboh";
	doAjaxCall${formName}("");
}
function batalPenceroboh(){
	document.${formName}.step.value = "kemaskini";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function batalKemaskiniPenceroboh(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function simpanKemaskiniPenceroboh() {
	if(document.${formName}.txtNamaPenceroboh.value == ""){
		alert('Sila masukkan Nama.');
  		document.${formName}.txtNamaPenceroboh.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.flagPopup.value = "openPopupPenceroboh";
		document.${formName}.modePopup.value = "new";
		return;
	}
	
	document.${formName}.step.value = "kemaskini";
	document.${formName}.flagPopup.value = "openPopupPenceroboh";
	document.${formName}.modePopup.value = "update";
	document.${formName}.hitButton.value = "doSimpanKemaskiniPenceroboh";
	doAjaxCall${formName}("");
}
function hapusMaklumatPenceroboh(){	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "doHapusMaklumatPenceroboh";
	doAjaxCall${formName}("");
}
</script>