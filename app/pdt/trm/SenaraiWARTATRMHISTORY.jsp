
#if($command == "displayUtamaHISTORY" )
<script>
//alert('div_row$ID_WARTATRM');
if( $jquery('#'+'div_row$ID_WARTATRM').length ) 
{
	window.scrollTo(0, $jquery('#'+'div_row$ID_WARTATRM').offset().top-10);
}
</script>
#else
<script>
//alert('div_row$ID_WARTATRM');
if( $jquery('#'+'div_ListHISTORY$ID_WARTATRM').length ) 
{
	window.scrollTo(0, $jquery('#'+'div_ListHISTORY$ID_WARTATRM').offset().top-10);
}
</script>
#end

<!--
#if($FLAG_DEFAULT_HISTORY != "Y") 
<script>
if( $jquery('#'+'div_ListHISTORY$ID_WARTATRM').length ) 
{
	//window.scrollTo(0, $jquery('#'+'div_ListHISTORY$ID_WARTATRM').offset().top-10);
}
</script>
#else
<script>
//alert('div_row$ID_WARTATRM');
if( $jquery('#'+'div_row$ID_WARTATRM').length ) 
{
	//window.scrollTo(0, $jquery('#'+'div_row$ID_WARTATRM').offset().top-10);
}
</script>
#end
-->
#if($adaHISTORY=="Y")
	<br><table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr class="table_header" >
		<td width="2%" class="underline_td_sub">
		</td>
		<td width="96%" class="underline_td_sub" onclick="javascript:setTableFade('div_SHcarianHISTORY_$ID_WARTATRM')" >
		<b>SEJARAH PERUBAHAN WARTA</b>&nbsp;&nbsp;(JUMLAH KESELURUHAN AKTVITI : <b>$totalHISTORY</b>)		
		</td>
        <td width="2%" class="underline_td_sub" ></td>
		</tr>
		</table>  
        
	<div id="div_SHcarianHISTORY_$ID_WARTATRM">
	<fieldset id="div_carianHISTORY_$ID_WARTATRM">
	
	<table  border="0" cellspacing="1" cellpadding="2"  width="100%" > 
	
	
	<tr >
		  <td  align="left" valign="top" colspan="8"  >
		   Carian Terperinci : 
		   <input type="text" size="50" style="text-transform:uppercase;" id="carianTerperinciHISTORY_$ID_WARTATRM" name="carianTerperinciHISTORY_$ID_WARTATRM" value="$carianTerperinciHISTORY" 
		   onkeypress="if(event.keyCode == 13){ if(checkCarianHISTORY('$ID_WARTATRM') == true){doDivAjaxCall$formname('div_ListHISTORY$ID_WARTATRM','carianUtamaHISTORY','ID_WARTATRM=$ID_WARTATRM');} return false; }" >
		 &nbsp;
		   Dari : <input name="TARIKH_MULA_HISTORY_$ID_WARTATRM" type="text" id="TARIKH_MULA_HISTORY_$ID_WARTATRM" style="text-transform:uppercase;" value="$TARIKH_MULA_HISTORY" size="15" maxlength="15" 
           onKeyUp="checkFormatDate_V4(this,this.value,'span_CHECK_TARIKH_MULA_HISTORY_$ID_WARTATRM');"
        onBlur="checkFormatDate_V4(this,this.value,'span_CHECK_TARIKH_MULA_HISTORY_$ID_WARTATRM');" 
        onFocus="checkFormatDate_V4(this,this.value,'span_CHECK_TARIKH_MULA_HISTORY_$ID_WARTATRM');" 			
		   onkeypress="if(event.keyCode == 13){ if(checkCarianHISTORY('$ID_WARTATRM') == true){doDivAjaxCall$formname('div_ListHISTORY$ID_WARTATRM','carianUtamaHISTORY','ID_WARTATRM=$ID_WARTATRM');} return false; }"
		   />
       <a href="javascript:displayDatePicker('TARIKH_MULA_HISTORY_$ID_WARTATRM',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
        <span id="span_CHECK_TARIKH_MULA_HISTORY_$ID_WARTATRM">
       <input type='hidden' id='CHECK_TARIKH_MULA_HISTORY_$ID_WARTATRM' name='CHECK_TARIKH_MULA_HISTORY_$ID_WARTATRM' value='true' >
       </span>
       &nbsp;
      	   Sehingga :
      	   <input name="TARIKH_AKHIR_HISTORY_$ID_WARTATRM" type="text" id="TARIKH_AKHIR_HISTORY_$ID_WARTATRM" style="text-transform:uppercase;" value="$TARIKH_AKHIR_HISTORY" size="15" maxlength="15"  
           onKeyUp="checkFormatDate_V4(this,this.value,'span_CHECK_TARIKH_AKHIR_HISTORY_$ID_WARTATRM');"
        onBlur="checkFormatDate_V4(this,this.value,'span_CHECK_TARIKH_AKHIR_HISTORY_$ID_WARTATRM');" 
        onFocus="checkFormatDate_V4(this,this.value,'span_CHECK_TARIKH_AKHIR_HISTORY_$ID_WARTATRM');" 			
      	   onkeypress="if(event.keyCode == 13){ if(checkCarianHISTORY('$ID_WARTATRM') == true){doDivAjaxCall$formname('div_ListHISTORY$ID_WARTATRM','carianUtamaHISTORY','ID_WARTATRM=$ID_WARTATRM');} return false; }"
      	   />
       <a href="javascript:displayDatePicker('TARIKH_AKHIR_HISTORY_$ID_WARTATRM',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
       <span id="span_CHECK_TARIKH_AKHIR_HISTORY_$ID_WARTATRM">
       <input type='hidden' id='CHECK_TARIKH_AKHIR_HISTORY_$ID_WARTATRM' name='CHECK_TARIKH_AKHIR_HISTORY_$ID_WARTATRM' value='true' >
       </span> 
      &nbsp;
      <input type="button" id="cmdCariPengguna" name="cmdCariPengguna" value="Cari" onClick="if(checkCarianHISTORY('$ID_WARTATRM') == true){doDivAjaxCall$formname('div_ListHISTORY$ID_WARTATRM','carianUtamaHISTORY','ID_WARTATRM=$ID_WARTATRM');}" >
	  <input type="button" id="cmdBatalCariPengguna" name="cmdBatalCariPengguna" value="Batal" onClick="document.getElementById('TARIKH_AKHIR_HISTORY_$ID_WARTATRM').value='';document.getElementById('TARIKH_MULA_HISTORY_$ID_WARTATRM').value='';document.getElementById('carianTerperinciHISTORY_$ID_WARTATRM').value='';doDivAjaxCall$formname('div_ListHISTORY$ID_WARTATRM','carianUtamaHISTORY','ID_WARTATRM=$ID_WARTATRM');" >
      #if($listWARTATRMHISTORY.size()==0)
      &nbsp;<span class="blink"><font color="red">Tiada Rekod</font> </span>
      #elseif($listWARTATRMHISTORY.size()>0)
      	   <br><br>
		   #parse("app/pdt/trm/record_paging_HISTORY.jsp")
	  #end

		   </td>
		     
	</tr>
	
	#if($listWARTATRMHISTORY.size()>0)
	
	<tr class="table_header">
		   <td  align="center" valign="top" width="3%"><b>BIL.</b></td>
		   <td   align="left" valign="top" width="27%"><b>AKTIVITI</b></td>
		   <td   align="left" valign="top" width="30%"><b>TARIKH TRANSAKSI</b></td>
		   <td   align="left" valign="top" width="40%"><b>DIEDIT OLEH</b></td>
	</tr>
	
		#foreach($wartaHISTORY in $listWARTATRMHISTORY)
		<tr id="div_mainRowHISTORY_$wartaHISTORY.ID_SEJARAHWTRMUTAMA" >
			   <td class="$wartaHISTORY.rowCss"  align="center" valign="top" >$wartaHISTORY.BIL </td>
			   <td class="$wartaHISTORY.rowCss"  align="left" valign="top">
			   
               
               
               
               #set($span1= "span1listWARTATRMHISTORY"+$wartaHISTORY.BIL)	   
			   <span id="$span1">
               
               
               <a href="javascript:doDivAjaxCall$formname('div_viewHISTORY_$wartaHISTORY.ID_SEJARAHWTRMUTAMA','viewHISTORY_SUB','&ID_SEJARAHWTRMUTAMA=$wartaHISTORY.ID_SEJARAHWTRMUTAMA&AKTIVITI=$wartaHISTORY.AKTIVITI&TARIKH_MASUK=$wartaHISTORY.TARIKH_MASUK&ID_WARTATRM=$ID_WARTATRM');"><u><font color="blue">$wartaHISTORY.AKTIVITI</font></u></a>
               
               </span>
               #if($carianTerperinciHISTORY != "")
               <script>
			  	highlight_item('$carianTerperinciHISTORY','$span1')		   
			   </script>
               #end
			   
			   </td>
			   <td  class="$wartaHISTORY.rowCss"  align="left" valign="top">
			   
			   $wartaHISTORY.TARIKH_MASUK
			  
			   
			   </td>
			   
			   
			   <td  class="$wartaHISTORY.rowCss"  align="left" valign="top">
			   
			   
			   $wartaHISTORY.USER_NAME
			   
			   </td>
			   
			   		 
		</tr>
		<tr id="div_viewHISTORY_$wartaHISTORY.ID_SEJARAHWTRMUTAMA" style="display:none">
		<td class="$userPLA.rowCss" colspan="9"  >
		
		</td>
		</tr>
		
		#end
		
        #else
		
		<tr>
		<td colspan="8" style="display:none">
		Tiada Rekod
		</td>
		</tr>
		#end	
	</table>
	
	</fieldset>
	<br />
    </div>
#end



#if($mode == "view" &&  $ID_WARTATRM != "")
    <script>
	doDivAjaxCall3$formname('div_editTRM$ID_WARTATRM','reloadTRMFromHistory','ID_WARTATRM=$ID_WARTATRM&BIL=$BIL&rowCss=$rowCss&commandFrom=$commandFrom');
    </script>
#end
