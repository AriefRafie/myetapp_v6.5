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
<!-- 	<tr>
	<td>&nbsp;</td>
	</tr> -->
	<tr>
	<td> 
		<fieldset>
		<br>
			<fieldset>
			<legend>PILIHAN LAPORAN</legend>
				<table border="0" align="center" width="100%">
				      <tr> 
				        <td width="28%" height="24" scope="row" align="right">Sila Pilih&nbsp;&nbsp;</td>
				        <td width="72%"><input type="radio" $!checkA name="sorLaporan_" value="1" 
				        	onClick="doChangeKementerian()" >&nbsp;Laporan Mengikut Kementerian</td>
					</tr>
					<tr> 
						<td scope="row" align="right">&nbsp;</td>
					 	<td ><input type="radio" $!checkB name="sorLaporan_" value="2" 
					 	 	onClick="doChangeNegeri()" >&nbsp;Laporan Mengikut Negeri</td>
					</tr>
					<tr> 
						<td scope="row" align="right">&nbsp;</td>
					  	<td ><input type="radio" $!checkC name="sorLaporan_" value="3" 
					  		onClick="doChangeSelect()" >&nbsp;Laporan Lain-lain</td>
					</tr>
				</table>
			</fieldset>		
		<br>
			<fieldset>
			<!-- <legend>PARAMETER LAPORAN</legend> -->
				<table border="0" align="center" width="60%" >      
			     <tbody> 
	<!-- Midified by rosli 17/12/2010 -->
			      <tr> 
			        <td scope="row" align="left">&nbsp;Jenis Pajakan </td>
			        <td>:&nbsp;</td>
			        <td>$!selectSuburusan
			        </td>
			      </tr> 
			     
			      <tr> 
			        <td scope="row" align="left">&nbsp;Negeri </td>
			        <td>:&nbsp;</td>
			        <td>$selectNegeri</td>
			      </tr>
			      <tr> 
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
			      </tr>   
	
			      </tr>  		      
			    	##set ($txdMula = "")
			   		##set ($txdAkhir = "")
			      
			       <tr> 
			        <td scope="row" align="left">&nbsp;Tarikh </td>
			        <td>:&nbsp;</td>
			        <td>
			       		<label><!-- $!txdMula -->
			      			<input name="txdMula" type="text" id="txdMula" value="" size="10" onblur="check_date(this);semakTarikhHariIni(document.${formName}.txdMula);" />
			        	</label>
			        	<a href="javascript:displayDatePicker('txdMula',false,'dmy');">
			        		<img border="0" src="../img/calendar.gif"/>
					 		<span class="pautanms" class="stylefont">dd/mm/yyyy</span> 
	      				</a>
			        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;hingga&nbsp;&nbsp;&nbsp;:&nbsp;
			     		<label><!-- $!txdAkhir -->
			      			<input name="txdAkhir" type="text" id="txdAkhir" value="" size="10" onblur="check_date(this);semakTarikhHariIni(document.${formName}.txdAkhir);"/>
			        	</label>
			        	<a href="javascript:displayDatePicker('txdAkhir',false,'dmy');">
			        		<img border="0" src="../img/calendar.gif"/>
					 		<span class="pautanms">dd/mm/yyyy</span> 
			        	</a>
					 <!----></td>
			      </tr> 
			      <tr> 
			        <td scope="row" align="left" colspan="3"></td>
			      </tr>
				<tr> 
			        <td scope="row" align="left"></td>
			        <td>&nbsp;</td>
			        <td>	          			
			        <input class="stylobutton100" name="cmdBayar" value="Cetak" id="cmdBayar" type="button" onClick="openLaporan('ekptg.report.htp.LaporanPermohonan','IDSUBURUSAN=0','lainpemantauan','HTPajakanLaporanPemantauan');">
			      </td>
			  	</tr>
			   </tbody>
			 </table>
			</fieldset>	
			##if ( $senaraiLaporan.size() > 0 )

			       
		<br><!--  
			<fieldset>
			<legend>CETAKAN</legend>
				
			<table border="0" align="center" width="100%" >
			 	#set ($cnt = 0)
			 	#set( $row = "" )
				#set ( $nextno = $cnt + 1 )
				#if ( $!checkAA.equals("checked") )
					<tr>
						<td class="row1">1.</td>
						<td class="row1">
							<a href="javascript:openLaporan('ekptg.report.htp.LaporanPermohonan','IDSUBURUSAN=0','kementerian','')" class="style1" >
								SENARAI PERMOHONAN PAJAKAN MENGIKUT KEMENTERIAN
		 					</a>
						</td>
					</tr>
					#end				
				#if ( $!checkBB.equals("checked") )
				<tr>
					<td class="row1">1.</td>
					<td class="row1">
						<a href="javascript:openLaporan('ekptg.report.htp.LaporanPermohonan','IDSUBURUSAN=0','negeri','')" class="style1" >
							SENARAI PERMOHONAN PAJAKAN MENGIKUT NEGERI
	 					</a>
					</td>
				</tr>
			#end
			#if ( $!checkCC.equals("checked") )
				<tr>
					<td class="row1">1.</td>
					<td class="row1">
						<a href="javascript:openLaporan('ekptg.report.htp.LaporanPajakanPemantauan','IDSUBURUSAN=0','lainpemantauan','')" class="style1" >
							LAPORAN PEMANTAUAN KERJA UNIT PAJAKAN
	 					</a>
					</td>
				</tr>
			#end		
			</table>
			
			</fieldset> -->
			##end		
		</fieldset>
	</td>
	</tr>	
</table>
	<input name="sorLaporan" type="hidden" id="sorLaporan"/>

<script>

	function doChangeNegeri() {
		//if(document.${formName}.socNegeri.value=="-1")
		//	return;
		document.${formName}.sorLaporan.value = document.${formName}.sorLaporan_.value;
		doAjaxCall${formName}("PilihNegeri");
	}
	function doChangeKementerian() {
		//if(document.${formName}.socUnit.value=="-1")
		//	return;
		document.${formName}.sorLaporan.value = document.${formName}.sorLaporan_.value;
		doAjaxCall${formName}("PilihUnit");
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
		var range_akhir = document.${formName}.txdAkhir.value;
		var jenisPajakan = document.${formName}.socsuburusan.value;	
		var punit = "&ID_KEMENTERIAN="+unit+"&ID_AGENSI=0"+"&bulan=1";
		var jenisLaporan = document.${formName}.sorLaporan_.value;
		//alert(jenisLaporan+":"+laporan);

		if(jenisLaporan==2){
			laporan ="negeri";
		}
		else if(jenisLaporan==1){
			laporan ="kementerian";
		}
		//alert(jenisLaporan+":"+laporan);

		if(jenisPajakan=="-1"){
			alert("Sila pilih \"Pajakan\" terlebih dahulu.");
			document.${formName}.socsuburusan.focus(); 
			return;
		
		}
		param += "&IDSUBURUSAN1="+jenisPajakan;
		if(range_akhir==""){
		
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
		  	var temRange = "BlnThnRange";
			ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+akhir_tahun;
			pbulanmula = "&BULANTAHUN="+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
		  	//if(laporan=="kementerian"){
			if(jenisLaporan=="1"){
				// 2017/03/30
				ptem = '&template='+tem+'MengikutKementerian'+temRange;
				var jenisPajakan = document.${formName}.socsuburusan.value;
				if(unit=="-1"){
					alert("Sila pilih \"Kementerian\" terlebih dahulu.");
					document.${formName}.socUnit.focus(); 
					return;
				}	
				punit = "&ID_KEMENTERIAN="+unit+"&ID_AGENSI=0"+"&bulan=1";
				/* if(jenisPajakan != "-1"){
					ptem = "&template=HTPPajakanSenaraiMengikutKementerianJenis";
					param += "&IDSUBURUSAN1="+jenisPajakan;
				} */
				if(mulatemp != "" && akhirtemp != ""){
					//ptem = "&template=HTPPajakanSenaraiMengikutKementerianSelangTahun";
					//ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+akhir_tahun;

				}
				/* if(mulatemp != "" && akhirtemp != "" && jenisPajakan != "-1"){
					ptem = "&template=HTPPajakanSenaraiMengikutKementerianSelangTahunJenis";
					ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+akhir_tahun;
					param += "&IDSUBURUSAN1="+jenisPajakan;

				} */
				if(negeri != "-1"){
					ptem = '&template='+tem+'MengikutNegeriKementerian'+temRange;				
					pnegeri = "&ID_NEGERI="+negeri;
					
				}
								
		  	} //end kementerian with date
		  	//else if(laporan=="negeri"){
			else if(jenisLaporan=="2"){
				if(negeri=="-1"){
					alert("Sila pilih \"Negeri\" terlebih dahulu.");
					document.${formName}.socNegeri.focus(); 
					return;
				
				}else if(negeri=="0"){ 				
				}else{
					ptem = '&template='+tem+'MengikutNegeri'+temRange;
					pnegeri = "&ID_KEMENTERIAN=0&ID_AGENSI=0&ID_NEGERI="+negeri+"&bulan=1";
/* 					if(jenisPajakan != "-1"){
						ptem = "&template=HTPPajakanSenaraiMengikutNegeriJenis";
						param += "&IDSUBURUSAN1="+jenisPajakan;
					} */
					if(mulatemp != "" && akhirtemp != ""){
						//ptem = "&template=HTPPajakanSenaraiMengikutNegeriSelangTahun";
						//ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+akhir_tahun;

					}
					if(mulatemp != "" && akhirtemp != "" && jenisPajakan != "-1"){
						//ptem = "&template=HTPPajakanSenaraiMengikutNegeriSelangTahunJenis";
						//ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+akhir_tahun;
						param += "&IDSUBURUSAN1="+jenisPajakan;

					}
					if(unit != "-1"){
						ptem = '&template='+tem+'MengikutNegeriKementerian'+temRange;				
						punit = "&ID_KEMENTERIAN="+unit+"&ID_AGENSI=0";
						
					}
					
				}
				
			}else{				
				ptem = '&template='+tem+'MengikutTahun';
				if(mulatemp != "" && akhirtemp != ""){
					ptem = '&template='+tem+'MengikutTahunRange';
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

