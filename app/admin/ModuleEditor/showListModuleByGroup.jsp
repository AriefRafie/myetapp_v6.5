<fieldset >

#if($SuccessMesejDeleteModule!="")
<div class="info" id="div_SenaraiModul$BIL">
	$SuccessMesejDeleteModule	
</div>


<script>
$jquery("#div_SenaraiModul$BIL").show().delay(3000).fadeOut();
/*
if( $jquery('#'+'div_Main$BIL').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_Main$BIL').offset().top);
}
*/
</script>
#end

<table width="100%">
<tr>
<td>


	<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($listModule.size()>0)
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		   #parse("app/admin/ModuleEditor/record_paging_V3.jsp")
		   </td>
		     
	</tr>
	#end 
	<tr class="table_header" >
		   <td   align="center" valign="top" width="3%">Bil.</td>
		   <td   align="left" valign="top"  width="45%">ID Modul</td>
		   <td   align="left" valign="top"  width="32%">Nama Modul</td>
		   <td   align="left" valign="top"  width="10%">Group</td>
		   <!-- 
		   <td   align="left" valign="top">NAMA CLASS</td>
		   <td   align="left" valign="top">GROUP</td>
		   <td   align="left" valign="top">KETERANGAN</td>-->
		   <td   align="center" valign="top" width="10%">Tindakan</td>   
	</tr>
	#if($listModule.size()>0)
	
	
		#foreach($lm in $listModule)
		<tr id="div_rowModul$MODULE_GROUP$lm.MODULE_ID">
			   <td class="$lm.rowCss"  align="center" valign="top" >$lm.BIL </td>
			   <td class="$lm.rowCss"  align="left" valign="top">
			   #set($span1 = "span1"+$MODULE_GROUP+"listModule"+$lm.BIL)
			   <span id="$span1"> 
			   $lm.MODULE_ID_ASAL
			   </span>
			   </td>
			   <td class="$lm.rowCss"  align="left" valign="top">
			   #set($span2 = "span2"+$MODULE_GROUP+"listModule"+$lm.BIL)
			   <span id="$span2"> 
			   $lm.MODULE_TITLE
			   </span>
			   </td>
			   <td class="$lm.rowCss"  align="left" valign="top">
			   #set($span3 = "span3"+$MODULE_GROUP+"listModule"+$lm.BIL)
			   <span id="$span3"> 
			   $lm.MODULE_GROUP
			   </span>
			   </td> 
			   <!-- 
			   <td class="$lm.rowCss"  align="left" valign="top">
			   #set($span3 = "span3listModule"+$MODULE_GROUP+$lm.BIL)
			   <span id="$span3"> 
			   $lm.MODULE_CLASS
			   </span>
			   </td>
			   <td class="$lm.rowCss"  align="left" valign="top">
			   #set($span4 = "span4listModule"+$MODULE_GROUP+$lm.BIL)
			   <span id="$span4"> 
			   $lm.MODULE_GROUP
			   </span>
			   </td>
			   <td class="$lm.rowCss"  align="left" valign="top">
			   #set($span5 = "span5listModule"+$MODULE_GROUP+$lm.BIL)
			   <span id="$span5"> 
			   $lm.MODULE_DESCRIPTION
			   </span>
			   </td>
			   -->
			   
			  
			   <td class="$lm.rowCss"  align="center" valign="top">
			   <a href="javascript:doDivAjaxCall$formname('div_viewModul$MODULE_GROUP$lm.MODULE_ID','viewModule','MODULE_ID=$lm.MODULE_ID_ASAL&MODULE_GROUP=$MODULE_GROUP');"><img title="Kemaskini" src="../img/edit.gif" border="0"></a>
	   		   <a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_Main$BIL','showSenaraiModulByGroup','&carianTerperinci='+$jquery('#carianTerperinci').val()+'&MODULE_GROUP=$MODULE_GROUP&BIL=$BIL&FLAG_DELETE=Y&MODULE_ID=$lm.MODULE_ID_ASAL');}"><img title="Hapus"  src="../img/delete.gif" border="0"></a>
	   		   
			   </td>
			    
		</tr>
		<tr  >
		<td align="left" valign="top" colspan="5" id="div_viewModul$MODULE_GROUP$lm.MODULE_ID">
		
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




#if($NEXT_BIL != "")
<script>
	var h_BIL = $jquery('#hiddenModulBIL'+'$NEXT_BIL').val();
	var h_MODULE_GROUP = $jquery('#hiddenModulMODULE_GROUP'+'$NEXT_BIL').val();
	var h_MODULE_GROUP_ASAL = $jquery('#hiddenModulMODULE_GROUP_ASAL'+'$NEXT_BIL').val();
	var TOTAL_LIST = '$TOTAL_LIST';
	if(h_BIL=='$NEXT_BIL')
	{
		$jquery(document).ready(function () {	
			doDivAjaxCall3$formname('div_Main'+h_BIL,'showSenaraiModulByGroup','&carianTerperinci='+$jquery('#carianTerperinci').val()+'&MODULE_GROUP='+h_MODULE_GROUP+'&MODULE_GROUP_ASAL='+h_MODULE_GROUP_ASAL+'&BIL='+h_BIL+'&TOTAL_LIST='+TOTAL_LIST+'&SHOWNEXT=Y&FLAG_DELETE=');
		});
	}
</script>
#end