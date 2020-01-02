 <!--frmSenaraiSuratHTP.jsp-->
 <style type="text/css">
  <!--
  .style1 {color: #0033FF}
  -->
  </style>
<fieldset>
	<fieldset>
	<legend>SENARAI SURAT</legend>
		 	#set ($para1 = "&idpermohonan=$permohonanInfo.idpermohonan")
	
		<table border="0" align="center" width="100%" >
		 	#set ($cntBil = 0)
		 	#foreach ( $senarai in $senaraiSurat )
		  		#set ( $cntBil = $cntBil + 1 )
		    	#set( $i = $velocityCount )
		        #if ( ($i % 2) == 0 )
		        	#set( $row = "row2" )
		      	#else
		        	#set( $row = "row1" )
		        #end
		        #set ($para = "idsuburusan=$senarai.idsuburusan")
		  	 	##set ($para1 = "&idpermohonan=$senarai.idpermohonan")
		        #set ($by = "negeri")
		  	<tr>
		  	<td width="3%" class="$row">$cntBil.</td>
			  	#if ($senarai.peringkat == "terus")
				  	<td width="97%" class="$row">
				  		<a  href="javascript:cetakFailDoket('$para1','&template=$senarai.template', '$senarai.idmodule')" 
				  			class="style1"><b></b>	$senarai.keterangan</a>
				  	</td>
				#elseif($senarai.peringkat == "negeri")
				  	<td width="97%" class="$row">
				  		<a href="javascript:openSuratPegawaiNegeri('$senarai.idmodule','$para$para1','$senarai.peringkat','&template=$senarai.template')" 
				  			class="style1" ><b></b>	$senarai.keterangan</a>
				 	</td>
				 	
			 	#elseif($senarai.peringkat == "cukaimaklum")
				  	<td width="97%" class="$row">
				  		<a href="javascript:openSuratPegawaiNegeri('$senarai.idmodule','$para$para1','$senarai.peringkat','&template=$senarai.template')" 
				  			class="style1" ><b></b>	$senarai.keterangan</a>
				 	</td>
			 	#else
				  	<td width="97%" class="$row">
				  		<a href="javascript:openSuratPegawai('$senarai.idmodule','$para$para1','$senarai.peringkat','&template=$senarai.template')" 
				  			class="style1" ><b></b>	$senarai.keterangan</a>
				 	</td>
			 	#end
		   	</tr>	
			#end 
		</table>
	
	</fieldset>


</fieldset>

<script>
	
function doChangeSelect() {
	document.f1.command.value = "doChangeSelect";
	document.f1.action = "";
	document.f1.submit();
}

function openSuratPegawai(urli,param,laporan,tem){
	var negeri = document.${formName}.socNegeri.value;
	var unit = document.${formName}.socUnit.value;
	var daerah = document.${formName}.socDaerah.value;
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
		pnegeri = "&ID_NEGERI="+negeri;
	}else if(laporan=="unit"){
			if(unit=="" || unit=="0"){
			alert("Sila pilih \"Kementerian\" terlebih dahulu.");
			document.${formName}.socUnit.focus(); 
			return;
		}	
		punit = "&ID_PEJABAT="+unit;
	}else if(laporan=="daerah"){
		if(daerah==""){
			alert("Sila pilih \"Daerah\" terlebih dahulu.");
			document.${formName}.socDaerah.focus(); 
			return;
		}	
		pdaerah = "&ID="+daerah;
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
			ptahun = "&TAHUN="+mula_tahun;
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
		if(laporan=="urusantahun" || laporan=="urusanbulan"){
			if(mula_tahun==""){
				alert("Sila pilih \"Tarikh\" terlebih dahulu.");
				document.${formName}.txdMula.focus(); 
				return;
			}	
			if(tem == "LaporanPrestasiGadaian"){
				ptem += mula_bulan;			
			}
			ptahun = "&TAHUN="+mula_tahun+"&bulan="+mula_bulan;
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
	if(document.${formName}.socUnit.value=="0")
		return;
	doAjaxCall${formName}("PilihUnit");
}

</script>


