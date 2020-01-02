<fieldset>
<legend>CARIAN</legend>
<table width="100%" border="0">

  <tr>
    <td width="29%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="70%">
     	<input class="stylobutton100" type="button" name="cmdCari" id="cmdCari" value="Pindah" onClick="carian()"/>
   	<input class="stylobutton100" type="button" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onClick="kosongkan()"/>
    </td>
  </tr>
</table>
</fieldset>


<script>

function carian(){
	doAjaxCall${formName}("pindah");
}


</script>