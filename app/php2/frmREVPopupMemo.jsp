<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
#parse("css/eTapp_PHP.css")
-->
</style>
<input name="idHasil" type="hidden" id="idHasil" value="$idHasil"/>
<input name="report" type="hidden" id="report" value="$report"/>
<fieldset>
<legend><strong>MEMO</strong></legend>
<table align="center" width="100%">
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="37%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="61%">&nbsp;</td>
  </tr>
  <tr>
    <td ><span class="style1">*</span></td>
    <td >Jenis Memo</td>
    <td >:</td>
    <td ><select name="bilPeringatan" id="bilPeringatan">
        <option value="1">MEMO TUNTUTAN DEPOSIT</option>
        <option value="2">MEMO PELARASAN DEPOSIT</option>
        <option value="3">MEMO TUNTUTAN HASIL</option>
        <option value="4">MEMO RAMPASAN DEPOSIT</option>
      </select></td>
  </tr>
  <tr>
    <td ><span class="style1">*</span></td>
    <td >Tarikh  Notis</td>
    <td >:</td>
    <td ><input type="text" name="tarikhNotis" id="tarikhNotis" onblur="check_date(this)" size="9"/>
      <a href="javascript:displayDatePicker('tarikhNotis',false,'dmy');"><img border="0" src="../../img/calendar.gif"/></a></td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td >&nbsp;</td>
    <td >&nbsp;</td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td></td>
    <td></td>
    <td><input type="button" name="cmdCetak" id="cmdCetak" value="Simpan" onClick="janaMemo()"/></td>
  </tr>
</table>
</fieldset>
<script>
function janaMemo(){
	if(document.${formName}.bilPeringatan.value == ""){
		alert('Sila pilih Jenis Memo.');
  		document.${formName}.bilPeringatan.focus();
		return;
	}
	if(document.${formName}.tarikhNotis.value == ""){
		alert('Sila masukan Tarikh Notis.');
  		document.${formName}.tarikhNotis.focus();
		return;
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	doAjaxCall${formName}("janaMemo");
}
</script>
#if ($!successSave == 'Y')
<script>
window.opener.doRefreshScreen();
window.close();
</script>
#end