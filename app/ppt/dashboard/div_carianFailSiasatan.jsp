



#if($!div_carianFailSiasatan_open == "Y")
#set($count_carianFailSiasatan = 0)
#foreach ($list in $listFailSiasatan)
#set($count_carianFailSiasatan = $count_carianFailSiasatan + 1)
#end

<input type="hidden" id="div_carianFailSiasatan_open" name="div_carianFailSiasatan_open" value="Y">
<input type="hidden" id="val_count_carianFailSiasatan" name="val_count_carianFailSiasatan" value="$!count_carianFailSiasatan" />

   				  #if($!count_carianFailSiasatan > 0) 
						<font color="blue">
                         
                           
                                <li>                    
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><span class="blink">$!count_carianFailSiasatan</span></font></b>
                             </label>&nbsp;
                                                      
                            <a href="javascript:getListFailSiasatan()" class="help" title="Senarai Fail Siasatan"><font color="blue">Fail</font></a>
                            </li>
                             
                            </font>						
				 #end 
                  		<div  id="div_listFailSiasatan"  style="width:40"></div>

#end
 #parse("app/ppt/dashboard/script.jsp")