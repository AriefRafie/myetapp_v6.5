<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><strong>$!laporan.namaLaporan</strong></legend>
      <table align="center" width="100%">
        #if ($!userRole == '(PHP)UserAPB')
        <tr>
          <td colspan="4"><input name="cmdDaftarDokumen" type="button" value="Tambah" onclick="doDivAjaxCall$formname('divMainForm','daftarDokumen','');"/></td>
        </tr>
        #end
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td ><strong>Nama Dokumen</strong></td>
          <td ><strong>Catatan</strong></td>
          <td ><strong>History</strong></td>
          #if ($!userRole == '(PHP)UserAPB')
          <td >&nbsp;</td>
          #end
        </tr>
        #set ($list = "")
        #set ( $count = 0 )
        #if ($listDokumen.size() > 0)
        #foreach ($list in $listDokumen)
        #set ( $count = $count + 1 )
        #if ($count == '')
        #set( $row = "row1" )
        #elseif (($count % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$count</td>
          <td class="$row"><a href="javascript:muatTurun($!list.idDokumen)" class="style2">$!list.namaDokumen</a></td>
          <td class="$row" align="left">$!list.catatan</td>
          <td class="$row" align="left"><a href="javascript:lihatHistory($!list.idDokumen)" class="style2">Lihat</a></td>
          #if ($!userRole == '(PHP)UserAPB')
          <td class="$row" align="center"><span id="hapusDoc"> <a href="javascript:void()" onClick="deleteDokumen($!list.idDokumen)" ><img src="../img/delete.gif" border="0"></a> </span></td>
          #end
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" colspan="2">Tiada Rekod</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td align="center"><input name="cmdKembali" type="button" value="Kembali" onclick="doDivAjaxCall$formname('divMainForm','','');"/></td>
  </tr>
</table>

