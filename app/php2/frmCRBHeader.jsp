<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr> #foreach($beanHeader in $BeanHeader)
    #set($idFail = $beanHeader.idFail)
    #set($noFail = $beanHeader.noFail)
    #set($noFailNegeri = $beanHeader.noFailNegeri)
    #set($urusan = $beanHeader.urusan)
    #set($subUrusan = $beanHeader.subUrusan)
    #set($idPermohonan = $beanHeader.idPermohonan)
    #set($tarikhTerima = $beanHeader.tarikhTerima)
    #set($tarikhSurat = $beanHeader.tarikhSurat)
    #set($tajukFail = $beanHeader.tajukFail)
    #set($idPemohon = $beanHeader.idPemohon)
    #set($namaPemohon = $beanHeader.namaPemohon)
    #set($alamat1 = $beanHeader.alamat1)             
    #set($alamat2 = $beanHeader.alamat2)
    #set($alamat3 = $beanHeader.alamat3)
    #set($poskod = $beanHeader.poskod)   
    #set($bandar = $beanHeader.bandar)         
    #set($negeri = $beanHeader.negeri)
    #set($noTel = $beanHeader.noTel)
    #set($noFax = $beanHeader.noFax)
    #set($idStatus = $beanHeader.idStatus) 
    #set($status = $beanHeader.status)    
    #set($idHakmilik = $beanHeader.idHakmilik)    
    #end
    <td width="50%" valign="top"><fieldset>
      <legend><strong>MAKLUMAT FAIL</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="36%" align="right">No Fail</td>
          <td width="1%">:</td>
          <td width="63%">
          	<font color="blue">$noFail</font>
          	&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="button" name="cmdLogTugasan" id="cmdLogTugasan" value="LOG TUGASAN FAIL" onclick="paparLogTugasan('$idFail')"/>
          </td>
        </tr>
        <tr>
          <td width="36%" align="right">No Fail Negeri</td>
          <td width="1%">:</td>
          <td width="63%"><font color="blue">$noFailNegeri</font></td>
        </tr>
        <tr>
          <td width="36%" align="right">Urusan</td>
          <td width="1%">:</td>
          <td width="63%"><font color="blue">$urusan</font></td>
        </tr>
        <tr>
          <td align="right">Tarikh Terima Aduan</td>
          <td>:</td>
          <td><font color="blue">$tarikhTerima</font></td>
        </tr>
        <tr>
          <td align="right">Tarikh Surat Aduan</td>
          <td>:</td>
          <td><font color="blue">$tarikhSurat</font></td>
        </tr>
        <tr>
          <td align="right" valign="top">Perkara</td>
          <td valign="top">:</td>
          <td rowspan="3" valign="top"><font color="blue">$tajukFail</font></td>
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
          <td align="right">Status Fail</td>
          <td>:</td>
          <td><font color="red">$status</font></td>
        </tr>
       <!-- <tr>
          <td align="right" valign="top"></td>
          <td valign="top"></td>
          <td><font color="red"><input type="button" id="" name="" value="Capaian Sistem Imbasan Dokumen" onclick="javascript:arkibWindow('$noFail')"/></font></td>
        </tr>-->
      </table>
      </fieldset></td>
    <td width="50%" valign="top"><fieldset>
      <legend><strong>MAKLUMAT PENGADU</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr valign="top">
          <td width="37%" align="right" valign="top">Nama Pengadu :</td>
          <td width="63%"><font color="blue" valign="top">$namaPemohon</font></td>
        </tr>
        <tr>
          <td align="right" valign="top">Alamat :</td>
          <td><font color="blue" valign="top">$alamat1</font></td>
        </tr>
        <tr>
          <td align="right">&nbsp;</td>
          <td><font color="blue" valign="top">$alamat2</font></td>
        </tr>
        <tr>
          <td align="right">&nbsp;</td>
          <td><font color="blue" valign="top">$alamat3</font></td>
        </tr>
        <tr>
          <td align="right">Poskod :</td>
          <td><font color="blue">$poskod</font></td>
        </tr>
        <tr>
          <td align="right"> Bandar :</td>
          <td><font color="blue">$bandar</font></td>
        </tr>
        <tr>
          <td align="right"> Negeri :</td>
          <td><font color="blue">$negeri</font></td>
        </tr>
        <tr>
          <td align="right">No Telefon. :</td>
          <td><font color="blue">$noTel</font></td>
        </tr>
        <tr>
          <td align="right">No Fax :</td>
          <td><font color="blue">$noFax</font></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>

<script>
function paparLogTugasan(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPYWPopupLogTugasanView?idFail="+idFail;
    var hWnd = window.open(url,'printuser','width=1000,height=400, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
}

/* function arkibWindow(noFail){
	var url = "../x/${securityToken}/ekptg.view.integrasi.sid.FrmPopupPaparArkibDokumen?kodModul=PHP&noFail="+noFail;
	var hWnd = window.open(url,"printuser","scrollbars=1,width=800,height=700");
	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
} */
</script>
