

<input name="CHECK_ADA_MODUL_$GROUPUNIK$ROLEUNIK" type="hidden" id="CHECK_ADA_MODUL_$GROUPUNIK$ROLEUNIK" value="$adaModul"  />
   

#if($adaModul=="Y")
<br>


	<fieldset id="div_carianModul_$GROUPUNIK$ROLEUNIK">
	<legend><b>JAVA CLASS & MODUL</b>&nbsp;&nbsp;(JUMLAH KESELURUHAN : <b>$totalModul</b>)</legend>
	
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
		   <input type="text" size="50" id="carianTerperinciModul_$GROUPUNIK$ROLEUNIK" name="carianTerperinciModul_$GROUPUNIK$ROLEUNIK" value="$carianTerperinciModul" 
		   onkeypress="if(event.keyCode == 13){ if(checkCarianModul('$GROUPUNIK','$ROLEUNIK') == true){doDivAjaxCall$formname('div_ListModul$GROUPUNIK$ROLEUNIK','carianUtamaModul','ROLEUNIK=$ROLEUNIK&GROUPUNIK=$GROUPUNIK&ROLE_NAME=$ROLE_NAME&scrolPosition='+getPageLocation('$command'));} return false; }" >
		&nbsp;
      <input type="button" id="cmdCariPengguna" name="cmdCariPengguna" value="Cari" onClick="if(checkCarianModul('$GROUPUNIK','$ROLEUNIK') == true){doDivAjaxCall$formname('div_ListModul$GROUPUNIK$ROLEUNIK','carianUtamaModul','ROLEUNIK=$ROLEUNIK&GROUPUNIK=$GROUPUNIK&ROLE_NAME=$ROLE_NAME&scrolPosition='+getPageLocation('$command'));}" >
	  <input type="button" id="cmdBatalCariPengguna" name="cmdBatalCariPengguna" value="Batal" onClick="document.getElementById('carianTerperinciModul_$GROUPUNIK$ROLEUNIK').value='';doDivAjaxCall$formname('div_ListModul$GROUPUNIK$ROLEUNIK','carianUtamaModul','ROLEUNIK=$ROLEUNIK&GROUPUNIK=$GROUPUNIK&ROLE_NAME=$ROLE_NAME&scrolPosition='+getPageLocation('$command'));" >
      #if($listPengunaModul.size()==0)
      &nbsp;<span class="blink"><font color="red">Tiada Rekod</font> </span>
      #elseif($listPengunaModul.size()>0)
      	   <br><br>
		   #parse("app/admin/RoleModule/record_paging_V3_MODUL.jsp")
	  #end

		   </td>
		     
	</tr>
	
	#if($listPengunaModul.size()>0)
	<script>
	var element =  document.getElementById('show_cb_Modul_'+'$GROUPUNIK'+'$ROLEUNIK');
	if (typeof(element) != 'undefined' && element != null)
    {
    	element.style.display = "";
    }
    </script>
	<tr class="table_header">
		   <td  align="center" valign="top"><b>BIL.</b></td>
		   <td   align="left" valign="top"><b>MODUL ID</b></td>
		   <td   align="left" valign="top"><b>JAVA CLASS</b></td>
		   <td   align="left" valign="top"><b>KETERANGAN</b></td>
		   <td  align="left" valign="top"><b>KUMPULAN MODUL</b></td>
	</tr>
	
		#foreach($userAT in $listPengunaModul)
		<tr >
			   <td class="$userAT.rowCss"  align="center" valign="top" >$userAT.BIL </td>
			   <td class="$userAT.rowCss"  align="left" valign="top">
			   
			   #set($span1 = "span1listPengunaModul"+$userAT.BIL)
			   <span id="$span1"> 
			   $userAT.MODULE_ID
			   </span>
			   
			   </td>
			   <td  class="$userAT.rowCss"  align="left" valign="top">
			   
			   #set($span2 = "span2listPengunaModul"+$userAT.BIL)
			   <span id="$span2"> 
			   $userAT.MODULE_CLASS
			   </span>
			   
			   </td>
			   
			   
			   <td  class="$userAT.rowCss"  align="left" valign="top">
			   
			   #set($span3 = "span3listPengunaModul"+$userAT.BIL)
			   <span id="$span3"> 
			   $userAT.MODULE_DESCRIPTION
			   </span>
			   
			   </td>
			   
			 
			   </td>
			   <td  class="$userAT.rowCss"  align="left" valign="top">
			   
			   #set($span4 = "span4listPengunaModul"+$userAT.BIL)
			   <span id="$span4">  
			   $userAT.MODULE_GROUP
			   </span>
			   
			  
			   #set($span5 = "span5listPengunaModul"+$userAT.BIL)
			   <span id="$span5"> 
			   </span>
			   
			   #set($span6 = "span6listPengunaModul"+$userAT.BIL)
			   <span id="$span6"> 
			   </span>
			   
			   #set($span7 = "span7listPengunaModul"+$userAT.BIL)
			   <span id="$span7"> 
			   </span>
			   
			   </td>
			   
			   
		</tr>
		
		#end
		#else
		<script>
		var element =  document.getElementById('show_cb_Modul_'+'$GROUPUNIK'+'$ROLEUNIK');
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
	
	
	<div id="div_ModulforPrint_$GROUPUNIK$ROLEUNIK"  style="display:none" >
	
	<table width="100%"  
	style="border-collapse: collapse;"  cellspacing="1" cellpadding="2"  width="100%" > 
	<tr >
		   <td  align="left" valign="top" colspan="8"  >
		   <b>MODUL & JAVA CLASS</b>
		   
		      
		   </td>
	</tr>
	</table>
	
	
	<table width="100%"  style="border-collapse: collapse;"  cellspacing="1" cellpadding="2"  width="100%" > 	
	
	#if($listPengunaModulforPrint.size()>0)
	<thead>
	<tr >
	

		   <th style="border: 1px solid black;font-size:75%;"  align="center" valign="top"><b>BIL.</b></th>
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>MODULE ID</b></th>
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>JAVA CLASS</b></th>
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>KETERANGAN</b></th>
		   <th style="border: 1px solid black;font-size:75%;"  align="left" valign="top"><b>KUMPULAN MODUL</b></th>
	</tr>
	</thead>
	
		#foreach($userAT in $listPengunaModulforPrint)
		<tr  > <!-- class="page-break" -->
			   <td style="border: 1px solid black;font-size:75%;"   align="center" valign="top" >$userAT.BIL </td>
			   <td style="border: 1px solid black;font-size:75%;"   align="left" valign="top">
			   $userAT.MODULE_ID			   
			   </td>
			   <td style="border: 1px solid black; font-size:75%;"   align="left" valign="top">
			   $userAT.MODULE_CLASS
			   </td>
			   <td style="border: 1px solid black;font-size:75%;"   align="left" valign="top">
			   $userAT.MODULE_DESCRIPTION
			   </td>
			   <td style="border: 1px solid black;font-size:75%;"   align="left" valign="top">
			   $userAT.MODULE_GROUP
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
					 // doDivAjaxCall$formname('div_ListPLA$GROUPUNIK$ROLEUNIK','carianUtamaPLA','&carianTerperinciPLA=&TARIKH_MULA_PLA=&TARIKH_AKHIR_PLA=&ROLEUNIK=$ROLEUNIK&GROUPUNIK=$GROUPUNIK');			 	  
				  });
</script>

