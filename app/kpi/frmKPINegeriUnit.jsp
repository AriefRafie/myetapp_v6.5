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
	<strong>PILIHAN</strong></legend>
	<table width="100%" border="0" align="center">
		<tr>
			<td scope="row" align="right">Urusan</td>
			<td width="1%" scope="row">:</td>		
			<td >$socsuburusan</td>
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

#set ($bilColumn = 0)
#foreach ( $kesan in $senaraidesc )
	#if ( $kesan.giliran == 2 || $kesan.giliran == 3 || $kesan.giliran == 4 )
		#set ($bilColumn = $bilColumn+1)	
	#end	
#end
#set ($bilColumns = ($bilColumn*3))
<fieldset>
<legend><strong>MAKLUMAT KPI</strong></legend>
	<!--<table width="100%" border="0" align="center" >-->
  	<table height="100%" width="100%" border="0" align="left">
	  	<tr class="table_headerkpi">
			<td width="35%" align="center" rowspan=3><strong>NEGERI</strong></td>
			<td width="5%" align="center" rowspan=3><strong>Jumlah permohonan baru diterima</strong></td>
			
			<td width="20%" colspan="$bilColumns" align="center"><strong>Bilangan & Peringkat Menunggu</strong></td>
			
			<td width="40%" colspan=3 rowspan=2 align="center" ><strong>Jumlah permohonan diselesaikan</strong></td>
		</tr>
	
		 <tr class="table_headerkpi">
			#foreach ( $kesan in $senaraidesc )
				#set ($countk = $countk+1)
				#if ( $kesan.giliran == 2 || $kesan.giliran == 3 || $kesan.giliran == 4 )				
						<!--<td  width="4%" colspan=3 align="center">$kesan.keteranganheader</td>-->
					<td  colspan=3 align="center">$kesan.keteranganheader</td>
				#end	
			#end	
			#if ($bilColumn == 0)
				<td  colspan=3 align="center"></td>				
			#end
		</tr> 
		
		<tr class="table_headerkpi">
			#foreach ( $kesan in $senaraidesc )
				#set ($countk = $countk+1)
				#if ( $kesan.giliran == 2 )
				<td  width="2%" align="center"><5</td>		
				<td  width="2%" align="center">5-14</td>
				<td  width="2%" align="center">>15</td>
				#elseif ($kesan.giliran == 3)
				<td  width="2%" align="center"><7</td>		
				<td  width="2%" align="center">8-14</td>
				<td  width="2%" align="center">>15</td>
				#elseif ($kesan.giliran == 4)
				<td  width="2%" align="center"><30</td>		
				<td  width="2%" align="center">31-60</td>
				<td  width="2%" align="center">>60</td>
				#end
			#end
			#if ($bilColumn == 0)
				<td  width="2%" align="center"></td>		
			#end		
			<td width="10%" align="center" ><strong>Bil.</strong></td>
			<td width="15%" align="center" ><strong>Kecekapan Dalaman JKPTG(%)</strong></td>
			<td width="15%"align="center" ><strong>Purata Kitaran Masa(Hari)</strong></td>			
		</tr>
<!--End of Header-->	
	    		$mengikutnegeri
	    
	    #if ($bilColumn == 0)
 		<tr> 
			<td align="center" colspan="6" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
		</tr>
    	#end
	</table>
    <!--</table>-->
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
	/*var urusan = document.${formName}.socUrusan.value;  
	if(urusan == ""){
		alert("Sila pilih urusan terlebih dahulu.");
	    return;
	}
	if(akhir== "" || mula== ""){
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

/*function doChangeUrusan() {
	if(document.${formName}.socUrusan.value=="")
		return;
	document.${formName}.pagemode.value = "view";
	doAjaxCall${formName}("byurusandefault");
}*/

function doChangeSubUrusan() {
	if(document.${formName}.socSuburusan.value=="0")
		return;
	document.${formName}.pagemode.value = "bysuburusan";
 	doAjaxCall${formName}("pilihsuburusan");
}
</script>
