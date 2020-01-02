
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
				onKeyUp="javascript:validateIC_V3(event,this,this.value,'NOKP2_$internalType$viewPengguna.USER_ID');setCheckLogin('$internalType','$viewPengguna.USER_ID');getDOBByIC_V3(this,this.value,'TARIKH_LAHIR_$internalType$viewPengguna.USER_ID','span_NOKP1_$internalType$viewPengguna.USER_ID');" 
				value="" size="7" maxlength="6"  
				
				/>
               
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
				$viewPengguna.DATE_DAYS_AFTERCHANGEPASS
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
				<input style="text-transform:uppercase;" size="50" type="text" id="USER_NAME_$internalType$viewPengguna.USER_ID" 
				name="USER_NAME_$internalType$viewPengguna.USER_ID" 
				onKeyUp="avoidScriptInjection(this,this.value);"
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
				
				<input  size="50" type="text" id="EMEL_$internalType$viewPengguna.USER_ID" 
				name="EMEL_$internalType$viewPengguna.USER_ID" 
				value="$viewPengguna.EMEL" >
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
					<input style="text-transform:uppercase;" size="50" type="text" id="JAWATAN_$internalType$viewPengguna.USER_ID" 
				name="JAWATAN_$internalType$viewPengguna.USER_ID" 
				value="$viewPengguna.JAWATAN" >	
				</td>
			</tr>
			
			
			
			<tr>
				<td valign="top" >	
							
				</td>			
				<td valign="top" >
				Jenis Pejabat
				</td>
				<td valign="top" >
				:
				</td>
				<td >
                $viewPengguna.JENIS_PEJABAT
                 <input type="hidden" id="ID_JENISPEJABAT_$internalType$viewPengguna.USER_ID" name="ID_JENISPEJABAT_$internalType$viewPengguna.USER_ID" value="$viewPengguna.ID_JENISPEJABAT">
				
				</td>
			</tr>
			</table>
			
			<div id="showDETAILBY_JENISPEJABAT_$internalType$viewPengguna.USER_ID">
			#if($viewPengguna.ID_JENISPEJABAT!="16111")
			<table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
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
					$viewPengguna.NEGERI_UI
                    <input type="hidden" id="ID_NEGERI_$internalType$viewPengguna.USER_ID" name="ID_NEGERI_$internalType$viewPengguna.USER_ID" value="$viewPengguna.ID_NEGERI">
				</td>
			</tr>
			<tr>
				<td valign="top" >
				<font color="red" >*</font>						
				</td>			
				<td valign="top" >
				Daerah
				</td>
				<td valign="top" >
				:
				</td>
				<td >
				$viewPengguna.DAERAH_UI
                 <input type="hidden" id="ID_DAERAH_$internalType$viewPengguna.USER_ID" name="ID_DAERAH_$internalType$viewPengguna.USER_ID" value="$viewPengguna.ID_DAERAH">
				
				</td>
			</tr>
			<tr>
				<td valign="top" >	
				<font color="red" >*</font>		
				</td>			
				<td valign="top" >
				Pejabat
				</td>
				<td valign="top" >
				:
				</td>
				<td >
				$viewPengguna.NAMA_PEJABAT
                 <input type="hidden" id="ID_PEJABAT_$internalType$viewPengguna.USER_ID" name="ID_PEJABAT_$internalType$viewPengguna.USER_ID" value="$viewPengguna.ID_PEJABAT">
				
				</td>
			</tr>
			
			
			
			<tr id="div_ALAMAT_PEJABAT_$internalType$viewPengguna.USER_ID" style="display:none">
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Alamat Pejabat
				</td>
				<td valign="top" >
				:
				</td>
				<td  >
				#if($viewPejabat.size() > 0)
					#if($viewPejabat.ALAMAT1 != "")
					$viewPejabat.ALAMAT1<br>
					#end
					#if($viewPejabat.ALAMAT2 != "")
						$viewPejabat.ALAMAT2<br>
					#end
					#if($viewPejabat.ALAMAT3 != "")
						$viewPejabat.ALAMAT3<br>
					#end
					#if($viewPejabat.POSKOD != "")
						$viewPejabat.POSKOD &nbsp;						
					#end
					#if($viewPejabat.BANDAR != "")
						$viewPejabat.BANDAR<br>
					#end
					#if($viewPejabat.NEGERI != "")
						$viewPejabat.NEGERI<br>
					#end
					
					#if($viewPejabat.NO_TEL != "")
						No. Tel : $viewPejabat.NO_TEL<br>
					#end
					#if($viewPejabat.NO_FAX != "")
						No. Fax : $viewPejabat.NO_FAX<br>
					#end
					
					<script type="text/javascript">
					document.getElementById("div_ALAMAT_PEJABAT_$internalType$viewPengguna.USER_ID").style.display="";
					</script>
					
				#end
				</td>
			</tr>
			
			
			
			
			
			<tr id="div_displayDaerahJagaan$internalType$viewPengguna.USER_ID" style="display:none">
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Daerah Urusan / Jagaan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top"  >
				<script>
					
				  document.getElementById("div_displayDaerahJagaan$internalType$viewPengguna.USER_ID").style.display="";
				  $jquery(document).ready(function () {
					  doDivAjaxCall$formname('div_displayDaerahJagaan$internalType$viewPengguna.USER_ID','showDisplayDaerahJagaan','ID_PEJABATJKPTG=$viewPengguna.ID_PEJABAT&ID_JENISPEJABAT=3');			 	  
				  });
				
				</script>
				</td>
			</tr>
		
			</table>
			#end
			</div>
			
			<table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
			</tr>
           
			<tr>
				<td valign="top" >			
				</td>			
				<td valign="top" >
				Peranan Utama di dalam Sistem
				</td>
				<td valign="top" >
				:
				</td>
				<td >
				 #if ($USER_LOGIN_ROLE.equals("online_kjp"))
                -
                #else
				$viewPengguna.ROLE_UTAMA
                <input type="hidden" id="ROLE_MAIN_$viewPengguna.USER_ID"  name="ROLE_MAIN_$viewPengguna.USER_ID"  value="$viewPengguna.ROLE_ID" />
				#end
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
				<td valign="top" >
				INTEGRASI
				</td>
			</tr>
	
			<tr>
				<td valign="top" >
				<font color="red" >*</font>						
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
				<input type="button" id="BTNSAVE$internalType$viewPengguna.USER_ID" name="BTNSAVE$internalType$viewPengguna.USER_ID" onClick="doDivAjaxCall$formname('div_viewPengguna$viewPengguna.USER_ID','savePenggunaINT','USER_ID=$viewPengguna.USER_ID&internalType=');" value="Simpan" > 
	   			<input type="button" id="BTNBTL$internalType$viewPengguna.USER_ID" name="BTNBTL$internalType$viewPengguna.USER_ID" onClick="doDivAjaxCall$formname('div_viewPengguna$viewPengguna.USER_ID','edit_PenggunaINT','USER_ID=$viewPengguna.USER_ID&internalType=');" value="Batal" > 
	   			</td>
			</tr>
		
		
		</table>
	</fieldset>
	<br>
</td>		
</tr>
<script>
document.getElementById('PASSWORD_$internalType$viewPengguna.USER_ID').value = null;
</script>