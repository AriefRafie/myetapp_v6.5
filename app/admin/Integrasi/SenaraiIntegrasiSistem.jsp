<fieldset id="div_rowGred">
<legend><strong> Senarai Integrasi Antara Sistem </strong></legend>
<!--<legend><strong> Senarai Integrasi #if ($Type.equals("1")) Dalaman #elseif ($Type.equals("2")) Luaran #elseif ($Type.equals("3")) Sistem #end </strong></legend>-->
<table width="100%" align="center">
<tr>
<td>


	<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($listIntegrasi.size()>0)
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		   #parse("app/admin/Integrasi/record_paging.jsp")
		   </td>
      
	</tr>
	#end 
   
<tr class="table_header" >
<td width="16%"   align="center" valign="top">Bil.</td>
<!--<td width="18%"   align="center" valign="top">Kod</td>-->
<td width="46%"   align="center" valign="top">Nama Agensi </td>
<td width="20%"   align="center" valign="top">Tindakan</td>

		   
	</tr>
	#if($listIntegrasi.size()>0)
	<!--gred = userHQ-->
#foreach($list in $listIntegrasi)

#set ($Type = $list.ID_JENISINTEG_INT)
<tr id="div_rowPejabatUrusan$gred.ID_GRED">
<td   align="center" valign="top" class="$list.rowCss">$list.BIL.</td>
<!--<td  align="center" valign="top">($list.KOD_AGENSI)</td>-->
<td  align="left" valign="top" class="$list.rowCss">$list.NAMA</td>

<td align ="center" valign="top" class="$list.rowCss">
<input type="hidden" value="$list.ID_JENISINTEG_INT" name="type$list.ID_INTEGRASI" id="type$list.ID_INTEGRASI" />
<a href="javascript:doDivAjaxCall$formname('div_viewInteg$list.ID_INTEGRASI','viewDetailsIntegrasi','ID_INTEGRASI=$list.ID_INTEGRASI&Type=$Type');"><img src="../img/edit.gif" border="0"></a>

<a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_ListInteg$Type','showSenaraiInteg$Type','Type=$Type&FlagDelete=Y&ID_INTEGRASI=$list.ID_INTEGRASI');}"><img src="../img/delete.gif" border="0"></a>

</td> 
</tr>

<tr  id="div_viewInteg$list.ID_INTEGRASI">
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

#if($listIntegrasi.size()>0)
	<script>
	var butoncetakCT =  document.getElementById('cmdCetakPejabat');
	//alert(' masuk sini ');
	if (typeof(butoncetakCT) != 'undefined' && butoncetakCT != null)
    {
    	butoncetakCT.style.display = "";
    }
	</script>
	#end
	<div id="SenaraiForPrint" style="display:none">	
	</div>

</td>
</tr>
</table>
</fieldset>

