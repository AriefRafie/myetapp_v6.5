<!-- frmPajakanKecilTindakanSenarai.jsp -->
<!--<strong>[CL-02-0405] Pajakan Kecil Tanah/Bangunan</strong>
<br><br>
-->
<style type="text/css">
<!--
	.pautanms {color: #0000FF}
-->
</style>
<!-- <br/> -->
#parse('app/htp/pajakankecil/utiliti/frmPajakanKecilPaging.jsp')

<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>
		#parse ("app/htp/frmPajakanKecilInfo.jsp")			
			
		</td>
	</tr>	

  	<tr>
    	<td>
			<fieldset>
				<legend><strong>MAKLUMAT TINDAKAN </strong></legend>
  					<table width="100%" >
  						<tr>
  							<td width="50%" valign="top">&nbsp;
								<table width="100%" >
			          				<tr>
							 			<td width="1%">&nbsp;</td>
							            <td width="40%">
							            	<div align="right" class="labelinput">
												<div align="left">Tarikh Tindakan</div>
											</div>             
							            </td>
							            <td width="1%">:</td>
							            <td width="58%">
							            	<input name="txtarikhkeputusan" type="text" value="$!tarikhHantar" size="11" maxlength="10" $!readonly class="$disabled"/>
										    #if($mode != 'view')
										      <a href="javascript:displayDatePicker('txtarikhkeputusan',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a>
											#end
										</td>
						          	</tr>
				          			<tr>
							 			<td width="1%" valign=top>&nbsp;</td>
							            <td width="40%" valign=top>
							            	<div align="right" class="labelinput">
												<div align="left">Catatan</div>
											</div>             
							            </td>
							            <td width="1%" valign=top>:</td>
							            <td width="58%"><textarea name="txtcatatan" cols="45" rows="5" $!readonly class="$disabled" onkeyup="this.value=this.value.toUpperCase();textCounter(this.form.txtcatatan,this.form.remtxtcatatan,250);" onkeydown="textCounter(this.form.txtcatatan,this.form.remtxtcatatan,250);">$!txtCatatan</textarea></td>
							     	</tr>
							     #if ($mode != 'view')
									<tr>
								        <td>&nbsp;</td>
								        <td>&nbsp;</td>
								        <td valign="top">&nbsp;</td>
								        <td><input type="text" readonly class="disabled" name="remtxtcatatan" size="3" maxlength="4" value="250"> Baki Aksara </td>
								    </tr>	
								#end															
								</table>
							</td>
  							<td width="50%" valign="top">&nbsp;</td> 							
						</tr>
		
				</table>
			</fieldset>

			<p align="center">
				<div align="center">
			#if ($mode =='new')
		    	<input type="button" class="stylobutton100" name="cmdtambah" value="Simpan" onclick="simpanTindakan('$permohonanInfo.idpermohonan')"/>
		    	<input type="button" class="stylobutton100" name="cmdBatal" value="Batal" onclick="screen8('$permohonanInfo.idpermohonan')"/>
		  	#elseif ($mode == 'update')
		    	<input type="button" class="stylobutton100" name="btnUpdateHakmilik" id="btnUpdateHakmilik" value="Simpan" onclick="doKemaskiniTindakan('$permohonanInfo.idpermohonan','$!idSusulanStatus','$!idSusulan')"/>
		    	<input type="button" class="stylobutton100" name="btnBackPergerakan" value="Batal" onclick="batalTindakan('$permohonanInfo.idpermohonan','$!idSusulanStatus','$!idSusulan')"/>
		  	#elseif ($mode == 'view')
		  		#if(($disableFungsi) && $portal_role.contains('PenggunaNegeri'))
		    		<input type="button" class="stylobutton" name="cmdtambah" value="Hantar Untuk Semakan" onclick="simpanTindakanRole('$permohonanInfo.idpermohonan','$!idSusulan')"/>
		  	
		  		#elseif (($disableFungsi) && $portal_role.contains('PegawaiNegeri'))
		    		<input type="button" class="stylobutton100" name="cmdtambah" value="Sahkan " onclick="simpanTindakanRole('$permohonanInfo.idpermohonan','$!idSusulan')"/>
		  		
		  		#elseif (($disableFungsi) && $portal_role.contains('PengarahNegeri'))
		    		<input type="button" class="stylobutton100" name="cmdtambah" value="Dilulus" onclick="simpanTindakanRole('$permohonanInfo.idpermohonan','$!idSusulan')"/>
	  			
	  			#elseif(($disableFungsi) && $portal_role.contains('HQPengguna'))
		    		<input type="button" class="stylobutton" name="cmdtambah" value="Hantar Untuk Semakan" onclick="simpanTindakanRole('$permohonanInfo.idpermohonan','$!idSusulan')"/>
		  	
		  		#elseif (($disableFungsi) && ($portal_role.contains('HQPegawai') || $portal_role.contains('KetuaPenolong')))
		    		<input type="button" class="stylobutton100" name="cmdtambah" value="Sahkan " onclick="simpanTindakanRole('$permohonanInfo.idpermohonan','$!idSusulan')"/>
		  		
		  		#elseif (($disableFungsi) && $portal_role.contains('HQPengarah'))
		    		<input type="button" class="stylobutton100" name="cmdtambah" value="Dilulus" onclick="simpanTindakanRole('$permohonanInfo.idpermohonan','$!idSusulan')"/>
		  				  		
		  		#end
		    	<input type="button" class="stylobutton100" name="btnKemaskiniHakmilik" value="Kemaskini" onclick="kemaskiniTindakan('$permohonanInfo.idpermohonan','$!idSusulanStatus','$!idSusulan')"/>
		    	<!-- <input type="button" class="stylobutton100" name="btnBackPergerakan" id="btnBackPergerakan" value="Kembali" onclick="kembaliSenaraiPergerakan()"/> -->
        		<input class="stylobutton100" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="screen8('$permohonanInfo.idpermohonan')">
		    #end
  				</div>
			</p> 
		   	<input type="hidden" name="id_kemaskini">
		    <input type="hidden" name="fail" >
		   	<input type="hidden" name="pagemode" >
		    <input type="hidden" name="langkah" value="0" >
    
		</td>
	</tr>
</table>
	
<script>
/*
function nexti2(i) {
	document.${formName}.id_kemaskini.value = i;
	document.${formName}.command.value = "pkpemilikseterus";
	document.${formName}.action = "";
	document.${formName}.submit();
}

function cancel() {
	//document.setup.buttonsubmit.value = "ADD";
	document.cari.reset();
	//document.forms[0].kod_agensi.focus();
}

function kemaskiniHakmilik(idh,id) {
	document.${formName}.fail.value = idh;
	document.${formName}.pagemode.value = 1;
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.command.value = "pkpemiliktambah";
	document.${formName}.action = "";
	document.${formName}.submit();
}

function kemaskiniPemilik(id,idp) {
	document.${formName}.fail.value = idp;
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.pagemode.value = 2;
	document.${formName}.command.value = "pkpemiliktambah";
	document.${formName}.action = "";
	document.${formName}.submit();
}

function backPre(id) {
	document.${formName}.command.value = "pksenaraipermohonan";
	document.${formName}.langkah.value = '0->-1';
	document.${formName}.fail.value = id;
	document.${formName}.action = "";
	document.${formName}.submit();
}

function backMain() {
	document.${formName}.action = "";
	document.${formName}.submit();
}


function back(id){
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.command.value = "pkpemilikseterus";
	//doAjaxCall${formName}("pkpemilikseterus");
}
*/
</script>
