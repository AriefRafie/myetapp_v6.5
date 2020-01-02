<input name="CHECK_ADA_PLA_$internalType$USER_ID" type="hidden" id="CHECK_ADA_PLA_$internalType$USER_ID" value="$adaPLA"  />
   

#if($adaPLA=="Y")
<br>

	
	<fieldset id="div_carianPLA_$internalType$USER_ID" >
	<legend><b>PENGURUSAN LOG ADUAN</b>&nbsp;&nbsp;(JUMLAH KESELURUHAN ADUAN : <b>$totalPLA</b>)</legend>
	
	<table width="100%"  border="0" cellspacing="1" cellpadding="1" width="100%" > 
	<!--  
	<tr >
		   <td  align="left" valign="top" colspan="9" style="border-bottom:1pt solid black;" >
		   <b>PENGURUSAN LOG ADUAN</b>&nbsp;&nbsp;(JUMLAH KESELURUHAN ADUAN : <b>$totalPLA</b>)
		   <br>		  
		   </td>
	</tr>
	-->
	
	<tr >
		  <td  align="left" valign="top" colspan="9"  >
		   Carian Terperinci : 
		   <input type="text" size="50" id="carianTerperinciPLA_$internalType$USER_ID" name="carianTerperinciPLA_$internalType$USER_ID" value="$carianTerperinciPLA" 
		   onkeypress="if(event.keyCode == 13){ if(checkCarianPLA('$internalType','$USER_ID') == true){doDivAjaxCall$formname('div_ListPLA$internalType$USER_ID','carianUtamaPLA','USER_ID=$USER_ID&internalType=$internalType');} return false; }" >
		 &nbsp;
		   Dari : <input name="TARIKH_MULA_PLA_$internalType$USER_ID" type="text" id="TARIKH_MULA_PLA_$internalType$USER_ID" style="text-transform:uppercase;" value="$TARIKH_MULA_PLA" size="15" maxlength="15" 
		   onkeypress="if(event.keyCode == 13){ if(checkCarianPLA('$internalType','$USER_ID') == true){doDivAjaxCall$formname('div_ListPLA$internalType$USER_ID','carianUtamaPLA','USER_ID=$USER_ID&internalType=$internalType');} return false; }"
		   />
       <a href="javascript:displayDatePicker('TARIKH_MULA_PLA_$internalType$USER_ID',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
       &nbsp;
      	   Sehingga :
      	   <input name="TARIKH_AKHIR_PLA_$internalType$USER_ID" type="text" id="TARIKH_AKHIR_PLA_$internalType$USER_ID" style="text-transform:uppercase;" value="$TARIKH_AKHIR_PLA" size="15" maxlength="15"  
      	   onkeypress="if(event.keyCode == 13){ if(checkCarianPLA('$internalType','$USER_ID') == true){doDivAjaxCall$formname('div_ListPLA$internalType$USER_ID','carianUtamaPLA','USER_ID=$USER_ID&internalType=$internalType');} return false; }"
      	   />
       <a href="javascript:displayDatePicker('TARIKH_AKHIR_PLA_$internalType$USER_ID',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
      &nbsp;
      <input type="button" id="cmdCariPengguna" name="cmdCariPengguna" value="Cari" onClick="if(checkCarianPLA('$internalType','$USER_ID') == true){doDivAjaxCall$formname('div_ListPLA$internalType$USER_ID','carianUtamaPLA','USER_ID=$USER_ID&internalType=$internalType');}" >
	  <input type="button" id="cmdBPLAalCariPengguna" name="cmdBPLAalCariPengguna" value="Batal" onClick="document.getElementById('TARIKH_AKHIR_PLA_$internalType$USER_ID').value='';document.getElementById('TARIKH_MULA_PLA_$internalType$USER_ID').value='';document.getElementById('carianTerperinciPLA_$internalType$USER_ID').value='';doDivAjaxCall$formname('div_ListPLA$internalType$USER_ID','carianUtamaPLA','USER_ID=$USER_ID&internalType=$internalType');" >
      #if($listPengunaPLA.size()==0)
      &nbsp;<span class="blink"><font color="red">Tiada Rekod</font> </span>
      #elseif($listPengunaPLA.size()>0)
      	   <br><br>
		   #parse("app/admin/UV3/record_paging_V3_PLA.jsp")
	  #end

		   </td>
		     
	</tr>
	
	#if($listPengunaPLA.size()>0)
	<script>
	var element =  document.getElementById('show_cb_PLA_'+'$internalType'+'$USER_ID');
    if (typeof(element) != 'undefined' && element != null)
    {
    	element.style.display = "";
    }
    </script>
	
	
	<tr class="table_header">
		   <td  align="center" valign="top"><b>BIL.</b></td>
		   <td  align="left" valign="top"><b>LOG</b></td>
		   <td align="left" valign="top"><b>NO. FAIL</b></td>
		   <td  align="left" valign="top"><b>JENIS ADUAN</b></td>
		   <td  align="left" valign="top"><b>TARIKH ADUAN</b></td>
		   <td  align="left" valign="top"><b>STATUS</b></td>
		   <td  align="left" valign="top"><b>NAMA SKRIN</b></td>
		   <td  align="left" valign="top"><b>MODUL</b></td>
		   <td  align="center" valign="top"><b>TINDAKAN</b></td>
	</tr>
	
		#foreach($userPLA in $listPengunaPLA)
		<tr id="div_mainRowAduan_$internalType$userPLA.ID_ESADUAN" >
			   <td class="$userPLA.rowCss"  align="center" valign="top" >$userPLA.BIL </td>
			   <td class="$userPLA.rowCss"  align="left" valign="top">
			   
			   #set($span1 = "span1listPengunaPLA"+$userPLA.BIL)
			   <span id="$span1"> 
			   $userPLA.LOG_ADUAN
			   </span>
			   
			   </td>
			   <td class="$userPLA.rowCss"  align="left" valign="top">
			   
			   #set($span2 = "span2listPengunaPLA"+$userPLA.BIL)
			   <span id="$span2"> 
			   $userPLA.NO_FAIL
			   </span>
			   
			   </td>
			   
			   
			   <td class="$userPLA.rowCss"  align="left" valign="top">
			   
			   #set($span3 = "span3listPengunaPLA"+$userPLA.BIL)
			   <span id="$span3"> 
			   $userPLA.JENIS_ADUAN
			   </span>
			   
			   </td>
			   
			 
			   <td  class="$userPLA.rowCss"  align="left" valign="top">
			   
			   #set($span4 = "span4listPengunaPLA"+$userPLA.BIL)
			   <span id="$span4">  
			   $userPLA.TARIKH_ADUAN
			   </span>
			   
			   </td>		   
			   
			   
			   
			   <td class="$userPLA.rowCss"  align="left" valign="top">
			   #set($span5 = "span5listPengunaPLA"+$userPLA.BIL)
			   <span id="$span5"> 
			   $userPLA.STATUS
			   </span>
			   </td>
			   
			   <td class="$userPLA.rowCss"  align="left" valign="top">
			   #set($span6 = "span6listPengunaPLA"+$userPLA.BIL)
			   <span id="$span6"> 
			   $userPLA.NAMA_SKRIN
			   </span>
			  
			   </td>
			   
			   <td class="$userPLA.rowCss"  align="left" valign="top">
			   #set($span7 = "span7listPengunaPLA"+$userPLA.BIL)
			   <span id="$span7"> 
			   $userPLA.MODUL
			   </span>
			  
			   </td>
			   
			   <td class="$userPLA.rowCss"  align="center" valign="top">
			   <a href="javascript:doDivAjaxCall$formname('div_viewAduan_$internalType$userPLA.ID_ESADUAN','viewPLA','internalType=$internalType&ID_ESADUAN=$userPLA.ID_ESADUAN');"><img title="Papar" src="../img/edit.gif" border="0"></a>
	   		   </td>
	   		 
			   
			   
		</tr>
		<tr id="div_viewAduan_$internalType$userPLA.ID_ESADUAN" style="display:none">
		<td class="$userPLA.rowCss" colspan="9"  >
		
		</td>
		</tr>
		
		#end
		#else
		<script>
		var element =  document.getElementById('show_cb_PLA_'+'$internalType'+'$USER_ID');
	    if (typeof(element) != 'undefined' && element != null)
	    {
	    	element.style.display = "none";
	    }
	    </script>
		<tr>
		<td colspan="9" style="display:none">
		Tiada Rekod
		</td>
		</tr>
		#end	
	</table>
	</fieldset>
	
	
	
	<div id="div_PLAforPrint_$internalType$USER_ID"  style="display:none">
	
	<table width="100%"  
	style="border-collapse: collapse;"  cellspacing="1" cellpadding="2"  width="100%" > 
	<tr >
		   <td  align="left" valign="top" colspan="9"  >
		   <b>PENGURUSAN LOG ADUAN</b>
		   &nbsp;&nbsp;
		   #if($TARIKH_MULA_PLA!="" && $TARIKH_AKHIR_PLA!="")
		   (DARI : $TARIKH_MULA_PLA&nbsp;SEHINGGA : $TARIKH_AKHIR_PLA)
		   #elseif($TARIKH_MULA_PLA!="" && $TARIKH_AKHIR_PLA=="")
		   (DARI : $TARIKH_MULA_PLA&nbsp;SEHINGGA : $TARIKH_CURRENT)
		   #elseif($TARIKH_MULA_PLA=="" && $TARIKH_AKHIR_PLA!="")
		   (SEHINGGA : $TARIKH_AKHIR_PLA)
		   #end		   
		   </td>
	</tr>
	</table>
	<table width="100%"  
	style=" border-collapse: collapse;"  cellspacing="1" cellpadding="2"  width="100%" > 	
	
	#if($listPengunaPLAforPrint.size()>0)
	<thead>
	<tr >
		   <td style="border: 1px solid black;font-size:75%;"  align="center" valign="top"><b>BIL.</b></td>
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>LOG</b></td>
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>NO. FAIL</b></td>
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>JENIS ADUAN</b></td>
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>TARIKH ADUAN</b></td>
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>STATUS</b></td>
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>NAMA SKRIN</b></td>
		   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>MODUL</b></td>
	</tr>
	</thead>
	
		#foreach($userPLA in $listPengunaPLAforPrint)
		<tr class="page-break" >
			   <td style="border: 1px solid black;font-size:75%;" class="$userPLA.rowCss"  align="center" valign="top" >
			   $userPLA.BIL 
			   </td>
			   
			   <td style="border: 1px solid black;font-size:75%;" class="$userPLA.rowCss"  align="left" valign="top">
			   $userPLA.LOG_ADUAN
			   </td>
			   
			   <td style="border: 1px solid black;font-size:75%;" class="$userPLA.rowCss"  align="left" valign="top">
			   $userPLA.NO_FAIL</td>
			   
			   
			   <td style="border: 1px solid black;font-size:75%;" class="$userPLA.rowCss"  align="left" valign="top">
			   $userPLA.JENIS_ADUAN
			   </td>
			   
			   
			   <td style="border: 1px solid black;font-size:75%;" class="$userPLA.rowCss"  align="left" valign="top">
			   $userPLA.TARIKH_ADUAN
			   </td>
			   
			   <td style="border: 1px solid black;font-size:75%;" class="$userPLA.rowCss"  align="left" valign="top">
			   $userPLA.STATUS
			   </td>
			   
			   <td style="border: 1px solid black;font-size:75%;" class="$userPLA.rowCss"  align="left" valign="top">
			   $userPLA.NAMA_SKRIN
			   </td>
			   
			   <td style="border: 1px solid black;font-size:75%;" class="$userPLA.rowCss"  align="left" valign="top">			   
			   $userPLA.MODUL			  
			   </td>
			   
			   
		</tr>
		
		#end
		#else		
		<tr>
		<td colspan="9">
		Tiada Rekod
		</td>
		</tr>
		#end	
	</table>
</div>

#end
   

<script>   
//lepas abis load PLA, call list HISTORY plak
				 $jquery(document).ready(function () {
					  doDivAjaxCall$formname('div_ListHISTORY$internalType$USER_ID','carianUtamaHISTORY','&carianTerperinciHISTORY=&TARIKH_MULA_HISTORY=&TARIKH_AKHIR_HISTORY=&USER_ID=$USER_ID&internalType=$internalType');			 	  
				  });
</script>

