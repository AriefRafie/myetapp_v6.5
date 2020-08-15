<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>#foreach($beanHeader in $BeanHeader)
  	#set($idFail = $beanHeader.idFail)
    #set($idNegeriTanah = $beanHeader.idNegeriTanah)
    #set($idKementerian = $beanHeader.idKementerian)
    #set($idPermohonan = $beanHeader.idPermohonan)
    #set($noPermohonan = $beanHeader.noPermohonan)
    #set($status = $beanHeader.status)    
    #set($idHakmilik = $beanHeader.idHakmilik)
    #set($noFail = $beanHeader.noFail)
    #set($statusTanah = $beanHeader.statusTanah)
    #set($tajuk = $beanHeader.tajuk)    
    #end
    <td>
    <fieldset>
    <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>   
    <table width="100%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td width="50%" valign="top">
        
        <table width="100%" border="0" cellspacing="2" cellpadding="2">
          <tr>
            <td width="36%" align="right">NEGERI</td>
            <td width="1%">:</td>
            <td width="63%"><font color="blue">$idNegeriTanah</font></td>
          </tr>
          <tr>
            <td align="right">KEMENTERIAN</td>
            <td>:</td>
            <td><font color="blue">$idKementerian</font></td>
          </tr>
          <tr>
            <td align="right">AGENSI</td>
            <td>:</td>
             <td><font color="blue">$agensi</font></td>
          </tr>
          <tr>
            <td align="right">STATUS TANAH</td>
            <td>:</td>
             <td><font color="blue">$statusTanah</font></td>
          </tr>          
          <tr>
            <td align="right" valign="top">TUJUAN PAJAKAN</td>
            <td valign="top">:</td>
            <td valign="top"><font color="blue">$tajuk</font></td>
          </tr>
        </table></td>
        <td width="50%" valign="top">
        
        <table width="100%" border="0" cellspacing="2" cellpadding="2">
         <tr>
            <td width="37%"  align="right">NO. FAIL ONLINE :</td>
            <td width="63%"><font color="blue">$noFail</font></td>
          </tr>
         <!-- <tr>
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
          </tr>--->
          <tr>
            <td align="right">TARIKH SURAT PEMOHON :</td>
            <td><font color="blue">$tarikhSuratPemohon</font></td>
          </tr>
          <tr>
            <td align="right">JENIS FAIL :</td>
            <td><font color="blue">$idJenisFail</font></td>
          </tr>
          <tr>
            <td align="right">STATUS :</td>
            <td><font color="blue">$status</font></td>
          </tr>
          
        </table>
        </td>
      </tr>
    </table>
    </fieldset>
    </td>
  </tr>
</table>