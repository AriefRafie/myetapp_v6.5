<table width="100%"  border="0" cellpadding="0" cellspacing="0">
	<tr>
   		<td>&nbsp;</td>
	</tr>

	<tr>
    	<td>
<!-- <tr>
    <td width="8" nowrap></td>
	<td> -->
		<fieldset>
		<legend>Carian</legend>
		<table border="0" cellpadding="2" cellspacing="2">
		            <tr>
		             <td >Negeri</td>
		             <td>             
			     $!selectNegeri
		             </td>
		    </tr>
		    <tr>
		         <td>Bahagian</td>
		         <td>        
			 $!selectSeksyen
		         </td>
		    </tr>
		</table>
		</fieldset>
		
<!-- </table>
 -->
<!-- Senarai -->

#if ($lists.size() > 0)
		<tr>
    	<td>
    		<fieldset>
		<legend></legend>
	<table width="100%" cellpadding="1" cellspacing="0" border="0">
		<tr>
    		<td colspan=7>
    		#parse("app/utils/record_paging.jsp")
			</td>
		<tr/>    	
		<tr class="table_row">
			<td></td>
				<!--<td>Nama Pejabat</td>-->
			<td>Negeri</td>
			<td>
				<a href="javascript:doAjaxCall${formname}('doChanges','orderBy=daerah')">Daerah</a>
			</td>
			<td>Kod Unit</td>
			<td>Alamat</td>
			<td></td>
			<td></td>
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
			<td>
			#set ($cnt = ($page - 1) * $itemsPerPage + $counter )
			$cnt
			</td>
			##<td>$list.nama_pejabat</td>
			<td>$list.negeri</td>
			<td>$list.daerah</td>
			<td>$list.seksyen</td>
			<td>
				<textarea class="disabled" readonly name="alamat" cols="55" rows="5">$list.alamat</textarea>
			</td>
			<td align="right">
				<a href="javascript:doAjaxCall${formName}('edit','id=$list.id_pejabatjkptg')"><img src="../img/edit.gif" border="0"></a>
				<a href="javascript:if(confirm('Data akan dipadam.Adakah Anda Pasti?')){ doAjaxCall${formName}('delete','id=$list.id_pejabatjkptg') } "><img src="../img/delete.gif" border="0"></a>
				<a href="javascript:doAjaxCall${formName}('view-daerahjagaan','id=$list.id_pejabatjkptg')"><img src="../img/filefolder.gif" border="0"></a>
			</td>
		</tr>
	#end
		<input type=hidden name=page value=$page>
	</table>
		</td>
	<tr/>
#else

<div class="info">Tiada Rekod</div>
#end
	  <!-- End Senarai -->  
	 <p></p>
	<p>
	</p>
		
	<tr>
   		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>
	  <input class="stylobutton"  type="button" value="Tambah Unit" onclick="javascript:doAjaxCall${formName}('addNewPejabat')" />
		</td>
	<tr/>

</table>
	
<script>
	
	function submitRegister(s) {
	  	    
		if ( document.${formName}.id_seksyen.value == "" ) { 
			document.getElementById('error_box').style.display = '';
		    document.getElementById('error_box_text').innerHTML = "Sila pilih Bahagian";
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
	
	function doFilter2() {
		doAjaxCall${formName}("addNewPejabat","action=filterDaerah");
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
	
	function doAdd() {
		if (document.${formName}.Form_kod_jkptg.value == "") {
			alert("Sila masukkan Kod JKPTG");
			document.${formName}.Form_kod_jkptg.focus();
			return;
		
		}
		doAjaxCall${formname}('doAddNew');
		
	}

</script>
