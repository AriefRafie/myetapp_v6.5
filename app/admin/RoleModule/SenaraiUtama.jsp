<script>
highlightGroup('$listGroup.size()',$jquery('#carianTerperinci').val(),'listGroup'); 
</script>



<table width="100%">
<tr id="div_rowGroup" >
<td class="">
</td>
</tr>
</table>
<br>
#if($SuccessMesejDelete!="")
<div class="info" id="div_SuccessMesejDeleteGroup" >
		#if($SuccessMesejDelete=="Delete")
		
			Maklumat Kumpulan Role Berjaya Dihapuskan		
		#end
	</div>	
	<script >
	//alert($jquery('div_SuccessMesejModule$GROUPUNIK$viewRole.NAME').length);
	$jquery("#div_SuccessMesejDeleteGroup").show().delay(3000).fadeOut();
	</script>
#end

<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0">
<tr>
<td width="2%">
</td>
<td width="58%">

</td>
<td width="35%">
</td>
<td width="5%">
</td>
</tr>





#if($listGroup.size()>0)
#foreach($lg in $listGroup)
<tr 
id="div_rowGroup$lg.GROUPUNIK"
class="table_header" 

>
<td class="underline_td_main" >
</td>
<td class="underline_td_main" align="left" valign="top" 

>
<input type="hidden" id="hiddenGroupBIL$lg.BIL" name="hiddenGroupBIL$lg.BIL" value="$lg.BIL">
<input type="hidden" id="hiddenGroupGROUPUNIK$lg.BIL" name="hiddenGroupGROUPUNIK$lg.BIL" value="$lg.GROUPUNIK">
<input type="hidden" id="hiddenGroupCSS_TITLE$lg.BIL" name="hiddenGroupCSS_TITLE$lg.BIL" value="$lg.CSS_TITLE">

 #set($span1group = "span1grouplistGroup"+$lg.BIL)
 <span id="$span1group" >

#if($lg.TITLE!="")
<strong>$lg.TITLE</strong> - [$lg.CSS_NAME]
#else
<strong>*UNASSIGNED*</strong>
#end

#if($lg.NAMA_DOC!="")
<br>
<a href="javascript:paparDoc('$lg.CSS_TITLE')"><font color="white"><u>$lg.NAMA_DOC</u></font></a>
#end


</span>
</td>
<td class="underline_td_sub" align="right" valign="top" >
($lg.TOTAL_ROLE #if($lg.TOTAL_ROLE>1) ROLES #else ROLE #end FOUND)
</td>
<td class="underline_td_main" align="center" valign="top">
#if($lg.TITLE!="")
<a href="javascript:doDivAjaxCall$formname('div_rowGroup$lg.GROUPUNIK','viewGroup','BIL=$lg.BIL&GROUPUNIK=$lg.GROUPUNIK&CSS_TITLE=$lg.CSS_TITLE&MODE=EDIT');"><img title="Kemaskini Group" src="../img/edit.gif" border="0"></a>

<a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ document.getElementById('setPageCoor').value='';doDivAjaxCall$formname('div_senaraiUtama','carianUtama','&BIL=$lg.BIL&GROUPUNIK=$lg.GROUPUNIK&CSS_TITLE=$lg.CSS_TITLE&DELETE=Y&carianTerperinci='+$jquery('#carianTerperinci').val());}"><img title="Hapus Group"  src="../img/delete.gif" border="0"></a>
	   		   
<a href="javascript:document.getElementById('setPageCoor').value='';doDivAjaxCall$formname('div_viewRole$lg.GROUPUNIK','editRole','CSS_NAME=$lg.CSS_NAME&CSS_TITLE=$lg.CSS_TITLE&ID=&ROLE_NAME=$lg.TITLE&GROUPUNIK=$lg.GROUPUNIK&BIL=$lg.BIL');">
<img title="Tambah Role" src="../img/plus3.gif" border="0"></a>
#end	   		   
</td>
</tr>


<!-- 
<tr  id="div_rowRole$lg.GROUPUNIK" > 
<td colspan="4" valign="top" >
</td>
</tr>
<tr > 
<td colspan="4" valign="top" id="div_viewRole$lg.GROUPUNIK">
</td>
</tr>
 -->

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

<script>
//alert("XXXX : "+document.getElementById("setPageCoor").value);
//setPageLocation(document.getElementById("setPageCoor").value);
</script>




#if($listGroup.size()>0)	
	<script>
	var h_BIL = $jquery('#hiddenGroupBIL1').val();
	var h_GROUPUNIK = $jquery('#hiddenGroupGROUPUNIK1').val();
	var h_CSS_TITLE = $jquery('#hiddenGroupCSS_TITLE1').val();
	var TOTAL_LIST = '$listGroup.size()';
	
	//alert(" h_BIL : "+h_BIL+" h_GROUPUNIK : "+h_GROUPUNIK+" h_CSS_TITLE : "+h_CSS_TITLE+" TOTAL_LIST : "+TOTAL_LIST);
	
	if(h_BIL=='1')
	{
		$jquery(document).ready(function () {	
		doDivAjaxCall$formname('div_Main'+h_BIL,'showSenaraiRoleByGroup','&carianTerperinci='+$jquery('#carianTerperinci').val()+'&GROUPUNIK='+h_GROUPUNIK+'&CSS_TITLE='+h_CSS_TITLE+'&BIL='+h_BIL+'&TOTAL_LIST='+TOTAL_LIST+'&SHOWNEXT=Y&FLAG_DELETE=');
		});
		
		
	}

	</script>
#end
