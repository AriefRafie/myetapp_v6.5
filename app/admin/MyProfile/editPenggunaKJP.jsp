
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

<fieldset>
	<legend>
	
	#if($viewPengguna.USER_ID != "")
	KEMASKINI PROFIL PENGGUNA : $viewPengguna.NAMA_PENUH
	#else
	Kemasukan Maklumat Pengguna
	#end
	</legend>
		<table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
			</tr>
			
			<tr>
				<td valign="top" >&nbsp;</td>			
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
                <input type="hidden" id="ps_call$internalType$viewPengguna.USER_ID" name="ps_call$internalType$viewPengguna.USER_ID" value="no">
               
				<input size="50" maxlength="12" type="password" onKeyUp="setPasswordClass(this,this.value,'$internalType','$viewPengguna.USER_ID');"	
                 id="PASSWORD_$internalType$viewPengguna.USER_ID" 
				name="PASSWORD_$internalType$viewPengguna.USER_ID" 
				value="viewPengguna.PASSWORD"
				 >				
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
				<input size="50" type="password" id="PASSWORD2_$internalType$viewPengguna.USER_ID" 
				name="PASSWORD2_$internalType$viewPengguna.USER_ID" 
				value="" >				
				</td>
			</tr>
            
               <!--penambahan admin 23/1/2017-->
                <tr><td></td></tr>
                 <tr><td></td></tr>
                  <tr><td></td></tr>
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tarikh Luput Kata laluan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.DAYS_AFTERCHANGEPASS
				</td>
			</tr>
			
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tempoh Sah
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.TEMPOH_SAH Hari
                <br /> <font color="red">Nota : Tempoh SAH Kata Laluan adalah selama 3 BULAN sahaja. Auto reset apabila Kata Laluan dikemaskini.</font>
				</td>
			</tr>
            
             <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Log Masuk terakir
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.WAKTU_AKHIR_LOGIN
				</td>
			</tr>
			<!--tamat-->
              <tr><td></td></tr>
                <tr><td></td></tr>
                 <tr><td></td></tr>
            
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
			<tr>
				<td valign="top" >&nbsp;</td>			
			  <td valign="top" >
				Kategori Pengguna
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >				
				#if($current_role_kjp=="online_kjp") Kementerian
				#elseif($current_role_kjp=="online_kjpagensi")Agensi
                #else -
				#end		
				
			<!--	<input type="radio" $radio1 onClick="sendValueJenisPengguna(this,this.value,'JENIS_PENGGUNA_$internalType$viewPengguna.USER_ID')" id="radio_JENIS_PENGGUNA_$internalType$viewPengguna.USER_ID" name="radio_JENIS_PENGGUNA_$internalType$viewPengguna.USER_ID" value="online_kjp" >Kementerian
				&nbsp;&nbsp;
				<input type="radio" $radio2 onClick="sendValueJenisPengguna(this,this.value,'JENIS_PENGGUNA_$internalType$viewPengguna.USER_ID')" id="radio_JENIS_PENGGUNA_$internalType$viewPengguna.USER_ID" name="radio_JENIS_PENGGUNA_$internalType$viewPengguna.USER_ID" value="online_kjpagensi" >Agensi-->
								
				<input style="text-transform:uppercase;"  type="hidden" id="JENIS_PENGGUNA_$internalType$viewPengguna.USER_ID" 
				name="JENIS_PENGGUNA_$internalType$viewPengguna.USER_ID" 
				value="$current_role_kjp" >	
							
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
				
				<input  size="50" type="text" id="EMEL_$internalType$viewPengguna.USER_ID" 
				name="EMEL_$internalType$viewPengguna.USER_ID" 
				value="$viewPengguna.EMEL" >
				</td>
			</tr>
		
			
			<tr>
				<td valign="top" >&nbsp;</td>			
				<td valign="top" >
				Kementerian
				</td>
				<td valign="top" >
				:
				</td>
				<td >
                $viewPengguna.NAMA_KEMENTERIAN
                <input type="hidden" id="ID_KEMENTERIAN_$internalType$viewPengguna.USER_ID"  name="ID_KEMENTERIAN_$internalType$viewPengguna.USER_ID" value="$viewPengguna.ID_KEMENTERIAN" >	   
				</td>
			</tr>
			
			<tr>
				<td valign="top" >&nbsp;</td>			
				<td valign="top" >
				Agensi
				</td>
				<td valign="top" >
				:
				</td>
				<td >$viewPengguna.NAMA_AGENSI
				<div id="div_ID_AGENSI$internalType$viewPengguna.USER_ID" >
				<input type="hidden" id="ID_AGENSI_$internalType$viewPengguna.USER_ID"  name="ID_AGENSI_$internalType$viewPengguna.USER_ID" value="$viewPengguna.ID_AGENSI"  /> 
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
				<td valign="top" >&nbsp;</td>			
			  <td valign="top" >
				Tugasan
				</td>
				<td valign="top" >
				:
				</td>
				<td  >
					#if($viewPengguna.ID_JAWATAN=="4")
						PELULUS
					#elseif($viewPengguna.ID_JAWATAN=="9")
						PENYEMAK
					#elseif($viewPengguna.ID_JAWATAN=="24")
						PENYEDIA
                    #else -
					#end
		
		
					<input type="hidden" id="ID_JAWATAN_$internalType$viewPengguna.USER_ID"  name="ID_JAWATAN_$internalType$viewPengguna.USER_ID" value="$viewPengguna.ID_JAWATAN" />
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
			<!--<tr>
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
			</tr>-->
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >				
				</td>
				<td valign="top" >				
				</td>
				<td valign="top" >
                <input type="button" id="BTNSAVE$internalType$viewPengguna.USER_ID" name="BTNSAVE$internalType$viewPengguna.USER_ID" onClick="if(valEditPenggunaKJP('$internalType','$viewPengguna.USER_ID') == true){doDivAjaxCall$formname('div_viewPengguna$internalType$viewPengguna.USER_ID','savePenggunaKJP','USER_ID=$viewPengguna.USER_ID&internalType=$internalType');}" value="Simpan" > 
                
            <!--    <input type="button" id="BTNSAVE$internalType$viewPengguna.USER_ID" name="BTNSAVE$internalType$viewPengguna.USER_ID" onClick="submitRegister('$viewPengguna.USER_ID');" value="Simpan" > 
             -->   
	   			<input type="button" id="BTNBTL$internalType$viewPengguna.USER_ID" name="BTNBTL$internalType$viewPengguna.USER_ID" onClick="doDivAjaxCall$formname('div_viewPengguna$internalType$viewPengguna.USER_ID','edit_PenggunaKJP','USER_ID=$viewPengguna.USER_ID&internalType=$internalType');" value="Batal" > 
	   			</td>
			</tr>
		
		
		</table>
	</fieldset>
	<br>

<script>
document.getElementById('PASSWORD_$internalType$viewPengguna.USER_ID').value = null;   
</script>