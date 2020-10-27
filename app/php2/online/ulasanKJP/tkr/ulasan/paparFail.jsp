<input type="hidden" name="idUlasanTeknikal" id="idUlasanTeknikal" value='$!idUlasanTeknikal'>
<input type="hidden" name="idFail" id="idFail" value='$!idFail'>
<input type="hidden" name="idPermohonan" id="idPermohonan" value='$!idPermohonan'>
<input type="hidden" name="idDokumen" id="idDokumen" value='$!idDokumen'>
<input type="hidden" name="flagDokumen" id="flagDokumen" value='$!flagDokumen'>
<div id="divUlasan2">
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
</div>