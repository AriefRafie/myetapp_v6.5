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
<legend> #if ($!report == 'notisTuntutanTunggakan') <strong>KEMASKINI NOTIS TUNTUTAN TUNGGAKAN SEWA</strong> #end
#if ($!report == 'notisRampasanDeposit') <strong>KEMASKINI NOTIS RAMPASAN DEPOSIT</strong> #end
#if ($!report == 'notisTuntutan') <strong>KEMASKINI NOTIS TUNTUTAN</strong> #end </legend>
#foreach ($beanMaklumatNotis in $BeanMaklumatNotis)
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
    <td ><select name="bilPeringatan" id="bilPeringatan" disabled>
        <option value="1" #if($!beanMaklumatNotis.bilPeringatan.equals("1")) selected #end>PERTAMA</option>
        <option value="2" #if($!beanMaklumatNotis.bilPeringatan.equals("2")) selected #end>KEDUA</option>
        <option value="3" #if($!beanMaklumatNotis.bilPeringatan.equals("3")) selected #end>KETIGA</option>
      </select></td>
  </tr>
  #end
  <tr>
    <td ><span class="style1">*</span></td>
    <td >Tarikh Mula Notis</td>
    <td >:</td>
    <td ><input type="text" name="tarikhNotis" id="tarikhNotis" onblur="check_date(this)" size="9" value="$!beanMaklumatNotis.tarikhNotis"/>
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
      <input type="button" name="cmdCetak" id="cmdCetak" value="Simpan" onClick="kemaskiniNotisTuntutanTunggakan()"/>
      #end
      #if ($!report == 'notisRampasanDeposit')
      <input type="button" name="cmdCetak" id="cmdCetak" value="Simpan" onClick="kemaskiniNotisRampasanDeposit()"/>
      #end
      #if($report=="notisTuntutan")
      <input type="button" name="cmdCetak" id="cmdCetak" value="Simpan" onClick="kemaskiniNotisTuntutan()"/>
      #end </td>
  </tr>
</table>
#end
</fieldset>
<script>
function kemaskiniNotisTuntutanTunggakan(){
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
	doAjaxCall${formName}("kemaskiniNotisTuntutanTunggakan");
}

function kemaskiniNotisRampasanDeposit(){
	if(document.${formName}.tarikhNotis.value == ""){
		alert('Sila masukan Tarikh Notis.');
  		document.${formName}.tarikhNotis.focus();
		return;
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	doAjaxCall${formName}("kemaskiniNotisRampasanDeposit");
}

function kemaskiniNotisTuntutan(){
	if(document.${formName}.tarikhNotis.value == ""){
		alert('Sila masukan Tarikh Notis.');
  		document.${formName}.tarikhNotis.focus();
		return;
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	doAjaxCall${formName}("kemaskiniNotisTuntutan");
}
</script>
#if ($!successSave == 'Y')
<script>
window.opener.doRefreshScreen();
window.close();
</script>
#end