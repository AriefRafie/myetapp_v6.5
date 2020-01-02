<fieldset >

#if($SuccessMesejDeleteRole=="Delete")
<div class="info" id="div_rowRoleDeleteMesej$GROUPUNIK">
	Maklumat Role Berjaya Dihapus!		
</div>
<script>
$jquery("#div_rowRoleDeleteMesej$GROUPUNIK").show().delay(3000).fadeOut();
</script>
#end




<script>
/*
var element =  document.getElementById('div_rowGroup$GROUPUNIK');
if (typeof(element) != 'undefined' && element != null)
{
	window.scrollTo(0, $jquery('#div_rowGroup$GROUPUNIK').offset().top);
}
*/
</script>
<input type="hidden" id="hiddenGroupBIL$lg.BIL" name="hiddenGroupBIL$lg.BIL" value="$lg.BIL">
<input type="hidden" id="hiddenGroupGROUPUNIK$lg.BIL" name="hiddenGroupGROUPUNIK$lg.BIL" value="$lg.GROUPUNIK">
<input type="hidden" id="hiddenGroupCSS_TITLE$lg.BIL" name="hiddenGroupCSS_TITLE$lg.BIL" value="$lg.CSS_TITLE">


<table width="100%" id="table_diplay_role$GROUPUNIK">
<tr  id="div_rowRole$GROUPUNIK" > 
<td colspan="4" valign="top" >
</td>
</tr>
<tr > 
<td colspan="4" valign="top" id="div_viewRole$GROUPUNIK">
</td>
</tr>
<tr>
<td>


	<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($listRole.size()>0)
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		   #parse("app/admin/RoleModule/record_paging_V3.jsp")
		   
		   </td>
		     
	</tr>
	#end 

	<tr class="table_header" >
		   <td   align="center" valign="top" width="3%">Bil.</td>
		   <td   align="left" valign="top"  width="45%">Role ID</td>
		   <td   align="left" valign="top"  width="32%">Role Name</td>
		   <td   align="left" valign="top"  width="10%">Group</td>
		   <!-- 
		   <td   align="left" valign="top">NAMA CLASS</td>
		   <td   align="left" valign="top">GROUP</td>
		   <td   align="left" valign="top">KETERANGAN</td>-->
		   <td   align="center" valign="top" width="10%">Tindakan</td>   
	</tr>
	#if($listRole.size()>0)
	
	
		#foreach($lm in $listRole)
		<tr id="div_rowRole$GROUPUNIK$lm.ROLEUNIK">
			   <td class="$lm.rowCss"  align="center" valign="top" >$lm.BIL </td>
			   <td class="$lm.rowCss"  align="left" valign="top">
			   
			   #set($span1 = "span1"+$GROUPUNIK+"listRole"+$lm.BIL)
			   <span id="$span1"> 
			   $lm.NAME
			   </span>
			   </td>
			   <td class="$lm.rowCss"  align="left" valign="top">
			   #set($span2 = "span2"+$GROUPUNIK+"listRole"+$lm.BIL)
			   <span id="$span2"> 
			   $lm.DESCRIPTION
			   </span>
			   </td>
			   <td class="$lm.rowCss"  align="left" valign="top">
			   #set($span3 = "span3"+$GROUPUNIK+"listRole"+$lm.BIL)
			   <span id="$span3"> 
			   $lm.CSS_TITLE
			   </span>
			   </td> 
			   <!-- 
			   <td class="$lm.rowCss"  align="left" valign="top">
			   #set($span3 = "span3listRole"+$GROUPUNIK+$lm.BIL)
			   <span id="$span3"> 
			   $lm.MODULE_CLASS
			   </span>
			   </td>
			   <td class="$lm.rowCss"  align="left" valign="top">
			   #set($span4 = "span4listRole"+$GROUPUNIK+$lm.BIL)
			   <span id="$span4"> 
			   $lm.MODULE_GROUP
			   </span>
			   </td>
			   <td class="$lm.rowCss"  align="left" valign="top">
			   #set($span5 = "span5listRole"+$GROUPUNIK+$lm.BIL)
			   <span id="$span5"> 
			   $lm.MODULE_DESCRIPTION
			   </span>
			   </td>
			   -->
			   
			  
			   <td class="$lm.rowCss"  align="center" valign="top">
			   <a href="javascript:doDivAjaxCall$formname('div_viewRole$GROUPUNIK$lm.ROLEUNIK','viewRole','BIL=$BIL&ROLEUNIK=$lm.ROLEUNIK&ID=$lm.ID&ROLE_NAME=$lm.NAME&CSS_TITLE=$CSS_TITLE&GROUPUNIK=$GROUPUNIK&page=$page');"><img title="Kemaskini" src="../img/edit.gif" border="0"></a>
	   		   <a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_Main$BIL','showSenaraiRoleByGroup','&carianTerperinci='+$jquery('#carianTerperinci').val()+'&GROUPUNIK=$GROUPUNIK&CSS_TITLE=$CSS_TITLE&BIL=$BIL&DELETE=Y&ROLE_NAME=$lm.NAME&ID=$lm.ID&ROLEUNIK=$lm.ROLEUNIK&page=$page');}"><img title="Hapus"  src="../img/delete.gif" border="0"></a>
	   		   </td>
			    
		</tr>
		<tr  >
		<td align="left" valign="top" colspan="5" id="div_viewRole$GROUPUNIK$lm.ROLEUNIK">
		
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

<tr>
<td>
</td>
</tr>
</table>
</fieldset>


#if($NEXT_BIL=="" && $TOTAL_LIST=="")
<script>

	var spc = document.getElementById("setPageCoor").value;
	//alert(" spc : "+spc);
	if(spc!="")
	{
		setPageLocation(document.getElementById("setPageCoor").value);
	}
</script>
#end

#if($NEXT_BIL != "")
<script>
	var h_BIL = $jquery('#hiddenGroupBIL'+'$NEXT_BIL').val();
	var h_GROUPUNIK = $jquery('#hiddenGroupGROUPUNIK'+'$NEXT_BIL').val();
	var h_CSS_TITLE = $jquery('#hiddenGroupCSS_TITLE'+'$NEXT_BIL').val();
	var TOTAL_LIST = '$TOTAL_LIST';
	if(h_BIL=='$NEXT_BIL')
	{
		$jquery(document).ready(function () {	
		doDivAjaxCall$formname('div_Main'+h_BIL,'showSenaraiRoleByGroup','&carianTerperinci='+$jquery('#carianTerperinci').val()+'&GROUPUNIK='+h_GROUPUNIK+'&CSS_TITLE='+h_CSS_TITLE+'&BIL='+h_BIL+'&TOTAL_LIST='+TOTAL_LIST+'&SHOWNEXT=Y&FLAG_DELETE=');
		});
		
		
	}
</script>
#end