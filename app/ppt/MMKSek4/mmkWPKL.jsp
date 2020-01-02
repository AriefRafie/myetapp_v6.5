

#set($txtBUTIRASAS1 = "Pesuruhjaya Tanah Persekutuan bagi pihak Ketua Setiausaha, "+$kementerian+".")
#set($txtBUTIRASAS2 = "Pengambilan tanah di bawah Seksyen 3(1)(a) dan pewartaan di bawah Seksyen 8 Akta Pengambilan Tanah 1960.")
#set($txtBUTIRASAS3 = $sysdateMalaytarikhMohon+".")
#set($txtBUTIRASAS4 = "Seperti pelan di Lampiran \'A\' dan Jadual Lot di Lampiran \'B\'.")
#set($txtBUTIRASAS5 = $totaLLot + " Lot.")
#set($txtBUTIRASAS6 = "Kesemua tanah lot-lot yang terlibat.")
#set($txtBUTIRASAS7 = $nama_projek_tujuan)
#set($txtBUTIRASAS8 = "")




<br />
<font color="blue">* Terdapat Perubahan pada skrin & cetakan Format kertas MMK Wilayah Persekutuan Kuala Lumpur didalam sistem MyEtapp. Untuk cetakan kertas MMK melalui MyEtapp, sila kemaskini maklumat diskrin ini.  </font>


<fieldset>
<legend><strong>Penyediaan Kertas MMK/MB/KM/LC</strong></legend>

	<table width="100%" border="0">
    
    
    <tr>
				  <td><b>1. BUTIR-BUTIR ASAS</b></td></tr>
                  
				  <tr>
				  <td>                    
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 	<b>1.1 Pemohon</b>
					
					#if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
    					#if($list.FLAG_JENIS_MMK == "BUTIRASAS1" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    					<input type="hidden" name="BUTIRASAS1_MAIN_temp1"  id="BUTIRASAS1_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end     	
    				<span id="BUTIRASAS1_MAIN"></span>           
   					<div id="BUTIRASAS1_MAIN_temp"></div>					
					</td>
				    </tr>
                    
                     <tr>
				  <td>                    
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 	<b>1.2 Tujuan</b>
					
					#if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
    					#if($list.FLAG_JENIS_MMK == "BUTIRASAS2" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    					<input type="hidden" name="BUTIRASAS2_MAIN_temp1"  id="BUTIRASAS2_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end     	
    				<span id="BUTIRASAS2_MAIN"></span>           
   					<div id="BUTIRASAS2_MAIN_temp"></div>					
					</td>
				    </tr>
                    
                     <tr>
				  <td>                    
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 	<b>1.3 Tarikh Permohonan</b>
					
					#if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
    					#if($list.FLAG_JENIS_MMK == "BUTIRASAS3" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    					<input type="hidden" name="BUTIRASAS3_MAIN_temp1"  id="BUTIRASAS3_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end     	
    				<span id="BUTIRASAS3_MAIN"></span>           
   					<div id="BUTIRASAS3_MAIN_temp"></div>					
					</td>
				    </tr>
                
                
                 <tr>
				  <td>                    
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 	<b>1.4 Lot-Lot Terlibat</b>
					
					#if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
    					#if($list.FLAG_JENIS_MMK == "BUTIRASAS4" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    					<input type="hidden" name="BUTIRASAS4_MAIN_temp1"  id="BUTIRASAS4_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end     	
    				<span id="BUTIRASAS4_MAIN"></span>           
   					<div id="BUTIRASAS4_MAIN_temp"></div>					
					</td>
				    </tr>
                    
                    
                   <tr>
				   <td>                    
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 	<b>1.5 Bilangan Lot</b>
					
					#if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
    					#if($list.FLAG_JENIS_MMK == "BUTIRASAS5" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    					<input type="hidden" name="BUTIRASAS5_MAIN_temp1"  id="BUTIRASAS5_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end     	
    				<span id="BUTIRASAS5_MAIN"></span>           
   					<div id="BUTIRASAS5_MAIN_temp"></div>					
					</td>
				    </tr>
                    
                    
                   <tr>
				   <td>                    
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 	<b>1.6 Luas Tanah Yang Perlu Diambil</b>
					
					#if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
    					#if($list.FLAG_JENIS_MMK == "BUTIRASAS6" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    					<input type="hidden" name="BUTIRASAS6_MAIN_temp1"  id="BUTIRASAS6_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end     	
    				<span id="BUTIRASAS6_MAIN"></span>           
   					<div id="BUTIRASAS6_MAIN_temp"></div>					
					</td>
				    </tr>
                    
                   <tr>
				   <td>                    
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 	<b>1.7 Projek</b>
					
					#if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
    					#if($list.FLAG_JENIS_MMK == "BUTIRASAS7" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    					<input type="hidden" name="BUTIRASAS7_MAIN_temp1"  id="BUTIRASAS7_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end     	
    				<span id="BUTIRASAS7_MAIN"></span>           
   					<div id="BUTIRASAS7_MAIN_temp"></div>					
					</td>
				    </tr>
               
               
               	   <tr>
				   <td>                    
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 	<b>1.8 Lokasi</b>
					
					#if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)  
                         
    					#if($list.FLAG_JENIS_MMK == "BUTIRASAS8" && $list.FLAG_JENIS_SUBMMK == "MAIN" )  
                               
    					<input type="hidden" name="BUTIRASAS8_MAIN_temp1"  id="BUTIRASAS8_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end     	
    				<span id="BUTIRASAS8_MAIN"></span>           
   					<div id="BUTIRASAS8_MAIN_temp"></div>					
					</td>
      </tr>
    
    
    
    

	<!-- BUTIR - BUTIR ASAS -->	
    <!--
	<tr><td><b>1. BUTIR - BUTIR ASAS :</b></td></tr>

	<tr>
		<td>

		#if($senarai_submmk.size()!=0)
    		#foreach($list in $senarai_submmk)   
    		#if($list.FLAG_JENIS_MMK == "BUTIRASAS" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    		<input type="hidden" name="BUTIRASAS_MAIN_temp1"  id="BUTIRASAS_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    		#end
    		#end
    	#end 
    	
    	
    	<span id="BUTIRASAS_MAIN"></span>           
   		<div id="BUTIRASAS_MAIN_temp"></div>
    
		</td>

	</tr>
-->
	<tr><td>&nbsp;</td></tr>
		
	<!-- TUJUAN -->	
	<tr><td><b>2. TUJUAN :</b></td></tr>
	<tr>
		<td>

		#if($senarai_submmk.size()!=0)
    		#foreach($list in $senarai_submmk)   
    		#if($list.FLAG_JENIS_MMK == "TUJUAN" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    		<input type="hidden" name="TUJUAN_MAIN_temp1"  id="TUJUAN_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    		#end
    		#end
    	#end 
    	
    	
    	<span id="TUJUAN_MAIN"></span>           
   		<div id="TUJUAN_MAIN_temp"></div>
    
		</td>

	</tr>

	<tr><td>&nbsp;</td></tr>

	<!-- LATAR BELAKANG -->	
	<tr><td><b>3. LATAR BELAKANG :</b></td></tr>
	<tr>
		<td>

		#if($senarai_submmk.size()!=0)
    		#foreach($list in $senarai_submmk)   
    		#if($list.FLAG_JENIS_MMK == "LATARBELAKANG" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    		<input type="hidden" name="LATARBELAKANG_MAIN_temp1"  id="LATARBELAKANG_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    		#end
    		#end
    	#end 
    	
    	
    	<span id="LATARBELAKANG_MAIN"></span>           
   		<div id="LATARBELAKANG_MAIN_temp"></div>
    
		</td>

	</tr>

	<tr><td>&nbsp;</td></tr>


	<!-- LAPORAN TANAH -->	
	<tr><td><b>4. LAPORAN TANAH :</b></td></tr>
    <tr>
				  <td>                    
                   #if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
    					#if($list.FLAG_JENIS_MMK == "LAPORANTANAH" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    					<input type="hidden" name="LAPORANTANAH_MAIN_temp1"  id="LAPORANTANAH_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end     	
    				<span id="LAPORANTANAH_MAIN"></span>           
   					<div id="LAPORANTANAH_MAIN_temp"></div>			
					</td>
				    </tr>
    
	

	<tr><td>&nbsp;</td></tr>
	
	<!-- KEADAAN TANAH -->	
	
	
	<!-- BUTIR - BUTIR TANAH-->	
	<tr><td><b>5. KEADAAN TANAH :</b></td></tr>
	<tr>
		<td>

		#if($senarai_submmk.size()!=0)
    		#foreach($list in $senarai_submmk)   
    		#if($list.FLAG_JENIS_MMK == "PERIHALTANAH" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    		<input type="hidden" name="PERIHALTANAH_MAIN_temp1"  id="PERIHALTANAH_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    		#end
    		#end
    	#end 
    	
    	
    	<span id="PERIHALTANAH_MAIN"></span>           
   		<div id="PERIHALTANAH_MAIN_temp"></div>
    
		</td>

	</tr>

	<tr><td>&nbsp;</td></tr>
	
	<!-- KEMUDAHAN ASAS-->	
	<tr><td><b>6. KEMUDAHAN ASAS :</b></td></tr>
	<tr>
		<td>

		#if($senarai_submmk.size()!=0)
    		#foreach($list in $senarai_submmk)   
    		#if($list.FLAG_JENIS_MMK == "KEMUDAHANASAS" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    		<input type="hidden" name="KEMUDAHANASAS_MAIN_temp1"  id="KEMUDAHANASAS_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    		#end
    		#end
    	#end 
    	
    	
    	<span id="KEMUDAHANASAS_MAIN"></span>           
   		<div id="KEMUDAHANASAS_MAIN_temp"></div>
    
		</td>

	</tr>

	<tr><td>&nbsp;</td></tr>
	
    <!-- BUTIR - BUTIR TANAH-->	
	<tr>
	  <td><b>7. BUTIR-BUTIR TANAH :</b></td></tr>
	<tr>
		<td>

		#if($senarai_submmk.size()!=0)
    		#foreach($list in $senarai_submmk)   
    		#if($list.FLAG_JENIS_MMK == "BUTIRTANAH" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    		<input type="hidden" name="BUTIRTANAH_MAIN_temp1"  id="BUTIRTANAH_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    		#end
    		#end
    	#end 
    	
    	
    	<span id="BUTIRTANAH_MAIN"></span>           
   		<div id="BUTIRTANAH_MAIN_temp"></div>
    
		</td>

	</tr>

	<tr><td>&nbsp;</td></tr>
	
	<!-- ANGGARAN NILAIAN TANAH-->	
	<tr>
	  <td><b>8. ANGGARAN NILAIAN TANAH :</b></td></tr>
	<tr>
		<td>

		#if($senarai_submmk.size()!=0)
    		#foreach($list in $senarai_submmk)   
    		#if($list.FLAG_JENIS_MMK == "NILAITANAH" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    		<input type="hidden" name="NILAITANAH_MAIN_temp1"  id="NILAITANAH_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    		#end
    		#end
    	#end 
    	
    	
    	<span id="NILAITANAH_MAIN"></span>           
   		<div id="NILAITANAH_MAIN_temp"></div>
    
		</td>

	</tr>

	<tr><td>&nbsp;</td></tr>
	
	
	<!-- PENGESAHAN PERUNTUKAN-->	
	<tr>
	  <td><b>9. PENGESAHAN PERUNTUKAN :</b></td></tr>
	<tr>
		<td>

		#if($senarai_submmk.size()!=0)
    		#foreach($list in $senarai_submmk)   
    		#if($list.FLAG_JENIS_MMK == "WANGPERUNTUKAN" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    		<input type="hidden" name="WANGPERUNTUKAN_MAIN_temp1"  id="WANGPERUNTUKAN_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    		#end
    		#end
    	#end 
    	
    	
    	<span id="WANGPERUNTUKAN_MAIN"></span>           
   		<div id="WANGPERUNTUKAN_MAIN_temp"></div>
    
		</td>

	</tr>

	<tr><td>&nbsp;</td></tr>
	
	
	<!-- ULASAN PENTADBIR TANAH WILAYAH PERSEKUTUAN KUALA LUMPUR -->	
	<tr>
	  <td><b>10. ULASAN PENTADBIR TANAH WILAYAH PERSEKUTUAN KUALA LUMPUR :</b></td></tr>
	<tr>
		<td>

		#if($senarai_submmk.size()!=0)
    		#foreach($list in $senarai_submmk)   
    		#if($list.FLAG_JENIS_MMK == "ULASANPT" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    		<input type="hidden" name="ULASANPT_MAIN_temp1"  id="ULASANPT_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    		#end
    		#end
    	#end 
    	
    	
    	<span id="ULASANPT_MAIN"></span>           
   		<div id="ULASANPT_MAIN_temp"></div>
    
		</td>

	</tr>

	<tr><td>&nbsp;</td></tr>
	
	
	<!-- PERAKUAN PENTADBIR TANAH WILAYAH PERSEKUTUAN KUALA LUMPUR -->	
	<tr>
	  <td><b>11. PANDANGAN DAN SYOR PENTADBIR TANAH WILAYAH PERSEKUTUAN KUALA LUMPUR :</b></td></tr>
    <tr>
     <td>
                
                
                <table width="100%">
                
                <tr >                
                <td width="4%">
                </td>
                <td width="1%">
                </td>
                <td width="6%">
                </td>
                <td width="5%">
                </td>
                <td width="84%">
                </td>
                </tr>
               
                <tr> 
                <td align="right" valign="top" ><b>11.1</b></td>
                 <td ></td>
                <td colspan="3">
                Pentadbir Tanah Wilayah Persekutuan Kuala Lumpur dengan ini memperakukan seperti berikut:
                
                
                </td>                
                </tr>
                
               
                
                
                </table>
                
                
                
                </td></tr>
	<tr>
		<td>

		#if($senarai_submmk.size()!=0)
    		#foreach($list in $senarai_submmk)   
    		#if($list.FLAG_JENIS_MMK == "PERAKUANPT" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    		<input type="hidden" name="PERAKUANPT_MAIN_temp1"  id="PERAKUANPT_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    		#end
    		#end
    	#end 
    	
    	
    	<span id="PERAKUANPT_MAIN"></span>           
   		<div id="PERAKUANPT_MAIN_temp"></div>
    
		</td>

	</tr>

	<tr><td>&nbsp;</td></tr>
	
	<!-- KEPUTUSAN DIPOHON -->	
	<tr>
	  <td><b>12. KEPUTUSAN DIPOHON :</b></td></tr>
	<tr>
		<td>

		#if($senarai_submmk.size()!=0)
    		#foreach($list in $senarai_submmk)   
    		#if($list.FLAG_JENIS_MMK == "KEPUTUSAN" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    		<input type="hidden" name="KEPUTUSAN_MAIN_temp1"  id="KEPUTUSAN_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    		#end
    		#end
    	#end 
    	
    	
    	<span id="KEPUTUSAN_MAIN"></span>           
   		<div id="KEPUTUSAN_MAIN_temp"></div>
    
		</td>

	</tr>

	</table>  

</fieldset>


<script>
function check_length(my_form,maxLen,alert_field,text_num,jenis_field,mandatory,value_field)
{

	   var lepas_or_xlepas = 1;
       if(jenis_field == "normal")
	   { 
	   if(my_form.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }	   
	   if(my_form.value == "")
	   {
	   document.getElementById(text_num).value = maxLen;
	   }   
	   if(lepas_or_xlepas == "2")
	   {	   
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > Sila masukkan "+value_field+"");
	   }
	   else
	   {	  
	   if (my_form.value.length >= maxLen) 
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='invalid' >  Jumlah aksara telah melebihi had yang ditetapkan");
	   my_form.value = my_form.value.substring(0, maxLen);
       maxLen = 0;
	   }
	   else
	   {
	   $jquery("#"+alert_field).html("<input type='hidden' id='validation_field' name='validation_field' value='valid' > ");
	   maxLen = maxLen - my_form.value.length;
       }		
	   }
	   
	   	   
	   }

$jquery("#"+text_num).html(maxLen+"");
}

//BUTIRASAS
function textarea_BUTIRASAS_MAIN(tambahtolak,jenis,index_tolak){
	
	var BUTIRASAS_MAIN_temp1_length=0;

	if(document.${formName}.BUTIRASAS_MAIN_temp1 != null){

		if(document.${formName}.BUTIRASAS_MAIN_temp1.length>0){
			BUTIRASAS_MAIN_temp1_length = document.${formName}.BUTIRASAS_MAIN_temp1.length;
		}else{
			BUTIRASAS_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanBUTIRASAS_MAIN!=null){

		if(document.${formName}.txtUlasanBUTIRASAS_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanBUTIRASAS_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='BUTIRASAS_MAIN_tempX1' name='BUTIRASAS_MAIN_tempX1' value= '"+document.${formName}.txtUlasanBUTIRASAS_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='BUTIRASAS_MAIN_tempX1' name='BUTIRASAS_MAIN_tempX1' value= '"+document.${formName}.txtUlasanBUTIRASAS_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#BUTIRASAS_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(BUTIRASAS_MAIN_temp1_length>0){
			ttBUTIRASAS_MAIN = BUTIRASAS_MAIN_temp1_length;
		}else{
			ttBUTIRASAS_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttBUTIRASAS_MAIN = ttBUTIRASAS_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttBUTIRASAS_MAIN = ttBUTIRASAS_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttBUTIRASAS_MAIN; i++){	

  		if(ttBUTIRASAS_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.BUTIRASAS_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.BUTIRASAS_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanBUTIRASAS_MAIN\" id=\"txtUlasanBUTIRASAS_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS_MAIN_check','txtUlasanBUTIRASAS_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS_MAIN_check','txtUlasanBUTIRASAS_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanBUTIRASAS_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanBUTIRASAS_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanBUTIRASAS_MAIN\" id=\"txtUlasanBUTIRASAS_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS_MAIN_check','txtUlasanBUTIRASAS_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS_MAIN_check','txtUlasanBUTIRASAS_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanBUTIRASAS_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanBUTIRASAS_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_BUTIRASAS_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttBUTIRASAS_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_BUTIRASAS_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttBUTIRASAS_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.BUTIRASAS_MAIN_tempX1.value

			}else if(ttBUTIRASAS_MAIN>2 && i!=(ttBUTIRASAS_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.BUTIRASAS_MAIN_tempX1[i].value

			}else if(ttBUTIRASAS_MAIN>1 && i!=(ttBUTIRASAS_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.BUTIRASAS_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.BUTIRASAS_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.BUTIRASAS_MAIN_tempX1[i+1].value	
				}	

			}else if(ttBUTIRASAS_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.BUTIRASAS_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.BUTIRASAS_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 1."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanBUTIRASAS_MAIN\" id=\"txtUlasanBUTIRASAS_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS_MAIN_check"+i+"','txtUlasanBUTIRASAS_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS_MAIN_check"+i+"','txtUlasanBUTIRASAS_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanBUTIRASAS_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanBUTIRASAS_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanBUTIRASAS_MAIN\" id=\"txtUlasanBUTIRASAS_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS_MAIN_check"+i+"','txtUlasanBUTIRASAS_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS_MAIN_check"+i+"','txtUlasanBUTIRASAS_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanBUTIRASAS_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanBUTIRASAS_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttBUTIRASAS_MAIN>1 && ttBUTIRASAS_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_BUTIRASAS_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttBUTIRASAS_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_BUTIRASAS_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#BUTIRASAS_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(BUTIRASAS_MAIN_temp1_length > 1 && document.${formName}.txtUlasanBUTIRASAS_MAIN.length > 1 ){

		for (t = 0; t < BUTIRASAS_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanBUTIRASAS_MAIN[t].value = document.${formName}.BUTIRASAS_MAIN_temp1[t].value;
		}

		}else if(BUTIRASAS_MAIN_temp1_length > 1 && document.${formName}.txtUlasanBUTIRASAS_MAIN.length < 1 ){
		
			for (t = 0; t < BUTIRASAS_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanBUTIRASAS_MAIN.value = document.${formName}.BUTIRASAS_MAIN_temp1[0].value;
			}
		}
		
		else if(BUTIRASAS_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanBUTIRASAS_MAIN.value = document.${formName}.BUTIRASAS_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(BUTIRASAS_MAIN_temp1_length > 1){

			for (t = 0; t < BUTIRASAS_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.BUTIRASAS_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.BUTIRASAS_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(BUTIRASAS_MAIN_temp1_length == 1){
			
	 		document.${formName}.BUTIRASAS_MAIN_temp1.value = "";			
	 		var element = document.${formName}.BUTIRASAS_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttBUTIRASAS_MAIN; i++){		

		    if(ttBUTIRASAS_MAIN==1){
		 check_length(document.${formName}.txtUlasanBUTIRASAS_MAIN,'30000','txtUlasanBUTIRASAS_MAIN_check','txtUlasanBUTIRASAS_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanBUTIRASAS_MAIN[i],'30000','txtUlasanBUTIRASAS_MAIN_check'+i,'txtUlasanBUTIRASAS_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//CLOSE BUTIRASAS



//LAPORANTANAH1
function textarea_LAPORANTANAH1_MAIN(tambahtolak,jenis,index_tolak){
	
	var LAPORANTANAH1_MAIN_temp1_length=0;

	if(document.${formName}.LAPORANTANAH1_MAIN_temp1 != null){

		if(document.${formName}.LAPORANTANAH1_MAIN_temp1.length>0){
			LAPORANTANAH1_MAIN_temp1_length = document.${formName}.LAPORANTANAH1_MAIN_temp1.length;
		}else{
			LAPORANTANAH1_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanLAPORANTANAH1_MAIN!=null){

		if(document.${formName}.txtUlasanLAPORANTANAH1_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanLAPORANTANAH1_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='LAPORANTANAH1_MAIN_tempX1' name='LAPORANTANAH1_MAIN_tempX1' value= '"+document.${formName}.txtUlasanLAPORANTANAH1_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='LAPORANTANAH1_MAIN_tempX1' name='LAPORANTANAH1_MAIN_tempX1' value= '"+document.${formName}.txtUlasanLAPORANTANAH1_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#LAPORANTANAH1_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(LAPORANTANAH1_MAIN_temp1_length>0){
			ttLAPORANTANAH1_MAIN = LAPORANTANAH1_MAIN_temp1_length;
		}else{
			ttLAPORANTANAH1_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttLAPORANTANAH1_MAIN = ttLAPORANTANAH1_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttLAPORANTANAH1_MAIN = ttLAPORANTANAH1_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttLAPORANTANAH1_MAIN; i++){	

  		if(ttLAPORANTANAH1_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.LAPORANTANAH1_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.LAPORANTANAH1_MAIN_tempX1[0].value
				} 	
			}	
			
			if(jenis == "umum" )
			{
			//temp_value = '$txtLAPORANTANAH1';
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'> </td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanLAPORANTANAH1_MAIN\" id=\"txtUlasanLAPORANTANAH1_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanLAPORANTANAH1_MAIN_check','txtUlasanLAPORANTANAH1_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanLAPORANTANAH1_MAIN_check','txtUlasanLAPORANTANAH1_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanLAPORANTANAH1_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanLAPORANTANAH1_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanLAPORANTANAH1_MAIN\" id=\"txtUlasanLAPORANTANAH1_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanLAPORANTANAH1_MAIN_check','txtUlasanLAPORANTANAH1_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanLAPORANTANAH1_MAIN_check','txtUlasanLAPORANTANAH1_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanLAPORANTANAH1_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanLAPORANTANAH1_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_LAPORANTANAH1_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttLAPORANTANAH1_MAIN>1) {      
	     	codes1 += "				<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_LAPORANTANAH1_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttLAPORANTANAH1_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.LAPORANTANAH1_MAIN_tempX1.value

			}else if(ttLAPORANTANAH1_MAIN>2 && i!=(ttLAPORANTANAH1_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.LAPORANTANAH1_MAIN_tempX1[i].value

			}else if(ttLAPORANTANAH1_MAIN>1 && i!=(ttLAPORANTANAH1_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.LAPORANTANAH1_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.LAPORANTANAH1_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.LAPORANTANAH1_MAIN_tempX1[i+1].value	
				}	

			}else if(ttLAPORANTANAH1_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.LAPORANTANAH1_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.LAPORANTANAH1_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 2."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanLAPORANTANAH1_MAIN\" id=\"txtUlasanLAPORANTANAH1_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanLAPORANTANAH1_MAIN_check"+i+"','txtUlasanLAPORANTANAH1_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanLAPORANTANAH1_MAIN_check"+i+"','txtUlasanLAPORANTANAH1_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanLAPORANTANAH1_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanLAPORANTANAH1_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanLAPORANTANAH1_MAIN\" id=\"txtUlasanLAPORANTANAH1_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanLAPORANTANAH1_MAIN_check"+i+"','txtUlasanLAPORANTANAH1_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanLAPORANTANAH1_MAIN_check"+i+"','txtUlasanLAPORANTANAH1_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanLAPORANTANAH1_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanLAPORANTANAH1_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttLAPORANTANAH1_MAIN>1 && ttLAPORANTANAH1_MAIN==(i+1)){  	
			codes1 += 	" 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_LAPORANTANAH1_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttLAPORANTANAH1_MAIN>1){      
			codes1 += 	"			<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_LAPORANTANAH1_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#LAPORANTANAH1_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(LAPORANTANAH1_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLAPORANTANAH1_MAIN.length > 1 ){

		for (t = 0; t < LAPORANTANAH1_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanLAPORANTANAH1_MAIN[t].value = document.${formName}.LAPORANTANAH1_MAIN_temp1[t].value;
		}

		}else if(LAPORANTANAH1_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLAPORANTANAH1_MAIN.length < 1 ){
		
			for (t = 0; t < LAPORANTANAH1_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanLAPORANTANAH1_MAIN.value = document.${formName}.LAPORANTANAH1_MAIN_temp1[0].value;
			}
		}
		
		else if(LAPORANTANAH1_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanLAPORANTANAH1_MAIN.value = document.${formName}.LAPORANTANAH1_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(LAPORANTANAH1_MAIN_temp1_length > 1){

			for (t = 0; t < LAPORANTANAH1_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.LAPORANTANAH1_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.LAPORANTANAH1_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(LAPORANTANAH1_MAIN_temp1_length == 1){
			
	 		document.${formName}.LAPORANTANAH1_MAIN_temp1.value = "";			
	 		var element = document.${formName}.LAPORANTANAH1_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttLAPORANTANAH1_MAIN; i++){		

		    if(ttLAPORANTANAH1_MAIN==1){
		 check_length(document.${formName}.txtUlasanLAPORANTANAH1_MAIN,'30000','txtUlasanLAPORANTANAH1_MAIN_check','txtUlasanLAPORANTANAH1_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanLAPORANTANAH1_MAIN[i],'30000','txtUlasanLAPORANTANAH1_MAIN_check'+i,'txtUlasanLAPORANTANAH1_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
    }//CLOSE LAPORANTANAH1




//BUTIRASAS1
function textarea_BUTIRASAS1_MAIN(tambahtolak,jenis,index_tolak){
	
	var BUTIRASAS1_MAIN_temp1_length=0;

	if(document.${formName}.BUTIRASAS1_MAIN_temp1 != null){

		if(document.${formName}.BUTIRASAS1_MAIN_temp1.length>0){
			BUTIRASAS1_MAIN_temp1_length = document.${formName}.BUTIRASAS1_MAIN_temp1.length;
		}else{
			BUTIRASAS1_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanBUTIRASAS1_MAIN!=null){

		if(document.${formName}.txtUlasanBUTIRASAS1_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanBUTIRASAS1_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='BUTIRASAS1_MAIN_tempX1' name='BUTIRASAS1_MAIN_tempX1' value= '"+document.${formName}.txtUlasanBUTIRASAS1_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='BUTIRASAS1_MAIN_tempX1' name='BUTIRASAS1_MAIN_tempX1' value= '"+document.${formName}.txtUlasanBUTIRASAS1_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#BUTIRASAS1_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(BUTIRASAS1_MAIN_temp1_length>0){
			ttBUTIRASAS1_MAIN = BUTIRASAS1_MAIN_temp1_length;
		}else{
			ttBUTIRASAS1_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttBUTIRASAS1_MAIN = ttBUTIRASAS1_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttBUTIRASAS1_MAIN = ttBUTIRASAS1_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttBUTIRASAS1_MAIN; i++){	

  		if(ttBUTIRASAS1_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.BUTIRASAS1_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.BUTIRASAS1_MAIN_tempX1[0].value
				} 	
			}	
			
			if(jenis == "umum" )
			{
			temp_value = '$txtBUTIRASAS1';
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='5%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'> </td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanBUTIRASAS1_MAIN\" id=\"txtUlasanBUTIRASAS1_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS1_MAIN_check','txtUlasanBUTIRASAS1_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS1_MAIN_check','txtUlasanBUTIRASAS1_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanBUTIRASAS1_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanBUTIRASAS1_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanBUTIRASAS1_MAIN\" id=\"txtUlasanBUTIRASAS1_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS1_MAIN_check','txtUlasanBUTIRASAS1_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS1_MAIN_check','txtUlasanBUTIRASAS1_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanBUTIRASAS1_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanBUTIRASAS1_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_BUTIRASAS1_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttBUTIRASAS1_MAIN>1) {      
	     	codes1 += "				<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_BUTIRASAS1_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttBUTIRASAS1_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.BUTIRASAS1_MAIN_tempX1.value

			}else if(ttBUTIRASAS1_MAIN>2 && i!=(ttBUTIRASAS1_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.BUTIRASAS1_MAIN_tempX1[i].value

			}else if(ttBUTIRASAS1_MAIN>1 && i!=(ttBUTIRASAS1_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.BUTIRASAS1_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.BUTIRASAS1_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.BUTIRASAS1_MAIN_tempX1[i+1].value	
				}	

			}else if(ttBUTIRASAS1_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.BUTIRASAS1_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.BUTIRASAS1_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='5%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 2."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='92%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanBUTIRASAS1_MAIN\" id=\"txtUlasanBUTIRASAS1_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS1_MAIN_check"+i+"','txtUlasanBUTIRASAS1_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS1_MAIN_check"+i+"','txtUlasanBUTIRASAS1_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanBUTIRASAS1_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanBUTIRASAS1_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='92%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanBUTIRASAS1_MAIN\" id=\"txtUlasanBUTIRASAS1_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS1_MAIN_check"+i+"','txtUlasanBUTIRASAS1_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS1_MAIN_check"+i+"','txtUlasanBUTIRASAS1_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanBUTIRASAS1_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanBUTIRASAS1_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttBUTIRASAS1_MAIN>1 && ttBUTIRASAS1_MAIN==(i+1)){  	
			codes1 += 	" 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_BUTIRASAS1_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttBUTIRASAS1_MAIN>1){      
			codes1 += 	"			<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_BUTIRASAS1_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#BUTIRASAS1_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(BUTIRASAS1_MAIN_temp1_length > 1 && document.${formName}.txtUlasanBUTIRASAS1_MAIN.length > 1 ){

		for (t = 0; t < BUTIRASAS1_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanBUTIRASAS1_MAIN[t].value = document.${formName}.BUTIRASAS1_MAIN_temp1[t].value;
		}

		}else if(BUTIRASAS1_MAIN_temp1_length > 1 && document.${formName}.txtUlasanBUTIRASAS1_MAIN.length < 1 ){
		
			for (t = 0; t < BUTIRASAS1_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanBUTIRASAS1_MAIN.value = document.${formName}.BUTIRASAS1_MAIN_temp1[0].value;
			}
		}
		
		else if(BUTIRASAS1_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanBUTIRASAS1_MAIN.value = document.${formName}.BUTIRASAS1_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(BUTIRASAS1_MAIN_temp1_length > 1){

			for (t = 0; t < BUTIRASAS1_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.BUTIRASAS1_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.BUTIRASAS1_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(BUTIRASAS1_MAIN_temp1_length == 1){
			
	 		document.${formName}.BUTIRASAS1_MAIN_temp1.value = "";			
	 		var element = document.${formName}.BUTIRASAS1_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttBUTIRASAS1_MAIN; i++){		

		    if(ttBUTIRASAS1_MAIN==1){
		 check_length(document.${formName}.txtUlasanBUTIRASAS1_MAIN,'30000','txtUlasanBUTIRASAS1_MAIN_check','txtUlasanBUTIRASAS1_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanBUTIRASAS1_MAIN[i],'30000','txtUlasanBUTIRASAS1_MAIN_check'+i,'txtUlasanBUTIRASAS1_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
    }//CLOSE BUTIRASAS1


//BUTIRTANAH
function textarea_BUTIRTANAH_MAIN(tambahtolak,jenis,index_tolak){
	
	var BUTIRTANAH_MAIN_temp1_length=0;

	if(document.${formName}.BUTIRTANAH_MAIN_temp1 != null){

		if(document.${formName}.BUTIRTANAH_MAIN_temp1.length>0){
			BUTIRTANAH_MAIN_temp1_length = document.${formName}.BUTIRTANAH_MAIN_temp1.length;
		}else{
			BUTIRTANAH_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanBUTIRTANAH_MAIN!=null){

		if(document.${formName}.txtUlasanBUTIRTANAH_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanBUTIRTANAH_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='BUTIRTANAH_MAIN_tempX1' name='BUTIRTANAH_MAIN_tempX1' value= '"+document.${formName}.txtUlasanBUTIRTANAH_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='BUTIRTANAH_MAIN_tempX1' name='BUTIRTANAH_MAIN_tempX1' value= '"+document.${formName}.txtUlasanBUTIRTANAH_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#BUTIRTANAH_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(BUTIRTANAH_MAIN_temp1_length>0){
			ttBUTIRTANAH_MAIN = BUTIRTANAH_MAIN_temp1_length;
		}else{
			ttBUTIRTANAH_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttBUTIRTANAH_MAIN = ttBUTIRTANAH_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttBUTIRTANAH_MAIN = ttBUTIRTANAH_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttBUTIRTANAH_MAIN; i++){	

  		if(ttBUTIRTANAH_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.BUTIRTANAH_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.BUTIRTANAH_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanBUTIRTANAH_MAIN\" id=\"txtUlasanBUTIRTANAH_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanBUTIRTANAH_MAIN_check','txtUlasanBUTIRTANAH_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRTANAH_MAIN_check','txtUlasanBUTIRTANAH_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanBUTIRTANAH_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanBUTIRTANAH_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanBUTIRTANAH_MAIN\" id=\"txtUlasanBUTIRTANAH_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanBUTIRTANAH_MAIN_check','txtUlasanBUTIRTANAH_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRTANAH_MAIN_check','txtUlasanBUTIRTANAH_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanBUTIRTANAH_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanBUTIRTANAH_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_BUTIRTANAH_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttBUTIRTANAH_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_BUTIRTANAH_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttBUTIRTANAH_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.BUTIRTANAH_MAIN_tempX1.value

			}else if(ttBUTIRTANAH_MAIN>2 && i!=(ttBUTIRTANAH_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.BUTIRTANAH_MAIN_tempX1[i].value

			}else if(ttBUTIRTANAH_MAIN>1 && i!=(ttBUTIRTANAH_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.BUTIRTANAH_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.BUTIRTANAH_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.BUTIRTANAH_MAIN_tempX1[i+1].value	
				}	

			}else if(ttBUTIRTANAH_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.BUTIRTANAH_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.BUTIRTANAH_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 7."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanBUTIRTANAH_MAIN\" id=\"txtUlasanBUTIRTANAH_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanBUTIRTANAH_MAIN_check"+i+"','txtUlasanBUTIRTANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRTANAH_MAIN_check"+i+"','txtUlasanBUTIRTANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanBUTIRTANAH_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanBUTIRTANAH_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanBUTIRTANAH_MAIN\" id=\"txtUlasanBUTIRTANAH_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanBUTIRTANAH_MAIN_check"+i+"','txtUlasanBUTIRTANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRTANAH_MAIN_check"+i+"','txtUlasanBUTIRTANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanBUTIRTANAH_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanBUTIRTANAH_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttBUTIRTANAH_MAIN>1 && ttBUTIRTANAH_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_BUTIRTANAH_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttBUTIRTANAH_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_BUTIRTANAH_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#BUTIRTANAH_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(BUTIRTANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanBUTIRTANAH_MAIN.length > 1 ){

		for (t = 0; t < BUTIRTANAH_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanBUTIRTANAH_MAIN[t].value = document.${formName}.BUTIRTANAH_MAIN_temp1[t].value;
		}

		}else if(BUTIRTANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanBUTIRTANAH_MAIN.length < 1 ){
		
			for (t = 0; t < BUTIRTANAH_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanBUTIRTANAH_MAIN.value = document.${formName}.BUTIRTANAH_MAIN_temp1[0].value;
			}
		}
		
		else if(BUTIRTANAH_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanBUTIRTANAH_MAIN.value = document.${formName}.BUTIRTANAH_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(BUTIRTANAH_MAIN_temp1_length > 1){

			for (t = 0; t < BUTIRTANAH_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.BUTIRTANAH_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.BUTIRTANAH_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(BUTIRTANAH_MAIN_temp1_length == 1){
			
	 		document.${formName}.BUTIRTANAH_MAIN_temp1.value = "";			
	 		var element = document.${formName}.BUTIRTANAH_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttBUTIRTANAH_MAIN; i++){		

		    if(ttBUTIRTANAH_MAIN==1){
		 check_length(document.${formName}.txtUlasanBUTIRTANAH_MAIN,'30000','txtUlasanBUTIRTANAH_MAIN_check','txtUlasanBUTIRTANAH_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanBUTIRTANAH_MAIN[i],'30000','txtUlasanBUTIRTANAH_MAIN_check'+i,'txtUlasanBUTIRTANAH_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//CLOSE BUTIRTANAH



//BUTIRASAS2
function textarea_BUTIRASAS2_MAIN(tambahtolak,jenis,index_tolak){
	
	var BUTIRASAS2_MAIN_temp1_length=0;

	if(document.${formName}.BUTIRASAS2_MAIN_temp1 != null){

		if(document.${formName}.BUTIRASAS2_MAIN_temp1.length>0){
			BUTIRASAS2_MAIN_temp1_length = document.${formName}.BUTIRASAS2_MAIN_temp1.length;
		}else{
			BUTIRASAS2_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanBUTIRASAS2_MAIN!=null){

		if(document.${formName}.txtUlasanBUTIRASAS2_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanBUTIRASAS2_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='BUTIRASAS2_MAIN_tempX1' name='BUTIRASAS2_MAIN_tempX1' value= '"+document.${formName}.txtUlasanBUTIRASAS2_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='BUTIRASAS2_MAIN_tempX1' name='BUTIRASAS2_MAIN_tempX1' value= '"+document.${formName}.txtUlasanBUTIRASAS2_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#BUTIRASAS2_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(BUTIRASAS2_MAIN_temp1_length>0){
			ttBUTIRASAS2_MAIN = BUTIRASAS2_MAIN_temp1_length;
		}else{
			ttBUTIRASAS2_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttBUTIRASAS2_MAIN = ttBUTIRASAS2_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttBUTIRASAS2_MAIN = ttBUTIRASAS2_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttBUTIRASAS2_MAIN; i++){	

  		if(ttBUTIRASAS2_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.BUTIRASAS2_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.BUTIRASAS2_MAIN_tempX1[0].value
				} 	
			}	
			
			if(jenis == "umum" )
			{
			temp_value = '$txtBUTIRASAS2';
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='5%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'> </td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanBUTIRASAS2_MAIN\" id=\"txtUlasanBUTIRASAS2_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS2_MAIN_check','txtUlasanBUTIRASAS2_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS2_MAIN_check','txtUlasanBUTIRASAS2_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanBUTIRASAS2_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanBUTIRASAS2_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanBUTIRASAS2_MAIN\" id=\"txtUlasanBUTIRASAS2_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS2_MAIN_check','txtUlasanBUTIRASAS2_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS2_MAIN_check','txtUlasanBUTIRASAS2_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanBUTIRASAS2_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanBUTIRASAS2_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_BUTIRASAS2_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttBUTIRASAS2_MAIN>1) {      
	     	codes1 += "				<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_BUTIRASAS2_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttBUTIRASAS2_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.BUTIRASAS2_MAIN_tempX1.value

			}else if(ttBUTIRASAS2_MAIN>2 && i!=(ttBUTIRASAS2_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.BUTIRASAS2_MAIN_tempX1[i].value

			}else if(ttBUTIRASAS2_MAIN>1 && i!=(ttBUTIRASAS2_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.BUTIRASAS2_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.BUTIRASAS2_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.BUTIRASAS2_MAIN_tempX1[i+1].value	
				}	

			}else if(ttBUTIRASAS2_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.BUTIRASAS2_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.BUTIRASAS2_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='5%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 2."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='92%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanBUTIRASAS2_MAIN\" id=\"txtUlasanBUTIRASAS2_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS2_MAIN_check"+i+"','txtUlasanBUTIRASAS2_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS2_MAIN_check"+i+"','txtUlasanBUTIRASAS2_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanBUTIRASAS2_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanBUTIRASAS2_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='92%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanBUTIRASAS2_MAIN\" id=\"txtUlasanBUTIRASAS2_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS2_MAIN_check"+i+"','txtUlasanBUTIRASAS2_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS2_MAIN_check"+i+"','txtUlasanBUTIRASAS2_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanBUTIRASAS2_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanBUTIRASAS2_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttBUTIRASAS2_MAIN>1 && ttBUTIRASAS2_MAIN==(i+1)){  	
			codes1 += 	" 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_BUTIRASAS2_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttBUTIRASAS2_MAIN>1){      
			codes1 += 	"			<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_BUTIRASAS2_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#BUTIRASAS2_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(BUTIRASAS2_MAIN_temp1_length > 1 && document.${formName}.txtUlasanBUTIRASAS2_MAIN.length > 1 ){

		for (t = 0; t < BUTIRASAS2_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanBUTIRASAS2_MAIN[t].value = document.${formName}.BUTIRASAS2_MAIN_temp1[t].value;
		}

		}else if(BUTIRASAS2_MAIN_temp1_length > 1 && document.${formName}.txtUlasanBUTIRASAS2_MAIN.length < 1 ){
		
			for (t = 0; t < BUTIRASAS2_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanBUTIRASAS2_MAIN.value = document.${formName}.BUTIRASAS2_MAIN_temp1[0].value;
			}
		}
		
		else if(BUTIRASAS2_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanBUTIRASAS2_MAIN.value = document.${formName}.BUTIRASAS2_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(BUTIRASAS2_MAIN_temp1_length > 1){

			for (t = 0; t < BUTIRASAS2_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.BUTIRASAS2_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.BUTIRASAS2_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(BUTIRASAS2_MAIN_temp1_length == 1){
			
	 		document.${formName}.BUTIRASAS2_MAIN_temp1.value = "";			
	 		var element = document.${formName}.BUTIRASAS2_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttBUTIRASAS2_MAIN; i++){		

		    if(ttBUTIRASAS2_MAIN==1){
		 check_length(document.${formName}.txtUlasanBUTIRASAS2_MAIN,'30000','txtUlasanBUTIRASAS2_MAIN_check','txtUlasanBUTIRASAS2_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanBUTIRASAS2_MAIN[i],'30000','txtUlasanBUTIRASAS2_MAIN_check'+i,'txtUlasanBUTIRASAS2_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
    }//CLOSE BUTIRASAS2



//BUTIRASAS3
function textarea_BUTIRASAS3_MAIN(tambahtolak,jenis,index_tolak){
	
	var BUTIRASAS3_MAIN_temp1_length=0;

	if(document.${formName}.BUTIRASAS3_MAIN_temp1 != null){

		if(document.${formName}.BUTIRASAS3_MAIN_temp1.length>0){
			BUTIRASAS3_MAIN_temp1_length = document.${formName}.BUTIRASAS3_MAIN_temp1.length;
		}else{
			BUTIRASAS3_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanBUTIRASAS3_MAIN!=null){

		if(document.${formName}.txtUlasanBUTIRASAS3_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanBUTIRASAS3_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='BUTIRASAS3_MAIN_tempX1' name='BUTIRASAS3_MAIN_tempX1' value= '"+document.${formName}.txtUlasanBUTIRASAS3_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='BUTIRASAS3_MAIN_tempX1' name='BUTIRASAS3_MAIN_tempX1' value= '"+document.${formName}.txtUlasanBUTIRASAS3_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#BUTIRASAS3_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(BUTIRASAS3_MAIN_temp1_length>0){
			ttBUTIRASAS3_MAIN = BUTIRASAS3_MAIN_temp1_length;
		}else{
			ttBUTIRASAS3_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttBUTIRASAS3_MAIN = ttBUTIRASAS3_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttBUTIRASAS3_MAIN = ttBUTIRASAS3_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttBUTIRASAS3_MAIN; i++){	

  		if(ttBUTIRASAS3_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.BUTIRASAS3_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.BUTIRASAS3_MAIN_tempX1[0].value
				} 	
			}	
			
			if(jenis == "umum" )
			{
			temp_value = '$txtBUTIRASAS3';
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='5%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'> </td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanBUTIRASAS3_MAIN\" id=\"txtUlasanBUTIRASAS3_MAIN\" cols=\"90\"  rows=\"3\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS3_MAIN_check','txtUlasanBUTIRASAS3_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS3_MAIN_check','txtUlasanBUTIRASAS3_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanBUTIRASAS3_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanBUTIRASAS3_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanBUTIRASAS3_MAIN\" id=\"txtUlasanBUTIRASAS3_MAIN\" cols=\"90\"  rows=\"3\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS3_MAIN_check','txtUlasanBUTIRASAS3_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS3_MAIN_check','txtUlasanBUTIRASAS3_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanBUTIRASAS3_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanBUTIRASAS3_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_BUTIRASAS3_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttBUTIRASAS3_MAIN>1) {      
	     	codes1 += "				<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_BUTIRASAS3_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttBUTIRASAS3_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.BUTIRASAS3_MAIN_tempX1.value

			}else if(ttBUTIRASAS3_MAIN>2 && i!=(ttBUTIRASAS3_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.BUTIRASAS3_MAIN_tempX1[i].value

			}else if(ttBUTIRASAS3_MAIN>1 && i!=(ttBUTIRASAS3_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.BUTIRASAS3_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.BUTIRASAS3_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.BUTIRASAS3_MAIN_tempX1[i+1].value	
				}	

			}else if(ttBUTIRASAS3_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.BUTIRASAS3_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.BUTIRASAS3_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='5%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 2."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='92%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanBUTIRASAS3_MAIN\" id=\"txtUlasanBUTIRASAS3_MAIN\" cols=\"90\"  rows=\"3\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS3_MAIN_check"+i+"','txtUlasanBUTIRASAS3_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS3_MAIN_check"+i+"','txtUlasanBUTIRASAS3_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanBUTIRASAS3_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanBUTIRASAS3_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='92%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanBUTIRASAS3_MAIN\" id=\"txtUlasanBUTIRASAS3_MAIN\" cols=\"90\"  rows=\"3\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS3_MAIN_check"+i+"','txtUlasanBUTIRASAS3_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS3_MAIN_check"+i+"','txtUlasanBUTIRASAS3_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanBUTIRASAS3_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanBUTIRASAS3_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttBUTIRASAS3_MAIN>1 && ttBUTIRASAS3_MAIN==(i+1)){  	
			codes1 += 	" 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_BUTIRASAS3_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttBUTIRASAS3_MAIN>1){      
			codes1 += 	"			<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_BUTIRASAS3_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#BUTIRASAS3_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(BUTIRASAS3_MAIN_temp1_length > 1 && document.${formName}.txtUlasanBUTIRASAS3_MAIN.length > 1 ){

		for (t = 0; t < BUTIRASAS3_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanBUTIRASAS3_MAIN[t].value = document.${formName}.BUTIRASAS3_MAIN_temp1[t].value;
		}

		}else if(BUTIRASAS3_MAIN_temp1_length > 1 && document.${formName}.txtUlasanBUTIRASAS3_MAIN.length < 1 ){
		
			for (t = 0; t < BUTIRASAS3_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanBUTIRASAS3_MAIN.value = document.${formName}.BUTIRASAS3_MAIN_temp1[0].value;
			}
		}
		
		else if(BUTIRASAS3_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanBUTIRASAS3_MAIN.value = document.${formName}.BUTIRASAS3_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(BUTIRASAS3_MAIN_temp1_length > 1){

			for (t = 0; t < BUTIRASAS3_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.BUTIRASAS3_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.BUTIRASAS3_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(BUTIRASAS3_MAIN_temp1_length == 1){
			
	 		document.${formName}.BUTIRASAS3_MAIN_temp1.value = "";			
	 		var element = document.${formName}.BUTIRASAS3_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttBUTIRASAS3_MAIN; i++){		

		    if(ttBUTIRASAS3_MAIN==1){
		 check_length(document.${formName}.txtUlasanBUTIRASAS3_MAIN,'30000','txtUlasanBUTIRASAS3_MAIN_check','txtUlasanBUTIRASAS3_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanBUTIRASAS3_MAIN[i],'30000','txtUlasanBUTIRASAS3_MAIN_check'+i,'txtUlasanBUTIRASAS3_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
    }//CLOSE BUTIRASAS3



//BUTIRASAS4
function textarea_BUTIRASAS4_MAIN(tambahtolak,jenis,index_tolak){
	
	var BUTIRASAS4_MAIN_temp1_length=0;

	if(document.${formName}.BUTIRASAS4_MAIN_temp1 != null){

		if(document.${formName}.BUTIRASAS4_MAIN_temp1.length>0){
			BUTIRASAS4_MAIN_temp1_length = document.${formName}.BUTIRASAS4_MAIN_temp1.length;
		}else{
			BUTIRASAS4_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanBUTIRASAS4_MAIN!=null){

		if(document.${formName}.txtUlasanBUTIRASAS4_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanBUTIRASAS4_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='BUTIRASAS4_MAIN_tempX1' name='BUTIRASAS4_MAIN_tempX1' value= '"+document.${formName}.txtUlasanBUTIRASAS4_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='BUTIRASAS4_MAIN_tempX1' name='BUTIRASAS4_MAIN_tempX1' value= '"+document.${formName}.txtUlasanBUTIRASAS4_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#BUTIRASAS4_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(BUTIRASAS4_MAIN_temp1_length>0){
			ttBUTIRASAS4_MAIN = BUTIRASAS4_MAIN_temp1_length;
		}else{
			ttBUTIRASAS4_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttBUTIRASAS4_MAIN = ttBUTIRASAS4_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttBUTIRASAS4_MAIN = ttBUTIRASAS4_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttBUTIRASAS4_MAIN; i++){	

  		if(ttBUTIRASAS4_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.BUTIRASAS4_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.BUTIRASAS4_MAIN_tempX1[0].value
				} 	
			}	
			
			if(jenis == "umum" )
			{
			temp_value = '$txtBUTIRASAS4';
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='5%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'> </td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanBUTIRASAS4_MAIN\" id=\"txtUlasanBUTIRASAS4_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS4_MAIN_check','txtUlasanBUTIRASAS4_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS4_MAIN_check','txtUlasanBUTIRASAS4_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanBUTIRASAS4_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanBUTIRASAS4_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanBUTIRASAS4_MAIN\" id=\"txtUlasanBUTIRASAS4_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS4_MAIN_check','txtUlasanBUTIRASAS4_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS4_MAIN_check','txtUlasanBUTIRASAS4_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanBUTIRASAS4_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanBUTIRASAS4_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_BUTIRASAS4_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttBUTIRASAS4_MAIN>1) {      
	     	codes1 += "				<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_BUTIRASAS4_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttBUTIRASAS4_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.BUTIRASAS4_MAIN_tempX1.value

			}else if(ttBUTIRASAS4_MAIN>2 && i!=(ttBUTIRASAS4_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.BUTIRASAS4_MAIN_tempX1[i].value

			}else if(ttBUTIRASAS4_MAIN>1 && i!=(ttBUTIRASAS4_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.BUTIRASAS4_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.BUTIRASAS4_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.BUTIRASAS4_MAIN_tempX1[i+1].value	
				}	

			}else if(ttBUTIRASAS4_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.BUTIRASAS4_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.BUTIRASAS4_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='5%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 2."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='92%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanBUTIRASAS4_MAIN\" id=\"txtUlasanBUTIRASAS4_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS4_MAIN_check"+i+"','txtUlasanBUTIRASAS4_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS4_MAIN_check"+i+"','txtUlasanBUTIRASAS4_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanBUTIRASAS4_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanBUTIRASAS4_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='92%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanBUTIRASAS4_MAIN\" id=\"txtUlasanBUTIRASAS4_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS4_MAIN_check"+i+"','txtUlasanBUTIRASAS4_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS4_MAIN_check"+i+"','txtUlasanBUTIRASAS4_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanBUTIRASAS4_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanBUTIRASAS4_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttBUTIRASAS4_MAIN>1 && ttBUTIRASAS4_MAIN==(i+1)){  	
			codes1 += 	" 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_BUTIRASAS4_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttBUTIRASAS4_MAIN>1){      
			codes1 += 	"			<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_BUTIRASAS4_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#BUTIRASAS4_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(BUTIRASAS4_MAIN_temp1_length > 1 && document.${formName}.txtUlasanBUTIRASAS4_MAIN.length > 1 ){

		for (t = 0; t < BUTIRASAS4_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanBUTIRASAS4_MAIN[t].value = document.${formName}.BUTIRASAS4_MAIN_temp1[t].value;
		}

		}else if(BUTIRASAS4_MAIN_temp1_length > 1 && document.${formName}.txtUlasanBUTIRASAS4_MAIN.length < 1 ){
		
			for (t = 0; t < BUTIRASAS4_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanBUTIRASAS4_MAIN.value = document.${formName}.BUTIRASAS4_MAIN_temp1[0].value;
			}
		}
		
		else if(BUTIRASAS4_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanBUTIRASAS4_MAIN.value = document.${formName}.BUTIRASAS4_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(BUTIRASAS4_MAIN_temp1_length > 1){

			for (t = 0; t < BUTIRASAS4_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.BUTIRASAS4_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.BUTIRASAS4_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(BUTIRASAS4_MAIN_temp1_length == 1){
			
	 		document.${formName}.BUTIRASAS4_MAIN_temp1.value = "";			
	 		var element = document.${formName}.BUTIRASAS4_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttBUTIRASAS4_MAIN; i++){		

		    if(ttBUTIRASAS4_MAIN==1){
		 check_length(document.${formName}.txtUlasanBUTIRASAS4_MAIN,'30000','txtUlasanBUTIRASAS4_MAIN_check','txtUlasanBUTIRASAS4_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanBUTIRASAS4_MAIN[i],'30000','txtUlasanBUTIRASAS4_MAIN_check'+i,'txtUlasanBUTIRASAS4_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
    }//CLOSE BUTIRASAS4



//BUTIRASAS5
function textarea_BUTIRASAS5_MAIN(tambahtolak,jenis,index_tolak){
	
	var BUTIRASAS5_MAIN_temp1_length=0;

	if(document.${formName}.BUTIRASAS5_MAIN_temp1 != null){

		if(document.${formName}.BUTIRASAS5_MAIN_temp1.length>0){
			BUTIRASAS5_MAIN_temp1_length = document.${formName}.BUTIRASAS5_MAIN_temp1.length;
		}else{
			BUTIRASAS5_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanBUTIRASAS5_MAIN!=null){

		if(document.${formName}.txtUlasanBUTIRASAS5_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanBUTIRASAS5_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='BUTIRASAS5_MAIN_tempX1' name='BUTIRASAS5_MAIN_tempX1' value= '"+document.${formName}.txtUlasanBUTIRASAS5_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='BUTIRASAS5_MAIN_tempX1' name='BUTIRASAS5_MAIN_tempX1' value= '"+document.${formName}.txtUlasanBUTIRASAS5_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#BUTIRASAS5_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(BUTIRASAS5_MAIN_temp1_length>0){
			ttBUTIRASAS5_MAIN = BUTIRASAS5_MAIN_temp1_length;
		}else{
			ttBUTIRASAS5_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttBUTIRASAS5_MAIN = ttBUTIRASAS5_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttBUTIRASAS5_MAIN = ttBUTIRASAS5_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttBUTIRASAS5_MAIN; i++){	

  		if(ttBUTIRASAS5_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.BUTIRASAS5_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.BUTIRASAS5_MAIN_tempX1[0].value
				} 	
			}	
			
			if(jenis == "umum" )
			{
			temp_value = '$txtBUTIRASAS5';
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='5%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'> </td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanBUTIRASAS5_MAIN\" id=\"txtUlasanBUTIRASAS5_MAIN\" cols=\"90\"  rows=\"3\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS5_MAIN_check','txtUlasanBUTIRASAS5_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS5_MAIN_check','txtUlasanBUTIRASAS5_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanBUTIRASAS5_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanBUTIRASAS5_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanBUTIRASAS5_MAIN\" id=\"txtUlasanBUTIRASAS5_MAIN\" cols=\"90\"  rows=\"3\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS5_MAIN_check','txtUlasanBUTIRASAS5_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS5_MAIN_check','txtUlasanBUTIRASAS5_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanBUTIRASAS5_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanBUTIRASAS5_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_BUTIRASAS5_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttBUTIRASAS5_MAIN>1) {      
	     	codes1 += "				<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_BUTIRASAS5_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttBUTIRASAS5_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.BUTIRASAS5_MAIN_tempX1.value

			}else if(ttBUTIRASAS5_MAIN>2 && i!=(ttBUTIRASAS5_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.BUTIRASAS5_MAIN_tempX1[i].value

			}else if(ttBUTIRASAS5_MAIN>1 && i!=(ttBUTIRASAS5_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.BUTIRASAS5_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.BUTIRASAS5_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.BUTIRASAS5_MAIN_tempX1[i+1].value	
				}	

			}else if(ttBUTIRASAS5_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.BUTIRASAS5_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.BUTIRASAS5_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='5%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 2."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='92%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanBUTIRASAS5_MAIN\" id=\"txtUlasanBUTIRASAS5_MAIN\" cols=\"90\"  rows=\"3\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS5_MAIN_check"+i+"','txtUlasanBUTIRASAS5_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS5_MAIN_check"+i+"','txtUlasanBUTIRASAS5_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanBUTIRASAS5_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanBUTIRASAS5_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='92%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanBUTIRASAS5_MAIN\" id=\"txtUlasanBUTIRASAS5_MAIN\" cols=\"90\"  rows=\"3\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS5_MAIN_check"+i+"','txtUlasanBUTIRASAS5_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS5_MAIN_check"+i+"','txtUlasanBUTIRASAS5_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanBUTIRASAS5_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanBUTIRASAS5_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttBUTIRASAS5_MAIN>1 && ttBUTIRASAS5_MAIN==(i+1)){  	
			codes1 += 	" 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_BUTIRASAS5_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttBUTIRASAS5_MAIN>1){      
			codes1 += 	"			<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_BUTIRASAS5_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#BUTIRASAS5_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(BUTIRASAS5_MAIN_temp1_length > 1 && document.${formName}.txtUlasanBUTIRASAS5_MAIN.length > 1 ){

		for (t = 0; t < BUTIRASAS5_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanBUTIRASAS5_MAIN[t].value = document.${formName}.BUTIRASAS5_MAIN_temp1[t].value;
		}

		}else if(BUTIRASAS5_MAIN_temp1_length > 1 && document.${formName}.txtUlasanBUTIRASAS5_MAIN.length < 1 ){
		
			for (t = 0; t < BUTIRASAS5_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanBUTIRASAS5_MAIN.value = document.${formName}.BUTIRASAS5_MAIN_temp1[0].value;
			}
		}
		
		else if(BUTIRASAS5_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanBUTIRASAS5_MAIN.value = document.${formName}.BUTIRASAS5_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(BUTIRASAS5_MAIN_temp1_length > 1){

			for (t = 0; t < BUTIRASAS5_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.BUTIRASAS5_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.BUTIRASAS5_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(BUTIRASAS5_MAIN_temp1_length == 1){
			
	 		document.${formName}.BUTIRASAS5_MAIN_temp1.value = "";			
	 		var element = document.${formName}.BUTIRASAS5_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttBUTIRASAS5_MAIN; i++){		

		    if(ttBUTIRASAS5_MAIN==1){
		 check_length(document.${formName}.txtUlasanBUTIRASAS5_MAIN,'30000','txtUlasanBUTIRASAS5_MAIN_check','txtUlasanBUTIRASAS5_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanBUTIRASAS5_MAIN[i],'30000','txtUlasanBUTIRASAS5_MAIN_check'+i,'txtUlasanBUTIRASAS5_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
    }//CLOSE BUTIRASAS5



//BUTIRASAS6
function textarea_BUTIRASAS6_MAIN(tambahtolak,jenis,index_tolak){
	
	var BUTIRASAS6_MAIN_temp1_length=0;

	if(document.${formName}.BUTIRASAS6_MAIN_temp1 != null){

		if(document.${formName}.BUTIRASAS6_MAIN_temp1.length>0){
			BUTIRASAS6_MAIN_temp1_length = document.${formName}.BUTIRASAS6_MAIN_temp1.length;
		}else{
			BUTIRASAS6_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanBUTIRASAS6_MAIN!=null){

		if(document.${formName}.txtUlasanBUTIRASAS6_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanBUTIRASAS6_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='BUTIRASAS6_MAIN_tempX1' name='BUTIRASAS6_MAIN_tempX1' value= '"+document.${formName}.txtUlasanBUTIRASAS6_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='BUTIRASAS6_MAIN_tempX1' name='BUTIRASAS6_MAIN_tempX1' value= '"+document.${formName}.txtUlasanBUTIRASAS6_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#BUTIRASAS6_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(BUTIRASAS6_MAIN_temp1_length>0){
			ttBUTIRASAS6_MAIN = BUTIRASAS6_MAIN_temp1_length;
		}else{
			ttBUTIRASAS6_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttBUTIRASAS6_MAIN = ttBUTIRASAS6_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttBUTIRASAS6_MAIN = ttBUTIRASAS6_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttBUTIRASAS6_MAIN; i++){	

  		if(ttBUTIRASAS6_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.BUTIRASAS6_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.BUTIRASAS6_MAIN_tempX1[0].value
				} 	
			}	
			
			if(jenis == "umum" )
			{
			temp_value = '$txtBUTIRASAS6';
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='5%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'> </td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanBUTIRASAS6_MAIN\" id=\"txtUlasanBUTIRASAS6_MAIN\" cols=\"90\"  rows=\"3\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS6_MAIN_check','txtUlasanBUTIRASAS6_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS6_MAIN_check','txtUlasanBUTIRASAS6_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanBUTIRASAS6_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanBUTIRASAS6_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanBUTIRASAS6_MAIN\" id=\"txtUlasanBUTIRASAS6_MAIN\" cols=\"90\"  rows=\"3\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS6_MAIN_check','txtUlasanBUTIRASAS6_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS6_MAIN_check','txtUlasanBUTIRASAS6_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanBUTIRASAS6_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanBUTIRASAS6_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_BUTIRASAS6_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttBUTIRASAS6_MAIN>1) {      
	     	codes1 += "				<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_BUTIRASAS6_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttBUTIRASAS6_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.BUTIRASAS6_MAIN_tempX1.value

			}else if(ttBUTIRASAS6_MAIN>2 && i!=(ttBUTIRASAS6_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.BUTIRASAS6_MAIN_tempX1[i].value

			}else if(ttBUTIRASAS6_MAIN>1 && i!=(ttBUTIRASAS6_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.BUTIRASAS6_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.BUTIRASAS6_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.BUTIRASAS6_MAIN_tempX1[i+1].value	
				}	

			}else if(ttBUTIRASAS6_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.BUTIRASAS6_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.BUTIRASAS6_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='5%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 2."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='92%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanBUTIRASAS6_MAIN\" id=\"txtUlasanBUTIRASAS6_MAIN\" cols=\"90\"  rows=\"3\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS6_MAIN_check"+i+"','txtUlasanBUTIRASAS6_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS6_MAIN_check"+i+"','txtUlasanBUTIRASAS6_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanBUTIRASAS6_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanBUTIRASAS6_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='92%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanBUTIRASAS6_MAIN\" id=\"txtUlasanBUTIRASAS6_MAIN\" cols=\"90\"  rows=\"3\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS6_MAIN_check"+i+"','txtUlasanBUTIRASAS6_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS6_MAIN_check"+i+"','txtUlasanBUTIRASAS6_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanBUTIRASAS6_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanBUTIRASAS6_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttBUTIRASAS6_MAIN>1 && ttBUTIRASAS6_MAIN==(i+1)){  	
			codes1 += 	" 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_BUTIRASAS6_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttBUTIRASAS6_MAIN>1){      
			codes1 += 	"			<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_BUTIRASAS6_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#BUTIRASAS6_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(BUTIRASAS6_MAIN_temp1_length > 1 && document.${formName}.txtUlasanBUTIRASAS6_MAIN.length > 1 ){

		for (t = 0; t < BUTIRASAS6_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanBUTIRASAS6_MAIN[t].value = document.${formName}.BUTIRASAS6_MAIN_temp1[t].value;
		}

		}else if(BUTIRASAS6_MAIN_temp1_length > 1 && document.${formName}.txtUlasanBUTIRASAS6_MAIN.length < 1 ){
		
			for (t = 0; t < BUTIRASAS6_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanBUTIRASAS6_MAIN.value = document.${formName}.BUTIRASAS6_MAIN_temp1[0].value;
			}
		}
		
		else if(BUTIRASAS6_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanBUTIRASAS6_MAIN.value = document.${formName}.BUTIRASAS6_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(BUTIRASAS6_MAIN_temp1_length > 1){

			for (t = 0; t < BUTIRASAS6_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.BUTIRASAS6_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.BUTIRASAS6_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(BUTIRASAS6_MAIN_temp1_length == 1){
			
	 		document.${formName}.BUTIRASAS6_MAIN_temp1.value = "";			
	 		var element = document.${formName}.BUTIRASAS6_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttBUTIRASAS6_MAIN; i++){		

		    if(ttBUTIRASAS6_MAIN==1){
		 check_length(document.${formName}.txtUlasanBUTIRASAS6_MAIN,'30000','txtUlasanBUTIRASAS6_MAIN_check','txtUlasanBUTIRASAS6_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanBUTIRASAS6_MAIN[i],'30000','txtUlasanBUTIRASAS6_MAIN_check'+i,'txtUlasanBUTIRASAS6_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
    }//CLOSE BUTIRASAS6


//BUTIRASAS7
function textarea_BUTIRASAS7_MAIN(tambahtolak,jenis,index_tolak){
	
	var BUTIRASAS7_MAIN_temp1_length=0;

	if(document.${formName}.BUTIRASAS7_MAIN_temp1 != null){

		if(document.${formName}.BUTIRASAS7_MAIN_temp1.length>0){
			BUTIRASAS7_MAIN_temp1_length = document.${formName}.BUTIRASAS7_MAIN_temp1.length;
		}else{
			BUTIRASAS7_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanBUTIRASAS7_MAIN!=null){

		if(document.${formName}.txtUlasanBUTIRASAS7_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanBUTIRASAS7_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='BUTIRASAS7_MAIN_tempX1' name='BUTIRASAS7_MAIN_tempX1' value= '"+document.${formName}.txtUlasanBUTIRASAS7_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='BUTIRASAS7_MAIN_tempX1' name='BUTIRASAS7_MAIN_tempX1' value= '"+document.${formName}.txtUlasanBUTIRASAS7_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#BUTIRASAS7_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(BUTIRASAS7_MAIN_temp1_length>0){
			ttBUTIRASAS7_MAIN = BUTIRASAS7_MAIN_temp1_length;
		}else{
			ttBUTIRASAS7_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttBUTIRASAS7_MAIN = ttBUTIRASAS7_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttBUTIRASAS7_MAIN = ttBUTIRASAS7_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttBUTIRASAS7_MAIN; i++){	

  		if(ttBUTIRASAS7_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.BUTIRASAS7_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.BUTIRASAS7_MAIN_tempX1[0].value
				} 	
			}	
			
			if(jenis == "umum" )
			{
			temp_value = '$txtBUTIRASAS7';
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='5%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'> </td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanBUTIRASAS7_MAIN\" id=\"txtUlasanBUTIRASAS7_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS7_MAIN_check','txtUlasanBUTIRASAS7_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS7_MAIN_check','txtUlasanBUTIRASAS7_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanBUTIRASAS7_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanBUTIRASAS7_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanBUTIRASAS7_MAIN\" id=\"txtUlasanBUTIRASAS7_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS7_MAIN_check','txtUlasanBUTIRASAS7_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS7_MAIN_check','txtUlasanBUTIRASAS7_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanBUTIRASAS7_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanBUTIRASAS7_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_BUTIRASAS7_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttBUTIRASAS7_MAIN>1) {      
	     	codes1 += "				<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_BUTIRASAS7_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttBUTIRASAS7_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.BUTIRASAS7_MAIN_tempX1.value

			}else if(ttBUTIRASAS7_MAIN>2 && i!=(ttBUTIRASAS7_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.BUTIRASAS7_MAIN_tempX1[i].value

			}else if(ttBUTIRASAS7_MAIN>1 && i!=(ttBUTIRASAS7_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.BUTIRASAS7_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.BUTIRASAS7_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.BUTIRASAS7_MAIN_tempX1[i+1].value	
				}	

			}else if(ttBUTIRASAS7_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.BUTIRASAS7_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.BUTIRASAS7_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='5%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 2."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='92%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanBUTIRASAS7_MAIN\" id=\"txtUlasanBUTIRASAS7_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS7_MAIN_check"+i+"','txtUlasanBUTIRASAS7_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS7_MAIN_check"+i+"','txtUlasanBUTIRASAS7_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanBUTIRASAS7_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanBUTIRASAS7_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='92%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanBUTIRASAS7_MAIN\" id=\"txtUlasanBUTIRASAS7_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS7_MAIN_check"+i+"','txtUlasanBUTIRASAS7_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS7_MAIN_check"+i+"','txtUlasanBUTIRASAS7_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanBUTIRASAS7_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanBUTIRASAS7_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttBUTIRASAS7_MAIN>1 && ttBUTIRASAS7_MAIN==(i+1)){  	
			codes1 += 	" 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_BUTIRASAS7_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttBUTIRASAS7_MAIN>1){      
			codes1 += 	"			<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_BUTIRASAS7_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#BUTIRASAS7_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(BUTIRASAS7_MAIN_temp1_length > 1 && document.${formName}.txtUlasanBUTIRASAS7_MAIN.length > 1 ){

		for (t = 0; t < BUTIRASAS7_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanBUTIRASAS7_MAIN[t].value = document.${formName}.BUTIRASAS7_MAIN_temp1[t].value;
		}

		}else if(BUTIRASAS7_MAIN_temp1_length > 1 && document.${formName}.txtUlasanBUTIRASAS7_MAIN.length < 1 ){
		
			for (t = 0; t < BUTIRASAS7_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanBUTIRASAS7_MAIN.value = document.${formName}.BUTIRASAS7_MAIN_temp1[0].value;
			}
		}
		
		else if(BUTIRASAS7_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanBUTIRASAS7_MAIN.value = document.${formName}.BUTIRASAS7_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(BUTIRASAS7_MAIN_temp1_length > 1){

			for (t = 0; t < BUTIRASAS7_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.BUTIRASAS7_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.BUTIRASAS7_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(BUTIRASAS7_MAIN_temp1_length == 1){
			
	 		document.${formName}.BUTIRASAS7_MAIN_temp1.value = "";			
	 		var element = document.${formName}.BUTIRASAS7_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttBUTIRASAS7_MAIN; i++){		

		    if(ttBUTIRASAS7_MAIN==1){
		 check_length(document.${formName}.txtUlasanBUTIRASAS7_MAIN,'30000','txtUlasanBUTIRASAS7_MAIN_check','txtUlasanBUTIRASAS7_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanBUTIRASAS7_MAIN[i],'30000','txtUlasanBUTIRASAS7_MAIN_check'+i,'txtUlasanBUTIRASAS7_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
    }//CLOSE BUTIRASAS7



//BUTIRASAS8
function textarea_BUTIRASAS8_MAIN(tambahtolak,jenis,index_tolak){
	
	var BUTIRASAS8_MAIN_temp1_length=0;

	if(document.${formName}.BUTIRASAS8_MAIN_temp1 != null){

		if(document.${formName}.BUTIRASAS8_MAIN_temp1.length>0){
			BUTIRASAS8_MAIN_temp1_length = document.${formName}.BUTIRASAS8_MAIN_temp1.length;
		}else{
			BUTIRASAS8_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanBUTIRASAS8_MAIN!=null){

		if(document.${formName}.txtUlasanBUTIRASAS8_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanBUTIRASAS8_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='BUTIRASAS8_MAIN_tempX1' name='BUTIRASAS8_MAIN_tempX1' value= '"+document.${formName}.txtUlasanBUTIRASAS8_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='BUTIRASAS8_MAIN_tempX1' name='BUTIRASAS8_MAIN_tempX1' value= '"+document.${formName}.txtUlasanBUTIRASAS8_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#BUTIRASAS8_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(BUTIRASAS8_MAIN_temp1_length>0){
			ttBUTIRASAS8_MAIN = BUTIRASAS8_MAIN_temp1_length;
		}else{
			ttBUTIRASAS8_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttBUTIRASAS8_MAIN = ttBUTIRASAS8_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttBUTIRASAS8_MAIN = ttBUTIRASAS8_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttBUTIRASAS8_MAIN; i++){	

  		if(ttBUTIRASAS8_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.BUTIRASAS8_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.BUTIRASAS8_MAIN_tempX1[0].value
				} 	
			}	
			
			if(jenis == "umum" )
			{
			temp_value = '$txtBUTIRASAS8';
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='5%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'> </td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanBUTIRASAS8_MAIN\" id=\"txtUlasanBUTIRASAS8_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS8_MAIN_check','txtUlasanBUTIRASAS8_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS8_MAIN_check','txtUlasanBUTIRASAS8_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanBUTIRASAS8_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanBUTIRASAS8_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanBUTIRASAS8_MAIN\" id=\"txtUlasanBUTIRASAS8_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS8_MAIN_check','txtUlasanBUTIRASAS8_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS8_MAIN_check','txtUlasanBUTIRASAS8_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanBUTIRASAS8_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanBUTIRASAS8_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_BUTIRASAS8_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttBUTIRASAS8_MAIN>1) {      
	     	codes1 += "				<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_BUTIRASAS8_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttBUTIRASAS8_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.BUTIRASAS8_MAIN_tempX1.value

			}else if(ttBUTIRASAS8_MAIN>2 && i!=(ttBUTIRASAS8_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.BUTIRASAS8_MAIN_tempX1[i].value

			}else if(ttBUTIRASAS8_MAIN>1 && i!=(ttBUTIRASAS8_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.BUTIRASAS8_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.BUTIRASAS8_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.BUTIRASAS8_MAIN_tempX1[i+1].value	
				}	

			}else if(ttBUTIRASAS8_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.BUTIRASAS8_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.BUTIRASAS8_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='5%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 2."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='92%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanBUTIRASAS8_MAIN\" id=\"txtUlasanBUTIRASAS8_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS8_MAIN_check"+i+"','txtUlasanBUTIRASAS8_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS8_MAIN_check"+i+"','txtUlasanBUTIRASAS8_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanBUTIRASAS8_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanBUTIRASAS8_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='92%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanBUTIRASAS8_MAIN\" id=\"txtUlasanBUTIRASAS8_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanBUTIRASAS8_MAIN_check"+i+"','txtUlasanBUTIRASAS8_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanBUTIRASAS8_MAIN_check"+i+"','txtUlasanBUTIRASAS8_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanBUTIRASAS8_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanBUTIRASAS8_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttBUTIRASAS8_MAIN>1 && ttBUTIRASAS8_MAIN==(i+1)){  	
			codes1 += 	" 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_BUTIRASAS8_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttBUTIRASAS8_MAIN>1){      
			codes1 += 	"			<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_BUTIRASAS8_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#BUTIRASAS8_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(BUTIRASAS8_MAIN_temp1_length > 1 && document.${formName}.txtUlasanBUTIRASAS8_MAIN.length > 1 ){

		for (t = 0; t < BUTIRASAS8_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanBUTIRASAS8_MAIN[t].value = document.${formName}.BUTIRASAS8_MAIN_temp1[t].value;
		}

		}else if(BUTIRASAS8_MAIN_temp1_length > 1 && document.${formName}.txtUlasanBUTIRASAS8_MAIN.length < 1 ){
		
			for (t = 0; t < BUTIRASAS8_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanBUTIRASAS8_MAIN.value = document.${formName}.BUTIRASAS8_MAIN_temp1[0].value;
			}
		}
		
		else if(BUTIRASAS8_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanBUTIRASAS8_MAIN.value = document.${formName}.BUTIRASAS8_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(BUTIRASAS8_MAIN_temp1_length > 1){

			for (t = 0; t < BUTIRASAS8_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.BUTIRASAS8_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.BUTIRASAS8_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(BUTIRASAS8_MAIN_temp1_length == 1){
			
	 		document.${formName}.BUTIRASAS8_MAIN_temp1.value = "";			
	 		var element = document.${formName}.BUTIRASAS8_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttBUTIRASAS8_MAIN; i++){		

		    if(ttBUTIRASAS8_MAIN==1){
		 check_length(document.${formName}.txtUlasanBUTIRASAS8_MAIN,'30000','txtUlasanBUTIRASAS8_MAIN_check','txtUlasanBUTIRASAS8_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanBUTIRASAS8_MAIN[i],'30000','txtUlasanBUTIRASAS8_MAIN_check'+i,'txtUlasanBUTIRASAS8_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
    }//CLOSE BUTIRASAS8


//TUJUAN
function textarea_TUJUAN_MAIN(tambahtolak,jenis,index_tolak){
	
	var TUJUAN_MAIN_temp1_length=0;

	if(document.${formName}.TUJUAN_MAIN_temp1 != null){

		if(document.${formName}.TUJUAN_MAIN_temp1.length>0){
			TUJUAN_MAIN_temp1_length = document.${formName}.TUJUAN_MAIN_temp1.length;
		}else{
			TUJUAN_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanTUJUAN_MAIN!=null){

		if(document.${formName}.txtUlasanTUJUAN_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanTUJUAN_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='TUJUAN_MAIN_tempX1' name='TUJUAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanTUJUAN_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='TUJUAN_MAIN_tempX1' name='TUJUAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanTUJUAN_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#TUJUAN_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(TUJUAN_MAIN_temp1_length>0){
			ttTUJUAN_MAIN = TUJUAN_MAIN_temp1_length;
		}else{
			ttTUJUAN_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttTUJUAN_MAIN = ttTUJUAN_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttTUJUAN_MAIN = ttTUJUAN_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttTUJUAN_MAIN; i++){	

  		if(ttTUJUAN_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.TUJUAN_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.TUJUAN_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanTUJUAN_MAIN\" id=\"txtUlasanTUJUAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check','txtUlasanTUJUAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check','txtUlasanTUJUAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanTUJUAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanTUJUAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanTUJUAN_MAIN\" id=\"txtUlasanTUJUAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check','txtUlasanTUJUAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check','txtUlasanTUJUAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanTUJUAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanTUJUAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_TUJUAN_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttTUJUAN_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_TUJUAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttTUJUAN_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.TUJUAN_MAIN_tempX1.value

			}else if(ttTUJUAN_MAIN>2 && i!=(ttTUJUAN_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.TUJUAN_MAIN_tempX1[i].value

			}else if(ttTUJUAN_MAIN>1 && i!=(ttTUJUAN_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.TUJUAN_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.TUJUAN_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.TUJUAN_MAIN_tempX1[i+1].value	
				}	

			}else if(ttTUJUAN_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.TUJUAN_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.TUJUAN_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 2."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanTUJUAN_MAIN\" id=\"txtUlasanTUJUAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check"+i+"','txtUlasanTUJUAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check"+i+"','txtUlasanTUJUAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanTUJUAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanTUJUAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanTUJUAN_MAIN\" id=\"txtUlasanTUJUAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check"+i+"','txtUlasanTUJUAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check"+i+"','txtUlasanTUJUAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanTUJUAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanTUJUAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttTUJUAN_MAIN>1 && ttTUJUAN_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_TUJUAN_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttTUJUAN_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_TUJUAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#TUJUAN_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(TUJUAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanTUJUAN_MAIN.length > 1 ){

		for (t = 0; t < TUJUAN_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanTUJUAN_MAIN[t].value = document.${formName}.TUJUAN_MAIN_temp1[t].value;
		}

		}else if(TUJUAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanTUJUAN_MAIN.length < 1 ){
		
			for (t = 0; t < TUJUAN_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanTUJUAN_MAIN.value = document.${formName}.TUJUAN_MAIN_temp1[0].value;
			}
		}
		
		else if(TUJUAN_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanTUJUAN_MAIN.value = document.${formName}.TUJUAN_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(TUJUAN_MAIN_temp1_length > 1){

			for (t = 0; t < TUJUAN_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.TUJUAN_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.TUJUAN_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(TUJUAN_MAIN_temp1_length == 1){
			
	 		document.${formName}.TUJUAN_MAIN_temp1.value = "";			
	 		var element = document.${formName}.TUJUAN_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttTUJUAN_MAIN; i++){		

		    if(ttTUJUAN_MAIN==1){
		 check_length(document.${formName}.txtUlasanTUJUAN_MAIN,'30000','txtUlasanTUJUAN_MAIN_check','txtUlasanTUJUAN_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanTUJUAN_MAIN[i],'30000','txtUlasanTUJUAN_MAIN_check'+i,'txtUlasanTUJUAN_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//CLOSE TUJUAN



//LAPORANTANAHSUB
function textarea_LAPORANTANAHSUB_MAIN(tambahtolak,jenis,index_tolak){
	
	var LAPORANTANAHSUB_MAIN_temp1_length=0;

	if(document.${formName}.LAPORANTANAHSUB_MAIN_temp1 != null){

		if(document.${formName}.LAPORANTANAHSUB_MAIN_temp1.length>0){
			LAPORANTANAHSUB_MAIN_temp1_length = document.${formName}.LAPORANTANAHSUB_MAIN_temp1.length;
		}else{
			LAPORANTANAHSUB_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanLAPORANTANAHSUB_MAIN!=null){

		if(document.${formName}.txtUlasanLAPORANTANAHSUB_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanLAPORANTANAHSUB_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='LAPORANTANAHSUB_MAIN_tempX1' name='LAPORANTANAHSUB_MAIN_tempX1' value= '"+document.${formName}.txtUlasanLAPORANTANAHSUB_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='LAPORANTANAHSUB_MAIN_tempX1' name='LAPORANTANAHSUB_MAIN_tempX1' value= '"+document.${formName}.txtUlasanLAPORANTANAHSUB_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#LAPORANTANAHSUB_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(LAPORANTANAHSUB_MAIN_temp1_length>0){
			ttLAPORANTANAHSUB_MAIN = LAPORANTANAHSUB_MAIN_temp1_length;
		}else{
			ttLAPORANTANAHSUB_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttLAPORANTANAHSUB_MAIN = ttLAPORANTANAHSUB_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttLAPORANTANAHSUB_MAIN = ttLAPORANTANAHSUB_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttLAPORANTANAHSUB_MAIN; i++){	

  		if(ttLAPORANTANAHSUB_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.LAPORANTANAHSUB_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.LAPORANTANAHSUB_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanLAPORANTANAHSUB_MAIN\" id=\"txtUlasanLAPORANTANAHSUB_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanLAPORANTANAHSUB_MAIN_check','txtUlasanLAPORANTANAHSUB_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanLAPORANTANAHSUB_MAIN_check','txtUlasanLAPORANTANAHSUB_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanLAPORANTANAHSUB_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanLAPORANTANAHSUB_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanLAPORANTANAHSUB_MAIN\" id=\"txtUlasanLAPORANTANAHSUB_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanLAPORANTANAHSUB_MAIN_check','txtUlasanLAPORANTANAHSUB_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanLAPORANTANAHSUB_MAIN_check','txtUlasanLAPORANTANAHSUB_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanLAPORANTANAHSUB_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanLAPORANTANAHSUB_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_LAPORANTANAHSUB_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttLAPORANTANAHSUB_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_LAPORANTANAHSUB_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttLAPORANTANAHSUB_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.LAPORANTANAHSUB_MAIN_tempX1.value

			}else if(ttLAPORANTANAHSUB_MAIN>2 && i!=(ttLAPORANTANAHSUB_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.LAPORANTANAHSUB_MAIN_tempX1[i].value

			}else if(ttLAPORANTANAHSUB_MAIN>1 && i!=(ttLAPORANTANAHSUB_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.LAPORANTANAHSUB_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.LAPORANTANAHSUB_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.LAPORANTANAHSUB_MAIN_tempX1[i+1].value	
				}	

			}else if(ttLAPORANTANAHSUB_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.LAPORANTANAHSUB_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.LAPORANTANAHSUB_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='5%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 4.2."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanLAPORANTANAHSUB_MAIN\" id=\"txtUlasanLAPORANTANAHSUB_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanLAPORANTANAHSUB_MAIN_check"+i+"','txtUlasanLAPORANTANAHSUB_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanLAPORANTANAHSUB_MAIN_check"+i+"','txtUlasanLAPORANTANAHSUB_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanLAPORANTANAHSUB_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanLAPORANTANAHSUB_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanLAPORANTANAHSUB_MAIN\" id=\"txtUlasanLAPORANTANAHSUB_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanLAPORANTANAHSUB_MAIN_check"+i+"','txtUlasanLAPORANTANAHSUB_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanLAPORANTANAHSUB_MAIN_check"+i+"','txtUlasanLAPORANTANAHSUB_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanLAPORANTANAHSUB_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanLAPORANTANAHSUB_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttLAPORANTANAHSUB_MAIN>1 && ttLAPORANTANAHSUB_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_LAPORANTANAHSUB_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttLAPORANTANAHSUB_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_LAPORANTANAHSUB_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#LAPORANTANAHSUB_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(LAPORANTANAHSUB_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLAPORANTANAHSUB_MAIN.length > 1 ){

		for (t = 0; t < LAPORANTANAHSUB_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanLAPORANTANAHSUB_MAIN[t].value = document.${formName}.LAPORANTANAHSUB_MAIN_temp1[t].value;
		}

		}else if(LAPORANTANAHSUB_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLAPORANTANAHSUB_MAIN.length < 1 ){
		
			for (t = 0; t < LAPORANTANAHSUB_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanLAPORANTANAHSUB_MAIN.value = document.${formName}.LAPORANTANAHSUB_MAIN_temp1[0].value;
			}
		}
		
		else if(LAPORANTANAHSUB_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanLAPORANTANAHSUB_MAIN.value = document.${formName}.LAPORANTANAHSUB_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(LAPORANTANAHSUB_MAIN_temp1_length > 1){

			for (t = 0; t < LAPORANTANAHSUB_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.LAPORANTANAHSUB_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.LAPORANTANAHSUB_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(LAPORANTANAHSUB_MAIN_temp1_length == 1){
			
	 		document.${formName}.LAPORANTANAHSUB_MAIN_temp1.value = "";			
	 		var element = document.${formName}.LAPORANTANAHSUB_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttLAPORANTANAHSUB_MAIN; i++){		

		    if(ttLAPORANTANAHSUB_MAIN==1){
		 check_length(document.${formName}.txtUlasanLAPORANTANAHSUB_MAIN,'30000','txtUlasanLAPORANTANAHSUB_MAIN_check','txtUlasanLAPORANTANAHSUB_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanLAPORANTANAHSUB_MAIN[i],'30000','txtUlasanLAPORANTANAHSUB_MAIN_check'+i,'txtUlasanLAPORANTANAHSUB_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//CLOSE LAPORANTANAHSUB


//LATARBELAKANG
function textarea_LATARBELAKANG_MAIN(tambahtolak,jenis,index_tolak){
	
	var LATARBELAKANG_MAIN_temp1_length=0;

	if(document.${formName}.LATARBELAKANG_MAIN_temp1 != null){

		if(document.${formName}.LATARBELAKANG_MAIN_temp1.length>0){
			LATARBELAKANG_MAIN_temp1_length = document.${formName}.LATARBELAKANG_MAIN_temp1.length;
		}else{
			LATARBELAKANG_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanLATARBELAKANG_MAIN!=null){

		if(document.${formName}.txtUlasanLATARBELAKANG_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanLATARBELAKANG_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='LATARBELAKANG_MAIN_tempX1' name='LATARBELAKANG_MAIN_tempX1' value= '"+document.${formName}.txtUlasanLATARBELAKANG_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='LATARBELAKANG_MAIN_tempX1' name='LATARBELAKANG_MAIN_tempX1' value= '"+document.${formName}.txtUlasanLATARBELAKANG_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#LATARBELAKANG_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(LATARBELAKANG_MAIN_temp1_length>0){
			ttLATARBELAKANG_MAIN = LATARBELAKANG_MAIN_temp1_length;
		}else{
			ttLATARBELAKANG_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttLATARBELAKANG_MAIN = ttLATARBELAKANG_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttLATARBELAKANG_MAIN = ttLATARBELAKANG_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttLATARBELAKANG_MAIN; i++){	

  		if(ttLATARBELAKANG_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanLATARBELAKANG_MAIN\" id=\"txtUlasanLATARBELAKANG_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check','txtUlasanLATARBELAKANG_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check','txtUlasanLATARBELAKANG_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanLATARBELAKANG_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanLATARBELAKANG_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanLATARBELAKANG_MAIN\" id=\"txtUlasanLATARBELAKANG_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check','txtUlasanLATARBELAKANG_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check','txtUlasanLATARBELAKANG_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanLATARBELAKANG_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanLATARBELAKANG_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_LATARBELAKANG_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttLATARBELAKANG_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_LATARBELAKANG_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttLATARBELAKANG_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1.value

			}else if(ttLATARBELAKANG_MAIN>2 && i!=(ttLATARBELAKANG_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[i].value

			}else if(ttLATARBELAKANG_MAIN>1 && i!=(ttLATARBELAKANG_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[i+1].value	
				}	

			}else if(ttLATARBELAKANG_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.LATARBELAKANG_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 3."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanLATARBELAKANG_MAIN\" id=\"txtUlasanLATARBELAKANG_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check"+i+"','txtUlasanLATARBELAKANG_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check"+i+"','txtUlasanLATARBELAKANG_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanLATARBELAKANG_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanLATARBELAKANG_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanLATARBELAKANG_MAIN\" id=\"txtUlasanLATARBELAKANG_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check"+i+"','txtUlasanLATARBELAKANG_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check"+i+"','txtUlasanLATARBELAKANG_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanLATARBELAKANG_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanLATARBELAKANG_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttLATARBELAKANG_MAIN>1 && ttLATARBELAKANG_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_LATARBELAKANG_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttLATARBELAKANG_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_LATARBELAKANG_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#LATARBELAKANG_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(LATARBELAKANG_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLATARBELAKANG_MAIN.length > 1 ){

		for (t = 0; t < LATARBELAKANG_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanLATARBELAKANG_MAIN[t].value = document.${formName}.LATARBELAKANG_MAIN_temp1[t].value;
		}

		}else if(LATARBELAKANG_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLATARBELAKANG_MAIN.length < 1 ){
		
			for (t = 0; t < LATARBELAKANG_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanLATARBELAKANG_MAIN.value = document.${formName}.LATARBELAKANG_MAIN_temp1[0].value;
			}
		}
		
		else if(LATARBELAKANG_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanLATARBELAKANG_MAIN.value = document.${formName}.LATARBELAKANG_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(LATARBELAKANG_MAIN_temp1_length > 1){

			for (t = 0; t < LATARBELAKANG_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.LATARBELAKANG_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.LATARBELAKANG_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(LATARBELAKANG_MAIN_temp1_length == 1){
			
	 		document.${formName}.LATARBELAKANG_MAIN_temp1.value = "";			
	 		var element = document.${formName}.LATARBELAKANG_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttLATARBELAKANG_MAIN; i++){		

		    if(ttLATARBELAKANG_MAIN==1){
		 check_length(document.${formName}.txtUlasanLATARBELAKANG_MAIN,'30000','txtUlasanLATARBELAKANG_MAIN_check','txtUlasanLATARBELAKANG_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanLATARBELAKANG_MAIN[i],'30000','txtUlasanLATARBELAKANG_MAIN_check'+i,'txtUlasanLATARBELAKANG_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//CLOSE LATARBELAKANG


//LAPORANTANAH
function textarea_LAPORANTANAH_MAIN(tambahtolak,jenis,index_tolak){
	
	var LAPORANTANAH_MAIN_temp1_length=0;

	if(document.${formName}.LAPORANTANAH_MAIN_temp1 != null){

		if(document.${formName}.LAPORANTANAH_MAIN_temp1.length>0){
			LAPORANTANAH_MAIN_temp1_length = document.${formName}.LAPORANTANAH_MAIN_temp1.length;
		}else{
			LAPORANTANAH_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanLAPORANTANAH_MAIN!=null){

		if(document.${formName}.txtUlasanLAPORANTANAH_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanLAPORANTANAH_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='LAPORANTANAH_MAIN_tempX1' name='LAPORANTANAH_MAIN_tempX1' value= '"+document.${formName}.txtUlasanLAPORANTANAH_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='LAPORANTANAH_MAIN_tempX1' name='LAPORANTANAH_MAIN_tempX1' value= '"+document.${formName}.txtUlasanLAPORANTANAH_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#LAPORANTANAH_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(LAPORANTANAH_MAIN_temp1_length>0){
			ttLAPORANTANAH_MAIN = LAPORANTANAH_MAIN_temp1_length;
		}else{
			ttLAPORANTANAH_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttLAPORANTANAH_MAIN = ttLAPORANTANAH_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttLAPORANTANAH_MAIN = ttLAPORANTANAH_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttLAPORANTANAH_MAIN; i++){	

  		if(ttLAPORANTANAH_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.LAPORANTANAH_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.LAPORANTANAH_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanLAPORANTANAH_MAIN\" id=\"txtUlasanLAPORANTANAH_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanLAPORANTANAH_MAIN_check','txtUlasanLAPORANTANAH_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanLAPORANTANAH_MAIN_check','txtUlasanLAPORANTANAH_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanLAPORANTANAH_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanLAPORANTANAH_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanLAPORANTANAH_MAIN\" id=\"txtUlasanLAPORANTANAH_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanLAPORANTANAH_MAIN_check','txtUlasanLAPORANTANAH_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanLAPORANTANAH_MAIN_check','txtUlasanLAPORANTANAH_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanLAPORANTANAH_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanLAPORANTANAH_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_LAPORANTANAH_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttLAPORANTANAH_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_LAPORANTANAH_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttLAPORANTANAH_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.LAPORANTANAH_MAIN_tempX1.value

			}else if(ttLAPORANTANAH_MAIN>2 && i!=(ttLAPORANTANAH_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.LAPORANTANAH_MAIN_tempX1[i].value

			}else if(ttLAPORANTANAH_MAIN>1 && i!=(ttLAPORANTANAH_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.LAPORANTANAH_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.LAPORANTANAH_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.LAPORANTANAH_MAIN_tempX1[i+1].value	
				}	

			}else if(ttLAPORANTANAH_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.LAPORANTANAH_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.LAPORANTANAH_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 4."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanLAPORANTANAH_MAIN\" id=\"txtUlasanLAPORANTANAH_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanLAPORANTANAH_MAIN_check"+i+"','txtUlasanLAPORANTANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanLAPORANTANAH_MAIN_check"+i+"','txtUlasanLAPORANTANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanLAPORANTANAH_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanLAPORANTANAH_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanLAPORANTANAH_MAIN\" id=\"txtUlasanLAPORANTANAH_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanLAPORANTANAH_MAIN_check"+i+"','txtUlasanLAPORANTANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanLAPORANTANAH_MAIN_check"+i+"','txtUlasanLAPORANTANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanLAPORANTANAH_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanLAPORANTANAH_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttLAPORANTANAH_MAIN>1 && ttLAPORANTANAH_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_LAPORANTANAH_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttLAPORANTANAH_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_LAPORANTANAH_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#LAPORANTANAH_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(LAPORANTANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLAPORANTANAH_MAIN.length > 1 ){

		for (t = 0; t < LAPORANTANAH_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanLAPORANTANAH_MAIN[t].value = document.${formName}.LAPORANTANAH_MAIN_temp1[t].value;
		}

		}else if(LAPORANTANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLAPORANTANAH_MAIN.length < 1 ){
		
			for (t = 0; t < LAPORANTANAH_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanLAPORANTANAH_MAIN.value = document.${formName}.LAPORANTANAH_MAIN_temp1[0].value;
			}
		}
		
		else if(LAPORANTANAH_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanLAPORANTANAH_MAIN.value = document.${formName}.LAPORANTANAH_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(LAPORANTANAH_MAIN_temp1_length > 1){

			for (t = 0; t < LAPORANTANAH_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.LAPORANTANAH_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.LAPORANTANAH_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(LAPORANTANAH_MAIN_temp1_length == 1){
			
	 		document.${formName}.LAPORANTANAH_MAIN_temp1.value = "";			
	 		var element = document.${formName}.LAPORANTANAH_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttLAPORANTANAH_MAIN; i++){		

		    if(ttLAPORANTANAH_MAIN==1){
		 check_length(document.${formName}.txtUlasanLAPORANTANAH_MAIN,'30000','txtUlasanLAPORANTANAH_MAIN_check','txtUlasanLAPORANTANAH_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanLAPORANTANAH_MAIN[i],'30000','txtUlasanLAPORANTANAH_MAIN_check'+i,'txtUlasanLAPORANTANAH_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//CLOSE LAPORANTANAH


//KEADAANTANAH
function textarea_KEADAANTANAH_MAIN(tambahtolak,jenis,index_tolak){
	
	var KEADAANTANAH_MAIN_temp1_length=0;

	if(document.${formName}.KEADAANTANAH_MAIN_temp1 != null){

		if(document.${formName}.KEADAANTANAH_MAIN_temp1.length>0){
			KEADAANTANAH_MAIN_temp1_length = document.${formName}.KEADAANTANAH_MAIN_temp1.length;
		}else{
			KEADAANTANAH_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanKEADAANTANAH_MAIN!=null){

		if(document.${formName}.txtUlasanKEADAANTANAH_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanKEADAANTANAH_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='KEADAANTANAH_MAIN_tempX1' name='KEADAANTANAH_MAIN_tempX1' value= '"+document.${formName}.txtUlasanKEADAANTANAH_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='KEADAANTANAH_MAIN_tempX1' name='KEADAANTANAH_MAIN_tempX1' value= '"+document.${formName}.txtUlasanKEADAANTANAH_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#KEADAANTANAH_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(KEADAANTANAH_MAIN_temp1_length>0){
			ttKEADAANTANAH_MAIN = KEADAANTANAH_MAIN_temp1_length;
		}else{
			ttKEADAANTANAH_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttKEADAANTANAH_MAIN = ttKEADAANTANAH_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttKEADAANTANAH_MAIN = ttKEADAANTANAH_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttKEADAANTANAH_MAIN; i++){	

  		if(ttKEADAANTANAH_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.KEADAANTANAH_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.KEADAANTANAH_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanKEADAANTANAH_MAIN\" id=\"txtUlasanKEADAANTANAH_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanKEADAANTANAH_MAIN_check','txtUlasanKEADAANTANAH_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanKEADAANTANAH_MAIN_check','txtUlasanKEADAANTANAH_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanKEADAANTANAH_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanKEADAANTANAH_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanKEADAANTANAH_MAIN\" id=\"txtUlasanKEADAANTANAH_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanKEADAANTANAH_MAIN_check','txtUlasanKEADAANTANAH_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanKEADAANTANAH_MAIN_check','txtUlasanKEADAANTANAH_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanKEADAANTANAH_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanKEADAANTANAH_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_KEADAANTANAH_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttKEADAANTANAH_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_KEADAANTANAH_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttKEADAANTANAH_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.KEADAANTANAH_MAIN_tempX1.value

			}else if(ttKEADAANTANAH_MAIN>2 && i!=(ttKEADAANTANAH_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.KEADAANTANAH_MAIN_tempX1[i].value

			}else if(ttKEADAANTANAH_MAIN>1 && i!=(ttKEADAANTANAH_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.KEADAANTANAH_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.KEADAANTANAH_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.KEADAANTANAH_MAIN_tempX1[i+1].value	
				}	

			}else if(ttKEADAANTANAH_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.KEADAANTANAH_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.KEADAANTANAH_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 5."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanKEADAANTANAH_MAIN\" id=\"txtUlasanKEADAANTANAH_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanKEADAANTANAH_MAIN_check"+i+"','txtUlasanKEADAANTANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanKEADAANTANAH_MAIN_check"+i+"','txtUlasanKEADAANTANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanKEADAANTANAH_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanKEADAANTANAH_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanKEADAANTANAH_MAIN\" id=\"txtUlasanKEADAANTANAH_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanKEADAANTANAH_MAIN_check"+i+"','txtUlasanKEADAANTANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanKEADAANTANAH_MAIN_check"+i+"','txtUlasanKEADAANTANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanKEADAANTANAH_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanKEADAANTANAH_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttKEADAANTANAH_MAIN>1 && ttKEADAANTANAH_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_KEADAANTANAH_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttKEADAANTANAH_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_KEADAANTANAH_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#KEADAANTANAH_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(KEADAANTANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKEADAANTANAH_MAIN.length > 1 ){

		for (t = 0; t < KEADAANTANAH_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanKEADAANTANAH_MAIN[t].value = document.${formName}.KEADAANTANAH_MAIN_temp1[t].value;
		}

		}else if(KEADAANTANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKEADAANTANAH_MAIN.length < 1 ){
		
			for (t = 0; t < KEADAANTANAH_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanKEADAANTANAH_MAIN.value = document.${formName}.KEADAANTANAH_MAIN_temp1[0].value;
			}
		}
		
		else if(KEADAANTANAH_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanKEADAANTANAH_MAIN.value = document.${formName}.KEADAANTANAH_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(KEADAANTANAH_MAIN_temp1_length > 1){

			for (t = 0; t < KEADAANTANAH_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.KEADAANTANAH_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.KEADAANTANAH_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(KEADAANTANAH_MAIN_temp1_length == 1){
			
	 		document.${formName}.KEADAANTANAH_MAIN_temp1.value = "";			
	 		var element = document.${formName}.KEADAANTANAH_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttKEADAANTANAH_MAIN; i++){		

		    if(ttKEADAANTANAH_MAIN==1){
		 check_length(document.${formName}.txtUlasanKEADAANTANAH_MAIN,'30000','txtUlasanKEADAANTANAH_MAIN_check','txtUlasanKEADAANTANAH_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanKEADAANTANAH_MAIN[i],'30000','txtUlasanKEADAANTANAH_MAIN_check'+i,'txtUlasanKEADAANTANAH_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//CLOSE KEADAANTANAH


//PERIHALTANAH
function textarea_PERIHALTANAH_MAIN(tambahtolak,jenis,index_tolak){
	
	var PERIHALTANAH_MAIN_temp1_length=0;

	if(document.${formName}.PERIHALTANAH_MAIN_temp1 != null){

		if(document.${formName}.PERIHALTANAH_MAIN_temp1.length>0){
			PERIHALTANAH_MAIN_temp1_length = document.${formName}.PERIHALTANAH_MAIN_temp1.length;
		}else{
			PERIHALTANAH_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanPERIHALTANAH_MAIN!=null){

		if(document.${formName}.txtUlasanPERIHALTANAH_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanPERIHALTANAH_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='PERIHALTANAH_MAIN_tempX1' name='PERIHALTANAH_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPERIHALTANAH_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='PERIHALTANAH_MAIN_tempX1' name='PERIHALTANAH_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPERIHALTANAH_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#PERIHALTANAH_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(PERIHALTANAH_MAIN_temp1_length>0){
			ttPERIHALTANAH_MAIN = PERIHALTANAH_MAIN_temp1_length;
		}else{
			ttPERIHALTANAH_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttPERIHALTANAH_MAIN = ttPERIHALTANAH_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttPERIHALTANAH_MAIN = ttPERIHALTANAH_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttPERIHALTANAH_MAIN; i++){	

  		if(ttPERIHALTANAH_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.PERIHALTANAH_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.PERIHALTANAH_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanPERIHALTANAH_MAIN\" id=\"txtUlasanPERIHALTANAH_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check','txtUlasanPERIHALTANAH_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check','txtUlasanPERIHALTANAH_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERIHALTANAH_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanPERIHALTANAH_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanPERIHALTANAH_MAIN\" id=\"txtUlasanPERIHALTANAH_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check','txtUlasanPERIHALTANAH_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check','txtUlasanPERIHALTANAH_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERIHALTANAH_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanPERIHALTANAH_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_PERIHALTANAH_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttPERIHALTANAH_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_PERIHALTANAH_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttPERIHALTANAH_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.PERIHALTANAH_MAIN_tempX1.value

			}else if(ttPERIHALTANAH_MAIN>2 && i!=(ttPERIHALTANAH_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.PERIHALTANAH_MAIN_tempX1[i].value

			}else if(ttPERIHALTANAH_MAIN>1 && i!=(ttPERIHALTANAH_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.PERIHALTANAH_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.PERIHALTANAH_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.PERIHALTANAH_MAIN_tempX1[i+1].value	
				}	

			}else if(ttPERIHALTANAH_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.PERIHALTANAH_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.PERIHALTANAH_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 5."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanPERIHALTANAH_MAIN\" id=\"txtUlasanPERIHALTANAH_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check"+i+"','txtUlasanPERIHALTANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check"+i+"','txtUlasanPERIHALTANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERIHALTANAH_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERIHALTANAH_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanPERIHALTANAH_MAIN\" id=\"txtUlasanPERIHALTANAH_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check"+i+"','txtUlasanPERIHALTANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERIHALTANAH_MAIN_check"+i+"','txtUlasanPERIHALTANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERIHALTANAH_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERIHALTANAH_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttPERIHALTANAH_MAIN>1 && ttPERIHALTANAH_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_PERIHALTANAH_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttPERIHALTANAH_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_PERIHALTANAH_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#PERIHALTANAH_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(PERIHALTANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERIHALTANAH_MAIN.length > 1 ){

		for (t = 0; t < PERIHALTANAH_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanPERIHALTANAH_MAIN[t].value = document.${formName}.PERIHALTANAH_MAIN_temp1[t].value;
		}

		}else if(PERIHALTANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERIHALTANAH_MAIN.length < 1 ){
		
			for (t = 0; t < PERIHALTANAH_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanPERIHALTANAH_MAIN.value = document.${formName}.PERIHALTANAH_MAIN_temp1[0].value;
			}
		}
		
		else if(PERIHALTANAH_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanPERIHALTANAH_MAIN.value = document.${formName}.PERIHALTANAH_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(PERIHALTANAH_MAIN_temp1_length > 1){

			for (t = 0; t < PERIHALTANAH_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.PERIHALTANAH_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.PERIHALTANAH_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(PERIHALTANAH_MAIN_temp1_length == 1){
			
	 		document.${formName}.PERIHALTANAH_MAIN_temp1.value = "";			
	 		var element = document.${formName}.PERIHALTANAH_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttPERIHALTANAH_MAIN; i++){		

		    if(ttPERIHALTANAH_MAIN==1){
		 check_length(document.${formName}.txtUlasanPERIHALTANAH_MAIN,'30000','txtUlasanPERIHALTANAH_MAIN_check','txtUlasanPERIHALTANAH_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanPERIHALTANAH_MAIN[i],'30000','txtUlasanPERIHALTANAH_MAIN_check'+i,'txtUlasanPERIHALTANAH_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//CLOSE PERIHALTANAH


//KEMUDAHANASAS
function textarea_KEMUDAHANASAS_MAIN(tambahtolak,jenis,index_tolak){
	
	var KEMUDAHANASAS_MAIN_temp1_length=0;

	if(document.${formName}.KEMUDAHANASAS_MAIN_temp1 != null){

		if(document.${formName}.KEMUDAHANASAS_MAIN_temp1.length>0){
			KEMUDAHANASAS_MAIN_temp1_length = document.${formName}.KEMUDAHANASAS_MAIN_temp1.length;
		}else{
			KEMUDAHANASAS_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanKEMUDAHANASAS_MAIN!=null){

		if(document.${formName}.txtUlasanKEMUDAHANASAS_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanKEMUDAHANASAS_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='KEMUDAHANASAS_MAIN_tempX1' name='KEMUDAHANASAS_MAIN_tempX1' value= '"+document.${formName}.txtUlasanKEMUDAHANASAS_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='KEMUDAHANASAS_MAIN_tempX1' name='KEMUDAHANASAS_MAIN_tempX1' value= '"+document.${formName}.txtUlasanKEMUDAHANASAS_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#KEMUDAHANASAS_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(KEMUDAHANASAS_MAIN_temp1_length>0){
			ttKEMUDAHANASAS_MAIN = KEMUDAHANASAS_MAIN_temp1_length;
		}else{
			ttKEMUDAHANASAS_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttKEMUDAHANASAS_MAIN = ttKEMUDAHANASAS_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttKEMUDAHANASAS_MAIN = ttKEMUDAHANASAS_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttKEMUDAHANASAS_MAIN; i++){	

  		if(ttKEMUDAHANASAS_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.KEMUDAHANASAS_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.KEMUDAHANASAS_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanKEMUDAHANASAS_MAIN\" id=\"txtUlasanKEMUDAHANASAS_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanKEMUDAHANASAS_MAIN_check','txtUlasanKEMUDAHANASAS_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanKEMUDAHANASAS_MAIN_check','txtUlasanKEMUDAHANASAS_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanKEMUDAHANASAS_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanKEMUDAHANASAS_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanKEMUDAHANASAS_MAIN\" id=\"txtUlasanKEMUDAHANASAS_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanKEMUDAHANASAS_MAIN_check','txtUlasanKEMUDAHANASAS_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanKEMUDAHANASAS_MAIN_check','txtUlasanKEMUDAHANASAS_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanKEMUDAHANASAS_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanKEMUDAHANASAS_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_KEMUDAHANASAS_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttKEMUDAHANASAS_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_KEMUDAHANASAS_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttKEMUDAHANASAS_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.KEMUDAHANASAS_MAIN_tempX1.value

			}else if(ttKEMUDAHANASAS_MAIN>2 && i!=(ttKEMUDAHANASAS_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.KEMUDAHANASAS_MAIN_tempX1[i].value

			}else if(ttKEMUDAHANASAS_MAIN>1 && i!=(ttKEMUDAHANASAS_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.KEMUDAHANASAS_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.KEMUDAHANASAS_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.KEMUDAHANASAS_MAIN_tempX1[i+1].value	
				}	

			}else if(ttKEMUDAHANASAS_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.KEMUDAHANASAS_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.KEMUDAHANASAS_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 6."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanKEMUDAHANASAS_MAIN\" id=\"txtUlasanKEMUDAHANASAS_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanKEMUDAHANASAS_MAIN_check"+i+"','txtUlasanKEMUDAHANASAS_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanKEMUDAHANASAS_MAIN_check"+i+"','txtUlasanKEMUDAHANASAS_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanKEMUDAHANASAS_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanKEMUDAHANASAS_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanKEMUDAHANASAS_MAIN\" id=\"txtUlasanKEMUDAHANASAS_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanKEMUDAHANASAS_MAIN_check"+i+"','txtUlasanKEMUDAHANASAS_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanKEMUDAHANASAS_MAIN_check"+i+"','txtUlasanKEMUDAHANASAS_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanKEMUDAHANASAS_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanKEMUDAHANASAS_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttKEMUDAHANASAS_MAIN>1 && ttKEMUDAHANASAS_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_KEMUDAHANASAS_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttKEMUDAHANASAS_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_KEMUDAHANASAS_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#KEMUDAHANASAS_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(KEMUDAHANASAS_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKEMUDAHANASAS_MAIN.length > 1 ){

		for (t = 0; t < KEMUDAHANASAS_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanKEMUDAHANASAS_MAIN[t].value = document.${formName}.KEMUDAHANASAS_MAIN_temp1[t].value;
		}

		}else if(KEMUDAHANASAS_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKEMUDAHANASAS_MAIN.length < 1 ){
		
			for (t = 0; t < KEMUDAHANASAS_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanKEMUDAHANASAS_MAIN.value = document.${formName}.KEMUDAHANASAS_MAIN_temp1[0].value;
			}
		}
		
		else if(KEMUDAHANASAS_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanKEMUDAHANASAS_MAIN.value = document.${formName}.KEMUDAHANASAS_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(KEMUDAHANASAS_MAIN_temp1_length > 1){

			for (t = 0; t < KEMUDAHANASAS_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.KEMUDAHANASAS_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.KEMUDAHANASAS_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(KEMUDAHANASAS_MAIN_temp1_length == 1){
			
	 		document.${formName}.KEMUDAHANASAS_MAIN_temp1.value = "";			
	 		var element = document.${formName}.KEMUDAHANASAS_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttKEMUDAHANASAS_MAIN; i++){		

		    if(ttKEMUDAHANASAS_MAIN==1){
		 check_length(document.${formName}.txtUlasanKEMUDAHANASAS_MAIN,'30000','txtUlasanKEMUDAHANASAS_MAIN_check','txtUlasanKEMUDAHANASAS_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanKEMUDAHANASAS_MAIN[i],'30000','txtUlasanKEMUDAHANASAS_MAIN_check'+i,'txtUlasanKEMUDAHANASAS_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//CLOSE KEMUDAHANASAS


//NILAITANAH
function textarea_NILAITANAH_MAIN(tambahtolak,jenis,index_tolak){
	
	var NILAITANAH_MAIN_temp1_length=0;

	if(document.${formName}.NILAITANAH_MAIN_temp1 != null){

		if(document.${formName}.NILAITANAH_MAIN_temp1.length>0){
			NILAITANAH_MAIN_temp1_length = document.${formName}.NILAITANAH_MAIN_temp1.length;
		}else{
			NILAITANAH_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanNILAITANAH_MAIN!=null){

		if(document.${formName}.txtUlasanNILAITANAH_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanNILAITANAH_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='NILAITANAH_MAIN_tempX1' name='NILAITANAH_MAIN_tempX1' value= '"+document.${formName}.txtUlasanNILAITANAH_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='NILAITANAH_MAIN_tempX1' name='NILAITANAH_MAIN_tempX1' value= '"+document.${formName}.txtUlasanNILAITANAH_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#NILAITANAH_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(NILAITANAH_MAIN_temp1_length>0){
			ttNILAITANAH_MAIN = NILAITANAH_MAIN_temp1_length;
		}else{
			ttNILAITANAH_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttNILAITANAH_MAIN = ttNILAITANAH_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttNILAITANAH_MAIN = ttNILAITANAH_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttNILAITANAH_MAIN; i++){	

  		if(ttNILAITANAH_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.NILAITANAH_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.NILAITANAH_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanNILAITANAH_MAIN\" id=\"txtUlasanNILAITANAH_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanNILAITANAH_MAIN_check','txtUlasanNILAITANAH_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanNILAITANAH_MAIN_check','txtUlasanNILAITANAH_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanNILAITANAH_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanNILAITANAH_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanNILAITANAH_MAIN\" id=\"txtUlasanNILAITANAH_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanNILAITANAH_MAIN_check','txtUlasanNILAITANAH_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanNILAITANAH_MAIN_check','txtUlasanNILAITANAH_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanNILAITANAH_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanNILAITANAH_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_NILAITANAH_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttNILAITANAH_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_NILAITANAH_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttNILAITANAH_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.NILAITANAH_MAIN_tempX1.value

			}else if(ttNILAITANAH_MAIN>2 && i!=(ttNILAITANAH_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.NILAITANAH_MAIN_tempX1[i].value

			}else if(ttNILAITANAH_MAIN>1 && i!=(ttNILAITANAH_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.NILAITANAH_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.NILAITANAH_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.NILAITANAH_MAIN_tempX1[i+1].value	
				}	

			}else if(ttNILAITANAH_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.NILAITANAH_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.NILAITANAH_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 8."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanNILAITANAH_MAIN\" id=\"txtUlasanNILAITANAH_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanNILAITANAH_MAIN_check"+i+"','txtUlasanNILAITANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanNILAITANAH_MAIN_check"+i+"','txtUlasanNILAITANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanNILAITANAH_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanNILAITANAH_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanNILAITANAH_MAIN\" id=\"txtUlasanNILAITANAH_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanNILAITANAH_MAIN_check"+i+"','txtUlasanNILAITANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanNILAITANAH_MAIN_check"+i+"','txtUlasanNILAITANAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanNILAITANAH_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanNILAITANAH_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttNILAITANAH_MAIN>1 && ttNILAITANAH_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_NILAITANAH_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttNILAITANAH_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_NILAITANAH_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#NILAITANAH_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(NILAITANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanNILAITANAH_MAIN.length > 1 ){

		for (t = 0; t < NILAITANAH_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanNILAITANAH_MAIN[t].value = document.${formName}.NILAITANAH_MAIN_temp1[t].value;
		}

		}else if(NILAITANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanNILAITANAH_MAIN.length < 1 ){
		
			for (t = 0; t < NILAITANAH_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanNILAITANAH_MAIN.value = document.${formName}.NILAITANAH_MAIN_temp1[0].value;
			}
		}
		
		else if(NILAITANAH_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanNILAITANAH_MAIN.value = document.${formName}.NILAITANAH_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(NILAITANAH_MAIN_temp1_length > 1){

			for (t = 0; t < NILAITANAH_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.NILAITANAH_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.NILAITANAH_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(NILAITANAH_MAIN_temp1_length == 1){
			
	 		document.${formName}.NILAITANAH_MAIN_temp1.value = "";			
	 		var element = document.${formName}.NILAITANAH_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttNILAITANAH_MAIN; i++){		

		    if(ttNILAITANAH_MAIN==1){
		 check_length(document.${formName}.txtUlasanNILAITANAH_MAIN,'30000','txtUlasanNILAITANAH_MAIN_check','txtUlasanNILAITANAH_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanNILAITANAH_MAIN[i],'30000','txtUlasanNILAITANAH_MAIN_check'+i,'txtUlasanNILAITANAH_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//CLOSE NILAITANAH


//WANGPERUNTUKAN
function textarea_WANGPERUNTUKAN_MAIN(tambahtolak,jenis,index_tolak){
	
	var WANGPERUNTUKAN_MAIN_temp1_length=0;

	if(document.${formName}.WANGPERUNTUKAN_MAIN_temp1 != null){

		if(document.${formName}.WANGPERUNTUKAN_MAIN_temp1.length>0){
			WANGPERUNTUKAN_MAIN_temp1_length = document.${formName}.WANGPERUNTUKAN_MAIN_temp1.length;
		}else{
			WANGPERUNTUKAN_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN!=null){

		if(document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='WANGPERUNTUKAN_MAIN_tempX1' name='WANGPERUNTUKAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='WANGPERUNTUKAN_MAIN_tempX1' name='WANGPERUNTUKAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#WANGPERUNTUKAN_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(WANGPERUNTUKAN_MAIN_temp1_length>0){
			ttWANGPERUNTUKAN_MAIN = WANGPERUNTUKAN_MAIN_temp1_length;
		}else{
			ttWANGPERUNTUKAN_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttWANGPERUNTUKAN_MAIN = ttWANGPERUNTUKAN_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttWANGPERUNTUKAN_MAIN = ttWANGPERUNTUKAN_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttWANGPERUNTUKAN_MAIN; i++){	

  		if(ttWANGPERUNTUKAN_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.WANGPERUNTUKAN_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.WANGPERUNTUKAN_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanWANGPERUNTUKAN_MAIN\" id=\"txtUlasanWANGPERUNTUKAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanWANGPERUNTUKAN_MAIN_check','txtUlasanWANGPERUNTUKAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanWANGPERUNTUKAN_MAIN_check','txtUlasanWANGPERUNTUKAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanWANGPERUNTUKAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanWANGPERUNTUKAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanWANGPERUNTUKAN_MAIN\" id=\"txtUlasanWANGPERUNTUKAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanWANGPERUNTUKAN_MAIN_check','txtUlasanWANGPERUNTUKAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanWANGPERUNTUKAN_MAIN_check','txtUlasanWANGPERUNTUKAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanWANGPERUNTUKAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanWANGPERUNTUKAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_WANGPERUNTUKAN_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttWANGPERUNTUKAN_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_WANGPERUNTUKAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttWANGPERUNTUKAN_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.WANGPERUNTUKAN_MAIN_tempX1.value

			}else if(ttWANGPERUNTUKAN_MAIN>2 && i!=(ttWANGPERUNTUKAN_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.WANGPERUNTUKAN_MAIN_tempX1[i].value

			}else if(ttWANGPERUNTUKAN_MAIN>1 && i!=(ttWANGPERUNTUKAN_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.WANGPERUNTUKAN_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.WANGPERUNTUKAN_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.WANGPERUNTUKAN_MAIN_tempX1[i+1].value	
				}	

			}else if(ttWANGPERUNTUKAN_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.WANGPERUNTUKAN_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.WANGPERUNTUKAN_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 9."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanWANGPERUNTUKAN_MAIN\" id=\"txtUlasanWANGPERUNTUKAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanWANGPERUNTUKAN_MAIN_check"+i+"','txtUlasanWANGPERUNTUKAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanWANGPERUNTUKAN_MAIN_check"+i+"','txtUlasanWANGPERUNTUKAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanWANGPERUNTUKAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanWANGPERUNTUKAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanWANGPERUNTUKAN_MAIN\" id=\"txtUlasanWANGPERUNTUKAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanWANGPERUNTUKAN_MAIN_check"+i+"','txtUlasanWANGPERUNTUKAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanWANGPERUNTUKAN_MAIN_check"+i+"','txtUlasanWANGPERUNTUKAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanWANGPERUNTUKAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanWANGPERUNTUKAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttWANGPERUNTUKAN_MAIN>1 && ttWANGPERUNTUKAN_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_WANGPERUNTUKAN_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttWANGPERUNTUKAN_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_WANGPERUNTUKAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#WANGPERUNTUKAN_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(WANGPERUNTUKAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN.length > 1 ){

		for (t = 0; t < WANGPERUNTUKAN_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN[t].value = document.${formName}.WANGPERUNTUKAN_MAIN_temp1[t].value;
		}

		}else if(WANGPERUNTUKAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN.length < 1 ){
		
			for (t = 0; t < WANGPERUNTUKAN_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN.value = document.${formName}.WANGPERUNTUKAN_MAIN_temp1[0].value;
			}
		}
		
		else if(WANGPERUNTUKAN_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN.value = document.${formName}.WANGPERUNTUKAN_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(WANGPERUNTUKAN_MAIN_temp1_length > 1){

			for (t = 0; t < WANGPERUNTUKAN_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.WANGPERUNTUKAN_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.WANGPERUNTUKAN_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(WANGPERUNTUKAN_MAIN_temp1_length == 1){
			
	 		document.${formName}.WANGPERUNTUKAN_MAIN_temp1.value = "";			
	 		var element = document.${formName}.WANGPERUNTUKAN_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttWANGPERUNTUKAN_MAIN; i++){		

		    if(ttWANGPERUNTUKAN_MAIN==1){
		 check_length(document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN,'30000','txtUlasanWANGPERUNTUKAN_MAIN_check','txtUlasanWANGPERUNTUKAN_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN[i],'30000','txtUlasanWANGPERUNTUKAN_MAIN_check'+i,'txtUlasanWANGPERUNTUKAN_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//CLOSE WANGPERUNTUKAN


//ULASANPT
function textarea_ULASANPT_MAIN(tambahtolak,jenis,index_tolak){
	
	var ULASANPT_MAIN_temp1_length=0;

	if(document.${formName}.ULASANPT_MAIN_temp1 != null){

		if(document.${formName}.ULASANPT_MAIN_temp1.length>0){
			ULASANPT_MAIN_temp1_length = document.${formName}.ULASANPT_MAIN_temp1.length;
		}else{
			ULASANPT_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanULASANPT_MAIN!=null){

		if(document.${formName}.txtUlasanULASANPT_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanULASANPT_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='ULASANPT_MAIN_tempX1' name='ULASANPT_MAIN_tempX1' value= '"+document.${formName}.txtUlasanULASANPT_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='ULASANPT_MAIN_tempX1' name='ULASANPT_MAIN_tempX1' value= '"+document.${formName}.txtUlasanULASANPT_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#ULASANPT_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(ULASANPT_MAIN_temp1_length>0){
			ttULASANPT_MAIN = ULASANPT_MAIN_temp1_length;
		}else{
			ttULASANPT_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttULASANPT_MAIN = ttULASANPT_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttULASANPT_MAIN = ttULASANPT_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttULASANPT_MAIN; i++){	

  		if(ttULASANPT_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.ULASANPT_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.ULASANPT_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanULASANPT_MAIN\" id=\"txtUlasanULASANPT_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanULASANPT_MAIN_check','txtUlasanULASANPT_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPT_MAIN_check','txtUlasanULASANPT_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanULASANPT_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanULASANPT_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanULASANPT_MAIN\" id=\"txtUlasanULASANPT_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanULASANPT_MAIN_check','txtUlasanULASANPT_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPT_MAIN_check','txtUlasanULASANPT_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanULASANPT_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanULASANPT_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_ULASANPT_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttULASANPT_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_ULASANPT_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttULASANPT_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.ULASANPT_MAIN_tempX1.value

			}else if(ttULASANPT_MAIN>2 && i!=(ttULASANPT_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.ULASANPT_MAIN_tempX1[i].value

			}else if(ttULASANPT_MAIN>1 && i!=(ttULASANPT_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.ULASANPT_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.ULASANPT_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.ULASANPT_MAIN_tempX1[i+1].value	
				}	

			}else if(ttULASANPT_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.ULASANPT_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.ULASANPT_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 10."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanULASANPT_MAIN\" id=\"txtUlasanULASANPT_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanULASANPT_MAIN_check"+i+"','txtUlasanULASANPT_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPT_MAIN_check"+i+"','txtUlasanULASANPT_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanULASANPT_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanULASANPT_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanULASANPT_MAIN\" id=\"txtUlasanULASANPT_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanULASANPT_MAIN_check"+i+"','txtUlasanULASANPT_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPT_MAIN_check"+i+"','txtUlasanULASANPT_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanULASANPT_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanULASANPT_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttULASANPT_MAIN>1 && ttULASANPT_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_ULASANPT_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttULASANPT_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_ULASANPT_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#ULASANPT_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(ULASANPT_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPT_MAIN.length > 1 ){

		for (t = 0; t < ULASANPT_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanULASANPT_MAIN[t].value = document.${formName}.ULASANPT_MAIN_temp1[t].value;
		}

		}else if(ULASANPT_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPT_MAIN.length < 1 ){
		
			for (t = 0; t < ULASANPT_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanULASANPT_MAIN.value = document.${formName}.ULASANPT_MAIN_temp1[0].value;
			}
		}
		
		else if(ULASANPT_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanULASANPT_MAIN.value = document.${formName}.ULASANPT_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(ULASANPT_MAIN_temp1_length > 1){

			for (t = 0; t < ULASANPT_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.ULASANPT_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.ULASANPT_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(ULASANPT_MAIN_temp1_length == 1){
			
	 		document.${formName}.ULASANPT_MAIN_temp1.value = "";			
	 		var element = document.${formName}.ULASANPT_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttULASANPT_MAIN; i++){		

		    if(ttULASANPT_MAIN==1){
		 check_length(document.${formName}.txtUlasanULASANPT_MAIN,'30000','txtUlasanULASANPT_MAIN_check','txtUlasanULASANPT_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanULASANPT_MAIN[i],'30000','txtUlasanULASANPT_MAIN_check'+i,'txtUlasanULASANPT_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//CLOSE ULASANPT


//PERAKUANPT
function textarea_PERAKUANPT_MAIN(tambahtolak,jenis,index_tolak){
	
	var PERAKUANPT_MAIN_temp1_length=0;

	if(document.${formName}.PERAKUANPT_MAIN_temp1 != null){

		if(document.${formName}.PERAKUANPT_MAIN_temp1.length>0){
			PERAKUANPT_MAIN_temp1_length = document.${formName}.PERAKUANPT_MAIN_temp1.length;
		}else{
			PERAKUANPT_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanPERAKUANPT_MAIN!=null){

		if(document.${formName}.txtUlasanPERAKUANPT_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanPERAKUANPT_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='PERAKUANPT_MAIN_tempX1' name='PERAKUANPT_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPERAKUANPT_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='PERAKUANPT_MAIN_tempX1' name='PERAKUANPT_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPERAKUANPT_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#PERAKUANPT_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(PERAKUANPT_MAIN_temp1_length>0){
			ttPERAKUANPT_MAIN = PERAKUANPT_MAIN_temp1_length;
		}else{
			ttPERAKUANPT_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttPERAKUANPT_MAIN = ttPERAKUANPT_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttPERAKUANPT_MAIN = ttPERAKUANPT_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttPERAKUANPT_MAIN; i++){	

  		if(ttPERAKUANPT_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.PERAKUANPT_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.PERAKUANPT_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanPERAKUANPT_MAIN\" id=\"txtUlasanPERAKUANPT_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check','txtUlasanPERAKUANPT_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check','txtUlasanPERAKUANPT_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERAKUANPT_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanPERAKUANPT_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanPERAKUANPT_MAIN\" id=\"txtUlasanPERAKUANPT_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check','txtUlasanPERAKUANPT_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check','txtUlasanPERAKUANPT_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERAKUANPT_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanPERAKUANPT_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_PERAKUANPT_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttPERAKUANPT_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_PERAKUANPT_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttPERAKUANPT_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.PERAKUANPT_MAIN_tempX1.value

			}else if(ttPERAKUANPT_MAIN>2 && i!=(ttPERAKUANPT_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.PERAKUANPT_MAIN_tempX1[i].value

			}else if(ttPERAKUANPT_MAIN>1 && i!=(ttPERAKUANPT_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.PERAKUANPT_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.PERAKUANPT_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.PERAKUANPT_MAIN_tempX1[i+1].value	
				}	

			}else if(ttPERAKUANPT_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.PERAKUANPT_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.PERAKUANPT_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='5%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 11.1."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='92%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanPERAKUANPT_MAIN\" id=\"txtUlasanPERAKUANPT_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check"+i+"','txtUlasanPERAKUANPT_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check"+i+"','txtUlasanPERAKUANPT_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERAKUANPT_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERAKUANPT_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanPERAKUANPT_MAIN\" id=\"txtUlasanPERAKUANPT_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check"+i+"','txtUlasanPERAKUANPT_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check"+i+"','txtUlasanPERAKUANPT_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERAKUANPT_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERAKUANPT_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttPERAKUANPT_MAIN>1 && ttPERAKUANPT_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_PERAKUANPT_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttPERAKUANPT_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_PERAKUANPT_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#PERAKUANPT_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(PERAKUANPT_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERAKUANPT_MAIN.length > 1 ){

		for (t = 0; t < PERAKUANPT_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanPERAKUANPT_MAIN[t].value = document.${formName}.PERAKUANPT_MAIN_temp1[t].value;
		}

		}else if(PERAKUANPT_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERAKUANPT_MAIN.length < 1 ){
		
			for (t = 0; t < PERAKUANPT_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanPERAKUANPT_MAIN.value = document.${formName}.PERAKUANPT_MAIN_temp1[0].value;
			}
		}
		
		else if(PERAKUANPT_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanPERAKUANPT_MAIN.value = document.${formName}.PERAKUANPT_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(PERAKUANPT_MAIN_temp1_length > 1){

			for (t = 0; t < PERAKUANPT_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.PERAKUANPT_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.PERAKUANPT_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(PERAKUANPT_MAIN_temp1_length == 1){
			
	 		document.${formName}.PERAKUANPT_MAIN_temp1.value = "";			
	 		var element = document.${formName}.PERAKUANPT_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttPERAKUANPT_MAIN; i++){		

		    if(ttPERAKUANPT_MAIN==1){
		 check_length(document.${formName}.txtUlasanPERAKUANPT_MAIN,'30000','txtUlasanPERAKUANPT_MAIN_check','txtUlasanPERAKUANPT_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanPERAKUANPT_MAIN[i],'30000','txtUlasanPERAKUANPT_MAIN_check'+i,'txtUlasanPERAKUANPT_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//CLOSE PERAKUANPT


//KEPUTUSAN
function textarea_KEPUTUSAN_MAIN(tambahtolak,jenis,index_tolak){
	
	var KEPUTUSAN_MAIN_temp1_length=0;

	if(document.${formName}.KEPUTUSAN_MAIN_temp1 != null){

		if(document.${formName}.KEPUTUSAN_MAIN_temp1.length>0){
			KEPUTUSAN_MAIN_temp1_length = document.${formName}.KEPUTUSAN_MAIN_temp1.length;
		}else{
			KEPUTUSAN_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanKEPUTUSAN_MAIN!=null){

		if(document.${formName}.txtUlasanKEPUTUSAN_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanKEPUTUSAN_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='KEPUTUSAN_MAIN_tempX1' name='KEPUTUSAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanKEPUTUSAN_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='KEPUTUSAN_MAIN_tempX1' name='KEPUTUSAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanKEPUTUSAN_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#KEPUTUSAN_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(KEPUTUSAN_MAIN_temp1_length>0){
			ttKEPUTUSAN_MAIN = KEPUTUSAN_MAIN_temp1_length;
		}else{
			ttKEPUTUSAN_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttKEPUTUSAN_MAIN = ttKEPUTUSAN_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttKEPUTUSAN_MAIN = ttKEPUTUSAN_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttKEPUTUSAN_MAIN; i++){	

  		if(ttKEPUTUSAN_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($view=='no') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanKEPUTUSAN_MAIN\" id=\"txtUlasanKEPUTUSAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanKEPUTUSAN_MAIN_check','txtUlasanKEPUTUSAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanKEPUTUSAN_MAIN_check','txtUlasanKEPUTUSAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanKEPUTUSAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanKEPUTUSAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($view=='yes') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disabilityP name=\"txtUlasanKEPUTUSAN_MAIN\" id=\"txtUlasanKEPUTUSAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanKEPUTUSAN_MAIN_check','txtUlasanKEPUTUSAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanKEPUTUSAN_MAIN_check','txtUlasanKEPUTUSAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanKEPUTUSAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanKEPUTUSAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_KEPUTUSAN_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttKEPUTUSAN_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_KEPUTUSAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttKEPUTUSAN_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1.value

			}else if(ttKEPUTUSAN_MAIN>2 && i!=(ttKEPUTUSAN_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[i].value

			}else if(ttKEPUTUSAN_MAIN>1 && i!=(ttKEPUTUSAN_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[i+1].value	
				}	

			}else if(ttKEPUTUSAN_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.KEPUTUSAN_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 12."+(i+1)+"  </td> 																		"+
				  		"		#if($!view=='no') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanKEPUTUSAN_MAIN\" id=\"txtUlasanKEPUTUSAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanKEPUTUSAN_MAIN_check"+i+"','txtUlasanKEPUTUSAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanKEPUTUSAN_MAIN_check"+i+"','txtUlasanKEPUTUSAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanKEPUTUSAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanKEPUTUSAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!view=='yes') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disabilityP name=\"txtUlasanKEPUTUSAN_MAIN\" id=\"txtUlasanKEPUTUSAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanKEPUTUSAN_MAIN_check"+i+"','txtUlasanKEPUTUSAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanKEPUTUSAN_MAIN_check"+i+"','txtUlasanKEPUTUSAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanKEPUTUSAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanKEPUTUSAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($view=='no' || ($view=='yes' && ($edit_penyediaan=='yes' || $edit_penyediaan=='new')) ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttKEPUTUSAN_MAIN>1 && ttKEPUTUSAN_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_KEPUTUSAN_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttKEPUTUSAN_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_KEPUTUSAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#KEPUTUSAN_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(KEPUTUSAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKEPUTUSAN_MAIN.length > 1 ){

		for (t = 0; t < KEPUTUSAN_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanKEPUTUSAN_MAIN[t].value = document.${formName}.KEPUTUSAN_MAIN_temp1[t].value;
		}

		}else if(KEPUTUSAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKEPUTUSAN_MAIN.length < 1 ){
		
			for (t = 0; t < KEPUTUSAN_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanKEPUTUSAN_MAIN.value = document.${formName}.KEPUTUSAN_MAIN_temp1[0].value;
			}
		}
		
		else if(KEPUTUSAN_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanKEPUTUSAN_MAIN.value = document.${formName}.KEPUTUSAN_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(KEPUTUSAN_MAIN_temp1_length > 1){

			for (t = 0; t < KEPUTUSAN_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.KEPUTUSAN_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.KEPUTUSAN_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(KEPUTUSAN_MAIN_temp1_length == 1){
			
	 		document.${formName}.KEPUTUSAN_MAIN_temp1.value = "";			
	 		var element = document.${formName}.KEPUTUSAN_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttKEPUTUSAN_MAIN; i++){		

		    if(ttKEPUTUSAN_MAIN==1){
		 check_length(document.${formName}.txtUlasanKEPUTUSAN_MAIN,'30000','txtUlasanKEPUTUSAN_MAIN_check','txtUlasanKEPUTUSAN_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanKEPUTUSAN_MAIN[i],'30000','txtUlasanKEPUTUSAN_MAIN_check'+i,'txtUlasanKEPUTUSAN_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//CLOSE KEPUTUSAN


function textarea_kerosakan1()
{

	//BUTIRASAS
	var BUTIRASAS_MAIN_temp1_length=0;
    
	if(document.${formName}.BUTIRASAS_MAIN_temp1 != null){
		if(document.${formName}.BUTIRASAS_MAIN_temp1.length>0){
			BUTIRASAS_MAIN_temp1_length = document.${formName}.BUTIRASAS_MAIN_temp1.length;
		}else{
			BUTIRASAS_MAIN_temp1_length = 1;
		}
	}

    if(BUTIRASAS_MAIN_temp1_length > 1 && document.${formName}.txtUlasanBUTIRASAS_MAIN.length > 1 ){
		for (t = 0; t < BUTIRASAS_MAIN_temp1_length; t++){	
    		document.${formName}.BUTIRASAS_MAIN_temp1[t].value = document.${formName}.txtUlasanBUTIRASAS_MAIN[t].value;
		}
	}else if(BUTIRASAS_MAIN_temp1_length > 1 && document.${formName}.txtUlasanBUTIRASAS_MAIN.length < 1 ){
		for (t = 0; t < BUTIRASAS_MAIN_temp1_length; t++){	
    		document.${formName}.BUTIRASAS_MAIN_temp1[0].value = document.${formName}.txtUlasanBUTIRASAS_MAIN.value;
    	}
	}else if(BUTIRASAS_MAIN_temp1_length == 1){
		document.${formName}.BUTIRASAS_MAIN_temp1.value = document.${formName}.txtUlasanBUTIRASAS_MAIN.value;
	}
	
	//TUJUAN
	var TUJUAN_MAIN_temp1_length=0;
    
	if(document.${formName}.TUJUAN_MAIN_temp1 != null){
		if(document.${formName}.TUJUAN_MAIN_temp1.length>0){
			TUJUAN_MAIN_temp1_length = document.${formName}.TUJUAN_MAIN_temp1.length;
		}else{
			TUJUAN_MAIN_temp1_length = 1;
		}
	}

    if(TUJUAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanTUJUAN_MAIN.length > 1 ){
		for (t = 0; t < TUJUAN_MAIN_temp1_length; t++){	
    		document.${formName}.TUJUAN_MAIN_temp1[t].value = document.${formName}.txtUlasanTUJUAN_MAIN[t].value;
		}
	}else if(TUJUAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanTUJUAN_MAIN.length < 1 ){
		for (t = 0; t < TUJUAN_MAIN_temp1_length; t++){	
    		document.${formName}.TUJUAN_MAIN_temp1[0].value = document.${formName}.txtUlasanTUJUAN_MAIN.value;
    	}
	}else if(TUJUAN_MAIN_temp1_length == 1){
		document.${formName}.TUJUAN_MAIN_temp1.value = document.${formName}.txtUlasanTUJUAN_MAIN.value;
	}


    //LATAR BELAKANG
 	var LATARBELAKANG_MAIN_temp1_length=0;
    
	if(document.${formName}.LATARBELAKANG_MAIN_temp1 != null){
		if(document.${formName}.LATARBELAKANG_MAIN_temp1.length>0){
			LATARBELAKANG_MAIN_temp1_length = document.${formName}.LATARBELAKANG_MAIN_temp1.length;
		}else{
			LATARBELAKANG_MAIN_temp1_length = 1;
		}
	}

    if(LATARBELAKANG_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLATARBELAKANG_MAIN.length > 1 ){
		for (t = 0; t < LATARBELAKANG_MAIN_temp1_length; t++){	
    		document.${formName}.LATARBELAKANG_MAIN_temp1[t].value = document.${formName}.txtUlasanLATARBELAKANG_MAIN[t].value;
		}
	}else if(LATARBELAKANG_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLATARBELAKANG_MAIN.length < 1 ){
		for (t = 0; t < LATARBELAKANG_MAIN_temp1_length; t++){	
    		document.${formName}.LATARBELAKANG_MAIN_temp1[0].value = document.${formName}.txtUlasanLATARBELAKANG_MAIN.value;
    	}
	}else if(LATARBELAKANG_MAIN_temp1_length == 1){
		document.${formName}.LATARBELAKANG_MAIN_temp1.value = document.${formName}.txtUlasanLATARBELAKANG_MAIN.value;
	}

    
  	//LAPORAN TANAH
 	var PERIHALTANAH_MAIN_temp1_length=0;
    
	if(document.${formName}.PERIHALTANAH_MAIN_temp1 != null){
		if(document.${formName}.PERIHALTANAH_MAIN_temp1.length>0){
			PERIHALTANAH_MAIN_temp1_length = document.${formName}.PERIHALTANAH_MAIN_temp1.length;
		}else{
			PERIHALTANAH_MAIN_temp1_length = 1;
		}
	}

    if(PERIHALTANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERIHALTANAH_MAIN.length > 1 ){
		for (t = 0; t < PERIHALTANAH_MAIN_temp1_length; t++){	
    		document.${formName}.PERIHALTANAH_MAIN_temp1[t].value = document.${formName}.txtUlasanPERIHALTANAH_MAIN[t].value;
		}
	}else if(PERIHALTANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERIHALTANAH_MAIN.length < 1 ){
		for (t = 0; t < PERIHALTANAH_MAIN_temp1_length; t++){	
    		document.${formName}.PERIHALTANAH_MAIN_temp1[0].value = document.${formName}.txtUlasanPERIHALTANAH_MAIN.value;
    	}
	}else if(PERIHALTANAH_MAIN_temp1_length == 1){
		document.${formName}.PERIHALTANAH_MAIN_temp1.value = document.${formName}.txtUlasanPERIHALTANAH_MAIN.value;
	}

	//KEADAANTANAH
 	var KEADAANTANAH_MAIN_temp1_length=0;
    
	if(document.${formName}.KEADAANTANAH_MAIN_temp1 != null){
		if(document.${formName}.KEADAANTANAH_MAIN_temp1.length>0){
			KEADAANTANAH_MAIN_temp1_length = document.${formName}.KEADAANTANAH_MAIN_temp1.length;
		}else{
			KEADAANTANAH_MAIN_temp1_length = 1;
		}
	}

    if(KEADAANTANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKEADAANTANAH_MAIN.length > 1 ){
		for (t = 0; t < KEADAANTANAH_MAIN_temp1_length; t++){	
    		document.${formName}.KEADAANTANAH_MAIN_temp1[t].value = document.${formName}.txtUlasanKEADAANTANAH_MAIN[t].value;
		}
	}else if(KEADAANTANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKEADAANTANAH_MAIN.length < 1 ){
		for (t = 0; t < KEADAANTANAH_MAIN_temp1_length; t++){	
    		document.${formName}.KEADAANTANAH_MAIN_temp1[0].value = document.${formName}.txtUlasanKEADAANTANAH_MAIN.value;
    	}
	}else if(KEADAANTANAH_MAIN_temp1_length == 1){
		document.${formName}.KEADAANTANAH_MAIN_temp1.value = document.${formName}.txtUlasanKEADAANTANAH_MAIN.value;
	}

    
  	//NILAIAN TANAH
 	var PERIHALTANAH_MAIN_temp1_length=0;
    
	if(document.${formName}.PERIHALTANAH_MAIN_temp1 != null){
		if(document.${formName}.PERIHALTANAH_MAIN_temp1.length>0){
			PERIHALTANAH_MAIN_temp1_length = document.${formName}.PERIHALTANAH_MAIN_temp1.length;
		}else{
			PERIHALTANAH_MAIN_temp1_length = 1;
		}
	}

    if(PERIHALTANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERIHALTANAH_MAIN.length > 1 ){
		for (t = 0; t < PERIHALTANAH_MAIN_temp1_length; t++){	
    		document.${formName}.PERIHALTANAH_MAIN_temp1[t].value = document.${formName}.txtUlasanPERIHALTANAH_MAIN[t].value;
		}
	}else if(PERIHALTANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERIHALTANAH_MAIN.length < 1 ){
		for (t = 0; t < PERIHALTANAH_MAIN_temp1_length; t++){	
    		document.${formName}.PERIHALTANAH_MAIN_temp1[0].value = document.${formName}.txtUlasanPERIHALTANAH_MAIN.value;
    	}
	}else if(PERIHALTANAH_MAIN_temp1_length == 1){
		document.${formName}.PERIHALTANAH_MAIN_temp1.value = document.${formName}.txtUlasanPERIHALTANAH_MAIN.value;
	}

  	//ULASAN PENGARAH
 	var KEMUDAHANASAS_MAIN_temp1_length=0;
    
	if(document.${formName}.KEMUDAHANASAS_MAIN_temp1 != null){
		if(document.${formName}.KEMUDAHANASAS_MAIN_temp1.length>0){
			KEMUDAHANASAS_MAIN_temp1_length = document.${formName}.KEMUDAHANASAS_MAIN_temp1.length;
		}else{
			KEMUDAHANASAS_MAIN_temp1_length = 1;
		}
	}

    if(KEMUDAHANASAS_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKEMUDAHANASAS_MAIN.length > 1 ){
		for (t = 0; t < KEMUDAHANASAS_MAIN_temp1_length; t++){	
    		document.${formName}.KEMUDAHANASAS_MAIN_temp1[t].value = document.${formName}.txtUlasanKEMUDAHANASAS_MAIN[t].value;
		}
	}else if(KEMUDAHANASAS_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKEMUDAHANASAS_MAIN.length < 1 ){
		for (t = 0; t < KEMUDAHANASAS_MAIN_temp1_length; t++){	
    		document.${formName}.KEMUDAHANASAS_MAIN_temp1[0].value = document.${formName}.txtUlasanKEMUDAHANASAS_MAIN.value;
    	}
	}else if(KEMUDAHANASAS_MAIN_temp1_length == 1){
		document.${formName}.KEMUDAHANASAS_MAIN_temp1.value = document.${formName}.txtUlasanKEMUDAHANASAS_MAIN.value;
	}


  	//NILAITANAH
 	var NILAITANAH_MAIN_temp1_length=0;
    
	if(document.${formName}.NILAITANAH_MAIN_temp1 != null){
		if(document.${formName}.NILAITANAH_MAIN_temp1.length>0){
			NILAITANAH_MAIN_temp1_length = document.${formName}.NILAITANAH_MAIN_temp1.length;
		}else{
			NILAITANAH_MAIN_temp1_length = 1;
		}
	}

    if(NILAITANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanNILAITANAH_MAIN.length > 1 ){
		for (t = 0; t < NILAITANAH_MAIN_temp1_length; t++){	
    		document.${formName}.NILAITANAH_MAIN_temp1[t].value = document.${formName}.txtUlasanNILAITANAH_MAIN[t].value;
		}
	}else if(NILAITANAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanNILAITANAH_MAIN.length < 1 ){
		for (t = 0; t < NILAITANAH_MAIN_temp1_length; t++){	
    		document.${formName}.NILAITANAH_MAIN_temp1[0].value = document.${formName}.txtUlasanNILAITANAH_MAIN.value;
    	}
	}else if(NILAITANAH_MAIN_temp1_length == 1){
		document.${formName}.NILAITANAH_MAIN_temp1.value = document.${formName}.txtUlasanNILAITANAH_MAIN.value;
	}


  	//WANGPERUNTUKAN
 	var WANGPERUNTUKAN_MAIN_temp1_length=0;
    
	if(document.${formName}.WANGPERUNTUKAN_MAIN_temp1 != null){
		if(document.${formName}.WANGPERUNTUKAN_MAIN_temp1.length>0){
			WANGPERUNTUKAN_MAIN_temp1_length = document.${formName}.WANGPERUNTUKAN_MAIN_temp1.length;
		}else{
			WANGPERUNTUKAN_MAIN_temp1_length = 1;
		}
	}

    if(WANGPERUNTUKAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN.length > 1 ){
		for (t = 0; t < WANGPERUNTUKAN_MAIN_temp1_length; t++){	
    		document.${formName}.WANGPERUNTUKAN_MAIN_temp1[t].value = document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN[t].value;
		}
	}else if(WANGPERUNTUKAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN.length < 1 ){
		for (t = 0; t < WANGPERUNTUKAN_MAIN_temp1_length; t++){	
    		document.${formName}.WANGPERUNTUKAN_MAIN_temp1[0].value = document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN.value;
    	}
	}else if(WANGPERUNTUKAN_MAIN_temp1_length == 1){
		document.${formName}.WANGPERUNTUKAN_MAIN_temp1.value = document.${formName}.txtUlasanWANGPERUNTUKAN_MAIN.value;
	}

    
  	//ULASANPT
 	var ULASANPT_MAIN_temp1_length=0;
    
	if(document.${formName}.ULASANPT_MAIN_temp1 != null){
		if(document.${formName}.ULASANPT_MAIN_temp1.length>0){
			ULASANPT_MAIN_temp1_length = document.${formName}.ULASANPT_MAIN_temp1.length;
		}else{
			ULASANPT_MAIN_temp1_length = 1;
		}
	}

    if(ULASANPT_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPT_MAIN.length > 1 ){
		for (t = 0; t < ULASANPT_MAIN_temp1_length; t++){	
    		document.${formName}.ULASANPT_MAIN_temp1[t].value = document.${formName}.txtUlasanULASANPT_MAIN[t].value;
		}
	}else if(ULASANPT_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPT_MAIN.length < 1 ){
		for (t = 0; t < ULASANPT_MAIN_temp1_length; t++){	
    		document.${formName}.ULASANPT_MAIN_temp1[0].value = document.${formName}.txtUlasanULASANPT_MAIN.value;
    	}
	}else if(ULASANPT_MAIN_temp1_length == 1){
		document.${formName}.ULASANPT_MAIN_temp1.value = document.${formName}.txtUlasanULASANPT_MAIN.value;
	}

  	//PERAKUANPT
 	var PERAKUANPT_MAIN_temp1_length=0;
    
	if(document.${formName}.PERAKUANPT_MAIN_temp1 != null){
		if(document.${formName}.PERAKUANPT_MAIN_temp1.length>0){
			PERAKUANPT_MAIN_temp1_length = document.${formName}.PERAKUANPT_MAIN_temp1.length;
		}else{
			PERAKUANPT_MAIN_temp1_length = 1;
		}
	}

    if(PERAKUANPT_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERAKUANPT_MAIN.length > 1 ){
		for (t = 0; t < PERAKUANPT_MAIN_temp1_length; t++){	
    		document.${formName}.PERAKUANPT_MAIN_temp1[t].value = document.${formName}.txtUlasanPERAKUANPT_MAIN[t].value;
		}
	}else if(PERAKUANPT_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERAKUANPT_MAIN.length < 1 ){
		for (t = 0; t < PERAKUANPT_MAIN_temp1_length; t++){	
    		document.${formName}.PERAKUANPT_MAIN_temp1[0].value = document.${formName}.txtUlasanPERAKUANPT_MAIN.value;
    	}
	}else if(PERAKUANPT_MAIN_temp1_length == 1){
		document.${formName}.PERAKUANPT_MAIN_temp1.value = document.${formName}.txtUlasanPERAKUANPT_MAIN.value;
	}


 	//KEPUTUSAN
 	var KEPUTUSAN_MAIN_temp1_length=0;
    
	if(document.${formName}.KEPUTUSAN_MAIN_temp1 != null){
		if(document.${formName}.KEPUTUSAN_MAIN_temp1.length>0){
			KEPUTUSAN_MAIN_temp1_length = document.${formName}.KEPUTUSAN_MAIN_temp1.length;
		}else{
			KEPUTUSAN_MAIN_temp1_length = 1;
		}
	}

    if(KEPUTUSAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKEPUTUSAN_MAIN.length > 1 ){
		for (t = 0; t < KEPUTUSAN_MAIN_temp1_length; t++){	
    		document.${formName}.KEPUTUSAN_MAIN_temp1[t].value = document.${formName}.txtUlasanKEPUTUSAN_MAIN[t].value;
		}
	}else if(KEPUTUSAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanKEPUTUSAN_MAIN.length < 1 ){
		for (t = 0; t < KEPUTUSAN_MAIN_temp1_length; t++){	
    		document.${formName}.KEPUTUSAN_MAIN_temp1[0].value = document.${formName}.txtUlasanKEPUTUSAN_MAIN.value;
    	}
	}else if(KEPUTUSAN_MAIN_temp1_length == 1){
		document.${formName}.KEPUTUSAN_MAIN_temp1.value = document.${formName}.txtUlasanKEPUTUSAN_MAIN.value;
	}
	
}

</script>