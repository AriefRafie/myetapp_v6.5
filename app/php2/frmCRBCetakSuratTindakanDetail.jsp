<style type="text/css">
<!--
.style1 {color: #0000FF}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($flagPopup == 'openSuratTindakan')
  <tr>
    <td> #parse("app/php2/frmCRBMaklumatSuratTindakanPeringatan.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend><b>SENARAI SURAT TINDAKAN PENGUATKUASAAN</b></legend>
      <table align="center" width="100%">
        <tr>
          <td colspan="8" scope="row"><input name="cmdTambah" type="button" value="Tambah" onclick="javascript:doDaftarMaklumatSuratTindakan()"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="26%"><strong>Nama Dokumen</strong></td>
          <td width="39%"><strong>Individu  / Pejabat</strong></td>
          <td width="10%" align="center"><strong>Tarikh Hantar</strong></td>
          <td width="10%"><strong>Status</strong></td>
          <td width="5%"><strong></strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiSuratTindakan.size() > 0)
        #foreach ($list in $SenaraiSuratTindakan)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          #if ($list.bilUlangan == '' || $list.bilUlangan == '0')
          <td class="$row"><a href="javascript:paparMaklumatSuratTindakan('$list.idUlasanTeknikal')" class="style1">$list.namaDokumen</a></td>
          #else
          <td class="$row"><a href="javascript:paparMaklumatSuratTindakan('$list.idUlasanTeknikal')" class="style1">$list.namaDokumen</a>&nbsp;&nbsp;(ULANGAN $list.bilUlangan)</td>
          #end
          <td class="$row">$list.namaPejabat</td>
          <td class="$row" align="center">$list.tarikhHantar</td>
          <td class="$row">$list.status</td>
           <td class="$row" align="center"><a href="#" class="style2" onClick="javascript:cetakCRBSuratTindakanPenguatkuasaan('$idFail','$list.idUlasanTeknikal')"><img border="0" src="../img/print.gif"/></a></td>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" >Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function paparMaklumatSuratTindakan(idUlasanTeknikal){
	document.${formName}.flagPopup.value = "openSuratTindakan";
	document.${formName}.modePopup.value = "view";
	document.${formName}.idUlasanTeknikal.value = idUlasanTeknikal;
	doAjaxCall${formName}("");
}
function doDaftarMaklumatSuratTindakan(){
	document.${formName}.flagPopup.value = "openSuratTindakan";
	document.${formName}.modePopup.value = "new";
	doAjaxCall${formName}("");
}
function doSimpanMaklumatSuratTindakan(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openSuratTindakan";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanMaklumatSuratTindakan";
	doAjaxCall${formName}("");
}
function doBatalMaklumatSuratTindakan(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function doKemaskiniMaklumatSuratTindakan(){
	document.${formName}.flagPopup.value = "openSuratTindakan";
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function doBatalKemaskiniMaklumatSuratTindakan(){
	document.${formName}.flagPopup.value = "openSuratTindakan";
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function doSimpanKemaskiniMaklumatSuratTindakan(){	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openSuratTindakan";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniMaklumatSuratTindakan";
	doAjaxCall${formName}("");
}
function doHapusMaklumatSuratTindakan(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "hapusMaklumatSuratTindakan";
	doAjaxCall${formName}("");
}
</script>
