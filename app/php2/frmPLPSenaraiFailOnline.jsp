<style type="text/css">
<!--
.style1 {
	color: #0033FF
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>CARIAN</b></legend>
      <table width="100%" align="center" border="0">
        <tr>
          <td width="30%" height="24" scope="row" align="right">No Permohonan : </td>
          <td width="70%"><input name="txtNoPermohonan" id="txtNoPermohonan" type="text" value="$txtNoPermohonan" size="50" maxlength="50" style="text-transform:uppercase;" >
            <input type="hidden" name="idFail" />
            <input type="hidden" name="idPermohonan" />
            <input type="hidden" name="actionOnline" />
            <input type="hidden" name="idPemohon" />
            <input type="hidden" name="idSubUrusan" />
          </td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Tarikh Mohon : </td>
          <td width="70%"><input type="text" name="txdTarikhPermohonan" id="txdTarikhPermohonan" value="$txdTarikhPermohonan" onBlur="check_date(this)" size="9"/>
            <a href="javascript:displayDatePicker('txdTarikhPermohonan',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
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
      </fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><b>SENARAI PERMOHONAN</b></legend>
      #parse("app/utils/record_paging.jsp")
      <table align="center" width="100%">
        <tr class="table_header">
          <td scope="row" width="4%" align="left"><strong>Bil</strong></td>
          <td width="25%" align="left"><strong>No. Rujukan <i>Online</i></strong></td>
          <td width="11%" align="left"><strong>No. Fail</strong></td>
          <td width="25%" align="left"><strong>Nama Pemohon</strong></td>
          <td width="10%" align="center"><strong>Tarikh Mohon</strong></td>
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
          <td class="$row"><a href="javascript:papar('$list.idFail','$list.idSubUrusan','$list.idPermohonan')" class="style1">$list.noPermohonan</a></td>
          <td class="$row">$list.noFail</td>
          <td class="$row">$list.namaPemohon</td>
          <td class="$row" align="center">$list.tarikhPermohonan </td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1" align="center">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
<div id="setSessionIdFail_result"></div>
<script>
function carian(){
	document.${formName}.actionOnline.value = "";
	doAjaxCall${formName}("");
}
function kosongkan(flagDetail) {
	document.${formName}.reset();
	document.${formName}.txtNoPermohonan.value = "";
	document.${formName}.txdTarikhPermohonan.value = "";
	doAjaxCall${formName}("");
}
function papar(idFail,idSubUrusan,idPermohonan) {
	document.${formName}.idFail.value = idFail;
	document.${formName}.idSubUrusan.value = idSubUrusan;
	document.${formName}.idPermohonan.value = idPermohonan;
	document.${formName}.actionOnline.value = "daftar";
	document.${formName}.submit();
}
</script>
