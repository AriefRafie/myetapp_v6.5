<div id="printableAreaStatIndividu">
#set($Integer = 0)
<table border="0" cellspacing="0" cellpadding="1" width="100%" >  


 #if($listPICStatistik.size()>0)
 	 			         
	   #foreach($ps in $listPICStatistik)
	   #set($tr_content = "underline_td_item_content")
	   #set($td_title = "underline_td_main")
	   #set($table_content = "content_stat")
	   
	  
			   
	   <tr  id="divstat_ITEM_PIC_$ps.ID_PIC"  class="classFade1">
	   <td class="$td_title" width="2%" align="center"></td>
	   <td  class="$td_title" width="83%" align="left"	   
	   >
	   #if($ps.ID_PIC=="999999")
	   <strong>STATISTIK KESELURUHAN</strong>
	   #set($tr_content = "underline_td_item_content_total")
	   #set($table_content = "content_stat_total")	   
	   #else
	   <strong>$ps.PIC</strong>
	   #end
	   </td>
	   <td class="$td_title" width="15%" align="center">
	   
	   </td>	   
	   </tr>
	   
	   <tr class="$tr_content classFade2"  >
	   <td>
	   </td>
	   <td align="center">
		   <table  border="0" cellspacing="0" cellpadding="1" width="100%" >  
		   <tr>
		   <td width="50%" valign="top">
			   <table class="$table_content" border="0" cellspacing="0" cellpadding="2" align="center" width="90%" >
			   <tr>
			   <td width="3%" valign="top" >			   
			   </td>
			   <td width="90%" valign="top">
			   Jumlah Penambahbaikan
			   </td>
			   <td width="1%" valign="top">
			   :
			   </td>
			   <td width="6%" valign="top" style="cursor: pointer;" align="right" onClick="doDivAjaxCall$formname('div_liststat_individu_$ps.ID_PIC','show_listitemby_individu','ID_PIC=$ps.ID_PIC&PIC=$ps.PIC&flagStat=JUMLAH_PENAMBAHBAIKAN&countItem=$ps.JUMLAH_PENAMBAHBAIKAN&CURRENTDATE=$ps.CURRENTDATE&ID_PROJEK='+$jquery('#ID_PROJEK').val());">
			  $ps.JUMLAH_PENAMBAHBAIKAN
			   </td>			   
			   </tr>
			   <tr>
			   <td  valign="top" >			   
			   </td>
			   <td  valign="top">
			   Jumlah Selesai (Local Development)
			   </td>
			   <td  valign="top">
			   :
			   </td>
			   <td valign="top" style="cursor: pointer;" align="right" onClick="doDivAjaxCall$formname('div_liststat_individu_$ps.ID_PIC','show_listitemby_individu','ID_PIC=$ps.ID_PIC&PIC=$ps.PIC&flagStat=JUMLAH_SELESAI_CODING&countItem=$ps.JUMLAH_SELESAI_CODING&CURRENTDATE=$ps.CURRENTDATE&ID_PROJEK='+$jquery('#ID_PROJEK').val());">
			  
			   $ps.JUMLAH_SELESAI_CODING
			   </td>			   
			   </tr>
			   <!--  
			   <tr>
			   <td  valign="top" >			   
			   </td>
			   <td  valign="top">
			   Jumlah Selesai (Staging Deployment)
			   </td>
			   <td  valign="top">
			   :
			   </td>
			   <td  valign="top" align="right">
			   $ps.JUMLAH_SELESAI_DPY_STG
			   </td>			   
			   </tr>
			   <tr>
			   <td  valign="top" >			   
			   </td>
			   <td  valign="top">
			   Jumlah Selesai (Live Deployment)
			   </td>
			   <td  valign="top">
			   :
			   </td>
			   <td  valign="top" align="right">
			   $ps.JUMLAH_SELESAI_DPY_LIVE
			   </td>			   
			   </tr>
			   -->
			   <tr>
			   <td  valign="top" >			   
			   </td>
			   <td  valign="top">
			   Jumlah Delay
			   </td>
			   <td  valign="top">
			   :
			   </td>
			   
			   
			   #set($style_delay = "")
			   #set($style_blink_delay = "")
	   		   
			   #if($Integer.parseInt($ps.JUMLAH_DELAY) > 0)			   
			   #set($style_delay = "color:red;")
			   #set($style_blink_delay = "blink")
			   #end			
			      
			   <td  class="$style_blink_delay" style="$style_delay cursor: pointer"  valign="top"  align="right" onClick="doDivAjaxCall$formname('div_liststat_individu_$ps.ID_PIC','show_listitemby_individu','ID_PIC=$ps.ID_PIC&PIC=$ps.PIC&flagStat=JUMLAH_DELAY&countItem=$ps.JUMLAH_DELAY&CURRENTDATE=$ps.CURRENTDATE&ID_PROJEK='+$jquery('#ID_PROJEK').val());">
			  
			   $ps.JUMLAH_DELAY  
			   </td>			   
			   </tr>
			   <tr>
			   <td  valign="top" >			   
			   </td>
			   <td  valign="top">
			   Jumlah Sepatutnya Selesai as $ps.CURRENTDATE
			   </td>
			   <td  valign="top">
			   :
			   </td>
			   <td   valign="top" style="cursor: pointer;" align="right" onClick="doDivAjaxCall$formname('div_liststat_individu_$ps.ID_PIC','show_listitemby_individu','ID_PIC=$ps.ID_PIC&PIC=$ps.PIC&flagStat=JUMLAH_PATUT_SELESAI&countItem=$ps.JUMLAH_PATUT_SELESAI&CURRENTDATE=$ps.CURRENTDATE&ID_PROJEK='+$jquery('#ID_PROJEK').val());">
			  
			   $ps.JUMLAH_PATUT_SELESAI
			   </td>			   
			   </tr>
			   <tr>
			   <td  valign="top" >			   
			   </td>
			   <td  valign="top">
			   Jumlah KIV
			   </td>
			   <td  valign="top">
			   :
			   </td>
			   <td   valign="top" style="cursor: pointer;" align="right" onClick="doDivAjaxCall$formname('div_liststat_individu_$ps.ID_PIC','show_listitemby_individu','ID_PIC=$ps.ID_PIC&PIC=$ps.PIC&flagStat=JUMLAH_KIV&countItem=$ps.JUMLAH_KIV&CURRENTDATE=$ps.CURRENTDATE&ID_PROJEK='+$jquery('#ID_PROJEK').val());">
			  
			   $ps.JUMLAH_KIV
			   </td>			   
			   </tr>
			   </table>
		   </td>
		   <td width="50%" valign="top">
		   
		   <table class="$table_content" border="0" cellspacing="0" cellpadding="2" width="90%" align="center" >
			   <tr>
			   <td width="3%" valign="top" >			   
			   </td>
			   <td width="86%" valign="top">
			   Progress Local Development as $ps.CURRENTDATE (%)
			   </td>
			   <td width="1%" valign="top">
			   :
			   </td>
			   <td width="10%" valign="top" align="right">
			   $ps.PERATUS_PROGRESS
			   
			   </td>			   
			   </tr>
			   
			   
			   
			   
			   <tr>
			   <td  valign="top" >			   
			   </td>
			   <td  valign="top">
			   	Ahead / Behind (%)
			   </td>
			   <td  valign="top">
			   :
			   </td>
			   #set($style_progress = "")
			   #set($style_blink_progress = "")
	   		   
			   #if($ps.PERATUS_AHEADBEHIND > 0.00)			   
			   	#set($style_progress = "color:green;")
			   #elseif($ps.PERATUS_AHEADBEHIND < 0.00)
			   	#set($style_progress = "color:red;")
			  	#set($style_blink_progress = "blink")
			   #end			   
			   <td class="$style_blink_progress"  style="$style_progress" valign="top" align="right">
			   $ps.PERATUS_AHEADBEHIND
			   </td>			   
			   </tr>
			   
			   
			   <tr>
			   <td  valign="top" >			   
			   </td>
			   <td  valign="top">
			   Overall Progress Local Development (%)
			   </td>
			   <td  valign="top">
			   :
			   </td>
			   <td valign="top" align="right">
			   $ps.PERATUS_OVERALLPROGRESS			   
			   </td>			   
			   </tr>
			   
			   </table>
		   
		   </td>	   
		   </tr>
		   </table>	   
	   </td>
	   <td>
	   </td>   
	   </tr>
	   
	   <tr class="underline_td_item_content" id="div_liststat_individu_$ps.ID_PIC">
	   <td >
	   </td>
	   <td>
	   </td>
	   <td>
	   </td>   
	   </tr>
	   
	  
	   #end
	   
	   
 #else
 	   <tr  >
	   <td class="underline_td_main" width="2%" align="center" valign="top" ></td>
	   <td class="underline_td_main" width="83%" align="left" valign="top" >
	   TIADA REKOD INDIVIDU
	   </td>
	   <td class="underline_td_main" width="15%" align="center" valign="top" >
	   </td>	   
	   </tr>
 #end
</table>
</div>

<input type="button" id="BTNPRINTSTATINDIVIDU" name="BTNPRINTSTATINDIVIDU" onclick="printDiv('printableAreaStatIndividu','individu','2')" value="Cetak" />
#if($current_role=="Admin_Eprogress")
<input type="button" id="BTN_HANTAREMELINDIVIDU" name="BTN_HANTAREMELINDIVIDU" onClick="hantarEmel('printableAreaStatIndividu','individu','2')" value="Hantar Progress Emel" >
<input type="hidden" id="textAreaStatIndividu" name="textAreaStatIndividu" value="">
#end

<script type="text/javascript">
<!--
doDivAjaxCall$formname('div_main_statistik_modul','blank_statistik','ID_PROJEK='+$jquery('#ID_PROJEK').val());
//doDivAjaxCall$formname('div_main_statistik_individu','blank_statistik','ID_PROJEK='+$jquery('#ID_PROJEK').val());
doDivAjaxCall$formname('div_by_modul','blank_statistik','ID_PROJEK='+$jquery('#ID_PROJEK').val());
//-->
</script>
  



              