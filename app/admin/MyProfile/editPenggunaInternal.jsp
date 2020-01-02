
<script>
if( $jquery('#'+'div_rowPengguna$viewPengguna.USER_ID').length ) 
{
	window.scrollTo(0, $jquery('#'+'div_rowPengguna$viewPengguna.USER_ID').offset().top);
}
else
{
	if( $jquery('#'+'div_viewPengguna$viewPengguna.USER_ID').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_viewPengguna$viewPengguna.USER_ID').offset().top);
	}
	
}
</script>

<tr id="div_viewPengguna$viewPengguna.USER_ID">
<td align="left" valign="top" colspan="14">
	<fieldset>
	<legend>
	
	#if($viewPengguna.USER_ID != "")
	KEMASKINI PROFIL PENGGUNA : $viewPengguna.USER_NAME
	#else
	Kemasukan Maklumat Pengguna
	#end
	</legend>
		<table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
			</tr>
			
			<tr>
				<td valign="top" ></td>			
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
				onKeyUp="javascript:validateIC_V3(event,this,this.value,'NOKP2_$viewPengguna.USER_ID');setCheckLogin('','$viewPengguna.USER_ID');getDOBByIC_V3(this,this.value,'TARIKH_LAHIR_$viewPengguna.USER_ID','span_NOKP1_$viewPengguna.USER_ID');" 
				value="" size="7" maxlength="6"  
				
				/>
               
               	<span id="span_NOKP1_$viewPengguna.USER_ID">
                <input type="hidden" id="CHECK_NOKP1_$viewPengguna.USER_ID" 
				name="CHECK_NOKP1_$viewPengguna.USER_ID" value="true" >
                </span> 
                                     &nbsp; 
                <input name="NOKP2_$viewPengguna.USER_ID" type="$login_kp2" 
                id="NOKP2_$viewPengguna.USER_ID" style="width:20px;" 
                onKeyUp="javascript:validateIC_V3(event,this,this.value,'NOKP3_$viewPengguna.USER_ID');setCheckLogin('','$viewPengguna.USER_ID')" 
                value="" size="1" maxlength="2"  />
                                      &nbsp; 
                                      
                <input name="NOKP3_$viewPengguna.USER_ID" type="$login_kp3" 
                id="NOKP3_$viewPengguna.USER_ID" style="width:40px;" 
                onKeyUp="javascript:validateIC_V3(event,this,this.value,'NOKP3_$viewPengguna.USER_ID');setCheckLogin('','$viewPengguna.USER_ID')" 
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
				name="GET_USER_ID_EXIST_$viewPengguna.USER_ID" value="" >
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
                <!--<input type="hidden" id="ps_call$viewPengguna.USER_ID" name="ps_call$viewPengguna.USER_ID" value="no"> 
				<input size="50" maxlength="12" type="password" onKeyUp="setPasswordClass(this,this.value,'','$viewPengguna.USER_ID');" id="PASSWORD_$viewPengguna.USER_ID" 
				name="PASSWORD_$viewPengguna.USER_ID" 
				value="viewPengguna.PASSWORD"
				 > <font color="red">Nota : Kata laluan yang TERBAIK adalah gabungan abjad,nombor dan simbol. Minimum 8 aksara.</font>		-->	
                 <div style="width:50%">
				<input type="hidden" id="ps_call$viewPengguna.USER_ID" name="ps_call$viewPengguna.USER_ID" value="no">
				<input size="50" maxlength="12" type="text" 
				onKeyUp="setPasswordClass(this,this.value,'','$viewPengguna.USER_ID');"	
				id="PASSWORD_$viewPengguna.USER_ID" 
				name="PASSWORD_$viewPengguna.USER_ID" 
				value="$viewPengguna.PASSWORD"
				 >	
				<span id="SHOWHIDE_PASS_$viewPengguna.USER_ID" 
				style="cursor: pointer;"
				>
				<font color='blue'
				onClick="showHidePass('SHOWHIDE_PASS_$viewPengguna.USER_ID','Hide','PASSWORD_$viewPengguna.USER_ID','PASSWORD2_$viewPengguna.USER_ID');"
				><u>Hide Password</u></font>
				</span>
				</div>	
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
              <!--penambahan admin 201/1/2017-->
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
                <br /> Nota : Tempoh SAH Kata Laluan adalah selama 3 BULAN sahaja.
				</td>
			</tr>
            
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
				$viewPengguna.LAST_LOGIN
				</td>
			</tr>
			<!--tamat-->
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
				<input style="text-transform:uppercase;" size="50" type="text" id="USER_NAME_$viewPengguna.USER_ID" 
				name="USER_NAME_$viewPengguna.USER_ID" 
				value="$viewPengguna.USER_NAME" >				
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
                <br /> Nota :  Emel rasmi Jabatan seperti yang tertera dalam Portal  JKPTG > Direktori
				</td>
			</tr>
			<tr>
				<td valign="top" >
							
				</td>			
				<td valign="top" >
				Jawatan
				</td>
				<td valign="top" >
				:
				</td>
				<td  >
					<select id="ID_JAWATAN_$viewPengguna.USER_ID"  name="ID_JAWATAN_$viewPengguna.USER_ID" >	   
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
				</td>			
				<td valign="top" >
				Agama
				</td>
				<td valign="top" >
				:
				</td>
				<td >
					<select id="ID_AGAMA_$viewPengguna.USER_ID"  name="ID_AGAMA_$viewPengguna.USER_ID" >	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJAGAMA )		
							#set ( $selected_ruj = "" )
							#if($viewPengguna.ID_AGAMA==$ruj.ID)
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
				Bangsa
				</td>
				<td valign="top" >
				:
				</td>
				<td >
					<select id="ID_BANGSA_$viewPengguna.USER_ID"  name="ID_BANGSA_$viewPengguna.USER_ID" >	   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJBANGSA )		
							#set ( $selected_ruj = "" )
							#if($viewPengguna.ID_BANGSA==$ruj.ID)
							#set ( $selected_ruj = "selected" )
							#end		
							<option $selected_ruj value="$ruj.ID" >
							$ruj.KETERANGAN
							</option>
						#end
					</select>
				</td>
			</tr>
            
            #if ($USER_LOGIN_ROLE.equals("adminekptg") || $USER_LOGIN_ROLE.equals("adminstate"))
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
					<select id="ID_SEKSYEN_$viewPengguna.USER_ID"  
					name="ID_SEKSYEN_$viewPengguna.USER_ID" 
					onChange = "doDivAjaxCall$formname('div_ID_PEJABATJKPTG$viewPengguna.USER_ID','showListPejabat','ID_SEKSYEN='+$jquery('#ID_SEKSYEN_$viewPengguna.USER_ID').val()+'&ID_NEGERI='+$jquery('#ID_NEGERI_$viewPengguna.USER_ID').val()+'&internalType=&USER_ID=$viewPengguna.USER_ID');"
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
					<select id="ID_NEGERI_$viewPengguna.USER_ID"  name="ID_NEGERI_$viewPengguna.USER_ID"
					onChange = "doDivAjaxCall$formname('div_ID_PEJABATJKPTG$viewPengguna.USER_ID','showListPejabat','ID_SEKSYEN='+$jquery('#ID_SEKSYEN_$viewPengguna.USER_ID').val()+'&ID_NEGERI='+$jquery('#ID_NEGERI_$viewPengguna.USER_ID').val()+'&internalType=&USER_ID=$viewPengguna.USER_ID');"
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
			<!--tambah no tel-->
           <!-- <tr>
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
				
				<input  size="50" type="text" id="EMEL_$viewPengguna.USER_ID" 
				name="NO_TEL_PEJ_$viewPengguna.USER_ID" 
				value="$viewPengguna.NO_TEL_PEJ" >
				</td>
			</tr>-->
            <!--tamat-->
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
				<td id="div_ID_PEJABATJKPTG$viewPengguna.USER_ID" >
					<select id="ID_PEJABATJKPTG_$viewPengguna.USER_ID"  name="ID_PEJABATJKPTG_$viewPengguna.USER_ID" 
					onChange = "doDivAjaxCall$formname('div_ALAMAT_PEJABATJKPTG$viewPengguna.USER_ID','showDisplayPejabat','ID_PEJABATJKPTG='+$jquery('#ID_PEJABATJKPTG_$viewPengguna.USER_ID').val()+'&internalType=&USER_ID=$viewPengguna.USER_ID');"
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
			
			<tr id="div_ALAMAT_PEJABATJKPTG$viewPengguna.USER_ID" style="display:none">
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
					document.getElementById("div_ALAMAT_PEJABATJKPTG$viewPengguna.USER_ID").style.display="";
					</script>
					
				#end
				</td>
			</tr>
		#if($viewPengguna.ID_SEKSYEN == "2" )
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Daerah Urusan / Jagaan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" id="div_displayDaerahJagaan" >
				<script>	
				  $jquery(document).ready(function () {
					  doDivAjaxCall$formname('div_displayDaerahJagaan','showDisplayDaerahJagaan','ID_PEJABATJKPTG=$viewPengguna.ID_PEJABATJKPTG&ID_JENISPEJABAT=22');			 	  
				  });		
				</script>
				</td>
			</tr>
			#end
		#else 
        
        <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Bahagian
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.NAMA_BAHAGIAN
                <input type="hidden" id="ID_SEKSYEN_$viewPengguna.USER_ID"  name="ID_SEKSYEN_$viewPengguna.USER_ID" value="$viewPengguna.ID_SEKSYEN"/>
				</td>
			</tr>
            
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Unit / Pejabat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.NAMA_PEJABAT
                <input type="hidden" id="ID_PEJABATJKPTG_$viewPengguna.USER_ID"  name="ID_PEJABATJKPTG_$viewPengguna.USER_ID"   value="$viewPengguna.ID_PEJABATJKPTG"/>
				</td>
			</tr>
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Alamat Pejabat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.ALAMAT1_PEJ
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
				$viewPengguna.ALAMAT2_PEJ
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
				$viewPengguna.ALAMAT3_PEJ
				</td>
			</tr>
		
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Poskod
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.POSKOD_PEJ
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Bandar
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.BANDAR_PEJ
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Negeri
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.NEGERI_PEJ 
                <input type="hidden" id="ID_NEGERI_$viewPengguna.USER_ID"  name="ID_NEGERI_$viewPengguna.USER_ID"  value="$viewPengguna.ID_NEGERI"/>
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				No. Tel
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.NO_TEL_PEJ
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
				$viewPengguna.NO_FAX_PEJ
				</td>
			</tr>
            
            #if($viewPengguna.ID_SEKSYEN == "2" )
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Daerah Urusan / Jagaan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" id="div_displayDaerahJagaan" >
				<script>	
				  $jquery(document).ready(function () {
					  doDivAjaxCall$formname('div_displayDaerahJagaan','showDisplayDaerahJagaan','ID_PEJABATJKPTG=$viewPengguna.ID_PEJABATJKPTG&ID_JENISPEJABAT=22');			 	  
				  });		
				</script>
				</td>
			</tr>
			#end
            
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Peranan Utama di dalam Sistem
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                #if ($USER_LOGIN_ROLE.equals("online_kjp"))
                -
                #else
				$viewPengguna.ROLE_MAIN_DETAILS
                <input type="hidden" id="ROLE_MAIN_$viewPengguna.USER_ID"  name="ROLE_MAIN_$viewPengguna.USER_ID"  value="$viewPengguna.ROLE_MAIN" />
				#end
                </td>
			</tr>
             #if ($USER_LOGIN_ROLE.equals("adminekptg") || $USER_LOGIN_ROLE.equals("adminstate")) 	
            #if($listAdditionalRoleByUserLogin.size()>0)
			<tr id="div_displayaddrole$viewPengguna.USER_ID">
							<td valign="top" >				
							</td>			
							<td valign="top" >
							Peranan Tambahan (Pilihan)
							</td>
							<td valign="top" >
							:
							</td>
							<td>
							<div id="div_listaddrole$viewPengguna.USER_ID">
							<table width="100%" border="0"  >
								<tr>
											<td align="right" width="1%"  valign="top" >
											</td>
											<td align="center"  width="1%"  valign="top" ></td>
											<td align="left"  width="94%"  valign="top" ></td>
											</tr>
									#foreach($ar in $listAdditionalRoleByUserLogin)
									
									#if($ar.LAYER == "1")
									
											<tr>
											<td colspan="3"><b>$ar.KOD</b></td>
											</tr>
									#end
									#if($ar.LAYER == "2")
									
											<tr>
											<td align="right"   valign="top" >
											$ar.NEWBIL.
											</td>
											<td align="center"   valign="top" ></td>
											<td align="left"   valign="top" >$ar.KETERANGAN</td>
											</tr>
									#end
									
									
											
									#end

                                    </table>
                                    </div>
                            </td></tr>				
			
            #end
            <tr colspan="14">
				<td valign="top" >				
				</td>			
				<td valign="top" >				
				</td>
				<td valign="top" >				
				</td>
				<td valign="top" colspan="14">
				<input type="button" id="BTNEDIT$viewPengguna.USER_ID" name="BTNEDIT$viewPengguna.USER_ID"   onClick="doDivAjaxCall$formname('div_viewPengguna$viewPengguna.USER_ID','edit_PenggunaInternal','USER_ID=$viewPengguna.USER_ID&internalType=');" value="Kemaskini" > 
	   			</td>
	   			</tr>
            #end
            #end
			
			<!--<tr>
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
							#if($viewPengguna.ROLE_MAIN==$ruj.ID && $ruj.ID !="")
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
				
				<select id="FLAG_AKTIF_$viewPengguna.USER_ID" name="FLAG_AKTIF_$viewPengguna.USER_ID">
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
<tr><td></td></tr>
<tr><td></td></tr>
<tr>

	<td align="left" valign="top" colspan="14">
PERHATIAN : Sila hubungi pegawai IT terlibat di Jabatan anda jika terdapat sebarang perubahan bagi medan yang tidak boleh dikemaskini pada skrin ini.
</td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
			<tr>
				<td valign="top" >			
				</td>			
				<td valign="top" >				
				</td>
				<td valign="top" >				
				</td>
				<td valign="top" >
               
				<input type="button" id="BTNSAVE$viewPengguna.USER_ID" name="BTNSAVE$viewPengguna.USER_ID" onClick="if(valEditPenggunaInternal('','$viewPengguna.USER_ID') == true){doDivAjaxCall$formname('div_viewPengguna$viewPengguna.USER_ID','savePenggunaInternal','USER_ID=$viewPengguna.USER_ID&internalType=');}" value="Simpan" > 
	   			<input type="button" id="BTNBTL$viewPengguna.USER_ID" name="BTNBTL$viewPengguna.USER_ID" onClick="doDivAjaxCall$formname('div_viewPengguna$viewPengguna.USER_ID','batalPaparMyProfile','USER_ID=$viewPengguna.USER_ID&internalType=');" value="Batal" > 
	   			</td>
			</tr>
		
		
		</table>
	</fieldset>
	<br>
</td>		
</tr>
<script>
document.getElementById('PASSWORD_$viewPengguna.USER_ID').value = null;
</script>