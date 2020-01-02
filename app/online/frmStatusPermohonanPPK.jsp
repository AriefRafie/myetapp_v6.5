<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>


&nbsp;
<fieldset><legend><strong>Carian</strong></legend>
<table width="100%" border="0" cellpadding="2">
  <tr>
    <td width="29%" align="right">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="70%"><label>
      <input type="radio" name="radio" id="sorNoFail" value="sorNoFail" />
    No Fail 
    <input type="radio" name="radio" id="sorNoRujOnline" value="sorNoRujOnline" />
    No Permohonan Online 
    <input type="radio" name="radio" id="sorNoKPPemohon" value="sorNoKPPemohon" />
    No KP Pemohon 
    <input type="radio" name="radio" id="sorNoSijilMati" value="sorNoSijilMati" />
    No Sijil Mati</label></td>
  </tr>
  <tr>
    <td align="right">&nbsp;</td>
    <td>&nbsp;</td>
    <td><label>
      <input name="txtCarian" type="text" id="txtCarian" size="40" />
    </label></td>
  </tr>
  <tr>
    <td align="right">&nbsp;</td>
    <td>&nbsp;</td>
    <td><label>
      <input type="submit" name="cmdCari" id="cmdCari" value="Cari" />
    </label>
      <label>
      <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" />
      </label></td>
  </tr>
</table>
</fieldset>
<fieldset><legend><strong>Senarai Permohonan</strong></legend>
<table width="100%" border="0" cellpadding="2">
  <tr>
    <td width="1%">No</td>
    <td width="20%">No Fail</td>
    <td width="10%">Status Fail</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</fieldset>
<table width="100%" border="0" cellpadding="2">
  	<tr>
    <td align="right"><strong>CL-08-05</strong></td>
  	</tr>
    <tr>
    <td>
      <div align="center"><a href="javascript:statusPPK()" class="style1 style1">Semakan Status Permohonan bagi Seksyen Pembahagian Pusaka Kecil</a>||
        <a href="javascript:statusPPT()" class="style1">Semakan Status Permohonan bagi Seksyen Pengambilan Tanah</a>||
        <a href="javascript:statusPHP()" class="style1">Semakan Status Permohonan bagi Seksyen Penguatkuasa dan Hasil Persekutuan</a>||
        <a href="javascript:statusHTP()" class="style1">Semakan Status Permohonan bagi Seksyen Harta Tanah Persekutuan</a>||
      <a href="javascript:aduan()" class="style1">Semakan Aduan</a>	  </div>
     </td>
    </tr>
</table>
<script>
function statusPPK(){
	
	document.${formName}.action.value = "statusPPK";
	document.${formName}.submit();

}
function statusPPT(){

	document.${formName}.action.value = "statusPPT";
	document.${formName}.submit();
	
}
function statusPHP(){
	
	document.${formName}.action.value = "statusPHP";
	document.${formName}.submit();

}
function statusHTP(){
	
	document.${formName}.action.value = "statusHTP";
	document.${formName}.submit();
	
}
function aduan(){
	
	document.${formName}.action.value = "semakAduan";
	document.${formName}.submit();
	
}

