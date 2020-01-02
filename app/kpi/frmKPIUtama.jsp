<style type="text/css"> 
  <!--
  .style1 {color: #0033FF}
  -->
</style>
<fieldset>
	#set ($txdMula = "")
    #set ($txdAkhir = "")
    #if($tarikhAkhir != "")
    	#set ($txdMula = $tarikhMula)
      	#set ($txdAkhir = $tarikhAkhir)     
  	#end

<legend>
<strong>Pilihan</strong></legend>
<table width="100%" border="0" align="center">
  	<tr>
		<td scope="row" align="right">Urusan</td>
		<td width="1%" scope="row">:</td>		
		<td >$socurusan</td>
	</tr>
	<tr>
		<td scope="row" align="right">Sub Urusan</td>
		<td width="1%" scope="row">:</td>		
		<td >$socsuburusan</td>
	</tr>
  	<tr>
    	<td scope="row" align="right">Negeri</td>
   		<td width="1%" scope="row">:</td>
    	<td> $selectNegeri </td>
  	</tr>
    <tr>
    <td scope="row" align="right">Tarikh Mula</td>
    <td width="1%" scope="row">:</td>
    <td>
      <label>
      <input name="txdMula" type="text" id="txdMula" value="$txdMula" size="10" />
        </label>
        <a href="javascript:displayDatePicker('txdMula',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>    </td>
  </tr>
  <tr>
    <td scope="row" align="right">Tarikh Akhir</td>
    <td width="1%" scope="row">:</td>
    <td>
      <label>
      <input name="txdAkhir" type="text" id="txdAkhir" value="$txdAkhir" size="10" />
        </label>
        <a href="javascript:displayDatePicker('txdAkhir',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>    </td>
  </tr>
  <tr>
    <td colspan="2" scope="row">
      <label></label>    </td>
    <td>
      <label>
        <input type="button" name="cmdCari" id="cmdCari" value="Papar" onclick="janaLaporan()"/>
        <input type="reset" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" />
        </label>    
   </td>
  </tr>
</table>
</fieldset>
<fieldset>
 <legend><strong>Maklumat KPI</strong></legend>
 <table width="100%" border="0" align="center" >
  <table height="100%" width="100%" border="0" align="left">
	<tr class="table_headerkpi">
		<td width="35%" height="60" align="center"><strong>AKTIVITI</strong></td>
		<td width="5%" height="60" align="center"><strong>Sasaran Masa (hari)</strong></td>
		<td width="5%" height="60" align="center"><strong>Bilangan Aktiviti</strong></td>
		<td width="5%" height="60" align="center"><strong>Jumlah Masa Sebenar (hari)</strong></td>
		<td width="5%" height="60" align="center"><strong>Purata Masa (hari)</strong></td>
		<td width="5%" height="60" align="center"><strong>Kecekapan</strong></td>
		<td width="40%" align="center" colspan=2><strong>KEBERKESANAN</strong></td>
	
		
	
	</tr>
	<tr class="table_headerkpi">
		<td width="35%" align="center"><strong>A</strong></td>
		<td width="5%" align="center"><strong>B</strong></td>
		<td width="5%" align="center"><strong>C</strong></td>
		<td width="5%" align="center"><strong>D</strong></td>
		<td width="5%" align="center"><strong>E=D/C</strong></td>
		<td width="5%" align="center"><strong>F=B/E(%)</strong></td>
		<td width="20%" align="center"><strong>Permohonan Menunggu</strong></td>
		<td width="20%" align="center"><strong>Bilangan</strong></td>
		
	</tr>
	#set ($count_span = 1)
	#foreach ( $senarai in $senaraidesc )
		#set ($count_span = $count_span+1)
	#end
	
	</tr>
		<tr class="table_header">
		<td colspan=6></td>
		<td bgcolor="#bc861b" width="20%" valign="top" colspan="2" rowspan="$count_span">
		
			<table width="100%" border="0" >
			#set ($countk = 0)
			#foreach ( $kesan in $kesans )
				#set ($countk = $countk+1)
				#set( $i = $velocityCount )
				#if ( ($i % 2) != 1 )
					#set( $row = "row2" )
				#else
					#set( $row = "row1" )
				#end
					<tr>
						<td width="50%" align="left" class=row2><strong>$countk.</strong>$kesan.menunggu</td>
						<td width="50%" align="center" class=row2><strong></strong>$kesan.bilangan</td>
					</tr>
			#end
			    #if ($countk == 0)
					<tr> 
						<td colspan="2" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
					</tr>
			    #end
			    			    
			    <tr>
			    	<td align="left"  colspan="2" rowspan="$count_span">$terperinci</td>
			    </tr>
		
			</table>
		</td>
	
	</tr>
	#set ($count = 0)
	#set ($countg = 0)
	#set ($countxg = 0)
	
#foreach ( $senarai in $senaraidesc )
		#set ($count = $count+1)
	#set( $i = $velocityCount )
	#if ( ($i % 2) != 1 )
		#set( $row = "row2" )
	#else
		#set( $row = "row1" )
	#end
		#if ( $senarai.giliran == 2 || $senarai.giliran == 3 || $senarai.giliran == 4 )
			#set ($countg = $countg+1)
			#set ($countA = $countg+$countxg)
		<tr> 
			<td align="left" colspan=4 class=row1><strong>$count.</strong>$senarai.keterangan</td>
			<td align="center" colspan=2 class=row1>
			#if ($senarai.pilihan == 1)
           	(pilihan)
           	#end		
			</td>					
		</tr>
		#else
		#set ($countxg = $countxg+1)
		#set ($countAi = $countg+$countxg)
		
		<tr>
			<td width="35%" align="left" class=row2><strong>$count.</strong>$senarai.keterangan</td>
			<td width="5%" align="center" class=row2><strong></strong>$senarai.sasaranmasa<br>
			#if ($senarai.pilihan == 1)
           	</br>(pilihan)
           	#end
       		</td>
			<td width="5%" align="center" class=row2><strong>$senarai.aktiviti</strong></td>
			<td width="5%" align="center" class=row2><strong>$senarai.hari1f</strong></td>
				#set ($purata = $senarai.hari1/$senarai.aktiviti)	
			<td width="5%" align="center" class=row2><strong>$senarai.puratanew</strong></td>
				#set ($peratus =($senarai.sasaranmasa/$purata)*100)	
			
			<td width="5%" align="center" class=row2><strong>$senarai.peratusnew</strong></td>
						
		</tr>
		#end
	#end
    #if ($count == 0)
 		<tr> 
			<td colspan="8" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
		</tr>
    #end
    
    </table>
	<input type="hidden" name="pagemode">
    
 </fieldset>
 <script>
  
 function doChangeNegeri() {
 	if(document.${formName}.idnegeri.value==""||document.${formName}.socUrusan.value=="")
 		return;       			
	
	doAjaxCall${formName}("pilihannegeri");
}

function janaLaporan(){
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
	var urusan = document.${formName}.socUrusan.value;  
	if(urusan == ""){
		alert("Sila pilih urusan terlebih dahulu.");
	    return;
	}
	/*if(akhir== "" || mula== ""){
		alert("Sila pastikan Tarikh Akhir tidak melebihi dari Tarikh Mula.");
	    return;
	}*/
	if(akhir<mula){
		alert("Sila pastikan Tarikh Akhir tidak melebihi dari Tarikh Mula.");
	    return;
	}
	if(akhir>tarikhsemasa){
		alert("Sila pastikan Tarikh Akhir tidak melebihi dari Tarikh Semasa.");
	    return;
	}	
	doAjaxCall${formName}("janalaporan");
}

function showWindow(servlet){
	var url = "../x/${securityToken}/"+servlet;
    var hWnd = window.open(url,'printuser','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
    	hWnd.opener = document.window;
  	if (hWnd.focus != null) hWnd.focus();
}

function doChangeUrusan() {
	if(document.${formName}.socUrusan.value=="")
		return;
	document.${formName}.pagemode.value = "view";
	doAjaxCall${formName}("byurusandefault");
}

function doChangeSubUrusan() {
	if(document.${formName}.socSuburusan.value=="")
		return;
	document.${formName}.pagemode.value = "bysuburusan";
 	doAjaxCall${formName}("byurusandefault");
}
</script>
