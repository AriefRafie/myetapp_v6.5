<input name="CHECK_ADA_HISTORY_$internalType$USER_ID" type="hidden" id="CHECK_ADA_HISTORY_$internalType$USER_ID" value="$adaHISTORY"  />
   

#if($adaHISTORY=="Y")
<br>


	<fieldset id="div_carianHISTORY_$internalType$USER_ID">
	<legend><b>SEJARAH PERUBAHAN MAKLUMAT PENGGUNA</b>&nbsp;&nbsp;(JUMLAH KESELURUHAN AKTVITI : <b>$totalHISTORY</b>)</legend>
	
	<table width="100%"  border="0" cellspacing="1" cellpadding="2"  width="100%" > 
	<!--  
	<tr >
		   <td  align="left" valign="top" colspan="8" style="border-bottom:1pt solid black;" >
		   <b>AKTIVITI / AUDITTRAIL</b>&nbsp;&nbsp;(JUMLAH KESELURUHAN AKTVITI : <b>$totalHISTORY</b>)
		   <br>		  
		   </td>
	</tr>
	-->
	
	<tr >
		  <td  align="left" valign="top" colspan="8"  >
		   Carian Terperinci : 
		   <input type="text" size="50" id="carianTerperinciHISTORY_$internalType$USER_ID" name="carianTerperinciHISTORY_$internalType$USER_ID" value="$carianTerperinciHISTORY" 
		   onkeypress="if(event.keyCode == 13){ if(checkCarianHISTORY('$internalType','$USER_ID') == true){doDivAjaxCall$formname('div_ListHISTORY$internalType$USER_ID','carianUtamaHISTORY','USER_ID=$USER_ID&internalType=$internalType');} return false; }" >
		 &nbsp;
		   Dari : <input name="TARIKH_MULA_HISTORY_$internalType$USER_ID" type="text" id="TARIKH_MULA_HISTORY_$internalType$USER_ID" style="text-transform:uppercase;" value="$TARIKH_MULA_HISTORY" size="15" maxlength="15" 
		   onkeypress="if(event.keyCode == 13){ if(checkCarianHISTORY('$internalType','$USER_ID') == true){doDivAjaxCall$formname('div_ListHISTORY$internalType$USER_ID','carianUtamaHISTORY','USER_ID=$USER_ID&internalType=$internalType');} return false; }"
		   />
       <a href="javascript:displayDatePicker('TARIKH_MULA_HISTORY_$internalType$USER_ID',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
       &nbsp;
      	   Sehingga :
      	   <input name="TARIKH_AKHIR_HISTORY_$internalType$USER_ID" type="text" id="TARIKH_AKHIR_HISTORY_$internalType$USER_ID" style="text-transform:uppercase;" value="$TARIKH_AKHIR_HISTORY" size="15" maxlength="15"  
      	   onkeypress="if(event.keyCode == 13){ if(checkCarianHISTORY('$internalType','$USER_ID') == true){doDivAjaxCall$formname('div_ListHISTORY$internalType$USER_ID','carianUtamaHISTORY','USER_ID=$USER_ID&internalType=$internalType');} return false; }"
      	   />
       <a href="javascript:displayDatePicker('TARIKH_AKHIR_HISTORY_$internalType$USER_ID',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
      &nbsp;
      <input type="button" id="cmdCariPengguna" name="cmdCariPengguna" value="Cari" onClick="if(checkCarianHISTORY('$internalType','$USER_ID') == true){doDivAjaxCall$formname('div_ListHISTORY$internalType$USER_ID','carianUtamaHISTORY','USER_ID=$USER_ID&internalType=$internalType');}" >
	  <input type="button" id="cmdBatalCariPengguna" name="cmdBatalCariPengguna" value="Batal" onClick="document.getElementById('TARIKH_AKHIR_HISTORY_$internalType$USER_ID').value='';document.getElementById('TARIKH_MULA_HISTORY_$internalType$USER_ID').value='';document.getElementById('carianTerperinciHISTORY_$internalType$USER_ID').value='';doDivAjaxCall$formname('div_ListHISTORY$internalType$USER_ID','carianUtamaHISTORY','USER_ID=$USER_ID&internalType=$internalType');" >
      #if($listPengunaHISTORY.size()==0)
      &nbsp;<span class="blink"><font color="red">Tiada Rekod</font> </span>
      #elseif($listPengunaHISTORY.size()>0)
      	   <br><br>
		   #parse("app/admin/UV3/record_paging_V3_HISTORY.jsp")
	  #end

		   </td>
		     
	</tr>
	
	#if($listPengunaHISTORY.size()>0)
	<script>
	var element =  document.getElementById('show_cb_HISTORY_'+'$internalType'+'$USER_ID');
	if (typeof(element) != 'undefined' && element != null)
    {
    	element.style.display = "";
    }
    </script>
	<tr class="table_header">
		   <td  align="center" valign="top" width="5%"><b>BIL.</b></td>
		   <td   align="left" valign="top"><b>AKTIVITI</b></td>
		   <td   align="left" valign="top"><b>TARIKH TRANSAKSI</b></td>
		   <td   align="left" valign="top"><b>DIEDIT OLEH</b></td>
		   <td  align="center" valign="top" width="10%"><b>TINDAKAN</b></td>
	</tr>
	
		#foreach($userHISTORY in $listPengunaHISTORY)
		<tr id="div_mainRowHISTORY_$internalType$userHISTORY.ID_SEJARAHPENGGUNAUTAMA" >
			   <td class="$userHISTORY.rowCss"  align="center" valign="top" >$userHISTORY.BIL </td>
			   <td class="$userHISTORY.rowCss"  align="left" valign="top">
			   
			   #set($span1 = "span1listPengunaHISTORY"+$userHISTORY.BIL)
			   <span id="$span1"> 
			   $userHISTORY.AKTIVITI
			   </span>
			   
			   </td>
			   <td  class="$userHISTORY.rowCss"  align="left" valign="top">
			   
			   #set($span2 = "span2listPengunaHISTORY"+$userHISTORY.BIL)
			   <span id="$span2"> 
			   $userHISTORY.TARIKH_MASUK
			   </span>
			   
			   </td>
			   
			   
			   <td  class="$userHISTORY.rowCss"  align="left" valign="top">
			   
			   #set($span3 = "span3listPengunaHISTORY"+$userHISTORY.BIL)
			   <span id="$span3"> 
			   $userHISTORY.USER_NAME
			   </span>
			   
			   
			   
			   #set($span4 = "span4listPengunaHISTORY"+$userHISTORY.BIL)
			   <span id="$span4"></span>
			   #set($span5 = "span5listPengunaHISTORY"+$userHISTORY.BIL)
			   <span id="$span5"></span>
			   #set($span6 = "span6listPengunaHISTORY"+$userHISTORY.BIL)
			   <span id="$span6"></span>
			   #set($span7 = "span7listPengunaHISTORY"+$userHISTORY.BIL)
			   <span id="$span7"></span>
			   
			   </td>
			   
			   <td class="$userHISTORY.rowCss"  align="center" valign="top">
			   <a href="javascript:doDivAjaxCall$formname('div_viewHISTORY_$internalType$userHISTORY.ID_SEJARAHPENGGUNAUTAMA','viewHISTORY_SUB','internalType=$internalType&ID_SEJARAHPENGGUNAUTAMA=$userHISTORY.ID_SEJARAHPENGGUNAUTAMA&AKTIVITI=$userHISTORY.AKTIVITI&TARIKH_MASUK=$userHISTORY.TARIKH_MASUK&USER_ID=$USER_ID');"><img title="Papar" src="../img/edit.gif" border="0"></a>
	   		   </td>			 
		</tr>
		<tr id="div_viewHISTORY_$internalType$userHISTORY.ID_SEJARAHPENGGUNAUTAMA" style="display:none">
		<td class="$userPLA.rowCss" colspan="9"  >
		
		</td>
		</tr>
		
		#end
		#else
		<script>
		var element =  document.getElementById('show_cb_HISTORY_'+'$internalType'+'$USER_ID');
	    if (typeof(element) != 'undefined' && element != null)
	    {
	    	element.style.display = "none";
	    }
	    </script>
		<tr>
		<td colspan="8" style="display:none">
		Tiada Rekod
		</td>
		</tr>
		#end	
	</table>
	
	</fieldset>
	
		
	<div id="div_HISTORYforPrint_$internalType$USER_ID" style="display:none" >
	
	<table width="100%"  
	style="border-collapse: collapse;"  cellspacing="1" cellpadding="2"  width="100%" > 
	<tr >
		   <td  align="left" valign="top" colspan="8"  >
		   <b>SEJARAH PERUBAHAN MAKLUMAT PENGGUNA</b>
		   &nbsp;&nbsp;
		   #if($TARIKH_MULA_HISTORY!="" && $TARIKH_AKHIR_HISTORY!="")
		   (DARI : $TARIKH_MULA_HISTORY&nbsp;SEHINGGA : $TARIKH_AKHIR_HISTORY)
		   #elseif($TARIKH_MULA_HISTORY!="" && $TARIKH_AKHIR_HISTORY=="")
		   (DARI : $TARIKH_MULA_HISTORY&nbsp;SEHINGGA : $TARIKH_CURRENT)
		   #elseif($TARIKH_MULA_HISTORY=="" && $TARIKH_AKHIR_HISTORY!="")
		   (SEHINGGA : $TARIKH_AKHIR_HISTORY)
		   #end		   
		   </td>
	</tr>
	</table>
	<table width="100%"  
	style=" border-collapse: collapse;"  cellspacing="1" cellpadding="2"   > 	
	
	#if($listPengunaHISTORYforPrint.size()>0)
	<thead>
	<tr >
		   <th style="border: 1px solid black;font-size:75%;" width="5%"  align="center" valign="top"><b>BIL.</b></th>
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>AKTIVITI [TARIKH TRANSAKSI]</b></th>
		   	</tr>
	</thead>	
		#foreach($userHISTORY in $listPengunaHISTORYforPrint)
		
		#if($userHISTORY.LAYER == "1")
		<tr  class="page-break"  >
			   <td style="border: 1px solid black;font-size:75%;" width="5%"   align="center" valign="top" >$userHISTORY.BIL </td>
			   <td style="border: 1px solid black;font-size:75%;"   align="left" valign="top">
			   $userHISTORY.AKTIVITI [$userHISTORY.TARIKH_MASUK]
			   </td>  	   
		</tr>			
		#elseif($userHISTORY.LAYER == "2")
		<tr >
			   <td style="border: 1px solid black;font-size:75%;" width="5%"   align="center" valign="top" ></td>
			   <td style="border: 1px solid black;font-size:75%;"   align="left" valign="top">
				   <table width="100%" style=" border-collapse: collapse;"  cellspacing="1" cellpadding="2">
				   <tr>
				   <td style="font-size:75%;" width="3%" align="right" valign="top" >$userHISTORY.BIL</td>
				   <td style="font-size:75%;" width="1%"  valign="top" ></td>
				   <td style="font-size:75%;" width="20%" align="left" valign="top" >$userHISTORY.REKOD_LABEL</td>
				   <td style="font-size:75%;" width="3%"  align="center" valign="top" >:</td>
				   <td style="font-size:75%;" width="35%" align="left" valign="top" >$userHISTORY.REKOD_SEBELUM</td>
				   <td style="font-size:75%;" width="3%"  align="center" valign="top" >>></td>
				   <td style="font-size:75%;" width="35%" align="left" valign="top" >$userHISTORY.REKOD_SELEPAS</td>
				   </tr>
				   </table>
			  </td>  	   
		</tr>			
		#end	
		#end
		#else		
		<tr>
		<td colspan="8">
		Tiada Rekod
		</td>
		</tr>
		#end	
	</table>
</div>

#end


