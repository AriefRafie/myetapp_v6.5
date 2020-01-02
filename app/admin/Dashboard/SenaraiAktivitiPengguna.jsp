<script>
//$jquery("#div_rowPejabatUrusan_deletemesej").show().delay(3000).fadeOut();
if( $jquery('#'+'senaraiAktivitiPengguna').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'senaraiAktivitiPengguna').offset().top);
}

</script>
<tr>
<td align="left" valign="top" colspan="3">
<table width="48%" border="0" cellpadding="0" cellspacing="0">
<tr>
<td id="totalDaerah" align="right"><!--<strong>Jumlah Aktiviti : $totalRecords</strong>--></td>
</tr>
</table>

<table width="40%" align="left">
<tr>
<td>
<table border="0" cellspacing="1" cellpadding="1" width="590" >  
<tr><td colspan="14" align="right"><img width="20" height="20" src="../img/printer.png" onclick="doCetak('listLogPengguna')" /></td></tr>
<tr class="table_header" >
		   <td width="26%"   align="left" valign="top">Nama</td>
	      <td width="25%"   align="left" valign="top">Tarikh Terakhir Log masuk</td>
          <td width="49%"   align="left" valign="top">Aktiviti</td>
	</tr>
	#if($listAktiviti.size()>0)
	#foreach($senaraiAktiviti in $listAktiviti)
    
    <tr>
	   <td width="26%"   align="left" valign="top" class="$senaraiAktiviti.rowCss">$senaraiAktiviti.USER_NAME</td>
	   <td width="25%"  align="left" valign="top" class="$senaraiAktiviti.rowCss">$senaraiAktiviti.LOG_DATE</td>
       <td width="49%"  align="left" valign="top" class="$senaraiAktiviti.rowCss">$senaraiAktiviti.KETERANGAN</td>
    </tr>
#end
	#else 
	<tr >
	<td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
	</tr>
	#end
</table>

#if($listAktiviti.size()>0)
<table width="100%" cellpadding="0" cellspacing="0" border="0" id="tableSenaraiDaerah">
	    <tr>

		<td width="70%" align="left" valign="middle">
         <input type="hidden" name="scrolPosition$command" id="scrolPosition$command" >
         <input type="hidden" id="itemsPerPage$command"  name="itemsPerPage$command" value="10" >
		 #if( $isFirstPage )
		 	<img style="vertical-align=bottom;" src="../img/paging/page-first-disabled.gif">
		    <img style="vertical-align=bottom;" src="../img/paging/page-prev-disabled.gif">
		 #else
		   #set ($previous10 = $page - 9)
		   <img title="First Page" style="vertical-align=bottom;cursor:hand;cursor:pointer;" 
		   onClick="doDivAjaxCall$formname('$div','$command','action=getPage&value=1&showSenaraiAktiviti='+$jquery('#senaraiAktivitiPengguna').val()+'&scrolPosition='+getPageLocation('$command'));"
		   src="../img/paging/page-first.gif">
		   #if ($page > 10)
		   	<img title="Previous 10" style="vertical-align=bottom;cursor:hand;cursor:pointer;" 
		   	onClick="doDivAjaxCall$formname('$div','$command','action=getPrevious&page=$previous10&showSenaraiAktiviti='+$jquery('#senaraiAktivitiPengguna').val()+'&scrolPosition='+getPageLocation('$command'));"
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
					href="javascript:doDivAjaxCall$formname('$div','$command','action=getPage&value=$i&showSenaraiAktiviti='+$jquery('#senaraiAktivitiPengguna').val()+'&scrolPosition='+getPageLocation('$command'));"
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
		    onClick="doDivAjaxCall$formname('$div','$command','action=getNext&page=$next10&showSenaraiAktiviti='+$jquery('#senaraiAktivitiPengguna').val()+'&scrolPosition='+getPageLocation('$command'));"
		    
		    src="../img/paging/page-next.gif">
		    <img title="Last page:$totalPages" style="vertical-align=bottom;cursor:hand;cursor:pointer;" 
		    onClick="doDivAjaxCall$formname('$div','$command','action=getPage&value=$totalPages&showSenaraiAktiviti='+$jquery('#senaraiAktivitiPengguna').val()+'&scrolPosition='+getPageLocation('$command'));"
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

</td>
</tr>