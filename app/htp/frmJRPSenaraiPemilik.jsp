<!--<strong>[CL-02-0405] Pajakan Kecil Tanah/Bangunan</strong>
<br><br>
-->
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>


#parse ("app/htp/frmJRPInfo.jsp")			

<fieldset>
<legend><strong>SENARAI HAKMILIK </strong></legend>
  	<table width="100%" >
		<tr>
        	<td width="100%">
  				<input class="stylobutton" type="submit" name="cmdTambah" id="cmdTambah" value="Tambah" onclick="skrinTambahHakmilik('$permohonanInfo.idpermohonan')">      		
	        </td>
 		<tr>	        
 		<tr>
        	<td width="100%">
  			<table width="100%" >
			  <tr class="table_header">
			  	<td width="3%">NO</td>
			  	<td width="16%">NEGERI</td>
			  	<td width="15%">DAERAH</td>
			  	<td width="15%">MUKIM</td>
			  	<td width="24%">NO. HAKMILIK</td>
			  	<td width="17%">NO. PT/LOT</td>
			  	<td width="10%">TAMBAH PEMILIK</td>
			  	<!--<td width="6%"></td>-->
			  </tr>
  
  				#set ( $cnth = 0 )			
  				#foreach ( $senarai in $senaraihakmilik )
  				#set ( $cnth = $cnth + 1 )
              	#set( $i = $velocityCount )
                #if ( ($i % 2) == 0 )
                    #set( $row = "row2" )
                #else
                    #set( $row = "row1" )
                #end

				  <tr>
				  <td class="$row">$cnth.</td>
				  <td class="$row">$senarai.namanegeri</td>
				  <td class="$row">$senarai.namadaerah</td>
				  <td class="$row">$senarai.namamukim</td>
				  <td class="$row">
				  	$senarai.kodjenishakmilik<a href="javascript:kemaskiniHakmilik('$senarai.idhakmilikurusan','$permohonanInfo.idpermohonan')">$senarai.nohakmilik	</a>  
				  </td>
				  <td class="$row">$senarai.keterangan$senarai.nolot</td>
				  <td class="$row">
				  	<input type="submit" name="cmdTambah2" id="cmdTambah2" value="[+]" onclick="tambahHakmilik2('$permohonanInfo.idpermohonan','$senarai.idhakmilikurusan')"/>
				  </td>
				  	<!--<td>
				  
						    <table width="100%" cellspacing="0" cellpadding="0" border="0">
						
						  #set ( $cnt = 0 )		
						  ##set ($senaraiPemilik = $frmpk.getSenaraiPemilik($permohonanInfo.idpermohonan,$senarai.idhakmilikurusan))
						  #foreach ( $senarai in $senaraiPemilik )
						  	#set ( $cnt = $cnt + 1 )
						
						  <tr>
						  <td>$cnt.</td>
						  <td>
						  	<a href="javascript:kemaskiniPemilik('$permohonanInfo.idpermohonan','$senarai.idpihakberkepentingan')">
							$senarai.nama			</a>		  </td>
						  <td>$senarai.norujukan</td>
						  <td></td>
						  </tr>
						  #end
						  </table>  
					</td> -->
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
  
  <fieldset>
  <legend><strong>SENARAI PEMILIK</strong></legend>
  <table width="100%" >
  			<tr>
        	<td width="100%">
  			<table width="100%" >
  	
			  <tr class="table_header">
			  	<td>NO</td>
			  	<td>NAMA PEMILIK</td>
			  	<td>NO. KP/POLIS/TENTERA/SYARIKAT</td>
			  </tr>
  
			  #set ( $cnts = 0 )			
			  #foreach ( $senarai in $senaraipemilik )
			  	#set ( $cnts = $cnts + 1 )
	            #set( $i = $velocityCount )
                #if ( ($i % 2) == 0 )
                    #set( $row = "row2" )
                #else
                    #set( $row = "row1" )
                #end
			
			  <tr>
			  <td class="$row" width="3%">$cnts.</td>
			  <td class="$row" width="43%">
			  	<a href="javascript:kemaskiniPemilik('$permohonanInfo.idpermohonan','$senarai.idpihakberkepentingan')">
				$senarai.nama
				</a>
			  </td>
			  <td class="$row" width="54%">$senarai.norujukan</td>
			  </tr>
  			#end
				#if ($cnts == 0)
				<tr> 
					<td colspan="3" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
				</tr> 
  				#end
  			</table>
		</td>
	</tr>
 	</table>
 	
    <table width="100%" cellspacing="0" cellpadding="0" border="0">
    	<tr>
		<td align="center">
			
			<input class="stylobutton" type="button" name="cmdKembali" id="cmdKembali" value="Sebelumnya" onclick="skrinKemaskiniPermohonan('$permohonanInfo.idpermohonan')">
			<input class="stylobutton" type="button" name="cmdSeterusnya" id="cmdsblm" value="Seterusnya" onclick="nexti2('$permohonanInfo.idpermohonan')">
			<!--<input type="reset" name="cmdBatal" id="cmdBatal" value="Batal">&nbsp; 
			<input class="stylobutton" type="button" name="cmdFail" id="cmdKembali" value="Senarai Fail" onclick="backMain()"> -->
		</td>
      	</tr> 		
      <tr>
                <td>
                <p align="right">CL - 04 - 08</p>
                </td>
                </tr>  
 	 </table>
  

   	<input type="hidden" name="id_kemaskini">
    <input type="hidden" name="fail" >
   	<input type="hidden" name="pagemode" >
    <input type="hidden" name="langkah" value="0" >
    
</fieldset>
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
