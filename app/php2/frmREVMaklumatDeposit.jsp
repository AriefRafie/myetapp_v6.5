<style type="text/css">
<!--
.style2 {color: #0000FF}
.style3 {color: #FF0000}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($mode == 'newBayaranD' || $mode == 'viewBayaranD' || $mode == 'updateBayaranD')
  <tr>
  	<td>#parse("app/php2/frmREVBayaranDeposit.jsp")</td>
  </tr>
  <tr>
  	<td>&nbsp; </td>
  </tr>  
  #end 
  #if ($mode == 'newPelarasanD' || $mode == 'viewPelarasanD' || $mode == 'updatePelarasanD')
  <tr>
  	<td>#parse("app/php2/frmREVPelarasanDeposit.jsp")</td>
  </tr>
  <tr>
  	<td>&nbsp; </td>
  </tr>  
  #end  
  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT DEPOSIT</strong></legend>
      #if ($!selectedTabUpper == '0')
      	#parse("app/utils/record_paging.jsp")
      #end
      <table align="center" width="100%">
        #if ($mode == 'view')
        <tr >
          <td colspan="10"><input name="cmdBayaran" type="button" value="Pembayaran" onClick="pembayaranD()">
            <input name="cmdPelarasan" type="button" value="Pelarasan" onClick="pelarasanD()">
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
          <td width="10%" align="right"><strong>Debit (RM)</strong></td>
          <td width="10%" align="right"><strong>Kredit (RM)</strong></td>
          <td width="10%" align="right"><strong>Baki (RM)</strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiDeposit.size() > 0)
        #foreach ($list in $SenaraiDeposit)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr> #if ($list.idJenisTransaksi == '2')
          <td align="center" class="$row style2">$list.bil</td>
          <td class="$row" align="center"><a href="javascript:paparBayaranD('$list.idAkaun')" class="style2 style2"><u>$list.tarikh</u></a></td>
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
          <td class="$row" align="center"><a href="javascript:paparPelarasanD('$list.idAkaun')" class="style3"><u>$list.tarikh</u></a></td>
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
          <td class="$row" align="center"><a href="#" class="style2" onClick="javascript:cetakInvois('$list.idAkaun')"><img border="0" src="../img/print.gif"/></a></td>
          #else
          <td class="$row">&nbsp;</td>
          #end
          #end </tr>
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
          <td class="row1" align="right">&nbsp;</td>
          <td class="row1" align="right">&nbsp;</td>
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
          <td align="right"><strong>BAKI TUNGGAKAN / (LEBIHAN)</strong></td>
          <td align="right">&nbsp;</td>
          <td align="right">&nbsp;</td>          
          <td align="right"><strong>RM $totalDeposit</strong></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>

