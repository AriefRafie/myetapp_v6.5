#if($pagingTitle1 == "")
	#set ($pagingTitle1 = "Jumlah Rekod")
#else
	#set ($pagingTitle1 = "Jumlah Carian")
#end

<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="left">$pagingTitle1: $!totalRecords1</td>
		<td align="right">
			Papar
			<select class="smallselect" name="itemsPerPage1" onchange="javascript:doAjaxCall${formname}('doChanges1','action1=doChangeItemPerPage')">
				<option value="10" #if ($itemsPerPage1 == 10) selected #end>10</option>
				<option value="20" #if ($itemsPerPage1 == 20) selected #end>20</option>
				<option value="30" #if ($itemsPerPage1 == 30) selected #end>30</option>
				<option value="40" #if ($itemsPerPage1 == 40) selected #end>40</option>
				<option value="50" #if ($itemsPerPage1 == 50) selected #end>50</option>
				<option value="100" #if ($itemsPerPage1 == 100) selected #end>100</option>
			</select>
		</td>
	</tr>
	<tr>
		<td width="70%">
		   	<table cellspacing="1" cellpadding="3" align="left" border="0">
		   		<tr>
			   		<td>
				   		#if( $isFirstPage1 )
				    		<img style="vertical-align:bottom;" src="../img/paging/page-first-disabled.gif">
					    	<img style="vertical-align:bottom;" src="../img/paging/page-prev-disabled.gif">
    			   		#else
    			   			#if ( $totalPages1 >= 11 )
								#set ($previous = $page1)
							#else
								#set ($previous = $page1 - 1)
							#end
							<img title="Halaman Pertama" style="vertical-align:bottom;cursor:hand;cursor:pointer;" onclick="javascript:doAjaxCall${formname}('record_listing1','action1=getPage&value1=1')" src="../img/paging/page-first.gif">
							#if ($page1 > 1)
							   	<img title="Sebelumnya" style="vertical-align:bottom;cursor:hand;cursor:pointer;" onclick="javascript:doAjaxCall${formname}('record_listing1','action1=getPrevious&page1=$previous')" src="../img/paging/page-prev.gif">
							#else
						   		<img style="vertical-align:bottom;" src="../img/paging/page-prev-disabled.gif">
							#end
						#end
			   		</td>
					#if ( $totalPages1 > 0 )
					   	#set ( $x = 0 )
						#set ( $paging = 1 )
						#if ( $totalPages1 > 11 )
							#if ( $page1 > 5 && $page1 <= ($totalPages1 - 5) )
								#set ( $paging = $page1 - 5 )
							#elseif ( $page1 > ($totalPages1 - 5) )
								#set ( $paging = $totalPages1 - 10 )		
							#end
						#end
						#foreach ( $i in [$paging..$totalPages1] )
							#set ( $x = $x + 1 )
							#if ( $!page1 == $i )
							    #set ( $visited = "squareV" )
							#else
							    #set ( $visited = "square" )
							#end
							#if ( $x <= 11 )
								<td><a href="javascript:doAjaxCall${formname}('doChanges1','action1=getPage&value1=$i')"><div class=$visited>$i</div></a></td>
							#end
						#end
					#end	
					<td>
					#if( $isLastPage1 )
					    <img style="vertical-align:bottom;" src="../img/paging/page-next-disabled.gif">
					    <img style="vertical-align:bottom;" src="../img/paging/page-last-disabled.gif">
					#else
   			   			#if ( $totalPages1 >= 11 )
							#set ($next1 = $page1)
						#else
							#set ($next1 = $page1 + 1)
						#end
				    	<img title="Berikutnya" style="vertical-align:bottom;cursor:hand;cursor:pointer;"onclick="javascript:doAjaxCall${formname}('record_listing1','action1=getNext&page1=$next')" src="../img/paging/page-next.gif">
					    <img title="Halaman Belakang : $totalPages1" style="vertical-align:bottom;cursor:hand;cursor:pointer;"onclick="javascript:doAjaxCall${formname}('record_listing1','action1=getPage&value=$totalPages1')" src="../img/paging/page-last.gif">
					#end
					</td>
				</tr>
			</table>
		</td>		
		#if ($totalPages1 > 0)
			<td align="right" nowrap>Mukasurat $page1 dari $totalPages1</td>
		#end
    </tr>
</table>