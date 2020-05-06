<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>SENARAI NOTIFIKASI EMEL</b></legend>
      <table align="center" width="100%">
        <tr>
          <td colspan="9" scope="row"><input name="cmdTambah" type="button" value="Tambah" onClick="javascript:daftarNotifikasi()"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="25%"><strong>Nama Pegawai</strong></td>
          <td width="35%"><strong>PTD / PTG / KJP / JKPTG</strong></td>
          <td width="15%" align="center"><strong>Emel</strong></td>
          <td width="10%"><strong>Status</strong></td>
          ##<td width="5%"><strong></strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiNotifikasiEmel.size() > 0)
        #foreach ($list in $SenaraiNotifikasiEmel)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row"><a href="javascript:paparMaklumatSuratPenghargaan('$list.idUlasanTeknikal')" class="style2">$list.namaPegawai</a></td>
          <td class="$row">$list.namaPejabatPTGPTD</td>
          <td class="$row" align="center">$list.emel</td>
          <td class="$row">$list.status</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" >Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
