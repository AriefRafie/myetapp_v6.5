



#if($!div_carianFailCaj_open == "Y")
#set($count_carianFailCaj = 0)
#foreach ($list in $listFailCaj)
#set($count_carianFailCaj = $count_carianFailCaj + 1)
#end

<input type="hidden" id="div_carianFailCaj_open" name="div_carianFailCaj_open" value="Y">
<input type="hidden" id="val_count_carianFailCaj" name="val_count_carianFailCaj" value="$!count_carianFailCaj" />

   				  #if($!count_carianFailCaj > 0) 
						<font color="blue">
                         
                           
                                <li>                    
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><span class="blink">$!count_carianFailCaj</span></font></b>
                             </label>&nbsp;
                                                      
                            <a href="javascript:getListFailCaj()" class="help" title="Senarai Fail"><font color="blue">Fail</font></a>
                            </li>
                             
                            </font>						
				 #end 
                  		<div  id="div_listFailCaj"  style="width:40"></div>

#end
 #parse("app/ppt/dashboard/script.jsp")