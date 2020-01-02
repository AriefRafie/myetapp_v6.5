
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($flagPopup == 'openPopupKehadiran')
  <tr>
    <td> #parse("app/php2/frmCRBMaklumatKehadiranDetail.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI KEHADIRAN</strong></legend>
      <table align="center" width="100%">
        <tr>
          <td colspan="3" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onClick="javascript:doDaftarBaruKehadiran()"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="35%"><strong>Nama</strong></td>
          <td width="60%"><strong>Jawatan</strong></td>
        </tr>
        #set ($senaraiKehadiran = "")
        #if ($SenaraiKehadiran.size() > 0)
        #foreach ($senaraiKehadiran in $SenaraiKehadiran)
        #if ($senaraiKehadiran.bil == '')
        #set( $row = "row1" )
        #elseif (($senaraiKehadiran.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$senaraiKehadiran.bil</td>
          <td class="$row"><a href="javascript:doPaparKehadiran('$senaraiKehadiran.idPegawaiLaporanTanah')" class="style2">$senaraiKehadiran.namaPegawai</a></td>
          <td class="$row">$senaraiKehadiran.namaJawatan</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
        <tr>
          <td colspan="7" align="center">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="7" align="center"> #if ($flagPopup == '')
            <input name="cmdBatalLT3" id="cmdBatalLT3" type="button" value="Kembali" onClick="batalLawatanTapak()" />
            #end </td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
