<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<p>&nbsp;</p>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  
  <input name="actionPajakan" type="hidden" id="actionPajakan" value="$actionPajakan"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton" value="$hitButton"/>
  
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2">   
    	<fieldset><legend><strong>MAKLUMAT PENERIMAAN PERMOHONAN</strong></legend>
		<table width="100%">
        #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
			<tr>
				<td width="50%" align="center" valign="top">
					<table width="100%" border="0">
							  
   		<!-- <table width="100%" border="0" cellspacing="2" cellpadding="2"> -->
			         <tr>
			          	<td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
			            <td width="28%">Negeri</td>
			            <td width="1%">:</td>
			            <td width="70%">$selectNegeri</td>
			         </tr>
			         <tr>
			          	<td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
			            <td>Kementerian</td>
			            <td>:</td>
			            <td>$selectKementerian</td>
			         </tr>
			         <tr>
			         	<td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
			            <td>Agensi</td>
			            <td>:</td>
			            <td>$selectAgensi</td>
			         </tr>
			         <!--  <tr>
			         	<td>&nbsp;</td>
			            <td>Urusan</td>
			            <td>:</td>
			            <td>882 - PAJAKAN TANAH</td>
			         </tr> -->
			         <tr>
			         	<td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
			            <td>Sub Urusan</td>
			            <td>:</td>
			            <td>$selectSuburusan</td>
			         </tr>
			         <tr>
			         	<td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
			            <td>Status Tanah</td>
			            <td>:</td>
			            <td>$selectStatusTanah</td>
			         </tr>
			         <tr>
			         	<td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
			         	<td>Jenis Fail</td>
			            <td>:</td>
			            <td>$selectJenisFail</td>
			         </tr>

         <tr style="display:none">
         	<td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
            <td valign="top">Tarikh Agihan</td>
            <td>:</td>
            <td><input type="text" name="tarikhAgihan" id="tarikhAgihan" onblur="check_date(this)" size="9" $readonly class="$inputTextClass" value="$beanMaklumatPermohonan.tarikhAgihan"/>
            #if ($mode != 'view')
            <a href="javascript:displayDatePicker('tarikhAgihan',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
            #end            
            </td>
         </tr>
			         <tr>
			         	<td valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
			           <td valign="top">Tujuan</td>
			            <td valign="top">:</td>
			            <td valign="top"><textarea name="txtTajuk" id="txtTajuk" rows="5" cols="41" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();">$beanMaklumatPermohonan.tajuk</textarea></td>
			         </tr>
   					</table>		
				</td>
        		<td width="50%" align="center" valign="top">
        			<table width="100%" border="0">
        	        	<tr>
				         	<td>
				         	</td>
				            <td>No. Fail KJP</td>
				            <td>:</td>
				            <td><input type="text" name="txtNoFailKJP" id="txtNoFailKJP" $!readonly class="$inputTextClass" value="$beanMaklumatPermohonan.noFailKJP" onblur="this.value=this.value.toUpperCase();" /></td>
				         </tr>	
					</table>	        				
    			</td>
    		</tr>					         
         #end
        </table>
    </fieldset>    
    </td>
  </tr>
<!--  <tr>
    <td colspan="2">&nbsp;</td>
  </tr> -->
  	#if ($mode != 'view')
  	<tr>
    	<td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
 	</tr>
  	#end
	
	<tr>
    	<td width="30%">&nbsp;</td>
    	<td width="70%">
    	#if ($mode == 'view')
    		<!--<input type="button" class="stylobutton" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan()"/>
    		<input type="button" class="stylobutton" name="cmdBatal" id="cmdBatal" value="Batal" onclick="kembali()"/>
    		-->
 			<input class="stylobutton100" type="button" onclick="javascript:pajakanTerima($!idFail);" value="Simpan & Email" />
			<!--<input class="stylobutton" type="button" onclick="javascript:pajakanTolak($!idFail);" value="Tolak" /> -->
			<img src="../img/emel.gif" title="Pemberitahuan melalui emel" >
 
    	#else
    		<input type="button" class="stylobutton100" name="cmdSeterus" id="cmdSeterus" value="Seterusnya" onclick="pajakanViewMaklumatOnline('$!idFail','idB','idC','idE');"/>
    	
    	#end
    	</td>
	</tr>
</table>

<script>
	
function doChangeKementerian() {
	doAjaxCall${formName}("doChangeKementerian");
}

function simpan() {
	
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih Negeri.');
  		document.${formName}.socNegeri.focus(); 
		return; 
	}
	if(document.${formName}.socKementerian.value == ""){
		alert('Sila pilih Kementerian.');
  		document.${formName}.socKementerian.focus(); 
		return; 
	}
	if(document.${formName}.socAgensi.value == ""){
		alert('Sila pilih Agensi.');
  		document.${formName}.socAgensi.focus(); 
		return; 
	}
	if(document.${formName}.socAgensi.value == ""){
		alert('Sila pilih Sub Urusan.');
  		document.${formName}.socAgensi.focus(); 
		return; 
	}
	if(document.${formName}.socStatusTanah.value == ""){
		alert('Sila pilih Status Tanah.');
  		document.${formName}.socStatusTanah.focus(); 
		return; 
	}
	if(document.${formName}.socJenisFail.value == ""){
		alert('Sila pilih Jenis Fail.');
  		document.${formName}.socJenisFail.focus(); 
		return; 
	}
	/*if(document.${formName}.txtNoFailKJP.value == ""){
		alert('Sila masukkan No. Fail KJP.');
  		document.${formName}.txtNoFailKJP.focus(); 
		return; 
	}
	if(document.${formName}.tarikhSuratKJP.value == ""){
		alert('Sila masukkan Tarikh Surat KJP.');
  		document.${formName}.tarikhSuratKJP.focus(); 
		return; 
	}
	
	if(document.${formName}.txtNoFailLain.value == ""){
		alert('Sila masukkan No. Fail Lain.');
  		document.${formName}.txtNoFailLain.focus(); 
		return; 
	}
	
	if(document.${formName}.tarikhAgihan.value == ""){
		alert('Sila masukkan Tarikh Agihan.');
  		document.${formName}.tarikhAgihan.focus(); 
		return; 
	}*/
	if(document.${formName}.txtTajuk.value == ""){
		alert('Sila masukkan Tajuk.');
  		document.${formName}.txtTajuk.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPajakan.value = "daftarBaru";
		return;
	}
	
	document.${formName}.actionPajakan.value = "papar";
	document.${formName}.hitButton.value = "simpan";
	document.${formName}.mode.value = "view";	
	doAjaxCall${formName}("");
	//goToNext();
}

function kembali() {	
	document.${formName}.actionPajakan.value = "";
	document.${formName}.submit();
}

function goToNext(){
	//document.${formName}.action = "$EkptgUtil.getTabID("Pajakan",$portal_role)?_portal_module=ekptg.view.htp.FrmPajakanPendaftaranView";	
	document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanPendaftaranView";	
	document.${formName}.submit();
}
</script>
