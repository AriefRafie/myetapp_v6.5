<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<strong> Maklumat Si Mati</strong> 
<form name="f1" method="post">
<fieldset>
  <table style="width: 100%;" align="center" border="0">
    <tbody>
      <tr> 
        <td width="24%" height="24" scope="row" align="right"><div align="left">&nbsp;Hubungan 
            Pemohon Dengan Si Mati&nbsp;</div></td>
        <td width="76%"><input type="radio" name="sorWaris">
          Waris &nbsp;&nbsp; <select name="socWaris">
            <option selected><SILA PILIH></option>
          </select></td>
      </tr>
      <tr> 
        <td scope="row"></td>
        <td ><input type="radio" name="sorOB">
          Orang Berkepentingan &nbsp;&nbsp; <select name="socOB">
            <option selected><SILA PILIH></option>
            <option>Amanah Raya</option>
            <option>Penghulu</option>
          </select></td>
      </tr>
      <tr> 
        <td scope="row"></td>
        <td >&nbsp;</td>
      </tr>
      <tr> 
        <td scope="row">&nbsp;Memiliki Harta Tak Alih</td>
        <td ><input type="radio" name="sorAdaHTA">
          Ya </td>
      </tr>
      <tr>
        <td scope="row"></td>
        <td ><input type="radio" name="sorTiadaHTA">
          Tidak </td>
      </tr>
    </tbody>
  </table>
</fieldset>
<fieldset>
  <p>&nbsp;</p>
  <table border="0" align="center" style="width: 100%;">
    <tbody>
      <tr> 
        <td width="24%" height="24" scope="row" align="right"><div align="left">&nbsp;No. 
            KP Baru&nbsp;</div></td>
        <td width="76%">&nbsp;<input type="textbox" name="txtNoKPBaru1" size="7" maxlength="6"> - <input type="textbox" name="txtNoKPBaru2" size="3" maxlength="2"> - <input type="textbox" name="txtNoKPBaru3" size="5" maxlength="4"></td>
      </tr>
      <tr> 
        <td scope="row">&nbsp;No. KP Lama</td>
        <td >&nbsp;<input name="txtNoKPLama" type="textbox" id="txtNoKPLama" size="9" maxlength="8"></td>
      </tr>
      <tr> 
        <td scope="row">&nbsp;Lain-lain KP </td>
        <td >&nbsp;<select name="select">
            <option selected>&lt;SILA PILIH&gt;</option>
            <option>Tentera</option>
            <option>Polis</option>
            <option>Pasport</option>
          </select></td>
      </tr>
      <tr> 
        <td scope="row">&nbsp;Memiliki Harta Tak Alih</td>
        <td ><input type="radio" name="sorAdaHTA">
          Ya </td>
      </tr>
      <tr> 
        <td scope="row"></td>
        <td ><input type="radio" name="sorTiadaHTA">
          Tidak </td>
      </tr>
    </tbody>
  </table>
  <p>&nbsp;</p>
  <p> 
    <legend></legend>
  </p>
  <p>
    <legend>Senarai Fail Pusaka Kecil</legend>
  </p>
<input
 name="cmdTambah" id="cmdTambah" value="Tambah" type="button"  onclick="Tambah()"><input
 name="cmdCetak" id="cmdCetak" value="Cetak Senarai" type="submit">
<table align="center" width="100%">
  <tbody>
    <tr>
      <td scope="row" width="66">No. Bil</td>
      <td width="453">No Fail</td>
      <td width="322">Tarikh Mohon</td>
      <td width="352">Status
Fail</td>
    </tr>
   #foreach($fail in $Senaraifail)
    <tr>
      <td scope="row">$fail.bil</td>
      <td><a href="javascript:edit_item('$fail.id_Permohonan','$fail.id_Simati')" class="style1">$fail.no_Fail
      </a></td>
      <td>$fail.tarikh_Mohon</td>
      <td>$fail.keterangan</td>
    </tr>
    #end
  </tbody>
</table>
</fieldset>
	<input type="hidden" name="command" />
	<input type="hidden" name="idPermohonan" />
    <input type="hidden" name="idSimati" />
</form>
<script>
function cancel() {
document.f1.reset();
document.f1.txtNoFail.value = "";
document.f1.txtNoFail.focus();
}
function Tambah() {
	document.f1.command.value = "tambah";
	document.f1.action = "";
	document.f1.submit();
}
function search_data(){
	document.f1.command.value = "";
	//document.f1.nama_fail.value = key;
	document.f1.action = "";
	document.f1.submit();
}
function edit_item(id,id2){
	document.f1.command.value = "papar";
	document.f1.action = "";
	document.f1.idPermohonan.value = id;
	document.f1.idSimati.value = id2;
	document.f1.submit();
}
</script>

