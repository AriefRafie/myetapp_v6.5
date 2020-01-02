
<fieldset id="div_rowKlasifikasi">

<legend>Senarai Klasifikasi</legend>
#if($SuccessMesejDeleteKlasifikasi!="")
<div class="info" id="div_rowKlasifikasi_deletemesej">
	$SuccessMesejDeleteKlasifikasi
</div>

<script>
$jquery("#div_rowKlasifikasi_deletemesej").show().delay(3000).fadeOut();
if( $jquery('#'+'div_rowKlasifikasi').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_rowKlasifikasi').offset().top);
}
</script>
#end

<table width="100%" id="div_ViewKlasifikasi" style="display:none">
<tr >
</tr>
</table>



<table width="100%" align="center">
<tr>
<td>


<table border="0" cellspacing="1" cellpadding="1" width="100%" > 

#if($listDataKlasifikasi.size()>0)
<tr><td colspan="14">
#parse("app/admin/PerkhidmatanJawatan/record_paging_klasifikasi.jsp")
</td></tr>
#end
   
<tr class="table_header" >
<td   align="center" valign="top">Bil.</td>
<td   align="left" valign="top">Nama Klasifikasi</td>
<td   align="left" valign="top">Keterangan</td>
<td   align="left" valign="top">Status</td>
<td   align="left" valign="top">Tindakan</td>
</tr>

#if($listDataKlasifikasi.size()>0)
	
<!--klasifikasi = userHQ-->
#foreach($klasifikasi in $listDataKlasifikasi)
<tr id="div_rowPejabatUrusan$klasifikasi.ID_klasifikasi">
<td   align="center" valign="top" class="$klasifikasi.rowCss">$klasifikasi.BIL </td>
<td  align="left" valign="top" class="$klasifikasi.rowCss">$klasifikasi.NAMA_KLASIFIKASI</td>
<td  align="left" valign="top" class="$klasifikasi.rowCss">$klasifikasi.KETERANGAN</td>
<td  align="left" valign="top" class="$klasifikasi.rowCss">
$klasifikasi.FLAG_STATUS
</td>

<td align ="center" valign="top" class="$klasifikasi.rowCss"><a href="javascript:doDivAjaxCall$formname('div_viewKlasifikasi$klasifikasi.ID_KLASIFIKASI','viewKlasifikasi','ID_KLASIFIKASI=$klasifikasi.ID_KLASIFIKASI');"><img src="../img/edit.gif" border="0"></a>

<a href="javascript:if(confirm('Data akan dipadam.Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_Klasifikasi','showSenaraiKlasifikasi','ID_KLASIFIKASI=$klasifikasi.ID_KLASIFIKASI&FLAG_DELETE=Y');}"><img src="../img/delete.gif" border="0"></a>



</td> 
 
</tr>
<tr  id="div_viewKlasifikasi$klasifikasi.ID_KLASIFIKASI">
<td align="left" valign="top" colspan="14"></td>
</tr>
#end
#else
<tr >
<td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
</tr>
#end
</table>

#if($PrintlistDataKlasifikasi.size()>0)
	<script>
	var bttnCetakKlasifikasi =  document.getElementById('cmdCetakKlasifikasi');
	//alert(' masuk sini ');
	if (typeof(bttnCetakKlasifikasi) != 'undefined' && bttnCetakKlasifikasi != null)
    {
    	bttnCetakKlasifikasi.style.display = "";
    }
	</script>
	#end
<div id="SenaraiForPrintKlasifikasi">	</div>

</td>
</tr>
</table>
</fieldset>
<br>