<fieldset>
<legend>MAKLUMAT ABT</legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%" valign="top">Jumlah Sewa Dikenakan (RM)</td>
    <td width="1%">:</td>
    <td width="70%"><strong>$!abt.jumlahSewa</strong></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td valign="top">Jumlah Bayaran Diterima (RM)</td>
    <td>:</td>
    <td><strong>$!abt.jumlahBayaran</strong></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td valign="top">Jumlah Tunggakan / (Lebihan) (RM)</td>
    <td>:</td>
    <td><strong>$!abt.tunggakan</strong></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td valign="top">Usia ABT</td>
    <td valign="top">:</td>
    <td valign="top"><strong>$!abt.bulan Bulan</strong></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td valign="top">Catatan</td>
    <td valign="top">:</td>
    <td valign="top"><textarea name="txtCatatanABT" id="txtCatatanABT" rows="5" cols="50" onblur="this.value=this.value.toUpperCase();"  >$!abt.catatanABT</textarea></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td valign="top">&nbsp;</td>
    <td valign="top">&nbsp;</td>
    <td valign="top"><input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onclick="doSimpanKemaskiniABT()"/></td>
  </tr>
</table>
</fieldset>
