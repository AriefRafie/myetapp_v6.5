<table border="0" cellpadding="0" cellspacing="0">
<tr>
    <td width="8" nowrap></td>
    <td>
<fieldset>
<legend>Carian</legend>
<table border="0" cellpadding="2" cellspacing="2">
	<tr>
	<td >Jenis Pejabat</td>
	<td>  
	<select id="Form_id_jenispejabat" name="Form_id_jenispejabat">
	$!selectPejabat
	</select>
	</td>
	</tr>
            <tr>
             <td >Negeri</td>
             <td>             
	     $!selectNegeri
             </td>
    	</tr>
       <tr>
           <td >Daerah</td>
           
           <td>             
		$selectDaerah
           </td>
    </tr>
    
        <tr>
             <td >Seksyen</td>
             <td>             
    	     $!selectSeksyen
             </td>
    </tr>
    
    
	<tr>
	<td></td>
	<td>
<input type="button" onclick="Carian()" value="Cari" class="stylobutton"/>
<input type="button" onclick="Kosongkan()" value="Kosongkan" class="stylobutton"/>
</td>
</tr>
</table>
</fieldset>
</table>

##if ($doCarian == "true")

<!-- Senarai -->
#if ($SenaraiFail.size() > 0)
#parse("app/utils/record_paging.jsp")
<table width="100%" cellpadding="1" cellspacing="0" border="0">
<tr class="table_header">
	<td>Bil</td>
	<td>Nama Pejabat</td>
	<td>Negeri</td>
	<td>Daerah</td>
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
<td>$list.nama_pejabat</td>
<td>$list.negeri</td>
<td>$list.daerah</td>
<td><a href="javascript:doAjaxCall${formName}('editPejabatUrusan','idPejabat=$list.id_pejabat')"><img src="../img/edit.gif" border="0"></a>
<a href="javascript:if(confirm('Data akan dipadam.Adakah Anda Pasti?')){ doAjaxCall${formName}('deletePejabat','idPejabat=$list.id_pejabat') } "><img src="../img/delete.gif" border="0"></a>
</td>
</tr>
#end
<input type=hidden name=page value=$page>
</table>
#else
<div class="info">Tiada Rekod</div>
#end


<p>
  <input class="stylobutton"  type="button" value="Tambah Pejabat" onclick="javascript:doAjaxCall${formName}('addNewPejabat')" />
</p>


##end
<script>
function doChanges() {
	doAjaxCall${formName}("doChanges");
}

function Carian() {
	doAjaxCall${formName}("Carian");
}
function Kosongkan() {
	doAjaxCall${formName}("reset");
}
function tambah() {
	if (document.${formname}.Form_id_jenispejabat.value == "") {
		alert('Sila masukkan jenis pejabat');
		document.${formname}.Form_id_jenispejabat.focus();
		return;
	}
	else if (document.${formname}.Form_id_negeri.value == "") {
		alert('Sila masukkan maklumat negeri');
		document.${formname}.Form_id_negeri.focus();
		return;
	}
	else if (document.${formname}.Form_id_daerah.value == "") {
		alert('Sila masukkan maklumat daerah');
		document.${formname}.Form_id_daerah.focus();
		return;
	}
	else if (document.${formname}.Form_id_seksyen.value == "") {
		alert('Sila masukkan maklumat seksyen');
		document.${formname}.Form_id_seksyen.focus();
		return;
	}
	else if (document.${formname}.Form_nama_pejabat.value == "") {
		alert('Sila masukkan maklumat pejabat');
		document.${formname}.Form_nama_pejabat.focus();
		return;
	}
	doAjaxCall${formname}('doAddNew')
}
</script>
 
