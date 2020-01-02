<div id="displayDoc$detailIntegrasi.ID_INTEGRASI">
<table border="0" cellspacing="1" cellpadding="1" width="100%" >
<tr class="table_header" >
<td width="16%"   align="center" valign="top">Bil.</td>
<!--<td width="18%"   align="center" valign="top">Kod</td>-->
<td width="46%"   align="center" valign="top">Nama Dokumen </td>
<td width="20%"   align="center" valign="top">Tindakan</td>

		   
	</tr>
	#if($listDokumen.size()>0)
	<!--gred = userHQ-->
#foreach($list in $listDokumen)
<tr id="div_rowPejabatUrusan$gred.ID_GRED">
<td   align="center" valign="top" >$list.BIL.</td>
<!--<td  align="center" valign="top">($list.KOD_AGENSI)</td>-->
<td  align="left" valign="top"><a href="javascript:paparDoc('$list.ID_DOKUMEN');"><font color="blue"><u>$list.NAMA_DOKUMEN</u></font></a></td>

<td align ="center" valign="top">
<a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('displayDoc$detailIntegrasi.ID_INTEGRASI','showDokumen','Type=$Type&FLAG_DELETE=Y&ID_INTEGRASI=$list.ID_INTEGRASI&ID_DOKUMEN=$list.ID_DOKUMEN');}"><img src="../img/delete.gif" border="0"></a>

</td> 
</tr>
#end
#else
<tr >
<td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
</tr>
#end
</table>
</div>