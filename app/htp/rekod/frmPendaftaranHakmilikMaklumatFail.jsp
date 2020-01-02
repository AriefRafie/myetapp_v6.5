<fieldset>
<!--<legend>MAKLUMAT FAIL HAKMILIK</legend>-->
	<table width="100%" border="0" align="left">
      		<tr>
            	<td width="50%" valign="top">
                	<table width="100%" border="0">
                    	<tr>
  							<td width="1%" valign="top" >
  								<span class="labelmandatory">#if ($mode != 'doView' && $mode != 'view') * #end </span>
  								
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Kementerian</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms" >
 								$txtNamaKementerian 
 								<input type="hidden" name="txtIdKementerian1" value="$txtIdKementerian"/>
 								
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
 								<input type="hidden" name="txtNoFail" value="$txtNoFailSeksyen"/>
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
 								<input type="text" name="txtFailPTG" id="txtFailPTG" value="$!txtFailPTG" size="45" $readonly class="$disabled" />
  							</td>
                		</tr>
                    	<tr>
  							<td width="1%" valign="top" >
				        	</td>				        
                        	<td width="30%" valign="top">
                            	<div align="left">
                            		<span class="labelinput">Tajuk</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" valign="top" >:</td>
                   			<td width="68%" class="pautanms" >
 								<textarea readonly class="disabled" name="txtNamaFail" id="txtNamaFail" cols="61" rows="3" 
                                class="$inputTextClass" onkeyup="this.value=this.value.toUpperCase()">$txtTajuk</textarea>
  							</td>
                		</tr>
                 	</table>
           		</td>
                        	
                <td valign="top">
               		<table width="100%">
                   	<tr>
  							<td width="1%" valign="top" >
  								<span class="labelmandatory">#if ($mode != 'doView' && $mode != 'view') * #end </span>
  								
				        	</td>				        
                        	<td width="30%">
                            	<div align="left">
                            		<span class="labelinput">Agensi</span>
                            	</div>
                        	</td>
                  			<td width="1%" class="labelinput" >:</td>
                   			<td width="68%" class="pautanms" >
 								$txtNamaAgensi
 								<input type="hidden" name="txtIdAgensi1" value="$txtIdAgensi"/>
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
 								$!txtFailKJP
 								<input type="hidden" name="txtFailKJP" value="$txtFailKJP"/>
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
 								<input type="text" name="txtFailPTD" id="txtFailPTD" value="$!txtFailPTD" size="45" $readonly class="$disabled"/>
  							</td>
                		</tr>

                	</table>
                </td>
       		</tr>   
	</table>
</fieldset>
