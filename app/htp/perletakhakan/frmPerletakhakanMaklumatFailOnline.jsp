<!-- frmPPerletakhakanMaklumatFail.jsp -->
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>

<style type="text/css">
<!--
.pautanms {color: #0033FF}
-->
</style>
<p>
<input name="idPermohonan" type="hidden" value="$idPermohonan" id="idPermohonan" /> 
<input name="idFail" type="hidden" value="$idFail" id="idFail" /> 
<input name="idHtpPermohonan" type="hidden" value="$idHtpPermohonan" id="idHtpPermohonan" />
<input name="idSuburusanStatusFail" type="hidden" value="$idSuburusanStatusFail" id="idSuburusanStatusFail" />
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'/>
<input type="hidden" name="mode" value='$!mode'/>
</p>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
    
    #if($mode1 != 'new')
		<tr>
	   		<td>
				#parse ("app/htp/perletakhakan/paging.jsp") 	    	
	    	</td>
    	</tr>
    #end
		<tr>
	   		<td>
				<fieldset><legend>PENDAFTARAN PERMOHONAN</legend>	
					
				<table width="100%" border="0">
			  		<tr>
			  			<td>			
				    		<table width="100%" border="0" align="left">
				    			
				            	<tr>
						      		<td width="1%"><span class="labelmandatory">#if($mode != 'view')*#end</span></td>
									<td width="30%"><div align="left">Negeri</div></td>
									<td width="1%"><div align="center">:</div></td>
									<td width="68%">$selectNegeri</td>	                	
				            	</tr>
			            	<tr>
					      		<td width="1%"><span class="labelmandatory">#if($mode != 'view')*#end</span></td>
								<td width="30%"><div align="left">Daerah</div></td>
								<td width="1%"><div align="center">:</div></td>
								<td width="68%">$selectDaerah</td>	                	
			            	</tr>  
			            	<tr>
					      		<td width="1%"><span class="labelmandatory">#if($mode != 'view')*#end</span></td>
								<td width="30%"><div align="left">Kementerian</div></td>
								<td width="1%"><div align="center">:</div></td>
								<td width="68%">$selectKementerian</td>	                	
			            	</tr>  
			            	<tr>
					      		<td width="1%"><span class="labelmandatory">#if($mode != 'view')*#end</span></td>
								<td width="30%"><div align="left">Agensi</div></td>
								<td width="1%"><div align="center">:</div></td>
								<td width="68%">$selectAgensi</td>	                	
			            	</tr>  
			            	<tr>
					      		<td width="1%"><span class="labelmandatory">#if($mode != 'view')*#end</span></td>
								<td width="30%"><div align="left">Urusan</div></td>
								<td width="1%"><div align="center">:</div></td>
								<td width="68%">$selectSuburusan</td>	                	
			            	</tr>  
			            	<tr>
					      		<td width="1%" valign="top"><span class="labelmandatory">#if($mode != 'view')*#end</span></td>
								<td width="30%" valign="top"><div align="left">Tajuk</div></td>
								<td width="1%" valign="top"><div align="center">:</div></td>
								<td width="68%">
									<textarea name="txtTajuk" id="txtTajuk" cols="41" rows="5" $readonly class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();">$txtTajuk</textarea>			
								</td>	                	
			            	</tr> 
			            	            		            	
						    </table>
						</td>
						
						<td width="50%" align="center" valign="top">
			    			<table width="100%" border="0" align="left">
			    				
				            	<tr>
						      		<td width="1%"><span class="labelmandatory"></span></td>
									<td width="30%"><div align="left">No Permohonan</div></td>
									<td width="1%"><div align="center">:</div></td>
									<td width="68%">
										<input name="noFail" type="text" id="noFail" value="$!noP" size="43" readonly="readonly" class="disabled"/>
									</td>	                	
				            	</tr>     				
				            	<tr>
						      		<td width="1%" valign="top"><span class="labelmandatory">#if($mode != 'view')*#end</span></td>
									<td width="30%" valign="top"><div align="left">No. Fail KJP</div></td>
									<td width="1%" valign="top"><div align="center">:</div></td>
									<td width="68%">
				          				<input name="txtNoFailKJP" type="text" class="$inputTextClass" id="txtNoFailKJP" value="$txtNoFailKJP" size="43" $readonly onblur="this.value=this.value.toUpperCase();"/>				          							
									</td>	                	
				            	</tr> 
				             	<tr>
						      		<td width="1%" valign="top"><span class="labelmandatory">#if($mode != 'view')*#end</span></td>
									<td width="30%" valign="top"><div align="left">Tarikh Surat KJP</div></td>
									<td width="1%" valign="top"><div align="center">:</div></td>
									<td width="68%">
										<input name="txdTarikhSurKJP" type="text" id="txdTarikhSurKJP" value="$txdTarikhSurKJP" $readonly class="$inputTextClass" size="11" onblur="check_date(this)"/>
          								#if ($mode != 'view') 
          									<a href="javascript:displayDatePicker('txdTarikhSurKJP',false,'dmy');"> <img src="../img/calendar.gif" border="0"/></a> 
          								#end				
									</td>	                	
				            	</tr> 
				             	<tr>
						      		<td width="1%" valign="top"><span class="labelmandatory"></span></td>
									<td width="30%" valign="top"><div align="left">No. Fail Lain</div></td>
									<td width="1%" valign="top"><div align="center">:</div></td>
									<td width="68%">
										<input name="txtNoFailLain" type="text" class="$inputTextClass" id="txtNoFailLain" value="$txtNoFailLain" size="43" $readonly  onblur="this.value=this.value.toUpperCase();"/>				
									</td>	                	
				            	</tr> 
			    				
						    </table>
				    	</td>
					</tr>   				   				
			    </table>
			    
				</fieldset>
	    	</td>
    	</tr>
    	<tr>
		    <td colspan="2" align=left>
		    	<fieldset><legend>SENARAI SEMAK</legend>
		    
		    <table width="100%" border="0" align="center">
		    
				#foreach ( $semak in $senaraiSemakan )
					#set( $i = $velocityCount )
				#if ( ($i % 2) == 0 )
					#set( $row = "row2" )
				#else
					#set( $row = "row1" )
				#end
		
		      <tr>
		      <td width="30%" >
		      	#if ( $semakclass.isSemakan("$idPermohonan", "$semak.id" ))
					#set ( $checked = "checked" )
				#else
					#set ( $checked = "" )
				#end
		
		        <input type="checkbox" name="sbcSemakan" id="sbcSemakan" value="$semak.id" $checked $inputTextClass class="$inputTextClass" />
			
		    	$semak.keterangan</td>
		      </tr>
		      #end
		    </table>
		    </fieldset>
		    </td>
  		</tr>
	#if ($mode != 'view')
    	<tr>
      		<td align="center"><div align="left"><em><span class="style1">Perhatian</span> : Pastikan label bertanda <span class="style1">*</span> diisi.</em></div></td>
    	</tr>
    #end
    	<tr>
      		<td align="center">
      	#if($mode1 == 'new')
        <input class="stylobutton100" id="cmdSimpan" name="cmdSimpan" type="button" value="Simpan" onclick="simpan()"/>
        <input class="stylobutton100" id="cmdKembali" name="cmdKembali" type="button" value="Kembali" onClick="kembali()"/>
        #end
        #if ($mode1 == 'view')
        <input class="stylobutton100" id="cmdKemaskini" name="cmdKemaskini" type="button" value="Kemaskini" onClick="kemaskini('$idFail','$idPermohonan','$idHtpPermohonan','$idSuburusanStatusFail')"/>
        <!--<input class="stylobutton" type="button"  name="Cetak" id="Cetak" value="Cetak" onclick="javascript:senaraiDokumenSurat('tabledokumensurat');" /> -->
        <input class="stylobutton100" id="cmdKembali" name="cmdKembali" type="button" value="Kembali" onClick="kembali()"/>
        #if($isTransfered == false)
       	 <!--<input class="stylobutton" id="cmdSahkan" name="cmdSahkan" type="button" value="Sahkan" onClick="sahkan($idFail,$idPermohonan)"/> -->
       	#end
        #end 
        #if ($mode1 == 'update')
        <input class="stylobutton100" id="cmdUpdate" name="cmdUpdate" type="button" value="Simpan" onclick="edit('$idFail','$idPermohonan','$idHtpPermohonan','$idSuburusanStatusFail')"/>
        <input class="stylobutton100" id="cmdBatal" name="cmdBatal" type="button" value="Batal" onclick="batal()"/>
        #end
      		</td>
    	</tr>
  		
		<tr>
	   		<td>
		  		 <fieldset id="tabledokumensurat" style="display:none;">
					<legend>SENARAI SURAT/DOKUMEN</legend>
					<table width="100%" border="0">
					  <tr>
					    <td><a href="javascript:cetakFailDoket('&idpermohonan=$idPermohonan','&template=NoFailTajukFail','ekptg.report.htp.NoFailTajukFail')" class="pautanms">KULIT FAIL</a></td>
					  </tr>
					  <tr>
					    <td><a href="javascript:cetakFailDoket('&idpermohonan=$idPermohonan','&template=rptNoFailTajukFail','ekptg.report.htp.NoFailTajukFail')" class="pautanms">DOKET</a></td>
					  </tr>  
		   
					  </table>
					</fieldset>
    	
	    	</td>
    	</tr>
		<!-- 
		<tr>
	   		<td>
				<fieldset><legend></legend>	
					
				</fieldset>
    	
	    	</td>
    	</tr>    
    	-->	

	</table>

<script>
function edit(idA,idB,idC,idE){
if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih "Negeri" terlebih dahulu.');
		document.${formName}.socNegeri.focus();
		return;
	}
	if(document.${formName}.socKementerian.value == ""){
		alert('Sila pilih "Kementerian" terlebih dahulu.');
		document.${formName}.socKementerian.focus();
		return;
	}
		if(document.${formName}.socAgensi.value == ""){
		alert('Sila pilih "Agensi" terlebih dahulu.');
		document.${formName}.socAgensi.focus();
		return;
	}
	if(document.${formName}.socSuburusan.value == ""){
		alert('Sila pilih "Urusan" terlebih dahulu.');
		document.${formName}.socSuburusan.focus();
		return;
	}
	if(document.${formName}.txtTajuk.value == ""){
		alert('Sila pilih "Tujuan" terlebih dahulu.');
		document.${formName}.txtTajuk.focus();
		return;
	}
	if(document.${formName}.txtNoFailKJP.value == ""){
		alert('Sila pilih "No Fail KJP" terlebih dahulu.');
		document.${formName}.txtNoFailKJP.focus();
		return;
	}
		if(document.${formName}.txdTarikhSurKJP.value == ""){
		alert('Sila pilih "Tarikh Surat KJP" terlebih dahulu.');
		document.${formName}.txdTarikhSurKJP.focus();
		return;
	}
	
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmPerletakhakanPendaftaran&actionPerletakhakan=edit&idFail="+idA+"&idPermohonan="+idB+"&idHtpPermohonan="+idC+"&idSuburusanStatusFail="+idE;
	document.${formName}.submit();
	
}
function sahkan(idFail,idPermohohan){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmPerletakhakanPendaftaran&actionPerletakhakan=sahkan&idPermohonan="+idPermohohan+"&idFail="+idFail;
	document.${formName}.submit();
	
}
function batal(){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmPerletakhakanPendaftaran&actionPerletakhakan=papar";
	document.${formName}.submit();
}
function simpan(){
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih "Negeri" terlebih dahulu.');
		document.${formName}.socNegeri.focus();
		return;
	}
	if(document.${formName}.socKementerian.value == ""){
		alert('Sila pilih "Kementerian" terlebih dahulu.');
		document.${formName}.socKementerian.focus();
		return;
	}
		if(document.${formName}.socAgensi.value == ""){
		alert('Sila pilih "Agensi" terlebih dahulu.');
		document.${formName}.socAgensi.focus();
		return;
	}
	if(document.${formName}.socSuburusan.value == ""){
		alert('Sila pilih "Urusan" terlebih dahulu.');
		document.${formName}.socSuburusan.focus();
		return;
	}
	if(document.${formName}.txtTajuk.value == ""){
		alert('Sila pilih "Tujuan" terlebih dahulu.');
		document.${formName}.txtTajuk.focus();
		return;
	}
	if(document.${formName}.txtNoFailKJP.value == ""){
		alert('Sila pilih "No Fail KJP" terlebih dahulu.');
		document.${formName}.txtNoFailKJP.focus();
		return;
	}
		if(document.${formName}.txdTarikhSurKJP.value == ""){
		alert('Sila pilih "Tarikh Surat KJP" terlebih dahulu.');
		document.${formName}.txdTarikhSurKJP.focus();
		return;
	}
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmPerletakhakanPendaftaran&actionPerletakhakan=simpanBaru";
	document.${formName}.submit();
}
function kemaskini(idA,idB,idC,idE) {
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmPerletakhakanPendaftaran&actionPerletakhakan=kemaskini&idFail="+idA+"&idPermohonan="+idB+"&idHtpPermohonan="+idC+"&idSuburusanStatusFail="+idE;
	document.${formName}.submit();
}
function kembali() {	
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmPerletakhakanPendaftaran&actionPerletakhakan=";
	document.${formName}.submit();
}

function doChangeNegeri(){
	doAjaxCall${formName}("doChangeNegeri","actionPerletakhakan=tambah");
}
function doChangeNegeriEdit(){
	doAjaxCall${formName}("doChangeNegeri","actionPerletakhakan=kemaskini");
}
function doChangeKementerian(){
	doAjaxCall${formName}("doChangeKementerian","actionPerletakhakan=tambah");
}
function doChangeKementerianEdit(){
	doAjaxCall${formName}("doChangeKementerian","actionPerletakhakan=kemaskini");
}

// 01/06/2010 -
function senaraiDokumenSurat(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}

//2010/04/09 bertujuan mencetak doket atau Fail
// Dibuat oleh  : Rosli
// Dimodifikasi oleh :
function cetakFailDoket(id,temp,urlserv) {
	var param ="";
    var url = "../servlet/"+urlserv+"?"+id+temp;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
</script>
