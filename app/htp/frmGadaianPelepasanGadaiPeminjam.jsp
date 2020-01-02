#foreach ( $peminjam in $Peminjam )

<fieldset>
<legend>Maklumat Peminjam</legend>
<table width="100%" border="0">
  <tr>
    <td width="33%"><div align="left"><strong>Nama </strong></div></td>
    <td width="1%"><strong>:</strong></td>
    <td width="66%">$peminjam.nama</td>
    </tr>
  <tr>
    <td><div align="left"><strong>Alamat </strong></div></td>
    <td><strong>:</strong></td>
    <td>$peminjam.alamat1</td>
    </tr>
  <tr>
    <td><div align="left"><strong></strong></div></td>
    <td>&nbsp;</td>
    <td>$peminjam.alamat2</td>
    </tr>
  <tr>
    <td><div align="left"><strong></strong></div></td>
    <td>&nbsp;</td>
    <td>$peminjam.alamat3</td>
    </tr>
  <tr>
    <td><div align="left"><strong>Poskod </strong></div></td>
    <td><strong>:</strong></td>
    <td>$peminjam.poskod</td>
    </tr>
  <tr>
    <td><div align="left"><strong>Negeri </strong></div></td>
    <td><strong>:</strong></td>
    <td>$peminjam.namaNegeri</td>
    </tr>
  <tr>
    <td><div align="left"><strong>Daerah </strong></div></td>
    <td><strong>:</strong></td>
    <td>$peminjam.namaDaerah</td>
    </tr>
  <tr>
    <td><div align="left"><strong>No. Telefon </strong></div></td>
    <td><strong>:</strong></td>
    <td>$peminjam.noTel</td>
    </tr>
  <tr>
    <td><div align="left"><strong>No. Fax </strong></div></td>
    <td><strong>:</strong></td>
    <td>$peminjam.noFax</td>
    </tr>
  <tr>
    <td><div align="left"></div></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    </tr>
</table>
<fieldset>
<legend>Maklumat Hakmilik</legend>
<table width="100%" border="0">
  <tr>
    <td><strong>Gadaian Pendua No. Perserahan </strong></td>
    <td><strong>:</strong></td>
    <td>$peminjam.noPerserahan</td>
  </tr>
  <tr>
    <td width="34%"><strong>No. Hakmilik</strong></td>
    <td width="1%"><strong>:</strong></td>
    <td width="65%">$peminjam.noHakmilik</td>
  </tr>
  <tr>
    <td width="34%"><strong>Jenis Hakmilik</strong></td>
    <td><strong>:</strong></td>
    <td>$peminjam.jenisHakmilik</td>
  </tr>
  <tr>
    <td><strong>Kod / No. Lot</strong></td>
    <td><strong>:</strong></td>
    <td>$peminjam.namaLot / $peminjam.noLot</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</fieldset>
<fieldset>
<legend>Pelepasan Gadaian</legend>
<table width="100%" border="0">
  <tr>
    <td width="34%"><strong>Tarikh Pelepasan Gadaian</strong></td>
    <td width="1%"><strong>:</strong></td>
    <td width="65%"><input type="text" name="txTarikhLepasGadai" id="txTarikhLepasGadai" size="15" value="$peminjam.TarikhLepasGadai" $classDis $modeDis />
    
    #if($pagemode == 'new')
      <img src="../img/calendar.gif" alt="calendar" border="0" style="display:$Style2" onclick="displayDatePicker('txTarikhLepasGadai',false,'dmy');" />
      #end
      
      </td>
    </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3" align="center"></td>
    </tr>
  <tr>
    <td colspan="3">
    
    #if($pagemode == 'new')
      <div align="center">
        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:fGPGP_SimpanPelepasan()">
        &nbsp;
        <input type="reset" name="cmdBatal" id="cmdBatal" $btnNamePemilik onclick="javascript:fGPGP_BatalPelepasan()">
        </div>
#elseif($pagemode == 'view')
<div align="center">
  <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:fGPGP_KemaskiniPelepasan()">
  &nbsp;
  <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:fGPGP_KembaliPelepasan()">
</div>
#elseif ($pagemode == 'update')
<div align="center">
  <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:fGPGP_SimpanPelepasan()">
  &nbsp;
  <input type="reset" name="cmdBatal" id="cmdBatal" $btnNamePemilik onclick="javascript:fGPGP__BatalPelepasan()">
  </div>
#end


</td>
    </tr>
</table>
</fieldset>
<p>&nbsp;</p>
<p>&nbsp;</p>
</fieldset>

<input type="hidden" name="idFail" value="$idFail" />
<input type="hidden" name="idPermohonan" value="$idPermohonan" />
#end