
<fieldset id="div_rowSkimKhidmat">
<legend>Senarai Skim Khidmat </legend>
#if($SuccessMesejDeleteSkimKhidmat!="")
<div class="info" id="div_rowSkimKhidmat_deletemesej">
	$SuccessMesejDeleteSkimKhidmat
</div>

<script>
$jquery("#div_rowSkimKhidmat_deletemesej").show().delay(3000).fadeOut();
if( $jquery('#'+'div_rowSkimKhidmat').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_rowSkimKhidmat').offset().top);
}
</script>
#end

<table width="100%" id="div_ViewSkim" style="display:none">
<tr >
</tr>
</table>



<table width="100%" align="center">
<tr>
<td>


	<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($listDataSkimKhidmat.size()>0)
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		   #parse("app/admin/PerkhidmatanJawatan/record_paging_skimkhidmat.jsp")
		   </td>
      
	</tr>
	#end 
   
<tr class="table_header" >
<td   align="center" valign="top">Bil.</td>
<td   align="left" valign="top">Nama Klasifikasi</td>
<td   align="left" valign="top">Nama Skim Khidmat</td>
<td   align="left" valign="top">Kod Skim</td>
<td   align="left" valign="top">Kumpulan Perkhidmatan</td>
<!--<td   align="left" valign="top">Gred</td>-->
<td   align="left" valign="top">Sumber Maklumat</td>
<td   align="left" valign="top">Tarikh Kemaskini</td>
<td   align="left" valign="top">Tindakan</td>

		   
	</tr>
	#if($listDataSkimKhidmat.size()>0)
	
	<!--SkimKhidmat = userHQ-->
#foreach($SkimKhidmat in $listDataSkimKhidmat)
<tr id="div_rowPejabatUrusan$SkimKhidmat.ID_JAWATAN">
<td   align="center" 
valign="top" class="$SkimKhidmat.rowCss">$SkimKhidmat.BIL</td>
<td  align="left" 
valign="top" class="$SkimKhidmat.rowCss">$SkimKhidmat.NAMA_KLASIFIKASI</td>
<td  align="left" 
valign="top" class="$SkimKhidmat.rowCss">$SkimKhidmat.KETERANGAN</td>
<td  align="left" 
valign="top" class="$SkimKhidmat.rowCss">$SkimKhidmat.KOD_SKIM</td>
<td  align="left" 
valign="top" class="$SkimKhidmat.rowCss">$SkimKhidmat.NAMA_KHIDMAT</td>
<!--<td  align="left" 
valign="top">$SkimKhidmat.NAMA_GRED</td>-->
<td  align="left" 
valign="top" class="$SkimKhidmat.rowCss">$SkimKhidmat.SUMBER_MAKLUMAT</td>
<td  align="left" 
valign="top" class="$SkimKhidmat.rowCss">$SkimKhidmat.TARIKH_KEMASKINI</td>

<td align ="center" valign="top" class="$SkimKhidmat.rowCss"><a href="javascript:doDivAjaxCall$formname('div_viewSkimKhidmat$SkimKhidmat.ID_JAWATAN','viewSkimKhidmat','ID_JAWATAN=$SkimKhidmat.ID_JAWATAN');"><img src="../img/edit.gif" border="0"></a>

<a href="javascript:if(confirm('Data akan dipadam.Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_SkimKhidmat','showSenaraiSkimKhidmat','ID_JAWATAN=$SkimKhidmat.ID_JAWATAN&FLAG_DELETE=Y');}"><img src="../img/delete.gif" border="0"></a>



</td> 
</tr>
<tr  id="div_viewSkimKhidmat$SkimKhidmat.ID_JAWATAN">
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

#if($PrintlistDataSkimKhidmat.size()>0)
	<script>
	var bttnCetakSkimKhidmat =  document.getElementById('cmdCetakSkimKhidmat');
	//alert(' masuk sini ');
	if (typeof(bttnCetakSkimKhidmat) != 'undefined' && bttnCetakSkimKhidmat != null)
    {
    	bttnCetakSkimKhidmat.style.display = "";
    }
	</script>
	#end
<div id="SenaraiForPrintSkimKhidmat">	</div>

</td>
</tr>
</table>
</fieldset>
<br>