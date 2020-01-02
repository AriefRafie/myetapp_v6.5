<style type="text/css">
<!--
.style1 {
	color: #0033FF
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
    <input name="flagDetail" type="hidden" id="flagDetail" value="$flagDetail"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>CARIAN</b></legend>
      <table width="100%" align="center" border="0">
        <tr>
          <td width="30%" height="24" scope="row" align="right">No Aduan : </td>
          <td width="70%"><input type="text" name="noAduan" /></td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Tarikh Aduan : </td>
          <td width="70%"><input type="text" name="tarikhAduan" />dd/mm/yyyy</td>
        </tr>
		 <tr>
          <td width="30%" height="24" scope="row" align="right">Status Aduan : </td>
          <td width="70%">
          <select name="tindakan">
          	#foreach($status in $statuses)
							
				<option value="$status">$status</option>
			#end
          </select>
          </td>
		 </tr>
          
        <tr>
          <td scope="row"></td>
          <td><input type="button" value="Cari" onclick="cari()" /></td>
        </tr>
        <tr>
          <td scope="row">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
 </table>
<script>
	function cari(){
		doAjaxCall${formName}("cari");
	}
		

</script>