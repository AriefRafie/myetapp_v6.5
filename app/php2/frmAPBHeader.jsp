<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td width="50%" valign="top"><fieldset>
      <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach($beanHeader in $BeanHeader)
        <tr>
          <td width="36%" align="right">NO FAIL</td>
          <td width="1%">:</td>
          <td width="63%"><font color="blue">$beanHeader.noFail</font></td>
        </tr>
<!--        <tr>
          <td width="36%" align="right">NO RAYUAN</td>
          <td width="1%">:</td>
          <td width="63%"><font color="blue">$beanHeader.noRayuan</font></td>
        </tr>-->
        <tr>
          <td width="36%" align="right">URUSAN</td>
          <td width="1%">:</td>
          <td width="63%"><font color="blue">$beanHeader.urusan</font></td>
        </tr>
        <tr>
          <td align="right">TARIKH TERIMA</td>
          <td>:</td>
          <td><font color="blue">$beanHeader.tarikhTerima</font></td>
        </tr>
        <tr>
          <td align="right">TARIKH SURAT</td>
          <td>:</td>
          <td><font color="blue">$beanHeader.tarikhSurat</font></td>
        </tr>
        <tr>
          <td align="right" valign="top">PERKARA</td>
          <td valign="top">:</td>
          <td rowspan="3" valign="top"><font color="blue">$beanHeader.perkara</font></td>
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
          <td align="right" valign="top">STATUS FAIL</td>
          <td valign="top">:</td>
          <td><font color="red">$beanHeader.status</font></td>
        </tr>
       <!-- <tr>
          <td align="right" valign="top"></td>
          <td valign="top"></td>
          <td><font color="red"><input type="button" id="" name="" value="Capaian Arkib Dokumen" onclick="javascript:arkibWindow('$beanHeader.noFail')"/></font></td>
        </tr>-->
        #end
      </table>
      </fieldset></td>
    <td width="50%" valign="top"><fieldset>
      <legend><strong>MAKLUMAT PEMOHON</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach($beanHeader in $BeanHeader)
        <tr valign="top">
          <td width="37%" align="right" valign="top">NAMA PEMOHON :</td>
          <td width="63%"><font color="blue" valign="top">$beanHeader.namaPemohon</font></td>
        </tr>
        <tr>
          <td align="right" valign="top">ALAMAT :</td>
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
          <td align="right">POSKOD :</td>
          <td><font color="blue">$beanHeader.poskod</font></td>
        </tr>
        <tr>
          <td align="right"> BANDAR :</td>
          <td><font color="blue">$beanHeader.bandar</font></td>
        </tr>
        <tr>
          <td align="right"> NEGERI :</td>
          <td><font color="blue">$beanHeader.negeri</font></td>
        </tr>
        <tr>
          <td align="right">NO. TEL. :</td>
          <td><font color="blue">$beanHeader.noTel</font></td>
        </tr>
        <tr>
          <td align="right">NO. FAX :</td>
          <td><font color="blue">$beanHeader.noFax</font></td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>

<!--<script>
function arkibWindow(noFail){
		var url = "../x/${securityToken}/ekptg.view.integrasi.sid.FrmPopupPaparArkibDokumen?kodModul=PHP&noFail="+noFail;
		
		var hWnd = window.open(url,"printuser","scrollbars=1,width=800,height=700");
		 if ((document.window != null) && (!hWnd.opener))
		       hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();
			hWnd.focus();
			
	}
</script>-->
