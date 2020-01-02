<div id="div_LaporanIntegrasiforPrint_$Type">

<fieldset id="div_rowGred">
<legend><strong> Senarai Integrasi #if ($Type.equals("1")) Dalaman #elseif ($Type.equals("2")) Luaran #elseif ($Type.equals("3")) Sistem #end </strong></legend>
<table width="100%" align="center">
<tr>
<td>

<table width="100%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" > 
   
<tr class="table_header" >
<td width="16%"   align="center" valign="top">Bil.</td>
<td width="18%"   align="center" valign="top">Kod</td>
<td width="46%"   align="center" valign="top">Nama Agensi</td>
<!--<td width="20%"   align="center" valign="top">Tindakan</td>-->

		   
	</tr>
	#if($listIntegrasi.size()>0)
	<!--gred = userHQ-->
#foreach($list in $listIntegrasi)
<tr id="div_rowPejabatUrusan$gred.ID_GRED">
<td   align="center" valign="top" >$list.BIL</td>
<td  align="center" valign="top">($list.KOD_AGENSI)</td>
<td  align="left" valign="top">$list.NAMA</td>

<!--<td align ="center" valign="top"><a href="javascript:doDivAjaxCall$formname('div_viewInteg$list.ID_INTEGRASI','viewDetailsIntegrasi','ID_INTEGRASI=$list.ID_INTEGRASI&Type=$Type');"><img src="../img/edit.gif" border="0"></a>
<a href="javascript:if(confirm('Data akan dipadam.Adakah Anda Pasti?')){ doAjaxCall${formName}('deletePejabat','ID_INTEGRASI=$list.ID_INTEGRASI') } "><img src="../img/delete.gif" border="0"></a>
</td>--> 
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
</div>

#if($FlagCari=="Y" && $Type=="1")
<script>  
		  $jquery(document).ready(function () {
			  doDivAjaxCall$formname('div_ListInteg2','showSenaraiInteg','FlagCari=Y&Type=2');			 	  
		  });
		  
</script>
#end

#if($FlagCari=="Y" && $Type=="2")
<script>  
		  $jquery(document).ready(function () {
			  doDivAjaxCall$formname('div_ListInteg3','showSenaraiInteg','FlagCari=Y&Type=3');			 	  
		  });
		  
</script>
#end


<script type="text/javascript">
	printHideDiv('div_LaporanIntegrasiforPrint_$Type','$Type');
</script>