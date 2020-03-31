 #if($scrolPosition!="")
 <script>
 setPageLocation('$scrolPosition');
 </script>
 #end
<table border="0" cellspacing="1" cellpadding="3" width="100%"  class="classFade"> 
<tr >
<td align="left" valign="top" colspan="15" >
 
  
  <input type="hidden" id="sortType$skrinName$command" value="$!sortType" />
  <input type="hidden" id="sortColumn$skrinName$command" value="$!sortColumn" />
  <input type="hidden" id="sortColumnType$skrinName$command" value="$!sortColumnType" />
  <input type="hidden" id="sortDateFormat$skrinName$command" value="$!sortDateFormat" />
  <input type="hidden" id="currentPage$skrinName$command"  name="currentPage$skrinName$command"  value="$!page"  />
           <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td  align="left"><strong>Jumlah Rekod: $totalRecords</strong>
                    
                    #if($totalRecords > 0 &&  $totalRecords == $maxRowNum )
                    <div style="margin:5px"><i><font color='blue'>Info</font> : Paparan maximum adalah $maxRowNum rekod.</i></div>
                    #end
                    
                    </td>
                    <td  align="right">
                    	#if($totalRecords != 0)
                        Papar
                        <select class="smallselect" id="itemsPerPage$skrinName$command"  name="itemsPerPage$skrinName$command" 
                        onChange="doDivAjaxCall$formname('$div','$command','div=$div&namaList=$namaList&actionajax=doChangeItemPerPage$param&scrolPosition='+getPageLocation());"
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
		   onClick="doDivAjaxCall$formname('$div','$command','div=$div&namaList=$namaList&actionajax=getPage$param&value=1&scrolPosition='+getPageLocation());"
		   src="../img/paging/page-first.gif">
		   #if ($page > 10)
		   	<img title="Previous 10" style="vertical-align=bottom;cursor:hand;cursor:pointer;" 
		   	onClick="doDivAjaxCall$formname('$div','$command','div=$div&namaList=$namaList&actionajax=getPrevious$param&page=$previous10&scrolPosition='+getPageLocation());"
		    src="../img/paging/page-prev.gif">
		   #else
		   	<img style="vertical-align=bottom;" src="../img/paging/page-prev-disabled.gif">
		   #end
		   
		#end
		&nbsp;
		
		
		
		#if ($totalPages > 0)
			#set ($next10 = $page + 9)
            
			#foreach ( $i in [$page_mula..$totalPages] )
            	
                #set ( $x = $x + 1 )
							#if ( $!page == $i )
							    #set ( $visited = "squareV" )
							#else
							    #set ( $visited = "square" )
							#end
							#if ( $i <= $next10)
								<a href="javascript:doDivAjaxCall$formname('$div','$command','div=$div&namaList=$namaList$param&actionajax=getPage&value=$i&scrolPosition='+getPageLocation());">
                                <span class=$visited>$i</span>
                                </a>
							#end
			#end
		#end
		&nbsp;
		#if( $isLastPage )
		    <img style="vertical-align=bottom;" src="../img/paging/page-next-disabled.gif">
		    <img style="vertical-align=bottom;" src="../img/paging/page-last-disabled.gif">

		#else
		    <img title="Next 10" style="vertical-align=bottom;cursor:hand;cursor:pointer;" 
		    onClick="doDivAjaxCall$formname('$div','$command','div=$div&namaList=$namaList&actionajax=getNext$param&page=$next10&scrolPosition='+getPageLocation());"
		    
		    src="../img/paging/page-next.gif">
		    <img title="Last page:$totalPages" style="vertical-align=bottom;cursor:hand;cursor:pointer;" 
		    onClick="doDivAjaxCall$formname('$div','$command','div=$div&namaList=$namaList&actionajax=getPage$param&value=$totalPages&scrolPosition='+getPageLocation());"
		    src="../img/paging/page-last.gif">
	
		#end		</td>
		#if ($totalPages > 0)
		<td align="right" nowrap>Mukasurat $page dari $totalPages</td>
		#end
	    </tr>
	</table>
    
    </td>
</tr>
</table>
   