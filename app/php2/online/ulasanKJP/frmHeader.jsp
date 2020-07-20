
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach($beanHeader in $BeanHeader)
  <tr>
    <td width="50%" valign="top"><fieldset>
        <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
        <table width="100%" border="0" cellspacing="2" cellpadding="2">
          <tr>
            <td width="36%" align="right">No Fail</td>
            <td width="1%">:</td>
            <td width="63%"><font color="blue">$beanHeader.noFail</font></td>
          </tr>
          <tr>
            <td width="36%" align="right">Suburusan</td>
            <td width="1%">:</td>
            <td width="63%"><font color="blue">$beanHeader.subUrusan</font></td>
          </tr>
          <tr>
            <td align="right">Tarikh Surat</td>
            <td>:</td>
            <td><font color="blue">$beanHeader.tarikhSurat</font></td>
          </tr>
          <tr>
            <td align="right">Tarikh Terima</td>
            <td>:</td>
            <td><font color="blue">$beanHeader.tarikhTerima</font></td>
          </tr>
          <tr>
            <td align="right">Status Permohonan</td>
            <td>:</td>
            <td><font color="green">$beanHeader.status</font></td>
          </tr>
          <tr>
            <td align="right" valign="top">Perkara</td>
            <td valign="top">:</td>
            <td rowspan="5" valign="top"><font color="blue">$beanHeader.tajukFail</font></td>
          </tr>
           
          <tr>
          <tr>
            <td align="right">&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td align="right">&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td align="right">&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td align="right">&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table>
      </fieldset></td>
    <td width="50%" valign="top"><fieldset>
        <legend><strong>MAKLUMAT PEMOHON</strong></legend>
        <table width="100%" border="0" cellspacing="2" cellpadding="2">
          <tr valign="top">
            <td width="37%" align="right" valign="top">Nama Pemohon :</td>
            <td width="63%"><font color="blue" valign="top">$beanHeader.namaPemohon</font></td>
          </tr>
          <tr>
            <td align="right" valign="top">Alamat :</td>
            <td><font color="blue" valign="top">$beanHeader.alamat1</font></td>
          </tr>
          <tr>
            <td align="right">&nbsp;</td>
            <td><font color="blue" valign="top">$beanHeader.alamat2</font></td>
          </tr>
          <tr>
            <td align="right">&nbsp;</td>
            <td><font color="blue" valign="top">$beanHeader.alamat3</font></td>
          </tr>
          <tr>
            <td align="right">Poskod :</td>
            <td><font color="blue">$beanHeader.poskod</font></td>
          </tr>
          <tr>
            <td align="right"> Bandar :</td>
            <td><font color="blue">$beanHeader.bandar</font></td>
          </tr>
          <tr>
            <td align="right"> Negeri :</td>
            <td><font color="blue">$beanHeader.negeri</font></td>
          </tr>
          <tr>
            <td align="right">No Telefon. :</td>
            <td><font color="blue">$beanHeader.noTel</font></td>
          </tr>
          <tr>
            <td align="right">No Fax :</td>
            <td><font color="blue">$beanHeader.noFax</font></td>
          </tr>
        </table>
      </fieldset></td>
  </tr>
  #end
</table>
