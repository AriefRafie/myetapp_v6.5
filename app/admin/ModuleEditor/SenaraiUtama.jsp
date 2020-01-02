<script>
highlightGroup('$listGroup.size()',$jquery('#carianTerperinci').val(),'listGroup'); 
</script>

<br>
<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0">
<tr>
<td width="2%">
</td>
<td width="58%">
</td>
<td width="38%">
</td>
<td width="2%">
</td>
</tr>
#if($listGroup.size()>0)
#foreach($lg in $listGroup)
<tr 
id="div_rowGroup$lg.MODULE_GROUP"
class="table_header" 

>
<td class="underline_td_main" >

</td>
<td class="underline_td_main" align="left" valign="top" 
>

<input type="hidden" id="hiddenModulBIL$lg.BIL" name="hiddenModulBIL$lg.BIL" value="$lg.BIL">
<input type="hidden" id="hiddenModulMODULE_GROUP$lg.BIL" name="hiddenModulMODULE_GROUP$lg.BIL" value="$lg.MODULE_GROUP">
<input type="hidden" id="hiddenModulMODULE_GROUP_ASAL$lg.BIL" name="hiddenModulMODULE_GROUP_ASAL$lg.BIL" value="$lg.MODULE_GROUP_ASAL">



 #set($span1group = "span1grouplistGroup"+$lg.BIL)
<strong><span id="$span1group" >$lg.MODULE_GROUP_ASAL</span></strong>
</td>
<td class="underline_td_sub" align="right" valign="top" >
($lg.TOTAL_MODULE #if($lg.TOTAL_MODULE>1) MODULES #else MODULE #end FOUND)
</td>
<td class="underline_td_main" align="center" valign="top">
<a href="javascript:doDivAjaxCall$formname('div_rowGroup$lg.MODULE_GROUP','editGroup','MODULE_GROUP=$lg.MODULE_GROUP&MODULE_GROUP_ASAL=$lg.MODULE_GROUP_ASAL&CLIK_ROW=Y');"><img title="Kemaskini" src="../img/edit.gif" border="0"></a>
</td>
</tr>

<tr > 
<td colspan="4" valign="top" id="div_Main$lg.BIL">

</td>
</tr>
#end
#else
#if($command=="carianUtama")
<tr>
<td colspan="4">Tiada Rekod</td>
</tr>
#end
#end
</table>


#if($listGroup.size()>0)
<script>
	var h_BIL = $jquery('#hiddenModulBIL1').val();
	var h_MODULE_GROUP = $jquery('#hiddenModulMODULE_GROUP1').val();
	var h_MODULE_GROUP_ASAL = $jquery('#hiddenModulMODULE_GROUP_ASAL1').val();
	var TOTAL_LIST = '$listGroup.size()';
	
	//alert(" h_BIL : "+h_BIL+" h_GROUPUNIK : "+h_GROUPUNIK+" h_CSS_TITLE : "+h_CSS_TITLE+" TOTAL_LIST : "+TOTAL_LIST);
	
	if(h_BIL=='1')
	{
		$jquery(document).ready(function () {	
		doDivAjaxCall3$formname('div_Main'+h_BIL,'showSenaraiModulByGroup','&carianTerperinci='+$jquery('#carianTerperinci').val()+'&MODULE_GROUP='+h_MODULE_GROUP+'&MODULE_GROUP_ASAL='+h_MODULE_GROUP_ASAL+'&BIL='+h_BIL+'&TOTAL_LIST='+TOTAL_LIST+'&SHOWNEXT=Y&FLAG_DELETE=');
		});
	}
</script>
#end
		
