<style type="text/css">
<!--
.style1 {font-size: 10px}
-->
</style>
<form id="f1" name="f1" method="post" action="">
<input name="idMinitmesyuaratpara" type="hidden" value="$idMinitmesyuaratpara" />
<input name="passIdMinitmesyuaratsubpara" type="hidden" value="$passIdMinitmesyuaratsubpara" />
<input name="command" type="hidden" value="$command" />
<input name="command1" type="hidden" value="" />
<input name="mode" type="hidden" value="" />
&nbsp;
<fieldset>
 MAKLUMAT SUB MINIT MESYUARAT
 <table width="100%">
 #foreach ($subminit in $SubMinitMesyuarat)
 <input name="idMinitmesyuaratsubpara" type="hidden" value="$subminit.id_Minitmesyuaratsubpara" />
  <tr>
    <td width="29%" scope="row" valign="top"><div align="right" class="style1">
      <div align="left">Sub Minit Mesyuarat</div>
    </div></td>
    <td width="1%" scope="row" valign="top"><div align="right" class="style1">:</div></td>
    <td width="70%">
      <label>
        <textarea name="txtSubMinit" cols="41" id="txtSubMinit" $readOnly>$subminit.sub_Para</textarea>
        </label>        </td>
  </tr>
  <tr>
    <td colspan="3" align="center" scope="row"><div align="left"></div></td>
  </tr>
  <tr>
    <td colspan="3" align="center" scope="row">
      <div align="left">#if ($mode == 'View')
        <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskini()" />
        <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()" />
        #else
        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan()" />
        <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal()"/>
      #end   </div></td>
  </tr>
  #end
</table>
<table width="100%" border="0" cellpadding="2">
  <tr>
    <td align="right"><strong>CL-05-24</strong></td>
  </tr>
</table>
</fieldset>
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
	if (document.f1.idMinitmesyuaratsubpara.value == "" || document.f1.idMinitmesyuaratsubpara.value == 0){
		
		document.f1.mode.value = "tambahBaru";
	}
	else{
		
		document.f1.mode.value = "kemaskiniSubMinit";
	}
	document.f1.submit();
	window.close();
}
function kemaskini(){
	document.f1.command1.value = "kemaskini";
	document.f1.submit();
}
 

</script>