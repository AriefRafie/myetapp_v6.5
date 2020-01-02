#if ($FlagCari == "N")
#set ($carianUmum = "")
#end
<tr>
<td></td>
<td align="left" valign="top" colspan="3">
<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0">
<tr>
<td id="totalMukim" align="right"><strong></strong></td>
</tr>
</table>

<table width="98%" align="center">
<tr class="table_header" >
<!--<td colspan="10">
SENARAI KOD UPI DAN MUKIM
<a href="javascript:document.getElementById('div_tambahMukim$ID_DAERAH').style.display=''; doDivAjaxCall$formname('div_tambahMukim$ID_DAERAH','editMukim','ID_NEGERI=$ID_NEGERI&ID_DAERAH=$ID_DAERAH&ID_MUKIM=');">
<img title="Tambah Daerah" src="../img/plus3.gif" border="0" align="right"></a>

</td>
</tr>-->
<td width="9%"   align="center" valign="top">Kod UPI</td>
<td width="83%" align="left" valign="top" colspan="2">Mukim #if ($NAMA_DAERAH != "") Bagi Daerah $NAMA_DAERAH #end</td>
</tr>


<tr>

	#if($listMukim.size()>0)
	#foreach($senaraiMukim in $listMukim)
    <div id="div_tambahMukim$ID_DAERAH" style="display:none">
    
     <!--<tr ><td align="left" valign="top" colspan="10">
     </td> </tr>--> 
    </div>
    
	<tr>
    <!--<td width="8%"   align="center" valign="top" class="$senaraiDaerah.rowCss">($senaraiDaerah.KOD_DAERAH)</td>-->
	   <td width="8%"   align="center" valign="top" class="$senaraiMukim.rowCss">($senaraiMukim.KOD_MUKIM_UPI)</td>
	   <td width="60%"  align="left" valign="top"  class="$senaraiMukim.rowCss"><a href="#" onclick="doDivAjaxCall$formname('SenaraiSeksyen','showSenaraiSeksyen','cetakReport=$cetakReport&FlagCetak=$FlagCetak&ID_MUKIM=$senaraiMukim.ID_MUKIM&NAMA_MUKIM=$senaraiMukim.NAMA_MUKIM');"><u>$senaraiMukim.NAMA_MUKIM</u></a></td>
#if ($cetakReport.equals("Y") && $FlagCetak.equals("Y"))
#else
<td width="10%" align ="center" valign="top" class="$senaraiMukim.rowCss"><a href="javascript:document.getElementById('div_tambahMukim$ID_DAERAH').style.display=''; doDivAjaxCall$formname('div_viewMukim$senaraiMukim.ID_MUKIM','viewMukim','ID_NEGERI=$ID_NEGERI&ID_DAERAH=$ID_DAERAH&ID_MUKIM=$senaraiMukim.ID_MUKIM');">
<img src="../img/edit.gif" border="0"></a>
<!-- 
<a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_senaraiUtama','carianBahagian','ID_SEKSYEN=$cariBahagianHQ.ID_SEKSYEN&FLAG_DELETE=Y&TYPE=$TYPE');}">
	<img title="Hapus"  src="../img/delete.gif" border="0"></a>-->
    </td>
    #end  
    </tr>
    

     <tr id="div_viewMukim$senaraiMukim.ID_MUKIM">
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
