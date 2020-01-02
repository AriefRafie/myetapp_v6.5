 <style type="text/css">
  <!--
  .style1 {color: #0033FF}
  -->
  </style>
  <br>
  <br>
<fieldset>
istSenarai1 = $listSenarai1
<legend>Senarai Laporan Kes Tertunggak</legend>
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
      <tr> 
        <td scope="row" align="left">&nbsp;Sebab Tertunggak </td>
        <td>:&nbsp;</td>
        <td>
         <select name="print">
         	 <option value="0">SILA PILIH</option>
  			 <option value="PrintLaporanA">MASIH MENUNGGU BORANG C</option>
 			 <option value="PrintLaporanB">MENUNGGU SIASATAN / NILAIAN</option>
  			 <option value="PrintLaporanC">PEMOHON / WARIS TIDAK HADIR</option>
 			 <option value="PrintLaporanD">SENARAI WARIS TIDAK LENGKAP</option>
 			 <option value="PrintLaporanE">MENUNGGU KEPUTUSAN RUJUKAN KE MAHKAMAH TINGGI</option>
 			 <option value="PrintLaporanF">MENUNGGU KEPUTUSAN RAYUAN MAHKAMAH</option>
  			 <option value="PrintLaporanG">LAIN-LAIN</option>
</select> 
        </td>
        
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
      <td></td>
      <td></td>
      <td><input type="button" value="Semak" onclick="getSemak()"></td>
      
      <tr>
      
      </tr>

   </tbody>
 </table>
</fieldset>	
<fieldset>
<legend>Laporan </legend>
	<!-- 
<table border="0" align="center" width="100%" >
 	
  	<tr bgcolor="#DAFACB">
  	<td >1.</td>
  	
  	<td >
  		<a href="javascript:PrintLaporanA()" class="style1" >Masih Menunggu Borang C</a>
 	</td>
   	</tr>	
   	<tr>
  	<td class="$row">2.</td>
  	
  	<td class="$row">
  		<a href="javascript:PrintLaporanB()" class="style1" >Menunggu Siasatan / Nilaian</a>
 	</td>
   	</tr>	
   	<tr bgcolor="#DAFACB">
  	<td >3.</td>
  	
  	<td >
  		<a href="javascript:PrintLaporanC()" class="style1" >Pemohon / Waris Tidak Hadir</a>
 	</td>
   	</tr>	
   	<tr>
  	<td class="$row">4.</td>
  	
  	<td class="$row">
  		<a href="javascript:PrintLaporanD()" class="style1" >Senarai Waris Tidak Lengkap</a>
 	</td>
   	</tr>	
   		<tr bgcolor="#DAFACB">
  	<td >5.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanE()" class="style1" >Menunggu Keputusan Rujukan ke Mahkamah Tinggi / Faraid</a>
 	</td>
   	</tr>	
   	<tr>
  	<td class="$row">6.</td>
  	
  	<td class="$row">
  		<a href="javascript:PrintLaporanF()" class="style1" >Tunggu Keputusan Rayuan Mahkamah</a>
 	</td>
   	</tr>	
   	
   		<tr bgcolor="#DAFACB">
  	<td >7.</td>
  	
  	<td>
  		<a href="javascript:PrintLaporanG()" class="style1" >Sebab-sebab Lain</a>
 	</td>
   	</tr>	
   	
   	

</table>
 -->
 <p align="center">
<table style="border-color: #369900;" border="1" cellspacing="0">
<tbody>
<tr>
<td style="min-width: 50px;  background-color: #72a942; text-align: center;" rowspan="2"><span style="color: #ffffff;"><strong>Negeri</strong>&nbsp;</span></td>
<td style="min-width: 50px;  background-color: #72a942; width: 90px; text-align: center;" colspan="7"><span style="color: #ffffff;"><strong>Sebab&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong>&nbsp;</span></td>
<td style="min-width: 50px;  background-color: #72a942; width: 70px; text-align: center;" rowspan="2"><span style="color: #ffffff;"><strong>Jumlah</strong>&nbsp;</span></td>
</tr>
<tr>
<td style="min-width: 50px;  background-color: #72a942;width: 90px; text-align: center;"><span style="color: #ffffff;">Menunggu Borang C</span></td>
<td style="min-width: 50px;  background-color: #72a942;width: 90px; text-align: center;"><span style="color: #ffffff;">Menunggu Siasatan / Nilaian</span></td>
<td style="min-width: 50px;  background-color: #72a942;width: 90px; text-align: center;"><span style="color: #ffffff;">Pemohon Waris Tidak Hadir</span></td>
<td style="min-width: 50px;  background-color: #72a942;width: 90px; text-align: center;"><span style="color: #ffffff;">Senarai Waris Tidak Lengkap</span></td>
<td style="min-width: 50px;  background-color: #72a942;width: 90px; text-align: center;"><span style="color: #ffffff;">Menunggu Keputusan Rujukan ke Mahkamah Tinggi</span></td>
<td style="min-width: 50px;  background-color: #72a942;width: 90px; text-align: center;"><span style="color: #ffffff;">Menunggu Keputusan Rayuan Mahkamah</span></td>
<td style="min-width: 50px;  background-color: #72a942;width: 90px; text-align: center;"><span style="color: #ffffff;">Lain-lain</span></td>
</tr>
<tr style="background-color: #C7DEB3;">
<td style="min-width: 50px;">Pulau Pinang</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
</tr>
<tr>
<td style="min-width: 50px;">Terengganu</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
</tr>
<tr style="background-color: #C7DEB3;">
<td style="min-width: 50px;">Kelantan</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
</tr>
<tr>
<td style="min-width: 50px;">Johor</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
</tr>
<tr style="background-color: #C7DEB3;">
<td style="min-width: 50px;">Pahang</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
</tr>
<tr>
<td style="min-width: 50px;">Melaka</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
</tr>
<tr style="background-color: #C7DEB3;">
<td style="min-width: 50px;">Negeri Sembilan</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
</tr>
<tr>
<td style="min-width: 50px;">Kedah</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
</tr>
<tr style="background-color: #C7DEB3;">
<td style="min-width: 50px;">Perak</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
</tr>
<tr>
<td style="min-width: 50px;">Selangor</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
</tr>
<tr style="background-color: #C7DEB3;">
<td style="min-width: 50px;">Perlis</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
</tr>
<tr>
<td style="min-width: 50px;">WP Kuala Lumpur</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
</tr>

<tr style="background-color: #C7DEB3;">
<td style="min-width: 50px;">WP Labuan</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
</tr>
<tr>
<td style="min-width: 50px;">WP Putrajaya</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
</tr>
<tr style="background-color: #C7DEB3;">
<td style="min-width: 50px;  text-align: center;"><strong>Jumlah</strong></td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
<td style="min-width: 50px;">&nbsp;</td>
</tr>
</tbody>
</table>


</fieldset>	



	
<script>
window.onload=function()
{
populatedropdown("yeardropdown")
populatedropdown2("yeardropdown2")
}

function PrintLaporanA()
{
	if (document.${formName}.txdMula.value==""){
		alert("Sila masukkan Tarikh Mula Laporan");
		txdMula.focus();
	}
	else if (document.${formName}.txdAkhir.value==""){
		alert("Sila masukkan Tarikh Akhir Laporan");
		txdAkhir.focus();
	}
	else
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
	
	
	var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanKesTertunggak?print=PrintLaporanA&mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun;
	var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();	
		}
	
}

function PrintLaporanB()
{
	if (document.${formName}.txdMula.value==""){
		alert("Sila masukkan Tarikh Mula Laporan");
		txdMula.focus();
	}
	else if (document.${formName}.txdAkhir.value==""){
		alert("Sila masukkan Tarikh Akhir Laporan");
		txdAkhir.focus();
	}
	else
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
	
	
	var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanKesTertunggak?print=PrintLaporanB&mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun;
	var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();	
		}
	
}

function PrintLaporanC()
{
	if (document.${formName}.txdMula.value==""){
		alert("Sila masukkan Tarikh Mula Laporan");
		txdMula.focus();
	}
	else if (document.${formName}.txdAkhir.value==""){
		alert("Sila masukkan Tarikh Akhir Laporan");
		txdAkhir.focus();
	}
	else
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
	
	
	var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanKesTertunggak?print=PrintLaporanC&mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun;
	var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();	
		}
	
}

function PrintLaporanD()
{
	if (document.${formName}.txdMula.value==""){
		alert("Sila masukkan Tarikh Mula Laporan");
		txdMula.focus();
	}
	else if (document.${formName}.txdAkhir.value==""){
		alert("Sila masukkan Tarikh Akhir Laporan");
		txdAkhir.focus();
	}
	else
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
	
	
	var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanKesTertunggak?print=PrintLaporanD&mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun;
	var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();	
		}
	
}

function PrintLaporanE()
{
	if (document.${formName}.txdMula.value==""){
		alert("Sila masukkan Tarikh Mula Laporan");
		txdMula.focus();
	}
	else if (document.${formName}.txdAkhir.value==""){
		alert("Sila masukkan Tarikh Akhir Laporan");
		txdAkhir.focus();
	}
	else
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
	
	
	var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanKesTertunggak?print=PrintLaporanE&mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun;
	var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();	
		}
	
}

function PrintLaporanF()
{
	if (document.${formName}.txdMula.value==""){
		alert("Sila masukkan Tarikh Mula Laporan");
		txdMula.focus();
	}
	else if (document.${formName}.txdAkhir.value==""){
		alert("Sila masukkan Tarikh Akhir Laporan");
		txdAkhir.focus();
	}
	else
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
	
	
	var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanKesTertunggak?print=PrintLaporanF&mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun;
	var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();	
		}
	
}

function PrintLaporanG()
{
	if (document.${formName}.txdMula.value==""){
		alert("Sila masukkan Tarikh Mula Laporan");
		txdMula.focus();
	}
	else if (document.${formName}.txdAkhir.value==""){
		alert("Sila masukkan Tarikh Akhir Laporan");
		txdAkhir.focus();
	}
	else
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
	
	
	var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanKesTertunggak?print=PrintLaporanG&mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun;
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

function getSemak(){
	var sebab = document.${formName}.print.value;
	var negeri = document.${formName}.socNegeri.value;
	var daerah = document.${formName}.socDaerah.value;
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
	
	
	var url = "../x/${securityToken}/ekptg.view.ppk.PaparanLaporanKesTertunggak?mula_hari="+mula_hari+"&mula_bulan="+mula_bulan+"&mula_tahun="+mula_tahun+"&akhir_hari="+akhir_hari+"&akhir_bulan="+akhir_bulan+"&akhir_tahun="+akhir_tahun+"&negeri="+negeri+"&daerah="+daerah+"&unit="+unit+"&print="+sebab+"&mula="+mula+"&akhir="+akhir;
	var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();

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


