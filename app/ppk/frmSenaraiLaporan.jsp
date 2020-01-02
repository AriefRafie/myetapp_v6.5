 <style type="text/css">
  <!--
  .style1 {color: #0033FF}
  -->
  </style>
  <br>
  <br>
<fieldset>
<legend>Senarai Rekod/Laporan</legend>
<fieldset>
<table border="0" align="center" width="60%" >      
     <tbody> 
      <tr> 
        <td scope="row" align="left">&nbsp;Negeri </td>
        <td>:&nbsp;</td>
        <td>$selectNegeri</td>
      </tr>
      <tr> 
        <td scope="row" align="left">&nbsp;Unit </td>
        <td>:&nbsp;</td>
        <td>$selectUnit</td>
      </tr>
      <tr> 
        <td scope="row" align="left">&nbsp;Daerah </td>
        <td>:&nbsp;</td>
        <td>$selectDaerah</td>
      </tr>   
    	#set ($txdMula = "")
   		#set ($txdAkhir = "")
      
      <tr> 
        <td scope="row" align="left">&nbsp;Tarikh </td>
        <td>:&nbsp;</td>
        <td>
       		<label>
      			<input name="txdMula" type="text" id="txdMula" value="$txdMula" size="10" onblur="check_date(this);"/>
        	</label>
        	<a href="javascript:displayDatePicker('txdMula',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>

        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;hingga&nbsp;&nbsp;&nbsp;:&nbsp;
     		<label>
      			<input name="txdAkhir" type="text" id="txdAkhir" value="$txdAkhir" size="10" onblur="check_date(this);"/>
        	</label>
        	<a href="javascript:displayDatePicker('txdAkhir',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
		</td>
      </tr>  
   </tbody>
 </table>
</fieldset>	

<fieldset>
<legend>Rekod </legend>
	
<table border="0" align="center" width="100%" >
 	#set ($cnt = 0)
 	#foreach ( $senarai in $senaraiRekod )
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
  	<td width="3%" class="$row">$cnt.</td>
  	<td width="97%" class="$row">
  		<a href="javascript:openLaporan('$senarai.idmodule','$para','$senarai.peringkat','$senarai.template')" class="style1" >$senarai.keterangan</a>
 	</td>
   	</tr>	
	#end 
</table>
</fieldset>	
<fieldset>
<legend>Laporan</legend>
	
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
  	<td width="3%" class="$row">$cnt.</td>
  	<td width="97%" class="$row">
  		<a href="javascript:openLaporan('$senarai.idmodule','$para','$senarai.peringkat','$senarai.template')" class="style1" >$senarai.keterangan</a>
 	</td>
   	</tr>	
	#end 
</table>

</fieldset>

</fieldset>


#if($valLaporan=="1")
	<input type="hidden" name="LcanEdit" value="byPejabat">
#elseif($valLaporan=="2")
	<input type="hidden" name="LcanEdit" value="byNegeri">
#elseif($valLaporan=="3")
	<input type="hidden" name="LcanEdit" value="byUnit">			
#else
	<input type="hidden" name="LcanEdit" value="">	
#end

#if($valTempoh=="1")
	<input type="hidden" name="TcanEdit" value="byBulan">
	<input type="hidden" name="bulan2" value="">
	<input type="hidden" name="tahun2" value="">
#elseif($valTempoh=="2")
	<input type="hidden" name="TcanEdit" value="byTahun">
	<input type="hidden" name="bulan1" value="">
	<input type="hidden" name="bulan2" value="">
	<input type="hidden" name="tahun2" value="">
#elseif($valTempoh=="3")
	<input type="hidden" name="TcanEdit" value="byTempoh">
#else
	<input type="hidden" name="TcanEdit" value="">	
#end


<script>
	
	function doChangeSelect() {
		document.f1.command.value = "doChangeSelect";
		document.f1.action = "";
		document.f1.submit();

	}
	
	function doChangeTempoh() {
		document.f1.command.value = "doChangeSelect";
		document.f1.command2.value = "doChangeTempoh";
		document.f1.action = "";
		document.f1.submit();

	}
	
	function doChangeTempatBicara() {
		document.f1.command.value = "doChangeSelect";
		document.f1.command2.value = "doChangeTempoh";
		document.f1.command3.value = "doChangeTempatBicara";
		document.f1.action = "";
		document.f1.submit();

	}

	function openLaporan(urli,param,laporan,tem){
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
				alert("Sila pilih \"Unit\" terlebih dahulu.");
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
				
				//pbulanmula = "&BULANTAHUN="+mula_hari+"/"+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
				//pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
				pbulanmula = "&BULANTAHUN="+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
				pbulantamat = "&BULANTAHUNTMT="+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
					
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
				
				//pbulanmula = "&BULANTAHUN="+mula_hari+"/"+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
				//pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
				pbulanmula = "&BULANTAHUN="+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
				pbulantamat = "&BULANTAHUNTMT="+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
					
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
				
				//pbulanmula = "&BULANTAHUN="+mula_hari+"/"+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
				//pbulantamat = "&BULANTAHUNTMT="+akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
				pbulanmula = "&BULANTAHUN="+mula_bulan+"/"+mula_tahun+"&bulan="+mula_bulan;
				pbulantamat = "&BULANTAHUNTMT="+akhir_bulan+"/"+akhir_tahun+"&bulantamat="+akhir_bulan;
					
				ptahun = "&TAHUN="+mula_tahun;
			}		
			
			if(laporan=="urusanselang"){
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
					
				ptahun = "&TAHUN="+mula_tahun+"&TAHUN_TAMAT="+akhir_tahun;
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


