<strong> Senarai Fail Gadaian </strong>
<br><br>
<fieldset> <legend>Carian</legend>
<form name="f1" method="post">
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
        <td height="25" align="right"><strong>No. Fail : &nbsp;&nbsp;</strong></td>
        <td><input name="NoFail" type="text" size="55" maxlength="400" value="$carianNoFail" onkeyup="this.value=this.value.toUpperCase();" /></td>
      </tr>
      <tr>
        <td height="25" align="right"><strong>Nama Fail : &nbsp;&nbsp;</strong></td>
        <td><input name="NamaFail" type="text" size="55" maxlength="300" value="$carian" onkeyup="this.value=this.value.toUpperCase();"></td>
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
<form name="f2" method="post">
  <table border="0" cellpadding="2" cellspacing="1" width="100%">
    <tbody>
      <tr class="table_header">
        <td width="5%"><b>No</b></td>
        <td width="20%"><b>No Fail</b></td>
        <td width="17%"><b>Negeri</b></td>
        <td width="35%"><b>Nama Fail</b></td>
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
        <td class="$row">$fail.bil</td>
        <td class="$row"><a href="javascript:seterusnya('$fail.id', '$fail.no')">$fail.no</a></td>
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