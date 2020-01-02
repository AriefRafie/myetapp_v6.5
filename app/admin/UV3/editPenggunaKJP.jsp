
<script>
if( $jquery('#'+'div_rowPengguna$internalType$viewPengguna.USER_ID').length ) 
{
	window.scrollTo(0, $jquery('#'+'div_rowPengguna$internalType$viewPengguna.USER_ID').offset().top);
}
else
{
	if( $jquery('#'+'div_viewPengguna$internalType$viewPengguna.USER_ID').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_viewPengguna$internalType$viewPengguna.USER_ID').offset().top);
	}
	
}
</script>

<tr id="div_viewPengguna$internalType$viewPengguna.USER_ID">
<td align="left" valign="top" colspan="14">
<fieldset>
	<legend>
	
	#if($viewPengguna.USER_ID != "")
	Kemaskini Maklumat $viewPengguna.NAMA_PENUH
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
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				ID Pengguna	(User Login)
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				#set($login_hidden="hidden")
				#set($login_kp1="text")
				#set($login_kp2="text")
				#set($login_kp3="text")
				#if($viewPengguna.USER_ID != "")
					$viewPengguna.USER_LOGIN
					#set($login_hidden="hidden")
					#set($login_kp1="hidden")
					#set($login_kp2="hidden")
					#set($login_kp3="hidden")	  
				#end
				
				<!-- open username by IC -->
				<input name="NOKP1_$internalType$viewPengguna.USER_ID" type="$login_kp1" 
				id="NOKP1_$internalType$viewPengguna.USER_ID" style="width:50px;" 
				onKeyUp="javascript:validateIC_V3(event,this,this.value,'NOKP2_$internalType$viewPengguna.USER_ID');setCheckLogin('$internalType','$viewPengguna.USER_ID')" 
				value="" size="7" maxlength="6"  
				onBlur="getAgeByIC_V3(this,this.value,'UMUR_$internalType$viewPengguna.USER_ID');getDOBByIC_V3(this,this.value,'TARIKH_LAHIR_$internalType$viewPengguna.USER_ID','span_NOKP1_$internalType$viewPengguna.USER_ID');"  />
               
               	<span id="span_NOKP1_$internalType$viewPengguna.USER_ID">
                <input type="hidden" id="CHECK_NOKP1_$internalType$viewPengguna.USER_ID" 
				name="CHECK_NOKP1_$internalType$viewPengguna.USER_ID" value="true" >
                </span> 
                                     &nbsp; 
                <input name="NOKP2_$internalType$viewPengguna.USER_ID" type="$login_kp2" 
                id="NOKP2_$internalType$viewPengguna.USER_ID" style="width:20px;" 
                onKeyUp="javascript:validateIC_V3(event,this,this.value,'NOKP3_$internalType$viewPengguna.USER_ID');setCheckLogin('$internalType','$viewPengguna.USER_ID')" 
                value="" size="1" maxlength="2"  />
                                     &nbsp; 
                <input name="NOKP3_$internalType$viewPengguna.USER_ID" type="$login_kp3" 
                id="NOKP3_$internalType$viewPengguna.USER_ID" style="width:40px;" 
                onKeyUp="javascript:validateIC_V3(event,this,this.value,'NOKP3_$internalType$viewPengguna.USER_ID');setCheckLogin('$internalType','$viewPengguna.USER_ID')" 
                onBlur="getJantinaByIC_V3(this,this.value,'JANTINA_$internalType$viewPengguna.USER_ID');"  
                value="" size="5" maxlength="4" />     
             
             	<!-- close username by IC -->
			
			    
				<input size="50" type="$login_hidden" id="USER_LOGIN_$internalType$viewPengguna.USER_ID" 
				name="USER_LOGIN_$internalType$viewPengguna.USER_ID" 
				value="$viewPengguna.USER_LOGIN" 
				onKeyUp = "removeSpaces(this, this.value); if($jquery('#USER_LOGIN_$internalType$viewPengguna.USER_ID').val()!=''){doDivAjaxCall$formname('div_CHECK_USER_LOGIN_$internalType$viewPengguna.USER_ID','checkUSER_LOGIN','internalType=$internalType&USER_ID=$viewPengguna.USER_ID&USER_LOGIN='+$jquery('#USER_LOGIN_$internalType$viewPengguna.USER_ID').val());}"
				>
				<div id="div_CHECK_USER_LOGIN_$internalType$viewPengguna.USER_ID" >
				<input type="hidden" id="CHECK_USER_LOGIN_$internalType$viewPengguna.USER_ID" 
				name="CHECK_USER_LOGIN_$internalType$viewPengguna.USER_ID" value="true" >
				<input type="hidden" id="GET_USER_ID_EXIST_$internalType$viewPengguna.USER_ID" 
				name="GET_USER_ID_EXIST_$internalType$viewPengguna.USER_ID" value="" >
				</div>
				
				<script type="text/javascript">
				if('$viewPengguna.USER_LOGIN'!="")
				{
					$jquery(document).ready(function () 
					{
						//doDivAjaxCall$formname('div_CHECK_USER_LOGIN_$internalType$viewPengguna.USER_ID','checkUSER_LOGIN','internalType=$internalType&USER_ID=$viewPengguna.USER_ID&USER_LOGIN=$viewPengguna.USER_LOGIN');
					});
				}
				</script>
				
				
				</td>
				
			</tr>
			<tr>
				<td valign="top" >
				#if($viewPengguna.USER_ID=="")
				<font color="red" >
				<div id="mando_PASSWORD_$internalType$viewPengguna.USER_ID">*</div>				
				</font>	
				#end
				</td>			
				<td valign="top" >
				Kata Laluan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<div style="width:50%">	
				 
				<input type="hidden" id="ps_call$internalType$viewPengguna.USER_ID" name="ps_call$internalType$viewPengguna.USER_ID" value="no">
				<input size="50" maxlength="12" type="text" 
				onKeyUp="setPasswordClass(this,this.value,'$internalType','$viewPengguna.USER_ID');"	
				id="PASSWORD_$internalType$viewPengguna.USER_ID" 
				name="PASSWORD_$internalType$viewPengguna.USER_ID" 
				value="viewPengguna.PASSWORD"
				 >
				<span id="SHOWHIDE_PASS_$internalType$viewPengguna.USER_ID" 
				style="cursor: pointer;"
				>
				<font color='blue'
				onClick="showHidePass('SHOWHIDE_PASS_$internalType$viewPengguna.USER_ID','Hide','PASSWORD_$internalType$viewPengguna.USER_ID','PASSWORD2_$internalType$viewPengguna.USER_ID');"
				><u>Hide Password</u></font>
				</span>		
				</div>	
				</td>
			</tr>
			<tr>
				<td valign="top" >
				#if($viewPengguna.USER_ID=="")
				<font color="red" >
				<div id="mando_PASSWORD2_$internalType$viewPengguna.USER_ID">*</div>	
				</font>	
				#end
				</td>			
				<td valign="top" >
				Pengesahan Kata Laluan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<input size="50" type="text" maxlength="12"  id="PASSWORD2_$internalType$viewPengguna.USER_ID" 
				name="PASSWORD2_$internalType$viewPengguna.USER_ID" 
				value="" >				
				</td>
			</tr>
			<tr>
				<td valign="top" >
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Nama
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<input style="text-transform:uppercase;" size="50" type="text" id="USER_NAME_$internalType$viewPengguna.USER_ID" 
				name="USER_NAME_$internalType$viewPengguna.USER_ID" 
				value="$viewPengguna.NAMA_PENUH"  onKeyUp="avoidScriptInjection(this,this.value);">				
				</td>
			</tr>
			<!--<tr>
				<td valign="top" >
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Kategori Pengguna
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >	-->
				<!--#set($radio1="")
				#set($radio2="")
				
				#if($current_role_kjp=="online_kjp")
				#set($radio1="checked")
				#set($radio2="")
				#elseif($current_role_kjp=="online_kjpagensi")
				#set($radio1="")
				#set($radio2="checked")
				#end	-->	
				
			<!--	<input type="radio" $radio1 onClick="sendValueJenisPengguna(this,this.value,'JENIS_PENGGUNA_$internalType$viewPengguna.USER_ID')" id="radio_JENIS_PENGGUNA_$internalType$viewPengguna.USER_ID" name="radio_JENIS_PENGGUNA_$internalType$viewPengguna.USER_ID" value="online_kjp" >Kementerian
				&nbsp;&nbsp;
				<input type="radio" $radio2 onClick="sendValueJenisPengguna(this,this.value,'JENIS_PENGGUNA_$internalType$viewPengguna.USER_ID')" id="radio_JENIS_PENGGUNA_$internalType$viewPengguna.USER_ID" name="radio_JENIS_PENGGUNA_$internalType$viewPengguna.USER_ID" value="online_kjpagensi" >Agensi-->
								
				<!--<input style="text-transform:uppercase;"  type="hidden" id="JENIS_PENGGUNA_$internalType$viewPengguna.USER_ID" 
				name="JENIS_PENGGUNA_$internalType$viewPengguna.USER_ID" 
				value="$current_role_kjp" >	-->
                
                <input style="text-transform:uppercase;"  type="hidden" id="JENIS_PENGGUNA_$internalType$viewPengguna.USER_ID" 
				name="JENIS_PENGGUNA_$internalType$viewPengguna.USER_ID" 
				value="online_kjp" >	
                
							
				<!--</td>
			</tr>-->
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
				
				<input  size="50" type="text" id="EMEL_$internalType$viewPengguna.USER_ID" 
				name="EMEL_$internalType$viewPengguna.USER_ID" 
				value="$viewPengguna.EMEL" >
				</td>
			</tr>
			
			
			
			<tr>
				<td valign="top" >	
				<font color="red" >*</font>			
				</td>			
				<td valign="top" >
				Kementerian
				</td>
				<td valign="top" >
				:
				</td>
				<td >
					<select id="ID_KEMENTERIAN_$internalType$viewPengguna.USER_ID"  
					name="ID_KEMENTERIAN_$internalType$viewPengguna.USER_ID" 
					onChange = "doDivAjaxCall$formname('div_ID_AGENSI$internalType$viewPengguna.USER_ID','showListAgensi','ID_KEMENTERIAN='+$jquery('#ID_KEMENTERIAN_$internalType$viewPengguna.USER_ID').val()+'&internalType=$internalType&USER_ID=$viewPengguna.USER_ID');"
					>	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJKEMENTERIAN)		
							#set ( $selected_ruj = "" )
							#if($viewPengguna.ID_KEMENTERIAN==$ruj.ID)
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
				Agensi
				</td>
				<td valign="top" >
				:
				</td>
				<td >
				<div id="div_ID_AGENSI$internalType$viewPengguna.USER_ID" >
				<select id="ID_AGENSI_$internalType$viewPengguna.USER_ID"  name="ID_AGENSI_$internalType$viewPengguna.USER_ID"
					>	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJAGENSI)		
							#set ( $selected_ruj = "" )
							#if($viewPengguna.ID_AGENSI==$ruj.ID)
							#set ( $selected_ruj = "selected" )
							#end	
							<option $selected_ruj value="$ruj.ID" >
							$ruj.KETERANGAN
							</option>					
							
						#end
					</select>
				</div>
				</td>
			</tr>
			
			<tr id="div_ALAMAT_KEMENTERIAN$internalType$viewPengguna.USER_ID" 
			style="display:none">
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Alamat
				</td>
				<td valign="top" >
				:
				</td>
				<td  >
				#if($viewAlamatKJP.size() > 0)
					#if($viewAlamatKJP.ALAMAT1 != "")
					$viewAlamatKJP.ALAMAT1<br>
					#end
					#if($viewAlamatKJP.ALAMAT2 != "")
						$viewAlamatKJP.ALAMAT2<br>
					#end
					#if($viewAlamatKJP.ALAMAT3 != "")
						$viewAlamatKJP.ALAMAT3<br>
					#end
					#if($viewAlamatKJP.POSKOD != "")
						$viewAlamatKJP.POSKOD &nbsp;						
					#end					
					#if($viewAlamatKJP.NAMA_NEGERI != "")
						$viewAlamatKJP.NAMA_NEGERI<br>
					#end
					
					
					<script type="text/javascript">
					document.getElementById("div_ALAMAT_KEMENTERIAN$internalType$viewPengguna.USER_ID").style.display="";
					</script>
					
				#end
				</td>
			</tr>
			
			
			
			<tr>
				<td valign="top" >
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Tugasan
				</td>
				<td valign="top" >
				:
				</td>
				<td  >
				
					#set($selected_pelulus = "")
					#set($selected_penyemak = "")
					#set($selected_penyedia = "")
					
					#if($viewPengguna.ID_JAWATAN=="4")
						#set($selected_pelulus = "selected")
					#elseif($viewPengguna.ID_JAWATAN=="9")
						#set($selected_penyemak = "selected")
					#elseif($viewPengguna.ID_JAWATAN=="24")
						#set($selected_penyedia = "selected")
					#end
		
		
					<select id="ID_JAWATAN_$internalType$viewPengguna.USER_ID"  name="ID_JAWATAN_$internalType$viewPengguna.USER_ID" >	   
					   		<option value = "" >SILA PILIH</option>
						
							
							<option $selected_pelulus value="4" >PELULUS</option>
							<option $selected_penyemak value="9" >PENYEMAK</option>
							<option $selected_penyedia value="24" >PENYEDIA</option>
							
							
						
					</select>
				</td>
			</tr>
			<!--  
			<tr>
				<td valign="top" >			
				<font color="red" >*</font>	
				</td>			
				<td valign="top" >
				Peranan Utama di dalam Sistem
				</td>
				<td valign="top" >
				:
				</td>
				<td >
				<div id="div_ROLE_MAIN_$internalType$viewPengguna.USER_ID" >
					<select id="ROLE_MAIN_$internalType$viewPengguna.USER_ID"  name="ROLE_MAIN_$internalType$viewPengguna.USER_ID" >	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $listRole )		
							#set ( $selected_ruj = "" )
							#if($viewPengguna.USER_ROLE==$ruj.ID && $ruj.ID !="")
							#set ( $selected_ruj = "selected" )
							#end		
							<option $selected_ruj value="$ruj.ID" >
							#if($ruj.ID == "" && $ruj.KOD!="")
								--------- $ruj.KOD --------- 
							#else
								$ruj.KOD - $ruj.KETERANGAN
							#end
							</option>
						#end
					</select>
					</div>
				</td>
			</tr>
			-->
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Jenis Pengguna
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				ONLINE KJP
				</td>
			</tr>
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Keaktifan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				<select id="FLAG_AKTIF_$internalType$viewPengguna.USER_ID" name="FLAG_AKTIF_$internalType$viewPengguna.USER_ID">
				#set ( $selected_aktif = "" )
				#set ( $selected_tidakaktif = "" )
							#if($viewPengguna.FLAG_AKTIF=="1" || $viewPengguna.FLAG_AKTIF=="")
								#set ( $selected_aktif = "selected" )
							#else
								#set ( $selected_tidakaktif = "selected" )
							#end
				<option value="1" $selected_aktif >AKTIF</option>
				<option value="2" $selected_tidakaktif >TIDAK AKTIF</option>
				</select>
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >				
				</td>
				<td valign="top" >				
				</td>
				<td valign="top" >
				<input type="button" id="BTNSAVE$internalType$viewPengguna.USER_ID" name="BTNSAVE$internalType$viewPengguna.USER_ID" onClick="if(valEditPenggunaKJP('$internalType','$viewPengguna.USER_ID') == true){doDivAjaxCall$formname('div_viewPengguna$internalType$viewPengguna.USER_ID','savePenggunaKJP','USER_ID=$viewPengguna.USER_ID&internalType=$internalType');}" value="Simpan" > 
	   			<input type="button" id="BTNBTL$internalType$viewPengguna.USER_ID" name="BTNBTL$internalType$viewPengguna.USER_ID" onClick="doDivAjaxCall$formname('div_viewPengguna$internalType$viewPengguna.USER_ID','edit_PenggunaKJP','USER_ID=$viewPengguna.USER_ID&internalType=$internalType');" value="Batal" > 
	   			<input type="button" id="BTNCLOSE$internalType$viewPengguna.USER_ID" name="BTNCLOSE$internalType$viewPengguna.USER_ID" onClick="doDivAjaxCall$formname('div_viewPengguna$internalType$viewPengguna.USER_ID','close_PenggunaKJP','USER_ID=$viewPengguna.USER_ID&internalType=$internalType');" value="Tutup" > 
	   			
	   			</td>
			</tr>
		
		
		</table>
	</fieldset>
	<br>
</td>		
</tr>
<script>
//pwdStrength();
document.getElementById('PASSWORD_$internalType$viewPengguna.USER_ID').value = null;
</script>