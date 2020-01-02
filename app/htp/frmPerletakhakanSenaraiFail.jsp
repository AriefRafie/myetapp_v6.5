<strong> Senarai Fail $tajuk </strong>
<br><br>
<fieldset> <legend>Carian</legend>
<form name="f1" method="post">
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
        <td width="40%" height="25" align="right"><strong>Nama Fail : &nbsp;&nbsp;</strong></td>
        <td width="60%"><input name="NamaFail" type="text" size="44" maxlength="300" value="$carian" onkeyup="this.value=this.value.toUpperCase();"></td>
      </tr>
      <tr>
        <td height="25"  align="right"><strong>No. Fail : &nbsp;&nbsp;</strong></td>
        <td><input name="NoFail" type="text" size="44" maxlength="400" value="$carianNoFail" onkeyup="this.value=this.value.toUpperCase();"></td>
      </tr>
      <tr>
        <td height="25"  align="right"><strong>Negeri : &nbsp;&nbsp;</strong></td>
        <td>$selectNegeri</td>
      </tr>
      <tr>
        <td  align="right"><strong>Status : &nbsp;&nbsp;</strong></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td></td>
        <td><input name="cari" value="Cari" type="button" onclick="javascript:search_data()">
        <input value="Kosongkan" onclick="javascript:cancel()" type="button"></td>
      </tr>
    </tbody>
  </table>
  <input type="hidden" name="command">
</form>
</fieldset>
<fieldset>
<legend>Senarai Fail</legend>
<form name="tbh" method="post">
	<input name="add" value="Tambah" type="button" onClick="Tambah()">
    <input type="hidden" name="command">
</form>
<form name="f2" method="post">
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr class="table_header">
        <td width="8%" align="center"><b>No</b></td>
        <td width="15%" align="center"><b>No Fail</b></td>
        <td width="19%" align="center"><b>Negeri</b></td>
        <td width="38%" align="center"><b>Nama Fail</b></td>
        <td width="20%" align="center"><b>Status</b></td>
      </tr>
      <tr>
      	#set ($count = 0)
      		#foreach ( $fail in $Senaraifail )
            #set ($count = $count+1)
            #set( $i = $velocityCount )
            #if ( ($i % 2) != 1 )
              #set( $row = "row2" )
            #else
              #set( $row = "row1" )
            #end
        <td class="$row"><div align="center">$fail.bil</div></td>
        <td class="$row"><div align="center"><a href="javascript:seterusnya('$fail.id', '$fail.no')">$fail.no</a></div></td>
        <td class="$row"><div align="center">$fail.negeri</div></td>
        <td class="$row"><div align="center">$fail.tajuk</div></td>
        <td class="$row"><div align="center">$fail.keterangan</div></td>
      </tr>
      #end
    </tbody>
  </table>
  <input type="hidden" name="idFail">
  <input type="hidden" name="noFail">
  <input type="hidden" name="command">
</form>
</fieldset>
<script>
function cancel() {
document.f1.reset();
document.f1.NamaFail.value = "";
document.f1.NoFail.value = "";
document.f1.socNegeri.value = "";
document.f1.NamaFail.focus();
}
function Tambah() {
	document.tbh.command.value = "FailBaru";
	document.tbh.action = "";
	document.tbh.submit();
}
function search_data(){
	document.f1.command.value = "";
	//document.f1.nama_fail.value = key;
	document.f1.action = "";
	document.f1.submit();
}
function seterusnya(id, no) {
	document.f2.idFail.value = id;
	document.f2.noFail.value = no;
	document.f2.command.value = "SenaraiPermohonan";
	//document.f2.action = "?_portal_module=ekptg.htp.FrmGadaianSemakan";
	document.f2.action = "";
	document.f2.submit();
}
</script>
<script language="JavaScript"> 
	document.forms[0].NamaFail.focus(); 
	#if ($Negeri == 20)
		document.forms[0].socNegeri.value = "";
	#end
</script>