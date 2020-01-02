<table border="0" cellpadding="0" cellspacing="0">
<tr>
    <td width="8" nowrap></td>
    <td>
<fieldset>
<legend>Carian</legend>
<table border="0" cellpadding="2" cellspacing="2">
            <tr>
             <td >Sub Suburusan</td>
             <td>             
	     $!selectSubSuburusan
             </td>
    </tr>
</table>
</fieldset>
</table>

#if ($SenaraiFail.size() > 0)
#parse("app/utils/record_paging.jsp")
<table width="100%" cellpadding="1" cellspacing="0" border="0">
<tr class="table_header">
	<td>Bil</td>
	<td>Kod Sub Sub Suburusan</td>
	<td>Sub Sub Suburusan</td>
    <td>Sub Suburusan</td>
	<td></td>
</tr>
<!-- Table Content -->
#foreach ( $list in $SenaraiFail )
#set( $counter = $velocityCount )
#if ( ($counter % 2) == 0 )
    #set( $row = "row2" )
#else
    #set( $row = "row1" )
#end
#set ($cnt = ($page - 1) * $itemsPerPage + $counter )
<tr class="$row">
<td>
$cnt
</td>
<td>$list.kod_subsubsuburusan</td>
<td>$list.nama_subsubsuburusan</td>
<td>$list.nama_subsuburusan</td>
<td><a href="javascript:doAjaxCall${formName}('editSubSubSuburusan','idSubSubSuburusan=$list.id_subsubsuburusan')"><img src="../img/edit.gif" border="0"></a>
<a href="javascript:if(confirm('Data akan dipadam.Adakah Anda Pasti?')){ doAjaxCall${formName}('delete','idSubSubSuburusan=$list.id_subsubsuburusan') } "><img src="../img/delete.gif" border="0"></a>
</td>
</tr>
#end
<input type=hidden name=page value=$page>
</table>
#else
<div class="info">Tiada Rekod</div>
#end


<p>
    <input  type="button" value="Tambah Sub Sub Suburusan" onClick="javascript:doAjaxCall${formName}('addNewSubSubSuburusan')" />
</p>



<script>
function doChanges() {
	doAjaxCall${formName}("doChanges");
	
}

function tambah() {
	if (document.${formname}.Form_kod_subsubsuburusan.value == "") {
		alert('Sila masukkan kod sub sub suburusan');
		document.${formname}.Form_kod_subsubsuburusan.focus();
		return;
	}
	else if (document.${formname}.Form_nama_subsubsuburusan.value == "") {
		alert('Sila masukkan nama sub sub suburusan');
		document.${formname}.Form_nama_subsubsuburusan.focus();
		return;
	}
	doAjaxCall${formname}('doAddNew')
}
</script>