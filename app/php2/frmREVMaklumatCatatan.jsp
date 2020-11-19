<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($mode == 'newCatatan' || $mode == 'viewCatatan' || $mode == 'updateCatatan')
  <tr>
    <td>#parse("app/php2/frmREVCatatan.jsp")</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT CATATAN</strong></legend>
      <table align="center" width="100%">
        #if ($mode == 'view')
        <tr >
          <td colspan="8"><input name="cmdCatatan" type="button" value="Tambah" onClick="tambahCatatan()"></td>
        </tr>
        #end
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="95%"><strong>Catatan</strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiCatatan.size() > 0)
        #foreach ($list in $SenaraiCatatan)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row" ><a href="javascript:paparCatatan('$list.idCatatan')" class="style2">$list.catatan</a></td>
        </tr>
        #end
        #else
        <tr>
          <td colspan="2" align="left" class="row1">Tiada Rekod</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
