<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>


<script type="text/javascript" src="../../library/js/SpryTabbedPanels.js"></script>
<script type="text/javascript" src="../../library/js/ekptgTools.js"></script>
<script type="text/javascript" src="../../img"></script>
<link rel="stylesheet" type="text/css" href="../../css/SpryTabbedPanels.css">
<style type="text/css">
#parse("css/eTapp_PPK.css")
</style>


<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr><td></td></tr>
	<tr>
  		<td><marquee behavior="scroll" direction="left">Sebarang pertanyaan, sila hubungi Pusat Pertanyaan Pusaka di talian  <b>03-88712999</b></marquee></td>
  	</tr>
	<tr>
    	<td>
    		<fieldset>
    			<legend><strong>Maklumat Pindah Mahkamah</strong></legend>
	    		<table align="left" width="100%" cellspacing="2" cellpadding="1" border="0">
	    		#foreach ($Listflag5juta in $flag5juta)
					#set($check5juta = $Listflag5juta.flag_5juta )
				#end
				
				#if($check5juta == 'T')
					#set($nilaiHartaMaximum = 5000000)
				#else
					#set($nilaiHartaMaximum = 2000000)
				#end
				
	    		#foreach ($senarai in $senaraiMaklumat)
	    			<tr>
				 		<td>&nbsp;</td>
				  	</tr>
				  	#if($nilaiHartaMaximum == 5000000)
					  	#if($!senarai.tujuan_pindah == "2")
			       			#set($tujuan = "Wasiat")
			       		#else
			       			#set($tujuan = "Jumlah Harta > RM 5,000,000")
			       		#end
			       	#else
			       		#if($!senarai.tujuan_pindah == "2")
			       			#set($tujuan = "Wasiat")
			       		#else
			       			#set($tujuan = "Jumlah Harta > RM 2,000,000")
			       		#end
			       	#end
				  	<tr>
				  		<td width="25%">Tujuan Pindah Mahkamah</td>
				  		<td> : </td>
				  		<td><b>$!tujuan</b></td>
				  	</tr>
				  	#foreach ($list in $ListNegeri) 
	     				#if ($list.idNegeri == $senarai.id_negerim)	 
			            	#set($listnamaNegeri = $list.namaNegeri)   
			            #end
			        #end
				  	<tr>
				  		<td>Negeri</td>
				  		<td>:</td>
				  		<td><b>$!listnamaNegeri</b></td>
				  	</tr>
				  	#foreach ($listDaerah in $ListDaerah) 
		    			#if ($listDaerah.idDaerah == $!senarai.id_daerahm)            
	            			#set($listDaerahnamaDaerah=$listDaerah.namaDaerah)
	            		#end
        			#end
				  	<tr>
				  		<td>Daerah</td>
				  		<td>:</td>
				  		<td><b>$!listDaerahnamaDaerah</b></td>
				  	</tr>
				  	
				  	#foreach ($listM in $listMaklumatMahkamahJ)
				    	#if($senarai.id_negerim == $listM.idnegeri && $senarai.id_daerahm == $listM.iddaerah && $listM.jenispejabat == 8 && $listM.id_Pejabat != 193 )				    
							#set ($namapejabat = $listM.nama_pejabat)
							#set ($alamat1pejabat = $listM.alamat1)
							#set ($alamat2pejabat = $listM.alamat2)
							#set ($alamat3pejabat = $listM.alamat3)
							#set ($poskodpejabat = $listM.poskod)
							#set ($notel = $listM.no_tel)
							#set ($nofax = $listM.no_fax)
						#end
					#end

				  	<tr>
					  	<td align="top">Mahkamah</td>
					  	<td align="top"> : </td>
					  	<td><b>
					  	#if($namapejabat != "")
					    	$namapejabat
					    #else
					    	-
					    #end</b>
					  	</td>
				  	</tr>
				  	<tr>
					  	<td align="top">Alamat</td>
					  	<td align="top"> : </td>
					  	<td><b>
						#if( $alamat1pejabat != "")
				      		$alamat1pejabat
				      	#else
				      		-
				      	#end</b>
					  	</td>
				  	</tr>
				  	<tr>
					  	<td align="top"></td>
					  	<td align="top"></td>
					  	<td><b>
						#if( $alamat2pejabat != "")
				      		$alamat2pejabat
				      	#else
				      		
				      	#end</b>
					  	</td>
				  	</tr>
				  	<tr>
					  	<td align="top"></td>
					  	<td align="top"></td>
					  	<td><b>
						#if( $alamat3pejabat != "")
				      		$alamat3pejabat
				      	#else
				      		
				      	#end</b>
					  	</td>
				  	</tr>
				  	<tr>
					  	<td align="top">Poskod</td>
					  	<td align="top"> : </td>
					  	<td><b>
					  	#if( $poskodpejabat != "")
	    				 	$poskodpejabat
	      				 #else
	      				 	-
	      				 #end</b>
					  	</td>
				  	</tr>
				  	<tr>
					  	<td align="top">No Telefon</td>
					  	<td align="top"> : </td>
					  	<td><b>
					  	#if($notel != "")
	    					$notel
	      				#else
	      					-
	      				#end</b>
					  	</td>
				  	</tr>
				  	<tr>
					  	<td align="top">No Fax</td>
					  	<td align="top"> : </td>
					  	<td><b>
					  	#if($nofax != "")
	    					$nofax
	      				#else
	      					-
	      				#end</b>
					  	</td>
				  	</tr>
				  	
				 		<td>&nbsp;</td>
				  	</tr>
				  #end
				</table>
      		</fieldset>
    	</td>
  	</tr>
  	<tr>
 		<td>&nbsp;</td>
  	</tr>
</table>


<script>


function keluar() {
	window.close();
}

</script>


