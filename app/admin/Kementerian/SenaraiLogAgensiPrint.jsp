
<div id="LogAgensi">
<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">
<tr class="table_header" >
<td width="2%" class="underline_td_main">
</td>
<td width="58%" class="underline_td_main">
<font size="3"><strong> Senarai Kementerian dan Jabatan/ Agensi dan Tahap Penggunaan ( Jumlah Log Masuk )</strong></font></td>
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


<table width="100%" border="1" cellpadding="1" cellspacing="0" bordercolor="#000000" >
 <td width="70%"   align="center" valign="top">Kementerian</td>
<td width="30%"   align="center" valign="top">Jumlah Log Masuk</td>
#if($listLogAgensi.size()>0)
#foreach($list in $listLogAgensi)

#if($list.LAYER=="1")
   <tr >
	<td colspan="5" border="0"  style="border-bottom: 0px solid #000;font-size: 100%;"><br />
   <b>$list.LAYER_1</b><br /></td>
  </tr>
  #if($list.LAYER=="1" && $list.ID_AGENSI=="" )
  <tr class="table_header">
	<td border="0" >Nama Agensi</td>
    <td border="0" align="center">Jumlah Log Masuk</td>
  </tr>
  #end
    #end
    


    
#if($list.LAYER=="2")  
<tr id="div_rowPejabatUrusan$gred.ID_GRED">
<td  align="left" valign="top" >
#if (!$list.LAYER_2.equals(""))
$list.LAYER_2
#else 
Tiada Rekod
#end
</td>
#if ($list.TOTALLOG != 0)
<td  align="center" valign="top" >$list.TOTALLOG</td>
#end
</tr>
#end
#end
<tr>
<td colspan="11">&nbsp;</td>
</tr>

#else
<tr >
<td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
</tr>
#end

<br />
</table>

</td>
</tr>
</table>
</td>
</tr>
</table>
</div>


<script>
$jquery(document).ready(function () {
		printHideDiv2('LogAgensi');
		
	});
</script>
