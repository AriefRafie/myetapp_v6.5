<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($mode == 'newPembeliPasir' || $mode == 'viewPembeliPasir' || $mode == 'updatePembeliPasir')
  <tr>
    <td> #parse("app/php2/frmAPBMaklumatPembeliPasir.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI PEMBELI PASIR</strong></legend>
      <table align="center" width="100%">
        #if ($mode == 'view')
        <tr>
          <td colspan="5" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onclick="javascript:tambahPembeliPasir()"/></td>
        </tr>
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
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="center"> #if ($mode == 'view')
      #if ($idStatus == '1610198')
      <input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="seterusnya()"/>
      #end
      <input type="button" name="cdmCetak3" id="cdmCetak3" value="Cetak" onClick="javascript:setTable('tableReport')"/>
      #end </td>
  </tr>
</table>
