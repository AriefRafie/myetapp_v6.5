<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<p></p>
<fieldset>
<legend>Ringkasan Sebab-sebab Kes Tunggakan / Baki</legend>
<p></p>
<fieldset>
<table border="0" align="center" width="100%">
    <tbody>
      <tr> 
        <td width="30%" height="24px" scope="row" align="right" >Negeri : </td>
        <td width="70%">
		<!--<select name="id_negeri" style="width: 280px;" onchange="javascript:doAjaxCall${formname}('doChanges')">
		<option value="0">Sila Pilih</option>
		#foreach ($x in $listNegeri)
		<option value="$x.idnegeri">$x.namanegeri</option>
		#end
		</select>-->
		$selectNegeri
		</td>
      </tr>
	  <tr> 
        <td scope="row" align="right">&nbsp;Unit :</td>
        <td>
        <!--<select name="socUnit" style="width: 280px;">
		<option value="0">SILA PILIH</option>
		//#foreach ($i in $selectUnit)
          #if($showUnit == 'no')
          <option value="$i.iddaerah">$i.namapejabat $i.alamatOne</option>
        //  #else
			<option value="$i.iddaerah" selected="selected">$i.namapejabat $i.alamatOne</option>
        //  #end
		#end
		</select>-->
      $selectUnit
        </td>
      </tr>
	  <tr> 
        <td scope="row" align="right">&nbsp;Tahun :</td>
        <td>$selectTahun
       
         
        <!--<select name="socTahun" style="width: 120px;">
		<option value="0">Sila Pilih</option>
		<option value="2004">2004</option>
		<option value="2005">2005</option>
		<option value="2006">2006</option>
		<option value="2007">2007</option>
		<option value="2008">2008</option>
		<option value="2009">2009</option>
		<option value="2010">2010</option>
		<option value="2011">2011</option>
		</select>-->
        </td>
      </tr>
	  <tr> 
        <td scope="row" align="right">&nbsp;Bulan :</td>
        <td><select name="socBulan" style="width: 100px;">
        #if($bulan == "0")
		<option value="0" selected="selected">SILA PILIH</option>
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
        #elseif($bulan == "01")
        <option value="0">SILA PILIH</option>
		<option value="01" selected="selected">JANUARI</option>
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
        #elseif($bulan == "02")
        <option value="0">SILA PILIH</option>
		<option value="01">JANUARI</option>
		<option value="02" selected="selected">FEBRUARI</option>
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
        #elseif($bulan == "03")
        <option value="0">SILA PILIH</option>
		<option value="01">JANUARI</option>
		<option value="02">FEBRUARI</option>
		<option value="03" selected="selected">MAC</option>
		<option value="04">APRIL</option>
		<option value="05">MEI</option>
		<option value="06">JUN</option>
		<option value="07">JULAI</option>
		<option value="08">OGOS</option>
		<option value="09">SEPTEMBER</option>
		<option value="10">OKTOBER</option>
		<option value="11">NOVEMBER</option>
		<option value="12">DISEMBER</option>
         #elseif($bulan == "04")
        <option value="0">SILA PILIH</option>
		<option value="01">JANUARI</option>
		<option value="02">FEBRUARI</option>
		<option value="03">MAC</option>
		<option value="04" selected="selected">APRIL</option>
		<option value="05">MEI</option>
		<option value="06">JUN</option>
		<option value="07">JULAI</option>
		<option value="08">OGOS</option>
		<option value="09">SEPTEMBER</option>
		<option value="10">OKTOBER</option>
		<option value="11">NOVEMBER</option>
		<option value="12">DISEMBER</option>
         #elseif($bulan == "05")
        <option value="0">SILA PILIH</option>
		<option value="01">JANUARI</option>
		<option value="02">FEBRUARI</option>
		<option value="03">MAC</option>
		<option value="04">APRIL</option>
		<option value="05" selected="selected">MEI</option>
		<option value="06">JUN</option>
		<option value="07">JULAI</option>
		<option value="08">OGOS</option>
		<option value="09">SEPTEMBER</option>
		<option value="10">OKTOBER</option>
		<option value="11">NOVEMBER</option>
		<option value="12">DISEMBER</option>
         #elseif($bulan == "06")
        <option value="0">SILA PILIH</option>
		<option value="01">JANUARI</option>
		<option value="02">FEBRUARI</option>
		<option value="03">MAC</option>
		<option value="04">APRIL</option>
		<option value="05">MEI</option>
		<option value="06" selected="selected">JUN</option>
		<option value="07">JULAI</option>
		<option value="08">OGOS</option>
		<option value="09">SEPTEMBER</option>
		<option value="10">OKTOBER</option>
		<option value="11">NOVEMBER</option>
		<option value="12">DISEMBER</option>
         #elseif($bulan == "07")
        <option value="0">SILA PILIH</option>
		<option value="01">JANUARI</option>
		<option value="02">FEBRUARI</option>
		<option value="03">MAC</option>
		<option value="04">APRIL</option>
		<option value="05">MEI</option>
		<option value="06">JUN</option>
		<option value="07" selected="selected">JULAI</option>
		<option value="08">OGOS</option>
		<option value="09">SEPTEMBER</option>
		<option value="10">OKTOBER</option>
		<option value="11">NOVEMBER</option>
		<option value="12">DISEMBER</option>
         #elseif($bulan == "08")
        <option value="0">SILA PILIH</option>
		<option value="01">JANUARI</option>
		<option value="02">FEBRUARI</option>
		<option value="03">MAC</option>
		<option value="04">APRIL</option>
		<option value="05">MEI</option>
		<option value="06">JUN</option>
		<option value="07">JULAI</option>
		<option value="08" selected="selected">OGOS</option>
		<option value="09">SEPTEMBER</option>
		<option value="10">OKTOBER</option>
		<option value="11">NOVEMBER</option>
		<option value="12">DISEMBER</option>
         #elseif($bulan == "09")
        <option value="0">SILA PILIH</option>
		<option value="01">JANUARI</option>
		<option value="02">FEBRUARI</option>
		<option value="03">MAC</option>
		<option value="04">APRIL</option>
		<option value="05">MEI</option>
		<option value="06">JUN</option>
		<option value="07">JULAI</option>
		<option value="08">OGOS</option>
		<option value="09" selected="selected">SEPTEMBER</option>
		<option value="10">OKTOBER</option>
		<option value="11">NOVEMBER</option>
		<option value="12">DISEMBER</option>
         #elseif($bulan == "10")
        <option value="0">SILA PILIH</option>
		<option value="01">JANUARI</option>
		<option value="02">FEBRUARI</option>
		<option value="03">MAC</option>
		<option value="04">APRIL</option>
		<option value="05">MEI</option>
		<option value="06">JUN</option>
		<option value="07">JULAI</option>
		<option value="08">OGOS</option>
		<option value="09">SEPTEMBER</option>
		<option value="10" selected="selected">OKTOBER</option>
		<option value="11">NOVEMBER</option>
		<option value="12">DISEMBER</option>
         #elseif($bulan == "11")
        <option value="0">SILA PILIH</option>
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
		<option value="11" selected="selected">NOVEMBER</option>
		<option value="12">DISEMBER</option>
         #elseif($bulan == "12")
        <option value="0">SILA PILIH</option>
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
		<option value="12" selected="selected">DISEMBER</option>
        #end 
		</select></td>
      </tr>
	  <tr> 
	  	<td>&nbsp;</td>
        <td scope="row" height="50px" valign="bottom"><input type="button" name="cmdSemak" value="Semak" onClick="getSemak()"></td>
      </tr>
	  </tbody>
      </table>
	  </fieldset>
      <br />
	  <fieldset>
<table border="0" align="center" width="100%">
    <tbody>
      <tr> 
        <td width="50%">
		<table border="0" align="center" width="100%">
    	<tbody>
      	<tr>
	  	<td width="80%" align="right">Masih Menunggu Borang C :</td>
		<td width="20%"><input type="text" name="txtPrmhnnBorangAP" size="10" value="$!A"></td>
	  	</tr>
		<tr>
	  	<td width="80%" align="right">Menunggu Maklumat Tambahan :</td>
		<td width="20%"><input name="txtPrmhnnBorangAP" type="text" value="$!N" size="10"></td>
	  	</tr>
			<tr>
	  	<td width="80%" align="right">Menunggu Siasatan / Nilaian :</td>
		<td width="20%"><input type="text" name="txtPrmhnnBorangAP" size="10" value="$!B"></td>
	  	</tr>
			<tr>
	  	<td width="80%" align="right">Menunggu Keputusan Rujukan ke Mahkamah Tinggi / Faraid :</td>
		<td width="20%"><input type="text" name="txtPrmhnnBorangAP" size="10" value="$!C"></td>
	  	</tr>
			<tr>
	  	<td width="80%" align="right">Pemohon / Waris Tidak Hadir :</td>
		<td width="20%"><input type="text" name="txtPrmhnnBorangAP" size="10" value="$!D"></td>
	  	</tr>
			<tr>
	  	<td width="80%" align="right">Tunggu Keputusan Rayuan Mahkamah :</td>
		<td width="20%"><input type="text" name="txtPrmhnnBorangAP" size="10" value="$!E"></td>
	  	</tr>
			<tr>
	  	<td width="80%" align="right">Senarai Waris Tidak Lengkap :</td>
		<td width="20%"><input type="text" name="txtPrmhnnBorangAP" size="10" value="$!F"></td>
	  	</tr>
			<tr>
	  	<td width="80%" align="right">Bukti Kematian Simati / Waris Tidak Lengkap :</td>
		<td width="20%"><input type="text" name="txtPrmhnnBorangAP" size="10" value="$!G"></td>
	  	</tr>
	  	</tbody>
		</table>
	  </td>
        <td width="50%"><table border="0" align="center" width="100%">
    	<tbody>
      	<tr>
	  	<td width="50%" align="right">Sebab - sebab Lain :</td>
		<td width="50%"><input type="text" name="txtPrmhnnBorangAP" size="10" value="$!I"></td>
	  	</tr>
		<tr>
	  	<td width="50%" align="right">Kes Yang Siap Diproses Menunggu Bicara / Tarikh Bicara :</td>
		<td width="50%"><input type="text" name="txtPrmhnnBorangAP" size="10" value="$!H"></td>
	  	</tr>
		<tr>
	  	<td width="50%" align="right">Jumlah Belum Selesai :</td>
		<td width="50%"><input name="txtPrmhnnBorangAP" type="text" value="$!J" size="10"></td>
	  	</tr>
		<tr>
	  	<td width="50%" align="right">Jumlah Permohonan Semasa 5 1/2 Bulan Masuk Bulan Laporan :</td>
		<td width="50%"><input type="text" name="txtPrmhnnBorangAP" size="10" value="$!K"></td>
	  	</tr>
		<tr>
	  	<td width="50%" align="right">Jumlah Tunggakkan Sebenar :</td>
		<td width="50%"><input type="text" name="txtPrmhnnBorangAP" size="10" value="$!L"></td>
	  	</tr>
		<tr>
	  	<td width="50%" align="right">Tarikh Laporan Dikeluarkan :</td>
		<td width="50%"><input type="text" name="txtPrmhnnBorangAP" size="10" value="$!M"></td>
	  	</tr>
		<tr>
	  	<td colspan="2">&nbsp;</td>
	  	</tr>
		<tr>
	  	<td colspan="2">&nbsp;</td>
	  	</tr>
	  	</tbody>
		</table></td>
      </tr>
	  </tbody>
      </table>
	  </fieldset>	
	
<p align="center">
<input type="button" name="cmdCetak" value="Cetak" onClick="getCetak()"></p>  
</fieldset>
	<input type="hidden" name="action"/>
<script>


function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}

function getSemak(){
	if (document.${formName}.socNegeri.value=="0"){
		alert("Sila pilih ' Negeri '");
		socNegeri.focus();
	}
	else if (document.${formName}.socUnit.value=="0"){
		alert("Sila pilih ' Unit '");
		socUnit.focus();
	}
	else if (document.${formName}.socTahun.value=="0"){
		alert("Sila pilih ' Tahun '");
		socTahun.focus();
	}
	else if (document.${formName}.socBulan.value=="0"){
		alert("Sila pilih ' Bulan '");
		socBulan.focus();
	}
	else{
		//doAjaxCall${formName}("retrieve_info");
		document.${formName}.command.value = "retrieve_info";
		document.${formName}.action = "";
		document.${formName}.submit();
	}
}

function getCetak(){
	if (document.${formName}.socNegeri.value=="0"){
		alert("Sila pilih ' Negeri '");
		socNegeri.focus();
	}
	else if (document.${formName}.socUnit.value=="0"){
		alert("Sila pilih ' Unit '");
		socUnit.focus();
	}
	else if (document.${formName}.socTahun.value=="0"){
		alert("Sila pilih ' Tahun '");
		socTahun.focus();
	}
	else if (document.${formName}.socBulan.value=="0"){
		alert("Sila pilih ' Bulan '");
		socBulan.focus();
	}
	else{
		
		
		    var url="../servlet/ekptg.report.ppk.RingkasanKesBelumSelesai?NO_NEGERI="+document.${formName}.socNegeri.value+"&NO_UNIT="+document.${formName}.socUnit.value+"&NO_TAHUN="+document.${formName}.socTahun.value+"&NO_BULAN="+document.${formName}.socBulan.value+"&NO_TUNGGAKAN="+$L+"&N0_BLMSELESAI="+$J+"&NO_JUMPRMHNN="+$K;
    		var hWnd=window.open(url,'Cetak2','width=800,height=500, resizable=yes,scrollbars=yes');
		
	}
}
</script>


