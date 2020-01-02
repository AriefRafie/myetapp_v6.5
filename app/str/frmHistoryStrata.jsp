<fieldset id="headerppk" name="headerppk">
	<legend>
		<strong>Maklumat </strong>
	</legend>
<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	
	<tr class="table_header" >
		   <td   align="center" valign="top">Bil.</td>
		   <td   align="left" valign="top">Jenis Hakmilik</td>  
           <td   align="left" valign="top">No. Hakmilik</td>  
           <td   align="left" valign="top">No. Lot</td>  
	</tr>
    
    #if($listHistoryStata.size()>0)
	
	
		#foreach($gh in $listHistoryStata)
		<tr>
			   <td class="$gh.rowCss"  align="center" valign="top" >$gh.BIL </td>
               <td class="$gh.rowCss"  align="left" valign="top">$gh.KeteranganHakmilik</td>
			   <td class="$gh.rowCss"  align="left" valign="top">$gh.NO_HAKMILIK</td>
               <td class="$gh.rowCss"  align="left" valign="top">$gh.KeteranganLot $gh.LOT</td>
       
        </tr>
    	#end
        
     #else
     <tr>
     <td colspan="8">Tiada Rekod</td>
     </tr>
     
     #end
        
    </table>
    
    </fieldset>