<style type="text/css">
<!--
.style1 {
	color: #0033FF
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="actionLesen" type="hidden" id="actionLesen" value="$actionLesen"/>
  <input name="idJadualKedua" type="hidden" id="idJadualKedua" value="$idJadualKedua"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>CARIAN</b></legend>
      <table width="100%" align="center" border="0">
        <tr>
          <td width="30%" height="24" scope="row" align="right">Nama Pemegang Lesen : </td>
          <td width="70%"><input name="txtNamaPelesen" id="txtNamaPelesen" type="text" value="$txtNamaPelesen" size="50" maxlength="50" style="text-transform:uppercase;" >
          </td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">No. Lesen : </td>
          <td width="70%"><input name="txtNoLesen" id="txtNoLesen" type="text" value="$txtNoLesen" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
        </tr>
        <tr>
          <td scope="row"></td>
          <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:carian()">
            <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="javascript:kosongkan()"></td>
        </tr>
        <tr>
          <td scope="row">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><b>SENARAI PEMEGANG LESEN</b></legend>
      #parse("app/utils/record_paging.jsp")
      <table align="center" width="100%">
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="30%"><strong>Nama Pemegang Lesen</strong></td>
          <td width="40%"><strong>No. Lesen</strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiFail.size() > 0)
        #foreach ($list in $SenaraiFail)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row"><a href="javascript:papar('$list.idJadualKedua')" class="style1">$list.namaPelesen</a></td>
          <td class="$row">$list.noLesen</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function carian(){
	document.${formName}.actionLesen.value = "";
	document.${formName}.submit();
}
function kosongkan() {
	document.${formName}.reset();
	document.${formName}.txtNamaPelesen.value = "";
	document.${formName}.txtNoLesen.value = "";
	document.${formName}.submit();
}
function papar(idJadualKedua) {
	document.${formName}.idJadualKedua.value = idJadualKedua;
	document.${formName}.actionLesen.value = "papar";
	document.${formName}.submit();
}

</script>
