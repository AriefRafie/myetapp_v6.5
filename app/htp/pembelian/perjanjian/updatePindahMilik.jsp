
<style type="text/css">
<!--
	.pautanms{color: #0000FF}
-->
</style>
<table width="100%" border="0">
		<tr>
		<td>
			<fieldset><legend><strong>MAKLUMAT PERMOHONAN</strong> </legend>	
				#parse ("app/htp/pembelian/fail/fileInfo.jsp")			
			</fieldset>
		</td>
    </tr>
	<tr>
		<td colspan="2">
			<fieldset>
			<legend><strong>MAKLUMAT BORANG PINDAH MILIK</strong></legend>
			
			<table width="100%">
                	<tr>
						<td valign="top" width="1%">
				        <!--<span class="labelmandatory">#if ($mode != 'readonly') * #end </span>-->
				        </td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Tarikh Terima daripada KJP</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
                            <input type="text" name="tarikhTerimaKJP" value="$!pindahMilik.tarikhTerima" $mode $classDis onblur="check_date(this);checkDate(document.${formName}.tarikhTerimaKJP);">
		<img src="../img/calendar.gif" alt="calender" border="0" style="" onclick="displayDatePicker('tarikhTerimaKJP',false,'dmy');" />
							<a href="javascript:onclick=setSelected(2,'senaraisemakpmilik','senaraisemakpmilik',0);">	<span class="pautanms">Semakan</span></a>
                        </td>
					</tr>
                	<tr>
						<td valign="top" width="1%">
				        <!--<span class="labelmandatory">#if ($mode != 'readonly') * #end </span>-->
				        </td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Tarikh Tandatangan PTP</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
                        		<input type="text" name="tarikhTandatanganPTP" value="$!pindahMilik.tarikhTandatangan" $mode $classDis $classDis onblur="check_date(this);checkDate(document.${formName}.tarikhTandatanganPTP);">
		 						<img src="../img/calendar.gif" alt="calender" border="0" style="" onclick="displayDatePicker('tarikhTandatanganPTP',false,'dmy');" />
						</td>
					</tr>
                	<tr>
						<td valign="top" width="1%">
				        <!--<span class="labelmandatory">#if ($mode != 'readonly') * #end </span>-->
				        </td>				        
				        <td width="30%">
				        	<div align="right" class="labelinput">
				        	<div align="left">Tarikh Hantar ke KJP</div>
				        	</div>
				       	</td>				      	
				      	<td width="1%">:</td>				        
				        <td width="68%">
                        	<input type="text" name="tarikhHantarKJP" value="$!pindahMilik.tarikhHantar" $mode $classDis onblur="check_date(this);checkDate(document.${formName}.tarikhHantarKJP);">
							<img src="../img/calendar.gif" alt="calender" border="0" style="" onclick="displayDatePicker('tarikhHantarKJP',false,'dmy');" />
						</td>
					</tr>
			</table>
			
			</fieldset>
		</td>
	</tr>

	<tr>
    
		    	<td align="center" colspan="2">
		   			<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Simpan" onclick="updatePindahMilik()">
            		 <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="setSelected(2,'pindahMilik','ptambahanview',0);">
           		 </td>
		    
	 </tr>
</table>
<input type="hidden" name="idPindahMilik" value="$!pindahMilik.idPindahMilik">
<input type="hidden" name="idPermohonan" value="$!htpPermohonan.permohonan.getIdPermohonan()"/>
<input type="hidden" name="idHtpPermohonan" value="$!htpPermohonan.getIdHtpPermohonan()"/>