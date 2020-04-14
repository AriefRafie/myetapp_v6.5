<style type="text/css">
<!--
.blink_ {
font-size: 15px;
color: red;
display: inline;
}
-->
</style>

		<table width="100%">
			<tr>
            	<td>
					<fieldset>	<legend>MAKLUMAT SEMASA</legend>		
		<!-- Ajax: Maklumat Detail -->
					<div id="div_maklumatsemasa"></div>
		
		<!-- SENARAI URUSAN -->
					<table width="100%">
						<tr class="table_header">
							<td scope="row" width="5%" align="center">Bil.</td> 
							<td width="20%">No. Fail</td>
							<td width="35%">Tujuan</td>
							<td width="20%">Urusan</td>
							<td width="20%">Status Semasa</td>
						</tr>
			
			 	#if($!senaraiUrusanLain.size()!=0)
		           	#foreach($list in $!senaraiUrusanLain)
		                #set( $i = $velocityCount )
		         		#if ( ($i % 2) != 1 )
		              		 #set( $row = "row2" )
		         		#else
		               		 #set( $row = "row1" )
		         		#end
		         	
			        	<tr>
			        		<td class="$row" align="center">$list.bil</td>
			        		<td class="$row">$!list.noFail</td>
			            	<td class="$row">$!list.tujuan</td>
			            	<td class="$row">$!list.urusan</td>
			            	<td class="$row">$!list.tindakan</td>
			        	</tr>
		        	#end
		        	
		     	#else
		        		<tr>
		        			<td colspan="5">Tiada rekod</td>
		        		</tr>
		     	#end
					</table>	
		<!-- END SENARAI URUSAN SEMASA -->						
					</fieldset>            		
	            </td>	
       		</tr>
       		
       		<tr>
            	<td>
					<fieldset>	<legend>MAKLUMAT PENYEWAAN</legend>
		
<!-- NOT USE. REPLACE WITH DIV -->
		
		<!-- Ajax: Maklumat Detail -->
					<div id="div_maklumatPenyewaan"></div>
		
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
		<!-- END LIST PENYEWAAN -->						
					</fieldset>            		
	            </td>	
       		</tr>
			       		   
       		<tr>
				<td>
					<fieldset>	<legend>MAKLUMAT PAJAKAN</legend>
						<div id="divmaklumatpajakan"></div>
					
					<table width="100%">
						<tr class="table_header">
							<td scope="row" width="5%" align="center">Bil.</td> 
							<td width="20%">No. Fail Pajakan</td>
							<td width="20%">Nama Pemajak</td>
							<td width="35%">Tujuan</td>
							<td width="10%">Tarikh Mula</td>
							<td width="10%">Tarikh Tamat</td>
						</tr>
				#if($!senaraiPajakan.size()!=0)
		           	#foreach($list in $!senaraiPajakan)
		                #set( $i = $velocityCount )
		         		#if ( ($i % 2) != 1 )
		              		 #set( $row = "row2" )
		         		#else
		               		 #set( $row = "row1" )
		         		#end
		         	
			        	<tr>
			        		<td class="$row" align="center">$list.bil</td>
			        		<td class="$row">
			        			<a href="javascript:terperinciPajakan('$!list.idPermohonan','$!list.idHakmilik')"><font color="blue">$!list.noFail</font></a>
			        			<input type="hidden" name="id_permohonan" value="$!list.idPermohonan">
			        			<input type="hidden" name="idHakmilik" value="$!list.idHakmilik">
			        		</td>
			            	<td class="$row">$!list.pemohon</td>
			            	<td class="$row">$!list.tujuan</td>
			            	<td class="$row">$!list.tarikhMula</td>
			            	<td class="$row">$!list.tarikhTamat</td>
			        	</tr>
		        	#end
		        	
		     	#else
		        		<tr>
		        			<td colspan="6">Tiada rekod</td>
		        		</tr>
		     	#end						
       		<!-- <tr>
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
       		</tr>  -->                   
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
			            	<!-- <td class="row1" align="center">&nbsp;</td> -->
			            	<td class="row1" colspan="3">Tiada Rekod</td>
						</tr>
			  	#end
					</table>
					</fieldset>
	       		</td>	
      		</tr>					

   	 
<!-- </fieldset> -->



