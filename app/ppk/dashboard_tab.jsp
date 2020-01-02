<table width="100%" border="0" class="classFade dashboard_bawah" >
<tr>
<td>


<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
  
   
    #if($list_memo_aktif.size()>0)
    <li class="TabbedPanelsTab" tabindex="0" id="Pengumuman_Head" >Pengumuman</li>
    #end
    
 
  
    <li class="TabbedPanelsTab" tabindex="0" id="Peringatan_Head"  ><font color="red" class="blink"><blink>Peringatan!</blink></font>
    </li>
   
    <li class="TabbedPanelsTab" tabindex="0"  id="Carta_Head"  >Carta</li>
  </ul>
     <div class="TabbedPanelsContentGroup">
   
   
#if($list_memo_aktif.size()>0)
<div class="TabbedPanelsContent"  id="Peringatan_Main" style="height:300" >  
<table width="100%" >
<tr>
<td width="12%" align="center" valign="top"><img width="30" height="30" src="../img/online/kblogger.png" align="center"/></td>
<td width="88%" valign="top">
<table width="100%">
<!--
<tr>
<td  valign="middle">
<b>Pengumuman</b></td>
</tr>
-->

<tr>
<td   valign="top" >


#parse("app/ppk/frmPengumuman.jsp")

</td>
</tr>
</table>
</td>
</tr>
</table>
</div>
#end



   <div class="TabbedPanelsContent" id="Peringatan_Main">  
   <div style="height:250"> 
   <input type="hidden" name="idpermohonan" />
   <input type="hidden" name="idSimati" />
   <input type="hidden" name="flagFromSenaraiPermohonanSek8"/>   
   
   <div id="showBorangB_stats">
		 <script>
        $jquery(document).ready(function () {
            doDivAjaxCall$formname('showBorangB_stats','showBorangB_stats','');	
                
        });
        </script>
   </div>
   
  
   
  

   
   
   <!--
   ALERT XDAPAT DISPLAY, QUERYTERLALU BERAT
   <table width="90%" align="center" border="0" class="alert"> 
   <tr>
   <td>
   
   </td>
   </tr>   
   <tr > 
   <td width="100%">
   Jumlah fail dimana maklumat serahan notis yang tidak dikemaskini sehingga tarikh bicara : 
   <a href="javascript:gotoSenaraiBorangB()" title="Fail-fail permohonan dimana maklumat keputusan permohonan tidak dimasukan sedangkan fail permohonan ini telah cukup tempoh 30 hari daripada tarikh Borang B dicetak.">
   <font  color="red" onClick="gotoSenaraiBorangB()" ><b><blink></blink></b></font>    
   </a>
   </td>    
   </tr>
   </table>
   -->
   
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
    var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
	</script>


 	
   
    
    
    
    
    
    
  
   
    
   
    