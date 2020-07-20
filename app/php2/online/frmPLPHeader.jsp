<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr> #foreach($beanHeader in $BeanHeader)
    #set($idFail = $beanHeader.idFail)
    #set($subUrusan = $beanHeader.subUrusan)
    #set($idPermohonan = $beanHeader.idPermohonan)
    #set($noPermohonan = $beanHeader.noPermohonan)
    #set($tarikhTerima = $beanHeader.tarikhTerima)
    #set($idStatus = $beanHeader.idStatus) 
    #set($keterangan = $beanHeader.keterangan)    
    #set($idHakmilik = $beanHeader.idHakmilik)
    #set($noFail = $beanHeader.noFail) 
    #set($flagLayerKJP = $beanHeader.flagLayerKJP)           
    #end
    
    <td width="50%" valign="top" ><fieldset>
      <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="36%" align="right">No Rujukan <em>Online</em></td>
          <td width="1%">:</td>
          <td width="63%"><font color="blue">$noPermohonan</font></td>
        </tr>
        <tr>
          <td width="36%" align="right">No Fail </td>
          <td width="1%">:</td>
          <td width="63%"><font color="blue">$!noFail</font></td>
        </tr>
        <tr>
          <td width="36%" align="right">Suburusan </td>
          <td width="1%">:</td>
          <td width="63%"><font color="blue">$subUrusan</font></td>
        </tr>
        <tr>
          <td align="right">Tarikh Permohonan </td>
          <td>:</td>
          <td><font color="blue">$tarikhTerima</font></td>
        </tr>
        <tr>
          <td align="right">Status Permohonan </td>
          <td>:</td>
          <td><font color="green">$keterangan</font></td>
        </tr>
        <tr>
          <td align="right">Perkara </td>
          <td>:</td>
          <td><font color="blue">$perkara</font></td>
        </tr>
       </table>
      </fieldset></td>
      	  <td><fieldset>
      <legend><strong>MAKLUMAT PEMOHON</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%"><span class="style1">*</span></td>
          <td width="28%">Kategori Pemohon</td>
          <td>:</td>
          <td width="70%">$idKategoriPemohon</td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Kementerian</td>
          <td>:</td>
          <td>$namaKementerian</td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Agensi</td>
          <td>:</td>
          <td>$namaAgensi</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Nama</td>
          <td>:</td>
          <td>$namaAgensi
            
        </tr>
        
        <tr>
          <td>&nbsp;</td>
          <td>Alamat</td>
          <td>:</td>
          <td>$alamat1</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>:</td>
          <td>$alamat2</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>:</td>
          <td>$alamat3</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Poskod</td>
          <td>:</td>
          <td>$poskod</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Negeri</td>
          <td>:</td>
          <td>$negeri</td>
        </tr>
        
      </table>
      </fieldset>
      </td>
  </tr>
</table>
