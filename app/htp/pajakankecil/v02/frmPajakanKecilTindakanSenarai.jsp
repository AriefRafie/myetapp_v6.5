<!-- frmPajakanKecilTindakanSenarai.jsp -->
<!--<strong>[CL-02-0405] Pajakan Kecil Tanah/Bangunan</strong>
<br><br>
-->
<style type="text/css">
<!--
	.pautanms {color: #0000FF}
-->
</style>
<!----> <br/> 
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
				<legend><strong>SENARAI MAKLUMAT TINDAKAN </strong></legend>
  					<table width="100%" >
			#if (!$!jenisAkses.equals('Readonly'))
						<tr>
				        	<td width="100%">
				  				<input type="button" class="stylobutton100" name="cmdTambah" value="Tambah" onclick="skrinTindakanTambah('$permohonanInfo.idpermohonan')">      		
					        </td>
			#end
				 		<tr>	        
				 		<tr>
				        	<td width="100%">
				  			<table width="100%" >
							  <tr class="table_header">
							  	<td width="3%"><b>Bil.</b></td>
							  	<td width="74%"><b>Catatan</b></td>
							  	<td width="10%"><b>Tarikh Catatan</b></td>
							  	<td width="10%"><b>Tarikh Daftar</b></td>
							  	<td width="3%">&nbsp;</td>
							  </tr>
				              
				              	#set ( $cnth = 0 )			
				  				#foreach ( $senarai in $senaraiTindakan )
				  				#set ( $cnth = $cnth + 1 )
			        			#set( $i = $velocityCount )
				                #if ( ($i % 2) == 0 )
				                    #set( $row = "row2" )
				                #else
				                    #set( $row = "row1" )
				                #end
					                
							  <tr>
							    <td class="$row">$senarai.bil.</td>
							    <td class="$row">
							    	<a href="javascript:viewTindakan('$senarai.idSusulanStatus','$senarai.idStatusFail','$senarai.idSusulan','$permohonanInfo.idpermohonan')" class="pautanms">$senarai.keterangan </a>
						   		 <div class="pautanms"><b><blink>$!senarai.status</blink></b></div>
							    </td>
							    <td class="$row">$senarai.tarikh</td>
							    <td class="$row">$senarai.tarikh</td>
							    <td class="$row">
							#if($senarai.bil !=0)
			    				#if($!senarai.status == '')
			    				
						   		<a alt="Hapus" href = "javascript:hapusTindakan('$permohonanInfo.idpermohonan','$senarai.idSusulan')">
						   			<img border="0" src="../img/delete.gif" />
						   		</a>
						   		#end
						   	#end
							    </td>
						
						        </tr>
								  #end
								#if ($cnth == 0)
								<tr> 
									<td colspan="5" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
								</tr> 
				  				#end
								  	
				  			</table>
						</td>
					</tr>
				</table>
			</fieldset>

			<p align="center">
				<div align="center">
        			<input class="stylobutton100" type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="skrinKemaskiniPermohonan('$permohonanInfo.idpermohonan')">
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
