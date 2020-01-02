<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">


	<tr>
    	<td> 
			<fieldset>
				<legend>SENARAI PERMOHONAN PAJAKAN KECIL TANAH/BANGUNAN ONLINE</legend>
				<input class="stylobutton" type="button" name=add value="Tambah" onclick="tambahPermohonan2('$idFail')" style="display:none">
			  	<table width="100%" cellspacing="2" cellpadding="1" border="0">
				  	<tr class="table_header">
						<td width="3%"><b>BIL.</b></td>
						<td width="20%"><b>NO FAIL</b></td>
						<!--<td width="17%"><b>NEGERI</b></td>-->
						<td width="54%"><b>NAMA FAIL</b></td>
						<td width="23%"><b>STATUS</b></td>
			  		</tr>			  
			  	#set ( $cnt = 0 )			
			  	#foreach ( $senarai in $senaraiList )
			  	#set ( $cnt = $cnt + 1 )
			  	#set( $i = $velocityCount )
			    #if ( ($i % 2) == 0 )
			    	#set( $row = "row2" )
			   	#else
			    	#set( $row = "row1" )
			   	#end
				  	<tr>
				  		<td class="$row">$cnt.</td>
				  		<td class="$row">
				    		<a href="javascript:kemaskiniPermohonan('$senarai.id')" class="style1">$senarai.noP</a></td>
				  		<!--<td class="$row">$senarai.negeri</td> -->
				  		<td class="$row">$senarai.tajuk</td>
				  		<td class="$row">$senarai.keterangan</td>
				  	</tr>
			  	#end
				</table>
			<input type="hidden" name="id_kemaskini">
			</fieldset>

		</td>
	</tr>
</table>

<script>
function cancel() {
	document.cari.reset();
}

/* komen pada 11/06/2010
function kemaskiniPermohonan(id) {
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.command.value = "pkpermohonankemaskini";
	document.${formName}.action = "";
	document.${formName}.submit();
} */

function backMain() {
	document.${formName}.action = "";
	document.${formName}.submit();
}
</script>
