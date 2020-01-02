
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($flagPopup == 'openPenceroboh')
  <tr>
    <td> #parse("app/php2/frmCRBPencerobohPeringatanDetail.jsp") </td>
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
          <td colspan="3" scope="row"><input name="cmdDaftarPenceroboh" type="button" value="Tambah" onClick="javascript:doDaftarMaklumatPenceroboh()"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="35%"><strong>Nama</strong></td>
          <td width="60%"><strong>Alamat</strong></td>
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
          <td class="$row"><a href="javascript:paparMaklumatPenceroboh('$senaraiPenceroboh.idPenceroboh')" class="style1">$senaraiPenceroboh.nama</a></td>
          <td class="$row">$senaraiPenceroboh.alamat1&nbsp;$senaraiPenceroboh.alamat2&nbsp;$senaraiPenceroboh.alamat3 &nbsp; </td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}

function paparMaklumatPenceroboh(idPenceroboh){
	document.${formName}.flagPopup.value = "openPenceroboh";
	document.${formName}.modePopup.value = "view";
	document.${formName}.idPenceroboh.value = idPenceroboh;
	doAjaxCall${formName}("");
}
function doDaftarMaklumatPenceroboh(){
	document.${formName}.flagPopup.value = "openPenceroboh";
	document.${formName}.modePopup.value = "new";
	doAjaxCall${formName}("");
}
function simpanPenceroboh(){
	if(document.${formName}.txtNama.value == ""){
		alert('Sila masukkan Nama Penceroboh.');
  		document.${formName}.txtNama.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openPenceroboh";
	document.${formName}.modePopup.value = "new";
	document.${formName}.hitButton.value = "simpanPenceroboh";
	doAjaxCall${formName}("");
}
function batalPenceroboh(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function kemaskiniPenceroboh(){
	document.${formName}.flagPopup.value = "openPenceroboh";
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function batalKemaskiniPenceroboh(){
	document.${formName}.flagPopup.value = "openPenceroboh";
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function simpanKemaskiniPenceroboh(){
	if(document.${formName}.txtNama.value == ""){
		alert('Sila masukkan Nama Penceroboh.');
  		document.${formName}.txtNama.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openPenceroboh";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniPenceroboh";
	doAjaxCall${formName}("");
}
function hapusMaklumatPenceroboh(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "hapusMaklumatPenceroboh";
	doAjaxCall${formName}("");
}
</script>
