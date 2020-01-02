<tr id="div_rowdiv_row$JENISSUB$ID_WARTATRMINDUK$view.ID_WARTATRM" >
			   <td class="$view.rowCss"  align="center" valign="top" >$view.BIL
			   
			   </td>
			   <td class="$view.rowCss"  align="left" valign="top">			   
			   
               <a href="javascript:document.getElementById('div_row$JENISSUB$ID_WARTATRMINDUK').innerHTML ='';doDivAjaxCall$formname('div_row$JENISSUB$ID_WARTATRMINDUK$ID_WARTATRM','view','ID_WARTATRM=$ID_WARTATRM&BIL=$BIL&rowCss=$rowCss&mode=view&commandFrom=list&ID_WARTATRMINDUK=$ID_WARTATRMINDUK&JENISSUB=$JENISSUB');">
			   
               #set($span1 = "span1list"+$view.BIL)
			   <span id="$span1"> 
			   <font color='blue' class="font_tajuk_sub" >	<u>$view.NO_WARTA</u>  </font>   
                </span>			
			   <script>highlight_item('$CR_NO_WARTA','$span1')</script>          		   
			  
			   </a>		
			   </td>
               
               <td class="$view.rowCss"  align="center" valign="top">			   
			   #set($span9= "span9list"+$view.BIL)	   
			   <span id="$span9">$view.TARIKH_WARTA</span>
               #if($CR_TARIKH_MULA != "" || $CR_TARIKH_AKHIR!= "")
               <script>			   
			   //highlight_item(returnDropDownSelectedText_value('CR_ID_MUKIM'),'$span4');
			   highlight_item('$view.TARIKH_WARTA','$span9')
               </script>
               #end
               </td>	
               		   
			   <td class="$view.rowCss"  align="left" valign="top">			   
			   #set($span2= "span2list"+$view.BIL)	   
			   <span id="$span2">$view.NEGERI</span>
               #if($CR_ID_NEGERI != "")
               <script>			   
			   highlight_item(returnDropDownSelectedText_value('CR_ID_NEGERI'),'$span2');
               </script>
               #end
               
			   </td>
               
               <td class="$view.rowCss"  align="left" valign="top">			   
			   #set($span3= "span3list"+$view.BIL)	   
			   <span id="$span3">$view.DAERAH</span>
               #if($CR_ID_DAERAH != "")
               <script>			   
			   highlight_item(returnDropDownSelectedText_value('CR_ID_DAERAH'),'$span3');
               </script>
               #end
			   </td>
               
               <td class="$view.rowCss"  align="left" valign="top">			   
			   #set($span4= "span4list"+$view.BIL)	   
			   <span id="$span4">$view.MUKIM</span>
               #if($CR_ID_MUKIM != "")
               <script>			   
			   highlight_item(returnDropDownSelectedText_value('CR_ID_MUKIM'),'$span4');
               </script>
               #end
			   </td>
               
              
               <!-- 
               <td class="$view.rowCss"  align="left" valign="top">		
               #set($span5= "span5list"+$view.BIL)	   
			   <span id="$span5">$view.KAWASAN</span>
               #if($CR_KAWASAN != "")
               <script>	
			   highlight_item('$CR_KAWASAN','$span5')		   
			   </script>
               #end
			   </td>
               
               <td class="$view.rowCss"  align="left" valign="top">		
               #set($span6= "span6list"+$view.BIL)	   
			   <span id="$span6">$view.NO_PELAN</span>
               #if($CR_NO_PELAN != "")
               <script>	
			   highlight_item('$CR_NO_PELAN','$span6')		   
			   </script>
               #end
			   </td>
               -->
               
               
               <td class="$view.rowCss"  align="left" valign="top">		
               #if($view.LUAS < 0)
                            <font color="red"><b>$view.LUAS_DISPLAY</b></font>
                    #else
                        $view.LUAS_DISPLAY
                    #end
			   </td>
               
               <td class="$view.rowCss"  align="left" valign="top">			   
			   $view.JENISWARTA
               
               <!--
               #if($view.NO_WARTA_GANTI!="")
               <BR />[PENGGANTIAN $view.NO_WARTA_GANTI]
               #end
               
               #if($view.NO_WARTA_BATAL!="")
               <BR />[PEMBATALAN $view.NO_WARTA_BATAL]
               #end
			   </td>
               -->
               
               <td class="$view.rowCss"  align="left" valign="top">			   
			   $view.STATUSWARTA
			   </td>
               
             
              
			   
			   
			   <td class="$view.rowCss"  align="left" valign="top">
               
               #if($view.NO_WARTA_ASAL != "")
               		#if($view.FLAG_JENISWARTA == "B")
               		$view.JENISWARTA : $view.NO_WARTA_ASAL<br />
                    #else
                    PENGGANTIAN : $view.NO_WARTA_ASAL<br />
                    #end
               #end
             
               #if($view.KOSONG>0)               
               <span class="blink" ><font color="red"><b>$view.KOSONG_DISPLAY HEKTAR TIDAK DIGANTI</b></font></span><br />
               #end
               <!--
               <a href="javascript:if(confirm('$label_adakah_pasti_delete')){ doDivAjaxCall$formname('div_Senarai','delete','ID_WARTATRM=$view.ID_WARTATRM');}"><img title="$label_hapus"  src="../img/hapus.gif" border="0"></a>	
               -->   		
               <!--
               <a href="javascript:if(confirm('$label_adakah_pasti_delete')){ doDivAjaxCall$formname('div_Senarai$JENISSUB$ID_WARTATRMINDUK','delete','ID_WARTATRM=$view.ID_WARTATRM&ID_WARTATRMINDUK=$ID_WARTATRMINDUK&JENISSUB=$JENISSUB');}"><img title="$label_hapus"  src="../img/hapus.gif" border="0"></a>	
               -->   		  
			   </td>	   
		</tr>