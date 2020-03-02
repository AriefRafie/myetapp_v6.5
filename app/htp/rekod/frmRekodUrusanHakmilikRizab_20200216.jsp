<<<<<<< HEAD
<style type="text/css">
<!--
.blink_ {
font-size: 15px;
color: red;
display: inline;
}
-->
</style>


<!-- <fieldset>
<legend>MAKLUMAT HAKMILIK</legend> -->

<!--	<fieldset>	<legend>Maklumat Penswastaan</legend>
		<table width="100%">
       		<tr>
            	<td width="50%" valign="top">
                	<table width="100%" border="0">
                    	<tr>
  							<td width="1%" valign="top">
				        	</td>				        
                        	<td width="30%" valign="top">
                            	<div align="left">
                            		<span class="labelinput">Kementerian</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" valign="top">:</td>
                   			<td width="68%" class="pautanms" >
 								$txtNamaKementerian
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
 								$txtNoFailSeksyen
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
 								$txtFailPTG
  							</td>
                		</tr>
                    	<tr>
  							<td width="1%" valign="top" >
				        	</td>				        
                        	<td width="30%" valign="top" >
                            	<div align="left">
                            		<span class="labelinput">Tajuk</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" valign="top" >:</td>
                   			<td width="68%" class="pautanms" >
 								$txtTajuk
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
 								$txtNamaAgensi
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
 								$txtFailKJP
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
 								$!txtFailPTD
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
 								$caraPerolehan
  							</td>
                		</tr>
                	</table>
                </td>
       		</tr>                    
		</table>
	</fieldset>	-->
	<!--<br>-->
		<table width="100%">
       		<tr>
            	<td>
	<fieldset>	
		<legend>MAKLUMAT PENYEWAAN</legend>
		
		<!-- NOT USE. REPLACE WITH DIV -->
		<!-- 
		<table width="100%">
       		<tr>
            	<td width="50%" valign="top">
                	<table width="100%" border="0">
                    	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">No. Fail</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
 								$!dataPHPPenyewaan.NO_FAIL
  							</td>
                		</tr>
                     	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Nama Penyewa</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
 							
  							</td>
                		</tr>
                    	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">&nbsp;</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >&nbsp;</td>
                   			<td width="68%" class="pautanms1" >
 								&nbsp;
  							</td>
                		</tr>

                 	</table>
           		</td>
                        	
                <td valign="top">
               		<table width="100%">
                   	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">No. Siri Perjanjian</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
                   				$!sewa.getHtpPerjanjian().getNoRujukanPerjanjian()
  							</td>
                		</tr>
              			
            			<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Tarikh Mula</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
                    			#if ($!sewa.getTarikhMulaString() != '01/01/1900')
                   				$!sewa.getTarikhMulaString()
                   				#end
  							</td>
                		</tr>

                    	<tr>
  							<td width="1%"  >
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Tarikh Tamat</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
                   				#if ($!sewa.getTarikhTamatString() != '01/01/1900')
                   				$!sewa.getTarikhTamatString()
                   				#end
      						</td>
                		</tr>
                    	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Tempoh</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
 								$!sewa.getTempoh() Bulan
  							</td>
                		</tr>
                    	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Kadar Sewa (RM)</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
                   				$!sewa.getKadar()
  							</td>
                		</tr> 
                		
                	</table>
                </td>
       		</tr>                    
		</table>
		 -->
		
		<!-- Ajax: Maklumat Detail -->
		<div id="div_maklumatPenyewaan"></div>
		<br/>
		
		<!-- LIST PENYEWAAN -->
		<table width="100%">
			<tr class="table_header">
				<td scope="row" width="5%" align="center">Bil.</td> 
				<td width="20%">No. Fail Penyewaan</td>
				<td width="20%">Penyewa</td>
				<td width="35%">Tujuan</td>
				<td width="10%">Tarikh Mula</td>
				<td width="10%">Tarikh Tamat</td>
			</tr>
			
			 #if($listPHPPenyewaan.size()!=0)
		           	#foreach($list in $listPHPPenyewaan)
		                #set( $i = $velocityCount )
		         		#if ( ($i % 2) != 1 )
		              		 #set( $row = "row2" )
		         		#else
		               		 #set( $row = "row1" )
		         		#end
		         	
		        	<tr>
		        		<td class="$row" align="center">$list.BIL</td>
		        		<td class="$row">
		        			<a href="javascript:viewDetailSewa('$!list.ID_PERMOHONAN')"><font color="blue">$!list.NO_FAIL</font></a>
		        			<input type="hidden" name="id_permohonan" value="$!list.ID_PERMOHONAN">
		        		</td>
		            	<td class="$row">$list.NAMA_PEMOHON</td>
		            	<td class="$row">$list.TUJUAN</td>
		            	<td class="$row">$list.TARIKH_MULA_PERJANJIAN</td>
		            	<td class="$row">$list.TARIKH_TAMAT_PERJANJIAN</td>
		        	</tr>
		        	#end
		     #else
		        	<tr>
		        		<td colspan="6">Tiada rekod</td>
		        	</tr>
		     #end
	</table>
		
	</fieldset>            		

	            </td>	
       		</tr>   
       		<tr>
				<td>
						<fieldset>	<legend>MAKLUMAT PAJAKAN</legend>
		<table width="100%">
       		<tr>
            	<td width="50%" valign="top">
                	<table width="100%" border="0">
                    	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">No. Fail</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
 								$!pajakan.getPermohonan().getPfdFail().getNoFail()
  							</td>
                		</tr>
                     	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Nama Pemajak</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
 								$!pajakan.getPemohon().getNama()
  							</td>
                		</tr>
                    	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">&nbsp;</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >&nbsp;</td>
                   			<td width="68%" class="pautanms1" >
 								&nbsp;
  							</td>
                		</tr>

                 	</table>
           		</td>
                        	
                <td valign="top">
               		<table width="100%">
                <!--   	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">No. Siri Perjanjian</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
 								$selectJenisHakmilikHR
  							</td>
                		</tr> -->
              			
            			<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Tarikh Mula</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
                    			#if ($!pajakan.getTarikhMulaPajakanString() != '01/01/1900')
                   				$!pajakan.getTarikhMulaPajakanString()
                   				#end
  							</td>
                		</tr>

                    	<tr>
  							<td width="1%"  >
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Tarikh Tamat</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
                    			#if ($!pajakan.getTarikhTamatPajakanString() != '01/01/1900')
                   				$!pajakan.getTarikhTamatPajakanString()
                   				#end
      						</td>
                		</tr>
                    	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Tempoh</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
 								$!pajakan.getTempohPajakan()
  							</td>
                		</tr>
                    	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Kadar Pajakan (RM)</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
                   				$!UTIL.format2Decimal($!pajakan.getKadarPajakan())
  							</td>
                		</tr> 
                		
                	</table>
                </td>
       		</tr>                    
		</table>
	</fieldset>
	            </td>	
       		</tr>					
			<tr>		
				<td>	
	            	<fieldset>	<legend>MAKLUMAT PENSWASTAAN</legend>
						<table width="100%">
				         	<tr class="table_header">
				            	<td scope="row" width="5%" align="center">Bil.</td> 
				              	<td width="20%">No. Fail</td>
				              	<td width="75%">Tindakan Lanjut</td>
				            </tr>
						#set ($list = "")
			          	#if ($!swasta.size() > 0)
				          #foreach ($list in $swasta)
				            #if ($list.bil == '')
				                #set( $row = "row1" )
				            #elseif (($list.bil % 2) != 0)
				                #set( $row = "row1" )
				            #else 
				                #set( $row = "row2" )
				            #end
					          <tr>
					            <td class="$row" align="center"><a href="javascript:paparHakmilik('$!list.idHakmilikUrusan')" >$list.bil.</a></td>
					            <td class="$row">$list.noFail</td>
					            <td class="$row">
					            	$!list.tindakan
					            	#if($list.tindakan == 'PAJAK SEMUA')
					          			<a href="javascript:daftarPajakanSemua('$list.idHakmilikUrusan')" class="style1">.</a>
					          		#end
					            </td>
					          </tr>
			          		#end
				    	
				    	#else
					          <tr>
					            <td class="row1" align="center">&nbsp;</td>
					            <td class="row1" colspan="2">Tiada Rekod</td>
					          </tr>
			          	#end
						</table>
					</fieldset>
	            </td>	
       		</tr>					

   	 
<!-- </fieldset> -->



=======
<style type="text/css">
<!--
.blink_ {
font-size: 15px;
color: red;
display: inline;
}
-->
</style>


<!-- <fieldset>
<legend>MAKLUMAT HAKMILIK</legend> -->

<!--	<fieldset>	<legend>Maklumat Penswastaan</legend>
		<table width="100%">
       		<tr>
            	<td width="50%" valign="top">
                	<table width="100%" border="0">
                    	<tr>
  							<td width="1%" valign="top">
				        	</td>				        
                        	<td width="30%" valign="top">
                            	<div align="left">
                            		<span class="labelinput">Kementerian</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" valign="top">:</td>
                   			<td width="68%" class="pautanms" >
 								$txtNamaKementerian
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
 								$txtNoFailSeksyen
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
 								$txtFailPTG
  							</td>
                		</tr>
                    	<tr>
  							<td width="1%" valign="top" >
				        	</td>				        
                        	<td width="30%" valign="top" >
                            	<div align="left">
                            		<span class="labelinput">Tajuk</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" valign="top" >:</td>
                   			<td width="68%" class="pautanms" >
 								$txtTajuk
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
 								$txtNamaAgensi
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
 								$txtFailKJP
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
 								$!txtFailPTD
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
 								$caraPerolehan
  							</td>
                		</tr>
                	</table>
                </td>
       		</tr>                    
		</table>
	</fieldset>	-->
	<!--<br>-->
		<table width="100%">
       		<tr>
            	<td>
	<fieldset>	
		<legend>MAKLUMAT PENYEWAAN</legend>
		
		<!-- NOT USE. REPLACE WITH DIV -->
		<!-- 
		<table width="100%">
       		<tr>
            	<td width="50%" valign="top">
                	<table width="100%" border="0">
                    	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">No. Fail</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
 								$!dataPHPPenyewaan.NO_FAIL
  							</td>
                		</tr>
                     	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Nama Penyewa</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
 							
  							</td>
                		</tr>
                    	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">&nbsp;</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >&nbsp;</td>
                   			<td width="68%" class="pautanms1" >
 								&nbsp;
  							</td>
                		</tr>

                 	</table>
           		</td>
                        	
                <td valign="top">
               		<table width="100%">
                   	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">No. Siri Perjanjian</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
                   				$!sewa.getHtpPerjanjian().getNoRujukanPerjanjian()
  							</td>
                		</tr>
              			
            			<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Tarikh Mula</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
                    			#if ($!sewa.getTarikhMulaString() != '01/01/1900')
                   				$!sewa.getTarikhMulaString()
                   				#end
  							</td>
                		</tr>

                    	<tr>
  							<td width="1%"  >
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Tarikh Tamat</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
                   				#if ($!sewa.getTarikhTamatString() != '01/01/1900')
                   				$!sewa.getTarikhTamatString()
                   				#end
      						</td>
                		</tr>
                    	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Tempoh</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
 								$!sewa.getTempoh() Bulan
  							</td>
                		</tr>
                    	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Kadar Sewa (RM)</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
                   				$!sewa.getKadar()
  							</td>
                		</tr> 
                		
                	</table>
                </td>
       		</tr>                    
		</table>
		 -->
		
		<!-- Ajax: Maklumat Detail -->
		<div id="div_maklumatPenyewaan"></div>
		<br/>
		
		<!-- LIST PENYEWAAN -->
		<table width="100%">
			<tr class="table_header">
				<td scope="row" width="5%" align="center">Bil.</td> 
				<td width="20%">No. Fail Penyewaan</td>
				<td width="20%">Penyewa</td>
				<td width="35%">Tujuan</td>
				<td width="10%">Tarikh Mula</td>
				<td width="10%">Tarikh Tamat</td>
			</tr>
			
			 #if($listPHPPenyewaan.size()!=0)
		           	#foreach($list in $listPHPPenyewaan)
		                #set( $i = $velocityCount )
		         		#if ( ($i % 2) != 1 )
		              		 #set( $row = "row2" )
		         		#else
		               		 #set( $row = "row1" )
		         		#end
		         	
		        	<tr>
		        		<td class="$row" align="center">$list.BIL</td>
		        		<td class="$row">
		        			<a href="javascript:viewDetailSewa('$!list.ID_PERMOHONAN')"><font color="blue">$!list.NO_FAIL</font></a>
		        			<input type="hidden" name="id_permohonan" value="$!list.ID_PERMOHONAN">
		        		</td>
		            	<td class="$row">$list.NAMA_PEMOHON</td>
		            	<td class="$row">$list.TUJUAN</td>
		            	<td class="$row">$list.TARIKH_MULA_PERJANJIAN</td>
		            	<td class="$row">$list.TARIKH_TAMAT_PERJANJIAN</td>
		        	</tr>
		        	#end
		     #else
		        	<tr>
		        		<td colspan="6">Tiada rekod</td>
		        	</tr>
		     #end
	</table>
		
	</fieldset>            		

	            </td>	
       		</tr>   
       		<tr>
				<td>
						<fieldset>	<legend>MAKLUMAT PAJAKAN</legend>
		<table width="100%">
       		<tr>
            	<td width="50%" valign="top">
                	<table width="100%" border="0">
                    	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">No. Fail</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
 								$!pajakan.getPermohonan().getPfdFail().getNoFail()
  							</td>
                		</tr>
                     	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Nama Pemajak</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
 								$!pajakan.getPemohon().getNama()
  							</td>
                		</tr>
                    	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">&nbsp;</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >&nbsp;</td>
                   			<td width="68%" class="pautanms1" >
 								&nbsp;
  							</td>
                		</tr>

                 	</table>
           		</td>
                        	
                <td valign="top">
               		<table width="100%">
                <!--   	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">No. Siri Perjanjian</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
 								$selectJenisHakmilikHR
  							</td>
                		</tr> -->
              			
            			<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Tarikh Mula</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
                    			#if ($!pajakan.getTarikhMulaPajakanString() != '01/01/1900')
                   				$!pajakan.getTarikhMulaPajakanString()
                   				#end
  							</td>
                		</tr>

                    	<tr>
  							<td width="1%"  >
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Tarikh Tamat</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
                    			#if ($!pajakan.getTarikhTamatPajakanString() != '01/01/1900')
                   				$!pajakan.getTarikhTamatPajakanString()
                   				#end
      						</td>
                		</tr>
                    	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Tempoh</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
 								$!pajakan.getTempohPajakan()
  							</td>
                		</tr>
                    	<tr>
  							<td width="1%"  >
  								<span class="labelmandatory">#if ($mode == 'update') * #end </span>
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Kadar Pajakan (RM)</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms1" >
                   				$!UTIL.format2Decimal($!pajakan.getKadarPajakan())
  							</td>
                		</tr> 
                		
                	</table>
                </td>
       		</tr>                    
		</table>
	</fieldset>
	            </td>	
       		</tr>					
			<tr>		
				<td>	
	            	<fieldset>	<legend>MAKLUMAT PENSWASTAAN</legend>
						<table width="100%">
				         	<tr class="table_header">
				            	<td scope="row" width="5%" align="center">Bil.</td> 
				              	<td width="20%">No. Fail</td>
				              	<td width="75%">Tindakan Lanjut</td>
				            </tr>
						#set ($list = "")
			          	#if ($!swasta.size() > 0)
				          #foreach ($list in $swasta)
				            #if ($list.bil == '')
				                #set( $row = "row1" )
				            #elseif (($list.bil % 2) != 0)
				                #set( $row = "row1" )
				            #else 
				                #set( $row = "row2" )
				            #end
					          <tr>
					            <td class="$row" align="center"><a href="javascript:paparHakmilik('$!list.idHakmilikUrusan')" >$list.bil.</a></td>
					            <td class="$row">$list.noFail</td>
					            <td class="$row">
					            	$!list.tindakan
					            	#if($list.tindakan == 'PAJAK SEMUA')
					          			<a href="javascript:daftarPajakanSemua('$list.idHakmilikUrusan')" class="style1">.</a>
					          		#end
					            </td>
					          </tr>
			          		#end
				    	
				    	#else
					          <tr>
					            <td class="row1" align="center">&nbsp;</td>
					            <td class="row1" colspan="2">Tiada Rekod</td>
					          </tr>
			          	#end
						</table>
					</fieldset>
	            </td>	
       		</tr>					

   	 
<!-- </fieldset> -->



>>>>>>> 4e4516aef7b87a63eadc341f0289ec0170adc5a1
