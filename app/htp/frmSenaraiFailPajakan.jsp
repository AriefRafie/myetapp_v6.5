<style type="text/css">
<!--
.style1 {font-weight: bold}
-->
</style>
<strong> Senarai Fail Pajakan </strong>
<br><br>
<fieldset> <legend>Carian</legend>
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
        <td height="25" align="right"><strong>No. Fail : &nbsp;&nbsp;</strong></td>
        <td><input name="NoFail" type="text" size="44" maxlength="400" value="$carianNoFail" onkeyup="this.value=this.value.toUpperCase();" /></td>
      </tr>
      <tr>
        <td height="25" align="right"><strong>Nama Fail : &nbsp;&nbsp;</strong></td>
        <td><input name="NamaFail" type="text" size="44" maxlength="300" value="$carian" onkeyup="this.value=this.value.toUpperCase();"></td>
      </tr>
      <tr>
        <td height="25"  align="right"><strong>Negeri : &nbsp;&nbsp;</strong></td>
        <td>$selectNegeri</td>
      </tr>
      <tr style="display:none">
        <td  align="right"><strong>Status : &nbsp;&nbsp;</strong></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td></td>
        <td><input class=button name="cari" value="Cari " type="button" onclick="javascript:search_data()">
        <input value="Kosongkan" onclick="javascript:cancel()" type="button"></td>
      </tr>
    </tbody>
  </table>
</fieldset>
<fieldset>
<legend>Senarai Fail</legend>
	<input class=button name="add" value="Tambah" type="button" onClick="Tambah()">
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr class="table_header">
        <td width="5%"><b>No</b></td>
        <td width="23%"><b>No Fail</b></td>
        <td width="17%"><b>Negeri</b></td>
        <td width="32%"><b>Nama Fail</b></td>
        <td width="23%"><b>Status</b></td>
      </tr>	
      #set ($count = 0)
      #foreach ( $fail in $Senaraifail )
      	#set ($count = $count+1)
          #set( $i = $velocityCount )
          #if ( ($i % 2) != 1 )
               #set( $row = "row2" )
          #else
               #set( $row = "row1" )
          #end
      <tr>
        <td class="$row" scope="row">$fail.bil</td>
        <td class="$row"><a href="javascript:seterusnya('$fail.idPermohonan', '$fail.idFail', '$fail.noFail')" class="style1">$fail.noFail</a></td>
        <td class="$row">$fail.negeri</td>
        <td class="$row">$fail.tajuk</td>
        <td class="$row">$fail.keterangan</td>
      </tr>
      #end
      #if ($count == 0)
      <tr> 
        <td colspan="5" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
      </tr>
      #end
    </tbody>
  </table>
  <input type="hidden" name="idPermohonan">
  <input type="hidden" name="idFail">
  <input type="hidden" name="noFail">
  <input type="hidden" name="commander" value="$commander">
  <input type="hidden" name="mode">
</fieldset>
<script>
function cancel() {
document.${formName}.reset();
document.${formName}.NamaFail.value = "";
document.${formName}.NoFail.value = "";
document.${formName}.socNegeri.value = "";
document.${formName}.NoFail.focus();
}
function Tambah() {
	document.${formName}.commander.value = "FailBaru";
	//document.tbh.action = "?_portal_module=ekptg.htp.FrmGadaianSemakan";
	document.${formName}.action = "";
	document.${formName}.submit();
}
function search_data(){
	document.${formName}.commander.value = "";
	//document.f1.nama_fail.value = key;
	document.${formName}.action = "";
	document.${formName}.submit();
}
function seterusnya(idPermohonan, idFail, noFail) {
	document.${formName}.idPermohonan.value = idPermohonan;
	document.${formName}.idFail.value = idFail;
	document.${formName}.noFail.value = noFail;
	document.${formName}.mode.value = "view";
	document.${formName}.commander.value = "FailBaru";
	//document.f2.action = "?_portal_module=ekptg.htp.FrmGadaianSemakan";
	document.${formName}.action = "";
	document.${formName}.submit();
}
</script>
<script language="JavaScript"> 
	document.${formName}.NoFail.focus(); 
	#if ($Negeri == 20)
		document.${formName}.socNegeri.value = "";
	#end
</script>