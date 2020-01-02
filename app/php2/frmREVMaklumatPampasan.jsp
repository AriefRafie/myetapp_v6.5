<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($mode == 'newBayaran' || $mode == 'viewBayaran' || $mode == 'updateBayaran')
  <tr>
  	<td>#parse("app/php2/frmREVBayaran.jsp")</td>
  </tr>
  <tr>
  	<td>&nbsp; </td>
  </tr>  
  #end 
  #if ($mode == 'newPelarasan' || $mode == 'viewPelarasan' || $mode == 'updatePelarasan')
  <tr>
  	<td>#parse("app/php2/frmREVPelarasan.jsp")</td>
  </tr>
  <tr>
  	<td>&nbsp; </td>
  </tr>  
  #end  
  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT PAMPASAN</strong></legend>
      <table align="center" width="100%">
        #if ($mode == 'view')
        <tr >
          <td colspan="8"><input name="cmdBayaran" type="button" value="Pembayaran" onClick="pemBayaran()">
            <input name="cmdPelarasan" type="button" value="Pelarasan" onClick="pelarasan()">
          </td>
        </tr>
        #end
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="8%" align="center"><strong>Tarikh Transaksi</strong></td>
          <td width="8%" align="center"><strong>Tarikh Resit</strong></td>
          <td width="10%"><strong>No Resit</strong></td>
          <td width="12%"><strong>No Cek / Rujukan</strong></td>
          <td width="27%"><strong>Butiran</strong></td>
          <td width="10%" align="right"><strong>Debit (RM)</strong></td>
          <td width="10%" align="right"><strong>Kredit (RM)</strong></td>
          <td width="10%" align="right"><strong>Baki (RM)</strong></td>
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
        <tr>
          <td class="$row" align="center">$list.bil</td>
          #if ($list.idJenisTransaksi == '2')
          <td class="$row" align="center"><a href="javascript:paparBayaran('$list.idAkaun')" class="style2">$list.tarikh</a></td>
          #elseif ($list.idJenisTransaksi == '3')
          <td class="$row" align="center"><a href="javascript:paparPelarasan('$list.idAkaun')" class="style2">$list.tarikh</a></td>
          #else
          <td class="$row" align="center">$list.tarikh</td>
          #end          
          <td class="$row" align="center">$list.tarikhResit</td>
          <td class="$row">$list.noResit</td>
          <td class="$row">$list.noRujukan</td>
          <td class="$row">$list.butiran</td>
          <td class="$row" align="right">$list.debit</td>
          <td class="$row" align="right">$list.kredit</td>
          <td class="$row" align="right">$list.baki</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" align="center">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1" align="right">&nbsp;</td>
          <td class="row1" align="right">&nbsp;</td>
          <td class="row1" align="right">&nbsp;</td>
        </tr>
        #end
        <tr >
          <td colspan="9" align="center"><hr color="#000000"></td>
        </tr>
        <tr >
          <td align="center">&nbsp;</td>
          <td align="center">&nbsp;</td>
          <td align="center">&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td align="right"><strong>BAKI TUNGGAKAN / (LEBIHAN)</strong></td>
          <td align="right">&nbsp;</td>
          <td align="right">&nbsp;</td>
          <td align="right"><strong>RM $total</strong></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>

