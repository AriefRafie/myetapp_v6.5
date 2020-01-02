
#if($commandFrom != "list")
<script>
document.getElementById('span_LINK_CARIAN').style.display = "";
</script>
#end
    



<tr id="div_row$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" >
<td align="left" valign="top" colspan="14" style="border-bottom: 1px solid black;border-top: 1px solid black;" >
<div id="printableArea_$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM">

	#set($tableWidth = "100%")
	#if($ID_WARTATRMINDUK == "" && $JENISSUB == "" && $view.ID_WARTATRM != "")
	<fieldset >
	<legend>	
	MAKLUMAT TANAH RIZAB MELAYU #if($view.NO_WARTA!="")[$view.JENISWARTA, NO. WARTA : $view.NO_WARTA] #end
	</legend>
    #set($tableWidth = "95%")
    #end
    
    
    <table width="$tableWidth" align="center" >
    <tr>
    <td width="100%">
    <!-- ::::::::: $viewWartaAsal -->
    #if($viewWartaAsal.size()>0)
        #parse("app/pdt/trm/viewWartaAsal.jsp")
    #end
    <div id="div_editTRM$view.ID_WARTATRM" >
    #parse("app/pdt/trm/edit_TRM.jsp")
    </div>
    
    <!-- open history -->
    #if($view.ID_WARTATRM != "" && $mode == "view" && $ID_WARTATRMINDUK == "" && $JENISSUB == "")
        #if($view.FLAG_JENISWARTA == "W") 
        #set($divSenarai = "div_Senarai"+"B"+$view.ID_WARTATRM )  
        <div id="$divSenarai" >
        <script>
          $jquery(document).ready(function () {
             doDivAjaxCall$formname('$divSenarai','showSenaraiBatal','ID_WARTATRMINDUK=$view.ID_WARTATRM&LOCATION=$divSenarai&JENISSUB=B&commandFrom=$commandFrom&BIL=$BIL&rowCss=$rowCss'); 
          });
        </script>	
        </div>
        
        #set($divSenarai = "div_Senarai"+"G"+$view.ID_WARTATRM )  
        <div id="$divSenarai" >       
        </div>
        
        #else
        <script> 
		  $jquery(document).ready(function () {
			  doDivAjaxCall$formname('div_ListHISTORY$view.ID_WARTATRM','displayUtamaHISTORY','&carianTerperinciHISTORY=&TARIKH_MULA_HISTORY=&TARIKH_AKHIR_HISTORY=&ID_WARTATRM=$view.ID_WARTATRM&FLAG_DEFAULT_HISTORY=Y&ID_WARTATRMINDUK=&JENISSUB=&commandFrom=$commandFrom&BIL=$BIL&rowCss=$rowCss');		 	  
		  });	  
    	</script>	
        #end
   	#end
    
    <div id="div_ListHISTORY$view.ID_WARTATRM" >
        
    </div>
  
     
    <!-- close history --> 
    
   </td>
   </tr>
   </table>
	 
    
	
	#if($ID_WARTATRMINDUK == "" && $JENISSUB == "")
	</fieldset>
    #end
	</div>
	
	<br>
    
</td>		
</tr>

