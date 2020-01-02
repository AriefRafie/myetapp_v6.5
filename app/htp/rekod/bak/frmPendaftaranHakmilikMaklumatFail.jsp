<fieldset>
<!--<legend>MAKLUMAT FAIL HAKMILIK</legend>-->
<table width="100%" border="0" align="left">
	<tr>
		<td align="center" valign="top" width="50%">			
			<table width="100%" border="0">
				<!--<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory">#if ($mode != 'readonly') * #end </span></td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left"> Kementerian  </div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
                            <input type="text" name="txtNamaPeguam" onkeyup="this.value=this.value.toUpperCase();" size="60" maxlength="80"  value="$pNama" $mode $classDis />
                        </td>
					</tr>
				
			</table>-->
	              <tr>
	                <td width="3%"><div align="right"><font color="#FF0000"></font></div></td>
	                <td width="20%">Kementerian
				     </td>
	                <td width="2%">:</td>
	                <td width="75%">$txtNamaKementerian</td><input type="hidden" name="txtIdKementerian" value="$txtIdKementerian"/>
	              </tr>
			</table>
		</td>
		<td align="center" valign="top" width="50%">
			<table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"></div></td>
                <td width="20%"><div align="left">Agensi</div></td>
                <td width="2%">:</td>
                <td width="75%">$txtNamaAgensi</td><input type="hidden" name="txtIdAgensi" value="$txtIdAgensi"/>
              </tr>
			</table>
		</td>		
	</tr>
	<tr>
		<td align="center" valign="top" width="50%">			
			<table width="100%" border="0">
	              <tr>
	                <td width="3%"><div align="right"><font color="#FF0000"></font></div></td>
	                <td width="20%"><div align="left">No. Fail Seksyen</div></td>
	                <td width="2%">:</td>
	                <td width="75%">$txtNoFailSeksyen</td><input type="hidden" name="txtNoFail" value="$txtNoFailSeksyen"/>
	              </tr>
			</table>
		</td>
		<td align="center" valign="top" width="50%">
			<table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"></div></td>
                <td width="20%"><div align="left">No. Fail KJP</div></td>
                <td width="2%">:</td>
                <td width="75%">$txtFailKJP</td><input type="hidden" name="txtFailKJP" value="$txtFailKJP"/>
              </tr>
			</table>
		</td>		
	</tr>
	<tr>
		<td align="center" valign="top" width="50%">			
			<table width="100%" border="0">
	              <tr>
	                <td width="3%"><div align="right"><font color="#FF0000"></font></div></td>
	                <td width="20%"><div align="left">No. Fail PTG</div></td>
	                <td width="2%">:</td>
	                <td width="75%"><input type="text" name="txtFailPTG" id="txtFailPTG" value="$txtFailPTG"/></td>
	              </tr>
			</table>
		</td>
		<td align="center" valign="top" width="50%">
			<table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"></div></td>
                <td width="20%"><div align="left">No. Fail PTD</div></td>
                <td width="2%">:</td>
                <td width="75%"><input type="text" name="txtFailPTD" id="txtFailPTD" /></td>
              </tr>
			</table>
		</td>		
	</tr>	
		<tr>
		<td align="center" valign="top" width="50%">			
			<table width="100%" border="0">
	              <tr>
	                <td width="3%" ><div align="right"><font color="#FF0000"></font></div></td>
	                <td width="20%" valign="top"><div align="left">Cara Diperolehi</div></td>
	                <td width="2%" valign="top">:</td>
	                <td width="75%">
	                       <label>
					        	<textarea name="txtNamaFail" id="txtNamaFail" cols="31" rows="3" class="$inputTextClass" onkeyup="this.value=this.value.toUpperCase()">$txtTajuk</textarea>
					        </label>   
	                </td>
	              </tr>
			</table>
		</td>
		<td align="center" valign="top" width="50%">
			<table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"></div></td>
                <td width="20%"><div align="left"></div></td>
                <td width="2%"></td>
                <td width="75%"></td>
              </tr>
			</table>
		</td>		
		                       <!--<td valign="top">
		                         <table width="100%">
                                    
                                      <tr>
                                        <td class="style38" valign="top"></td>
                                        <td class="style38"><span class="labelmandatory">#if ($mode != 'readonly') * #end </span></td>				        

                                        <td class="style36"><div align="right"><span class="style38">:</span></div></td>
                                        <td><label>
                                          <input name="txtAlamatTerakhir1PemohonSurat" type="text" class="disabled" id="txtAlamatTerakhir1PemohonSurat" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase()" value="KAMPUNG KOR" size="45" maxlength="100"   readonly />
                                        </label></td>
                                      </tr>
                              	</table>
                              </td>-->
	</tr>
</table>
</fieldset>
