
<tr>
<td align="left" valign="top" colspan="3">
<table width="48%" border="0" cellpadding="0" cellspacing="0">
<tr>
<td id="totalDaerah" align="left"><strong>Jumlah Tugasan: $totalRecords</strong></td>
</tr>
</table>

<table width="50%" align="left">
<tr>
<td>
<table border="0" cellspacing="1" cellpadding="1" width="732" >  
<tr class="table_header" >
		   <td width="30%"   align="left" valign="top">No Fail</td>
	      <td width="58%"   align="left" valign="top">Keterangan</td>
          <td width="12%"   align="left" valign="top">Tindakan</td>
	</tr>
	#if($listTugasan.size()>0)
	#foreach($senaraiTugasan in $listTugasan)
    
    <tr>
	   <td width="30%"   align="left" valign="top" >$senaraiTugasan.no_Fail $senaraiTugasan.tajuk_Fail</td>
	   <td width="58%"  align="left" valign="top" id="div_viewDaerah$senaraiTugasan.ID_DAERAH">$senaraiTugasan.keterangan</td>
       <td>Link Lihat</td>
    </tr>
#end
	#else 
	<tr >
	<td  align="left" valign="top" colspan="14" >Tiada Tugasan untuk anda.</td>
	</tr>
	#end
</table>

#if($listTugasan.size()>0)
<table width="100%" cellpadding="0" cellspacing="0" border="0" id="tableSenaraiDaerah">
	    <tr>

		<td width="70%" align="left" valign="middle">
         <input type="hidden" id="itemsPerPage$command"  name="itemsPerPage$command" value="5" >
		 #if( $isFirstPage )
		 	<img style="vertical-align=bottom;" src="../img/paging/page-first-disabled.gif">
		    <img style="vertical-align=bottom;" src="../img/paging/page-prev-disabled.gif">
		 #else
		   #set ($previous10 = $page - 4)
		   <img title="First Page" style="vertical-align=bottom;cursor:hand;cursor:pointer;" 
		   onClick="doDivAjaxCall$formname('$div','$command','action=getPage&value=1&ID_NEGERI='+$jquery('#Form_id_negeri').val()+'&scrolPosition='+getPageLocation('$command'));"
		   src="../img/paging/page-first.gif">
		   #if ($page > 5)
		   	<img title="Previous 5" style="vertical-align=bottom;cursor:hand;cursor:pointer;" 
		   	onClick="doDivAjaxCall$formname('$div','$command','action=getPrevious&page=$previous10&ID_NEGERI='+$jquery('#Form_id_negeri').val()+'&scrolPosition='+getPageLocation('$command'));"
		    src="../img/paging/page-prev.gif">
		   #else
		   	<img style="vertical-align=bottom;" src="../img/paging/page-prev-disabled.gif">
		   #end
		   
		#end
		&nbsp;
		
		
		
		#if ($totalPages > 0)
			#set ($next10 = $page + 4)
			#foreach ( $i in [$page_mula..$totalPages] )
				
							
							
				#if ($i <= $next10)
					#if ($i == $page)
					<b>[$i]</b>
					#else
					<a 
					href="javascript:doDivAjaxCall$formname('$div','$command','action=getPage&value=$i&ID_NEGERI='+$jquery('#Form_id_negeri').val()+'&scrolPosition='+getPageLocation('$command'));"
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
		    <img title="Next 5" style="vertical-align=bottom;cursor:hand;cursor:pointer;" 
		    onClick="doDivAjaxCall$formname('$div','$command','action=getNext&page=$next10&ID_NEGERI='+$jquery('#Form_id_negeri').val()+'&scrolPosition='+getPageLocation('$command'));"
		    src="../img/paging/page-next.gif">
            
		    <img title="Last page:$totalPages" style="vertical-align=bottom;cursor:hand;cursor:pointer;" 
		    onClick="doDivAjaxCall$formname('$div','$command','action=getPage&value=$totalPages&ID_NEGERI='+$jquery('#Form_id_negeri').val()+'&scrolPosition='+getPageLocation('$command'));"
		    src="../img/paging/page-last.gif">
	
		#end		
        </td>
		</tr>
	</table>
#end
</td>
</tr>
</table>
</td>
</tr>
