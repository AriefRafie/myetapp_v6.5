<table width="100%" cellpadding="0" cellspacing="0" border="0">
<tr>

		<td width="70%" align="left" valign="middle">
		 #if( $isFirstPage )
		    ##
		  <!--<input class="stylobutton"  type="button"  value="<< Previous" disabled>-->
		    <img style="vertical-align=bottom;" src="../img/paging/page-first-disabled.gif">
		    <img style="vertical-align=bottom;" src="../img/paging/page-prev-disabled.gif">
		#else
		    ##<!--<input class="stylobutton"  type="button"  value="<< Previous" onclick="javascript:doAjaxCall${formname}('carian','action=getPrevious')">-->
		   
		   #set ($previous10 = $page - 9)
		   <img title="First Page" style="vertical-align=bottom;cursor:hand;cursor:pointer;" onclick="javascript:doAjaxCall${formname}('doPaging','action=getPage&value=1')" src="../img/paging/page-first.gif">
		   #if ($page > 10)
		   	<img title="Previous 10" style="vertical-align=bottom;cursor:hand;cursor:pointer;" onclick="javascript:doAjaxCall${formname}('doPaging','action=getPrevious&page=$previous10')" src="../img/paging/page-prev.gif">
		   #else
		   	<img style="vertical-align=bottom;" src="../img/paging/page-prev-disabled.gif">
		   #end
		   
		#end
		&nbsp;
		#if ($totalPages > 0)
			#set ($next10 = $page + 9)
			#foreach ( $i in [$page..$totalPages] )
				#if ($i <= $next10)
					#if ($i == $page)
					<b>[$i]</b>
					#else
					<a href="javascript:doAjaxCall${formname}('doPaging','action=getPage&value=$i')">[$i]</a>
					#end
				#end
			#end
		#end
		&nbsp;
		#if( $isLastPage )
		    ##<!--<input class="stylobutton"  type="button"  value="Next >>" disabled>-->
		    <img style="vertical-align=bottom;" src="../img/paging/page-next-disabled.gif">
		    <img style="vertical-align=bottom;" src="../img/paging/page-last-disabled.gif">

		#else
		    ##<!--<input class="stylobutton"  type="button"  value="Next >>" onclick="javascript:doAjaxCall${formname}('carian','action=getNext&page=$page')">-->
		    <img title="Next 10" style="vertical-align=bottom;cursor:hand;cursor:pointer;"onclick="javascript:doAjaxCall${formname}('doPaging','action=getNext&page=$next10')" src="../img/paging/page-next.gif">
		    <img title="Last page:$totalPages" style="vertical-align=bottom;cursor:hand;cursor:pointer;"onclick="javascript:doAjaxCall${formname}('doPaging','action=getPage&value=$totalPages')" src="../img/paging/page-last.gif">
	
		#end		</td>
		#if ($totalPages > 0)
		<td align="right" nowrap>Mukasurat $page dari $totalPages</td>
		#end
	    </tr>
	</table>