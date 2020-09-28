<!-- frmPajakanKecilSenaraiHakmilikPemilik2.jsp -->
<!--<strong>[CL-02-0405] Pajakan Kecil Tanah/Bangunan</strong>
<br><br>
-->
<style type="text/css">
<!--
	.pautanms {color: #0000FF}
-->
</style>
<br/> 
#parse('app/htp/frmPajakanKecilPaging.jsp')

<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>
		#parse ("app/htp/frmJRPInfo.jsp")			
			
		</td>
	</tr>	

  	<tr>
    	<td>
			<fieldset>
				<legend><strong>SENARAI HAKMILIK </strong></legend>
  					<table width="100%" >
						<tr>
				        	<td width="100%">
				  				<input class="stylobutton100" type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onclick="skrinTambahHakmilikPemilik('$permohonanInfo.idpermohonan')">      		
					        </td>
				 		<tr>	        
				 		<tr>
				        	<td width="100%">
				  			<table width="100%" >
							  <tr class="table_header">
							  	<td width="3%">BIL.</td>
							  	<td width="16%">NEGERI</td>
							  	<td width="15%">DAERAH</td>
							  	<td width="15%">MUKIM</td>
							  	<td width="24%">NO. HAKMILIK</td>
							  	<td width="10%">NO. PT/LOT</td>
							  	<td width="17%">&nbsp;</td>
							  	<!--<td width="6%"></td>-->
							  </tr>
				              
				              	#set ( $cnth = 0 )			
				  				#foreach ( $senarai in $senaraihakmilik )
				  				#set ( $cnth = $cnth + 1 )
				                
							  <tr>
							    <td class="row1">$cnth.</td>
							    <td class="row1"><a href="javascript:viewTanah('$senarai.idhakmilikurusan','$permohonanInfo.idpermohonan')" class="pautanms">$senarai.namanegeri</a></td>
							    <td class="row1">$senarai.namadaerah</td>
							    <td class="row1">$senarai.namamukim</td>
							    <td class="row1"> $senarai.kodjenishakmilik $senarai.nohakmilik</td>
							    <td class="row1">$senarai.keterangan $senarai.nolot</td>
							    <td class="row1">
							    	<input type="button" name="cmdTambah3" id="cmdTambah3" value="Tambah Pemilik" onclick="tambahHakmilik2('$permohonanInfo.idpermohonan','$senarai.idhakmilikurusan')"/>
							    	<input type="button" name="cmdTambah2" id="cmdTambah2" value="[X]" onclick="deleteHakmilik('$permohonanInfo.idpermohonan','$senarai.idhakmilikurusan')"/>
							    </td>
							    </tr>			
				
								    <tr>
				                    
								      <td class="row2">&nbsp;</td>
								      <td colspan="6" class="row2"><table width="100%" >
								        
				                        
				                        #set ( $cnts = 0 )			
				                        
				                        #foreach ( $senaraip in $senarai.pihaks )
											#set ( $cnts = $cnts + 1 )
								        <tr>
								          <td class="row2" width="5%"><strong>$cnts.</strong></td>
								          <td class="row2" width="39%"><strong>
								            <!-- <a href="javascript:kemaskiniPemilik('$permohonanInfo.idpermohonan','$senarai.idpihakberkepentingan')">  -->
				                   
				                               <a href="javascript:viewPemilik('$permohonanInfo.idpermohonan','$senaraip.idpihakberkepentingan')" class="pautanms">
								            $senaraip.nama -  $senaraip.noRujukan
				                            </a>
				                          
				                             </strong>
				                            </td>
								         <!-- <td class="row2" width="29%"><strong>$senaraip.noRujukan</strong></td> $senaraip.idHakmilikurusanPB  --> 
								          <td width="27%" class="row2" align="left">
								          	
								          		<input type="button" name="cmdTambah2" id="cmdTambah2" value="Tambah Hakmilik" onclick="tambahHakmiliktoPemilik('$permohonanInfo.idpermohonan','$senaraip.idpihakberkepentingan')"/>
								          		<input type="button" name="cmdTambah2" id="cmdTambah2" value="[X]" onclick="deletePemilik('$permohonanInfo.idpermohonan','$senaraip.idHakmilikurusanPB')"/>
								          	
								          </td>
							            </tr>
								        #end
								        #if ($cnts == 0)
										  <tr>
										    <td colspan="4" scope="row"><font color="#FF0000">Tiada Pemilik.</font></td>
										  </tr>
								        #end
							          </table></td>
						        </tr>
								  #end
								#if ($cnth == 0)
								<tr> 
									<td colspan="7" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
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
        			<input type="button" class="stylobutton100" name="cmdSeterusnya" id="cmdsblm" value="Seterusnya" onclick="skrinMaklumatSewaan('$permohonanInfo.idpermohonan')">
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
