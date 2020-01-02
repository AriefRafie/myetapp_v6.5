
<!--<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
Statistik Kementerian dan Agensi <img width="20" height="20" src="../img/images_stat.png" onclick="doCetakStats('statKJP')">
</table>
-->



<br />
<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">
<tr class="table_header" >
<td width="2%" class="underline_td_main">
</td>
<td width="58%" class="underline_td_main">
<font size="3"><strong> Senarai Kementerian </strong></font>
</td>
<td width="38%" class="underline_td_main">
</td>
<td width="2%" class="underline_td_main">
<a href="javascript:doDivAjaxCall$formname('div_AddKementerian','addKementerian','ID_KEMENTERIAN=');"><img title="Tambah Kementerian" src="../img/plus3.gif" border="0"></a>
<!--<input type="button" id="cmdTambahKementerian" name="cmdTambahKementerian"   
onClick="doDivAjaxCall$formname('div_AddKementerian','addKementerian','ID_KEMENTERIAN=');" value="Tambah Kementerian" > -->
</td>
</tr>
</table>
<fieldset id="div_rowGred">

<table width="100%" id="div_viewGred" style="display:none">
<tr >
</tr>
</table>



<table width="100%" align="center">
<tr>
<td>


	<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($listKementerian.size()>0)
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		   #parse("app/admin/Kementerian/record_paging.jsp")
         <!--   #parse("app/admin/UV3/record_paging_V3.jsp")-->
		   </td>
      
	</tr>
	#end 
   
<tr class="table_header" >
<td   align="center" valign="top" >Bil.</td>
<td   align="center" valign="top">Kod</td>
<td   align="left" valign="top">Nama Kementerian</td>
<td   align="center" valign="top">Tindakan</td>

		   
	</tr>
	#if($listKementerian.size()>0)
	
	<!--gred = userHQ-->
#foreach($list in $listKementerian)
<tr id="div_rowPejabatUrusan$gred.ID_GRED">
<td   align="center" valign="top" class="$list.rowCss">$list.BIL</td>
<td  align="center" valign="top" class="$list.rowCss">($list.KOD_KEMENTERIAN)</td>
<td  align="left" valign="top" class="$list.rowCss">$list.NAMA_KEMENTERIAN</td>

<td align ="center" valign="top" class="$list.rowCss"><a href="javascript:doDivAjaxCall$formname('div_viewKementerian$list.ID_KEMENTERIAN','viewDetailsKementerian','ID_KEMENTERIAN=$list.ID_KEMENTERIAN');"><img src="../img/edit.gif" border="0"></a>
<a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_senaraiUtama','showSenaraiKementerian','ID_KEMENTERIAN=$list.ID_KEMENTERIAN&FLAG_DELETE=Y');}">
<img src="../img/delete.gif" border="0"></a>
</td> 
</tr>
<tr  id="div_viewKementerian$list.ID_KEMENTERIAN">
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

#if($listKementerian.size()>0)
	<script>
	var butoncetakCT =  document.getElementById('cmdCetakPejabat');
	//alert(' masuk sini ');
	if (typeof(butoncetakCT) != 'undefined' && butoncetakCT != null)
    {
    	butoncetakCT.style.display = "";
    }
	
	var butoncetakCT2 =  document.getElementById('cmdCetakAll');
	//alert(' masuk sini ');
	if (typeof(butoncetakCT2) != 'undefined' && butoncetakCT2 != null)
    {
    	butoncetakCT2.style.display = "";
    }
	</script>
	#end
	<div id="SenaraiForPrint" style="display:none">	
	</div>
    <div id="SenaraiForPrintAll" style="display:none">	
	</div>
   

</td>
</tr>
</table>
</fieldset>

<tr><td>
  <div align="center">
    <input  type="button" id="cmdCetakPejabat" name="cmdCetakPejabat" value="Cetak Kementerian" 
onClick="$jquery('#cetakReport').val('Y');doDivAjaxCall$formname('SenaraiForPrint','cetakListKementerian','FlagCetak=Y&ID_NEGERI='+$jquery('#Form_id_negeri').val());"  />
<input  type="button" id="cmdCetakAll" name="cmdCetakAll" value="Cetak Keseluruhan" 
onClick="$jquery('#cetakReport').val('Y');doDivAjaxCall$formname('SenaraiForPrintAll','cetakAllKementerian','');"  />

  </div></td></tr>