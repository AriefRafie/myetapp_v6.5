<!--frmJRPPendaftaran.jsp-->	
<style type="text/css">
<!--
	.pautanms {color: #0000FF}
-->
</style>
	## 0=First View
	## 1=save
	## 2=view	
	## 2=update
	## 3=delete
	## 4=simpan permohonan
	#set ( $pagemode = 0 )			
	#set ( $nofailseksyen = $nofail )
	#set ( $nofaillain = ""  )
	#set ( $tarikhsurat = "" )	
	#set ( $tarikhdaftar= $sekarang )	
    #set ($classDis = "" )

	##set ($inputstyle = "class=disabled" )
	#set ($inputstyle = "" )
	#set ($inputstylerep = "" )
	#set ($inputstyledate = "class=disabled" )
	#set ($selectstyle = "" )
	#set ($cbstyle = "" )
	
	#set ($tajuk = "")
	#set ($idFail = "")
	#if ($pageMode == 2 )	
		##set ($inputstyle = "" )
		##set ($selectstyle = "" )
		#set ($inputstylerep = "class=disabled readonly" )
		#set ($cbstyle = "disabled class=disabled" )
        #set ($classDis = "class='disabled' disabled='disabled'" )
        
		
		#set ($tajuk = $permohonanInfo.tujuan)	
		#set ( $nofailseksyen = $permohonanInfo.fail)
		#set ( $nofaillain = $permohonanInfo.rujukanlain)
		#set ( $tarikhsurat = $permohonanInfo.tsurat)	
		#set ( $tarikhdaftar = $permohonanInfo.tdaftar)	
		##set ( $tarikhsurat = $util.getDateTime($permohonanInfo.tsurat, "dd/MM/yyyy"))	
		##set ( $tarikhdaftar = $util.getDateTime($permohonanInfo.tdaftar, "dd/MM/yyyy")) 	
	#elseif($pageMode == 4)	
	    #set ($inputstylerep = "class=disabled readonly" )	
        #set ($classDis = "" )
		#set ($tajuk = $permohonanInfo.tujuan)	
		#set ( $nofailseksyen = $permohonanInfo.fail)
		#set ( $nofaillain = $permohonanInfo.rujukanlain)
		##set ( $tarikhsurat = $util.getDateTime($permohonanInfo.tsurat, "dd/MM/yyyy"))	
		##set ( $tarikhdaftar = $util.getDateTime($permohonanInfo.tdaftar, "dd/MM/yyyy"))
		#set ( $tarikhsurat = $permohonanInfo.tsurat)	
		#set ( $tarikhdaftar = $permohonanInfo.tdaftar)	
		#set ( $idFail = $permohonanInfo.idFail)
	#else
		##set ($inputstyle = "class=disabled" )
		##elseif ( $pageMode != 0  )
		#set ( $pagemode = $pageMode )	
	#end		

<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
   		<td>&nbsp;</td>
	</tr>

	<tr>
    	<td> 
    		
			<fieldset><legend>PENDAFTARAN FAIL</legend>
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
										<textarea name="txttajuk" cols="41" rows="5" onblur="this.value=this.value.toUpperCase();"  $classDis>$tajuk</textarea>					        		
					        		</td>					
			              		</tr>			              			        		
		              		</table>
		              		
			        	</td>
			        	
			        	<!-- Right-->
			        	<td width="50%" align="center" valign="top">
			        		<table width="100%" border="0">
			              		<tr>
						      		<td width="1%"></td>
					        		<td width="30%"><div align="left">No. Fail</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%"><input type="text" name="txtNoFailSek" class="disabled" size="30" value="$nofailseksyen"  $inputstylerep onblur="this.value=this.value.toUpperCase();" disabled></td>							              			
		              			</tr>		
			              		<tr>
						      		<td width="1%"><span class="labelmandatory">*</span></td>
						      		<!-- <td width="1%"></td>  -->
					        		<td width="30%"><div align="left">No. Rujukan Kami</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%"><input type="text" name="txtNoFailLain" size="30" value="$nofaillain" id="txtNoFailLain" onblur="this.value=this.value.toUpperCase();" $classDis></td>							              			
		              			</tr>			              			        			
			              		<tr>
						      		<td width="1%"><span class="labelmandatory">*</span></td>
						      		<!-- <td width="1%"></td>  -->
					        		<td width="30%"><div align="left">Tarikh Surat KJP</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%">
					        			<input type="text" name="txdTarikhSuratKJP" value="$tarikhsurat" id="txdTarikhSurKJP" size="11" onkeyup="validateNumber(this,this.value);" onblur="check_date(this);checkDate();" $classDis>
					        			#if ($pageMode != 2 )	
			                				<a href="javascript:displayDatePicker('txdTarikhSuratKJP',false,'dmy');"><img border="0" src="../img/calendar.gif"/>&nbsp;<span class="pautanms">dd/mm/yyyy</span></a> 
			                			#end
					        		</td>							              			
		              			</tr>		
			              		<tr>
						      		<td width="1%"></td>
					        		<td width="30%"><div align="left">Tarikh Buka Fail</div></td>
					        		<td width="1%"><div align="center">:</div></td>
					        		<td width="68%"><input type="text" name="txdTarikhBukaFail" value="$tarikhdaftar" id="txdTarikhBukaFail" size="11"  readonly $inputstyledate></td>							              			
		              			</tr>		
		              		</table>						
						</td>
			    	</tr>				    						
                    <tr>
                    	<td colspan=2>&nbsp;</td>
                  	</tr>		      
				</table>  

			 <!-- <table>

			  </table>	-->			
			<input type="hidden" name="id_kemaskini" value="$permohonanInfo.idpermohonan" >
			<input type="hidden" name="fail" value="$idFail">
			<input type="hidden" name="pagemode" value="$pageMode">
			<input type="hidden" name="socSeksyen" value="$socSeksyen" size="30" >
			<input type="hidden" name="socSuburusan" value="$idsuburusan" size="30">
			<input type="hidden" name="idurusan" value="$idurusan" size="30">
			<input type="hidden" name="idagensi" value="$idagensi" size="30">
			<input type="hidden" name="idFailN" value="$idFailN">
			   	
			</fieldset>

		</td>
	</tr>
	
	<tr>
		<td align="left">
			<span class="labelwar"><em><span class="labelmandatory">Perhatian</span> : Sila pastikan label bertanda <span class="labelmandatory">*</span> diisi.</em></span>
		</td>
	</tr>
	
	<tr>		
		<td align=center>
		#if ($pageMode == 2 )	
			<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Kemaskini" onclick= "simpanFail()">
			<input class="stylobutton100" type="button"  onclick="javascript:cetakKulitFail('$idPermohonan');" value="Cetak Kulit Fail" />
			<!-- <input type="button" class="stylobutton" onclick="javascript:cetakDoket('$idPermohonan');" value="Cetak Doket" />	-->    
		#end
		#if ($pageMode == 0 || $pageMode == 4)	
			<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick= "simpanFail()">
			<input class="stylobutton100" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan" onclick="cancel">
		#end
		#if ($pageMode == 2 )	
			<input class="stylobutton100" type="button" name="cmdSeterus" id="cmdSeterus" value="Seterusnya" onclick="nexti($idPermohonan)">
		#end
			<input class="stylobutton100" type="button" name="cmdKembali" id="cmdKembali" value="Batal" onclick="backMain()">			
		</td>
	</tr>	
</table>

<script>

function cancel() {
	document.${formName}.reset();
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
