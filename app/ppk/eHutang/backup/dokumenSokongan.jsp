<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><strong>DOKUMEN SOKONGAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
      	#if ($role == "(INTEGRASI)UsersAgensi")
        <tr>
          <td>#parse("$templateDir/muatNaikDokumenSokongan.jsp")</td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>#parse("$templateDir/senaraiDokumenSokongan.jsp")</td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
