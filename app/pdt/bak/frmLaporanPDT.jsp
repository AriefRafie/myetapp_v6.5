<style type="text/css">
<!--
.style1 {color: #0000FF}
.style2 {color: #FF0000}
-->
</style>
<fieldset>
<legend>Cetak Laporan</legend>
<input type="hidden" name="action" value="$action" id="action">
<input type="hidden" name="BulanDariNo" value="$BulanDariNo" id="BulanDariNo">
<input type="hidden" name="BulanDariNama" value="$BulanDariNama" id="BulanDariNama">
<input type="hidden" name="BulanHinggaNo" value="$BulanHinggaNo" id="BulanHinggaNo">
<input type="hidden" name="BulanHinggaNama" value="$BulanHinggaNama" id="BulanHinggaNama">
<input type="hidden" name="tahun" value="$tahun" id="tahun">

<table width="100%" border="0">
  <tr>
    <td width="21%"><div align="left">Tahun</div></td><td> : </td>
    <td width="79%"><select name="sbcTahun1" id="sbcTahun1" style="width:auto">
      <option value="0" $selectedthn1 >00</option>
      <option value="1" $selectedthn11 >19</option>
      <option value="2" $selectedthn12 >20</option>
    </select>
      <select name="sbcTahun2" id="sbcTahun2" style="width:auto">
        <option value="0" $selectedthn2 >0</option>
        <option value="1" $selectedthn21 >1</option>
        <option value="2" $selectedthn22 >2</option>
        <option value="3" $selectedthn23 >3</option>
        <option value="4" $selectedthn24 >4</option>
        <option value="5" $selectedthn25 >5</option>
        <option value="6" $selectedthn26 >6</option>
        <option value="7" $selectedthn27 >7</option>	
        <option value="8" $selectedthn28 >8</option>
        <option value="9" $selectedthn29 >9</option>
      </select>
      <select name="sbcTahun3" id="sbcTahun3" style="width:auto">
        <option value="0" $selectedthn3 >0</option>
        <option value="1" $selectedthn31 >1</option>
        <option value="2" $selectedthn32 >2</option>
        <option value="3" $selectedthn33 >3</option>
        <option value="4" $selectedthn34 >4</option>
        <option value="5" $selectedthn35 >5</option>
        <option value="6" $selectedthn36 >6</option>
        <option value="7" $selectedthn37 >7</option>
        <option value="8" $selectedthn38 >8</option>
        <option value="9" $selectedthn39 >9</option>      </select></td>
  </tr>
  <tr>
    <td><div align="left">#if ($view=='error') <span class="style2">*</span>#end Dari Bulan</div></td><td> : </td>
    <td><select name="socDariBulan" id="socDariBulan">
      <option value="00" 	  	  $selecteddb>-Sila Pilih Bulan-</option>
      <option value="01/Januari"   $selecteddb01 >Januari</option>
      <option value="02/Febuari"   $selecteddb02 >Febuari</option>
      <option value="03/Mac"       $selecteddb03 >Mac</option>
      <option value="04/April" 	  $selecteddb04 >April</option>
      <option value="05/Mei" 	  $selecteddb05 >Mei</option>
      <option value="06/Jun" 	  $selecteddb06 >Jun</option>
      <option value="07/Julai" 	  $selecteddb07 >Julai</option>
      <option value="08/Ogos" 	  $selecteddb08 >Ogos</option>
      <option value="09/September" $selecteddb09 >September</option>
      <option value="10/Oktober"  $selecteddb10 >Oktober</option>
      <option value="11/November" $selecteddb11 >November</option>
      <option value="12/Disember" $selecteddb12 >Disember</option>
    </select></td>
  </tr>
    <tr>
    <td> <div align="left">#if ($view=='error') <span class="style2">*</span>#end Hingga Bulan</div></td><td> : </td>
    <td><select name="socKeBulan" id="socKeBulan">
      <option value="00" 	  	  $selectedhb>-Sila Pilih Bulan-</option>
      <option value="01/Januari"   $selectedhb01 >Januari</option>
      <option value="02/Febuari"   $selectedhb02 >Febuari</option>
      <option value="03/Mac"       $selectedhb03 >Mac</option>
      <option value="04/April" 	  $selectedhb04 >April</option>
      <option value="05/Mei" 	  $selectedhb05 >Mei</option>
      <option value="06/Jun" 	  $selectedhb06 >Jun</option>
      <option value="07/Julai" 	  $selectedhb07 >Julai</option>
      <option value="08/Ogos" 	  $selectedhb08 >Ogos</option>
      <option value="09/September" $selectedhb09 >September</option>
      <option value="10/Oktober"  $selectedhb10 >Oktober</option>
      <option value="11/November" $selectedhb11 >November</option>
      <option value="12/Disember" $selectedhb12 >Disember</option>
    </select></td>
  </tr>
  <tr><td></td>
    <td>&nbsp;</td>
  <td><div align="left">
    <input name="cmdCetak" type="button" value="Jana Laporan" onClick="janamesyuarat()">
  </div></td>
  </tr>
</table>
<br/>
<br/>
#if ($view=='error')
<table width="100%" bordercolor="#000000" bgcolor="#CC0000" align="center">
<tr><td><div align="center">Sila Pilih "Hingga Bulan" lebih besar atau sama dengan "Dari Bulan" </div></td></tr>
</table>
#end

#if ($view == 'view')
<br /><!--table><tr>
<td bgcolor="#CC6600"><div align="center"><a href="javascript:janamesyuarat_test('$ahli.id_Ahlimesyuarat','papar')" class="style1 style1 style1">sample</a></div></td>
</tr></table-->
<fieldset>
<table border="0">
<tr bgcolor="#999999"><td colspan="3"><div align="center"><strong>Laporan</strong></div></td></tr>
    <tr>
    <td bgcolor="#CC6600"><div align="center"><a href="javascript:janamesyuaratAkta()" class="style1 style1 style1"><ul>LAPORAN AKTA  MENGIKUT TAHUN <strong>$tahun</strong> , BULAN <strong>$BulanDariNama</strong> HINGGA <strong>$BulanHinggaNama</strong></ul></a></div></td>
    <td bgcolor="#CC6600"><div align="center"><a href="javascript:janamesyuaratPekeliling()" class="style1"><ul>LAPORAN PEKELILING MENGIKUT TAHUN <strong>$tahun</strong> , BULAN<strong> $BulanDariNama</strong> HINGGA <strong>$BulanHinggaNama</strong></ul></a></div></td>    
    <td bgcolor="#CC6600"><div align="center"><a href="javascript:janamesyuaratEnakmen()" class="style1"><ul>LAPORAN ENAKMEN MENGIKUT TAHUN <strong>$tahun</strong> , BULAN<strong> $BulanDariNama</strong> HINGGA <strong>$BulanHinggaNama</strong></ul></a></div></td>
    </tr>
</table> 
</fieldset>
#end
</fieldset>

<script>
function janamesyuarat(){

			if (document.${formName}.sbcTahun1.value == "0"){
				alert('Sila pilih " Tahun " terlebih dahulu.');
				document.${formName}.sbcTahun1.focus();
				return;
			}
						if (document.${formName}.socDariBulan.value == "0"){
				alert('Sila pilih " Dari Bulan " terlebih dahulu.');
				document.${formName}.socDariBulan.focus();
				return;
			}
						if (document.${formName}.socKeBulan.value == "0"){
				alert('Sila pilih " Hingga Bulan" terlebih dahulu.');
				document.${formName}.socKeBulan.focus();
				return;
			}
				
	document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmLaporanPDT&action=jana";
	document.${formName}.submit();
}
function janamesyuaratAkta(){
	//alert(document.${formName}.sbcTahun1.value);
	var Tahun = document.${formName}.tahun.value;
	var bulan1 =  document.${formName}.BulanDariNo.value;;
	var bulan2 = document.${formName}.BulanHinggaNo.value; ;
	var nama_bulan1 = document.${formName}.BulanDariNama.value; ;
	var nama_bulan2 = document.${formName}.BulanHinggaNama.value; ;
	//alert(
	var url = "../servlet/ekptg.report.pdt.LaporanBulanan?jrxmlfile=LAPORAN_AKTA_MENGIKUT_TAHUNAN_DAN_BULANAN&Tahun="+Tahun+"&bulan1="+bulan1+"&bulan2="+bulan2+"&nama_bulan1="+nama_bulan1+"&nama_bulan2="+nama_bulan2;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}
function janamesyuaratPekeliling(){
	//alert(document.${formName}.sbcTahun1.value);
	var Tahun = document.${formName}.tahun.value;
	var bulan1 =  document.${formName}.BulanDariNo.value;;
	var bulan2 = document.${formName}.BulanHinggaNo.value; ;
	var nama_bulan1 = document.${formName}.BulanDariNama.value; ;
	var nama_bulan2 = document.${formName}.BulanHinggaNama.value; ;
	//alert(
	var url = "../servlet/ekptg.report.pdt.LaporanBulanan?jrxmlfile=LAPORAN_PEKELILING_MENGIKUT_TAHUNAN_DAN_BULANAN&Tahun="+Tahun+"&bulan1="+bulan1+"&bulan2="+bulan2+"&nama_bulan1="+nama_bulan1+"&nama_bulan2="+nama_bulan2;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}
function janamesyuarat_test(){
	//alert(document.${formName}.sbcTahun1.value);
	var Tahun = document.${formName}.tahun.value;
	var bulan1 =  document.${formName}.BulanDariNo.value;;
	var bulan2 = document.${formName}.BulanHinggaNo.value; ;
	var nama_bulan1 = document.${formName}.BulanDariNama.value; ;
	var nama_bulan2 = document.${formName}.BulanHinggaNama.value; ;
	//alert(
	var url = "../servlet/ekptg.report.pdt.Laporan1?jrxmlfile=lptrtest&Tahun="+Tahun+"&bulan1="+bulan1+"&bulan2="+bulan2+"&nama_bulan1="+nama_bulan1+"&nama_bulan2="+nama_bulan2;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}
function janamesyuaratEnakmen(){
	//alert(document.${formName}.sbcTahun1.value);
	var Tahun = document.${formName}.tahun.value;
	var bulan1 =  document.${formName}.BulanDariNo.value;;
	var bulan2 = document.${formName}.BulanHinggaNo.value; ;
	var nama_bulan1 = document.${formName}.BulanDariNama.value; ;
	var nama_bulan2 = document.${formName}.BulanHinggaNama.value; ;
	//alert(
	var url = "../servlet/ekptg.report.pdt.LaporanBulanan?jrxmlfile=LAPORAN_ENAKMEN_MENGIKUT_TAHUNAN_DAN_BULANAN&Tahun="+Tahun+"&bulan1="+bulan1+"&bulan2="+bulan2+"&nama_bulan1="+nama_bulan1+"&nama_bulan2="+nama_bulan2;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}

function janamesyuarat1(){
	//alert(document.${formName}.sbcTahun1.value);
	var var1 = 	"2009" ;
	//alert(
	var url = "../servlet/ekptg.report.pdt.Laporan1?var1="+var1;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	
}
</script>
