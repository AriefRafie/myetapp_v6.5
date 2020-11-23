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
<table style="width:99%;">
<!-- 	<tr>
	<td>&nbsp;</td>
	</tr> -->
	<tr>
		<td> 
			<fieldset><!-- <legend>CARIAN</legend> -->
			<br>
			<table style="width:99%;align:center" >
				<tr>
					<td> 
						<fieldset><legend>CARIAN</legend>
						<table border="0" align="center" width="60%">
							<!-- Dibuat Mohamad Rosli 27/10/2020 -->
														<tr> 
					        <td scope="row" align="left">Jenis Tanah</td>
					        	<td>:&nbsp;</td>				        	
					       		<td>
					       			<input type="radio" name="sortanah_" Onclick="doChangeTanah()" value="1" $checkMilik $sordisabledM >&nbsp;TANAH MILIK&nbsp;&nbsp;
					       			<input type="radio" name="sortanah_" Onclick="doChangeTanah()" value="2" $checkRizab $sordisabledR >&nbsp;TANAH RIZAB&nbsp;&nbsp;
					       			<!-- <input type="radio" name="sortanah_" Onclick="doChangeTanah()" value="3" $checkMR $sordisabledMR >&nbsp;TANAH MILIK & RIZAB -->
					       		</td>
					      	</tr>	
							<tr>
								<td scope="row" align="left">&nbsp;Perolehan</td>
								<td>:&nbsp;</td>
								<td>$!selectSuburusan</td>
							</tr>
							<tr>
								<td scope="row" align="left">&nbsp;Negeri</td>
								<td>:&nbsp;</td>
								<td>$selectNegeri</td>
							</tr>
							<tr>
								<td scope="row" align="left">&nbsp;Daerah</td>
								<td>:&nbsp;</td>
								<td>$selectDaerahBaru</td>
							</tr>
							<tr>
								<td scope="row" align="left">&nbsp;Kementerian</td>
								<td>:&nbsp;</td>
								<td>$selectKementerian</td>
							</tr>
							<tr>
								<td scope="row" align="left">&nbsp;Agensi</td>
								<td>:&nbsp;</td>
								<td>$selectAgensi</td>
							</tr>
							<tr> 
					        	<td scope="row" align="left">Bulan/Tahun/Tempoh</td>
					        	<td>:&nbsp;</td>				        	
					       		<td>
					       			<input type="radio" name="sorTempoh" Onclick="doChangeTempoh()" value="1" $checkBulan $sordisabledA >&nbsp;Bulan&nbsp;&nbsp;
					       			<input type="radio" name="sorTempoh" Onclick="doChangeTempoh()" value="2" $checkTahun $sordisabledB >&nbsp;Tahun&nbsp;&nbsp;
					       			<input type="radio" name="sorTempoh" Onclick="doChangeTempoh()" value="3" $checkTempoh $sordisabledC >&nbsp;Tempoh
					       		</td>
					      	</tr>	
					      	
					    #if($!checkBulan != "")					      	
							<tr>
								<td scope="row" align="left">&nbsp;Bulan</td>
								<td>:&nbsp;</td>
								<td>$!socTarikhMula</td>
							</tr>						
						#end
						
						#if($!checkBulan != "" || $!checkTahun != "")					      	
							<tr>
								<td scope="row" align="left">&nbsp;Tahun</td>
								<td>:&nbsp;</td>
								<td>$!socTahunMula</td>
							</tr>							
						#end
						
					    #if($!checkTempoh != "")	
					    	<tr> 
						        <td scope="row" align="left">&nbsp;Bulan</td>
						        <td>:&nbsp;</td>
						        <td>
						       		<label>$!socTarikhMula</label>
						        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sehingga&nbsp;&nbsp;&nbsp;:&nbsp;
						     		<label>$!socTarikhTamat</label>
						 
					 			</td>
			      			</tr> 		
			      			<tr> 
						        <td scope="row" align="left">&nbsp;Tahun</td>
						        <td>:&nbsp;</td>
						        <td>
						       		<label>$!socTahunMula</label>
						        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sehingga&nbsp;&nbsp;&nbsp;:&nbsp;
						     		<label>$!socTahunTamat</label>
						 
					 			</td>
			      			</tr> 		      								
				<!-- 			<tr> 
						        <td scope="row" align="left">&nbsp;Tarikh Mula</td>
						        <td>:&nbsp;</td>
						        <td>
						       		<label>
						      			<input name="txdMula" type="text" id="txdMula" value="" size="10" onblur="check_date(this);semakTarikhHariIni(document.${formName}.txdMula);" />
						        	</label>
						        	<a href="javascript:displayDatePicker('txdMula',false,'dmy');">
						        		<img border="0" src="../img/calendar.gif"/>
								 		<span class="pautanms" class="stylefont">dd/mm/yyyy</span> 
				      				</a>
						        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sehingga&nbsp;&nbsp;&nbsp;:&nbsp;
						     		<label>
						      			<input name="txdAkhir" type="text" id="txdAkhir" value="" size="10" onblur="check_date(this);semakTarikhHariIni(document.${formName}.txdAkhir);"/>
						        	</label>
						        	<a href="javascript:displayDatePicker('txdAkhir',false,'dmy');">
						        		<img border="0" src="../img/calendar.gif"/>
								 		<span class="pautanms">dd/mm/yyyy</span> 
						        	</a>
					 			</td>
			      			</tr> --> 
					#end
						
						</table>
						</fieldset>
					</td>
				</tr>	
				
				<tr>
						<td> 
							<fieldset><legend>PILIHAN LAPORAN</legend>
								<table border="0" align="center" width="100%">
								     <tr> 
								        <td width="28%" height="24" scope="row" align="right">Sila Pilih&nbsp;&nbsp;</td>
								        <td width="72%"><input type="radio" $!checkA name="sorLaporan_" value="1" 
								        	onClick="doChangeKementerian2()" >&nbsp;Laporan Mengikut Kementerian</td>
									</tr>
<!-- 								    <tr> 
								        <td width="28%" height="24" scope="row" align="right">&nbsp;&nbsp;</td>
								        <td width="72%"><input type="radio" $!checkD name="sorLaporan_" value="4" 
								        	onClick="doChangeAgensi02()" >&nbsp;Laporan Mengikut Agensi</td>
									</tr>	 -->								
									<tr> 
										<td scope="row" align="right">&nbsp;</td>
									 	<td ><input type="radio" $!checkB name="sorLaporan_" value="2" 
									 	 	onClick="doChangeNegeri02()" >&nbsp;Laporan Mengikut Negeri</td>
									</tr>
							<!-- 		<tr> 
										<td scope="row" align="right">&nbsp;</td>
									  	<td ><input type="radio" $!checkC name="sorLaporan_" value="3" 
									  		onClick="doChangeSelect02()" >&nbsp;Laporan Lain-lain</td>
									</tr> -->
								</table>
							</fieldset>	
						</td>
				</tr>	
				
				<tr> 
			        <td scope="row" align="left" colspan=""></td>
			   	</tr>
				
				<tr> 
			        <td align="center">	          			
			        <input class="stylobutton100" name="cmdcetak" value="Cetak" id="cmdcetak" type="button" onClick="openLaporan('ekptg.report.htp.LaporanPermohonan','IDSUBURUSAN=0','lainpemantauan','HTPRekodSenarai');">
			      </td>
			  	</tr>
			</table>
			</fieldset>
		</td>
	</tr>	
</table>
	<input name="sorLaporan" type="hidden" id="sorLaporan"/>
	<input name="sortanah" type="hidden" id="sortanah" value="$!sorTanah"/>

<script>
	function doChangeTempoh() {
		doAjaxCall${formName}("pilihtempoh");
	}
	function doChangeTanah() {
		document.${formName}.sortanah.value = document.${formName}.sortanah_.value;
	//doAjaxCall${formName}("pilihtanah");
		
	}
	function doChangeAgensi02() {
		document.${formName}.sorLaporan.value = document.${formName}.sorLaporan_.value;
	}
	function doChangeAgensi() {
		//if(document.${formName}.socNegeri.value=="-1")
		//	return;
		document.${formName}.sorLaporan.value = document.${formName}.sorLaporan_.value;
		doAjaxCall${formName}("PilihAgensi");
	}
	function doChangeNegeri02() {
		document.${formName}.sorLaporan.value = document.${formName}.sorLaporan_.value;
	}
	function doChangeNegeri() {
		//if(document.${formName}.socNegeri.value=="-1")
		//	return;
		document.${formName}.sorLaporan.value = document.${formName}.sorLaporan_.value;
		doAjaxCall${formName}("PilihNegeri");
	}
	function doChangeKementerian2() {
		document.${formName}.sorLaporan.value = document.${formName}.sorLaporan_.value;
	}
	function doChangeKementerian() {
		//if(document.${formName}.socUnit.value=="-1")
		//	return;
		document.${formName}.sorLaporan.value = document.${formName}.sorLaporan_.value;
		doAjaxCall${formName}("PilihUnit");
	}
	function doChangeSelect02() {
		document.${formName}.sorLaporan.value = document.${formName}.sorLaporan_.value;
	}
	function doChangeSelect() {
		//if(document.${formName}.socUnit.value=="-1")
		//	return;
		document.${formName}.sorLaporan.value = document.${formName}.sorLaporan_.value;
		doAjaxCall${formName}("PilihLain");
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
		//var punit = "&ID_PEJABAT=0";
		var pdaerah = "&ID=0";
		var jenisPajakan = document.${formName}.socsuburusan.value;	
		var punit = "&ID_KEMENTERIAN="+unit+"&ID_AGENSI=0"+"&bulan=1";
		var jenisLaporan = document.${formName}.sorLaporan_.value;
		var masa = document.${formName}.sorTempoh.value;
		//alert(jenisLaporan+":"+laporan);

		if(jenisLaporan==2){
			laporan ="negeri";
		}else if(jenisLaporan==1){
			laporan ="kementerian";
		}else if(jenisLaporan==4){
			laporan ="agensi";
		}
		//alert(jenisLaporan+":"+laporan);

		var tanah = document.${formName}.sortanah.value;
		if(tanah == ""){
			alert("Sila pilih \"Tanah\" terlebih dahulu.");
			document.${formName}.sortanah.focus(); 
			return;
		
		}
		var ptemtanah = "";
		var ptemAsal = ptem;
		if(tanah==1){
			ptem += "Milik";
			ptemtanah = "Milik";
		}else if (tanah==2){
			ptem += "Rizab";
			ptemtanah = "Rizab";
		}
		//OKalert("1."+ptem);	

/* 		if(jenisPajakan==""){
			//if(jenisPajakan=="-1"){
			alert("Sila pilih \"Jenis Urusan\" terlebih dahulu.");
			document.${formName}.socsuburusan.focus(); 
			return;
		
		} */
		param += "&IDSUBURUSAN1="+jenisPajakan;
		ptem += jenisPajakan;
		var ptempajakan = jenisPajakan
		//OKalert("2."+ptem);	

		if(masa==""){
			alert("Sila pilih \"Bulan/ Tahun/ Tempoh\" terlebih dahulu.");
			document.${formName}.sorTempoh.focus(); 
			return;
		
		}
		var range_akhir = document.${formName}.txdAkhir.value;
		//alert('masa='+document.${formName}.sorTempoh);
		if(masa == 1 || masa == 3){
			if(range_akhir == ""){
				alert("Sila pilih \"Bulan\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
				
			}
		}
		

		if(range_akhir==""){
			//alert("3."+ptem);	

			if(laporan=="negeri"){
			//if(jenisLaporan==2){
				if(negeri=="-1"){
					alert("Sila pilih \"Negeri\" terlebih dahulu.");
					document.${formName}.socNegeri.focus(); 
					return;
				
				}else if(negeri=="0"){ 
				
				}else{
					ptem = '&template='+tem+'MengikutNegeri';
					pnegeri = "&ID_NEGERI="+negeri+"&bulan=1";
					/* if(jenisPajakan != "-1"){
						ptem = "&template=HTPPajakanSenaraiMengikutNegeriJenis";
						param += "&IDSUBURUSAN1="+jenisPajakan;
					} */
					if(unit != "-1"){
						ptem = '&template='+tem+'MengikutNegeriKementerian';	
						punit = "&ID_KEMENTERIAN="+unit+"&ID_AGENSI=0";
						
					}
					
				}
				
			}else if(laporan=="kementerian"){
			//}else if(jenisLaporan==1){
				// 2017/03/30
				//ptem = "&template=HTPPajakanSenaraiMengikutKementerian";
				if(unit=="-1"){
					alert("Sila pilih \"Kementerian\" terlebih dahulu");
					document.${formName}.socUnit.focus(); 
					return;
			
				}else if(unit=="0"){ 
				
				}else{
					ptem = '&template='+tem+'MengikutKementerian';
					punit = "&ID_KEMENTERIAN="+unit+"&ID_AGENSI=0"+"&bulan=1";
				/* if(jenisPajakan != "-1"){
					ptem = "&template='++'MengikutKementerianJenis";
					param += "&IDSUBURUSAN1="+jenisPajakan;
				} */
					if(negeri != "-1" && negeri != "0"){
						ptem = '&template='+tem+'MengikutNegeriKementerian';	
						pnegeri = "&ID_NEGERI="+negeri;

					}
				
				}
				
			//}else if(laporan=="lainpemantauan"){
			}else{
				//ptem = "&template=HTPajakanLaporanPemantauanKerja";
				ptem = "&template=HTPajakanLaporanPemantauan";
				
			}
			//alert('256:'+ptem);
		}else{	//range akhir else
			if(jenisLaporan == ""){
				alert("Sila pilih \"Laporan\" terlebih dahulu");
				document.${formName}.sorLaporan_.focus(); 
				return;
			}
		  	var temRange = "";

			if(masa == 1){
				temRange = "BlnThn";
				mula_tahun = document.${formName}.txdTahunMula.value;
				ptahun = "&TAHUN="+mula_tahun;
				pbulanmula = "&bulan="+range_akhir;
				
				//alert('masa 1:range_akhir='+range_akhir);
				if(jenisLaporan == "1"){			
					//laporanKementerianBulan();
					var jenisPajakan = document.${formName}.socsuburusan.value;
					if(unit == "-1"){
						alert("Sila pilih \"Kementerian\" terlebih dahulu.");
						document.${formName}.socUnit.focus(); 
						return;
					}	
					if(unit != "0"){
						ptem = ptemAsal+'TanahMengikutKementerian'+temRange;
						if(negeri !="0")
							ptem = ptemAsal+'TanahMengikutKementerianNegeri'+temRange;
						
					}else{
						ptem = ptemAsal+'TanahMengikutKementerian'+'Semua'+temRange;				
					}
					punit = "&ID_KEMENTERIAN="+unit+"&ID_AGENSI=0&TANAH="+tanah;
					pnegeri = "&ID_NEGERI="+negeri;

				}else if(jenisLaporan == "2"){			
					var jenisPajakan = document.${formName}.socsuburusan.value;
					ptemnegeri = 'MengikutNegeri';
					
					if(negeri=="-1"){
						alert("Sila pilih \"Negeri\" terlebih dahulu.");
						document.${formName}.socNegeri.focus(); 
						return;
					
					}else{
						//ptem = '&template='+tem+'MengikutNegeri'+temRange;
						mula_tahun = document.${formName}.txdTahunMula.value;
						pnegeri = "&ID_NEGERI="+negeri+"&TANAH="+tanah;

						//ptahun = "&TAHUN="+mula_tahun;
						//pbulanmula = "&bulan="+range_akhir;
						
						if(negeri !="0"){
							ptem += ptemnegeri+temRange;
					 		if(unit != "0" && unit != "-1"){
								ptem = '&template='+tem+'TanahMengikutKementerianNegeri'+temRange;	
					 		}

					 	}else
							ptem += ptemnegeri+'Semua'+temRange;

		/* 			 	if(unit != "0" && unit != "-1"){
							//ptem = '&template='+tem+ptemtanah+ptempajakan+'MengikuKementeriantNegeri'+temRange;	
							if(negeri !="0")
								ptem = '&template='+tem+ptemtanah+ptempajakan+'MengikutKementerianNegeri'+temRange;	
							else
								ptem = '&template='+tem+ptemtanah+ptempajakan+'MengikutKementerianNegeriSemua'+temRange;	

							punit = "&ID_KEMENTERIAN="+unit+"&ID_AGENSI=0";
							
						} */
						
					}
				}else if(jenisLaporan == "4"){			
					//laporanAgensiBulan();
					ptem = '&template='+tem+'MengikutAgensi'+temRange;

					mula_tahun = document.${formName}.txdTahunMula.value;
					//ptahun = "&TAHUN="+mula_tahun;
					//pbulanmula = "&bulan="+range_akhir;

					var jenisPajakan = document.${formName}.socsuburusan.value;
					var idAgensi = document.${formName}.socDaerah.value;
					if(idAgensi == "-1"){
						alert("Sila pilih \"Agensi\" terlebih dahulu.");
						document.${formName}.socDaerah.focus(); 
						return;
					}else if(idAgensi == "0"){
						ptem = '&template='+tem+'MengikutKementerian'+temRange;
					}
					
					punit = "&ID_KEMENTERIAN="+unit+"&ID_AGENSI="+idAgensi;
				
				}else {			
					ptem = '&template='+tem+'Mengikut'+temRange;
				}
			
			}else if(masa == 2){
				//alert('masa == 2,'+ptem);
				temRange = "Thn";
				ptahun = "&TAHUN="+range_akhir;
				var jenisPajakan = document.${formName}.socsuburusan.value;

				//alert('jenisLaporan='+jenisLaporan);
				if(jenisLaporan == "1"){
					ptemkem = 'TanahMengikutKementerian';					

					if(unit == "-1"){
						alert("Sila pilih \"Kementerian\" terlebih dahulu.");
						document.${formName}.socUnit.focus(); 
						return;
					}	
					if(unit != "0"){
						ptem = ptemAsal+ptemkem+temRange;
						if(negeri=="-1" || negeri=="0"){
							//ptem = ptemAsal+ptemkem+'Semua'+temRange;				
						}else{
							ptem = ptemAsal+ptemkem+'Negeri'+temRange;				
						}
						
					}else{
						ptem = ptemAsal+ptemkem+'Semua'+temRange;	
						if(negeri=="-1" || negeri=="0"){
							//ptem = ptemAsal+ptemkem+'Semua'+temRange;				
						}else{
							ptem = ptemAsal+ptemkem+'Negeri'+temRange;				
						}
					}
					pnegeri = "&ID_NEGERI="+negeri+"&bulan=1";
					punit = "&ID_KEMENTERIAN="+unit+"&ID_AGENSI=0&TANAH="+tanah;
					
				}else if(jenisLaporan == "2"){		
					if(negeri=="-1"){
						alert("Sila pilih \"Negeri\" terlebih dahulu.");
						document.${formName}.socNegeri.focus(); 
						return;
					
					//}else if(negeri=="0"){ 				
					}else{
						ptemnegeri = 'MengikutNegeri';
						pnegeri = "&ID_AGENSI=0&ID_NEGERI="+negeri+"&bulan=1&TANAH="+tanah;
						if(negeri !="0")
							ptem += ptemnegeri+temRange;
 					 		if(unit != "0" && unit != "-1")
								ptem = '&template='+tem+'TanahMengikutKementerianNegeri'+temRange;	
	
						else	
							ptem += ptemnegeri+'Semua'+temRange;
						
						if(mulatemp != "" && akhirtemp != "" && jenisPajakan != "-1"){
							param += "&IDSUBURUSAN1="+jenisPajakan;

						}
/* 					 	if(unit != "0" && unit != "-1"){
							if(negeri !="0")
								ptem = '&template='+tem+ptemtanah+ptempajakan+'MengikutKementerianNegeri'+temRange;	
							//}else
							//	ptem = '&template='+tem+'MengikutNegeriKementerian'+temRange;								
								
							punit = "&ID_KEMENTERIAN="+unit+"&ID_AGENSI=0";
								
						} */
						
					}

				}else if(jenisLaporan == "4"){			
					//laporanAgensiBulan();
					ptem = '&template='+tem+'MengikutAgensi'+temRange;
					pnegeri = "&ID_NEGERI="+negeri+"";

					//mula_tahun = document.${formName}.txdTahunMula.value;
					//ptahun = "&TAHUN="+mula_tahun;
					//pbulanmula = "&bulan="+range_akhir;

					//var jenisPajakan = document.${formName}.socsuburusan.value;
					var idAgensi = document.${formName}.socDaerah.value;
					if(idAgensi == "-1"){
						alert("Sila pilih \"Agensi\" terlebih dahulu.");
						document.${formName}.socDaerah.focus(); 
						return;
					}	
					if(idAgensi != "0"){
						//ptem = '&template='+tem+'MengikutAgensiNegeri'+temRange;	
						//alert(negeri);
						if(negeri !="0" && negeri !="-1"){
							ptem = '&template='+tem+'MengikutAgensiNegeri'+temRange;	
						}						
					}else{
						ptem = '&template='+tem+'MengikutKementerian'+temRange;	
					}

					punit = "&ID_KEMENTERIAN="+unit+"&ID_AGENSI="+idAgensi;
					
				}else {			
					ptem = '&template='+tem+'MengikutTahun';

				}
			
			}else if(masa == 3){
				var tarikhsemasa = new Date();
				var day_ = tarikhsemasa.getDate();
			
		  		akhir_hari = day_;
				akhir_bulan = document.${formName}.txdAkhir.value;
		  		akhir_tahun = document.${formName}.txdTahunAkhir.value;
				var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
		 		mula_hari = day_;
		  		mula_bulan = document.${formName}.txdMula.value;
		  		mula_tahun = document.${formName}.txdTahunMula.value;
				var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;	
				
				temRange = "BlnThnRange";
				ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+akhir_tahun;
				pbulanmula = "&BULANTAHUN="+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
				pbulantamat = "&BULANTAHUNTMT="+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
				var jenisPajakan = document.${formName}.socsuburusan.value;

				var mula = Date.parse(mulatemp);
				var akhir = Date.parse(akhirtemp);
				
			  	if(akhir<mula){
			    	alert("Sila pastikan Tarikh Akhir tidak melebihi dari Tarikh Mula.");
			    	return;
			  	}
			  	if(akhir>tarikhsemasa){
			    	alert("Sila pastikan Tarikh Akhir tidak melebihi dari Tarikh Semasa.");
			    	return;
			  	}
			  	//alert('masa 3, mengikut kem'+ptem);
				if(jenisLaporan=="1"){
					ptemkem = 'TanahMengikutKementerian';					
					var jenisPajakan = document.${formName}.socsuburusan.value;
					if(unit=="-1"){
						alert("Sila pilih \"Kementerian\" terlebih dahulu.");
						document.${formName}.socUnit.focus(); 
						return;
					}	
					if(unit != "0"){
						ptem = ptemAsal+ptemkem+temRange;
					}else{
						ptem = ptemAsal+ptemkem+'Semua'+temRange;				
					}
					punit = "&ID_KEMENTERIAN="+unit+"&ID_AGENSI=0"+"&bulan=1&TANAH="+tanah;
			
					if(negeri != "-1" && negeri != "0"){
						ptem = '&template='+tem+'TanahMengikutKementerianNegeri'+temRange;				
						//ptem = ptemAsal+ptemkem+'Negeri'+temRange;
						pnegeri = "&ID_NEGERI="+negeri;
						
					}
									
			  	}else if(jenisLaporan=="2"){
					if(negeri=="-1"){
						alert("Sila pilih \"Negeri\" terlebih dahulu.");
						document.${formName}.socNegeri.focus(); 
						return;
					
					//}else if(negeri=="0"){ 				
					}else{
						//alert("range negeri"+ptem);
						ptemNegeri = "MengikutNegeri";
						//ptem = '&template='+tem+'MengikutNegeri'+temRange;
						pnegeri = "&ID_AGENSI=0&ID_NEGERI="+negeri+"&bulan=1&TANAH="+tanah;
						if(negeri !="0")
							ptem += ptemNegeri+temRange;
						else
							ptem += ptemNegeri+'Semua'+temRange;
	
						if(mulatemp != "" && akhirtemp != "" && jenisPajakan != "-1"){
							param += "&IDSUBURUSAN1="+jenisPajakan;

						}
					 	if(unit != "0" && unit != "-1"){
							//ptem = '&template='+tem+'MengikutNegeriKementerian'+temRange;	
							if(negeri !="0")
								ptem = '&template='+tem+ptemtanah+ptempajakan+'MengikutKementerianNegeri'+temRange;	
					 		
							//alert("range negeri|kem"+ptem);
							punit = "&ID_KEMENTERIAN="+unit+"&ID_AGENSI=0";
							
						}
						
					}
			  	
			  	}else if(jenisLaporan=="4"){
					var idAgensi = document.${formName}.socDaerah.value;
					if(idAgensi == "-1"){
						alert("Sila pilih \"Agensi\" terlebih dahulu.");
						document.${formName}.socDaerah.focus(); 
						return;
					//}else if(negeri=="0"){ 				
					}else{
						ptem = '&template='+tem+'MengikutAgensi'+temRange;
						//if(negeri != "0")
							//ptem = '&template='+tem+'MengikutAgensi'+temRange;
				
						pnegeri = "&ID_KEMENTERIAN=0&ID_AGENSI="+idAgensi+"&ID_NEGERI="+negeri+"&bulan=1";
						
						if(mulatemp != "" && akhirtemp != "" && jenisPajakan != "-1"){
							param += "&IDSUBURUSAN1="+jenisPajakan;

						}
						
					}
			
			  	}else{
					ptem = '&template='+tem+'MengikutTahun';
					if(mulatemp != "" && akhirtemp != ""){
						ptem = '&template='+tem+'MengikutTahunRange';
					}
					
			  	}
			  	
			}

		} //else
		//alert(ptem);
		var url = "../servlet/"+urli+"?"+param+pnegeri+ptahun+ptem+pbulanmula+pbulantamat+punit+pdaerah;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
			
	}

		  	
</script>

