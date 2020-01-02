
<div id="div_SenaraiAgensi$ID_KEMENTERIAN">
<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">
<tr class="table_header" >
<td width="2%" class="underline_td_main">
</td>
<td width="58%" class="underline_td_main">
<font size="3"><strong> Senarai Agensi </strong></font>
</td>
<td width="38%" class="underline_td_main">
</td>
<td width="2%" class="underline_td_main">

</td>
</tr>
</table> 


<table border="0" cellpadding="2" cellspacing="2" align="center" width="98%">
<tr width="100%" >
<td colspan="14">
<table width="100%" align="center">
<tr>
<td>


	<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
#if($listAgensi.size()>0)
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		   #parse("app/admin/Kementerian/record_paging_Agensi.jsp")
         <!--   #parse("app/admin/UV3/record_paging_V3.jsp")-->
		   </td>
      
	</tr>
	#end 	
   
<tr class="table_header" >
<td   align="center" valign="top">Bil.</td>
<td   align="left" valign="top">Kod</td>
<td   align="left" valign="top">Nama Agensi</td>
<td   align="left" valign="top">Tindakan</td>

		   
	</tr>
	#if($listAgensi.size()>0)
	
	<!--gred = userHQ-->
#foreach($list in $listAgensi)
<tr id="div_rowPejabatUrusan$gred.ID_GRED">
<td   align="center" valign="top" class="$list.rowCss2" >$list.BIL</td>
<td  align="left" valign="top" class="$list.rowCss2">$list.KOD_AGENSI</td>
<td  align="left" valign="top" class="$list.rowCss2">$list.NAMA_AGENSI</td>

<td align ="center" valign="top" class="$list.rowCss2"><a href="javascript:doDivAjaxCall$formname('div_viewAgensi$list.ID_AGENSI','viewDetailsAgensi','ID_AGENSI=$list.ID_AGENSI');"><img src="../img/edit.gif" border="0"></a>
<a href="javascript:if(confirm('Data akan dipadam.Adakah Anda Pasti?')){ doAjaxCall${formName}('deletePejabat','ID_AGENSI=$list.ID_AGENSI') } "><img src="../img/delete.gif" border="0"></a>
</td> 
</tr>
<tr  id="div_viewAgensi$list.ID_AGENSI">
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

</td>
</tr>
</table>
</td>
</tr>
</table>
</div>