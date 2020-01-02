
<fieldset id="div_rowGred">
<legend>Senarai Gred</legend>

#if($SuccessMesejDeleteGred!="")
<div class="info" id="div_rowGred_deletemesej">
	$SuccessMesejDeleteGred	
</div>

<script>
$jquery("#div_rowGred_deletemesej").show().delay(3000).fadeOut();
if( $jquery('#'+'div_rowGred').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_rowGred').offset().top);
}
</script>
#end

<table width="100%" id="div_ViewGred" style="display:none">
<tr >
</tr>
</table>



<table width="100%" align="center">
<tr>
<td>
<table border="0" cellspacing="1" cellpadding="1" width="100%" > 

#if($listDataGred.size()>0)
<tr><td colspan="14">
#parse("app/admin/PerkhidmatanJawatan/record_paging_gred.jsp")
</td></tr>
#end
   
<tr class="table_header" >
<td   align="center" valign="top">Bil.</td>
<td   align="left" valign="top">Nama Gred</td>
<td   align="left" valign="top">Keterangan</td>
<td   align="left" valign="top">Status</td>
<td   align="left" valign="top">Tindakan</td>

		   
	</tr>
	#if($listDataGred.size()>0)
	
	<!--gred = userHQ-->
#foreach($gred in $listDataGred)
<tr id="div_rowPejabatUrusan$gred.ID_GRED">
<td   align="center" valign="top" class="$gred.rowCss" >$gred.BIL</td>
<td  align="left" valign="top" 
class="$gred.rowCss">$gred.NAMA_GRED</td>
<td  align="left" valign="top" 
class="$gred.rowCss">$gred.KETERANGAN</td>
<td  align="left" valign="top" 
class="$gred.rowCss">$gred.FLAG_STATUS</td>

<td align ="center" valign="top" 
class="$gred.rowCss"><a href="javascript:doDivAjaxCall$formname('div_viewGred$gred.ID_GRED','viewGred','ID_GRED=$gred.ID_GRED');"><img src="../img/edit.gif" border="0"></a>

<a href="javascript:if(confirm('Data akan dipadam.Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_Gred','showSenaraiGred','ID_GRED=$gred.ID_GRED&FLAG_DELETE=Y');}"><img src="../img/delete.gif" border="0"></a>

</td> 
</tr>
<tr  id="div_viewGred$gred.ID_GRED">
<td align="left" valign="top" colspan="14">
</td>
</tr>
#end
#else
<tr >
<td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
</tr>
#end
</table>

#if($PrintlistDataGred.size()>0)
	<script>
	var bttnCetakGred =  document.getElementById('cmdCetakGred');
	//alert(' masuk sini ');
	if (typeof(bttnCetakGred) != 'undefined' && bttnCetakGred != null)
    {
    	bttnCetakGred.style.display = "";
    }
	</script>
	#end
<div id="SenaraiForPrint">	</div>

</td>
</tr>
</table>
</fieldset>
<br>