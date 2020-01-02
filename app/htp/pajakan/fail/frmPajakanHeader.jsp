<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr> 
    <td>
    <fieldset>
    <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
    
    <table width="100%" border="0" cellspacing="2" cellpadding="2">
    #foreach($beanHeader in $BeanHeader)
      <tr>
        <td width="50%" valign="top">
        
        <table width="100%" border="0" cellspacing="2" cellpadding="2">
          <tr>
            <td width="36%" align="right">NEGERI</td>
            <td width="1%">:</td>
            <td width="63%"><font color="blue">$beanHeader.negeri</font></td>
          </tr>
          <tr>
            <td align="right">KEMENTERIAN</td>
            <td>:</td>
            <td><font color="blue">$beanHeader.kementerian</font></td>
          </tr>
          <tr>
            <td align="right">AGENSI</td>
            <td>:</td>
             <td><font color="blue">$beanHeader.agensi</font></td>
          </tr>
          <tr>
            <td align="right">URUSAN</td>
            <td>:</td>
             <td><font color="blue">$beanHeader.urusan</font></td>
          </tr>
          <tr>
            <td align="right">SUB URUSAN</td>
            <td>:</td>
             <td><font color="blue">$beanHeader.namaSubUrusan</font></td>
          </tr>
          <tr>
            <td align="right">STATUS TANAH</td>
            <td>:</td>
             <td><font color="blue">$beanHeader.statusTanah</font></td>
          </tr>          
          <tr>
            <td align="right" valign="top">TAJUK</td>
            <td valign="top">:</td>
            <td valign="top"><font color="blue">$beanHeader.tajuk</font></td>
          </tr>
        </table></td>
        <td width="50%" valign="top">
        
        <table width="100%" border="0" cellspacing="2" cellpadding="2">
          <tr>
            <td width="37%"  align="right">NO. FAIL SEKSYEN :</td>
            <td width="63%"><font color="blue">$beanHeader.noFail</font></td>
          </tr>
          <tr>
            <td align="right">NO. FAIL KJP :</td>
            <td><font color="blue">$beanHeader.noFailKJP</font></td>
          </tr>
          <tr>
            <td align="right">TARIKH SURAT KJP :</td>
            <td><font color="blue">$beanHeader.tarikhSuratKJP</font></td>
          </tr>
          <tr>
            <td align="right">NO FAIL LAIN :</td>
            <td><font color="blue">$beanHeader.noFailLain</font></td>
          </tr>
          <tr style="display:none">
            <td align="right">TARIKH AGIHAN :</td>
            <td><font color="blue">$beanHeader.tarikhAgihan</font></td>
          </tr>
          <tr>
            <td align="right">JENIS FAIL :</td>
            <td><font color="blue">$beanHeader.jenisFail</font></td>
          </tr>
          <tr>
            <td align="right">STATUS :</td>
            <td><font color="blue">$beanHeader.status</font></td>
          </tr>
          <tr>
            <td colspan="2" align="right" valign="bottom">
	            <table>
			        <tr>
			            <td width="25%" valign="top"></td>
			            <td width="1%" valign="top"></td>
			            <td width="34%" valign="top" align="right"><input type="button" id="" name="" value="Capaian Sistem Imbasan Dokumen" onclick="javascript:arkibWindow('$no_fail_atheader')"/></td>
			         </tr> 
	         	</table>
            </td>
          </tr>
          #end
        </table>
       
        </td>
      </tr>
    </table>
 	
    </fieldset>
    </td>
  </tr>
</table>

<!--<script>
function arkibWindow(noFail){
	
		var url = "../x/${securityToken}/ekptg.view.integrasi.sid.FrmPopupPaparArkibDokumen?kodModul=HTP&noFail="+noFail;
		
		var hWnd = window.open(url,"printuser","scrollbars=1,width=800,height=700");
		 if ((document.window != null) && (!hWnd.opener))
		       hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();
			hWnd.focus();
			
	}
</script>-->
