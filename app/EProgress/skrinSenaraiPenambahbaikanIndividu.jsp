

<tr >
<td colspan="3" >
<fieldset class="classFade"  >
<table border="0" cellspacing="0" cellpadding="1" width="100%" align="center" > 
<tr  >

#set($onClickTitle = "")
#if($ID_PIC != "")
#set($onClickTitle = "doDivAjaxCall$formname('div_liststat_individu_$ID_PIC','close_listitemby_individu','ID_PIC=$ID_PIC');")
#elseif($ID_MAINMODUL != "")
#set($onClickTitle = "doDivAjaxCall$formname('div_liststat_modul_$ID_MAINMODUL','close_listitemby_modul','ID_MAINMODUL=$ID_MAINMODUL');")
#end

		   <td  width="5%" align="center"></td>
		   <td class="underline_td_main_flagstat"  width="80%" align="left" 
		   style="cursor: pointer;" onClick="$onClickTitle"
		    >
		   #if($flagStat=="JUMLAH_PENAMBAHBAIKAN")
		   JUMLAH PENAMBAHBAIKAN
		   #elseif($flagStat=="JUMLAH_SELESAI_CODING")
		   JUMLAH SELESAI (LOCAL DEVELOPMENT)
		   #elseif($flagStat=="JUMLAH_DELAY")
		   JUMLAH DELAY
		   #elseif($flagStat=="JUMLAH_PATUT_SELESAI")
		   JUMLAH SEPATUTNYA SELESAI AS $CURRENTDATE 	
		   #elseif($flagStat=="JUMLAH_KIV")
		   JUMLAH KIV 
		   #end
		   
		   ($countItem)   
		   </td>
		   <td  width="15%" align="center">
		   </td>	   
		   </tr>   
 #if($listPaparItemPenambahbaikanStats.size()>0) 
 	   #set($setHeaderCount = 0)			         
	   #foreach($mm in $listPaparItemPenambahbaikanStats)
		   #if($mm.LAYERS == "B")
		   <tr  id="div_ITEM_MAINMODUL_$mm.ID_MAINMODUL" >
		   <td class="underline_td_main" align="center"></td>
		   <td  class="underline_td_main"  align="left"  >$mm.MAINMODUL</td>
		   <td class="underline_td_main"  align="center">
		   </td>	   
		   </tr>  
		   #end
		   #if($mm.LAYERS == "C")
		   #set($setHeaderCount = $setHeaderCount + 1)
		   <tr id="div_ITEM_SUBMODUL_$sm.ID_SUBMODUL"  >
	   	   <td class="underline_td_sub" align="center"></td>
	   	   <td class="underline_td_sub"  align="left"  >$mm.MAINMODUL - $mm.SUBMODUL</td>
	       <td class="underline_td_sub"  align="center">
	       </td>	   
	       </tr>
	       #end
	       #if($mm.LAYERS == "D")
	       <tr  >
	   	   <td  align="left" colspan="3" >
	   	   
	   	   <table border="0" cellspacing="0" cellpadding="0" width="100%" > 
	   	       <tr  >
			   <td width="5%" align="left"></td>
			   <td width="5%" align="center"></td>
			   <td width="15%" align="left"></td>
			   <td width="15%" align="left"></td>
			   <td width="15%" align="left"></td>
			   <td width="15%" align="left"></td>
			   <td width="15%" align="left"></td>
			   <td width="15%" align="center"></td>	   
			   </tr> 
	   	   	   #if($setHeaderCount==1)
		   	   <tr class="row2" >
			   <td class="underline_td_item"  align="center" colspan="2"></td>
			   <td class="underline_td_item"  align="left">PENAMBAHBAIKKAN</td>
			   <td class="underline_td_item" align="left">PIC</td>
			   <td class="underline_td_item" align="left">TARIKH</td>
			   <td class="underline_td_item" align="left">STATUS SELESAI</td>
			   <td class="underline_td_item" align="left" colspan="2">CATATAN</td>
			   </tr> 
			   #set($setHeaderCount = 0)
			   #end 
			   <tr id="div_ITEM_PENAMBAHBAIKAN_$mm.ID_PENAMBAHBAIKAN"  >
	   <td class="underline_td_item_content"  align="center" valign="top" ></td>
	   <td class="underline_td_item_content"  align="center" valign="top" >$mm.BIL</td>
	   <td class="underline_td_item_content"  align="left" valign="top" >$mm.PENAMBAHBAIKAN</td>
	   <td class="underline_td_item_content"  align="left" valign="top" >$mm.PIC</td>
	   
	   
	   <td class="underline_td_item_content"  align="left" valign="top" >
	   $mm.TARIKH_MULA
	   <br>
	   -
	   <br>
	   $mm.TARIKH_TARGET_SIAP
	   </td>
	   <td class="underline_td_item_content"  align="left" valign="top" >
	   
	   <table border="0" width="95%" class="underline_table_item_content" >
	   <tr>
	   <td width="89%" valign="top" align="left">
	   LOCAL DEVELOPMENT	   
	   </td>
	   <td width="1%" valign="top" align="center">
	   :
	   </td>
	   <td width="10%" valign="top" align="left">
	    #if($mm.FS_LOCAL==1)
	    <img title="Selesai"  src="../img/validyes.png" border="0">
	    #else
	    <img title="Belum Selesai"  src="../img/validno.png" border="0">
	    #end
	   
	   </td>
	   </tr>
	   <tr>
	   <td valign="top" align="left">
	   STG DEPLOYMENT	   
	   </td>
	   <td valign="top" align="center">
	   :
	   </td>
	   <td valign="top" align="left">
	   	#if($mm.FS_STG==1)
	    <img title="Selesai"  src="../img/validyes.png" border="0">
	    #else
	    <img title="Belum Selesai"  src="../img/validno.png" border="0">
	    #end
	   </td>
	   </tr>	
	   <tr>
	   <td valign="top" align="left">
	   LIVE DEPLOYMENT	   
	   </td>
	   <td valign="top" align="center">
	   :
	   </td>
	   <td valign="top" align="left">
	   	#if($mm.FS_DEV==1)
	    <img title="Selesai"  src="../img/validyes.png" border="0">
	    #else
	    <img title="Belum Selesai"  src="../img/validno.png" border="0">
	    #end
	   </td>
	   </tr>	      
	   </table>
	   </td>	
	   <td class="underline_td_item_content"  align="left" valign="top" >
	   
	   <table border="0" width="95%" class="underline_table_item_content">
	   
	   #if($mm.F_KIV==1)
	   <tr>
	   <td width="89%" valign="top" align="left">
	   STATUS KIV
	   </td>
	   <td width="1%" valign="top" align="center">
	   :
	   </td>
	   <td width="10%" valign="top" align="left">
	   	#if($mm.F_KIV==1)
	    <img title="Selesai"  src="../img/validyes.png" border="0">
	    #else
	    <img title="Belum Selesai"  src="../img/validno.png" border="0">
	    #end
	   </td>
	   </tr>
	   #end
	   <tr>
	   <td valign="top" align="left" colspan="3">
	   $mm.CATATAN	   
	   </td>
	   </tr>	
	   </table>
	   </td>	   
	   <td class="underline_td_item_content"  align="center" valign="top" >
	   <a href="javascript:doDivAjaxCall$formname('div_ITEM_PENAMBAHBAIKAN_$mm.ID_PENAMBAHBAIKAN','edit_penambahbaikkan','ID_PENAMBAHBAIKAN=$mm.ID_PENAMBAHBAIKAN&BIL_TEMP=$mm.BIL&fromStatististik=Y');"><img title="Kemaskini" src="../img/edit.gif" border="0"></a>
	   <!--  
	   <a href="javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){ doDivAjaxCall$formname('div_PENAMBAHBAIKKAN_$ID_SUBMODUL','delete_penambahbaikkan','ID_PENAMBAHBAIKAN=$mm.ID_PENAMBAHBAIKAN&BIL_TEMP=$mm.BIL&ID_SUBMODUL=$mm.ID_SUBMODUL');} "><img title="Hapus"  src="../img/delete.gif" border="0"></a>
	   -->
	   </td>	   
	   </tr>
	   	   </table>
	   	   
	   	   </td>
	       </td>	   
	       </tr>
	       #end
	   #end
	   
 #else
 	   <tr id="div_MAINMODUL_ADD_" >
	   <td class="underline_td_main" width="2%" align="center" valign="top" ></td>
	   <td class="underline_td_main" width="83%" align="left" valign="top" >
	   TIADA REKOD MODUL
	   </td>
	   <td class="underline_td_main" width="15%" align="center" valign="top" >
	   </td>	   
	   </tr>
 #end
</table>
</fieldset>
<br>
</td>
</tr> 