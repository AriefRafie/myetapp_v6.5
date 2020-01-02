#if($!div_carianAktaPindaan_open == "Y")
	#set($count_carianAktaPindaan = 0)
	#foreach ($list in $listAktaPindaan)
	#set($count_carianAktaPindaan = $count_carianAktaPindaan + 1)
#end

<input type="hidden" id="div_carianAktaPindaan_open" name="div_carianAktaPindaan_open" value="Y">
<input type="hidden" id="val_count_carianAktaPindaan" name="val_count_carianAktaPindaan" value="$!count_carianAktaPindaan" />

   				  #if($!count_carianAktaPindaan > 0) 
						<font color="blue">
                         
                                <li>                    
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE">$!count_carianAktaPindaan</font></b>
                             </label>&nbsp;
                                                      
                            <a href="javascript:doGetListAktaPindaan()" class="help" title="Senarai Fail Akta Pindaan"><font color="blue">Akta Pindaan</font></a>
                            </li>
                             
                            </font>						
				 #end 
                  		<div  id="div_listAktaPindaan"  style="width:40"></div>

#end

#parse("app/pdt/utiliti/script.jsp")