<style type="text/css">
<!--
.style1 {
	color: #0033FF
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idPemohon" type="hidden" id="idPemohon" value="$idPemohon"/>
  <input name="idHakmilik" type="hidden" id="idHakmilik" value="$idHakmilik"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="flagAktif" type="hidden" id="flagAktif" value="$flagAktif"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="hitButtonDaftar" type="hidden" id="hitButtonDaftar"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
</p>
<body>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/frmAPBHeader.jsp") </td>
  </tr>   
  #else
  <tr>
    <td>&nbsp;
      <div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end
  #if ($idFail != '' &&  $idStatus != '1610207')
  <tr>
    <td>&nbsp;
      <div class="error">ADUAN TIDAK BOLEH DILAKUKAN BAGI FAIL INI</div></td> 
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
  </tr>
 #if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610235'  && $idStatus != '1610201'  && $idStatus != '1610213' && $idStatus != '1610205'  && $idStatus != '1610206' && $idStatus != '1610236' && $idStatus != '1610237' && $idStatus != '1610238' && $idStatus != '1610239' && $idStatus != '1610244')  
  #if ($flagAktif =='1')
  <tr>
  <td>
  <fieldset>
      <legend><b>CARIAN</b></legend>
      <table width="100%" align="center" border="0">
        <tr>
          <td width="30%" height="24" scope="row" align="right">Nama Pengadu : </td>
          <td width="70%"><input name="txtNamaPengadu" id="txtNamaPengadu" type="text" value="$txtNamaPengadu" size="50" maxlength="50" style="text-transform:uppercase;" >
            <input type="hidden" name="idPengadu" />
            <input type="hidden" name="actionAduan"/></td>
        </tr>
        <tr>
          <td scope="row"></td>
          <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onClick="javascript:carian()">
            <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="javascript:kosongkan()"></td>
        </tr>
        <tr>
          <td scope="row">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </table>
      </fieldset>
  </td>
  </tr>
  <tr>
    <td>
    <fieldset>
      <legend><b>SENARAI PENGADU</b></legend>
      #parse("app/utils/record_paging.jsp")
      <table align="center" width="100%">
        <tr>
          <td colspan="3" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onClick="javascript:daftarBaru()"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="50%"><strong>Nama Pengadu</strong></td>
          <td width="45%"><strong>Perkara</strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiPengadu.size() > 0)
        #foreach ($list in $SenaraiPengadu)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row"><a href="javascript:papar('$list.idPengadu')" class="style1">$list.namaPengadu</a></td>
          <td class="$row">$list.perkara</td>
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
      </fieldset>
    </td>
  </tr>
  #end
  #end
</table>
<script>
function carian(){
	document.${formName}.actionAduan.value = "";
	document.${formName}.submit();
}
function kosongkan() {
	document.${formName}.reset();
	document.${formName}.txtNamaPengadu.value = "";
	document.${formName}.submit();
}
function papar(idPengadu) {

	document.${formName}.idPengadu.value = idPengadu;
	document.${formName}.actionAduan.value = "maklumatAduan";
	document.${formName}.submit();
}
function daftarBaru(){
	document.${formName}.actionAduan.value = "daftarBaru";
	document.${formName}.submit();
}
</script>
