



#if($!div_carianPB_open == "Y")
#set($count_carianPB = 0)
#foreach ($list in $listPB)
#set($count_carianPB = $count_carianPB + 1)
#end

<input type="hidden" id="div_carianPB_open" name="div_carianPB_open" value="Y">
<input type="hidden" id="val_count_carianPB" name="val_count_carianPB" value="$!count_carianPB" />


<div id="display_when_zero"></div>


<script type="text/javascript" >

if (document.getElementById('val_count_carianPB') != null && document.getElementById('val_count_carianPB') != 'undefined' 
&& document.getElementById('val_count_carianHakmilik') != null && document.getElementById('val_count_carianHakmilik') != 'undefined'
&& document.getElementById('val_count_carianFail') != null && document.getElementById('val_count_carianFaik') != 'undefined') 
{
	if(document.getElementById('val_count_carianPB').value == '0' && document.getElementById('val_count_carianHakmilik').value == '0' && document.getElementById('val_count_carianFail').value == '0')
	{
	$jquery("#display_when_zero").html("<span class="blink"><font color='blue'>Tiada Rekod</font></span>");
	
	}
	else
	{
	$jquery("#display_when_zero").html("");	
	}
	
}
else
{
	$jquery("#display_when_zero").html("");	
}


</script>

  #if($!count_carianPB > 0) 
						<font color="blue"><li>
                          
                            
                                                    
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><span class="blink">$!count_carianPB</span></font></b>
                             </label>&nbsp;
                                                      
                            <a href="javascript:getListPB()" class="help" title="Senarai Pihak Berkepentingan"><font color="blue">Pihak Berkepentingan</font></a>
                            </li></font>
                            #end 						
				 
                  		<div  id="div_listPB"  style="width:40"></div>

#end

 #parse("app/ppt/dashboard/script.jsp")