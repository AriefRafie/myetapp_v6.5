<style type="text/css"> 
  <!--
  .style1 {color: #0033FF}
  -->
</style>
<fieldset>
      #set ($txdMula = "")
      #set ($txdAkhir = "")

<legend>
<strong>Pilihan</strong></legend>
<table width="100%" border="0" align="center">
<!--  <tr>
    <td width="29%" scope="row" align="right">No Fail</td>
    <td width="1%" scope="row">:</td>
    <td width="70%">
      <label>
      <input name="txtNoFail" type="text" id="txtNoFail" onblur="this.value=this.value.toUpperCase()" value="$txtNoFail" size="44" />
        </label>
      <input type="hidden" name="idFail" />
      <input type="hidden" name="action" value="$action" />
      <input type="hidden" name="mode" /></td>
  </tr>
  <tr>
    <td scope="row" align="right" valign="top">Tajuk Fail</td>
    <td width="1%" scope="row" valign="top">:</td>
    <td>
      <label>
      <textarea name="txtTajukFail" cols="41" onblur="this.value=this.value.toUpperCase()" id="txtTajukFail">$txtTajukFail</textarea>
        </label>    </td>
  </tr>
  <tr>
    <td scope="row" align="right">Negeri</td>
   <td width="1%" scope="row">:</td>
    <td>
        $selectNegeriD    </td>
  </tr>
  <tr>
    <td scope="row" align="right">Seksyen</td>
    <td width="1%" scope="row">:</td>
    <td>
     
     $selectSeksyenD</td>
  </tr>
  <tr>
    <td scope="row" align="right">Status</td>
    <td width="1%" scope="row">:</td>
    <td>
     
    $selectStatusD</td>
  </tr>
  -->
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
        <input type="button" name="cmdCari" id="cmdCari" value="Papar" onclick="paparLaporan()"/>
        <input type="reset" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" />
        </label>    </td>
  </tr>
</table>
</fieldset>
#set ($bilColumn = 0)

#foreach ( $kesan in $senaraidesc )
	##if ($kesan.giliran == 1 )
	#if ( $kesan.giliran == 2 || $kesan.giliran == 3 || $kesan.giliran == 4 )
		#set ($bilColumn = $bilColumn+1)	
	#end	
#end
#set ($bilColumns = ($bilColumn*3))
<fieldset>
 <legend><strong>Maklumat KPI</strong></legend>
 <table width="100%" border="0" align="center" >
	<table height="100%" width="100%" border="0" align="left">
	  	<tr class="table_headerkpi">
			<td width="35%" align="center" rowspan=3><strong>NEGERI</strong></td>
			<td width="5%" align="center" rowspan=3><strong>Jumlah permohonan baru diterima</strong></td>
			
			<td width="20%" colspan="$bilColumns" align="center"><strong>Bilangan & Peringkat Menunggu</strong></td>
			
			<td width="40%" colspan=3 rowspan=2 align="center" ><strong>Jumlah permohonan diselesaikan</strong></td>
		</tr>
		
		<tr class="table_headerkpi">
			<!--<td width="35%" align="center"><strong>NEGERI</strong></td>
			<td width="5%" align="center"><strong>Jumlah permohonan baru diterima</strong></td>-->
			#foreach ( $kesan in $senaraidesc )
				#set ($countk = $countk+1)
				##if ($kesan.giliran == 1 )
				#if ( $kesan.giliran == 2 || $kesan.giliran == 3 || $kesan.giliran == 4 )
				
				<td  width="4%" colspan=3 align="center">$kesan.keteranganheader</td>
				#end	
			#end	
			<!--
			<td width="10%" align="center" ><strong>Bil.</strong></td>
			<td width="15%" align="center" ><strong>Kecekapan Dalaman JKPTG(%)</strong></td>
			<td width="15%"align="center" ><strong>Purata Kitaran Masa(Hari)</strong></td>-->
			
		</tr>
		
		<tr class="table_headerkpi">
			<!--
			<td width="35%" align="center"><strong>NEGERI</strong></td>
			<td width="5%" align="center"><strong>Jumlah permohonan baru diterima</strong></td>
			-->
			##foreach ( $y in [1..$bilColumn] )
			#foreach ( $kesan in $senaraidesc )
				#set ($countk = $countk+1)
				#if ( $kesan.giliran == 2 )
				<td  width="1%" align="center"><5</td>		
				<td  width="1%" align="center">5-14</td>
				<td  width="2%" align="center">>15</td>
				#elseif ($kesan.giliran == 3)
				<td  width="1%" align="center"><7</td>		
				<td  width="1%" align="center">8-14</td>
				<td  width="2%" align="center">>15</td>
				#elseif ($kesan.giliran == 4)
				<td  width="1%" align="center"><30</td>		
				<td  width="1%" align="center">31-60</td>
				<td  width="2%" align="center">>60</td>
				#end
			#end
					
			<td width="10%" align="center" ><strong>Bil.</strong></td>
			<td width="15%" align="center" ><strong>Kecekapan Dalaman JKPTG(%)</strong></td>
			<td width="15%"align="center" ><strong>Purata Kitaran Masa(Hari)</strong></td>			
		</tr>
		
		<!--
		<tr class="table_header">
			
			<td width="35%" align="center"><strong>PERLIS</strong></td>
			<td width="5%" align="center"><strong>5</strong></td>
			
			#foreach ( $y in [1..$bilColumn] )
				#set ($countk = $countk+1)
				
				<td  width="1%" align="center"><7</td>		
				<td  width="1%" align="center">8-14</td>
				<td  width="2%" align="center">>15</td>
			#end	
		
			<td width="10%" align="center" ><strong>1.</strong></td>
			<td width="15%" align="center" ><strong>85%</strong></td>
			<td width="15%"align="center" ><strong>15</strong></td>			
		</tr>
		-->
		$mengikutnegeri
		        	
		<tr>
			<td width="100%" align="center" colspan=($bilColumns+4)>
				<input type="button" name="cmdPrint" id="cmdPrint" value="Cetak" onclick="javascript:print()">
			</td>
			</tr>
	</table>

    
    </table> 
 </fieldset>
 
 <script>
 	
	function paparLaporan(){
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
	
		doAjaxCall${formName}("negerimengikuttarikh");
}

function showWindow(servlet){

          //var url = "../x/${securityToken}/ekptg.view.utils.FrmNegeri";
          var url = "../x/${securityToken}/"+servlet;
          var hWnd = window.open(url,'printuser','width=800,height=500, resizable=yes,scrollbars=yes');
          if ((document.window != null) && (!hWnd.opener))
            hWnd.opener = document.window;
          if (hWnd.focus != null) hWnd.focus();
}

</script>