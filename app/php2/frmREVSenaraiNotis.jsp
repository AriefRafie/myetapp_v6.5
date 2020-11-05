<input name="usiaABT" type="hidden" value="$!abt.bulan" />
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI NOTIS</strong></legend>
      <table align="center" width="100%">
        #if ($mode == 'view')
        <tr >
          <td colspan="9"><input name="cmdNotisTuntutan" type="button" value="Jana Notis Tuntutan Tunggakan" onClick="janaNotisTunggakan('$!idHasil')">
            <input name="cmdNotisRampasan" type="button" value="Jana Notis Rampasan Deposit" onClick="janaNotisRampasan('$!idHasil')">
          </td>
        </tr>
        #end
        <tr class="table_header" align="center">
          <td width="5%" align="center"><strong>Bil</strong></td>
          <td width="25%" ><strong>Jenis Notis</strong></td>
          <td width="10%"><strong>Bil. Peringatan</strong></td>
          <td width="10%"><strong>Tarikh Mula Notis</strong></td>
          <td width="10%"><strong>Tarikh Akhir Notis</strong></td>
          <td width="10%"><strong>Kadar Sewa (RM)</strong></td>
          <td width="10%"><strong>Jumlah Bulan Tertunggak (Bulan)</strong></td>
          <td width="10%"><strong>Jumlah Tunggakan (RM)</strong></td>
          <td width="10%"><strong>Status</strong></td>
          <td width="5%">&nbsp;</td>
          <td width="5%">&nbsp;</td>
        </tr>
        #set ($list = "")
        #set ( $count = 0 )
        #if ($listNotis.size() > 0)
        #if ($listNotis.size() == '1')
	          #set( $statusP = 'AKTIF')
	        	#set( $statusD = 'TIDAK AKTIF')
	        	#set( $statusT = 'TIDAK AKTIF')
	    #end
          #if ($listNotis.size()  == '2')
          		#set( $statusP = 'TIDAK AKTIF')
        		#set( $statusD = 'AKTIF')
	        	#set( $statusT = 'TIDAK AKTIF')
	    #end
        #if ($listNotis.size()  == '3')
	          	#set( $statusP = 'TIDAK AKTIF')
	        	#set( $statusD = 'TIDAK AKTIF')
	        	#set( $statusT = 'AKTIF')
        #end
        #foreach ($list in $listNotis)
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
          <td class="$row">
          #if ($list.idJenisNotis == '1' && $list.bilPeringatan == '1')
          	<font color="#006600">$list.jenisNotis</font>
          #elseif ($list.idJenisNotis == '1' && $list.bilPeringatan == '2')
          	<font color="#FF6600">$list.jenisNotis</font>
          #elseif ($list.idJenisNotis == '1' && $list.bilPeringatan == '3')
          	<font color="#FF0000">$list.jenisNotis</font>
          #else
          	$list.jenisNotis
          #end
          </td>
          <td class="$row" align="center">$list.peringatan</td>
          <td class="$row" align="center">$list.tarikhNotis</td>
          <td class="$row" align="center">$list.tarikhAkhirNotis</td>
          <td class="$row" align="right">$list.kadarSewa</td>
          <td class="$row" align="center">$list.bulanTunggakan</td>
          <td class="$row" align="right">$list.jumlahTunggakan</td>
          <td class="$row"  align="center">

          #if ($list.idJenisNotis == '1' && $list.bilPeringatan == '1')
	          $statusP
          #elseif ($list.idJenisNotis == '1' && $list.bilPeringatan == '2')
         	  $statusD
          #elseif ($list.idJenisNotis == '1' && $list.bilPeringatan == '3')
	          $statusT
          #end

          </td>
          <td class="$row" align="center">
          #if ($list.idJenisNotis == '1')
          <a href="#" class="style2" onClick="javascript:cetakSuratTuntutanTunggakanSewa('$list.idNotis')"><img border="0" src="../img/print.gif"/></a>
          #end
          #if ($list.idJenisNotis == '2')
          <a href="#" class="style2" onClick="javascript:cetakSuratRampasanDeposit('$list.idNotis')"><img border="0" src="../img/print.gif"/></a>
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
function janaNotisTunggakan(idHasil) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmREVPopupNotisTunggakanView?idHasil="+idHasil+"&report=notisTuntutanTunggakan";
    var hWnd = window.open(url,'printuser','width=1000,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function janaNotisRampasan(idHasil) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmREVPopupNotisTunggakanView?idHasil="+idHasil+"&report=notisRampasanDeposit";
    var hWnd = window.open(url,'printuser','width=1000,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>