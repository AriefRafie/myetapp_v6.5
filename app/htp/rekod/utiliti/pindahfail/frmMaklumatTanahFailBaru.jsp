	<fieldset><legend>CARIAN</legend>
		<table width="100%">
      		<tr>
            	<td width="50%" valign="top">
					<table width="100%" border="0">
                    	<tr>
  							<td width="1%" valign="top" >
				        	</td>				        
                        	<td width="30%" valign="top">
                            	<div align="left">
                            		<span class="labelinput">No. Fail</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" valign="top">:</td>
                   			<td width="68%" class="pautanms" valign="top">
								<input name="txtNoFail" type="text" value="$!txtNoFail" size="45" maxlength="50" style="text-transform:uppercase;" >  							
						        <input type="button" class="stylobutton100" name="btncarianfailbaru" value="Cari" onclick="carianFailBaru()"/>
						        <input type="reset" class="stylobutton100" name="cmdkosongkancarianfailbaru" value="Kosongkan" />
							</td>
                		</tr>
                	</table>
                </td>
            	<td width="50%" valign="top">
                </td>
			</tr>
		</table>
	</fieldset>

#if( $!paparsenarai == true )
	<fieldset><legend>SENARAI FAIL</legend>
		<table width="100%">
      		<tr>
            	<td colspan=2 valign="top">
            		<table width="100%">
	            		<tr class="table_header">
							<td width="3%"><strong>BIL.</strong></td>
					   	  	<td width="20%"><strong>NO. FAIL</strong></td>
					  	  	<td width="47%"><strong>TAJUK FAIL</strong></td>
					   	  	<td width="15%"><strong>NO. FAIL PTG</strong></td>
					  	  	<td width="15%"><strong>NO. FAIL PTD</strong></td>
						</tr>
					#set ($count = 0)
					#foreach ($senaraiHakmilik in $SenaraiHakmilik)
						#set ($count = $count+1)
					  #set( $i = $velocityCount )
					    #if ( ($i % 2) != 1 )
					       #set( $row = "row2" )
					    #else
					       #set( $row = "row1" )
					    #end
					   <tr class="$row">
					   	<td height="20">$count.</td> 
					      #if($senaraiHakmilik.flagTanah == '1' || $senaraiHakmilik.flagTanah == '2' || $senaraiHakmilik.flagTanah == '5' )  
					   			<td><a href="javascript:hakmilik_detail2('$senaraiHakmilik.idpermohonan');" class="pautanms">$!senaraiHakmilik.no</a></td>
					   	  #elseif($senaraiHakmilik.flagTanah == '10')
					   			<td><a href="javascript:rizab_detail2('$senaraiHakmilik.idpermohonan');" class="pautanms">$!senaraiHakmilik.no</a></td>
					 	  #else  
					 	  	<td>$!senaraiHakmilik.no</td>
					   	  #end
					   	   
					      #if($senaraiHakmilik.flagTanah == '1' || $senaraiHakmilik.flagTanah == '2' || $senaraiHakmilik.flagTanah == '5' )  
					    	<td>$!senaraiHakmilik.tajuk</td>
					   	  #elseif($senaraiHakmilik.flagTanah == '10')
					    	<td>$!senaraiHakmilik.tajuk</td>
					 	  #end  
					    <td>$!senaraiHakmilik.ptd</td>
					    <td>$!senaraiHakmilik.ptg</td>
					    <!--<td>$senaraiHakmilik.tarikhTerima</td> -->
					</tr> 
					  #end						
						
					</table>
               </td>
			</tr>
		</table>
	</fieldset>
#end
			
#if( $!paparmaklumat == true )
	<fieldset><legend>MAKLUMAT FAIL BARU</legend>
		<table width="100%">		
       		<tr>
            	<td width="50%" valign="top">
                	<table width="100%" border="0">
                    	<tr>
  							<td width="1%" valign="top" >
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Kementerian</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" valign="top">:</td>
                   			<td width="68%" class="pautanms" >
 								$!txtNamaKementerianb
  							</td>
                		</tr>
                     	<tr>
  							<td width="1%" valign="top" >
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">No. Fail Seksyen</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms" >
 								$!txtNoFailSeksyenb
 								<input type="hidden" name="txtnofailama" value="$!txtNoFailSeksyen" style="text-transform:uppercase;" >  							
 								<input type="hidden" name="txtnofailb" value="$!txtNoFailSeksyenb" size="45" maxlength="50" style="text-transform:uppercase;" >  							
 								<input type="hidden" name="idpermohonan" value="$idPermohonanb" />
 								
  							</td>
                		</tr>
                    	<tr>
  							<td width="1%" valign="top" >
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">No. Fail PTG</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms" >
 								$!txtFailPTGb
  							</td>
                		</tr>
                    	<tr>
  							<td width="1%" valign="top">
				        	</td>				        
                        	<td width="30%" valign="top">
                            	<div align="left">
                            		<span class="labelinput">Tajuk</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" valign="top">:</td>
                   			<td width="68%" class="pautanms" >
 								$!txtTajukb
  							</td>
                		</tr>
                 	</table>
           		</td>
                        	
                <td valign="top">
               		<table width="100%">
                   	<tr>
  							<td width="1%" valign="top" >
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Agensi</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms" >
 								$!txtNamaAgensib
  							</td>
                		</tr>
              			
            			<tr>
  							<td width="1%" valign="top" >
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">No. Fail KJP</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms" >
 								$!txtFailKJPb
  							</td>
                		</tr>

                    	<tr>
  							<td width="1%" valign="top" >
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">No. Fail PTD</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms" >
 								$!txtFailPTDb
  							</td>
                		</tr>
                    	<tr>
  							<td width="1%" valign="top" >
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Cara Perolehan</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms" >
 								$!caraPerolehanb
  							</td>
                		</tr> 
                	</table>
                </td>
       		</tr>  
       		                  
		</table>
	</fieldset>
#end
		