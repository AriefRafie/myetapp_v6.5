 <style type="text/css">
  <!--
  .style1 {color: #0033FF}
  -->
  <!--
  .pautanms {color: #0000FF}
  -->
  <!--
  .stylefont {font-size:10px}
  -->
 </style>
<table width="99%" border="0">
<tr>
<td>
    	


	<fieldset>
	<!--<legend>LAPORAN</legend>-->
	
	
		<br>
		<fieldset>
		<legend>PILIHAN</legend>
			<table border="0" align="center" width="60%" >      
		     <tbody> 

		      <tr> 
		        <td scope="row" align="left" valign="top">&nbsp;Laporan</td>
		        <td valign="top">:&nbsp;</td>
		        <td>

			    	<input type="radio" name="soclaporantemp" value="N" $!checkN  onclick="senaraiLaporan()"/>Laporan Mengikut Negeri
<!-- 					</br><input type="radio" name="soclaporantemp" value="D" $!checkD  onclick="senaraiLaporan()"/>Laporan Mengikut Daerah
					</br><input type="radio" name="soclaporantemp" value="K" $!checK  onclick="senaraiLaporan()"/>Laporan Mengikut Kementerian
					</br><input type="radio" name="soclaporantemp" value="A" $!checkA  onclick="senaraiLaporan()"/>Laporan Mengikut Agensi -->
					</br><input type="radio" name="soclaporantemp" value="L" $!checkL  onclick="senaraiLaporan()"/>Laporan Lain
					</br><input type="hidden" name="socjenislaporan" value="$!jenisLaporan">
		        
		        </td>
		      </tr>		     
		      <tr> 
		        <td scope="row" align="left">&nbsp;Negeri </td>
		        <td>:&nbsp;</td>
		        <td>$selectNegeri</td>
		      </tr>
<!-- 		      <tr> 
		        <td scope="row" align="left">&nbsp;Daerah </td>
		        <td>:&nbsp;</td>
		        <td>$selectDaerahBaru</td>
		      </tr> 
		      <tr> 
		        <td scope="row" align="left">&nbsp;Kementerian </td>
		        <td>:&nbsp;</td>
		        <td>$selectUnit</td>
		      </tr>
		      <tr> 
		        <td scope="row" align="left">&nbsp;Agensi </td>
		        <td>:&nbsp;</td>
		        <td>$selectDaerah</td>
		      </tr>    -->
<!-- Midified by rosli 17/12/2010 -->
	<!-- 	      <tr> 
		        <td scope="row" align="left">&nbsp;Status Keputusan </td>
		        <td>:&nbsp;</td>
		        <td>
		        	<select name="pilihkeputusan" style="width:200px;" $style/>

					#set ($list = ["BELUM ADA KEPUTUSAN","DILULUSKAN","TIDAK DILULUSKAN","DIBATALKAN/DITARIKBALIK"])
					#set( $counter = 0 )
						<option selected value="0">SILA PILIH</option>
					#foreach ($i in $list)
						#if ($!keputusan.status == $counter) 
						<option selected value="0$counter">$i</option>
						#else
						<option value="0$counter">$i</option>
						#end
						#set ($counter = $counter+1)
					#end
					
					</select>
		        </td>
		      </tr> 

		      <tr> 
		        <td scope="row" align="left">&nbsp;Urusan</td>
		        <td>:&nbsp;</td>
		        <td>
				#set($checkedMilik = "")
				#set($checkedRizab = "")
				#set($checkedMilikRizab = "")
				#if($idUrusan == "1")
					#set($checkedMilik = "checked")
					#set($checkedRizab = "")
					#set($checkedMilikRizab = "")
					            
				#elseif($idUrusan == "10")
					#set($checkedRizab ="checked")
					#set($checkedMilik ="")
					#set($checkedMilikRizab = "")
				
				#elseif($idUrusan == "1,10")
					#set($checkedRizab ="")
					#set($checkedMilik ="")
					#set($checkedMilikRizab = "checked")
				
				#else
					#set($checkedRizab ="")
					#set($checkedMilik ="")
					#set($checkedMilikRizab = "")
									
				#end
			    	<input type="radio" name="socJenisTanahtemp" value="1" $checkedMilik  onclick="senaraiLaporan()"/>PERMOHONAN TANAH
					<input type="radio" name="socJenisTanahtemp" value="10" $checkedRizab  onclick="senaraiLaporan()"/>PERIZAPAN TANAH
					<input type="radio" name="socJenisTanahtemp" value="1,10" $checkedMilikRizab  onclick="senaraiLaporan()"/>PERMOHONAN/ PERIZAPAN TANAH
					<input type="hidden" name="socJenisTanah" value="$!jenisTanah">
		        
		        </td>
		      </tr>  --> 		      		      
		       <tr> 
		        <td scope="row" align="left">&nbsp;Tarikh Mula</td>
		        <td>:&nbsp;</td>
		        <td>
		       		<label>
		      			<input name="txdMula" type="text" id="txdMula" value="$!txdMula" size="10" onblur="check_date(this);semakTarikhHariIni(document.${formName}.txdMula);" />
		        	</label>
		        	<a href="javascript:displayDatePicker('txdMula',false,'dmy');">
		        		<img border="0" src="../img/calendar.gif"/>
				 		<span class="pautanms" class="stylefont">dd/mm/yyyy</span> 
      				</a>
		        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hingga&nbsp;&nbsp;&nbsp;:&nbsp;
		     		<label>
		      			<input name="txdAkhir" type="text" id="txdAkhir" value="$!txdAkhir" size="10" onblur="check_date(this);semakTarikhHariIni(document.${formName}.txdAkhir);"/>
		        	</label>
		        	<a href="javascript:displayDatePicker('txdAkhir',false,'dmy');">
		        		<img border="0" src="../img/calendar.gif"/>
				 		<span class="pautanms">dd/mm/yyyy</span> 
		        	</a>
				 <!----></td>
		      </tr> 
		   </tbody>
		 </table>
		</fieldset>	
		<br>
	#if ( $!checkN.equals("checked") )
		
		<fieldset>
		<legend>LAPORAN MENGIKUT NEGERI</legend>
			
		<table border="0" align="center" width="100%" >
			<tr>
			  	<td width="3%" class="row2">1.</td>
			  	<td width="97%" class="row2">
			  		<a href="javascript:openReport('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','Negeri','HTPGadaianSenaraiJPK')" class="style1" >SENARAI PERMOHONAN (TMP-JPK)</a>
			 	</td>
		   	</tr>
		 	<tr>
		  	<td width="3%" class="row2">1.</td>
		  	<td width="97%" class="row2">
		  		<a href="javascript:openReport('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','Negeri','HTPGadaianSenaraiKPKT')" class="style1" >SENARAI PERMOHONAN (JPN)</a>
		 	</td>
		   	</tr>
		   	<!--
		  	<tr>
			  	<td width="3%" class="row1">2.</td>
			  	<td width="97%" class="row1">
			  		<a href="javascript:openLaporan('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','urusdae','HTPPermohonanTanahMengikutDaerah')" class="style1" >SENARAI PERMOHONAN MENGIKUT NEGERI DAN DAERAH</a>
			 	</td>
		   	</tr>
		  	<tr>
			  	<td width="3%" class="row2">3.</td>
			  	<td width="97%" class="row2">
			  		<a href="javascript:openLaporan('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','urusnegkem','HTPPermohonanTanahMengikutNegeriKementerian')" class="style1" >SENARAI PERMOHONAN MENGIKUT NEGERI DAN KEMENTERIAN</a>
			 	</td>
		   	</tr>
		   	<tr>
			  	<td width="3%" class="row1">4.</td>
			  	<td width="97%" class="row1">
			  		<a href="javascript:openLaporan('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','urusagen','HTPPermohonanTanahMengikutAgensi')" class="style1" >SENARAI PERMOHONAN MENGIKUT AGENSI</a>
			 	</td>
		   	</tr>		   		
		   	<tr>
			  	<td width="3%" class="row2">5.</td>
			  	<td width="97%" class="row2">
			  		<a href="javascript:openLaporan('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','urusnegagen','HTPPermohonanTanahMengikutNegeriAgensi')" class="style1" >SENARAI PERMOHONAN MENGIKUT AGENSI DAN NEGERI</a>
			 	</td>
		   	</tr>
		   	  -->
		   </table>
		
		</fieldset>
	#end				
	#if ( $!checkD.equals("checked") )
		
		<fieldset>
		<legend>LAPORAN MENGIKUT DAERAH</legend>
			
		<table border="0" align="center" width="100%" >
			<tr>
			  	<td width="3%" class="row2">1.</td>
			  	<td width="97%" class="row2">
			  		<a href="javascript:openLaporan('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','urusneg','HTPPermohonanTanahMengikutDerah')" class="style1" >SENARAI PERMOHONAN</a>
			 	</td>
		   	</tr>
		   	<!--
		  	<tr>
			  	<td width="3%" class="row1">2.</td>
			  	<td width="97%" class="row1">
			  		<a href="javascript:openLaporan('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','urusdae','HTPPermohonanTanahMengikutDaerah')" class="style1" >SENARAI PERMOHONAN MENGIKUT NEGERI DAN DAERAH</a>
			 	</td>
		   	</tr>
		  	<tr>
			  	<td width="3%" class="row2">3.</td>
			  	<td width="97%" class="row2">
			  		<a href="javascript:openLaporan('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','urusnegkem','HTPPermohonanTanahMengikutNegeriKementerian')" class="style1" >SENARAI PERMOHONAN MENGIKUT NEGERI DAN KEMENTERIAN</a>
			 	</td>
		   	</tr>
		   	<tr>
			  	<td width="3%" class="row1">4.</td>
			  	<td width="97%" class="row1">
			  		<a href="javascript:openLaporan('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','urusagen','HTPPermohonanTanahMengikutAgensi')" class="style1" >SENARAI PERMOHONAN MENGIKUT AGENSI</a>
			 	</td>
		   	</tr>		   		
		   	<tr>
			  	<td width="3%" class="row2">5.</td>
			  	<td width="97%" class="row2">
			  		<a href="javascript:openLaporan('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','urusnegagen','HTPPermohonanTanahMengikutNegeriAgensi')" class="style1" >SENARAI PERMOHONAN MENGIKUT AGENSI DAN NEGERI</a>
			 	</td>
		   	</tr>
		   	  -->
		   </table>
		
		</fieldset>
	#end
		#if ( $!checK.equals("checked") )
		
		<fieldset>
		<legend>LAPORAN MENGIKUT KEMENTERIAN</legend>
			
		<table border="0" align="center" width="100%" >
			<tr>
			  	<td width="3%" class="row2">1.</td>
			  	<td width="97%" class="row2">
			  		<a href="javascript:openLaporan('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','urusnegkem','HTPPermohonanTanahMengikutNegeriKementerian')" class="style1" >SENARAI PERMOHONAN</a>
			 	</td>
		   	</tr>
		   	<!--
		  	  	<tr>
			  	<td width="3%" class="row2">3.</td>
			  	<td width="97%" class="row2">
			  		<a href="javascript:openLaporan('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','urusnegkem','HTPPermohonanTanahMengikutNegeriKementerian')" class="style1" >SENARAI PERMOHONAN MENGIKUT NEGERI DAN KEMENTERIAN</a>
			 	</td>
		   	</tr>
		   	  -->
		   </table>
		
		</fieldset>
	#end		
					
	#if ( $!checkA.equals("checked") )
		
		<fieldset>
		<legend>LAPORAN MENGIKUT AGENSI</legend>
			
		<table border="0" align="center" width="100%" >
			<tr>
			  	<td width="3%" class="row2">1.</td>
			  	<td width="97%" class="row2">
			  		<a href="javascript:openLaporan('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','urusneg','HTPPermohonanTanahMengikutDerah')" class="style1" >SENARAI PERMOHONAN</a>
			 	</td>
		   	</tr>
		   	<!--
		  	  	<tr>
			  	<td width="3%" class="row2">3.</td>
			  	<td width="97%" class="row2">
			  		<a href="javascript:openLaporan('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','urusnegkem','HTPPermohonanTanahMengikutNegeriKementerian')" class="style1" >SENARAI PERMOHONAN MENGIKUT NEGERI DAN KEMENTERIAN</a>
			 	</td>
		   	</tr>
		   	<tr>
			  	<td width="3%" class="row1">4.</td>
			  	<td width="97%" class="row1">
			  		<a href="javascript:openLaporan('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','urusagen','HTPPermohonanTanahMengikutAgensi')" class="style1" >SENARAI PERMOHONAN MENGIKUT AGENSI</a>
			 	</td>
		   	</tr>		   		
		   	<tr>
			  	<td width="3%" class="row2">5.</td>
			  	<td width="97%" class="row2">
			  		<a href="javascript:openLaporan('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','urusnegagen','HTPPermohonanTanahMengikutNegeriAgensi')" class="style1" >SENARAI PERMOHONAN MENGIKUT AGENSI DAN NEGERI</a>
			 	</td>
		   	</tr>
		   	  -->
		   </table>
		
		</fieldset>
	#end		
		
	#if ( $!checkL.equals("checked") )
		
		<fieldset>
		<legend>LAIN-LAIN LAPORAN</legend>
			
		<table border="0" align="center" width="100%" >
		<!-- 	<tr>
			  	<td width="3%" class="row2">1.</td>
			  	<td width="97%" class="row2">
			  		<a href="javascript:openReport('ekptg.report.htp.LaporanUtamaHTP','IDSUBURUSAN=1','urusan','HTPPermohonanRingkasanTanahMilik')" class="style1" >RINGKASAN PERMOHONAN</a>
			 	</td>
		   	</tr> -->
			<tr>
			  	<td width="3%" class="row2">1.</td>
			  	<td width="97%" class="row2">
			  		<a href="javascript:openReport('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','Bulanan','HTPGadaianLaporanPrestasi')" class="style1" >LAPORAN PRESTASI</a>
			 	</td>
		   	</tr>		 
		   </table>
		
		</fieldset>
	#end		
	</fieldset>




</td>
</tr>	
</table>

<script>
	
function doChangeSelect() {
	document.f1.command.value = "doChangeSelect";
	document.f1.action = "";
	document.f1.submit();
}

	function openReport(urli,param,laporan,tem){
		var negeri = document.${formName}.socNegeri.value;
		//var unit = document.${formName}.socUnit.value;
		//var daerah = document.${formName}.socDaerah.value;
		//var daerahbaru = document.${formName}.socDaerahBaru.value;
		var pnegeri = "&ID_NEGERI=0";
		var pdaerah = "&ID=0";
		var pkem = "&ID_KEMENTERIAN=0";
		var pagen = "&ID_AGENSI=0";
		var ptem = "&template="+tem;
		var pbulanmula = "&BULANTAHUN=0";
		var pbulantamat = "&BULANTAHUNTMT=0";
		var ptahun = "&TAHUN=";
		var punit = "&ID_PEJABAT=0";
		var status ="&STATUS=-1";
		var bulan = "0"
		// param = &IDSUBURUSAN=1
		//pdaerah += "&ID_KEMENTERIAN=0&ID_AGENSI=0";
		//pnegeri ="&ID_NEGERI=0&bulan=1";
	/* 	if(document.${formName}.socJenisTanah.value == ""){
			alert("Sila pilih \"Urusan\" terlebih dahulu.");
			return;
		}	
		if(document.${formName}.socJenisTanah.value != ""){ 
				if(document.${formName}.socJenisTanah.value == "10")
					ptem = "&template=HTPPermohonanRingkasanTanahRizab";
				else if(document.${formName}.socJenisTanah.value == "1,10"){
					alert('Tiada Laporan');
					return;
				}
					
		} */
		//param += "&ID_URUSAN="+document.${formName}.socJenisTanah.value;
		//alert("negeri="+negeri);
		if(laporan == "Negeri"){
			if(negeri == "-1"){
				alert("Sila pilih \"Negeri\" terlebih dahulu.");
				document.${formName}.socNegeri.focus(); 
				return;
				
			}else if(negeri != "0"){
				 ptem = "&template="+tem+"Mengikut"+laporan;
			}
			pnegeri = "&ID_NEGERI="+negeri;
			
			akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
	  		akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
	  		akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
			var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
	  		mula_bulan = document.${formName}.txdMula.value.substring(3,5);
	 		mula_hari = document.${formName}.txdMula.value.substring(0,2);
	  		mula_tahun = document.${formName}.txdMula.value.substring(6,10);
			var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;
		
			var mula = Date.parse(mulatemp);
			var akhir = Date.parse(akhirtemp);
			var tarikhsemasa = new Date();
		
			if(isNaN(mula) == false && isNaN(akhir) == true){
				ptem = "&template="+tem+"Mengikut"+laporan+"Semasa";
				//if(negeri == "0"){
				//	 ptem = "&template="+tem+"Semasa;
				//}
				pbulanmula = "&BULANTAHUN="+mula_bulan+"/"+mula_tahun;
				bulan ="&bulan="+mula_bulan;
				pbulantamat = "&BULANTAHUNTMT="+mula_bulan+"/"+mula_tahun+"&bulantamat="+mula_bulan;
				ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+mula_tahun;
			
			}
		
		}else if(laporan == "Bulanan"){

			//if(document.${formName}.txdMula.value ==""){
			//	pdaerah +="&bulan=00";
			//}else{
				akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
		  		akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
		  		akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
				var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
		  		mula_bulan = document.${formName}.txdMula.value.substring(3,5);
		 		mula_hari = document.${formName}.txdMula.value.substring(0,2);
		  		mula_tahun = document.${formName}.txdMula.value.substring(6,10);
				var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;
			
				var mula = Date.parse(mulatemp);
				var akhir = Date.parse(akhirtemp);
				var tarikhsemasa = new Date();
			  
			  	if(akhir<mula){
			    	alert("Sila pastikan Tarikh Akhir tidak melebihi dari Tarikh Mula.");
			    	return;
			  	}
			  	if(akhir>tarikhsemasa){
			    	alert("Sila pastikan Tarikh Akhir tidak melebihi dari Tarikh Semasa.");
			    	return;
			  	}
				//alert(mula_tahun);
				//alert(akhir_tahun);
				if(mula_tahun==""){
					alert("Sila pilih \"Tarikh\" terlebih dahulu.");
					document.${formName}.txdMula.focus(); 
					return;
					
				}else if(akhir_tahun==""){
					alert("Sila pilih \"Tarikh Hingga\" terlebih dahulu.");
					document.${formName}.txdMula.focus(); 
					return;
					
				}else{
					if(semak_tarikh(document.${formName}.txdMula)==false){ 
						document.${formName}.txdMula.focus(); 
						return;

					}					
					semakTarikhHariIni(document.${formName}.txdMula)
					
				}
				pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;			
				pbulanmula = "&BULANTAHUN="+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
				ptem = "&template="+tem+laporan; //nama template
				ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+akhir_tahun;
	  	        
			//}
			

		}
	
		var url = "../servlet/"+urli+"?"+param+pnegeri+pdaerah+pkem+pagen+ptem+pbulanmula+pbulantamat+ptahun+punit+status+bulan;
		//alert(url);
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	
	}
function openLaporan(urli,param,laporan,tem){
	var negeri = document.${formName}.socNegeri.value;
	var unit = document.${formName}.socUnit.value;
	var daerah = document.${formName}.socDaerah.value;
	var daerahbaru = document.${formName}.socDaerahBaru.value;
	var pnegeri = "&ID_NEGERI=0";
	var ptahun = "&TAHUN=";
	var ptem = "&template="+tem;
	var pbulanmula = "&BULANTAHUN=0";
	var pbulantamat = "&BULANTAHUNTMT=0";
	var punit = "&ID_PEJABAT=0";
	var pdaerah = "&ID=0";

	if(laporan=="negeri"){
		if(negeri=="0"){
			alert("Sila pilih \"Negeri\" terlebih dahulu.");
			document.${formName}.socNegeri.focus(); 
			return;
		}	
		pnegeri = "&ID_KEMENTERIAN=0&ID_AGENSI=0&ID_NEGERI="+negeri+"&bulan=1";
	
	}else if(laporan=="unit"){ //ppk
			if(unit=="" || unit=="0"){
			alert("Sila pilih \"Kementerian\" terlebih dahulu.");
			document.${formName}.socUnit.focus(); 
			return;
		}	
		punit = "&ID_PEJABAT="+unit;
	
	}else if(laporan=="daerah"){ //ppk
		if(daerahbaru==""){
			alert("Sila pilih \"Daerah\" terlebih dahulu.");
			document.${formName}.socDaerahBaru.focus(); 
			return;
		}	
		pdaerah = "&ID="+daerahbaru;
		punit += "&ID_KEMENTERIAN=0&ID_AGENSI=0"+"&bulan=1";
		
	}else if(laporan=="kementerian"){
		if(unit=="" || unit=="0"){
			alert("Sila pilih \"Kementerian\" terlebih dahulu.");
			document.${formName}.socUnit.focus(); 
			return;
		}	
		punit = "&ID_KEMENTERIAN="+unit+"&ID_AGENSI=0"+"&bulan=1";
		
		
	}else if(laporan=="kemnegeri"){
		if(unit=="" || unit=="0"){
			alert("Sila pilih \"Kementerian\" terlebih dahulu.");
			document.${formName}.socUnit.focus(); 
			return;
		}	
		punit = "&ID_KEMENTERIAN="+unit+"&ID_AGENSI=0";
		if(negeri=="0"){
			alert("Sila pilih \"Negeri\" terlebih dahulu.");
			document.${formName}.socNegeri.focus(); 
			return;
		}	
		pnegeri = "&ID_NEGERI="+negeri+"&bulan=1";
		
		
	}else if(laporan=="kemdaerah"){
		if(unit=="" || unit=="0"){
			alert("Sila pilih \"Kementerian\" terlebih dahulu.");
			document.${formName}.socUnit.focus(); 
			return;
		}	
		punit = "&ID_KEMENTERIAN="+unit+"&ID_AGENSI=0";
		if(daerahbaru==""){
			alert("Sila pilih \"Daerah\" terlebih dahulu.");
			document.${formName}.socDaerahBaru.focus(); 
			return;
		}	
		pdaerah = "&ID="+daerahbaru;	
		pnegeri = "&ID_NEGERI="+negeri+"&bulan=1";
		
	}else if(laporan=="ageneg"){
		if(daerah==""){
			alert("Sila pilih \"Agensi\" terlebih dahulu.");
			document.${formName}.socDaerah.focus(); 
			return;
		}
		punit = "&ID_KEMENTERIAN=0&ID_AGENSI="+daerah;
		if(negeri=="0"){
			alert("Sila pilih \"Negeri\" terlebih dahulu.");
			document.${formName}.socNegeri.focus(); 
			return;
		}	
		pnegeri = "&ID_NEGERI="+negeri+"&bulan=1";			
		
	}else if(laporan=="agensi"){
		if(daerah==""){
			alert("Sila pilih \"Agensi\" terlebih dahulu.");
			document.${formName}.socDaerah.focus(); 
			return;
		}	
		pdaerah += "&ID_KEMENTERIAN="+unit+"&ID_AGENSI="+daerah;
		pnegeri ="&ID_NEGERI="+negeri+"&bulan=1";
		
	// Dikemaskini pada 2012/11/21
	}else if(laporan=="xurusan"){
		pdaerah += "&ID_KEMENTERIAN=0&ID_AGENSI=0";
		pnegeri ="&ID_NEGERI=0&bulan=1";
		param = "ID_URUSAN="+document.${formName}.socJenisTanah.value;

	// Mula Dibuat pada 2012/11/21
	}else if(laporan=="urusan"){
		pdaerah += "&ID_KEMENTERIAN=0&ID_AGENSI=0";
		pnegeri ="&ID_NEGERI=0&bulan=1";
		if(document.${formName}.socJenisTanah.value == ""){
			alert("Sila pilih \"Urusan\" terlebih dahulu.");
			return;
		}	
		param += "&ID_URUSAN="+document.${formName}.socJenisTanah.value;
		//if(daerahbaru==""){
		//	alert("Sila pilih \"Daerah\" terlebih dahulu.");
		//	document.${formName}.socDaerahBaru.focus(); 
		//	return;
		//}	
		//pdaerah = "&ID="+daerahbaru;
	}else if(laporan =="urusdae"){
		pdaerah += "&ID_KEMENTERIAN=0&ID_AGENSI=0";
		if(document.${formName}.socJenisTanah.value == ""){
			alert("Sila pilih \"Urusan\" terlebih dahulu.");
			return;
		}	
		param += "&ID_URUSAN="+document.${formName}.socJenisTanah.value;
		if(negeri=="0"){
			alert("Sila pilih \"Negeri\" terlebih dahulu.");
			document.${formName}.socNegeri.focus(); 
			return;
		}	
		pnegeri = "&ID_KEMENTERIAN=0&ID_AGENSI=0&ID_NEGERI="+negeri;
		var idkpts = document.${formName}.pilihkeputusan.value;
		var status = "";
		if(daerahbaru == "" && idkpts == "0"){
			ptem += "Semua";			
		}else if(daerahbaru == "" && idkpts != "0"){
			ptem += "StatusSemua";
			status ="&STATUS="+idkpts;		

		}else if(daerahbaru != "" && idkpts != "0"){
			ptem += "Status";
			status ="&STATUS="+idkpts;		

		}
		//pnegeri ="&ID_NEGERI="+negeri+status;
		pdaerah = "&ID="+daerahbaru+status;

		if(document.${formName}.txdMula.value ==""){
			pdaerah +="&bulan=00";
		}else{
			akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
	  		akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
	  		akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
			var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
	  		mula_bulan = document.${formName}.txdMula.value.substring(3,5);
	 		mula_hari = document.${formName}.txdMula.value.substring(0,2);
	  		mula_tahun = document.${formName}.txdMula.value.substring(6,10);
			var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;
		
			var mula = Date.parse(mulatemp);
			var akhir = Date.parse(akhirtemp);
			var tarikhsemasa = new Date();
		  
		  	if(akhir<mula){
		    	alert("Sila pastikan Tarikh Akhir tidak melebihi dari Tarikh Mula.");
		    	return;
		  	}
		  	if(akhir>tarikhsemasa){
		    	alert("Sila pastikan Tarikh Akhir tidak melebihi dari Tarikh Semasa.");
		    	return;
		  	}

			if(mula_tahun=="" && akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;

			}else{
				if(semak_tarikh(document.${formName}.txdMula)==false){ 
					document.${formName}.txdMula.focus(); 
					return;

				}					
				semakTarikhHariIni(document.${formName}.txdMula)
				
			}
			pbulanmula = "&BULANTAHUN="+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;			
			ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+akhir_tahun;
		  	
		}
		
	}else if(laporan =="urusneg"){
		pdaerah += "&ID_KEMENTERIAN=0&ID_AGENSI=0";
		if(document.${formName}.socJenisTanah.value == ""){
			alert("Sila pilih \"Urusan\" terlebih dahulu.");
			return;
		}	
		param += "&ID_URUSAN="+document.${formName}.socJenisTanah.value;
		var idkpts = document.${formName}.pilihkeputusan.value;
		var status = "";
		if(negeri == "0" && idkpts == "0"){
			ptem += "Semua";			
		}else if(negeri == "0" && idkpts != "0"){
			ptem += "StatusSemua";
			status ="&STATUS="+idkpts;		

		}else if(negeri != "0" && idkpts != "0"){
			ptem += "Status";
			status ="&STATUS="+idkpts;		

		}
		pnegeri ="&ID_NEGERI="+negeri+status;

		if(document.${formName}.txdMula.value ==""){
			pnegeri +="&bulan=00";

		}else{
			akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
	  		akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
	  		akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
			var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
	  		mula_bulan = document.${formName}.txdMula.value.substring(3,5);
	 		mula_hari = document.${formName}.txdMula.value.substring(0,2);
	  		mula_tahun = document.${formName}.txdMula.value.substring(6,10);
			var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;
		
			var mula = Date.parse(mulatemp);
			var akhir = Date.parse(akhirtemp);
			var tarikhsemasa = new Date();
		  
		  	if(akhir<mula){
		    	alert("Sila pastikan Tarikh Akhir tidak melebihi dari Tarikh Mula.");
		    	return;
		  	}
		  	if(akhir>tarikhsemasa){
		    	alert("Sila pastikan Tarikh Akhir tidak melebihi dari Tarikh Semasa.");
		    	return;
		  	}

			if(mula_tahun=="" && akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdMula)==false){ 
					document.${formName}.txdMula.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdMula)
			}
			pbulanmula = "&BULANTAHUN="+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			//pbulantamat = "&BULANTAHUNTMT="+akhir_bulan+"/"+akhir_tahun;
			pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
				
			//ptahun = "&TAHUN="+mula_tahun;
			ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+akhir_tahun;
		  	

		}
		//pnegeri +="&bulan=1";		
	// Akhir Dibuat pada 2012/11/21
	// Mula Dibuat pada 2012/12/04
	}else if(laporan=="urusnegkem"){
		if(document.${formName}.socJenisTanah.value == ""){
			alert("Sila pilih \"Urusan\" terlebih dahulu.");
			return;
		}	
		param += "&ID_URUSAN="+document.${formName}.socJenisTanah.value;

		if(negeri=="0"){
			alert("Sila pilih \"Negeri\" terlebih dahulu.");
			document.${formName}.socNegeri.focus(); 
			return;
		}	
		pnegeri = "&ID_NEGERI="+negeri;
		
		var idkpts = document.${formName}.pilihkeputusan.value;
		var status = "";
		if((unit=="" || unit=="0") && idkpts == "0"){
			//alert("Sila pilih \"Kementerian\" terlebih dahulu.");
			//document.${formName}.socUnit.focus(); 
			//return;
			ptem += "Semua";			

		}else if((unit=="" || unit=="0")  && idkpts != "0"){
			ptem += "StatusSemua";
			status ="&STATUS="+idkpts;		

		}else if((unit!="" || unit!="0")  && idkpts != "0"){
			ptem += "Status";
			status ="&STATUS="+idkpts;		

		}
		punit = "&ID_KEMENTERIAN="+unit+"&ID_AGENSI=0"+status;

		if(document.${formName}.txdMula.value ==""){
			pnegeri +="&bulan=00";

		}else{
			akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
	  		akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
	  		akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
			var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
	  		mula_bulan = document.${formName}.txdMula.value.substring(3,5);
	 		mula_hari = document.${formName}.txdMula.value.substring(0,2);
	  		mula_tahun = document.${formName}.txdMula.value.substring(6,10);
			var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;
		
			var mula = Date.parse(mulatemp);
			var akhir = Date.parse(akhirtemp);
			var tarikhsemasa = new Date();
		  
		  	if(akhir<mula){
		    	alert("Sila pastikan Tarikh Akhir tidak melebihi dari Tarikh Mula.");
		    	return;
		  	}
		  	if(akhir>tarikhsemasa){
		    	alert("Sila pastikan Tarikh Akhir tidak melebihi dari Tarikh Semasa.");
		    	return;
		  	}

			if(mula_tahun=="" && akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdMula)==false){ 
					document.${formName}.txdMula.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdMula)
			}
			pbulanmula = "&BULANTAHUN="+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
			ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+akhir_tahun;
		  	
		}
		
	// Tamat, Mula Dibuat pada 2012/12/04

	// 15/5/2013
	}else if(laporan=="urusagen"){
		if(document.${formName}.socJenisTanah.value == ""){
			alert("Sila pilih \"Urusan\" terlebih dahulu.");
			return;
		}	
		param += "&ID_URUSAN="+document.${formName}.socJenisTanah.value;

		if(daerah==""){
			alert("Sila pilih \"Agensi\" terlebih dahulu.");
			document.${formName}.socDaerah.focus(); 
			return;
		}	
		pdaerah += "&ID_KEMENTERIAN="+unit+"&ID_AGENSI="+daerah;
		pnegeri ="&ID_NEGERI="+negeri;

		var idkpts = document.${formName}.pilihkeputusan.value;
		var status = "";
		if((unit=="" || unit=="0") && idkpts == "0"){
			//ptem += "Semua";			

		}else if((unit=="" || unit=="0")  && idkpts != "0"){
			//ptem += "StatusSemua";
			status ="&STATUS="+idkpts;		

		}else if((unit!="" || unit!="0")  && idkpts != "0"){
			ptem += "Status";
			status ="&STATUS="+idkpts;		

		}
		punit = status;
		
		if(document.${formName}.txdMula.value ==""){
			pnegeri +="&bulan=00";

		}else{
			akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
	  		akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
	  		akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
			var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
	  		mula_bulan = document.${formName}.txdMula.value.substring(3,5);
	 		mula_hari = document.${formName}.txdMula.value.substring(0,2);
	  		mula_tahun = document.${formName}.txdMula.value.substring(6,10);
			var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;
		
			var mula = Date.parse(mulatemp);
			var akhir = Date.parse(akhirtemp);
			var tarikhsemasa = new Date();
		  
		  	if(akhir<mula){
		    	alert("Sila pastikan Tarikh Akhir tidak melebihi dari Tarikh Mula.");
		    	return;
		  	}
		  	if(akhir>tarikhsemasa){
		    	alert("Sila pastikan Tarikh Akhir tidak melebihi dari Tarikh Semasa.");
		    	return;
		  	}

			if(mula_tahun=="" && akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdMula)==false){ 
					document.${formName}.txdMula.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdMula)
			}
			pbulanmula = "&BULANTAHUN="+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
			ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+akhir_tahun;
		  	
		}
		
	}else if(laporan=="urusnegagen"){
			if(document.${formName}.socJenisTanah.value == ""){
				alert("Sila pilih \"Urusan\" terlebih dahulu.");
				return;
			}	
			param += "&ID_URUSAN="+document.${formName}.socJenisTanah.value;

			if(daerah==""){
				alert("Sila pilih \"Agensi\" terlebih dahulu.");
				document.${formName}.socDaerah.focus(); 
				return;
			}	
			if(negeri=="0"){
				alert("Sila pilih \"Negeri\" terlebih dahulu.");
				document.${formName}.socNegeri.focus(); 
				return;
			}	
			pdaerah += "&ID_KEMENTERIAN="+unit+"&ID_AGENSI="+daerah;
			pnegeri ="&ID_NEGERI="+negeri;

			var idkpts = document.${formName}.pilihkeputusan.value;
			var status = "";
			if((unit=="" || unit=="0") && idkpts == "0"){
				//ptem += "Semua";			

			}else if((unit=="" || unit=="0")  && idkpts != "0"){
				//ptem += "StatusSemua";
				status ="&STATUS="+idkpts;		

			}else if((unit!="" || unit!="0")  && idkpts != "0"){
				ptem += "Status";
				status ="&STATUS="+idkpts;		

			}
			punit = status;
			
			if(document.${formName}.txdMula.value ==""){
				pnegeri +="&bulan=00";

			}else{
				akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
		  		akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
		  		akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
				var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
		  		mula_bulan = document.${formName}.txdMula.value.substring(3,5);
		 		mula_hari = document.${formName}.txdMula.value.substring(0,2);
		  		mula_tahun = document.${formName}.txdMula.value.substring(6,10);
				var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;
			
				var mula = Date.parse(mulatemp);
				var akhir = Date.parse(akhirtemp);
				var tarikhsemasa = new Date();
			  
			  	if(akhir<mula){
			    	alert("Sila pastikan Tarikh Akhir tidak melebihi dari Tarikh Mula.");
			    	return;
			  	}
			  	if(akhir>tarikhsemasa){
			    	alert("Sila pastikan Tarikh Akhir tidak melebihi dari Tarikh Semasa.");
			    	return;
			  	}

				if(mula_tahun=="" && akhir_tahun==""){
					alert("Sila pilih \"Tarikh\" terlebih dahulu.");
					document.${formName}.txdMula.focus(); 
					return;
				}else{
					if(semak_tarikh(document.${formName}.txdMula)==false){ 
						document.${formName}.txdMula.focus(); 
						return;
					}
						
					semakTarikhHariIni(document.${formName}.txdMula)
				}
				pbulanmula = "&BULANTAHUN="+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
				pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
				ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+akhir_tahun;

		}
	
	}else if(laporan=="negs"){
			var idkpts = document.${formName}.pilihkeputusan.value;
			if(negeri=="0"){
				alert("Sila pilih \"Negeri\" terlebih dahulu.");
				document.${formName}.socNegeri.focus(); 
				return;
			}	
			pnegeri = "&ID_KEMENTERIAN=0&ID_AGENSI=0&ID_NEGERI="+negeri;
			if(idkpts == ""){
				alert("Sila pilih \"Status\" terlebih dahulu.");
				document.${formName}.pilihkeputusan.focus(); 
				return;
			}	
			pnegeri +="&STATUS="+idkpts+"&bulan=1";
	
	}else{
	
		akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
  		akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
  		akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
		var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
  		mula_bulan = document.${formName}.txdMula.value.substring(3,5);
 		mula_hari = document.${formName}.txdMula.value.substring(0,2);
  		mula_tahun = document.${formName}.txdMula.value.substring(6,10);
		var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;
	
		var mula = Date.parse(mulatemp);
		var akhir = Date.parse(akhirtemp);
		var tarikhsemasa = new Date();
	  
	  	if(akhir<mula){
	    	alert("Sila pastikan Tarikh Akhir tidak melebihi dari Tarikh Mula.");
	    	return;
	  	}
	  	if(akhir>tarikhsemasa){
	    	alert("Sila pastikan Tarikh Akhir tidak melebihi dari Tarikh Semasa.");
	    	return;
	  	}
		
		if(laporan=="negeritahun"){
			if(negeri=="0"){
				alert("Sila pilih \"Negeri\" terlebih dahulu.");
				document.${formName}.socNegeri.focus(); 
				return;
			}	
			pnegeri = "&ID_NEGERI="+negeri;
			if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;		
			}else{
				if(semak_tarikh(document.${formName}.txdMula)==false){ 
					document.${formName}.txdMula.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdMula)
			}	
			ptahun = "&TAHUN="+mula_tahun;
		}
		if(laporan=="negeribulan"){
			if(negeri=="0"){
				alert("Sila pilih \"Negeri\" terlebih dahulu.");
				document.${formName}.socNegeri.focus(); 
				return;
			}	
			pnegeri = "&ID_NEGERI="+negeri;
			if(mula_tahun=="" && akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdMula)==false){ 
					document.${formName}.txdMula.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdMula)
			}
			pbulanmula = "&BULANTAHUN="+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_bulan+"/"+akhir_tahun;
				
			ptahun = "&TAHUN="+mula_tahun;
		}
		if(laporan=="negeriselang"){
			if(negeri=="0"){
				alert("Sila pilih \"Negeri\" terlebih dahulu.");
				document.${formName}.socNegeri.focus(); 
				return;
			}	
			pnegeri = "&ID_NEGERI="+negeri;
			if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdMula)==false){ 
					document.${formName}.txdMula.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdMula)
			}
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdAkhir)==false){ 
					document.${formName}.txdAkhir.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdAkhir)
			}
			
			pbulanmula = "&BULANTAHUN="+mula_hari+"/"+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
				
			ptahun = "&TAHUN="+mula_tahun;
		}
		/** By Unit*/
		if(laporan=="unittahun"){
			if(unit==""|| unit=="0"){
				alert("Sila pilih \"Unit\" terlebih dahulu.");
				document.${formName}.socUnit.focus(); 
				return;
			}	
			punit = "&ID_PEJABAT="+unit;
			if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdMula)==false){ 
					document.${formName}.txdMula.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdMula)
			}	
			ptahun = "&TAHUN="+mula_tahun;
		}
		if(laporan=="unitbulan"){
			if(unit==""|| unit=="0"){
				alert("Sila pilih \"Unit\" terlebih dahulu.");
				document.${formName}.socUnit.focus(); 
				return;
			}	
			punit = "&ID_PEJABAT="+unit;
			if(mula_tahun=="" && akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdMula)==false){ 
					document.${formName}.txdMula.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdMula)
			}
			pbulanmula = "&BULANTAHUN="+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_bulan+"/"+akhir_tahun;
				
			ptahun = "&TAHUN="+mula_tahun;
		}
		if(laporan=="unitselang"){
			if(unit==""|| unit=="0"){
				alert("Sila pilih \"Unit\" terlebih dahulu.");
				document.${formName}.socUnit.focus(); 
				return;
			}	
			punit = "&ID_PEJABAT="+unit;
			if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdMula)==false){ 
					document.${formName}.txdMula.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdMula)
			}
			
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdAkhir)==false){ 
					document.${formName}.txdAkhir.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdAkhir)
			}
			
			pbulanmula = "&BULANTAHUN="+mula_hari+"/"+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
				
			ptahun = "&TAHUN="+mula_tahun;
		}
		/** By Daerah*/
		if(laporan=="daerahtahun"){
			//if(daerah==""){
			if(document.${formName}.socDaerahBaru.value==""){
				alert("Sila pilih \"Daerah\" terlebih dahulu.");
				//document.${formName}.socDaerah.focus(); 
				document.${formName}.socDaerahBaru.focus(); 
				return;
			}	
			pdaerah = "&ID_KEMENTERIAN=0&ID_AGENSI=0&ID="+daerahbaru;
			if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdMula)==false){ 
					document.${formName}.txdMula.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdMula)
			}	
			//ptahun = "&TAHUN="+mula_tahun;
			ptahun = "&ID_KEMENTERIAN=0&ID_AGENSI=0&TAHUN="+mula_tahun+"&bulan="+mula_bulan;
			
		}
		if(laporan=="daerahbulan"){
			if(daerah==""){
				alert("Sila pilih \"Daerah\" terlebih dahulu.");
				document.${formName}.socDaerah.focus(); 
				return;
			}	
			pdaerah = "&ID="+daerah;
			if(mula_tahun=="" && akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdMula)==false){ 
					document.${formName}.txdMula.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdMula)
			}
			pbulanmula = "&BULANTAHUN="+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_bulan+"/"+akhir_tahun;
				
			ptahun = "&TAHUN="+mula_tahun;
		}
		if(laporan=="daerahselang"){
			if(daerah==""){
				alert("Sila pilih \"Daerah\" terlebih dahulu.");
				document.${formName}.socDaerah.focus(); 
				return;
			}	
			pdaerah = "&ID="+daerah;
			if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdMula)==false){ 
					document.${formName}.txdMula.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdMula)
			}
			
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdAkhir)==false){ 
					document.${formName}.txdAkhir.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdAkhir)
			}
			
			pbulanmula = "&BULANTAHUN="+mula_hari+"/"+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
				
			ptahun = "&TAHUN="+mula_tahun;
		}	
		// KEM SELANG MASA
		if(laporan=="kemselangthn"){
			if(unit=="" || unit=="0"){
				alert("Sila pilih \"Kementerian\" terlebih dahulu.");
				document.${formName}.socUnit.focus(); 
				return;
			}	
			punit = "&ID_KEMENTERIAN="+unit+"&ID_AGENSI=0";

			if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdMula)==false){ 
					document.${formName}.txdMula.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdMula)
			}
			
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdAkhir)==false){ 
					document.${formName}.txdAkhir.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdAkhir)
			}
			
			pbulanmula = "&BULANTAHUN="+mula_hari+"/"+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
				
			ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+akhir_tahun;
		}	
		else if(laporan=="agenselangthn"){
			if(daerah==""){
				alert("Sila pilih \"Agensi\" terlebih dahulu.");
				document.${formName}.socDaerah.focus(); 
				return;
			}	
			pdaerah += "&ID_KEMENTERIAN="+unit+"&ID_AGENSI="+daerah;
			pnegeri ="&ID_NEGERI="+negeri;
			if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdMula)==false){ 
					document.${formName}.txdMula.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdMula)
			}
			
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdAkhir)==false){ 
					document.${formName}.txdAkhir.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdAkhir)
			}
			
			pbulanmula = "&BULANTAHUN="+mula_hari+"/"+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;				
			ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+akhir_tahun;		
		
		}else if(laporan=="negselangthn"){
			if(negeri=="0"){
				alert("Sila pilih \"Negeri\" terlebih dahulu.");
				document.${formName}.socNegeri.focus(); 
				return;
			}	
			pnegeri = "&ID_KEMENTERIAN=0&ID_AGENSI=0&ID_NEGERI="+negeri;
			if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdMula)==false){ 
					document.${formName}.txdMula.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdMula)
			}
			
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdAkhir)==false){ 
					document.${formName}.txdAkhir.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdAkhir)
			}
			
			pbulanmula = "&BULANTAHUN="+mula_hari+"/"+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
				
			ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+akhir_tahun;
				
		}else if(laporan=="daeselangthn"){
			if(daerahbaru==""){
				alert("Sila pilih \"Daerah\" terlebih dahulu.");
				document.${formName}.socDaerahBaru.focus(); 
				return;
			}	
			pdaerah = "&ID="+daerahbaru;
			punit += "&ID_KEMENTERIAN=0&ID_AGENSI=0";
						if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}
			if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdMula)==false){ 
					document.${formName}.txdMula.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdMula)
			}
			
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdAkhir)==false){ 
					document.${formName}.txdAkhir.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdAkhir)
			}
			
			pbulanmula = "&BULANTAHUN="+mula_hari+"/"+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
				
			ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+akhir_tahun;	
			
		}else if(laporan=="kemnegselangthn"){
			if(unit=="" || unit=="0"){
				alert("Sila pilih \"Kementerian\" terlebih dahulu.");
				document.${formName}.socUnit.focus(); 
				return;
			}	
			punit = "&ID_KEMENTERIAN="+unit+"&ID_AGENSI=0";
			if(negeri=="0"){
				alert("Sila pilih \"Negeri\" terlebih dahulu.");
				document.${formName}.socNegeri.focus(); 
				return;
			}	
			pnegeri = "&ID_NEGERI="+negeri;
			if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdMula)==false){ 
					document.${formName}.txdMula.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdMula)
			}
			
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdAkhir)==false){ 
					document.${formName}.txdAkhir.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdAkhir)
			}
			
			pbulanmula = "&BULANTAHUN="+mula_hari+"/"+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
				
			ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+akhir_tahun;	
		
		
		}else if(laporan=="kemdaeselangthn"){
			if(unit=="" || unit=="0"){
				alert("Sila pilih \"Kementerian\" terlebih dahulu.");
				document.${formName}.socUnit.focus(); 
				return;
			}	
			punit = "&ID_KEMENTERIAN="+unit+"&ID_AGENSI=0";
			if(daerahbaru==""){
				alert("Sila pilih \"Daerah\" terlebih dahulu.");
				document.${formName}.socDaerahBaru.focus(); 
				return;
			}	
			pdaerah = "&ID="+daerahbaru;	
			pnegeri = "&ID_NEGERI=0";
			if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdMula)==false){ 
					document.${formName}.txdMula.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdMula)
			}
			
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdAkhir)==false){ 
					document.${formName}.txdAkhir.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdAkhir)
			}
			
			pbulanmula = "&BULANTAHUN="+mula_hari+"/"+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
				
			ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+akhir_tahun;	
		
		
		}else if(laporan=="agenegselangthn"){
			if(daerah==""){
				alert("Sila pilih \"Agensi\" terlebih dahulu.");
				document.${formName}.socDaerah.focus(); 
				return;
			}	
			punit = "&ID_KEMENTERIAN=0&ID_AGENSI="+daerah;
			if(negeri=="0"){
				alert("Sila pilih \"Negeri\" terlebih dahulu.");
				document.${formName}.socNegeri.focus(); 
				return;
			}	
			pnegeri = "&ID_NEGERI="+negeri;
			if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdMula)==false){ 
					document.${formName}.txdMula.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdMula)
			}
			
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdAkhir)==false){ 
					document.${formName}.txdAkhir.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdAkhir)
			}
			
			pbulanmula = "&BULANTAHUN="+mula_hari+"/"+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
				
			ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+akhir_tahun;	
			
		} else if(laporan=="kemsslgthn"){
			var idkpts = document.${formName}.pilihkeputusan.value;
			if(unit=="" || unit=="0"){
				alert("Sila pilih \"Kementerian\" terlebih dahulu.");
				document.${formName}.socUnit.focus(); 
				return;
			}	
			punit = "&ID_KEMENTERIAN="+unit+"&ID_AGENSI=0";
			if(idkpts == ""){
				alert("Sila pilih \"Status\" terlebih dahulu.");
				document.${formName}.pilihkeputusan.focus(); 
				return;
			}	
			punit +="&STATUS="+idkpts;

			if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdMula)==false){ 
					document.${formName}.txdMula.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdMula)
			}
			
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdAkhir)==false){ 
					document.${formName}.txdAkhir.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdAkhir)
			}
			
			pbulanmula = "&BULANTAHUN="+mula_hari+"/"+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
				
			ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+akhir_tahun;
		
		}else if(laporan=="agensslgthn"){
			var idkpts = document.${formName}.pilihkeputusan.value;
			if(daerah==""){
				alert("Sila pilih \"Agensi\" terlebih dahulu.");
				document.${formName}.socDaerah.focus(); 
				return;
			}	
			pdaerah += "&ID_KEMENTERIAN="+unit+"&ID_AGENSI="+daerah;
			pnegeri ="&ID_NEGERI="+negeri;
			if(idkpts == ""){
				alert("Sila pilih \"Status\" terlebih dahulu.");
				document.${formName}.pilihkeputusan.focus(); 
				return;
			}	
			pnegeri +="&STATUS="+idkpts;
		
			if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdMula)==false){ 
					document.${formName}.txdMula.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdMula)
			}
			
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdAkhir)==false){ 
					document.${formName}.txdAkhir.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdAkhir)
			}
			
			pbulanmula = "&BULANTAHUN="+mula_hari+"/"+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;			
			ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+akhir_tahun;		
		
		
		}else if(laporan=="agenegsslgthn"){ //Agensi,Negeri, status
			var idkpts = document.${formName}.pilihkeputusan.value;
		
			if(daerah==""){
				alert("Sila pilih \"Agensi\" terlebih dahulu.");
				document.${formName}.socDaerah.focus(); 
				return;
			}	
			punit = "&ID_KEMENTERIAN=0&ID_AGENSI="+daerah;
			if(negeri=="0"){
				alert("Sila pilih \"Negeri\" terlebih dahulu.");
				document.${formName}.socNegeri.focus(); 
				return;
			}	
			pnegeri = "&ID_NEGERI="+negeri;
			if(idkpts == ""){
				alert("Sila pilih \"Status\" terlebih dahulu.");
				document.${formName}.pilihkeputusan.focus(); 
				return;
			}	
			pnegeri +="&STATUS="+idkpts;		
			if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdMula)==false){ 
					document.${formName}.txdMula.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdMula)
			}
			
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdAkhir)==false){ 
					document.${formName}.txdAkhir.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdAkhir)
			}
			
			pbulanmula = "&BULANTAHUN="+mula_hari+"/"+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
				
			ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+akhir_tahun;	

		}else if(laporan=="negsslgthn"){
			var idkpts = document.${formName}.pilihkeputusan.value;
			if(negeri=="0"){
				alert("Sila pilih \"Negeri\" terlebih dahulu.");
				document.${formName}.socNegeri.focus(); 
				return;
			}	
			pnegeri = "&ID_KEMENTERIAN=0&ID_AGENSI=0&ID_NEGERI="+negeri;
			if(idkpts == ""){
				alert("Sila pilih \"Status\" terlebih dahulu.");
				document.${formName}.pilihkeputusan.focus(); 
				return;
			}	
			pnegeri +="&STATUS="+idkpts;		

			if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdMula)==false){ 
					document.${formName}.txdMula.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdMula)
			}
			
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdAkhir)==false){ 
					document.${formName}.txdAkhir.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdAkhir)
			}
			
			pbulanmula = "&BULANTAHUN="+mula_hari+"/"+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
				
			ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+akhir_tahun;
				
		}else if(laporan=="daesslgthn"){
			var idkpts = document.${formName}.pilihkeputusan.value;
			if(daerahbaru==""){
				alert("Sila pilih \"Daerah\" terlebih dahulu.");
				document.${formName}.socDaerahBaru.focus(); 
				return;
			}	
			pdaerah = "&ID="+daerahbaru;
			punit += "&ID_KEMENTERIAN=0&ID_AGENSI=0";
						if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}
			if(idkpts == ""){
				alert("Sila pilih \"Status\" terlebih dahulu.");
				document.${formName}.pilihkeputusan.focus(); 
				return;
			}	
			punit +="&STATUS="+idkpts;		

			if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdMula)==false){ 
					document.${formName}.txdMula.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdMula)
			}
			
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdAkhir)==false){ 
					document.${formName}.txdAkhir.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdAkhir)
			}
			
			pbulanmula = "&BULANTAHUN="+mula_hari+"/"+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
				
			ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+akhir_tahun;	
			
		}else if(laporan=="kemnegsslgthn"){
			var idkpts = document.${formName}.pilihkeputusan.value;
			if(unit=="" || unit=="0"){
				alert("Sila pilih \"Kementerian\" terlebih dahulu.");
				document.${formName}.socUnit.focus(); 
				return;
			}	
			punit = "&ID_KEMENTERIAN="+unit+"&ID_AGENSI=0";
			if(negeri=="0"){
				alert("Sila pilih \"Negeri\" terlebih dahulu.");
				document.${formName}.socNegeri.focus(); 
				return;
			}	
			pnegeri = "&ID_NEGERI="+negeri;
			if(idkpts == ""){
				alert("Sila pilih \"Status\" terlebih dahulu.");
				document.${formName}.pilihkeputusan.focus(); 
				return;
			}	
			pnegeri +="&STATUS="+idkpts;		
			
			if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdMula)==false){ 
					document.${formName}.txdMula.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdMula)
			}
			
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdAkhir)==false){ 
					document.${formName}.txdAkhir.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdAkhir)
			}
			
			pbulanmula = "&BULANTAHUN="+mula_hari+"/"+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
				
			ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+akhir_tahun;	
		
		}else if(laporan=="urusanselangthn"){
			punit = "&ID_KEMENTERIAN=0&ID_AGENSI=0";
			pnegeri = "&ID_NEGERI=0";
			if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdMula)==false){ 
					document.${formName}.txdMula.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdMula)
			}
			
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdAkhir)==false){ 
					document.${formName}.txdAkhir.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdAkhir)
			}
			
			pbulanmula = "&BULANTAHUN="+mula_hari+"/"+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
				
			ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+akhir_tahun;	
		
		
		}else if(laporan=="ursselangbuta"){
			pdaerah = "&ID=0";
			if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdMula)==false){ 
					document.${formName}.txdMula.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdMula)
			}
			
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdAkhir)==false){ 
					document.${formName}.txdAkhir.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdAkhir)
			}
			
			//pbulanmula = "&BULANTAHUN="+mula_hari+"/"+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			//pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
			pbulanmula = "&BULANTAHUN="+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
				
			ptahun = "&ID_KEMENTERIAN=0&ID_AGENSI=0&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+akhir_tahun;

		}
		
		if(laporan=="urusantahun" || laporan=="urusanbulan"){
			if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}else{
				if(semak_tarikh(document.${formName}.txdMula)==false){ 
					document.${formName}.txdMula.focus(); 
					return;
				}
					
				semakTarikhHariIni(document.${formName}.txdMula)
			}
				
			//if(tem == "LaporanPrestasiGadaian"){
			if(tem == "LaporanGadaianPrestasiSetiapBulan"){
				ptem += mula_bulan;			
			}
			ptahun = "&ID_KEMENTERIAN=0&ID_AGENSI=0&TAHUN="+mula_tahun+"&bulan="+mula_bulan;
		}
	}
	
	var url = "../servlet/"+urli+"?"+param+pnegeri+ptahun+ptem+pbulanmula+pbulantamat+punit+pdaerah;
	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
}

function doChangeNegeri() {
	if(document.${formName}.socNegeri.value=="-1")
		return;
	doAjaxCall${formName}("PilihNegeri");
}
function doChangeUnit() {
	if(document.${formName}.socUnit.value=="0" || document.${formName}.socUnit.value=="")
		return;
	doAjaxCall${formName}("PilihUnit");
}

	function senaraiLaporan(){   
		/*field_ = document.${formName}.socJenisTanahtemp;
		for (i = 0; i < field_.length; i++){
			if(field_[i].checked==true){
				document.${formName}.socJenisTanah.value = field_[i].value;
			}
		}*/
		document.${formName}.socjenislaporan.value = document.${formName}.soclaporantemp.value;

		//
		doAjaxCall${formName}("senarailaporan");
		
	}
	
	function semakTarikhHariIni(txdTarikhSuratKJP) {
		var today = new Date();
		dari_bulan	= txdTarikhSuratKJP.value.substring(3,5);
		dari_hari 	= txdTarikhSuratKJP.value.substring(0,2);
		dari_tahun 	= txdTarikhSuratKJP.value.substring(6,10);
		var daritemp = dari_bulan+"/"+dari_hari+"/"+dari_tahun;
		
		var myDate = Date.parse(daritemp);	
		if (myDate>today) {
	  		alert("Tarikh yang dimasukkan tidak boleh melebihi Tarikh semasa");
	  		txdTarikhSuratKJP.value="";	 
	  		txdTarikhSuratKJP.focus();	 	  		
	 		return;
	 		
	 	}
	
	}
	
	function semak_tarikh(field){	
	
	/*
	 * utk format : ddmmyy
	 * 				ddmmyyyy
	 * 				ddXmmXyy   
	 * 				ddXmmXyyyy
	 * 
	 * 				( X = sign selain 0-9 )
	 */
	var returnValue = true;
	var checkstr = "0123456789";
	var DateField = field;
	var Datevalue = "";
	var DateTemp = "";
	var seperator = "/";
	var day;
	var month;
	var year;
	var leap = 0;
	var err = 0;
	var i;
	   err = 0;
	   DateValue = DateField.value;
	   /* Delete all chars except 0..9 */
	   for (i = 0; i < DateValue.length; i++) {
		  if (checkstr.indexOf(DateValue.substr(i,1)) >= 0) {
		     DateTemp = DateTemp + DateValue.substr(i,1);
		  }
	   }
	   DateValue = DateTemp;
	   /* Always change date to 8 digits - string*/
	   /* if year is entered as 2-digit / always assume 20xx */
	   if (DateValue.length == 6) {
	      DateValue = DateValue.substr(0,4) + '20' + DateValue.substr(4,2); }
	   if (DateValue.length != 8) {
	      err = 19;}
	   /* year is wrong if year = 0000 */
	   year = DateValue.substr(4,4);
	   if (year == 0) {
	      err = 20;
	   }
	   /* Validation of month*/
	   month = DateValue.substr(2,2);
	   if ((month < 1) || (month > 12)) {
	      err = 21;
	   }
	   /* Validation of day*/
	   day = DateValue.substr(0,2);
	   if (day < 1) {
	     err = 22;
	   }
	   /* Validation leap-year february / day */
	   if ((year % 4 == 0) || (year % 100 == 0) || (year % 400 == 0)) {
	      leap = 1;
	   }
	   if ((month == 2) && (leap == 1) && (day > 29)) {
	      err = 23;
	   }
	   if ((month == 2) && (leap != 1) && (day > 28)) {
	      err = 24;
	   }
	   /* Validation of other months */
	   if ((day > 31) && ((month == "01") || (month == "03") || (month == "05") || (month == "07") || (month == "08") || (month == "10") || (month == "12"))) {
	      err = 25;
	   }
	   if ((day > 30) && ((month == "04") || (month == "06") || (month == "09") || (month == "11"))) {
	      err = 26;
	   }
	   /* if 00 ist entered, no error, deleting the entry */
	   if ((day == 0) && (month == 0) && (year == 00)) {
	      err = 0; day = ""; month = ""; year = ""; seperator = "";
	   }
	   /* if no error, write the completed date to Input-Field (e.g. 13.12.2001) */
	   if (err == 0) {
	      DateField.value = day + seperator + month + seperator + year;
	   }
	   /* Error-message if err != 0 */
	   else {
	   	alert("Sila masukkan tarikh dengan betul");
	   	returnValue = false;
	   }
	   return returnValue;
	}
</script>


