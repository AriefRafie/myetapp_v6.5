
 #if($scrolPosition!="")
 <script>
 setPageLocation('$scrolPosition');
 </script>
 #end
 
 
 <input type="hidden" name="scrolPosition$command" id="scrolPosition$command" >
 
           <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td  align="left"><strong>Jumlah Rekod: $totalRecords</strong></td>
                    <td  align="right">
                    	#if($totalRecords != 0)
                        Papar
                        <select class="smallselect" id="itemsPerPage$command"  name="itemsPerPage$command" 
                        onChange="doDivAjaxCall$formname('$div','$command','action=doChangeItemPerPage&FLAG_POT_CARIAN=$FLAG_POT_CARIAN&scrolPosition='+getPageLocation('$command'));"
                        >
                            <option value="10" #if ($itemsPerPage == 10) selected #end>10</option>
                            <option value="20" #if ($itemsPerPage == 20) selected #end>20</option>
                            <option value="30" #if ($itemsPerPage == 30) selected #end>30</option>
                            <option value="40" #if ($itemsPerPage == 40) selected #end>40</option>
                            <option value="50" #if ($itemsPerPage == 50) selected #end>50</option>
                            <option value="100" #if ($itemsPerPage == 100) selected #end>100</option>
                        </select>
                        #end
                    </td>
                 </tr>
            </table>
            
            
	  <table width="100%" cellpadding="0" cellspacing="0" border="0">
	    <tr>

		<td width="70%" align="left" valign="middle">
		 #if( $isFirstPage )
		 	<img style="vertical-align=bottom;" src="../img/paging/page-first-disabled.gif">
		    <img style="vertical-align=bottom;" src="../img/paging/page-prev-disabled.gif">
		 #else
		   #set ($previous10 = $page - 9)
		   <img title="First Page" style="vertical-align=bottom;cursor:hand;cursor:pointer;" 
		   onClick="doDivAjaxCall$formname('$div','$command','action=getPage&FLAG_POT_CARIAN=$FLAG_POT_CARIAN&value=1&scrolPosition='+getPageLocation('$command'));"
		   src="../img/paging/page-first.gif">
		   #if ($page > 10)
		   	<img title="Previous 10" style="vertical-align=bottom;cursor:hand;cursor:pointer;" 
		   	onClick="doDivAjaxCall$formname('$div','$command','action=getPrevious&FLAG_POT_CARIAN=$FLAG_POT_CARIAN&page=$previous10&scrolPosition='+getPageLocation('$command'));"
		    src="../img/paging/page-prev.gif">
		   #else
		   	<img style="vertical-align=bottom;" src="../img/paging/page-prev-disabled.gif">
		   #end
		   
		#end
		&nbsp;
		
		
		
		#if ($totalPages > 0)
			#set ($next10 = $page + 9)
			#foreach ( $i in [$page_mula..$totalPages] )
				
							
							
				#if ($i <= $next10)
					#if ($i == $page)
					<b>[$i]</b>
					#else
					<a 
					href="javascript:doDivAjaxCall$formname('$div','$command','action=getPage&FLAG_POT_CARIAN=$FLAG_POT_CARIAN&value=$i&scrolPosition='+getPageLocation('$command'));"
		    		>[$i]</a>
					#end
				#end
			#end
		#end
		&nbsp;
		#if( $isLastPage )
		    <img style="vertical-align=bottom;" src="../img/paging/page-next-disabled.gif">
		    <img style="vertical-align=bottom;" src="../img/paging/page-last-disabled.gif">

		#else
		    <img title="Next 10" style="vertical-align=bottom;cursor:hand;cursor:pointer;" 
		    onClick="doDivAjaxCall$formname('$div','$command','action=getNext&FLAG_POT_CARIAN=$FLAG_POT_CARIAN&page=$next10&scrolPosition='+getPageLocation('$command'));"
		    src="../img/paging/page-next.gif">
		    <img title="Last page:$totalPages" style="vertical-align=bottom;cursor:hand;cursor:pointer;" 
		    onClick="doDivAjaxCall$formname('$div','$command','action=getPage&FLAG_POT_CARIAN=$FLAG_POT_CARIAN&value=$totalPages&scrolPosition='+getPageLocation('$command'));"
		    src="../img/paging/page-last.gif">
	
		#end		</td>
		#if ($totalPages > 0)
		<td align="right" nowrap>Mukasurat $page dari $totalPages</td>
		#end
	    </tr>
	</table>