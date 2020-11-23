<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0000FF
}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset><legend><strong>STATUS ADUAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
          <tr>
            <td>&nbsp;</td>
            <td valign="top">No Aduan</td>
            <td >:</td>
            <td>$!complaint.id </td>
          </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%" valign="top">Nama Pengadu</td>
          <td width="1%" >:</td>
          <td width="70%">$!complaint.namaPengadu </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Emel</td>
          <td>:</td>
          <td>$!complaint.emailPengadu</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">No Telefon</td>
          <td>:</td>
          <td>$!complaint.phonePengadu</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">Jenis Aduan</td>
          <td>:</td>
          <td>$!complaint.type.code -  $!complaint.type.description</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">Butiran Aduan Pengadu</td>
          <td>:</td>
          <td>$!complaint.catatan </td>
        </tr>
        <!-- 
        <tr>
          <td width="1%">&nbsp;</td>
          <td valign="top">Status</td>
          <td>:</td>
          <td>#if($complaint.status == '')  NO ADUAN TIDAK DITEMUI #end <font color="red">$!complaint.status</font>#if($complaint.status == "")  NO ADUAN TIDAK DITEMUI #end</td>
        </tr>
        -->
        <tr>
        	<td width="1%">&nbsp;</td>
          	<td valign="top">Jawapan</td>
          	<td>:</td>
          	<td>
          		#if($!complaint.catatanSelesai =="")
          			Aduan masih dalam proses
          		#else
          			$!complaint.catatanSelesai
          		#end
          	</td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2" valign="bottom">&nbsp;</td>
  </tr>
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%"><input type="button" name="cmdCari" id="cmdCari" value="Kembali" onclick="kembali()" /> 
    </td>
  </tr>
</table>
