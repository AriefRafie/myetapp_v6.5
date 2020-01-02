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
            <input type="hidden" name="tahun" />
            <input type="hidden" name="actionCukai" />
          </td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Tahun : </td>
          <td width="70%"><input name="txtTahun" id="txtTahun" type="text" value="$txtTahun" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
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
      <legend><b>SENARAI CUKAI</b></legend>
      #parse("app/utils/record_paging.jsp")
      <table align="center" width="100%">
        <tr>
          <td colspan="11" scope="row">
       	  <input name="cmdDaftar" type="button" value="Intergrasi Sistem eTanah" onclick="javascript:popupIntergrasi()"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="10%"><strong>ID Hakmilik</strong></td>
          <td width="5%"><strong>Tahun</strong></td>          
          <td width="10%"><strong>Cukai Parit</strong></td>
          <td width="10%"><strong>Cukai Tali Air</strong></td>
          <td width="10%"><strong>Denda</strong></td>
          <td width="10%"><strong>Pengecualian</strong></td>
          <td width="10%"><strong>Pengurangan</strong></td>
          <td width="10%"><strong>Tunggakan</strong></td>
          <td width="10%"><strong>Cukai Kena Bayar</strong></td>
          <td width="10%"><strong>Cukai Perlu Bayar</strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiMaklumatCukai.size() > 0)
        #foreach ($list in $SenaraiMaklumatCukai)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row"><a href="javascript:papar('$list.idHakmilik', '$list.tahun')" class="style1">$list.idHakmilik</a></td>
          <td class="$row">$list.tahun</td>
          <td class="$row">$list.cukaiParit</td>
          <td class="$row">$list.cukaiTaliAir</td>
          <td class="$row">$list.denda</td>
          <td class="$row">$list.pengecualian</td>
          <td class="$row">$list.pengurangan</td>
          <td class="$row">$list.tunggakan</td>
          <td class="$row">$list.cukaiKenaBayar</td>
          <td class="$row">$list.cukaiPerluBayar</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function carian(){
	document.${formName}.actionCukai.value = "";
	doAjaxCall${formName}("");
}
function kosongkan() {
	document.${formName}.reset();
	document.${formName}.txtIDHakmilik.value = "";
	document.${formName}.txtTahun.value = "";
	doAjaxCall${formName}("");
}
function papar(idHakmilik,tahun) {

	document.${formName}.idHakmilik.value = idHakmilik;
	document.${formName}.tahun.value = tahun;
	document.${formName}.actionCukai.value = "papar";
	document.${formName}.submit();
}

function popupIntergrasi() {

	var url = "../x/${securityToken}/ekptg.view.htp.FrmPopupIntergrasiCukaiEtanahView";
    var hWnd = window.open(url,'printuser','width=1000,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();

}
</script>
