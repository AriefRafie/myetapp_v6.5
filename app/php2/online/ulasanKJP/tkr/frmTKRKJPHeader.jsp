<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr> #foreach($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
    #set($idFail = $beanMaklumatPermohonan.idFail)
    #set($subUrusan = $beanMaklumatPermohonan.subUrusan)
    #set($idPermohonan = $beanMaklumatPermohonan.idPermohonan)
    #set($noRujukanOnline = $beanMaklumatPermohonan.noRujukanOnline)
    #set($noFail = $beanMaklumatPermohonan.noFail)
    #set($tarikhTerima = $beanMaklumatPermohonan.tarikhTerima)
    #set($idStatus = $beanMaklumatPermohonan.idStatus)
    #set($status = $beanMaklumatPermohonan.status)
    #set($idHakmilik = $beanMaklumatPermohonan.idHakmilik)
    #set($noFail = $beanMaklumatPermohonan.noFail)
    #set($perkara = $beanMaklumatPermohonan.perkara)
    #end

    <td width="50%" valign="top" ><fieldset>
      <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="36%" align="right">No Rujukan <em>Online</em> </td>
          <td width="1%">:</td>
          <td width="63%"><font color="blue">$noRujukanOnline</font></td>
        </tr>
        <tr>
          <td width="36%" align="right">No Fail </td>
          <td width="1%">:</td>
          <td width="63%"><font color="blue">$!noFail</font></td>
        </tr>
        <tr>
          <td width="36%" align="right">Urusan </td>
          <td width="1%">:</td>
          <td width="63%"><font color="blue">TUKARGUNA</font></td>
        </tr>
        <tr>
          <td width="36%" align="right">Tarikh Permohonan </td>
          <td width="1%">:</td>
          <td><font color="blue">$tarikhTerima</font></td>
        </tr>
        <tr>
          <td width="36%" align="right">Status Permohonan </td>
          <td width="1%">:</td>
          <td><font color="green">$status</font></td>
        </tr>
      </table>
      </fieldset></td>
	  <td><fieldset>
      <legend><strong>MAKLUMAT PEMOHON</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Kategori Pemohon</td>
          <td>:</td>
          <td width="70%">$idKategoriPemohon</td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Kementerian</td>
          <td>:</td>
          <td>$selectKementerian</td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Agensi</td>
          <td>:</td>
          <td>$selectAgensi</td>
        </tr>
        #foreach ($beanMaklumatAgensi in $BeanMaklumatAgensi)
        <tr>
          <td>&nbsp;</td>
          <td>Nama</td>
          <td>:</td>
          <td>$beanMaklumatAgensi.namaAgensi

        </tr>

        <tr>
          <td>&nbsp;</td>
          <td>Alamat</td>
          <td>:</td>
          <td>$beanMaklumatAgensi.alamat1</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>:</td>
          <td>$beanMaklumatAgensi.alamat2</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>:</td>
          <td>$beanMaklumatAgensi.alamat3</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Poskod</td>
          <td>:</td>
          <td>$beanMaklumatAgensi.poskod</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Negeri</td>
          <td>:</td>
          <td>$beanMaklumatAgensi.negeri</td>
        </tr>
        #end
      </table>
      </fieldset>
      </td>
      </tr>
      </table>
