<br/>

	<fieldset><legend><b>Carian</b></legend>
		
		<table width="100%" align="center" border="0">
			<tr>
      			<td scope="row" align="right">No Fail : </td>
      			<td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" value="" size="50" maxlength="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
      			<td scope="row" align="right">Nama Pemohon : </td>
      			<td><input name="txtPemohon" id="txtPemohon" type="text" value="" size="50" maxlength="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
   			</tr>
   			<tr>
      			<td scope="row" align="right">Nama Simati : </td>
      			<td><input name="txtSimati" id="txtSimati" type="text" value="" size="50" maxlength="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
      			<td scope="row" align="right">No.KP Simati : </td>
      			<td ><select name="jenisIc" class="autoselect">
      				<option value="0">Sila Pilih</option>
      				<option value="1">No. KP Baru</option>
      				<option value="2">No. KP Lama</option>
      				<option value="3">No. KP Lain</option>
      			</select>&nbsp;&nbsp;<input name="txtIcSimati" id="txtIcSimati" type="text" value="" size="20" maxlength="14" style="text-transform:uppercase;" /></td>
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
	<legend><strong>Senarai Fail Pusaka Kecil</strong></legend>
	#parse("app/utils/record_paging.jsp")
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
			<tr class="table_header">
      			<td scope="row"><b>Bil</b></td>
      			<td><b>No Fail</b></td>
      			<td><b>Nama Simati</b></td>
      			<td><b>Tarikh Mohon</b></td>
      			<td><b>Status Fail</b></td>
    		</tr>
    		
    	#if($list_size!=0)
           #foreach($list in $listRayuan)
           #set($id_status=$list.id_status)
           #set($id_permohonan=$list.id_permohonan)
           #set($flag=$list.flag)
                   	 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
         		
         	<tr>
        		<td class="$row">$list.bil</td>
        		<td class="$row"><a href="javascript:semakRayuan('$id_permohonan','$id_status','$flag')"><font color="blue">$list.no_fail</font></a></td> 
        		<td class="$row">$list.nama_simati.toUpperCase()</td>
            	<td class="$row">$list.tarikh_mohon</td>
            	<td class="$row">$list.keterangan</td>
        	</tr>	
           #end
        #else   	
    		<tr>
    			<td colspan="5">Tiada Rekod</td>
    		</tr>
    	#end
    		
		</table>
	</fieldset>	
	
<input type="hidden" name="flag" value="$flag"/>
<input type="hidden" name="id_status" value="$id_status"/>
<!-- <input type="hidden" name="command" /> -->
<input type="hidden" name="id_fail" />
<input type="hidden" name="id_permohonan" value="$id_permohonan"/>
<input type="hidden" name="id_simati" />
	


<script>
function cancel() 
{
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txtNoFail.focus();
}
function search_data()
{
	document.${formName}.command.value = "cari";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}
function semakRayuan(id_permohonan,id_status,flag) 
{
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.flag.value = flag;
	document.${formName}.command.value = "semakRayuan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmRynPermohonanRayuan";
	document.${formName}.submit();
}

</script>