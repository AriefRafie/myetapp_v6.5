<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<input name="action" type="hidden" value="$action">
<table width="100%" border="0" cellpadding="2">
  <tr>
    <td><fieldset>
    <legend>::<strong>Semakan</strong>::</legend>
    <p><a href="javascript:statusPPK()" class="style1">Semakan Status Permohonan bagi Seksyen Pembahagian Pusaka Kecil</a></p>
    <p><a href="javascript:statusPPT()" class="style1">Semakan Status Permohonan bagi Seksyen Pengambilan Tanah</a></p>
    <p><a href="javascript:statusPHP()" class="style1">Semakan Status Permohonan bagi Seksyen Penguatkuasa dan Hasil Persekutuan</a></p>
    <p><a href="javascript:statusHTP()" class="style1">Semakan Status Permohonan bagi Seksyen Harta Tanah Persekutuan</a></p>
    <p><a href="javascript:aduan()" class="style1">Semakan Aduan</a></p>
    </fieldset>
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
</script>