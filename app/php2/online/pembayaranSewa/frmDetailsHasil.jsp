<input type="hidden" name="idHasil" id="idHasil" value="$idHasil"/>

<table width="95%"  align="center" >
<tr><td width="100%">
<fieldset >
<legend>MAKLUMAT SEWA [$NO_FAIL] <input type="button"  value="Tutup" onclick="$jquery('#showDetails$ID_HASIL').html('');" /></legend>
<div id="TabbedPanels1$ID_HASIL" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTabUpper(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT DEPOSIT</li>
          <li onClick="doChangeTabUpper(1);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT SEWA</li>
          <li onClick="doChangeTabUpper(2);" class="TabbedPanelsTab" tabindex="0">BAYARAN LAIN LAIN</li>
          <li onClick="doChangeTabUpper(3);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT PERJANJIAN</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent">
          <table align="center" width="100%">        
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
	        #if ($senaraiDeposit.size() > 0)
	        #foreach ($list in $senaraiDeposit)
	        #if ($list.bil == '')
	        #set( $row = "row1" )
	        #elseif (($list.bil % 2) != 0)
	        #set( $row = "row1" )
	        #else 
	        #set( $row = "row2" )
	        #end
	        <tr>
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
          </div>
          <div class="TabbedPanelsContent">
          <table align="center" width="100%">
          <tr>
			   <td><fieldset>
			   <legend><strong>SENARAI CETAKAN</strong></legend>
			     <table align="center" width="100%">
			       <tr>
			         <td><a href="#" class="style1" onclick="javascript:janaPenyataAkaun('$ID_HASIL')">Penyata Akaun</a></td>
			       </tr>
			     </table>
			   </fieldset></td>
		  </tr>
		  </table>
	      <table align="center" width="100%">
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
	        #if ($senaraiMaklumatSewa.size() > 0)
	        #foreach ($list in $senaraiMaklumatSewa)
	        #if ($list.bil == '')
	        #set( $row = "row1" )
	        #elseif (($list.bil % 2) != 0)
	        #set( $row = "row1" )
	        #else 
	        #set( $row = "row2" )
	        #end
	        <tr>
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
	          <td colspan="2" align="right"><strong>BAKI TUNGGAKAN / (LEBIHAN)</strong></td>
	          <td align="right">&nbsp;</td>          
	          <td align="right"><strong>RM $totalMaklumatSewa</strong></td>
	        </tr>
	      </table>
          </div>
          <div class="TabbedPanelsContent">
	      <table align="center" width="100%">
	        <tr class="table_header">
	          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
	          <td width="8%" align="center"><strong>Tarikh Transaksi</strong></td>
	          <td width="8%" align="center"><strong>Tarikh Resit</strong></td>
	          <td width="10%"><strong>No Resit</strong></td>
	          <td width="8%" align="center"><strong>Tarikh Cek</strong></td>
	          <td width="12%"><strong>No Cek / Rujukan</strong></td>
	          <td width="19%"><strong>Butiran</strong></td>
	          <td width="10%" align="right"><strong>Jumlah (RM)</strong></td>
	        </tr>
	        #set ($list = "")
	        #if ($senaraiBayaranLL.size() > 0)
	        #foreach ($list in $senaraiBayaranLL)
	        #if ($list.bil == '')
	        #set( $row = "row1" )
	        #elseif (($list.bil % 2) != 0)
	        #set( $row = "row1" )
	        #else 
	        #set( $row = "row2" )
	        #end
	        <tr>
	          <td class="$row" align="center">$list.bil</td>
	          <td class="$row" align="center">$list.tarikh</td>
	          <td class="$row" style="color:$color" align="center">$list.tarikhResit</td>
	          <td class="$row" style="color:$color">$list.noResit</td>
	          <td class="$row" style="color:$color" align="center">$list.tarikhCek</td>
	          <td class="$row" style="color:$color">$list.noRujukan</td>
	          <td class="$row" style="color:$color">$list.butiran</td>
	          <td class="$row" style="color:$color" align="right">$list.baki</td>
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
	          <td colspan="10" align="center"><hr color="#000000"></td>
	        </tr>
	        <tr >
	          <td align="center">&nbsp;</td>
	          <td>&nbsp;</td>
	          <td>&nbsp;</td>
	          <td class="row1">&nbsp;</td>
	          <td align="right"><strong>JUMLAH (RM)</strong></td>
	          <td align="right">&nbsp;</td>
	          <td align="right">&nbsp;</td>
	          <td align="right"><strong>RM $totalBayaranLL</strong></td>
	        </tr>
	      </table>
          </div>
          <div class="TabbedPanelsContent">
	      <table align="center" width="100%">
	        <tr class="table_header">
	          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
	          <td width="20%"><strong>No. Siri Perjanjian</strong></td>
	          <td width="15%" align="center"><strong>Tarikh Mula</strong></td>
	          <td width="15%" align="center"><strong>Tarikh Tamat</strong></td>
	          <td width="15%" align="right"><strong>Deposit (RM)</strong></td>
	          <td width="15%" align="right"><strong>Sewa (RM)</strong></td>
	          <td width="15%" align="center"><strong>Status</strong></td>
	        </tr>
	        #set ($list = "")
	        #if ($senaraiPerjanjian.size() > 0)
	        #foreach ($list in $senaraiPerjanjian)
	        #if ($list.bil == '')
	        #set( $row = "row1" )
	        #elseif (($list.bil % 2) != 0)
	        #set( $row = "row1" )
	        #else 
	        #set( $row = "row2" )
	        #end
	        <tr>
	          <td class="$row" align="center">$list.bil</td>
			  <td class="$row">$list.NO_RUJUKAN</td>
	          <td class="$row" align="center">$list.TARIKH_MULA</td>
	          <td class="$row" align="center">$list.TARIKH_TAMAT</td>
	          <td class="$row" align="right">$list.DEPOSIT</td>
	          <td class="$row" align="right">$list.BAYARAN</td>
	          #if ($list.FLAG_AKTIF == 'Y')
	          <td class="$row" align="center"><strong>AKTIF</strong></td>
	          #else
	          <td class="$row" align="center"><strong>TIDAK AKTIF</strong></td>
	          #end </tr>
	        #end
	        #else
	        <tr>
	          <td class="row1" align="center">&nbsp;</td>
	          <td class="row1">Tiada Rekod</td>
	          <td class="row1" align="center">&nbsp;</td>
	          <td class="row1" align="center">&nbsp;</td>
	          <td class="row1" align="right">&nbsp;</td>
	          <td class="row1" align="right">&nbsp;</td>
	          <td class="row1" align="right">&nbsp;</td>
	        </tr>
	        #end
	      </table>
          </div>
        </div>
</fieldset>
</td>
</tr>
</table>

<script>
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1$ID_HASIL",{defaultTab:0});
</script>