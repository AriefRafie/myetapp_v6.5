<!-- frmPerletakhakanPengesahan.jsp -->
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>

<style type="text/css">
<!--
.pautanms {color: #0033FF}
-->
</style>
<p>
<input type="hidden" name="idPermohonan" value="$idPermohonan" id="idPermohonan" /> 
<input type="hidden" name="idfail"  value="$!idFail" id="idfail" /> 
<input type="hidden" name="idHtpPermohonan" value="$idHtpPermohonan" id="idHtpPermohonan" />
<input type="hidden" name="idSuburusanStatusFail" value="$idSuburusanStatusFail" id="idSuburusanStatusFail" />
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
</p>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
    	<td>
    	
		<fieldset><legend><strong>MAKLUMAT PENERIMAAN PERMOHONAN</strong></legend>
			<table width="100%" border="0" cellspacing="2" cellpadding="2">
  				<tr>
    				<td>
    					<table width="100%" border="0" align="left">    
					      <tr>
					        <td width="1%">
					        	#if ($mode != 'view')<span class="style1">*</span>#end
					        </td>
					        <td width="11%"><div align="left">Negeri</div></td>
					        <td width="1%"><div align="center">:</div></td>
					        <td width="36%">$selectNegeri</td>
					        	<input name="id_negeri" type="hidden" value="$id_negeri"/>
					        <td>&nbsp;</td>
					        <td width="20%"><div align="left">No. Fail</div></td>
					        <td width="1%"><div align="center">:</div></td>
					        <td width="36%"><label>
					          <input name="noFail" type="text" id="noFail" value="$noFail" size="30" readonly="readonly" class="disabled"/>
					        </label></td>
					      </tr>
					      <tr>
					        <td width="1%">
								#if ($mode != 'view')<span class="style1">*</span>#end    
					        </td>
					        <td width="11%"><div align="left">Daerah</div></td>
					        <td width="1%"><div align="center">:</div></td>
					        <td width="36%">$selectDaerah</td>
					       	<td width="1%">
					        	#if ($mode != 'view')<span class="style1">*</span>#end      
					        </td>
					        <td><div align="left">No. Fail KJP</div></td>
					        <td><div align="center">:</div></td>
					        <td>
					        	<input name="txtNoFailKJP" type="text" class="$inputTextClass" id="txtNoFailKJP" value="$txtNoFailKJP" size="30" $readonly onblur="this.value=this.value.toUpperCase();"/>	 
					        </td>
					      </tr>
					      <tr>
					      	<td width="1%">
					        	#if ($mode != 'view')<span class="style1">*</span>#end        
					        </td>
					        <td width="11%"><div align="left">Kementerian</div></td>
					        <td width="1%"><div align="center">:</div></td>
					        <td width="36%"><label>$selectKementerian</label></td>
					        <td width="1%">
					        	#if ($mode != 'view')<span class="style1">*</span>#end        
					        </td>
					        <td><div align="left"> Tarikh Surat KJP</div></td>
					        <td><div align="center">:</div></td>
					        <td>
					        	<label>
          							<input name="txdTarikhSurKJP" type="text" id="txdTarikhSurKJP" value="$txdTarikhSurKJP" $readonly class="$inputTextClass" size="9" onblur="check_date(this)"/>
          						</label>
          						#if ($mode != 'view') <a href="javascript:displayDatePicker('txdTarikhSurKJP',false,'dmy');"> <img src="../img/calendar.gif" border="0"/></a> #end     
					      	</td>
					      </tr>
					      
					      <tr>
					     	<td width="1%">
					        	#if ($mode != 'view')<span class="style1">*</span>#end        
					        </td>
					        <td width="11%"><div align="left">Agensi</div></td>
					        <td width="1%"><div align="center">:</div></td>
					        <td width="36%"><label>$selectAgensi</label></td>
					       	<td width="1%">&nbsp;</td>
					        <td><div align="left">No. Fail Lain</div></td>
					        <td><div align="center">:</div></td>
					        <td>
					        	<label>
          							<input name="txtNoFailLain" type="text" class="$inputTextClass" id="txtNoFailLain" value="$txtNoFailLain" size="30" $readonly  onblur="this.value=this.value.toUpperCase();"/>
          						</label>       
					          </td>
					      </tr>
					      
					      <tr>
					      	<td width="1%">
					        	#if ($mode != 'view')<span class="style1">*</span>#end
					        </td>
					        <td>Urusan</td>
					        <td>:</td>
					        <td>
					        	$selectSuburusan
          						<input name="idSuburusan" type="hidden" value="$idSuburusan"/>
          					</td>
					       	<td width="1%">&nbsp;</td>
					        <td width="20%">&nbsp;</td>
					        <td width="1%">&nbsp;</td>
					        <td width="36%">&nbsp;</td>
					      </tr>

					      <tr>
					        <td valign="top"> 
					        	#if ($mode != 'view')<span class="style1">*</span>#end 
					        </td>
					        <td valign="top"><div align="left">Tajuk</div></td>
					        <td valign="top"><div align="center">:</div></td>
					        <td>
					        	<textarea $readonly class="$disabled" name="txtTajuk" id="txtTajuk" cols="41" rows="5" 
					        	onblur="this.value=this.value.toUpperCase();">$!txtTajuk</textarea>
					        </td>
					        <td>&nbsp;</td>
					        <td></td>
					        <td>&nbsp;</td>
					        <td></td>
					      </tr>

  						</table> ##$!readOnly 
    				</td>
  				</tr>
			</table>
		</fieldset>
		
    	</td>
	</tr>
	 						
#if($!readOnly == "")

	<td align="center">
			<!-- <input class="stylobutton" type="button" onclick="javascript:permohonanTerima($!idfail);" value="Terima" /> -->
			<input class="stylobutton100" type="button" onclick="javascript:perletakhakanViewMaklumatOnline('$!idFail','$!idPermohonan','$!idHtpPermohonan','$!idSuburusanStatusFail');" value="Seterusnya" />
		</td>
	</tr>
#else
	<tr>
		<td align="center" >
			<input class="stylobutton100" type="button" onclick="javascript:perletakhakanTerima($!idFail);" value="Simpan & Email" />
			<!-- <input class="stylobutton" type="button" onclick="javascript:perletakhakanTolak($!idFail);" value="Tolak" /> -->
			
			<!-- <input class="stylobutton" type="button" onclick="javascript:cetakKulitFail('$idPermohonan');" value="Cetak Kulit Fail" />
			<input class="stylobutton" type="button" onclick="javascript:cetakDoket('$idPermohonan');" value="Cetak Doket" /> --><img src="../img/emel.gif" title="Pemberitahuan melalui emel" >
		</td>
	</tr>
#end
	
</table>
