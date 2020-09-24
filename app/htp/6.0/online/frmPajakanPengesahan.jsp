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
<table style="width:100%">
	<tr>
		<td>
			<fieldset><legend>MAKLUMAT PENERIMAAN PERMOHONAN</legend>
		#foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
			<table style="width:100%">
				<tr>
					<td width="50%" align="center" valign="top">
						<table style="width:100%" >
							<tr>
					          	<td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
					            <td width="30%">Negeri</td>
					            <td width="1%">:</td>
					            <td width="68%">$selectNegeri</td>
					         </tr>	
						</table>
					</td>
					<td width="50%" align="center" valign="top">
						<table style="width:100%">
							<tr>
					       		<td width="1%">&nbsp;</td>
					            <td width="30%">No. Rujukan <i>Online</i></td>
					            <td width="1%">:</td>
					            <td width="68%"><input type="text" size="35" name="txtNoFail" id="txtNoFail" disabled="disabled" class="disabled" value="$beanMaklumatPermohonan.noP"/></td>
					    	</tr>
					      	<tr>
						         <td style="visibility:hidden">#if ($mode != 'view')<span class="style1">*</span>#end </td>
						         <td>No. Fail Lain /  Pemohon</td>
						         <td>:</td>
						         <td><input type="text" size="35" name="txtNoFailLain" id="txtNoFailLain" value="$beanMaklumatPermohonan.noFailLain" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();"/></td>
					   		</tr>
         					<tr>
					        	<td>
					          	</td>
					        	<td>Tarikh Surat Pemohon</td>
					            <td>:</td>
					            <td>            	
					            	<input type="text" size="11" maxlength="10" name="tarikhSuratPemohon" class="$classDis" id="tarikhSuratPemohon" onblur="check_date(this)" value="$beanMaklumatPermohonan.tarikhSuratPemohon" readonly="readonly" $readOnly/>
									#if ($mode != 'view') 
										<a href="javascript:displayDatePicker('tarikhSuratPemohon',false,'dmy');"><img src="../img/calendar.gif" alt="Calendar" border="0"/> 
									#end 
								</td>         
					      	</tr>
						</table>
					</td>
				</tr>
			</table>	
		#end		
			</fieldset>	
		</td>
	</tr>
			
			
  	#if ($mode != 'view')
  	<!-- <tr>
    	<td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
 	</tr> -->
  	#end
	<tr>
		<td>
			<fieldset><legend>SENARAI DOKUMEN YANG DISERTAKAN</legend>
			<table style="width:100%">
		    	<tr class="row2">
					<td width="3%"><b>Bil.</b></td>
					<td width="82%"><b>Keterangan</b></td>
					<td width="15%"><b>Dokumen</b></td>
				</tr>  
	#if ($senaraiSemak.size() > 0)
        #set ($list = "")
        #foreach ($list in $senaraiSemak)
          	#set( $i = $velocityCount )
       		#if ( ($i % 2) == 0 )
   	        	#set( $row = "row2" )
            #else
               	#set( $row = "row1" )
          	#end
                	
        #if($list.flag == 'Y')
        	#set($checked_ = 'checked')
        	#set($disabled = 'disabled')
        #else
        	#set($checked_ = '')
        #end
        
        #if ($mode == 'update')
	        <tr class="$row">
	          <td class="$row" width="3%"><input type="checkbox" value="$list.idSenaraiSemak" name="idsSenaraiSemak" $checked_ /></td>
	          <td class="$row" width="82%">$i. $list.keterangan</td>
	          	<td class="$row" width="15%">
	          	$!list.lampirans
	        	</td>
	        </tr>
	      #end
	      #if ($mode == 'view')
	      	<tr class="$row">
	          <td class="$row" width="3%"><input type="checkbox" value="$list.idSenaraiSemak" name="idsSenaraiSemak" $checked_ $disabled /></td>
	          <td class="$row" width="82%">$i. $list.keterangan</td>
	          <td class="$row" width="15%">
	          $!list.lampirans
	          </td>
	        </tr>
	      #end      
        #end
        
   	#else
        <tr>
          <td class="$row" width="3%">&nbsp;</td>
          <td class="$row" colspan="2" width="95%">Tiada Rekod</td>
        </tr>
  	#end        
			</table>
			</fieldset>
		</td>
	</tr>
	
	<tr>
		<td align=center>
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
$!javaScriptLampiran