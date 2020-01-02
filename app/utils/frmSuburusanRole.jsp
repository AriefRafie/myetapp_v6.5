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
				<tr>
					<td >Role</td>
					<td>  					
						<select name="Form_name" onChange="javascript:doAjaxCall${formname}('pilihrole')">
		                    <option value="">- Sila pilih -</option>
		                	#foreach ( $role in $roleList )
			                    #if ( $role.getName() != "root" )
			                        #if ( $userRole ==  $role.getName() )
			                            #set ( $description = $role.getDescription() )
			                    		<option value="$role.getName()" selected>$role.getName()</option>
			                        #else
			                    		<option value="$role.getName()">$role.getName()</option>
			                        #end
			                    #end
		                	#end
		                </select>
	                </td>
				</tr>
			</table>
			</fieldset>
		</td>
	</tr>
</table>
<!-- Senarai -->

#if ($lists.size() > 0)
	
<table width="100%" border="0" cellpadding="2" cellspacing="0">
	<tr>
		<td  align="left">Total: $totalRecords</td>
	    <td  align="right">Show
	   		<select class="smallselect" name="itemsPerPage" onchange="javascript:doAjaxCall${formname}('doChanges','action=doChangeItemPerPage')">
	        	<option value="10" #if ($itemsPerPage == 10) selected #end>10</option>
	        	<option value="20" #if ($itemsPerPage == 20) selected #end>20</option>
	    		<option value="30" #if ($itemsPerPage == 30) selected #end>30</option>
	        	<option value="40" #if ($itemsPerPage == 40) selected #end>40</option>
	         	<option value="50" #if ($itemsPerPage == 50) selected #end>50</option>
	   		</select>
		</td>
	</tr>
</table>
	            
<table width="100%" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td nowrap>
			#if( $isFirstPage )
			    <input class="stylobutton"  type="button"  value="<< Previous" disabled>
			#else
			    <input class="stylobutton"  type="button"  value="<< Previous" onclick="javascript:doAjaxCall${formname}('record_listing','action=getPrevious')">
			#end
			#if( $isLastPage )
			    <input class="stylobutton"  type="button"  value="Next >>" disabled>
			#else
			    <input class="stylobutton"  type="button"  value="Next >>" onclick="javascript:doAjaxCall${formname}('record_listing','action=getNext')">
			#end
		</td>
		<td width="90%">
			&nbsp;
			#foreach ( $i in [1..$totalPages] )
				#if ($i == $page)
				<b>[$i]</b>
				#else
				<a href="javascript:doAjaxCall${formname}('doChanges','action=getPage&value=$i')">[$i]</a>
				#end
			#end
		</td>
		<td align="right" nowrap>
				Page $page of $totalPages
		</td>
	</tr>
</table>
	
<table width="100%" cellpadding="1" cellspacing="0" border="0">
	<tr class="table_row">
		<td width="5%"></td>
		<td width="45%">Nama Sub Urusan</td>
		<td width="40%">Role</td>
		<td width="10%"></td>
	</tr>
	<!-- Table Content -->
	#foreach ( $list in $lists )
		#set( $counter = $velocityCount )
		#if ( ($counter % 2) == 0 )
		    #set( $row = "row2" )
		#else
		    #set( $row = "row1" )
		#end
		<tr class="$row" onMouseOver="this.className='highlight'" onMouseOut="this.className='$row'">
			<td>#set ($cnt = ($page - 1) * $itemsPerPage + $counter ) $cnt </td>
			<td>$list.namasuburusan</td>
			<td>$list.kodurusan</td>
			<td align="right">
				<a href="javascript:doAjaxCall${formName}('edit','id=$list.idrolesuburusan')"><img src="../img/edit.gif" border="0"></a>
				<a href="javascript:if(confirm('Data akan dipadam.Adakah Anda Pasti?')){ doAjaxCall${formName}('delete','id=$list.idrolesuburusan') } "><img src="../img/delete.gif" border="0"></a>
			</td>
		</tr>
		#end
	<input type=hidden name=page value=$page>	
</table>

	<input class="stylobutton"  type="button" value="Tambah" onClick="javascript:doAjaxCall${formName}('tambah')">
#else
	<div class="info">Tiada Rekod</div>
#end

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

