<br/>

#foreach($list in $listNotis)
 #set($id_permohonan=$list.id_Permohonan)
#end
#set ($commands = $commands)

<input type="hidden" name="idFail" />
<!-- <input type="text" name="commands" value="$commands"/>  -->
<input type="hidden" name="id_permohonan" value="$id_permohonan"/>
<input type="hidden" name="idSimati" />
<input type="hidden" name="commands" />


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
    		<!-- 
    		<tr>
      			<td scope="row" align="right">Status Fail : </td>
      			<td ><select name="statusFail" class="autoselect" >
      			    <option value="0">Sila Pilih</option>
      				<option value="1">Batal</option> 
      				<option value="2">Notis Perbicaraan</option>
      				<option value="3">Permohonan Diteruskan</option>
      				</select></td>
    		</tr>
    		 -->
    		<tr>
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
		#set ($pagingTitle = "Jumlah Carian")
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
           #foreach($list in $listNotis)
           #set($id_status=$list.id_status)
           #set($id_permohonan=$list.id_Permohonan)
                   	 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
         		
         	<tr>
        		<td class="$row">$list.bil</td>
        		#if($list.id_status == "53" || $list.id_status == "151")
        		<td class="$row"><a href="javascript:semakNoData('$id_permohonan','$id_status')"><font color="blue">$list.no_Fail</font></a></td> 
        		#elseif($list.id_status == "44" || $list.id_status == "173" || $list.id_status == "175" || $list.id_status == "177" || $list.id_status == "166")
        		<td class="$row"><a href="javascript:tukarNotis('$id_permohonan','$id_status')"><font color="blue">$list.no_Fail</font></a></td> 
        		#elseif($list.id_status == "18")
        		<td class="$row"><a href="javascript:semakWithData('$id_permohonan','$id_status')"><font color="blue">$list.no_Fail</font></a></td> 
        		#end
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
<input type="hidden" name="id_status" value="$id_status"/>


<script>
function cancel() {
document.${formName}.reset();
document.${formName}.txtNoFail.value = "";
document.${formName}.txtNoFail.focus();
}
function search_data(){
/*	if (document.${formName}.txtNoFail.value == "" && document.${formName}.txtPemohon.value == "" && document.${formName}.txtSimati.value == "" && document.${formName}.txtIcSimati.value == ""){
		alert("Sila masukkan maklumat yang ingin dicari.");
	}
	else {
*/	document.${formName}.command.value = "CariSenaraiNotisFailLengkap";
	document.${formName}.action = "";
	document.${formName}.submit();
//	}
}
function semakWithData(id_permohonan,id_status) {
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semakWithData";
	document.${formName}.action = "";
	document.${formName}.submit();
}
function semakNoData(id_permohonan,id_status) {
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_permohonan.value = id_permohonan;
	//document.${formName}.command.value = "semakNoData";
	//document.${formName}.commando.value = "";
	document.${formName}.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiNotis&command=semakNoData";
	document.${formName}.submit();
}
function tukarNotis(id_permohonan,id_status) {
	document.${formName}.id_status.value = id_status;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "tukarNotis";
	document.${formName}.action = "";
	document.${formName}.submit();
}
</script>