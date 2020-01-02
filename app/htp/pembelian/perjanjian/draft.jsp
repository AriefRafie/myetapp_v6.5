
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>
			<fieldset><legend><strong>MAKLUMAT PERMOHONAN</strong> </legend>	
				#parse ("app/htp/pembelian/fail/fileInfo.jsp")			
			</fieldset>
		</td>
    </tr>

	<tr>
		<td>
			<fieldset><legend><strong>SEMAKAN DRAF PERJANJIAN</strong> </legend>	
						<table width="100%">
                	<tr>
						<td valign="top" width="1%">
				        <span class="labelmandatory">*</span><!---->
				        </td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Tarikh Terima</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
                            <input type="text" name="txdTarikhTerima" id="txdTarikhTerima" size="15" value="$!draft.tarikhTerima" onblur="check_date(this);checkDate(document.${formName}.txdTarikhTerima)" />
							<img src="../img/calendar.gif" alt="calender" border="0" style="" onclick="displayDatePicker('txdTarikhTerima',false,'dmy');" />
                        </td>
					</tr>
                	<tr>
						<td valign="top" width="1%">
				        <!--<span class="labelmandatory">#if ($mode != 'readonly') * #end </span>-->
				        </td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Tarikh Hantar</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
<input type="text" name="txdTarikhHantar" id="txdTarikhHantar" size="15" value="$!draft.tarikhHantar" onblur="check_date(this)"   />
          		 <img src="../img/calendar.gif" alt="calender" border="0" style="" onclick="displayDatePicker('txdTarikhHantar',false,'dmy');" />
						</td>
					</tr>
                	<tr>
						<td valign="top" width="1%">
				        <!--<span class="labelmandatory">#if ($mode != 'readonly') * #end </span>-->
				        </td>				        
				        <td width="30%" valign="top">
				        	<div align="right" class="labelinput">
				        	<div align="left" >Ulasan</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%" valign="top">:</td>				        
				        <td width="68%">
                        	<textarea name="txtcatatan" id="txtcatatan" cols="41" rows="5" onkeyup="this.value=this.value.toUpperCase();textCounter(this.form.txtcatatan,this.form.remLen4,4000);" onKeyDown="textCounter(this.form.txtcatatan,this.form.remLen4,4000);" >$!draft.ulasan</textarea>
						</td>
					</tr>
					<tr>
						<td valign="top">&nbsp;</td>
						<td valign="top">&nbsp;</td>
						<td valign="top">&nbsp;</td>
						<td valign="top"><input type="text" readonly class="disabled" name="remLen4" size="3" maxlength="3" value="4000"> Baki Aksara</td>
					</tr>
			</table>	
			</fieldset>
		</td>
    </tr>
    
    	<tr>
    	<td align="center" colspan="4">
    		#if($drafMode == "edit")
    			<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="updateDraft()" />
    		#else
    		 <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanDraft()" />
    		#end
    		 <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliDraft()" />
    	
    	</td>
    </tr>

</table>
<input type="hidden" name="idDraf" value="$!draft.idDrafPerjanjian"/>
<input type="hidden" name="idPermohonan" value="$!htpPermohonan.permohonan.getIdPermohonan()"/>
<input type="hidden" name="idHtpPermohonan" value="$!htpPermohonan.getIdHtpPermohonan()"/>