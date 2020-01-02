#if($pagingTitle == "")
	#set ($pagingTitle = "Jumlah Rekod")
#else
	#set ($pagingTitle = "Jumlah Carian")
#end

<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="left">$pagingTitle: $!totalRecords</td>
		<td align="right">
			Papar
			<select class="smallselect" name="itemsPerPage" onchange="javascript:doAjaxCall${formname}('doChanges','action=doChangeItemPerPage')">
				<option value="10" #if ($itemsPerPage == 10) selected #end>10</option>
				<option value="20" #if ($itemsPerPage == 20) selected #end>20</option>
				<option value="30" #if ($itemsPerPage == 30) selected #end>30</option>
				<option value="40" #if ($itemsPerPage == 40) selected #end>40</option>
				<option value="50" #if ($itemsPerPage == 50) selected #end>50</option>
				<option value="100" #if ($itemsPerPage == 100) selected #end>100</option>
			</select>
		</td>
	</tr>
	<tr>
		<td width="70%">
		   	<table cellspacing="1" cellpadding="3" align="left" border="0">
		   		<tr>
			   		<td>
				   		#if( $isFirstPage )
				    		<img style="vertical-align:bottom;" src="../../img/paging/page-first-disabled.gif">
					    	<img style="vertical-align:bottom;" src="../../img/paging/page-prev-disabled.gif">
    			   		#else
    			   			#if ( $totalPages >= 11 )
								#set ($previous = $page)
							#else
								#set ($previous = $page - 1)
							#end
							<img title="Halaman Pertama" style="vertical-align:bottom;cursor:hand;cursor:pointer;" onclick="javascript:doAjaxCall${formname}('record_listing','action=getPage&value=1')" src="../../img/paging/page-first.gif">
							#if ($page > 1)
							   	<img title="Sebelumnya" style="vertical-align:bottom;cursor:hand;cursor:pointer;" onclick="javascript:doAjaxCall${formname}('record_listing','action=getPrevious&page=$previous')" src="../../img/paging/page-prev.gif">
							#else
						   		<img style="vertical-align:bottom;" src="../../img/paging/page-prev-disabled.gif">
							#end
						#end
			   		</td>
					#if ( $totalPages > 0 )
					   	#set ( $x = 0 )
						#set ( $paging = 1 )
						#if ( $totalPages > 11 )
							#if ( $page > 5 && $page <= ($totalPages - 5) )
								#set ( $paging = $page - 5 )
							#elseif ( $page > ($totalPages - 5) )
								#set ( $paging = $totalPages - 10 )		
							#end
						#end
						#foreach ( $i in [$paging..$totalPages] )
							#set ( $x = $x + 1 )
							#if ( $!page == $i )
							    #set ( $visited = "squareV" )
							#else
							    #set ( $visited = "square" )
							#end
							#if ( $x <= 11 )
								<td><a href="javascript:doAjaxCall${formname}('doChanges','action=getPage&value=$i')"><div class=$visited>$i</div></a></td>
							#end
						#end
					#end	
					<td>
					#if( $isLastPage )
					    <img style="vertical-align:bottom;" src="../../img/paging/page-next-disabled.gif">
					    <img style="vertical-align:bottom;" src="../../img/paging/page-last-disabled.gif">
					#else
   			   			#if ( $totalPages >= 11 )
							#set ($next = $page)
						#else
							#set ($next = $page + 1)
						#end
				    	<img title="Berikutnya" style="vertical-align:bottom;cursor:hand;cursor:pointer;"onclick="javascript:doAjaxCall${formname}('record_listing','action=getNext&page=$next')" src="../../img/paging/page-next.gif">
					    <img title="Halaman Belakang : $totalPages" style="vertical-align:bottom;cursor:hand;cursor:pointer;"onclick="javascript:doAjaxCall${formname}('record_listing','action=getPage&value=$totalPages')" src="../../img/paging/page-last.gif">
					#end
					</td>
				</tr>
			</table>
		</td>		
		#if ($totalPages > 0)
			<td align="right" nowrap>Mukasurat $page dari $totalPages</td>
		#end
    </tr>
</table>