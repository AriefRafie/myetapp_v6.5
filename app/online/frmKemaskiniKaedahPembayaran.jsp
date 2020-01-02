<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>

&nbsp;
<input name="modePembayaran" type="hidden" value="$modePembayaran" />
<input name="noAkaunBil" type="hidden" value="$noAkaunBil" />
<input name="id_Jenisbayaran" type="hidden" value="$jenisBayaran" />
<input name="amaunBayaran" type="hidden" value="$amaunBayaran" />
<input name="action" type="hidden" value="$action" />
<input name="radioCheck2" type="hidden" value="$radioCheck2" />
<fieldset>
<legend><strong>::Kaedah Pembayaran::</strong></legend>
<table width="100%" border="0" cellpadding="2">
  <tr>
    <td width="29%"><span class="style1">*</span>Kaedah Pembayaran</td>
    <td width="1%">:</td>
    <td width="70%"><label>
    <input type="radio" name="radio" id="sorDebit" value="1" onclick="debit()" $radioChecked3/>
    Debit Kad
    <input type="radio" name="radio" id="sorCredit" value="2" onclick="credit()" $radioChecked4/>
    Kredit Kad
    </label></td>
  </tr>
  <tr>
    <td><span class="style1">*</span>Bank</td>
    <td>:</td>
    <td><label>$selectBank</label></td>
  </tr>
  <tr>
    <td><span class="style1">*</span>No Akaun Pembayar</td>
    <td>:</td>
    <td><label>
      <input type="text" name="txtNoAkaunPembayar" id="txtNoAkaunPembayar">
    </label></td>
  </tr>
  <tr>
    <td><span class="style1">*</span>Nama Pemegang Akaun</td>
    <td>:</td>
    <td><label>
      <input type="text" name="txtNamaPemegangAkaun" id="txtNamaPemegangAkaun" />
    </label></td>
  </tr>
  <tr>
    <td><span class="style1">*</span>No Akaun Penerima</td>
    <td>:</td>
    <td><label>
      <input type="text" name="txtNoAkaunPenerima" id="txtNoAkaunPenerima">
    </label></td>
  </tr>
  <tr>
    <td>Tarikh Efektif</td>
    <td>:</td>
    <td>$tarikhEfektif    </td>
  </tr>
  <tr>
    <td><span class="style1">*</span>Emel</td>
    <td>:</td>
    <td><label>
      <input type="text" name="txtEmel" id="txtEmel">
    </label>    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="seterusnya()"></td>
  </tr>
</table>
</fieldset>
<table width="100%" border="0" cellpadding="2">
  	<tr>
    <td align="right"><strong>CL-08-19</strong></td>
  	</tr>
</table>
<script>
function seterusnya(){
if ( !window.confirm("Anda Pasti?") ) return;
	
		if (document.${formName}.sorDebit.value == ""){
			alert('Sila pilih " Kaedah Pembayaran " terlebih dahulu.');
			document.${formName}.sorDebit.focus();
			return;
		} 
		if (document.${formName}.sorCredit.value == ""){
			alert('Sila pilih " Kaedah Pembayaran " terlebih dahulu.');
			document.${formName}.sorCredit.focus();
			return;
		}
		if (document.${formName}.socBank.value == ""){
			alert('Sila masukkan " Bank " terlebih dahulu.');
			document.${formName}.socBank.focus();
			return;
		} 
		if (document.${formName}.txtNoAkaunPembayar.value == ""){
			alert('Sila masukkan " No Akaun Pembayar " terlebih dahulu.');
			document.${formName}.txtNoAkaunPembayar.focus();
			return;
		} 
		if (document.${formName}.txtNamaPemegangAkaun.value == ""){
			alert('Sila masukkan " Nama Pemegang Akaun " terlebih dahulu.');
			document.${formName}.txtNamaPemegangAkaun.focus();
			return;
		}
		if (document.${formName}.txtNoAkaunPenerima.value == ""){
			alert('Sila masukkan " No Akaun Penerima " terlebih dahulu.');
			document.${formName}.txtNoAkaunPenerima.focus();
			return;
		} 
		if (document.${formName}.txtEmel.value == ""){
			alert('Sila masukkan " Emel " terlebih dahulu.');
			document.${formName}.txtEmel.focus();
			return;
		}
	
	document.${formName}.action.value = "seterusnya2";
	document.${formName}.submit();
}
function debit(){
	document.${formName}.radioCheck2.value = "debit";
	document.${formName}.submit();
}
function credit(){
	document.${formName}.radioCheck2.value = "credit";
	document.${formName}.submit();
}
</script>

