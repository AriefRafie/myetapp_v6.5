<table width="100%">
<tr>
	<td width="5"></td>
	<td align="left"> <strong>ENAKMEN</strong></td>
</tr>
</table>
<fieldset>
  <legend><strong>Carian</strong></legend>
  <table width="100%">
    <tr>
      <td width="29%" align="right" scope="row">No. Enakmen</td>
      <td width="1%" scope="row">:</td>
      <td width="70%">
        <input name="txtNoEnakmen" type="text" id="txtNoEnakmen" value="$txtNoEnakmen" />
      </td>
    </tr>
    <tr>
      <td align="right" scope="row">Nama Enakmen</td>
      <td scope="row">:</td>
      <td>
        <input name="txtNamaEnakmen" type="text" id="txtNamaEnakmen" value="$txtNamaEnakmen" />
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
      <td align="right" valign="top" scope="row"><i>Tag</i> Dokumen</td>
      <td scope="row" valign="top">:</td>
      <td>
      	<textarea name="tag_dokumen" cols="41" rows="3" onblur="this.value=this.value.toUpperCase()" >$!tag_Dokumen</textarea>
        <input name="id_tagdokumen" type="hidden" value="$id_tagdokumen"/>
      </td>
    </tr>
    
    <tr>
      <td align="right" scope="row">&nbsp;</td>
      <td scope="row">&nbsp;</td>
      <td>
        <input type="submit" name="cmdCari" id="cmdCari" value="Cari" onclick="searchEnakmen()" />
        <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosong()" />
      </td>
    </tr>
  </table>
</fieldset>
<fieldset>
  <legend><strong>Senarai Enakmen</strong></legend>
  <input type="button" value="Tambah" onclick="tambahEnakmen()"/>
  #parse("app/utils/record_paging.jsp") 
  <table width="100%">
     
    <tr class="table_header">
      <td width="3%" scope="row"><strong>Bil.</strong></td>
      <td width="20%"><strong>No. Enakmen</strong></td>
      <td width="60%"><strong>Nama Enakmen</strong></td>
      <td width="12%"><strong>Tarikh Kuatkuasa</strong></td>
      <td width="5%">&nbsp;</td>
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
      $fail.No.
      </td>
      <td class="$row">
    #if ($fail.No != '') 
      <a href="javascript:viewEnakmen('$fail.IDEnakmen')" style="color:#0000FF">$fail.NoEnakmen</a>
    #else
      <div align="left">$fail.NoEnakmen</div>
    #end
    </td>
      <td class="$row">$fail.NamaEnakmen</td>
      <td class="$row">$fail.TarikhKuatkuasa</td>
      <td class="$row">
      <a href = "javascript:viewEnakmenBlob('$fail.IDEnakmen')">
      <img border="0" src="../img/pdf-small.png" /></a>
      <a alt="Hapus Enakmen" href = "javascript:deleteEnakmen('$fail.IDEnakmen')">
      &nbsp;&nbsp;<img border="0" src="../img/delete.gif" /></a> 
      </td>
    </tr>
#end
  </table>
</fieldset>
<input type="hidden" id="Enakmen_IDEnakmen" name="Enakmen_IDEnakmen" />  

<script type="text/javascript" ></script>
<script>
function tambahEnakmen(){
	//alert("tambah");
	//document.Fekptg_view_pdt_FrmViewEnakmen.action.value="tambah";
	//document.Fekptg_view_pdt_FrmViewEnakmen.submit();
	//doAjaxCallFekptg_view_pdt_FrmViewEnakmen("","action=tambah");
	 doAjaxCall${formName}("","action=tambah");
}

function viewEnakmen(IDEnakmen) {
	document.${formName}.Enakmen_IDEnakmen.value = IDEnakmen;
	document.${formName}.action = "?_portal_module=ekptg.view.pdt.readonly.FrmViewEnakmenReadOnly&action=view";
	document.${formName}.submit();
	
}

function paparEnakmen(idEnakmen) {
    var url = "../x/${securityToken}/ekptg.view.pdt.readonly.FrmViewEnakmenReadOnly?idEnakmen="+idEnakmen;
    var hWnd = window.open(url,'Cetak','width=900,height=700, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
</script>

