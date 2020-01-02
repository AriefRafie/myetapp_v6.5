
<script>
if( $jquery('#'+'div_rowPengguna$internalType$viewPengguna.ID_PERMOHONAN').length ) 
{
	window.scrollTo(0, $jquery('#'+'div_rowPengguna$internalType$viewPengguna.ID_PERMOHONAN').offset().top);
}
else
{
	if( $jquery('#'+'div_viewPengguna$internalType$viewPengguna.ID_PERMOHONAN').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_viewPengguna$internalType$viewPengguna.ID_PERMOHONAN').offset().top);
	}
	
}
</script>

<tr id="div_viewPengguna$internalType$viewPengguna.ID_PERMOHONAN">
<td align="left" valign="top" colspan="14">
	<fieldset>
	<legend>
	
	#if($viewPengguna.ID_PERMOHONAN != "")
	Kemaskini Maklumat $viewPengguna.NAMA
	#else
	Kemasukan Maklumat Pengguna
	#end
	</legend>
		<table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
			</tr>
			<tr>
				<td valign="top" >					
				</td>			
				<td valign="top" >
				Tarikh Permohonan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.TARIKH_PENDAFTARAN
				</td>
			</tr>
			
			<tr>
				<td valign="top" >
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				MyID
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
					
			
				
				<!-- open username by IC -->
				<input name="NOKP1_$internalType$viewPengguna.ID_PERMOHONAN" type="text" 
				id="NOKP1_$internalType$viewPengguna.ID_PERMOHONAN" style="width:50px;" 
				onKeyUp="javascript:validateIC_V3(event,this,this.value,'NOKP2_$internalType$viewPengguna.ID_PERMOHONAN');checkDOBByIC_V3(this,this.value,'span_NOKP1_$internalType$viewPengguna.ID_PERMOHONAN');" 
				value="$viewPengguna.NOKP1" size="7" maxlength="6"  
				
				/>
               
               	<span id="span_NOKP1_$internalType$viewPengguna.ID_PERMOHONAN">
                <input type="hidden" id="CHECK_NOKP1_$internalType$viewPengguna.ID_PERMOHONAN" 
				name="CHECK_NOKP1_$internalType$viewPengguna.ID_PERMOHONAN" value="true" >
                </span> 
                                     &nbsp; 
                <input name="NOKP2_$internalType$viewPengguna.ID_PERMOHONAN" type="text" 
                id="NOKP2_$internalType$viewPengguna.ID_PERMOHONAN" style="width:20px;" 
                onKeyUp="javascript:validateIC_V3(event,this,this.value,'NOKP3_$internalType$viewPengguna.ID_PERMOHONAN');" 
                value="$viewPengguna.NOKP2" size="1" maxlength="2"  />
                                      &nbsp; 
                                      
                <input name="NOKP3_$internalType$viewPengguna.ID_PERMOHONAN" type="text" 
                id="NOKP3_$internalType$viewPengguna.ID_PERMOHONAN" style="width:40px;" 
                onKeyUp="javascript:validateIC_V3(event,this,this.value,'NOKP3_$internalType$viewPengguna.ID_PERMOHONAN');" 
               
                value="$viewPengguna.NOKP3" size="5" maxlength="4" />     
             
             	<!-- close username by IC -->
			</td>
				
			</tr>
			
			<tr>
				<td valign="top" >
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Nama Penuh
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<input style="text-transform:uppercase;" size="50" type="text" id="NAMA_$internalType$viewPengguna.ID_PERMOHONAN" 
				name="NAMA_$internalType$viewPengguna.ID_PERMOHONAN" 
				value="$viewPengguna.NAMA" onKeyUp="avoidScriptInjection(this,this.value);" >				
				</td>
			</tr>
			
			<tr>
				<td valign="top" >
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Jawatan
				</td>
				<td valign="top" >
				:
				</td>
				<td  >
				<select id="ID_JAWATAN_$internalType$viewPengguna.ID_PERMOHONAN"  name="ID_JAWATAN_$internalType$viewPengguna.ID_PERMOHONAN" 
                onchange="doDivAjaxCall$formname('div_ID_GRED$internalType$viewPengguna.ID_PERMOHONAN','showListGred','USER_ID=$viewPengguna.ID_PERMOHONAN&internalType=$internalType&ID_JAWATAN='+$jquery('#ID_JAWATAN_$internalType$viewPengguna.ID_PERMOHONAN').val());" >	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJJAWATAN )		
							#set ( $selected_ruj = "" )
							#if($viewPengguna.ID_JAWATAN==$ruj.ID)
							#set ( $selected_ruj = "selected" )
							#end		
							<option $selected_ruj value="$ruj.ID" >
							$ruj.KETERANGAN
							</option>
						#end
					</select>
				</td>
			</tr>
			
			<tr>
				<td valign="top" >
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Gred
				</td>
				<td valign="top" >
				:
				</td>
				<td  id="div_ID_GRED$internalType$viewPengguna.ID_PERMOHONAN">
					<select id="ID_GRED_$internalType$viewPengguna.ID_PERMOHONAN"  name="ID_GRED_$internalType$viewPengguna.ID_PERMOHONAN" >	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJGRED )		
							#set ( $selected_ruj = "" )
							#if($viewPengguna.ID_GRED == $ruj.ID)
							#set ( $selected_ruj = "selected" )
							#end		
							<option $selected_ruj value="$ruj.ID" >
							$ruj.KOD
							</option>
						#end
					</select>
				</td>
			</tr>
			
			
			<tr>
				<td valign="top" >	
				<font color="red" >*</font>			
				</td>			
				<td valign="top" >
				Bahagian
				</td>
				<td valign="top" >
				:
				</td>
				<td >
					<select id="ID_SEKSYEN_$internalType$viewPengguna.ID_PERMOHONAN"  
					name="ID_SEKSYEN_$internalType$viewPengguna.ID_PERMOHONAN" 
					onChange = "doDivAjaxCall$formname('div_ID_PEJABATJKPTG$internalType$viewPengguna.ID_PERMOHONAN','showListPejabat','ID_SEKSYEN='+$jquery('#ID_SEKSYEN_$internalType$viewPengguna.ID_PERMOHONAN').val()+'&ID_NEGERI='+$jquery('#ID_NEGERI_$internalType$viewPengguna.ID_PERMOHONAN').val()+'&internalType=$internalType&USER_ID=$viewPengguna.ID_PERMOHONAN');"
					>	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJSEKSYEN )		
							#set ( $selected_ruj = "" )
							#if($viewPengguna.ID_SEKSYEN==$ruj.ID)
							#set ( $selected_ruj = "selected" )
							#end		
							<option $selected_ruj value="$ruj.ID" >
							$ruj.KETERANGAN
							</option>
						#end
					</select>
				</td>
			</tr>
			
			<tr>
				<td valign="top" >	
				<font color="red" >*</font>			
				</td>			
				<td valign="top" >
				Negeri
				</td>
				<td valign="top" >
				:
				</td>
				<td >
					<select id="ID_NEGERI_$internalType$viewPengguna.ID_PERMOHONAN"  name="ID_NEGERI_$internalType$viewPengguna.ID_PERMOHONAN"
					onChange = "doDivAjaxCall$formname('div_ID_PEJABATJKPTG$internalType$viewPengguna.ID_PERMOHONAN','showListPejabat','ID_SEKSYEN='+$jquery('#ID_SEKSYEN_$internalType$viewPengguna.ID_PERMOHONAN').val()+'&ID_NEGERI='+$jquery('#ID_NEGERI_$internalType$viewPengguna.ID_PERMOHONAN').val()+'&internalType=$internalType&USER_ID=$viewPengguna.ID_PERMOHONAN');"
					>	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJNEGERI )		
							#set ( $selected_ruj = "" )
							#if($viewPengguna.ID_NEGERI==$ruj.ID)
							#set ( $selected_ruj = "selected" )
							#end	
							
							
							<option $selected_ruj value="$ruj.ID" >
							$ruj.KETERANGAN
							</option>					
							
						#end
					</select>
				</td>
			</tr>
			
			<tr>
				<td valign="top" >	
				<font color="red" >*</font>			
				</td>			
				<td valign="top" >
				Unit / Pejabat
				</td>
				<td valign="top" >
				:
				</td>
				<td id="div_ID_PEJABATJKPTG$internalType$viewPengguna.ID_PERMOHONAN" >
					<select id="ID_PEJABATJKPTG_$internalType$viewPengguna.ID_PERMOHONAN"  name="ID_PEJABATJKPTG_$internalType$viewPengguna.ID_PERMOHONAN" 
					onChange = "doDivAjaxCall$formname('div_ALAMAT_PEJABATJKPTG$internalType$viewPengguna.ID_PERMOHONAN','showDisplayPejabat','ID_PEJABATJKPTG='+$jquery('#ID_PEJABATJKPTG_$internalType$viewPengguna.ID_PERMOHONAN').val()+'&internalType=$internalType&USER_ID=$viewPengguna.ID_PERMOHONAN');"
					>	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $listPejabatJKPTG )		
							#set ( $selected_ruj = "" )
							#if($viewPengguna.ID_PEJABATJKPTG==$ruj.ID_PEJABATJKPTG)
							#set ( $selected_ruj = "selected" )
							#end		
							<option $selected_ruj value="$ruj.ID_PEJABATJKPTG" >
							$ruj.NAMA_UNIT
							</option>
						#end
					</select>
				</td>
				
				
			</tr>
			
			<tr id="div_ALAMAT_PEJABATJKPTG$internalType$viewPengguna.ID_PERMOHONAN" style="display:none">
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Alamat Pejabat
				</td>
				<td valign="top" >
				:
				</td>
				<td  >
				#if($viewPejabatJKPTG.size() > 0)
					#if($viewPejabatJKPTG.ALAMAT1 != "")
					$viewPejabatJKPTG.ALAMAT1<br>
					#end
					#if($viewPejabatJKPTG.ALAMAT2 != "")
						$viewPejabatJKPTG.ALAMAT2<br>
					#end
					#if($viewPejabatJKPTG.ALAMAT3 != "")
						$viewPejabatJKPTG.ALAMAT3<br>
					#end
					#if($viewPejabatJKPTG.POSKOD != "")
						$viewPejabatJKPTG.POSKOD &nbsp;						
					#end
					#if($viewPejabatJKPTG.BANDAR != "")
						$viewPejabatJKPTG.BANDAR<br>
					#end
					#if($viewPejabatJKPTG.NEGERI != "")
						$viewPejabatJKPTG.NEGERI<br>
					#end
					
					#if($viewPejabatJKPTG.NO_TEL != "")
						No. Tel : $viewPejabatJKPTG.NO_TEL<br>
					#end
					#if($viewPejabatJKPTG.NO_FAX != "")
						No. Fax : $viewPejabatJKPTG.NO_FAX<br>
					#end
					
					<script type="text/javascript">
					document.getElementById("div_ALAMAT_PEJABATJKPTG$internalType$viewPengguna.ID_PERMOHONAN").style.display="";
					</script>
					
				#end
				</td>
			</tr>
			
			
			
			<tr>
				<td valign="top" >
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Emel
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				
				<input name="EMELPEMOHON_$internalType$viewPengguna.ID_PERMOHONAN" type="text" 
				id="EMELPEMOHON_$internalType$viewPengguna.ID_PERMOHONAN"  value="$viewPengguna.EMEL" size="50" onKeyUp="ValidateEmailFormat(this,this.value,'EMELPEMOHON_errorSpan_$internalType$viewPengguna.ID_PERMOHONAN')" />
				<div id="EMELPEMOHON_errorSpan_$internalType$viewPengguna.ID_PERMOHONAN"></div>
					
				
				</td>
			</tr>
			
			<tr>
				<td valign="top" >
				<font color="red" ></font>				
				</td>			
				<td valign="top" >
				No. Telefon Bimbit
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				<input name="NO_HP_$internalType$viewPengguna.ID_PERMOHONAN" type="text" id="NO_HP_$internalType$viewPengguna.ID_PERMOHONAN" 
				onKeyUp="RemoveNonNumeric_V3(this,this.value)"  value="$viewPengguna.NO_HP" size="50" />
				
				
				</td>
			</tr>
			
			<tr>
				<td valign="top" >	
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Penyelia
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<input name="PEGAWAIPENYELIA_$internalType$viewPengguna.ID_PERMOHONAN" type="text" id="PEGAWAIPENYELIA_$internalType$viewPengguna.ID_PERMOHONAN" 
				value="$viewPengguna.PEGAWAIPENYELIA" size="50" />		
				</td>
			</tr>
			
			<tr>
				<td valign="top" >			
				<font color="red" >*</font>		
				</td>			
				<td valign="top" >
				Status Pendaftaran ID
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				#set($check2 = "")
				#set($check3 = "")
				#if($viewPengguna.FLAG_STATUS=="2")
				#set($check2 = "checked")
				#elseif($viewPengguna.FLAG_STATUS=="3")
				#set($check3 = "checked")
				#end		
				
				<input type="radio" id="RADIO_STATUS_PID_$internalType$viewPengguna.ID_PERMOHONAN" 
				name="RADIO_STATUS_PID_$internalType$viewPengguna.ID_PERMOHONAN" 
				onClick="fromRadioToText(this,this.value,'STATUS_PID_$internalType$viewPengguna.ID_PERMOHONAN')" 
				value="3" $check3>DITERIMA
				&nbsp;&nbsp;
				<input type="radio" id="RADIO_STATUS_PID_$internalType$viewPengguna.ID_PERMOHONAN" 
				name="RADIO_STATUS_PID_$internalType$viewPengguna.ID_PERMOHONAN" 
				onClick="fromRadioToText(this,this.value,'STATUS_PID_$internalType$viewPengguna.ID_PERMOHONAN')" 
				value="2" $check2>DITOLAK
				
				<input type="hidden" id="STATUS_PID_$internalType$viewPengguna.ID_PERMOHONAN" 
				name="STATUS_PID_$internalType$viewPengguna.ID_PERMOHONAN" value="$viewPengguna.FLAG_STATUS" >
				
				</td>
			</tr>
			
			<tr>
				<td valign="top" >
				</td>			
				<td valign="top" >
				Catatan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<textarea style="text-transform:uppercase;" rows="4" cols="50" id="CATATAN_$internalType$viewPengguna.ID_PERMOHONAN" 
				name="CATATAN_$internalType$viewPengguna.ID_PERMOHONAN" 
				>$viewPengguna.CATATAN</textarea>			
				</td>
			</tr>
			<tr>
			<td valign="top" align="center" >
			
			</td>
			<td valign="top" align="left" >
			Lampiran
			</td>
			<td valign="top" align="center" >
			:
			</td>
			<td valign="top" align="left" >
			
			<div id="displayDocPIP_$internalType$viewPengguna.ID_PERMOHONAN">
			<script> 	
			  $jquery(document).ready(function () {
				  doDivAjaxCall$formname('displayDocPIP_$internalType$viewPengguna.ID_PERMOHONAN','showDocPIP','ID_PERMOHONAN=$viewPengguna.ID_PERMOHONAN&internalType=$internalType&USER_ID=$viewPengguna.ID_PERMOHONAN');			 	  
			  });		
			</script>
			</div>
			
			<span id="displayDocPIP_$internalType$viewPengguna.ID_PERMOHONAN"></span>
			<input size="50" type="file" id="FILE_$internalType$viewPengguna.ID_PERMOHONAN" 
							name="FILE_$internalType$viewPengguna.ID_PERMOHONAN" 
							onChange="uploadDocPIP(this,'$internalType','','$viewPengguna.ID_PERMOHONAN','displayDocPIP_$internalType$viewPengguna.ID_PERMOHONAN');"
							>
			
			</td>
			</tr>
			
			#if($viewPengguna.TARIKH_KEPUTUSAN!="")
			<tr>
				<td valign="top" >			
				</td>			
				<td valign="top" >
				Tarikh Pendaftaran ID Pengguna
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.TARIKH_KEPUTUSAN			
				</td>
			</tr>
			#end
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >				
				</td>
				<td valign="top" >				
				</td>
				<td valign="top" >
				
				<input type="button" id="BTNSAVE$internalType$viewPengguna.ID_PERMOHONAN" name="BTNSAVE$internalType$viewPengguna.ID_PERMOHONAN" 
				onClick="if(valEditPenggunaMohon('$internalType','$viewPengguna.ID_PERMOHONAN') == true){doDivAjaxCall$formname('div_viewPengguna$internalType$viewPengguna.ID_PERMOHONAN','savePenggunaMOHON','internalType=MOHON&ID_PERMOHONAN=$viewPengguna.ID_PERMOHONAN');}" value="Simpan" > 
	   			<input type="button" id="BTNBTL$internalType$viewPengguna.ID_PERMOHONAN" name="BTNBTL$internalType$viewPengguna.ID_PERMOHONAN" onClick="doDivAjaxCall$formname('div_viewPengguna$internalType$viewPengguna.ID_PERMOHONAN','edit_PenggunaMOHON','ID_PERMOHONAN=$viewPengguna.ID_PERMOHONAN&internalType=$internalType');" value="Batal" > 
	   			<input type="button" id="BTNCLOSE$internalType$viewPengguna.ID_PERMOHONAN" name="BTNCLOSE$internalType$viewPengguna.ID_PERMOHONAN" onClick="doDivAjaxCall$formname('div_viewPengguna$internalType$viewPengguna.ID_PERMOHONAN','close_PenggunaMOHON','ID_PERMOHONAN=$viewPengguna.ID_PERMOHONAN&internalType=$internalType');" value="Tutup" > 
	   			
	   			
	   			</td>
			</tr>
		
		
		</table>
	</fieldset>
	<br>
</td>		
</tr>
<script>
//pwdStrength();
//document.getElementById('PASSWORD_$internalType$viewPengguna.ID_PERMOHONAN').value = null;
</script>