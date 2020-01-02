<style type="text/css">
<!--
.style1 {
	color: #0033FF
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input type="hidden" name="actionOnline" />
  <input type="hidden" name="idFail" />
  <input type="hidden" name="idStatus" />
  <input type="hidden" name="idPemohon" />
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>SENARAI PERMOHONAN</b></legend>
      <table align="center" width="100%">
        <tr>
          <td colspan="5" scope="row"><input name="cmdDaftar" type="button" value="Daftar Permohonan Baru" onClick="javascript:daftarBaru()"/></td>
        </tr>
        <tr class="table_header">
            <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="30%"><strong>No Permohonan</strong></td>
          <td width="30%"><strong>No Fail</strong></td>
          <td width="14%" align="center"><strong>Tarikh Mohon</strong></td>
          <td width="20%"><strong>Status</strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiFail.size() > 0)
        #foreach ($list in $SenaraiFail)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row"><a href="javascript:papar('$list.idFail','$list.idStatus','$list.idPemohon')" class="style1">$list.noPermohonan</a></td>
          <td class="$row">$list.noFail</td>
          <td class="$row" align="center">$list.tarikhPermohonan </td>
          <td class="$row">$list.status</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
<div id="setSessionIdFail_result"></div>
<script>
function papar(idFail,idStatus,idPemohon) {
	document.${formName}.idFail.value = idFail;
	document.${formName}.idStatus.value = idStatus;
	document.${formName}.idPemohon.value = idPemohon;
	document.${formName}.actionOnline.value = "seterusnya"; 	
	document.${formName}.submit();
}
function daftarBaru(){
	document.${formName}.actionOnline.value = "daftarBaru";
	document.${formName}.submit();
}
</script>
