
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

			<!-- Kategori Pengguna -->
            <tr>
				<td valign="top" >
				<font color="red" >*</font>
				</td>
				<td valign="top" >
				Kategori Pengguna
				</td>
				<td valign="top" >
				:
				</td>

				<td valign="top" >
				<select id="KATEGORI_$internalType$viewPengguna.USER_ID" name="KATEGORI_$internalType$viewPengguna.USER_ID"
				onChange = "doDivAjaxCall$formname('div_NO_PENGENALAN$internalType$viewPengguna.USER_ID'
				,'showFieldPengenalan'
				,'ID_KATEGORI='+$jquery('#KATEGORI_$internalType$viewPengguna.USER_ID').val()+'&internalType=$internalType&USER_ID=$viewPengguna.USER_ID&NO_PENGENALAN=$viewPengguna.NO_PENGENALAN&NO_PENGENALAN1=$viewPengguna.NO_PENGENALAN1&NO_PENGENALAN2=$viewPengguna.NO_PENGENALAN2&NO_PENGENALAN3=$viewPengguna.NO_PENGENALAN3');hiddenMyID('$internalType','$viewPengguna.USER_ID');hiddenPejabat('$internalType','$viewPengguna.USER_ID');">
				<option value = "" >SILA PILIH</option>
				#set ( $selected_individu = "" )
				#set ( $selected_syarikat = "" )
							#if($viewPengguna.KATEGORI=="INDIVIDU")
								#set ( $selected_individu = "selected" )
							#elseif($viewPengguna.KATEGORI=="SYARIKAT")
								#set ( $selected_syarikat = "selected" )
							#end
					<option value="Individu" $selected_individu >INDIVIDU</option>
					<option value="Syarikat" $selected_syarikat >BUKAN INDIVIDU</option>
			   <!-- <option value="Syarikat" $selected_syarikat >SYARIKAT</option> -->
				</select>
				</td>
			</tr>

<!-- 			<tr>
				<td valign="top" >
					<font color="red" >*</font>
				</td>
				<td valign="top" >
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<select id="SUBKATEGORI_$internalType$viewPengguna.USER_ID" name="SUBKATEGORI_$internalType$viewPengguna.USER_ID"
				onChange = "doDivAjaxCall$formname('div_NO_PENGENALAN$internalType$viewPengguna.USER_ID'
					,'showFieldPengenalan'
					,'ID_KATEGORI='+$jquery('#KATEGORI_$internalType$viewPengguna.USER_ID').val()+'&internalType=$internalType&USER_ID=$viewPengguna.USER_ID&NO_PENGENALAN=$viewPengguna.NO_PENGENALAN&NO_PENGENALAN1=$viewPengguna.NO_PENGENALAN1&NO_PENGENALAN2=$viewPengguna.NO_PENGENALAN2&NO_PENGENALAN3=$viewPengguna.NO_PENGENALAN3');hiddenMyID('$internalType'
					,'$viewPengguna.USER_ID');">
				<option value = "" >SILA PILIH</option>
				#set ( $selected_warga = "" )
				#set ( $selected_xwarga = "" )
							#if($viewPengguna.SUBKATEGORI=="WARGA")
								#set ( $selected_warga = "selected" )
							#elseif($viewPengguna.SUBKATEGORI=="XWARGA")
								#set ( $selected_xwarga = "selected" )
							#end
				<option value="warga" $selected_warga >WARGANEGARA</option>
				<option value="xwarga" $selected_xwarga >BUKAN WARGANEGARA</option>
				</select>
				</td>
			</tr> -->

			<tr>
				<td valign="top" >
					<font color="red" >*</font>
				</td>
				<td valign="top" >
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<select id="SUBKATEGORIEXT_$internalType$viewPengguna.USER_ID" name="SUBKATEGORI_$internalType$viewPengguna.USER_ID"
				onChange = "doDivAjaxCall$formname('div_NO_PENGENALAN$internalType$viewPengguna.USER_ID'
					,'showFieldPengenalan'
					,'ID_KATEGORI='+$jquery('#KATEGORI_$internalType$viewPengguna.USER_ID').val()+'&internalType=$internalType&USER_ID=$viewPengguna.USER_ID&NO_PENGENALAN=$viewPengguna.NO_PENGENALAN&NO_PENGENALAN1=$viewPengguna.NO_PENGENALAN1&NO_PENGENALAN2=$viewPengguna.NO_PENGENALAN2&NO_PENGENALAN3=$viewPengguna.NO_PENGENALAN3');hiddenMyID('$internalType'
					,'$viewPengguna.USER_ID');">
				<option value = "" >SILA PILIH</option>
				#set ( $selected_badan = "" )
				#set ( $selected_badanx = "" )
				#set ( $selected_knegeri = "" )
				#set ( $selected_koperasi = "" )
				#set ( $selected_syarikat = "" )
				#set ( $selected_lain = "" )

				#if($viewPengguna.SUBKATEGORIEXT=="MAMP")
					#set ( $selected_badan = "selected" )
				#elseif($viewPengguna.SUBKATEGORIEXT=="ROS")
					#set ( $selected_badanx = "selected" )
				#elseif($viewPengguna.SUBKATEGORIEXT=="PBN")
					#set ( $selected_knegeri = "selected" )
				#elseif($viewPengguna.SUBKATEGORIEXT=="SKM")
					#set ( $selected_koperasi = "selected" )
				#elseif($viewPengguna.SUBKATEGORIEXT=="SSM")
					#set ( $selected_syarikat = "selected" )
				#elseif($viewPengguna.SUBKATEGORIEXT=="LAI")
					#set ( $selected_lain = "selected" )
				#end
				      	<option value="MAMP" $!selected_badan>BADAN BERKANUN</option>
				   		<option value="ROS" $!selected_badanx>BADAN BUKAN KERAJAAN</option>
		                <option value="PBN" $selected_knegeri>KERAJAAN NEGERI/ WILAYAH PERSEKUTUAN</option>
		               	<option value="SKM" $selected_koperasi>KOPERASI</option>
		               	<option value="SSM" $selected_syarikat>SYARIKAT</option>
		               	<option value="LAI" $selected_lain>LAIN-LAIN (NYATAKAN)</option>
					</select>
				</td>

			</tr>

			<!-- Majlis Agama -->
			<tr id="div_Pejabat_$internalType$viewPengguna.USER_ID" style="display:none">
				<td valign="top" >
				<font color="red" >*</font>
				</td>
				<td valign="top" >

				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<!--  <select id="PEJABAT_$internalType$viewPengguna.USER_ID"  name="PEJABAT_$internalType$viewPengguna.USER_ID"
					onChange = "doDivAjaxCall$formname('div_nama$internalType$viewPengguna.USER_ID'
					,'showfieldPejabat'
					,'ID_PEJABAT='+$jquery('#PEJABAT_$internalType$viewPengguna.USER_ID').val()+'&internalType=$internalType&USER_ID=$viewPengguna.USER_ID');">
					  -->
					  <select onChange="javascript:setName(this.value,'USER_NAME_$internalType$viewPengguna.USER_ID')">
					   <option value = "" >SILA PILIH</option>
						#foreach ( $ruj in $list_TBLRUJPEJABAT )
							#set ( $selected_ruj = "" )
								#if($viewPengguna.ID_PEJABAT==$ruj.ID)
							#set( $selected_ruj = "selected" )
							#end
							<option $selected_ruj value="$ruj.KETERANGAN" >
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
				Nama
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<input style="text-transform:uppercase;" size="50" type="text" id="USER_NAME_$internalType$viewPengguna.USER_ID"
				name="USER_NAME_$internalType$viewPengguna.USER_ID"
				value="$viewPengguna.NAMA_PENUH" onKeyUp="avoidScriptInjection(this,this.value);" >
				</td>
			</tr>
            <tr id="div_NO_PENGENALAN_$internalType$viewPengguna.USER_ID">
				<td valign="top" >
				<font color="red" >*</font>
				</td>
				<td valign="top" >
				No Kad Pengenalan Baru
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
				onKeyUp="javascript:validateIC_V3(event,this,this.value,'NOKP2_$internalType$viewPengguna.USER_ID');copyValueIC(event,this,this.value,'NO_PENGENALAN1_$internalType$viewPengguna.USER_ID',$jquery('#KATEGORI_$internalType$viewPengguna.USER_ID').val());setCheckLogin('$internalType','$viewPengguna.USER_ID')"
				value="" size="7" maxlength="6"
				onBlur="getAgeByIC_V3(this,this.value,'UMUR_$internalType$viewPengguna.USER_ID');getDOBByIC_V3(this,this.value,'TARIKH_LAHIR_$internalType$viewPengguna.USER_ID','span_NOKP1_$internalType$viewPengguna.USER_ID');"  />

               	<span id="span_NOKP1_$internalType$viewPengguna.USER_ID">
                <input type="hidden" id="CHECK_NOKP1_$internalType$viewPengguna.USER_ID"
				name="CHECK_NOKP1_$internalType$viewPengguna.USER_ID" value="true" >
                </span>
                                     &nbsp;
                <input name="NOKP2_$internalType$viewPengguna.USER_ID" type="$login_kp2"
                id="NOKP2_$internalType$viewPengguna.USER_ID" style="width:20px;"
                onKeyUp="javascript:validateIC_V3(event,this,this.value,'NOKP3_$internalType$viewPengguna.USER_ID');copyValueIC(event,this,this.value,'NO_PENGENALAN2_$internalType$viewPengguna.USER_ID',$jquery('#KATEGORI_$internalType$viewPengguna.USER_ID').val());setCheckLogin('$internalType','$viewPengguna.USER_ID')"
                value="" size="1" maxlength="2"  />
                                     &nbsp;
                <input name="NOKP3_$internalType$viewPengguna.USER_ID" type="$login_kp3"
                id="NOKP3_$internalType$viewPengguna.USER_ID" style="width:40px;"
                onKeyUp="javascript:validateIC_V3(event,this,this.value,'NOKP3_$internalType$viewPengguna.USER_ID');copyValueIC(event,this,this.value,'NO_PENGENALAN3_$internalType$viewPengguna.USER_ID',$jquery('#KATEGORI_$internalType$viewPengguna.USER_ID').val());setCheckLogin('$internalType','$viewPengguna.USER_ID')"
                onBlur="getJantinaByIC_V3(this,this.value,'JANTINA_$internalType$viewPengguna.USER_ID');"
                value="" size="5" maxlength="4" />

             	<!-- close username by IC -->



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

            <input size="50" type="hidden" id="USER_LOGIN_$internalType$viewPengguna.USER_ID"
				name="USER_LOGIN_$internalType$viewPengguna.USER_ID"
				value="$viewPengguna.USER_LOGIN"
				onKeyUp = "removeSpaces(this, this.value); if($jquery('#USER_LOGIN_$internalType$viewPengguna.USER_ID').val()!=''){doDivAjaxCall$formname('div_CHECK_USER_LOGIN_$internalType$viewPengguna.USER_ID','checkUSER_LOGIN','internalType=$internalType&USER_ID=$viewPengguna.USER_ID&USER_LOGIN='+$jquery('#USER_LOGIN_$internalType$viewPengguna.USER_ID').val());}"
				>

            <tr id="div_NO_PENGENALAN$internalType$viewPengguna.USER_ID">
				<td valign="top" >
				<font color="red" >*</font>
				</td>
				<td valign="top" >
				#if($viewPengguna.KATEGORI=="INDIVIDU" || $viewPengguna.KATEGORI=="")
				ID Pengguna	(User Login)
				#else
				MyCOID (User Login)
				#end
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				#set($readonlyKP1 = "")
				#set($readonlyKP2 = "")
				#set($readonlyKP3 = "")


				#if($viewPengguna.USER_ID=="")
				#set($readonlyKP1 = "readonly")
				#set($readonlyKP2 = "readonly")
				#set($readonlyKP3 = "readonly")
				#end

				#if($viewPengguna.KATEGORI=="INDIVIDU" || $viewPengguna.KATEGORI=="")
				<input $readonlyKP1 name="NO_PENGENALAN1_$internalType$viewPengguna.USER_ID" type="text" id="NO_PENGENALAN1_$internalType$viewPengguna.USER_ID" style="width:50px;" onKeyUp="javascript:validateIC_V3(event,this,this.value,'NO_PENGENALAN2_$internalType$viewPengguna.USER_ID');" value="$viewPengguna.NO_PENGENALAN1" size="7" maxlength="6"  onBlur="getAgeByIC_V3(this,this.value,'UMUR_$internalType$viewPengguna.USER_ID');getDOBByIC_V3(this,this.value,'TARIKH_LAHIR_$internalType$viewPengguna.USER_ID','span_NO_PENGENALAN1_$internalType$viewPengguna.USER_ID');"  />
                <span id="span_NO_PENGENALAN1_$internalType$viewPengguna.USER_ID">
                <input type="hidden" id="CHECK_NO_PENGENALAN1_$internalType$viewPengguna.USER_ID"
				name="CHECK_NO_PENGENALAN1_$internalType$viewPengguna.USER_ID" value="true" >
                </span>
                                  &nbsp;
                <input $readonlyKP2 name="NO_PENGENALAN2_$internalType$viewPengguna.USER_ID" type="text" id="NO_PENGENALAN2_$internalType$viewPengguna.USER_ID" style="width:20px;" onKeyUp="javascript:validateIC_V3(event,this,this.value,'NO_PENGENALAN3_$internalType$viewPengguna.USER_ID');" value="$viewPengguna.NO_PENGENALAN2" size="1" maxlength="2"  />
                                      &nbsp;
                <input $readonlyKP3 name="NO_PENGENALAN3_$internalType$viewPengguna.USER_ID" type="text" id="NO_PENGENALAN3_$internalType$viewPengguna.USER_ID" style="width:40px;" onKeyUp="javascript:validateIC_V3(event,this,this.value,'NO_PENGENALAN3_$internalType$viewPengguna.USER_ID');" onBlur="getJantinaByIC_V3(this,this.value,'JANTINA_$internalType$viewPengguna.USER_ID');"  value="$viewPengguna.NO_PENGENALAN3" size="5" maxlength="4" />
                #else
                <input name="NO_PENGENALAN_$internalType$viewPengguna.USER_ID" type="text" id="NO_PENGENALAN_$internalType$viewPengguna.USER_ID" size="50" value="$viewPengguna.NO_PENGENALAN" onkeyup="copyValueIC(event,this,this.value,'USER_LOGIN_$internalType$viewPengguna.USER_ID',$jquery('#KATEGORI_$internalType$viewPengguna.USER_ID').val());" />

                #end
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
				<input style="text-transform:uppercase;" size="50" type="text" id="ALAMAT1_$internalType$viewPengguna.USER_ID"
				name="ALAMAT1_$internalType$viewPengguna.USER_ID"
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
				<input style="text-transform:uppercase;" size="50" type="text" id="ALAMAT2_$internalType$viewPengguna.USER_ID"
				name="ALAMAT2_$internalType$viewPengguna.USER_ID"
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
				<input style="text-transform:uppercase;" size="50" type="text" id="ALAMAT3_$internalType$viewPengguna.USER_ID"
				name="ALAMAT3_$internalType$viewPengguna.USER_ID"
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

				<input size="10" type="text" id="POSKOD_$internalType$viewPengguna.USER_ID"
				name="POSKOD_$internalType$viewPengguna.USER_ID"
				value="$viewPengguna.POSKOD"  maxlength="5"
				onKeyUp = "RemoveNonNumeric_V3(this,this.value)"
				onBlur = "checkMaxLength_V3(this,this.value,5,'span_ERR_POSKOD_$internalType$viewPengguna.USER_ID');"
				>
				<span id="span_ERR_POSKOD_$internalType$viewPengguna.USER_ID">
				<input type="hidden" id="CHECK_POSKOD_$internalType$viewPengguna.USER_ID"
				name="CHECK_POSKOD_$internalType$viewPengguna.USER_ID" value="true" >
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
				<select id="ID_NEGERI_$internalType$viewPengguna.USER_ID"  name="ID_NEGERI_$internalType$viewPengguna.USER_ID"
	onChange = "doDivAjaxCall$formname('div_ID_BANDAR$internalType$viewPengguna.USER_ID','showListBandar','ID_NEGERI='+$jquery('#ID_NEGERI_$internalType$viewPengguna.USER_ID').val()+'&internalType=$internalType&USER_ID=$viewPengguna.USER_ID');"
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
				<td valign="top" id="div_ID_BANDAR$internalType$viewPengguna.USER_ID" >
				<select id="ID_BANDAR_$internalType$viewPengguna.USER_ID"  name="ID_BANDAR_$internalType$viewPengguna.USER_ID" >
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
				<input size="5" maxlength="2" type="text" id="UMUR_$internalType$viewPengguna.USER_ID"
				name="UMUR_$internalType$viewPengguna.USER_ID"
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

				<select id="JANTINA_$internalType$viewPengguna.USER_ID" name="JANTINA_$internalType$viewPengguna.USER_ID">
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

				<select id="TARAF_PERKAHWINAN_$internalType$viewPengguna.USER_ID" name="TARAF_PERKAHWINAN_$internalType$viewPengguna.USER_ID">
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
				<input name="TARIKH_LAHIR_$internalType$viewPengguna.USER_ID" type="text" id="TARIKH_LAHIR_$internalType$viewPengguna.USER_ID" style="text-transform:uppercase;" value="$viewPengguna.TARIKH_LAHIR" size="15" maxlength="15"  />
       <a href="javascript:displayDatePicker('TARIKH_LAHIR_$internalType$viewPengguna.USER_ID',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>

				</td>
			</tr>


			<tr>
				<td valign="top" >
				</td>
				<td valign="top" >
				No. HP
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				<input onKeyUp = "RemoveNonNumeric_V3(this,this.value)" size="50" type="text" id="NO_HP_$internalType$viewPengguna.USER_ID"
				name="NO_HP_$internalType$viewPengguna.USER_ID"
				value="$viewPengguna.NO_HP" >
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
				<input onKeyUp = "RemoveNonNumeric_V3(this,this.value)" size="50" type="text" id="NO_TEL_$internalType$viewPengguna.USER_ID"
				name="NO_TEL_$internalType$viewPengguna.USER_ID"
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
				<input onKeyUp = "RemoveNonNumeric_V3(this,this.value)" size="50" type="text" id="NO_FAX_$internalType$viewPengguna.USER_ID"
				name="NO_FAX_$internalType$viewPengguna.USER_ID"
				value="$viewPengguna.NO_FAX" >
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
				<input type="button" id="BTNSAVE$internalType$viewPengguna.USER_ID" name="BTNSAVE$internalType$viewPengguna.USER_ID" onClick="if(valEditPenggunaOnline('$internalType','$viewPengguna.USER_ID') == true){doDivAjaxCall$formname('div_viewPengguna$internalType$viewPengguna.USER_ID','savePenggunaOnline','USER_ID=$viewPengguna.USER_ID&internalType=$internalType');}" value="Simpan" >
	   			<input type="button" id="BTNBTL$internalType$viewPengguna.USER_ID" name="BTNBTL$internalType$viewPengguna.USER_ID" onClick="doDivAjaxCall$formname('div_viewPengguna$internalType$viewPengguna.USER_ID','edit_PenggunaOnline','USER_ID=$viewPengguna.USER_ID&internalType=$internalType');" value="Batal" >
	   			<input type="button" id="BTNCLOSE$internalType$viewPengguna.USER_ID" name="BTNCLOSE$internalType$viewPengguna.USER_ID" onClick="doDivAjaxCall$formname('div_viewPengguna$internalType$viewPengguna.USER_ID','close_PenggunaOnline','USER_ID=$viewPengguna.USER_ID&internalType=$internalType');" value="Tutup" >

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