
<fieldset>
<legend><strong> Statistik Aduan Agensi Mengikut Kategori Aduan </strong></legend>
<table width="100%%" align="center" border="0" cellpadding="0" cellspacing="0">
<tr  >
<td width="2%" >
</td>
<td width="58%" ></td>
<td width="38%" >
</td>
<td width="2%" >

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

#if($listAduanAgensi.size()>0)
	<tr >
		   <td  align="left" valign="top" colspan="14" >
		   #parse("app/admin/Kementerian/record_pagingSmall.jsp")
         <!--   #parse("app/admin/UV3/record_paging_V3.jsp")-->
		   </td>
      
	</tr>
	#end 
	
<tr class="table_header" >
<td   align="center" valign="top">Bil.</td>
<td   align="left" valign="top">Jawatan</td>
<td   align="center" valign="top">Jumlah Pengguna</td>
</tr>
#if($listAduanAgensi.size()>0)
#set ($jumlah = 0)
#foreach($list in $listAduanAgensi)
#set ($jumlah = $jumlah + $EkptgUtil.parseInt($list.TOTALADUAN))
#set( $i = $velocityCount ) 
#if ( ($i % 2) != 1 ) #set( $row = "row2" ) 
#else #set( $row = "row1" ) #end
<tr id="div_rowPejabatUrusan$gred.ID_GRED">
<td   align="center" valign="top" class="$list.rowCss">$list.BIL</td>
<td  align="left" valign="top" class="$list.rowCss">$list.NAMA_AGENSI</td>
<td  align="center" valign="top" class="$list.rowCss">$list.TOTALADUAN</td>
</tr>
#end
<tr>
<td colspan="11">&nbsp;</td>
</tr>
<!--<tr valign="top" class="$row">
<td class="$row"></td>
<td class="$row"><div align="right">
<strong>JUMLAH</strong>
</div></td>
<td class="$row"><div align="center">
<strong>$jumlah</strong>
</div></td>
</tr>-->
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
</fieldset>