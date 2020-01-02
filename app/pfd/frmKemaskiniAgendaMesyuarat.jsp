<style type="text/css">
<!--
.style1 {font-size: 10px}
-->
</style>
<form id="f1" name="f1" method="post" action="">
<input name="command" type="hidden" value="$command" />
<input name="command1" type="hidden" />
<input name="mode" type="hidden" value="$mode" />
<input name="agendaMsyrt" type="hidden" value="$agendaMsyrt" />
&nbsp;
<fieldset>
<legend>MAKLUMAT AGENDA MESYUARAT</legend>
<table width="100%">
  <tr>
  #foreach ($agenda in $AgendaMesyuarat)
  <input name="idAgendamesyuarat" type="hidden" value="$agenda.id_Agendamesyuarat" />
    <td width="29%" align="left" scope="row"><div align="right" class="style1">Agenda Mesyuarat</div></td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td width="70%">
      <label>
        <textarea name="txtAgenda" cols="44" id="txtAgenda" $readonly="$readOnly">$agenda.agenda_Mesyuarat</textarea>
        </label>        </td>
  #end  </tr>
  <tr>
    <td colspan="3" align="center" scope="row">&nbsp;</td>
    </tr>
  <tr>
    <td colspan="3" align="center" scope="row">
     #if ($mode == 'View')
        <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskini()" />
        <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()" />
     #else
        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan()" />
        <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal()"  />
     #end        </td>
  </tr>
</table>
</fieldset>
<table width="100%" border="0" cellpadding="2">
  <tr>
    <td align="right"><strong>CL-05-21</strong></td>
  </tr>
</table>
 </form>
<script>
function batal(){

	window.close();
}
function kembali(){

	window.close();
}
function simpan(){
if ( !window.confirm("Anda Pasti?") ) return;
	document.f1.action = "";
	document.f1.command1.value = "simpan";
	if (document.f1.idAgendamesyuarat.value == "" || document.f1.idAgendamesyuarat.value == 0){
		
		document.f1.mode.value = "tambahBaru";
	}
	else{
		
		document.f1.mode.value = "kemaskiniAgenda";
	}
	
	document.f1.submit();
	window.close();
}
function kemaskini(){
	document.f1.command1.value = "kemaskini";
	document.f1.submit();
}
 

</script>