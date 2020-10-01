<style type="text/css">
<!--
.pautan {color: #0033FF}
-->
</style>
<br/><br/>
	<fieldset><legend><b>Carian Fail</b></legend>
		
		<table width="100%" align="center" border="0">
			<tr>
      			<td scope="row" align="right">No. Fail : </td>
      			<td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" 
      			value="$!nofail" size="30" maxlength="50" 
      			style="text-transform:uppercase;" 
      			onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
      			<td scope="row"></td>
      			<td>
      			<input name="cmdSemak" id="cmdSemak" value="Semak" type="button"  onClick="javascript:doAjaxCall${formname}('doCarian')">
        		<input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset"></td>
    		</tr>
    		
		</table>
		
	</fieldset>

#if($listSemak_size!=0 && $listSemak_size!="")	
 
<br/>
#parse("app/utils/record_paging.jsp")

	<fieldset>
	<legend><strong>Senarai Fail</strong></legend>
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
			<tr class="table_header">
			<td width="3%"><b>Bil.</b></td>
			<td width="29%"><b>No. Fail</b></td>
			<td width="30%"><b>Nama Simati</b></td>
			<td width="30%"><b>Nama Pemohon</b></td>
			<td width="8%"><b>Jana</b></td>
			</tr>
			
		#foreach($list in $SenaraiFail)      
			#set ($idSimati = $list.ID_SIMATI)
			#set ($idPermohonan = $list.ID_PERMOHONAN)
        	#set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
         		
         	<tr>
         		<td class="$row">$i.</td>
            	<td class="$row">
            		<!-- <a href="#" onClick = "janaFaraid('$list.ID_SIMATI','$list.ID_PERMOHONAN')" class="pautan">$list.NO_FAIL</a></td> -->
        			$list.NO_FAIL</td>       			
        		<td class="$row"> $list.NAMA_SIMATI</td>
        		<td class="$row"> $list.NAMA_PEMOHON</td>			 
			 	<td class="$row" > 
			 	#if ($isMuslim == false)
			 		<input type="button" name="button2" id="button2" value="Akta" onClick = "janaAkta('$list.ID_PERMOHONAN','$list.ID_SIMATI')" /> 
			 	#end
			 	#if ($isMuslim == true)
				 	<input type="button" name="button2" id="button2" value="Faraid" onClick = "janaFaraid('$list.ID_SIMATI','$list.ID_PERMOHONAN')" /> 
			 	#end
			 	</td>
			 </tr>
       
       #end
			
		#if ($!SenaraiFail.isEmpty())
			 <tr>
		        <td></td>
            	<td colspan=4>Tiada Rekod</td>
			 </tr>
		
		#end	
		</table>
	</fieldset>

<br/>
#end

<script>
	function janaFaraid(id_simati,id_permohonan){
		  
		var url = "../x/${securityToken}/ekptg.faraid.FrmFaraid?id_simati="+id_simati+"&id_permohonan="+id_permohonan;
	    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
	      if ((document.window != null) && (!hWnd.opener))
	        hWnd.opener = document.window;
	      if (hWnd.focus != null) hWnd.focus();
	
	}
	
	function janaAkta(id,id_mati){
		var url = "../x/${securityToken}/ekptg.faraid.FrmAkta1958?id_simati="+id_mati+"&id_permohonan="+id;
		var hWnd = window.open(url,'printuser','width=500,height=300, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	
	}
</script>
