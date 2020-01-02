<!--frmPajakanKecilSemakanPermohonan.jsp-->
<style type="text/css">
<!--
.style1 {
	font-family: Arial, Helvetica, sans-serif
}
.style3 {font-size: 12px}

.style38 {font-size: 10px}

.style40 {color: #0000FF}
.style44 {color: #FF0000}
.style43 {font-size: 10px; color: #FF0000; }
.style47 {
	font-size: 9px;
	font-style: italic;
}
.style50 {color: #0000FF; font-size: 9px; font-style: italic; }
.style51 {
	color: #0000FF;
	font-size: 9;
}
.style52 {color: #000000}


.stylelabel{color: #0000FF}
-->
</style>
<style type="text/css">
<!--
.pautanms {color: #0000FF}
-->
</style> 

	## 0=
	## 1=
	## 2=view
	## 3=
	## 4=
	#set ( $pagemode = 0 )			
	##set ( $nofailseksyen = $nofail )
	##if ( $nofailseksyen == ""  )
			##set ( $nofailseksyen = "" )	
	##end
	
	#set ($inputstyle = "class=disabled" )
	#set ($inputstyleread = "readonly class=disabled" )
	#set ($selectstyle = "disabled class=disabled" )
	#if ($pageMode == 2 )	
		##set ($inputstyle = "" )
		##set ($selectstyle = "" )
		##set ($inputstylerep = "class=disabled readonly" )
			
	#elseif($pageMode == 3 )	
		#set ($inputstyleread = "" )
		#set ($selectstyle = "" )

	#else
		#set ($inputstyle = "class=disabled" )
		##elseif ( $pageMode != 0  )
		#set ( $pagemode = $pageMode )	
	#end		
<!-- <strong>[CL-02-0403] Pajakan Kecil Tanah/Bangunan</strong>
<br><br> -->
<br/> 
#parse('app/htp/pajakankecil/utiliti/frmPajakanKecilPaging.jsp')

<table width="100%" border="0" cellspacing="2" cellpadding="2">
<tr>
    <td>
		<fieldset><legend>MAKLUMAT PERMOHONAN</legend>
  			<table width="100%">
      		<tr>
				<td width="50%" align="center" valign="top">	
					<table width="100%" border="0">
              			<tr>
			             	<td width="35%" style="text-transform:uppercase;"><div align="right">Negeri</div></td>
			                <td width="1%">:</td>            
			                <td width="64%">
			                	<div align="left" class="stylelabel">
			                			#if ($pageMode == 2 )	
			                				$labelNegeri 
			                			#else	
			                				$socNegeri 
			                			#end
			                	</div>               	
			                </td>  
			          	</tr>
			          	<tr>
			             	<td width="35%" style="text-transform:uppercase;"><div align="right">Kementerian</div></td>
			                <td width="1%">:</td>            
			                <td width="64%">
			               	<div align="left" class="stylelabel">
			                			#if ($pageMode == 2 )	
			                				$labelKementerian 
			                			#else	
			                				$socKementerian 
			                			#end
			                	</div>
							</td>
			          	</tr>
			          	<tr>
			             	<td width="35%" style="text-transform:uppercase;" valign="top"><div align="right">Agensi</div></td>
			                <td width="1%" valign="top">:</td>            
			                <td width="64%">
			               	<div align="left" class="stylelabel">
			                			#if ($pageMode == 2 )	
			                				$labelAgensi 
			                			#else	
			                				$socAgensi 
			                			#end
			                	</div>
							</td>
			           	</tr> 
		              	<tr>
			             	<td width="35%" style="text-transform:uppercase;" valign=top><div align="right">Tajuk</div></td>
			                <td width="1%" valign=top>:</td>            
			                <td width="64%">
			               	<div align="left" class="stylelabel">
			                			#if ($pageMode == 2 )	
			                				$labelTajuk 
			                			#else	
			                  				<textarea name="txttajuk" id="txttajuk" cols="54" rows="3" onkeyup="this.value=this.value.toUpperCase();" $inputstyleread>$permohonanInfo.tujuan</textarea>
			                			#end
			                	</div>
							</td>
		              	</tr>
         			</table>
         		</td>
        
				<td width="50%" align="center" valign="top">	
					<table width="100%" border="0">
						<tr>
			               <td width="35%" style="text-transform:uppercase;"><div align="right">No. Fail Seksyen</div></td>
			                <td width="1%">:</td>
			                <td width="64%">
			                	<div align="left" class="stylelabel">$labelNoFail</div>               	
							</td>		
			            </tr> 
			    		<tr>
			               <td width="35%" style="text-transform:uppercase;"><div align="right">No. Fail Lain</div></td>
			                <td width="1%">:</td>
			                <td width="64%">
			                	        #if ($pageMode == 2 )	
			                	<div align="left" class="stylelabel">$labelNoFailLain</div>               	
			                			#else	
			                	<input type="text" name="txtNoFailLain" size="30" id="txtNoFailLain" value="$permohonanInfo.rujukanlain" $inputstyleread>
			                			#end
							</td>		
			          	</tr>               
    					<tr>
			               <td width="35%" style="text-transform:uppercase;"><div align="right">Tarikh Surat KJP</div></td>
			                <td width="1%">:</td>
			                <td width="64%">
			                	        #if ($pageMode == 2 )	
			                	<div align="left" class="stylelabel">$labelTarikhSuratKJP</div>               	
			                			#else	
			                	<input type="text" name="txtNoFailLain" size="30" id="txdTarikhSurKJP" value="$permohonanInfo.tsurat" $inputstyleread>
			                			#end
							</td>		
              			</tr> 
     					<tr>
			               <td width="35%" style="text-transform:uppercase;"><div align="right">Tarikh Buka Fail</div></td>
			                <td width="1%">:</td>
			                <td width="64%">
			                	        #if ($pageMode == 2 )	
			                	<div align="left" class="stylelabel">$labelTarikhBukaFail</div>               	
			                			#else	
			                	<input type="text" name="txtNoFailLain" size="30" id="txdTarikhBukaFail" value="$permohonanInfo.tdaftar" $inputstyleread>
			                			#end
							</td>		
			          	</tr>              
        			</table>
        		</td>
      		</tr>
      		
      		<tr>
        		<td colspan="2"></td>
      		</tr>
  		</table>  
   	<input type="hidden" name="id_kemaskini" >
   	<input type="hidden" name="fail" >
   	<input type="hidden" name="langkah" value="0" >
	<input type="hidden" name="pagemode" value="$pageMode">

		</fieldset>
	</td>
</tr>

<tr>
	<td>	
		<fieldset><legend><strong>SENARAI SEMAKAN</strong></legend>
			<!-- <table width="100%" >
				<tr>
				  	<td width="100%"> -->
			 <!-- $!permohonanInfo.idnegeri -->
			<table width="100%" >
				#set ( $checked = "" )
			    #foreach ( $semak in $senaraiSemakan )
			    #set( $i = $velocityCount )
			    #if ( ($i % 2) == 0 )
			    	#set( $row = "row2" )
			    #else
			    	#set( $row = "row1" )
			   	#end
				<tr>
			    	<td class="$row" width="10">
                   
			        #if ( $semakclass.isSemakan("$permohonanInfo.idpermohonan", "$semak.id" ))
			        	#set ( $checked = "checked" )
			        #else
			        	#set ( $checked = "" )
			    	#end
			        	 <input class="cb" type="checkbox" name="cbsemaks" value="$semak.id" $checked $selectstyle>
			       	</td>
			        <td class="$row">
			        	$semak.keterangan <!-- $semak.id -->
			        </td>
			
			 	</tr>       
			    #end			  	
			</table>
		</fieldset>			
	</td>
</tr>
			


<!--		</td>
	</tr>-->
							<tr>
							<td align="center">
								#if ($pageMode == 2 )	
									<input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="senaraiPermohonan('$permohonanInfo.idfail','$permohonanInfo.idpermohonan')"> 
									
									#if (!$!jenisAkses.equals('Readonly'))
									<input type="button" class="stylobutton100" name="cmdSimpan" id="cmdSimpan" value="Kemaskini " onclick="semakanPermohonanTambah('$permohonanInfo.idpermohonan')">
   									#end	
   									<input type="button" class="stylobutton100" name="Cetak" id="Cetak" value="Cetak" onclick="javascript:senaraiSuratSkrinSemakan('tablesuratp');" />
									<input type="button" class="stylobutton100" name="cmdSeterusnya" id="cmdsblm" value="Seterusnya" onclick="skrinSenaraiHakmilikPemilik('$permohonanInfo.idpermohonan')">
								#else
									<input type="button" class="stylobutton100" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="semakanPermohonanTambah1('$permohonanInfo.idpermohonan','simpan')">	
									<input type="button" class="stylobutton100" name="cmdKembali" id="cmdKembali" value="Batal" onclick="skrinSemakanPermohonan('$permohonanInfo.idpermohonan')">
 								#end
								<!--Add on 2010/06/01 for print Checklist 1st-->
		        								
								<!--<input type="reset" name="cmdBatal" id="cmdBatal" value="Batal">&nbsp; -->
								<!-- Komen pada 11/06/2010 
								<input class="stylobutton" type="button" name="cmdKembali" id="cmdKembali" value="Sebelumnya" onclick="backPreSenaraiPermohonan('$permohonanInfo.idfail','$permohonanInfo.idpermohonan')">
								-->
								<!--<input class="stylobutton" type="button" name="cmdFail" id="cmdKembali" value="Senarai Fail" onclick="backMain()"> -->
							</td>
					      </tr>			
<!--	<tr>
  		<td colspan="2">
        	<p align="right">CL - 02 - 0403</p>
      	</td>
	</tr>	--> 
</table>

 <fieldset id="tablesuratp" style="display:none;">
<legend>SENARAI SURAT/DOKUMEN</legend>
<table width="100%" border="0">
  <tr>
  #if ($!permohonanInfo.idnegeri==12)
    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=38&idpermohonan=$permohonanInfo.idpermohonan','urusan','&template=HTPPajakanKecilSenaraiSemak12')" class="pautanms">SENARAI SEMAKAN</a></td>
  #else
    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=38&idpermohonan=$permohonanInfo.idpermohonan','urusan','&template=HTPPajakanKecilSenaraiSemak13')" class="pautanms">SENARAI SEMAKAN</a></td>
  
  #end
  </tr>
  <tr>
    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=38&idpermohonan=$permohonanInfo.idpermohonan','urusan','&template=HTPPajakanKecilMemoPKP')" class="pautanms">MEMO KEPADA PEGUAM KANAN PERSEKUTUAN</a></td>
  </tr>  
  <tr>
    <td><a href="javascript:openSuratPegawai('ekptg.report.htp.SuratUtamaHTP','idsuburusan=38&idpermohonan=$permohonanInfo.idpermohonan','urusan','&template=HTPPajakanKecilSuratMaklumanBebanan')" class="pautanms">SURAT MAKLUMAN PERJANJIAN ADA BEBANAN</a></td>
  </tr>   
  </table>
</fieldset>
<script>
	/*
function semakanPTambah(id) {
	document.${formName}.command.value = "pkpermohonankemaskini";

	if ( document.${formName}.cmdSimpan.value == "Kemaskini" ) {
		//document.${formName}.cmdSimpan.value = "Simpan";	
		document.${formName}.pagemode.value="3";
	}else {
		//document.${formName}.command.value = "semakankemaskini";
		//document.${formName}.command.value = "pksenaraipermohonan";
		document.${formName}.pagemode.value="4";
	}

	//document.${formName}.command.value = "pkpermohonankemaskini";
	//document.${formName}.method="post";
	document.${formName}.id_kemaskini.value = id;
	//document.${formName}.fail.value = id;
	document.${formName}.action = "";
	document.${formName}.submit();
}

function kemaskiniPermohonan(id) {
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.command.value = "update";
	document.${formName}.method="post";
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
	//document.${formName}.command.value = "semakanseterus";
	document.${formName}.method="post";
	document.${formName}.action = "";
	document.${formName}.submit();
}

*/
</script>
