<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr> #foreach($beanHeader in $BeanHeader)
    #set($noFail = $beanHeader.noFail)
    #set($urusan = $beanHeader.urusan)
    #set($subUrusan = $beanHeader.subUrusan)
    #set($tarikhTerima = $beanHeader.tarikhTerima)
    #set($tarikhSurat = $beanHeader.tarikhSurat)
    #set($tajukFail = $beanHeader.tajukFail) 
    #set($status = $beanHeader.status)     
    #set($permohonanDari = $beanHeader.permohonanDari)   
    #set($prosesFail = $beanHeader.prosesFail) 
    #set($noSambungan = $beanHeader.noSambungan)    
    #end
    
    #foreach($beanHeaderPerjanjianTambahan in $BeanHeaderPerjanjianTambahan)
        #set($tarikhTerimaPT = $beanHeaderPerjanjianTambahan.tarikhTerima)
        #set($tarikhSuratPT = $beanHeaderPerjanjianTambahan.tarikhSurat)
        #set($noRujSuratPT = $beanHeaderPerjanjianTambahan.noRujSurat)
        #set($jenisPerjanjianPT = "") 
        #if ($flagJenisPerjanjian == '1')
        	#set($jenisPerjanjianPT = "PERUBAHAN TUJUAN PENYEWAAN")
        #elseif ($flagJenisPerjanjian == '2')
        	#set($jenisPerjanjianPT = "PERUBAHAN TARIKH MULA PERJANJIAN")
        #elseif ($flagJenisPerjanjian == '3')
        	#set($jenisPerjanjianPT = "PERUBAHAN LUAS PENYEWAAN")
        #elseif ($flagJenisPerjanjian == '4')
        	#set($jenisPerjanjianPT = "RAYUAN KADAR SEWA")
        #end
        #set($statusPT = $beanHeaderPerjanjianTambahan.status)     
        #set($catatanPT = $beanHeaderPerjanjianTambahan.catatan)   
    #end
    
    <td width="50%" valign="top"><fieldset>
      <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #if ($noSambungan != '' && $noSambungan != '0')
        <tr>
          <td width="36%" align="right">No Fail</td>
          <td width="1%">:</td>
          <td width="63%"><font color="blue">$noFail</font>&nbsp;(SAMBUNGAN)</td>
        </tr>
        #else
        <tr>
          <td width="36%" align="right">No Fail</td>
          <td width="1%">:</td>
          <td width="63%"><font color="blue">$noFail</font></td>
        </tr>
        #end
        <tr>
          <td width="36%" align="right">Lokasi Tanah</td>
          <td width="1%">:</td>
          <td width="63%"><font color="blue">$permohonanDari</font></td>
        </tr>
        <tr>
          <td width="36%" align="right">Flag Proses Fail</td>
          <td width="1%">:</td>
          <td width="63%"><font color="blue">$prosesFail</font></td>
        </tr>
        <tr>
          <td width="36%" align="right">Urusan</td>
          <td width="1%">:</td>
          <td width="63%"><font color="blue">$urusan</font></td>
        </tr>
        <tr>
          <td width="36%" align="right">Suburusan</td>
          <td width="1%">:</td>
          <td width="63%"><font color="blue">$subUrusan</font></td>
        </tr>
        <tr>
          <td align="right">Tarikh Terima</td>
          <td>:</td>
          <td><font color="blue">$tarikhTerima</font></td>
        </tr>
        <tr>
          <td align="right">Tarikh Surat</td>
          <td>:</td>
          <td><font color="blue">$tarikhSurat</font></td>
        </tr>
        <tr>
          <td align="right" valign="top">Perkara</td>
          <td valign="top">:</td>
          <td rowspan="4" valign="top"><font color="blue">$tajukFail</font></td>
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
      <legend><strong>MAKLUMAT PERMOHONAN PERJANJIAN TAMBAHAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="36%" align="right">Tarikh Terima</td>
          <td width="1%">:</td>
          <td width="63%"><font color="blue">$tarikhTerimaPT</font></td>
        </tr>
        <tr>
          <td width="36%" align="right">Tarikh Surat</td>
          <td width="1%">:</td>
          <td width="63%"><font color="blue">$tarikhSuratPT</font></td>
        </tr>
        <tr>
          <td width="36%" align="right">No Rujukan Surat</td>
          <td width="1%">:</td>
          <td width="63%"><font color="blue">$noRujSuratPT</font></td>
        </tr>
        <tr>
          <td width="36%" align="right">Jenis Perjanjian Tambahan</td>
          <td width="1%">:</td>
          <td width="63%"><font color="blue">$jenisPerjanjianPT</font></td>
        </tr>
        <tr>
          <td align="right" valign="top">Catatan</td>
          <td valign="top">:</td>
          <td rowspan="6" valign="top"><font color="blue">$catatanPT</font></td>
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
          <td><font color="red">$statusPT</font></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
