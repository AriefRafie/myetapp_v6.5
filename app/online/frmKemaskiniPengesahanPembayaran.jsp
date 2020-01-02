
<input name="modePembayaran" type="hidden" value="$modePembayaran" />
<input name="noAkaunBil" type="hidden" value="$noAkaunBil" />
<input name="id_Jenisbayaran" type="hidden" value="$jenisBayaran" />
<input name="amaunBayaran" type="hidden" value="$amaunBayaran" />
<input name="kaedahPembayaran" type="hidden" value="$kaedahPembayaran" />
<input name="bank" type="hidden" value="$bank" />
<input name="noAkaunPembayar" type="hidden" value="$noAkaunPembayar" />
<input name="namaPemegangAkaun" type="hidden" value="$namaPemegangAkaun" />
<input name="noAkaunPenerima" type="hidden" value="$noAkaunPenerima" />
<input name="emel" type="hidden" value="$emel" />
<input name="action" type="hidden" value="$action" />
&nbsp;
<fieldset>
<legend><strong>Pengesahan Pembayaran</strong></legend>
<table width="100%" border="0" cellpadding="2">
  <tr>
    <td width="29%">Nama</td>
    <td width="1%">:</td>
    <td width="70%"><label>
      <input name="txtNamaPemegangAkaun" type="text" id="txtNamaPemegangAkaun" value="$namaPemegangAkaun" readonly="readonly" />
    </label></td>
  </tr>
  <tr>
    <td>Jenis Urusan Pembayaran</td>
    <td>:</td>
    <td>$selectJenisBayaranB</td>
  </tr>
  <tr>
    <td>No Perjanjian / No Lesen / No Pajakan</td>
    <td>:</td>
    <td><label>
      <input name="txtNoAkaunBil" type="text" id="txtNoAkaunBil" value="$noAkaunBil" readonly="readonly" />
    </label></td>
  </tr>
  <tr>
    <td>Amaun Bayaran (RM)</td>
    <td>:</td>
    <td><label>
      <input name="txtAmaunBayaran" type="text" id="txtAmaunBayaran" value="$amaunBayaran" readonly="readonly" />
    </label></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr align="center">
    <td colspan="3"><label>
      <input type="button" name="cmdSetuju" id="cmdSetuju" value="Setuju" onclick="setuju()">
    </label>
      <label>
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal">
      </label></td>
    </tr>
</table>
</fieldset>
<table width="100%" border="0" cellpadding="2">
  	<tr>
    <td align="right"><strong>CL-08-18</strong></td>
  	</tr>
</table>

<script>
function setuju(){
	document.${formName}.action.value = "setuju";
	document.${formName}.submit();
}

</script>
