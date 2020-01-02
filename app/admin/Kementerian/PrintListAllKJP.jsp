<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">
<tr class="table_header" >
<td width="2%" class="underline_td_main">
</td>
<td width="58%" class="underline_td_main">
<font size="5"><strong> Senarai Kementerian dan Jabatan/Agensi </strong></font>
</td>
<td width="38%" class="underline_td_main">
</td>
<td width="2%" class="underline_td_main">

</td>
</tr>
</table>
<br />

<table width="100%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000">
<tr>
<td colspan="10">
	#if($listAll.size()>0)
	#foreach($senaraiKJP in $listAll)
	#if($senaraiKJP.LAYER=="1")
   <tr class="table_header">
   <td colspan="5" border="0" >
   Nama Kementerian : <b>$senaraiKJP.LAYER_1</b><br />
   Nama Agensi : #if($senaraiKJP.LAYER=="1" && $senaraiKJP.ID_AGENSI=="") #else - #end </td>
  </tr>
    #end
    
   <!-- #if($listAll.size()>0)
	<tr class="table_header" >
		<td align="center" valign="top">Kod Agensi</td>
		<td align="left" valign="top"  colspan="2">Nama Jabatan/Agensi</td>
	</tr>
	#end-->
    
    #if($senaraiKJP.LAYER=="2")
  <tr>
    <td width="9%" align="center"   valign="top" border="0">$senaraiKJP.BIL.</td>
    <td width="81%" align="left"   valign="top"  border="0">($senaraiKJP.KOD_KJP) $senaraiKJP.LAYER_2 </td>
    <td width="10%" align="left"   valign="top" border="0"></td>
  </tr>
   
    #end
    
  
    #end
   #end
<br />
</td>
</tr>
</table>
</div>

<script>
$jquery(document).ready(function () {
		printHideDiv2('SenaraiForPrintAll');
		
		//var divPrint =  document.getElementById('cmdCetakAll');
		//divPrint.style.display = "none";
		
		var SenaraiForPrintAll =  document.getElementById('SenaraiForPrintAll');
		SenaraiForPrintAll.style.display = "none";
		
	});
</script>