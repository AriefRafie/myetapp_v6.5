<fieldset>
  <legend><strong>Carian</strong></legend>
  <table width="100%">
    <tr>
      <td width="29%" align="right" scope="row">No Akta</td>
      <td width="1%" scope="row">:</td>
      <td width="70%">
        <input name="txtNoAkta" type="text" id="txtNoAkta" value="$txtNoAkta" />
      </td>
    </tr>
    <tr>
      <td align="right" scope="row">Nama Akta</td>
      <td scope="row">:</td>
      <td>
        <input name="txtNamaAkta" type="text" id="txtNamaAkta" value="$txtNamaAkta" />
      </td>
    </tr>
    <tr>
      <td align="right" scope="row">Tarikh Kuatkuasa</td>
      <td scope="row">:</td>
      <td>
        <input name="txdTarikhKuatkuasa" type="text" id="txdTarikhKuatkuasa" value="$txdTarikhKuatkuasa" size="10" />
        <a href="javascript:displayDatePicker('txdTarikhKuatkuasa',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>      
      </td>
    </tr>
    <tr>
      <td align="right" scope="row">&nbsp;</td>
      <td scope="row">&nbsp;</td>
      <td>
        <input type="submit" name="cmdCari" id="cmdCari" value="Cari" onclick="searchAkta()" />
        <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosong()" />
      </td>
    </tr>
  </table>
</fieldset>
<fieldset>
  <legend><strong>Senarai Akta</strong></legend>
  
  #parse("app/utils/record_paging.jsp") 
  <table width="100%" cellspacing="0" cellpadding="2">
     
    <tr class="table_header">
      <td width="6%" scope="row"><strong>No</strong></td>
      <td width="15%"><strong>No Akta</strong></td>
      <td width="49%"><strong>Nama Akta</strong></td>
      <td width="17%"><strong>Tarikh Kuatkuasa</strong></td>
      <td width="13%">&nbsp;</td>
    </tr>
#set ($fail = '')

#foreach ($fail in $SenaraiFail)
	#if ($fail.No == '') 
    	#set ($row = 'row1')
    #elseif ($fail.No % 2 != 0)
	    #set ($row = 'row1')
    #else 
	    #set ($row = 'row2')
    #end
    	<tr>
      <td height="20" class="$row">
      $fail.No
      </td>
      <td class="$row">
    #if ($fail.No != '') 
      <a href="javascript:viewAkta('$fail.IDAkta')" style="color:#0000FF">$fail.NoAkta</a>
    #else
      <div align="left">$fail.NoAkta</div>
    #end
    </td>
      <td class="$row">$fail.NamaAkta</td>
      <td class="$row">$fail.TarikhKuatkuasa</td>
      <td class="$row">
      <a href = "javascript:viewAktaBlob('$fail.IDAkta')">
      <img border="0" src="../img/pdf-small.png" /></a></td>
    </tr>
#end
  </table>
</fieldset>
<input type="hidden" id="Akta_IDAkta" name="Akta_IDAkta" />  

<script>

function viewAktaBlob(id) {
    var url = "../servlet/ekptg.view.pdt.DisplayBlob2?type=akta&id="+id;
    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function paparAkta(idAkta) {
    var url = "../y/ekptg.view.pdt.FrmViewAkta2?idAkta="+idAkta;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function viewAkta(Akta_IDAkta) {
	
    document.Fekptg_view_pdt_readonly_FrmViewAktaReadonly.Akta_IDAkta.value = Akta_IDAkta;
    //document.Fekptg_view_pdt_FrmViewAkta.action.value = "view";
    //document.Fekptg_view_pdt_FrmViewAkta.submit();
	doAjaxCallFekptg_view_pdt_readonly_FrmViewAktaReadonly("","action=view");
}
function doChangeTab(TabID) {
    //document.Fekptg_view_pdt_FrmViewAkta.action.value = '$action';
    document.Fekptg_view_pdt_readonly_FrmViewAktaReadonly.selectedTab.value = TabID;
    //document.Fekptg_view_pdt_FrmViewAkta.submit();
    //doAjaxCallFekptg_view_pdt_FrmViewAkta("","action=$action");
    doAjaxCallFekptg_view_pdt_readonly_FrmViewAktaReadonly("","action=view");
}
function paparAkta(idAkta) {
    var url = "../x/${securityToken}/ekptg.view.pdt.FrmViewAkta2?idAkta="+idAkta;
    var hWnd = window.open(url,'Cetak','width=900,height=700, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
</script>