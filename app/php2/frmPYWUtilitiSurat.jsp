<style type="text/css">
<!--
.style1 {
	color: #0033FF
}
-->
</style>
<p>
  	<input name="idFail" type="hidden" id="idFail" value="$idFail"/>
	<input name="idUlasanTeknikal" type="hidden" id="idUlasanTeknikal" value="$idUlasanTeknikal"/>
	<input name="report" type="hidden" id="report" value="$report"/>
</p>
<table width="100%" cellspacing="2" cellpadding="2" border="0">
  <tr>
    <td><fieldset>
      <legend><strong>CETAKAN SURAT / LAPORAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr class="table_header">
          <td width="1%"><strong>Bil</strong></td>
          <td width="99%"><strong>Nama Surat</strong></td>
        </tr>
        <tr>
          <td>1</td>
          <td><a href="javascript:cetakPYWSuratTolakTanahBukanMilikPTP()" class="style1">Surat Tolak Tanah Bukan Milik PTP</a></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function cetakPYWSuratTolakTanahBukanMilikPTP() {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupCetakUtilitiView?&report=PYWSuratTolakTanahBukanMilikPTP";
    var hWnd = window.open(url,'printuser','width=700,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
