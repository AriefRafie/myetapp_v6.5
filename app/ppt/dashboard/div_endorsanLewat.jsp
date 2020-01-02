#if($listHakmilik.size()>0)

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
   
   <a href="javascript:getListEndorsanK()" title="Hakmilik yang telah dihantar melebihi 14 hari dari tarikh hantar endorsan.">    
    #if($listHakmilik.size() > 0)                         
                             <label style="background-color:red"  align="center" valign="top" > 
                            <b><font color="WHITE"><span class="blink">&nbsp;$listHakmilik.size()</span></font></b>
                             </label>&nbsp;
                             #end
    Hakmilik yang telah dihantar melebihi 14 hari dari tarikh hantar endorsan. 
      
   </a> 
   <div  id="div_listEndorsanK"  ></div>
   
   </td>    
   </tr>
   </table>
 
   </div>
   
   	
   </div>    
   #end
   <script>
$jquery(document).ready(function () {
	
	doDivAjaxCall$formname('showCajLewat','getLewatCaj','');	
	//doDivAjaxCall$formname('dashboard_tab','getTabDashboard','');	
	//doDivAjaxCall$formname('Carta_Main','getStatsFailHakmilik','');	
	//doDivAjaxCall$formname('div_carianMain','getCarianMain','');
	//doDivAjaxCall$formname('div_getMainOnline','getMainOnline','');			
});
</script>