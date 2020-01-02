

#if($BU_OPENCLOSE_CARIAN=="open")
<script>
	document.getElementById('div_CARIAN_BU').style.display = "";
	document.getElementById('span_LINK_CARIAN_BU').style.display = "none";	
</script>
#end
#if($BU_OPENCLOSE_CARIAN=="close")
<script>
	document.getElementById('div_CARIAN_BU').style.display = "none";
	document.getElementById('span_LINK_CARIAN_BU').style.display = "";
</script>
#end


#if($command=="insertBU")
<script>
document.getElementById('div_addBU').innerHTML='';
</script>
#end

#if($command!="showSenaraiBU" || $FLAG_BU_CARIAN=="Y" ||  $FLAG_BU_CARIAN=="N")
	#if($FLAG_BU_CARIAN=="Y")
	<script>
	if( $jquery('#div_CARIAN_BU').length )         // use this if you are using id to check
	{
		window.scrollTo(0, $jquery('#div_CARIAN_BU').offset().top);
	}
	</script>
	#else
	<script>
	if( $jquery('#div_SenaraiBU').length )         // use this if you are using id to check
	{
		window.scrollTo(0, $jquery('#div_SenaraiBU').offset().top);
	}
	</script>
	#end
#end


<fieldset id="div_rowBU">
<table width="100%" id="div_viewBU" >
<tr >
</tr>
</table>
<!-- ::::::::: $listBU -->
<table width="100%" align="center">
<tr>
<td>


	<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($listBU.size()>0)
    
	<script>
	var butoncetakCT =  document.getElementById('cmdCetakBU');
	if (typeof(butoncetakCT) != 'undefined' && butoncetakCT != null)
    {
    	butoncetakCT.style.display = "";
    }
	</script>
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		   #parse("app/ppk/BantuUnit/BUrecord_paging_BU.jsp")
		   </td>
		     
	</tr>
	#if($listBU.size()==1)
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
		   <td   align="center" valign="top" width="3%" >Bil.</td>
		   <td   align="left" valign="top" width="12%">No. Bantu Unit</td>
		   <td   align="left" valign="top" width="15%">Nama Pemohon</td>
		   <td   align="left" valign="top" width="15%">Nama Pelulus</td>
		   <td   align="center" valign="top" width="10%">Tarikh Bantuan</td>
           <td   align="left" valign="top" width="10%">Negeri & Unit Bantuan</td>	
           <td   align="left" valign="top" width="15%">Daerah Bantuan</td>		     
		   <td   align="center" valign="top" width="10%">Status</td>		  
		   <td   align="center" valign="top"width="10%">Tindakan</td>	   
	</tr>
	#if($listBU.size()>0)
	
		#foreach($BU in $listBU)

		<tr id="div_rowBU$BU.ID_PERMOHONANBANTUUNIT" >
			   <td class="$BU.rowCss"  align="center" valign="top" >$BU.BIL
			   
			   </td>
			   <td class="$BU.rowCss"  align="left" valign="top">			   
			   #set($span1 = "span1listBU"+$BU.BIL)
			   <span id="$span1"> 
			   <a href="javascript:doDivAjaxCall$formname('div_rowBU$BU.ID_PERMOHONANBANTUUNIT','editBU','ID_PERMOHONANBANTUUNIT=$BU.ID_PERMOHONANBANTUUNIT&BIL=$BU.BIL&rowCss=$BU.rowCss');">
			   <font color='blue' >
			   #if(($BU.ID_STATUS == "1" && $BU.ID_NEGERI == $USER_NEGERI && $jawatanKpp == true) ||($BU.ID_STATUS == "1" && $USER_NEGERI.equals("16")))
			   <u>$BU.NO_BANTUUNIT</u> <br>
			   <span  class="blink" ><i><b>Tindakan Pegawai Pelulus!<b></i></span>
			   #else
			   <u>$BU.NO_BANTUUNIT</u>
			   #end
			   
			   #if($BU.tempohTamat ==  true)			 
			   <span style="color:#F00" ><b>Telah Tamat<b></span>
			   #else
			  
			   #end
			   
			   </font>
			   </a>
			   </span>			
			   <script>highlight_item('$BU_NO_BANTUUNIT','$span1')</script>   
			   </td>			   
			   <td class="$BU.rowCss"  align="left" valign="top">			   
			   #set($span2 = "span2listBU"+$BU.BIL)
			   <span id="$span2"> 
			   $BU.NAMA_PEMOHON			  
			   </span>			
			   <script>highlight_item('$BU_NAMA_PEMOHON','$span2')</script>  
               
               <br />
               $BU.PEJABAT_PEMOHON, $BU.NEGERI_PEMOHON
			   
                
			   </td>
			   <td class="$BU.rowCss"  align="left" valign="top">	
               #if($BU.ID_PELULUS != "")		   
                   #set($span3 = "span3listBU"+$BU.BIL)
                   <span id="$span3"> 
                   $BU.NAMA_PELULUS
                   </span>			
                   <script>highlight_item('$BU_NAMA_PELULUS','$span3')</script>   
                    <br />
                   $BU.PEJABAT_PELULUS, $BU.NEGERI_PELULUS
               #end
			   </td>
			   <td class="$BU.rowCss"  align="center" valign="top">		   
			   $BU.TARIKH_MULA <br />-<br /> $BU.TARIKH_AKHIR
			   </td>
			   
			   <td class="$BU.rowCss"  align="left" valign="top">	
               #set($span4 = "span4listBU"+$BU.BIL)	   
			   <span id="$span4">$BU.NAMA_NEGERI</span>,
               #if($BU_ID_NEGERI != "")
               <script>			   
			   highlight_item(returnDropDownSelectedText_value('BU_ID_NEGERI'),'$span4');
               </script>
               #end
               <br />
               #set($span5 = "span5listBU"+$BU.BIL)	  
               <span id="$span5" > 
               $BU.NAMA_UNIT 
               </span>
               #if($BU_ID_NEGERI != "")
               <script>			   
			   highlight_item(returnDropDownSelectedText_value('BU_ID_UNIT'),'$span5');
               </script>
			   #end
			   </td>
			   
               <td class="$BU.rowCss"  align="left" valign="top">		   
			   $BU.DAERAH_JAGAAN
			   </td>
			   <td class="$BU.rowCss"  align="center" valign="top">		
               #set($span6 = "span6listBU"+$BU.BIL)	
               <span id="$span6" >      
			   $BU.STATUS
               </span>
               #if($BU_ID_STATUS != "")
               <script>			   
			   highlight_item(returnDropDownSelectedText_value('BU_ID_STATUS'),'$span6');
               </script>
			   #end
			   </td>
			   
			   <td class="$BU.rowCss"  align="center" valign="top">
			  
			   
			   <a href="javascript:doDivAjaxCall$formname('div_rowBU$BU.ID_PERMOHONANBANTUUNIT','editBU','ID_PERMOHONANBANTUUNIT=$BU.ID_PERMOHONANBANTUUNIT&BIL=$BU.BIL&rowCss=$BU.rowCss');"><img title="Kemaskini" src="../img/edit.gif" border="0"></a>

	   		   #if($BU.ID_STATUS == "1")
	   		   <a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_SenaraiBU','deleteBU','ID_PERMOHONANBANTUUNIT=$BU.ID_PERMOHONANBANTUUNIT');}"><img title="Hapus"  src="../img/delete.gif" border="0"></a>
	   		  	#end
			   </td>	   
		</tr>
		
		#end
	#else
	<tr >
		   <td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
		     
	</tr>
    <script>
	var butoncetakCT =  document.getElementById('cmdCetakBU');
	if (typeof(butoncetakCT) != 'undefined' && butoncetakCT != null)
    {
    	butoncetakCT.style.display = "none";
    }
	</script>
	#end
	</table>


</td>
</tr>
</table>
</fieldset>
<br>
