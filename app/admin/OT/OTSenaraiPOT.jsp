

#if($POT_OPENCLOSE_CARIAN=="open")
<script>
	document.getElementById('div_CARIAN_POT').style.display = "";
	document.getElementById('span_LINK_CARIAN_POT').style.display = "none";	
</script>
#end
#if($POT_OPENCLOSE_CARIAN=="close")
<script>
	document.getElementById('div_CARIAN_POT').style.display = "none";
	document.getElementById('span_LINK_CARIAN_POT').style.display = "";
</script>
#end


#if($command=="insertPOT")
<script>
document.getElementById('div_addPOT').innerHTML='';
</script>
#end

#if($command!="showSenaraiPOT" || $FLAG_POT_CARIAN=="Y" ||  $FLAG_POT_CARIAN=="N")
	#if($FLAG_POT_CARIAN=="Y")
	<script>
	if( $jquery('#div_CARIAN_POT').length )         // use this if you are using id to check
	{
		window.scrollTo(0, $jquery('#div_CARIAN_POT').offset().top);
	}
	</script>
	#else
	<script>
	if( $jquery('#div_SenaraiPOT').length )         // use this if you are using id to check
	{
		window.scrollTo(0, $jquery('#div_SenaraiPOT').offset().top);
	}
	</script>
	#end
#end


<fieldset id="div_rowPOT">
<table width="100%" id="div_viewPOT" >
<tr >
</tr>
</table>
<!-- ::::::::: $listPOT -->
<table width="100%" align="center">
<tr>
<td>

	
	<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($listPOT.size()>0)
	<script>
	var butoncetakCT =  document.getElementById('cmdCetakPOT');
	if (typeof(butoncetakCT) != 'undefined' && butoncetakCT != null)
    {
    	butoncetakCT.style.display = "";
    }
	</script>
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		   #parse("app/admin/OT/OTrecord_paging_POT.jsp")
		   </td>
		     
	</tr>
	#if($listPOT.size()==1)
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
		   <td   align="left" valign="top">No. OT</td>
		   <td   align="left" valign="top">Nama Pemohon</td>
		   <td   align="left" valign="top">Nama Pelulus</td>
		   <td   align="center" valign="top">Tarikh OT</td>		     
		   <!-- <td   align="center" valign="top">Tarikh Akhir</td>	 -->	   	   
		   <td   align="center" valign="top">Status</td>
		   <td   align="left" valign="top">Jumlah Minit</td>
		   <td   align="center" valign="top">Tindakan</td>	   
	</tr>
	#if($listPOT.size()>0)
	
		#foreach($POT in $listPOT)
		
		<tr id="div_rowPOT$POT.ID_PERMOHONANOT" >
			   <td class="$POT.rowCss"  align="center" valign="top" >$POT.BIL
			   
			   </td>
			   <td class="$POT.rowCss"  align="left" valign="top">			   
			   #set($span1 = "span1listPOT"+$POT.BIL)
			   <span id="$span1"> 
			   <a href="javascript:doDivAjaxCall$formname('div_rowPOT$POT.ID_PERMOHONANOT','editPOT','ID_PERMOHONANOT=$POT.ID_PERMOHONANOT&BIL=$POT.BIL&rowCss=$POT.rowCss');">
			   <font color='blue' >
			   #if($POT.ID_STATUS == "1" && $POT.ID_PELULUS == $USER_ID_SYSTEM)
			   <u>$POT.NO_OT</u> <br>
			   <span  class="blink" ><i><b>Tindakan Pegawai Pelulus!<b></i></span>
			   #else
			   <u>$POT.NO_OT</u>
			   #end
			   </font>
			   </a>
			   </span>			
			   <script>highlight_item('$POT_NO_OT','$span1')</script>   
			   </td>			   
			   <td class="$POT.rowCss"  align="left" valign="top">			   
			   #set($span2 = "span2listPOT"+$POT.BIL)
			   <span id="$span2"> 
			   $POT.NAMA_PEMOHON<br>
			   $POT.NAMA_UNIT,<br> 
			   $POT.NAMA_NEGERI
			   </span>			
			   <script>highlight_item('$POT_NAMA_PEMOHON','$span2')</script>   
			   </td>
			   <td class="$POT.rowCss"  align="left" valign="top">			   
			   #set($span3 = "span3listPOT"+$POT.BIL)
			   <span id="$span3"> 
			   $POT.NAMA_PELULUS
			   </span>			
			   <script>highlight_item('$POT_NAMA_PELULUS','$span3')</script>   
			   </td>
			   <td class="$POT.rowCss"  align="center" valign="top">		   
			   $POT.TARIKH_MULA - $POT.TARIKH_AKHIR
			   </td>
			   <!--    
			   <td class="$POT.rowCss"  align="center" valign="top">		   
			   $POT.TARIKH_AKHIR
			   </td>
			   -->
			   <td class="$POT.rowCss"  align="center" valign="top">		   
			   $POT.STATUS
			   </td>
			   
			   <td class="$POT.rowCss"  align="center" valign="top">		   
			   <table width="100%" border="0" cellspacing="0" cellpadding="0">
				   <tr>
				   	<td valign="top" width="70%">Jumlah Hari</td><td valign="top" width="1%">:</td><td valign="top" width="29%">$POT.OV_DAYS</td>
				   </tr>
				   <tr>
				   	<td valign="top">Waktu Kerja</td><td valign="top">:</td><td valign="top">
				   	#if($POT.OV_MWH < $POT.OV_MWH_ALL)
				   		<font color="red">$POT.OV_MWH</font>
				   	#else
				   		$POT.OV_MWH
				   	#end
				   	</td>
				   </tr>
				   <tr>
				   	<td valign="top"><b>OT</b></td><td valign="top">:</td><td valign="top"><b>$POT.OV_MOT</b></td>
				   </tr>
				   <tr>
				   	<td valign="top">Total</td><td valign="top">:</td><td valign="top">$POT.OV_MPD</td>
				   <tr>
			   </table>
			   </td>
			   
			   <td class="$POT.rowCss"  align="center" valign="top">
			   <a href="javascript:doDivAjaxCall$formname('div_rowPOT$POT.ID_PERMOHONANOT','editPOT','ID_PERMOHONANOT=$POT.ID_PERMOHONANOT&BIL=$POT.BIL&rowCss=$POT.rowCss');"><img title="Kemaskini" src="../img/edit.gif" border="0"></a>
	   		   <a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_SenaraiPOT','deletePOT','ID_PERMOHONANOT=$POT.ID_PERMOHONANOT');}"><img title="Hapus"  src="../img/delete.gif" border="0"></a>
	   		  
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
