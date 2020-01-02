
#if($!div_carianFail_open == "Y")
	#set($count_carianFail = 0)
	#foreach ($list in $listFail)
	#set($count_carianFail = $count_carianFail + 1)
#end

<input type="hidden" id="div_carianFail_open" name="div_carianFail_open" value="Y">
<input type="hidden" id="val_count_carianFail" name="val_count_carianFail" value="$!count_carianFail" />

   				  #if($!count_carianFail > 0) 
						<font color="blue">
                         
                                <li>                    
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE">$!count_carianFail</font></b>
                             </label>&nbsp;
                                                      
                            <a href="javascript:doGetListFail()" class="help" title="Senarai Fail"><font color="blue">Akta</font></a>
                            </li>
                             
                            </font>						
				 #end 
                  		<div  id="div_listFail"  style="width:40"></div>

#end

#parse("app/pdt/utiliti/script.jsp")