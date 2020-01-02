<fieldset>
<legend><strong>ULASAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanJUPEM in $BeanJUPEM)
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">No Fail JUPEM</td>
    <td width="1%">:</td>
    <td width="70%">$beanJUPEM.noRujukan</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Tarikh Hantar</td>
    <td>:</td>
    <td>$beanJUPEM.tarikhHantar</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Tarikh Terima</td>
    <td>:</td>
    <td>$beanJUPEM.tarikhTerima</td>
  </tr>
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">Ulasan Terperinci</td>
    <td valign="top">:</td>
    <td valign="top">$beanJUPEM.ulasan</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Tarikh Surat Kementerian/Agensi</td>
    <td>:</td>
    <td>$beanJUPEM.tarikhSurat </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Bertindih</td>
    <td>:</td>
    <td> #if($beanJUPEM.flagTindih == '1') YA
      #elseif($beanJUPEM.flagTindih == '2') TIDAK    
      #end </td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</fieldset>
