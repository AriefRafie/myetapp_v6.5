<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($mode == 'newBayaranRoyalti' || $mode == 'viewBayaranRoyalti' || $mode == 'updateBayaranRoyalti')
  <tr>
    <td>#parse("app/php2/frmREVBayaranRoyalti.jsp")</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT ROYALTI PASIR</strong></legend>
      #parse("app/utils/record_paging.jsp")
      <table align="center" width="100%">
        #if ($mode == 'view')
        <tr >
          <td colspan="10"><input name="cmdBayaran" type="button" value="Pembayaran" onClick="pemBayaranRoyalti()">
            <input name="cmdJana" type="button" value="Jana Borang Penyerahan" onclick="javascript:janaBorangPenyerahanAPBPelesen('$idJadualPertama')"/>
          </td>
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
          ##<td width="10%" align="right"><strong>Debit (RM)</strong></td>
          ##<td width="10%" align="right"><strong>Kredit (RM)</strong></td>
          <td width="10%" align="right"><strong>Jumlah (RM)</strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiRoyalti.size() > 0)
        #foreach ($list in $SenaraiRoyalti)
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
          <td class="$row" align="center"><a href="javascript:paparBayaranRoyalti('$list.idAkaun')" class="style2">$list.tarikh</a></td>
          #else
          <td class="$row" align="center">$list.tarikh</td>
          #end
          <td class="$row" align="center">$list.tarikhResit</td>
          <td class="$row">$list.noResit</td>
          <td class="$row" align="center">$list.tarikhCek</td>
          <td class="$row">$list.noRujukan</td>
          <td class="$row">#if ($!list.butiran == '') $!list.jenisBayaran #else $!list.butiran #end</td>
          ##<td class="$row" align="right">$list.debit</td>
          ##<td class="$row" align="right">$list.kredit</td>
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
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          ##<td class="row1" align="right">&nbsp;</td>
          ##<td class="row1" align="right">&nbsp;</td>
          <td class="row1" align="right">&nbsp;</td>
        </tr>
        #end
        <tr >
          <td colspan="10" align="center"><hr color="#000000"></td>
        </tr>
        <tr >
          <td align="center">&nbsp;</td>
          <td align="center">&nbsp;</td>
          <td align="center">&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td align="right">&nbsp;</td>
          <td align="right"><strong>JUMLAH</strong></td>
          ##<td align="right">&nbsp;</td>
          ##<td align="right">&nbsp;</td>
          <td align="right"><strong>RM $total</strong></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
