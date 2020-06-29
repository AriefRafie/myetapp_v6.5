<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($mode == 'newPembeliPasir' || $mode == 'viewPembeliPasir' || $mode == 'updatePembeliPasir')
  <tr>
    <td> #parse("app/php2/online/frmAPBMaklumatPembeliPasir.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI PEMBELI PASIR</strong></legend>
      <table align="center" width="100%">
        #if ($idStatus == '')
        #if ($mode == 'view')
        <tr>
          <td colspan="5" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onclick="javascript:tambahPembeliPasir()"/></td>
        </tr>
        #end
        #end
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="95%"><strong>Nama Pembeli Pasir</strong></td>
        </tr>
        #set ($listPembeliPasir = "")
        #if ($SenaraiPembeliPasir.size() > 0)
        #foreach ($listPembeliPasir in $SenaraiPembeliPasir)
        #if ($listPembeliPasir.bil == '')
        #set( $row = "row1" )
        #elseif (($listPembeliPasir.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$listPembeliPasir.bil</td>
          <td class="$row"><a href="javascript:paparPembeliPasir('$listPembeliPasir.idPembeliPasir')" class="style2">$listPembeliPasir.nama</a></td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td align="center"> <!-- #if ($idStatus == '') -->
    	<!-- Pindah ke tab pengesahan -->
    	<!-- <input type="button" name="cdmCetak" id="cdmCetakBorang" value="Cetak Borang Permohonan" onClick="javascript:cetakBorangPermohonan('$idPermohonan')"/>
      <input type="button" name="cmdHantar" id="cmdHantar" value="Hantar &amp; Emel" onClick="doHantarEmel()"/>
      <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onClick="doHapus()"/> -->
      <input type="button" name="cmdBackList1" id="cmdBackList1" value="Kembali" onclick="doBacklist()"/>
      <!--#else
      #if ($idStatus !='')
      <input type="button" name="cdmCetak" id="cdmCetakBorang" value="Cetak Borang Permohonan" onClick="javascript:cetakBorangPermohonan('$idPermohonan')"/>
      <input type="button" name="cdmCetak" id="cdmCetakPengesahan" value="Cetak Pengesahan Permohonan" onClick="javascript:cetakPengesahanPermohonan('$idPermohonan')"/>
      #end
      #end--> </td>
  </tr>
  <tr>
    <td align="center"></td>
  </tr>
</table>
