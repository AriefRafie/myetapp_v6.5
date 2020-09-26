

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
				<input name="NOKP1_$viewPengguna.USER_ID" type="$login_kp1" 
				id="NOKP1_$viewPengguna.USER_ID" style="width:50px;" 
				onKeyUp="javascript:validateIC_V3(event,this,this.value,'NOKP2_$viewPengguna.USER_ID');copyValueIC(event,this,this.value,'NO_PENGENALAN1_$viewPengguna.USER_ID',$jquery('#KATEGORI_$viewPengguna.USER_ID').val());setCheckLogin('','$viewPengguna.USER_ID')" 
				value="" size="7" maxlength="6"  
				onBlur="getAgeByIC_V3(this,this.value,'UMUR_$viewPengguna.USER_ID');getDOBByIC_V3(this,this.value,'TARIKH_LAHIR_$viewPengguna.USER_ID','span_NOKP1_$viewPengguna.USER_ID');"  />
               
               	<span id="span_NOKP1_$viewPengguna.USER_ID">
                <input type="hidden" id="CHECK_NOKP1_$viewPengguna.USER_ID" 
				name="CHECK_NOKP1_$viewPengguna.USER_ID" value="true" >
                </span> 
                                     &nbsp; 
                <input name="NOKP2_$viewPengguna.USER_ID" type="$login_kp2" 
                id="NOKP2_$viewPengguna.USER_ID" style="width:20px;" 
                onKeyUp="javascript:validateIC_V3(event,this,this.value,'NOKP3_$viewPengguna.USER_ID');copyValueIC(event,this,this.value,'NO_PENGENALAN2_$viewPengguna.USER_ID',$jquery('#KATEGORI_$viewPengguna.USER_ID').val());setCheckLogin('','$viewPengguna.USER_ID')" 
                value="" size="1" maxlength="2"  />
                                     &nbsp; 
                <input name="NOKP3_$viewPengguna.USER_ID" type="$login_kp3" 
                id="NOKP3_$viewPengguna.USER_ID" style="width:40px;" 
                onKeyUp="javascript:validateIC_V3(event,this,this.value,'NOKP3_$viewPengguna.USER_ID');copyValueIC(event,this,this.value,'NO_PENGENALAN3_$viewPengguna.USER_ID',$jquery('#KATEGORI_$viewPengguna.USER_ID').val());setCheckLogin('','$viewPengguna.USER_ID')" 
                onBlur="getJantinaByIC_V3(this,this.value,'JANTINA_$viewPengguna.USER_ID');"  
                value="" size="5" maxlength="4" />     
             
             	<!-- close username by IC -->
				
			    
				<input size="50" type="$login_hidden" id="USER_LOGIN_$viewPengguna.USER_ID" 
				name="USER_LOGIN_$viewPengguna.USER_ID" 
				value="$viewPengguna.USER_LOGIN" 
				onKeyUp = "removeSpaces(this, this.value); if($jquery('#USER_LOGIN_$viewPengguna.USER_ID').val()!=''){doDivAjaxCall$formname('div_CHECK_USER_LOGIN_$viewPengguna.USER_ID','checkUSER_LOGIN','internalType=&USER_ID=$viewPengguna.USER_ID&USER_LOGIN='+$jquery('#USER_LOGIN_$viewPengguna.USER_ID').val());}"
				>
				<div id="div_CHECK_USER_LOGIN_$viewPengguna.USER_ID" >
				<input type="hidden" id="CHECK_USER_LOGIN_$viewPengguna.USER_ID" 
				name="CHECK_USER_LOGIN_$viewPengguna.USER_ID" value="true" >
				<input type="hidden" id="GET_USER_ID_EXIST_$viewPengguna.USER_ID" 
				name="GET_USER_ID_EXIST_$viewPengguna.USER_ID" value="">
				</div>
				
				<script type="text/javascript">
				if('$viewPengguna.USER_LOGIN'!="")
				{
					$jquery(document).ready(function () 
					{
						//doDivAjaxCall$formname('div_CHECK_USER_LOGIN_$viewPengguna.USER_ID','checkUSER_LOGIN','internalType=&USER_ID=$viewPengguna.USER_ID&USER_LOGIN=$viewPengguna.USER_LOGIN');
					});
				}
				</script>
				
				</td>
				
			</tr>
			<tr>
				<td valign="top" >
				#if($viewPengguna.USER_ID=="")
				<font color="red" >
				<div id="mando_PASSWORD_$viewPengguna.USER_ID">*</div>
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
                <input type="hidden" id="ps_call$viewPengguna.USER_ID" name="ps_call$viewPengguna.USER_ID" value="no">
				<input size="50" maxlength="12" type="password" onKeyUp="setPasswordClass(this,this.value,'','$viewPengguna.USER_ID');"	 id="PASSWORD_$viewPengguna.USER_ID" 
				name="PASSWORD_$viewPengguna.USER_ID" 
				value="viewPengguna.PASSWORD"
				 >				
				</td>
			</tr>
			<tr>
				<td valign="top" >
				#if($viewPengguna.USER_ID=="")
				<font color="red" >
				<div id="mando_PASSWORD2_$viewPengguna.USER_ID">*</div>
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
				<input size="50" type="password" id="PASSWORD2_$viewPengguna.USER_ID" 
				name="PASSWORD2_$viewPengguna.USER_ID" 
				value="" >				
				</td>
			</tr>
			    <!--penambahan admin 23/1/2017-->
                <tr><td></td></tr>
                 <tr><td></td></tr>
                  <tr><td></td></tr>
           <!-- <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tarikh Luput Kata laluan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.DATE_DAYS_AFTERCHANGEPASS
				</td>
			</tr>-->
			
           <!-- <tr>
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
			</tr>-->
            
             <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Log Masuk terakhir
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.WAKTU_AKHIR_LOGIN
				</td>
			</tr>
			<!--tamat-->
			<tr>
				<td valign="top" >&nbsp;</td>			
				<td valign="top" >
				Kategori Pengguna			
				</td>
				<td valign="top" >
				:
				</td>
				
				<td valign="top" >
                $viewPengguna.KATEGORI
				<input type="hidden" id="KATEGORI_$viewPengguna.USER_ID" name="KATEGORI_$viewPengguna.USER_ID" value="$viewPengguna.KATEGORI">
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
				<input style="text-transform:uppercase;" size="50" type="text" id="USER_NAME_$viewPengguna.USER_ID" 
				name="USER_NAME_$viewPengguna.USER_ID" 
				value="$viewPengguna.NAMA_PENUH" onKeyUp="avoidScriptInjection(this,this.value);" >				
				</td>
			</tr>
			
			
			
			<tr id="div_NO_PENGENALAN$viewPengguna.USER_ID">
				<td valign="top" >&nbsp;</td>			
				<td valign="top" >
				#if($viewPengguna.KATEGORI=="INDIVIDU" || $viewPengguna.KATEGORI=="")
				MyID	
				#else
				MyCOID
				#end							
				</td>
				<td valign="top" >
				:
				</td>
				<td>
				#if($viewPengguna.KATEGORI=="INDIVIDU" || $viewPengguna.KATEGORI=="")
                $viewPengguna.NO_PENGENALAN
				#else
                $viewPengguna.NO_PENGENALAN
                #end
                
                <input name="NO_PENGENALAN_$viewPengguna.USER_ID" type="hidden" id="NO_PENGENALAN_$viewPengguna.USER_ID" size="50" value="$viewPengguna.NO_PENGENALAN"  />
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
				
				<input  size="50" type="text" id="EMEL_$viewPengguna.USER_ID" 
				name="EMEL_$viewPengguna.USER_ID" 
				value="$viewPengguna.EMEL" >
				</td>
			</tr>
			<tr>
				<td valign="top" >
				</td>			
				<td valign="top" >
				Jenis Pengguna 
				</td>
				<td valign="top" >
				:
				</td>
				<td  >
				ONLINE
				</td>
			</tr>
			
			
			
			
			<tr>
				<td valign="top" >	
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Alamat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<input style="text-transform:uppercase;" size="50" type="text" id="ALAMAT1_$viewPengguna.USER_ID" 
				name="ALAMAT1_$viewPengguna.USER_ID" 
				value="$viewPengguna.ALAMAT1" >	
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
				<input style="text-transform:uppercase;" size="50" type="text" id="ALAMAT2_$viewPengguna.USER_ID" 
				name="ALAMAT2_$viewPengguna.USER_ID" 
				value="$viewPengguna.ALAMAT2" >	
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
				<input style="text-transform:uppercase;" size="50" type="text" id="ALAMAT3_$viewPengguna.USER_ID" 
				name="ALAMAT3_$viewPengguna.USER_ID" 
				value="$viewPengguna.ALAMAT3" >	
				</td>
			</tr>
			
			
			<tr>
				<td valign="top" >
				<font color="red" >*</font>					
				</td>			
				<td valign="top" >
				Poskod
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >			
				
				<input size="10" type="text" id="POSKOD_$viewPengguna.USER_ID" 
				name="POSKOD_$viewPengguna.USER_ID" 
				value="$viewPengguna.POSKOD"  maxlength="5" 
				onKeyUp = "RemoveNonNumeric_V3(this,this.value)"
				onBlur = "checkMaxLength_V3(this,this.value,5,'span_ERR_POSKOD_$viewPengguna.USER_ID');"
				>
				<span id="span_ERR_POSKOD_$viewPengguna.USER_ID">
				<input type="hidden" id="CHECK_POSKOD_$viewPengguna.USER_ID" 
				name="CHECK_POSKOD_$viewPengguna.USER_ID" value="true" >
				</span>
				
					
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
				<td valign="top" >
				<select id="ID_NEGERI_$viewPengguna.USER_ID"  name="ID_NEGERI_$viewPengguna.USER_ID"
					onChange = "doDivAjaxCall$formname('div_ID_BANDAR$viewPengguna.USER_ID','showListBandar','ID_NEGERI='+$jquery('#ID_NEGERI_$viewPengguna.USER_ID').val()+'&internalType=&USER_ID=$viewPengguna.USER_ID');"
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
				Bandar
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" id="div_ID_BANDAR$viewPengguna.USER_ID" >
				<select id="ID_BANDAR_$viewPengguna.USER_ID"  name="ID_BANDAR_$viewPengguna.USER_ID" >	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJBANDAR )		
							#set ( $selected_ruj = "" )
							#if($viewPengguna.ID_BANDAR==$ruj.ID)
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
				</td>			
				<td valign="top" >
				Umur
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<input size="5" maxlength="2" type="text" id="UMUR_$viewPengguna.USER_ID" 
				name="UMUR_$viewPengguna.USER_ID" 
				value="$viewPengguna.UMUR" 
				onKeyUp = "RemoveNonNumeric_V3(this,this.value)"
				>	
				</td>
			</tr>
			
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Jantina
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				<select id="JANTINA_$viewPengguna.USER_ID" name="JANTINA_$viewPengguna.USER_ID">
				<option value = "" >SILA PILIH</option>
				#set ( $selected_lelaki = "" )
				#set ( $selected_perempuan = "" )
							#if($viewPengguna.JANTINA=="L")
								#set ( $selected_lelaki = "selected" )
							#elseif($viewPengguna.JANTINA=="P")
								#set ( $selected_perempuan = "selected" )
							#end
				<option value="L" $selected_lelaki >LELAKI</option>
				<option value="P" $selected_perempuan >PEREMPUAN</option>
				</select>
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Taraf Perkahwinan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				<select id="TARAF_PERKAHWINAN_$viewPengguna.USER_ID" name="TARAF_PERKAHWINAN_$viewPengguna.USER_ID">
				<option value = "" >SILA PILIH</option>
				#set ( $selected_bujang = "" )
				#set ( $selected_kawin = "" )
							#if($viewPengguna.TARAF_PERKAHWINAN=="B")
								#set ( $selected_bujang = "selected" )
							#elseif($viewPengguna.TARAF_PERKAHWINAN=="K")
								#set ( $selected_kawin = "selected" )
							#end
				<option value="B" $selected_bujang >BUJANG</option>
				<option value="K" $selected_kawin >BERKAHWIN</option>
				</select>
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tarikh lahir
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<input name="TARIKH_LAHIR_$viewPengguna.USER_ID" type="text" id="TARIKH_LAHIR_$viewPengguna.USER_ID" style="text-transform:uppercase;" value="$viewPengguna.TARIKH_LAHIR" size="15" maxlength="15"  />
       <a href="javascript:displayDatePicker('TARIKH_LAHIR_$viewPengguna.USER_ID',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
      
				</td>
			</tr>
			
			
			<tr>
				<td valign="top" >
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				No. HP
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<input onKeyUp = "RemoveNonNumeric_V3(this,this.value)" size="50" type="text" id="NO_HP_$viewPengguna.USER_ID" 
				name="NO_HP_$viewPengguna.USER_ID" 
				value="$viewPengguna.NO_HP" >	
				</td>
			</tr>
			
			<tr>
				<td valign="top" >
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				No. Tel
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<input onKeyUp = "RemoveNonNumeric_V3(this,this.value)" size="50" type="text" id="NO_TEL_$viewPengguna.USER_ID" 
				name="NO_TEL_$viewPengguna.USER_ID" 
				value="$viewPengguna.NO_TEL" >	
				</td>
			</tr>
			
			<tr>
				<td valign="top" >			
				</td>			
				<td valign="top" >
				No. Fax
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<input onKeyUp = "RemoveNonNumeric_V3(this,this.value)" size="50" type="text" id="NO_FAX_$viewPengguna.USER_ID" 
				name="NO_FAX_$viewPengguna.USER_ID" 
				value="$viewPengguna.NO_FAX" >	
				</td>
			</tr>
			<input type="hidden" id="ROLE_MAIN_$viewPengguna.USER_ID" name="ROLE_MAIN_$viewPengguna.USER_ID" value="$viewPengguna.USER_ROLE">
            
			 #if ($USER_LOGIN_ROLE.equals("adminekptg") || $USER_LOGIN_ROLE.equals("adminstate"))
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
				<div id="div_ROLE_MAIN_$viewPengguna.USER_ID" >
					<select id="ROLE_MAIN_$viewPengguna.USER_ID"  name="ROLE_MAIN_$viewPengguna.USER_ID" >	   
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
			#end
            
			<tr>
				<td valign="top" >	
				<!--<font color="red" >*</font>	-->			
				</td>			
				<td valign="top" >
				<!--Keaktifan-->
				</td>
				<td valign="top" >
				<!--:-->
				</td>
				<td valign="top" >
				
				<input type="hidden" id="FLAG_AKTIF_$viewPengguna.USER_ID" name="FLAG_AKTIF_$viewPengguna.USER_ID" value="$viewPengguna.FLAG_AKTIF">
				
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
                #if ($USER_LOGIN_ROLE.equals("adminekptg") || $USER_LOGIN_ROLE.equals("adminstate"))
				<input type="button" id="BTNSAVE$viewPengguna.USER_ID" name="BTNSAVE$viewPengguna.USER_ID" onClick="if(valEditPenggunaOnline('','$viewPengguna.USER_ID') == true){doDivAjaxCall$formname('div_viewPengguna$viewPengguna.USER_ID','savePenggunaOnline','USER_ID=$viewPengguna.USER_ID&internalType=');}" value="Simpan" > 
                <input type="button" id="BTNBTL$viewPengguna.USER_ID" name="BTNBTL$viewPengguna.USER_ID" onClick="doDivAjaxCall$formname('div_viewPengguna$viewPengguna.USER_ID','edit_PenggunaOnline','USER_ID=$viewPengguna.USER_ID&internalType=');" value="Batal" > 
	   			#else 
                
                <input type="button" id="BTNSAVE$viewPengguna.USER_ID" name="BTNSAVE$viewPengguna.USER_ID" onClick="if(valEditPenggunaOnline('','$viewPengguna.USER_ID') == true){doDivAjaxCall$formname('div_viewPengguna$viewPengguna.USER_ID','savePenggunaOnline','USER_ID=$viewPengguna.USER_ID&internalType=');}" value="Simpan" > 
                <input type="button" id="BTNBTL$viewPengguna.USER_ID" name="BTNBTL$viewPengguna.USER_ID" onClick="doDivAjaxCall$formname('div_viewPengguna$viewPengguna.USER_ID','edit_PenggunaOnline','USER_ID=$viewPengguna.USER_ID&internalType=');" value="Batal" > 
                #end
                
				
	   			
	   			</td>
			</tr>
		
		
		</table>
	</fieldset>
	<br>

<script>
document.getElementById('PASSWORD_$viewPengguna.USER_ID').value = null;
</script>

#if ($USER_LOGIN_ROLE.equals("online")) 
<script>
        var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
</script>

#end