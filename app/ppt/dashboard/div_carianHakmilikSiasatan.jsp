



#if($!div_carianHakmilikSiasatan_open == "Y")
#set($count_carianHakmilikSiasatan = 0)
#foreach ($list in $listHakmilikSiasatan)
#set($count_carianHakmilikSiasatan = $count_carianHakmilikSiasatan + 1)
#end

<input type="hidden" id="div_carianHakmilikSiasatan_open" name="div_carianHakmilikSiasatan_open" value="Y">
<input type="hidden" id="val_count_carianHakmilikSiasatan" name="val_count_carianHakmilikSiasatan" value="$!count_carianHakmilikSiasatan" />

#if($!count_carianHakmilikSiasatan > 0) 
						<font color="blue"><li>
                            
                            
                                                    
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE"><span class="blink">$!count_carianHakmilikSiasatan</span></font></b>
                             </label>&nbsp;
                                                      
                            <a href="javascript:getListHakmilikSiasatan()" class="help" title="Senarai Hakmilik Siasatan"><font color="blue">Hakmilik</font></a>
                            </li></font>						
				 #end 
                  		<div  id="div_listHakmilikSiasatan"  style="width:40"></div>

#end

 #parse("app/ppt/dashboard/script.jsp")