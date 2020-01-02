<!--
 <a href="javascript:gotoSenaraiKiv()" class="help" title="Fail-fail permohonan yang telah Selesai Perbicaraannya tetapi masih lagi KIV diatas sebab-sebab tertentu.">
 
							<font color="blue">
                            <li>
                            
                             #if($getListKiv>0)
                             <label style="background-color:blue"  align="center" valign="top" > 
                            <b><font color="WHITE" class="blink"><blink>$!getListKiv</blink></font></b>
                             </label>&nbsp;    
                              #end                                                    
                             Fail yang masih KIV selepas Selesai Perbicaraan</li></font>						
				  </a>
                  
                  -->
 

 
  
  
  
   
   
    
              <a href="javascript:gotoSenaraiKiv()" class="help" title="Fail-fail permohonan yang telah Selesai Perbicaraannya tetapi masih lagi KIV diatas sebab-sebab tertentu.">
							
                            <table cellpadding="1" cellspacing="0"  width="100%" style="vertical-align:top"   >
                            <tr><td valign="top"  width="1%" style="white-space:nowrap;" >
                             <font color="blue">
                             <li >
                             #if($!getListKiv > 0)                            
                             <label style="background-color:blue" style="width:5%"  > 
                             <b><font color="WHITE" class="blink"><blink>$!getListKiv</blink>&nbsp;</font></b>
                             </label>&nbsp;
                             #end  
                             </li>
                             </font>	
                             </td>
                             <td valign="top">  
                            <font color="blue">                   
                          Fail yang masih KIV selepas Selesai Perbicaraan
                            </font>
                             </td>
                             </tr>
                             </table>					

</a>