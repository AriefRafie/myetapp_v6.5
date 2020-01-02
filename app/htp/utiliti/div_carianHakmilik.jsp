

#if($!div_carianHakmilik_open == "Y")
#set($count_carianHakmilik = 0)
#foreach ($list in $listHakmilik)
#set($count_carianHakmilik = $count_carianHakmilik + 1)
#end

<input type="hidden" id="div_carianHakmilik_open" name="div_carianHakmilik_open" value="Y">
<input type="hidden" id="val_count_carianHakmilik" name="val_count_carianHakmilik" value="$!count_carianHakmilik" />

#if($!count_carianHakmilik > 0) 
						<font color="blue"><li>
                            
                            
                                                    
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><blink>$!count_carianHakmilik</blink></font></b>
                             </label>&nbsp;
                                                      
                            <a href="javascript:doGetListHakmilik()" class="help" title="Senarai Hakmilik"><font color="blue">Hakmilik</font></a>
                            </li></font>						
				 #end 
                  		<div  id="div_listHakmilik"  style="width:40"></div>

#end


#parse("app/htp/utiliti/script.jsp")