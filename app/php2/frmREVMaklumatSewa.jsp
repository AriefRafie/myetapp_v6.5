<style type="text/css">
<!--
.style2 {color: #0000FF}
.style3 {color: #FF0000}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($mode == 'newBayaran' || $mode == 'viewBayaran' || $mode == 'updateBayaran')
  <tr>
    <td>#parse("app/php2/frmREVBayaran.jsp")</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end 
  #if ($mode == 'newPelarasan' || $mode == 'viewPelarasan' || $mode == 'updatePelarasan')
  <tr>
    <td>#parse("app/php2/frmREVPelarasan.jsp")</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI CETAKAN</strong></legend>
      <table align="center" width="100%">
        <tr>
          <td><a href="#" class="style2" onclick="javascript:janaPenyataAkaun('$idHasil')"> Penyata Akaun </a></td>
        </tr>
        <tr>
          <td><a href="#" class="style2" onclick="javascript:janaSuratIringanResit('$idHasil')"> Surat Iringan Resit</a></td>
        </tr>
        <tr>
          <td><a href="#" class="style2" onclick="javascript:janaSuratTuntutanDeposit('$idHasil')"> Surat Tuntutan Deposit </a></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT SEWA</strong></legend>
      #if ($!selectedTabUpper == '1')
      #parse("app/utils/record_paging.jsp")
      #end
      <table align="center" width="100%">
        #if ($mode == 'view')
        <tr >
          <td colspan="11"><input name="cmdBayaran" type="button" value="Pembayaran Sewa" onClick="pemBayaran()">
            <input name="cmdPelarasan" type="button" value="Pelarasan" onClick="pelarasan()">
            <input name="cmdJana" type="button" value="Jana Borang Penyerahan" onclick="javascript:janaBorangPenyerahanPenyewa('$idHasil')"/>          </td>
        </tr>
        #end
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="8%" align="center"><strong>Tarikh Transaksi</strong></td>
          <td width="8%" align="center"><strong>Tarikh Resit</strong></td>
          <td width="10%"><strong>No Resit</strong></td>
          <td width="8%" align="center"><strong>Tarikh Cek</strong></td>
          <td width="12%"><strong>No Cek / Rujukan</strong></td>
          <td width="19%"><strong>Butiran</strong></td>
          <td width="8%" align="right"><strong>Debit (RM)</strong></td>
          <td width="8%" align="right"><strong>Kredit (RM)</strong></td>
          <td width="8%" align="right"><strong>Baki (RM)</strong></td>
          <td width="6%" align="center"><strong>Invois</strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiAkaun.size() > 0)
        #foreach ($list in $SenaraiAkaun)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr> #if ($list.idJenisTransaksi == '2')
          <td align="center" class="$row style2">$list.bil</td>
          <td class="$row" align="center"><a href="javascript:paparBayaran('$list.idAkaun')" class="style2 style2"><u>$list.tarikh</u></a></td>
          <td align="center" class="$row style2">$list.tarikhResit</td>
          <td class="$row style2">$list.noResit</td>
          <td align="center" class="$row style2">$list.tarikhCek</td>
          <td class="$row style2">$list.noRujukan</td>
          <td class="$row style2">$list.butiran</td>
          <td align="right" class="$row style2">$list.debit</td>
          <td align="right" class="$row style2">$list.kredit</td>
          <td align="right" class="$row style2">$list.baki</td>
          <td class="$row style2">&nbsp;</td>
          #elseif ($list.idJenisTransaksi == '3')
          <td align="center" class="$row style3">$list.bil</td>
          <td class="$row" align="center"><a href="javascript:paparPelarasan('$list.idAkaun')" class="style3"><u>$list.tarikh</u></a></td>
          <td align="center" class="$row style3">$list.tarikhResit</td>
          <td class="$row style3">$list.noResit</td>
          <td align="center" class="$row style3">$list.tarikhCek</td>
          <td class="$row style3">$list.noRujukan</td>
          <td class="$row style3">$list.butiran</td>
          <td align="right" class="$row style3">$list.debit</td>
          <td align="right" class="$row style3">$list.kredit</td>
          <td align="right" class="$row style3">$list.baki</td>
          <td class="$row">&nbsp;</td>
          #else
          <td class="$row" align="center">$list.bil</td>
          <td class="$row" align="center">$list.tarikh</td>
          <td class="$row" align="center">$list.tarikhResit</td>
          <td class="$row">$list.noResit</td>
          <td class="$row" align="center">$list.tarikhCek</td>
          <td class="$row">$list.noRujukan</td>
          <td class="$row">$list.butiran</td>
          <td class="$row" align="right">$list.debit</td>
          <td class="$row" align="right">$list.kredit</td>
          <td class="$row" align="right">$list.baki</td>
          #if ($!list.idJenisTransaksi == '1')
          <td class="$row" align="center">
          	<a href="#" class="style2" onClick="javascript:cetakInvois('$list.idAkaun')"><img border="0" src="../img/print.gif"/></a>
          	<a class="style2" href="javascript:sendNotisByEmail('$list.idAkaun')">Hantar</a>
          </td>
          #else
          <td class="$row">&nbsp;</td>
          #end
          #end </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td colspan="10" align="left" class="row1">Tiada Rekod</td>
        </tr>
        #end
        <tr >
          <td colspan="11" align="center"><hr color="#000000"></td>
        </tr>
        <tr >
          <td colspan="7" align="right"><strong>BAKI TUNGGAKAN / (LEBIHAN)</strong></td>
          <td colspan="3" align="right"><strong>RM $total</strong></td>
          <td align="right">&nbsp;</td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>

<input name="upperButon"  id="upperButon" type="button" value="KEMBALI KE ATAS" onclick="goUpperScreen()">


