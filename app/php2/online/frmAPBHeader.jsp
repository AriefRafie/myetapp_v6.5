<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr> #foreach($beanHeader in $BeanHeader)
    #set($idFail = $beanHeader.idFail)
    #set($subUrusan = $beanHeader.subUrusan)
    #set($idPermohonan = $beanHeader.idPermohonan)
    #set($noPermohonan = $beanHeader.noPermohonan)
    #set($tarikhTerima = $beanHeader.tarikhTerima)
    #set($idStatus = $beanHeader.idStatus) 
    #set($status = $beanHeader.status)    
    #set($noFail = $beanHeader.noFail)  
    #end
    <td width="50%" valign="top" ><fieldset>
      <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="36%" align="right">No Permohonan </td>
          <td width="1%">:</td>
          <td width="63%"><font color="blue">$noPermohonan</font></td>
        </tr>
        <tr>
          <td width="36%" align="right">No Fail </td>
          <td width="1%">:</td>
          <td width="63%"><font color="blue">$noFail</font></td>
        </tr>
        <tr>
          <td align="right">Tarikh Permohonan </td>
          <td>:</td>
          <td><font color="blue">$tarikhTerima</font></td>
        </tr>
        <tr>
          <td align="right">Status Permohonan </td>
          <td>:</td>
          <td><font color="green">$status</font></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
