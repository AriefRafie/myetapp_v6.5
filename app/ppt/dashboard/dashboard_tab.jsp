
<table width="100%" border="0" class="classFade dashboard_bawah"  >
<tr>
<td>


<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
  
   
    #if($list_memo_aktif.size()>0)
    <li class="TabbedPanelsTab" tabindex="0" id="Pengumuman_Head" >Pengumuman</li>
    #end
    
     <li class="TabbedPanelsTab" tabindex="0" id="Peringatan_Head"   >
     
       
      <span class="blink">PERINGATAN !</span>
      
      </li>  
 
    
     
    <li class="TabbedPanelsTab" tabindex="0"  id="Carta_Head"  >Carta</li>
  </ul>
     <div class="TabbedPanelsContentGroup"  id="group_tab" >
   
   
#if($list_memo_aktif.size()>0)
<div class="TabbedPanelsContent"  id="Memo_Main"  >  
<table width="100%" id="table_memo" >
<tr>
<td width="12%" align="center" valign="top"><img width="30" height="30" src="../img/online/kblogger.png" align="center"/></td>
<td width="88%" valign="top">
<table width="100%">
<tr>
<td   valign="top" >
#parse("app/ppt/dashboard/frmPengumuman.jsp")
</td>
</tr>
</table>
</td>
</tr>
</table>
</div>
#end




<div class="TabbedPanelsContent" >  

<div id="showLewatEndorsanK">
  <script>
$jquery(document).ready(function () {
	//doDivAjaxCall$formname('dashboard_tab','getTabDashboard','');	
	doDivAjaxCall$formname('showLewatEndorsanK','getLewatEndorsanK','');	
	//doDivAjaxCall$formname('div_carianMain','getCarianMain','');
	//doDivAjaxCall$formname('div_getMainOnline','getMainOnline','');			
});
</script>

</div>

<div id="showCajLewat">

</div>


</div>  



<div class="TabbedPanelsContent" id="Carta_Main">   
   

</div>
  
   </div>
</div>
 


</td>
</tr>
</table>

    <script>
	//alert("XXXXXXXXXX :"+document.getElementById('table_utama').clientHeight);
	/*var total_list = '$list_memo_aktif.size()';	
	document.getElementById('group_tab').style.height = (document.getElementById('table_utama').clientHeight - 350);
	if(total_list>0)
	{	
	document.getElementById('div_pengumuman').height = (document.getElementById('table_utama').clientHeight - 300);
	}*/	
    var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
	</script>


 	
   
    
    
    
    
    
    
  
   
    
   
     #parse("app/ppt/dashboard/script.jsp")