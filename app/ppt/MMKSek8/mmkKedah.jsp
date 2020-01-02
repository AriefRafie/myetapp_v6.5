

#set($txtRingkasan = "KERTAS KERJA BERHUBUNG DENGAN CADANGAN PENGAMBILAN ..................................., NEGERI KEDAH DARUL AMAN OLEH PENGARAH TANAH DAN GALIAN NEGERI KEDAH DARUL AMAN BAGI PIHAK PESURUHJAYA TANAH PERSEKUTUAN UNTUK TUJUAN AWAM IAITU PENGAMBILAN TANAH BAGI ................................................ DI BAWAH SEKYEN 8, AKTA PENGAMBILAN TANAH 1960 (AKTA 486).")

#set($txtTujuan = "Tujuan kertas kerja ini adalah untuk melaporkan dan selanjutnya memohon pertimbangan Majlis Mesyuarat Kerajaan Negeri Kedah Darul Aman mengenai permohonan oleh Pengarah Tanah Dan Galian Negeri Kedah Darul Aman bagi pihak Pesuruhjaya Tanah Persekutuan supaya keseluruhan ..........................................................., Negeri Kedah Darul Aman seperti ditunjuk dengan warna merah di pelan berkelat dibeliambil di bawah Seksyen 3(1)(a) Akta Pengambilan Tanah 1960 [(Akta 486) (Pindaaan 1997)] untuk maksud yang tersebut di atas.")

#set($txtLatarBelakang1 = "Pengambilan tanah ini melibatkan ........................................................")

#set($txtLatarBelakang2 = "Tapak cadangan ini melibatkan tanah berimilik seluas ................... dipilih sendiri oleh ........................... dan dari segi kedudukannya adalah sesuai dengan maksud yang dipinta.")

#set($txtLatarBelakang3 = "Peruntukan yang mencukupi bagi membiayai pengambilan tanah ini telah pun disediakan oleh ........................... menurut surat Rujukan: ................... bertarikh .....................")



#set($pendapat1 = "Pengarah Tanah dan Galian tiada sebarang ketegahan di atas permohonan ini oleh kerana tanah tersebut adalah diperlukan dan dikehendaki pada keutamaannya untuk tujuan awam iaitu ................................  yang mana pada pandangan pentadbiran ini adalah selaras dengan kehendak Seksyen 3(1)(a) Akta Pengambilan Tanah 1960 yang berbunyi:-")

#set($pendapat2 = "Oleh yang demikian, inilah dipohon persetujuan Pihak Berkuasa Negeri supaya pengambilan tanah tersebut untuk maksud di atas seperti yang tercatat di senarai Borang D (Pengisytiharan Pengambilan Tanah Yang Dicadangkan) berkelat dapat diisytiharkan dalam Warta Kerajaan Negeri Kedah Darul Aman di bawah Seksyen 8, Akta Pengambilan Tanah 1960.")

#set($pendapat3 = "pengambilan tanah .......................................... di bawah Seksyen 3(1)(a) Akta Pengambilan Tanah 1960 kepada Pesuruhjaya Tanah Persekutuan untuk tujuan awam iaitu .................................... dan diisytiharkan dalam Warta Kerajaan Negeri Kedah Darul Aman menurut senarai Borang D berkelat;")

#set($pendapat4 = "dikeluarkan Sijil Kesegeraan kepada ............................ di bawah Seksyen 19, Akta Pengambilan Tanah 1960 jika dipinta;")

#set($pendapat5 = "sokongan hakmilik tapak ini akan disediakan selepas proses pengambilan selesai kelak; dan")

#set($pendapat6 = "pembayaran untuk mengisytiharkan Borang D dalam Warta Kerajaan adalah di bawah tanggungan pihak pemohon.")




#set($perakuan1 = "bersetuju meluluskan pengambilan ........................................... di bawah Seksyen 3(1)(a) Akta Pengambilan Tanah 1960 kepada Pesuruhjaya Tanah Persekutuan untuk tujuan awam iaitu ........................................... dan diisytiharkan dalam Warta Kerajaan Negeri Kedah Darul Aman menurut senarai Borang D berkelat;")

#set($perakuan2 = "bersetuju dikeluarkan Sijil Kesegeraan kepada Pentadbir Tanah Daerah ..................... di bawah Seksyen 19, Akta Pengambilan Tanah 1960 jika dipinta;")

#set($perakuan3 = "bersetuju sokongan hakmilik tapak ini akan disediakan selepas proses pengambilan selesai kelak; dan")

#set($perakuan4 = "bersetuju pembayaran untuk mengisytiharkan Borang D dalam Warta Kerajaan adalah di bawah tanggungan pihak pemohon.")



<br />
<font color="blue">* Terdapat Perubahan pada skrin & cetakan Format kertas MMK Kedah didalam sistem MyEtapp. Untuk cetakan kertas MMK melalui MyEtapp, sila kemaskini maklumat diskrin ini.  </font>




<fieldset>
<legend><strong>Penyediaan Kertas MMK/MB/KM/LC</strong></legend>
    	<table width="100%" border="0">
        
        		 <!-- RINGKASAN -->	
        		<tr>
				  <td><b>RINGKASAN</b></td></tr>
				
				<tr>
					<td style=" border-bottom:solid;">
	
					#if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
    					#if($list.FLAG_JENIS_MMK == "RINGKASAN" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    					<input type="hidden" name="RINGKASAN_MAIN_temp1"  id="RINGKASAN_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end 
    	
    	
    				<span id="RINGKASAN_MAIN"></span>           
   					<div id="RINGKASAN_MAIN_temp"></div>
					
					</td >
				</tr>
        
    	
    			<!-- TUJUAN -->	
				<tr>
				  <td><b>1. TUJUAN </b></td></tr>
				
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
        	
        		<!-- LATAR BELAKANG PERMOHONAN -->	
				<tr>
				  <td><b>2. LATAR BELAKANG </b></td></tr>
				<tr>
					<td>
                    
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 	<b>2.1 KEDUDUKAN TANAH</b>
					
					#if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
    					#if($list.FLAG_JENIS_MMK == "LATARBELAKANG1" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    					<input type="hidden" name="LATARBELAKANG1_MAIN_temp1"  id="LATARBELAKANG1_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end 
    	
    				<span id="LATARBELAKANG1_MAIN"></span>           
   					<div id="LATARBELAKANG1_MAIN_temp"></div>
					
					</td>
				</tr>
                
                <tr>
					<td>
                    
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 	<b>2.2 KELUASAN DAN KESESUAIAN TANAH</b>
					
					#if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
    					#if($list.FLAG_JENIS_MMK == "LATARBELAKANG2" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    					<input type="hidden" name="LATARBELAKANG2_MAIN_temp1"  id="LATARBELAKANG2_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end 
    	
    				<span id="LATARBELAKANG2_MAIN"></span>           
   					<div id="LATARBELAKANG2_MAIN_temp"></div>
					
					</td>
				</tr>
                
                
                <tr>
					<td>
                    
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 	<b>2.3 PERUNTUKAN PEMBIAYAAN PENGAMBILAN TANAH</b>
					
					#if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
    					#if($list.FLAG_JENIS_MMK == "LATARBELAKANG3" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
    					<input type="hidden" name="LATARBELAKANG3_MAIN_temp1"  id="LATARBELAKANG3_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end 
    	
    				<span id="LATARBELAKANG3_MAIN"></span>           
   					<div id="LATARBELAKANG3_MAIN_temp"></div>
					
					</td>
				</tr>
			
        		<tr><td>&nbsp;</td></tr>
        		
        		<!-- ULASAN PENGARAH TANAH -->	
				<tr>
				  <td><b>3. PENDAPAT DAN SOKONGAN PENGARAH TANAH DAN GALIAN </b></td></tr>
				<tr>
					<td>
					 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 	<b>3.1 SOKONGAN PENGARAH TANAH DAN GALIAN</b>
					#if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
	    				#if($list.FLAG_JENIS_MMK == "ULASANPENGARAH1" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
	    				<input type="hidden" name="ULASANPENGARAH1_MAIN_temp1"  id="ULASANPENGARAH1_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end 
    	
    	
    				<span id="ULASANPENGARAH1_MAIN"></span>           
   					<div id="ULASANPENGARAH1_MAIN_temp"></div>	
					
					</td>
				</tr>
                
                <tr><td>
                
                
                <table width="100%">
                
                <tr >                
                <td width="10%">
                </td>
                <td width="3%">
                </td>
                <td width="2%">
                </td>
                <td width="2%">
                </td>
                <td width="83%">
                </td>
                </tr>
               
                <tr> 
                <td >
                </td>
                <td >
                3.
                </td>
                <td colspan="3">
                Pengambilan Tanah.
                </td>                
                </tr>
                
                <tr> 
                <td >
                </td>
                <td >
                </td>
                <td >
                (1)
                </td>
                <td colspan="2">
                Pihak Berkuasa Negeri boleh mengambil mana-mana tanah yang diperlukan -
                </td>                
                </tr>
                
                
                <tr> 
                <td >
                </td>
                <td >
                </td>
                <td >                
                </td>
                <td > 
                <i>(a)</i>              
                </td>
                <td >
                bagi apa-apa maksud awam;.
                </td>                
                </tr>
                
                
                </table>
                
                
                
                </td></tr>
                
                
                <tr>
					<td>
					 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 	<b>3.2 PENGISYTIHARAN PENGAMBILAN DI BAWAH SEKSYEN 8 APT 1960</b>
					#if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
	    				#if($list.FLAG_JENIS_MMK == "ULASANPENGARAH2" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
	    				<input type="hidden" name="ULASANPENGARAH2_MAIN_temp1"  id="ULASANPENGARAH2_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end 
    	
    	
    				<span id="ULASANPENGARAH2_MAIN"></span>           
   					<div id="ULASANPENGARAH2_MAIN_temp"></div>	
					
					</td>
				</tr>
                
                
                <tr>
					<td>
					 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 	<b>3.3 PERMOHONAN PENGAMBILAN UNTUK PERTIMBANGAN MAJLIS</b>
                   	</td>
				</tr>
                
                
                <tr>
					<td>
					<table width="100%">
                    <tr>
                    <td width="5%">
                    </td>
                    <td width="95%">
                    Bersama-sama dengan kertas kerja ini juga disertakan Borang C (Jadual Tanah Yang Terkena Oleh Pengambilan), Seksyen 7, Akta Pengambilan Tanah 1960 yang memberikan butir-butir atas tanah tersebut agar Majlis Mesyuarat Kerajaan Negeri Kedah Darul Aman dapat memberikan pertimbangan dan membenarkan:
					</td>
                    </tr>
                    </table>					
					</td>
				</tr>
                
                <tr>
					<td>
				
                  
                  	#if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
	    				#if($list.FLAG_JENIS_MMK == "ULASANPENGARAH3" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
	    				<input type="hidden" name="ULASANPENGARAH3_MAIN_temp1"  id="ULASANPENGARAH3_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end 
    	
    	
    				<span id="ULASANPENGARAH3_MAIN"></span>           
   					<div id="ULASANPENGARAH3_MAIN_temp"></div>	
                  
										
					</td>
				</tr>
                
                
                <tr>
					<td>
				
                  
                  	#if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
	    				#if($list.FLAG_JENIS_MMK == "ULASANPENGARAH4" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
	    				<input type="hidden" name="ULASANPENGARAH4_MAIN_temp1"  id="ULASANPENGARAH4_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end 
    	
    	
    				<span id="ULASANPENGARAH4_MAIN"></span>           
   					<div id="ULASANPENGARAH4_MAIN_temp"></div>	
                  
										
					</td>
				</tr>
                
                
                
                <tr>
					<td>
				
                  
                  	#if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
	    				#if($list.FLAG_JENIS_MMK == "ULASANPENGARAH5" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
	    				<input type="hidden" name="ULASANPENGARAH_MAIN5_temp1"  id="ULASANPENGARAH5_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end 
    	
    	
    				<span id="ULASANPENGARAH5_MAIN"></span>           
   					<div id="ULASANPENGARAH5_MAIN_temp"></div>	
                  
										
					</td>
				</tr>
                
                
                <tr>
					<td>
				
                  
                  	#if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
	    				#if($list.FLAG_JENIS_MMK == "ULASANPENGARAH6" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
	    				<input type="hidden" name="ULASANPENGARAH_MAIN6_temp1"  id="ULASANPENGARAH6_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end 
    	
    	
    				<span id="ULASANPENGARAH6_MAIN"></span>           
   					<div id="ULASANPENGARAH6_MAIN_temp"></div>	
                  
										
					</td>
				</tr>
                
               
                
				
				<tr><td>&nbsp;</td></tr>
                <tr><td><b>4. PERAKUAN</b></td></tr>
                <tr>
					<td>
					<table width="100%">
                    <tr>
                    <td width="2%">
                    </td>
                    <td width="98%">
                    Inilah dipohon pertimbangan dan perakuan Majlis Mesyuarat Kerajaan bagi perkara-perkara berikut:
					</td>
                    </tr>
                    </table>					
					</td>
				</tr>
                
                
                 <tr>
					<td>
				
                  #if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
	    				#if($list.FLAG_JENIS_MMK == "PERAKUANPT1" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
	    				<input type="hidden" name="PERAKUANPT1_MAIN_temp1"  id="PERAKUANPT1_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end 
    	
    	
    				<span id="PERAKUANPT1_MAIN"></span>           
   					<div id="PERAKUANPT1_MAIN_temp"></div>	
                  
										
					</td>
				</tr>
                
                <tr>
					<td>
				
                  #if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
	    				#if($list.FLAG_JENIS_MMK == "PERAKUANPT2" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
	    				<input type="hidden" name="PERAKUANPT2_MAIN_temp1"  id="PERAKUANPT2_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end 
    	
    	
    				<span id="PERAKUANPT2_MAIN"></span>           
   					<div id="PERAKUANPT2_MAIN_temp"></div>	
                  
										
					</td>
				</tr>
                
                
                <tr>
					<td>
				
                  #if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
	    				#if($list.FLAG_JENIS_MMK == "PERAKUANPT3" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
	    				<input type="hidden" name="PERAKUANPT3_MAIN_temp1"  id="PERAKUANPT3_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end 
    	
    	
    				<span id="PERAKUANPT3_MAIN"></span>           
   					<div id="PERAKUANPT3_MAIN_temp"></div>	
                  
										
					</td>
				</tr>
                
                
                <tr>
					<td>
				
                  #if($senarai_submmk.size()!=0)
    					#foreach($list in $senarai_submmk)   
	    				#if($list.FLAG_JENIS_MMK == "PERAKUANPT4" && $list.FLAG_JENIS_SUBMMK == "MAIN" )         
	    				<input type="hidden" name="PERAKUANPT4_MAIN_temp1"  id="PERAKUANPT4_MAIN_temp1" value="$list.KETERANGAN_SUBMMK" >  
    					#end
    					#end
    				#end 
    	
    	
    				<span id="PERAKUANPT4_MAIN"></span>           
   					<div id="PERAKUANPT4_MAIN_temp"></div>	
                  
										
					</td>
				</tr>
                
                
                
                
                <tr><td>&nbsp;</td></tr>
        	
        		<!-- PERAKUAN PENTADBIR TANAH  -->	
				<tr><td><b>5. PENUTUP</b></td></tr>
                <tr>
					<td>
					<table width="100%">
                    <tr>
                    <td width="2%">
                    </td>
                    <td width="98%">
                   Inilah dipohon pertimbangan dan kelulusan Majlis.
					</td>
                    </tr>
                    </table>					
					</td>
				</tr>
				
				<tr>
					<td>
					
					
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
			
			if(jenis == "umum" )
			{
			temp_value = '$txtTujuan';
			}			

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanTUJUAN_MAIN\" id=\"txtUlasanTUJUAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check','txtUlasanTUJUAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check','txtUlasanTUJUAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanTUJUAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanTUJUAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanTUJUAN_MAIN\" id=\"txtUlasanTUJUAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check','txtUlasanTUJUAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check','txtUlasanTUJUAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanTUJUAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanTUJUAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
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
				  		"		<td width='3%' valign='top'> 1."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanTUJUAN_MAIN\" id=\"txtUlasanTUJUAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check"+i+"','txtUlasanTUJUAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check"+i+"','txtUlasanTUJUAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanTUJUAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanTUJUAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanTUJUAN_MAIN\" id=\"txtUlasanTUJUAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check"+i+"','txtUlasanTUJUAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanTUJUAN_MAIN_check"+i+"','txtUlasanTUJUAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanTUJUAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanTUJUAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
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
	
}
//TUJUAN


//RINGKASAN
function textarea_RINGKASAN_MAIN(tambahtolak,jenis,index_tolak){
	
	var RINGKASAN_MAIN_temp1_length=0;

	if(document.${formName}.RINGKASAN_MAIN_temp1 != null){

		if(document.${formName}.RINGKASAN_MAIN_temp1.length>0){
			RINGKASAN_MAIN_temp1_length = document.${formName}.RINGKASAN_MAIN_temp1.length;
		}else{
			RINGKASAN_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanRINGKASAN_MAIN!=null){

		if(document.${formName}.txtUlasanRINGKASAN_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanRINGKASAN_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='RINGKASAN_MAIN_tempX1' name='RINGKASAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanRINGKASAN_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='RINGKASAN_MAIN_tempX1' name='RINGKASAN_MAIN_tempX1' value= '"+document.${formName}.txtUlasanRINGKASAN_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#RINGKASAN_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(RINGKASAN_MAIN_temp1_length>0){
			ttRINGKASAN_MAIN = RINGKASAN_MAIN_temp1_length;
		}else{
			ttRINGKASAN_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttRINGKASAN_MAIN = ttRINGKASAN_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttRINGKASAN_MAIN = ttRINGKASAN_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttRINGKASAN_MAIN; i++){	

  		if(ttRINGKASAN_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.RINGKASAN_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.RINGKASAN_MAIN_tempX1[0].value
				} 	
			}
			
			if(jenis == "umum" )
			{
			temp_value = '$txtRingkasan';
			}			

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanRINGKASAN_MAIN\" id=\"txtUlasanRINGKASAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanRINGKASAN_MAIN_check','txtUlasanRINGKASAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanRINGKASAN_MAIN_check','txtUlasanRINGKASAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanRINGKASAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanRINGKASAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanRINGKASAN_MAIN\" id=\"txtUlasanRINGKASAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanRINGKASAN_MAIN_check','txtUlasanRINGKASAN_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanRINGKASAN_MAIN_check','txtUlasanRINGKASAN_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanRINGKASAN_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanRINGKASAN_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_RINGKASAN_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttRINGKASAN_MAIN>1) {      
	     	codes1 += "				<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_RINGKASAN_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttRINGKASAN_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.RINGKASAN_MAIN_tempX1.value

			}else if(ttRINGKASAN_MAIN>2 && i!=(ttRINGKASAN_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.RINGKASAN_MAIN_tempX1[i].value

			}else if(ttRINGKASAN_MAIN>1 && i!=(ttRINGKASAN_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.RINGKASAN_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.RINGKASAN_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.RINGKASAN_MAIN_tempX1[i+1].value	
				}	

			}else if(ttRINGKASAN_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.RINGKASAN_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.RINGKASAN_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 1."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanRINGKASAN_MAIN\" id=\"txtUlasanRINGKASAN_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanRINGKASAN_MAIN_check"+i+"','txtUlasanRINGKASAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanRINGKASAN_MAIN_check"+i+"','txtUlasanRINGKASAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanRINGKASAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanRINGKASAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanRINGKASAN_MAIN\" id=\"txtUlasanRINGKASAN_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanRINGKASAN_MAIN_check"+i+"','txtUlasanRINGKASAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanRINGKASAN_MAIN_check"+i+"','txtUlasanRINGKASAN_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanRINGKASAN_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanRINGKASAN_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttRINGKASAN_MAIN>1 && ttRINGKASAN_MAIN==(i+1)){  	
			codes1 += 	" 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_RINGKASAN_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttRINGKASAN_MAIN>1){      
			codes1 += 	"			<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_RINGKASAN_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#RINGKASAN_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(RINGKASAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanRINGKASAN_MAIN.length > 1 ){

		for (t = 0; t < RINGKASAN_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanRINGKASAN_MAIN[t].value = document.${formName}.RINGKASAN_MAIN_temp1[t].value;
		}

		}else if(RINGKASAN_MAIN_temp1_length > 1 && document.${formName}.txtUlasanRINGKASAN_MAIN.length < 1 ){
		
			for (t = 0; t < RINGKASAN_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanRINGKASAN_MAIN.value = document.${formName}.RINGKASAN_MAIN_temp1[0].value;
			}
		}
		
		else if(RINGKASAN_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanRINGKASAN_MAIN.value = document.${formName}.RINGKASAN_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(RINGKASAN_MAIN_temp1_length > 1){

			for (t = 0; t < RINGKASAN_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.RINGKASAN_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.RINGKASAN_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(RINGKASAN_MAIN_temp1_length == 1){
			
	 		document.${formName}.RINGKASAN_MAIN_temp1.value = "";			
	 		var element = document.${formName}.RINGKASAN_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttRINGKASAN_MAIN; i++){		

		    if(ttRINGKASAN_MAIN==1){
		 check_length(document.${formName}.txtUlasanRINGKASAN_MAIN,'30000','txtUlasanRINGKASAN_MAIN_check','txtUlasanRINGKASAN_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanRINGKASAN_MAIN[i],'30000','txtUlasanRINGKASAN_MAIN_check'+i,'txtUlasanRINGKASAN_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}
//RINGKASAN


//LATAR BELAKANG
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
   	   				  "			<td width='3%' valign='top'> </td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanLATARBELAKANG_MAIN\" id=\"txtUlasanLATARBELAKANG_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check','txtUlasanLATARBELAKANG_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check','txtUlasanLATARBELAKANG_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanLATARBELAKANG_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanLATARBELAKANG_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanLATARBELAKANG_MAIN\" id=\"txtUlasanLATARBELAKANG_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check','txtUlasanLATARBELAKANG_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check','txtUlasanLATARBELAKANG_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanLATARBELAKANG_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanLATARBELAKANG_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
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
				  		"		<td width='3%' valign='top'> 2."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanLATARBELAKANG_MAIN\" id=\"txtUlasanLATARBELAKANG_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check"+i+"','txtUlasanLATARBELAKANG_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check"+i+"','txtUlasanLATARBELAKANG_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanLATARBELAKANG_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanLATARBELAKANG_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanLATARBELAKANG_MAIN\" id=\"txtUlasanLATARBELAKANG_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check"+i+"','txtUlasanLATARBELAKANG_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG_MAIN_check"+i+"','txtUlasanLATARBELAKANG_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanLATARBELAKANG_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanLATARBELAKANG_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
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
	
}//CLOSE LATAR BELAKANG


//LATAR BELAKANG 1
function textarea_LATARBELAKANG1_MAIN(tambahtolak,jenis,index_tolak){
	
	var LATARBELAKANG1_MAIN_temp1_length=0;

	if(document.${formName}.LATARBELAKANG1_MAIN_temp1 != null){

		if(document.${formName}.LATARBELAKANG1_MAIN_temp1.length>0){
			LATARBELAKANG1_MAIN_temp1_length = document.${formName}.LATARBELAKANG1_MAIN_temp1.length;
		}else{
			LATARBELAKANG1_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanLATARBELAKANG1_MAIN!=null){

		if(document.${formName}.txtUlasanLATARBELAKANG1_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanLATARBELAKANG1_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='LATARBELAKANG1_MAIN_tempX1' name='LATARBELAKANG1_MAIN_tempX1' value= '"+document.${formName}.txtUlasanLATARBELAKANG1_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='LATARBELAKANG1_MAIN_tempX1' name='LATARBELAKANG1_MAIN_tempX1' value= '"+document.${formName}.txtUlasanLATARBELAKANG1_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#LATARBELAKANG1_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(LATARBELAKANG1_MAIN_temp1_length>0){
			ttLATARBELAKANG1_MAIN = LATARBELAKANG1_MAIN_temp1_length;
		}else{
			ttLATARBELAKANG1_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttLATARBELAKANG1_MAIN = ttLATARBELAKANG1_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttLATARBELAKANG1_MAIN = ttLATARBELAKANG1_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttLATARBELAKANG1_MAIN; i++){	

  		if(ttLATARBELAKANG1_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.LATARBELAKANG1_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.LATARBELAKANG1_MAIN_tempX1[0].value
				} 	
			}		



	if(jenis == "umum" )
    {
	temp_value = '$txtLatarBelakang1';
	}	


   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='5%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'> </td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanLATARBELAKANG1_MAIN\" id=\"txtUlasanLATARBELAKANG1_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanLATARBELAKANG1_MAIN_check','txtUlasanLATARBELAKANG1_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG1_MAIN_check','txtUlasanLATARBELAKANG1_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanLATARBELAKANG1_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanLATARBELAKANG1_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanLATARBELAKANG1_MAIN\" id=\"txtUlasanLATARBELAKANG1_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanLATARBELAKANG1_MAIN_check','txtUlasanLATARBELAKANG1_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG1_MAIN_check','txtUlasanLATARBELAKANG1_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanLATARBELAKANG1_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanLATARBELAKANG1_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_LATARBELAKANG1_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttLATARBELAKANG1_MAIN>1) {      
	     	codes1 += "				<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_LATARBELAKANG1_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttLATARBELAKANG1_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.LATARBELAKANG1_MAIN_tempX1.value

			}else if(ttLATARBELAKANG1_MAIN>2 && i!=(ttLATARBELAKANG1_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.LATARBELAKANG1_MAIN_tempX1[i].value

			}else if(ttLATARBELAKANG1_MAIN>1 && i!=(ttLATARBELAKANG1_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.LATARBELAKANG1_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.LATARBELAKANG1_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.LATARBELAKANG1_MAIN_tempX1[i+1].value	
				}	

			}else if(ttLATARBELAKANG1_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.LATARBELAKANG1_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.LATARBELAKANG1_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='5%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 2."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='92%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanLATARBELAKANG1_MAIN\" id=\"txtUlasanLATARBELAKANG1_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanLATARBELAKANG1_MAIN_check"+i+"','txtUlasanLATARBELAKANG1_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG1_MAIN_check"+i+"','txtUlasanLATARBELAKANG1_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanLATARBELAKANG1_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanLATARBELAKANG1_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='92%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanLATARBELAKANG1_MAIN\" id=\"txtUlasanLATARBELAKANG1_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanLATARBELAKANG1_MAIN_check"+i+"','txtUlasanLATARBELAKANG1_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG1_MAIN_check"+i+"','txtUlasanLATARBELAKANG1_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanLATARBELAKANG1_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanLATARBELAKANG1_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttLATARBELAKANG1_MAIN>1 && ttLATARBELAKANG1_MAIN==(i+1)){  	
			codes1 += 	" 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_LATARBELAKANG1_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttLATARBELAKANG1_MAIN>1){      
			codes1 += 	"			<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_LATARBELAKANG1_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#LATARBELAKANG1_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(LATARBELAKANG1_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLATARBELAKANG1_MAIN.length > 1 ){

		for (t = 0; t < LATARBELAKANG1_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanLATARBELAKANG1_MAIN[t].value = document.${formName}.LATARBELAKANG1_MAIN_temp1[t].value;
		}

		}else if(LATARBELAKANG1_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLATARBELAKANG1_MAIN.length < 1 ){
		
			for (t = 0; t < LATARBELAKANG1_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanLATARBELAKANG1_MAIN.value = document.${formName}.LATARBELAKANG1_MAIN_temp1[0].value;
			}
		}
		
		else if(LATARBELAKANG1_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanLATARBELAKANG1_MAIN.value = document.${formName}.LATARBELAKANG1_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(LATARBELAKANG1_MAIN_temp1_length > 1){

			for (t = 0; t < LATARBELAKANG1_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.LATARBELAKANG1_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.LATARBELAKANG1_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(LATARBELAKANG1_MAIN_temp1_length == 1){
			
	 		document.${formName}.LATARBELAKANG1_MAIN_temp1.value = "";			
	 		var element = document.${formName}.LATARBELAKANG1_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttLATARBELAKANG1_MAIN; i++){		

		    if(ttLATARBELAKANG1_MAIN==1){
		 check_length(document.${formName}.txtUlasanLATARBELAKANG1_MAIN,'30000','txtUlasanLATARBELAKANG1_MAIN_check','txtUlasanLATARBELAKANG1_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanLATARBELAKANG1_MAIN[i],'30000','txtUlasanLATARBELAKANG1_MAIN_check'+i,'txtUlasanLATARBELAKANG1_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//CLOSE LATAR BELAKANG1


//LATAR BELAKANG 2
function textarea_LATARBELAKANG2_MAIN(tambahtolak,jenis,index_tolak){
	
	var LATARBELAKANG2_MAIN_temp1_length=0;

	if(document.${formName}.LATARBELAKANG2_MAIN_temp1 != null){

		if(document.${formName}.LATARBELAKANG2_MAIN_temp1.length>0){
			LATARBELAKANG2_MAIN_temp1_length = document.${formName}.LATARBELAKANG2_MAIN_temp1.length;
		}else{
			LATARBELAKANG2_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanLATARBELAKANG2_MAIN!=null){

		if(document.${formName}.txtUlasanLATARBELAKANG2_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanLATARBELAKANG2_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='LATARBELAKANG2_MAIN_tempX1' name='LATARBELAKANG2_MAIN_tempX1' value= '"+document.${formName}.txtUlasanLATARBELAKANG2_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='LATARBELAKANG2_MAIN_tempX1' name='LATARBELAKANG2_MAIN_tempX1' value= '"+document.${formName}.txtUlasanLATARBELAKANG2_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#LATARBELAKANG2_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(LATARBELAKANG2_MAIN_temp1_length>0){
			ttLATARBELAKANG2_MAIN = LATARBELAKANG2_MAIN_temp1_length;
		}else{
			ttLATARBELAKANG2_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttLATARBELAKANG2_MAIN = ttLATARBELAKANG2_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttLATARBELAKANG2_MAIN = ttLATARBELAKANG2_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttLATARBELAKANG2_MAIN; i++){	

  		if(ttLATARBELAKANG2_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.LATARBELAKANG2_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.LATARBELAKANG2_MAIN_tempX1[0].value
				} 	
			}	
			
			if(jenis == "umum" )
			{
			temp_value = '$txtLatarBelakang2';
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='5%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'> </td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanLATARBELAKANG2_MAIN\" id=\"txtUlasanLATARBELAKANG2_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanLATARBELAKANG2_MAIN_check','txtUlasanLATARBELAKANG2_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG2_MAIN_check','txtUlasanLATARBELAKANG2_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanLATARBELAKANG2_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanLATARBELAKANG2_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanLATARBELAKANG2_MAIN\" id=\"txtUlasanLATARBELAKANG2_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanLATARBELAKANG2_MAIN_check','txtUlasanLATARBELAKANG2_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG2_MAIN_check','txtUlasanLATARBELAKANG2_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanLATARBELAKANG2_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanLATARBELAKANG2_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_LATARBELAKANG2_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttLATARBELAKANG2_MAIN>1) {      
	     	codes1 += "				<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_LATARBELAKANG2_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttLATARBELAKANG2_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.LATARBELAKANG2_MAIN_tempX1.value

			}else if(ttLATARBELAKANG2_MAIN>2 && i!=(ttLATARBELAKANG2_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.LATARBELAKANG2_MAIN_tempX1[i].value

			}else if(ttLATARBELAKANG2_MAIN>1 && i!=(ttLATARBELAKANG2_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.LATARBELAKANG2_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.LATARBELAKANG2_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.LATARBELAKANG2_MAIN_tempX1[i+1].value	
				}	

			}else if(ttLATARBELAKANG2_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.LATARBELAKANG2_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.LATARBELAKANG2_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='5%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 2."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='92%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanLATARBELAKANG2_MAIN\" id=\"txtUlasanLATARBELAKANG2_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanLATARBELAKANG2_MAIN_check"+i+"','txtUlasanLATARBELAKANG2_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG2_MAIN_check"+i+"','txtUlasanLATARBELAKANG2_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanLATARBELAKANG2_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanLATARBELAKANG2_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='92%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanLATARBELAKANG2_MAIN\" id=\"txtUlasanLATARBELAKANG2_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanLATARBELAKANG2_MAIN_check"+i+"','txtUlasanLATARBELAKANG2_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG2_MAIN_check"+i+"','txtUlasanLATARBELAKANG2_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanLATARBELAKANG2_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanLATARBELAKANG2_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttLATARBELAKANG2_MAIN>1 && ttLATARBELAKANG2_MAIN==(i+1)){  	
			codes1 += 	" 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_LATARBELAKANG2_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttLATARBELAKANG2_MAIN>1){      
			codes1 += 	"			<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_LATARBELAKANG2_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#LATARBELAKANG2_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(LATARBELAKANG2_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLATARBELAKANG2_MAIN.length > 1 ){

		for (t = 0; t < LATARBELAKANG2_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanLATARBELAKANG2_MAIN[t].value = document.${formName}.LATARBELAKANG2_MAIN_temp1[t].value;
		}

		}else if(LATARBELAKANG2_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLATARBELAKANG2_MAIN.length < 1 ){
		
			for (t = 0; t < LATARBELAKANG2_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanLATARBELAKANG2_MAIN.value = document.${formName}.LATARBELAKANG2_MAIN_temp1[0].value;
			}
		}
		
		else if(LATARBELAKANG2_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanLATARBELAKANG2_MAIN.value = document.${formName}.LATARBELAKANG2_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(LATARBELAKANG2_MAIN_temp1_length > 1){

			for (t = 0; t < LATARBELAKANG2_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.LATARBELAKANG2_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.LATARBELAKANG2_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(LATARBELAKANG2_MAIN_temp1_length == 1){
			
	 		document.${formName}.LATARBELAKANG2_MAIN_temp1.value = "";			
	 		var element = document.${formName}.LATARBELAKANG2_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttLATARBELAKANG2_MAIN; i++){		

		    if(ttLATARBELAKANG2_MAIN==1){
		 check_length(document.${formName}.txtUlasanLATARBELAKANG2_MAIN,'30000','txtUlasanLATARBELAKANG2_MAIN_check','txtUlasanLATARBELAKANG2_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanLATARBELAKANG2_MAIN[i],'30000','txtUlasanLATARBELAKANG2_MAIN_check'+i,'txtUlasanLATARBELAKANG2_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//CLOSE LATAR BELAKANG 2


//LATAR BELAKANG 3
function textarea_LATARBELAKANG3_MAIN(tambahtolak,jenis,index_tolak){
	
	var LATARBELAKANG3_MAIN_temp1_length=0;

	if(document.${formName}.LATARBELAKANG3_MAIN_temp1 != null){

		if(document.${formName}.LATARBELAKANG3_MAIN_temp1.length>0){
			LATARBELAKANG3_MAIN_temp1_length = document.${formName}.LATARBELAKANG3_MAIN_temp1.length;
		}else{
			LATARBELAKANG3_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanLATARBELAKANG3_MAIN!=null){

		if(document.${formName}.txtUlasanLATARBELAKANG3_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanLATARBELAKANG3_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='LATARBELAKANG3_MAIN_tempX1' name='LATARBELAKANG3_MAIN_tempX1' value= '"+document.${formName}.txtUlasanLATARBELAKANG3_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='LATARBELAKANG3_MAIN_tempX1' name='LATARBELAKANG3_MAIN_tempX1' value= '"+document.${formName}.txtUlasanLATARBELAKANG3_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#LATARBELAKANG3_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(LATARBELAKANG3_MAIN_temp1_length>0){
			ttLATARBELAKANG3_MAIN = LATARBELAKANG3_MAIN_temp1_length;
		}else{
			ttLATARBELAKANG3_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttLATARBELAKANG3_MAIN = ttLATARBELAKANG3_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttLATARBELAKANG3_MAIN = ttLATARBELAKANG3_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttLATARBELAKANG3_MAIN; i++){	

  		if(ttLATARBELAKANG3_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.LATARBELAKANG3_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.LATARBELAKANG3_MAIN_tempX1[0].value
				} 	
			}
			
			if(jenis == "umum" )
			{
			temp_value = '$txtLatarBelakang3';
			}			

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='5%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'> </td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanLATARBELAKANG3_MAIN\" id=\"txtUlasanLATARBELAKANG3_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanLATARBELAKANG3_MAIN_check','txtUlasanLATARBELAKANG3_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG3_MAIN_check','txtUlasanLATARBELAKANG3_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanLATARBELAKANG3_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanLATARBELAKANG3_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanLATARBELAKANG3_MAIN\" id=\"txtUlasanLATARBELAKANG3_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanLATARBELAKANG3_MAIN_check','txtUlasanLATARBELAKANG3_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG3_MAIN_check','txtUlasanLATARBELAKANG3_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanLATARBELAKANG3_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanLATARBELAKANG3_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_LATARBELAKANG3_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttLATARBELAKANG3_MAIN>1) {      
	     	codes1 += "				<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_LATARBELAKANG3_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttLATARBELAKANG3_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.LATARBELAKANG3_MAIN_tempX1.value

			}else if(ttLATARBELAKANG3_MAIN>2 && i!=(ttLATARBELAKANG3_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.LATARBELAKANG3_MAIN_tempX1[i].value

			}else if(ttLATARBELAKANG3_MAIN>1 && i!=(ttLATARBELAKANG3_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.LATARBELAKANG3_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.LATARBELAKANG3_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.LATARBELAKANG3_MAIN_tempX1[i+1].value	
				}	

			}else if(ttLATARBELAKANG3_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.LATARBELAKANG3_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.LATARBELAKANG3_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='5%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 2."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='92%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanLATARBELAKANG3_MAIN\" id=\"txtUlasanLATARBELAKANG3_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanLATARBELAKANG3_MAIN_check"+i+"','txtUlasanLATARBELAKANG3_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG3_MAIN_check"+i+"','txtUlasanLATARBELAKANG3_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanLATARBELAKANG3_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanLATARBELAKANG3_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='92%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanLATARBELAKANG3_MAIN\" id=\"txtUlasanLATARBELAKANG3_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanLATARBELAKANG3_MAIN_check"+i+"','txtUlasanLATARBELAKANG3_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanLATARBELAKANG3_MAIN_check"+i+"','txtUlasanLATARBELAKANG3_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanLATARBELAKANG3_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanLATARBELAKANG3_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttLATARBELAKANG3_MAIN>1 && ttLATARBELAKANG3_MAIN==(i+1)){  	
			codes1 += 	" 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_LATARBELAKANG3_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttLATARBELAKANG3_MAIN>1){      
			codes1 += 	"			<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_LATARBELAKANG3_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#LATARBELAKANG3_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(LATARBELAKANG3_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLATARBELAKANG3_MAIN.length > 1 ){

		for (t = 0; t < LATARBELAKANG3_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanLATARBELAKANG3_MAIN[t].value = document.${formName}.LATARBELAKANG3_MAIN_temp1[t].value;
		}

		}else if(LATARBELAKANG3_MAIN_temp1_length > 1 && document.${formName}.txtUlasanLATARBELAKANG3_MAIN.length < 1 ){
		
			for (t = 0; t < LATARBELAKANG3_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanLATARBELAKANG3_MAIN.value = document.${formName}.LATARBELAKANG3_MAIN_temp1[0].value;
			}
		}
		
		else if(LATARBELAKANG3_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanLATARBELAKANG3_MAIN.value = document.${formName}.LATARBELAKANG3_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(LATARBELAKANG3_MAIN_temp1_length > 1){

			for (t = 0; t < LATARBELAKANG3_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.LATARBELAKANG3_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.LATARBELAKANG3_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(LATARBELAKANG3_MAIN_temp1_length == 1){
			
	 		document.${formName}.LATARBELAKANG3_MAIN_temp1.value = "";			
	 		var element = document.${formName}.LATARBELAKANG3_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttLATARBELAKANG3_MAIN; i++){		

		    if(ttLATARBELAKANG3_MAIN==1){
		 check_length(document.${formName}.txtUlasanLATARBELAKANG3_MAIN,'30000','txtUlasanLATARBELAKANG3_MAIN_check','txtUlasanLATARBELAKANG3_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanLATARBELAKANG3_MAIN[i],'30000','txtUlasanLATARBELAKANG3_MAIN_check'+i,'txtUlasanLATARBELAKANG3_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//CLOSE LATAR BELAKANG 3


//ULASANPENGARAH
function textarea_ULASANPENGARAH_MAIN(tambahtolak,jenis,index_tolak){
	
	var ULASANPENGARAH_MAIN_temp1_length=0;

	if(document.${formName}.ULASANPENGARAH_MAIN_temp1 != null){

		if(document.${formName}.ULASANPENGARAH_MAIN_temp1.length>0){
			ULASANPENGARAH_MAIN_temp1_length = document.${formName}.ULASANPENGARAH_MAIN_temp1.length;
		}else{
			ULASANPENGARAH_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanULASANPENGARAH_MAIN!=null){

		if(document.${formName}.txtUlasanULASANPENGARAH_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanULASANPENGARAH_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='ULASANPENGARAH_MAIN_tempX1' name='ULASANPENGARAH_MAIN_tempX1' value= '"+document.${formName}.txtUlasanULASANPENGARAH_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='ULASANPENGARAH_MAIN_tempX1' name='ULASANPENGARAH_MAIN_tempX1' value= '"+document.${formName}.txtUlasanULASANPENGARAH_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#ULASANPENGARAH_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(ULASANPENGARAH_MAIN_temp1_length>0){
			ttULASANPENGARAH_MAIN = ULASANPENGARAH_MAIN_temp1_length;
		}else{
			ttULASANPENGARAH_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttULASANPENGARAH_MAIN = ttULASANPENGARAH_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttULASANPENGARAH_MAIN = ttULASANPENGARAH_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttULASANPENGARAH_MAIN; i++){	

  		if(ttULASANPENGARAH_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.ULASANPENGARAH_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.ULASANPENGARAH_MAIN_tempX1[0].value
				} 	
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='2%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanULASANPENGARAH_MAIN\" id=\"txtUlasanULASANPENGARAH_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH_MAIN_check','txtUlasanULASANPENGARAH_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH_MAIN_check','txtUlasanULASANPENGARAH_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanULASANPENGARAH_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanULASANPENGARAH_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanULASANPENGARAH_MAIN\" id=\"txtUlasanULASANPENGARAH_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH_MAIN_check','txtUlasanULASANPENGARAH_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH_MAIN_check','txtUlasanULASANPENGARAH_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanULASANPENGARAH_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanULASANPENGARAH_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_ULASANPENGARAH_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttULASANPENGARAH_MAIN>1) {      
	     	codes1 += "				<input type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_ULASANPENGARAH_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttULASANPENGARAH_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.ULASANPENGARAH_MAIN_tempX1.value

			}else if(ttULASANPENGARAH_MAIN>2 && i!=(ttULASANPENGARAH_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.ULASANPENGARAH_MAIN_tempX1[i].value

			}else if(ttULASANPENGARAH_MAIN>1 && i!=(ttULASANPENGARAH_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.ULASANPENGARAH_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.ULASANPENGARAH_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.ULASANPENGARAH_MAIN_tempX1[i+1].value	
				}	

			}else if(ttULASANPENGARAH_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.ULASANPENGARAH_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.ULASANPENGARAH_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 3."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanULASANPENGARAH_MAIN\" id=\"txtUlasanULASANPENGARAH_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH_MAIN_check"+i+"','txtUlasanULASANPENGARAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH_MAIN_check"+i+"','txtUlasanULASANPENGARAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanULASANPENGARAH_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanULASANPENGARAH_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanULASANPENGARAH_MAIN\" id=\"txtUlasanULASANPENGARAH_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH_MAIN_check"+i+"','txtUlasanULASANPENGARAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH_MAIN_check"+i+"','txtUlasanULASANPENGARAH_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanULASANPENGARAH_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanULASANPENGARAH_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttULASANPENGARAH_MAIN>1 && ttULASANPENGARAH_MAIN==(i+1)){  	
			codes1 += 	" 			<input type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_ULASANPENGARAH_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttULASANPENGARAH_MAIN>1){      
			codes1 += 	"			<input type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_ULASANPENGARAH_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#ULASANPENGARAH_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(ULASANPENGARAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPENGARAH_MAIN.length > 1 ){

		for (t = 0; t < ULASANPENGARAH_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanULASANPENGARAH_MAIN[t].value = document.${formName}.ULASANPENGARAH_MAIN_temp1[t].value;
		}

		}else if(ULASANPENGARAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPENGARAH_MAIN.length < 1 ){
		
			for (t = 0; t < ULASANPENGARAH_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanULASANPENGARAH_MAIN.value = document.${formName}.ULASANPENGARAH_MAIN_temp1[0].value;
			}
		}
		
		else if(ULASANPENGARAH_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanULASANPENGARAH_MAIN.value = document.${formName}.ULASANPENGARAH_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(ULASANPENGARAH_MAIN_temp1_length > 1){

			for (t = 0; t < ULASANPENGARAH_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.ULASANPENGARAH_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.ULASANPENGARAH_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(ULASANPENGARAH_MAIN_temp1_length == 1){
			
	 		document.${formName}.ULASANPENGARAH_MAIN_temp1.value = "";			
	 		var element = document.${formName}.ULASANPENGARAH_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttULASANPENGARAH_MAIN; i++){		

		    if(ttULASANPENGARAH_MAIN==1){
		 check_length(document.${formName}.txtUlasanULASANPENGARAH_MAIN,'30000','txtUlasanULASANPENGARAH_MAIN_check','txtUlasanULASANPENGARAH_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanULASANPENGARAH_MAIN[i],'30000','txtUlasanULASANPENGARAH_MAIN_check'+i,'txtUlasanULASANPENGARAH_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//ULASANPENGARAH





//ULASANPENGARAH1
function textarea_ULASANPENGARAH1_MAIN(tambahtolak,jenis,index_tolak){
	
	var ULASANPENGARAH1_MAIN_temp1_length=0;

	if(document.${formName}.ULASANPENGARAH1_MAIN_temp1 != null){

		if(document.${formName}.ULASANPENGARAH1_MAIN_temp1.length>0){
			ULASANPENGARAH1_MAIN_temp1_length = document.${formName}.ULASANPENGARAH1_MAIN_temp1.length;
		}else{
			ULASANPENGARAH1_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanULASANPENGARAH1_MAIN!=null){

		if(document.${formName}.txtUlasanULASANPENGARAH1_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanULASANPENGARAH1_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='ULASANPENGARAH1_MAIN_tempX1' name='ULASANPENGARAH1_MAIN_tempX1' value= '"+document.${formName}.txtUlasanULASANPENGARAH1_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='ULASANPENGARAH1_MAIN_tempX1' name='ULASANPENGARAH1_MAIN_tempX1' value= '"+document.${formName}.txtUlasanULASANPENGARAH1_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#ULASANPENGARAH1_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(ULASANPENGARAH1_MAIN_temp1_length>0){
			ttULASANPENGARAH1_MAIN = ULASANPENGARAH1_MAIN_temp1_length;
		}else{
			ttULASANPENGARAH1_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttULASANPENGARAH1_MAIN = ttULASANPENGARAH1_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttULASANPENGARAH1_MAIN = ttULASANPENGARAH1_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttULASANPENGARAH1_MAIN; i++){	

  		if(ttULASANPENGARAH1_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.ULASANPENGARAH1_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.ULASANPENGARAH1_MAIN_tempX1[0].value
				} 	
			}	
			
			
			if(jenis == "umum" )
			{
			temp_value = '$pendapat1';
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='5%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanULASANPENGARAH1_MAIN\" id=\"txtUlasanULASANPENGARAH1_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH1_MAIN_check','txtUlasanULASANPENGARAH1_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH1_MAIN_check','txtUlasanULASANPENGARAH1_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanULASANPENGARAH1_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanULASANPENGARAH1_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanULASANPENGARAH1_MAIN\" id=\"txtUlasanULASANPENGARAH1_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH1_MAIN_check','txtUlasanULASANPENGARAH1_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH1_MAIN_check','txtUlasanULASANPENGARAH1_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanULASANPENGARAH1_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanULASANPENGARAH1_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_ULASANPENGARAH1_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttULASANPENGARAH1_MAIN>1) {      
	     	codes1 += "				<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_ULASANPENGARAH1_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttULASANPENGARAH1_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.ULASANPENGARAH1_MAIN_tempX1.value

			}else if(ttULASANPENGARAH1_MAIN>2 && i!=(ttULASANPENGARAH1_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.ULASANPENGARAH1_MAIN_tempX1[i].value

			}else if(ttULASANPENGARAH1_MAIN>1 && i!=(ttULASANPENGARAH1_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.ULASANPENGARAH1_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.ULASANPENGARAH1_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.ULASANPENGARAH1_MAIN_tempX1[i+1].value	
				}	

			}else if(ttULASANPENGARAH1_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.ULASANPENGARAH1_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.ULASANPENGARAH1_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='5%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 3."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='92%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanULASANPENGARAH1_MAIN\" id=\"txtUlasanULASANPENGARAH1_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH1_MAIN_check"+i+"','txtUlasanULASANPENGARAH1_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH1_MAIN_check"+i+"','txtUlasanULASANPENGARAH1_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanULASANPENGARAH1_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanULASANPENGARAH1_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='92%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanULASANPENGARAH1_MAIN\" id=\"txtUlasanULASANPENGARAH1_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH1_MAIN_check"+i+"','txtUlasanULASANPENGARAH1_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH1_MAIN_check"+i+"','txtUlasanULASANPENGARAH1_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanULASANPENGARAH1_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanULASANPENGARAH1_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttULASANPENGARAH1_MAIN>1 && ttULASANPENGARAH1_MAIN==(i+1)){  	
			codes1 += 	" 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_ULASANPENGARAH1_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttULASANPENGARAH1_MAIN>1){      
			codes1 += 	"			<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_ULASANPENGARAH1_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#ULASANPENGARAH1_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(ULASANPENGARAH1_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPENGARAH1_MAIN.length > 1 ){

		for (t = 0; t < ULASANPENGARAH1_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanULASANPENGARAH1_MAIN[t].value = document.${formName}.ULASANPENGARAH1_MAIN_temp1[t].value;
		}

		}else if(ULASANPENGARAH1_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPENGARAH1_MAIN.length < 1 ){
		
			for (t = 0; t < ULASANPENGARAH1_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanULASANPENGARAH1_MAIN.value = document.${formName}.ULASANPENGARAH1_MAIN_temp1[0].value;
			}
		}
		
		else if(ULASANPENGARAH1_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanULASANPENGARAH1_MAIN.value = document.${formName}.ULASANPENGARAH1_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(ULASANPENGARAH1_MAIN_temp1_length > 1){

			for (t = 0; t < ULASANPENGARAH1_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.ULASANPENGARAH1_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.ULASANPENGARAH1_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(ULASANPENGARAH1_MAIN_temp1_length == 1){
			
	 		document.${formName}.ULASANPENGARAH1_MAIN_temp1.value = "";			
	 		var element = document.${formName}.ULASANPENGARAH1_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttULASANPENGARAH1_MAIN; i++){		

		    if(ttULASANPENGARAH1_MAIN==1){
		 check_length(document.${formName}.txtUlasanULASANPENGARAH1_MAIN,'30000','txtUlasanULASANPENGARAH1_MAIN_check','txtUlasanULASANPENGARAH1_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanULASANPENGARAH1_MAIN[i],'30000','txtUlasanULASANPENGARAH1_MAIN_check'+i,'txtUlasanULASANPENGARAH1_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//ULASANPENGARAH1


//ULASANPENGARAH2
function textarea_ULASANPENGARAH2_MAIN(tambahtolak,jenis,index_tolak){
	
	var ULASANPENGARAH2_MAIN_temp1_length=0;

	if(document.${formName}.ULASANPENGARAH2_MAIN_temp1 != null){

		if(document.${formName}.ULASANPENGARAH2_MAIN_temp1.length>0){
			ULASANPENGARAH2_MAIN_temp1_length = document.${formName}.ULASANPENGARAH2_MAIN_temp1.length;
		}else{
			ULASANPENGARAH2_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanULASANPENGARAH2_MAIN!=null){

		if(document.${formName}.txtUlasanULASANPENGARAH2_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanULASANPENGARAH2_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='ULASANPENGARAH2_MAIN_tempX1' name='ULASANPENGARAH2_MAIN_tempX1' value= '"+document.${formName}.txtUlasanULASANPENGARAH2_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='ULASANPENGARAH2_MAIN_tempX1' name='ULASANPENGARAH2_MAIN_tempX1' value= '"+document.${formName}.txtUlasanULASANPENGARAH2_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#ULASANPENGARAH2_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(ULASANPENGARAH2_MAIN_temp1_length>0){
			ttULASANPENGARAH2_MAIN = ULASANPENGARAH2_MAIN_temp1_length;
		}else{
			ttULASANPENGARAH2_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttULASANPENGARAH2_MAIN = ttULASANPENGARAH2_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttULASANPENGARAH2_MAIN = ttULASANPENGARAH2_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttULASANPENGARAH2_MAIN; i++){	

  		if(ttULASANPENGARAH2_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.ULASANPENGARAH2_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.ULASANPENGARAH2_MAIN_tempX1[0].value
				} 	
			}	
			
			if(jenis == "umum" )
			{
			temp_value = '$pendapat2';
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='5%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'></td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanULASANPENGARAH2_MAIN\" id=\"txtUlasanULASANPENGARAH2_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH2_MAIN_check','txtUlasanULASANPENGARAH2_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH2_MAIN_check','txtUlasanULASANPENGARAH2_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanULASANPENGARAH2_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanULASANPENGARAH2_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanULASANPENGARAH2_MAIN\" id=\"txtUlasanULASANPENGARAH2_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH2_MAIN_check','txtUlasanULASANPENGARAH2_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH2_MAIN_check','txtUlasanULASANPENGARAH2_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanULASANPENGARAH2_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanULASANPENGARAH2_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_ULASANPENGARAH2_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttULASANPENGARAH2_MAIN>1) {      
	     	codes1 += "				<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_ULASANPENGARAH2_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttULASANPENGARAH2_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.ULASANPENGARAH2_MAIN_tempX1.value

			}else if(ttULASANPENGARAH2_MAIN>2 && i!=(ttULASANPENGARAH2_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.ULASANPENGARAH2_MAIN_tempX1[i].value

			}else if(ttULASANPENGARAH2_MAIN>1 && i!=(ttULASANPENGARAH2_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.ULASANPENGARAH2_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.ULASANPENGARAH2_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.ULASANPENGARAH2_MAIN_tempX1[i+1].value	
				}	

			}else if(ttULASANPENGARAH2_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.ULASANPENGARAH2_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.ULASANPENGARAH2_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='5%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 3."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='92%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanULASANPENGARAH2_MAIN\" id=\"txtUlasanULASANPENGARAH2_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH2_MAIN_check"+i+"','txtUlasanULASANPENGARAH2_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH2_MAIN_check"+i+"','txtUlasanULASANPENGARAH2_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanULASANPENGARAH2_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanULASANPENGARAH2_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='92%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanULASANPENGARAH2_MAIN\" id=\"txtUlasanULASANPENGARAH2_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH2_MAIN_check"+i+"','txtUlasanULASANPENGARAH2_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH2_MAIN_check"+i+"','txtUlasanULASANPENGARAH2_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanULASANPENGARAH2_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanULASANPENGARAH2_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttULASANPENGARAH2_MAIN>1 && ttULASANPENGARAH2_MAIN==(i+1)){  	
			codes1 += 	" 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_ULASANPENGARAH2_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttULASANPENGARAH2_MAIN>1){      
			codes1 += 	"			<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_ULASANPENGARAH2_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#ULASANPENGARAH2_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(ULASANPENGARAH2_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPENGARAH2_MAIN.length > 1 ){

		for (t = 0; t < ULASANPENGARAH2_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanULASANPENGARAH2_MAIN[t].value = document.${formName}.ULASANPENGARAH2_MAIN_temp1[t].value;
		}

		}else if(ULASANPENGARAH2_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPENGARAH2_MAIN.length < 1 ){
		
			for (t = 0; t < ULASANPENGARAH2_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanULASANPENGARAH2_MAIN.value = document.${formName}.ULASANPENGARAH2_MAIN_temp1[0].value;
			}
		}
		
		else if(ULASANPENGARAH2_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanULASANPENGARAH2_MAIN.value = document.${formName}.ULASANPENGARAH2_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(ULASANPENGARAH2_MAIN_temp1_length > 1){

			for (t = 0; t < ULASANPENGARAH2_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.ULASANPENGARAH2_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.ULASANPENGARAH2_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(ULASANPENGARAH2_MAIN_temp1_length == 1){
			
	 		document.${formName}.ULASANPENGARAH2_MAIN_temp1.value = "";			
	 		var element = document.${formName}.ULASANPENGARAH2_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttULASANPENGARAH2_MAIN; i++){		

		    if(ttULASANPENGARAH2_MAIN==1){
		 check_length(document.${formName}.txtUlasanULASANPENGARAH2_MAIN,'30000','txtUlasanULASANPENGARAH2_MAIN_check','txtUlasanULASANPENGARAH2_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanULASANPENGARAH2_MAIN[i],'30000','txtUlasanULASANPENGARAH2_MAIN_check'+i,'txtUlasanULASANPENGARAH2_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//ULASANPENGARAH2



//ULASANPENGARAH3
function textarea_ULASANPENGARAH3_MAIN(tambahtolak,jenis,index_tolak){
	
	var ULASANPENGARAH3_MAIN_temp1_length=0;

	if(document.${formName}.ULASANPENGARAH3_MAIN_temp1 != null){

		if(document.${formName}.ULASANPENGARAH3_MAIN_temp1.length>0){
			ULASANPENGARAH3_MAIN_temp1_length = document.${formName}.ULASANPENGARAH3_MAIN_temp1.length;
		}else{
			ULASANPENGARAH3_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanULASANPENGARAH3_MAIN!=null){

		if(document.${formName}.txtUlasanULASANPENGARAH3_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanULASANPENGARAH3_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='ULASANPENGARAH3_MAIN_tempX1' name='ULASANPENGARAH3_MAIN_tempX1' value= '"+document.${formName}.txtUlasanULASANPENGARAH3_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='ULASANPENGARAH3_MAIN_tempX1' name='ULASANPENGARAH3_MAIN_tempX1' value= '"+document.${formName}.txtUlasanULASANPENGARAH3_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#ULASANPENGARAH3_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(ULASANPENGARAH3_MAIN_temp1_length>0){
			ttULASANPENGARAH3_MAIN = ULASANPENGARAH3_MAIN_temp1_length;
		}else{
			ttULASANPENGARAH3_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttULASANPENGARAH3_MAIN = ttULASANPENGARAH3_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttULASANPENGARAH3_MAIN = ttULASANPENGARAH3_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttULASANPENGARAH3_MAIN; i++){	

  		if(ttULASANPENGARAH3_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.ULASANPENGARAH3_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.ULASANPENGARAH3_MAIN_tempX1[0].value
				} 	
			}	
			
			
			if(jenis == "umum" )
			{
			temp_value = '$pendapat3';
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='5%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'>a.</td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanULASANPENGARAH3_MAIN\" id=\"txtUlasanULASANPENGARAH3_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH3_MAIN_check','txtUlasanULASANPENGARAH3_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH3_MAIN_check','txtUlasanULASANPENGARAH3_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanULASANPENGARAH3_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanULASANPENGARAH3_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanULASANPENGARAH3_MAIN\" id=\"txtUlasanULASANPENGARAH3_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH3_MAIN_check','txtUlasanULASANPENGARAH3_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH3_MAIN_check','txtUlasanULASANPENGARAH3_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanULASANPENGARAH3_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanULASANPENGARAH3_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_ULASANPENGARAH3_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttULASANPENGARAH3_MAIN>1) {      
	     	codes1 += "				<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_ULASANPENGARAH3_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttULASANPENGARAH3_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.ULASANPENGARAH3_MAIN_tempX1.value

			}else if(ttULASANPENGARAH3_MAIN>2 && i!=(ttULASANPENGARAH3_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.ULASANPENGARAH3_MAIN_tempX1[i].value

			}else if(ttULASANPENGARAH3_MAIN>1 && i!=(ttULASANPENGARAH3_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.ULASANPENGARAH3_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.ULASANPENGARAH3_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.ULASANPENGARAH3_MAIN_tempX1[i+1].value	
				}	

			}else if(ttULASANPENGARAH3_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.ULASANPENGARAH3_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.ULASANPENGARAH3_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='5%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 3."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='92%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanULASANPENGARAH3_MAIN\" id=\"txtUlasanULASANPENGARAH3_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH3_MAIN_check"+i+"','txtUlasanULASANPENGARAH3_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH3_MAIN_check"+i+"','txtUlasanULASANPENGARAH3_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanULASANPENGARAH3_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanULASANPENGARAH3_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='92%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanULASANPENGARAH3_MAIN\" id=\"txtUlasanULASANPENGARAH3_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH3_MAIN_check"+i+"','txtUlasanULASANPENGARAH3_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH3_MAIN_check"+i+"','txtUlasanULASANPENGARAH3_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanULASANPENGARAH3_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanULASANPENGARAH3_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttULASANPENGARAH3_MAIN>1 && ttULASANPENGARAH3_MAIN==(i+1)){  	
			codes1 += 	" 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_ULASANPENGARAH3_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttULASANPENGARAH3_MAIN>1){      
			codes1 += 	"			<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_ULASANPENGARAH3_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#ULASANPENGARAH3_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(ULASANPENGARAH3_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPENGARAH3_MAIN.length > 1 ){

		for (t = 0; t < ULASANPENGARAH3_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanULASANPENGARAH3_MAIN[t].value = document.${formName}.ULASANPENGARAH3_MAIN_temp1[t].value;
		}

		}else if(ULASANPENGARAH3_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPENGARAH3_MAIN.length < 1 ){
		
			for (t = 0; t < ULASANPENGARAH3_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanULASANPENGARAH3_MAIN.value = document.${formName}.ULASANPENGARAH3_MAIN_temp1[0].value;
			}
		}
		
		else if(ULASANPENGARAH3_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanULASANPENGARAH3_MAIN.value = document.${formName}.ULASANPENGARAH3_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(ULASANPENGARAH3_MAIN_temp1_length > 1){

			for (t = 0; t < ULASANPENGARAH3_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.ULASANPENGARAH3_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.ULASANPENGARAH3_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(ULASANPENGARAH3_MAIN_temp1_length == 1){
			
	 		document.${formName}.ULASANPENGARAH3_MAIN_temp1.value = "";			
	 		var element = document.${formName}.ULASANPENGARAH3_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttULASANPENGARAH3_MAIN; i++){		

		    if(ttULASANPENGARAH3_MAIN==1){
		 check_length(document.${formName}.txtUlasanULASANPENGARAH3_MAIN,'30000','txtUlasanULASANPENGARAH3_MAIN_check','txtUlasanULASANPENGARAH3_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanULASANPENGARAH3_MAIN[i],'30000','txtUlasanULASANPENGARAH3_MAIN_check'+i,'txtUlasanULASANPENGARAH3_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//ULASANPENGARAH3



//ULASANPENGARAH4
function textarea_ULASANPENGARAH4_MAIN(tambahtolak,jenis,index_tolak){
	
	var ULASANPENGARAH4_MAIN_temp1_length=0;

	if(document.${formName}.ULASANPENGARAH4_MAIN_temp1 != null){

		if(document.${formName}.ULASANPENGARAH4_MAIN_temp1.length>0){
			ULASANPENGARAH4_MAIN_temp1_length = document.${formName}.ULASANPENGARAH4_MAIN_temp1.length;
		}else{
			ULASANPENGARAH4_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanULASANPENGARAH4_MAIN!=null){

		if(document.${formName}.txtUlasanULASANPENGARAH4_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanULASANPENGARAH4_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='ULASANPENGARAH4_MAIN_tempX1' name='ULASANPENGARAH4_MAIN_tempX1' value= '"+document.${formName}.txtUlasanULASANPENGARAH4_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='ULASANPENGARAH4_MAIN_tempX1' name='ULASANPENGARAH4_MAIN_tempX1' value= '"+document.${formName}.txtUlasanULASANPENGARAH4_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#ULASANPENGARAH4_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(ULASANPENGARAH4_MAIN_temp1_length>0){
			ttULASANPENGARAH4_MAIN = ULASANPENGARAH4_MAIN_temp1_length;
		}else{
			ttULASANPENGARAH4_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttULASANPENGARAH4_MAIN = ttULASANPENGARAH4_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttULASANPENGARAH4_MAIN = ttULASANPENGARAH4_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttULASANPENGARAH4_MAIN; i++){	

  		if(ttULASANPENGARAH4_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.ULASANPENGARAH4_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.ULASANPENGARAH4_MAIN_tempX1[0].value
				} 	
			}
			
			if(jenis == "umum")
			{
			temp_value = '$pendapat4';
			}			

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='5%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'>b.</td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanULASANPENGARAH4_MAIN\" id=\"txtUlasanULASANPENGARAH4_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH4_MAIN_check','txtUlasanULASANPENGARAH4_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH4_MAIN_check','txtUlasanULASANPENGARAH4_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanULASANPENGARAH4_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanULASANPENGARAH4_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanULASANPENGARAH4_MAIN\" id=\"txtUlasanULASANPENGARAH4_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH4_MAIN_check','txtUlasanULASANPENGARAH4_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH4_MAIN_check','txtUlasanULASANPENGARAH4_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanULASANPENGARAH4_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanULASANPENGARAH4_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_ULASANPENGARAH4_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttULASANPENGARAH4_MAIN>1) {      
	     	codes1 += "				<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_ULASANPENGARAH4_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttULASANPENGARAH4_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.ULASANPENGARAH4_MAIN_tempX1.value

			}else if(ttULASANPENGARAH4_MAIN>2 && i!=(ttULASANPENGARAH4_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.ULASANPENGARAH4_MAIN_tempX1[i].value

			}else if(ttULASANPENGARAH4_MAIN>1 && i!=(ttULASANPENGARAH4_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.ULASANPENGARAH4_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.ULASANPENGARAH4_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.ULASANPENGARAH4_MAIN_tempX1[i+1].value	
				}	

			}else if(ttULASANPENGARAH4_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.ULASANPENGARAH4_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.ULASANPENGARAH4_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='5%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 3."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='92%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanULASANPENGARAH4_MAIN\" id=\"txtUlasanULASANPENGARAH4_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH4_MAIN_check"+i+"','txtUlasanULASANPENGARAH4_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH4_MAIN_check"+i+"','txtUlasanULASANPENGARAH4_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanULASANPENGARAH4_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanULASANPENGARAH4_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='92%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanULASANPENGARAH4_MAIN\" id=\"txtUlasanULASANPENGARAH4_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH4_MAIN_check"+i+"','txtUlasanULASANPENGARAH4_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH4_MAIN_check"+i+"','txtUlasanULASANPENGARAH4_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanULASANPENGARAH4_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanULASANPENGARAH4_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttULASANPENGARAH4_MAIN>1 && ttULASANPENGARAH4_MAIN==(i+1)){  	
			codes1 += 	" 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_ULASANPENGARAH4_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttULASANPENGARAH4_MAIN>1){      
			codes1 += 	"			<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_ULASANPENGARAH4_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#ULASANPENGARAH4_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(ULASANPENGARAH4_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPENGARAH4_MAIN.length > 1 ){

		for (t = 0; t < ULASANPENGARAH4_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanULASANPENGARAH4_MAIN[t].value = document.${formName}.ULASANPENGARAH4_MAIN_temp1[t].value;
		}

		}else if(ULASANPENGARAH4_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPENGARAH4_MAIN.length < 1 ){
		
			for (t = 0; t < ULASANPENGARAH4_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanULASANPENGARAH4_MAIN.value = document.${formName}.ULASANPENGARAH4_MAIN_temp1[0].value;
			}
		}
		
		else if(ULASANPENGARAH4_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanULASANPENGARAH4_MAIN.value = document.${formName}.ULASANPENGARAH4_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(ULASANPENGARAH4_MAIN_temp1_length > 1){

			for (t = 0; t < ULASANPENGARAH4_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.ULASANPENGARAH4_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.ULASANPENGARAH4_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(ULASANPENGARAH4_MAIN_temp1_length == 1){
			
	 		document.${formName}.ULASANPENGARAH4_MAIN_temp1.value = "";			
	 		var element = document.${formName}.ULASANPENGARAH4_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttULASANPENGARAH4_MAIN; i++){		

		    if(ttULASANPENGARAH4_MAIN==1){
		 check_length(document.${formName}.txtUlasanULASANPENGARAH4_MAIN,'30000','txtUlasanULASANPENGARAH4_MAIN_check','txtUlasanULASANPENGARAH4_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanULASANPENGARAH4_MAIN[i],'30000','txtUlasanULASANPENGARAH4_MAIN_check'+i,'txtUlasanULASANPENGARAH4_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//ULASANPENGARAH4



//ULASANPENGARAH5
function textarea_ULASANPENGARAH5_MAIN(tambahtolak,jenis,index_tolak){
	
	var ULASANPENGARAH5_MAIN_temp1_length=0;

	if(document.${formName}.ULASANPENGARAH5_MAIN_temp1 != null){

		if(document.${formName}.ULASANPENGARAH5_MAIN_temp1.length>0){
			ULASANPENGARAH5_MAIN_temp1_length = document.${formName}.ULASANPENGARAH5_MAIN_temp1.length;
		}else{
			ULASANPENGARAH5_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanULASANPENGARAH5_MAIN!=null){

		if(document.${formName}.txtUlasanULASANPENGARAH5_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanULASANPENGARAH5_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='ULASANPENGARAH5_MAIN_tempX1' name='ULASANPENGARAH5_MAIN_tempX1' value= '"+document.${formName}.txtUlasanULASANPENGARAH5_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='ULASANPENGARAH5_MAIN_tempX1' name='ULASANPENGARAH5_MAIN_tempX1' value= '"+document.${formName}.txtUlasanULASANPENGARAH5_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#ULASANPENGARAH5_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(ULASANPENGARAH5_MAIN_temp1_length>0){
			ttULASANPENGARAH5_MAIN = ULASANPENGARAH5_MAIN_temp1_length;
		}else{
			ttULASANPENGARAH5_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttULASANPENGARAH5_MAIN = ttULASANPENGARAH5_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttULASANPENGARAH5_MAIN = ttULASANPENGARAH5_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttULASANPENGARAH5_MAIN; i++){	

  		if(ttULASANPENGARAH5_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.ULASANPENGARAH5_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.ULASANPENGARAH5_MAIN_tempX1[0].value
				} 	
			}
			
			if(jenis == "umum" )
			{
			temp_value = '$pendapat5';
			}			

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='5%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'>c.</td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanULASANPENGARAH5_MAIN\" id=\"txtUlasanULASANPENGARAH5_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH5_MAIN_check','txtUlasanULASANPENGARAH5_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH5_MAIN_check','txtUlasanULASANPENGARAH5_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanULASANPENGARAH5_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanULASANPENGARAH5_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanULASANPENGARAH5_MAIN\" id=\"txtUlasanULASANPENGARAH5_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH5_MAIN_check','txtUlasanULASANPENGARAH5_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH5_MAIN_check','txtUlasanULASANPENGARAH5_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanULASANPENGARAH5_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanULASANPENGARAH5_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_ULASANPENGARAH5_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttULASANPENGARAH5_MAIN>1) {      
	     	codes1 += "				<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_ULASANPENGARAH5_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttULASANPENGARAH5_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.ULASANPENGARAH5_MAIN_tempX1.value

			}else if(ttULASANPENGARAH5_MAIN>2 && i!=(ttULASANPENGARAH5_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.ULASANPENGARAH5_MAIN_tempX1[i].value

			}else if(ttULASANPENGARAH5_MAIN>1 && i!=(ttULASANPENGARAH5_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.ULASANPENGARAH5_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.ULASANPENGARAH5_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.ULASANPENGARAH5_MAIN_tempX1[i+1].value	
				}	

			}else if(ttULASANPENGARAH5_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.ULASANPENGARAH5_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.ULASANPENGARAH5_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='5%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 3."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='92%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanULASANPENGARAH5_MAIN\" id=\"txtUlasanULASANPENGARAH5_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH5_MAIN_check"+i+"','txtUlasanULASANPENGARAH5_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH5_MAIN_check"+i+"','txtUlasanULASANPENGARAH5_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanULASANPENGARAH5_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanULASANPENGARAH5_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='92%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanULASANPENGARAH5_MAIN\" id=\"txtUlasanULASANPENGARAH5_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH5_MAIN_check"+i+"','txtUlasanULASANPENGARAH5_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH5_MAIN_check"+i+"','txtUlasanULASANPENGARAH5_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanULASANPENGARAH5_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanULASANPENGARAH5_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttULASANPENGARAH5_MAIN>1 && ttULASANPENGARAH5_MAIN==(i+1)){  	
			codes1 += 	" 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_ULASANPENGARAH5_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttULASANPENGARAH5_MAIN>1){      
			codes1 += 	"			<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_ULASANPENGARAH5_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#ULASANPENGARAH5_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(ULASANPENGARAH5_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPENGARAH5_MAIN.length > 1 ){

		for (t = 0; t < ULASANPENGARAH5_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanULASANPENGARAH5_MAIN[t].value = document.${formName}.ULASANPENGARAH5_MAIN_temp1[t].value;
		}

		}else if(ULASANPENGARAH5_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPENGARAH5_MAIN.length < 1 ){
		
			for (t = 0; t < ULASANPENGARAH5_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanULASANPENGARAH5_MAIN.value = document.${formName}.ULASANPENGARAH5_MAIN_temp1[0].value;
			}
		}
		
		else if(ULASANPENGARAH5_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanULASANPENGARAH5_MAIN.value = document.${formName}.ULASANPENGARAH5_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(ULASANPENGARAH5_MAIN_temp1_length > 1){

			for (t = 0; t < ULASANPENGARAH5_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.ULASANPENGARAH5_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.ULASANPENGARAH5_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(ULASANPENGARAH5_MAIN_temp1_length == 1){
			
	 		document.${formName}.ULASANPENGARAH5_MAIN_temp1.value = "";			
	 		var element = document.${formName}.ULASANPENGARAH5_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttULASANPENGARAH5_MAIN; i++){		

		    if(ttULASANPENGARAH5_MAIN==1){
		 check_length(document.${formName}.txtUlasanULASANPENGARAH5_MAIN,'30000','txtUlasanULASANPENGARAH5_MAIN_check','txtUlasanULASANPENGARAH5_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanULASANPENGARAH5_MAIN[i],'30000','txtUlasanULASANPENGARAH5_MAIN_check'+i,'txtUlasanULASANPENGARAH5_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//ULASANPENGARAH5



//ULASANPENGARAH6
function textarea_ULASANPENGARAH6_MAIN(tambahtolak,jenis,index_tolak){
	
	var ULASANPENGARAH6_MAIN_temp1_length=0;

	if(document.${formName}.ULASANPENGARAH6_MAIN_temp1 != null){

		if(document.${formName}.ULASANPENGARAH6_MAIN_temp1.length>0){
			ULASANPENGARAH6_MAIN_temp1_length = document.${formName}.ULASANPENGARAH6_MAIN_temp1.length;
		}else{
			ULASANPENGARAH6_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanULASANPENGARAH6_MAIN!=null){

		if(document.${formName}.txtUlasanULASANPENGARAH6_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanULASANPENGARAH6_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='ULASANPENGARAH6_MAIN_tempX1' name='ULASANPENGARAH6_MAIN_tempX1' value= '"+document.${formName}.txtUlasanULASANPENGARAH6_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='ULASANPENGARAH6_MAIN_tempX1' name='ULASANPENGARAH6_MAIN_tempX1' value= '"+document.${formName}.txtUlasanULASANPENGARAH6_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#ULASANPENGARAH6_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(ULASANPENGARAH6_MAIN_temp1_length>0){
			ttULASANPENGARAH6_MAIN = ULASANPENGARAH6_MAIN_temp1_length;
		}else{
			ttULASANPENGARAH6_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttULASANPENGARAH6_MAIN = ttULASANPENGARAH6_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttULASANPENGARAH6_MAIN = ttULASANPENGARAH6_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttULASANPENGARAH6_MAIN; i++){	

  		if(ttULASANPENGARAH6_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.ULASANPENGARAH6_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.ULASANPENGARAH6_MAIN_tempX1[0].value
				} 	
			}	
			
			if(jenis == "umum" )
			{
			temp_value = '$pendapat6';
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='5%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'>d.</td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanULASANPENGARAH6_MAIN\" id=\"txtUlasanULASANPENGARAH6_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH6_MAIN_check','txtUlasanULASANPENGARAH6_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH6_MAIN_check','txtUlasanULASANPENGARAH6_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanULASANPENGARAH6_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanULASANPENGARAH6_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanULASANPENGARAH6_MAIN\" id=\"txtUlasanULASANPENGARAH6_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH6_MAIN_check','txtUlasanULASANPENGARAH6_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH6_MAIN_check','txtUlasanULASANPENGARAH6_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanULASANPENGARAH6_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanULASANPENGARAH6_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_ULASANPENGARAH6_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttULASANPENGARAH6_MAIN>1) {      
	     	codes1 += "				<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_ULASANPENGARAH6_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttULASANPENGARAH6_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.ULASANPENGARAH6_MAIN_tempX1.value

			}else if(ttULASANPENGARAH6_MAIN>2 && i!=(ttULASANPENGARAH6_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.ULASANPENGARAH6_MAIN_tempX1[i].value

			}else if(ttULASANPENGARAH6_MAIN>1 && i!=(ttULASANPENGARAH6_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.ULASANPENGARAH6_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.ULASANPENGARAH6_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.ULASANPENGARAH6_MAIN_tempX1[i+1].value	
				}	

			}else if(ttULASANPENGARAH6_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.ULASANPENGARAH6_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.ULASANPENGARAH6_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='5%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 3."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='92%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanULASANPENGARAH6_MAIN\" id=\"txtUlasanULASANPENGARAH6_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH6_MAIN_check"+i+"','txtUlasanULASANPENGARAH6_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH6_MAIN_check"+i+"','txtUlasanULASANPENGARAH6_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanULASANPENGARAH6_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanULASANPENGARAH6_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='92%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanULASANPENGARAH6_MAIN\" id=\"txtUlasanULASANPENGARAH6_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanULASANPENGARAH6_MAIN_check"+i+"','txtUlasanULASANPENGARAH6_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanULASANPENGARAH6_MAIN_check"+i+"','txtUlasanULASANPENGARAH6_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanULASANPENGARAH6_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanULASANPENGARAH6_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttULASANPENGARAH6_MAIN>1 && ttULASANPENGARAH6_MAIN==(i+1)){  	
			codes1 += 	" 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_ULASANPENGARAH6_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttULASANPENGARAH6_MAIN>1){      
			codes1 += 	"			<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_ULASANPENGARAH6_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#ULASANPENGARAH6_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(ULASANPENGARAH6_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPENGARAH6_MAIN.length > 1 ){

		for (t = 0; t < ULASANPENGARAH6_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanULASANPENGARAH6_MAIN[t].value = document.${formName}.ULASANPENGARAH6_MAIN_temp1[t].value;
		}

		}else if(ULASANPENGARAH6_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPENGARAH6_MAIN.length < 1 ){
		
			for (t = 0; t < ULASANPENGARAH6_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanULASANPENGARAH6_MAIN.value = document.${formName}.ULASANPENGARAH6_MAIN_temp1[0].value;
			}
		}
		
		else if(ULASANPENGARAH6_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanULASANPENGARAH6_MAIN.value = document.${formName}.ULASANPENGARAH6_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(ULASANPENGARAH6_MAIN_temp1_length > 1){

			for (t = 0; t < ULASANPENGARAH6_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.ULASANPENGARAH6_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.ULASANPENGARAH6_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(ULASANPENGARAH6_MAIN_temp1_length == 1){
			
	 		document.${formName}.ULASANPENGARAH6_MAIN_temp1.value = "";			
	 		var element = document.${formName}.ULASANPENGARAH6_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttULASANPENGARAH6_MAIN; i++){		

		    if(ttULASANPENGARAH6_MAIN==1){
		 check_length(document.${formName}.txtUlasanULASANPENGARAH6_MAIN,'30000','txtUlasanULASANPENGARAH6_MAIN_check','txtUlasanULASANPENGARAH6_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanULASANPENGARAH6_MAIN[i],'30000','txtUlasanULASANPENGARAH6_MAIN_check'+i,'txtUlasanULASANPENGARAH6_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//ULASANPENGARAH6




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
					  "			#if($mode=='new') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanPERAKUANPT_MAIN\" id=\"txtUlasanPERAKUANPT_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check','txtUlasanPERAKUANPT_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check','txtUlasanPERAKUANPT_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERAKUANPT_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanPERAKUANPT_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='95%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanPERAKUANPT_MAIN\" id=\"txtUlasanPERAKUANPT_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check','txtUlasanPERAKUANPT_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check','txtUlasanPERAKUANPT_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERAKUANPT_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanPERAKUANPT_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
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
				  		"		<td width='2%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 4."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='95%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanPERAKUANPT_MAIN\" id=\"txtUlasanPERAKUANPT_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check"+i+"','txtUlasanPERAKUANPT_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check"+i+"','txtUlasanPERAKUANPT_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERAKUANPT_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERAKUANPT_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='95%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanPERAKUANPT_MAIN\" id=\"txtUlasanPERAKUANPT_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check"+i+"','txtUlasanPERAKUANPT_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT_MAIN_check"+i+"','txtUlasanPERAKUANPT_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERAKUANPT_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERAKUANPT_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
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
	
}//PERAKUANPT


//PERAKUANPT1
function textarea_PERAKUANPT1_MAIN(tambahtolak,jenis,index_tolak){
	
	var PERAKUANPT1_MAIN_temp1_length=0;

	if(document.${formName}.PERAKUANPT1_MAIN_temp1 != null){

		if(document.${formName}.PERAKUANPT1_MAIN_temp1.length>0){
			PERAKUANPT1_MAIN_temp1_length = document.${formName}.PERAKUANPT1_MAIN_temp1.length;
		}else{
			PERAKUANPT1_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanPERAKUANPT1_MAIN!=null){

		if(document.${formName}.txtUlasanPERAKUANPT1_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanPERAKUANPT1_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='PERAKUANPT1_MAIN_tempX1' name='PERAKUANPT1_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPERAKUANPT1_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='PERAKUANPT1_MAIN_tempX1' name='PERAKUANPT1_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPERAKUANPT1_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#PERAKUANPT1_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(PERAKUANPT1_MAIN_temp1_length>0){
			ttPERAKUANPT1_MAIN = PERAKUANPT1_MAIN_temp1_length;
		}else{
			ttPERAKUANPT1_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttPERAKUANPT1_MAIN = ttPERAKUANPT1_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttPERAKUANPT1_MAIN = ttPERAKUANPT1_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttPERAKUANPT1_MAIN; i++){	

  		if(ttPERAKUANPT1_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.PERAKUANPT1_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.PERAKUANPT1_MAIN_tempX1[0].value
				} 	
			}	
			
			if(jenis == "umum" )
			{
			temp_value = '$perakuan1';
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='5%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'>a.</td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanPERAKUANPT1_MAIN\" id=\"txtUlasanPERAKUANPT1_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT1_MAIN_check','txtUlasanPERAKUANPT1_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT1_MAIN_check','txtUlasanPERAKUANPT1_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERAKUANPT1_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanPERAKUANPT1_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanPERAKUANPT1_MAIN\" id=\"txtUlasanPERAKUANPT1_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT1_MAIN_check','txtUlasanPERAKUANPT1_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT1_MAIN_check','txtUlasanPERAKUANPT1_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERAKUANPT1_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanPERAKUANPT1_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_PERAKUANPT1_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttPERAKUANPT1_MAIN>1) {      
	     	codes1 += "				<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_PERAKUANPT1_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttPERAKUANPT1_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.PERAKUANPT1_MAIN_tempX1.value

			}else if(ttPERAKUANPT1_MAIN>2 && i!=(ttPERAKUANPT1_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.PERAKUANPT1_MAIN_tempX1[i].value

			}else if(ttPERAKUANPT1_MAIN>1 && i!=(ttPERAKUANPT1_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.PERAKUANPT1_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.PERAKUANPT1_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.PERAKUANPT1_MAIN_tempX1[i+1].value	
				}	

			}else if(ttPERAKUANPT1_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.PERAKUANPT1_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.PERAKUANPT1_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='5%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 4."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='92%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanPERAKUANPT1_MAIN\" id=\"txtUlasanPERAKUANPT1_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT1_MAIN_check"+i+"','txtUlasanPERAKUANPT1_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT1_MAIN_check"+i+"','txtUlasanPERAKUANPT1_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERAKUANPT1_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERAKUANPT1_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='92%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanPERAKUANPT1_MAIN\" id=\"txtUlasanPERAKUANPT1_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT1_MAIN_check"+i+"','txtUlasanPERAKUANPT1_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT1_MAIN_check"+i+"','txtUlasanPERAKUANPT1_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERAKUANPT1_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERAKUANPT1_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttPERAKUANPT1_MAIN>1 && ttPERAKUANPT1_MAIN==(i+1)){  	
			codes1 += 	" 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_PERAKUANPT1_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttPERAKUANPT1_MAIN>1){      
			codes1 += 	"			<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_PERAKUANPT1_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#PERAKUANPT1_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(PERAKUANPT1_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERAKUANPT1_MAIN.length > 1 ){

		for (t = 0; t < PERAKUANPT1_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanPERAKUANPT1_MAIN[t].value = document.${formName}.PERAKUANPT1_MAIN_temp1[t].value;
		}

		}else if(PERAKUANPT1_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERAKUANPT1_MAIN.length < 1 ){
		
			for (t = 0; t < PERAKUANPT1_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanPERAKUANPT1_MAIN.value = document.${formName}.PERAKUANPT1_MAIN_temp1[0].value;
			}
		}
		
		else if(PERAKUANPT1_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanPERAKUANPT1_MAIN.value = document.${formName}.PERAKUANPT1_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(PERAKUANPT1_MAIN_temp1_length > 1){

			for (t = 0; t < PERAKUANPT1_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.PERAKUANPT1_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.PERAKUANPT1_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(PERAKUANPT1_MAIN_temp1_length == 1){
			
	 		document.${formName}.PERAKUANPT1_MAIN_temp1.value = "";			
	 		var element = document.${formName}.PERAKUANPT1_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttPERAKUANPT1_MAIN; i++){		

		    if(ttPERAKUANPT1_MAIN==1){
		 check_length(document.${formName}.txtUlasanPERAKUANPT1_MAIN,'30000','txtUlasanPERAKUANPT1_MAIN_check','txtUlasanPERAKUANPT1_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanPERAKUANPT1_MAIN[i],'30000','txtUlasanPERAKUANPT1_MAIN_check'+i,'txtUlasanPERAKUANPT1_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//PERAKUANPT1


//PERAKUANPT2
function textarea_PERAKUANPT2_MAIN(tambahtolak,jenis,index_tolak){
	
	var PERAKUANPT2_MAIN_temp1_length=0;

	if(document.${formName}.PERAKUANPT2_MAIN_temp1 != null){

		if(document.${formName}.PERAKUANPT2_MAIN_temp1.length>0){
			PERAKUANPT2_MAIN_temp1_length = document.${formName}.PERAKUANPT2_MAIN_temp1.length;
		}else{
			PERAKUANPT2_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanPERAKUANPT2_MAIN!=null){

		if(document.${formName}.txtUlasanPERAKUANPT2_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanPERAKUANPT2_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='PERAKUANPT2_MAIN_tempX1' name='PERAKUANPT2_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPERAKUANPT2_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='PERAKUANPT2_MAIN_tempX1' name='PERAKUANPT2_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPERAKUANPT2_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#PERAKUANPT2_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(PERAKUANPT2_MAIN_temp1_length>0){
			ttPERAKUANPT2_MAIN = PERAKUANPT2_MAIN_temp1_length;
		}else{
			ttPERAKUANPT2_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttPERAKUANPT2_MAIN = ttPERAKUANPT2_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttPERAKUANPT2_MAIN = ttPERAKUANPT2_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttPERAKUANPT2_MAIN; i++){	

  		if(ttPERAKUANPT2_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.PERAKUANPT2_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.PERAKUANPT2_MAIN_tempX1[0].value
				} 	
			}		

			if(jenis == "umum" )
			{
			temp_value = '$perakuan2';
			}	

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='5%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'>b.</td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanPERAKUANPT2_MAIN\" id=\"txtUlasanPERAKUANPT2_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT2_MAIN_check','txtUlasanPERAKUANPT2_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT2_MAIN_check','txtUlasanPERAKUANPT2_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERAKUANPT2_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanPERAKUANPT2_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanPERAKUANPT2_MAIN\" id=\"txtUlasanPERAKUANPT2_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT2_MAIN_check','txtUlasanPERAKUANPT2_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT2_MAIN_check','txtUlasanPERAKUANPT2_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERAKUANPT2_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanPERAKUANPT2_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_PERAKUANPT2_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttPERAKUANPT2_MAIN>1) {      
	     	codes1 += "				<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_PERAKUANPT2_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttPERAKUANPT2_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.PERAKUANPT2_MAIN_tempX1.value

			}else if(ttPERAKUANPT2_MAIN>2 && i!=(ttPERAKUANPT2_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.PERAKUANPT2_MAIN_tempX1[i].value

			}else if(ttPERAKUANPT2_MAIN>1 && i!=(ttPERAKUANPT2_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.PERAKUANPT2_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.PERAKUANPT2_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.PERAKUANPT2_MAIN_tempX1[i+1].value	
				}	

			}else if(ttPERAKUANPT2_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.PERAKUANPT2_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.PERAKUANPT2_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='5%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 4."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='92%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanPERAKUANPT2_MAIN\" id=\"txtUlasanPERAKUANPT2_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT2_MAIN_check"+i+"','txtUlasanPERAKUANPT2_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT2_MAIN_check"+i+"','txtUlasanPERAKUANPT2_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERAKUANPT2_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERAKUANPT2_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='92%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanPERAKUANPT2_MAIN\" id=\"txtUlasanPERAKUANPT2_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT2_MAIN_check"+i+"','txtUlasanPERAKUANPT2_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT2_MAIN_check"+i+"','txtUlasanPERAKUANPT2_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERAKUANPT2_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERAKUANPT2_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttPERAKUANPT2_MAIN>1 && ttPERAKUANPT2_MAIN==(i+1)){  	
			codes1 += 	" 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_PERAKUANPT2_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttPERAKUANPT2_MAIN>1){      
			codes1 += 	"			<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_PERAKUANPT2_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#PERAKUANPT2_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(PERAKUANPT2_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERAKUANPT2_MAIN.length > 1 ){

		for (t = 0; t < PERAKUANPT2_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanPERAKUANPT2_MAIN[t].value = document.${formName}.PERAKUANPT2_MAIN_temp1[t].value;
		}

		}else if(PERAKUANPT2_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERAKUANPT2_MAIN.length < 1 ){
		
			for (t = 0; t < PERAKUANPT2_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanPERAKUANPT2_MAIN.value = document.${formName}.PERAKUANPT2_MAIN_temp1[0].value;
			}
		}
		
		else if(PERAKUANPT2_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanPERAKUANPT2_MAIN.value = document.${formName}.PERAKUANPT2_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(PERAKUANPT2_MAIN_temp1_length > 1){

			for (t = 0; t < PERAKUANPT2_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.PERAKUANPT2_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.PERAKUANPT2_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(PERAKUANPT2_MAIN_temp1_length == 1){
			
	 		document.${formName}.PERAKUANPT2_MAIN_temp1.value = "";			
	 		var element = document.${formName}.PERAKUANPT2_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttPERAKUANPT2_MAIN; i++){		

		    if(ttPERAKUANPT2_MAIN==1){
		 check_length(document.${formName}.txtUlasanPERAKUANPT2_MAIN,'30000','txtUlasanPERAKUANPT2_MAIN_check','txtUlasanPERAKUANPT2_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanPERAKUANPT2_MAIN[i],'30000','txtUlasanPERAKUANPT2_MAIN_check'+i,'txtUlasanPERAKUANPT2_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//PERAKUANPT2


//PERAKUANPT3
function textarea_PERAKUANPT3_MAIN(tambahtolak,jenis,index_tolak){
	
	var PERAKUANPT3_MAIN_temp1_length=0;

	if(document.${formName}.PERAKUANPT3_MAIN_temp1 != null){

		if(document.${formName}.PERAKUANPT3_MAIN_temp1.length>0){
			PERAKUANPT3_MAIN_temp1_length = document.${formName}.PERAKUANPT3_MAIN_temp1.length;
		}else{
			PERAKUANPT3_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanPERAKUANPT3_MAIN!=null){

		if(document.${formName}.txtUlasanPERAKUANPT3_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanPERAKUANPT3_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='PERAKUANPT3_MAIN_tempX1' name='PERAKUANPT3_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPERAKUANPT3_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='PERAKUANPT3_MAIN_tempX1' name='PERAKUANPT3_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPERAKUANPT3_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#PERAKUANPT3_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(PERAKUANPT3_MAIN_temp1_length>0){
			ttPERAKUANPT3_MAIN = PERAKUANPT3_MAIN_temp1_length;
		}else{
			ttPERAKUANPT3_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttPERAKUANPT3_MAIN = ttPERAKUANPT3_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttPERAKUANPT3_MAIN = ttPERAKUANPT3_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttPERAKUANPT3_MAIN; i++){	

  		if(ttPERAKUANPT3_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.PERAKUANPT3_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.PERAKUANPT3_MAIN_tempX1[0].value
				} 	
			}	
			
			if(jenis == "umum" )
			{
			temp_value = '$perakuan3';
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='5%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'>c.</td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanPERAKUANPT3_MAIN\" id=\"txtUlasanPERAKUANPT3_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT3_MAIN_check','txtUlasanPERAKUANPT3_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT3_MAIN_check','txtUlasanPERAKUANPT3_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERAKUANPT3_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanPERAKUANPT3_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanPERAKUANPT3_MAIN\" id=\"txtUlasanPERAKUANPT3_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT3_MAIN_check','txtUlasanPERAKUANPT3_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT3_MAIN_check','txtUlasanPERAKUANPT3_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERAKUANPT3_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanPERAKUANPT3_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_PERAKUANPT3_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttPERAKUANPT3_MAIN>1) {      
	     	codes1 += "				<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_PERAKUANPT3_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttPERAKUANPT3_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.PERAKUANPT3_MAIN_tempX1.value

			}else if(ttPERAKUANPT3_MAIN>2 && i!=(ttPERAKUANPT3_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.PERAKUANPT3_MAIN_tempX1[i].value

			}else if(ttPERAKUANPT3_MAIN>1 && i!=(ttPERAKUANPT3_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.PERAKUANPT3_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.PERAKUANPT3_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.PERAKUANPT3_MAIN_tempX1[i+1].value	
				}	

			}else if(ttPERAKUANPT3_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.PERAKUANPT3_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.PERAKUANPT3_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='5%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 4."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='92%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanPERAKUANPT3_MAIN\" id=\"txtUlasanPERAKUANPT3_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT3_MAIN_check"+i+"','txtUlasanPERAKUANPT3_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT3_MAIN_check"+i+"','txtUlasanPERAKUANPT3_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERAKUANPT3_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERAKUANPT3_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='92%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanPERAKUANPT3_MAIN\" id=\"txtUlasanPERAKUANPT3_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT3_MAIN_check"+i+"','txtUlasanPERAKUANPT3_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT3_MAIN_check"+i+"','txtUlasanPERAKUANPT3_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERAKUANPT3_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERAKUANPT3_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttPERAKUANPT3_MAIN>1 && ttPERAKUANPT3_MAIN==(i+1)){  	
			codes1 += 	" 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_PERAKUANPT3_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttPERAKUANPT3_MAIN>1){      
			codes1 += 	"			<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_PERAKUANPT3_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#PERAKUANPT3_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(PERAKUANPT3_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERAKUANPT3_MAIN.length > 1 ){

		for (t = 0; t < PERAKUANPT3_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanPERAKUANPT3_MAIN[t].value = document.${formName}.PERAKUANPT3_MAIN_temp1[t].value;
		}

		}else if(PERAKUANPT3_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERAKUANPT3_MAIN.length < 1 ){
		
			for (t = 0; t < PERAKUANPT3_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanPERAKUANPT3_MAIN.value = document.${formName}.PERAKUANPT3_MAIN_temp1[0].value;
			}
		}
		
		else if(PERAKUANPT3_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanPERAKUANPT3_MAIN.value = document.${formName}.PERAKUANPT3_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(PERAKUANPT3_MAIN_temp1_length > 1){

			for (t = 0; t < PERAKUANPT3_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.PERAKUANPT3_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.PERAKUANPT3_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(PERAKUANPT3_MAIN_temp1_length == 1){
			
	 		document.${formName}.PERAKUANPT3_MAIN_temp1.value = "";			
	 		var element = document.${formName}.PERAKUANPT3_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttPERAKUANPT3_MAIN; i++){		

		    if(ttPERAKUANPT3_MAIN==1){
		 check_length(document.${formName}.txtUlasanPERAKUANPT3_MAIN,'30000','txtUlasanPERAKUANPT3_MAIN_check','txtUlasanPERAKUANPT3_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanPERAKUANPT3_MAIN[i],'30000','txtUlasanPERAKUANPT3_MAIN_check'+i,'txtUlasanPERAKUANPT3_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//PERAKUANPT3


//PERAKUANPT4
function textarea_PERAKUANPT4_MAIN(tambahtolak,jenis,index_tolak){
	
	var PERAKUANPT4_MAIN_temp1_length=0;

	if(document.${formName}.PERAKUANPT4_MAIN_temp1 != null){

		if(document.${formName}.PERAKUANPT4_MAIN_temp1.length>0){
			PERAKUANPT4_MAIN_temp1_length = document.${formName}.PERAKUANPT4_MAIN_temp1.length;
		}else{
			PERAKUANPT4_MAIN_temp1_length = 1;
		}
	}

	var code_temp1 = "";
	
	if(document.${formName}.txtUlasanPERAKUANPT4_MAIN!=null){

		if(document.${formName}.txtUlasanPERAKUANPT4_MAIN.length > 1){
 			for (i = 0; i < document.${formName}.txtUlasanPERAKUANPT4_MAIN.length; i++){
 				code_temp1 += "<tr><td><input type='hidden' id='PERAKUANPT4_MAIN_tempX1' name='PERAKUANPT4_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPERAKUANPT4_MAIN[i].value+"'></td></tr>";
 			} 
 		}else{
			code_temp1 += "<tr><td><input type='hidden' id='PERAKUANPT4_MAIN_tempX1' name='PERAKUANPT4_MAIN_tempX1' value= '"+document.${formName}.txtUlasanPERAKUANPT4_MAIN.value+"'></td></tr>";
		}
	}


	$jquery("#PERAKUANPT4_MAIN_temp").html(""+code_temp1); 

	var codes1 = "";

	if(jenis == "umum"){
		if(PERAKUANPT4_MAIN_temp1_length>0){
			ttPERAKUANPT4_MAIN = PERAKUANPT4_MAIN_temp1_length;
		}else{
			ttPERAKUANPT4_MAIN = 1;
		}
	}
	
	if(jenis == "tambah"){
		ttPERAKUANPT4_MAIN = ttPERAKUANPT4_MAIN + parseInt(tambahtolak);
	}

	if(jenis == "tolak"){
		ttPERAKUANPT4_MAIN = ttPERAKUANPT4_MAIN + parseInt(tambahtolak);
	}


	
  	for (i = 0; i < ttPERAKUANPT4_MAIN; i++){	

  		if(ttPERAKUANPT4_MAIN==1){

  			var temp_value = "";
    		var temp_amaunt = "";
	
			if(i==0 && jenis == "tolak"){	
				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.PERAKUANPT4_MAIN_tempX1[1].value
				}

			    if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.PERAKUANPT4_MAIN_tempX1[0].value
				} 	
			}	
			
			if(jenis == "umum" )
			{
			temp_value = '$perakuan4';
			}		

   			codes1 += "<table width='100%' border='0'> 																									"+
   	   				  " 	<tr> 																														"+
   	   				  "			<td width='5%'>&nbsp;</td> 																								"+
   	   				  "			<td width='3%' valign='top'>d.</td> 																		"+
					  "			#if($mode=='new') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea name=\"txtUlasanPERAKUANPT4_MAIN\" id=\"txtUlasanPERAKUANPT4_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT4_MAIN_check','txtUlasanPERAKUANPT4_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT4_MAIN_check','txtUlasanPERAKUANPT4_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERAKUANPT4_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>					"+
         			  "				<div id=\"txtUlasanPERAKUANPT4_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+		
 					  "			#end   																													"+		
					  "			#if($mode=='view') 																										"+
					  "			<td width='92%'> 																										"+
	     			  "		 		<textarea $disability $disabilityx name=\"txtUlasanPERAKUANPT4_MAIN\" id=\"txtUlasanPERAKUANPT4_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
         	          "				onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT4_MAIN_check','txtUlasanPERAKUANPT4_MAIN_num','normal','no','');textarea_kerosakan1()\"	"+
         			  " 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT4_MAIN_check','txtUlasanPERAKUANPT4_MAIN_num','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
         			  " 			<div ><span id=\"txtUlasanPERAKUANPT4_MAIN_num\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>			"+
         			  "				<div id=\"txtUlasanPERAKUANPT4_MAIN_check\" class=\"alert_msg\" ></div> 													"+
 		 			  "			</td> 																													"+	
					  "			#end   																													"+
  					  "		</tr> 																														"+
					  "																																	"+
					  " 	#if($mode=='new' || ($mode=='view' && $isEdit=='yes') )			"+
					  "		<tr> 																														"+
					  "			<td colspan='2' valign='top'>&nbsp;</td>																				"+	
					  "			<td align='left' valign='top' > 																						"+	 	
					  " 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+		
					  "				onClick=\"textarea_PERAKUANPT4_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
	      		

		   	if(ttPERAKUANPT4_MAIN>1) {      
	     	codes1 += "				<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 								    					"+
		     	      " 			onClick=\"textarea_PERAKUANPT4_MAIN(-1,'tolak','');textarea_kerosakan1()\" >												";
			}

			codes1 += " 		</td>		"+	
					  "		</tr> 																														"+
					  "     #end "+
					  "</table> ";

		}else{	

			var temp_value = "";
			var temp_amaunt = "";	

			if(ttPERAKUANPT4_MAIN==2 && i==0 && jenis == "tambah"){	

				temp_value = document.${formName}.PERAKUANPT4_MAIN_tempX1.value

			}else if(ttPERAKUANPT4_MAIN>2 && i!=(ttPERAKUANPT4_MAIN-1) && jenis == "tambah"){	

				temp_value = document.${formName}.PERAKUANPT4_MAIN_tempX1[i].value

			}else if(ttPERAKUANPT4_MAIN>1 && i!=(ttPERAKUANPT4_MAIN+1) && jenis == "tolak"){	

   				if(i==parseInt(index_tolak)){
					temp_value = document.${formName}.PERAKUANPT4_MAIN_tempX1[parseInt(index_tolak)+1].value
				}

   			  	if(i<parseInt(index_tolak)){
	    			temp_value = document.${formName}.PERAKUANPT4_MAIN_tempX1[i].value
				}
				
   				if(i>parseInt(index_tolak)){
    				temp_value = document.${formName}.PERAKUANPT4_MAIN_tempX1[i+1].value	
				}	

			}else if(ttPERAKUANPT4_MAIN==1 && i==1 && jenis == "tolak"){
					
   				if(parseInt(index_tolak)==0){
					temp_value = document.${formName}.PERAKUANPT4_MAIN_tempX1[1].value;
				}

   			   	if(parseInt(index_tolak)==1){
					temp_value = document.${formName}.PERAKUANPT4_MAIN_tempX1[0].value;
				}

   			}		
	
			codes1 += 	"<table width='100%' border='0'> 																								"+
				 		" 	<tr> 																														"+
				  		"		<td width='5%'>&nbsp;</td> 																								"+
				  		"		<td width='3%' valign='top'> 4."+(i+1)+"  </td> 																		"+
				  		"		#if($!mode=='new') 																										"+														
				  		"		<td width='92%'> 																										"+
		     			"		 	<textarea name=\"txtUlasanPERAKUANPT4_MAIN\" id=\"txtUlasanPERAKUANPT4_MAIN\" cols=\"90\"  rows=\"10\"						"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT4_MAIN_check"+i+"','txtUlasanPERAKUANPT4_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT4_MAIN_check"+i+"','txtUlasanPERAKUANPT4_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERAKUANPT4_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERAKUANPT4_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+			
	 		 			"		#end   																													"+	
	 		 			"		#if($!mode=='view') 																										"+
	 		 			"		<td width='92%'> 																										"+
		     			"		 	<textarea $disability $disabilityx name=\"txtUlasanPERAKUANPT4_MAIN\" id=\"txtUlasanPERAKUANPT4_MAIN\" cols=\"90\"  rows=\"10\"			"+                  		
	         	        "			onKeyup=\"check_length(this,'30000','txtUlasanPERAKUANPT4_MAIN_check"+i+"','txtUlasanPERAKUANPT4_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\"	"+
	         			" 			onKeydown=\"check_length(this,'30000','txtUlasanPERAKUANPT4_MAIN_check"+i+"','txtUlasanPERAKUANPT4_MAIN_num"+i+"','normal','no','');textarea_kerosakan1()\">"+temp_value+"</textarea>	"+                 			   		
	         			" 			<div ><span id=\"txtUlasanPERAKUANPT4_MAIN_num"+i+"\" style=\"color:blue;\" ></span><span>&nbsp;Baki Aksara</span></div>		"+
	         			"			<div id=\"txtUlasanPERAKUANPT4_MAIN_check"+i+"\" class=\"alert_msg\" ></div> 											"+
	 		 			"		</td> 																													"+
	 		 			"		#end   																													"+
			  			"	</tr> 																														"+
			  			"																																"+
			  			"   #if($mode=='new' || ($mode=='view' && $isEdit=='yes') ) "+
			  			"	<tr> 																														"+
			  			"		<td colspan='2' valign='top'>&nbsp;</td>																				"+	
			  			"		<td align='left' valign='top' > 																						";	 	

        	if(ttPERAKUANPT4_MAIN>1 && ttPERAKUANPT4_MAIN==(i+1)){  	
			codes1 += 	" 			<input style='display:none;' type='button' name='cmdPlus' id='cmdPlus' value='+' 															"+
						"			onClick=\"textarea_PERAKUANPT4_MAIN(1,'tambah','');textarea_kerosakan1()\" > 											";
			}
		
			if(ttPERAKUANPT4_MAIN>1){      
			codes1 += 	"			<input style='display:none;' type='button' name='cmdMinus' id='cmdMinus' value='-' 													 	"+
						"			onClick=\"textarea_PERAKUANPT4_MAIN(-1,'tolak','"+i+"');textarea_kerosakan1()\"> 										";
			}
	 
			codes1 += 	" 		</td> 																													"+	
				  	  	" 	</tr>																														"+
						"   #end "+
				  	  	" 	<tr height='5'><td colspan='3'>&nbsp;</td></tr>																				"+	
				  	  	"</table>";

		}//close else
				
	}//close for loop
	
	
	$jquery("#PERAKUANPT4_MAIN").html(codes1);

	
	if(jenis == "tambah" || jenis == "umum" ){
		
		if(PERAKUANPT4_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERAKUANPT4_MAIN.length > 1 ){

		for (t = 0; t < PERAKUANPT4_MAIN_temp1_length; t++){	
    		document.${formName}.txtUlasanPERAKUANPT4_MAIN[t].value = document.${formName}.PERAKUANPT4_MAIN_temp1[t].value;
		}

		}else if(PERAKUANPT4_MAIN_temp1_length > 1 && document.${formName}.txtUlasanPERAKUANPT4_MAIN.length < 1 ){
		
			for (t = 0; t < PERAKUANPT4_MAIN_temp1_length; t++){	
    			document.${formName}.txtUlasanPERAKUANPT4_MAIN.value = document.${formName}.PERAKUANPT4_MAIN_temp1[0].value;
			}
		}
		
		else if(PERAKUANPT4_MAIN_temp1_length == 1){
			document.${formName}.txtUlasanPERAKUANPT4_MAIN.value = document.${formName}.PERAKUANPT4_MAIN_temp1.value;
		}
		
	}
	
	
	if(jenis == "tolak"){	
		
		if(PERAKUANPT4_MAIN_temp1_length > 1){

			for (t = 0; t < PERAKUANPT4_MAIN_temp1_length; t++){	 

				if(index_tolak==t){
			  		document.${formName}.PERAKUANPT4_MAIN_temp1[index_tolak].value = "";	
					var element = document.${formName}.PERAKUANPT4_MAIN_temp1[index_tolak];
    				element.parentNode.removeChild(element);	
				}
    		}	
	    	
		}else if(PERAKUANPT4_MAIN_temp1_length == 1){
			
	 		document.${formName}.PERAKUANPT4_MAIN_temp1.value = "";			
	 		var element = document.${formName}.PERAKUANPT4_MAIN_temp1;
     		element.parentNode.removeChild(element); 	 
		}
		
	}//close tolak			

	if(jenis == "tolak" || jenis == "tambah" || jenis == "umum"){	

		for (i = 0; i < ttPERAKUANPT4_MAIN; i++){		

		    if(ttPERAKUANPT4_MAIN==1){
		 check_length(document.${formName}.txtUlasanPERAKUANPT4_MAIN,'30000','txtUlasanPERAKUANPT4_MAIN_check','txtUlasanPERAKUANPT4_MAIN_num','normal','no','');	
      
				}else{	
				check_length(document.${formName}.txtUlasanPERAKUANPT4_MAIN[i],'30000','txtUlasanPERAKUANPT4_MAIN_check'+i,'txtUlasanPERAKUANPT4_MAIN_num'+i,'normal','no','');	
			}		 
		}	
	}
	
}//PERAKUANPT4

function textarea_kerosakan1()
{

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

    
    //ULASANPENGARAH
	var ULASANPENGARAH_MAIN_temp1_length=0;
    
	if(document.${formName}.ULASANPENGARAH_MAIN_temp1 != null){
		if(document.${formName}.ULASANPENGARAH_MAIN_temp1.length>0){
			ULASANPENGARAH_MAIN_temp1_length = document.${formName}.ULASANPENGARAH_MAIN_temp1.length;
		}else{
			ULASANPENGARAH_MAIN_temp1_length = 1;
		}
	}

    if(ULASANPENGARAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPENGARAH_MAIN.length > 1 ){
		for (t = 0; t < ULASANPENGARAH_MAIN_temp1_length; t++){	
    		document.${formName}.ULASANPENGARAH_MAIN_temp1[t].value = document.${formName}.txtUlasanULASANPENGARAH_MAIN[t].value;
		}
	}else if(ULASANPENGARAH_MAIN_temp1_length > 1 && document.${formName}.txtUlasanULASANPENGARAH_MAIN.length < 1 ){
		for (t = 0; t < ULASANPENGARAH_MAIN_temp1_length; t++){	
    		document.${formName}.ULASANPENGARAH_MAIN_temp1[0].value = document.${formName}.txtUlasanULASANPENGARAH_MAIN.value;
    	}
	}else if(ULASANPENGARAH_MAIN_temp1_length == 1){
		document.${formName}.ULASANPENGARAH_MAIN_temp1.value = document.${formName}.txtUlasanULASANPENGARAH_MAIN.value;
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
}

</script>