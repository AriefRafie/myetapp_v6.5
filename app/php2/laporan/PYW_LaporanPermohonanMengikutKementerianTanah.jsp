<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend>LAPORAN</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="30%">&nbsp;</td>
          <td width="70%"><input type="button" name="cmdJana" id="button" value="Jana Laporan" onclick="cetakPYWIktKementerianTanah()"></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
<script>

function cetakPYWIktKementerianTanah() {
	var url = "../servlet/ekptg.report.php2.PYWLaporanPermohonanMengikutKementerianTanah";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
