
<fieldset id="div_rowKhidmat">
<legend>Senarai Kumpulan Perkhidmatan</legend>
#if($SuccessMesejDeleteKhidmat!="")
<div class="info" id="div_rowKhidmat_deletemesej">
	$SuccessMesejDeleteKhidmat
</div>

<script>
$jquery("#div_rowKhidmat_deletemesej").show().delay(3000).fadeOut();
if( $jquery('#'+'div_rowKhidmat').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_rowKhidmat').offset().top);
}
</script>
#end

<table width="100%" id="div_ViewKhidmat" style="display:none">
<tr >
</tr>
</table>



<table width="100%" align="center">
<tr>
<td>
<table border="0" cellspacing="1" cellpadding="1" width="100%" > 

#if($listDataKhidmat.size()>0)
<tr><td colspan="14">
#parse("app/admin/PerkhidmatanJawatan/record_paging_khidmat.jsp")
</td></tr>
#end

<tr class="table_header" >
<td   align="center" valign="top">Bil.</td>
<td   align="left" valign="top">Nama Khidmat</td>
<td   align="left" valign="top">Keterangan</td>
<td   align="left" valign="top">Skop Gred</td>
<td   align="left" valign="top">Tindakan</td>

		   
	</tr>
	#if($listDataKhidmat.size()>0)
	
	<!--gred = userHQ-->
#foreach($Khidmat in $listDataKhidmat)
<tr id="div_rowPejabatUrusan$gred.ID_KHIDMAT">
<td   align="center" valign="top" class="$Khidmat.rowCss">$Khidmat.BIL</td>
<td  align="left" valign="top" class="$Khidmat.rowCss">$Khidmat.NAMA_KHIDMAT</td>
<td  align="left" valign="top" class="$Khidmat.rowCss">$Khidmat.KETERANGAN_KHIDMAT</td>
<td  align="left" valign="top" class="$Khidmat.rowCss">$Khidmat.SKOP_GRED1 - $Khidmat.SKOP_GRED2</td>

<td align ="center" valign="top" class="$Khidmat.rowCss"><a href="javascript:doDivAjaxCall$formname('div_viewKhidmat$Khidmat.ID_KHIDMAT','viewKhidmat','ID_KHIDMAT=$Khidmat.ID_KHIDMAT');"><img src="../img/edit.gif" border="0"></a>


<a href="javascript:if(confirm('Data akan dipadam.Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_KumpKhidmat','showSenaraiKhidmat','ID_KHIDMAT=$Khidmat.ID_KHIDMAT&FLAG_DELETE=Y');}"><img src="../img/delete.gif" border="0"></a>
</td> 
</tr>
<tr  id="div_viewKhidmat$Khidmat.ID_KHIDMAT">
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

#if($PrintlistDataKhidmat.size()>0)
	<script>
	var bttnCetakKhidmat =  document.getElementById('cmdCetakKhidmat');
	//alert(' masuk sini ');
	if (typeof(bttnCetakKhidmat) != 'undefined' && bttnCetakKhidmat != null)
    {
    	bttnCetakKhidmat.style.display = "";
    }
	</script>
	#end
<div id="SenaraiForPrintKhidmat">	</div>

</td>
</tr>
</table>
</fieldset>
<br>