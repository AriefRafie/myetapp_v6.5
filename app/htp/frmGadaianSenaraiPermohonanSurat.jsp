<strong> Senarai Permohonan </strong>
<br><br>
<fieldset> <legend>Carian</legend>
<form name="f1" method="post">
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr class="table_header">
        <td align="right">Nama Pemilik : &nbsp;&nbsp;</td>
        <td><input name="NamaPemohon" type="text" size="40" value="$carian" onkeyup="this.value=this.value.toUpperCase();"></td>
      </tr>
      <tr>
        <td></td>
        <td><input name="cari" value="Cari" type="button" onclick="javascript:search_data()">
        <input value="Kosongkan" onclick="javascript:cancel();" type="button"></td>
      </tr>
    </tbody>
  </table>
  <input type="hidden" name="idFail" value="$IdFail">
  <input type="hidden" name="noFail" value="$NoFail">
  <input type="hidden" name="command">
</form>
</fieldset>
<fieldset>
<legend>Senarai Permohonan untuk Fail $NoFail</legend>
<form name="tbh" method="post">
	<input name="kembali" value="Kembali" type="button" onclick="Kembali()" />
    <input type="hidden" name="command" />
    <input type="hidden" name="mode" />
</form>
<form name="f2" method="post">
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr class="table_header">
        <td width="20%"><b>No</b></td>
        <td width="70%"><b>Nama Pemilik</b></td>
        <td width="10%"><b>Tindakan</b></td>
      </tr>
      #set ($count = 0)
      #foreach ( $permohonan in $SenaraiPermohonan )
      #set ($count = $count+1)
      	#set( $i = $velocityCount )
          #if ( ($i % 2) != 1 )
               #set( $row = "row2" )
          #else
               #set( $row = "row1" )
          #end
      <tr>
        <td class="$row">$permohonan.bil</td>
        <td class="$row">$permohonan.nama</td>
        <td class="$row"><input type="submit" name="paparSurat" value="Papar Surat" onclick="Papar('$permohonan.idPermohonan','$permohonan.idNegeri')" /></td>
    </tr>
      #end
      #if ($count == 0)
      <tr>
        <td colspan="5" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
      </tr>
      #end
    </tbody>
  </table>
  <input type="hidden" name="idFail" value="$IdFail">
  <input type="hidden" name="noFail" value="$NoFail">
  <input type="hidden" name="idNegeri">
  <input type="hidden" name="idPermohonan">
  <input type="hidden" name="command">
</form>
</fieldset>
<script>
function cancel() {
document.f1.reset();
document.f1.NamaPemohon.value = "";
document.forms[0].NamaPemohon.focus();
}
function search_data(){
	document.f1.command.value = "SenaraiPermohonan";
	document.f1.action = "";
	document.f1.submit();
}
function Papar(idPermohonan, idNegeri) {
	document.f2.idPermohonan.value = idPermohonan;
	document.f2.idNegeri.value = idNegeri;
	document.f2.command.value = "PaparMenu";
	document.f2.action = "";
	document.f2.submit();
}
function Kembali() {
	document.tbh.command.value = "";
	document.tbh.action = "";
	document.tbh.submit();
}
</script>
<script language="JavaScript"> document.forms[0].NamaPemohon.focus(); </script>