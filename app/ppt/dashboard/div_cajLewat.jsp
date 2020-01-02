
#if($listFailCaj.size()>0)

   <div> 
   <input type="hidden" name="idpermohonan" />
   <input type="hidden" name="idSimati" />
   <input type="hidden" name="flagFromSenaraiPermohonanSek8"/> 
   
   
<table width="90%" align="center" border="0"  class="alert">   
   <tr>
   <td>
   
   </td>
   </tr> 
   <tr>
   <td width="2%" valign="top" align="right">   
   </td>
   <td width="98%"> 
   <div>
   
   <a href="javascript:getListCaj()" title="Caj Lewat Bayaran yang melebihi 90 hari dari tarikh serahan Borang H.">    
    #if($listFailCaj.size() > 0)                         
                             <label style="background-color:red"  align="center" valign="top" > 
                            <b><font color="WHITE"><span class="blink">&nbsp;$listFailCaj.size()</span></font></b>
                             </label>&nbsp;
                             #end
    Caj Lewat Bayaran yang melebihi 90 hari dari tarikh serahan Borang H. 
      
   </a> 
   <div  id="div_listCaj"  ></div>
   
   </td>    
   </tr>
   </table>
 
   </div>
   
   	
   </div>    
   #end
   
   <script>
$jquery(document).ready(function () {
	//doDivAjaxCall$formname('dashboard_tab','getTabDashboard','');	
	doDivAjaxCall$formname('Carta_Main','getStatsFailHakmilik','');	
	//doDivAjaxCall$formname('div_carianMain','getCarianMain','');
	//doDivAjaxCall$formname('div_getMainOnline','getMainOnline','');	
	//doDivAjaxCall$formname('Carta_Main','getStatsFailHakmilik','');	
	//doDivAjaxCall$formname('showCajLewat','getLewatCaj','');	
});
</script>