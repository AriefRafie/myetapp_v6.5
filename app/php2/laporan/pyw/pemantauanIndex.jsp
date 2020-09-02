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
							<!-- Kemaskini by Mohamad Rosli 22/16/2020 -->
							<tr>
								<td scope="row" align="left">&nbsp;Status Penyewaan</td>
								<td>:&nbsp;</td>
								<td>$!selectStatus</td>
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
							#end

						</table>
						</fieldset>
					</td>
				</tr>

				<tr>
			        <td scope="row" align="left" colspan=""></td>
			   	</tr>

				<tr>
			        <td align="center">
			        <input class="stylobutton100" name="cmdcetak" value="Cetak" id="cmdcetak" type="button" onClick="openLaporan('ekptg.report.LaporanPermohonan','IDSTATUS=0','lainpemantauan','PYWLaporanPemantauan');">
			      </td>
			  	</tr>
			</table>
			</fieldset>
		</td>
	</tr>
</table>
	<input name="sorLaporan" type="hidden" id="sorLaporan"/>

<script>
	function doChangeTempoh() {
		doAjaxCall${formName}("pilihtempoh");
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
		//document.${formName}.sorLaporan.value = document.${formName}.sorLaporan_.value;
		doAjaxCall${formName}("PilihNegeri");
	}
	function doChangeKementerian2() {
		document.${formName}.sorLaporan.value = document.${formName}.sorLaporan_.value;
	}
	function doChangeKementerian() {
		//if(document.${formName}.socUnit.value=="-1")
		//	return;
		//document.${formName}.sorLaporan.value = document.${formName}.sorLaporan_.value;
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
		var status = document.${formName}.socStatus.value;
		var negeri = document.${formName}.socNegeri.value;
		var unit = document.${formName}.socUnit.value;
		var daerah = document.${formName}.socDaerah.value;
		var daerahbaru = document.${formName}.socDaerahBaru.value;
		var pnegeri = "&ID_NEGERI=0";
		var ptahun = "&TAHUN=";
		var ptahuntamat = "&TAHUN_TAMAT=0";
		var ptem = "&template="+tem;
		var pbulanmula = "&BULANTAHUN=0";
		var pbulantamat = "&BULANTAHUNTMT=0";
		var pdaerah = "&ID=0";
		var punit = "&ID_KEMENTERIAN="+unit;
		var pagensi = "&ID_AGENSI=0";
		var masa = document.${formName}.sorTempoh.value;
		var folder = "&fol=php2/PYW/"
		var pmasa = "&TYPE="+masa;
		var pbulantahun = "&BULANTAHUN=0";
		var psuburusan = "&IDSUBURUSAN=0";

		var	pstatus = "&ID_STATUS="+status;

		if(masa==""){
			alert("Sila pilih \"Bulan/ Tahun/ Tempoh\" terlebih dahulu.");
			document.${formName}.sorTempoh.focus();
			return;
		}

		if(negeri && unit == "-1" && !daerahbaru && daerah == "-1"){
			if(negeri=="-1"){
				alert("Sila pilih \"Negeri\" terlebih dahulu.");
				document.${formName}.socNegeri.focus();
				return;
			}else if(negeri=="0"){
			}else{
				ptem = '&template=PYWLaporanPemantauanNegeri';
				pnegeri = "&ID_NEGERI="+negeri;
			}
		}else if(negeri && unit =="-1" && daerahbaru && daerah == "-1" ){
			if(daerahbaru=="-1"){
				alert("Sila pilih \"Daerah\" terlebih dahulu");
				document.${formName}.socDaerahBaru.focus();
				return;
			}else if(daerahbaru=="0"){
			}else if(daerahbaru==""){
			}else{
				ptem = '&template=PYWLaporanPemantauanDaerah';
				pdaerah = "&ID="+daerahbaru;
			}
		}else if(negeri && unit !="0" && daerahbaru =="0" && daerah == "0" ){
			if(unit=="-1"){
				alert("Sila pilih \"Kementerian\" terlebih dahulu");
				document.${formName}.socUnit.focus();
				return;
			}else if(unit=="0"){
			}else{
				ptem = '&template=PYWLaporanPemantauanKem';
				punit = "&ID_KEMENTERIAN="+unit;
			}
		}else if(negeri && unit !="0" && daerahbaru && daerah == "0" ){
			if(daerahbaru=="-1"){
				alert("Sila pilih \"Daerah\" terlebih dahulu");
				document.${formName}.socDaerahBaru.focus();
				return;
			}else if(daerahbaru=="0"){
			}else if(daerahbaru==""){
			}else{
				ptem = '&template=PYWLaporanPemantauanDaerahKem';
				pdaerah = "&ID="+daerahbaru;
			}
		}else if(negeri && unit !="0" && !daerahbaru && daerah != "0" ){
			if(daerah=="-1"){
				alert("Sila pilih \"Agensi\" terlebih dahulu");
				document.${formName}.socDaerah.focus();
				return;
			}else if(daerah=="0"){
			}else{
				ptem = '&template=PYWLaporanPemantauanAgensi';
				pagensi = "&ID_AGENSI="+daerah;
			}
		}else if(negeri && unit !="0" && !daerahbaru && daerah == "0" ){
			if(daerah=="-1"){
				alert("Sila pilih \"Agensi\" terlebih dahulu");
				document.${formName}.socDaerah.focus();
				return;
			}else{
				ptem = '&template=PYWLaporanPemantauanAgensi';
				pagensi = "&ID_AGENSI="+daerah;
			}
		}else if(negeri && unit !="0" && !daerahbaru && daerah != "0" ){
			if(daerah=="-1"){
				alert("Sila pilih \"Agensi\" terlebih dahulu");
				document.${formName}.socDaerah.focus();
				return;
			}else if(daerah=="0"){
			}else{
				ptem = '&template=PYWLaporanPemantauanAgensi';
				pagensi = "&ID_AGENSI="+daerah;
			}
		}else{
			ptem = '&template=PYWLaporanPemantauanAll';
			pagensi = "&ID_AGENSI="+daerah;
			pdaerah = "&ID="+daerahbaru;
			punit = "&ID_KEMENTERIAN="+unit;
			pnegeri = "&ID_NEGERI="+negeri;
		}




		var range_akhir = document.${formName}.txdAkhir.value;
		if(masa == 1 || masa == 3){
			if(range_akhir == ""){
				alert("Sila pilih \"Bulan\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus();
				return;
			}
		}

	  	var temRange = "";
		if(masa == 1){
			temRange = "BlnThn";
				mula_tahun = document.${formName}.txdTahunMula.value;
				//ptahun = "&TAHUN="+mula_tahun;
				pbulantahun = "&BULANTAHUN="+range_akhir+"/"+mula_tahun;
			//ptem = '&template='+tem;
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
			//ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+akhir_tahun;
			pbulanmula = "&BULANTAHUNMULA="+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
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
		}else{
			//ptem = '&template='+tem;
			ptahun = "&TAHUN="+range_akhir;
			/* if(mulatemp != "" && akhirtemp != ""){
				ptem = '&template='+tem;
			} */
		}
		var url = "../servlet/"+urli+"?"+param+ptem+pstatus+pnegeri+pdaerah+ptahun+ptahuntamat+pbulantahun+pbulanmula+pbulantamat+punit+pagensi+pmasa+psuburusan+folder;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;

	}
</script>