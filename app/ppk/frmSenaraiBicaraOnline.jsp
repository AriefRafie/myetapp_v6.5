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
	<legend><strong>Senarai Fail Bicara</strong></legend>
	#parse("app/utils/record_paging.jsp")
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
			<tr class="table_header">
      			<td scope="row"><b>Bil</b></td>
      			<td><b>No Fail</b></td>
      			<td><b>Nama Simati</b></td>
      			<td><b>Tarikh Mohon</b></td>
      			<td><b>Status Fail</b></td>
    		</tr>
    	#if($list !=0)
    	  #set ($list = "")
          #set ($counter = 0)
           #foreach($list in $listNotis)
                     #set( $counter = $velocityCount - 1 )
          	#if ($list.bil == '')
                #set( $row = "row1" )
            #elseif (($list.bil % 2) != 0)
                #set( $row = "row1" )
            #else 
                #set( $row = "row2" )
            #end
         		
         	<tr>
                <td class="$row">
                    #set ($cnt = ($page - 1) * $itemsPerPage + $counter )
                    $list.bil
                </td>  
        		<td class="$row"><a href="javascript:papar('$list.id_Permohonan','$list.id_simati','$list.id_status','$list.no_Fail')"><font color="blue">$list.no_Fail</font></a></td> 
        		<td class="$row">$list.namasimati.toUpperCase()</td>
            	<td class="$row">$list.tarikhmohon</td>
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
	
	<input type="hidden" name="id_permohonan"/>
    <input type="hidden" name="id_simati" />
    <input type="hidden" name="questioner" value="0"/>
    <input type="hidden" name="id_status" />  
    <input type="hidden" name="nofail" /> 
    
	


<script>

function doChanges() {
	doAjaxCall${formName}("doChanges");
}

function cancel() 
{
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txtNoFail.focus();
}

function search_data()
{
	document.${formName}.command.value = "cari";
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmBicaraOnline";
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

function papar(idpermohonan,id2,id_status,nofail) {
			document.${formName}.id_permohonan.value = idpermohonan;
			document.${formName}.id_simati.value = id2;
			document.${formName}.id_status.value = id_status;
			document.${formName}.nofail.value = nofail;
			document.${formName}.questioner.value = "0";
			document.${formName}.action = "?_portal_module=ekptg.view.ppk.FrmBicaraOnline";
			document.${formName}.command.value = "papar_bicara";
			document.${formName}.submit();
		
		}
</script>