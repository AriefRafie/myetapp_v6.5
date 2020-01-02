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
	Kemaskini Maklumat $viewPengguna.USER_NAME
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
				value="$viewPengguna.NOKP1" size="7" maxlength="6"  
				
				/>
               
               	<span id="span_NOKP1_$internalType$viewPengguna.USER_ID">
                <input type="hidden" id="CHECK_NOKP1_$internalType$viewPengguna.USER_ID" 
				name="CHECK_NOKP1_$internalType$viewPengguna.USER_ID" value="true" >
                </span> 
                                     &nbsp; 
                <input name="NOKP2_$internalType$viewPengguna.USER_ID" type="$login_kp2" 
                id="NOKP2_$internalType$viewPengguna.USER_ID" style="width:20px;" 
                onKeyUp="javascript:validateIC_V3(event,this,this.value,'NOKP3_$internalType$viewPengguna.USER_ID');setCheckLogin('$internalType','$viewPengguna.USER_ID')" 
                value="$viewPengguna.NOKP2" size="1" maxlength="2"  />
                                      &nbsp; 
                                      
                <input name="NOKP3_$internalType$viewPengguna.USER_ID" type="$login_kp3" 
                id="NOKP3_$internalType$viewPengguna.USER_ID" style="width:40px;" 
                onKeyUp="javascript:validateIC_V3(event,this,this.value,'NOKP3_$internalType$viewPengguna.USER_ID');setCheckLogin('$internalType','$viewPengguna.USER_ID')" 
                onBlur="getJantinaByIC_V3(this,this.value,'JANTINA_$internalType$viewPengguna.USER_ID');"  
                value="$viewPengguna.NOKP3" size="5" maxlength="4" />     
             
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
				value="$viewPengguna.PASSWORD"
				 >	
				<span id="SHOWHIDE_PASS_$internalType$viewPengguna.USER_ID" 
				style="cursor: pointer;"
				>
				<font color='blue'
				onClick="showHidePass('SHOWHIDE_PASS_$internalType$viewPengguna.USER_ID','Hide','PASSWORD_$internalType$viewPengguna.USER_ID','PASSWORD2_$internalType$viewPengguna.USER_ID');"
				><u>Hide Password</u></font><br />
				</span>(leave blank to remain unchanged)				</div>		
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
				<input size="50" type="text" maxlength="12" id="PASSWORD2_$internalType$viewPengguna.USER_ID" 
				name="PASSWORD2_$internalType$viewPengguna.USER_ID" 
				value="$viewPengguna.PASSWORD" >
				<br />
				(leave blank to remain unchanged)				</td>
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
				<input style="text-transform:uppercase;" size="50" type="text" id="USER_NAME_$internalType$viewPengguna.USER_ID" 
				name="USER_NAME_$internalType$viewPengguna.USER_ID" 
				value="$viewPengguna.USER_NAME" onKeyUp="avoidScriptInjection(this,this.value);" >				
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
					<select id="ID_JAWATAN_$internalType$viewPengguna.USER_ID"  name="ID_JAWATAN_$internalType$viewPengguna.USER_ID" >	   
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
					<select id="ID_AGAMA_$internalType$viewPengguna.USER_ID"  name="ID_AGAMA_$internalType$viewPengguna.USER_ID" >	   
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
					<select id="ID_BANGSA_$internalType$viewPengguna.USER_ID"  name="ID_BANGSA_$internalType$viewPengguna.USER_ID" >	   
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
					<select id="ID_SEKSYEN_$internalType$viewPengguna.USER_ID"  
					name="ID_SEKSYEN_$internalType$viewPengguna.USER_ID" 
					onChange = "doDivAjaxCall$formname('div_ID_PEJABATJKPTG$internalType$viewPengguna.USER_ID','showListPejabat','ID_SEKSYEN='+$jquery('#ID_SEKSYEN_$internalType$viewPengguna.USER_ID').val()+'&ID_NEGERI='+$jquery('#ID_NEGERI_$internalType$viewPengguna.USER_ID').val()+'&internalType=$internalType&USER_ID=$viewPengguna.USER_ID');"
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
					<select id="ID_NEGERI_$internalType$viewPengguna.USER_ID"  name="ID_NEGERI_$internalType$viewPengguna.USER_ID"
					onChange = "doDivAjaxCall$formname('div_ID_PEJABATJKPTG$internalType$viewPengguna.USER_ID','showListPejabat','ID_SEKSYEN='+$jquery('#ID_SEKSYEN_$internalType$viewPengguna.USER_ID').val()+'&ID_NEGERI='+$jquery('#ID_NEGERI_$internalType$viewPengguna.USER_ID').val()+'&internalType=$internalType&USER_ID=$viewPengguna.USER_ID');"
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
				<td id="div_ID_PEJABATJKPTG$internalType$viewPengguna.USER_ID" >
					<select id="ID_PEJABATJKPTG_$internalType$viewPengguna.USER_ID"  name="ID_PEJABATJKPTG_$internalType$viewPengguna.USER_ID" 
					onChange = "doDivAjaxCall$formname('div_ALAMAT_PEJABATJKPTG$internalType$viewPengguna.USER_ID','showDisplayPejabat','ID_PEJABATJKPTG='+$jquery('#ID_PEJABATJKPTG_$internalType$viewPengguna.USER_ID').val()+'&internalType=$internalType&USER_ID=$viewPengguna.USER_ID');"
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
			
			<tr id="div_ALAMAT_PEJABATJKPTG$internalType$viewPengguna.USER_ID" style="display:none">
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
					document.getElementById("div_ALAMAT_PEJABATJKPTG$internalType$viewPengguna.USER_ID").style.display="";
					</script>
					
				#end
				</td>
			</tr>
			
            <!--TAMBAH COLUMN WAKTU KERJA-->
            
			<tr>
				<td valign="top" >			
				</td>			
				<td valign="top" >
				Waktu Kerja
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<select id="WAKTU_KERJA_$internalType$viewPengguna.USER_ID" name="WAKTU_KERJA_$internalType$viewPengguna.USER_ID">
				#set ( $selected_wp1 = "" )
				#set ( $selected_wp2 = "" )
                #set ( $selected_wp3 = "" )
				
                #if($viewPengguna.WAKTU_KERJA=="WP1")
				#set ( $selected_wp1 = "selected" )
                #elseif($viewPengguna.WAKTU_KERJA=="WP2")
				#set ( $selected_wp2 = "selected" )
                #elseif($viewPengguna.WAKTU_KERJA=="WP3")
				#set ( $selected_wp3 = "selected" )
				#else
				
				#end
                <option value="" >SILA PILIH</option>
				<option value="WP1" $selected_wp1 >WP1</option>
				<option value="WP2" $selected_wp2 >WP2</option>
                <option value="WP3" $selected_wp3 >WP3</option>
				</select>	
				</td>
			</tr>
            
            
			
			
			
			<tr id="div_displayDaerahJagaan$internalType$viewPengguna.USER_ID">
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
				if('$viewPengguna.ID_SEKSYEN'=='2')
				{	
				  document.getElementById("div_displayDaerahJagaan$internalType$viewPengguna.USER_ID").style.display="";
				  $jquery(document).ready(function () {
					  doDivAjaxCall$formname('div_displayDaerahJagaan$internalType$viewPengguna.USER_ID','showDisplayDaerahJagaan','ID_PEJABATJKPTG=$viewPengguna.ID_PEJABATJKPTG&ID_JENISPEJABAT=22');			 	  
				  });
				}
				else
				{
					document.getElementById("div_displayDaerahJagaan$internalType$viewPengguna.USER_ID").style.display="none";
				}
				</script>
				</td>
			</tr>
		
			
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
					<select id="ROLE_MAIN_$internalType$viewPengguna.USER_ID"  
					name="ROLE_MAIN_$internalType$viewPengguna.USER_ID" 
					
					>	
					<!-- onMouseOver="doDivAjaxCall$formname('div_KETERANGAN_ROLE_MAIN_$internalType$viewPengguna.USER_ID','showKeteranganRole','ROLE_ID=$ruj.ID&USER_ID=$viewPengguna.USER_ID&internalType=$internalType');"
					 -->   
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $listRole )		
							#set ( $selected_ruj = "" )
							#if($viewPengguna.ROLE_MAIN==$ruj.ID && $ruj.ID !="")
							#set ( $selected_ruj = "selected" )
							#end	
							
								
							<option  $selected_ruj value="$ruj.ID"
							title="$ruj.ROLE_DETAILS"
							   >
							#if($ruj.ID == "" && $ruj.KOD!="")
								--------- $ruj.KOD --------- 
							#else
							
								$ruj.KOD - $ruj.KETERANGAN
							
							#end
						</option>
						#end
					</select>
					</div>
					
				
					
					
					<!--  
					<style>
					#tail {
					    position: absolute;
					    float: left;
					}
					#img {width:200px; height:200px; background:pink;}										
					</style>
					
					<div id="tail" >mouse tail</div>					
					<script>
					$jquery(document).ready(function () {
						$jquery('#tail').hide();
						
						$jquery('#ROLE_MAIN_$internalType$viewPengguna.USER_ID').bind('mousemove', function(e){
						    $jquery('#tail').show();
						   
						 
						    
						    $jquery('#tail').css({
						       position : 'absolute',
						       zIndex: '99999999999999',
						       left:  e.pageX + 20,
						       top:   e.pageY
						    });
						    $jquery('#ROLE_MAIN_$internalType$viewPengguna.USER_ID').bind('mouseleave', function(e){
						    	$jquery('#tail').hide();
							});
						});
					});
					 </script>					
					-->
				</td>
			</tr>
			
         
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Status Jawatan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				<select id="FLAG_JAWATAN_$internalType$viewPengguna.USER_ID" name="FLAG_JAWATAN_$internalType$viewPengguna.USER_ID">
				#set ( $selected_aktif = "" )
				#set ( $selected_tidakaktif = "" )
							#if($viewPengguna.FLAG_JAWATAN=="1" || $viewPengguna.FLAG_JAWATAN=="")
								#set ( $selected_aktif = "selected" )
							#else
								#set ( $selected_tidakaktif = "selected" )
							#end
				<option value="1" $selected_aktif >TETAP</option>
				<option value="2" $selected_tidakaktif >SEMENTARA</option>
				</select>
				</td>
			</tr>
            
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tempoh Mula Berkhidmat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				<input name="TARIKH_MULA_TEMPOH_$internalType$viewPengguna.USER_ID" type="text" id="TARIKH_MULA_TEMPOH_$internalType$viewPengguna.USER_ID" style="text-transform:uppercase;" value="$viewPengguna.TARIKH_MULA_TEMPOH" size="15" maxlength="15" 
		   onkeypress="if(event.keyCode == 13){ if(checkTempohKhidmat('$internalType','$USER_ID') == true){doDivAjaxCall$formname('div_ListAT$internalType$USER_ID','carianUtamaAT','USER_ID=$USER_ID&internalType=$internalType');} return false; }"
		   />
       <a href="javascript:displayDatePicker('TARIKH_MULA_TEMPOH_$internalType$viewPengguna.USER_ID',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
      
				</td>
			</tr>
            
             <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tempoh Akhir Berkhidmat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				<input name="TARIKH_TAMAT_TEMPOH_$internalType$viewPengguna.USER_ID" type="text" id="TARIKH_TAMAT_TEMPOH_$internalType$viewPengguna.USER_ID" style="text-transform:uppercase;" value="$viewPengguna.TARIKH_TAMAT_TEMPOH" size="15" maxlength="15" 
		   onkeypress="if(event.keyCode == 13){ if(checkTempohKhidmat('$internalType','$USER_ID') == true){doDivAjaxCall$formname('div_ListAT$internalType$USER_ID','carianUtamaAT','USER_ID=$USER_ID&internalType=$internalType');} return false; }"
		   />
       <a href="javascript:displayDatePicker('TARIKH_TAMAT_TEMPOH_$internalType$viewPengguna.USER_ID',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> 
      
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
            
			
			<input type="hidden" id="ID_PERMOHONAN_$internalType$viewPengguna.USER_ID" 
				name="ID_PERMOHONAN_$internalType$viewPengguna.USER_ID" 
				value="$viewPengguna.ID_PERMOHONAN" >	
			<input type="hidden" id="ID_TABLEUSERS_$internalType$viewPengguna.USER_ID" 
				name="ID_TABLEUSERS_$internalType$viewPengguna.USER_ID" 
				value="$viewPengguna.ID_TABLEUSERS" >
					
			#if($viewPengguna.ID_PERMOHONAN!="" && $viewPengguna.ID_TABLEUSERS=="")
			<script>
			setPasswordClassPIP('PASSWORD_$internalType$viewPengguna.USER_ID','$viewPengguna.PASSWORD','$internalType','$viewPengguna.USER_ID');
			$jquery(document).ready(function () {
				if($jquery('#USER_LOGIN_$internalType$viewPengguna.USER_ID').val()!='')
				{
					doDivAjaxCall$formname('div_CHECK_USER_LOGIN_$internalType$viewPengguna.USER_ID','checkUSER_LOGIN','&FLAG_PIP=Y&internalType=$internalType&USER_ID=$viewPengguna.USER_ID&USER_LOGIN='+$jquery('#USER_LOGIN_$internalType$viewPengguna.USER_ID').val());
				}
			});		
			</script>
			
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" colspan="3" class="underline_td_tajuk" >
				<b>STATUS PERMOHONAN ID PENGGUNA</b>
				
				</td>
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
				$viewPengguna.TARIKH_PERMOHONAN_PIP
				</td>
			</tr>
			<tr>
				<td valign="top" >					
				</td>			
				<td valign="top" >
				MyID
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.NOKP1_PIP - $viewPengguna.NOKP2_PIP - $viewPengguna.NOKP3_PIP
				</td>
			</tr>
			<tr>
				<td valign="top" >					
				</td>			
				<td valign="top" >
				Nama
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.USER_NAME_PIP
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
				<td valign="top" >
				$viewPengguna.JAWATAN_PIP
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
				$viewPengguna.NEGERI_PIP
				</td>
			</tr>
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
				$viewPengguna.BAHAGIAN_PIP
				</td>
			</tr>
			<tr>
				<td valign="top" >					
				</td>			
				<td valign="top" >
				Unit
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.UNIT_PIP
				</td>
			</tr>
			<tr>
				<td valign="top" >			
				</td>			
				<td valign="top" >
				Penyelia
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewPengguna.PEGAWAIPENYELIA			
				</td>
			</tr>
			<tr>
				<td valign="top" >			
				</td>			
				<td valign="top" >
				Status Semasa Permohonan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				#if($viewPengguna.FLAG_STATUS=="1")
				PERMOHONAN BARU
				#elseif($viewPengguna.FLAG_STATUS=="2")
				DITOLAK
				#elseif($viewPengguna.FLAG_STATUS=="3")
				DITERIMA
				#end		
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
				
				<input $check3 type="radio" id="RADIO_STATUS_PID_$internalType$viewPengguna.USER_ID" 
				name="RADIO_STATUS_PID_$internalType$viewPengguna.USER_ID" 
				onClick="fromRadioToText(this,this.value,'STATUS_PID_$internalType$viewPengguna.USER_ID')" 
				value="3">DITERIMA
				&nbsp;&nbsp;
				<input $check2 type="radio" id="RADIO_STATUS_PID_$internalType$viewPengguna.USER_ID" 
				name="RADIO_STATUS_PID_$internalType$viewPengguna.USER_ID" 
				onClick="fromRadioToText(this,this.value,'STATUS_PID_$internalType$viewPengguna.USER_ID')" 
				value="2">DITOLAK
				
				
				<input type="hidden" id="STATUS_PID_$internalType$viewPengguna.USER_ID" 
				name="STATUS_PID_$internalType$viewPengguna.USER_ID" value="$viewPengguna.FLAG_STATUS" >
					
				<!--		
				<select id="XSTATUS_PID_$internalType$viewPengguna.USER_ID" name="XSTATUS_PID_$internalType$viewPengguna.USER_ID">
				#set ( $selected_stat_1 = "" )
				#set ( $selected_stat_2 = "" )
				#set ( $selected_stat_3 = "" )
							#if($viewPengguna.FLAG_STATUS=="1")
								#set ( $selected_stat_1 = "selected" )
							#elseif($viewPengguna.FLAG_STATUS=="2")
								#set ( $selected_stat_2 = "selected" )
							#elseif($viewPengguna.FLAG_STATUS=="3")
								#set ( $selected_stat_3 = "selected" )
							#end
				<option value="1" $selected_stat_1 >PERMOHONAN BARU</option>
				<option value="2" $selected_stat_2 >DITOLAK</option>
				<option value="3" $selected_stat_3 >DITERIMA</option>
				</select>
				-->
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
				<textarea style="text-transform:uppercase;" rows="4" cols="50" id="CATATAN_PIP_$internalType$viewPengguna.USER_ID" 
				name="CATATAN_PIP_$internalType$viewPengguna.USER_ID" 
				>$viewPengguna.CATATAN_PIP</textarea>			
				</td>
			</tr>
			<tr>
			<td valign="top" align="center" >
			
			</td>
			<td valign="top" align="left" >
			Lampiran
			</td>
			<td valign="top"  >
			:
			</td>
			<td valign="top" align="left" >
			
			<div id="displayDocPIP_$internalType$viewPengguna.USER_ID">
			<script> 	
			  $jquery(document).ready(function () {
				  doDivAjaxCall$formname('displayDocPIP_$internalType$viewPengguna.USER_ID','showDocPIP','ID_PERMOHONAN=$viewPengguna.ID_PERMOHONAN&internalType=$internalType&USER_ID=$viewPengguna.USER_ID');			 	  
			  });		
			</script>
			</div>
			
			<span id="displayDocPIP_$internalType$viewPengguna.USER_ID"></span>
			<input size="50" type="file" id="FILE_$internalType$viewPengguna.USER_ID" 
							name="FILE_$internalType$viewPengguna.USER_ID" 
							onChange="uploadDocPIP(this,'$internalType','$viewPengguna.USER_ID','$viewPengguna.ID_PERMOHONAN','displayDocPIP_$internalType$viewPengguna.USER_ID');"
							>
			
			</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" colspan="3" class="underline_td_tajuk" >
				</td>
			</tr>
			#else
			<input type="hidden" id="STATUS_PID_$internalType$viewPengguna.USER_ID" 
				name="STATUS_PID_$internalType$viewPengguna.USER_ID" value="" >
			#end
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >				
				</td>
				<td valign="top" >				
				</td>
				<td valign="top" >
				<input type="button" id="BTNSAVE$internalType$viewPengguna.USER_ID" name="BTNSAVE$internalType$viewPengguna.USER_ID" 
				onClick="if(valEditPenggunaInternal('$internalType','$viewPengguna.USER_ID') == true){doDivAjaxCall$formname('div_viewPengguna$internalType$viewPengguna.USER_ID','savePenggunaInternal','ID_PERMOHONAN=$viewPengguna.ID_PERMOHONAN&USER_ID=$viewPengguna.USER_ID&internalType=$internalType');}" value="Simpan" > 
	   			<input type="button" id="BTNBTL$internalType$viewPengguna.USER_ID" name="BTNBTL$internalType$viewPengguna.USER_ID" onClick="doDivAjaxCall$formname('div_viewPengguna$internalType$viewPengguna.USER_ID','edit_PenggunaInternal','USER_ID=$viewPengguna.USER_ID&internalType=$internalType');" value="Batal" > 
	   			<input type="button" id="BTNCLOSE$internalType$viewPengguna.USER_ID" name="BTNCLOSE$internalType$viewPengguna.USER_ID" onClick="doDivAjaxCall$formname('div_viewPengguna$internalType$viewPengguna.USER_ID','close_PenggunaInternal','USER_ID=$viewPengguna.USER_ID&internalType=$internalType');" value="Tutup" > 
	   			
	   			</td>
			</tr>
		
		
		</table>
	</fieldset>
	<br>
</td>		
</tr>
<script>
//pwdStrength();
//document.getElementById('PASSWORD_$internalType$viewPengguna.USER_ID').value = null;
</script>