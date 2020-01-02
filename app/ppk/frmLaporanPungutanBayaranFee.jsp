
<br/>
<form name="f1" method="POST">
<fieldset>
<legend>Laporan Pungutan Bayaran dan Fee</legend>

<fieldset>
<table border="0" align="center" width="100%">
      
      <tr> 
      	
        <td width="30%" align="left" ><div align="right">Negeri </div></td>
        <td width="70%">:&nbsp;$selectNegeri</td>
      </tr>
     
	  <tr> 
        <td align="left" ><div align="right">Unit </div></td>
        <td>:&nbsp;$selectUnit</td>
      </tr>
      
      <tr> 
        <td align="left"><div align="right">Bulan </div></td>
        <td>:&nbsp;<select name="bulan" style="width: 100px;">
        #if($bulan=="")
		<option value="" selected="selected">SILA PILIH</option>
		<option value="1">JANUARI</option>
		<option value="2">FEBUARI</option>
		<option value="3">MAC</option>
		<option value="4">APRIL</option>
		<option value="5">MEI</option>
		<option value="6">JUN</option>
		<option value="7">JULAI</option>
		<option value="8">OGOS</option>
		<option value="9">SEPTEMBER</option>
		<option value="10">OKTOBER</option>
		<option value="11">NOVEMBER</option>
		<option value="12">DISEMBER</option>
		#end
		#if($bulan=="1")
        <option value="">SILA PILIH</option>
		<option value="1" selected="selected">JANUARI</option>
		<option value="2">FEBUARI</option>
		<option value="3">MAC</option>
		<option value="4">APRIL</option>
		<option value="5">MEI</option>
		<option value="6">JUN</option>
		<option value="7">JULAI</option>
		<option value="8">OGOS</option>
		<option value="9">SEPTEMBER</option>
		<option value="10">OKTOBER</option>
		<option value="11">NOVEMBER</option>
		<option value="12">DISEMBER</option>
		#end
		#if($bulan=="2")
        <option value="">SILA PILIH</option>
		<option value="1">JANUARI</option>
        <option value="2" selected="selected">FEBUARI</option>
		<option value="3">MAC</option>
		<option value="4">APRIL</option>
		<option value="5">MEI</option>
		<option value="6">JUN</option>
		<option value="7">JULAI</option>
		<option value="8">OGOS</option>
		<option value="9">SEPTEMBER</option>
		<option value="10">OKTOBER</option>
		<option value="11">NOVEMBER</option>
		<option value="12">DISEMBER</option>
		#end
		#if($bulan=="3")
		
		<option value="">SILA PILIH</option>
		<option value="1">JANUARI</option>
		<option value="2">FEBUARI</option>
        <option value="3" selected="selected">MAC</option>
		<option value="4">APRIL</option>
		<option value="5">MEI</option>
		<option value="6">JUN</option>
		<option value="7">JULAI</option>
		<option value="8">OGOS</option>
		<option value="9">SEPTEMBER</option>
		<option value="10">OKTOBER</option>
		<option value="11">NOVEMBER</option>
		<option value="12">DISEMBER</option>
		#end
		#if($bulan=="4")
        <option value="">SILA PILIH</option>
		<option value="1">JANUARI</option>
		<option value="2">FEBUARI</option>
		<option value="3">MAC</option>
        <option value="4" selected="selected">APRIL</option>
		<option value="5">MEI</option>
		<option value="6">JUN</option>
		<option value="7">JULAI</option>
		<option value="8">OGOS</option>
		<option value="9">SEPTEMBER</option>
		<option value="10">OKTOBER</option>
		<option value="11">NOVEMBER</option>
		<option value="12">DISEMBER</option>
		#end
		#if($bulan=="5")
         <option value="">SILA PILIH</option>
		<option value="1">JANUARI</option>
		<option value="2">FEBUARI</option>
		<option value="3">MAC</option>
		<option value="4">APRIL</option>
        <option value="5" selected="selected">MEI</option>
		<option value="6">JUN</option>
		<option value="7">JULAI</option>
		<option value="8">OGOS</option>
		<option value="9">SEPTEMBER</option>
		<option value="10">OKTOBER</option>
		<option value="11">NOVEMBER</option>
		<option value="12">DISEMBER</option>
		#end
		#if($bulan=="6")
		<option value="">SILA PILIH</option>
		<option value="1">JANUARI</option>
		<option value="2">FEBUARI</option>
		<option value="3">MAC</option>
		<option value="4">APRIL</option>
		<option value="5">MEI</option>
        <option value="6" selected="selected">JUN</option>
		<option value="7">JULAI</option>
		<option value="8">OGOS</option>
		<option value="9">SEPTEMBER</option>
		<option value="10">OKTOBER</option>
		<option value="11">NOVEMBER</option>
		<option value="12">DISEMBER</option>
		#end
		#if($bulan=="7")
		<option value="">SILA PILIH</option>
		<option value="1">JANUARI</option>
		<option value="2">FEBUARI</option>
		<option value="3">MAC</option>
		<option value="4">APRIL</option>
		<option value="5">MEI</option>
		<option value="6">JUN</option>
        <option value="7" selected="selected">JULAI</option>
		<option value="8">OGOS</option>
		<option value="9">SEPTEMBER</option>
		<option value="10">OKTOBER</option>
		<option value="11">NOVEMBER</option>
		<option value="12">DISEMBER</option>
		#end
		#if($bulan=="8")
		<option value="">SILA PILIH</option>
		<option value="1">JANUARI</option>
		<option value="2">FEBUARI</option>
		<option value="3">MAC</option>
		<option value="4">APRIL</option>
		<option value="5">MEI</option>
		<option value="6">JUN</option>
		<option value="7">JULAI</option>
        <option value="8" selected="selected">OGOS</option>
		<option value="9">SEPTEMBER</option>
		<option value="10">OKTOBER</option>
		<option value="11">NOVEMBER</option>
		<option value="12">DISEMBER</option>
		#end
		#if($bulan=="9")
		
		<option value="">SILA PILIH</option>
		<option value="1">JANUARI</option>
		<option value="2">FEBUARI</option>
		<option value="3">MAC</option>
		<option value="4">APRIL</option>
		<option value="5">MEI</option>
		<option value="6">JUN</option>
		<option value="7">JULAI</option>
		<option value="8">OGOS</option>
        <option value="9" selected="selected">SEPTEMBER</option>
		<option value="10">OKTOBER</option>
		<option value="11">NOVEMBER</option>
		<option value="12">DISEMBER</option>
		#end
		#if($bulan=="10")
		
		<option value="">SILA PILIH</option>
		<option value="1">JANUARI</option>
		<option value="2">FEBUARI</option>
		<option value="3">MAC</option>
		<option value="4">APRIL</option>
		<option value="5">MEI</option>
		<option value="6">JUN</option>
		<option value="7">JULAI</option>
		<option value="8">OGOS</option>
		<option value="9">SEPTEMBER</option>
        <option value="10" selected="selected">OKTOBER</option>
		<option value="11">NOVEMBER</option>
		<option value="12">DISEMBER</option>
		#end
		#if($bulan=="11")
		
		<option value="">SILA PILIH</option>
		<option value="1">JANUARI</option>
		<option value="2">FEBUARI</option>
		<option value="3">MAC</option>
		<option value="4">APRIL</option>
		<option value="5">MEI</option>
		<option value="6">JUN</option>
		<option value="7">JULAI</option>
		<option value="8">OGOS</option>
		<option value="9">SEPTEMBER</option>
		<option value="10">OKTOBER</option>
        <option value="11" selected="selected">NOVEMBER</option>
		<option value="12">DISEMBER</option>
		#end
		#if($bulan=="12")
		
		<option value="">SILA PILIH</option>
		<option value="1">JANUARI</option>
		<option value="2">FEBUARI</option>
		<option value="3">MAC</option>
		<option value="4">APRIL</option>
		<option value="5">MEI</option>
		<option value="6">JUN</option>
		<option value="7">JULAI</option>
		<option value="8">OGOS</option>
		<option value="9">SEPTEMBER</option>
		<option value="10">OKTOBER</option>
		<option value="11">NOVEMBER</option>
        <option value="12" selected="selected">DISEMBER</option>
		#end
		
        </select></td>
      </tr>
      
	  <tr> 
        <td align="left"><div align="right">Tahun </div></td>
        <td>:&nbsp;$selectTahun</td>
      </tr>  
     </table> 
  </fieldset>  
  
  <table border="0" align="center" width="100%">  
	  <tr> 
        <td align="center"><input type="button" name="cmdSemak" value="Semak" onClick="semak()" ></td>
      </tr>
  </table>	
  
#set($x="readonly")
#set($xx="class=disabled")
<fieldset>
<br/>
	<table border="0" align="center" width="100%">
   
	  <tr> 
	  	<td width="10%">&nbsp;</td>
	  	<td align="center" width="3%">1.</td>
        <td width="40%">Pengeluaran Perintah (Borang E,F,FF,T,N)</td>
        <td width="47%">:&nbsp;<input type="text" name="bayaran1" size="10" value="$!Util.formatDecimal($pengeluaran_perintah)" style="text-align:right" $xx $x></td>
      </tr>
      
	  <tr> 
	  	<td>&nbsp;</td>
	  	<td align="center">2.</td>
        <td>Salinan Perintah Pembahagian</td>
        <td>:&nbsp;<input type="text" name="bayaran2" size="10" value="$!Util.formatDecimal($salinan_perintah)" style="text-align:right" $xx $x></td>
      </tr>
      
	   <tr> 
	   	<td>&nbsp;</td>
	    <td align="center">3.</td>
        <td>Fee Rayuan</td>
        <td>:&nbsp;<input type="text" name="bayaran3" size="10" value="$!Util.formatDecimal($fee_rayuan)" style="text-align:right" $xx $x></td>
      </tr>
      
	   <tr> 
	   	<td>&nbsp;</td>
	    <td align="center">4.</td>
        <td>Salinan dokumen - dokumen yang dipohon</td>
        <td>:&nbsp;<input type="text" name="bayaran4" size="10" value="$!Util.formatDecimal($salinan_dokumen)" style="text-align:right" $xx $x></td>
      </tr>
      
	   <tr>
	   	<td>&nbsp;</td> 
	    <td align="center">5.</td>
        <td>Duti Harta Pusaka</td>
        <td>:&nbsp;<input type="text" name="bayaran5" size="10" value="$!Util.formatDecimal($duti)" style="text-align:right" $xx $x></td>
      </tr>
      
	   <tr> 
	   	<td>&nbsp;</td>
	    <td align="center">6.</td>
        <td>Pembelian Bahagian Baitulmal</td>
        <td>:&nbsp;<input type="text" name="bayaran6" size="10" value="$!Util.formatDecimal($baitulmal)" style="text-align:right" $xx $x></td>
      </tr>
      
	   <tr> 
	   	<td>&nbsp;</td>
	    <td align="center">7.</td>
        <td>Lain - Lain Bayaran - Pendaftaran Hakmilik</td>
        <td>:&nbsp;<input type="text" name="bayaran7" value="$!Util.formatDecimal($lain2)" style="text-align:right" size="10" $xx $x></td>
      </tr>	 
      </table>
</fieldset>	

<table border="0" align="center" width="100%">	
	<tr>
		<td align="center"><input type="button" name="cmdCetak" value="Cetak" onClick="cetak()"></td> 
	</tr>
</table>

</fieldset>

<input type="hidden" name="command" >
<input type="hidden" name="iddaerah" value="$iddaerah">
</form>	
	
	
<script>


function doChangeUnit() {
	document.f1.command.value = "doChangeUnit";
	document.f1.action = "";
	document.f1.submit();
}
function semak() {

	if (document.f1.socNegeri.value == "")
	{
		alert("Sila pilih \"Negeri\" terlebih dahulu.");
		document.f1.socNegeri.focus(); 
		return;
	}
	else if (document.f1.socUnit.value == "")
	{
		alert("Sila pilih \"Unit\" terlebih dahulu.");
		document.f1.socUnit.focus(); 
		return;
	}
	else if (document.f1.bulan.value == "")
	{
		alert("Sila pilih \"Bulan\" terlebih dahulu.");
		document.f1.bulan.focus(); 
		return;
	}
	else if (document.f1.socTahun.value == "")
	{
		alert("Sila pilih \"Tahun\" terlebih dahulu.");
		document.f1.socTahun.focus(); 
		return;
	}
	else{
	document.f1.command.value = "semak";
	document.f1.action = "";
	document.f1.submit();
	}
}
function cetak() {

	var idnegeri = document.f1.socNegeri.value;
	var iddaerah = document.f1.iddaerah.value;
	var idjkptg = document.f1.socUnit.value;
	var tahun = document.f1.socTahun.value;
	var bulan = document.f1.bulan.value;

	if (document.f1.bayaran1.value == "" && document.f1.bayaran2.value == "" && document.f1.bayaran3.value == ""
		&& document.f1.bayaran4.value == "" && document.f1.bayaran5.value == "" && document.f1.bayaran6.value == "")
	{
		alert("Sila pastikan maklumat laporan disemak terlebih dahulu.");
		return;
	}
	else
	{
		var url = "../servlet/ekptg.report.ppk.LaporanPungutanBayaran?idnegeri="+idnegeri+"&iddaerah="+iddaerah+"&idjkptg="+idjkptg+"&bulan="+bulan+"&tahun="+tahun;
    	var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
    	if (hWnd.focus != null) hWnd.focus();	
	}
}
</script>


