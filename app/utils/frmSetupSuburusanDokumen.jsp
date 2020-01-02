<!--frmSetupSuburusanDokumen.jsp-->
<table border="0" cellpadding="0" cellspacing="0">
	<tr>
    	<td width="8" nowrap></td>
    	<td>
			<fieldset>
			<legend>Carian</legend>
				<table border="0" cellpadding="2" cellspacing="2">
		            <tr>
			             <td>Urusan</td>
			             <td>$selectUrusan</td>
		    		</tr>
				    <tr>
				         <td>Sub Urusan</td>
				         <td>$selectSuburusan</td>
				    </tr>

				</table>
			</fieldset>
		</td>
	</tr>		
</table>

<!-- Senarai -->
#parse("app/utils/record_paging.jsp")
             
<table width="100%" cellpadding="1" border="0">
	<tr class="table_row">
	<td></td>
	<td>Dokumen</td>
	<td>Peringkat</td>
	<td>
		<a href="javascript:doAjaxCall${formname}('doChanges','orderBy=daerah')">
		Template</a>
	</td>
	<td>Aturan</td>
	<td></td>
	<td></td>
</tr>
<!-- Table Content -->
#foreach ( $list in $SenaraiFail)
	#set( $counter = $velocityCount )
	#if ( ($counter % 2) == 0 )
	    #set( $row = "row2" )
	#else
	    #set( $row = "row1" )
	#end
	<tr class="$row" onMouseOver="this.className='highlight'" onMouseOut="this.className='$row'">
		<td>
		##set ($cnt = ($page - 1) * $itemsPerPage + $counter )
		$!counter
		</td>
		<td>$list.keterangan</td>
		<td>$list.peringkat ( $list.dokumen )</td>
		<td>$list.template</td>
		<td>$list.aturan
			<!--<textarea class="disabled" readonly name="alamat" cols="55" rows="5">$list.alamat</textarea>-->
		</td>
		<td align="right">
			<a href="javascript:doAjaxCall${formName}('view-daerahjagaan','id=$list.idsuburusanstatus')"><img src="../img/filefolder.gif" border="0"></a>
			<a href="javascript:doAjaxCall${formName}('edit','id=$list.iddokumenurl')"><img src="../img/edit.gif" border="0"></a>
			<a href="javascript:if(confirm('Data akan dipadam.Adakah Anda Pasti?')){ doAjaxCall${formName}('delete','id=$list.iddokumenurl') } "><img src="../img/delete.gif" border="0"></a>
		</td>
	</tr>
	#end
<input type=hidden name=page_bak value=$page>	
</table>
<input class="stylobutton"  type="button" value="Tambah" onClick="javascript:doAjaxCall${formName}('addNewPejabat')">





<!-- End Senarai -->

<script>
function submitRegister(s) {
  	    
  if ( document.${formName}.id_seksyen.value == "" ) { 
	    document.getElementById('error_box').style.display = '';
	    document.getElementById('error_box_text').innerHTML = "Sila pilih Seksyen";
	    document.${formName}.id_seksyen.focus(); return; 
    } 
  
    if ( document.${formName}.id_negeri.value == "" ) { 
  	    document.getElementById('error_box').style.display = '';
  	    document.getElementById('error_box_text').innerHTML = "Sila pilih Negeri";
  	    document.${formName}.id_negeri.focus(); return; 
    }

    if ( document.${formName}.id_daerah.value == "" ) { 
  	    document.getElementById('error_box').style.display = '';
  	    document.getElementById('error_box_text').innerHTML = "Sila pilih Daerah";
  	    document.${formName}.id_daerah.focus(); return; 
    }
    if ( document.${formName}.id_pejabatjkptg.value == "" ) { 
      	    document.getElementById('error_box').style.display = '';
      	    document.getElementById('error_box_text').innerHTML = "Maklumat Pejabat tiada dalam Pangkalan Data.<br>Sila masukkan maklumat pejabat terlebih dahulu <a href=\"../c/1242196199970?_portal_module=ekptg.view.utils.FrmCodeSetup\">disini</a>";
      	    document.${formName}.id_daerah.focus(); return; 
    }
    

    document.${formName}.command.value = s;
    document.${formName}.action = "";
    document.${formName}.submit();
}   

function doChanges() {
	doAjaxCall${formName}("doChanges");
}

function doFilter() {
	doAjaxCall${formName}("edit","action=filterDaerah");
}


function showhide(layer_ref,displayType) {

if (displayType =="show") displayType = "display:inline";
else displayType = "display:none";

if (document.all) { //IS IE 4 or 5 (or 6 beta)
eval( "document.all." + layer_ref + ".style.display = "+displayType);
}
if (document.layers) { //IS NETSCAPE 4 or below
document.layers[layer_ref].display = displayType ;
}
if (document.getElementById &&!document.all) {
hza = document.getElementById(layer_ref);
hza.style.display = displayType;
}
} 

</script>

