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
<legend> #if ($!report == 'notisTuntutanTunggakan') <strong>NOTIS TUNTUTAN TUNGGAKAN SEWA</strong> #end
#if ($!report == 'notisRampasanDeposit') <strong>NOTIS RAMPASAN DEPOSIT</strong> #end </legend>
<table align="center" width="100%">
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="37%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="61%">&nbsp;</td>
  </tr>
  #if ($!report == 'notisTuntutanTunggakan')
  <tr>
    <td ><span class="style1">*</span></td>
    <td >Bil. Peringatan</td>
    <td >:</td>
    <td ><select name="bilPeringatan" id="bilPeringatan">
        <option value="1">PERTAMA</option>
        <option value="2">KEDUA</option>
        <option value="3">KETIGA</option>
      </select></td>
  </tr>
  #end
  <tr>
    <td ><span class="style1">*</span></td>
    <td >Tarikh Mula Notis</td>
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
    <td> #if($report=="notisTuntutanTunggakan")
      <input type="button" name="cmdCetak" id="cmdCetak" value="Simpan" onClick="janaNotisTuntutanTunggakan()"/>
      #end
      #if ($!report == 'notisRampasanDeposit')
      <input type="button" name="cmdCetak" id="cmdCetak" value="Simpan" onClick="janaNotisRampasanDeposit()"/>
      #end </td>
  </tr>
</table>
</fieldset>
<script>
function janaNotisTuntutanTunggakan(){
	if(document.${formName}.bilPeringatan.value == ""){
		alert('Sila pilih bilangan Peringatan.');
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
	doAjaxCall${formName}("janaNotisTuntutanTunggakan");
}

function janaNotisRampasanDeposit(){
	if(document.${formName}.tarikhNotis.value == ""){
		alert('Sila masukan Tarikh Notis.');
  		document.${formName}.tarikhNotis.focus();
		return;
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	doAjaxCall${formName}("janaNotisRampasanDeposit");
}
</script>
#if ($!successSave == 'Y')
<script>
window.opener.doRefreshScreen();
window.close();
</script>
#end