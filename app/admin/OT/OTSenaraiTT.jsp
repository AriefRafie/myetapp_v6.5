

#if($TT_OPENCLOSE_CARIAN=="open")
<script>
	document.getElementById('div_CARIAN_TT').style.display = "";
	document.getElementById('span_LINK_CARIAN_TT').style.display = "none";	
</script>
#end
#if($TT_OPENCLOSE_CARIAN=="close")
<script>
	document.getElementById('div_CARIAN_TT').style.display = "none";
	document.getElementById('span_LINK_CARIAN_TT').style.display = "";
</script>
#end

#if($command=="insertTT")
<script>
document.getElementById('div_addTT').innerHTML='';
</script>
#end

#if($command!="showSenaraiTT" || $FLAG_TT_CARIAN=="Y" ||  $FLAG_TT_CARIAN=="N")
	#if($FLAG_TT_CARIAN=="Y")
	<script>
	if( $jquery('#div_CARIAN_TT').length )         // use this if you are using id to check
	{
		window.scrollTo(0, $jquery('#div_CARIAN_TT').offset().top);
	}
	</script>
	#else
	<script>
	if( $jquery('#div_SenaraiTT').length )         // use this if you are using id to check
	{
		window.scrollTo(0, $jquery('#div_SenaraiTT').offset().top);
	}
	</script>
	#end
#end


<fieldset id="div_rowTT">
<table width="100%" id="div_viewTT" >
<tr >
</tr>
</table>
<!-- ::::::::: $listTT -->
<table width="100%" align="center">
<tr>
<td>

	
	<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($listTT.size()>0)
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		   #parse("app/admin/OT/OTrecord_paging_TT.jsp")
		   </td>
		     
	</tr>
	#if($listTT.size()==1)
	<script>
	//$jquery("#showUserCount_HQ").html("("+$totalRecords+" USER FOUND)");
	</script>
	#else
	<script>
	//$jquery("#showUserCount_HQ").html("("+$totalRecords+" USERS FOUND)");
	</script>
	#end
	
	#end 
	<tr class="table_header" >
		   <td   align="center" valign="top">Bil.</td>
		   <td   align="left" valign="top">Nama Aktiviti</td>
		   <td   align="left" valign="top">Nama Table</td>
		   <td   align="left" valign="top">Nama Field Check</td>		   
		   <!--   <td   align="left" valign="top">Bahagian</td> -->
		   <td   align="center" valign="top">Tindakan</td>	   
	</tr>
	#if($listTT.size()>0)
	
		#foreach($TT in $listTT)
		
		<tr id="div_rowTT$TT.ID_TRANSAKSI" >
			   <td class="$TT.rowCss"  align="center" valign="top" >$TT.BIL</td>
			   <td class="$TT.rowCss"  align="left" valign="top">			   
			   #set($span1 = "span1listTT"+$TT.BIL)
			   <span id="$span1"> 
			   $TT.NAMA_AKTIVITI
			   </span>			
			   <script>highlight_item('$TT_NAMA_AKTIVITI','$span1')</script>   
			   </td>
			   <td class="$TT.rowCss"  align="left" valign="top">			   
			   #set($span2 = "span2listTT"+$TT.BIL)
			   <span id="$span2"> 
			   $TT.NAMA_TABLE
			   </span>		
			   <script>highlight_item('$TT_NAMA_TABLE','$span2')</script>	   
			   </td>
			   <td class="$TT.rowCss"  align="left" valign="top">	
			  	   
			   #set($span3 = "span3listTT"+$TT.BIL)
			   <span id="$span3"> 
			   $TT.FIELD_CHECK
			   </span>			   
			   <script>highlight_item('$TT_NAMA_FIELD','$span3')</script>	
			   			   
			   </td>
			   <!--
			   <td class="$TT.rowCss"  align="left" valign="top">			   
			   #set($span4 = "span4listTT"+$TT.BIL)
			   <span id="$span4"> 
			   $TT.NAMA_SEKSYEN
			   </span>			   
			   </td>
			   -->
			   <td class="$TT.rowCss"  align="center" valign="top">
			   <a href="javascript:doDivAjaxCall$formname('div_rowTT$TT.ID_TRANSAKSI','editTT','ID_TRANSAKSI=$TT.ID_TRANSAKSI&BIL=$TT.BIL&rowCss=$TT.rowCss');"><img title="Kemaskini" src="../img/edit.gif" border="0"></a>
	   		   <a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_SenaraiTT','deleteTT','ID_TRANSAKSI=$TT.ID_TRANSAKSI');}"><img title="Hapus"  src="../img/delete.gif" border="0"></a>
	   		  
			   </td>	   
		</tr>
	
		#end
	#else
	<tr >
		   <td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
		     
	</tr>
	#end
	</table>


</td>
</tr>
</table>
</fieldset>

<br>
