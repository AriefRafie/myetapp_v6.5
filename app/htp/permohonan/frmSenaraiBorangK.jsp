<table width="100%" border="0" cellspacing="2" cellpadding="2">
	
	<tr>
   		<td>&nbsp;</td>
	</tr>
	<tr>
    	<td>
			<fieldset>
			<legend><strong>CARIAN</strong></legend>
				<table width="100%" border="0">
					<tr>
				    	<td width="29%"><div align="right">No. Fail</div></td>
				    	<td width="1%">:</td>
				    	<td width="70%"><input type="text" name="noFailPPT" value="$!noFailPPT" size="40"></td>
				  	</tr>
					<tr>
				    	<td width="29%"><div align="right">No. Lot/PT</div></td>
				    	<td width="1%">:</td>
				    	<td width="70%"><input type="text" name="nolot" value="$!noLot" size="40"></td>
				  	</tr>
					<tr>
					    <td width="29%">&nbsp;</td>
					    <td width="1%">&nbsp;</td>
					    <td width="70%">
					    	<input class="stylobutton100"  name="cmdCari" value="Cari" type="button" onclick="javascript:cariFailPPT()" />
					      	<input class="stylobutton100"  name="cmdKosongkan" value="Kosongkan" type="button" onclick="javascript:cancel()" />
			      		</td>
			  		</tr>
				</table>
			</fieldset>
		</td>
	<tr/>
	
	<tr>
		<td>			
			<fieldset>
			<legend><strong>SENARAI HAKMILIK</strong></legend>
			#parse("app/utils/record_paging.jsp")
			<table width="100%">
				<tr class="table_header"> 
			        <td width="2%"><b>Bil.</b></td>
			        <td width="28%"><b>No. Fail</b></td>
			        <td width="20%"><b>Tarikh Borang K</b></td>
			        <!--  <td width="17%"><b>No. Warta</b></td>   -->        
			        <td width="20%"><b>No. Hakmilik</b></td>
			        <td width="15%"><b>No. Lot/PT</b></td>
			        <td width="15%"><b>Tarikh Terima Borang K</b></td>
			       	<!-- <td width="7%">&nbsp;</td> -->
			    </tr>   
			    
			#set ($list = "")
		    #set ( $cnt = 0 )	
			#if ($SenaraiFail.size() > 0)
		    	#foreach ($senarai in $SenaraiFail)
		    		#set ( $cnt = $cnt + 1 )
		       		#set( $i = $velocityCount )
				    #if ( ($i % 2) != 1 )
				    	#set( $row = "row2" )
				    #else
				    	#set( $row = "row1" )
				    #end
			    <tr>
			    	<td class="$row">$cnt</td>
			    	<td class="$row">$!senarai.noFail</td>
			    	<td class="$row">$!senarai.tarikhBorangK</td>
			    	<!--  <td class="$row">$!senarai.noWarta</td> -->
			    	<td class="$row">$!senarai.noHakmilik</td>
			    	<td class="$row">$!senarai.noLot</td>
			    	<td class="$row">$!senarai.tarikhTerimaBorangK</td>
			    	<!-- <td class="$row">$!senarai.tarikhDaftar
			    		##if($!senarai.tarikhDaftar=="0")
			    		---
			    		<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanBorangK($senarai.idHakmilik)"> 
			    		<input type="button" name="cmdSimpan" id="cmdSimpan" value="Daftar" onclick="simpanBorangK($senarai.idHakmilik)"> 
			    		--
			    		##else
			    			Didaftar pada $!senarai.tarikhDaftar
			    		##end
			    	</td>-->
			    </tr>	    
		    	#end
		    	
		  	#else
		      	<tr>
		        	<td ><font color="#FF0000"></font></td>
		        	<td colspan="5" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
		      	</tr>
		    #end			
			</fieldset>
		</td>
	<tr/>

</table>
<input type="hidden" name="idpermohonan" value="$idpermohonan">
<input type="hidden" name="idfail" value="$idfail">
<input type="hidden" name="detailMode" value="">

<script>
	function cancel() {
		document.${formName}.reset();
		//doAjaxCall${formName}("","mode=cancel");
	}
	
	function cariFailPPT(){
		doAjaxCall${formName}("searchBorangK");
	}

function simpanBorangK_Lama(idHakmilikPPT){	
	//doAjaxCall${formName}("daftarBorangK","idHakmilikPPT="+idHakmilikPPT);

}

function simpanBorangK(idHakmilikPPT){
	document.${formName}.command.value = 'daftarBorangK';
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmTerimaPohon1&idHakmilikPPT="+idHakmilikPPT;
	document.${formName}.submit();
	
}


</script>