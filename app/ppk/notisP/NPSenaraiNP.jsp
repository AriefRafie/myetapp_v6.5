

#if($NP_OPENCLOSE_CARIAN=="open")
<script>
	document.getElementById('div_CARIAN_NP').style.display = "";
	document.getElementById('span_LINK_CARIAN_NP').style.display = "none";	
</script>
#end
#if($NP_OPENCLOSE_CARIAN=="close")
<script>
	document.getElementById('div_CARIAN_NP').style.display = "none";
	document.getElementById('span_LINK_CARIAN_NP').style.display = "";
</script>
#end




#if($command!="showSenaraiNP" || $FLAG_NP_CARIAN=="Y" ||  $FLAG_NP_CARIAN=="N")
	#if($FLAG_NP_CARIAN=="Y")
	<script>
	if( $jquery('#div_CARIAN_NP').length )         // use this if you are using id to check
	{
		window.scrollTo(0, $jquery('#div_CARIAN_NP').offset().top);
	}
	</script>
	#else
	<script>
	if( $jquery('#div_SenaraiNP').length )         // use this if you are using id to check
	{
		window.scrollTo(0, $jquery('#div_SenaraiNP').offset().top);
	}
	</script>
	#end
#end


<fieldset id="div_rowBU">
<table width="100%" id="div_viewBU" >
<tr >
</tr>
</table>
<!-- ::::::::: $listNP -->
<table width="100%" align="center">
<tr>
<td>

	
	<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($listNP.size()>0)
    
	<script>
	var butoncetakCT =  document.getElementById('cmdCetakBU');
	if (typeof(butoncetakCT) != 'undefined' && butoncetakCT != null)
    {
    	butoncetakCT.style.display = "";
    }
	</script>
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		   #parse("app/ppk/BantuUnit/BUrecord_paging_BU.jsp")
		   </td>
		     
	</tr>
	#if($listNP.size()==1)
	<script>
	//$jquery("#showUserCount_HQ").html("("+$totalRecords+" USER FOUND)");
	</script>
	#else
	<script>
	//$jquery("#showUserCount_HQ").html("("+$totalRecords+" USERS FOUND)");
	</script>
	#end
	
	#end 
	<tr class="table_header" >
		   <td   align="center" valign="top" width="3%" >Bil.</td>
		   <td   align="left" valign="top" width="12%">No Fail</td>
		   <td   align="left" valign="top" width="15%">Nama Simati</td>
		   <td   align="center" valign="top" width="15%">Keputusan Permohonan</td>
		   <td   align="center" valign="top" width="10%">Tarikh Notis</td>
           
	</tr>

	#if($listNP.size()>0)
	
		#foreach($NP in $listNP)
		
		<tr id="div_rowBU$NP.ID_PERMOHONANBANTUUNIT" >
		
			
			   <td class="$NP.rowCss"  align="center" valign="top" >
			   		$NP.BIL
			   		
			   			
			   		<input type="hidden" name="ID_SIMATI" value="$NP.ID_SIMATI"/>
			   		<input type="hidden" name="SEKSYEN" value="$NP.SEKSYEN"/>
			   		<input type="hidden" name="id_status" value="$NP.ID_STATUS"/>
					<input type="hidden" name="id_permohonan" value="$NP.ID_PERMOHONAN"/> 
					
			   </td>
			    
			  	
			  <!--  $NP.ID_STATUS - $NP.NO_FAIL -->
			  
        		<td class="$NP.rowCss"><a href="javascript:papar('$NP.ID_STATUS','$NP.SEKSYEN','$NP.ID_PERMOHONAN','$NP.ID_SIMATI')"><font color="blue">$NP.NO_FAIL</font></a></td> 
			   
			   <td class="$NP.rowCss"  align="left" valign="top">	
                   	$NP.NAMA_SIMATI		
			   </td>
			   
			   <td class="$NP.rowCss"  align="center" valign="top">		   
			  	 	$NP.KETERANGAN
			   </td>
			   
			   <td class="$NP.rowCss"  align="center" valign="top">	
               		$NP.TARIKH_NOTIS 
			   </td>
			   
              
		</tr>
		
		#end
	#else
	<tr >
		   <td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
		     
	</tr>
	

    <script>
	var butoncetakCT =  document.getElementById('cmdCetakBU');
	if (typeof(butoncetakCT) != 'undefined' && butoncetakCT != null)
    {
    	butoncetakCT.style.display = "none";
    }
	
	
	
	
	
	</script>
	#end
	</table>


</td>
</tr>
</table>
</fieldset>
<br>
