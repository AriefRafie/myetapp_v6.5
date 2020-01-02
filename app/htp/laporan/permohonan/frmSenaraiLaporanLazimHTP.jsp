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
					</br><input type="radio" name="soclaporantemp" value="D" $!checkD  onclick="senaraiLaporan()"/>Laporan Mengikut Daerah
					</br><input type="radio" name="soclaporantemp" value="K" $!checK  onclick="senaraiLaporan()"/>Laporan Mengikut Kementerian
					</br><input type="radio" name="soclaporantemp" value="A" $!checkA  onclick="senaraiLaporan()"/>Laporan Mengikut Agensi
					</br><input type="radio" name="soclaporantemp" value="L" $!checkL  onclick="senaraiLaporan()"/>Laporan Lain
					</br><input type="hidden" name="socjenislaporan" value="$!jenisLaporan">
		        
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
					<input type="radio" name="socJenisTanahtemp" value="10" $checkedRizab  onclick="senaraiLaporan()"/>PERIZABAN TANAH
					<input type="radio" name="socJenisTanahtemp" value="1,10" $checkedMilikRizab  onclick="senaraiLaporan()"/>PERMOHONAN/ PERIZABAN TANAH
					<input type="hidden" name="socJenisTanah" value="$!jenisTanah">
		        
		        </td>
		      </tr>  				      
		      	     
		      <tr> 
		        <td scope="row" align="left">&nbsp;Negeri </td>
		        <td>:&nbsp;</td>
		        <td>$selectNegeri</td>
		      </tr>
		      <tr> 
		        <td scope="row" align="left">&nbsp;$!labelDaerah</td>
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
<!-- Midified by rosli 17/12/2010 -->
		      <tr> 
		        <td scope="row" align="left">&nbsp;Status Keputusan </td>
		        <td>:&nbsp;</td>
		        <td>
		        	<select name="pilihkeputusan" style="width:200px;" $style/>
					#set($pk_ = "selected")
					#set($pk0 = "")
					#if($pk == "0")
						#set($pk_ = "")
						#set($pk0 = "selected")
					#end
					
					#set ($list = ["BELUM ADA KEPUTUSAN","DILULUSKAN","TIDAK DILULUSKAN","DIBATALKAN/DITARIKBALIK"])
					#set( $counter = 0 )
						<option $!pk_ value="-1">SILA PILIH</option>
						<!-- <option $!pk0 value="0">SEMUA STATUS</option> -->
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
      
		    	##set ($txdMula = "")
		   		##set ($txdAkhir = "")
		      
		       <tr> 
		        <td scope="row" align="left">&nbsp;Tarikh </td>
		        <td>:&nbsp;</td>
		        <td>
		       		<label>
		      			<input name="txdMula" type="text" id="txdMula" value="$!txdMula" size="10" onblur="check_date(this);semakTarikhHariIni(document.${formName}.txdMula);" />
		        	</label>
		        	<a href="javascript:displayDatePicker('txdMula',false,'dmy');">
		        		<img border="0" src="../img/calendar.gif"/>
				 		<span class="pautanms" class="stylefont">dd/mm/yyyy</span> 
      				</a>
		        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;hingga&nbsp;&nbsp;&nbsp;:&nbsp;
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
			  		<a href="javascript:openReport('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','urusneg','HTPermohonanTanah')" class="style1" >SENARAI PERMOHONAN</a>
			 	</td>
		   	</tr>
			   	<tr>
			  	<td width="3%" class="row1">2.</td>
			  	<td width="97%" class="row1">
			  		<a href="javascript:openReport('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','urusnegnotis','HTPermohonanTanah')" class="style1" >SENARAI PERMOHONAN (NOTIS 5A)</a>
			 	</td>
		   	</tr>	
		   	<tr>
			  	<td width="3%" class="row1">3.</td>
			  	<td width="97%" class="row1">
			  		<a href="javascript:openReport('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','urusnegnotisbayar','HTPermohonanTanahNotis5ASudahBayar')" class="style1" >SENARAI PERMOHONAN (NOTIS 5A - SUDAH BAYAR)</a>
			 	</td>
		   	</tr>	
		   	<tr>
			  	<td width="3%" class="row1">4.</td>
			  	<td width="97%" class="row1">
			  		<a href="javascript:openReport('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','urusnegnotisbayar','HTPermohonanTanahNotis5ABelumBayar')" class="style1" >SENARAI PERMOHONAN (NOTIS 5A - BELUM BAYAR)</a>
			 	</td>
		   	</tr>	
	   		<tr>
			  	<td width="3%" class="row1">5.</td>
			  	<td width="97%" class="row1">
			  		<a href="javascript:openReport('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','urusnegbelum','HTPermohonanTanah')" class="style1" >SENARAI PERMOHONAN BELUM DAFTAR HAKMILIK/ WARTA</a>
			 	</td>
		   	</tr>		   	
		   	<tr>
			  	<td width="3%" class="row1">6.</td>
			  	<td width="97%" class="row1">
			  		<a href="javascript:openReport('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','urusnegdaftar','HTPermohonanTanah')" class="style1" >SENARAI PERMOHONAN TELAH DAFTAR HAKMILIK/ WARTA</a>
			 	</td>
		   	</tr>		   			   	
			<tr>
		  	<td width="3%" class="row2">7.</td>
		  	<td width="97%" class="row2">
		  		<a href="javascript:openReport('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','ringkasan','HTPermohonanTanahRingkasanMengikutNegeri')" class="style1" >RINGKASAN PERMOHONAN</a>
		 	</td>
		   	</tr>
		  	<tr>
			  	<td width="3%" class="row1">8.</td>
			  	<td width="97%" class="row1">
			  		<a href="javascript:openReport('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','ringkasanurus','HTPermohonanTanah')" class="style1" >RINGKASAN PERMOHONAN MENGIKUT STATUS</a>
			 	</td>
		   	</tr>
 	  	<tr>
			  	<td width="3%" class="row2">9.</td>
			  	<td width="97%" class="row2">
			  		<a href="javascript:openReport('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','ringkasanotis','HTPPermohonanRingkasanNotis5AMengikutNegeri')" class="style1" >RINGKASAN NOTIS 5A</a>
			 	</td>
		   	</tr>
<!--		   		
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
			  		<a href="javascript:openReport('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','urusdae','HTPermohonanTanah')" class="style1" >SENARAI PERMOHONAN</a>
			 	</td>
		   	</tr>
		   	<tr>
			  	<td width="3%" class="row1">2.</td>
			  	<td width="97%" class="row1">
			  		<a href="javascript:openReport('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','urusdaebelum','HTPermohonanTanah')" class="style1" >SENARAI PERMOHONAN BELUM DAFTAR HAKMILIK/ WARTA</a>
			 	</td>
		   	</tr>		   	
		   	<tr>
			  	<td width="3%" class="row1">3.</td>
			  	<td width="97%" class="row1">
			  		<a href="javascript:openReport('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','urusdaedaftar','HTPermohonanTanah')" class="style1" >SENARAI PERMOHONAN TELAH DAFTAR HAKMILIK/ WARTA</a>
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
	#if ( $!checK.equals("checked") )
		
		<fieldset>
		<legend>LAPORAN MENGIKUT KEMENTERIAN</legend>
			
		<table border="0" align="center" width="100%" >
			<tr>
			  	<td width="3%" class="row2">1.</td>
			  	<td width="97%" class="row2">
			  		<a href="javascript:openReport('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','uruskem','HTPermohonanTanah')" class="style1" >SENARAI PERMOHONAN</a>
			 	</td>
		   	</tr>
			   	<tr>
			  	<td width="3%" class="row1">2.</td>
			  	<td width="97%" class="row1">
			  		<a href="javascript:openReport('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','uruskemnotis','HTPermohonanTanah')" class="style1" >SENARAI PERMOHONAN (NOTIS 5A)</a>
			 	</td>
		   	</tr>	
		   	<tr>
			  	<td width="3%" class="row1">3.</td>
			  	<td width="97%" class="row1">
			  		<a href="javascript:openReport('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','uruskemnotisbayar','HTPermohonanTanahNotis5ASudahBayar')" class="style1" >SENARAI PERMOHONAN (NOTIS 5A - SUDAH BAYAR)</a>
			 	</td>
		   	</tr>	
		   	<tr>
			  	<td width="3%" class="row1">4.</td>
			  	<td width="97%" class="row1">
			  		<a href="javascript:openReport('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','uruskemnotisbayar','HTPermohonanTanahNotis5ABelumBayar')" class="style1" >SENARAI PERMOHONAN (NOTIS 5A - BELUM BAYAR)</a>
			 	</td>
		   	</tr>	
	   	<tr>
			  	<td width="3%" class="row1">5.</td>
			  	<td width="97%" class="row1">
			  		<a href="javascript:openReport('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','uruskembelum','HTPermohonanTanah')" class="style1" >SENARAI PERMOHONAN BELUM DAFTAR HAKMILIK/ WARTA</a>
			 	</td>
		   	</tr>		   	
		   	<tr>
			  	<td width="3%" class="row1">6.</td>
			  	<td width="97%" class="row1">
			  		<a href="javascript:openReport('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','uruskemdaftar','HTPermohonanTanah')" class="style1" >SENARAI PERMOHONAN TELAH DAFTAR HAKMILIK/ WARTA</a>
			 	</td>
		   	</tr>		   	
			   	
<!-- 			<tr>
			  	<td width="3%" class="row2">1.</td>
			  	<td width="97%" class="row2">
			  		<a href="javascript:openLaporan('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','urusnegkem','HTPPermohonanTanahMengikutNegeriKementerian')" class="style1" >SENARAI PERMOHONAN</a>
			 	</td>
		   	</tr> -->
		   	
		  	  	<tr>
			  	<td width="3%" class="row2">7.</td>
			  	<td width="97%" class="row2">
			  		<a href="javascript:openReport('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','ringkasan','HTPermohonanTanahRingkasanMengikutKementerian')" class="style1" >RINGKASAN PERMOHONAN</a>
			 	</td>
		   	</tr>
		  	<tr>
			  	<td width="3%" class="row1">8.</td>
			  	<td width="97%" class="row1">
			  		<a href="javascript:openReport('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','ringkasanuruskem','HTPermohonanTanah')" class="style1" >RINGKASAN PERMOHONAN MENGIKUT STATUS</a>
			 	</td>
		   	</tr>
 	  	<tr>
			  	<td width="3%" class="row2">9.</td>
			  	<td width="97%" class="row2">
			  		<a href="javascript:openReport('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','ringkasanotis','HTPermohonanRingkasanNotis5AMengikutKementerian')" class="style1" >RINGKASAN NOTIS 5A</a>
			 	</td>
		   	</tr>		   	  
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
			  		<a href="javascript:openReport('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','urusagen','HTPermohonanTanah')" class="style1" >SENARAI PERMOHONAN</a>
			 	</td>
		   	</tr>
			<tr>
			  	<td width="3%" class="row1">2.</td>
			  	<td width="97%" class="row1">
			  		<a href="javascript:openReport('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','urusagenbelum','HTPermohonanTanah')" class="style1" >SENARAI PERMOHONAN BELUM DAFTAR HAKMILIK/ WARTA</a>
			 	</td>
		   	</tr>		   	
		   	<tr>
			  	<td width="3%" class="row1">3.</td>
			  	<td width="97%" class="row1">
			  		<a href="javascript:openReport('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','urusagendaftar','HTPermohonanTanah')" class="style1" >SENARAI PERMOHONAN TELAH DAFTAR HAKMILIK/ WARTA</a>
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
			<!--  <tr>
			  	<td width="3%" class="row2">1.</td>
			  	<td width="97%" class="row2"> -->
			  		<!--  javascript:openLaporan('ekptg.report.htp.LaporanUtamaHTP','IDSUBURUSAN=1','urusan','HTPPermohonanRingkasanTanahRizab')-->
			  		<!--  <a href="javascript:openReport('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','urusan','HTPPermohonan')" class="style1" >RINGKASAN PERMOHONAN MENGIKUT STATUS</a>
			 	</td>
		   	</tr> -->
			<tr>
			  	<td width="3%" class="row2">1.</td>
			  	<td width="97%" class="row2">
			  		<a href="javascript:openReport('ekptg.report.htp.permohonan.LaporanPermohonan','IDSUBURUSAN=1','buta','HTPermohonanTanahLaporanPrestasiPegawaiMengikutBulan')" class="style1" >LAPORAN PRESTASI</a>
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
	function openReport(urli,param,laporan,tem){
		var negeri = document.${formName}.socNegeri.value;
		var daerah = document.${formName}.socDaerah.value;
		var daerahbaru = document.${formName}.socDaerahBaru.value;
		var unit = document.${formName}.socUnit.value;
		
		var pnegeri = "&ID_NEGERI=-1";
		var pdaerah = "&ID=0";
		var pkem = "&ID_KEMENTERIAN=0";
		var pagen = "&ID_AGENSI=0";
		var ptem = "&template="+tem;
		var pbulanmula = "&BULANTAHUN=0";
		var pbulantamat = "&BULANTAHUNTMT=0";
		var ptahun = "&TAHUN=";
		var punit = "&ID_PEJABAT=0";
		var status ="&STATUS=-1";
		var bulan = "&bulan=00";
		//alert(laporan);
		if(document.${formName}.socJenisTanah.value == "" 
			&& (laporan != "ringkasan"
				&& laporan != "ringkasanotis"
				&& laporan != "urusnegnotis"
				&& laporan != "urusnegnotisbayar"
				&& laporan != "uruskemnotis"
				&& laporan != "uruskemnotisbayar"
				&& laporan != "buta"
				)
			){
			alert("Sila pilih \"Urusan\" terlebih dahulu.");
			return;
			
		}	
		//laporan =urusan (HTPPermohonanRingkasanTanahMilik)
		//laporan == "urusneg"
		var ptemurus = "";
		var ptempjenis = "";
		if(document.${formName}.socJenisTanah.value != "" 
			&& (laporan == "urusan" 
				|| laporan == "urusneg"
				|| laporan == "urusnegbelum"|| laporan == "urusnegdaftar"
				|| laporan == "urusdae"
				|| laporan == "urusdaebelum"|| laporan == "urusdaedaftar"
				|| laporan == "uruskem"
				|| laporan == "uruskembelum"|| laporan == "uruskemdaftar"
				|| laporan == "urusagen"
				|| laporan == "urusagenbelum"|| laporan == "urusagendaftar"
				|| laporan == "ringkasanurus"
				) 
		){ 
			if(document.${formName}.socJenisTanah.value == "10"){
				ptemurus = "Rizab";
				
			}else if(document.${formName}.socJenisTanah.value == "1"){
				ptemurus = "Milik";
					
			}else if(document.${formName}.socJenisTanah.value == "1,10"){
				alert('Tiada Laporan');
				return;
					
			}
			
			if(laporan == "urusan")
				ptem = "&template="+tem+"TanahRingkasan"+ptemurus;
			else if(laporan == "ringkasanurus")
				ptempjenis = tem+"RingkasanStatus";
			else if(laporan == "ringkasanotis")
				ptem = "&template="+tem;
			else if(laporan == "urusneg" || laporan == "urusdae"
				|| laporan == "uruskem" || laporan == "urusagen")
				ptempjenis = tem;
			else if(laporan == "urusnegbelum" || laporan == "urusdaebelum"
				|| laporan == "uruskembelum" || laporan == "urusagenbelum")
				ptempjenis = tem+ptemurus+"BelumDidaftarkan";
			else if(laporan == "urusnegdaftar" || laporan == "urusdaedaftar"
				|| laporan == "uruskemdaftar" || laporan == "urusagendaftar")
				ptempjenis = tem+ptemurus+"TelahDidaftarkan";
										
			ptem = "&template="+ptempjenis+"MengikutNegeri";
			if(negeri=="-1"){
				alert("Sila pilih \"Negeri\" terlebih dahulu.");
				document.${formName}.socNegeri.focus(); 
				return;			
			// HTPPermohonanTanahMilikMengikutNegeriKeseluruhan
			}else if(negeri=="0"){
				if(laporan == "urusneg")
					ptem += "Semua";
				
				//else if(laporan == "ringkasanurus")

					
			}
			pnegeri = "&ID_NEGERI="+negeri;
			
			var rtype= "MengikutDaerah";
			if(laporan.includes("dae")){
				ptem = "&template="+ptempjenis+rtype;
				if(daerahbaru==""){
					alert("Sila pilih \"Daerah\" terlebih dahulu.");
					document.${formName}.socDaerahBaru.focus(); 
					return;
				}	
				pdaerah = "&ID="+daerahbaru;
			
			}
			
			if(laporan.includes("kem")){
				rtype= "MengikutKementerian";
				ptem = "&template="+ptempjenis+rtype;
				
				if(unit=="-1"){
					alert("Sila pilih \"Kementerian\" terlebih dahulu.");
					document.${formName}.socUnit.focus(); 
					return;
				
				}else if(unit=="0"){
					if(laporan == "uruskem")
						ptem += "Semua";
			
				}
				pkem = "&ID_KEMENTERIAN="+unit;
			
			}
			
			if(laporan.includes("agen")){
				rtype= "MengikutAgensi";
				ptem = "&template="+ptempjenis+rtype;
		
				if(daerah=="-1"){
					alert("Sila pilih \"Agensi\" terlebih dahulu.");
					document.${formName}.socDaerah.focus(); 
					return;
					
				}	
				pdaerah = "&ID_AGENSI="+daerah;

				
			}
			//HTPPermohonanTanahMilikMengikutNegeriStatusSemasa
			//alert("pilihkeputusan="+document.${formName}.pilihkeputusan.value);
			if(document.${formName}.pilihkeputusan.value != -1){ 
				ptem = "&template="+tem+"MengikutNegeriStatus";
						//if(document.${formName}.pilihkeputusan.value == 0)
						//	ptem = "&template="+tem+"MengikutNegeriStatusSemua";
				if( laporan.includes("dae")
					||laporan.includes("kem") 
					||laporan.includes("agen")){
					ptem = "&template="+ptempjenis+"Status"+rtype;
				}else if(laporan.includes("kemneg")){
				
				}
				status ="&STATUS="+document.${formName}.pilihkeputusan.value;	
						
			} //end document.${formName}.pilihkeputusan.value != -1
					
		//} 
					
					
		}else{ //end document.${formName}.socJenisTanah.value != ""
			
			//alert('jenis tanah');
			if(negeri=="-1"
				&& (
				laporan == "urusnegnotisbayar"
				)		
			){
				alert("Sila pilih \"Negeri\" terlebih dahulu.");
				document.${formName}.socNegeri.focus(); 
				return;		
				
			}
			//else if(negeri=="0"){
			//	if(laporan == "urusneg")
			//		ptem += "Semua";
				
				//else if(laporan == "ringkasanurus")
			var temnegeri = "MengikutNegeri";
			//HTPermohonanTanahNotis5ABelumBayarMengikutNegeri
			if(laporan == "urusnegnotisbayar"){
				pnegeri = "&ID_NEGERI="+negeri;			
				ptem = "&template="+tem+temnegeri;	
				
			}
			if(unit=="-1"
				&& (
				laporan == "uruskemnotisbayar"
				)		
			){
				alert("Sila pilih \"Kementerian\" terlebih dahulu.");
				document.${formName}.socUnit.focus(); ; 
				return;		
				
			}
			if(laporan == "uruskemnotisbayar"){
				temnegeri = "MengikutKementerian";
				pkem = "&ID_KEMENTERIAN="+unit;
				ptem = "&template="+tem+temnegeri;	
				
			}

					
		
		}
		//alert('Tiada Tarikh='+ptem);
		param += "&ID_URUSAN="+document.${formName}.socJenisTanah.value;
		
		//Laporan ada tarikh dari/sehingga
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
	  	
		//alert('mula='+isNaN(mula));
		//alert('laporan='+laporan);
		if(isNaN(mula) == true 
			&& (laporan == "urusnegnotis" 
				|| laporan == "uruskemnotis"	
				|| laporan == "buta"
				)
			){
			alert("Sila pilih \"Tarikh\" terlebih dahulu.");
			document.${formName}.txdMula.focus(); 
			return;

		}
		
		if(isNaN(mula) == false && isNaN(akhir) == true){
			if(laporan == "urusnegnotis"
				&& laporan == "uruskemnotis"	
				&& laporan == "buta"){
				var ptemtahun= "Tahun";
				//HTPermohonanTanahNotis5AMengikutNegeriTahun.jrxml
				if(laporan == "urusnegnotis"){
					ptem = "&template="+tem+"Notis5AMengikutNegeri"+ptemtahun;
					pnegeri = "&ID_NEGERI="+negeri;
					
				}else if(laporan == "uruskemnotis"){
					ptem = "&template="+tem+"Notis5AMengikutKementerian"+ptemtahun;
					pkem = "&ID_KEMENTERIAN="+unit;
				
				}//else if(laporan == "buta"){
				//HTPermohonanLaporanPrestasiPegawaiMengikutBulan
				pbulanmula = "&BULANTAHUN="+mula_bulan+"/"+mula_tahun;
				bulan ="&bulan="+mula_bulan;
				pbulantamat = "&BULANTAHUNTMT="+mula_bulan+"/"+mula_tahun+"&bulantamat="+mula_bulan;
				ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+mula_tahun;
		
			}else{
	    		alert("Tiada Laporan Mengikut Bulan/ Tahun.");
				return;
			
			}
			
		}
		 
		//if(laporan == "prestasi"){
		//	alert("Sila pilih \"Tarikh\" terlebih dahulu.");
		//	document.${formName}.txdMula.focus(); 
		//	return;

		//}
		if(isNaN(mula)==false && isNaN(akhir)==false){
		  	if(akhir<mula){
		    	alert("Sila pastikan Tarikh Akhir tidak melebihi dari Tarikh Mula.");
		    	return;
		  	}
		  	if(akhir>tarikhsemasa){
		    	alert("Sila pastikan Tarikh Akhir tidak melebihi dari Tarikh Semasa.");
		    	return;
		  	}
		  	
			semakTarikhHariIni(document.${formName}.txdMula);
			//urusneg (tiada kondisi)
			if(laporan == "urusan")
				ptem = "&template="+tem+"TanahRingkasan"+ptemurus;
			else if(laporan == "urusneg"){
				//ptem = "&template="+tem+"MengikutNegeri";
			
			}else if(laporan == "uruskembelum"||laporan == "uruskemdaftar"){
				ptem += "BlnThnRange";
				pkem = "&ID_KEMENTERIAN="+unit;

			}else if(laporan == "ringkasan"){
				ptem += "ThnRange";

			}

			pbulanmula = "&BULANTAHUN="+mula_bulan+"/"+mula_tahun;
			bulan ="&bulan="+mula_bulan;
			pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
			ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+akhir_tahun;
				  	
		} //end isNaN(mula)
		//alert("ptem jenis tanah ="+ptem);
		
		var url = "../servlet/"+urli+"?"+param+pnegeri+pdaerah+pkem+pagen+ptem+pbulanmula+pbulantamat+ptahun+punit+status+bulan;
		var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		
	} //end function
/* 	function getTemplate(var laporan){
		var template = "";
		switch (laporan) {
	    case "urusan":
	    	template = "Ringkasan";
	        break;

	  	}
		return template;
		
	} */
	
	function senaraiLaporan(){   
		field_ = document.${formName}.socJenisTanahtemp;
		//field__ = document.${formName}.soclaporantemp;
		for (i = 0; i < field_.length; i++){
			if(field_[i].checked==true){
				document.${formName}.socJenisTanah.value = field_[i].value;
			}
		}
		//document.${formName}.socjenislaporan.value = document.${formName}.soclaporantemp.value;
		field2 = document.${formName}.soclaporantemp;
		for (ii = 0; ii < field2.length; ii++){
			if(field2[ii].checked==true){
				document.${formName}.socjenislaporan.value = field2[ii].value;
			}
		}
		//
		doAjaxCall${formName}("senarailaporan");
		
	}

function doChangeSelect() {
	document.f1.command.value = "doChangeSelect";
	document.f1.action = "";
	document.f1.submit();
}

	function doChangeNegeri() {
		if(document.${formName}.socNegeri.value=="-1")
			return;
		doAjaxCall${formName}("PilihNegeri");
	}
	function doChangeUnit() {
		//if(document.${formName}.socUnit.value=="0" || document.${formName}.socUnit.value=="")
		if(document.${formName}.socUnit.value=="-1")
			return;
		doAjaxCall${formName}("PilihUnit");
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


