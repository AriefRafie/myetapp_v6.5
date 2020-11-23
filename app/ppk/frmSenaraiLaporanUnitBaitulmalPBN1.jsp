 <style type="text/css">
  <!--
  .style1 {color: #0033FF}
  -->
  </style>
  <br>
  <br>

<fieldset>
<legend>Senarai Rekod/Laporan Baitulmal atau PBN</legend>
<fieldset>

#if($role == "meps")
#set($bg = "#b0b3dd")
#else
#set($bg = "#8BE75A")
#end

<table width="100%" border="0">      
    
     $selectNegeri
$selectUnit
<!-- 
     <tr> 
        <td scope="row" align="left">&nbsp;Negeri </td>
        <td>:&nbsp;</td>
        <td>$selectNegeri</td>
      </tr>
      <tr> 
        <td scope="row" align="left">&nbsp;Unit </td>
        <td>:&nbsp;</td>
        <td>$selectUnit</td>
      </tr>  -->
      <tr> 
        <td width="30%"><div align="right">Daerah :</div></td>
        
        <td>$selectDaerah</td>
      </tr>  
      
    	#set ($txdMula = "")
   		#set ($txdAkhir = "")
      
      <tr> 
        <td width="30%"><div align="right">Tarikh :</div></td>
        
        <td>
       		<label>
      			<input name="txdMula" type="text" id="txdMula" value="$txdMula" size="10"onblur="check_date(this);" />
        	</label>
        	<a href="javascript:displayDatePicker('txdMula',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>

        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;hingga&nbsp;&nbsp;&nbsp;:&nbsp;
     		<label>
      			<input name="txdAkhir" type="text" id="txdAkhir" value="$txdAkhir" size="10" onblur="check_date(this);"/>
        	</label>
        	<a href="javascript:displayDatePicker('txdAkhir',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
		</td>
      </tr>  

  
 </table>
</fieldset>	
<fieldset>
<legend>Laporan </legend>
	
<table border="0" align="center" width="100%" >
 	 <tr>
  	<td></td>
  	
  	<td>
  		<b>BAITULMAL</b>
 	</td>
   	</tr>
    
    <tr bgcolor="$bg">
  	<td>1.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanBaitulmalA()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA BAITULMAL (MENGIKUT UNIT)</a>
 	</td>
   	</tr>
    	
   	  	<tr>
  	<td>2.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanBaitulmalB()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA BAITULMAL (MENGIKUT UNIT & TAHUN)</a>
 	</td>
   	</tr>
    
    <tr bgcolor="$bg">
  	<td>3.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanBaitulmalC()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA BAITULMAL (MENGIKUT UNIT & SELANG MASA)</a>
 	</td>
   	</tr>
   
   	   	  	<tr >
  	<td>4.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanBaitulmalD()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA BAITULMAL (MENGIKUT UNIT & BULAN)</a>
 	</td>
   	</tr>
    
    <tr bgcolor="$bg">
  	<td>5.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanBaitulmalE()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA BAITULMAL (MENGIKUT NEGERI)</a>
 	</td>
   	</tr>	
    
    
   	  	<tr>
  	<td>6.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanBaitulmalF()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA BAITULMAL (MENGIKUT NEGERI & TAHUN)</a>
 	</td>
   	</tr>
    
    <tr bgcolor="$bg">
  	<td>7.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanBaitulmalG()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA BAITULMAL (MENGIKUT NEGERI & SELANG MASA)</a>
 	</td>
   	</tr>
    
    
   	   	  	<tr >
  	<td>8.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanBaitulmalH()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA BAITULMAL (MENGIKUT NEGERI & BULAN)</a>
 	</td>
   	</tr>
    
    <tr bgcolor="$bg">
  	<td>9.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanBaitulmalI()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA BAITULMAL (MENGIKUT DAERAH)</a>
 	</td>
   	</tr>
    
    	
   	  	<tr>
  	<td>10.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanBaitulmalJ()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA BAITULMAL (MENGIKUT DAERAH & TAHUN)</a>
 	</td>
   	</tr>
    
    <tr bgcolor="$bg">
  	<td>11.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanBaitulmalK()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA BAITULMAL (MENGIKUT DAERAH & SELANG MASA)</a>
 	</td>
   	</tr>
    
   	   	  	<tr >
  	<td>12.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanBaitulmalL()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA BAITULMAL (MENGIKUT DAERAH & BULAN)</a>
 	</td>
   	</tr>
   	<tr >
  	<td></td>
  	
  	<td>
  		
 	</td>
	</tr>
		<tr >
  	<td></td>
  	
  	<td>
  		<b>PIHAK BERKUASA NEGERI (PBN)</b>
 	</td>
	</tr>
    <tr bgcolor="$bg">
  	<td>1.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanPBN1()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA PBN (MENGIKUT UNIT)</a>
 	</td>
   	</tr>
    	
   	<tr>
  	<td>2.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanPBN2()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA PBN (MENGIKUT UNIT & TAHUN)</a>
 	</td>
   	</tr>
    
    <tr bgcolor="$bg">
  	<td>3.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanPBN3()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA PBN (MENGIKUT UNIT & SELANG MASA)</a>
 	</td>
   	</tr>
    
    
   	<tr >
  	<td>4.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanPBN4()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA PBN (MENGIKUT UNIT & BULAN)</a>
 	</td>
   	</tr>
    
    <tr bgcolor="$bg">
  	<td>5.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanPBN5()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA PBN (MENGIKUT NEGERI)</a>
 	</td>
   	</tr>
    
    	
   	<tr>
  	<td>6.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanPBN6()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA PBN (MENGIKUT NEGERI & TAHUN)</a>
 	</td>
   	</tr>
    
    <tr bgcolor="$bg">
  	<td>7.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanPBN7()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA PBN (MENGIKUT NEGERI & SELANG MASA)</a>
 	</td>
   	</tr>
    
    
   	<tr >
  	<td>8.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanPBN8()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA PBN (MENGIKUT NEGERI & BULAN)</a>
 	</td>
   	</tr>
    
    <tr bgcolor="$bg">
  	<td>9.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanPBN9()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA PBN (MENGIKUT DAERAH)</a>
 	</td>
   	</tr>
    
    	
   	  	<tr>
  	<td>10.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanPBN10()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA PBN (MENGIKUT DAERAH & TAHUN)</a>
 	</td>
   	</tr>
    <tr bgcolor="$bg">
  	<td>11.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanPBN11()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA PBN (MENGIKUT DAERAH & SELANG MASA)</a>
 	</td>
   	</tr>
    
    
    <tr bgcolor="$bg">
   	   	  	<tr >
  	<td>12.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanPBN12()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA PBN (MENGIKUT DAERAH & BULAN)</a>
 	</td>
   	</tr>
   	<td></td>
  	
  	<td>
  		<b>BAITULMAL (PEMEGANG AMANAH)</b>
 	</td>
   	</tr>
    
    <tr bgcolor="$bg">
  	<td>1.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanBaitulmalA()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA BAITULMAL (MENGIKUT UNIT)</a>
 	</td>
   	</tr>
    	
   	  	<tr>
  	<td>2.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanBaitulmalB()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA BAITULMAL (MENGIKUT UNIT & TAHUN)</a>
 	</td>
   	</tr>
    
    <tr bgcolor="$bg">
  	<td>3.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanBaitulmalC()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA BAITULMAL (MENGIKUT UNIT & SELANG MASA)</a>
 	</td>
   	</tr>
   
   	   	  	<tr >
  	<td>4.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanBaitulmalD()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA BAITULMAL (MENGIKUT UNIT & BULAN)</a>
 	</td>
   	</tr>
    
    <tr bgcolor="$bg">
  	<td>5.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanBaitulmalE()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA BAITULMAL (MENGIKUT NEGERI)</a>
 	</td>
   	</tr>	
    
    
   	  	<tr>
  	<td>6.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanBaitulmalF()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA BAITULMAL (MENGIKUT NEGERI & TAHUN)</a>
 	</td>
   	</tr>
    
    <tr bgcolor="$bg">
  	<td>7.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanBaitulmalG()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA BAITULMAL (MENGIKUT NEGERI & SELANG MASA)</a>
 	</td>
   	</tr>
    
    
   	   	  	<tr >
  	<td>8.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanBaitulmalH()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA BAITULMAL (MENGIKUT NEGERI & BULAN)</a>
 	</td>
   	</tr>
    
    <tr bgcolor="$bg">
  	<td>9.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanBaitulmalI()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA BAITULMAL (MENGIKUT DAERAH)</a>
 	</td>
   	</tr>
    
    	
   	  	<tr>
  	<td>10.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanBaitulmalJ()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA BAITULMAL (MENGIKUT DAERAH & TAHUN)</a>
 	</td>
   	</tr>
    
    <tr bgcolor="$bg">
  	<td>11.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanBaitulmalK()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA BAITULMAL (MENGIKUT DAERAH & SELANG MASA)</a>
 	</td>
   	</tr>
    
   	   	  	<tr >
  	<td>12.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanBaitulmalL()" class="style1" >LAPORAN HARTA DISERAHKAN KEPADA BAITULMAL (MENGIKUT DAERAH & BULAN)</a>
 	</td>
   	</tr>
   	<tr >
  	<td></td>
  	
  	<td>
  		
 	</td>
	</tr>
		<tr >
</table>
</fieldset>	



	
<script>
window.onload=function()
{
populatedropdown("yeardropdown")
populatedropdown2("yeardropdown2")
}

function PrintLaporanBaitulmalA()
{
//MENGIKUT UNIT	
		var unit = document.${formName}.socUnit.value;
		var akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
		var	akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
		var	akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
		var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
		var	mula_bulan = document.${formName}.txdMula.value.substring(3,5);
		var	mula_hari = document.${formName}.txdMula.value.substring(0,2);
		var	mula_tahun = document.${formName}.txdMula.value.substring(6,10);
		var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;

		var mula = Date.parse(mulatemp);
		var akhir = Date.parse(akhirtemp);
		var tarikhsemasa = new Date();
		if(unit==""|| unit=="0")
		{
			alert("Sila pilih \"Unit\" terlebih dahulu.");
			document.${formName}.socUnit.focus(); 
			return;
		}
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanSerahanBaitulmalPBN1?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_laporan=BaitulmalUNIT&unit="+unit;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
  		if ((document.window != null) && (!hWnd.opener))
  	    hWnd.opener = document.window;
   		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();	
			}
	
}

function PrintLaporanBaitulmalB()
{
		
		var unit = document.${formName}.socUnit.value;
		var daerah = document.${formName}.socDaerah.value;
		var akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
		var	akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
		var	akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
		var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
		var	mula_bulan = document.${formName}.txdMula.value.substring(3,5);
		var	mula_hari = document.${formName}.txdMula.value.substring(0,2);
		var	mula_tahun = document.${formName}.txdMula.value.substring(6,10);
		var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;
		
		var mula = Date.parse(mulatemp);
		var akhir = Date.parse(akhirtemp);
		var tarikhsemasa = new Date();
		if(unit==""|| unit=="0")
		{
			alert("Sila pilih \"Unit\" terlebih dahulu.");
			document.${formName}.socUnit.focus(); 
			return;
		}
		else if (mula_tahun==""){
			alert("Sila pilih \"Tarikh\" terlebih dahulu.");
			document.${formName}.txdMula.focus(); 
			return;
		}	
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanSerahanBaitulmalPBN1?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_laporan=BaitulmalUNITtahun&unit="+unit;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
		
		if ((document.window != null) && (!hWnd.opener))
  	    hWnd.opener = document.window;
   		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();	
			}
	
}

function PrintLaporanBaitulmalC()
{
		
		var unit = document.${formName}.socUnit.value;
		var daerah = document.${formName}.socDaerah.value;
		var akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
		var	akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
		var	akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
		var akhirtemp = akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun; 
		var	mula_bulan = document.${formName}.txdMula.value.substring(3,5);
		var	mula_hari = document.${formName}.txdMula.value.substring(0,2);
		var	mula_tahun = document.${formName}.txdMula.value.substring(6,10);
		var mulatemp = mula_hari+"/"+mula_bulan+"/"+mula_tahun;
		
		//var mula = Date.parse(mulatemp);
		//var akhir = Date.parse(akhirtemp);
		
		var mula = document.${formName}.txdMula.value;
		var akhir = document.${formName}.txdAkhir.value;
		var tarikhsemasa = new Date();
		if(unit==""|| unit=="0")
		{
			alert("Sila pilih \"Unit\" terlebih dahulu.");
			document.${formName}.socUnit.focus(); 
			return;
		}
		else if (mula_tahun==""){
			alert("Sila pilih \"Tarikh\" terlebih dahulu.");
			document.${formName}.txdMula.focus(); 
			return;
		}	
		else if (akhir_tahun==""){
			alert("Sila pilih \"Tarikh\" terlebih dahulu.");
			document.${formName}.txdAkhir.focus(); 
			return;
		}
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanSerahanBaitulmalPBN1?mula="+mula+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir="+akhir+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_laporan=BaitulmalUNITselangmasa&unit="+unit;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
		
		if ((document.window != null) && (!hWnd.opener))
  	    hWnd.opener = document.window;
   		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();	
			}
	
}


function PrintLaporanBaitulmalD()
{
		
		var unit = document.${formName}.socUnit.value;
		var daerah = document.${formName}.socDaerah.value;
		var akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
		var	akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
		var	akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
		var akhirtemp = akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun; 
		var	mula_bulan = document.${formName}.txdMula.value.substring(3,5);
		var	mula_hari = document.${formName}.txdMula.value.substring(0,2);
		var	mula_tahun = document.${formName}.txdMula.value.substring(6,10);
		var mulatemp = mula_hari+"/"+mula_bulan+"/"+mula_tahun;
		
		//var mula = Date.parse(mulatemp);
		//var akhir = Date.parse(akhirtemp);
		
		var mula = document.${formName}.txdMula.value;
		var akhir = document.${formName}.txdAkhir.value;
		var tarikhsemasa = new Date();
		if(unit==""|| unit=="0")
		{
			alert("Sila pilih \"Unit\" terlebih dahulu.");
			document.${formName}.socUnit.focus(); 
			return;
		}
		else if (mula_bulan==""){
			alert("Sila pilih \"Tarikh\" terlebih dahulu.");
			document.${formName}.txdMula.focus(); 
			return;
		}	
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanSerahanBaitulmalPBN1?mula="+mula+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir="+akhir+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_laporan=BaitulmalUNITbulan&unit="+unit;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
		
		if ((document.window != null) && (!hWnd.opener))
  	    hWnd.opener = document.window;
   		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();	
			}
	
}

function PrintLaporanBaitulmalE()
{
	//MENGIKUT NEGERI
			var negeri = document.${formName}.socNegeri.value;
			var akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
			var	akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
			var	akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
			var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
			var	mula_bulan = document.${formName}.txdMula.value.substring(3,5);
			var	mula_hari = document.${formName}.txdMula.value.substring(0,2);
			var	mula_tahun = document.${formName}.txdMula.value.substring(6,10);
			var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;

			var mula = Date.parse(mulatemp);
			var akhir = Date.parse(akhirtemp);
			var tarikhsemasa = new Date();
			if(negeri==""){
				alert("Sila pilih \"Negeri\" terlebih dahulu.");
				document.${formName}.socNegeri.focus(); 
				return;
			}	
			else
				{
			var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanSerahanBaitulmalPBN1?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_laporan=BaitulmalNegeri&negeri="+negeri;
			var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
	  		if ((document.window != null) && (!hWnd.opener))
	  	    hWnd.opener = document.window;
	    	if (hWnd.focus != null) hWnd.focus();
			hWnd.focus();	
				}
			
		
	}

function PrintLaporanBaitulmalF()
{
		
		var negeri = document.${formName}.socNegeri.value;
		var daerah = document.${formName}.socDaerah.value;
		var akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
		var	akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
		var	akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
		var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
		var	mula_bulan = document.${formName}.txdMula.value.substring(3,5);
		var	mula_hari = document.${formName}.txdMula.value.substring(0,2);
		var	mula_tahun = document.${formName}.txdMula.value.substring(6,10);
		var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;
		
		var mula = Date.parse(mulatemp);
		var akhir = Date.parse(akhirtemp);
		var tarikhsemasa = new Date();
		if(negeri==""){
			alert("Sila pilih \"Negeri\" terlebih dahulu.");
			document.${formName}.socNegeri.focus(); 
			return;
		}	
		else if (mula_tahun==""){
			alert("Sila pilih \"Tarikh\" terlebih dahulu.");
			document.${formName}.txdMula.focus(); 
			return;
		}	
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanSerahanBaitulmalPBN1?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_laporan=BaitulmalNegeritahun&negeri="+negeri;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
		
		if ((document.window != null) && (!hWnd.opener))
  	    hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();	
			}
	
}

function PrintLaporanBaitulmalG()
{
		
		var negeri = document.${formName}.socNegeri.value;
		var daerah = document.${formName}.socDaerah.value;
		var akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
		var	akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
		var	akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
		var akhirtemp = akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun; 
		var	mula_bulan = document.${formName}.txdMula.value.substring(3,5);
		var	mula_hari = document.${formName}.txdMula.value.substring(0,2);
		var	mula_tahun = document.${formName}.txdMula.value.substring(6,10);
		var mulatemp = mula_hari+"/"+mula_bulan+"/"+mula_tahun;
		
		//var mula = Date.parse(mulatemp);
		//var akhir = Date.parse(akhirtemp);
		
		var mula = document.${formName}.txdMula.value;
		var akhir = document.${formName}.txdAkhir.value;
		var tarikhsemasa = new Date();
		if(negeri==""){
			alert("Sila pilih \"Negeri\" terlebih dahulu.");
			document.${formName}.socNegeri.focus(); 
			return;
		}
		else if (mula_tahun==""){
			alert("Sila pilih \"Tarikh\" terlebih dahulu.");
			document.${formName}.txdMula.focus(); 
			return;
		}	
		else if (akhir_tahun==""){
			alert("Sila pilih \"Tarikh\" terlebih dahulu.");
			document.${formName}.txdAkhir.focus(); 
			return;
		}
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanSerahanBaitulmalPBN1?mula="+mula+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir="+akhir+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_laporan=BaitulmalNegeriselangmasa&negeri="+negeri;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
		
		if ((document.window != null) && (!hWnd.opener))
  	    hWnd.opener = document.window;
  		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();	
			}
	
}

function PrintLaporanBaitulmalH()
{
		
		var negeri = document.${formName}.socNegeri.value;
		var daerah = document.${formName}.socDaerah.value;
		var akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
		var	akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
		var	akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
		var akhirtemp = akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun; 
		var	mula_bulan = document.${formName}.txdMula.value.substring(3,5);
		var	mula_hari = document.${formName}.txdMula.value.substring(0,2);
		var	mula_tahun = document.${formName}.txdMula.value.substring(6,10);
		var mulatemp = mula_hari+"/"+mula_bulan+"/"+mula_tahun;
		
		//var mula = Date.parse(mulatemp);
		//var akhir = Date.parse(akhirtemp);
		
		var mula = document.${formName}.txdMula.value;
		var akhir = document.${formName}.txdAkhir.value;
		var tarikhsemasa = new Date();
		if(negeri==""){
			alert("Sila pilih \"Negeri\" terlebih dahulu.");
			document.${formName}.socNegeri.focus(); 
			return;
		}
		else if (mula_bulan==""){
			alert("Sila pilih \"Tarikh\" terlebih dahulu.");
			document.${formName}.txdMula.focus(); 
			return;
		}	
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanSerahanBaitulmalPBN1?mula="+mula+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir="+akhir+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_laporan=BaitulmalNegeribulan&unit="+negeri;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
		
		if ((document.window != null) && (!hWnd.opener))
  	    hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();	
			}
	
}

function PrintLaporanBaitulmalI()
{
//MENGIKUT DAERAH
		var daerah = document.${formName}.socDaerah.value;
		var akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
		var	akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
		var	akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
		var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
		var	mula_bulan = document.${formName}.txdMula.value.substring(3,5);
		var	mula_hari = document.${formName}.txdMula.value.substring(0,2);
		var	mula_tahun = document.${formName}.txdMula.value.substring(6,10);
		var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;

		var mula = Date.parse(mulatemp);
		var akhir = Date.parse(akhirtemp);
		var tarikhsemasa = new Date();
		if(daerah==""){
			alert("Sila pilih \"Daerah\" terlebih dahulu.");
			document.${formName}.socDaerah.focus(); 
			return;
		}	
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanSerahanBaitulmalPBN1?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_laporan=BaitulmalDAERAH&daerah="+daerah;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
  		if ((document.window != null) && (!hWnd.opener))
  	    hWnd.opener = document.window;
   		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();	
			}
	
}

function PrintLaporanBaitulmalJ()
{
		
		
		var daerah = document.${formName}.socDaerah.value;
		var akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
		var	akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
		var	akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
		var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
		var	mula_bulan = document.${formName}.txdMula.value.substring(3,5);
		var	mula_hari = document.${formName}.txdMula.value.substring(0,2);
		var	mula_tahun = document.${formName}.txdMula.value.substring(6,10);
		var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;
		
		var mula = Date.parse(mulatemp);
		var akhir = Date.parse(akhirtemp);
		var tarikhsemasa = new Date();
		if(daerah==""){
			alert("Sila pilih \"Daerah\" terlebih dahulu.");
			document.${formName}.socDaerah.focus(); 
			return;
		}
		else if (mula_tahun==""){
			alert("Sila pilih \"Tarikh\" terlebih dahulu.");
			document.${formName}.txdMula.focus(); 
			return;
		}	
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanSerahanBaitulmalPBN1?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_laporan=BaitulmalDAERAHtahun&daerah="+daerah;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
		
		if ((document.window != null) && (!hWnd.opener))
  	    hWnd.opener = document.window;
  		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();	
			}
	
}

function PrintLaporanBaitulmalK()
{
		var daerah = document.${formName}.socDaerah.value;
		var akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
		var	akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
		var	akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
		var akhirtemp = akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun; 
		var	mula_bulan = document.${formName}.txdMula.value.substring(3,5);
		var	mula_hari = document.${formName}.txdMula.value.substring(0,2);
		var	mula_tahun = document.${formName}.txdMula.value.substring(6,10);
		var mulatemp = mula_hari+"/"+mula_bulan+"/"+mula_tahun;
		
		//var mula = Date.parse(mulatemp);
		//var akhir = Date.parse(akhirtemp);
		
		var mula = document.${formName}.txdMula.value;
		var akhir = document.${formName}.txdAkhir.value;
		var tarikhsemasa = new Date();
		if(daerah==""){
			alert("Sila pilih \"Daerah\" terlebih dahulu.");
			document.${formName}.socDaerah.focus(); 
			return;
		}
		else if (mula_tahun==""){
			alert("Sila pilih \"Tarikh\" terlebih dahulu.");
			document.${formName}.txdMula.focus(); 
			return;
		}	
		else if (akhir_tahun==""){
			alert("Sila pilih \"Tarikh\" terlebih dahulu.");
			document.${formName}.txdAkhir.focus(); 
			return;
		}
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanSerahanBaitulmalPBN1?mula="+mula+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir="+akhir+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_laporan=BaitulmalDAERAHselangmasa&daerah="+daerah;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
		
		if ((document.window != null) && (!hWnd.opener))
  	    hWnd.opener = document.window;
   		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();	
			}
	
}

function PrintLaporanBaitulmalL()
{
		var daerah = document.${formName}.socDaerah.value;
		var akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
		var	akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
		var	akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
		var akhirtemp = akhir_hari+"/"+akhir_bulan+"/"+akhir_tahun; 
		var	mula_bulan = document.${formName}.txdMula.value.substring(3,5);
		var	mula_hari = document.${formName}.txdMula.value.substring(0,2);
		var	mula_tahun = document.${formName}.txdMula.value.substring(6,10);
		var mulatemp = mula_hari+"/"+mula_bulan+"/"+mula_tahun;
		
		//var mula = Date.parse(mulatemp);
		//var akhir = Date.parse(akhirtemp);
		
		var mula = document.${formName}.txdMula.value;
		var akhir = document.${formName}.txdAkhir.value;
		var tarikhsemasa = new Date();
		if(daerah==""){
			alert("Sila pilih \"Daerah\" terlebih dahulu.");
			document.${formName}.socDaerah.focus(); 
			return;
		}
		else if (mula_bulan==""){
			alert("Sila pilih \"Tarikh\" terlebih dahulu.");
			document.${formName}.txdMula.focus(); 
			return;
		}	
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanSerahanBaitulmalPBN1?mula="+mula+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir="+akhir+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_laporan=BaitulmalDAERAHbulan&daerah="+daerah;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
		
		if ((document.window != null) && (!hWnd.opener))
  	    hWnd.opener = document.window;
   		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();	
			}
		
	
}

function PrintLaporanPBN1()
{
	if (document.${formName}.socUnit.value==""){
		alert("Sila pilih \"Unit\" terlebih dahulu.");
		document.${formName}.socUnit.focus(); 
		return;
	}
	
	else
		{
		var unit = document.${formName}.socUnit.value;
		var akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
		var	akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
		var	akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
		var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
		var	mula_bulan = document.${formName}.txdMula.value.substring(3,5);
		var	mula_hari = document.${formName}.txdMula.value.substring(0,2);
		var	mula_tahun = document.${formName}.txdMula.value.substring(6,10);
		var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;

		var mula = Date.parse(mulatemp);
		var akhir = Date.parse(akhirtemp);
		var tarikhsemasa = new Date();
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanSerahanBaitulmalPBN1?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_laporan=PBNunit&unit="+unit;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
  		if ((document.window != null) && (!hWnd.opener))
  	    hWnd.opener = document.window;
   		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();	
		}
	
}

function PrintLaporanPBN2()
{
	
		var unit = document.${formName}.socUnit.value;
		var akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
		var	akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
		var	akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
		var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
		var	mula_bulan = document.${formName}.txdMula.value.substring(3,5);
		var	mula_hari = document.${formName}.txdMula.value.substring(0,2);
		var	mula_tahun = document.${formName}.txdMula.value.substring(6,10);
		var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;

		var mula = Date.parse(mulatemp);
		var akhir = Date.parse(akhirtemp);
		var tarikhsemasa = new Date();
		if (document.${formName}.socUnit.value==""){
			alert("Sila pilih \"Unit\" terlebih dahulu.");
			document.${formName}.socUnit.focus(); 
			return;
		}
		else if (mula_tahun==""){
			alert("Sila pilih \"Tarikh\" terlebih dahulu.");
			document.${formName}.txdMula.focus(); 
			return;
		}	
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanSerahanBaitulmalPBN1?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_laporan=PBNunittahun&unit="+unit;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
  		if ((document.window != null) && (!hWnd.opener))
  	    hWnd.opener = document.window;
   		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();	
		}
	
}

function PrintLaporanPBN3()
{
	
		var unit = document.${formName}.socUnit.value;
		var akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
		var	akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
		var	akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
		var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
		var	mula_bulan = document.${formName}.txdMula.value.substring(3,5);
		var	mula_hari = document.${formName}.txdMula.value.substring(0,2);
		var	mula_tahun = document.${formName}.txdMula.value.substring(6,10);
		var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;

		var mula = document.${formName}.txdMula.value;
		var akhir = document.${formName}.txdAkhir.value;
		var tarikhsemasa = new Date();
		if (document.${formName}.socUnit.value==""){
			alert("Sila pilih \"Unit\" terlebih dahulu.");
			document.${formName}.socUnit.focus(); 
			return;
		}
		else if (mula_tahun==""){
			alert("Sila pilih \"Tarikh\" terlebih dahulu.");
			document.${formName}.txdMula.focus(); 
			return;
		}	
		else if (akhir_tahun==""){
			alert("Sila pilih \"Tarikh\" terlebih dahulu.");
			document.${formName}.txdAkhir.focus(); 
			return;
		}
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanSerahanBaitulmalPBN1?mula="+mula+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir="+akhir+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_laporan=PBNunitselangmasa&unit="+unit;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
  		if ((document.window != null) && (!hWnd.opener))
  	     hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();	
		}
	
}

function PrintLaporanPBN4()
{
		var unit = document.${formName}.socUnit.value;
		var akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
		var	akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
		var	akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
		var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
		var	mula_bulan = document.${formName}.txdMula.value.substring(3,5);
		var	mula_hari = document.${formName}.txdMula.value.substring(0,2);
		var	mula_tahun = document.${formName}.txdMula.value.substring(6,10);
		var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;

		var mula = Date.parse(mulatemp);
		var akhir = Date.parse(akhirtemp);
		var tarikhsemasa = new Date();
		if(unit==""|| unit=="0")
		{
			alert("Sila pilih \"Unit\" terlebih dahulu.");
			document.${formName}.socUnit.focus(); 
			return;
		}
		else if (mula_bulan==""){
			alert("Sila pilih \"Tarikh\" terlebih dahulu.");
			document.${formName}.txdMula.focus(); 
			return;
		}	
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanSerahanBaitulmalPBN1?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_laporan=PBNunitbulan&unit="+unit;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
  		if ((document.window != null) && (!hWnd.opener))
  	     hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();	
		}
	
}

function PrintLaporanPBN5()
{
		var negeri = document.${formName}.socNegeri.value;
		var akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
		var	akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
		var	akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
		var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
		var	mula_bulan = document.${formName}.txdMula.value.substring(3,5);
		var	mula_hari = document.${formName}.txdMula.value.substring(0,2);
		var	mula_tahun = document.${formName}.txdMula.value.substring(6,10);
		var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;

		var mula = Date.parse(mulatemp);
		var akhir = Date.parse(akhirtemp);
		var tarikhsemasa = new Date();
		if(negeri==""){
			alert("Sila pilih \"Negeri\" terlebih dahulu.");
			document.${formName}.socNegeri.focus(); 
			return;
		}	
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanSerahanBaitulmalPBN1?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_laporan=PBNnegeri&negeri="+negeri;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
  		if ((document.window != null) && (!hWnd.opener))
  	     hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();	
		}
	
}

function PrintLaporanPBN6()
{
		var negeri = document.${formName}.socNegeri.value;
		var akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
		var	akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
		var	akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
		var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
		var	mula_bulan = document.${formName}.txdMula.value.substring(3,5);
		var	mula_hari = document.${formName}.txdMula.value.substring(0,2);
		var	mula_tahun = document.${formName}.txdMula.value.substring(6,10);
		var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;

		var mula = Date.parse(mulatemp);
		var akhir = Date.parse(akhirtemp);
		var tarikhsemasa = new Date();
		if(negeri==""){
			alert("Sila pilih \"Negeri\" terlebih dahulu.");
			document.${formName}.socNegeri.focus(); 
			return;
		}
		else if (mula_tahun==""){
			alert("Sila pilih \"Tarikh\" terlebih dahulu.");
			document.${formName}.txdMula.focus(); 
			return;
		}	
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanSerahanBaitulmalPBN1?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_laporan=PBNnegeritahun&negeri="+negeri;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
  		if ((document.window != null) && (!hWnd.opener))
  	     hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();	
		}
	
}

function PrintLaporanPBN7()
{
		var negeri = document.${formName}.socNegeri.value;
		var akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
		var	akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
		var	akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
		var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
		var	mula_bulan = document.${formName}.txdMula.value.substring(3,5);
		var	mula_hari = document.${formName}.txdMula.value.substring(0,2);
		var	mula_tahun = document.${formName}.txdMula.value.substring(6,10);
		var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;

		var mula = Date.parse(mulatemp);
		var akhir = Date.parse(akhirtemp);
		var tarikhsemasa = new Date();
		if(negeri==""){
			alert("Sila pilih \"Negeri\" terlebih dahulu.");
			document.${formName}.socNegeri.focus(); 
			return;
		}
		else if (mula_tahun==""){
			alert("Sila pilih \"Tarikh\" terlebih dahulu.");
			document.${formName}.txdMula.focus(); 
			return;
		}
		else if (akhir_tahun==""){
			alert("Sila pilih \"Tarikh\" terlebih dahulu.");
			document.${formName}.txdAkhir.focus(); 
			return;
		}
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanSerahanBaitulmalPBN1?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_laporan=PBNnegeriselangmasa&negeri="+negeri;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
  		if ((document.window != null) && (!hWnd.opener))
  	     hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();	
		}
	
}

function PrintLaporanPBN8()
{
		var negeri = document.${formName}.socNegeri.value;
		var akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
		var	akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
		var	akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
		var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
		var	mula_bulan = document.${formName}.txdMula.value.substring(3,5);
		var	mula_hari = document.${formName}.txdMula.value.substring(0,2);
		var	mula_tahun = document.${formName}.txdMula.value.substring(6,10);
		var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;

		var mula = Date.parse(mulatemp);
		var akhir = Date.parse(akhirtemp);
		var tarikhsemasa = new Date();
		if(negeri==""){
			alert("Sila pilih \"Negeri\" terlebih dahulu.");
			document.${formName}.socNegeri.focus(); 
			return;
		}
		else if (mula_bulan==""){
			alert("Sila pilih \"Tarikh\" terlebih dahulu.");
			document.${formName}.txdMula.focus(); 
			return;
		}	
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanSerahanBaitulmalPBN1?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_laporan=PBNnegeribulan&negeri="+negeri;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
  		if ((document.window != null) && (!hWnd.opener))
  	    hWnd.opener = document.window;
   		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();	
		}
	
}

function PrintLaporanPBN9()
{
		var daerah = document.${formName}.socDaerah.value;
		var akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
		var	akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
		var	akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
		var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
		var	mula_bulan = document.${formName}.txdMula.value.substring(3,5);
		var	mula_hari = document.${formName}.txdMula.value.substring(0,2);
		var	mula_tahun = document.${formName}.txdMula.value.substring(6,10);
		var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;

		var mula = Date.parse(mulatemp);
		var akhir = Date.parse(akhirtemp);
		var tarikhsemasa = new Date();
		if(daerah==""){
			alert("Sila pilih \"Daerah\" terlebih dahulu.");
			document.${formName}.socDaerah.focus(); 
			return;
		}	
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanSerahanBaitulmalPBN1?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_laporan=PBNdaerah&daerah="+daerah;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
  		if ((document.window != null) && (!hWnd.opener))
  	    hWnd.opener = document.window;
   		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();	
		}
	
}

function PrintLaporanPBN10()
{
		var akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
		var	akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
		var	akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
		var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
		var	mula_bulan = document.${formName}.txdMula.value.substring(3,5);
		var	mula_hari = document.${formName}.txdMula.value.substring(0,2);
		var	mula_tahun = document.${formName}.txdMula.value.substring(6,10);
		var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;

		var mula = Date.parse(mulatemp);
		var akhir = Date.parse(akhirtemp);
		var tarikhsemasa = new Date();
		if(daerah==""){
			alert("Sila pilih \"Daerah\" terlebih dahulu.");
			document.${formName}.socDaerah.focus(); 
			return;
		}
		else if (mula_tahun==""){
			alert("Sila pilih \"Tarikh\" terlebih dahulu.");
			document.${formName}.txdMula.focus(); 
			return;
		}	
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanSerahanBaitulmalPBN1?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_laporan=PBNdaerahtahun&daerah="+daerah;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
  		if ((document.window != null) && (!hWnd.opener))
  	     hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();	
		}
	
}

function PrintLaporanPBN11()
{
	
		var akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
		var	akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
		var	akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
		var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
		var	mula_bulan = document.${formName}.txdMula.value.substring(3,5);
		var	mula_hari = document.${formName}.txdMula.value.substring(0,2);
		var	mula_tahun = document.${formName}.txdMula.value.substring(6,10);
		var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;

		var mula = Date.parse(mulatemp);
		var akhir = Date.parse(akhirtemp);
		var tarikhsemasa = new Date();
		if(daerah==""){
			alert("Sila pilih \"Daerah\" terlebih dahulu.");
			document.${formName}.socDaerah.focus(); 
			return;
		}
		else if (mula_tahun==""){
			alert("Sila pilih \"Tarikh\" terlebih dahulu.");
			document.${formName}.txdMula.focus(); 
			return;
		}	
		else if (akhir_tahun==""){
			alert("Sila pilih \"Tarikh\" terlebih dahulu.");
			document.${formName}.txdAkhir.focus(); 
			return;
		}
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanSerahanBaitulmalPBN1?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_laporan=PBNdaerahselangmasa&daerah="+daerah;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
  		if ((document.window != null) && (!hWnd.opener))
  	     hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();	
		}
	
}

function PrintLaporanPBN12()
{
	
		var akhir_bulan = document.${formName}.txdAkhir.value.substring(3,5);
		var	akhir_hari = document.${formName}.txdAkhir.value.substring(0,2);
		var	akhir_tahun = document.${formName}.txdAkhir.value.substring(6,10);
		var akhirtemp = akhir_bulan+"/"+akhir_hari+"/"+akhir_tahun; 
		var	mula_bulan = document.${formName}.txdMula.value.substring(3,5);
		var	mula_hari = document.${formName}.txdMula.value.substring(0,2);
		var	mula_tahun = document.${formName}.txdMula.value.substring(6,10);
		var mulatemp = mula_bulan+"/"+mula_hari+"/"+mula_tahun;

		var mula = Date.parse(mulatemp);
		var akhir = Date.parse(akhirtemp);
		var tarikhsemasa = new Date();
		if(daerah==""){
			alert("Sila pilih \"Daerah\" terlebih dahulu.");
			document.${formName}.socDaerah.focus(); 
			return;
		}
		else if (mula_bulan==""){
			alert("Sila pilih \"Tarikh\" terlebih dahulu.");
			document.${formName}.txdMula.focus(); 
			return;
		}	
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanSerahanBaitulmalPBN1?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_laporan=PBNdaerahbulan&daerah="+daerah;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
  		if ((document.window != null) && (!hWnd.opener))
  	     hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();	
		}
	
}

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
function populatedropdown(yearfield){
var today=new Date()
var yearfield=document.getElementById(yearfield)

var thisyear=today.getFullYear()
for (var y=0; y<20; y++){	
yearfield.options[y]=new Option(thisyear, thisyear)
thisyear-=1
}
yearfield.options[0]=new Option(today.getFullYear(), today.getFullYear(), true, true) 
}

function populatedropdown2(yearfield){
	var today=new Date()
	var yearfield=document.getElementById(yearfield)

	var thisyear=today.getFullYear()
	for (var y=0; y<20; y++){
	yearfield.options[y]=new Option(thisyear, thisyear)
	thisyear-=1
	}
	yearfield.options[0]=new Option(today.getFullYear(), today.getFullYear(), true, true) 
	}

function cetakPrestasi() {

	
	var radioSelected = false;
	for (i = 0;  i < f1.sorLaporan.length;  i++){
	if (f1.sorLaporan[i].checked)
	radioSelected = true;
	}

	var radioSelected2 = false;
	for (i = 0;  i < f1.sorTempoh.length;  i++){
	if (f1.sorTempoh[i].checked)
	radioSelected2 = true;
	}
	
	var LcanEdit = document.f1.LcanEdit.value;
	var TcanEdit = document.f1.TcanEdit.value;

	
	var negeri = document.f1.socNegeri.value;
	var unit = document.f1.socTempatBicara.value;
	var bulan1 = document.f1.bulan1.value;
	var bulan2 = document.f1.bulan2.value;
	var tahun1 = document.f1.tahun1.value;
	var tahun2 = document.f1.tahun2.value;
	
	
	if(!radioSelected){

		//sila pilih salah satu jenis laporan
		alert("Sila pilih \"Jenis Laporan\" terlebih dahulu.");
		return;	
	}
	else if(!radioSelected2){

		//sila pilih salah satu jenis laporan
		alert("Sila pilih \"bulan/tahun/tempoh\" terlebih dahulu.");
		return;	
	}
	else if(LcanEdit=="byNegeri" && (negeri==""))
	{
		alert("Sila pilih \"Negeri\" terlebih dahulu.");
		document.f1.socNegeri.focus(); 
		return;		
	}
	
	else if(LcanEdit=="byUnit" && (negeri==""))
	{
		//validation negeri
		alert("Sila pilih \"Negeri\" terlebih dahulu.");
		document.f1.socNegeri.focus(); 
		return;	
	}
	else if(LcanEdit=="byUnit" && (unit==""))
	{
		//validation unit
		alert("Sila pilih \"Unit\" terlebih dahulu.");
		document.f1.socTempatBicara.focus(); 
		return;	
	}
	
	else if(TcanEdit=="byBulan" && (bulan1==""))
	{
		//validation unit
		alert("Sila pilih \"Bulan\" terlebih dahulu.");
		document.f1.bulan1.focus(); 
		return;	
	}
	else if(TcanEdit=="byBulan" && (tahun1==""))
	{
		//validation unit
		alert("Sila pilih \"Tahun\" terlebih dahulu.");
		document.f1.tahun1.focus(); 
		return;	
	}
	else if(TcanEdit=="byTempoh" && (bulan1=="" || bulan2==""))
	{
		//validation unit
		alert("Sila pilih \"Bulan\" terlebih dahulu.");
		document.f1.bulan1.focus(); 
		return;	
	}
	
	else
	{

		if(LcanEdit=="byNegeri"){
			var tahun = document.f1.tahun1.value;
			var bulan = document.f1.bulan1.value;
			var negeri = document.f1.socNegeri.value;
			if(TcanEdit=="byBulan")
			{
				//alert("cetak Prestasi Negeri BY Bulan");
				var url = "../servlet/ekptg.report.ppk.LaporanPrestasiByNegeriBulanan?tahun="+tahun+"&bulan="+bulan+"&negeri="+negeri;
			    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
			    if ((document.window != null) && (!hWnd.opener))
				hWnd.opener = document.window;
			    if (hWnd.focus != null) hWnd.focus();
			}
			else if(TcanEdit=="byTahun")
			{
				//alert("cetak Prestasi Negeri BY Tahun");	
				var url = "../servlet/ekptg.report.ppk.LaporanPrestasiByNegeriTahunan?tahun="+tahun+"&negeri="+negeri;
		    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		    	if ((document.window != null) && (!hWnd.opener))
				hWnd.opener = document.window;
		    	if (hWnd.focus != null) hWnd.focus();
		    }
		    else if(TcanEdit=="byTempoh")
			{
				//alert("cetak Prestasi Negeri BY Tempoh");	
				var url = "../servlet/ekptg.report.ppk.LaporanPrestasiByNegeriTempoh?bulan="+bulan1+"&bulanhingga="+bulan2+"&tahun="+tahun+"&negeri="+negeri;
		    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		    	if ((document.window != null) && (!hWnd.opener))
				hWnd.opener = document.window;
		    	if (hWnd.focus != null) hWnd.focus();	
			}
		
		}else if(LcanEdit=="byPejabat"){

			var tahun = document.f1.tahun1.value;
			var bulan = document.f1.bulan1.value;

			if(TcanEdit=="byBulan"){
				//alert("cetak Prestasi Ibu Pejabat BY Bulan");
				var url = "../servlet/ekptg.report.ppk.LaporanPrestasiIbuPejabat_byBulan?tahun="+tahun+"&bulan="+bulan;
				var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
				if ((document.window != null) && (!hWnd.opener))
				hWnd.opener = document.window;
		    	if (hWnd.focus != null) hWnd.focus();
		    	
			}else if(TcanEdit=="byTahun"){
				//alert("cetak Prestasi Ibu Pejabat BY Tahun");
				var url = "../servlet/ekptg.report.ppk.LaporanPrestasiIbuPejabat_byTahun?tahun="+tahun;
				var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
				if ((document.window != null) && (!hWnd.opener))
				hWnd.opener = document.window;
			    if (hWnd.focus != null) hWnd.focus();
			    
			}
			
		}else if(LcanEdit=="byUnit"){
			var tahun = document.f1.tahun1.value;
			var bulan = document.f1.bulan1.value;
			var negeri = document.f1.socNegeri.value;
			var unit = document.f1.socTempatBicara.value;
			if(TcanEdit=="byBulan")
			{
				//alert("cetak Prestasi Negeri BY Bulan");
				var url = "../servlet/ekptg.report.ppk.LaporanPrestasiByUnitBulanan?TAHUN="+tahun+"&BULAN="+bulan+"&ID_NEGERI="+negeri+"&ID_UNIT="+unit;
			    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
			    if ((document.window != null) && (!hWnd.opener))
				hWnd.opener = document.window;
			    if (hWnd.focus != null) hWnd.focus();
			}
			else if(TcanEdit=="byTahun")
			{
				//alert("cetak Prestasi Negeri BY Tahun");	
				var url = "../servlet/ekptg.report.ppk.LaporanPrestasiByUnitTahun?TAHUN="+tahun+"&NEGERI="+negeri+"&UNIT="+unit;
		    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		    	if ((document.window != null) && (!hWnd.opener))
				hWnd.opener = document.window;
		    	if (hWnd.focus != null) hWnd.focus();
		    }
		    else if(TcanEdit=="byTempoh")
			{
				//alert("cetak Prestasi Negeri BY Tempoh");	
				var url = "../servlet/ekptg.report.ppk.LaporanPrestasiByUnitTempoh?BULAN_DARI="+bulan1+"&BULAN_HINGGA="+bulan2+"&TAHUN_DARI="+tahun1+"&TAHUN_HINGGA="+tahun2+"&ID_NEGERI="+negeri+"&ID_UNIT="+unit;
		    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
		    	if ((document.window != null) && (!hWnd.opener))
				hWnd.opener = document.window;
		    	if (hWnd.focus != null) hWnd.focus();	
			}
		}
		  
	}//close if
}

function openLaporan(urli,param,laporan,tem){
	var negeri = 0;
	var unit = 0;
	var daerah = document.${formName}.socDaerah.value;
	var pnegeri = "&ID_NEGERI=0";
	var ptahun = "&TAHUN=";
	var ptem = "&template="+tem;
	var pbulanmula = "&BULANTAHUN=0";
	var pbulantamat = "&BULANTAHUNTMT=0";
	var punit = "&ID_PEJABAT=0";
	var pdaerah = "&ID=0";

	if(laporan=="negeri"){
		if(negeri==""){
			alert("Sila pilih \"Negeri\" terlebih dahulu.");
			document.${formName}.socNegeri.focus(); 
			return;
		}	
		pnegeri = "&ID_NEGERI="+negeri;
	}else if(laporan=="unit"){
			if(unit==""){
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
			if(negeri==""){
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
			if(negeri==""){
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
			if(negeri==""){
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
			if(unit==""){
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
			if(unit==""){
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
			if(unit==""){
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
</script>


