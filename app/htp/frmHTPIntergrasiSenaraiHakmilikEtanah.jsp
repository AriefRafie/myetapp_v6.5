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
          <td width="30%" height="24" scope="row" align="right">ID Hakmilik : </td>
          <td width="70%"><input name="txtIDHakmilik" id="txtIDHakmilik" type="text" value="$txtIDHakmilik" size="50" maxlength="50" style="text-transform:uppercase;" >
            <input type="hidden" name="idHakmilik" />
            <input type="hidden" name="actionHakmilik" />
          </td>
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
      <legend><b>SENARAI HAKMILIK</b></legend>
      #parse("app/utils/record_paging.jsp")
      <table align="center" width="100%">
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="15%"><strong>ID Hakmilik</strong></td>
          <td width="35%"><strong>No Hakmilik</strong></td>
          <td width="10%"><strong>Negeri</strong></td>
          <td width="8%" align="center"><strong>Tarikh Terima</strong></td>
          <td width="10%"><strong>Status Hakmilik</strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiHakmilik.size() > 0)
        #foreach ($list in $SenaraiHakmilik)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row"><a href="javascript:papar('$list.idHakmilik')" class="style1">$list.idHakmilik</a></td>
          <td class="$row">$list.idJenisHakmilik$list.noHakmilik</td>
          <td class="$row">$list.idNegeri</td>
          <td class="$row" align="center">$list.tarikhTerima</td>
          #if ($list.flagAktif == 'Y')
          	<td class="$row"><span style="color:#0000FF"><strong>BARU DITERIMA</strong></span></td>
          #elseif ($list.flagAktif == 'T')
          	<td class="$row"><span style="color:#FF0000"><strong>TELAH DIDAFTAR</strong></span></td>
          #else
       	  <td class="$row">&nbsp;</td>
          #end 
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function carian(){
	document.${formName}.actionHakmilik.value = "";
	doAjaxCall${formName}("");
}
function kosongkan() {
	document.${formName}.reset();
	document.${formName}.txtIDHakmilik.value = "";
	doAjaxCall${formName}("");
}
function papar(idHakmilik) {

	document.${formName}.idHakmilik.value = idHakmilik;
	document.${formName}.actionHakmilik.value = "papar";
	document.${formName}.submit();
}
</script>
