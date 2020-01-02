
#set ($vnamaAgensixx = $namaPejabat.ID_PEJABAT)

#if ($bulan1 == "01" )
	#set ($valBulan1 = $bulan1)
	#set ($desBulan1 = "JANUARI")
#elseif ($bulan1 == "02" )
	#set ($valBulan1 = $bulan1)
	#set ($desBulan1 = "FEBRUARI")
#elseif ($bulan1 == "03" )
	#set ($valBulan1 = $bulan1)
	#set ($desBulan1 = "MAC")
#elseif ($bulan1 == "04" )
	#set ($valBulan1 = $bulan1)
	#set ($desBulan1 = "APRIL")
#elseif ($bulan1 == "05" )
	#set ($valBulan1 = $bulan1)
	#set ($desBulan1 = "MEI")
#elseif ($bulan1 == "06" )
	#set ($valBulan1 = $bulan1)
	#set ($desBulan1 = "JUN")
#elseif ($bulan1 == "07" )
	#set ($valBulan1 = $bulan1)
	#set ($desBulan1 = "JULAI")
#elseif ($bulan1 == "08" )
	#set ($valBulan1 = $bulan1)
	#set ($desBulan1 = "OGOS")
#elseif ($bulan1 == "09" )
	#set ($valBulan1 = $bulan1)
	#set ($desBulan1 = "SEPTEMBER")
#elseif ($bulan1 == "10" )
	#set ($valBulan1 = $bulan1)
	#set ($desBulan1 = "OKTOBER")
#elseif ($bulan1 == "11" )
	#set ($valBulan1 = $bulan1)
	#set ($desBulan1 = "NOVEMBER")
#elseif ($bulan1 == "12" )
	#set ($valBulan1 = $bulan1)
	#set ($desBulan1 = "DISEMBER")
#else
	#set ($bulan1 = "")
#end

#if ($bulan2 == "01" )
	#set ($valBulan2 = $bulan2)
	#set ($desBulan2 = "JANUARI")
#elseif ($bulan2 == "02" )
	#set ($valBulan2 = $bulan2)
	#set ($desBulan2 = "FEBRUARI")
#elseif ($bulan2 == "03" )
	#set ($valBulan2 = $bulan2)
	#set ($desBulan2 = "MAC")
#elseif ($bulan2 == "04" )
	#set ($valBulan2 = $bulan2)
	#set ($desBulan2 = "APRIL")
#elseif ($bulan2 == "05" )
	#set ($valBulan2 = $bulan2)
	#set ($desBulan2 = "MEI")
#elseif ($bulan2 == "06" )
	#set ($valBulan2 = $bulan2)
	#set ($desBulan2 = "JUN")
#elseif ($bulan2 == "07" )
	#set ($valBulan2 = $bulan2)
	#set ($desBulan2 = "JULAI")
#elseif ($bulan2 == "08" )
	#set ($valBulan2 = $bulan2)
	#set ($desBulan2 = "OGOS")
#elseif ($bulan2 == "09" )
	#set ($valBulan2 = $bulan2)
	#set ($desBulan2 = "SEPTEMBER")
#elseif ($bulan2 == "10" )
	#set ($valBulan2 = $bulan2)
	#set ($desBulan2 = "OKTOBER")
#elseif ($bulan2 == "11" )
	#set ($valBulan2 = $bulan2)
	#set ($desBulan2 = "NOVEMBER")
#elseif ($bulan2 == "12" )
	#set ($valBulan2 = $bulan2)
	#set ($desBulan2 = "DISEMBER")
	
#else
	#set ($bulan2 = "")
#end

<style id="Book2_12161_Styles">
<!--table
	{mso-displayed-decimal-separator:"\.";
	mso-displayed-thousand-separator:"\,";}
.xl1512161
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Calibri, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:general;
	vertical-align:bottom;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl6512161
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Calibri, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:center;
	vertical-align:bottom;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl6612161
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Calibri, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:general;
	vertical-align:middle;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl6712161
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:10.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:Calibri, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl6812161
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Calibri, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl6912161
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Calibri, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl7012161
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Calibri, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl7112161
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:9.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:Calibri, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:center;
	vertical-align:bottom;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl7212161
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Calibri, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:general;
	vertical-align:bottom;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl7312161
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Calibri, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:center;
	vertical-align:bottom;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl7412161
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:10.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:Calibri, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:center;
	vertical-align:bottom;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl7512161
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:Calibri, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl7612161
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:Calibri, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:general;
	vertical-align:middle;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl7712161
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:black;
	font-size:11.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:Calibri, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl7812161
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:white;
	font-size:9.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:Calibri, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:center;
	vertical-align:top;
	border:.5pt solid windowtext;
	background:#0070C0;
	mso-pattern:black none;
	white-space:nowrap;}
.xl7912161
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:white;
	font-size:9.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:Calibri, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	background:#0070C0;
	mso-pattern:black none;
	white-space:nowrap;}
.xl8012161
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:white;
	font-size:9.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:Calibri, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:center;
	vertical-align:top;
	border:.5pt solid windowtext;
	background:#0070C0;
	mso-pattern:black none;
	white-space:normal;}
.xl8112161
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:white;
	font-size:9.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:Calibri, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:center;
	vertical-align:bottom;
	border:.5pt solid windowtext;
	background:#0070C0;
	mso-pattern:black none;
	white-space:nowrap;}
.xl8212161
	{padding-top:1px;
	padding-right:1px;
	padding-left:1px;
	mso-ignore:padding;
	color:white;
	font-size:11.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:Calibri, sans-serif;
	mso-font-charset:0;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	background:#0070C0;
	mso-pattern:black none;
	white-space:nowrap;}
-->
</style>

<form name="f1" method="post" >
#if ($cetak != "cetak")
<br/>
#end
<fieldset>
<legend>Laporan e-Hutang </legend>
<p></p>
<input type="hidden" name="jenisLaporan2" id="jenisLaporan2"/>

#if ($cetak != "cetak")
#if ($role != "(INTEGRASI)UsersAgensi")
<fieldset>
<table border="0" align="center" width="100%">
      <tr> 
      	
        <td width="28%" height="24" scope="row" align="right">Sila Pilih&nbsp;&nbsp;</td>
        <td width="72%"><input type="radio" name="jenisLaporan" id="jenisLaporan" value="1" $check1A Onclick="javascript:cetakLaporaneHutangAgensibyPPK_step1()" >&nbsp;Laporan Berdasarkan Agensi</td>
      </tr>
       
      <tr> 
        <td scope="row" align="right">&nbsp;</td>
        <td ><input type="radio" disabled='disabled' name="jenisLaporan" id="jenisLaporan" value="2" $check1B Onclick="javascript:cetakLaporaneHutangAgensibyPPK_step1()" >&nbsp;Laporan Berdasarkan Individu</td>
      </tr>
      
      
</table>
</fieldset>
<br/>
<!-- 
#if ($step2 == "step2")
<br/>
<fieldset>
<table border="0" align="center" width="60%" >
   	  <tr> 
        <td width="16%" height="24px" scope="row" align="right" valign="center">Sila pilih</td>
       	<td width="84%">
       	<input type="hidden" name="sorTempoh2" id="sorTempoh2"/> 
       	<input type="hidden" name="jenisLaporan" id="jenisLaporan" >	
       	<input type="radio" id="sorTempoh" name="sorTempoh" onclick="doChangeTempoh()" value="1" $check1 $sordisabledA />&nbsp;Bulan&nbsp;&nbsp;
       	<input type="radio" id="sorTempoh" name="sorTempoh" onclick="doChangeTempoh()" value="2" $check2 $sordisabledB />&nbsp;Tahun&nbsp;&nbsp;
       	<input type="radio" id="sorTempoh" name="sorTempoh" onclick="doChangeTempoh()" value="3" $check3 $sordisabledC />&nbsp;Tempoh</td>
      </tr>	
</table>
</fieldset>
#end
 -->



#end

#if (($step2 == "step2") || ($role == "(INTEGRASI)UsersAgensi"))
<fieldset>
<table border="0" align="center" width="60%" >
   	  <tr> 
        <td width="16%" height="24px" scope="row" align="right" valign="center">Sila pilih</td>
       	<td width="84%">
       
       	
       	<input type="radio" id="sorTempoh" name="sorTempoh" onclick="doChangeTempoh()" value="1" $check1 $sordisabledA />&nbsp;Bulan&nbsp;&nbsp;
       	<input type="radio" id="sorTempoh" name="sorTempoh" onclick="doChangeTempoh()" value="2" $check2 $sordisabledB />&nbsp;Tahun&nbsp;&nbsp;
       	<input type="radio" id="sorTempoh" name="sorTempoh" onclick="doChangeTempoh()" value="3" $check3 $sordisabledC />&nbsp;Tempoh</td>
      </tr>	
</table>


</fieldset>
#end
<br/>


#if ($role != "(INTEGRASI)UsersAgensi")
#if (($check1A == "checked") && ($check1 != "" || $check2 != "" || $check3 != ""))
<fieldset>
<table border="0" align="center" width="60%" >
   	  <tr> 
        <td width="16%" height="24px" scope="row" align="right" valign="center">Sila pilih agensi&nbsp;&nbsp;</td>
       	<td width="84%">
       #set ($idAgensix = "")
       
       	<select name="namaAgensi" style="width: 100px;">
			
       	#if ($namaAgensibyIdPejabat != "")
       	#foreach ($vnamaAgensibyIdPejabat in $namaAgensibyIdPejabat)
       		#set ($idAgensix = $vnamaAgensibyIdPejabat.idAgensi)
        	<option value="$vnamaAgensibyIdPejabat.idAgensi">$vnamaAgensibyIdPejabat.namaAgensi</option>
        #end
 		#end

			<option value="">SILA PILIH</option>
			#foreach ($vnamaAgensi in $namaAgensi)		
				#set ($vidAgensi = $vnamaAgensi.idAgensi)
				#set ($v2namaAgensi = $vnamaAgensi.namaAgensi)
				#if ($idAgensix != $vidAgensi)
					<option value="$vidAgensi">$v2namaAgensi</option>
				#end
			#end
			
		</select>
		
      </tr>	
</table>

</fieldset>
<br/>
#end
#end

#if(($!disA=="show") || ($!disB=="show") || ($!disC=="show"))
<fieldset>
<table border="0" align="center" width="60%" >      
     <tbody> 
      <tr> 
	   #if($disA=="show")
        <td scope="row" align="left">&nbsp;Bulan </td>
        <td>:&nbsp;</td>
        <td><select name="bulan1" style="width: 100px;">
        #if ($bulan1 != "")
        	<option value="$valBulan1">$desBulan1</option>
 		#end
		<option value="">SILA PILIH</option>
		<option value="01">JANUARI</option>
		<option value="02">FEBRUARI</option>
		<option value="03">MAC</option>
		<option value="04">APRIL</option>
		<option value="05">MEI</option>
		<option value="06">JUN</option>
		<option value="07">JULAI</option>
		<option value="08">OGOS</option>
		<option value="09">SEPTEMBER</option>
		<option value="10">OKTOBER</option>
		<option value="11">NOVEMBER</option>
		<option value="12">DISEMBER</option>
		</select>
	  #if($disB=="show")
		&nbsp;&nbsp; &nbsp;hingga&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
		<select name="bulan2" style="width: 100px;">	
		#if ($bulan2 != "")
        	<option value="$valBulan2">$desBulan2</option>
 		#end
		<option value="">SILA PILIH</option>
		<option value="01">JANUARI</option>
		<option value="02">FEBRUARI</option>
		<option value="03">MAC</option>
		<option value="04">APRIL</option>
		<option value="05">MEI</option>
		<option value="06">JUN</option>
		<option value="07">JULAI</option>
		<option value="08">OGOS</option>
		<option value="09">SEPTEMBER</option>
		<option value="10">OKTOBER</option>
		<option value="11">NOVEMBER</option>
		<option value="12">DISEMBER</option>
		</select></td>
		
	  #end	
	  #end      </tr>
      
      
      #if($disC=="show" || ($disA=="show"))
      <tr> 
        <td scope="row" align="left">&nbsp;Tahun </td>
        <td>:&nbsp;</td>
        <td>
        <select id="yeardropdown" name="tahun1" style="width: 100px;">
        	#if ($tahun1 != "")
        		<option value="$tahun1">$tahun1</option>
 			#end
       		<option value="">SILA PILIH</option>
       		#set($tahunStart = 2018)
			#set($tahunEnd = 1970)
			#set($range = [$tahunStart..$tahunEnd])
			#foreach($i in $range)
  				 #if ($i != $tahun1)
  					 <option value="$i">$i</option>
  				 #end
			#end

        </select>
          #if($disB=="show")&nbsp;&nbsp;&nbsp;&nbsp;hingga&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;
          <select id="yeardropdown2" name="tahun2" style="width: 100px;">
          	#if ($tahun2 != "")
        		<option value="$tahun2">$tahun2</option>
 			#end
 			
         	<option value="">SILA PILIH</option>
         	#set($tahunStart = 2018)
			#set($tahunEnd = 1970)
			#set($range = [$tahunStart..$tahunEnd])
			#foreach($i in $range)
  				 #if ($i != $tahun1)
  					 <option value="$i">$i</option>
  				 #end
			#end

		</select>#end</td>
    </tr>
      #end
   </tbody>
 </table>
 <br/>
 <br/>

 <table border="0" align="center" width="60%" > 
	#if ($role != "(INTEGRASI)UsersAgensi")
		<tr> 
			<td align="center"><input type="button" name="cmdCetak" value="Previu" onclick="javascript:cetakLaporaneHutangAgensi()"></td>
		</tr>
	#else	
		<tr> 
			<td align="center"><input type="button" name="cmdCetak" value="Previu" onclick="javascript:cetakLaporaneHutangAgensi2()"></td>
		</tr>
 	#end
</table>
</fieldset>	
#end
#end
<!-- End untuk button cetak -->
#set ($vlistFailPusaka = "")
#if ($LaporaneHutangAgensi2.size() > 0)
#foreach ($vlistFailPusaka in $LaporaneHutangAgensi2)
	#set ($listFailPusaka = $vlistFailPusaka.JUMLAH_FAIL)
#end
#end

#if ($buttonCetak=="buttonCetak")
#if ($LaporaneHutangAgensibyPPK.size() > 0)

<fieldset>
<p align="center">Laporan e-Hutang 
#if ($jenisLaporan2 == "1")
Agensi 
#if ($role != "(INTEGRASI)UsersAgensi")
	$v2namaAgensi
#end
#end
#if ($jenisLaporan2 == "2")
Individu
#end
<br/>
#if ($jenisLaporan2 == "1")
	#if ($sorTempoh == "1")
		Bulan $desBulan1 $tahun1
	#elseif ($sorTempoh == "2")
		Tahun $tahun1
	#elseif ($sorTempoh == "3")
		$desBulan1 $tahun1 hinggas $desBulan2 $tahun2
	#end
#end

</p>
<table align="center" border=0 cellpadding=0 cellspacing=0  style='border-collapse:
 collapse;table-layout:fixed'>
		<tr bgcolor="#0070C0">
          <!-- <td scope="row" width="5%" align="center"><strong>Bil.</strong></td> -->
          <td class="xl6812161" width="20%" align="center"><font color="#FFFFFF">Bilangan Penghutang</font></td>
          <td class="xl6812161" width="20%" align="center"><font color="#FFFFFF">Bilangan Akaun Hutang</font></td>
          <td class="xl6812161" width="20%" align="center"><font color="#FFFFFF">Jumlah Hutang Keseluruhan</font></td>      
          <td class="xl6812161" width="20%" align="center"><font color="#FFFFFF">Jumlah Baki Hutang</font></td>
          <td class="xl6812161" width="20%" align="center"><font color="#FFFFFF">Bilangan Penghutang Yang Mempunyai Rekod Di BPP</font></td>
          
        </tr>
		#set ($list = "")
        #set ( $count = 0 )
        
        #foreach ($list in $LaporaneHutangAgensibyPPK)
        #set ( $count = $count + 1 )
        #if ($count == '')
        #set( $row = "row1" )
        #elseif (($count % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        
        
		<tr>
			<!-- <td class="$row" align="center">$count</td> -->
			<td class="xl6812161" style="border-top:none" align="center">$list.JUMLAH_PENGHUTANG </td>
			<td class="xl6812161" style="border-top:none;border-left:none" align="center">$list.BILANGAN_AKAUN_HUTANG</td>
			<td class="xl6812161" style="border-top:none;border-left:none" align="right">$list.NILAI_HUTANG</td>		
			<td class="xl6812161" style="border-top:none;border-left:none" align="right">$list.BAKI_HUTANG</td>
			<td class="xl6812161" style="border-top:none;border-left:none" align="center">$!list.BILANGAN_FAIL_PUSAKA</td>		
		</tr>
		#end

</table>
<br/>
<p align="center"><input name="cmdCetak" value="Cetak" onclick="myFunction()" type="button"></p>


<br/>
</fieldset>
#else

<p align="center">Laporan e-Hutang
#if ($jenisLaporan2 == "1")
Agensi $v2namaAgensi
#end
#if ($jenisLaporan2 == "2")
Individu
#end
<br/>
#if ($jenisLaporan2 == "1")
	#if ($sorTempoh == "1")
		Bulan $desBulan1 $tahun1
	#elseif ($sorTempoh == "2")
		Tahun $tahun1
	#elseif ($sorTempoh == "3")
		$desBulan1 $tahun1 hingga $desBulan2 $tahun2
	#end
#end

</p>
<fieldset>
<table align="center" border=0 cellpadding=0 cellspacing=0  style='border-collapse:
 collapse;table-layout:fixed'>
		<tr bgcolor="#0070C0">
          <!-- <td scope="row" width="5%" align="center"><strong>Bil.</strong></td> -->
          <td class="xl6812161" width="20%" align="center"><font color="#FFFFFF">Bilangan Penghutang</font></td>
          <td class="xl6812161" width="20%" align="center"><font color="#FFFFFF">Bilangan Akaun Hutang</font></td>
          <td class="xl6812161" width="20%" align="center"><font color="#FFFFFF">Jumlah Hutang Keseluruhan</font></td>      
          <td class="xl6812161" width="20%" align="center"><font color="#FFFFFF">Jumlah Baki Hutang</font></td>
          <td class="xl6812161" width="20%" align="center"><font color="#FFFFFF">Bilangan Penghutang Yang Mempunyai Rekod Di BPP</font></td>
          
        </tr>
		#set ($list = "")
        #set ( $count = 0 )

        #set ( $count = $count + 1 )
        #if ($count == '')
        #set( $row = "row1" )
        #elseif (($count % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end

        <tr>
			<td td class="xl6812161" style="border-top:none" colspan="6">TIADA REKOD DITEMUI1</td>		
		</tr>
		

</table>
<br/>
<p align="center"><input name="cmdCetak" value="Cetak" onclick="myFunction()" type="button"></p>


<br/>
<br/>
</fieldset>
#end
#end

#if ($buttonCetak=="buttonCetak")
#if ($role == "(INTEGRASI)UsersAgensi")
#if ($LaporaneHutangAgensi.size() > 0)
<br/>
<br/>
<fieldset>
<table>
		<tr class="table_header">
          <!-- <td scope="row" width="5%" align="center"><strong>Bil.</strong></td> -->
          <td width="20%" align="center"><strong>Bilangan Penghutang</strong></td>
          <td width="20%" align="center"><strong>Jumlah Hutang Keseluruhan</strong></td>
          <td width="20%" align="center"><strong>Bilangan Hutang Yang Telah Selesai</strong></td>
          <td width="20%" align="center"><strong>Jumlah Baki Hutang</strong></td>
          <td width="20%" align="center"><strong>Bilangan Penghutang Yang Mempunyai Rekod Di BPP</strong></td>
          
        </tr>
		#set ($list = "")
        #set ( $count = 0 )
        #foreach ($list in $LaporaneHutangAgensi)
        #set ( $count = $count + 1 )
        #if ($count == '')
        #set( $row = "row1" )
        #elseif (($count % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        
        #if ($list.BIL == '0')
        <tr>
			<td class="$row" align="center" colspan="6">TIADA REKOD DITEMUI2</td>		
		</tr>
		#else
		<tr>
			<!-- <td class="$row" align="center">$count</td> -->
			<td class="$row" align="center">$list.BIL</td>
			<td class="$row" align="right">$list.NILAI_HUTANG</td>
			<td class="$row" align="center">$list.SELESAI_HUTANG</td>
			<td class="$row" align="right">$list.BAKI_HUTANG</td>
			<td class="$row" align="center">$!listFailPusaka</td>		
		</tr>
		#end
		#end
</table>
</fieldset>

#else

<br/>
<br/>
<fieldset>
<table>
		<tr class="table_header">
          <!-- <td scope="row" width="5%" align="center"><strong>Bil.</strong></td> -->
          <td width="20%" align="center"><strong>Bilangan Penghutang</strong></td>
          <td width="20%" align="center"><strong>Jumlah Hutang Keseluruhan</strong></td>
          <td width="20%" align="center"><strong>Bilangan Hutang Yang Telah Selesai</strong></td>
          <td width="20%" align="center"><strong>Jumlah Baki Hutang</strong></td>
          <td width="20%" align="center"><strong>Bilangan Penghutang Yang Mempunyai Rekod Di BPP</strong></td>
          
        </tr>
		#set ($list = "")
        #set ( $count = 0 )
        
        #set ( $count = $count + 1 )
        #if ($count == '')
        #set( $row = "row1" )
        #elseif (($count % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        
        
        <tr>
			<td class="$row" align="center" colspan="6">TIADA REKOD DITEMUI3</td>		
		</tr>
		

</table>
</fieldset>
#end
#end
#end

</fieldset>
<input type="hidden" name="vnamaAgensixx" value="$vnamaAgensixx" id="vnamaAgensixx">
<input type="hidden" name="command">
<input type="hidden" name="command2">
<input type="hidden" name="sorTempoh2" id="sorTempoh2"/>	
<input type="hidden" name="jenisLaporan" id="jenisLaporan" >
</form>


