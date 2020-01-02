 <style type="text/css">
  <!--
  .style1 {color: #0033FF}
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
		    	##set ($txdMula = "")
		   		##set ($txdAkhir = "")
		      
		       <tr> 
		        <td scope="row" align="left">&nbsp;Tarikh </td>
		        <td>:&nbsp;</td>
		        <td>
		       		<label>
		      			<input name="txdMula" type="text" id="txdMula" value="$!txdMula" size="10" />
		        	</label>
		        	<a href="javascript:displayDatePicker('txdMula',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
		
		        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;hingga&nbsp;&nbsp;&nbsp;:&nbsp;
		     		<label>
		      			<input name="txdAkhir" type="text" id="txdAkhir" value="$!txdAkhir" size="10" />
		        	</label>
		        	<a href="javascript:displayDatePicker('txdAkhir',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
				 <!----></td>
		      </tr> 
		   </tbody>
		 </table>
		</fieldset>	
		<br>
		<fieldset>
		<legend>LAPORAN</legend>
			
		<table border="0" align="center" width="100%" >
		 	#set ($cnt = 0)
		 	#foreach ( $senarai in $senaraiLaporan )
		  	#set ( $cnt = $cnt + 1 )
		    	#set( $i = $velocityCount )
		        #if ( ($i % 2) == 0 )
		        	#set( $row = "row2" )
		      	#else
		        	#set( $row = "row1" )
		        #end
		        #set ($para = "IDSUBURUSAN=$senarai.idsuburusan")
		        #set ($by = "negeri")
		  	<tr>
		  	<td class="$row">$cnt.</td>
		  	<!--<td class="$row">$senarai.keterangan</td> -->
		  	<td class="$row">
		  		<a href="javascript:openLaporan('$senarai.idmodule','$para','$senarai.peringkat','$senarai.template')" class="style1" >$senarai.keterangan</a>
		 	</td>
		   	</tr>	
			#end 
		</table>
		
		</fieldset>
		
		
		
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
		
		
	}else if(laporan=="agensi"){
		if(daerah==""){
			alert("Sila pilih \"Agensi\" terlebih dahulu.");
			document.${formName}.socDaerah.focus(); 
			return;
		}	
		pdaerah += "&ID_KEMENTERIAN="+unit+"&ID_AGENSI="+daerah;
		pnegeri ="&ID_NEGERI="+negeri+"&bulan=1";
		
	}else if(laporan=="urusan"){
		pdaerah += "&ID_KEMENTERIAN=0&ID_AGENSI=0";
		pnegeri ="&ID_NEGERI=0&bulan=1";
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
			}
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
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
			}
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
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
			}
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
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
			}
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
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
			}
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
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
			}
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
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
			}
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
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
			}
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
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
			}
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
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
			}
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
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
			}
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
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
			}
			if(akhir_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdAkhir.focus(); 
				return;
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
	if(document.${formName}.socNegeri.value=="0")
		return;
	doAjaxCall${formName}("PilihNegeri");
}
function doChangeUnit() {
	if(document.${formName}.socUnit.value=="0" || document.${formName}.socUnit.value=="")
		return;
	doAjaxCall${formName}("PilihUnit");
}

</script>


