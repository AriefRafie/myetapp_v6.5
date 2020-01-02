

#if($PMT_OPENCLOSE_CARIAN=="open")
<script>
	document.getElementById('div_CARIAN_PMT').style.display = "";
	document.getElementById('span_LINK_CARIAN_PMT').style.display = "none";	
</script>
#end
#if($PMT_OPENCLOSE_CARIAN=="close")
<script>
	document.getElementById('div_CARIAN_PMT').style.display = "none";
	document.getElementById('span_LINK_CARIAN_PMT').style.display = "";
</script>
#end

#if($command=="insertPMT")
<script>
document.getElementById('div_addPMT').innerHTML='';
</script>
#end



#if($command!="showSenaraiPMT" || $FLAG_PMT_CARIAN=="Y" ||  $FLAG_PMT_CARIAN=="N")
	#if($FLAG_PMT_CARIAN=="Y")
	<script>
	if( $jquery('#div_CARIAN_PMT').length )         // use this if you are using id to check
	{
		window.scrollTo(0, $jquery('#div_CARIAN_PMT').offset().top);
	}
	</script>
	#else
	<script>
	if( $jquery('#div_SenaraiPMT').length )         // use this if you are using id to check
	{
		window.scrollTo(0, $jquery('#div_SenaraiPMT').offset().top);
	}
	</script>
	#end
#end


<fieldset id="div_rowPMT">
<table width="100%" id="div_viewPMT" >
<tr >
</tr>
</table>
<!-- ::::::::: $listPMT -->
<table width="100%" align="center">
<tr>
<td>

	
	<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($listPMT.size()>0)
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		   #parse("app/admin/OT/OTrecord_paging_PMT.jsp")
		   </td>
		     
	</tr>
	#if($listPMT.size()==1)
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
		   <td   align="left" valign="top">Minit Insert</td>
		   <td   align="left" valign="top">Minit Update</td>		   
		   <!--   <td   align="left" valign="top">Bahagian</td> -->
		   <td   align="center" valign="top">Tindakan</td>	   
	</tr>
	#if($listPMT.size()>0)
	
		#foreach($PMT in $listPMT)
		
		<tr id="div_rowPMT$PMT.ID_MINITTRANSAKSI" >
			   <td class="$PMT.rowCss"  align="center" valign="top" >$PMT.BIL</td>
			   <td class="$PMT.rowCss"  align="left" valign="top">			   
			   #set($span1 = "span1listPMT"+$PMT.BIL)
			   <span id="$span1"> 
			   $PMT.NAMA_AKTIVITI
			   </span>			
			   <script>highlight_item('$PMT_NAMA_AKTIVITI','$span1')</script>   
			   </td>
			   <td class="$PMT.rowCss"  align="left" valign="top">  
			   $PMT.MINIT_INSERT
			   </td>
			   <td class="$PMT.rowCss"  align="left" valign="top">	
			   $PMT.MINIT_UPDATE
			   </td>
			   <!--
			   <td class="$PMT.rowCss"  align="left" valign="top">			   
			   #set($span4 = "span4listPMT"+$PMT.BIL)
			   <span id="$span4"> 
			   $PMT.NAMA_SEKSYEN
			   </span>			   
			   </td>
			   -->
			   <td class="$PMT.rowCss"  align="center" valign="top">
			   <a href="javascript:doDivAjaxCall$formname('div_rowPMT$PMT.ID_MINITTRANSAKSI','editPMT','ID_MINITTRANSAKSI=$PMT.ID_MINITTRANSAKSI&BIL=$PMT.BIL&rowCss=$PMT.rowCss');"><img title="Kemaskini" src="../img/edit.gif" border="0"></a>
	   		   <a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_SenaraiPMT','deletePMT','ID_MINITTRANSAKSI=$PMT.ID_MINITTRANSAKSI');}"><img title="Hapus"  src="../img/delete.gif" border="0"></a>
	   		  
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
