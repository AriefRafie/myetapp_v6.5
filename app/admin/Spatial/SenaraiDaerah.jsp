#if ($FlagCari == "N")
#set ($carianUmum = "")
#end
<tr>
<td></td>
<td align="left" valign="top" colspan="3">
<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0">
<tr>
<td id="totalDaerah" align="right"><strong></strong></td>
</tr>
</table>

<table width="98%" align="center">
<tr class="table_header" >
<td width="9%"   align="center" valign="top">Kod UPI</td>
<td width="83%" align="left" valign="top" colspan="2">Daerah #if ($NAMA_NEGERI != "") Bagi Negeri $NAMA_NEGERI #end </td>

</tr>

<tr>

	#if($listDaerah.size()>0)
	#foreach($senaraiDaerah in $listDaerah)
    <div id="div_tambahDaerah$ID_NEGERI" style="display:none">
    
     <!--<tr ><td align="left" valign="top" colspan="10">
     </td> </tr>--> 
    </div>
    
	<tr>
    <!--<td width="8%"   align="center" valign="top" class="$senaraiDaerah.rowCss">($senaraiDaerah.KOD_DAERAH)</td>-->
	   <td width="8%"   align="center" valign="top" class="$senaraiDaerah.rowCss">($senaraiDaerah.KOD_DAERAH_UPI)</td>
	   <td width="60%"  align="left" valign="top"  class="$senaraiDaerah.rowCss"><a href="#" onclick="doDivAjaxCall$formname('SenaraiMukim','showSenaraiMukim','cetakReport=$cetakReport&FlagCetak=$FlagCetak&ID_DAERAH=$senaraiDaerah.ID_DAERAH&NAMA_DAERAH=$senaraiDaerah.NAMA_DAERAH&NAMA_MUKIM=');"><u>$senaraiDaerah.NAMA_DAERAH</u></a>
</td>
#if ($cetakReport.equals("Y") && $FlagCetak.equals("Y"))
#else
<td width="10%" align ="center" valign="top" class="$senaraiDaerah.rowCss"><a href="javascript:document.getElementById('div_tambahDaerah$ID_NEGERI').style.display=''; doDivAjaxCall$formname('div_viewDaerah$senaraiDaerah.ID_DAERAH','viewDaerah','ID_NEGERI=$senaraiDaerah.ID_NEGERI&ID_DAERAH=$senaraiDaerah.ID_DAERAH');">
<img src="../img/edit.gif" border="0"></a><!-- 
<a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_senaraiUtama','carianBahagian','ID_SEKSYEN=$cariBahagianHQ.ID_SEKSYEN&FLAG_DELETE=Y&TYPE=$TYPE');}">
	<img title="Hapus"  src="../img/delete.gif" border="0"></a>-->
    </td>
    #end  
    </tr>
    

     <tr id="div_viewDaerah$senaraiDaerah.ID_DAERAH">
     <td align="left" valign="top" colspan="10">
     </td>
     </tr>
   
	#end
	#else 
	<tr >
	<td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
	</tr>
	#end
	</table>

#if ($cetakReport.equals("Y") && $FlagCetak.equals("Y"))
#else
<!--<table width="100%" cellpadding="0" cellspacing="0" border="0" id="tableSenaraiDaerah">
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
	</table>-->
#end
</td>
</tr>
</table>
</td>
</tr>
