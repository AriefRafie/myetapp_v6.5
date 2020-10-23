<style type="text/css">
<!--
.style1 {
	color: #0033FF
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input type="hidden" name="step">
  <input type="hidden" name="idLaporan">
  <input type="hidden" name="idFail">
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>CARIAN</b></legend>
      <table width="100%" align="center" border="0">
        <tr>
          <td width="30%" height="24" scope="row" align="right">Disediakan Oleh : </td>
          <td width="70%"><input name="txtPelaporC" id="txtPelaporC" type="text" value="$txtPelaporC" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">No. Fail : </td>
          <td width="70%"><input type="text" name="txtNoFail" id="txtNoFail" value="$txtNoFail" maxlength="50" size="30"/>
        </tr>
        <tr>
          <td scope="row" align="right"> Jenis Hakmilik :</td>
          <td>$selectJenisHakmilik</td>
        </tr>
        <tr>
          <td scope="row" align="right"> No. Hakmilik :</td>
          <td><input name="txtNoHakmilikC" id="txtNoHakmilikC" type="text" value="$txtNoHakmilikC" size="30" maxlength="50" /></td>
        </tr>
        <tr>
          <td scope="row" align="right"> No. Warta :</td>
          <td><input name="txtNoWartaC" id="txtNoWartaC" type="text" value="$txtNoWartaC" size="30" maxlength="50"/></td>
        </tr>
        <tr>
          <td scope="row" align="right">Jenis Lot :</td>
          <td>$selectLot</td>
        </tr>
        <tr>
          <td scope="row" align="right">No. Lot :</td>
          <td><input name="txtNoLotC" id="txtNoLotC" type="text" value="$txtNoLotC" size="30" maxlength="50"/></td>
        </tr>
        <tr>
          <td scope="row" align="right">Negeri :</td>
          <td>$selectNegeri</td>
        </tr>
        <tr>
          <td scope="row" align="right">Daerah :</td>
          <td>$selectDaerah</td>
        </tr>
        <tr>
          <td scope="row" align="right">Mukim :</td>
          <td>$selectMukim</td>
        </tr>
        <tr>
          <td scope="row"></td>
          <td><input name="cmdCari" id="cmdCaricmdKosongkan" value="Cari" type="button" onclick="javascript:carian()">
            <input name="cmdKosongkan" id="" value="Kosongkan" type="reset" onClick="javascript:kosongkan('$flagDetail')"></td>
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
      <legend><b>SENARAI LAPORAN</b></legend>
      #parse("app/utils/record_paging.jsp")
      <table align="center" width="100%">
        <tr class="table_header" align="center">
          <td scope="row" width="5%"><strong>Bil</strong></td>
          <td width="30%" ><strong>No. Fail</strong></td>
          <td width="15%"><strong>Urusan</strong></td>
          <td width="35%"><strong>Hakmilik</strong></td>
          <td width="15%">Disediakan Oleh</td>
        </tr>
        #set ($list = "")
        #if ($SenaraiLaporan.size() > 0)
        #foreach ($list in $SenaraiLaporan)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row"><a href="javascript:kemaskini('$list.idLaporan')" class="style1">$list.noFail</a></td>
          <td class="$row" align="center">$list.urusan</td>
          <td class="$row">$list.hakmilik</td>
          <td class="$row">$list.pelapor</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doChangeDaerah() {
	doAjaxCall${formName}("doChangeDaerah");
}
function carian(){
	doAjaxCall${formName}("doCarian");
}
function kosongkan(flagDetail) {
	document.${formName}.reset();
	document.${formName}.txdTarikhLawatanC.value = "";
	document.${formName}.txtPelaporC.value = "";
	document.${formName}.txtNoPeganganC.value = "";
	document.${formName}.socJenisHakmilikC.value = "";
	document.${formName}.txtNoHakmilikC.value = "";	
	document.${formName}.txtNoWartaC.value = "";
	document.${formName}.socLotC.value = "";
	document.${formName}.txtNoLotC.value = "";		
	document.${formName}.socNegeriC.value = "";
	document.${formName}.socDaerahC.value = "";
	document.${formName}.socMukimC.value = "";	
	doAjaxCall${formName}("");
}
function kemaskini(idLaporan) {
	document.${formName}.idLaporan.value = idLaporan;
	document.${formName}.step.value = "kemaskini";
	document.${formName}.submit();
}
function daftar(){
	document.${formName}.step.value = "daftar";
	document.${formName}.submit();
}
function semakanMaklumatTanah() {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPopupSemakanMaklumatTanahView";
    var hWnd = window.open(url,'printuser','width=1000,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function semakanMaklumatBorangK() {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPopupSemakanMaklumatBorangKView";
    var hWnd = window.open(url,'printuser','width=1000,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
