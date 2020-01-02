<br/>

	<fieldset><legend><b>Carian</b></legend>
		
		<table width="100%" align="center" border="0">
			
   			
    		<tr>
      			<td scope="row" align="right">No.KP : </td>
      			<td ><select name="jenisIc" class="autoselect">
      				<option value="0">Sila Pilih</option>
      				<option value="1">No. KP Baru</option>
      				<option value="2">No. KP Lama</option>
      				<option value="3">No. KP Lain</option>
      			</select>&nbsp;&nbsp;<input name="txtNoKp" id="txtNoKp" type="text" value="" size="20" maxlength="14" style="text-transform:uppercase;" /></td>
    		</tr>
    		
    		<tr>
      			<td scope="row" align="right">Nama : </td>
      			<td><input name="txtNama" id="txtNama" type="text" value="" size="50" maxlength="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
      			<td scope="row"></td>
      			<td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:search_data()">
        			<input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="javascript:cancel()"></td>
    		</tr>
    		
		</table>
		
	</fieldset>
	
<br/>

	<fieldset>
	<legend><strong>Senarai Penghutang</strong></legend>
	<table width="100%"  cellpadding="1" cellspacing="2" border="0">
			<tr >
			<td><input name="cmdTambah" id="cmdTambah" value="Tambah" type="button"  onclick="Tambah()"></td>
			</tr>
	#parse("app/utils/record_paging.jsp")
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
			<tr class="table_header">
      			<td scope="row" width=5%><b>Bil</b></td>
      			<td width=15%><b>No KP</b></td>
      			<td width=65%><b>Nama</b></td>
      			<td width=15%><b>Tindakan</b></td>
      			
    		</tr>
    		
    	#if($list_size!=0)
           #foreach($list in $listEHutang)
           
                   	 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
         		
         	<tr>
         	
        		<td class="$row">$list.bil</td>
        		<td class="$row"><a href="javascript:semakKeputusanRayuan('')"><font color="blue">$list.NO_KP</font></a></td> 
        	<!-- #if($list.id_status == "53") -->		
        	<!-- #elseif($list.id_status == "44")
        		<td class="$row"><a href="javascript:tukarNotis('$id_permohonan','$id_status')"><font color="blue">$list.no_fail</font></a></td> 
        		#else
        		<td class="$row"><a href="javascript:semakWithData('$id_permohonan','$id_status')"><font color="blue">$list.no_fail</font></a></td> 
        		#end -->	
        		<td class="$row">$list.NAMA_PENGHUTANG.toUpperCase()</td>
            	<td class="$row">xx</td>
        	</tr>	
           #end
        #else   	
    		<tr>
    			<td colspan="5">Tiada Rekod</td>
    		</tr>
    	#end
    		
		</table>
	</fieldset>	
	
<input type="hidden" name="id_status" value="$id_status"/>
<!-- <input type="hidden" name="command" /> -->
<input type="hidden" name="id_permohonan" value="$id_permohonan"/>
<input type="hidden" name="id_simati" />


<script>
function Tambah() {
	
	document.${formName}.command.value = "tambah";
	//document.f1.idFlag.value = "1";
	//document.f1.flagno.value = "0";
	//document.${formName}.method="post";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmHtgSemakPenghutang";
	document.${formName}.submit();
}

function cancel() 
{
	document.${formName}.reset();
	document.${formName}.txtNama.value = "";
	document.${formName}.txtNama.focus();
}
function search_data()
{
	document.${formName}.command.value = "cari";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmHtgSemakPenghutang";
	document.${formName}.submit();
}
function semakKeputusanRayuan(id_permohonan,id_status) 
{
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semakKeputusanRayuan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynSemakPenerimaan";
	document.${formName}.submit();
}

</script>