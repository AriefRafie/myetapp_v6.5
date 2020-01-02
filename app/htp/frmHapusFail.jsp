<!--<br/> -->
<form name="f1" method="post">
	<fieldset><legend><b>CARIAN FAIL</b></legend>
		
		<table width="100%" align="center" border="0">
			<tr>
      			<td scope="row" align="right">No Fail : </td>
      			<td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" value="$nofail" size="30" maxlength="50" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
      			<td scope="row"></td>
      			<td><input name="cmdSemak" id="cmdSemak" value="Semak" type="button" onclick="javascript:search_data()">
        			<input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="button" onClick="javascript:kosongkan()"></td>
    		</tr>
    		
		</table>
		
	</fieldset>

#if($listSemak_size!=0 && $listSemak_size!="")	
 
<br/>

	<fieldset>
	<legend><strong>STATUS SEMASA</strong></legend>
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
			<tr class="table_header">
				<td><b>Status</b></td>
			</tr>
			
         		<tr>
            	<td>$keterangan_status</td>
        		</tr>	
      
		</table>
	</fieldset>

<br/>

<table width="100%" border="0">
	<tr>
		<td><input type=button value="Hapus" onClick=doHapus('$!nofail')></td>
	</tr>	
</table>

#end

#if ($!carirekod == "tiada")
	<br/>

	<fieldset>
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
			<tr>
				<td>No fail : <b>$!nofail</b> &nbsp;tiada dalam pangkalan data.</td>
			</tr>
		</table>
	</fieldset>
#end
	
<input type="hidden" name="command" />	
<input type="hidden" name="id_status" value="$id_status"/>
<input type="hidden" name="keterangan" value="$keterangan_status"/>
<input type="hidden" name="id_fail" value="$id_fail"/>
<input type="hidden" name="id_permohonan" value="$id_permohonan"/>
<input type="hidden" name="id_suburusanstatusfail" value="$id_suburusanstatusfail"/>
<input type="hidden" name="no_fail" value="$nofail"/>
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
</form>

<script>
	
function kosongkan() {
	document.f1.action = "";
	document.f1.command.value = "kosongkan";
	document.f1.txtNoFail.focus();
	document.f1.submit();
}

function search_data(){
	document.f1.command.value = "Cari";
	document.f1.action = "";
	document.f1.submit();
}

function doHapus(no_fail){
	if(confirm('Fail:'+ no_fail +' \nakan dihapuskan.Adakah Anda Pasti?')){
		document.f1.command.value = "Hapus";
		document.f1.action = "";
		document.f1.submit();
	}
}
</script>