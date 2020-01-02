
#set($TAJUKWARTAASAL="")
#if($view.FLAG_JENISWARTA=="B")
	#set($TAJUKWARTAASAL="PEMBATALAN KEATAS WARTA ASAL")
#else
	#set($TAJUKWARTAASAL="PENGGANTIAN KEATAS WARTA ASAL")
#end
 
 


<table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="28%"></td>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="70%">
			</td>
			</tr>
            <tr><td></td><td colspan="3" style="border-bottom:1px solid black"><strong>$TAJUKWARTAASAL</strong></td></tr>
            <tr  >
				<td valign="top" >
                </td>			
				<td valign="top" >
				Jenis Warta
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
               $viewWartaAsal.JENISWARTA
				</td>
			</tr>
           
            
           
            <tr  >
				<td valign="top" >
                </td>			
				<td valign="top" >
				Negeri
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                $viewWartaAsal.NEGERI                
				</td>
			</tr>
         
            
            
            
          
            <tr >
				<td valign="top" >
                </td>			
				<td valign="top" >
				Daerah
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >                
                 $viewWartaAsal.DAERAH
				</td>
			</tr>
            
            
            <tr  >
				<td valign="top" >
                </td>			
				<td valign="top" >
				Mukim
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top"  >
                $viewWartaAsal.MUKIM
                </td>
			</tr>
            
            
            <tr>
				<td valign="top" >
               	</td>			
				<td valign="top" >
				No. Warta
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                $viewWartaAsal.NO_WARTA                			
			  </td>
			</tr>
            
            <tr>
				<td valign="top" >
               	
				</td>			
				<td valign="top" >
				Muat Naik Warta
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #if($viewWartaAsal.NAMA_FAIL_WARTA!="")
                <span class="font_tajuk_sub"  style="cursor:pointer" >
                <u onClick="paparDoc($viewWartaAsal.ID_WARTATRM,'WARTA');">$viewWartaAsal.NAMA_FAIL_WARTA</u>
                </span>
                #else
                -
                #end
        		
                
				</td>
			</tr>
            
            
            <tr>
				<td valign="top" >
               	</td>			
				<td valign="top" >
				Tarikh Warta
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #if($viewWartaAsal.TARIKH_WARTA != "") $viewWartaAsal.TARIKH_WARTA #else - #end
                						
			  </td>
			</tr>
            
            
            <tr >
				<td valign="top" >
               		
				</td>			
				<td valign="top" >
				Kawasan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #if($viewWartaAsal.KAWASAN != "") 
                    $viewWartaAsal.KAWASAN
                #else 
                - 
                #end                 			
			  </td>
			</tr>
          
            
            
            <tr>
				<td valign="top" >
                </td>			
				<td valign="top" >
				No. Pelan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #if($viewWartaAsal.NO_PELAN != "") 
                    $viewWartaAsal.NO_PELAN
                    #else 
                    - 
                #end    
								
			  </td>
			</tr>
            
            
            
            
            <tr>
				<td valign="top" >
               	
				</td>			
				<td valign="top" >
				Muat Naik Pelan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #if($viewWartaAsal.NAMA_FAIL_PELAN!="")
                <span class="font_tajuk_sub"  style="cursor:pointer" >
                <u onClick="paparDoc($viewWartaAsal.ID_WARTATRM,'PELAN');">$viewWartaAsal.NAMA_FAIL_PELAN</u>
                </span>
                #else
                -
                #end
        		
               
				</td>
			</tr>
          
            <tr >
				<td valign="top" >
                </td>			
				<td valign="top" >
				Luas Asal (Hektar)
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >                
                    $viewWartaAsal.LUAS_ASAL_DISPLAY               			
			  </td>
			</tr>
            
            
            #if($viewWartaAsal.LUAS_BATAL > 0) 
             <tr >
				<td valign="top" >
                </td>			
				<td valign="top" >
				Luas Pembatalan (Hektar)
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >                
                    $viewWartaAsal.LUAS_BATAL_DISPLAY                			
			  </td>
			</tr>
            #end
            
           #if($viewWartaAsal.LUAS_GANTI > 0)
           <tr >
				<td valign="top" >
                </td>			
				<td valign="top" >
				Luas Penggantian (Hektar)
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >                
                    $viewWartaAsal.LUAS_GANTI_DISPLAY               			
			  </td>
			</tr>
            #end
            
            
            #if($viewWartaAsal.KOSONG > 0)   
             <tr >
				<td valign="top" >
                </td>			
				<td valign="top" >
				Luas Tidak Diganti (Hektar)
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >  
                	           
                    <font color="red"><span class="blink"><b>$viewWartaAsal.KOSONG_DISPLAY</b></span></font>
                                 			
			  </td>
			</tr>
         	#end
            
            <tr >
				<td valign="top" >
                </td>			
				<td valign="top" >
				Luas (Hektar)
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #if($viewWartaAsal.LUAS != "") 
                    #if($viewWartaAsal.LUAS < 0)
                            <font color="red"><b>$viewWartaAsal.LUAS_DISPLAY</b></font>
                    #else
                        $viewWartaAsal.LUAS_DISPLAY
                    #end
                #else 
                - 
                #end 				
			  </td>
			</tr>
            
            <tr  >
				<td valign="top" >
                </td>			
				<td valign="top" >
				Status
                </td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #if($viewWartaAsal.FLAG_STATUSWARTA != "")                      
                     	$viewWartaAsal.STATUSWARTA
                #else 
                - 
                #end
                </td>
			</tr>            
</table>

    