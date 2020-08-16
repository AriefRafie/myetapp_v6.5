<input type="text" name="idUlasanTeknikal" id="idUlasanTeknikal" value='$!idUlasanTeknikal'>
<input type="text" name="idFail" id="idFail" value='$!idFail'>
<input type="text" name="idPermohonan" id="idPermohonan" value='$!idPermohonan'>
<input type="text" name="idDokumen" id="idDokumen" value='$!idDokumen'>
<input type="text" name="flagDokumen" id="flagDokumen" value='$!flagDokumen'>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2">#parse("app/php2/online/ulasanKJP/frmHeader.jsp")</td>
  </tr>
  <tr>
    <td colspan="2">
    #if ($flagBorangK == 'Y')
      #parse("app/php2/online/ulasanKJP/frmMaklumatBorangK.jsp")
      #else
      <fieldset><legend>MAKLUMAT TANAH</legend>
      #parse("app/php2/online/ulasanKJP/frmMaklumatTanah.jsp")
      </fieldset>
      #end </td>
  </tr>
  <tr>
    <td colspan="2"><div id="divUlasan">#parse("$templateDir/maklumatUlasan.jsp")</div></td>
  </tr>
</table>