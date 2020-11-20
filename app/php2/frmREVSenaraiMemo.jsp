<input name="usiaABT" type="hidden" value="$!abt.bulan" />
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI MEMO</strong></legend>
      <table align="center" width="100%">
        #if ($mode == 'view')
        <tr >
          <td colspan="9">
          	<input name="cmdMemo" type="button" value="Jana Memo" onClick="janaMemo('$!idHasil')">
          </td>
        </tr>
        #end
        <tr class="table_header" align="center">
          <td width="5%" align="center"><strong>Bil</strong></td>
          <td width="25%" ><strong>Jenis Memo</strong></td>
          <td width="10%"><strong>Tarikh Memo</strong></td>
          <td width="10%"><strong>Tarikh Akhir Memo</strong></td>
          <td width="10%"><strong>Kadar Sewa (RM)</strong></td>
          <td width="10%"><strong>Jumlah Bulan Tertunggak (Bulan)</strong></td>
          <td width="10%"><strong>Jumlah Tunggakan (RM)</strong></td>
          <td width="5%">&nbsp;</td>
          <td width="5%">&nbsp;</td>
        </tr>
        #set ($list = "")
        #set ( $count = 0 )
        #if ($listMemo.size() > 0)
        #foreach ($list in $listMemo)
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
          <td class="$row" align="center">$list.peringatan</td>
          <td class="$row" align="center">$list.tarikhNotis</td>
          <td class="$row" align="center">$list.tarikhAkhirNotis</td>
          <td class="$row" align="right">$list.kadarSewa</td>
          <td class="$row" align="center">$list.bulanTunggakan</td>
          <td class="$row" align="right">$list.jumlahTunggakan</td>
          <td class="$row" align="center">
	          #if ($list.idJenisNotis == '1')
	          	<a href="#" class="style2" onClick="javascript:cetakMemoTuntutanDeposit('$list.idNotis')"><img border="0" src="../img/print.gif"/></a>
	          #end
	          #if ($list.idJenisNotis == '2')
	          	<a href="#" class="style2" onClick="javascript:cetakMemoTuntutanHasil('$list.idNotis')"><img border="0" src="../img/print.gif"/></a>
	          #end
	          #if ($list.idJenisNotis == '3')
	          	<a href="#" class="style2" onClick="javascript:cetakMemoPelarasanDeposit('$list.idNotis')"><img border="0" src="../img/print.gif"/></a>
	          #end
	          #if ($list.idJenisNotis == '4')
	          	<a href="#" class="style2" onClick="javascript:cetakMemoRampasanDeposit'$list.idNotis')"><img border="0" src="../img/print.gif"/></a>
	          #end
          </td>
          <td class="$row" align="center"><a href="javascript:void()" onClick="hapusNotis('$!list.idNotis')" ><img src="../img/hapus.gif" border="0"></a></td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td colspan="8" class="row1">Tiada Rekod</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>

<script>
function janaMemo(idHasil) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmREVPopupMemoView?idHasil="+idHasil+"&report=memo";
    var hWnd = window.open(url,'printuser','width=1000,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>