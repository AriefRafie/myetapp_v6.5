<!--frmPajakanKecilPendaftaranPermohonan.jsp-->


	## 0=First View
	## 1=save
	## 2=view	
	## 2=update
	## 3=delete
	## 4=simpan permohonan
	#set ( $pagemode = 0 )			
	
<style type="text/css">
<!--
	.pautanms {color: #0000FF}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>							
		<td> 
    	
			<fieldset><legend>PENDAFTARAN PERMOHONAN</legend>
  				<table border="0" cellpadding="0" cellspacing="0" width="100%">
      				<tr>
						<td width="50%" align="center" valign="top">	
							<table width="100%" border="0">
			              		<tr>
						      		<td width="1%"><span class="labelmandatory">*</span></td>
					        		<td width="30%"><div align="left">Negeri</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">$socNegeri</td>					
			              		</tr>
			              		<tr>
						      		<td width="1%"><span class="labelmandatory">*</span></td>
					        		<td width="30%"><div align="left">Kementerian</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">$socKementerian</td>					
			              		</tr>
			              		<tr>
						      		<td width="1%"><span class="labelmandatory">*</span></td>
					        		<td width="30%"><div align="left">Agensi</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">$socAgensi</td>					
			              		</tr>
			              		<tr>
						      		<td width="1%"><span class="labelmandatory">*</span></td>
					        		<td width="30%"><div align="left">Urusan</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">$socUrusan</td>					
			              		</tr>
			              		<tr>
						      		<td width="1%" valign="top"><span class="labelmandatory">*</span></td>
					        		<td width="30%" valign="top"><div align="left">Tajuk</div></td>
					        		<td width="1%" valign="top"><div align="center">:</div></td>
					        		<td width="68%">
										<textarea name="txttajuk" cols="41" rows="5" onkeyup="this.value=this.value.toUpperCase();" disabled="disabled">$!permohonanInfo.tajuk</textarea>				        		
					        		</td>					
			              		</tr>			              			        		
		              		</table>
        
        				</td>
        				
        				<td width="50%" align="center" valign="top">
        					
 			        		<table width="100%" border="0">
			              		<tr>
						      		<td width="1%"></td>
					        		<td width="30%"><div align="left">No. Fail Seksyen</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%"><input type="text" name="txtNoFailSek" size="30" value="$permohonanInfo.no" readonly="readonly"></td>							              			
		              			</tr>		
			              		<tr>
						      		<td width="1%"></td>
					        		<td width="30%"><div align="left">No. Fail Lain</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%"><input type="text" name="txtNoFailLain" size="30" value="" id="txtNoFailLain" onkeyup="" onkeyup="this.value=this.value.toUpperCase();"></td>							              			
		              			</tr>			              			        			
			              		<tr>
						      		<td width="1%"></td>
					        		<td width="30%"><div align="left">Tarikh Surat KJP</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">
					        			<input type="text" name="txdTarikhSuratKJP" value="" id="txdTarikhSurKJP" size="15" onkeyup="validateNumber(this,this.value);" onblur="check_date(this);checkDate();">
					        			#if ($pageMode != 2 )	
			                				<a href="javascript:displayDatePicker('txdTarikhSuratKJP',false,'dmy');"><img border="0" src="../img/calendar.gif"/>&nbsp;<span class="pautanms">dd/mm/yyyy</span></a> 
			                			#end
					        		</td>							              			
		              			</tr>		
			              		<tr>
						      		<td width="1%"></td>
					        		<td width="30%"><div align="left">Tarikh Buka Fail</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%"><input type="text" name="txdTarikhBukaFail" value="$tarikhdaftar" id="txdTarikhBukaFail" size="15"  readonly></td>							              			
		              			</tr>		
		              		</table>						
						</td>
			    	</tr>				    						
                    <tr>
                    	<td colspan=2>&nbsp;</td>
                  	</tr>		      
				</table>  

			  <table>
			  <tr>
			    <td colspan="2">
		        			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
			    	</td>
			  </tr>
			  </table>				
			</fieldset>

		</td>
	</tr>
      
	<tr>
		<td colspan="2" align="center">
		#if ($pageMode == 2 )	
			<input  class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Kemaskini" onclick= "simpanPermohonan($!fail_id)">
		#end
		#if ($pageMode == 0 || $pageMode == 4)	
			<input  class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick= "simpanPermohonan($!fail_id)">
			<input class="stylobutton" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan" onclick="cancel">
		#end
		#if ($pageMode == 2 )	
		<input  class="stylobutton" type="button" name="cmdSeterus" id="cmdSeterus" value="Seterusnya" onclick="nexti($idPermohonan)">
		#end
		<input class="stylobutton" type="button" name="cmdKembali" id="cmdKembali" value="Batal" onclick="backMain()">			
		</td>
	</tr>
	
  </table>  
  	<input type="hidden" name="id_kemaskini" >
   	<input type="hidden" name="fail" value="$idFail">
   	<input type="hidden" name="pagemode" value="$pageMode">
	<input type="hidden" name="socSeksyen" value="$socSeksyen" size="30" >
    <input type="hidden" name="socSuburusan" value="$idsuburusan" size="30">
    <input type="hidden" name="idurusan" value="$idurusan" size="30">
    <input type="hidden" name="idagensi" value="$idagensi" size="30">
   	
<!--</form>
</fieldset>

</table>-->

<script>

function cancel() {
	document.${formName}.reset();
}

function simpanFail(){
	
  	if ( document.${formName}.socNegeri.value == "" ) { 
  		alert('Sila pilih negeri terlebih dahulu.');
  		document.${formName}.socNegeri.focus(); return; 
  	} 
    if ( document.${formName}.socKementerian.value == "" ) { 
  		alert('Sila pilih kementerian terlebih dahulu.');
    	document.${formName}.socKementerian.focus(); 
    	return; 
    } 
   	if ( document.${formName}.socAgensi.value == "" ) { 
  		alert('Sila pilih agensi terlebih dahulu.');
    	document.${formName}.socAgensi.focus(); 
    	return; 
    }
    if ( document.${formName}.txttajuk.value == "" ) { 
    	alert('Sila masukkan maklumat tajuk terlebih dahulu.');
    	document.${formName}.txttajuk.focus(); 
    	return; 
    }
  	if ( document.${formName}.txdTarikhSuratKJP.value == "" ) { 
    	alert('Sila masukkan tarikh surat KJP terlebih dahulu.');
    	document.${formName}.txdTarikhSuratKJP.focus(); 
    	return; 
    }
    if ( document.${formName}.txtNoFailLain.value == "" ) { 
    	alert('Sila masukkan nombor rujukan surat terlebih dahulu.');
    	document.${formName}.txtNoFailLain.focus(); 
    	return; 
    }    
    
    document.${formName}.command.value = "pkfailbaru";
	if(document.${formName}.pagemode.value == "0"){
      	document.${formName}.pagemode.value = "1";
  	}else if(document.${formName}.pagemode.value == "2"){
      	//document.${formName}.command.value = "pkpermohonanbaru";
      	//document.${formName}.command.value = "pkpermohonankemaskini";
      	document.${formName}.id_kemaskini.value = "$permohonanInfo.idpermohonan";
      	document.${formName}.pagemode.value = "3";
  	}else{
  	      	document.${formName}.id_kemaskini.value = "$permohonanInfo.idpermohonan";
  	
  	}
    document.${formName}.method = "post";
	document.${formName}.action = "";
	document.${formName}.submit();


}

function doChangeKementerian() {
	doAjaxCall${formName}("doChangeKementerian");
}

function checkDate() {
	var today = new Date();
	
	dari_bulan = document.${formName}.txdTarikhSuratKJP.value.substring(3,5);
	dari_hari = document.${formName}.txdTarikhSuratKJP.value.substring(0,2);
	dari_tahun = document.${formName}.txdTarikhSuratKJP.value.substring(6,10);
	var daritemp = dari_bulan+"/"+dari_hari+"/"+dari_tahun;
	
	var myDate = Date.parse(daritemp);
	

	if (myDate>today) {
  		alert("Tarikh yang dimasukkan tidak boleh melebihi Tarikh semasa");
 		return;
 	}

}

function nexti(id) {
	document.${formName}.command.value = "pkpendaftaranseterus";
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.method="post";
	document.${formName}.action = "";
	document.${formName}.submit();
}
</script>
