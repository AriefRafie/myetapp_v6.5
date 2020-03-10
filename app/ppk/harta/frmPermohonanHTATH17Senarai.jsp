		
	<!-- 	#set($xjumpa_lain = "")
	   	#set($xjumpa_kelompok = "")
	   	#set($xjumpa_beli = "") -->
	   	
		#if($listHTAX.size()!=0 )    
                             	<fieldset>
                             	<legend>PERJANJIAN JUAL BELI</legend>
                              	<table width="100%">
       		#foreach($listam in $listHTAX)                                     
            	#if($listam.kategori_hta == 1)
              		#set($xjumpa_beli = "no")
             	#end                                     
          	#end
            #if($xjumpa_beli =="no")
    								<tr class="table_header">
								   		<td width="3%"><div align="center">NO</div></td>
								       	<td width="15%"><div align="left">NEGERI</div></td>
								       	<td width="15%"><div align="left">DAERAH</div></td>
								     	<td width="15%"><div align="left">MUKIM</div></td>
								      	<td width="15%"><div align="left">ALAMAT HARTA</div></td>
										<td width="5%"><div align="center">BAHAGIAN SIMATI</div></td>
								   		<td width="34%"><div align="left">DOKUMEN</div></td>
								   	</tr>                                 	
         	#end
         	
  			#set($plko=0)
	       	#foreach($listam in $listHTAX)							         		
				#if($listam.kategori_hta == 1)
					#set($plko=$plko+1)
													
					#if ( ($plko % 2) != 1 )
						#set( $row = "row2" )
					#else
						#set( $row = "row1" )
					#end                                     
  									<tr bgcolor="white" class="$row">
  										<td><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$plko</div></td>
									   	<td><div align="left" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"><a href="javascript:get_htaam('$id_PermohonanSimati','$!listam.idhta','$!listam.idDokumen')" class="style42">$!listam.namaNegeri</a></div></td>
									 	<td><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$!listam.namaDaerah</div></td>
									 	<td><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$!listam.namaMukim</div></td>
    									<td>    
    				#set($al_harta = "") 
    				#set($al_harta = "$listam.alamat1  $listam.alamat2  $listam.alamat3 $listam.poskod")
    										<div align="left" style="text-transform:uppercase;" onblur="uppercase()">$al_harta</div>    
    									</td>
					#if($listam.basimati!="" && $listam.bbsimati!="")
										<td><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listam.basimati / $listam.bbsimati</div></td>
					#else
										<td></td>
					#end     														
										<td>
									#if($id_Status != "169" 
				                    	&& $id_Status != "21" 
				       					&& $id_Status != "64" 
				                        && $id_Status != "163" 
				                      	&& $id_Status != "164" 
				                      	&& $id_Status != "165")	 										  
										                   		<a href = "javascript:lampiranHarta('$listam.idhta','$!paramOnline');">
																	<img border="0" src="../img/plus.gif" width="20" height="15"/>
																</a><br>
<br>
									#end		
											$listam.lampirans																
										</td>
    								</tr>
  					#set($xjumpa_beli = "no")
                                    
              	#end
                                      		
         	#end
           	#if($xjumpa_beli == "" )                                
                                  	<tr bgcolor="white">  
                                		<!-- <td></td> -->	  
                                		<td colspan="7" ><div align="left"  onblur="uppercase()">Tiada Rekod</div></td>	  
   									</tr>                                     
         	#end 
                        		</table>
                           		</fieldset>                             	
 
                              	<fieldset>
                             	<legend> PEGANGAN DIBAWAH AKTA TANAH</legend>
                             	<table width="100%">
                                  
          	#foreach($listam in $listHTAX)                                     
            	#if($listam.kategori_hta == 2 && $listam.kategori_hta != 1 && $listam.kategori_hta != 3)
               		#set($xjumpa_kelompok = "no")
               	#end                                     
           	#end
                                    
          	#if($xjumpa_kelompok == "no")                             
	                              	<tr class="table_header">
										<td width="3%"><div align="center">NO</div></td>
									   	<td width="15%"><div align="left">NEGERI</div></td>
									   	<td width="15%"><div align="left">DAERAH</div></td>
									  	<td width="15%"><div align="left">MUKIM</div></td>
									   	<td width="15%"><div align="left">NO. ROH</div></td>
									   	<td width="5%"><div align="center">BAHAGIAN SIMATI</div></td>
									 	<td width="32%"><div align="left">DOKUMEN</div></td>                             	
									</tr>
        	#end
                                      
           	#set($plko=0)
          	#foreach($listam in $listHTAX)
				#if($listam.kategori_hta == 2 && $listam.kategori_hta != 1 && $listam.kategori_hta != 3)
					#set($plko=$plko+1)
					#if ( ($plko % 2) != 1 )
						#set( $row = "row2" )
					#else
						#set( $row = "row1" )
					#end                                      
                                      
									<tr bgcolor="white" class="$row">
									<!-- <td><input type="checkbox" name="selectHTATH" id="selectHTATH" value="$listam.idhta" /></td> -->
										<td><div align="center" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()">$plko</div></td>
										<td><div align="left" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"><a href="javascript:get_htaam('$!id_PermohonanSimati','$!listam.idhta','$!listam.idDokumen')" class="style42">$!listam.namaNegeri</a></div></td>
										<td><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$!listam.namaDaerah</div></td>
										<td><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$!listam.namaMukim</div></td>
										<td><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$listam.noroh</div></td>
					#if($listam.basimati!="" && $listam.bbsimati!="")
										<td><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listam.basimati / $listam.bbsimati</div></td>
					#else
										<td></td>
					#end 
										<td>
									#if($id_Status != "169" 
				                    	&& $id_Status != "21" 
				       					&& $id_Status != "64" 
				                        && $id_Status != "163" 
				                      	&& $id_Status != "164" 
				                      	&& $id_Status != "165")	 										  
											<a href = "javascript:lampiranHarta('$listam.idhta','$!paramOnline');">
												<img border="0" src="../img/plus.gif" width="20" height="15"/>
											</a><br>
									#end		
											$listam.lampirans
										</td>									                 	
									</tr>                        	
					#set($xjumpa_kelompok = "no") 
                                     
           		#end
                                      
        	#end
            #if($xjumpa_kelompok == "")
	                               	<tr bgcolor="white">  
	                                   	<!-- <td ></td> -->	  
	                               		<td colspan="7" ><div align="left"  onblur="uppercase()">Tiada Rekod</div></td>	  
	   								</tr>
           	#end 
                          		</table>
                          		</fieldset>
                          		
	                         	<fieldset>
	                         	<legend>KEPENTINGAN LAIN-LAIN</legend>
	                        	<table width="100%">	                             
        	#foreach($listam in $listHTAX)                                     
           		#if($listam.kategori_hta == 3 && $listam.kategori_hta != 2 && $listam.kategori_hta != 1  && $listam.negeri != "" )
               		#set($xjumpa_lain = "no")
               	#end                                     
          	#end
                                    
          	#if($xjumpa_lain == "no")
	                           		<tr class="table_header">
	    								<td width="3%"><div align="center">NO</div></td>
									   	<td width="15%"><div align="left">NEGERI</div></td>
									   	<td width="15%"><div align="left">DAERAH</div></td>
										<td width="15%"><div align="left">MUKIM</div></td>
										<td width="15%"><div align="left">JENIS KEPENTINGAN</div></td>
									  	<td width="5%"><div align="center">BAHAGIAN SIMATI</div></td>
										<td width="32%"><div align="left">DOKUMEN</div></td>
	                            	</tr>
         	#end                                      
 			
 			#set($plko=0)
			#foreach($listam in $listHTAX)
				#if($listam.kategori_hta == 3 && $listam.kategori_hta != 2 && $listam.kategori_hta != 1  && $listam.negeri != "" )
					#set($plko=$plko+1)						
					#if ( ($plko % 2) != 1 )
						#set( $row = "row2" )
					#else
						#set( $row = "row1" )
					#end                                        
									<tr bgcolor="white" class="$row">
		    						<!-- <td><input type="checkbox" name="selectHTATH" id="selectHTATH" value="$listam.idhta" /></td> -->
										<td><div align="center" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase()">$plko</div></td>
										<td><div align="left" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()"><a href="javascript:get_htaam('$!id_PermohonanSimati','$!listam.idhta','$!listam.idDokumen')" class="style42">$!listam.namaNegeri</a></div></td>
										<td><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$!listam.namaDaerah</div></td>
										<td><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$!listam.namaMukim</div></td>
										<td><div align="left" style="text-transform:uppercase;" onblur="uppercase()">$listam.jenis_penting</div></td>
					#if($listam.basimati!="" && $listam.bbsimati!="")
										<td><div align="center" style="text-transform:uppercase;" onblur="uppercase()">$listam.basimati / $listam.bbsimati</div></td>
					#else
										<td></td>
					#end 
										<td>  
									#if($id_Status != "169" 
				                    	&& $id_Status != "21" 
				       					&& $id_Status != "64" 
				                        && $id_Status != "163" 
				                      	&& $id_Status != "164" 
				                      	&& $id_Status != "165")	 										
											<a href = "javascript:lampiranHarta('$listam.idhta','$!paramOnline');">
												<img border="0" src="../img/plus.gif" width="20" height="15"/>
											</a><br>
									#end		
											$listam.lampirans
									   </td>
									</tr>                        	
					
					#set($xjumpa_lain = "no")
                                     
             	#end
                                     
         	#end
          	#if($xjumpa_lain == "")                                
				           		<tr bgcolor="white">  
                                   	<!-- <td ></td> -->	  
				               		<td colspan="7" ><div align="left"  onblur="uppercase()">Tiada Rekod</div></td>	  
				   				</tr>                                     
          	#end    
							</table>
                         	</fieldset>
       #else
								<table width="100%">
									<tr class="table_header">
								   		<td width="5%"><div align="center">NO</div></td>
								       	<td width="20%"><div align="center">NEGERI</div></td>
								     	<td width="20%"><div align="center">DAERAH</div></td>
								       	<td width="20%"><div align="center">MUKIM</div></td>
								       	<td width="5%"><div align="center">BAHAGIAN SIMATI</div></td>
								   	</tr>
									<tr bgcolor="white">
								    	<td align="left" colspan="5">Tiada Rekod</td>
									</tr>
								</table> 
								<input type="hidden" name="idhtaam" value="$listamid.idhta" />
                             	
         #end
  