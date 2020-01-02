<tr>
            <td colspan="14">
            <fieldset>
<legend>Senarai Dokumen Bahagian</legend>


<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($listDokumen.size()>0)
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		   #parse("app/admin/BahagianUnit/record_paging_Doc.jsp")
		   </td>
      
	</tr>
	#end 
  <tr>
  <td>
  <input size="50" id="tambahDocBahagian" name="tambahDocBahagian" onchange="uploadDoc(this,'$viewBahagianHQ.ID_SEKSYEN','displayDoc$viewBahagianHQ.ID_SEKSYEN');" type="file"></td>
  </tr> 

<tr>
<td>
<div id="displayDoc$viewBahagianHQ.ID_SEKSYEN">
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
<td   align="center" valign="top" class="$list.rowCss" >$list.BIL.</td>
<!--<td  align="center" valign="top">($list.KOD_AGENSI)</td>-->
<td  align="left" valign="top" class="$list.rowCss" ><a href="javascript:paparDoc('$list.ID_DOKUMEN');"><font color="blue"><u>$list.NAMA_DOKUMEN</u></font></a></td>

<td align ="center" valign="top" class="$list.rowCss" >
<a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('displayDoc$viewBahagianHQ.ID_SEKSYEN','showDokumen','Type=$Type&FLAG_DELETE=Y&ID_SEKSYEN=$viewBahagianHQ.ID_SEKSYEN&ID_DOKUMEN=$list.ID_DOKUMEN');}"><img src="../img/delete.gif" border="0"></a>

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
</td>
</tr>
</table>
</fieldset>
            </td>
            </tr>