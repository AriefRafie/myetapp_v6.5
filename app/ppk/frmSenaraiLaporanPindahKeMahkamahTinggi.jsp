 <style type="text/css">
  <!--
  .style1 {color: #0033FF}
  -->
  </style>
  <br>
  <br>
<fieldset>

<legend>Senarai Rekod/Laporan Kes Pindah ke Mahkamah Tinggi</legend>
<fieldset>

#if($role == "meps")
#set($bg = "#b0b3dd")
#else
#set($bg = "#8BE75A")
#end

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

   </tbody>
 </table>
</fieldset>	
<fieldset>
<legend>Harta melebihi RM 2 juta </legend>
	
<table border="0" align="center" width="100%" >
 	
    <tr bgcolor="$bg">
  	<td >1.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporan1()" class="style1" >PINDAH KE MAHKAMAH TINGGI (MENGIKUT UNIT)</a>
 	</td>
   	</tr>
    	
   	<tr >
  	<td >2.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporan2()" class="style1" >PINDAH KE MAHKAMAH TINGGI (MENGIKUT UNIT & TAHUN)</a>
 	</td>
   	</tr>
    
	
   	<tr bgcolor="$bg">
  	<td >3.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporan3()" class="style1" >PINDAH KE MAHKAMAH TINGGI (MENGIKUT UNIT & SELANG MASA)</a>
 	</td>
   	</tr>
    
    	
   	<tr >
  	<td >4.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporan4()" class="style1" >PINDAH KE MAHKAMAH TINGGI (MENGIKUT UNIT & BULAN)</a>
 	</td>
   	</tr>
   	
   	
    <tr bgcolor="$bg">
  	<td >5.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporan5()" class="style1" >PINDAH KE MAHKAMAH TINGGI (MENGIKUT NEGERI)</a>
 	</td>
   	</tr>
    
    	
   	<tr >
  	<td >6.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporan6()" class="style1" >PINDAH KE MAHKAMAH TINGGI (MENGIKUT NEGERI & TAHUN)</a>
 	</td>
   	</tr>
    
    <tr bgcolor="$bg">
  	<td >7.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporan7()" class="style1" >PINDAH KE MAHKAMAH TINGGI (MENGIKUT NEGERI & SELANG MASA)</a>
 	</td>
   	</tr>
    
    	
   	<tr >
  	<td >8.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporan8()" class="style1" >PINDAH KE MAHKAMAH TINGGI (MENGIKUT NEGERI & BULAN)</a>
 	</td>
   	</tr>
   	
   	   	<tr bgcolor="$bg">
  	<td >9.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporan9()" class="style1" >PINDAH KE MAHKAMAH TINGGI (MENGIKUT DAERAH)</a>
 	</td>
   	</tr>	
   	<tr >
  	<td >10.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporan10()" class="style1" >PINDAH KE MAHKAMAH TINGGI (MENGIKUT DAERAH & TAHUN)</a>
 	</td>
   	</tr>	
   	<tr bgcolor="$bg">
  	<td >11.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporan11()" class="style1" >PINDAH KE MAHKAMAH TINGGI (MENGIKUT DAERAH & SELANG MASA)</a>
 	</td>
   	</tr>	
   	<tr >
  	<td >12.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporan12()" class="style1" >PINDAH KE MAHKAMAH TINGGI (MENGIKUT DAERAH & BULAN)</a>
 	</td>
   	</tr>
</table>
   	
<legend>Wasiat </legend>
	
<table border="0" align="center" width="100%" >	

<tr bgcolor="$bg">
   	<!--<tr bgcolor="#8BE75A" >-->
  	<td >1.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporan13()" class="style1" >PINDAH KE MAHKAMAH TINGGI (MENGIKUT UNIT)</a>
 	</td>
   	</tr>	
   	<tr >
  	<td >2.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporan14()" class="style1" >PINDAH KE MAHKAMAH TINGGI (MENGIKUT UNIT & TAHUN)</a>
 	</td>
   	</tr>	
   	<tr bgcolor="$bg">
  	<td >3.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporan15()" class="style1" >PINDAH KE MAHKAMAH TINGGI (MENGIKUT UNIT & SELANG MASA)</a>
 	</td>
   	</tr>	
   	<tr >
  	<td >4.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporan16()" class="style1" >PINDAH KE MAHKAMAH TINGGI (MENGIKUT UNIT & BULAN)</a>
 	</td>
   	</tr>
   		<tr bgcolor="$bg">
  	<td >5.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporan17()" class="style1" >PINDAH KE MAHKAMAH TINGGI (MENGIKUT NEGERI)</a>
 	</td>
   	</tr>	
   	<tr >
  	<td >6.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporan18()" class="style1" >PINDAH KE MAHKAMAH TINGGI (MENGIKUT NEGERI & TAHUN)</a>
 	</td>
   	</tr>	
   	<tr bgcolor="$bg">
  	<td >7.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporan19()" class="style1" >PINDAH KE MAHKAMAH TINGGI (MENGIKUT NEGERI & SELANG MASA)</a>
 	</td>
   	</tr>	
   	<tr >
  	<td >8.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporan20()" class="style1" >PINDAH KE MAHKAMAH TINGGI (MENGIKUT NEGERI & BULAN)</a>
 	</td>
   	</tr>
   	</tr>
   		<tr bgcolor="$bg">
  	<td >9.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporan21()" class="style1" >PINDAH KE MAHKAMAH TINGGI (MENGIKUT DAERAH)</a>
 	</td>
   	</tr>	
   	<tr >
  	<td >10.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporan22()" class="style1" >PINDAH KE MAHKAMAH TINGGI (MENGIKUT DAERAH & TAHUN)</a>
 	</td>
   	</tr>	
   	<tr bgcolor="$bg">
  	<td >11.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporan23()" class="style1" >PINDAH KE MAHKAMAH TINGGI (MENGIKUT DAERAH & SELANG MASA)</a>
 	</td>
   	</tr>	
   	<tr >
  	<td >12.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporan24()" class="style1" >PINDAH KE MAHKAMAH TINGGI (MENGIKUT DAERAH & BULAN)</a>
 	</td>
   	</tr>

</table>
</fieldset>	



	
<script>
window.onload=function()
{
populatedropdown("yeardropdown")
populatedropdown2("yeardropdown2")
}

function PrintLaporan1()
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
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanPindahkeMahkamahTinggi?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_report=lebih2jutaunit&unit="+unit;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
  		if ((document.window != null) && (!hWnd.opener))
  	    hWnd.opener = document.window;
  		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
		}
	
}

function PrintLaporan2()
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
		else if (mula_tahun==""){
			alert("Sila pilih \"Tarikh\" terlebih dahulu.");
			document.${formName}.txdMula.focus(); 
			return;
		}	
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanPindahkeMahkamahTinggi?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_report=lebih2jutaunittahun&unit="+unit;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
  		if ((document.window != null) && (!hWnd.opener))
   	    hWnd.opener = document.window;
  		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
		}
	
}

function PrintLaporan3()
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
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanPindahkeMahkamahTinggi?mula="+mula+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir="+akhir+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_report=lebih2jutaunitselangmasa&unit="+unit;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
  		if ((document.window != null) && (!hWnd.opener))
   	    hWnd.opener = document.window;
   		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
		}
	
}

function PrintLaporan4()
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
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanPindahkeMahkamahTinggi?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_report=lebih2jutaunitbulan&unit="+unit;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
 	  	if ((document.window != null) && (!hWnd.opener))
  	    hWnd.opener = document.window;
   		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
		}
	
}

function PrintLaporan5()
{		
		var negeri = document.${formName}.socNegeri.value;
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
		if(negeri==""){
			alert("Sila pilih \"Negeri\" terlebih dahulu.");
			document.${formName}.socNegeri.focus(); 
			return;
		}	
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanPindahkeMahkamahTinggi?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_report=lebih2jutanegeri&negeri="+negeri;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
  		if ((document.window != null) && (!hWnd.opener))
  	    hWnd.opener = document.window;
  		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
		}
	
}

function PrintLaporan6()
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
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanPindahkeMahkamahTinggi?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_report=lebih2jutanegeritahun&negeri="+negeri;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
  		if ((document.window != null) && (!hWnd.opener))
   	    hWnd.opener = document.window;
  		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
		}
	
}

function PrintLaporan7()
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
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanPindahkeMahkamahTinggi?mula="+mula+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir="+akhir+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_report=lebih2jutanegeriselangmasa&negeri="+negeri;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
  		if ((document.window != null) && (!hWnd.opener))
   	    hWnd.opener = document.window;
   		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
		}
	
}

function PrintLaporan8()
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
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanPindahkeMahkamahTinggi?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_report=lebih2jutanegeribulan&negeri="+negeri;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
 	  	if ((document.window != null) && (!hWnd.opener))
  	    hWnd.opener = document.window;
   		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
		}
	
}


function PrintLaporan9()
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

		var mula = document.${formName}.txdMula.value;
		var akhir = document.${formName}.txdAkhir.value;
		var tarikhsemasa = new Date();
		if(daerah==""){
			alert("Sila pilih \"Daerah\" terlebih dahulu.");
			document.${formName}.socDaerah.focus(); 
			return;
		}	
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanPindahkeMahkamahTinggi?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_report=lebih2jutadaerah&daerah="+daerah;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
   		if ((document.window != null) && (!hWnd.opener))
    	hWnd.opener = document.window;
   		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
		}
	
}

function PrintLaporan10()
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
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanPindahkeMahkamahTinggi?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_report=lebih2jutadaerahtahun&daerah="+daerah;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
    	hWnd.opener = document.window;
   		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
		}
	
}

function PrintLaporan11()
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
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanPindahkeMahkamahTinggi?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_report=lebih2jutadaerahselangmasa&daerah="+daerah;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
   		if ((document.window != null) && (!hWnd.opener))
   	    hWnd.opener = document.window;
   		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
		}
	
}

function PrintLaporan12()
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
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanPindahkeMahkamahTinggi?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_report=lebih2jutadaerahbulan&daerah="+daerah;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
   		if ((document.window != null) && (!hWnd.opener))
   	    hWnd.opener = document.window;
  		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
		}
	
}

//

function PrintLaporan13()
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
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanPindahkeMahkamahTinggi?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_report=wasiatunit&unit="+unit;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
  		if ((document.window != null) && (!hWnd.opener))
  	    hWnd.opener = document.window;
  		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
		}
	
}

function PrintLaporan14()
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
		else if (mula_tahun==""){
			alert("Sila pilih \"Tarikh\" terlebih dahulu.");
			document.${formName}.txdMula.focus(); 
			return;
		}	
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanPindahkeMahkamahTinggi?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_report=wasiatunittahun&unit="+unit;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
  		if ((document.window != null) && (!hWnd.opener))
   	    hWnd.opener = document.window;
  		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
		}
	
}

function PrintLaporan15()
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
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanPindahkeMahkamahTinggi?mula="+mula+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir="+akhir+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_report=wasiatunitselangmasa&unit="+unit;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
  		if ((document.window != null) && (!hWnd.opener))
   	    hWnd.opener = document.window;
   		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
		}
	
}

function PrintLaporan16()
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
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanPindahkeMahkamahTinggi?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_report=wasiatunitbulan&unit="+unit;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
 	  	if ((document.window != null) && (!hWnd.opener))
  	    hWnd.opener = document.window;
   		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
		}
	
}

function PrintLaporan17()
{		
		var negeri = document.${formName}.socNegeri.value;
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
		if(negeri==""){
			alert("Sila pilih \"Negeri\" terlebih dahulu.");
			document.${formName}.socNegeri.focus(); 
			return;
		}	
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanPindahkeMahkamahTinggi?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_report=wasiatnegeri&negeri="+negeri;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
  		if ((document.window != null) && (!hWnd.opener))
  	    hWnd.opener = document.window;
  		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
		}
	
}

function PrintLaporan18()
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
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanPindahkeMahkamahTinggi?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_report=wasiatnegeritahun&negeri="+negeri;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
  		if ((document.window != null) && (!hWnd.opener))
   	    hWnd.opener = document.window;
  		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
		}
	
}

function PrintLaporan19()
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
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanPindahkeMahkamahTinggi?mula="+mula+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir="+akhir+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_report=wasiatnegeriselangmasa&negeri="+negeri;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
  		if ((document.window != null) && (!hWnd.opener))
   	    hWnd.opener = document.window;
   		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
		}
	
}

function PrintLaporan20()
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
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanPindahkeMahkamahTinggi?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_report=wasiatnegeribulan&negeri="+negeri;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
 	  	if ((document.window != null) && (!hWnd.opener))
  	    hWnd.opener = document.window;
   		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
		}
	
}


function PrintLaporan21()
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

		var mula = document.${formName}.txdMula.value;
		var akhir = document.${formName}.txdAkhir.value;
		var tarikhsemasa = new Date();
		if(daerah==""){
			alert("Sila pilih \"Daerah\" terlebih dahulu.");
			document.${formName}.socDaerah.focus(); 
			return;
		}	
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanPindahkeMahkamahTinggi?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_report=wasiatdaerah&daerah="+daerah;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
   		if ((document.window != null) && (!hWnd.opener))
    	hWnd.opener = document.window;
   		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
		}
	
}

function PrintLaporan22()
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
		else
			{
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanPindahkeMahkamahTinggi?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_report=wasiatdaerahtahun&daerah="+daerah;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
    	hWnd.opener = document.window;
   		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
		}
	
}

function PrintLaporan23()
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
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanPindahkeMahkamahTinggi?mula="+mula+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir="+akhir+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_report=wasiatdaerahselangmasa&daerah="+daerah;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
   		if ((document.window != null) && (!hWnd.opener))
   	    hWnd.opener = document.window;
   		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
		}
	
}

function PrintLaporan24()
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
		var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanPindahkeMahkamahTinggi?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&jenis_report=wasiatdaerahbulan&daerah="+daerah;
		var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
   		if ((document.window != null) && (!hWnd.opener))
   	    hWnd.opener = document.window;
  		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
		}
	
}

//

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


