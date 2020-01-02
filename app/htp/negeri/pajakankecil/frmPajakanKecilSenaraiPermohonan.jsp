<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
 <br/> 
##parse('app/htp/pajakankecil/utiliti/frmPajakanKecilPaging.jsp')

<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<!-- <tr>
   		<td>&nbsp;</td>
	</tr>  -->
	<tr>
    	<td> 
			<fieldset><legend>SENARAI PERMOHONAN PAJAKAN KECIL TANAH/BANGUNAN</legend>
   			<input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="carianFailDariPermohonan('$!nomborFail')">    		
			#if (!$!jenisAkses.equals('Readonly'))
				<input class="stylobutton100" type="button" name=add value="Tambah" onclick="tambahPermohonan2('$idFail')">
			#end
			  	<table width="100%" cellspacing="2" cellpadding="1" border="0">
				  	<tr class="table_header">
						<td width="3%"><b>BIL.</b></td>
						<td width="20%"><b>NO. FAIL</b></td>
						<!--<td width="17%"><b>NEGERI</b></td>-->
						<td width="54%"><b>NAMA FAIL</b></td>
						<td width="23%"><b>STATUS</b></td>
			  		</tr>			  
			  	#set ( $cnt = 0 )			
			  	#set ( $nomborFail = "" )			
			  	#foreach ( $senarai in $senaraiList )
			  	#set ( $cnt = $cnt + 1 )
			  	#set( $i = $velocityCount )
			    #if ( ($i % 2) == 0 )
			    	#set( $row = "row2" )
			   	#else
			    	#set( $row = "row1" )
			   	#end
			   	#set( $nomborFail = $senarai.no )
				  	<tr>
				  		<td class="$row">$cnt.</td>
				  		<td class="$row">
				    		<a href="javascript:kemaskiniPermohonan('$senarai.id')" class="style1">$senarai.no</a>	
				    	</td>
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
	<tr align="center">
   		<td>&nbsp;
   		</td>
	</tr>
</table>

<script>
function cancel() {
	document.cari.reset();
}

//min enable kemaskini 21/1/2017
function kemaskiniPermohonan(id) {
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.command.value = "pkpermohonankemaskini";
	document.${formName}.action = "";
	document.${formName}.submit();
}

function backMain() {
	document.${formName}.action = "";
	document.${formName}.submit();
}
</script>
