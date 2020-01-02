<input name="CHECK_ADA_AT_$internalType$USER_ID" type="hidden" id="CHECK_ADA_AT_$internalType$USER_ID" value="$adaAT"  />
   

#if($adaAT=="Y")
<br>


	<fieldset id="div_carianAT_$internalType$USER_ID">
	<legend><b>AKTIVITI / AUDITTRAIL</b>&nbsp;&nbsp;(JUMLAH KESELURUHAN AKTVITI : <b>$totalAT</b>)</legend>
	
	<table width="100%"  border="0" cellspacing="1" cellpadding="2"  > 
	<!--  
	<tr >
		   <td  align="left" valign="top" colspan="8" style="border-bottom:1pt solid black;" >
		   <b>AKTIVITI / AUDITTRAIL</b>&nbsp;&nbsp;(JUMLAH KESELURUHAN AKTVITI : <b>$totalAT</b>)
		   <br>		  
		   </td>
	</tr>
	-->
	
	<tr >
		  <td  align="left" valign="top" colspan="8"  >
		   Carian Terperinci : 
		   <input type="text" size="50" id="carianTerperinciAT_$internalType$USER_ID" name="carianTerperinciAT_$internalType$USER_ID" value="$carianTerperinciAT" 
		   onkeypress="if(event.keyCode == 13){ if(checkCarianAT('$internalType','$USER_ID') == true){doDivAjaxCall$formname('div_ListAT$internalType$USER_ID','carianUtamaAT','USER_ID=$USER_ID&internalType=$internalType');} return false; }" >
		 &nbsp;
		   Dari : <input name="TARIKH_MULA_AT_$internalType$USER_ID" type="text" id="TARIKH_MULA_AT_$internalType$USER_ID" style="text-transform:uppercase;" value="$TARIKH_MULA_AT" size="15" maxlength="15" 
		   onkeypress="if(event.keyCode == 13){ if(checkCarianAT('$internalType','$USER_ID') == true){doDivAjaxCall$formname('div_ListAT$internalType$USER_ID','carianUtamaAT','USER_ID=$USER_ID&internalType=$internalType');} return false; }"
		   />
       <a href="javascript:displayDatePicker('TARIKH_MULA_AT_$internalType$USER_ID',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
       &nbsp;
      	   Sehingga :
      	   <input name="TARIKH_AKHIR_AT_$internalType$USER_ID" type="text" id="TARIKH_AKHIR_AT_$internalType$USER_ID" style="text-transform:uppercase;" value="$TARIKH_AKHIR_AT" size="15" maxlength="15"  
      	   onkeypress="if(event.keyCode == 13){ if(checkCarianAT('$internalType','$USER_ID') == true){doDivAjaxCall$formname('div_ListAT$internalType$USER_ID','carianUtamaAT','USER_ID=$USER_ID&internalType=$internalType');} return false; }"
      	   />
       <a href="javascript:displayDatePicker('TARIKH_AKHIR_AT_$internalType$USER_ID',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
      &nbsp;
      <input type="button" id="cmdCariPengguna" name="cmdCariPengguna" value="Cari" onClick="if(checkCarianAT('$internalType','$USER_ID') == true){doDivAjaxCall$formname('div_ListAT$internalType$USER_ID','carianUtamaAT','USER_ID=$USER_ID&internalType=$internalType');}" >
	  <input type="button" id="cmdBatalCariPengguna" name="cmdBatalCariPengguna" value="Batal" onClick="document.getElementById('TARIKH_AKHIR_AT_$internalType$USER_ID').value='';document.getElementById('TARIKH_MULA_AT_$internalType$USER_ID').value='';document.getElementById('carianTerperinciAT_$internalType$USER_ID').value='';doDivAjaxCall$formname('div_ListAT$internalType$USER_ID','carianUtamaAT','USER_ID=$USER_ID&internalType=$internalType');" >
      #if($listPengunaAT.size()==0)
      &nbsp;<span class="blink"><font color="red">Tiada Rekod</font> </span>
      #elseif($listPengunaAT.size()>0)
      	   <br><br>
		   #parse("app/admin/UV3/record_paging_V3_AT.jsp")
	  #end

		   </td>
		     
	</tr>
	
	#if($listPengunaAT.size()>0)
	<script>
	var element =  document.getElementById('show_cb_AT_'+'$internalType'+'$USER_ID');
	if (typeof(element) != 'undefined' && element != null)
    {
    	element.style.display = "";
    }
    </script>
	<tr class="table_header">
		   <td  align="center" valign="top"><b>BIL.</b></td>
		   <td   align="left" valign="top"><b>NO. FAIL / HAKMILIK / LOT</b></td>
		   <td   align="left" valign="top"><b>AKTIVITI</b></td>
		   <td   align="left" valign="top"><b>TRANSAKSI</b></td>
		   <td  align="left" valign="top"><b>MODUL</b></td>
		   <td   align="left" valign="top"><b>MASA</b></td>
		   <td   align="left" valign="top"><b>IP ADDRESS</b></td>
		   <td   align="left" valign="top"><b>BAHAGIAN</b></td>
	</tr>
	
		#foreach($userAT in $listPengunaAT)
		<tr >
			   <td class="$userAT.rowCss"  align="center" valign="top" >$userAT.BIL </td>
			   <td class="$userAT.rowCss"  align="left" valign="top">
			   
			   #set($span1 = "span1listPengunaAT"+$userAT.BIL)
			   <span id="$span1"> 
			   $userAT.NO_FAIL
			   </span>
			   
			   </td>
			   <td  class="$userAT.rowCss"  align="left" valign="top">
			   
			   #set($span2 = "span2listPengunaAT"+$userAT.BIL)
			   <span id="$span2"> 
			   $userAT.KETERANGAN_AKTIVITI
			   </span>
			   
			   </td>
			   
			   
			   <td  class="$userAT.rowCss"  align="left" valign="top">
			   
			   #set($span3 = "span3listPengunaAT"+$userAT.BIL)
			   <span id="$span3"> 
			   $userAT.JENIS_AKTIVITI
			   </span>
			   
			   </td>
			   
			 
			   </td>
			   <td  class="$userAT.rowCss"  align="left" valign="top">
			   
			   #set($span4 = "span4listPengunaAT"+$userAT.BIL)
			   <span id="$span4">  
			   $userAT.NAMA_MODUL
			   </span>
			   
			   </td>		   
			   
			   <td  class="$userAT.rowCss"  align="left" valign="top">			   
			   $userAT.MASA_AKTIVITI			  
			   </td>
			   
			   <td class="$userAT.rowCss"  align="left" valign="top">
			   #set($span5 = "span5listPengunaAT"+$userAT.BIL)
			   <span id="$span5"> 
			   $userAT.IP_ADDRESS
			   </span>
			   </td>
			   
			   <td  class="$userAT.rowCss"  align="left" valign="top">
			   #set($span6 = "span6listPengunaAT"+$userAT.BIL)
			   <span id="$span6"> 
			   $userAT.BAHAGIAN
			   </span>
			   #set($span7 = "span7listPengunaAT"+$userAT.BIL)
			   <span id="$span7"> 
			   </span>
			   </td>
			   
			   
		</tr>
		
		#end
		#else
		<script>
		var element =  document.getElementById('show_cb_AT_'+'$internalType'+'$USER_ID');
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
	
	
	<div id="div_ATforPrint_$internalType$USER_ID"  style="display:none">
	
	<table width="100%"  
	style="border-collapse: collapse;"  cellspacing="1" cellpadding="2"  width="100%" > 
	<tr >
		   <td  align="left" valign="top" colspan="8"  >
		   <b>AKTIVITI / AUDITTRAIL</b>
		   &nbsp;&nbsp;
		   #if($TARIKH_MULA_AT!="" && $TARIKH_AKHIR_AT!="")
		   (DARI : $TARIKH_MULA_AT&nbsp;SEHINGGA : $TARIKH_AKHIR_AT)
		   #elseif($TARIKH_MULA_AT!="" && $TARIKH_AKHIR_AT=="")
		   (DARI : $TARIKH_MULA_AT&nbsp;SEHINGGA : $TARIKH_CURRENT)
		   #elseif($TARIKH_MULA_AT=="" && $TARIKH_AKHIR_AT!="")
		   (SEHINGGA : $TARIKH_AKHIR_AT)
		   #end		   
		   </td>
	</tr>
	</table>
	<table width="100%"  
	style=" border-collapse: collapse;"  cellspacing="1" cellpadding="2"  width="100%" > 	
	
	#if($listPengunaATforPrint.size()>0)
	<thead>
	<tr >
		   <th style="border: 1px solid black;font-size:75%;"  align="center" valign="top"><b>BIL.</b></th>
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>NO. FAIL / HAKMILIK / LOT</b></th>
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>AKTIVITI</b></th>
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>TRANSAKSI</b></th>
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>MODUL</b></th>
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>MASA</b></th>
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>IP ADDRESS</b></th>
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>BAHAGIAN</b></th>
	</tr>
	</thead>
	
		#foreach($userAT in $listPengunaATforPrint)
		<tr class="page-break" >
			   <td style="border: 1px solid black;font-size:75%;"   align="center" valign="top" >$userAT.BIL </td>
			   <td style="border: 1px solid black;font-size:75%;"   align="left" valign="top">
			   $userAT.NO_FAIL			   
			   </td>
			   <td style="border: 1px solid black; font-size:75%;"   align="left" valign="top">
			   $userAT.KETERANGAN_AKTIVITI
			   </td>
			   <td style="border: 1px solid black;font-size:75%;"   align="left" valign="top">
			   $userAT.JENIS_AKTIVITI
			   </td>
			   <td style="border: 1px solid black;font-size:75%;"   align="left" valign="top">
			   $userAT.NAMA_MODUL
			   </td>		   
			   <td style="border: 1px solid black;font-size:75%;"  align="left" valign="top">			   
			   $userAT.MASA_AKTIVITI			  
			   </td>
			   <td style="border: 1px solid black;font-size:75%;" align="left" valign="top">			   
			   $userAT.IP_ADDRESS
			   </td>			   
			   <td style="border: 1px solid black;font-size:75%;"   align="left" valign="top">
			   $userAT.BAHAGIAN			   
			   </td>
			   
			   
		</tr>
		
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


<script>   
//lepas abis load AT, call list PLA plak
				 $jquery(document).ready(function () {
					  doDivAjaxCall$formname('div_ListPLA$internalType$USER_ID','carianUtamaPLA','&carianTerperinciPLA=&TARIKH_MULA_PLA=&TARIKH_AKHIR_PLA=&USER_ID=$USER_ID&internalType=$internalType');			 	  
				  });
				</script>

