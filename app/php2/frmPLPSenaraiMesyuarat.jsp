<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($flagPopup == 'openPopupMesyuarat')
  <tr>
    <td>#parse("app/php2/frmPLPMesyuaratDetail.jsp")</td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI MESYUARAT</strong></legend>
      <table align="center" width="100%">
        #if ($flagPopup == '')
        <tr>
          <td colspan="3" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onClick="javascript:doDaftarBaruMesyuarat()"/></td>
        </tr>
        #end
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="45%"><strong>Bil Mesyuarat</strong></td>
          <td width="50%"><strong>Tarikh</strong></td>
        </tr>
        #set ($senaraiMesyuarat = "")
        #if ($SenaraiMesyuarat.size() > 0)
        #foreach ($senaraiMesyuarat in $SenaraiMesyuarat)
        #if ($senaraiMesyuarat.bil == '')
        #set( $row = "row1" )
        #elseif (($senaraiMesyuarat.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$senaraiMesyuarat.bil</td>
          <td class="$row"><a href="javascript:paparMesyuarat('$senaraiMesyuarat.idKertasKerja','$senaraiMesyuarat.idMesyuarat')" class="style2">$senaraiMesyuarat.bilMesyuarat</a></td>
          <td class="$row">$senaraiMesyuarat.tarikhMesyuarat</td>
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
          <td colspan="7">&nbsp;</td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #if ($flagPopup == '' && $idStatus == '1610201')
  <tr>
    <td align="center"><input type="button" name="cmdHantar" id="cmdHantar" value="Seterusnya" onClick="doSeterusnya()"/></td>
  </tr>
  #end
</table>
