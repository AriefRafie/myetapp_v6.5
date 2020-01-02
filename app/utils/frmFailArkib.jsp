<table border="0" cellpadding="0" cellspacing="0">
<tr>
    <td width="8" nowrap></td>
    <td>
<fieldset>
<legend>Carian</legend>
<table border="0" cellpadding="2" cellspacing="2">
            <tr>
             <td >Urusan</td>
             <td>             
	     $!selectUrusan
             </td>
    </tr>
    <tr>
             <td>Suburusan</td>
             <td>             
	     $!selectSuburusan
             </td>
    </tr>
    <tr>
             <td >Sub Suburusan</td>
             <td>             
	     $!selectSubSuburusan
             </td>
    </tr>
      
            <tr>
             <td >Sub Sub Suburusan</td>
             <td>             
	     $!selectSubSubSuburusan
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
	<td>No Fail Arkib</td>
    <td width="20%">URUSAN</td>
    <td width="20%">SUB URUSAN</td>
    <td width="20%">SUB SUBURUSAN</td>
    <td width="20%">SUB SUB SUBURUSAN</td>
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
<td>$list.NO_FAIL_ARKIB</td>
<td class="$row">$list.NAMA_URUSAN</td>
<td class="$row">$list.NAMA_SUBURUSAN</td>
<td class="$row">$list.NAMA_SUBSUBURUSAN</td>
<td class="$row">$list.NAMA_SUBSUBSUBURUSAN</td>
<td><a href="javascript:doAjaxCall${formName}('editFailArkib','idFailArkib=$list.ID_FAILARKIB')"><img src="../img/edit.gif" border="0"></a>
<a href="javascript:if(confirm('Data akan dipadam.Adakah Anda Pasti?')){ doAjaxCall${formName}('delete','idFailArkib=$list.ID_FAILARKIB') } "><img src="../img/delete.gif" border="0"></a>
</td>
</tr>
#end
<input type=hidden name=page value=$page>
</table>
#else
<div class="info">Tiada Rekod</div>
#end


<p>
    <input  type="button" value="Tambah Fail Arkib" onClick="javascript:doAjaxCall${formName}('addNewFailArkib')" />
</p>



<script>
function doChangesUrusan() {
	doAjaxCall${formName}("doChangesUrusan");
	
}
function doChangesSuburusan() {
	doAjaxCall${formName}("doChangesSuburusan");
	
}
function doChangesSubSuburusan() {
	doAjaxCall${formName}("doChangesSubSuburusan");
	
}
function doChangesSubSubSuburusan() {
	doAjaxCall${formName}("doChangesSubSubSuburusan");
	
}
function doChangesUrusanAdd() {
	doAjaxCall${formName}("doChangesUrusanAdd");
	
}
function doChangesSuburusanAdd() {
	doAjaxCall${formName}("doChangesSuburusanAdd");
	
}
function doChangesSubSuburusanAdd() {
	doAjaxCall${formName}("doChangesSubSuburusanAdd");
	
}
function doChangesSubSubSuburusanAdd() {
	doAjaxCall${formName}("doChangesSubSubSuburusanAdd");
	
}



function tambah() {
	if (document.${formname}.Form_id_urusan_add.value == "") {
		alert('Sila masukkan Urusan');
		document.${formname}.Form_id_urusan_add.focus();
		return;
	}
	else if (document.${formname}.Form_id_suburusan_add.value == "") {
		alert('Sila masukkan Suburusan');
		document.${formname}.Form_id_suburusan_add.focus();
		return;
	}
	else if (document.${formname}.Form_id_subsuburusan_add.value == "") {
		alert('Sila masukkan Sub Suburusan');
		document.${formname}.Form_id_subsuburusan_add.focus();
		return;
	}
	else if (document.${formname}.Form_id_subsubsuburusan_add.value == "") {
		alert('Sila masukkan Sub Sub Suburusan');
		document.${formname}.Form_id_subsubsuburusan_add.focus();
		return;
	}
	doAjaxCall${formname}('doAddNew')
}
</script>