<style type="text/css">
	<!--
	.pautanms {color: #0000FF}
	-->
</style> 
<table width="100%" border="0" cellspacing="2" cellpadding="2">

	<tr>
    	<td> 
			<br/>
			  #parse('app/htp/frmPajakanKecilPaging.jsp')
			<br/>
			#parse ("app/htp/frmJRPInfo.jsp")			
			  
			  #set ($pagemode = $pageMode)	
			  #set ($cbFlag = '')	
			  #if($pagemode == "2" )
			  #set ($cbFlag = 'disabled')	
			  #end 

		<td>		
	</tr>
	
	<tr>
    	<td> 	
    		
			
			<p>
			   	<input type="hidden" name="id_kemaskini" />
			   	<input type="hidden" name="fail" />
			   	<input type="hidden" name="langkah" value="0" />
			    
			 </p>
			
	
		<td>		
	</tr>

	
		
</table>