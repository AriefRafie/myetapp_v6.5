#set ($Senarai = $session.getAttribute("_portal_moduleVector"))
#set ($startno = $startnoInt.intValue())
#set ($i = $startno)
#set ($total = $totalInt.intValue())
<input name="action" type="hidden" value="$action" />
&nbsp;
<fieldset><legend><strong>Carian</strong></legend>
<table width="100%" border="0" cellpadding="2">
  <tr>
    <td width="29%" align="right">Dari Tarikh </td>
    <td width="1%">:</td>
    <td width="70%"><label>
    <input name="txdDariTarikh" type="text" id="txdDariTarikh" value="$txdDariBulan" size="10" />
    </label>
    <a href="javascript:displayDatePicker('txdDariTarikh',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
    </td>
  </tr>
  <tr>
    <td align="right">Ke Tarikh</td>
    <td>:</td>
    <td><label>
    <input name="txdKeTarikh" type="text" id="txdKeTarikh" value="$txdKeBulan" size="10" />
    </label>
    <a href="javascript:displayDatePicker('txdKeTarikh',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
    </td>
  </tr>
  <tr>
    <td align="right">&nbsp;</td>
    <td>&nbsp;</td>
    <td><label>
      <input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="carian()"/>
    </label>
      <label>
      <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongkan()" />
      </label></td>
  </tr>
</table>
</fieldset>
<fieldset>
<legend><strong>Senarai Fail</strong></legend>
<table width="100%" border="0" cellpadding="2">
  <tr>
    <td colspan="3"><label>
      <input type="button" name="cmdCetakSenarai" id="cmdCetakSenarai" value="Cetak Senarai" onclick="cetakSenarai()" />
    </label></td>
    <td colspan="4">&nbsp;</td>
  </tr>
  <tr class="table_header">
    <td width="3%">No</td>
    <td width="15%">Tarikh  Masa</td>
    <td width="42%">Butiran Transaksi</td>
    <td width="13%">Dari Akaun / Ke Akaun</td>
    <td width="12%">Amaun Bayaran (RM)</td>
    <td width="15%">Status Bayaran / No Rujukan</td>
    <td width="15%">&nbsp;</td>
  </tr>
   #foreach ($bayar in $Senarai)
    #if ($bayar.bil == '') 
    #set ($row = 'row1')
    #elseif ($bayar.bil % 2 != 0)
    #set ($row = 'row1')
    #else 
    #set ($row = 'row2')
    #end  
  <tr>
    <td>$bayar.bil</td>
    <td>$bayar.tarikhBayaran</td>
    <td>$bayar.jenisBayaran</td>
    <td>$bayar.noAkaun</td>
    <td>$bayar.amaunBayaran</td>
    <td>$bayar.statusBayaran</td>
    <td><label>
      <input type="button" name="cmdCetakResit" id="cmdCetakResit" value="Cetak Resit" onclick="cetakResit('$bayar.idPembayaranOnline')" />
    </label></td>
  </tr>
  #end
</table>
</fieldset>
<table width="100%" border="0" cellpadding="2">
  	<tr>
    <td align="right"><strong>CL-08-16</strong></td>
  	</tr>
</table>
<script>
function carian(){
	document.${formName}.action.value = "cari";
	document.${formName}.submit();
}
function kosongkan(){
	document.${formName}.reset();
	document.${formName}.txdDariTarikh.value = "";
	document.${formName}.txdKeTarikh.value = "";
}
function cetakSenarai() {
		var url = "../servlet/ekptg.report.online.SejarahPembayaran?dariBulan="+document.${formName}.txdDariTarikh.value+"&keBulan="+document.${formName}.txdKeTarikh.value;
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();

}
function cetakResit(id) {
		var url = "../servlet/ekptg.report.online.ResitBayaran?idPembayaranOnline="+id;
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();

}
</script>

